package com.helpfull.egg.services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.helpfull.egg.entities.Amigo;
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
	private ZonaService zonaService;
	
	@Transactional
	public void save(String nombre, String apellido, String telefono,
			  		 LocalDate nacimiento, MultipartFile foto, String direccion,
			  		 String provincia, String localidad,
			  		 Collection<Interes> intereses,
					 Collection<Discapacidad> discapacidades,
					 Collection<Necesidad> necesidades) throws Error {
		try {
			
			validar(nombre,apellido,telefono,nacimiento,foto,direccion,provincia,localidad,intereses,discapacidades,necesidades);
			
			Amigo amigo = new Amigo();
			amigo.setNombre(nombre);
			amigo.setApellido(apellido);
			amigo.setTelefono(telefono);
			amigo.setNacimiento(nacimiento);;
			amigo.setFoto(foto.getBytes());
			amigo.setAlta(LocalDate.now());
			amigo.setDireccion(direccion);
			
			Zona zona = zonaService.buscarZona(provincia, localidad);
			
			amigo.setZona(zona);
			amigo.setIntereses(intereses);
			amigo.setDiscapacidades(discapacidades);
			amigo.setNecesidades(necesidades);
			
			amigoRepository.save(amigo);
		}catch(Error e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new Error("Error de sistema");
		}
	}
	
	@Transactional
	public List<Amigo> listar(){
		return amigoRepository.findAll();
	}
	
	@Transactional
	public Amigo buscarPorId(String id) {
		return amigoRepository.getById(id);
	}
	
	@Transactional
	public void eliminarAmigo(String id) {
		amigoRepository.deleteById(id);
	}
	
	public void validar(String nombre, String apellido, String telefono,
			  		 LocalDate nacimiento, MultipartFile foto, String direccion,
			  		 String provincia, String localidad,
			  		 Collection<Interes> intereses,
					 Collection<Discapacidad> discapacidades,
					 Collection<Necesidad> necesidades) throws Error{
		
		if(nombre == null || nombre.isEmpty()){
			throw new Error("Ingresó un nombre vacio o nulo");
		}
		
		if(apellido == null || apellido.isEmpty()){
			throw new Error("Ingresó el apellido vacio o nulo");
		}
		
		if(telefono == null || telefono.isEmpty()){
			throw new Error("Ingresó el telefono vacio o nulo");
		}
		
		if(telefono.length() < 10 || Long.parseLong(telefono) < 0) {
			throw new Error("El telefono no puede tener menos de 8 digitos.");
		}
		
		if(provincia == null || provincia.isEmpty()) {
			throw new Error("No ingresó una provincia o es nula.");
		}
		
		if(localidad == null || localidad.isEmpty()) {
			throw new Error("No ingresó una localidad o es nula.");
		}
		
		if(nacimiento==null) {
			throw new Error("No ingresó una fecha de nacimiento.");
		}
		
		if(intereses == null || discapacidades == null || necesidades == null) {
			throw new Error("Debe marcar alguna opción.");
		}
	}
	
}
