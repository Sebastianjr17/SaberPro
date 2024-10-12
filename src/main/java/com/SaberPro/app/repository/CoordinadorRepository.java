package com.SaberPro.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.SaberPro.app.entidades.Coordinador;

public interface CoordinadorRepository extends MongoRepository<Coordinador, String> {
	Coordinador findByUserAndPassword(String user, String password);
}
