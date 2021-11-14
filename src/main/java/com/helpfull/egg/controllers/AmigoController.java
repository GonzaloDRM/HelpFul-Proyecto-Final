package com.helpfull.egg.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import com.helpfull.egg.enums.Interes;
import com.helpfull.egg.enums.Necesidad;
import com.helpfull.egg.services.AmigoService;

@Controller
@RequestMapping("/amigo")
public class AmigoController {
	
	@Autowired
	private AmigoService amigoService;
	
	@GetMapping("/registroAmigos")
	public String registroAmigos(Model model) {
		model.addAttribute("discapacidades", Discapacidad.values());
		model.addAttribute("necesidades", Necesidad.values());
		model.addAttribute("intereses", Interes.values());
		return "registroAmigos";
	}
	
	@PostMapping("/guardar")
	public String guardar(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String telefono,
						  @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate nacimiento, 
						  @RequestParam MultipartFile foto, @RequestParam String direccion,
						  @RequestParam Collection<Interes> intereses,
						  @RequestParam Collection<Discapacidad> discapacidades,
						  @RequestParam Collection<Necesidad> necesidades) throws IOException {
		amigoService.save(nombre, apellido, telefono, nacimiento, foto, direccion, intereses, discapacidades, necesidades);
		return "redirect:/";
	}
	
	@GetMapping("/listaAmigos")
	public String listaAmigos(Model model) {
		model.addAttribute("amigos", amigoService.listar());
		return "listaAmigos";
	}
	
	@GetMapping("/load/{id}")
	public ResponseEntity<byte[]> cargarFoto(@PathVariable String id) {
		Amigo amigo = amigoService.buscarPorId(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(amigo.getFoto(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam String id) {
		amigoService.eliminarAmigo(id);
		return "redirect:/amigo/listaAmigos";
	}
	
}
