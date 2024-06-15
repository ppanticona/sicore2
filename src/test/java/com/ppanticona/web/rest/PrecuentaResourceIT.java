package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.Precuenta;
import com.ppanticona.repository.PrecuentaRepository;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link PrecuentaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PrecuentaResourceIT {

    private static final Integer DEFAULT_NUM_PRECUENTA = 1;
    private static final Integer UPDATED_NUM_PRECUENTA = 2;

    private static final String DEFAULT_OBSERVACION = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACION = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/precuentas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private PrecuentaRepository precuentaRepository;

    @Autowired
    private MockMvc restPrecuentaMockMvc;

    private Precuenta precuenta;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Precuenta createEntity() {
        Precuenta precuenta = new Precuenta()
            .numPrecuenta(DEFAULT_NUM_PRECUENTA)
            .observacion(DEFAULT_OBSERVACION)
            .estado(DEFAULT_ESTADO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return precuenta;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Precuenta createUpdatedEntity() {
        Precuenta precuenta = new Precuenta()
            .numPrecuenta(UPDATED_NUM_PRECUENTA)
            .observacion(UPDATED_OBSERVACION)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return precuenta;
    }

    @BeforeEach
    public void initTest() {
        precuentaRepository.deleteAll();
        precuenta = createEntity();
    }

    @Test
    void createPrecuenta() throws Exception {
        int databaseSizeBeforeCreate = precuentaRepository.findAll().size();
        // Create the Precuenta
        restPrecuentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(precuenta)))
            .andExpect(status().isCreated());

        // Validate the Precuenta in the database
        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeCreate + 1);
        Precuenta testPrecuenta = precuentaList.get(precuentaList.size() - 1);
        assertThat(testPrecuenta.getNumPrecuenta()).isEqualTo(DEFAULT_NUM_PRECUENTA);
        assertThat(testPrecuenta.getObservacion()).isEqualTo(DEFAULT_OBSERVACION);
        assertThat(testPrecuenta.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testPrecuenta.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testPrecuenta.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testPrecuenta.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testPrecuenta.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testPrecuenta.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testPrecuenta.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testPrecuenta.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testPrecuenta.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createPrecuentaWithExistingId() throws Exception {
        // Create the Precuenta with an existing ID
        precuenta.setId("existing_id");

        int databaseSizeBeforeCreate = precuentaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrecuentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(precuenta)))
            .andExpect(status().isBadRequest());

        // Validate the Precuenta in the database
        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNumPrecuentaIsRequired() throws Exception {
        int databaseSizeBeforeTest = precuentaRepository.findAll().size();
        // set the field null
        precuenta.setNumPrecuenta(null);

        // Create the Precuenta, which fails.

        restPrecuentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(precuenta)))
            .andExpect(status().isBadRequest());

        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = precuentaRepository.findAll().size();
        // set the field null
        precuenta.setEstado(null);

        // Create the Precuenta, which fails.

        restPrecuentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(precuenta)))
            .andExpect(status().isBadRequest());

        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = precuentaRepository.findAll().size();
        // set the field null
        precuenta.setVersion(null);

        // Create the Precuenta, which fails.

        restPrecuentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(precuenta)))
            .andExpect(status().isBadRequest());

        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = precuentaRepository.findAll().size();
        // set the field null
        precuenta.setIndDel(null);

        // Create the Precuenta, which fails.

        restPrecuentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(precuenta)))
            .andExpect(status().isBadRequest());

        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = precuentaRepository.findAll().size();
        // set the field null
        precuenta.setFecCrea(null);

        // Create the Precuenta, which fails.

        restPrecuentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(precuenta)))
            .andExpect(status().isBadRequest());

        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = precuentaRepository.findAll().size();
        // set the field null
        precuenta.setUsuCrea(null);

        // Create the Precuenta, which fails.

        restPrecuentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(precuenta)))
            .andExpect(status().isBadRequest());

        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = precuentaRepository.findAll().size();
        // set the field null
        precuenta.setIpCrea(null);

        // Create the Precuenta, which fails.

        restPrecuentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(precuenta)))
            .andExpect(status().isBadRequest());

        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllPrecuentas() throws Exception {
        // Initialize the database
        precuentaRepository.save(precuenta);

        // Get all the precuentaList
        restPrecuentaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(precuenta.getId())))
            .andExpect(jsonPath("$.[*].numPrecuenta").value(hasItem(DEFAULT_NUM_PRECUENTA)))
            .andExpect(jsonPath("$.[*].observacion").value(hasItem(DEFAULT_OBSERVACION.toString())))
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
    void getPrecuenta() throws Exception {
        // Initialize the database
        precuentaRepository.save(precuenta);

        // Get the precuenta
        restPrecuentaMockMvc
            .perform(get(ENTITY_API_URL_ID, precuenta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(precuenta.getId()))
            .andExpect(jsonPath("$.numPrecuenta").value(DEFAULT_NUM_PRECUENTA))
            .andExpect(jsonPath("$.observacion").value(DEFAULT_OBSERVACION.toString()))
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
    void getNonExistingPrecuenta() throws Exception {
        // Get the precuenta
        restPrecuentaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingPrecuenta() throws Exception {
        // Initialize the database
        precuentaRepository.save(precuenta);

        int databaseSizeBeforeUpdate = precuentaRepository.findAll().size();

        // Update the precuenta
        Precuenta updatedPrecuenta = precuentaRepository.findById(precuenta.getId()).get();
        updatedPrecuenta
            .numPrecuenta(UPDATED_NUM_PRECUENTA)
            .observacion(UPDATED_OBSERVACION)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restPrecuentaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPrecuenta.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPrecuenta))
            )
            .andExpect(status().isOk());

        // Validate the Precuenta in the database
        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeUpdate);
        Precuenta testPrecuenta = precuentaList.get(precuentaList.size() - 1);
        assertThat(testPrecuenta.getNumPrecuenta()).isEqualTo(UPDATED_NUM_PRECUENTA);
        assertThat(testPrecuenta.getObservacion()).isEqualTo(UPDATED_OBSERVACION);
        assertThat(testPrecuenta.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testPrecuenta.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testPrecuenta.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testPrecuenta.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testPrecuenta.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testPrecuenta.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testPrecuenta.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testPrecuenta.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testPrecuenta.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingPrecuenta() throws Exception {
        int databaseSizeBeforeUpdate = precuentaRepository.findAll().size();
        precuenta.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrecuentaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, precuenta.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(precuenta))
            )
            .andExpect(status().isBadRequest());

        // Validate the Precuenta in the database
        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPrecuenta() throws Exception {
        int databaseSizeBeforeUpdate = precuentaRepository.findAll().size();
        precuenta.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrecuentaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(precuenta))
            )
            .andExpect(status().isBadRequest());

        // Validate the Precuenta in the database
        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPrecuenta() throws Exception {
        int databaseSizeBeforeUpdate = precuentaRepository.findAll().size();
        precuenta.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrecuentaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(precuenta)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Precuenta in the database
        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePrecuentaWithPatch() throws Exception {
        // Initialize the database
        precuentaRepository.save(precuenta);

        int databaseSizeBeforeUpdate = precuentaRepository.findAll().size();

        // Update the precuenta using partial update
        Precuenta partialUpdatedPrecuenta = new Precuenta();
        partialUpdatedPrecuenta.setId(precuenta.getId());

        partialUpdatedPrecuenta.observacion(UPDATED_OBSERVACION).indDel(UPDATED_IND_DEL).usuCrea(UPDATED_USU_CREA).ipCrea(UPDATED_IP_CREA);

        restPrecuentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPrecuenta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPrecuenta))
            )
            .andExpect(status().isOk());

        // Validate the Precuenta in the database
        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeUpdate);
        Precuenta testPrecuenta = precuentaList.get(precuentaList.size() - 1);
        assertThat(testPrecuenta.getNumPrecuenta()).isEqualTo(DEFAULT_NUM_PRECUENTA);
        assertThat(testPrecuenta.getObservacion()).isEqualTo(UPDATED_OBSERVACION);
        assertThat(testPrecuenta.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testPrecuenta.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testPrecuenta.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testPrecuenta.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testPrecuenta.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testPrecuenta.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testPrecuenta.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testPrecuenta.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testPrecuenta.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void fullUpdatePrecuentaWithPatch() throws Exception {
        // Initialize the database
        precuentaRepository.save(precuenta);

        int databaseSizeBeforeUpdate = precuentaRepository.findAll().size();

        // Update the precuenta using partial update
        Precuenta partialUpdatedPrecuenta = new Precuenta();
        partialUpdatedPrecuenta.setId(precuenta.getId());

        partialUpdatedPrecuenta
            .numPrecuenta(UPDATED_NUM_PRECUENTA)
            .observacion(UPDATED_OBSERVACION)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restPrecuentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPrecuenta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPrecuenta))
            )
            .andExpect(status().isOk());

        // Validate the Precuenta in the database
        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeUpdate);
        Precuenta testPrecuenta = precuentaList.get(precuentaList.size() - 1);
        assertThat(testPrecuenta.getNumPrecuenta()).isEqualTo(UPDATED_NUM_PRECUENTA);
        assertThat(testPrecuenta.getObservacion()).isEqualTo(UPDATED_OBSERVACION);
        assertThat(testPrecuenta.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testPrecuenta.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testPrecuenta.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testPrecuenta.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testPrecuenta.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testPrecuenta.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testPrecuenta.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testPrecuenta.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testPrecuenta.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingPrecuenta() throws Exception {
        int databaseSizeBeforeUpdate = precuentaRepository.findAll().size();
        precuenta.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrecuentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, precuenta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(precuenta))
            )
            .andExpect(status().isBadRequest());

        // Validate the Precuenta in the database
        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPrecuenta() throws Exception {
        int databaseSizeBeforeUpdate = precuentaRepository.findAll().size();
        precuenta.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrecuentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(precuenta))
            )
            .andExpect(status().isBadRequest());

        // Validate the Precuenta in the database
        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPrecuenta() throws Exception {
        int databaseSizeBeforeUpdate = precuentaRepository.findAll().size();
        precuenta.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPrecuentaMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(precuenta))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Precuenta in the database
        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePrecuenta() throws Exception {
        // Initialize the database
        precuentaRepository.save(precuenta);

        int databaseSizeBeforeDelete = precuentaRepository.findAll().size();

        // Delete the precuenta
        restPrecuentaMockMvc
            .perform(delete(ENTITY_API_URL_ID, precuenta.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Precuenta> precuentaList = precuentaRepository.findAll();
        assertThat(precuentaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
