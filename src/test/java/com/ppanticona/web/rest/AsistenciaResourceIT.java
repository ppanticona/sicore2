package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.Asistencia;
import com.ppanticona.repository.AsistenciaRepository;
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
 * Integration tests for the {@link AsistenciaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AsistenciaResourceIT {

    private static final String DEFAULT_TIP_ASISTENTE = "AAAAAAAAAA";
    private static final String UPDATED_TIP_ASISTENTE = "BBBBBBBBBB";

    private static final String DEFAULT_RESULTADO = "AAAAAAAAAA";
    private static final String UPDATED_RESULTADO = "BBBBBBBBBB";

    private static final Integer DEFAULT_ANO_ASISTENCIA = 1;
    private static final Integer UPDATED_ANO_ASISTENCIA = 2;

    private static final Integer DEFAULT_MES_ASISTENCIA = 1;
    private static final Integer UPDATED_MES_ASISTENCIA = 2;

    private static final Integer DEFAULT_DIA_ASISTENCIA = 1;
    private static final Integer UPDATED_DIA_ASISTENCIA = 2;

    private static final String DEFAULT_OBSERVACION = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACION = "BBBBBBBBBB";

    private static final String DEFAULT_COMENTARIOS = "AAAAAAAAAA";
    private static final String UPDATED_COMENTARIOS = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/asistencias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private MockMvc restAsistenciaMockMvc;

    private Asistencia asistencia;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Asistencia createEntity() {
        Asistencia asistencia = new Asistencia()
            .tipAsistente(DEFAULT_TIP_ASISTENTE)
            .resultado(DEFAULT_RESULTADO)
            .anoAsistencia(DEFAULT_ANO_ASISTENCIA)
            .mesAsistencia(DEFAULT_MES_ASISTENCIA)
            .diaAsistencia(DEFAULT_DIA_ASISTENCIA)
            .observacion(DEFAULT_OBSERVACION)
            .comentarios(DEFAULT_COMENTARIOS)
            .estado(DEFAULT_ESTADO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return asistencia;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Asistencia createUpdatedEntity() {
        Asistencia asistencia = new Asistencia()
            .tipAsistente(UPDATED_TIP_ASISTENTE)
            .resultado(UPDATED_RESULTADO)
            .anoAsistencia(UPDATED_ANO_ASISTENCIA)
            .mesAsistencia(UPDATED_MES_ASISTENCIA)
            .diaAsistencia(UPDATED_DIA_ASISTENCIA)
            .observacion(UPDATED_OBSERVACION)
            .comentarios(UPDATED_COMENTARIOS)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return asistencia;
    }

    @BeforeEach
    public void initTest() {
        asistenciaRepository.deleteAll();
        asistencia = createEntity();
    }

    @Test
    void createAsistencia() throws Exception {
        int databaseSizeBeforeCreate = asistenciaRepository.findAll().size();
        // Create the Asistencia
        restAsistenciaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asistencia)))
            .andExpect(status().isCreated());

        // Validate the Asistencia in the database
        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeCreate + 1);
        Asistencia testAsistencia = asistenciaList.get(asistenciaList.size() - 1);
        assertThat(testAsistencia.getTipAsistente()).isEqualTo(DEFAULT_TIP_ASISTENTE);
        assertThat(testAsistencia.getResultado()).isEqualTo(DEFAULT_RESULTADO);
        assertThat(testAsistencia.getAnoAsistencia()).isEqualTo(DEFAULT_ANO_ASISTENCIA);
        assertThat(testAsistencia.getMesAsistencia()).isEqualTo(DEFAULT_MES_ASISTENCIA);
        assertThat(testAsistencia.getDiaAsistencia()).isEqualTo(DEFAULT_DIA_ASISTENCIA);
        assertThat(testAsistencia.getObservacion()).isEqualTo(DEFAULT_OBSERVACION);
        assertThat(testAsistencia.getComentarios()).isEqualTo(DEFAULT_COMENTARIOS);
        assertThat(testAsistencia.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testAsistencia.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testAsistencia.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testAsistencia.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testAsistencia.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testAsistencia.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testAsistencia.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testAsistencia.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testAsistencia.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createAsistenciaWithExistingId() throws Exception {
        // Create the Asistencia with an existing ID
        asistencia.setId("existing_id");

        int databaseSizeBeforeCreate = asistenciaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAsistenciaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asistencia)))
            .andExpect(status().isBadRequest());

        // Validate the Asistencia in the database
        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTipAsistenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = asistenciaRepository.findAll().size();
        // set the field null
        asistencia.setTipAsistente(null);

        // Create the Asistencia, which fails.

        restAsistenciaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asistencia)))
            .andExpect(status().isBadRequest());

        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = asistenciaRepository.findAll().size();
        // set the field null
        asistencia.setEstado(null);

        // Create the Asistencia, which fails.

        restAsistenciaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asistencia)))
            .andExpect(status().isBadRequest());

        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = asistenciaRepository.findAll().size();
        // set the field null
        asistencia.setVersion(null);

        // Create the Asistencia, which fails.

        restAsistenciaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asistencia)))
            .andExpect(status().isBadRequest());

        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = asistenciaRepository.findAll().size();
        // set the field null
        asistencia.setIndDel(null);

        // Create the Asistencia, which fails.

        restAsistenciaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asistencia)))
            .andExpect(status().isBadRequest());

        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = asistenciaRepository.findAll().size();
        // set the field null
        asistencia.setFecCrea(null);

        // Create the Asistencia, which fails.

        restAsistenciaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asistencia)))
            .andExpect(status().isBadRequest());

        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = asistenciaRepository.findAll().size();
        // set the field null
        asistencia.setUsuCrea(null);

        // Create the Asistencia, which fails.

        restAsistenciaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asistencia)))
            .andExpect(status().isBadRequest());

        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = asistenciaRepository.findAll().size();
        // set the field null
        asistencia.setIpCrea(null);

        // Create the Asistencia, which fails.

        restAsistenciaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asistencia)))
            .andExpect(status().isBadRequest());

        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllAsistencias() throws Exception {
        // Initialize the database
        asistenciaRepository.save(asistencia);

        // Get all the asistenciaList
        restAsistenciaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(asistencia.getId())))
            .andExpect(jsonPath("$.[*].tipAsistente").value(hasItem(DEFAULT_TIP_ASISTENTE)))
            .andExpect(jsonPath("$.[*].resultado").value(hasItem(DEFAULT_RESULTADO)))
            .andExpect(jsonPath("$.[*].anoAsistencia").value(hasItem(DEFAULT_ANO_ASISTENCIA)))
            .andExpect(jsonPath("$.[*].mesAsistencia").value(hasItem(DEFAULT_MES_ASISTENCIA)))
            .andExpect(jsonPath("$.[*].diaAsistencia").value(hasItem(DEFAULT_DIA_ASISTENCIA)))
            .andExpect(jsonPath("$.[*].observacion").value(hasItem(DEFAULT_OBSERVACION)))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS.toString())))
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
    void getAsistencia() throws Exception {
        // Initialize the database
        asistenciaRepository.save(asistencia);

        // Get the asistencia
        restAsistenciaMockMvc
            .perform(get(ENTITY_API_URL_ID, asistencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(asistencia.getId()))
            .andExpect(jsonPath("$.tipAsistente").value(DEFAULT_TIP_ASISTENTE))
            .andExpect(jsonPath("$.resultado").value(DEFAULT_RESULTADO))
            .andExpect(jsonPath("$.anoAsistencia").value(DEFAULT_ANO_ASISTENCIA))
            .andExpect(jsonPath("$.mesAsistencia").value(DEFAULT_MES_ASISTENCIA))
            .andExpect(jsonPath("$.diaAsistencia").value(DEFAULT_DIA_ASISTENCIA))
            .andExpect(jsonPath("$.observacion").value(DEFAULT_OBSERVACION))
            .andExpect(jsonPath("$.comentarios").value(DEFAULT_COMENTARIOS.toString()))
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
    void getNonExistingAsistencia() throws Exception {
        // Get the asistencia
        restAsistenciaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingAsistencia() throws Exception {
        // Initialize the database
        asistenciaRepository.save(asistencia);

        int databaseSizeBeforeUpdate = asistenciaRepository.findAll().size();

        // Update the asistencia
        Asistencia updatedAsistencia = asistenciaRepository.findById(asistencia.getId()).get();
        updatedAsistencia
            .tipAsistente(UPDATED_TIP_ASISTENTE)
            .resultado(UPDATED_RESULTADO)
            .anoAsistencia(UPDATED_ANO_ASISTENCIA)
            .mesAsistencia(UPDATED_MES_ASISTENCIA)
            .diaAsistencia(UPDATED_DIA_ASISTENCIA)
            .observacion(UPDATED_OBSERVACION)
            .comentarios(UPDATED_COMENTARIOS)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restAsistenciaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAsistencia.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAsistencia))
            )
            .andExpect(status().isOk());

        // Validate the Asistencia in the database
        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeUpdate);
        Asistencia testAsistencia = asistenciaList.get(asistenciaList.size() - 1);
        assertThat(testAsistencia.getTipAsistente()).isEqualTo(UPDATED_TIP_ASISTENTE);
        assertThat(testAsistencia.getResultado()).isEqualTo(UPDATED_RESULTADO);
        assertThat(testAsistencia.getAnoAsistencia()).isEqualTo(UPDATED_ANO_ASISTENCIA);
        assertThat(testAsistencia.getMesAsistencia()).isEqualTo(UPDATED_MES_ASISTENCIA);
        assertThat(testAsistencia.getDiaAsistencia()).isEqualTo(UPDATED_DIA_ASISTENCIA);
        assertThat(testAsistencia.getObservacion()).isEqualTo(UPDATED_OBSERVACION);
        assertThat(testAsistencia.getComentarios()).isEqualTo(UPDATED_COMENTARIOS);
        assertThat(testAsistencia.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testAsistencia.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testAsistencia.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testAsistencia.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testAsistencia.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testAsistencia.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testAsistencia.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testAsistencia.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testAsistencia.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingAsistencia() throws Exception {
        int databaseSizeBeforeUpdate = asistenciaRepository.findAll().size();
        asistencia.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAsistenciaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, asistencia.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(asistencia))
            )
            .andExpect(status().isBadRequest());

        // Validate the Asistencia in the database
        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAsistencia() throws Exception {
        int databaseSizeBeforeUpdate = asistenciaRepository.findAll().size();
        asistencia.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAsistenciaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(asistencia))
            )
            .andExpect(status().isBadRequest());

        // Validate the Asistencia in the database
        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAsistencia() throws Exception {
        int databaseSizeBeforeUpdate = asistenciaRepository.findAll().size();
        asistencia.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAsistenciaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asistencia)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Asistencia in the database
        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAsistenciaWithPatch() throws Exception {
        // Initialize the database
        asistenciaRepository.save(asistencia);

        int databaseSizeBeforeUpdate = asistenciaRepository.findAll().size();

        // Update the asistencia using partial update
        Asistencia partialUpdatedAsistencia = new Asistencia();
        partialUpdatedAsistencia.setId(asistencia.getId());

        partialUpdatedAsistencia
            .diaAsistencia(UPDATED_DIA_ASISTENCIA)
            .observacion(UPDATED_OBSERVACION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA);

        restAsistenciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAsistencia.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAsistencia))
            )
            .andExpect(status().isOk());

        // Validate the Asistencia in the database
        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeUpdate);
        Asistencia testAsistencia = asistenciaList.get(asistenciaList.size() - 1);
        assertThat(testAsistencia.getTipAsistente()).isEqualTo(DEFAULT_TIP_ASISTENTE);
        assertThat(testAsistencia.getResultado()).isEqualTo(DEFAULT_RESULTADO);
        assertThat(testAsistencia.getAnoAsistencia()).isEqualTo(DEFAULT_ANO_ASISTENCIA);
        assertThat(testAsistencia.getMesAsistencia()).isEqualTo(DEFAULT_MES_ASISTENCIA);
        assertThat(testAsistencia.getDiaAsistencia()).isEqualTo(UPDATED_DIA_ASISTENCIA);
        assertThat(testAsistencia.getObservacion()).isEqualTo(UPDATED_OBSERVACION);
        assertThat(testAsistencia.getComentarios()).isEqualTo(DEFAULT_COMENTARIOS);
        assertThat(testAsistencia.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testAsistencia.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testAsistencia.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testAsistencia.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testAsistencia.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testAsistencia.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testAsistencia.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testAsistencia.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testAsistencia.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void fullUpdateAsistenciaWithPatch() throws Exception {
        // Initialize the database
        asistenciaRepository.save(asistencia);

        int databaseSizeBeforeUpdate = asistenciaRepository.findAll().size();

        // Update the asistencia using partial update
        Asistencia partialUpdatedAsistencia = new Asistencia();
        partialUpdatedAsistencia.setId(asistencia.getId());

        partialUpdatedAsistencia
            .tipAsistente(UPDATED_TIP_ASISTENTE)
            .resultado(UPDATED_RESULTADO)
            .anoAsistencia(UPDATED_ANO_ASISTENCIA)
            .mesAsistencia(UPDATED_MES_ASISTENCIA)
            .diaAsistencia(UPDATED_DIA_ASISTENCIA)
            .observacion(UPDATED_OBSERVACION)
            .comentarios(UPDATED_COMENTARIOS)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restAsistenciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAsistencia.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAsistencia))
            )
            .andExpect(status().isOk());

        // Validate the Asistencia in the database
        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeUpdate);
        Asistencia testAsistencia = asistenciaList.get(asistenciaList.size() - 1);
        assertThat(testAsistencia.getTipAsistente()).isEqualTo(UPDATED_TIP_ASISTENTE);
        assertThat(testAsistencia.getResultado()).isEqualTo(UPDATED_RESULTADO);
        assertThat(testAsistencia.getAnoAsistencia()).isEqualTo(UPDATED_ANO_ASISTENCIA);
        assertThat(testAsistencia.getMesAsistencia()).isEqualTo(UPDATED_MES_ASISTENCIA);
        assertThat(testAsistencia.getDiaAsistencia()).isEqualTo(UPDATED_DIA_ASISTENCIA);
        assertThat(testAsistencia.getObservacion()).isEqualTo(UPDATED_OBSERVACION);
        assertThat(testAsistencia.getComentarios()).isEqualTo(UPDATED_COMENTARIOS);
        assertThat(testAsistencia.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testAsistencia.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testAsistencia.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testAsistencia.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testAsistencia.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testAsistencia.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testAsistencia.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testAsistencia.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testAsistencia.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingAsistencia() throws Exception {
        int databaseSizeBeforeUpdate = asistenciaRepository.findAll().size();
        asistencia.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAsistenciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, asistencia.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(asistencia))
            )
            .andExpect(status().isBadRequest());

        // Validate the Asistencia in the database
        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAsistencia() throws Exception {
        int databaseSizeBeforeUpdate = asistenciaRepository.findAll().size();
        asistencia.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAsistenciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(asistencia))
            )
            .andExpect(status().isBadRequest());

        // Validate the Asistencia in the database
        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAsistencia() throws Exception {
        int databaseSizeBeforeUpdate = asistenciaRepository.findAll().size();
        asistencia.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAsistenciaMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(asistencia))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Asistencia in the database
        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAsistencia() throws Exception {
        // Initialize the database
        asistenciaRepository.save(asistencia);

        int databaseSizeBeforeDelete = asistenciaRepository.findAll().size();

        // Delete the asistencia
        restAsistenciaMockMvc
            .perform(delete(ENTITY_API_URL_ID, asistencia.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Asistencia> asistenciaList = asistenciaRepository.findAll();
        assertThat(asistenciaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
