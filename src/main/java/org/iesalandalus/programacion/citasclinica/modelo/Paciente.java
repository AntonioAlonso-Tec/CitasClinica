package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.Objects;

import org.iesalandalus.programacion.utilidades.Entrada;

public class Paciente {
	private static final String ER_DNI = "0";
	private static final String ER_TELEFONO = "0";
	private String nombre;
	private String dni;
	private String telefono;

	public Paciente(String nombre, String dni, String telefono) {
		nombre = getNombre();
		dni = getDni();
		telefono = getTelefono();
	}

	public Paciente(Paciente paciente) {
		if (dni == null || nombre == null || telefono == null) {
			System.out.println("ERROR: No pueden haber datos nulos");
		} else {
			setNombre(paciente.getNombre());
			setDni(paciente.getDni());
			setTelefono(paciente.getTelefono());
		}
	}

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
		int numTelefono;
		do {
			do {
				System.out.println("Introduce el numero de teléfono");
				telefono = Entrada.cadena();
			} while (telefono == null);
			numTelefono = Integer.parseInt(telefono);
		} while (numTelefono < 600000000 || numTelefono > 999999999);
		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Paciente)) {
			return false;
		}
		Paciente other = (Paciente) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono);
	}

	private String getIniciales() {
		String iniciales = "";
		char inicial;

		for (int i = 0; i < formateaNombre().length(); i++) {
			if (Character.isUpperCase(formateaNombre().charAt(i))) {
				inicial = formateaNombre().charAt(i);
				iniciales += inicial;
			}
		}
		return iniciales;
	}

	@Override
	public String toString() {
		return "nombre= (" +getIniciales() + ") " + formateaNombre() + ", dni=" + getDni() + ", telefono=" + getTelefono();
	}
	
	

}
