package com.aldeerdev.apiturnosmedicos.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aldeerdev.apiturnosmedicos.model.Turno;
import com.aldeerdev.apiturnosmedicos.service.TurnoService;

@RestController
public class TurnoController {

	@Autowired
	private TurnoService service;

	@GetMapping("/turnos")
	public List<Turno> listar(@RequestParam(required = false) LocalDate fecha,
			@RequestParam(required = false) Long idMedico) {
		return service.listarTurnos(fecha, idMedico);
	}

	@PostMapping("/turnos")
	public Turno crearTurno(@RequestParam Long idPaciente, @RequestParam Long idMedico, @RequestBody Turno turno) {
		return service.crearTurno(idPaciente, idMedico, turno);
	}
}
