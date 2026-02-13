package com.aldeerdev.apiturnosmedicos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldeerdev.apiturnosmedicos.exception.MedicoInactivoException;
import com.aldeerdev.apiturnosmedicos.exception.MedicoNotFoundException;
import com.aldeerdev.apiturnosmedicos.model.Medico;
import com.aldeerdev.apiturnosmedicos.repository.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;

	public Medico crearMedico(Medico medico) {
		return repository.save(medico);
	}

	public List<Medico> listarMedicosPorEspecialidad(String especialidad) {
		return repository.findAll().stream().filter(m -> m.getEspecialidad().equals(especialidad)).toList();
	}

	public Medico obtenerMedicoPorId(Long id) {
		return repository.findById(id).map(medico -> {
			if (!medico.isActivo()) {
				throw new MedicoInactivoException();
			}
			return medico;
		}).orElseThrow(() -> new MedicoNotFoundException(id));
	}

	public Medico actualizarMedico(Long id, Medico medico) {
		Medico actualizado = obtenerMedicoPorId(id);
		actualizado.setNombre(medico.getNombre());
		actualizado.setApellido(medico.getApellido());
		actualizado.setEspecialidad(medico.getEspecialidad());
		actualizado.setMatricula(medico.getMatricula());
		return repository.save(actualizado);
	}

	public void eliminarMedico(Long id) {
		Medico bajaMedico = repository.findById(id).orElse(null);
		bajaMedico.setActivo(false);
		repository.save(bajaMedico);
	}
}
