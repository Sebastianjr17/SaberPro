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

import com.SaberPro.app.entidades.Estudiante;
import com.SaberPro.app.exception.NotFoundException;
import com.SaberPro.app.repository.EstudianteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/estudiantes")
public class EstudianteRestController {
	
	@Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("/")
    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Estudiante getEstudianteById(@PathVariable String id) {
        return estudianteRepository.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
    }

    @PostMapping("/")
    public Estudiante saveEstudiante(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Estudiante estudiante = mapper.convertValue(body, Estudiante.class);
        return estudianteRepository.save(estudiante);
    }

    @PutMapping("/{id}")
    public Estudiante updateEstudiante(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Estudiante etudiante = mapper.convertValue(body, Estudiante.class);
        etudiante.setId(id);
        return estudianteRepository.save(etudiante);
    }

    @DeleteMapping("/{id}")
    public Estudiante deleteEstudiante(@PathVariable String id) {
    	Estudiante estudiante = estudianteRepository.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
    	estudianteRepository.deleteById(id);
        return estudiante;
    }
    
    @PutMapping("/{numeroDocumento}")
    public Estudiante resultEstudiante(@PathVariable String numeroDocumento) {
    	return estudianteRepository.findByNumeroDocumento(numeroDocumento);
    }

}
