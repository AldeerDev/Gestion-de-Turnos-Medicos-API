package com.aldeerdev.apiturnosmedicos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aldeerdev.apiturnosmedicos.dto.medico.MedicoRequestDTO;
import com.aldeerdev.apiturnosmedicos.dto.medico.MedicoResponseDTO;
import com.aldeerdev.apiturnosmedicos.model.Medico;
import com.aldeerdev.apiturnosmedicos.service.MedicoService;

@RestController
public class MedicoController {

	@Autowired
	private MedicoService service;

	@GetMapping("/medicos")
	public List<MedicoResponseDTO> listarMedicosPorEspecialidad(@RequestParam String especialidad) {
		return service.listarMedicosPorEspecialidad(especialidad);
	}
	
	@GetMapping("/medicos/{id}")
	public Medico obtenerMedicoPorId(@PathVariable Long id) {
		return service.obtenerMedicoPorId(id);
	}

	@PostMapping("/medicos")
	public MedicoResponseDTO crearMedico(@RequestBody MedicoRequestDTO dto) {
		return service.crearMedico(dto);
	}
	
	@PutMapping("/medicos/{id}")
	public Medico actualizarMedico(@PathVariable Long id, @RequestBody Medico medico) {
		return service.actualizarMedico(id, medico);
	}
	
	@DeleteMapping("/medicos/{id}")
	public String eliminarMedico(@PathVariable Long id) {
		service.eliminarMedico(id);
		return "Eliminacion exitosa!";
	}

}
