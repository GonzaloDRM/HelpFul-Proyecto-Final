package com.helpfull.egg.entities;

import com.helpfull.egg.enums.Interes;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import com.helpfull.egg.enums.InteresVoluntario;
import com.helpfull.egg.enums.Rol;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "Voluntario")
@SQLDelete(sql = "UPDATE Voluntario SET deleted = true WHERE id=?")
@FilterDef(name = "deleteVoluntarioFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deleteVoluntarioFilter", condition = "deleted = is Deleted")
public class Voluntario {

    @Id
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String direccion;
    private Integer dni;
    private String email;
    private Long telefono;
    private LocalDate nacimiento;
    private String descripcion;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] foto;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private LocalDate alta;
    private LocalDate baja;

    @ElementCollection(targetClass = InteresVoluntario.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "interesesVoluntarios")
    private Collection<InteresVoluntario> intereses;

    @ManyToOne
    private Zona zona;

    @ManyToMany
    private List<Amigo> amigos;

    //inhabilitar la cuenta sin eliminar base de datos
    private boolean deleted = Boolean.FALSE;

    public Voluntario() {
        super();
    }

    public Voluntario(String username, String password, String nombre, String apellido, String direccion, Integer dni,
            String email, Long telefono, LocalDate nacimiento, String descripcion, byte[] foto, Rol rol,
            LocalDate alta, LocalDate baja, Collection<InteresVoluntario> intereses, Zona zona) {
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
        this.intereses = intereses;
        this.zona = zona;

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

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
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

    public Collection<InteresVoluntario> getIntereses() {
        return intereses;
    }

    public void setIntereses(Collection<InteresVoluntario> intereses) {
        this.intereses = intereses;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public List<Amigo> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Amigo> amigos) {
        this.amigos = amigos;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
