package com.helpfull.egg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpfull.egg.repositories.AmigoRepository;

@Service
public class AmigoService {
	
	@Autowired
	private AmigoRepository amigoRepository;
	
	@Transactional
	public void save() {
		amigoRepository.save(null);
	}
	
}
