package com.SaberPro.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.SaberPro.app.entidades.Calificacion;
import com.SaberPro.app.entidades.Estudiante;

public interface CalificacionRepository extends MongoRepository<Calificacion, String> {
	Calificacion findByEstudiante(Estudiante estudiante);
}
