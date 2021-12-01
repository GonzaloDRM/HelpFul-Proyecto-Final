package com.helpfull.egg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entities.Zona;
import java.util.List;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, String> {

    @Query("SELECT c FROM Zona c WHERE c.provincia = :provincia AND c.localidad = :localidad")
    public Zona buscarPorLocalidadProvincia(@Param("provincia") String provincia, @Param("localidad") String localidad);

    @Query("SELECT c FROM Zona c WHERE c.provincia = :provincia")
    public List<Zona> buscarPorProvincia(@Param("provincia") String provincia);

}
