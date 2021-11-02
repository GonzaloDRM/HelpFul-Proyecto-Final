package com.helpfull.egg.entities;

import java.util.Date;
import java.util.EnumSet;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.TemporalType;

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
	
	@Enumerated(EnumType.STRING)
	private EnumSet<Interes> intereses;
	
	@javax.persistence.Temporal(TemporalType.TIMESTAMP)
	private Date alta;
	
	@javax.persistence.Temporal(TemporalType.TIMESTAMP)
	private Date baja;
	
	@OneToOne
	private Zona zona;
	
	@OneToOne
	private Amigo amigo;

	
	
	public EnumSet<Interes> getIntereses() {
		return intereses;
	}

	public void setIntereses(EnumSet<Interes> intereses) {
		this.intereses = intereses;
	}


	public Date getAlta() {
		return alta;
	}


	public void setAlta(Date alta) {
		this.alta = alta;
	}


	public Date getBaja() {
		return baja;
	}


	public void setBaja(Date baja) {
		this.baja = baja;
	}


	public Zona getZona() {
		return zona;
	}


	public void setZona(Zona zona) {
		this.zona = zona;
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


	public Amigo getAmigo() {
		return amigo;
	}


	public void setAmigo(Amigo amigo) {
		this.amigo = amigo;
	}


	@Override
	public String toString() {
		return "Voluntario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", amigo=" + amigo
				+ ", toString()=" + super.toString() + "]";
	}







	}
