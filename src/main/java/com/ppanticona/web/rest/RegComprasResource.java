package com.ppanticona.web.rest;

import com.ppanticona.domain.RegCompras;
import com.ppanticona.repository.RegComprasRepository;
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
 * REST controller for managing {@link com.ppanticona.domain.RegCompras}.
 */
@RestController
@RequestMapping("/api")
public class RegComprasResource {

    private final Logger log = LoggerFactory.getLogger(RegComprasResource.class);

    private static final String ENTITY_NAME = "regCompras";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RegComprasRepository regComprasRepository;

    public RegComprasResource(RegComprasRepository regComprasRepository) {
        this.regComprasRepository = regComprasRepository;
    }

    /**
     * {@code POST  /reg-compras} : Create a new regCompras.
     *
     * @param regCompras the regCompras to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new regCompras, or with status {@code 400 (Bad Request)} if the regCompras has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reg-compras")
    public ResponseEntity<RegCompras> createRegCompras(@Valid @RequestBody RegCompras regCompras) throws URISyntaxException {
        log.debug("REST request to save RegCompras : {}", regCompras);
        if (regCompras.getId() != null) {
            throw new BadRequestAlertException("A new regCompras cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RegCompras result = regComprasRepository.save(regCompras);
        return ResponseEntity
            .created(new URI("/api/reg-compras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /reg-compras/:id} : Updates an existing regCompras.
     *
     * @param id the id of the regCompras to save.
     * @param regCompras the regCompras to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated regCompras,
     * or with status {@code 400 (Bad Request)} if the regCompras is not valid,
     * or with status {@code 500 (Internal Server Error)} if the regCompras couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reg-compras/{id}")
    public ResponseEntity<RegCompras> updateRegCompras(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody RegCompras regCompras
    ) throws URISyntaxException {
        log.debug("REST request to update RegCompras : {}, {}", id, regCompras);
        if (regCompras.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, regCompras.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!regComprasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RegCompras result = regComprasRepository.save(regCompras);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, regCompras.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /reg-compras/:id} : Partial updates given fields of an existing regCompras, field will ignore if it is null
     *
     * @param id the id of the regCompras to save.
     * @param regCompras the regCompras to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated regCompras,
     * or with status {@code 400 (Bad Request)} if the regCompras is not valid,
     * or with status {@code 404 (Not Found)} if the regCompras is not found,
     * or with status {@code 500 (Internal Server Error)} if the regCompras couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/reg-compras/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RegCompras> partialUpdateRegCompras(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody RegCompras regCompras
    ) throws URISyntaxException {
        log.debug("REST request to partial update RegCompras partially : {}, {}", id, regCompras);
        if (regCompras.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, regCompras.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!regComprasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RegCompras> result = regComprasRepository
            .findById(regCompras.getId())
            .map(existingRegCompras -> {
                if (regCompras.getPeriodo() != null) {
                    existingRegCompras.setPeriodo(regCompras.getPeriodo());
                }
                if (regCompras.getCuo() != null) {
                    existingRegCompras.setCuo(regCompras.getCuo());
                }
                if (regCompras.getAsientContab() != null) {
                    existingRegCompras.setAsientContab(regCompras.getAsientContab());
                }
                if (regCompras.getFecEmiComp() != null) {
                    existingRegCompras.setFecEmiComp(regCompras.getFecEmiComp());
                }
                if (regCompras.getFecVencComp() != null) {
                    existingRegCompras.setFecVencComp(regCompras.getFecVencComp());
                }
                if (regCompras.getTipComp() != null) {
                    existingRegCompras.setTipComp(regCompras.getTipComp());
                }
                if (regCompras.getNroSerieComp() != null) {
                    existingRegCompras.setNroSerieComp(regCompras.getNroSerieComp());
                }
                if (regCompras.getAnhoEmisionDua() != null) {
                    existingRegCompras.setAnhoEmisionDua(regCompras.getAnhoEmisionDua());
                }
                if (regCompras.getNroComp() != null) {
                    existingRegCompras.setNroComp(regCompras.getNroComp());
                }
                if (regCompras.getNroFinal() != null) {
                    existingRegCompras.setNroFinal(regCompras.getNroFinal());
                }
                if (regCompras.getTipDocProv() != null) {
                    existingRegCompras.setTipDocProv(regCompras.getTipDocProv());
                }
                if (regCompras.getNroDocProv() != null) {
                    existingRegCompras.setNroDocProv(regCompras.getNroDocProv());
                }
                if (regCompras.getNomApeRazSocProv() != null) {
                    existingRegCompras.setNomApeRazSocProv(regCompras.getNomApeRazSocProv());
                }
                if (regCompras.getBaseImponible() != null) {
                    existingRegCompras.setBaseImponible(regCompras.getBaseImponible());
                }
                if (regCompras.getMontoIgv() != null) {
                    existingRegCompras.setMontoIgv(regCompras.getMontoIgv());
                }
                if (regCompras.getBaseImponible2() != null) {
                    existingRegCompras.setBaseImponible2(regCompras.getBaseImponible2());
                }
                if (regCompras.getMontoIgv2() != null) {
                    existingRegCompras.setMontoIgv2(regCompras.getMontoIgv2());
                }
                if (regCompras.getBaseImponible3() != null) {
                    existingRegCompras.setBaseImponible3(regCompras.getBaseImponible3());
                }
                if (regCompras.getMontoIgv3() != null) {
                    existingRegCompras.setMontoIgv3(regCompras.getMontoIgv3());
                }
                if (regCompras.getMontoNoGravado() != null) {
                    existingRegCompras.setMontoNoGravado(regCompras.getMontoNoGravado());
                }
                if (regCompras.getMontoIsc() != null) {
                    existingRegCompras.setMontoIsc(regCompras.getMontoIsc());
                }
                if (regCompras.getImpConsBols() != null) {
                    existingRegCompras.setImpConsBols(regCompras.getImpConsBols());
                }
                if (regCompras.getOtrosCargos() != null) {
                    existingRegCompras.setOtrosCargos(regCompras.getOtrosCargos());
                }
                if (regCompras.getImporteTotal() != null) {
                    existingRegCompras.setImporteTotal(regCompras.getImporteTotal());
                }
                if (regCompras.getCodMoneda() != null) {
                    existingRegCompras.setCodMoneda(regCompras.getCodMoneda());
                }
                if (regCompras.getTipCambio() != null) {
                    existingRegCompras.setTipCambio(regCompras.getTipCambio());
                }
                if (regCompras.getFecEmiModif() != null) {
                    existingRegCompras.setFecEmiModif(regCompras.getFecEmiModif());
                }
                if (regCompras.getTipCompModif() != null) {
                    existingRegCompras.setTipCompModif(regCompras.getTipCompModif());
                }
                if (regCompras.getNroSerieCompModif() != null) {
                    existingRegCompras.setNroSerieCompModif(regCompras.getNroSerieCompModif());
                }
                if (regCompras.getCodDuaRef() != null) {
                    existingRegCompras.setCodDuaRef(regCompras.getCodDuaRef());
                }
                if (regCompras.getNroCompModif() != null) {
                    existingRegCompras.setNroCompModif(regCompras.getNroCompModif());
                }
                if (regCompras.getFecEmiDetracc() != null) {
                    existingRegCompras.setFecEmiDetracc(regCompras.getFecEmiDetracc());
                }
                if (regCompras.getNroConstDetracc() != null) {
                    existingRegCompras.setNroConstDetracc(regCompras.getNroConstDetracc());
                }
                if (regCompras.getIndCompSujRetenc() != null) {
                    existingRegCompras.setIndCompSujRetenc(regCompras.getIndCompSujRetenc());
                }
                if (regCompras.getClasBienesyServicios() != null) {
                    existingRegCompras.setClasBienesyServicios(regCompras.getClasBienesyServicios());
                }
                if (regCompras.getIdentContrato() != null) {
                    existingRegCompras.setIdentContrato(regCompras.getIdentContrato());
                }
                if (regCompras.getErrTipUno() != null) {
                    existingRegCompras.setErrTipUno(regCompras.getErrTipUno());
                }
                if (regCompras.getErrTipDos() != null) {
                    existingRegCompras.setErrTipDos(regCompras.getErrTipDos());
                }
                if (regCompras.getErrTipTres() != null) {
                    existingRegCompras.setErrTipTres(regCompras.getErrTipTres());
                }
                if (regCompras.getErrTipCuatro() != null) {
                    existingRegCompras.setErrTipCuatro(regCompras.getErrTipCuatro());
                }
                if (regCompras.getIndCompPagoMedPago() != null) {
                    existingRegCompras.setIndCompPagoMedPago(regCompras.getIndCompPagoMedPago());
                }
                if (regCompras.getEstado() != null) {
                    existingRegCompras.setEstado(regCompras.getEstado());
                }
                if (regCompras.getCampoLibre() != null) {
                    existingRegCompras.setCampoLibre(regCompras.getCampoLibre());
                }
                if (regCompras.getIndDel() != null) {
                    existingRegCompras.setIndDel(regCompras.getIndDel());
                }
                if (regCompras.getFecCrea() != null) {
                    existingRegCompras.setFecCrea(regCompras.getFecCrea());
                }
                if (regCompras.getUsuCrea() != null) {
                    existingRegCompras.setUsuCrea(regCompras.getUsuCrea());
                }
                if (regCompras.getIpCrea() != null) {
                    existingRegCompras.setIpCrea(regCompras.getIpCrea());
                }
                if (regCompras.getFecModif() != null) {
                    existingRegCompras.setFecModif(regCompras.getFecModif());
                }
                if (regCompras.getUsuModif() != null) {
                    existingRegCompras.setUsuModif(regCompras.getUsuModif());
                }
                if (regCompras.getIpModif() != null) {
                    existingRegCompras.setIpModif(regCompras.getIpModif());
                }

                return existingRegCompras;
            })
            .map(regComprasRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, regCompras.getId())
        );
    }

    /**
     * {@code GET  /reg-compras} : get all the regCompras.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of regCompras in body.
     */
    @GetMapping("/reg-compras")
    public List<RegCompras> getAllRegCompras() {
        log.debug("REST request to get all RegCompras");
        return regComprasRepository.findAll();
    }

    /**
     * {@code GET  /reg-compras/:id} : get the "id" regCompras.
     *
     * @param id the id of the regCompras to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the regCompras, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reg-compras/{id}")
    public ResponseEntity<RegCompras> getRegCompras(@PathVariable String id) {
        log.debug("REST request to get RegCompras : {}", id);
        Optional<RegCompras> regCompras = regComprasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(regCompras);
    }

    /**
     * {@code DELETE  /reg-compras/:id} : delete the "id" regCompras.
     *
     * @param id the id of the regCompras to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reg-compras/{id}")
    public ResponseEntity<Void> deleteRegCompras(@PathVariable String id) {
        log.debug("REST request to delete RegCompras : {}", id);
        regComprasRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
