package com.helpfull.egg.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.helpfull.egg.entities.Amigo;
import com.helpfull.egg.repositories.AmigoRepository;

@Service
public class AmigoService {

	@Autowired
	private AmigoRepository amigoRepository;
	
	@Transactional
	public void save(String nombre, String apellido, String telefono,
			  		 LocalDate nacimiento, MultipartFile foto, String direccion) throws IOException {
		Amigo amigo = new Amigo();
		amigo.setNombre(nombre);
		amigo.setApellido(apellido);
		amigo.setTelefono(telefono);
		amigo.setNacimiento(nacimiento);;
		amigo.setFoto(foto.getBytes());
		amigo.setAlta(LocalDate.now());
		amigo.setDireccion(direccion);
		
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
	
	
	
}
