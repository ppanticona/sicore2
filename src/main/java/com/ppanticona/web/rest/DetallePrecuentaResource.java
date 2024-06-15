package com.ppanticona.web.rest;

import com.ppanticona.domain.DetallePrecuenta;
import com.ppanticona.repository.DetallePrecuentaRepository;
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
 * REST controller for managing {@link com.ppanticona.domain.DetallePrecuenta}.
 */
@RestController
@RequestMapping("/api")
public class DetallePrecuentaResource {

    private final Logger log = LoggerFactory.getLogger(DetallePrecuentaResource.class);

    private static final String ENTITY_NAME = "detallePrecuenta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetallePrecuentaRepository detallePrecuentaRepository;

    public DetallePrecuentaResource(DetallePrecuentaRepository detallePrecuentaRepository) {
        this.detallePrecuentaRepository = detallePrecuentaRepository;
    }

    /**
     * {@code POST  /detalle-precuentas} : Create a new detallePrecuenta.
     *
     * @param detallePrecuenta the detallePrecuenta to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detallePrecuenta, or with status {@code 400 (Bad Request)} if the detallePrecuenta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/detalle-precuentas")
    public ResponseEntity<DetallePrecuenta> createDetallePrecuenta(@Valid @RequestBody DetallePrecuenta detallePrecuenta)
        throws URISyntaxException {
        log.debug("REST request to save DetallePrecuenta : {}", detallePrecuenta);
        if (detallePrecuenta.getId() != null) {
            throw new BadRequestAlertException("A new detallePrecuenta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetallePrecuenta result = detallePrecuentaRepository.save(detallePrecuenta);
        return ResponseEntity
            .created(new URI("/api/detalle-precuentas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /detalle-precuentas/:id} : Updates an existing detallePrecuenta.
     *
     * @param id the id of the detallePrecuenta to save.
     * @param detallePrecuenta the detallePrecuenta to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detallePrecuenta,
     * or with status {@code 400 (Bad Request)} if the detallePrecuenta is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detallePrecuenta couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/detalle-precuentas/{id}")
    public ResponseEntity<DetallePrecuenta> updateDetallePrecuenta(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody DetallePrecuenta detallePrecuenta
    ) throws URISyntaxException {
        log.debug("REST request to update DetallePrecuenta : {}, {}", id, detallePrecuenta);
        if (detallePrecuenta.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detallePrecuenta.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detallePrecuentaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DetallePrecuenta result = detallePrecuentaRepository.save(detallePrecuenta);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detallePrecuenta.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /detalle-precuentas/:id} : Partial updates given fields of an existing detallePrecuenta, field will ignore if it is null
     *
     * @param id the id of the detallePrecuenta to save.
     * @param detallePrecuenta the detallePrecuenta to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detallePrecuenta,
     * or with status {@code 400 (Bad Request)} if the detallePrecuenta is not valid,
     * or with status {@code 404 (Not Found)} if the detallePrecuenta is not found,
     * or with status {@code 500 (Internal Server Error)} if the detallePrecuenta couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/detalle-precuentas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DetallePrecuenta> partialUpdateDetallePrecuenta(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody DetallePrecuenta detallePrecuenta
    ) throws URISyntaxException {
        log.debug("REST request to partial update DetallePrecuenta partially : {}, {}", id, detallePrecuenta);
        if (detallePrecuenta.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detallePrecuenta.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detallePrecuentaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DetallePrecuenta> result = detallePrecuentaRepository
            .findById(detallePrecuenta.getId())
            .map(existingDetallePrecuenta -> {
                if (detallePrecuenta.getCorrelativo() != null) {
                    existingDetallePrecuenta.setCorrelativo(detallePrecuenta.getCorrelativo());
                }
                if (detallePrecuenta.getEstado() != null) {
                    existingDetallePrecuenta.setEstado(detallePrecuenta.getEstado());
                }
                if (detallePrecuenta.getVersion() != null) {
                    existingDetallePrecuenta.setVersion(detallePrecuenta.getVersion());
                }
                if (detallePrecuenta.getIndDel() != null) {
                    existingDetallePrecuenta.setIndDel(detallePrecuenta.getIndDel());
                }
                if (detallePrecuenta.getFecCrea() != null) {
                    existingDetallePrecuenta.setFecCrea(detallePrecuenta.getFecCrea());
                }
                if (detallePrecuenta.getUsuCrea() != null) {
                    existingDetallePrecuenta.setUsuCrea(detallePrecuenta.getUsuCrea());
                }
                if (detallePrecuenta.getIpCrea() != null) {
                    existingDetallePrecuenta.setIpCrea(detallePrecuenta.getIpCrea());
                }
                if (detallePrecuenta.getFecModif() != null) {
                    existingDetallePrecuenta.setFecModif(detallePrecuenta.getFecModif());
                }
                if (detallePrecuenta.getUsuModif() != null) {
                    existingDetallePrecuenta.setUsuModif(detallePrecuenta.getUsuModif());
                }
                if (detallePrecuenta.getIpModif() != null) {
                    existingDetallePrecuenta.setIpModif(detallePrecuenta.getIpModif());
                }

                return existingDetallePrecuenta;
            })
            .map(detallePrecuentaRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detallePrecuenta.getId())
        );
    }

    /**
     * {@code GET  /detalle-precuentas} : get all the detallePrecuentas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detallePrecuentas in body.
     */
    @GetMapping("/detalle-precuentas")
    public List<DetallePrecuenta> getAllDetallePrecuentas() {
        log.debug("REST request to get all DetallePrecuentas");
        return detallePrecuentaRepository.findAll();
    }

    /**
     * {@code GET  /detalle-precuentas/:id} : get the "id" detallePrecuenta.
     *
     * @param id the id of the detallePrecuenta to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detallePrecuenta, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/detalle-precuentas/{id}")
    public ResponseEntity<DetallePrecuenta> getDetallePrecuenta(@PathVariable String id) {
        log.debug("REST request to get DetallePrecuenta : {}", id);
        Optional<DetallePrecuenta> detallePrecuenta = detallePrecuentaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(detallePrecuenta);
    }

    /**
     * {@code DELETE  /detalle-precuentas/:id} : delete the "id" detallePrecuenta.
     *
     * @param id the id of the detallePrecuenta to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/detalle-precuentas/{id}")
    public ResponseEntity<Void> deleteDetallePrecuenta(@PathVariable String id) {
        log.debug("REST request to delete DetallePrecuenta : {}", id);
        detallePrecuentaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
