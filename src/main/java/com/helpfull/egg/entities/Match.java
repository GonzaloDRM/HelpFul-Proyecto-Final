package com.helpfull.egg.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

@Entity
public class Match {
	
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy ="uuid2")
	private String id;
	
	@ManyToMany
	private Voluntario voluntario;
	
	@ManyToMany
	private Amigo amigo;

	public Match() {
		super();
	}

	public Match(String id, Voluntario voluntario, Amigo amigo) {
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
