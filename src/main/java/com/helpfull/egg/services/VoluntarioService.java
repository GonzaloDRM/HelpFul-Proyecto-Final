import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpfull.egg.entities.Amigo;
import com.helpfull.egg.entities.Voluntario;
import com.helpfull.egg.entities.Zona;
import com.helpfull.egg.enums.Interes;
import com.helpfull.egg.errors.ErrorServicio;
import com.helpfull.egg.repositories.AmigoRepository;
import com.helpfull.egg.repositories.FamiliaracargoRepository;
import com.helpfull.egg.repositories.VoluntarioRepository;
import com.helpfull.egg.repositories.ZonaRepository;

@Service
public class VoluntarioService {
	@Autowired
	private VoluntarioRepository voluntarioRepository;
	@Autowired 
	private ZonaRepository zonaRepository;
    @Autowired 
    private AmigoRepository amigoRepository;
   	
	@Transactional
	public void agregarVoluntario(String nombre, String apellido, Amigo amigo, EnumSet <Interes> intereses) {

		Voluntario voluntario = new Voluntario ();

		voluntario.setNombre(nombre);
		voluntario.setApellido(apellido);
		voluntario.setAlta(new Date());
		voluntario.setIntereses(intereses);

		voluntarioRepository.save(voluntario);
	}
	
	
	@Transactional
	public void  eliminar(String id) {
		
		Optional <Voluntario> respuesta = voluntarioRepository.findById(id);
		
		if (respuesta.isPresent()) {
			Voluntario voluntario = respuesta.get();
			voluntario.setBaja(new Date());
			voluntarioRepository.save(voluntario);
			
		}
		
	}
	
	@Transactional
	public void  modificar (String id, String nombre, String apellido, EnumSet <Interes> intereses, String idAmigo, String idZona) {
		
		Optional <Voluntario> respuesta = voluntarioRepository.findById(id);
		
		if (respuesta.isPresent()) {
			
			validar(nombre,apellido,intereses);
			
			Voluntario voluntario = respuesta.get();
			
			voluntario.setNombre(nombre);
			voluntario.setApellido(apellido);
			voluntario.setIntereses(intereses);
			Zona zona = zonaRepository.consultarZonaPorId(idZona);
			voluntario.setZona(zona);
		    Amigo amigo = amigoRepository.buscarAmigoPorId(idAmigo);
		    voluntario.setAmigo(amigo);
			voluntarioRepository.save(voluntario);
			
		}
		
	}
	
	
	
	public void validar(String nombre, String apellido, EnumSet <Interes> intereses) throws ErrorServicio{
		
		if (nombre.isEmpty()||nombre == null) {
			
			throw new ErrorServicio("nombre invalido");
			
		}
		
	    if (intereses.isEmpty()) {
	    	
	    	throw new ErrorServicio("la lista de intereses esta vacia");
			
		}
	} 

}
