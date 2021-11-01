package com.helpfull.egg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpfull.egg.entities.Amigo;
import com.helpfull.egg.entities.Familiaracargo;
import com.helpfull.egg.repositories.FamiliaracargoRepository;


@Service
public class FamiliaracargoService {

	
	

		@Autowired
		private FamiliaracargoRepository FamiliaracargoRepository;
		
		@Transactional
		public void crear (String nombre, String apellido, Amigo amigo)  {
			
			Familiaracargo familiaracargo = new Familiaracargo();
			
			familiaracargo.setNombre(nombre);
			familiaracargo.setApellido(apellido);
			familiaracargo.setAmigo(amigo);
			
					
			FamiliaracargoRepository.save(familiaracargo);
		}
	
}
