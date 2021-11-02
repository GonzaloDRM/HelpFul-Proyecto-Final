package com.helpfull.egg.services;

import com.helpfull.egg.entities.Zona;
import com.helpfull.egg.repositories.ZonaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZonaService {

    @Autowired
    private ZonaRepository zonaRepository;

    @Transactional
    public void crearZona(String direccion, String barrio, String municipio, String departamento, String provincia) throws Exception {

        validar(direccion, barrio, municipio, departamento, provincia);

        Zona zona = new Zona();
        zona.setDireccion(direccion);
        zona.setBarrio(barrio);
        zona.setMunicipio(municipio);
        zona.setDepartamento(departamento);
        zona.setProvincia(provincia);

        zonaRepository.save(zona);
    }

    @Transactional(readOnly = true)
    public void modificarZona(String id, String direccion, String barrio, String municipio, String departamento, String provincia) {

        validar(direccion, barrio, municipio, departamento, provincia);

        Optional<Zona> respuesta = zonaRepository.findById(id);
        if (respuesta.isPresent()) {
            Zona zona = zonaRepository.findById(id).get();
            zona.setDireccion(direccion);
            zona.setBarrio(barrio);
            zona.setMunicipio(municipio);
            zona.setDepartamento(departamento);
            zona.setProvincia(provincia);;

            zonaRepository.save(zona);
        } else {
            throw new Error("Nose encontro la zona solicitada");
        }
    }

    @Transactional
    private void validar(String direccion, String barrio, String municipio, String departamento, String provincia) throws Error {

        if (direccion == null || direccion.isEmpty()) {
            throw new Error("el direccion del usuario no puede ser nulo");
        }

        if (barrio == null || barrio.isEmpty()) {
            throw new Error("la barrio del usuario no puede ser nulo");
        }

        if (municipio == null || municipio.isEmpty()) {
            throw new Error("la municipio del usuario no puede ser nulo");
        }

        if (departamento == null || departamento.isEmpty()) {
            throw new Error("la departamento del usuario no puede ser nulo");
        }

        if (provincia == null || provincia.isEmpty()) {
            throw new Error("la provincia del usuario no puede ser nulo");
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
