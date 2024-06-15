package com.ppanticona.repository;

import com.ppanticona.domain.RegVenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the RegVenta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegVentaRepository extends MongoRepository<RegVenta, String> {}
