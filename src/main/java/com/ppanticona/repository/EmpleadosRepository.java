package com.ppanticona.repository;

import com.ppanticona.domain.Empleados;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Empleados entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmpleadosRepository extends MongoRepository<Empleados, String> {}
