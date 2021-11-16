package com.helpfull.egg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helpfull.egg.entities.Amigo;
import com.helpfull.egg.entities.Voluntario;
import com.helpfull.egg.services.MatchService;

@Controller
@RequestMapping("/match")
public class MatchController {

	@Autowired
	private MatchService matchService;
	
	@GetMapping("/match")
	public String match(Model model) {
		
		
		return "match";
	}
	
	@PostMapping("/guardar")
	public String guardar(@RequestParam Amigo amigo, @RequestParam Voluntario voluntario) {
		matchService.save(amigo, voluntario);
		return "redirect:/match/match";
	}
	
}