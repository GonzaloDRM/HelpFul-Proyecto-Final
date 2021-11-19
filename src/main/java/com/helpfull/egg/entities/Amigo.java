package com.helpfull.egg.entities;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

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
	private LocalDate nacimiento;
	private String telefono;
	private String direccion;
	private LocalDate alta;
	
	@ManyToOne(cascade= {CascadeType.PERSIST , CascadeType.REMOVE})
	private Zona zona;
	
	@Lob @Basic(fetch = FetchType.LAZY) 
	private byte[] foto;
	
	@ElementCollection(targetClass=Interes.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="intereses")
	private Collection<Interes> intereses;
	
	@ElementCollection(targetClass=Discapacidad.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="discapacidades")
	private Collection<Discapacidad> discapacidades;
	
	@ElementCollection(targetClass=Necesidad.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="necesidades")
	private Collection<Necesidad> necesidades;
	
	@ManyToOne(cascade= {CascadeType.PERSIST , CascadeType.REMOVE})
	private FamiliarAcargo familiarAcargo;

	public Amigo() {
		super();
	}

	public Amigo(String id, String nombre, String apellido, LocalDate nacimiento, String telefono, String direccion,
			LocalDate alta, Zona zona, byte[] foto, Collection<Interes> intereses,
			Collection<Discapacidad> discapacidades, Collection<Necesidad> necesidades, FamiliarAcargo familiarAcargo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacimiento = nacimiento;
		this.telefono = telefono;
		this.direccion = direccion;
		this.alta = alta;
		this.zona = zona;
		this.foto = foto;
		this.intereses = intereses;
		this.discapacidades = discapacidades;
		this.necesidades = necesidades;
		this.familiarAcargo = familiarAcargo;
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

	public LocalDate getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(LocalDate edad) {
		this.nacimiento = edad;
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public Collection<Interes> getIntereses() {
		return intereses;
	}

	public void setIntereses(Collection<Interes> intereses) {
		this.intereses = intereses;
	}

	public Collection<Discapacidad> getDiscapacidades() {
		return discapacidades;
	}

	public void setDiscapacidades(Collection<Discapacidad> discapacidades) {
		this.discapacidades = discapacidades;
	}

	public Collection<Necesidad> getNecesidades() {
		return necesidades;
	}

	public void setNecesidades(Collection<Necesidad> necesidades) {
		this.necesidades = necesidades;
	}
	
	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public FamiliarAcargo getFamiliarAcargo() {
		return familiarAcargo;
	}

	public void setFamiliarAcargo(FamiliarAcargo familiarAcargo) {
		this.familiarAcargo = familiarAcargo;
	}

}
	