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
		if (capacidadSuperada(capacidad) == true) {
			this.capacidad = capacidad;
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

	private boolean tamanoSuperado(int tamano) {
		boolean tamanoCorrecto = false;

		return tamanoCorrecto;
	}

	private boolean capacidadSuperada(int capacidad) {
		boolean capacidadCorrecta = false;
		if (capacidad < 1) {
			capacidadCorrecta = false;
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		} else {
			capacidad = capacidad;
			capacidadCorrecta = true;
		}

		return capacidadCorrecta;
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

	public void insertar(Cita cita) throws OperationNotSupportedException {
		int citasNoDisponibles = 0;
		int posicionCita=0;
		
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		} else{

			for (int i = 0; i < coleccionCitas.length; i++) {
				if (coleccionCitas[i] == cita) {
					throw new IllegalArgumentException("ERROR: Esta cita ya existe");
				}
			}
			for (int i = 0; i < coleccionCitas.length; i++) {
				if (coleccionCitas[i] != null) {
					citasNoDisponibles++;
				}
			}
			if (citasNoDisponibles == coleccionCitas.length - 1) {
				throw new OperationNotSupportedException("ERROR: No quedan citas disponibles");
			} else {
				for (int i = 0; i < coleccionCitas.length; i++) {
					if (coleccionCitas[i] == null) {
						posicionCita=i;
						break;
					}
				}
				coleccionCitas[posicionCita]=cita;
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

		if (contador == coleccionCitas.length) {
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

	public void borrar(Cita cita) throws OperationNotSupportedException {
		int posicionCita = 0;
		int contador = 0;

		if (cita == null) {
			throw new OperationNotSupportedException("ERROR: No se puede borrar una cita nula");
		}

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

	public Cita[] getCitas(LocalDate fecha) {
		Cita[] almacenaCitas = new Cita[coleccionCitas.length];
		if (fecha == null) {
			throw new NullPointerException("ERROR: El día no puede ser nulo");
		} else {
			for (int i = 0; i < coleccionCitas.length; i++) {
				if (fecha == coleccionCitas[i].getFechaHora().toLocalDate()) {
					almacenaCitas[i] = coleccionCitas[i];
				}
			}
		}
		return almacenaCitas;

	}
	public static void main(String[] args) throws OperationNotSupportedException {
		Paciente paciente1=new Paciente("Tatisaka Alonso Hernandez","75255135D","950044954");
		LocalDateTime fechahora=LocalDateTime.of(2019, 06, 12, 10, 00);
		Cita cita1=new Cita(paciente1,fechahora);
		Citas citas= new Citas(3);
		citas.insertar(cita1);
		System.out.println(citas);
	}
}
