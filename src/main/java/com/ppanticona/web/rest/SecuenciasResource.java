package com.ppanticona.web.rest;

import com.ppanticona.domain.Secuencias;
import com.ppanticona.repository.SecuenciasRepository;
import com.ppanticona.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ppanticona.domain.Secuencias}.
 */
@RestController
@RequestMapping("/api")
public class SecuenciasResource {

    private final Logger log = LoggerFactory.getLogger(SecuenciasResource.class);

    private static final String ENTITY_NAME = "secuencias";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecuenciasRepository secuenciasRepository;

    public SecuenciasResource(SecuenciasRepository secuenciasRepository) {
        this.secuenciasRepository = secuenciasRepository;
    }

    /**
     * {@code POST  /secuencias} : Create a new secuencias.
     *
     * @param secuencias the secuencias to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new secuencias, or with status {@code 400 (Bad Request)} if the secuencias has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/secuencias")
    public ResponseEntity<Secuencias> createSecuencias(@RequestBody Secuencias secuencias) throws URISyntaxException {
        log.debug("REST request to save Secuencias : {}", secuencias);
        if (secuencias.getId() != null) {
            throw new BadRequestAlertException("A new secuencias cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Secuencias result = secuenciasRepository.save(secuencias);
        return ResponseEntity
            .created(new URI("/api/secuencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /secuencias/:id} : Updates an existing secuencias.
     *
     * @param id the id of the secuencias to save.
     * @param secuencias the secuencias to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secuencias,
     * or with status {@code 400 (Bad Request)} if the secuencias is not valid,
     * or with status {@code 500 (Internal Server Error)} if the secuencias couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/secuencias/{id}")
    public ResponseEntity<Secuencias> updateSecuencias(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Secuencias secuencias
    ) throws URISyntaxException {
        log.debug("REST request to update Secuencias : {}, {}", id, secuencias);
        if (secuencias.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, secuencias.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!secuenciasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Secuencias result = secuenciasRepository.save(secuencias);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, secuencias.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /secuencias/:id} : Partial updates given fields of an existing secuencias, field will ignore if it is null
     *
     * @param id the id of the secuencias to save.
     * @param secuencias the secuencias to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secuencias,
     * or with status {@code 400 (Bad Request)} if the secuencias is not valid,
     * or with status {@code 404 (Not Found)} if the secuencias is not found,
     * or with status {@code 500 (Internal Server Error)} if the secuencias couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/secuencias/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Secuencias> partialUpdateSecuencias(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Secuencias secuencias
    ) throws URISyntaxException {
        log.debug("REST request to partial update Secuencias partially : {}, {}", id, secuencias);
        if (secuencias.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, secuencias.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!secuenciasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Secuencias> result = secuenciasRepository
            .findById(secuencias.getId())
            .map(existingSecuencias -> {
                if (secuencias.getSeqid() != null) {
                    existingSecuencias.setSeqid(secuencias.getSeqid());
                }
                if (secuencias.getDescripcion() != null) {
                    existingSecuencias.setDescripcion(secuencias.getDescripcion());
                }
                if (secuencias.getSequence() != null) {
                    existingSecuencias.setSequence(secuencias.getSequence());
                }

                return existingSecuencias;
            })
            .map(secuenciasRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, secuencias.getId())
        );
    }

    /**
     * {@code GET  /secuencias} : get all the secuencias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of secuencias in body.
     */
    @GetMapping("/secuencias")
    public List<Secuencias> getAllSecuencias() {
        log.debug("REST request to get all Secuencias");
        return secuenciasRepository.findAll();
    }

    /**
     * {@code GET  /secuencias/:id} : get the "id" secuencias.
     *
     * @param id the id of the secuencias to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the secuencias, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/secuencias/{id}")
    public ResponseEntity<Secuencias> getSecuencias(@PathVariable String id) {
        log.debug("REST request to get Secuencias : {}", id);
        Optional<Secuencias> secuencias = secuenciasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(secuencias);
    }

    /**
     * {@code DELETE  /secuencias/:id} : delete the "id" secuencias.
     *
     * @param id the id of the secuencias to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/secuencias/{id}")
    public ResponseEntity<Void> deleteSecuencias(@PathVariable String id) {
        log.debug("REST request to delete Secuencias : {}", id);
        secuenciasRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
