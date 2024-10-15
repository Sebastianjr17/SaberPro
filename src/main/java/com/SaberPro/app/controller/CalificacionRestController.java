package com.SaberPro.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SaberPro.app.entidades.Calificacion;
import com.SaberPro.app.exception.NotFoundException;
import com.SaberPro.app.repository.CalificacionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/calificaciones")
public class CalificacionRestController {

	@Autowired
	private CalificacionRepository calificacionRepository;

	@GetMapping("/")
	public List<Calificacion> getAllCalificaciones() {
		return calificacionRepository.findAll();
	}

	@GetMapping("/{id}")
	public Calificacion getCalificacionById(@PathVariable String id) {
		return calificacionRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Calificacion no encontrado"));
	}

	@PostMapping("/")
	public Calificacion saveCalificacion(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Calificacion calificacion = mapper.convertValue(body, Calificacion.class);
		return calificacionRepository.save(calificacion);
	}

	@PutMapping("/{id}")
	public Calificacion updateCalificacion(@PathVariable String id, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Calificacion calificacion = mapper.convertValue(body, Calificacion.class);
		calificacion.setId(id);
		return calificacionRepository.save(calificacion);
	}

	@DeleteMapping("/{id}")
	public Calificacion deleteCalificacion(@PathVariable String id) {
		Calificacion calificacion = calificacionRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Calificacion no encontrado"));
		calificacionRepository.deleteById(id);
		return calificacion;
	}
}
