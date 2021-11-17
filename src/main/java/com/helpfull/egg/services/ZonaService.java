package com.helpfull.egg.services;

import com.helpfull.egg.entities.Zona;
import com.helpfull.egg.repositories.ZonaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZonaService {

    @Autowired
    private ZonaRepository zonaRepository;
    
    @Transactional
    public List<Zona> listar(){
    	return zonaRepository.findAll();
    }

    @Transactional
    public Zona crearZona(String provincia, String localidad) throws Exception {

        validar(provincia,localidad);

        Zona zona = new Zona();

        zona.setProvincia(provincia);
        zona.setLocalidad(localidad);
       
        zonaRepository.save(zona);
        return zona;
    }

    @Transactional(readOnly = true)
    public void modificarZona(String id, String provincia, String localidad) {

        validar(provincia,localidad);

        Optional<Zona> respuesta = zonaRepository.findById(id);
        if (respuesta.isPresent()) {
            Zona zona = zonaRepository.findById(id).get();
            
            zona.setProvincia(provincia);
            zona.setLocalidad(localidad);

            zonaRepository.save(zona);
        } else {
            throw new Error("Nose encontro la zona solicitada");
        }
    }

    @Transactional
    private void validar(String provincia, String localidad) throws Error {
    	
        if (provincia == null || provincia.isEmpty()) {
            throw new Error("la provincia del usuario no puede ser nulo");
        }
        
        if (localidad == null || localidad.isEmpty()) {
            throw new Error("la departamento del usuario no puede ser nulo");
        }

    }
    
    public void eliminar (String id){
        Optional<Zona> respuesta = zonaRepository.findById(id);
        if(respuesta.isPresent()){
            Zona zona = respuesta.get();
            zonaRepository.delete(zona);
        }
    }
}
