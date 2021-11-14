package com.helpfull.egg.entities;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.helpfull.egg.enums.Rol;

@Entity
public class Voluntario {

	@Id
	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private String direccion;
	private Integer dni;
	private String email;
	private Integer telefono;
	private LocalDate nacimiento;
	private String descripcion;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] foto;

	@Enumerated(EnumType.STRING)
	private Rol rol;

	private LocalDate alta;
	private LocalDate baja;

	public Voluntario() {
		super();
	}

	public Voluntario(String username, String password, String nombre, String apellido, String direccion, Integer dni,
			String email, Integer telefono, LocalDate nacimiento, String descripcion, byte[] foto, Rol rol,
			LocalDate alta, LocalDate baja) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.nacimiento = nacimiento;
		this.descripcion = descripcion;
		this.foto = foto;
		this.rol = rol;
		this.alta = alta;
		this.baja = baja;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public LocalDate getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
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

}
