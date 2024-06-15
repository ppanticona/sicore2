package com.ppanticona.web.rest;

import com.ppanticona.domain.MovimientoCaja;
import com.ppanticona.repository.MovimientoCajaRepository;
import com.ppanticona.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ppanticona.domain.MovimientoCaja}.
 */
@RestController
@RequestMapping("/api")
public class MovimientoCajaResource {

    private final Logger log = LoggerFactory.getLogger(MovimientoCajaResource.class);

    private static final String ENTITY_NAME = "movimientoCaja";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MovimientoCajaRepository movimientoCajaRepository;

    public MovimientoCajaResource(MovimientoCajaRepository movimientoCajaRepository) {
        this.movimientoCajaRepository = movimientoCajaRepository;
    }

    /**
     * {@code POST  /movimiento-cajas} : Create a new movimientoCaja.
     *
     * @param movimientoCaja the movimientoCaja to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new movimientoCaja, or with status {@code 400 (Bad Request)} if the movimientoCaja has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/movimiento-cajas")
    public ResponseEntity<MovimientoCaja> createMovimientoCaja(@Valid @RequestBody MovimientoCaja movimientoCaja)
        throws URISyntaxException {
        log.debug("REST request to save MovimientoCaja : {}", movimientoCaja);
        if (movimientoCaja.getId() != null) {
            throw new BadRequestAlertException("A new movimientoCaja cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MovimientoCaja result = movimientoCajaRepository.save(movimientoCaja);
        return ResponseEntity
            .created(new URI("/api/movimiento-cajas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /movimiento-cajas/:id} : Updates an existing movimientoCaja.
     *
     * @param id the id of the movimientoCaja to save.
     * @param movimientoCaja the movimientoCaja to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated movimientoCaja,
     * or with status {@code 400 (Bad Request)} if the movimientoCaja is not valid,
     * or with status {@code 500 (Internal Server Error)} if the movimientoCaja couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/movimiento-cajas/{id}")
    public ResponseEntity<MovimientoCaja> updateMovimientoCaja(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody MovimientoCaja movimientoCaja
    ) throws URISyntaxException {
        log.debug("REST request to update MovimientoCaja : {}, {}", id, movimientoCaja);
        if (movimientoCaja.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, movimientoCaja.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!movimientoCajaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MovimientoCaja result = movimientoCajaRepository.save(movimientoCaja);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, movimientoCaja.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /movimiento-cajas/:id} : Partial updates given fields of an existing movimientoCaja, field will ignore if it is null
     *
     * @param id the id of the movimientoCaja to save.
     * @param movimientoCaja the movimientoCaja to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated movimientoCaja,
     * or with status {@code 400 (Bad Request)} if the movimientoCaja is not valid,
     * or with status {@code 404 (Not Found)} if the movimientoCaja is not found,
     * or with status {@code 500 (Internal Server Error)} if the movimientoCaja couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/movimiento-cajas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MovimientoCaja> partialUpdateMovimientoCaja(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody MovimientoCaja movimientoCaja
    ) throws URISyntaxException {
        log.debug("REST request to partial update MovimientoCaja partially : {}, {}", id, movimientoCaja);
        if (movimientoCaja.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, movimientoCaja.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!movimientoCajaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MovimientoCaja> result = movimientoCajaRepository
            .findById(movimientoCaja.getId())
            .map(existingMovimientoCaja -> {
                if (movimientoCaja.getTipMovimiento() != null) {
                    existingMovimientoCaja.setTipMovimiento(movimientoCaja.getTipMovimiento());
                }
                if (movimientoCaja.getConcepto() != null) {
                    existingMovimientoCaja.setConcepto(movimientoCaja.getConcepto());
                }
                if (movimientoCaja.getMonto() != null) {
                    existingMovimientoCaja.setMonto(movimientoCaja.getMonto());
                }
                if (movimientoCaja.getTipMoneda() != null) {
                    existingMovimientoCaja.setTipMoneda(movimientoCaja.getTipMoneda());
                }
                if (movimientoCaja.getFormPago() != null) {
                    existingMovimientoCaja.setFormPago(movimientoCaja.getFormPago());
                }
                if (movimientoCaja.getComprobante() != null) {
                    existingMovimientoCaja.setComprobante(movimientoCaja.getComprobante());
                }
                if (movimientoCaja.getFecMovimiento() != null) {
                    existingMovimientoCaja.setFecMovimiento(movimientoCaja.getFecMovimiento());
                }
                if (movimientoCaja.getVersion() != null) {
                    existingMovimientoCaja.setVersion(movimientoCaja.getVersion());
                }
                if (movimientoCaja.getIndDel() != null) {
                    existingMovimientoCaja.setIndDel(movimientoCaja.getIndDel());
                }
                if (movimientoCaja.getFecCrea() != null) {
                    existingMovimientoCaja.setFecCrea(movimientoCaja.getFecCrea());
                }
                if (movimientoCaja.getUsuCrea() != null) {
                    existingMovimientoCaja.setUsuCrea(movimientoCaja.getUsuCrea());
                }
                if (movimientoCaja.getIpCrea() != null) {
                    existingMovimientoCaja.setIpCrea(movimientoCaja.getIpCrea());
                }
                if (movimientoCaja.getFecModif() != null) {
                    existingMovimientoCaja.setFecModif(movimientoCaja.getFecModif());
                }
                if (movimientoCaja.getUsuModif() != null) {
                    existingMovimientoCaja.setUsuModif(movimientoCaja.getUsuModif());
                }
                if (movimientoCaja.getIpModif() != null) {
                    existingMovimientoCaja.setIpModif(movimientoCaja.getIpModif());
                }

                return existingMovimientoCaja;
            })
            .map(movimientoCajaRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, movimientoCaja.getId())
        );
    }

    /**
     * {@code GET  /movimiento-cajas} : get all the movimientoCajas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of movimientoCajas in body.
     */
    @GetMapping("/movimiento-cajas")
    public List<MovimientoCaja> getAllMovimientoCajas() {
        log.debug("REST request to get all MovimientoCajas");
        return movimientoCajaRepository.findAll();
    }

    /**
     * {@code GET  /movimiento-cajas/:id} : get the "id" movimientoCaja.
     *
     * @param id the id of the movimientoCaja to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the movimientoCaja, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/movimiento-cajas/{id}")
    public ResponseEntity<MovimientoCaja> getMovimientoCaja(@PathVariable String id) {
        log.debug("REST request to get MovimientoCaja : {}", id);
        Optional<MovimientoCaja> movimientoCaja = movimientoCajaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(movimientoCaja);
    }

    /**
     * {@code DELETE  /movimiento-cajas/:id} : delete the "id" movimientoCaja.
     *
     * @param id the id of the movimientoCaja to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/movimiento-cajas/{id}")
    public ResponseEntity<Void> deleteMovimientoCaja(@PathVariable String id) {
        log.debug("REST request to delete MovimientoCaja : {}", id);
        movimientoCajaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
