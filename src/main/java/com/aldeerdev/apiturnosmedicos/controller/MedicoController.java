package com.aldeerdev.apiturnosmedicos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aldeerdev.apiturnosmedicos.model.Medico;
import com.aldeerdev.apiturnosmedicos.service.MedicoService;

@RestController
public class MedicoController {

	@Autowired
	private MedicoService service;

	@GetMapping("/medicos")
	public List<Medico> listarMedicosPorEspecialidad(@RequestParam String especialidad) {
		return service.listarMedicosPorEspecialidad(especialidad);
	}
	
	@GetMapping("/medicos/{id}")
	public Medico obtenerMedicoPorId(@PathVariable Long id) {
		return service.obtenerMedicoPorId(id);
	}

	@PostMapping("/medicos")
	public Medico crearMedico(@RequestBody Medico medico) {
		return service.crearMedico(medico);
	}

}
