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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.helpfull.egg.entities.Voluntario;
import com.helpfull.egg.services.VoluntarioService;
import com.helpfull.egg.services.ZonaService;

@Controller
@RequestMapping("/voluntario")
public class VoluntarioController {
	
	@Autowired
	private VoluntarioService voluntarioService;
	
	@Autowired
	private ZonaService zonaService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/perfil")
	public String perfil(Model model) {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		model.addAttribute("usuario", voluntarioService.buscarPorId(userDetails.getUsername()));
		return "perfil";
	}
	
	@GetMapping("/registrarse")
	public String registrarse(Model model) {
		model.addAttribute("zonas", zonaService.listar());
		return "registrarse";
	}
	
	@PostMapping("/registroVoluntario")
	public String registroVoluntario(@RequestParam String username, @RequestParam String nombre,
						@RequestParam String apellido, @RequestParam String password, @RequestParam Integer telefono,
						@RequestParam String email, @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate nacimiento,
						@RequestParam MultipartFile foto) throws IOException {
		
		voluntarioService.save(username, nombre, apellido, password, telefono, email, nacimiento, foto);
		return "redirect:/";
	}
	
	@GetMapping("/load/{id}")
	public ResponseEntity<byte[]> cargarFoto(@PathVariable String id) {
		Voluntario voluntario = voluntarioService.buscarPorId(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(voluntario.getFoto(), headers, HttpStatus.OK);
	}

}
