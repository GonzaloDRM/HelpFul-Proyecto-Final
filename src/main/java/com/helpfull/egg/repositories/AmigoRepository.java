package com.helpfull.egg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entities.Amigo;
import com.helpfull.egg.entities.Zona;

@Repository
public class AmigoRepository extends JpaRepository<Amigo, String> {
	
	@Query("SELECT c FROM Amigo c WHERE Amigo.id = :id")
	public Amigo buscarAmigoPorId(@Param ("id")String id);

}

