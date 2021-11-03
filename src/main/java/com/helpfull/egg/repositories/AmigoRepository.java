package com.helpfull.egg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entities.Amigo;

@Repository
public interface AmigoRepository extends JpaRepository<Amigo,String>{
	
	@Query("SELECT a FROM Amigo a WHERE a.zona = :idZona")
	public List<Amigo> buscarAmigosPorZona(@Param("idZona") String idZona);
}
