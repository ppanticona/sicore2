package com.ppanticona.web.rest;

import com.ppanticona.domain.AsignacionCaja;
import com.ppanticona.repository.AsignacionCajaRepository;
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
 * REST controller for managing {@link com.ppanticona.domain.AsignacionCaja}.
 */
@RestController
@RequestMapping("/api")
public class AsignacionCajaResource {

    private final Logger log = LoggerFactory.getLogger(AsignacionCajaResource.class);

    private static final String ENTITY_NAME = "asignacionCaja";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AsignacionCajaRepository asignacionCajaRepository;

    public AsignacionCajaResource(AsignacionCajaRepository asignacionCajaRepository) {
        this.asignacionCajaRepository = asignacionCajaRepository;
    }

    /**
     * {@code POST  /asignacion-cajas} : Create a new asignacionCaja.
     *
     * @param asignacionCaja the asignacionCaja to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new asignacionCaja, or with status {@code 400 (Bad Request)} if the asignacionCaja has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/asignacion-cajas")
    public ResponseEntity<AsignacionCaja> createAsignacionCaja(@Valid @RequestBody AsignacionCaja asignacionCaja)
        throws URISyntaxException {
        log.debug("REST request to save AsignacionCaja : {}", asignacionCaja);
        if (asignacionCaja.getId() != null) {
            throw new BadRequestAlertException("A new asignacionCaja cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AsignacionCaja result = asignacionCajaRepository.save(asignacionCaja);
        return ResponseEntity
            .created(new URI("/api/asignacion-cajas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /asignacion-cajas/:id} : Updates an existing asignacionCaja.
     *
     * @param id the id of the asignacionCaja to save.
     * @param asignacionCaja the asignacionCaja to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated asignacionCaja,
     * or with status {@code 400 (Bad Request)} if the asignacionCaja is not valid,
     * or with status {@code 500 (Internal Server Error)} if the asignacionCaja couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/asignacion-cajas/{id}")
    public ResponseEntity<AsignacionCaja> updateAsignacionCaja(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody AsignacionCaja asignacionCaja
    ) throws URISyntaxException {
        log.debug("REST request to update AsignacionCaja : {}, {}", id, asignacionCaja);
        if (asignacionCaja.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, asignacionCaja.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!asignacionCajaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AsignacionCaja result = asignacionCajaRepository.save(asignacionCaja);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, asignacionCaja.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /asignacion-cajas/:id} : Partial updates given fields of an existing asignacionCaja, field will ignore if it is null
     *
     * @param id the id of the asignacionCaja to save.
     * @param asignacionCaja the asignacionCaja to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated asignacionCaja,
     * or with status {@code 400 (Bad Request)} if the asignacionCaja is not valid,
     * or with status {@code 404 (Not Found)} if the asignacionCaja is not found,
     * or with status {@code 500 (Internal Server Error)} if the asignacionCaja couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/asignacion-cajas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AsignacionCaja> partialUpdateAsignacionCaja(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody AsignacionCaja asignacionCaja
    ) throws URISyntaxException {
        log.debug("REST request to partial update AsignacionCaja partially : {}, {}", id, asignacionCaja);
        if (asignacionCaja.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, asignacionCaja.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!asignacionCajaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AsignacionCaja> result = asignacionCajaRepository
            .findById(asignacionCaja.getId())
            .map(existingAsignacionCaja -> {
                if (asignacionCaja.getCodAsignacion() != null) {
                    existingAsignacionCaja.setCodAsignacion(asignacionCaja.getCodAsignacion());
                }
                if (asignacionCaja.getMntoInicialSoles() != null) {
                    existingAsignacionCaja.setMntoInicialSoles(asignacionCaja.getMntoInicialSoles());
                }
                if (asignacionCaja.getMntoFinalSoles() != null) {
                    existingAsignacionCaja.setMntoFinalSoles(asignacionCaja.getMntoFinalSoles());
                }
                if (asignacionCaja.getMontoMaximoSoles() != null) {
                    existingAsignacionCaja.setMontoMaximoSoles(asignacionCaja.getMontoMaximoSoles());
                }
                if (asignacionCaja.getDiferenciaSoles() != null) {
                    existingAsignacionCaja.setDiferenciaSoles(asignacionCaja.getDiferenciaSoles());
                }
                if (asignacionCaja.getMntoInicialDolares() != null) {
                    existingAsignacionCaja.setMntoInicialDolares(asignacionCaja.getMntoInicialDolares());
                }
                if (asignacionCaja.getMntoFinalDolares() != null) {
                    existingAsignacionCaja.setMntoFinalDolares(asignacionCaja.getMntoFinalDolares());
                }
                if (asignacionCaja.getMontoMaximoDolares() != null) {
                    existingAsignacionCaja.setMontoMaximoDolares(asignacionCaja.getMontoMaximoDolares());
                }
                if (asignacionCaja.getDiferenciaDolares() != null) {
                    existingAsignacionCaja.setDiferenciaDolares(asignacionCaja.getDiferenciaDolares());
                }
                if (asignacionCaja.getObservacionApertura() != null) {
                    existingAsignacionCaja.setObservacionApertura(asignacionCaja.getObservacionApertura());
                }
                if (asignacionCaja.getObservacionCierre() != null) {
                    existingAsignacionCaja.setObservacionCierre(asignacionCaja.getObservacionCierre());
                }
                if (asignacionCaja.getFecAsignacion() != null) {
                    existingAsignacionCaja.setFecAsignacion(asignacionCaja.getFecAsignacion());
                }
                if (asignacionCaja.getEstado() != null) {
                    existingAsignacionCaja.setEstado(asignacionCaja.getEstado());
                }
                if (asignacionCaja.getVersion() != null) {
                    existingAsignacionCaja.setVersion(asignacionCaja.getVersion());
                }
                if (asignacionCaja.getIndDel() != null) {
                    existingAsignacionCaja.setIndDel(asignacionCaja.getIndDel());
                }
                if (asignacionCaja.getFecCrea() != null) {
                    existingAsignacionCaja.setFecCrea(asignacionCaja.getFecCrea());
                }
                if (asignacionCaja.getUsuCrea() != null) {
                    existingAsignacionCaja.setUsuCrea(asignacionCaja.getUsuCrea());
                }
                if (asignacionCaja.getIpCrea() != null) {
                    existingAsignacionCaja.setIpCrea(asignacionCaja.getIpCrea());
                }
                if (asignacionCaja.getFecModif() != null) {
                    existingAsignacionCaja.setFecModif(asignacionCaja.getFecModif());
                }
                if (asignacionCaja.getUsuModif() != null) {
                    existingAsignacionCaja.setUsuModif(asignacionCaja.getUsuModif());
                }
                if (asignacionCaja.getIpModif() != null) {
                    existingAsignacionCaja.setIpModif(asignacionCaja.getIpModif());
                }

                return existingAsignacionCaja;
            })
            .map(asignacionCajaRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, asignacionCaja.getId())
        );
    }

    /**
     * {@code GET  /asignacion-cajas} : get all the asignacionCajas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of asignacionCajas in body.
     */
    @GetMapping("/asignacion-cajas")
    public List<AsignacionCaja> getAllAsignacionCajas() {
        log.debug("REST request to get all AsignacionCajas");
        return asignacionCajaRepository.findAll();
    }

    /**
     * {@code GET  /asignacion-cajas/:id} : get the "id" asignacionCaja.
     *
     * @param id the id of the asignacionCaja to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the asignacionCaja, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/asignacion-cajas/{id}")
    public ResponseEntity<AsignacionCaja> getAsignacionCaja(@PathVariable String id) {
        log.debug("REST request to get AsignacionCaja : {}", id);
        Optional<AsignacionCaja> asignacionCaja = asignacionCajaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(asignacionCaja);
    }

    /**
     * {@code DELETE  /asignacion-cajas/:id} : delete the "id" asignacionCaja.
     *
     * @param id the id of the asignacionCaja to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/asignacion-cajas/{id}")
    public ResponseEntity<Void> deleteAsignacionCaja(@PathVariable String id) {
        log.debug("REST request to delete AsignacionCaja : {}", id);
        asignacionCajaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
