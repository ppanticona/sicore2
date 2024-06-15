package com.ppanticona.web.rest;

import com.ppanticona.domain.Precuenta;
import com.ppanticona.repository.PrecuentaRepository;
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
 * REST controller for managing {@link com.ppanticona.domain.Precuenta}.
 */
@RestController
@RequestMapping("/api")
public class PrecuentaResource {

    private final Logger log = LoggerFactory.getLogger(PrecuentaResource.class);

    private static final String ENTITY_NAME = "precuenta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrecuentaRepository precuentaRepository;

    public PrecuentaResource(PrecuentaRepository precuentaRepository) {
        this.precuentaRepository = precuentaRepository;
    }

    /**
     * {@code POST  /precuentas} : Create a new precuenta.
     *
     * @param precuenta the precuenta to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new precuenta, or with status {@code 400 (Bad Request)} if the precuenta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/precuentas")
    public ResponseEntity<Precuenta> createPrecuenta(@Valid @RequestBody Precuenta precuenta) throws URISyntaxException {
        log.debug("REST request to save Precuenta : {}", precuenta);
        if (precuenta.getId() != null) {
            throw new BadRequestAlertException("A new precuenta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Precuenta result = precuentaRepository.save(precuenta);
        return ResponseEntity
            .created(new URI("/api/precuentas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /precuentas/:id} : Updates an existing precuenta.
     *
     * @param id the id of the precuenta to save.
     * @param precuenta the precuenta to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated precuenta,
     * or with status {@code 400 (Bad Request)} if the precuenta is not valid,
     * or with status {@code 500 (Internal Server Error)} if the precuenta couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/precuentas/{id}")
    public ResponseEntity<Precuenta> updatePrecuenta(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Precuenta precuenta
    ) throws URISyntaxException {
        log.debug("REST request to update Precuenta : {}, {}", id, precuenta);
        if (precuenta.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, precuenta.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!precuentaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Precuenta result = precuentaRepository.save(precuenta);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, precuenta.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /precuentas/:id} : Partial updates given fields of an existing precuenta, field will ignore if it is null
     *
     * @param id the id of the precuenta to save.
     * @param precuenta the precuenta to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated precuenta,
     * or with status {@code 400 (Bad Request)} if the precuenta is not valid,
     * or with status {@code 404 (Not Found)} if the precuenta is not found,
     * or with status {@code 500 (Internal Server Error)} if the precuenta couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/precuentas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Precuenta> partialUpdatePrecuenta(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Precuenta precuenta
    ) throws URISyntaxException {
        log.debug("REST request to partial update Precuenta partially : {}, {}", id, precuenta);
        if (precuenta.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, precuenta.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!precuentaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Precuenta> result = precuentaRepository
            .findById(precuenta.getId())
            .map(existingPrecuenta -> {
                if (precuenta.getNumPrecuenta() != null) {
                    existingPrecuenta.setNumPrecuenta(precuenta.getNumPrecuenta());
                }
                if (precuenta.getObservacion() != null) {
                    existingPrecuenta.setObservacion(precuenta.getObservacion());
                }
                if (precuenta.getEstado() != null) {
                    existingPrecuenta.setEstado(precuenta.getEstado());
                }
                if (precuenta.getVersion() != null) {
                    existingPrecuenta.setVersion(precuenta.getVersion());
                }
                if (precuenta.getIndDel() != null) {
                    existingPrecuenta.setIndDel(precuenta.getIndDel());
                }
                if (precuenta.getFecCrea() != null) {
                    existingPrecuenta.setFecCrea(precuenta.getFecCrea());
                }
                if (precuenta.getUsuCrea() != null) {
                    existingPrecuenta.setUsuCrea(precuenta.getUsuCrea());
                }
                if (precuenta.getIpCrea() != null) {
                    existingPrecuenta.setIpCrea(precuenta.getIpCrea());
                }
                if (precuenta.getFecModif() != null) {
                    existingPrecuenta.setFecModif(precuenta.getFecModif());
                }
                if (precuenta.getUsuModif() != null) {
                    existingPrecuenta.setUsuModif(precuenta.getUsuModif());
                }
                if (precuenta.getIpModif() != null) {
                    existingPrecuenta.setIpModif(precuenta.getIpModif());
                }

                return existingPrecuenta;
            })
            .map(precuentaRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, precuenta.getId())
        );
    }

    /**
     * {@code GET  /precuentas} : get all the precuentas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of precuentas in body.
     */
    @GetMapping("/precuentas")
    public List<Precuenta> getAllPrecuentas() {
        log.debug("REST request to get all Precuentas");
        return precuentaRepository.findAll();
    }

    /**
     * {@code GET  /precuentas/:id} : get the "id" precuenta.
     *
     * @param id the id of the precuenta to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the precuenta, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/precuentas/{id}")
    public ResponseEntity<Precuenta> getPrecuenta(@PathVariable String id) {
        log.debug("REST request to get Precuenta : {}", id);
        Optional<Precuenta> precuenta = precuentaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(precuenta);
    }

    /**
     * {@code DELETE  /precuentas/:id} : delete the "id" precuenta.
     *
     * @param id the id of the precuenta to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/precuentas/{id}")
    public ResponseEntity<Void> deletePrecuenta(@PathVariable String id) {
        log.debug("REST request to delete Precuenta : {}", id);
        precuentaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
