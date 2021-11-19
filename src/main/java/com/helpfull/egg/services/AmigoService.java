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
		validar(nombre,apellido,telefono,nacimiento,foto,direccion,provincia,localidad,intereses,discapacidades,necesidades);
		
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
	
	@Transactional
    private void validar(String nombre, String apellido, String telefono,
	  		 LocalDate nacimiento, MultipartFile foto, String direccion,
	  		 String provincia, String localidad,
	  		 Collection<Interes> intereses,
			 Collection<Discapacidad> discapacidades,
			 Collection<Necesidad> necesidades) throws Exception {

        if (nombre == null || nombre.isEmpty()) {
            throw new Exception ("El nombre no puede ser nulo");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new Exception ("El apellido no puede ser nulo");
        }
        if (telefono == null || telefono.isEmpty()) {
            throw new Exception("El telefono del usuario no puede ser nulo");
        }
        if (nacimiento == null ) {
            throw new Exception("La fecha de nacimiento del usuario no puede ser nulo");
        }
        if (foto == null || foto.isEmpty()) {
            throw new Exception("La foto del usuario no puede ser nulo");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new Exception("La foto del usuario no puede ser nulo");
        }
        if (provincia == null || provincia.isEmpty()) {
            throw new Exception("La provincia del usuario no puede ser nulo");
            
        }
        if (localidad == null || localidad.isEmpty()) {
            throw new Exception("La provincia del usuario no puede ser nulo");
            
        }
        if (intereses == null || intereses.isEmpty()) {
            throw new Exception("Los intereses del usuario no pueden ser nulos");
            
        }
        
        if (discapacidades == null || discapacidades.isEmpty()) {
            throw new Exception("La discapacidad no pueden estar vacío");
            
        }
        if (necesidades == null || necesidades.isEmpty()) {
            throw new Exception("Las necesidades del usuario no pueden ser nulos");
                    
    }
	}
}

