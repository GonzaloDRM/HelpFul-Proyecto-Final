package com.helpfull.egg.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.helpfull.egg.entities.Foto;
import com.helpfull.egg.errors.ErrorServicio;
import com.helpfull.egg.repositories.FotoRepository;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@Transactional
	public Foto guardar(MultipartFile archivo) throws ErrorServicio{
		if (archivo != null) {
			try {
				Foto foto = new Foto();
				foto.setMime(archivo.getContentType());
				foto.setNombre(archivo.getName());
				foto.setContenido(archivo.getBytes());
				
				return fotoRepository.save(foto);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		return null;
	}
	
	@Transactional
	public Foto actualizar(String idFoto, MultipartFile archivo) throws ErrorServicio{
		
		if (archivo != null) {
		
			try {
				Foto foto = new Foto();
				
				if (idFoto != null) {
					Optional <Foto> respuesta = fotoRepository.findById(idFoto);
					if (respuesta.isPresent()) {
						foto = respuesta.get();
						
					}
				}
				
				foto.setMime(archivo.getContentType());
				foto.setNombre(archivo.getName());
				foto.setContenido(archivo.getBytes());
				
				return fotoRepository.save(foto);
			} catch(Exception e) {
				System.err.print(e.getMessage());
			}
		}
		
		return null;
		
		
	}
	
	
	
	

}
