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

import com.SaberPro.app.entidades.Calificacion;
import com.SaberPro.app.entidades.Estudiante;
import com.SaberPro.app.exception.NotFoundException;
import com.SaberPro.app.repository.CalificacionRepository;
import com.SaberPro.app.repository.EstudianteRepository;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteWebController {

	@Autowired
	private EstudianteRepository estudianteRepository;

	@Autowired
	private CalificacionRepository calificacionRepository;

	@GetMapping("/")
	public String estudianteListTemplate(Model model) {
		model.addAttribute("estudiantes", estudianteRepository.findAll());
		return "estudiantes-list";
	}

	@GetMapping("/new")
	public String estudiantesNewTemplate(Model model) {
		model.addAttribute("estudiante", new Estudiante());
		return "estudiantes-form";
	}

	@GetMapping("/edit/{id}")
	public String estudianteEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("estudiante",
				estudianteRepository.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado")));
		return "estudiantes-form";
	}

	@PostMapping("/save")
	public String estudiantesSaveProcess(@ModelAttribute("estudiante") Estudiante estudiante) {
		initializeEstudiante(estudiante);
		estudianteRepository.save(estudiante);
		return "vista-puntaje";
	}

	@PostMapping("/salvar")
	public String estudiantesSalvarProcess(@ModelAttribute("estudiante") Estudiante estudiante) {
		initializeEstudiante(estudiante);
		estudianteRepository.save(estudiante);
		return "redirect:/estudiantes/";
	}

	private void initializeEstudiante(Estudiante estudiante) {
		if (estudiante.getId() == null || estudiante.getId().isEmpty()) {
			estudiante.setId(null);
		}
		if (estudiante.getRevisado() == null || estudiante.getRevisado().isEmpty()) {
			estudiante.setRevisado("NO");
		}
	}

	@GetMapping("/delete/{id}")
	public String estudiantesDeleteProcess(@PathVariable("id") String id) {
		Estudiante estudiante = estudianteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
		Calificacion calificacion = calificacionRepository.findByEstudiante(estudiante);
		if (calificacion != null) {
			calificacionRepository.deleteById(calificacion.getId());
		}
		estudianteRepository.deleteById(id);
		return "redirect:/estudiantes/";
	}

	@GetMapping("/registro")
	public String registroTemplate(Model model) {
		model.addAttribute("estudiante", new Estudiante());
		return "registro-estudiante";
	}

	@PostMapping("/ingresar")
	public String login(@RequestParam("numeroDocumento") String numeroDocumento,
			@RequestParam("numeroRegistro") String numeroRegistro, Model model) {
		Estudiante estudiante = estudianteRepository.findByNumeroDocumento(numeroDocumento);
		if (estudiante != null) {
			return "redirect:/estudiantes/resultado/" + numeroDocumento;
		} else {
			model.addAttribute("authenticationFailed", true);
			model.addAttribute("errorMessage", "No se encontró ningún estudiante");
			return "index";
		}
	}

	@GetMapping("/resultado/{numeroDocumento}")
	public String estudianteResultTemplate(@PathVariable("numeroDocumento") String numeroDocumento, Model model) {
		Estudiante estudiante = estudianteRepository.findByNumeroDocumento(numeroDocumento);
		if (estudiante == null) {
			throw new NotFoundException("Estudiante no encontrado");
		}

		Calificacion calificacion = calificacionRepository.findByEstudiante(estudiante);
		model.addAttribute("calificacion", calificacion);

		// Establecer el mensaje en caso de que no haya calificaciones
		if (calificacion == null) {
			model.addAttribute("mensaje", "No tienes calificaciones disponibles.");
		}

		estudiante.setRevisado("SI");
		estudianteRepository.save(estudiante);
		return "estudiante_menu"; // Asegúrate de que este es el nombre correcto del template
	}

	@GetMapping("/detallado/{numeroDocumento}")
	public String estudianteDetailtTemplate(@PathVariable("numeroDocumento") String numeroDocumento, Model model) {
		Estudiante estudiante = estudianteRepository.findByNumeroDocumento(numeroDocumento);
		if (estudiante == null) {
			throw new NotFoundException("Estudiante no encontrado");
		}
		model.addAttribute("calificacion", calificacionRepository.findByEstudiante(estudiante));
		return "resultado-detallado";
	}
}
