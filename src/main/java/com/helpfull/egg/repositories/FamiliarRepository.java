package com.helpfull.egg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entities.FamiliarAcargo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface FamiliarRepository extends JpaRepository<FamiliarAcargo, String> {

    @Query("SELECT f FROM FamiliarAcargo f WHERE f.nombre = :nombre AND f.apellido = :apellido")
    public List<FamiliarAcargo> buscarPorNombreApellido(@Param("nombre") String nombre, @Param("apellido") String apellido);
}
