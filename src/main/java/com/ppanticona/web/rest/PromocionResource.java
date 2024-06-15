package com.ppanticona.web.rest;

import com.ppanticona.domain.Promocion;
import com.ppanticona.repository.PromocionRepository;
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
 * REST controller for managing {@link com.ppanticona.domain.Promocion}.
 */
@RestController
@RequestMapping("/api")
public class PromocionResource {

    private final Logger log = LoggerFactory.getLogger(PromocionResource.class);

    private static final String ENTITY_NAME = "promocion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PromocionRepository promocionRepository;

    public PromocionResource(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    /**
     * {@code POST  /promocions} : Create a new promocion.
     *
     * @param promocion the promocion to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new promocion, or with status {@code 400 (Bad Request)} if the promocion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/promocions")
    public ResponseEntity<Promocion> createPromocion(@Valid @RequestBody Promocion promocion) throws URISyntaxException {
        log.debug("REST request to save Promocion : {}", promocion);
        if (promocion.getId() != null) {
            throw new BadRequestAlertException("A new promocion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Promocion result = promocionRepository.save(promocion);
        return ResponseEntity
            .created(new URI("/api/promocions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /promocions/:id} : Updates an existing promocion.
     *
     * @param id the id of the promocion to save.
     * @param promocion the promocion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated promocion,
     * or with status {@code 400 (Bad Request)} if the promocion is not valid,
     * or with status {@code 500 (Internal Server Error)} if the promocion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/promocions/{id}")
    public ResponseEntity<Promocion> updatePromocion(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Promocion promocion
    ) throws URISyntaxException {
        log.debug("REST request to update Promocion : {}, {}", id, promocion);
        if (promocion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, promocion.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!promocionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Promocion result = promocionRepository.save(promocion);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, promocion.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /promocions/:id} : Partial updates given fields of an existing promocion, field will ignore if it is null
     *
     * @param id the id of the promocion to save.
     * @param promocion the promocion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated promocion,
     * or with status {@code 400 (Bad Request)} if the promocion is not valid,
     * or with status {@code 404 (Not Found)} if the promocion is not found,
     * or with status {@code 500 (Internal Server Error)} if the promocion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/promocions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Promocion> partialUpdatePromocion(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Promocion promocion
    ) throws URISyntaxException {
        log.debug("REST request to partial update Promocion partially : {}, {}", id, promocion);
        if (promocion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, promocion.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!promocionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Promocion> result = promocionRepository
            .findById(promocion.getId())
            .map(existingPromocion -> {
                if (promocion.getTipPromocion() != null) {
                    existingPromocion.setTipPromocion(promocion.getTipPromocion());
                }
                if (promocion.getVal1() != null) {
                    existingPromocion.setVal1(promocion.getVal1());
                }
                if (promocion.getVal2() != null) {
                    existingPromocion.setVal2(promocion.getVal2());
                }
                if (promocion.getVal3() != null) {
                    existingPromocion.setVal3(promocion.getVal3());
                }
                if (promocion.getVal4() != null) {
                    existingPromocion.setVal4(promocion.getVal4());
                }
                if (promocion.getVal5() != null) {
                    existingPromocion.setVal5(promocion.getVal5());
                }
                if (promocion.getMaxProm() != null) {
                    existingPromocion.setMaxProm(promocion.getMaxProm());
                }
                if (promocion.getFecIniVig() != null) {
                    existingPromocion.setFecIniVig(promocion.getFecIniVig());
                }
                if (promocion.getFecFinVig() != null) {
                    existingPromocion.setFecFinVig(promocion.getFecFinVig());
                }
                if (promocion.getComentarios() != null) {
                    existingPromocion.setComentarios(promocion.getComentarios());
                }
                if (promocion.getEstado() != null) {
                    existingPromocion.setEstado(promocion.getEstado());
                }
                if (promocion.getVersion() != null) {
                    existingPromocion.setVersion(promocion.getVersion());
                }
                if (promocion.getIndDel() != null) {
                    existingPromocion.setIndDel(promocion.getIndDel());
                }
                if (promocion.getFecCrea() != null) {
                    existingPromocion.setFecCrea(promocion.getFecCrea());
                }
                if (promocion.getUsuCrea() != null) {
                    existingPromocion.setUsuCrea(promocion.getUsuCrea());
                }
                if (promocion.getIpCrea() != null) {
                    existingPromocion.setIpCrea(promocion.getIpCrea());
                }
                if (promocion.getFecModif() != null) {
                    existingPromocion.setFecModif(promocion.getFecModif());
                }
                if (promocion.getUsuModif() != null) {
                    existingPromocion.setUsuModif(promocion.getUsuModif());
                }
                if (promocion.getIpModif() != null) {
                    existingPromocion.setIpModif(promocion.getIpModif());
                }

                return existingPromocion;
            })
            .map(promocionRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, promocion.getId())
        );
    }

    /**
     * {@code GET  /promocions} : get all the promocions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of promocions in body.
     */
    @GetMapping("/promocions")
    public List<Promocion> getAllPromocions() {
        log.debug("REST request to get all Promocions");
        return promocionRepository.findAll();
    }

    /**
     * {@code GET  /promocions/:id} : get the "id" promocion.
     *
     * @param id the id of the promocion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the promocion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/promocions/{id}")
    public ResponseEntity<Promocion> getPromocion(@PathVariable String id) {
        log.debug("REST request to get Promocion : {}", id);
        Optional<Promocion> promocion = promocionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(promocion);
    }

    /**
     * {@code DELETE  /promocions/:id} : delete the "id" promocion.
     *
     * @param id the id of the promocion to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/promocions/{id}")
    public ResponseEntity<Void> deletePromocion(@PathVariable String id) {
        log.debug("REST request to delete Promocion : {}", id);
        promocionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
