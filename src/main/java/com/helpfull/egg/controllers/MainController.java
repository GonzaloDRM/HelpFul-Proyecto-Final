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
	
	@GetMapping("/nuestrahistoria")
	public String quienesSomos() {
		return "nuestrahistoria";
	}
	
	@GetMapping("/amigos")
	public String amigos(){
		return "amigos";
	}
	
	@GetMapping("/match")
	public String match(){
		return "match";
	}
	
	@GetMapping("/voluntarios")
	public String voluntarios(){
		return "voluntarios";
	}
	
	@GetMapping("/seleccion")
	public String seleccion() {
		return "seleccion";
	}
	
}
