package com.helpfull.egg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entities.Amigo;

@Repository
public interface AmigoRepository extends JpaRepository<Amigo,String>{

}
