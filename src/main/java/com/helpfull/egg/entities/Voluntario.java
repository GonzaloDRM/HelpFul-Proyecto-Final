package com.helpfull.egg.entities;

import java.time.LocalDate;
import java.util.EnumSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.helpfull.egg.enums.Interes;

@Entity
public class Voluntario {

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid2")
	private String id;
	private String nombre;
	private String apellido;
	
	private EnumSet<Interes> intereses;
	
	private LocalDate alta;
	
	private LocalDate baja;
	
	private Zona zona;

	public Voluntario(String id, String nombre, String apellido, EnumSet<Interes> intereses, LocalDate alta,
			LocalDate baja, Zona zona) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.intereses = intereses;
		this.alta = alta;
		this.baja = baja;
		this.zona = zona;
	}

	public Voluntario() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public EnumSet<Interes> getIntereses() {
		return intereses;
	}

	public void setIntereses(EnumSet<Interes> intereses) {
		this.intereses = intereses;
	}

	public LocalDate getAlta() {
		return alta;
	}

	public void setAlta(LocalDate alta) {
		this.alta = alta;
	}

	public LocalDate getBaja() {
		return baja;
	}

	public void setBaja(LocalDate baja) {
		this.baja = baja;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	@Override
	public String toString() {
		return "Voluntario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", intereses=" + intereses
				+ ", alta=" + alta + ", baja=" + baja + ", zona=" + zona + "]";
	}
	
}
