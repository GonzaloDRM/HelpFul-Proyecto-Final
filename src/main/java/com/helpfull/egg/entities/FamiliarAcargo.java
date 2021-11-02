package com.helpfull.egg.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class FamiliarAcargo {

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy ="uuid2")
    private String id;
	
	private String nombre;
	private String apellido;
	private Integer edad;
	private String telefono;
	private String direccion;
	
	public FamiliarAcargo(String id, String nombre, String apellido, Integer edad, String telefono, String direccion) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	public FamiliarAcargo() {
		
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

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
		
}
