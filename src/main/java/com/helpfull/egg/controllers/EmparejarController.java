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
import com.helpfull.egg.services.EmparejarService;
import com.helpfull.egg.services.VoluntarioService;

@Controller
@RequestMapping("/emparejar")
public class EmparejarController {

	@Autowired
	private EmparejarService matchService;
	
	@Autowired
	private VoluntarioService voluntarioService;
	
	@GetMapping("/empareja")
	public String match(Model model) {
		model.addAttribute("voluntarios", voluntarioService.listartodos());
		return "emparejar";
	}
	
	@GetMapping("/emparejarAmigos")
	public String emparejarAmigos(Model model, @RequestParam(required = false) String username) {
		model.addAttribute("voluntario", voluntarioService.buscarPorId(username).getAmigos());
		return "emparejarAmigos";
	}
	
	@PostMapping("/guardar")
	public String guardar(@RequestParam Amigo amigo, @RequestParam Voluntario voluntario) {
		matchService.save(amigo, voluntario);
		return "redirect:/emparejar/empareja";
	}
	
}
