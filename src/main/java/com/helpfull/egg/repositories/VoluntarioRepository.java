package com.helpfull.egg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpfull.egg.entities.Voluntario;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario,String>{

}
