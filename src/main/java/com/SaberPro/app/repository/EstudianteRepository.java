package com.SaberPro.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.SaberPro.app.entidades.Estudiante;


public interface EstudianteRepository extends MongoRepository<Estudiante, String>{

	Estudiante findByNumeroDocumentoAndNumeroRegistro(String numeroDocumento, String numeroRegistro);
	Estudiante findByNumeroDocumento(String numeroDocumento);
	
}
