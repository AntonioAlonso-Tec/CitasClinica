package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

public class Citas {
	private int capacidad;
	private int tamano;
	private Cita[] coleccionCitas;

	public Citas(int capacidad) {
		if (capacidad < 1) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		} else {
			this.capacidad = capacidad;
			this.coleccionCitas = new Cita[capacidad];
			this.tamano = 0;
		}

	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	public Cita[] getCitas() {
		return coleccionCitas;
	}

	private boolean tamanoSuperado(int indice) {
		return indice >= tamano;

	}

	private boolean capacidadSuperada(int indice) {
		return indice >= capacidad;
	}

	private int buscarIndice(Cita cita) {
		int indice = 0;
		boolean indiceEncontrado = false;

		while (!tamanoSuperado(indice) && indiceEncontrado == false) {
			if (coleccionCitas[indice].equals(cita)) {
				indiceEncontrado = true;
			} else {
				indice++;
			}

		}
		return indice;
	}

	public void insertar(Cita cita) throws OperationNotSupportedException {

		System.out.println(buscar(cita));
		System.out.println(cita);
		if (capacidadSuperada(buscarIndice(cita))) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");
		}

		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}
		if (!tamanoSuperado(buscarIndice(cita))) {
			throw new OperationNotSupportedException("ERROR: La cita está repetida");
		} else {
			coleccionCitas[buscarIndice(cita)] = new Cita(cita);
			tamano++;
		}

		for (int i = 0; i < coleccionCitas.length; i++) {
			System.out.println(coleccionCitas[i] + " en posicion " + i);
			System.out.println("El tamaño del array es " + tamano);
		}

	}

	public Cita buscar(Cita cita) {
		int posicionCita = 0;
		boolean citaEncontrada = false;

		while (posicionCita < getTamano() && citaEncontrada == false) {
			if (coleccionCitas[posicionCita].equals(cita)) {
				citaEncontrada = true;
			} else {
				posicionCita++;
			}
		}

		if (citaEncontrada == true) {
			return new Cita(cita);
		} else {
			return null;
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int posicion) {
		if (!coleccionCitas[posicion].equals(null)) {
			throw new IllegalArgumentException("ERROR: Ya hay una cita en esta posicion");
		} else {
			coleccionCitas[posicion].equals(null);

			for (int i = posicion + 1; i < coleccionCitas.length; i++) {
				if (!coleccionCitas[i].equals(null)) {
					coleccionCitas[i - 1] = coleccionCitas[i];
				}
			}
		}
	}

	public void borrar(Cita cita) throws OperationNotSupportedException {
		if (cita.equals(null)) {
			throw new NullPointerException("ERROR: No se puede borrar una cita nula.");
		}

		if (buscarIndice(cita) > tamano) {
			throw new IllegalArgumentException("ERROR: Esta cita no existe y no se puede borrar");
		} else {
			coleccionCitas[buscarIndice(cita)] = null;
			tamano--;
			desplazarUnaPosicionHaciaIzquierda(buscarIndice(cita));
		}
	}

	public Cita[] getCitas(LocalDate fecha) {
		if (fecha == null) {
			throw new NullPointerException("ERROR: No se pueden devolver las citas para un día nulo.");
		}
		if (fecha != null) {
			for (int i = 0; i < coleccionCitas.length - 1; i++) {
				if (fecha.equals(coleccionCitas[i].getFechaHora().toLocalDate())) {
					if (!coleccionCitas[i].equals(null)) {
						System.out.println(coleccionCitas[i]);
					} else {
						throw new NullPointerException("ERROR: No se puede consultar citas nulas.");

					}
				}
			}
		}
		return coleccionCitas;
	}

}
