package com.aldeerdev.apiturnosmedicos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
