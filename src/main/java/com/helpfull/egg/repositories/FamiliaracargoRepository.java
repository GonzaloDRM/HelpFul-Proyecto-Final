package com.helpfull.egg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entities.Familiaracargo;

@Repository
public interface FamiliaracargoRepository extends JpaRepository<Familiaracargo, String> {
	
	@Query("SELECT c FROM Familiaracargo c WHERE c.id = :id")
	public Familiaracargo buscarFamiliarPorId(@Param ("id")String id);
		 
	
}
