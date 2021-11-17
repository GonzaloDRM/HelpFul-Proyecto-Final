package com.helpfull.egg.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Emparejar {
	
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy ="uuid2")
	private String id;
	
	@OneToOne
	private Voluntario voluntario;
	
	@OneToOne
	private Amigo amigo;

	public Emparejar() {
		super();
	}

	public Emparejar(String id, Voluntario voluntario, Amigo amigo) {
		super();
		this.id = id;
		this.voluntario = voluntario;
		this.amigo = amigo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Voluntario getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(Voluntario voluntario) {
		this.voluntario = voluntario;
	}

	public Amigo getAmigo() {
		return amigo;
	}

	public void setAmigo(Amigo amigo) {
		this.amigo = amigo;
	}
	
}
