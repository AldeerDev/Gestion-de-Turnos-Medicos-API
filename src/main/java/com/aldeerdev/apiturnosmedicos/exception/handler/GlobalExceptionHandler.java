package com.aldeerdev.apiturnosmedicos.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aldeerdev.apiturnosmedicos.exception.MedicoNotFoundException;
import com.aldeerdev.apiturnosmedicos.exception.PacienteNotFoundException;
import com.aldeerdev.apiturnosmedicos.exception.TurnoEnElPasadoException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PacienteNotFoundException.class)
	public ResponseEntity<String> handlePacienteNotFound(PacienteNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(MedicoNotFoundException.class)
	public ResponseEntity<String> handleMedicoNotFound(MedicoNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(TurnoEnElPasadoException.class)
	public ResponseEntity<String> handleTurnoEnElPasado(TurnoEnElPasadoException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
