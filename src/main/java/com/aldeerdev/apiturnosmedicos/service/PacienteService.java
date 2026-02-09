package com.aldeerdev.apiturnosmedicos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldeerdev.apiturnosmedicos.exception.PacienteNotFoundException;
import com.aldeerdev.apiturnosmedicos.model.Paciente;
import com.aldeerdev.apiturnosmedicos.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repository;

	public Paciente crearPaciente(Paciente paciente) {
		return repository.save(paciente);
	}

	public Paciente obtenerPacientePorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new PacienteNotFoundException(id));
	}

	public List<Paciente> listarPacientes() {
		return repository.findAll();
	}

	public Paciente actualizarPaciente(Long id, Paciente paciente) {
		Paciente actualizado = repository.findById(id).orElse(null);
		actualizado.setNombre(paciente.getNombre());
		actualizado.setApellido(paciente.getApellido());
		actualizado.setEmail(paciente.getEmail());
		actualizado.setDni(paciente.getDni());
		actualizado.setFechaNacimiento(paciente.getFechaNacimiento());
		return repository.save(actualizado);
	}

	public void eliminarPaciente(Long id) {
		Paciente pacienteBaja = repository.findById(id).orElse(null);
		pacienteBaja.setActivo(false);
		repository.save(pacienteBaja);
	}
}
