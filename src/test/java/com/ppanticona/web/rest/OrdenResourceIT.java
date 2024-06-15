package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.Orden;
import com.ppanticona.repository.OrdenRepository;
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
 * Integration tests for the {@link OrdenResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OrdenResourceIT {

    private static final Integer DEFAULT_NUM_ORDEN = 1;
    private static final Integer UPDATED_NUM_ORDEN = 2;

    private static final String DEFAULT_OBSERVACION = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_INGRESO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_INGRESO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FEC_SALIDA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_SALIDA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_COD_CANAL = "AAAAAAAAAA";
    private static final String UPDATED_COD_CANAL = "BBBBBBBBBB";

    private static final String DEFAULT_TIP_ORDEN = "AAAAAAAAAA";
    private static final String UPDATED_TIP_ORDEN = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/ordens";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private MockMvc restOrdenMockMvc;

    private Orden orden;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Orden createEntity() {
        Orden orden = new Orden()
            .numOrden(DEFAULT_NUM_ORDEN)
            .observacion(DEFAULT_OBSERVACION)
            .fecIngreso(DEFAULT_FEC_INGRESO)
            .fecSalida(DEFAULT_FEC_SALIDA)
            .codCanal(DEFAULT_COD_CANAL)
            .tipOrden(DEFAULT_TIP_ORDEN)
            .estado(DEFAULT_ESTADO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return orden;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Orden createUpdatedEntity() {
        Orden orden = new Orden()
            .numOrden(UPDATED_NUM_ORDEN)
            .observacion(UPDATED_OBSERVACION)
            .fecIngreso(UPDATED_FEC_INGRESO)
            .fecSalida(UPDATED_FEC_SALIDA)
            .codCanal(UPDATED_COD_CANAL)
            .tipOrden(UPDATED_TIP_ORDEN)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return orden;
    }

    @BeforeEach
    public void initTest() {
        ordenRepository.deleteAll();
        orden = createEntity();
    }

    @Test
    void createOrden() throws Exception {
        int databaseSizeBeforeCreate = ordenRepository.findAll().size();
        // Create the Orden
        restOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orden)))
            .andExpect(status().isCreated());

        // Validate the Orden in the database
        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeCreate + 1);
        Orden testOrden = ordenList.get(ordenList.size() - 1);
        assertThat(testOrden.getNumOrden()).isEqualTo(DEFAULT_NUM_ORDEN);
        assertThat(testOrden.getObservacion()).isEqualTo(DEFAULT_OBSERVACION);
        assertThat(testOrden.getFecIngreso()).isEqualTo(DEFAULT_FEC_INGRESO);
        assertThat(testOrden.getFecSalida()).isEqualTo(DEFAULT_FEC_SALIDA);
        assertThat(testOrden.getCodCanal()).isEqualTo(DEFAULT_COD_CANAL);
        assertThat(testOrden.getTipOrden()).isEqualTo(DEFAULT_TIP_ORDEN);
        assertThat(testOrden.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testOrden.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testOrden.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testOrden.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testOrden.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testOrden.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testOrden.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testOrden.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testOrden.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createOrdenWithExistingId() throws Exception {
        // Create the Orden with an existing ID
        orden.setId("existing_id");

        int databaseSizeBeforeCreate = ordenRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orden)))
            .andExpect(status().isBadRequest());

        // Validate the Orden in the database
        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNumOrdenIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordenRepository.findAll().size();
        // set the field null
        orden.setNumOrden(null);

        // Create the Orden, which fails.

        restOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orden)))
            .andExpect(status().isBadRequest());

        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCodCanalIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordenRepository.findAll().size();
        // set the field null
        orden.setCodCanal(null);

        // Create the Orden, which fails.

        restOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orden)))
            .andExpect(status().isBadRequest());

        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTipOrdenIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordenRepository.findAll().size();
        // set the field null
        orden.setTipOrden(null);

        // Create the Orden, which fails.

        restOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orden)))
            .andExpect(status().isBadRequest());

        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordenRepository.findAll().size();
        // set the field null
        orden.setEstado(null);

        // Create the Orden, which fails.

        restOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orden)))
            .andExpect(status().isBadRequest());

        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordenRepository.findAll().size();
        // set the field null
        orden.setVersion(null);

        // Create the Orden, which fails.

        restOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orden)))
            .andExpect(status().isBadRequest());

        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordenRepository.findAll().size();
        // set the field null
        orden.setIndDel(null);

        // Create the Orden, which fails.

        restOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orden)))
            .andExpect(status().isBadRequest());

        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordenRepository.findAll().size();
        // set the field null
        orden.setFecCrea(null);

        // Create the Orden, which fails.

        restOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orden)))
            .andExpect(status().isBadRequest());

        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordenRepository.findAll().size();
        // set the field null
        orden.setUsuCrea(null);

        // Create the Orden, which fails.

        restOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orden)))
            .andExpect(status().isBadRequest());

        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordenRepository.findAll().size();
        // set the field null
        orden.setIpCrea(null);

        // Create the Orden, which fails.

        restOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orden)))
            .andExpect(status().isBadRequest());

        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllOrdens() throws Exception {
        // Initialize the database
        ordenRepository.save(orden);

        // Get all the ordenList
        restOrdenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orden.getId())))
            .andExpect(jsonPath("$.[*].numOrden").value(hasItem(DEFAULT_NUM_ORDEN)))
            .andExpect(jsonPath("$.[*].observacion").value(hasItem(DEFAULT_OBSERVACION.toString())))
            .andExpect(jsonPath("$.[*].fecIngreso").value(hasItem(sameInstant(DEFAULT_FEC_INGRESO))))
            .andExpect(jsonPath("$.[*].fecSalida").value(hasItem(sameInstant(DEFAULT_FEC_SALIDA))))
            .andExpect(jsonPath("$.[*].codCanal").value(hasItem(DEFAULT_COD_CANAL)))
            .andExpect(jsonPath("$.[*].tipOrden").value(hasItem(DEFAULT_TIP_ORDEN)))
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
    void getOrden() throws Exception {
        // Initialize the database
        ordenRepository.save(orden);

        // Get the orden
        restOrdenMockMvc
            .perform(get(ENTITY_API_URL_ID, orden.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orden.getId()))
            .andExpect(jsonPath("$.numOrden").value(DEFAULT_NUM_ORDEN))
            .andExpect(jsonPath("$.observacion").value(DEFAULT_OBSERVACION.toString()))
            .andExpect(jsonPath("$.fecIngreso").value(sameInstant(DEFAULT_FEC_INGRESO)))
            .andExpect(jsonPath("$.fecSalida").value(sameInstant(DEFAULT_FEC_SALIDA)))
            .andExpect(jsonPath("$.codCanal").value(DEFAULT_COD_CANAL))
            .andExpect(jsonPath("$.tipOrden").value(DEFAULT_TIP_ORDEN))
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
    void getNonExistingOrden() throws Exception {
        // Get the orden
        restOrdenMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingOrden() throws Exception {
        // Initialize the database
        ordenRepository.save(orden);

        int databaseSizeBeforeUpdate = ordenRepository.findAll().size();

        // Update the orden
        Orden updatedOrden = ordenRepository.findById(orden.getId()).get();
        updatedOrden
            .numOrden(UPDATED_NUM_ORDEN)
            .observacion(UPDATED_OBSERVACION)
            .fecIngreso(UPDATED_FEC_INGRESO)
            .fecSalida(UPDATED_FEC_SALIDA)
            .codCanal(UPDATED_COD_CANAL)
            .tipOrden(UPDATED_TIP_ORDEN)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restOrdenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOrden.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOrden))
            )
            .andExpect(status().isOk());

        // Validate the Orden in the database
        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeUpdate);
        Orden testOrden = ordenList.get(ordenList.size() - 1);
        assertThat(testOrden.getNumOrden()).isEqualTo(UPDATED_NUM_ORDEN);
        assertThat(testOrden.getObservacion()).isEqualTo(UPDATED_OBSERVACION);
        assertThat(testOrden.getFecIngreso()).isEqualTo(UPDATED_FEC_INGRESO);
        assertThat(testOrden.getFecSalida()).isEqualTo(UPDATED_FEC_SALIDA);
        assertThat(testOrden.getCodCanal()).isEqualTo(UPDATED_COD_CANAL);
        assertThat(testOrden.getTipOrden()).isEqualTo(UPDATED_TIP_ORDEN);
        assertThat(testOrden.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testOrden.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testOrden.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testOrden.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testOrden.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testOrden.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testOrden.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testOrden.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testOrden.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingOrden() throws Exception {
        int databaseSizeBeforeUpdate = ordenRepository.findAll().size();
        orden.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrdenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orden.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orden))
            )
            .andExpect(status().isBadRequest());

        // Validate the Orden in the database
        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchOrden() throws Exception {
        int databaseSizeBeforeUpdate = ordenRepository.findAll().size();
        orden.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrdenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orden))
            )
            .andExpect(status().isBadRequest());

        // Validate the Orden in the database
        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamOrden() throws Exception {
        int databaseSizeBeforeUpdate = ordenRepository.findAll().size();
        orden.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrdenMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orden)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Orden in the database
        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateOrdenWithPatch() throws Exception {
        // Initialize the database
        ordenRepository.save(orden);

        int databaseSizeBeforeUpdate = ordenRepository.findAll().size();

        // Update the orden using partial update
        Orden partialUpdatedOrden = new Orden();
        partialUpdatedOrden.setId(orden.getId());

        partialUpdatedOrden
            .fecSalida(UPDATED_FEC_SALIDA)
            .codCanal(UPDATED_COD_CANAL)
            .tipOrden(UPDATED_TIP_ORDEN)
            .version(UPDATED_VERSION)
            .fecCrea(UPDATED_FEC_CREA)
            .ipCrea(UPDATED_IP_CREA);

        restOrdenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrden.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrden))
            )
            .andExpect(status().isOk());

        // Validate the Orden in the database
        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeUpdate);
        Orden testOrden = ordenList.get(ordenList.size() - 1);
        assertThat(testOrden.getNumOrden()).isEqualTo(DEFAULT_NUM_ORDEN);
        assertThat(testOrden.getObservacion()).isEqualTo(DEFAULT_OBSERVACION);
        assertThat(testOrden.getFecIngreso()).isEqualTo(DEFAULT_FEC_INGRESO);
        assertThat(testOrden.getFecSalida()).isEqualTo(UPDATED_FEC_SALIDA);
        assertThat(testOrden.getCodCanal()).isEqualTo(UPDATED_COD_CANAL);
        assertThat(testOrden.getTipOrden()).isEqualTo(UPDATED_TIP_ORDEN);
        assertThat(testOrden.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testOrden.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testOrden.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testOrden.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testOrden.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testOrden.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testOrden.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testOrden.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testOrden.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void fullUpdateOrdenWithPatch() throws Exception {
        // Initialize the database
        ordenRepository.save(orden);

        int databaseSizeBeforeUpdate = ordenRepository.findAll().size();

        // Update the orden using partial update
        Orden partialUpdatedOrden = new Orden();
        partialUpdatedOrden.setId(orden.getId());

        partialUpdatedOrden
            .numOrden(UPDATED_NUM_ORDEN)
            .observacion(UPDATED_OBSERVACION)
            .fecIngreso(UPDATED_FEC_INGRESO)
            .fecSalida(UPDATED_FEC_SALIDA)
            .codCanal(UPDATED_COD_CANAL)
            .tipOrden(UPDATED_TIP_ORDEN)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restOrdenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrden.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrden))
            )
            .andExpect(status().isOk());

        // Validate the Orden in the database
        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeUpdate);
        Orden testOrden = ordenList.get(ordenList.size() - 1);
        assertThat(testOrden.getNumOrden()).isEqualTo(UPDATED_NUM_ORDEN);
        assertThat(testOrden.getObservacion()).isEqualTo(UPDATED_OBSERVACION);
        assertThat(testOrden.getFecIngreso()).isEqualTo(UPDATED_FEC_INGRESO);
        assertThat(testOrden.getFecSalida()).isEqualTo(UPDATED_FEC_SALIDA);
        assertThat(testOrden.getCodCanal()).isEqualTo(UPDATED_COD_CANAL);
        assertThat(testOrden.getTipOrden()).isEqualTo(UPDATED_TIP_ORDEN);
        assertThat(testOrden.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testOrden.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testOrden.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testOrden.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testOrden.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testOrden.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testOrden.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testOrden.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testOrden.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingOrden() throws Exception {
        int databaseSizeBeforeUpdate = ordenRepository.findAll().size();
        orden.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrdenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, orden.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(orden))
            )
            .andExpect(status().isBadRequest());

        // Validate the Orden in the database
        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchOrden() throws Exception {
        int databaseSizeBeforeUpdate = ordenRepository.findAll().size();
        orden.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrdenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(orden))
            )
            .andExpect(status().isBadRequest());

        // Validate the Orden in the database
        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamOrden() throws Exception {
        int databaseSizeBeforeUpdate = ordenRepository.findAll().size();
        orden.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrdenMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(orden)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Orden in the database
        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteOrden() throws Exception {
        // Initialize the database
        ordenRepository.save(orden);

        int databaseSizeBeforeDelete = ordenRepository.findAll().size();

        // Delete the orden
        restOrdenMockMvc
            .perform(delete(ENTITY_API_URL_ID, orden.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Orden> ordenList = ordenRepository.findAll();
        assertThat(ordenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
