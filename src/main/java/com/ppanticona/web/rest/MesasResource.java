package com.ppanticona.web.rest;

import com.ppanticona.domain.Mesas;
import com.ppanticona.repository.MesasRepository;
import com.ppanticona.service.MesasService;
import com.ppanticona.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ppanticona.domain.Mesas}.
 */
@RestController
@RequestMapping("/api")
public class MesasResource {

    private final Logger log = LoggerFactory.getLogger(MesasResource.class);

    private static final String ENTITY_NAME = "mesas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MesasRepository mesasRepository;
    
    private final MesasService mesasService;

    public MesasResource(MesasRepository mesasRepository,MesasService mesasService) {
        this.mesasRepository = mesasRepository;
        this.mesasService = mesasService;
    }

    /**
     * {@code POST  /mesas} : Create a new mesas.
     *
     * @param mesas the mesas to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mesas, or with status {@code 400 (Bad Request)} if the mesas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mesas")
    public ResponseEntity<Mesas> createMesas(@Valid @RequestBody Mesas mesas) throws URISyntaxException {
        log.debug("REST request to save Mesas : {}", mesas);
        if (mesas.getId() != null) {
            throw new BadRequestAlertException("A new mesas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Mesas result = mesasRepository.save(mesas);
        return ResponseEntity
            .created(new URI("/api/mesas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /mesas/:id} : Updates an existing mesas.
     *
     * @param id the id of the mesas to save.
     * @param mesas the mesas to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mesas,
     * or with status {@code 400 (Bad Request)} if the mesas is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mesas couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mesas/{id}")
    public ResponseEntity<Mesas> updateMesas(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Mesas mesas
    ) throws URISyntaxException {
        log.debug("REST request to update Mesas : {}, {}", id, mesas);
        if (mesas.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mesas.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mesasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Mesas result = mesasRepository.save(mesas);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mesas.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /mesas/:id} : Partial updates given fields of an existing mesas, field will ignore if it is null
     *
     * @param id the id of the mesas to save.
     * @param mesas the mesas to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mesas,
     * or with status {@code 400 (Bad Request)} if the mesas is not valid,
     * or with status {@code 404 (Not Found)} if the mesas is not found,
     * or with status {@code 500 (Internal Server Error)} if the mesas couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/mesas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Mesas> partialUpdateMesas(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Mesas mesas
    ) throws URISyntaxException {
        log.debug("REST request to partial update Mesas partially : {}, {}", id, mesas);
        if (mesas.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mesas.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mesasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Mesas> result = mesasRepository
            .findById(mesas.getId())
            .map(existingMesas -> {
                if (mesas.getSeqMesa() != null) {
                    existingMesas.setSeqMesa(mesas.getSeqMesa());
                }
                if (mesas.getNroMesa() != null) {
                    existingMesas.setNroMesa(mesas.getNroMesa());
                }
                if (mesas.getCodGrupo() != null) {
                    existingMesas.setCodGrupo(mesas.getCodGrupo());
                }
                if (mesas.getCategoria() != null) {
                    existingMesas.setCategoria(mesas.getCategoria());
                }
                if (mesas.getCapacidad() != null) {
                    existingMesas.setCapacidad(mesas.getCapacidad());
                }
                if (mesas.getIndMesaJunta() != null) {
                    existingMesas.setIndMesaJunta(mesas.getIndMesaJunta());
                }
                if (mesas.getEstado() != null) {
                    existingMesas.setEstado(mesas.getEstado());
                }
                if (mesas.getVersion() != null) {
                    existingMesas.setVersion(mesas.getVersion());
                }
                if (mesas.getIndDel() != null) {
                    existingMesas.setIndDel(mesas.getIndDel());
                }
                if (mesas.getFecCrea() != null) {
                    existingMesas.setFecCrea(mesas.getFecCrea());
                }
                if (mesas.getUsuCrea() != null) {
                    existingMesas.setUsuCrea(mesas.getUsuCrea());
                }
                if (mesas.getIpCrea() != null) {
                    existingMesas.setIpCrea(mesas.getIpCrea());
                }
                if (mesas.getFecModif() != null) {
                    existingMesas.setFecModif(mesas.getFecModif());
                }
                if (mesas.getUsuModif() != null) {
                    existingMesas.setUsuModif(mesas.getUsuModif());
                }
                if (mesas.getIpModif() != null) {
                    existingMesas.setIpModif(mesas.getIpModif());
                }

                return existingMesas;
            })
            .map(mesasRepository::save);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mesas.getId()));
    }

    /**
     * {@code GET  /mesas} : get all the mesas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mesas in body.
     */
    @GetMapping("/mesas")
    public List<Mesas> getAllMesas() {
        log.debug("REST request to get all Mesas");
        return mesasRepository.findAll();
    }

    /**
     * {@code GET  /mesas/:id} : get the "id" mesas.
     *
     * @param id the id of the mesas to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mesas, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mesas/{id}")
    public ResponseEntity<Mesas> getMesas(@PathVariable String id) {
        log.debug("REST request to get Mesas : {}", id);
        Optional<Mesas> mesas = mesasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mesas);
    }

    /**
     * {@code DELETE  /mesas/:id} : delete the "id" mesas.
     *
     * @param id the id of the mesas to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mesas/{id}")
    public ResponseEntity<Void> deleteMesas(@PathVariable String id) {
        log.debug("REST request to delete Mesas : {}", id);
        mesasRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }


	@PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMIN')")
	@GetMapping("/listarMesasPorEstado/{cod_estado}")
	public ResponseEntity<String> listarMesasPorEstado(@PathVariable("cod_estado") String codEstado,HttpServletResponse response) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");

		StringBuffer data = new StringBuffer();

		String listaMesas = mesasService.listarMesasPorEstado(codEstado);
		data.append(listaMesas);

		return new ResponseEntity(data.toString(), responseHeaders, HttpStatus.OK);

	}


}
