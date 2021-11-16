package com.helpfull.egg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpfull.egg.entities.Amigo;
import com.helpfull.egg.entities.Match;
import com.helpfull.egg.entities.Voluntario;
import com.helpfull.egg.repositories.MatchRepository;

@Service
public class MatchService {

	@Autowired
	private MatchRepository matchRepository;
	
	@Transactional
	public void save(Amigo amigo, Voluntario voluntario) {
		Match match = new Match();
		
		match.setAmigo(amigo);
		match.setVoluntario(voluntario);
		
		matchRepository.save(match);
	}
	
}
