package com.ppanticona.web.rest;

import com.ppanticona.domain.DetalleOrden;
import com.ppanticona.repository.DetalleOrdenRepository;
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
 * REST controller for managing {@link com.ppanticona.domain.DetalleOrden}.
 */
@RestController
@RequestMapping("/api")
public class DetalleOrdenResource {

    private final Logger log = LoggerFactory.getLogger(DetalleOrdenResource.class);

    private static final String ENTITY_NAME = "detalleOrden";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetalleOrdenRepository detalleOrdenRepository;

    public DetalleOrdenResource(DetalleOrdenRepository detalleOrdenRepository) {
        this.detalleOrdenRepository = detalleOrdenRepository;
    }

    /**
     * {@code POST  /detalle-ordens} : Create a new detalleOrden.
     *
     * @param detalleOrden the detalleOrden to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detalleOrden, or with status {@code 400 (Bad Request)} if the detalleOrden has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/detalle-ordens")
    public ResponseEntity<DetalleOrden> createDetalleOrden(@Valid @RequestBody DetalleOrden detalleOrden) throws URISyntaxException {
        log.debug("REST request to save DetalleOrden : {}", detalleOrden);
        if (detalleOrden.getId() != null) {
            throw new BadRequestAlertException("A new detalleOrden cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetalleOrden result = detalleOrdenRepository.save(detalleOrden);
        return ResponseEntity
            .created(new URI("/api/detalle-ordens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /detalle-ordens/:id} : Updates an existing detalleOrden.
     *
     * @param id the id of the detalleOrden to save.
     * @param detalleOrden the detalleOrden to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detalleOrden,
     * or with status {@code 400 (Bad Request)} if the detalleOrden is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detalleOrden couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/detalle-ordens/{id}")
    public ResponseEntity<DetalleOrden> updateDetalleOrden(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody DetalleOrden detalleOrden
    ) throws URISyntaxException {
        log.debug("REST request to update DetalleOrden : {}, {}", id, detalleOrden);
        if (detalleOrden.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detalleOrden.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detalleOrdenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DetalleOrden result = detalleOrdenRepository.save(detalleOrden);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detalleOrden.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /detalle-ordens/:id} : Partial updates given fields of an existing detalleOrden, field will ignore if it is null
     *
     * @param id the id of the detalleOrden to save.
     * @param detalleOrden the detalleOrden to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detalleOrden,
     * or with status {@code 400 (Bad Request)} if the detalleOrden is not valid,
     * or with status {@code 404 (Not Found)} if the detalleOrden is not found,
     * or with status {@code 500 (Internal Server Error)} if the detalleOrden couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/detalle-ordens/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DetalleOrden> partialUpdateDetalleOrden(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody DetalleOrden detalleOrden
    ) throws URISyntaxException {
        log.debug("REST request to partial update DetalleOrden partially : {}, {}", id, detalleOrden);
        if (detalleOrden.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detalleOrden.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detalleOrdenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DetalleOrden> result = detalleOrdenRepository
            .findById(detalleOrden.getId())
            .map(existingDetalleOrden -> {
                if (detalleOrden.getObservacion() != null) {
                    existingDetalleOrden.setObservacion(detalleOrden.getObservacion());
                }
                if (detalleOrden.getMonto() != null) {
                    existingDetalleOrden.setMonto(detalleOrden.getMonto());
                }
                if (detalleOrden.getIndPagado() != null) {
                    existingDetalleOrden.setIndPagado(detalleOrden.getIndPagado());
                }
                if (detalleOrden.getEstado() != null) {
                    existingDetalleOrden.setEstado(detalleOrden.getEstado());
                }
                if (detalleOrden.getVersion() != null) {
                    existingDetalleOrden.setVersion(detalleOrden.getVersion());
                }
                if (detalleOrden.getIndDel() != null) {
                    existingDetalleOrden.setIndDel(detalleOrden.getIndDel());
                }
                if (detalleOrden.getFecCrea() != null) {
                    existingDetalleOrden.setFecCrea(detalleOrden.getFecCrea());
                }
                if (detalleOrden.getUsuCrea() != null) {
                    existingDetalleOrden.setUsuCrea(detalleOrden.getUsuCrea());
                }
                if (detalleOrden.getIpCrea() != null) {
                    existingDetalleOrden.setIpCrea(detalleOrden.getIpCrea());
                }
                if (detalleOrden.getFecModif() != null) {
                    existingDetalleOrden.setFecModif(detalleOrden.getFecModif());
                }
                if (detalleOrden.getUsuModif() != null) {
                    existingDetalleOrden.setUsuModif(detalleOrden.getUsuModif());
                }
                if (detalleOrden.getIpModif() != null) {
                    existingDetalleOrden.setIpModif(detalleOrden.getIpModif());
                }

                return existingDetalleOrden;
            })
            .map(detalleOrdenRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detalleOrden.getId())
        );
    }

    /**
     * {@code GET  /detalle-ordens} : get all the detalleOrdens.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detalleOrdens in body.
     */
    @GetMapping("/detalle-ordens")
    public List<DetalleOrden> getAllDetalleOrdens() {
        log.debug("REST request to get all DetalleOrdens");
        return detalleOrdenRepository.findAll();
    }

    /**
     * {@code GET  /detalle-ordens/:id} : get the "id" detalleOrden.
     *
     * @param id the id of the detalleOrden to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detalleOrden, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/detalle-ordens/{id}")
    public ResponseEntity<DetalleOrden> getDetalleOrden(@PathVariable String id) {
        log.debug("REST request to get DetalleOrden : {}", id);
        Optional<DetalleOrden> detalleOrden = detalleOrdenRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(detalleOrden);
    }

    /**
     * {@code DELETE  /detalle-ordens/:id} : delete the "id" detalleOrden.
     *
     * @param id the id of the detalleOrden to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/detalle-ordens/{id}")
    public ResponseEntity<Void> deleteDetalleOrden(@PathVariable String id) {
        log.debug("REST request to delete DetalleOrden : {}", id);
        detalleOrdenRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
