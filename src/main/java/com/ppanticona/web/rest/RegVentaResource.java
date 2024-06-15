package com.ppanticona.web.rest;

import com.ppanticona.domain.RegVenta;
import com.ppanticona.repository.RegVentaRepository;
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
 * REST controller for managing {@link com.ppanticona.domain.RegVenta}.
 */
@RestController
@RequestMapping("/api")
public class RegVentaResource {

    private final Logger log = LoggerFactory.getLogger(RegVentaResource.class);

    private static final String ENTITY_NAME = "regVenta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RegVentaRepository regVentaRepository;

    public RegVentaResource(RegVentaRepository regVentaRepository) {
        this.regVentaRepository = regVentaRepository;
    }

    /**
     * {@code POST  /reg-ventas} : Create a new regVenta.
     *
     * @param regVenta the regVenta to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new regVenta, or with status {@code 400 (Bad Request)} if the regVenta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reg-ventas")
    public ResponseEntity<RegVenta> createRegVenta(@Valid @RequestBody RegVenta regVenta) throws URISyntaxException {
        log.debug("REST request to save RegVenta : {}", regVenta);
        if (regVenta.getId() != null) {
            throw new BadRequestAlertException("A new regVenta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RegVenta result = regVentaRepository.save(regVenta);
        return ResponseEntity
            .created(new URI("/api/reg-ventas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /reg-ventas/:id} : Updates an existing regVenta.
     *
     * @param id the id of the regVenta to save.
     * @param regVenta the regVenta to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated regVenta,
     * or with status {@code 400 (Bad Request)} if the regVenta is not valid,
     * or with status {@code 500 (Internal Server Error)} if the regVenta couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reg-ventas/{id}")
    public ResponseEntity<RegVenta> updateRegVenta(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody RegVenta regVenta
    ) throws URISyntaxException {
        log.debug("REST request to update RegVenta : {}, {}", id, regVenta);
        if (regVenta.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, regVenta.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!regVentaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RegVenta result = regVentaRepository.save(regVenta);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, regVenta.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /reg-ventas/:id} : Partial updates given fields of an existing regVenta, field will ignore if it is null
     *
     * @param id the id of the regVenta to save.
     * @param regVenta the regVenta to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated regVenta,
     * or with status {@code 400 (Bad Request)} if the regVenta is not valid,
     * or with status {@code 404 (Not Found)} if the regVenta is not found,
     * or with status {@code 500 (Internal Server Error)} if the regVenta couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/reg-ventas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RegVenta> partialUpdateRegVenta(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody RegVenta regVenta
    ) throws URISyntaxException {
        log.debug("REST request to partial update RegVenta partially : {}, {}", id, regVenta);
        if (regVenta.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, regVenta.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!regVentaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RegVenta> result = regVentaRepository
            .findById(regVenta.getId())
            .map(existingRegVenta -> {
                if (regVenta.getPeriodo() != null) {
                    existingRegVenta.setPeriodo(regVenta.getPeriodo());
                }
                if (regVenta.getCuo() != null) {
                    existingRegVenta.setCuo(regVenta.getCuo());
                }
                if (regVenta.getAsientContab() != null) {
                    existingRegVenta.setAsientContab(regVenta.getAsientContab());
                }
                if (regVenta.getFecEmiComp() != null) {
                    existingRegVenta.setFecEmiComp(regVenta.getFecEmiComp());
                }
                if (regVenta.getFecVencComp() != null) {
                    existingRegVenta.setFecVencComp(regVenta.getFecVencComp());
                }
                if (regVenta.getTipComp() != null) {
                    existingRegVenta.setTipComp(regVenta.getTipComp());
                }
                if (regVenta.getNroSerieComp() != null) {
                    existingRegVenta.setNroSerieComp(regVenta.getNroSerieComp());
                }
                if (regVenta.getNroComp() != null) {
                    existingRegVenta.setNroComp(regVenta.getNroComp());
                }
                if (regVenta.getConsoDia() != null) {
                    existingRegVenta.setConsoDia(regVenta.getConsoDia());
                }
                if (regVenta.getTipDocCli() != null) {
                    existingRegVenta.setTipDocCli(regVenta.getTipDocCli());
                }
                if (regVenta.getNroDocCli() != null) {
                    existingRegVenta.setNroDocCli(regVenta.getNroDocCli());
                }
                if (regVenta.getApeRazSocCli() != null) {
                    existingRegVenta.setApeRazSocCli(regVenta.getApeRazSocCli());
                }
                if (regVenta.getValFacExpor() != null) {
                    existingRegVenta.setValFacExpor(regVenta.getValFacExpor());
                }
                if (regVenta.getBaseImpOperGrav() != null) {
                    existingRegVenta.setBaseImpOperGrav(regVenta.getBaseImpOperGrav());
                }
                if (regVenta.getDsctoBaseImp() != null) {
                    existingRegVenta.setDsctoBaseImp(regVenta.getDsctoBaseImp());
                }
                if (regVenta.getIgvIpm() != null) {
                    existingRegVenta.setIgvIpm(regVenta.getIgvIpm());
                }
                if (regVenta.getDsctoIgvIpm() != null) {
                    existingRegVenta.setDsctoIgvIpm(regVenta.getDsctoIgvIpm());
                }
                if (regVenta.getImpOpeExo() != null) {
                    existingRegVenta.setImpOpeExo(regVenta.getImpOpeExo());
                }
                if (regVenta.getImpTotOpeInafec() != null) {
                    existingRegVenta.setImpTotOpeInafec(regVenta.getImpTotOpeInafec());
                }
                if (regVenta.getImpSecCons() != null) {
                    existingRegVenta.setImpSecCons(regVenta.getImpSecCons());
                }
                if (regVenta.getBaseImpArroz() != null) {
                    existingRegVenta.setBaseImpArroz(regVenta.getBaseImpArroz());
                }
                if (regVenta.getImpVentArroz() != null) {
                    existingRegVenta.setImpVentArroz(regVenta.getImpVentArroz());
                }
                if (regVenta.getOtrosNoBaseImp() != null) {
                    existingRegVenta.setOtrosNoBaseImp(regVenta.getOtrosNoBaseImp());
                }
                if (regVenta.getImporteTotalComp() != null) {
                    existingRegVenta.setImporteTotalComp(regVenta.getImporteTotalComp());
                }
                if (regVenta.getCodMoneda() != null) {
                    existingRegVenta.setCodMoneda(regVenta.getCodMoneda());
                }
                if (regVenta.getTipCambio() != null) {
                    existingRegVenta.setTipCambio(regVenta.getTipCambio());
                }
                if (regVenta.getFecEmiModif() != null) {
                    existingRegVenta.setFecEmiModif(regVenta.getFecEmiModif());
                }
                if (regVenta.getTipCompModif() != null) {
                    existingRegVenta.setTipCompModif(regVenta.getTipCompModif());
                }
                if (regVenta.getNroSerieCompModif() != null) {
                    existingRegVenta.setNroSerieCompModif(regVenta.getNroSerieCompModif());
                }
                if (regVenta.getNroCompModif() != null) {
                    existingRegVenta.setNroCompModif(regVenta.getNroCompModif());
                }
                if (regVenta.getIdentContrato() != null) {
                    existingRegVenta.setIdentContrato(regVenta.getIdentContrato());
                }
                if (regVenta.getErrTipUno() != null) {
                    existingRegVenta.setErrTipUno(regVenta.getErrTipUno());
                }
                if (regVenta.getIndCompVancMp() != null) {
                    existingRegVenta.setIndCompVancMp(regVenta.getIndCompVancMp());
                }
                if (regVenta.getEstadoOperaCancMp() != null) {
                    existingRegVenta.setEstadoOperaCancMp(regVenta.getEstadoOperaCancMp());
                }
                if (regVenta.getCampoLibre() != null) {
                    existingRegVenta.setCampoLibre(regVenta.getCampoLibre());
                }
                if (regVenta.getFormPago() != null) {
                    existingRegVenta.setFormPago(regVenta.getFormPago());
                }
                if (regVenta.getMoneda() != null) {
                    existingRegVenta.setMoneda(regVenta.getMoneda());
                }
                if (regVenta.getIndDel() != null) {
                    existingRegVenta.setIndDel(regVenta.getIndDel());
                }
                if (regVenta.getFecCrea() != null) {
                    existingRegVenta.setFecCrea(regVenta.getFecCrea());
                }
                if (regVenta.getUsuCrea() != null) {
                    existingRegVenta.setUsuCrea(regVenta.getUsuCrea());
                }
                if (regVenta.getIpCrea() != null) {
                    existingRegVenta.setIpCrea(regVenta.getIpCrea());
                }
                if (regVenta.getFecModif() != null) {
                    existingRegVenta.setFecModif(regVenta.getFecModif());
                }
                if (regVenta.getUsuModif() != null) {
                    existingRegVenta.setUsuModif(regVenta.getUsuModif());
                }
                if (regVenta.getIpModif() != null) {
                    existingRegVenta.setIpModif(regVenta.getIpModif());
                }

                return existingRegVenta;
            })
            .map(regVentaRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, regVenta.getId())
        );
    }

    /**
     * {@code GET  /reg-ventas} : get all the regVentas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of regVentas in body.
     */
    @GetMapping("/reg-ventas")
    public List<RegVenta> getAllRegVentas() {
        log.debug("REST request to get all RegVentas");
        return regVentaRepository.findAll();
    }

    /**
     * {@code GET  /reg-ventas/:id} : get the "id" regVenta.
     *
     * @param id the id of the regVenta to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the regVenta, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reg-ventas/{id}")
    public ResponseEntity<RegVenta> getRegVenta(@PathVariable String id) {
        log.debug("REST request to get RegVenta : {}", id);
        Optional<RegVenta> regVenta = regVentaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(regVenta);
    }

    /**
     * {@code DELETE  /reg-ventas/:id} : delete the "id" regVenta.
     *
     * @param id the id of the regVenta to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reg-ventas/{id}")
    public ResponseEntity<Void> deleteRegVenta(@PathVariable String id) {
        log.debug("REST request to delete RegVenta : {}", id);
        regVentaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
