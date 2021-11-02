package com.helpfull.egg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Zona implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Barrio")
    private String barrio;

    @Column(name = "Municipio")
    private String municipio;

    @Column(name = "Departamento")
    private String departamento;

    @Column(name = "Provincia")
    private String provincia;

//    @ManyToMany
//    private Amigo amigo;
//
//    @ManyToMany
//    private Voluntario voluntario;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Zona() {
    }

    public Zona(String barrio, String municipio, String departamento, String provincia) {
        this.barrio = barrio;
        this.municipio = municipio;
        this.departamento = departamento;
        this.provincia = provincia;
    }

//      public Amigo getAmigo() {
//        return amigo;
//    }
//
//    public void setAmigo(Amigo amigo) {
//        this.amigo = amigo;
//    }
//
//    public Voluntario getVoluntario() {
//        return voluntario;
//    }
//
//    public void setVoluntario(Voluntario voluntario) {
//        this.voluntario = voluntario;
//    }
//
    @Override
    public String toString() {
        return "Zona {"
                + "Id" + id
                + "Direccion" + direccion
                + "Barrio" + barrio
                + "Municipio" + municipio
                + "Departamento" + departamento
                + "Provincia" + provincia
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Zona zona = (Zona) o;
        return id != null ? id.equals(zona.id) : zona.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
