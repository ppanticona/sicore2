package com.ppanticona.repository;

import com.ppanticona.domain.Parametros;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Parametros entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParametrosRepository extends MongoRepository<Parametros, String> {}
