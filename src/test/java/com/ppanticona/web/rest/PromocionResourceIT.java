package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.Promocion;
import com.ppanticona.repository.PromocionRepository;
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
 * Integration tests for the {@link PromocionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PromocionResourceIT {

    private static final String DEFAULT_TIP_PROMOCION = "AAAAAAAAAA";
    private static final String UPDATED_TIP_PROMOCION = "BBBBBBBBBB";

    private static final String DEFAULT_VAL_1 = "AAAAAAAAAA";
    private static final String UPDATED_VAL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_VAL_2 = "AAAAAAAAAA";
    private static final String UPDATED_VAL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_VAL_3 = "AAAAAAAAAA";
    private static final String UPDATED_VAL_3 = "BBBBBBBBBB";

    private static final String DEFAULT_VAL_4 = "AAAAAAAAAA";
    private static final String UPDATED_VAL_4 = "BBBBBBBBBB";

    private static final String DEFAULT_VAL_5 = "AAAAAAAAAA";
    private static final String UPDATED_VAL_5 = "BBBBBBBBBB";

    private static final String DEFAULT_MAX_PROM = "AAAAAAAAAA";
    private static final String UPDATED_MAX_PROM = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_INI_VIG = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_INI_VIG = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FEC_FIN_VIG = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_FIN_VIG = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

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

    private static final String ENTITY_API_URL = "/api/promocions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private PromocionRepository promocionRepository;

    @Autowired
    private MockMvc restPromocionMockMvc;

    private Promocion promocion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Promocion createEntity() {
        Promocion promocion = new Promocion()
            .tipPromocion(DEFAULT_TIP_PROMOCION)
            .val1(DEFAULT_VAL_1)
            .val2(DEFAULT_VAL_2)
            .val3(DEFAULT_VAL_3)
            .val4(DEFAULT_VAL_4)
            .val5(DEFAULT_VAL_5)
            .maxProm(DEFAULT_MAX_PROM)
            .fecIniVig(DEFAULT_FEC_INI_VIG)
            .fecFinVig(DEFAULT_FEC_FIN_VIG)
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
        return promocion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Promocion createUpdatedEntity() {
        Promocion promocion = new Promocion()
            .tipPromocion(UPDATED_TIP_PROMOCION)
            .val1(UPDATED_VAL_1)
            .val2(UPDATED_VAL_2)
            .val3(UPDATED_VAL_3)
            .val4(UPDATED_VAL_4)
            .val5(UPDATED_VAL_5)
            .maxProm(UPDATED_MAX_PROM)
            .fecIniVig(UPDATED_FEC_INI_VIG)
            .fecFinVig(UPDATED_FEC_FIN_VIG)
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
        return promocion;
    }

    @BeforeEach
    public void initTest() {
        promocionRepository.deleteAll();
        promocion = createEntity();
    }

    @Test
    void createPromocion() throws Exception {
        int databaseSizeBeforeCreate = promocionRepository.findAll().size();
        // Create the Promocion
        restPromocionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promocion)))
            .andExpect(status().isCreated());

        // Validate the Promocion in the database
        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeCreate + 1);
        Promocion testPromocion = promocionList.get(promocionList.size() - 1);
        assertThat(testPromocion.getTipPromocion()).isEqualTo(DEFAULT_TIP_PROMOCION);
        assertThat(testPromocion.getVal1()).isEqualTo(DEFAULT_VAL_1);
        assertThat(testPromocion.getVal2()).isEqualTo(DEFAULT_VAL_2);
        assertThat(testPromocion.getVal3()).isEqualTo(DEFAULT_VAL_3);
        assertThat(testPromocion.getVal4()).isEqualTo(DEFAULT_VAL_4);
        assertThat(testPromocion.getVal5()).isEqualTo(DEFAULT_VAL_5);
        assertThat(testPromocion.getMaxProm()).isEqualTo(DEFAULT_MAX_PROM);
        assertThat(testPromocion.getFecIniVig()).isEqualTo(DEFAULT_FEC_INI_VIG);
        assertThat(testPromocion.getFecFinVig()).isEqualTo(DEFAULT_FEC_FIN_VIG);
        assertThat(testPromocion.getComentarios()).isEqualTo(DEFAULT_COMENTARIOS);
        assertThat(testPromocion.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testPromocion.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testPromocion.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testPromocion.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testPromocion.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testPromocion.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testPromocion.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testPromocion.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testPromocion.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createPromocionWithExistingId() throws Exception {
        // Create the Promocion with an existing ID
        promocion.setId("existing_id");

        int databaseSizeBeforeCreate = promocionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPromocionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promocion)))
            .andExpect(status().isBadRequest());

        // Validate the Promocion in the database
        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTipPromocionIsRequired() throws Exception {
        int databaseSizeBeforeTest = promocionRepository.findAll().size();
        // set the field null
        promocion.setTipPromocion(null);

        // Create the Promocion, which fails.

        restPromocionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promocion)))
            .andExpect(status().isBadRequest());

        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecIniVigIsRequired() throws Exception {
        int databaseSizeBeforeTest = promocionRepository.findAll().size();
        // set the field null
        promocion.setFecIniVig(null);

        // Create the Promocion, which fails.

        restPromocionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promocion)))
            .andExpect(status().isBadRequest());

        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecFinVigIsRequired() throws Exception {
        int databaseSizeBeforeTest = promocionRepository.findAll().size();
        // set the field null
        promocion.setFecFinVig(null);

        // Create the Promocion, which fails.

        restPromocionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promocion)))
            .andExpect(status().isBadRequest());

        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = promocionRepository.findAll().size();
        // set the field null
        promocion.setEstado(null);

        // Create the Promocion, which fails.

        restPromocionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promocion)))
            .andExpect(status().isBadRequest());

        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = promocionRepository.findAll().size();
        // set the field null
        promocion.setVersion(null);

        // Create the Promocion, which fails.

        restPromocionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promocion)))
            .andExpect(status().isBadRequest());

        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = promocionRepository.findAll().size();
        // set the field null
        promocion.setIndDel(null);

        // Create the Promocion, which fails.

        restPromocionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promocion)))
            .andExpect(status().isBadRequest());

        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = promocionRepository.findAll().size();
        // set the field null
        promocion.setFecCrea(null);

        // Create the Promocion, which fails.

        restPromocionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promocion)))
            .andExpect(status().isBadRequest());

        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = promocionRepository.findAll().size();
        // set the field null
        promocion.setUsuCrea(null);

        // Create the Promocion, which fails.

        restPromocionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promocion)))
            .andExpect(status().isBadRequest());

        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = promocionRepository.findAll().size();
        // set the field null
        promocion.setIpCrea(null);

        // Create the Promocion, which fails.

        restPromocionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promocion)))
            .andExpect(status().isBadRequest());

        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllPromocions() throws Exception {
        // Initialize the database
        promocionRepository.save(promocion);

        // Get all the promocionList
        restPromocionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(promocion.getId())))
            .andExpect(jsonPath("$.[*].tipPromocion").value(hasItem(DEFAULT_TIP_PROMOCION)))
            .andExpect(jsonPath("$.[*].val1").value(hasItem(DEFAULT_VAL_1)))
            .andExpect(jsonPath("$.[*].val2").value(hasItem(DEFAULT_VAL_2)))
            .andExpect(jsonPath("$.[*].val3").value(hasItem(DEFAULT_VAL_3)))
            .andExpect(jsonPath("$.[*].val4").value(hasItem(DEFAULT_VAL_4)))
            .andExpect(jsonPath("$.[*].val5").value(hasItem(DEFAULT_VAL_5)))
            .andExpect(jsonPath("$.[*].maxProm").value(hasItem(DEFAULT_MAX_PROM)))
            .andExpect(jsonPath("$.[*].fecIniVig").value(hasItem(sameInstant(DEFAULT_FEC_INI_VIG))))
            .andExpect(jsonPath("$.[*].fecFinVig").value(hasItem(sameInstant(DEFAULT_FEC_FIN_VIG))))
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
    void getPromocion() throws Exception {
        // Initialize the database
        promocionRepository.save(promocion);

        // Get the promocion
        restPromocionMockMvc
            .perform(get(ENTITY_API_URL_ID, promocion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(promocion.getId()))
            .andExpect(jsonPath("$.tipPromocion").value(DEFAULT_TIP_PROMOCION))
            .andExpect(jsonPath("$.val1").value(DEFAULT_VAL_1))
            .andExpect(jsonPath("$.val2").value(DEFAULT_VAL_2))
            .andExpect(jsonPath("$.val3").value(DEFAULT_VAL_3))
            .andExpect(jsonPath("$.val4").value(DEFAULT_VAL_4))
            .andExpect(jsonPath("$.val5").value(DEFAULT_VAL_5))
            .andExpect(jsonPath("$.maxProm").value(DEFAULT_MAX_PROM))
            .andExpect(jsonPath("$.fecIniVig").value(sameInstant(DEFAULT_FEC_INI_VIG)))
            .andExpect(jsonPath("$.fecFinVig").value(sameInstant(DEFAULT_FEC_FIN_VIG)))
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
    void getNonExistingPromocion() throws Exception {
        // Get the promocion
        restPromocionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingPromocion() throws Exception {
        // Initialize the database
        promocionRepository.save(promocion);

        int databaseSizeBeforeUpdate = promocionRepository.findAll().size();

        // Update the promocion
        Promocion updatedPromocion = promocionRepository.findById(promocion.getId()).get();
        updatedPromocion
            .tipPromocion(UPDATED_TIP_PROMOCION)
            .val1(UPDATED_VAL_1)
            .val2(UPDATED_VAL_2)
            .val3(UPDATED_VAL_3)
            .val4(UPDATED_VAL_4)
            .val5(UPDATED_VAL_5)
            .maxProm(UPDATED_MAX_PROM)
            .fecIniVig(UPDATED_FEC_INI_VIG)
            .fecFinVig(UPDATED_FEC_FIN_VIG)
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

        restPromocionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPromocion.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPromocion))
            )
            .andExpect(status().isOk());

        // Validate the Promocion in the database
        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeUpdate);
        Promocion testPromocion = promocionList.get(promocionList.size() - 1);
        assertThat(testPromocion.getTipPromocion()).isEqualTo(UPDATED_TIP_PROMOCION);
        assertThat(testPromocion.getVal1()).isEqualTo(UPDATED_VAL_1);
        assertThat(testPromocion.getVal2()).isEqualTo(UPDATED_VAL_2);
        assertThat(testPromocion.getVal3()).isEqualTo(UPDATED_VAL_3);
        assertThat(testPromocion.getVal4()).isEqualTo(UPDATED_VAL_4);
        assertThat(testPromocion.getVal5()).isEqualTo(UPDATED_VAL_5);
        assertThat(testPromocion.getMaxProm()).isEqualTo(UPDATED_MAX_PROM);
        assertThat(testPromocion.getFecIniVig()).isEqualTo(UPDATED_FEC_INI_VIG);
        assertThat(testPromocion.getFecFinVig()).isEqualTo(UPDATED_FEC_FIN_VIG);
        assertThat(testPromocion.getComentarios()).isEqualTo(UPDATED_COMENTARIOS);
        assertThat(testPromocion.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testPromocion.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testPromocion.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testPromocion.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testPromocion.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testPromocion.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testPromocion.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testPromocion.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testPromocion.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingPromocion() throws Exception {
        int databaseSizeBeforeUpdate = promocionRepository.findAll().size();
        promocion.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPromocionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, promocion.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(promocion))
            )
            .andExpect(status().isBadRequest());

        // Validate the Promocion in the database
        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPromocion() throws Exception {
        int databaseSizeBeforeUpdate = promocionRepository.findAll().size();
        promocion.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPromocionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(promocion))
            )
            .andExpect(status().isBadRequest());

        // Validate the Promocion in the database
        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPromocion() throws Exception {
        int databaseSizeBeforeUpdate = promocionRepository.findAll().size();
        promocion.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPromocionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promocion)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Promocion in the database
        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePromocionWithPatch() throws Exception {
        // Initialize the database
        promocionRepository.save(promocion);

        int databaseSizeBeforeUpdate = promocionRepository.findAll().size();

        // Update the promocion using partial update
        Promocion partialUpdatedPromocion = new Promocion();
        partialUpdatedPromocion.setId(promocion.getId());

        partialUpdatedPromocion
            .tipPromocion(UPDATED_TIP_PROMOCION)
            .val5(UPDATED_VAL_5)
            .fecIniVig(UPDATED_FEC_INI_VIG)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restPromocionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPromocion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPromocion))
            )
            .andExpect(status().isOk());

        // Validate the Promocion in the database
        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeUpdate);
        Promocion testPromocion = promocionList.get(promocionList.size() - 1);
        assertThat(testPromocion.getTipPromocion()).isEqualTo(UPDATED_TIP_PROMOCION);
        assertThat(testPromocion.getVal1()).isEqualTo(DEFAULT_VAL_1);
        assertThat(testPromocion.getVal2()).isEqualTo(DEFAULT_VAL_2);
        assertThat(testPromocion.getVal3()).isEqualTo(DEFAULT_VAL_3);
        assertThat(testPromocion.getVal4()).isEqualTo(DEFAULT_VAL_4);
        assertThat(testPromocion.getVal5()).isEqualTo(UPDATED_VAL_5);
        assertThat(testPromocion.getMaxProm()).isEqualTo(DEFAULT_MAX_PROM);
        assertThat(testPromocion.getFecIniVig()).isEqualTo(UPDATED_FEC_INI_VIG);
        assertThat(testPromocion.getFecFinVig()).isEqualTo(DEFAULT_FEC_FIN_VIG);
        assertThat(testPromocion.getComentarios()).isEqualTo(DEFAULT_COMENTARIOS);
        assertThat(testPromocion.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testPromocion.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testPromocion.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testPromocion.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testPromocion.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testPromocion.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testPromocion.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testPromocion.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testPromocion.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void fullUpdatePromocionWithPatch() throws Exception {
        // Initialize the database
        promocionRepository.save(promocion);

        int databaseSizeBeforeUpdate = promocionRepository.findAll().size();

        // Update the promocion using partial update
        Promocion partialUpdatedPromocion = new Promocion();
        partialUpdatedPromocion.setId(promocion.getId());

        partialUpdatedPromocion
            .tipPromocion(UPDATED_TIP_PROMOCION)
            .val1(UPDATED_VAL_1)
            .val2(UPDATED_VAL_2)
            .val3(UPDATED_VAL_3)
            .val4(UPDATED_VAL_4)
            .val5(UPDATED_VAL_5)
            .maxProm(UPDATED_MAX_PROM)
            .fecIniVig(UPDATED_FEC_INI_VIG)
            .fecFinVig(UPDATED_FEC_FIN_VIG)
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

        restPromocionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPromocion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPromocion))
            )
            .andExpect(status().isOk());

        // Validate the Promocion in the database
        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeUpdate);
        Promocion testPromocion = promocionList.get(promocionList.size() - 1);
        assertThat(testPromocion.getTipPromocion()).isEqualTo(UPDATED_TIP_PROMOCION);
        assertThat(testPromocion.getVal1()).isEqualTo(UPDATED_VAL_1);
        assertThat(testPromocion.getVal2()).isEqualTo(UPDATED_VAL_2);
        assertThat(testPromocion.getVal3()).isEqualTo(UPDATED_VAL_3);
        assertThat(testPromocion.getVal4()).isEqualTo(UPDATED_VAL_4);
        assertThat(testPromocion.getVal5()).isEqualTo(UPDATED_VAL_5);
        assertThat(testPromocion.getMaxProm()).isEqualTo(UPDATED_MAX_PROM);
        assertThat(testPromocion.getFecIniVig()).isEqualTo(UPDATED_FEC_INI_VIG);
        assertThat(testPromocion.getFecFinVig()).isEqualTo(UPDATED_FEC_FIN_VIG);
        assertThat(testPromocion.getComentarios()).isEqualTo(UPDATED_COMENTARIOS);
        assertThat(testPromocion.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testPromocion.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testPromocion.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testPromocion.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testPromocion.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testPromocion.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testPromocion.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testPromocion.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testPromocion.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingPromocion() throws Exception {
        int databaseSizeBeforeUpdate = promocionRepository.findAll().size();
        promocion.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPromocionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, promocion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(promocion))
            )
            .andExpect(status().isBadRequest());

        // Validate the Promocion in the database
        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPromocion() throws Exception {
        int databaseSizeBeforeUpdate = promocionRepository.findAll().size();
        promocion.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPromocionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(promocion))
            )
            .andExpect(status().isBadRequest());

        // Validate the Promocion in the database
        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPromocion() throws Exception {
        int databaseSizeBeforeUpdate = promocionRepository.findAll().size();
        promocion.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPromocionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(promocion))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Promocion in the database
        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePromocion() throws Exception {
        // Initialize the database
        promocionRepository.save(promocion);

        int databaseSizeBeforeDelete = promocionRepository.findAll().size();

        // Delete the promocion
        restPromocionMockMvc
            .perform(delete(ENTITY_API_URL_ID, promocion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Promocion> promocionList = promocionRepository.findAll();
        assertThat(promocionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
