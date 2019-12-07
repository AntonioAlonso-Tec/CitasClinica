package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.*;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private Consola() {

	}

	public static void mostrarMenu() {
		System.out.println("*****************************************************************************");
		System.out.println("BIENVENIDO AL SISTEMA DE GESTIÓN DE CITAS MÉDICAS. iNTRODUZCA UNA OPCIÓN");
		System.out.println("*****************************************************************************");
		System.out.println("");
		System.out.println("");
		System.out.println("1.- INSERTAR UNA NUEVA CITA");
		System.out.println("2.- BUSCAR UNA CITA ESPECÍFICA");
		System.out.println("3.- BORRAR UNA CITA");
		System.out.println("4.- MOSTRAR CITAS ALMACENADAS");
		System.out.println("5.- MOSTRAR CITAS POR FECHA");
		System.out.println("6.- SALIR");
	}

	public static Opciones elegirOpcion() {
		int opcion = 0;
		Opciones opcionSeleccionada;

		switch (opcion) {
		case 1:
			// Insertar citas
			return Opciones.INSERTAR_CITA;

		case 2:
			// Buscar cita (metodo buscar(cita))
			return Opciones.BUSCAR_CITA;

		case 3:
			// Borrar cita
			return Opciones.BORRAR_CITA;

		case 4:
			// Mostrar citas almacenadas (Metodo insertar(Cita))
			return Opciones.MOSTRAR_CITAS;

		case 5:
			// Mostrar citas por fecha (Metodo getCitas(LocalDate))
			return Opciones.MOSTRAR_CITAS_DIA;

		case 6:
			// Salir
			return Opciones.SALIR;
		default:
			return null;
		}
	}

	public static Paciente leerPaciente() {
		String nombre, nombreCompleto, apellidos, dni, telefono;

		do {
			System.out.println("Introduce el nombre del paciente");
			nombre = Entrada.cadena();
		} while (nombre.length() < 2);

		do {
			System.out.println("Introduce los apellidos del paciente");
			apellidos = Entrada.cadena();
		} while (apellidos.length() < 2);

		do {
			System.out.println("Introduce el DNI del paciente");
			dni = Entrada.cadena();
		} while (dni.length() < 9);

		do {
			System.out.println("Introduce el nombre del paciente");
			telefono = Entrada.cadena();
		} while (telefono.length() < 2);

		nombreCompleto = nombre + " " + apellidos;

		Paciente paciente = new Paciente(nombreCompleto, dni, telefono);

		return paciente;
	}

	public static LocalDateTime leerFechaHora() {
		int diaString, mesString, anoString, horaString, minutoString;
		String fechaHoraString;
		DateTimeFormatter formato = DateTimeFormatter.ofPattern(Cita.FORMATO_FECHA_HORA);

		System.out.println("Introduzca la fecha de la cita");
		do {
			System.out.println("Dia");
			diaString = Entrada.entero();
		} while (diaString < 1 || diaString > 31);
		do {
			System.out.println("Mes");
			mesString = Entrada.entero();
		} while (mesString < 1 || mesString > 12);
		do {
			System.out.println("Año");
			anoString = Entrada.entero();
		} while (anoString < 1990 || anoString > 2100);
		System.out.println("Introduzca la hora de la cita");
		do {
			System.out.println("Hora");
			horaString = Entrada.entero();
		} while (horaString < 0 || horaString > 23);
		do {
			System.out.println("Minuto");
			minutoString = Entrada.entero();
		} while (minutoString < 0 || minutoString > 59);

		fechaHoraString = Integer.toString(diaString) + "/" + Integer.toString(mesString) + "/"
				+ Integer.toString(anoString) + " " + Integer.toString(horaString) + "/"
				+ Integer.toString(minutoString);

		return LocalDateTime.parse(fechaHoraString, formato);
	}

	public static Cita leerCita() {
		Paciente paciente = leerPaciente();
		LocalDateTime fechaHora = leerFechaHora();

		Cita cita = new Cita(paciente, fechaHora);

		return cita;
	}

	public static LocalDate leerFecha() {
		int dia, mes, ano;
		String fechaBusqueda;
		String formatoAno = "dd/MM/yyyy";
		DateTimeFormatter formato = DateTimeFormatter.ofPattern(formatoAno);

		System.out.println("Introduce la fecha de búsqueda");
		do {
			System.out.println("Dia");
			dia = Entrada.entero();
		} while (dia < 1 || dia > 31);
		do {
			System.out.println("Mes");
			mes = Entrada.entero();
		} while (mes < 1 || mes > 12);
		do {
			System.out.println("Año");
			ano = Entrada.entero();
		} while (ano < 1990 || ano > 2100);

		fechaBusqueda = Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(ano);

		return LocalDate.parse(fechaBusqueda, formato);
	}
}
