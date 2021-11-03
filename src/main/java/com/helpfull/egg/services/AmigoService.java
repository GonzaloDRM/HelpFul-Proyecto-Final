package com.helpfull.egg.services;

import java.util.Date;
import java.util.EnumSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpfull.egg.entities.Amigo;
import com.helpfull.egg.entities.FamiliarAcargo;
import com.helpfull.egg.entities.Zona;
import com.helpfull.egg.enums.Discapacidad;
import com.helpfull.egg.enums.Interes;
import com.helpfull.egg.enums.Necesidad;
import com.helpfull.egg.repositories.AmigoRepository;

@Service
public class AmigoService {

	@Autowired
	private AmigoRepository amigoRepository;
	
	@Autowired
	private FamiliarAcargoService familiarAcargoService;
	
	//las calocciones de enum se vera como lo armamos de acuerdo a la html y como lo manda al controlador.
	
	@Transactional
	public void crearAmigo(String nombre, String apellido, Integer edad, String telefono, String direccion, Zona zona,
			EnumSet<Interes> intereses, EnumSet<Discapacidad> discapacidades, EnumSet<Necesidad> necesidades, String nombreFamiliarAcargo, String apellidoFamiliarAcargo, Integer edadFamiliarAcargo, String telefonoFamiliarAcargo,
			String direccionFamiliarAcargo) throws Error{
		
		validar(nombre,apellido,Integer.toString(edad),telefono,direccion,zona,intereses,discapacidades,necesidades);
		
		familiarAcargoService.validarFamiliarAcargo(nombreFamiliarAcargo,apellidoFamiliarAcargo,Integer.toString(edadFamiliarAcargo),telefonoFamiliarAcargo,direccionFamiliarAcargo);
		
		Amigo amigo = new Amigo();
		
		FamiliarAcargo familiarAcargo = familiarAcargoService.crearFamiliarAcargo(nombreFamiliarAcargo, apellidoFamiliarAcargo, edadFamiliarAcargo, telefonoFamiliarAcargo, direccionFamiliarAcargo);
		
		//cuando se crea un amigo se setea automaticamente la fecha y hora.
		amigo.setAlta(new Date());
		
		amigo.setNombre(nombre);
		amigo.setApellido(apellido);
		amigo.setEdad(edad);
		amigo.setTelefono(telefono);
		amigo.setZona(zona);
		amigo.setIntereses(intereses);
		amigo.setDiscapacidades(discapacidades);
		amigo.setNecesidades(necesidades);
		
		amigo.setFamiliarAcargo(familiarAcargo);
		
		amigoRepository.save(amigo);
	}
	
	//las calocciones de enum se vera como lo armamos de acuerdo a la html y como lo manda al controlador.
	
	@Transactional
	public void modificarAmigo(String id, String nombre, String apellido, Integer edad, String telefono, String direccion,
			Zona zona, EnumSet<Interes> intereses,
			EnumSet<Discapacidad> discapacidades, EnumSet<Necesidad> necesidades, String nombreFamiliarAcargo, String apellidoFamiliarAcargo, Integer edadFamiliarAcargo, String telefonoFamiliarAcargo,
			String direccionFamiliarAcargo) throws Error {
		
		Amigo amigo = buscarAmigoID(id);
		
		//seteamos los valores modificado de amigo
		amigo.setNombre(nombre);
		amigo.setApellido(apellido);
		amigo.setEdad(edad);
		amigo.setTelefono(telefono);
		amigo.setZona(zona);
		amigo.setIntereses(intereses);
		amigo.setDiscapacidades(discapacidades);
		amigo.setNecesidades(necesidades);
		
		//seteamos los valores modificado de familiar a cargo
		amigo.getFamiliarAcargo().setNombre(nombreFamiliarAcargo);
		amigo.getFamiliarAcargo().setApellido(apellidoFamiliarAcargo);
		amigo.getFamiliarAcargo().setEdad(edadFamiliarAcargo);
		amigo.getFamiliarAcargo().setTelefono(telefonoFamiliarAcargo);
		amigo.getFamiliarAcargo().setDireccion(direccionFamiliarAcargo);
		
		amigoRepository.save(amigo);
		
	}
	
	public Amigo buscarAmigoID(String id) throws Error {
		Optional<Amigo> optional = amigoRepository.findById(id);
		if(optional.isPresent()) {
			throw new Error("No se encontro el amigo");
		}
		Amigo amigo = optional.get();
		return amigo;
	}

	public void validar(String nombre, String apellido, String edad, String telefono, String direccion, Zona zona,
			EnumSet<Interes> intereses, EnumSet<Discapacidad> discapacidades, EnumSet<Necesidad> necesidades)
			throws Error {

		if (nombre.isEmpty() || nombre == null) {
			throw new Error("nombre invalido");
		}

		if (apellido.isEmpty() || apellido == null) {
			throw new Error("apellido invalido");
		}

		if (Integer.parseInt(edad) <= 0 || edad.isEmpty() || edad == null) {
			throw new Error("edad invalida");
		}

		if (telefono.isEmpty() || telefono == null) {
			throw new Error("telefono invalido");
		}

		if (direccion.isEmpty() || direccion == null) {
			throw new Error("direccion invalida");
		}

		if (zona == null) {
			throw new Error("no ingresó la zona");
		}

		if (intereses == null || intereses.isEmpty()) {
			throw new Error("no marcó ningún interes");
		}

		if (necesidades == null || necesidades.isEmpty()) {
			throw new Error("no marcó ninguna necesidad");
		}

		if (discapacidades == null || discapacidades.isEmpty()) {
			throw new Error("no marcó ninguna discapacidad");
		}

	}

	
}