package com.ppanticona.repository;

import com.ppanticona.domain.MovimientoCaja;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the MovimientoCaja entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovimientoCajaRepository extends MongoRepository<MovimientoCaja, String> {


    
    @Aggregation(pipeline = {
        "{$match: { 'asignacionCaja._id': ?0, 'tipMovimiento': ?1, 'tipMoneda': ?2 }}",
        "{$group: { '_id': null, 'totalMonto': { $sum: '$monto' } }}"
    })
  public abstract Double sumMontoByAsignacionCajaAndTipMovimientoAndTipMoneda(String asignacionCajaId, String tipMovimiento, String tipMoneda);
}
