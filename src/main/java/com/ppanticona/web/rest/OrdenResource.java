package com.ppanticona.web.rest;

import com.ppanticona.domain.Orden;
import com.ppanticona.repository.OrdenRepository;
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
 * REST controller for managing {@link com.ppanticona.domain.Orden}.
 */
@RestController
@RequestMapping("/api")
public class OrdenResource {

    private final Logger log = LoggerFactory.getLogger(OrdenResource.class);

    private static final String ENTITY_NAME = "orden";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrdenRepository ordenRepository;

    public OrdenResource(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    /**
     * {@code POST  /ordens} : Create a new orden.
     *
     * @param orden the orden to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orden, or with status {@code 400 (Bad Request)} if the orden has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ordens")
    public ResponseEntity<Orden> createOrden(@Valid @RequestBody Orden orden) throws URISyntaxException {
        log.debug("REST request to save Orden : {}", orden);
        if (orden.getId() != null) {
            throw new BadRequestAlertException("A new orden cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Orden result = ordenRepository.save(orden);
        return ResponseEntity
            .created(new URI("/api/ordens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /ordens/:id} : Updates an existing orden.
     *
     * @param id the id of the orden to save.
     * @param orden the orden to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orden,
     * or with status {@code 400 (Bad Request)} if the orden is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orden couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ordens/{id}")
    public ResponseEntity<Orden> updateOrden(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Orden orden
    ) throws URISyntaxException {
        log.debug("REST request to update Orden : {}, {}", id, orden);
        if (orden.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orden.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ordenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Orden result = ordenRepository.save(orden);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orden.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /ordens/:id} : Partial updates given fields of an existing orden, field will ignore if it is null
     *
     * @param id the id of the orden to save.
     * @param orden the orden to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orden,
     * or with status {@code 400 (Bad Request)} if the orden is not valid,
     * or with status {@code 404 (Not Found)} if the orden is not found,
     * or with status {@code 500 (Internal Server Error)} if the orden couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ordens/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Orden> partialUpdateOrden(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Orden orden
    ) throws URISyntaxException {
        log.debug("REST request to partial update Orden partially : {}, {}", id, orden);
        if (orden.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orden.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ordenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Orden> result = ordenRepository
            .findById(orden.getId())
            .map(existingOrden -> {
                if (orden.getNumOrden() != null) {
                    existingOrden.setNumOrden(orden.getNumOrden());
                }
                if (orden.getObservacion() != null) {
                    existingOrden.setObservacion(orden.getObservacion());
                }
                if (orden.getFecIngreso() != null) {
                    existingOrden.setFecIngreso(orden.getFecIngreso());
                }
                if (orden.getFecSalida() != null) {
                    existingOrden.setFecSalida(orden.getFecSalida());
                }
                if (orden.getCodCanal() != null) {
                    existingOrden.setCodCanal(orden.getCodCanal());
                }
                if (orden.getTipOrden() != null) {
                    existingOrden.setTipOrden(orden.getTipOrden());
                }
                if (orden.getEstado() != null) {
                    existingOrden.setEstado(orden.getEstado());
                }
                if (orden.getVersion() != null) {
                    existingOrden.setVersion(orden.getVersion());
                }
                if (orden.getIndDel() != null) {
                    existingOrden.setIndDel(orden.getIndDel());
                }
                if (orden.getFecCrea() != null) {
                    existingOrden.setFecCrea(orden.getFecCrea());
                }
                if (orden.getUsuCrea() != null) {
                    existingOrden.setUsuCrea(orden.getUsuCrea());
                }
                if (orden.getIpCrea() != null) {
                    existingOrden.setIpCrea(orden.getIpCrea());
                }
                if (orden.getFecModif() != null) {
                    existingOrden.setFecModif(orden.getFecModif());
                }
                if (orden.getUsuModif() != null) {
                    existingOrden.setUsuModif(orden.getUsuModif());
                }
                if (orden.getIpModif() != null) {
                    existingOrden.setIpModif(orden.getIpModif());
                }

                return existingOrden;
            })
            .map(ordenRepository::save);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orden.getId()));
    }

    /**
     * {@code GET  /ordens} : get all the ordens.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ordens in body.
     */
    @GetMapping("/ordens")
    public List<Orden> getAllOrdens() {
        log.debug("REST request to get all Ordens");
        return ordenRepository.findAll();
    }

    /**
     * {@code GET  /ordens/:id} : get the "id" orden.
     *
     * @param id the id of the orden to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orden, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ordens/{id}")
    public ResponseEntity<Orden> getOrden(@PathVariable String id) {
        log.debug("REST request to get Orden : {}", id);
        Optional<Orden> orden = ordenRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(orden);
    }

    /**
     * {@code DELETE  /ordens/:id} : delete the "id" orden.
     *
     * @param id the id of the orden to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ordens/{id}")
    public ResponseEntity<Void> deleteOrden(@PathVariable String id) {
        log.debug("REST request to delete Orden : {}", id);
        ordenRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
