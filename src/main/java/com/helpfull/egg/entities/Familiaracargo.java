package com.helpfull.egg.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;


@Entity


public class Familiaracargo {

	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid2")

	private String id;
	private String nombre;
	private String apellido;
	
	
	
	@OneToOne
	private Amigo amigo;


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
		return "Familiaracargo [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", amigo=" + amigo
				+ ", toString()=" + super.toString() + "]";
	}
	




	}

