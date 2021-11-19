package com.helpfull.egg.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entities.Voluntario;
import java.util.List;
import javax.websocket.Session;
import org.hibernate.annotations.Filter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, String> {

    @Filter(name = "deleteVoluntarioFilter", condition = "isDeleted = 0")
    Optional<Voluntario> findByUsername(String username);

    //inhabilitar la cuenta sin eliminar base de datos
    public interface ProductRepository extends CrudRepository<Voluntario, Long> {

    }
}
