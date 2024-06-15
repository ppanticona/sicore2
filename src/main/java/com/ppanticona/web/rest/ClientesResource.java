package com.ppanticona.web.rest;

import com.ppanticona.domain.Clientes;
import com.ppanticona.repository.ClientesRepository;
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
 * REST controller for managing {@link com.ppanticona.domain.Clientes}.
 */
@RestController
@RequestMapping("/api")
public class ClientesResource {

    private final Logger log = LoggerFactory.getLogger(ClientesResource.class);

    private static final String ENTITY_NAME = "clientes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientesRepository clientesRepository;

    public ClientesResource(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    /**
     * {@code POST  /clientes} : Create a new clientes.
     *
     * @param clientes the clientes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clientes, or with status {@code 400 (Bad Request)} if the clientes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/clientes")
    public ResponseEntity<Clientes> createClientes(@Valid @RequestBody Clientes clientes) throws URISyntaxException {
        log.debug("REST request to save Clientes : {}", clientes);
        if (clientes.getId() != null) {
            throw new BadRequestAlertException("A new clientes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Clientes result = clientesRepository.save(clientes);
        return ResponseEntity
            .created(new URI("/api/clientes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /clientes/:id} : Updates an existing clientes.
     *
     * @param id the id of the clientes to save.
     * @param clientes the clientes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientes,
     * or with status {@code 400 (Bad Request)} if the clientes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clientes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Clientes> updateClientes(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Clientes clientes
    ) throws URISyntaxException {
        log.debug("REST request to update Clientes : {}, {}", id, clientes);
        if (clientes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientes.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Clientes result = clientesRepository.save(clientes);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientes.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /clientes/:id} : Partial updates given fields of an existing clientes, field will ignore if it is null
     *
     * @param id the id of the clientes to save.
     * @param clientes the clientes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientes,
     * or with status {@code 400 (Bad Request)} if the clientes is not valid,
     * or with status {@code 404 (Not Found)} if the clientes is not found,
     * or with status {@code 500 (Internal Server Error)} if the clientes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/clientes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Clientes> partialUpdateClientes(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Clientes clientes
    ) throws URISyntaxException {
        log.debug("REST request to partial update Clientes partially : {}, {}", id, clientes);
        if (clientes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientes.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Clientes> result = clientesRepository
            .findById(clientes.getId())
            .map(existingClientes -> {
                if (clientes.getTipDoc() != null) {
                    existingClientes.setTipDoc(clientes.getTipDoc());
                }
                if (clientes.getNroDoc() != null) {
                    existingClientes.setNroDoc(clientes.getNroDoc());
                }
                if (clientes.getNombres() != null) {
                    existingClientes.setNombres(clientes.getNombres());
                }
                if (clientes.getApellidos() != null) {
                    existingClientes.setApellidos(clientes.getApellidos());
                }
                if (clientes.getCategoria() != null) {
                    existingClientes.setCategoria(clientes.getCategoria());
                }
                if (clientes.getTel1() != null) {
                    existingClientes.setTel1(clientes.getTel1());
                }
                if (clientes.getTel2() != null) {
                    existingClientes.setTel2(clientes.getTel2());
                }
                if (clientes.getCorreo() != null) {
                    existingClientes.setCorreo(clientes.getCorreo());
                }
                if (clientes.getDireccion() != null) {
                    existingClientes.setDireccion(clientes.getDireccion());
                }
                if (clientes.getRefDirecc() != null) {
                    existingClientes.setRefDirecc(clientes.getRefDirecc());
                }
                if (clientes.getDistrito() != null) {
                    existingClientes.setDistrito(clientes.getDistrito());
                }
                if (clientes.getFecNac() != null) {
                    existingClientes.setFecNac(clientes.getFecNac());
                }
                if (clientes.getUserId() != null) {
                    existingClientes.setUserId(clientes.getUserId());
                }
                if (clientes.getEstado() != null) {
                    existingClientes.setEstado(clientes.getEstado());
                }
                if (clientes.getVersion() != null) {
                    existingClientes.setVersion(clientes.getVersion());
                }
                if (clientes.getIndDel() != null) {
                    existingClientes.setIndDel(clientes.getIndDel());
                }
                if (clientes.getFecCrea() != null) {
                    existingClientes.setFecCrea(clientes.getFecCrea());
                }
                if (clientes.getUsuCrea() != null) {
                    existingClientes.setUsuCrea(clientes.getUsuCrea());
                }
                if (clientes.getIpCrea() != null) {
                    existingClientes.setIpCrea(clientes.getIpCrea());
                }
                if (clientes.getFecModif() != null) {
                    existingClientes.setFecModif(clientes.getFecModif());
                }
                if (clientes.getUsuModif() != null) {
                    existingClientes.setUsuModif(clientes.getUsuModif());
                }
                if (clientes.getIpModif() != null) {
                    existingClientes.setIpModif(clientes.getIpModif());
                }

                return existingClientes;
            })
            .map(clientesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientes.getId())
        );
    }

    /**
     * {@code GET  /clientes} : get all the clientes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientes in body.
     */
    @GetMapping("/clientes")
    public List<Clientes> getAllClientes() {
        log.debug("REST request to get all Clientes");
        return clientesRepository.findAll();
    }

    /**
     * {@code GET  /clientes/:id} : get the "id" clientes.
     *
     * @param id the id of the clientes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Clientes> getClientes(@PathVariable String id) {
        log.debug("REST request to get Clientes : {}", id);
        Optional<Clientes> clientes = clientesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(clientes);
    }

    /**
     * {@code DELETE  /clientes/:id} : delete the "id" clientes.
     *
     * @param id the id of the clientes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> deleteClientes(@PathVariable String id) {
        log.debug("REST request to delete Clientes : {}", id);
        clientesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
