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
		nombre = getNombre();

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		do {
			System.out.println("Introduzca el nombre del paciente");
			nombre = Entrada.cadena();
		} while (nombre == " ");
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		String numeroDni;
		int numeroDniInt;
		char[] arrayLetraDni = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' };
		char letraDni;

		do {
			System.out.println("Introduce tu numero de DNI");
			numeroDni = Entrada.cadena();
			numeroDniInt = Integer.parseInt(numeroDni);
		} while (numeroDniInt < 10000000 || numeroDniInt > 99999999);

		letraDni = arrayLetraDni[numeroDniInt % 23];

		dni = numeroDni + letraDni;

		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
