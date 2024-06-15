package com.ppanticona.repository;

import com.ppanticona.domain.RegCompras;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the RegCompras entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegComprasRepository extends MongoRepository<RegCompras, String> {}
