package com.aldeerdev.apiturnosmedicos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aldeerdev.apiturnosmedicos.model.Paciente;
import com.aldeerdev.apiturnosmedicos.service.PacienteService;

@RestController
public class PacienteController {

	@Autowired
	private PacienteService service;
	
	@GetMapping("/pacientes")
	public List<Paciente> listaDePacientes() {
		return service.listarPacientes();
	}
	
	@GetMapping("/pacientes/{id}")
	public Paciente obtenerPacientePorId(@PathVariable Long id) {
		return service.obtenerPacientePorId(id);
	}
	
	@PostMapping("/pacientes")
	public Paciente crearPaciente(@RequestBody Paciente paciente) {
		return service.crearPaciente(paciente);
	}
	
	@PutMapping("/pacientes/{id}")
	public Paciente actualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
		return service.actualizarPaciente(id, paciente);
	}
}
