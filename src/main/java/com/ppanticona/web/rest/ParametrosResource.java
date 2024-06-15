package com.ppanticona.web.rest;

import com.ppanticona.domain.Parametros;
import com.ppanticona.repository.ParametrosRepository;
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
 * REST controller for managing {@link com.ppanticona.domain.Parametros}.
 */
@RestController
@RequestMapping("/api")
public class ParametrosResource {

    private final Logger log = LoggerFactory.getLogger(ParametrosResource.class);

    private static final String ENTITY_NAME = "parametros";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParametrosRepository parametrosRepository;

    public ParametrosResource(ParametrosRepository parametrosRepository) {
        this.parametrosRepository = parametrosRepository;
    }

    /**
     * {@code POST  /parametros} : Create a new parametros.
     *
     * @param parametros the parametros to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parametros, or with status {@code 400 (Bad Request)} if the parametros has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/parametros")
    public ResponseEntity<Parametros> createParametros(@RequestBody Parametros parametros) throws URISyntaxException {
        log.debug("REST request to save Parametros : {}", parametros);
        if (parametros.getId() != null) {
            throw new BadRequestAlertException("A new parametros cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Parametros result = parametrosRepository.save(parametros);
        return ResponseEntity
            .created(new URI("/api/parametros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /parametros/:id} : Updates an existing parametros.
     *
     * @param id the id of the parametros to save.
     * @param parametros the parametros to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parametros,
     * or with status {@code 400 (Bad Request)} if the parametros is not valid,
     * or with status {@code 500 (Internal Server Error)} if the parametros couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/parametros/{id}")
    public ResponseEntity<Parametros> updateParametros(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Parametros parametros
    ) throws URISyntaxException {
        log.debug("REST request to update Parametros : {}, {}", id, parametros);
        if (parametros.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, parametros.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!parametrosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Parametros result = parametrosRepository.save(parametros);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, parametros.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /parametros/:id} : Partial updates given fields of an existing parametros, field will ignore if it is null
     *
     * @param id the id of the parametros to save.
     * @param parametros the parametros to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parametros,
     * or with status {@code 400 (Bad Request)} if the parametros is not valid,
     * or with status {@code 404 (Not Found)} if the parametros is not found,
     * or with status {@code 500 (Internal Server Error)} if the parametros couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/parametros/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Parametros> partialUpdateParametros(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Parametros parametros
    ) throws URISyntaxException {
        log.debug("REST request to partial update Parametros partially : {}, {}", id, parametros);
        if (parametros.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, parametros.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!parametrosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Parametros> result = parametrosRepository
            .findById(parametros.getId())
            .map(existingParametros -> {
                if (parametros.getCodParam() != null) {
                    existingParametros.setCodParam(parametros.getCodParam());
                }
                if (parametros.getDetParam() != null) {
                    existingParametros.setDetParam(parametros.getDetParam());
                }
                if (parametros.getPrimParam() != null) {
                    existingParametros.setPrimParam(parametros.getPrimParam());
                }
                if (parametros.getSegParam() != null) {
                    existingParametros.setSegParam(parametros.getSegParam());
                }
                if (parametros.getTercParam() != null) {
                    existingParametros.setTercParam(parametros.getTercParam());
                }
                if (parametros.getCuarParam() != null) {
                    existingParametros.setCuarParam(parametros.getCuarParam());
                }
                if (parametros.getDescripcion() != null) {
                    existingParametros.setDescripcion(parametros.getDescripcion());
                }
                if (parametros.getSequence() != null) {
                    existingParametros.setSequence(parametros.getSequence());
                }

                return existingParametros;
            })
            .map(parametrosRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, parametros.getId())
        );
    }

    /**
     * {@code GET  /parametros} : get all the parametros.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parametros in body.
     */
    @GetMapping("/parametros")
    public List<Parametros> getAllParametros() {
        log.debug("REST request to get all Parametros");
        return parametrosRepository.findAll();
    }

    /**
     * {@code GET  /parametros/:id} : get the "id" parametros.
     *
     * @param id the id of the parametros to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parametros, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/parametros/{id}")
    public ResponseEntity<Parametros> getParametros(@PathVariable String id) {
        log.debug("REST request to get Parametros : {}", id);
        Optional<Parametros> parametros = parametrosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(parametros);
    }

    /**
     * {@code DELETE  /parametros/:id} : delete the "id" parametros.
     *
     * @param id the id of the parametros to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/parametros/{id}")
    public ResponseEntity<Void> deleteParametros(@PathVariable String id) {
        log.debug("REST request to delete Parametros : {}", id);
        parametrosRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
