package org.iesalandalus.programacion.citasclinica;

import java.time.*;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.*;

public class MainApp {

	private static final int NUM_MAX_CITAS = 5;
	private static Citas citasClinica = new Citas(NUM_MAX_CITAS);
	private static Cita cita;

	private static void insertarCita() {
		Consola.leerPaciente();
		Consola.leerFechaHora();
		cita = new Cita(Consola.leerPaciente(), Consola.leerFechaHora());
		try {
			citasClinica.insertar(cita);
		} catch (OperationNotSupportedException e) {
			System.out.println("ERROR al insertar cita");
		}

	}

	private static void buscarCita() {
		LocalDate fecha;
		System.out.println("Introduzca la fecha de la cita a buscar");
		Consola.leerFechaHora();
		fecha = Consola.leerFechaHora().toLocalDate();

		citasClinica.getCitas(fecha);
	}

	public static void main(String[] args) {
		System.out.println("Programa para gestionar las citas de la Clínica.");

	}

}
