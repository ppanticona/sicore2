package com.ppanticona.repository;

import com.ppanticona.domain.AsignacionCaja;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the AsignacionCaja entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AsignacionCajaRepository extends MongoRepository<AsignacionCaja, String> {}
