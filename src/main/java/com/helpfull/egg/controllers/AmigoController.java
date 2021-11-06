package com.helpfull.egg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helpfull.egg.enums.Discapacidad;
import com.helpfull.egg.enums.Necesidad;
import com.helpfull.egg.services.AmigoService;

@Controller
@RequestMapping("/amigo")
public class AmigoController {
	
	@Autowired
	private AmigoService amigoService;
	
	@GetMapping("/registroAmigos")
	public String registroAmigos(Model model) {
		try {
			Necesidad[] necesidades = Necesidad.values();
			Discapacidad[] discapacidades = Discapacidad.values();
			model.addAttribute("discapacidades", discapacidades);
			model.addAttribute("necesidades", necesidades);
		}catch(Error e) {
			model.addAttribute("error", e.getMessage());
			return "registroAmigos";
		}
		return "registroAmigos";
	}
	
	@GetMapping("/mostar_amigos")
	public String mostrarAmigos(Model model, @RequestParam String idVoluntario) {
		/*el metodo recibe el id del voluntario que se logeo.
		 lugo con ese id buscamos la zona del voluntario y buscamos amigos de esa zona 
		*/
		try {
			//Voluntario voluntario = voluntarioService.buscarVoluntarioID(idVoluntario);
			//List<Amigo> amigos = amigoService.buscarAmigosZona(voluntario.getZona());
			//model.addAttribute("amigos", amigos);
		}catch(Error e) {
			model.addAttribute("error", e.getMessage());
			return "amigo";
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
			return "amigo";
		}	
		
		return "amigo";
	}
	
	@GetMapping("/registroAmigos")
	public String registroAmigos() {
		return "registroAmigos";
	}
}
