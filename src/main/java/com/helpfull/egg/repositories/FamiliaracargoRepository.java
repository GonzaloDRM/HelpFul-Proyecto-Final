package com.helpfull.egg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entities.Familiaracargo;

@Repository
public interface FamiliaracargoRepository extends JpaRepository<Familiaracargo, String> {
	
	@Query("SELECT c FROM Familiaracargo c where c.nombre= :nombre")
		public List<Familiaracargo>listar(String nombre);
		 
		
	
	
}
