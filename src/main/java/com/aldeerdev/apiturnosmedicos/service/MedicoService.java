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
		Medico medico = obtenerEntidadPorId(id);
		if (!medico.isActivo()) {
			throw new MedicoInactivoException();
		}
		return MedicoMapper.toDTO(medico);
	}

	public MedicoResponseDTO actualizarMedico(Long id, MedicoRequestDTO dto) {
		Medico actualizado = obtenerEntidadPorId(id);
		actualizado.setNombre(dto.getNombre());
		actualizado.setApellido(dto.getApellido());
		actualizado.setEspecialidad(dto.getEspecialidad());
		actualizado.setMatricula(dto.getMatricula());
		repository.save(actualizado);
		return MedicoMapper.toDTO(actualizado);
	}

	public void eliminarMedico(Long id) {
		Medico bajaMedico = repository.findById(id).orElse(null);
		bajaMedico.setActivo(false);
		repository.save(bajaMedico);
	}
	
	// metodos privados
	
	private Medico obtenerEntidadPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new MedicoNotFoundException(id));
	}
}
