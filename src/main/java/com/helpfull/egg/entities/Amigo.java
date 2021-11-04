package com.helpfull.egg.entities;

import java.util.Date;
import java.util.EnumSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.helpfull.egg.enums.Discapacidad;
import com.helpfull.egg.enums.Interes;
import com.helpfull.egg.enums.Necesidad;

@Entity
public class Amigo {

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy ="uuid2")
    private String id;
	
	private String nombre;
	private String apellido;
	private Integer edad;
	private String telefono;
	private String direccion;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date alta;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date baja;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Foto foto;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private FamiliarAcargo familiarAcargo;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Zona zona;
	
	private EnumSet<Interes> intereses;
	
	private EnumSet<Discapacidad> discapacidades;
	
	private EnumSet<Necesidad> necesidades;

	public Amigo() {

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

	public FamiliarAcargo getFamiliarAcargo() {
		return familiarAcargo;
	}

	public void setFamiliarAcargo(FamiliarAcargo familiarAcargo) {
		this.familiarAcargo = familiarAcargo;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public EnumSet<Interes> getIntereses() {
		return intereses;
	}

	public void setIntereses(EnumSet<Interes> intereses) {
		this.intereses = intereses;
	}

	public EnumSet<Discapacidad> getDiscapacidades() {
		return discapacidades;
	}

	public void setDiscapacidades(EnumSet<Discapacidad> discapacidades) {
		this.discapacidades = discapacidades;
	}

	public EnumSet<Necesidad> getNecesidades() {
		return necesidades;
	}

	public void setNecesidades(EnumSet<Necesidad> necesidades) {
		this.necesidades = necesidades;
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

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}
	
	

}
	