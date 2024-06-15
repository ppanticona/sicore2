package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.DetallePrecuenta;
import com.ppanticona.repository.DetallePrecuentaRepository;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link DetallePrecuentaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DetallePrecuentaResourceIT {

    private static final Integer DEFAULT_CORRELATIVO = 1;
    private static final Integer UPDATED_CORRELATIVO = 2;

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final Boolean DEFAULT_IND_DEL = false;
    private static final Boolean UPDATED_IND_DEL = true;

    private static final ZonedDateTime DEFAULT_FEC_CREA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_CREA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_USU_CREA = "AAAAAAAAAA";
    private static final String UPDATED_USU_CREA = "BBBBBBBBBB";

    private static final String DEFAULT_IP_CREA = "AAAAAAAAAA";
    private static final String UPDATED_IP_CREA = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_MODIF = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_MODIF = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_USU_MODIF = "AAAAAAAAAA";
    private static final String UPDATED_USU_MODIF = "BBBBBBBBBB";

    private static final String DEFAULT_IP_MODIF = "AAAAAAAAAA";
    private static final String UPDATED_IP_MODIF = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/detalle-precuentas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private DetallePrecuentaRepository detallePrecuentaRepository;

    @Autowired
    private MockMvc restDetallePrecuentaMockMvc;

    private DetallePrecuenta detallePrecuenta;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetallePrecuenta createEntity() {
        DetallePrecuenta detallePrecuenta = new DetallePrecuenta()
            .correlativo(DEFAULT_CORRELATIVO)
            .estado(DEFAULT_ESTADO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return detallePrecuenta;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetallePrecuenta createUpdatedEntity() {
        DetallePrecuenta detallePrecuenta = new DetallePrecuenta()
            .correlativo(UPDATED_CORRELATIVO)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return detallePrecuenta;
    }

    @BeforeEach
    public void initTest() {
        detallePrecuentaRepository.deleteAll();
        detallePrecuenta = createEntity();
    }

    @Test
    void createDetallePrecuenta() throws Exception {
        int databaseSizeBeforeCreate = detallePrecuentaRepository.findAll().size();
        // Create the DetallePrecuenta
        restDetallePrecuentaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isCreated());

        // Validate the DetallePrecuenta in the database
        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeCreate + 1);
        DetallePrecuenta testDetallePrecuenta = detallePrecuentaList.get(detallePrecuentaList.size() - 1);
        assertThat(testDetallePrecuenta.getCorrelativo()).isEqualTo(DEFAULT_CORRELATIVO);
        assertThat(testDetallePrecuenta.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testDetallePrecuenta.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testDetallePrecuenta.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testDetallePrecuenta.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testDetallePrecuenta.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testDetallePrecuenta.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testDetallePrecuenta.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testDetallePrecuenta.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testDetallePrecuenta.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createDetallePrecuentaWithExistingId() throws Exception {
        // Create the DetallePrecuenta with an existing ID
        detallePrecuenta.setId("existing_id");

        int databaseSizeBeforeCreate = detallePrecuentaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetallePrecuentaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetallePrecuenta in the database
        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkCorrelativoIsRequired() throws Exception {
        int databaseSizeBeforeTest = detallePrecuentaRepository.findAll().size();
        // set the field null
        detallePrecuenta.setCorrelativo(null);

        // Create the DetallePrecuenta, which fails.

        restDetallePrecuentaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isBadRequest());

        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = detallePrecuentaRepository.findAll().size();
        // set the field null
        detallePrecuenta.setEstado(null);

        // Create the DetallePrecuenta, which fails.

        restDetallePrecuentaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isBadRequest());

        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = detallePrecuentaRepository.findAll().size();
        // set the field null
        detallePrecuenta.setVersion(null);

        // Create the DetallePrecuenta, which fails.

        restDetallePrecuentaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isBadRequest());

        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = detallePrecuentaRepository.findAll().size();
        // set the field null
        detallePrecuenta.setIndDel(null);

        // Create the DetallePrecuenta, which fails.

        restDetallePrecuentaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isBadRequest());

        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = detallePrecuentaRepository.findAll().size();
        // set the field null
        detallePrecuenta.setFecCrea(null);

        // Create the DetallePrecuenta, which fails.

        restDetallePrecuentaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isBadRequest());

        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = detallePrecuentaRepository.findAll().size();
        // set the field null
        detallePrecuenta.setUsuCrea(null);

        // Create the DetallePrecuenta, which fails.

        restDetallePrecuentaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isBadRequest());

        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = detallePrecuentaRepository.findAll().size();
        // set the field null
        detallePrecuenta.setIpCrea(null);

        // Create the DetallePrecuenta, which fails.

        restDetallePrecuentaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isBadRequest());

        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllDetallePrecuentas() throws Exception {
        // Initialize the database
        detallePrecuentaRepository.save(detallePrecuenta);

        // Get all the detallePrecuentaList
        restDetallePrecuentaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detallePrecuenta.getId())))
            .andExpect(jsonPath("$.[*].correlativo").value(hasItem(DEFAULT_CORRELATIVO)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].indDel").value(hasItem(DEFAULT_IND_DEL.booleanValue())))
            .andExpect(jsonPath("$.[*].fecCrea").value(hasItem(sameInstant(DEFAULT_FEC_CREA))))
            .andExpect(jsonPath("$.[*].usuCrea").value(hasItem(DEFAULT_USU_CREA)))
            .andExpect(jsonPath("$.[*].ipCrea").value(hasItem(DEFAULT_IP_CREA)))
            .andExpect(jsonPath("$.[*].fecModif").value(hasItem(sameInstant(DEFAULT_FEC_MODIF))))
            .andExpect(jsonPath("$.[*].usuModif").value(hasItem(DEFAULT_USU_MODIF)))
            .andExpect(jsonPath("$.[*].ipModif").value(hasItem(DEFAULT_IP_MODIF)));
    }

    @Test
    void getDetallePrecuenta() throws Exception {
        // Initialize the database
        detallePrecuentaRepository.save(detallePrecuenta);

        // Get the detallePrecuenta
        restDetallePrecuentaMockMvc
            .perform(get(ENTITY_API_URL_ID, detallePrecuenta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detallePrecuenta.getId()))
            .andExpect(jsonPath("$.correlativo").value(DEFAULT_CORRELATIVO))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.indDel").value(DEFAULT_IND_DEL.booleanValue()))
            .andExpect(jsonPath("$.fecCrea").value(sameInstant(DEFAULT_FEC_CREA)))
            .andExpect(jsonPath("$.usuCrea").value(DEFAULT_USU_CREA))
            .andExpect(jsonPath("$.ipCrea").value(DEFAULT_IP_CREA))
            .andExpect(jsonPath("$.fecModif").value(sameInstant(DEFAULT_FEC_MODIF)))
            .andExpect(jsonPath("$.usuModif").value(DEFAULT_USU_MODIF))
            .andExpect(jsonPath("$.ipModif").value(DEFAULT_IP_MODIF));
    }

    @Test
    void getNonExistingDetallePrecuenta() throws Exception {
        // Get the detallePrecuenta
        restDetallePrecuentaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingDetallePrecuenta() throws Exception {
        // Initialize the database
        detallePrecuentaRepository.save(detallePrecuenta);

        int databaseSizeBeforeUpdate = detallePrecuentaRepository.findAll().size();

        // Update the detallePrecuenta
        DetallePrecuenta updatedDetallePrecuenta = detallePrecuentaRepository.findById(detallePrecuenta.getId()).get();
        updatedDetallePrecuenta
            .correlativo(UPDATED_CORRELATIVO)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restDetallePrecuentaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDetallePrecuenta.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDetallePrecuenta))
            )
            .andExpect(status().isOk());

        // Validate the DetallePrecuenta in the database
        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeUpdate);
        DetallePrecuenta testDetallePrecuenta = detallePrecuentaList.get(detallePrecuentaList.size() - 1);
        assertThat(testDetallePrecuenta.getCorrelativo()).isEqualTo(UPDATED_CORRELATIVO);
        assertThat(testDetallePrecuenta.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testDetallePrecuenta.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testDetallePrecuenta.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testDetallePrecuenta.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testDetallePrecuenta.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testDetallePrecuenta.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testDetallePrecuenta.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testDetallePrecuenta.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testDetallePrecuenta.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingDetallePrecuenta() throws Exception {
        int databaseSizeBeforeUpdate = detallePrecuentaRepository.findAll().size();
        detallePrecuenta.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetallePrecuentaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, detallePrecuenta.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetallePrecuenta in the database
        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDetallePrecuenta() throws Exception {
        int databaseSizeBeforeUpdate = detallePrecuentaRepository.findAll().size();
        detallePrecuenta.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetallePrecuentaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetallePrecuenta in the database
        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDetallePrecuenta() throws Exception {
        int databaseSizeBeforeUpdate = detallePrecuentaRepository.findAll().size();
        detallePrecuenta.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetallePrecuentaMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetallePrecuenta in the database
        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDetallePrecuentaWithPatch() throws Exception {
        // Initialize the database
        detallePrecuentaRepository.save(detallePrecuenta);

        int databaseSizeBeforeUpdate = detallePrecuentaRepository.findAll().size();

        // Update the detallePrecuenta using partial update
        DetallePrecuenta partialUpdatedDetallePrecuenta = new DetallePrecuenta();
        partialUpdatedDetallePrecuenta.setId(detallePrecuenta.getId());

        partialUpdatedDetallePrecuenta
            .correlativo(UPDATED_CORRELATIVO)
            .estado(UPDATED_ESTADO)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .ipModif(UPDATED_IP_MODIF);

        restDetallePrecuentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetallePrecuenta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetallePrecuenta))
            )
            .andExpect(status().isOk());

        // Validate the DetallePrecuenta in the database
        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeUpdate);
        DetallePrecuenta testDetallePrecuenta = detallePrecuentaList.get(detallePrecuentaList.size() - 1);
        assertThat(testDetallePrecuenta.getCorrelativo()).isEqualTo(UPDATED_CORRELATIVO);
        assertThat(testDetallePrecuenta.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testDetallePrecuenta.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testDetallePrecuenta.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testDetallePrecuenta.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testDetallePrecuenta.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testDetallePrecuenta.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testDetallePrecuenta.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testDetallePrecuenta.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testDetallePrecuenta.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void fullUpdateDetallePrecuentaWithPatch() throws Exception {
        // Initialize the database
        detallePrecuentaRepository.save(detallePrecuenta);

        int databaseSizeBeforeUpdate = detallePrecuentaRepository.findAll().size();

        // Update the detallePrecuenta using partial update
        DetallePrecuenta partialUpdatedDetallePrecuenta = new DetallePrecuenta();
        partialUpdatedDetallePrecuenta.setId(detallePrecuenta.getId());

        partialUpdatedDetallePrecuenta
            .correlativo(UPDATED_CORRELATIVO)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restDetallePrecuentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetallePrecuenta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetallePrecuenta))
            )
            .andExpect(status().isOk());

        // Validate the DetallePrecuenta in the database
        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeUpdate);
        DetallePrecuenta testDetallePrecuenta = detallePrecuentaList.get(detallePrecuentaList.size() - 1);
        assertThat(testDetallePrecuenta.getCorrelativo()).isEqualTo(UPDATED_CORRELATIVO);
        assertThat(testDetallePrecuenta.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testDetallePrecuenta.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testDetallePrecuenta.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testDetallePrecuenta.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testDetallePrecuenta.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testDetallePrecuenta.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testDetallePrecuenta.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testDetallePrecuenta.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testDetallePrecuenta.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingDetallePrecuenta() throws Exception {
        int databaseSizeBeforeUpdate = detallePrecuentaRepository.findAll().size();
        detallePrecuenta.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetallePrecuentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, detallePrecuenta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetallePrecuenta in the database
        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDetallePrecuenta() throws Exception {
        int databaseSizeBeforeUpdate = detallePrecuentaRepository.findAll().size();
        detallePrecuenta.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetallePrecuentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetallePrecuenta in the database
        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDetallePrecuenta() throws Exception {
        int databaseSizeBeforeUpdate = detallePrecuentaRepository.findAll().size();
        detallePrecuenta.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetallePrecuentaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detallePrecuenta))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetallePrecuenta in the database
        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDetallePrecuenta() throws Exception {
        // Initialize the database
        detallePrecuentaRepository.save(detallePrecuenta);

        int databaseSizeBeforeDelete = detallePrecuentaRepository.findAll().size();

        // Delete the detallePrecuenta
        restDetallePrecuentaMockMvc
            .perform(delete(ENTITY_API_URL_ID, detallePrecuenta.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetallePrecuenta> detallePrecuentaList = detallePrecuentaRepository.findAll();
        assertThat(detallePrecuentaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
