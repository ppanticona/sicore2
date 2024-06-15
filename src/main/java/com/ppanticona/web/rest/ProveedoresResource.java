package com.ppanticona.web.rest;

import com.ppanticona.domain.Proveedores;
import com.ppanticona.repository.ProveedoresRepository;
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
 * REST controller for managing {@link com.ppanticona.domain.Proveedores}.
 */
@RestController
@RequestMapping("/api")
public class ProveedoresResource {

    private final Logger log = LoggerFactory.getLogger(ProveedoresResource.class);

    private static final String ENTITY_NAME = "proveedores";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProveedoresRepository proveedoresRepository;

    public ProveedoresResource(ProveedoresRepository proveedoresRepository) {
        this.proveedoresRepository = proveedoresRepository;
    }

    /**
     * {@code POST  /proveedores} : Create a new proveedores.
     *
     * @param proveedores the proveedores to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proveedores, or with status {@code 400 (Bad Request)} if the proveedores has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/proveedores")
    public ResponseEntity<Proveedores> createProveedores(@Valid @RequestBody Proveedores proveedores) throws URISyntaxException {
        log.debug("REST request to save Proveedores : {}", proveedores);
        if (proveedores.getId() != null) {
            throw new BadRequestAlertException("A new proveedores cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Proveedores result = proveedoresRepository.save(proveedores);
        return ResponseEntity
            .created(new URI("/api/proveedores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /proveedores/:id} : Updates an existing proveedores.
     *
     * @param id the id of the proveedores to save.
     * @param proveedores the proveedores to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proveedores,
     * or with status {@code 400 (Bad Request)} if the proveedores is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proveedores couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/proveedores/{id}")
    public ResponseEntity<Proveedores> updateProveedores(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Proveedores proveedores
    ) throws URISyntaxException {
        log.debug("REST request to update Proveedores : {}, {}", id, proveedores);
        if (proveedores.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, proveedores.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proveedoresRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Proveedores result = proveedoresRepository.save(proveedores);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proveedores.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /proveedores/:id} : Partial updates given fields of an existing proveedores, field will ignore if it is null
     *
     * @param id the id of the proveedores to save.
     * @param proveedores the proveedores to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proveedores,
     * or with status {@code 400 (Bad Request)} if the proveedores is not valid,
     * or with status {@code 404 (Not Found)} if the proveedores is not found,
     * or with status {@code 500 (Internal Server Error)} if the proveedores couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/proveedores/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Proveedores> partialUpdateProveedores(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Proveedores proveedores
    ) throws URISyntaxException {
        log.debug("REST request to partial update Proveedores partially : {}, {}", id, proveedores);
        if (proveedores.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, proveedores.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proveedoresRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Proveedores> result = proveedoresRepository
            .findById(proveedores.getId())
            .map(existingProveedores -> {
                if (proveedores.getTipDoc() != null) {
                    existingProveedores.setTipDoc(proveedores.getTipDoc());
                }
                if (proveedores.getNroDoc() != null) {
                    existingProveedores.setNroDoc(proveedores.getNroDoc());
                }
                if (proveedores.getNombres() != null) {
                    existingProveedores.setNombres(proveedores.getNombres());
                }
                if (proveedores.getApellidos() != null) {
                    existingProveedores.setApellidos(proveedores.getApellidos());
                }
                if (proveedores.getCategoria() != null) {
                    existingProveedores.setCategoria(proveedores.getCategoria());
                }
                if (proveedores.getTel1() != null) {
                    existingProveedores.setTel1(proveedores.getTel1());
                }
                if (proveedores.getTel2() != null) {
                    existingProveedores.setTel2(proveedores.getTel2());
                }
                if (proveedores.getCorreo() != null) {
                    existingProveedores.setCorreo(proveedores.getCorreo());
                }
                if (proveedores.getDireccion() != null) {
                    existingProveedores.setDireccion(proveedores.getDireccion());
                }
                if (proveedores.getRefDirecc() != null) {
                    existingProveedores.setRefDirecc(proveedores.getRefDirecc());
                }
                if (proveedores.getDistrito() != null) {
                    existingProveedores.setDistrito(proveedores.getDistrito());
                }
                if (proveedores.getFecNac() != null) {
                    existingProveedores.setFecNac(proveedores.getFecNac());
                }
                if (proveedores.getUserId() != null) {
                    existingProveedores.setUserId(proveedores.getUserId());
                }
                if (proveedores.getEstado() != null) {
                    existingProveedores.setEstado(proveedores.getEstado());
                }
                if (proveedores.getVersion() != null) {
                    existingProveedores.setVersion(proveedores.getVersion());
                }
                if (proveedores.getIndDel() != null) {
                    existingProveedores.setIndDel(proveedores.getIndDel());
                }
                if (proveedores.getFecCrea() != null) {
                    existingProveedores.setFecCrea(proveedores.getFecCrea());
                }
                if (proveedores.getUsuCrea() != null) {
                    existingProveedores.setUsuCrea(proveedores.getUsuCrea());
                }
                if (proveedores.getIpCrea() != null) {
                    existingProveedores.setIpCrea(proveedores.getIpCrea());
                }
                if (proveedores.getFecModif() != null) {
                    existingProveedores.setFecModif(proveedores.getFecModif());
                }
                if (proveedores.getUsuModif() != null) {
                    existingProveedores.setUsuModif(proveedores.getUsuModif());
                }
                if (proveedores.getIpModif() != null) {
                    existingProveedores.setIpModif(proveedores.getIpModif());
                }

                return existingProveedores;
            })
            .map(proveedoresRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proveedores.getId())
        );
    }

    /**
     * {@code GET  /proveedores} : get all the proveedores.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proveedores in body.
     */
    @GetMapping("/proveedores")
    public List<Proveedores> getAllProveedores() {
        log.debug("REST request to get all Proveedores");
        return proveedoresRepository.findAll();
    }

    /**
     * {@code GET  /proveedores/:id} : get the "id" proveedores.
     *
     * @param id the id of the proveedores to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proveedores, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/proveedores/{id}")
    public ResponseEntity<Proveedores> getProveedores(@PathVariable String id) {
        log.debug("REST request to get Proveedores : {}", id);
        Optional<Proveedores> proveedores = proveedoresRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(proveedores);
    }

    /**
     * {@code DELETE  /proveedores/:id} : delete the "id" proveedores.
     *
     * @param id the id of the proveedores to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/proveedores/{id}")
    public ResponseEntity<Void> deleteProveedores(@PathVariable String id) {
        log.debug("REST request to delete Proveedores : {}", id);
        proveedoresRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
