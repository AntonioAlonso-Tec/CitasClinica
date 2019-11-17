package org.iesalandalus.programacion.citasclinica.modelo;

import org.iesalandalus.programacion.utilidades.Entrada;

public class Paciente {
	private static final String ER_DNI = "0";
	private static final String ER_TELEFONO = "0";
	private String nombre;
	private String dni;
	private String telefono;

	private String formateaNombre() {
		String nombre;
		do {
			System.out.println("Introduzca el nombre del paciente");
			nombre = Entrada.cadena();
		} while (nombre == " ");

		StringBuilder convertir = new StringBuilder(nombre.length());

		for (int i = 0; i < nombre.length(); i++) {
			int posicionAnterior = i - 1;
			char letra = nombre.charAt(i);
			char letraAnterior = nombre.charAt(posicionAnterior);

			if (Character.isLowerCase(letra) && nombre.indexOf(i) == 0) {
				convertir.append(Character.toUpperCase(letra));
			} else if (Character.isLowerCase(letra) && nombre.charAt(posicionAnterior) == ' ') {
				convertir.append(Character.toUpperCase(letra));
			}

		}

		String nombreConvertido = convertir.toString().replaceAll(" ", "");
		return nombreConvertido;

	}
	
}
