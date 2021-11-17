package com.helpfull.egg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entities.Zona;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, String> {
/*
    @Query("SELECT c FROM Zona c WHERE c.barrio = :barrio  ORDER BY c.barrio ASC")
	public List<Zona> buscarPorBarrio(@Param("barrio") String barrio);
        
        @Query("SELECT c FROM Zona c WHERE c.municipio = :municipio  ORDER BY c.municipio ASC")
	public List<Zona> buscarPorMunicipio(@Param("municipio") String municipio);
        
        @Query("SELECT c FROM Zona c WHERE c.departamento = :departamento ORDER BY c.departamento ASC")
        public List<Zona> buscarPorDepartameto (@Param("departamento") String departamento);
        
   	@Query("SELECT c FROM Zona c WHERE c.provincia = :provincia  ORDER BY c.provincia ASC")
	public List<Zona> buscarPorProvincia(@Param("provincia") String provincia);

	@Query("SELECT c FROM Zona c WHERE c.direccion = :direccion ORDER BY c.direccion ASC")
	public List<Zona> buscarPorDireccion(@Param("direccion") String direccion);

	@Query("SELECT c FROM Zona c WHERE c.id = :id")
	public Zona consultarZonaPorId(@Param("id") Integer id);
*/	
}
