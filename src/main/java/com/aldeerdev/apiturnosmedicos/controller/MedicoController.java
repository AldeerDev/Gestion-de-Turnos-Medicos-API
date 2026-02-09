package com.aldeerdev.apiturnosmedicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aldeerdev.apiturnosmedicos.model.Medico;
import com.aldeerdev.apiturnosmedicos.service.MedicoService;

@RestController
public class MedicoController {

	@Autowired
	private MedicoService service;

	@PostMapping("/medicos")
	public Medico crearMedico(@RequestBody Medico medico) {
		return service.crearMedico(medico);
	}

}
