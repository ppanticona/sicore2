package com.ppanticona.repository;

import com.ppanticona.domain.Precuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Precuenta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrecuentaRepository extends MongoRepository<Precuenta, String> {}
