package com.aldeerdev.apiturnosmedicos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldeerdev.apiturnosmedicos.dto.medico.MedicoRequestDTO;
import com.aldeerdev.apiturnosmedicos.dto.medico.MedicoResponseDTO;
import com.aldeerdev.apiturnosmedicos.exception.MedicoInactivoException;
import com.aldeerdev.apiturnosmedicos.exception.MedicoNotFoundException;
import com.aldeerdev.apiturnosmedicos.mapper.MedicoMapper;
import com.aldeerdev.apiturnosmedicos.model.Medico;
import com.aldeerdev.apiturnosmedicos.repository.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;

	public MedicoResponseDTO crearMedico(MedicoRequestDTO dto) {
		Medico medicoNuevo = MedicoMapper.toEntity(dto);
		repository.save(medicoNuevo);
		return MedicoMapper.toDTO(medicoNuevo);
	}

	public List<MedicoResponseDTO> listarMedicosPorEspecialidad(String especialidad) {
		return repository.findAll().stream().filter(m -> m.getEspecialidad().equals(especialidad))
				.map(MedicoMapper::toDTO)
				.toList();
	}

	public MedicoResponseDTO obtenerMedicoPorId(Long id) {
		return repository.findById(id).map(medico -> {
			if (!medico.isActivo()) {
				throw new MedicoInactivoException();
			}
			return MedicoMapper.toDTO(medico);
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
