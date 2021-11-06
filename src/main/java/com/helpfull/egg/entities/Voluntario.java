package com.helpfull.egg.entities;

import java.time.LocalDate;
import java.util.EnumSet;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.helpfull.egg.enums.Interes;
import com.helpfull.egg.enums.Rol;

@Entity
public class Voluntario {

	@Id
	private String username;
	private String nombre;
	private String apellido;
	private String password;
	private Integer telefono;
	private String email;
	private LocalDate nacimiento;
	
	@Enumerated(EnumType.STRING)
	private Rol rol;
	
//	private EnumSet<Interes> intereses;
	
	private LocalDate alta;
	private LocalDate baja;
//	private Zona zona;
	
	public Voluntario() {
		super();
	}

	public Voluntario(String username, String nombre, String apellido, String password, Integer telefono, String email,
			LocalDate nacimiento, Rol rol, LocalDate alta, LocalDate baja) {
		super();
		this.username = username;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.telefono = telefono;
		this.email = email;
		this.nacimiento = nacimiento;
		this.rol = rol;
//		this.intereses = intereses;
		this.alta = alta;
		this.baja = baja;
//		this.zona = zona;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

//	public EnumSet<Interes> getIntereses() {
//		return intereses;
//	}
//
//	public void setIntereses(EnumSet<Interes> intereses) {
//		this.intereses = intereses;
//	}

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

//	public Zona getZona() {
//		return zona;
//	}
//
//	public void setZona(Zona zona) {
//		this.zona = zona;
//	}

	public LocalDate getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}

	@Override
	public String toString() {
		return "Voluntario [username=" + username + ", nombre=" + nombre + ", apellido=" + apellido + ", password=" + password
				+ ", telefono=" + telefono + ", email=" + email + ", nacimiento=" + nacimiento + ", rol=" + rol
				+ ", intereses=" + ", alta=" + alta + ", baja=" + baja + "]";
	}

}
