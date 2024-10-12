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
		// Verificar que el coordinador exista
		Coordinador coordinador = coordinadorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Coordinador no encontrado"));
		model.addAttribute("coordinador", coordinador);
		return "coordinadores-form";
	}

	@PostMapping("/save")
	public String coordinadoresSaveProcess(@ModelAttribute("coordinador") Coordinador coordinador) {
		// Verifica si el ID es nulo
		if (coordinador.getId() == null) {
			coordinador.setId(null);
		}
		coordinadorRepository.save(coordinador);
		return "redirect:/coordinadores/"; // Redirige a la lista de coordinadores
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
		// Verificar las credenciales
		System.out.println("Usuario: " + user + " Password:" + password);

		Coordinador coordinador = coordinadorRepository.findByUserAndPassword(user, password);
		if (coordinador != null) {
			// Inicio de sesión exitoso, redirigir a la página de inicio
			System.out.println("Usuario: " + coordinador.getUser() + " Password:" + coordinador.getPassword());
			return "home"; // Nombre de la página de inicio
		} else {
			// Inicio de sesión fallido
			model.addAttribute("authenticationFailed", true);
			model.addAttribute("errorMessage", "Usuario o contraseña incorrectos");
			return "login-general";
		}
	}
}
