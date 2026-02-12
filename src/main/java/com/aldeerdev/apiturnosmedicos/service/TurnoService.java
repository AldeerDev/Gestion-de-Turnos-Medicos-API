package com.aldeerdev.apiturnosmedicos.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldeerdev.apiturnosmedicos.exception.MedicoOcupadoException;
import com.aldeerdev.apiturnosmedicos.exception.PacienteOcupadoException;
import com.aldeerdev.apiturnosmedicos.exception.TurnoEnElPasadoException;
import com.aldeerdev.apiturnosmedicos.model.Medico;
import com.aldeerdev.apiturnosmedicos.model.Paciente;
import com.aldeerdev.apiturnosmedicos.model.Turno;
import com.aldeerdev.apiturnosmedicos.model.enums.EstadoTurno;
import com.aldeerdev.apiturnosmedicos.repository.TurnoRepository;

@Service
public class TurnoService {

	private static final int DURACION_TURNO_MINUTOS = 30;

	@Autowired
	private TurnoRepository turnoRep;

	@Autowired
	private PacienteService pacienteServ;

	@Autowired
	private MedicoService medicoServ;

	public Turno crearTurno(Long idPaciente, Long idMedico, Turno turno) {
		Paciente paciente = pacienteServ.obtenerPacientePorId(idPaciente);
		Medico medico = medicoServ.obtenerMedicoPorId(idMedico);

		// valida si la fecha del turno no es pasada
		if (turno.getFecha().isBefore(LocalDate.now())) {
			throw new TurnoEnElPasadoException();
		}

		List<Turno> turnos = turnoRep.findAll();

		// validar si el paciente tiene un turno pendiente el la fecha y hora
		boolean pacienteOcupado = turnos.stream().filter(t -> t.getPaciente().getId().equals(paciente.getId()))
				.filter(t -> t.getFecha().equals(turno.getFecha())).anyMatch(t -> t.getHora().equals(turno.getHora()));

		if (pacienteOcupado) {
			throw new PacienteOcupadoException();
		}

		// valida disponibilidad del medico en fecha y hora
		validarMedicoDisponible(medico, turno.getFecha(), turno.getHora());

		turno.setPaciente(paciente);
		turno.setMedico(medico);
		turno.setEstado(EstadoTurno.CONFIRMADO);
		return turnoRep.save(turno);
	}

	public List<Turno> listarTurnos(LocalDate fecha, Long idMedico) {

		Stream<Turno> turnos = turnoRep.findAll().stream();
		
		if (fecha != null) {
			turnos = turnos.filter(t -> t.getFecha().equals(fecha));
		}
		
		if (idMedico != null) {
			turnos = turnos.filter(t -> t.getMedico().getId().equals(idMedico));
		}
		
		return turnos.toList();
	}

	private void validarMedicoDisponible(Medico medico, LocalDate fecha, LocalTime hora) {
		LocalTime finNuevoTurno = hora.plusMinutes(DURACION_TURNO_MINUTOS);
		boolean haySolapamiento = turnoRep.findAll().stream().filter(t -> t.getMedico().getId().equals(medico.getId()))
				.filter(t -> t.getFecha().equals(fecha)).anyMatch(turnoExistente -> {
					LocalTime inicioTurnoExistente = turnoExistente.getHora();
					LocalTime finTurnoExistente = inicioTurnoExistente.plusMinutes(DURACION_TURNO_MINUTOS);

					return hora.isBefore(finTurnoExistente) && finNuevoTurno.isAfter(inicioTurnoExistente);
				});
		if (haySolapamiento) {
			throw new MedicoOcupadoException();
		}
	}
}
