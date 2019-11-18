package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.utilidades.Entrada;

public class Cita {
	public static final String FORMATO_FECHA_HORA = "dd/MM/yyyy hh:mm";
	private LocalDateTime fechaHora;
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(LocalDateTime fechaHora) {
		String anio;
		String mes;
		String dia;
		String hora;
		String minuto;
		String fechaHoraString;
		DateTimeFormatter formatoFecha=DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA);
		
		do {
			System.out.println("Introduce el año");
			anio=Entrada.cadena();
		}while(anio==null);
		do {
			System.out.println("Introduce el mes");
			mes=Entrada.cadena();
		}while(mes==null);
		do {
			System.out.println("Introduce el dia");
			dia=Entrada.cadena();
		}while(dia==null);
		
		do {
			System.out.println("Introduce la hora");
			hora=Entrada.cadena();
		}while(hora==null);
		do {
			System.out.println("Introduce el minuto");
			minuto=Entrada.cadena();
		}while(minuto==null);
		this.fechaHora = fechaHora;
		
		fechaHoraString=anio+"/"+mes+"/"+dia+" "+hora+":"+minuto;
		
		fechaHora=LocalDateTime.parse(fechaHoraString, formatoFecha);	
	}
	
	
}
