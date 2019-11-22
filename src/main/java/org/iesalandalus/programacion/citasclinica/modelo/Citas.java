package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Citas {
	private int capacidad;
	private int tamano;
	private Cita[] coleccionCitas;

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	public Cita[] getColeccionCitas() {
		return coleccionCitas;
	}

	private int tamanoSuperado() {
		int tamano = 0;

		return tamano;
	}

	private int capacidadSuperada() {
		int capacidad;

		return capacidad = 0;
	}

	private int buscarIndice(Cita cita) {
		int indiceCita = 0;

		for (int i = 0; i < coleccionCitas.length; i++) {
			if (cita == coleccionCitas[i]) {
				indiceCita = i;
			}
		}

		return indiceCita;
	}

	public void insertar(Cita cita) {
		int citasNoDisponibles = 0;
		boolean arrayLleno = false;

		for (int i = 0; i < coleccionCitas.length; i++) {
			if (coleccionCitas[i] != null) {
				citasNoDisponibles++;
			}
		}

		if (citasNoDisponibles == coleccionCitas.length - 1) {
			throw new IllegalArgumentException("ERROR: No quedan citas disponibles");
		} else {
			for (int i = 0; i < coleccionCitas.length; i++) {
				if (coleccionCitas[i] == null) {
					coleccionCitas[i] = cita;
				}
			}
		}
	}

	public Cita buscar(Cita cita) {
		int contador = 0;
		Cita almacenaCita = null;
		for (int i = 0; i < coleccionCitas.length; i++) {
			if (coleccionCitas[i] != cita) {
				contador++;
			} else {
				almacenaCita = coleccionCitas[i];
			}
		}

		if (contador == coleccionCitas.length - 1) {
			cita = null;
			throw new NullPointerException("ERROR: Cita no encontrada");
		} else {
			cita = almacenaCita;
		}

		return cita;

	}

	private void desplazarUnaPosicionHaciaIzquierda(int posicion) {
		int nuevaPosicion = 1;
		if (coleccionCitas[posicion] != null) {
			throw new IllegalArgumentException("ERROR: Ya hay una cita en esta posicion");
		} else {
			coleccionCitas[posicion] = null;

			for (int i = posicion + 1; i < coleccionCitas.length; i++) {
				if (coleccionCitas[i] != null) {
					coleccionCitas[i] = coleccionCitas[i - nuevaPosicion];
				}
			}
		}
	}

	public void borrar(Cita cita) {
		int posicionCita = 0;
		int contador = 0;
		for (int i = 0; i < coleccionCitas.length; i++) {
			if (cita != coleccionCitas[i]) {
				contador++;
			} else if (cita == coleccionCitas[i]) {
				posicionCita = i;
			}
		}
		if (contador == coleccionCitas.length) {
			throw new IllegalArgumentException("ERROR: Esta cita no existe");
		} else {

			desplazarUnaPosicionHaciaIzquierda(posicionCita);
		}
	}

	public void getCitas(LocalDateTime fechaHora) {

		for (int i = 0; i < coleccionCitas.length; i++) {
			if (fechaHora == coleccionCitas[i].getFechaHora()) {
				System.out.println(coleccionCitas[i]);
			}
		}

	}
}
