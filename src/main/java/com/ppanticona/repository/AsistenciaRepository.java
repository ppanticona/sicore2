package com.ppanticona.repository;

import com.ppanticona.domain.Asistencia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Asistencia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AsistenciaRepository extends MongoRepository<Asistencia, String> {}
