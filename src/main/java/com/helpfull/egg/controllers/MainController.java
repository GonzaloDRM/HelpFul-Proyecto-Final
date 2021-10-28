package com.helpfull.egg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping("")
	public String index() {
		return "index";
	}
	
	@GetMapping("/seleccion")
	public String seleccion() {
		return "seleccion";
	}
	
	@GetMapping("/registroAmigo")
	public String registroAmigo() {
		return "registroAmigo";
	}
	
	@GetMapping("/registroVoluntario")
	public String registroVoluntario() {
		return "registroVoluntario";
	}
	
}
