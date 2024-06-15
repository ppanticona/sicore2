package com.ppanticona.repository;

import com.ppanticona.domain.Sede;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Sede entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SedeRepository extends MongoRepository<Sede, String> {}
