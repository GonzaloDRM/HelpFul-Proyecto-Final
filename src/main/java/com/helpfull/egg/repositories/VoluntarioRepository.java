package com.helpfull.egg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entities.Voluntario;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, String> {

	@Query("SELECT c FROM Voluntario c WHERE c.id = :id")
	public Voluntario buscarVoluntarioPorId(@Param ("id")String id);
		
	
	
}
