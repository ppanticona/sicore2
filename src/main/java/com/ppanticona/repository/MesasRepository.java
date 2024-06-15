package com.ppanticona.repository;
 
import com.ppanticona.domain.Mesas;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Mesas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MesasRepository extends MongoRepository<Mesas, String> {

    
    public abstract List<Mesas> findAllByEstado(String cod_estado);
}
