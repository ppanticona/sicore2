package com.ppanticona.repository;

import com.ppanticona.domain.Proveedores;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Proveedores entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProveedoresRepository extends MongoRepository<Proveedores, String> {}
