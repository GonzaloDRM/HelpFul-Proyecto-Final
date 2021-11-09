package com.helpfull.egg.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/registrarse")
	public String registrarse(Model model) {
		model.addAttribute("zonas", zonaService.listar());
		return "registrarse";
	}
	
	@PostMapping("/registroVoluntario")
	public String registroVoluntario(@RequestParam String username, @RequestParam String nombre,
						@RequestParam String apellido, @RequestParam String password, @RequestParam Integer telefono,
						@RequestParam String email, @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate nacimiento) {
		System.out.println("asd");
		voluntarioService.save(username, nombre, apellido, password, telefono, email, nacimiento);
		return "redirect:/";
	}

}
