package com.helpfull.egg.entities;

import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Amigo {

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy ="uuid2")
    private String id;
	
	private String nombre;
	private String apellido;
	private LocalDate edad;
	private String telefono;
	private String direccion;
	private LocalDate alta;
	private LocalDate baja;
	
	@Lob @Basic(fetch = FetchType.LAZY) 
	private byte[] foto;

	public Amigo() {
		super();
	}

	public Amigo(String id, String nombre, String apellido, LocalDate edad, String telefono, String direccion,
			LocalDate alta, LocalDate baja, byte[] foto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.telefono = telefono;
		this.direccion = direccion;
		this.alta = alta;
		this.baja = baja;
		this.foto = foto;
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

	public LocalDate getEdad() {
		return edad;
	}

	public void setEdad(LocalDate edad) {
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Amigo [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", telefono="
				+ telefono + ", direccion=" + direccion + ", alta=" + alta + ", baja=" + baja + ", foto="
				+ Arrays.toString(foto) + "]";
	}
	
}
	