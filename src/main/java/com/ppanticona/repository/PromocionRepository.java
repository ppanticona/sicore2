package com.ppanticona.repository;

import com.ppanticona.domain.Promocion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Promocion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PromocionRepository extends MongoRepository<Promocion, String> {}
