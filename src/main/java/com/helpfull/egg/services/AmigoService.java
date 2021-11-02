package com.helpfull.egg.services;

import org.springframework.stereotype.Service;

import com.helpfull.egg.entities.FamiliarAcargo;
import com.helpfull.egg.entities.Zona;
import com.helpfull.egg.enums.Discapacidad;
import com.helpfull.egg.enums.Necesidad;
import com.helpfull.egg.repositories.AmigoRepository;

@Service
public class AmigoService {
	
	@Autowired
	AmigoRepository amigoRepository;
	
	public void crearAmigo(String id, String nombre, String apellido, Integer edad, String telefono, String direccion,
			FamiliarAcargo familiarAcargo, Zona zona, List<Interes> intereses, List<Discapacidad> discapacidades,
			List<Necesidad> necesidades) throws Error{
		
	}
}
 