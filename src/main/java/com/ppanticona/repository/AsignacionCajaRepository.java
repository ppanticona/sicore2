package com.ppanticona.repository;

import com.ppanticona.domain.AsignacionCaja;
import com.ppanticona.domain.User;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the AsignacionCaja entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AsignacionCajaRepository extends MongoRepository<AsignacionCaja, String> {

    
	public abstract AsignacionCaja findByCajaAndEstado(String cajaId, String estado);
    public abstract List<AsignacionCaja> findAllByEstado(String cod_estado);
	public abstract AsignacionCaja findByUserAndEstado(User user, String estado);
}
