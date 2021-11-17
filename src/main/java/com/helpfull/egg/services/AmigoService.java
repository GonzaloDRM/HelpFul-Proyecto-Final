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
					 Collection<Necesidad> necesidades) throws Exception {
		Amigo amigo = new Amigo();
		amigo.setNombre(nombre);
		amigo.setApellido(apellido);
		amigo.setTelefono(telefono);
		amigo.setNacimiento(nacimiento);;
		amigo.setFoto(foto.getBytes());
		amigo.setAlta(LocalDate.now());
		amigo.setDireccion(direccion);
		
		Zona zona = zonaService.crearZona(provincia, localidad);
		
		amigo.setZona(zona);
		amigo.setIntereses(intereses);
		amigo.setDiscapacidades(discapacidades);
		amigo.setNecesidades(necesidades);
		
		amigoRepository.save(amigo);
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
	
}
