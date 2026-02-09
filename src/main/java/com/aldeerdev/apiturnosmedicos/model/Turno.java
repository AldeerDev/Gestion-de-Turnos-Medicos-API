package com.aldeerdev.apiturnosmedicos.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.aldeerdev.apiturnosmedicos.model.enums.EstadoTurno;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "turnos")
public class Turno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_turno")
	private LocalDate fecha;

	@Column(name = "hora_turno")
	private LocalTime hora;

	@Enumerated(EnumType.STRING)
	private EstadoTurno estado;
	private Paciente paciente;
	private Medico medico;

	public Turno() {
		super();
	}

	public Turno(LocalDate fecha, LocalTime hora, EstadoTurno estado, Paciente paciente, Medico medico) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.estado = estado;
		this.paciente = paciente;
		this.medico = medico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public EstadoTurno getEstado() {
		return estado;
	}

	public void setEstado(EstadoTurno estado) {
		this.estado = estado;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

}
