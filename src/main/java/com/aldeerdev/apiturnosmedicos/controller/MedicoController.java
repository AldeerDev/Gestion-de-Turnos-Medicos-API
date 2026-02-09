package com.aldeerdev.apiturnosmedicos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aldeerdev.apiturnosmedicos.model.Medico;
import com.aldeerdev.apiturnosmedicos.service.MedicoService;

@RestController
public class MedicoController {

	@Autowired
	private MedicoService service;
	
	@GetMapping("/medicos/{especialidad}")
	public List<Medico> listarMedicosPorEspecialidad(@PathVariable String especialidad) {
		return service.listarMedicosPorEspecialidad(especialidad);
	}

	@PostMapping("/medicos")
	public Medico crearMedico(@RequestBody Medico medico) {
		return service.crearMedico(medico);
	}
	

}
