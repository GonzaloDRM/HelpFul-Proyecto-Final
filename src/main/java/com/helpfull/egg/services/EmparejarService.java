package com.helpfull.egg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpfull.egg.entities.Emparejar;
import com.helpfull.egg.repositories.MatchRepository;

@Service
public class EmparejarService {

	@Autowired
	private MatchRepository emparejarRepository;
	
	@Autowired
	private AmigoService amigoService;
	
	@Autowired
	private VoluntarioService voluntarioService;
	
	@Transactional
	public void save(String amigo, String voluntario) {
		Emparejar emparejar = new Emparejar();
		
		emparejar.setAmigo(amigoService.buscarPorId(amigo));
		emparejar.setVoluntario(voluntarioService.buscarPorId(voluntario));
		
		emparejarRepository.save(emparejar);
	}
	
	@Transactional
	public List<Emparejar> listar() {
		return emparejarRepository.findAll();
	}
}
