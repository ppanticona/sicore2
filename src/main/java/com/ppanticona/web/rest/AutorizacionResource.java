package com.ppanticona.web.rest;

import com.ppanticona.domain.Autorizacion;
import com.ppanticona.repository.AutorizacionRepository;
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
 * REST controller for managing {@link com.ppanticona.domain.Autorizacion}.
 */
@RestController
@RequestMapping("/api")
public class AutorizacionResource {

    private final Logger log = LoggerFactory.getLogger(AutorizacionResource.class);

    private static final String ENTITY_NAME = "autorizacion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutorizacionRepository autorizacionRepository;

    public AutorizacionResource(AutorizacionRepository autorizacionRepository) {
        this.autorizacionRepository = autorizacionRepository;
    }

    /**
     * {@code POST  /autorizacions} : Create a new autorizacion.
     *
     * @param autorizacion the autorizacion to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autorizacion, or with status {@code 400 (Bad Request)} if the autorizacion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/autorizacions")
    public ResponseEntity<Autorizacion> createAutorizacion(@Valid @RequestBody Autorizacion autorizacion) throws URISyntaxException {
        log.debug("REST request to save Autorizacion : {}", autorizacion);
        if (autorizacion.getId() != null) {
            throw new BadRequestAlertException("A new autorizacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Autorizacion result = autorizacionRepository.save(autorizacion);
        return ResponseEntity
            .created(new URI("/api/autorizacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /autorizacions/:id} : Updates an existing autorizacion.
     *
     * @param id the id of the autorizacion to save.
     * @param autorizacion the autorizacion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autorizacion,
     * or with status {@code 400 (Bad Request)} if the autorizacion is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autorizacion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/autorizacions/{id}")
    public ResponseEntity<Autorizacion> updateAutorizacion(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Autorizacion autorizacion
    ) throws URISyntaxException {
        log.debug("REST request to update Autorizacion : {}, {}", id, autorizacion);
        if (autorizacion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autorizacion.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autorizacionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Autorizacion result = autorizacionRepository.save(autorizacion);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, autorizacion.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /autorizacions/:id} : Partial updates given fields of an existing autorizacion, field will ignore if it is null
     *
     * @param id the id of the autorizacion to save.
     * @param autorizacion the autorizacion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autorizacion,
     * or with status {@code 400 (Bad Request)} if the autorizacion is not valid,
     * or with status {@code 404 (Not Found)} if the autorizacion is not found,
     * or with status {@code 500 (Internal Server Error)} if the autorizacion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/autorizacions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Autorizacion> partialUpdateAutorizacion(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Autorizacion autorizacion
    ) throws URISyntaxException {
        log.debug("REST request to partial update Autorizacion partially : {}, {}", id, autorizacion);
        if (autorizacion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autorizacion.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autorizacionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Autorizacion> result = autorizacionRepository
            .findById(autorizacion.getId())
            .map(existingAutorizacion -> {
                if (autorizacion.getTipAutorizacion() != null) {
                    existingAutorizacion.setTipAutorizacion(autorizacion.getTipAutorizacion());
                }
                if (autorizacion.getSubTipAutorizacion() != null) {
                    existingAutorizacion.setSubTipAutorizacion(autorizacion.getSubTipAutorizacion());
                }
                if (autorizacion.getConcepto() != null) {
                    existingAutorizacion.setConcepto(autorizacion.getConcepto());
                }
                if (autorizacion.getComentario() != null) {
                    existingAutorizacion.setComentario(autorizacion.getComentario());
                }
                if (autorizacion.getMonto() != null) {
                    existingAutorizacion.setMonto(autorizacion.getMonto());
                }
                if (autorizacion.getMoneda() != null) {
                    existingAutorizacion.setMoneda(autorizacion.getMoneda());
                }
                if (autorizacion.getToken() != null) {
                    existingAutorizacion.setToken(autorizacion.getToken());
                }
                if (autorizacion.getFecIniVig() != null) {
                    existingAutorizacion.setFecIniVig(autorizacion.getFecIniVig());
                }
                if (autorizacion.getFecFinVig() != null) {
                    existingAutorizacion.setFecFinVig(autorizacion.getFecFinVig());
                }
                if (autorizacion.getEstado() != null) {
                    existingAutorizacion.setEstado(autorizacion.getEstado());
                }
                if (autorizacion.getVersion() != null) {
                    existingAutorizacion.setVersion(autorizacion.getVersion());
                }
                if (autorizacion.getIndDel() != null) {
                    existingAutorizacion.setIndDel(autorizacion.getIndDel());
                }
                if (autorizacion.getFecCrea() != null) {
                    existingAutorizacion.setFecCrea(autorizacion.getFecCrea());
                }
                if (autorizacion.getUsuCrea() != null) {
                    existingAutorizacion.setUsuCrea(autorizacion.getUsuCrea());
                }
                if (autorizacion.getIpCrea() != null) {
                    existingAutorizacion.setIpCrea(autorizacion.getIpCrea());
                }
                if (autorizacion.getFecModif() != null) {
                    existingAutorizacion.setFecModif(autorizacion.getFecModif());
                }
                if (autorizacion.getUsuModif() != null) {
                    existingAutorizacion.setUsuModif(autorizacion.getUsuModif());
                }
                if (autorizacion.getIpModif() != null) {
                    existingAutorizacion.setIpModif(autorizacion.getIpModif());
                }

                return existingAutorizacion;
            })
            .map(autorizacionRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, autorizacion.getId())
        );
    }

    /**
     * {@code GET  /autorizacions} : get all the autorizacions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autorizacions in body.
     */
    @GetMapping("/autorizacions")
    public List<Autorizacion> getAllAutorizacions() {
        log.debug("REST request to get all Autorizacions");
        return autorizacionRepository.findAll();
    }

    /**
     * {@code GET  /autorizacions/:id} : get the "id" autorizacion.
     *
     * @param id the id of the autorizacion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autorizacion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/autorizacions/{id}")
    public ResponseEntity<Autorizacion> getAutorizacion(@PathVariable String id) {
        log.debug("REST request to get Autorizacion : {}", id);
        Optional<Autorizacion> autorizacion = autorizacionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(autorizacion);
    }

    /**
     * {@code DELETE  /autorizacions/:id} : delete the "id" autorizacion.
     *
     * @param id the id of the autorizacion to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/autorizacions/{id}")
    public ResponseEntity<Void> deleteAutorizacion(@PathVariable String id) {
        log.debug("REST request to delete Autorizacion : {}", id);
        autorizacionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
