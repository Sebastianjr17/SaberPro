package com.SaberPro.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SaberPro.app.entidades.Coordinador;
import com.SaberPro.app.exception.NotFoundException;
import com.SaberPro.app.repository.CoordinadorRepository;

@Controller
@RequestMapping("/coordinadores")
public class CoordinadorWebController {

	@Autowired
	private CoordinadorRepository coordinadorRepository;

	@GetMapping("/")
	public String CoordinadorListTemplate(Model model) {
		model.addAttribute("coordinadores", coordinadorRepository.findAll());
		return "coordinadores-list";
	}

	@GetMapping("/new")
	public String coordinadoresNewTemplate(Model model) {
		model.addAttribute("coordinador", new Coordinador());
		return "coordinadores-form";
	}

	@GetMapping("/edit/{id}")
	public String CoordinadorEditTemplate(@PathVariable("id") String id, Model model) {
		Coordinador coordinador = coordinadorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Coordinador no encontrado"));
		model.addAttribute("coordinador", coordinador);
		return "coordinadores-form";
	}

	@PostMapping("/save")
	public String coordinadoresSaveProcess(@ModelAttribute("coordinador") Coordinador coordinador) {
		if (coordinador.getId().isEmpty()) {
			coordinador.setId(null);
		}
		coordinadorRepository.save(coordinador);
		return "redirect:/coordinadores/";
	}

	@GetMapping("/delete/{id}")
	public String coordinadoresDeleteProcess(@PathVariable("id") String id) {
		coordinadorRepository.deleteById(id);
		return "redirect:/coordinadores/";
	}

	@GetMapping("/registro")
	public String registroTemplate(Model model) {
		model.addAttribute("coordinador", new Coordinador());
		return "registro-coordinador";
	}

	@PostMapping("/ingresar")
	public String login(@RequestParam("user") String user, @RequestParam("password") String password, Model model) {
		Coordinador coordinador = coordinadorRepository.findByUserAndPassword(user, password);
		if (coordinador != null) {
			return "coordinador_menu";
		} else {
			model.addAttribute("authenticationFailed", true);
			model.addAttribute("errorMessage", "Correo o Clave incorrecto");
			return "index";
		}
	}
}
