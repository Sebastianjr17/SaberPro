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

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/coordinadores")
public class CoordinadorWebController {

	@Autowired
	private CoordinadorRepository coordinadorRepository;

	// Método para la lista de coordinadores
	@GetMapping("/")
	public String CoordinadorListTemplate(Model model, HttpSession session) {
		String authCoordinadorId = (String) session.getAttribute("authCoordinadorId");

		// Only return the authenticated coordinator's info
		if (authCoordinadorId != null) {
			Coordinador coordinador = coordinadorRepository.findById(authCoordinadorId)
					.orElseThrow(() -> new NotFoundException("Coordinador no encontrado"));
			model.addAttribute("coordinadores", coordinador); // Add only the authenticated coordinator
		} else {
			model.addAttribute("coordinadores", null); // No coordinator if not authenticated
		}

		return "coordinadores-list";
	}

	// Método para el formulario de nuevo coordinador
	@GetMapping("/new")
	public String coordinadoresNewTemplate(Model model) {
		model.addAttribute("coordinador", new Coordinador());
		return "coordinadores-form";
	}

	// Método para editar un coordinador solo si es el mismo usuario
	@GetMapping("/edit/{id}")
	public String CoordinadorEditTemplate(@PathVariable("id") String id, Model model, HttpSession session) {
		String authCoordinadorId = (String) session.getAttribute("authCoordinadorId");

		if (authCoordinadorId == null || !authCoordinadorId.equals(id)) {
			return "redirect:/coordinadores/?noPermission=true"; // Redirect if no permission
		}

		Coordinador coordinador = coordinadorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Coordinador no encontrado"));

		model.addAttribute("coordinador", coordinador);
		return "coordinadores-form";
	}

	// Método para guardar coordinadores
	@PostMapping("/save")
	public String coordinadoresSaveProcess(@ModelAttribute("coordinador") Coordinador coordinador,
			HttpSession session) {
		String authCoordinadorId = (String) session.getAttribute("authCoordinadorId");

		// Only allow saving if the authenticated user matches the coordinator being
		// saved
		if (authCoordinadorId == null || !authCoordinadorId.equals(coordinador.getId())) {
			return "redirect:/coordinadores/?noPermission=true"; // Redirect if no permission
		}

		if (coordinador.getId() == null || coordinador.getId().isEmpty()) {
			coordinador.setId(authCoordinadorId); // Set the authenticated user's ID
		}
		coordinadorRepository.save(coordinador);
		return "redirect:/coordinadores/";
	}

	// Método para eliminar coordinador solo si es el mismo usuario
	@GetMapping("/delete/{id}")
	public String coordinadoresDeleteProcess(@PathVariable("id") String id, HttpSession session) {
		String authCoordinadorId = (String) session.getAttribute("authCoordinadorId");

		if (authCoordinadorId == null || !authCoordinadorId.equals(id)) {
			return "redirect:/coordinadores/?noPermission=true"; // Redirect if no permission
		}

		coordinadorRepository.deleteById(id);
		return "redirect:/coordinadores/";
	}

	// Método para el login del coordinador
	@PostMapping("/ingresar")
	public String login(@RequestParam("user") String user, @RequestParam("password") String password, Model model,
			HttpSession session) {
		Coordinador coordinador = coordinadorRepository.findByUserAndPassword(user, password);
		if (coordinador != null) {
			session.setAttribute("authCoordinadorId", coordinador.getId()); // Store authenticated user's ID
			return "coordinador_menu"; // Redirect to the menu page
		} else {
			model.addAttribute("authenticationFailed", true);
			model.addAttribute("errorMessage", "Usuario o contraseña incorrectos");
			return "index"; // Return to login page
		}
	}
}
