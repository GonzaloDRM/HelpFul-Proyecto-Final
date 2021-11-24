package com.helpfull.egg.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helpfull.egg.entities.Amigo;
import com.helpfull.egg.entities.Emparejar;
import com.helpfull.egg.entities.Voluntario;
import com.helpfull.egg.enums.Rol;
import com.helpfull.egg.services.EmparejarService;
import com.helpfull.egg.services.VoluntarioService;

@Controller
@RequestMapping("/emparejar")
public class EmparejarController {

	@Autowired
	private EmparejarService emparejarService;
	
	@Autowired
	private VoluntarioService voluntarioService;
	
	@GetMapping("/empareja")
	public String match(Model model) {
		
		List<Voluntario> filtradaMap = voluntarioService.listartodos().stream()
				.filter(a -> a.getRol().equals(Rol.ROLE_VOLUNTARIO))
				.collect(Collectors.toList());
		
		model.addAttribute("voluntarios", filtradaMap);
		return "emparejar";
	}
	
	@GetMapping("/emparejarAmigos")
	public String emparejarAmigos(Model model, @RequestParam(required = false) String username) {
		model.addAttribute("volun", voluntarioService.buscarPorId(username));
		List<Emparejar> emparejamientos = emparejarService.listar();
		List<Amigo> amigos = voluntarioService.buscarPorId(username).getAmigos();
		List<Amigo> eliminar = new ArrayList<>();
		for (Emparejar empareja : emparejamientos) {
			if(empareja.getVoluntario().equals(voluntarioService.buscarPorId(username))) {
				eliminar.add(empareja.getAmigo());
			}
		}
		for (Amigo elim : eliminar) {
			amigos.remove(elim);
		}
		model.addAttribute("voluntario", amigos);
		return "emparejarAmigos";
	}
	
	@PostMapping("/guardar")
	public String guardar(@RequestParam String amigo, @RequestParam String voluntario) {
		emparejarService.save(amigo, voluntario);
		return "redirect:/emparejar/empareja";
	}
	
}
