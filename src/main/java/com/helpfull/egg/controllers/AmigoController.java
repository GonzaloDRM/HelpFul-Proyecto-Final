package com.helpfull.egg.controllers;

import java.io.IOException;
import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.helpfull.egg.entities.Amigo;
import com.helpfull.egg.enums.Discapacidad;
import com.helpfull.egg.enums.Necesidad;
import com.helpfull.egg.services.AmigoService;

@Controller
@RequestMapping("/amigo")
public class AmigoController {
	
	@Autowired
	private AmigoService amigoService;
	
	@GetMapping("/registroAmigos")
	public String registroAmigos(Model model) {
		try {
			Necesidad[] necesidades = Necesidad.values();
			Discapacidad[] discapacidades = Discapacidad.values();
			model.addAttribute("discapacidades", discapacidades);
			model.addAttribute("necesidades", necesidades);
		}catch(Error e) {
			model.addAttribute("error", e.getMessage());
			return "registroAmigos";
		}
		return "registroAmigos";
	}
	
	@PostMapping("/registroAmigos")
	public String registroAmigos1(Model model, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String telefono
			,@RequestParam String fechaDeNacimiento, @RequestParam String direccion,
			@RequestParam String discapacidades, @RequestParam String necesidades,@RequestParam MultipartFile archivo) throws IOException, Error {
		
		System.out.println("asd");
		//transformamos lo que reibimos del html a listas y luego a enumset
		EnumSet<Discapacidad> setDiscapacidades = amigoService.transformarArregloAEnumSetDisc(discapacidades);
		EnumSet<Necesidad> setNecesidad = amigoService.transformarArregloAEnumSetNec(necesidades);
		
		//calculamos la edad en base a la fecha de nacimiento.
		Integer  edad = amigoService.calcularEdad(fechaDeNacimiento);
		
		amigoService.crearAmigo(archivo, nombre, apellido, edad, telefono);
		return "redirect:/amigo/registroAmigos";
	}
	
	@GetMapping("/mostar_amigos")
	public String mostrarAmigos(Model model, @RequestParam String idVoluntario) {
		/*el metodo recibe el id del voluntario que se logeo.
		 lugo con ese id buscamos la zona del voluntario y buscamos amigos de esa zona 
		*/
		try {
			//Voluntario voluntario = voluntarioService.buscarVoluntarioID(idVoluntario);
			//List<Amigo> amigos = amigoService.buscarAmigosZona(voluntario.getZona());
			//model.addAttribute("amigos", amigos);
		}catch(Error e) {
			model.addAttribute("error", e.getMessage());
			return "amigo";
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
			return "amigo";
		}	
		
		return "amigo";
	}
	
	@GetMapping("/load/{id}")
	public ResponseEntity<byte[]> cargarFoto(@PathVariable String id) {
		Amigo amigo = amigoService.buscarAmigoID(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(amigo.getFoto(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/listaAmigos")
	public String listaAmigos(Model model) {
		model.addAttribute("amigos", amigoService.listar());
		return "listaAmigos";
	}
	
}
