package com.helpfull.egg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpfull.egg.entities.Amigo;
import com.helpfull.egg.entities.Emparejar;
import com.helpfull.egg.entities.Voluntario;
import com.helpfull.egg.repositories.MatchRepository;

@Service
public class EmparejarService {

	@Autowired
	private MatchRepository matchRepository;
	
	@Transactional
	public void save(Amigo amigo, Voluntario voluntario) {
		Emparejar emparejar = new Emparejar();
		
		emparejar.setAmigo(amigo);
		emparejar.setVoluntario(voluntario);
		
		matchRepository.save(emparejar);
	}
	
}
