package com.ppanticona.web.rest;

import com.ppanticona.domain.Empleados;
import com.ppanticona.repository.EmpleadosRepository;
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
 * REST controller for managing {@link com.ppanticona.domain.Empleados}.
 */
@RestController
@RequestMapping("/api")
public class EmpleadosResource {

    private final Logger log = LoggerFactory.getLogger(EmpleadosResource.class);

    private static final String ENTITY_NAME = "empleados";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmpleadosRepository empleadosRepository;

    public EmpleadosResource(EmpleadosRepository empleadosRepository) {
        this.empleadosRepository = empleadosRepository;
    }

    /**
     * {@code POST  /empleados} : Create a new empleados.
     *
     * @param empleados the empleados to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new empleados, or with status {@code 400 (Bad Request)} if the empleados has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/empleados")
    public ResponseEntity<Empleados> createEmpleados(@Valid @RequestBody Empleados empleados) throws URISyntaxException {
        log.debug("REST request to save Empleados : {}", empleados);
        if (empleados.getId() != null) {
            throw new BadRequestAlertException("A new empleados cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Empleados result = empleadosRepository.save(empleados);
        return ResponseEntity
            .created(new URI("/api/empleados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /empleados/:id} : Updates an existing empleados.
     *
     * @param id the id of the empleados to save.
     * @param empleados the empleados to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated empleados,
     * or with status {@code 400 (Bad Request)} if the empleados is not valid,
     * or with status {@code 500 (Internal Server Error)} if the empleados couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleados> updateEmpleados(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Empleados empleados
    ) throws URISyntaxException {
        log.debug("REST request to update Empleados : {}, {}", id, empleados);
        if (empleados.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, empleados.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empleadosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Empleados result = empleadosRepository.save(empleados);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, empleados.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /empleados/:id} : Partial updates given fields of an existing empleados, field will ignore if it is null
     *
     * @param id the id of the empleados to save.
     * @param empleados the empleados to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated empleados,
     * or with status {@code 400 (Bad Request)} if the empleados is not valid,
     * or with status {@code 404 (Not Found)} if the empleados is not found,
     * or with status {@code 500 (Internal Server Error)} if the empleados couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/empleados/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Empleados> partialUpdateEmpleados(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Empleados empleados
    ) throws URISyntaxException {
        log.debug("REST request to partial update Empleados partially : {}, {}", id, empleados);
        if (empleados.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, empleados.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empleadosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Empleados> result = empleadosRepository
            .findById(empleados.getId())
            .map(existingEmpleados -> {
                if (empleados.getTipDoc() != null) {
                    existingEmpleados.setTipDoc(empleados.getTipDoc());
                }
                if (empleados.getNroDoc() != null) {
                    existingEmpleados.setNroDoc(empleados.getNroDoc());
                }
                if (empleados.getNombres() != null) {
                    existingEmpleados.setNombres(empleados.getNombres());
                }
                if (empleados.getApellidos() != null) {
                    existingEmpleados.setApellidos(empleados.getApellidos());
                }
                if (empleados.getCategoria() != null) {
                    existingEmpleados.setCategoria(empleados.getCategoria());
                }
                if (empleados.getTel1() != null) {
                    existingEmpleados.setTel1(empleados.getTel1());
                }
                if (empleados.getTel2() != null) {
                    existingEmpleados.setTel2(empleados.getTel2());
                }
                if (empleados.getCorreo() != null) {
                    existingEmpleados.setCorreo(empleados.getCorreo());
                }
                if (empleados.getDireccion() != null) {
                    existingEmpleados.setDireccion(empleados.getDireccion());
                }
                if (empleados.getRefDirecc() != null) {
                    existingEmpleados.setRefDirecc(empleados.getRefDirecc());
                }
                if (empleados.getDistrito() != null) {
                    existingEmpleados.setDistrito(empleados.getDistrito());
                }
                if (empleados.getFecIngreso() != null) {
                    existingEmpleados.setFecIngreso(empleados.getFecIngreso());
                }
                if (empleados.getFecNac() != null) {
                    existingEmpleados.setFecNac(empleados.getFecNac());
                }
                if (empleados.getImagen() != null) {
                    existingEmpleados.setImagen(empleados.getImagen());
                }
                if (empleados.getUserId() != null) {
                    existingEmpleados.setUserId(empleados.getUserId());
                }
                if (empleados.getEstado() != null) {
                    existingEmpleados.setEstado(empleados.getEstado());
                }
                if (empleados.getVersion() != null) {
                    existingEmpleados.setVersion(empleados.getVersion());
                }
                if (empleados.getIndDel() != null) {
                    existingEmpleados.setIndDel(empleados.getIndDel());
                }
                if (empleados.getFecCrea() != null) {
                    existingEmpleados.setFecCrea(empleados.getFecCrea());
                }
                if (empleados.getUsuCrea() != null) {
                    existingEmpleados.setUsuCrea(empleados.getUsuCrea());
                }
                if (empleados.getIpCrea() != null) {
                    existingEmpleados.setIpCrea(empleados.getIpCrea());
                }
                if (empleados.getFecModif() != null) {
                    existingEmpleados.setFecModif(empleados.getFecModif());
                }
                if (empleados.getUsuModif() != null) {
                    existingEmpleados.setUsuModif(empleados.getUsuModif());
                }
                if (empleados.getIpModif() != null) {
                    existingEmpleados.setIpModif(empleados.getIpModif());
                }

                return existingEmpleados;
            })
            .map(empleadosRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, empleados.getId())
        );
    }

    /**
     * {@code GET  /empleados} : get all the empleados.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of empleados in body.
     */
    @GetMapping("/empleados")
    public List<Empleados> getAllEmpleados() {
        log.debug("REST request to get all Empleados");
        return empleadosRepository.findAll();
    }

    /**
     * {@code GET  /empleados/:id} : get the "id" empleados.
     *
     * @param id the id of the empleados to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the empleados, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleados> getEmpleados(@PathVariable String id) {
        log.debug("REST request to get Empleados : {}", id);
        Optional<Empleados> empleados = empleadosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(empleados);
    }

    /**
     * {@code DELETE  /empleados/:id} : delete the "id" empleados.
     *
     * @param id the id of the empleados to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Void> deleteEmpleados(@PathVariable String id) {
        log.debug("REST request to delete Empleados : {}", id);
        empleadosRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
