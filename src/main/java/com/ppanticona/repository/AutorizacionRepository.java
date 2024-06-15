package com.ppanticona.repository;

import com.ppanticona.domain.Autorizacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Autorizacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutorizacionRepository extends MongoRepository<Autorizacion, String> {}
