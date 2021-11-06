package com.helpfull.egg.controllers;

import java.time.LocalDate;
import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helpfull.egg.entities.Zona;
import com.helpfull.egg.enums.Interes;
import com.helpfull.egg.services.VoluntarioService;

@Controller
@RequestMapping("/voluntario")
public class VoluntarioController {
	
	@Autowired
	private VoluntarioService voluntarioService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/registrarse")
	public String registrarse() {
		return "registrarse";
	}
	
	@PostMapping("/registroVoluntario")
	public String registroVoluntario(@RequestParam String username, @RequestParam String nombre,
						@RequestParam String apellido, @RequestParam String password, @RequestParam Integer telefono,
						@RequestParam String email, @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate nacimiento) {
		voluntarioService.save(username, nombre, apellido, password, telefono, email, nacimiento);
		return "redirect:/";
	}

}
