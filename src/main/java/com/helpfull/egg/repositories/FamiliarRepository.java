package com.helpfull.egg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entities.FamiliarAcargo;


@Repository
public interface FamiliarRepository extends JpaRepository<FamiliarAcargo,String>{

}
