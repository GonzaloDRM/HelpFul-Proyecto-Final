package com.helpfull.egg.controllers;

import java.io.IOException;
import java.time.LocalDate;

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
import com.helpfull.egg.services.AmigoService;

@Controller
@RequestMapping("/amigo")
public class AmigoController {
	
	@Autowired
	private AmigoService amigoService;
	
	@GetMapping("/registroAmigos")
	public String registroAmigos() {
		return "registroAmigos";
	}
	
	@PostMapping("/guardar")
	public String guardar(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String telefono,
						  @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate edad, @RequestParam MultipartFile foto) throws IOException {
		amigoService.save(nombre, apellido, telefono, edad, foto);
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
	
	
	
}
