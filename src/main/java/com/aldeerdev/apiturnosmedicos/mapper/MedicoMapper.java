package com.aldeerdev.apiturnosmedicos.mapper;

import com.aldeerdev.apiturnosmedicos.dto.medico.MedicoRequestDTO;
import com.aldeerdev.apiturnosmedicos.dto.medico.MedicoResponseDTO;
import com.aldeerdev.apiturnosmedicos.model.Medico;

public class MedicoMapper {

	public static Medico toEntity(MedicoRequestDTO dto) {
		Medico medico = new Medico();
		medico.setNombre(dto.getNombre());
		medico.setApellido(dto.getApellido());
		medico.setEspecialidad(dto.getEspecialidad());
		medico.setMatricula(dto.getMatricula());
		medico.setActivo(true);
		return medico;
	}
	
	public static MedicoResponseDTO toDTO(Medico medico) {
		MedicoResponseDTO dto = new MedicoResponseDTO();
		dto.setId(medico.getId());
		dto.setNombre(medico.getNombre());
		dto.setApellido(medico.getApellido());
		dto.setEspecialidad(medico.getEspecialidad());
		dto.setMatricula(medico.getMatricula());
		return dto;
	}
}
