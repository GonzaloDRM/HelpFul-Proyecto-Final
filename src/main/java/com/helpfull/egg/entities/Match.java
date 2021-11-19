package com.helpfull.egg.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "MatchTable")//MATCH ES UNA PALABRA RESERVADA DE MYSQL Y NO SE PUEDE USAR COMO NOMBRE DE TABLA
//https://stackoverflow.com/questions/23446377/syntax-error-due-to-using-a-reserved-word-as-a-table-or-column-name-in-mysql
public class Match {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    private Voluntario voluntario;

    @OneToOne
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
