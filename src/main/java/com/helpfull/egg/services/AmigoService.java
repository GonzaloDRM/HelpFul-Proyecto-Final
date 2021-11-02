package com.helpfull.egg.services;

import com.helpfull.egg.entities.Discapacidad;
import com.helpfull.egg.entities.FamiliarAcargo;
import com.helpfull.egg.entities.Interes;
import com.helpfull.egg.entities.List;
import com.helpfull.egg.entities.Necesidad;
import com.helpfull.egg.entities.Zona;

@Service
public class AmigoService {
	
	@Overwride
	AmigoRepository amigoRepository;
	
	public void crearAmigo(String id, String nombre, String apellido, Integer edad, String telefono, String direccion,
			FamiliarAcargo familiarAcargo, Zona zona, List<Interes> intereses, List<Discapacidad> discapacidades,
			List<Necesidad> necesidades) throws Error{
		
	}
}
