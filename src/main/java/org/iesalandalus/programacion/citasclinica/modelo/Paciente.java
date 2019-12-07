package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.Objects;
import java.util.regex.*;

import org.iesalandalus.programacion.utilidades.Entrada;

public class Paciente {
	private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private static final String ER_TELEFONO = "[6-9][0-9]{8}";
	private String nombre;
	private String dni;
	private String telefono;

	public Paciente(String nombre, String dni, String telefono) {
		setNombre(formateaNombre(nombre));
		setDni(comprobarLetraDni(dni));
		setTelefono(telefono);
	}

	public Paciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");
		} else {
			setNombre(paciente.getNombre());
			setDni(paciente.getDni());
			setTelefono(paciente.getTelefono());
		}

	}

	private String formateaNombre(String nombre) {
		String nombreFormateado = "";
		String nombreConEspacios = "";
		int indice = 0;

		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		} else {
			StringBuilder formato = new StringBuilder(nombre);
			for (indice = 0; indice < formato.length(); indice++) {
				if (Character.isUpperCase(formato.charAt(indice))) {
					formato.setCharAt(indice, Character.toLowerCase(formato.charAt(indice)));
				}
			}

			for (int i = 1; i < formato.length(); i++) {
				if (Character.isLowerCase(formato.charAt(i)) && formato.charAt(i) > 1 && formato.charAt(i - 1) == ' ') {
					formato.setCharAt(i, Character.toUpperCase(formato.charAt(i)));
				} else if (Character.isUpperCase(formato.charAt(i)) && formato.charAt(i) < 1) {
					Character.toUpperCase(formato.charAt(i));
				}
			}
			if (Character.isLowerCase(formato.charAt(0))) {
				formato.setCharAt(0, Character.toUpperCase(formato.charAt(0)));
			}
			nombreConEspacios = formato.toString();
			nombreFormateado = nombreConEspacios.trim().replaceAll("\\s{2,}", " ");
			return nombreFormateado;
		}
	}

	private String comprobarLetraDni(String dni) {
		int numeroDni;
		char letraDni;
		char[] arrayLetras = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' };
		Pattern patron;
		Matcher comparar;
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		} else {
			patron = Pattern.compile(ER_DNI);
			if (!dni.matches(ER_DNI)) {
				throw new IllegalArgumentException("ERROR: Este no es un número de DNI.");
			} else {
				comparar = patron.matcher(dni);
				comparar.matches();
				numeroDni = Integer.parseInt(comparar.group(1));
				letraDni = comparar.group(2).charAt(0);
			}
			if (letraDni != arrayLetras[numeroDni % 23]) {
				throw new IllegalArgumentException("ERROR: La letra no es correcta.");
			} else {
				return dni;
			}
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		} else {
			this.nombre = nombre;
		}
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		} else {
			this.dni = dni;
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {

		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
		} else if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: Este número de teléfono no es correcto");
		} else if (telefono.matches(ER_TELEFONO)) {
			this.telefono = telefono;
		}

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

		for (int i = 0; i < formateaNombre(nombre).length(); i++) {
			if (Character.isUpperCase(formateaNombre(nombre).charAt(i))) {
				inicial = formateaNombre(nombre).charAt(i);
				iniciales += inicial;
			}
		}
		return iniciales;
	}

	@Override
	public String toString() {
		return String.format("nombre=%s (%s), DNI=%s, teléfono=%s", formateaNombre(nombre), getIniciales(),
				comprobarLetraDni(dni), telefono);
	}

}
