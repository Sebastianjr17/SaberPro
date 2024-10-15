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

import com.SaberPro.app.entidades.Coordinador;
import com.SaberPro.app.exception.NotFoundException;
import com.SaberPro.app.repository.CoordinadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/coordinadores")
public class CoordinadorRestController {

	@Autowired
	private CoordinadorRepository coordinadorRepository;

	@GetMapping("/")
	public List<Coordinador> getAllCoordinadores() {
		return coordinadorRepository.findAll();
	}

	@GetMapping("/{id}")
	public Coordinador getCoordinadorById(@PathVariable String id) {
		return coordinadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Coordinador no encontrado"));
	}

	@PostMapping("/")
	public Coordinador saveCoordinador(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Coordinador coordinador = mapper.convertValue(body, Coordinador.class);
		return coordinadorRepository.save(coordinador);
	}

	@PutMapping("/{id}")
	public Coordinador updateCoordinador(@PathVariable String id, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Coordinador coordinador = mapper.convertValue(body, Coordinador.class);
		coordinador.setId(id);
		return coordinadorRepository.save(coordinador);
	}

	@DeleteMapping("/{id}")
	public Coordinador deleteCoordinador(@PathVariable String id) {
		Coordinador coordinador = coordinadorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Coordinador no encontrado"));
		coordinadorRepository.deleteById(id);
		return coordinador;
	}

}
