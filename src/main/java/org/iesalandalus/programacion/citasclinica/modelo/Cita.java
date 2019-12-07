package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.iesalandalus.programacion.utilidades.Entrada;

public class Cita {
	public static final String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm";
	private LocalDateTime fechaHora;
	private Paciente paciente;

	public Cita(Paciente paciente, LocalDateTime fechaHora) {
		setPaciente(paciente);
		setFechaHora(fechaHora);
	}

	public Cita(Cita cita) {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		} else {
			setPaciente(cita.getPaciente());
			setFechaHora(cita.getFechaHora());
		}
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {

		if (fechaHora == null) {
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
		} else {
			this.fechaHora = fechaHora;
		}
	}

	public Paciente getPaciente() {
		return new Paciente(paciente);
	}

	private void setPaciente(Paciente paciente) {

		if (paciente == null) {
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
		} else {
			this.paciente = new Paciente(paciente);
		}

	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaHora, paciente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Cita)) {
			return false;
		}
		Cita other = (Cita) obj;
		return Objects.equals(fechaHora, other.fechaHora) && Objects.equals(paciente, other.paciente);
	}

	@Override
	public String toString() {
		return String.format("%s, fechaHora=%s", paciente,
				fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA)));
	}

}
