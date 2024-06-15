package com.ppanticona.repository;

import com.ppanticona.domain.DetallePrecuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the DetallePrecuenta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetallePrecuentaRepository extends MongoRepository<DetallePrecuenta, String> {}
