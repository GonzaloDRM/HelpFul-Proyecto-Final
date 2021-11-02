package com.helpfull.egg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entidades.Amigo;

@Repository
public class AmigoRepository extends JpaRepository<Amigo, String>{
	

}
