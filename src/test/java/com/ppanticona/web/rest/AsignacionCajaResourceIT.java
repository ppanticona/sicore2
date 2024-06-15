package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.AsignacionCaja;
import com.ppanticona.repository.AsignacionCajaRepository;
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
 * Integration tests for the {@link AsignacionCajaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AsignacionCajaResourceIT {

    private static final String DEFAULT_COD_ASIGNACION = "AAAAAAAAAA";
    private static final String UPDATED_COD_ASIGNACION = "BBBBBBBBBB";

    private static final Double DEFAULT_MNTO_INICIAL_SOLES = 1D;
    private static final Double UPDATED_MNTO_INICIAL_SOLES = 2D;

    private static final Double DEFAULT_MNTO_FINAL_SOLES = 1D;
    private static final Double UPDATED_MNTO_FINAL_SOLES = 2D;

    private static final Double DEFAULT_MONTO_MAXIMO_SOLES = 1D;
    private static final Double UPDATED_MONTO_MAXIMO_SOLES = 2D;

    private static final Double DEFAULT_DIFERENCIA_SOLES = 1D;
    private static final Double UPDATED_DIFERENCIA_SOLES = 2D;

    private static final Double DEFAULT_MNTO_INICIAL_DOLARES = 1D;
    private static final Double UPDATED_MNTO_INICIAL_DOLARES = 2D;

    private static final Double DEFAULT_MNTO_FINAL_DOLARES = 1D;
    private static final Double UPDATED_MNTO_FINAL_DOLARES = 2D;

    private static final Double DEFAULT_MONTO_MAXIMO_DOLARES = 1D;
    private static final Double UPDATED_MONTO_MAXIMO_DOLARES = 2D;

    private static final Double DEFAULT_DIFERENCIA_DOLARES = 1D;
    private static final Double UPDATED_DIFERENCIA_DOLARES = 2D;

    private static final String DEFAULT_OBSERVACION_APERTURA = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACION_APERTURA = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACION_CIERRE = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACION_CIERRE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_ASIGNACION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_ASIGNACION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

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

    private static final String ENTITY_API_URL = "/api/asignacion-cajas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private AsignacionCajaRepository asignacionCajaRepository;

    @Autowired
    private MockMvc restAsignacionCajaMockMvc;

    private AsignacionCaja asignacionCaja;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AsignacionCaja createEntity() {
        AsignacionCaja asignacionCaja = new AsignacionCaja()
            .codAsignacion(DEFAULT_COD_ASIGNACION)
            .mntoInicialSoles(DEFAULT_MNTO_INICIAL_SOLES)
            .mntoFinalSoles(DEFAULT_MNTO_FINAL_SOLES)
            .montoMaximoSoles(DEFAULT_MONTO_MAXIMO_SOLES)
            .diferenciaSoles(DEFAULT_DIFERENCIA_SOLES)
            .mntoInicialDolares(DEFAULT_MNTO_INICIAL_DOLARES)
            .mntoFinalDolares(DEFAULT_MNTO_FINAL_DOLARES)
            .montoMaximoDolares(DEFAULT_MONTO_MAXIMO_DOLARES)
            .diferenciaDolares(DEFAULT_DIFERENCIA_DOLARES)
            .observacionApertura(DEFAULT_OBSERVACION_APERTURA)
            .observacionCierre(DEFAULT_OBSERVACION_CIERRE)
            .fecAsignacion(DEFAULT_FEC_ASIGNACION)
            .estado(DEFAULT_ESTADO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return asignacionCaja;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AsignacionCaja createUpdatedEntity() {
        AsignacionCaja asignacionCaja = new AsignacionCaja()
            .codAsignacion(UPDATED_COD_ASIGNACION)
            .mntoInicialSoles(UPDATED_MNTO_INICIAL_SOLES)
            .mntoFinalSoles(UPDATED_MNTO_FINAL_SOLES)
            .montoMaximoSoles(UPDATED_MONTO_MAXIMO_SOLES)
            .diferenciaSoles(UPDATED_DIFERENCIA_SOLES)
            .mntoInicialDolares(UPDATED_MNTO_INICIAL_DOLARES)
            .mntoFinalDolares(UPDATED_MNTO_FINAL_DOLARES)
            .montoMaximoDolares(UPDATED_MONTO_MAXIMO_DOLARES)
            .diferenciaDolares(UPDATED_DIFERENCIA_DOLARES)
            .observacionApertura(UPDATED_OBSERVACION_APERTURA)
            .observacionCierre(UPDATED_OBSERVACION_CIERRE)
            .fecAsignacion(UPDATED_FEC_ASIGNACION)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return asignacionCaja;
    }

    @BeforeEach
    public void initTest() {
        asignacionCajaRepository.deleteAll();
        asignacionCaja = createEntity();
    }

    @Test
    void createAsignacionCaja() throws Exception {
        int databaseSizeBeforeCreate = asignacionCajaRepository.findAll().size();
        // Create the AsignacionCaja
        restAsignacionCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isCreated());

        // Validate the AsignacionCaja in the database
        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeCreate + 1);
        AsignacionCaja testAsignacionCaja = asignacionCajaList.get(asignacionCajaList.size() - 1);
        assertThat(testAsignacionCaja.getCodAsignacion()).isEqualTo(DEFAULT_COD_ASIGNACION);
        assertThat(testAsignacionCaja.getMntoInicialSoles()).isEqualTo(DEFAULT_MNTO_INICIAL_SOLES);
        assertThat(testAsignacionCaja.getMntoFinalSoles()).isEqualTo(DEFAULT_MNTO_FINAL_SOLES);
        assertThat(testAsignacionCaja.getMontoMaximoSoles()).isEqualTo(DEFAULT_MONTO_MAXIMO_SOLES);
        assertThat(testAsignacionCaja.getDiferenciaSoles()).isEqualTo(DEFAULT_DIFERENCIA_SOLES);
        assertThat(testAsignacionCaja.getMntoInicialDolares()).isEqualTo(DEFAULT_MNTO_INICIAL_DOLARES);
        assertThat(testAsignacionCaja.getMntoFinalDolares()).isEqualTo(DEFAULT_MNTO_FINAL_DOLARES);
        assertThat(testAsignacionCaja.getMontoMaximoDolares()).isEqualTo(DEFAULT_MONTO_MAXIMO_DOLARES);
        assertThat(testAsignacionCaja.getDiferenciaDolares()).isEqualTo(DEFAULT_DIFERENCIA_DOLARES);
        assertThat(testAsignacionCaja.getObservacionApertura()).isEqualTo(DEFAULT_OBSERVACION_APERTURA);
        assertThat(testAsignacionCaja.getObservacionCierre()).isEqualTo(DEFAULT_OBSERVACION_CIERRE);
        assertThat(testAsignacionCaja.getFecAsignacion()).isEqualTo(DEFAULT_FEC_ASIGNACION);
        assertThat(testAsignacionCaja.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testAsignacionCaja.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testAsignacionCaja.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testAsignacionCaja.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testAsignacionCaja.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testAsignacionCaja.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testAsignacionCaja.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testAsignacionCaja.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testAsignacionCaja.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createAsignacionCajaWithExistingId() throws Exception {
        // Create the AsignacionCaja with an existing ID
        asignacionCaja.setId("existing_id");

        int databaseSizeBeforeCreate = asignacionCajaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAsignacionCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the AsignacionCaja in the database
        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkCodAsignacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = asignacionCajaRepository.findAll().size();
        // set the field null
        asignacionCaja.setCodAsignacion(null);

        // Create the AsignacionCaja, which fails.

        restAsignacionCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isBadRequest());

        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = asignacionCajaRepository.findAll().size();
        // set the field null
        asignacionCaja.setEstado(null);

        // Create the AsignacionCaja, which fails.

        restAsignacionCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isBadRequest());

        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = asignacionCajaRepository.findAll().size();
        // set the field null
        asignacionCaja.setVersion(null);

        // Create the AsignacionCaja, which fails.

        restAsignacionCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isBadRequest());

        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = asignacionCajaRepository.findAll().size();
        // set the field null
        asignacionCaja.setIndDel(null);

        // Create the AsignacionCaja, which fails.

        restAsignacionCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isBadRequest());

        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = asignacionCajaRepository.findAll().size();
        // set the field null
        asignacionCaja.setFecCrea(null);

        // Create the AsignacionCaja, which fails.

        restAsignacionCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isBadRequest());

        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = asignacionCajaRepository.findAll().size();
        // set the field null
        asignacionCaja.setUsuCrea(null);

        // Create the AsignacionCaja, which fails.

        restAsignacionCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isBadRequest());

        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = asignacionCajaRepository.findAll().size();
        // set the field null
        asignacionCaja.setIpCrea(null);

        // Create the AsignacionCaja, which fails.

        restAsignacionCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isBadRequest());

        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllAsignacionCajas() throws Exception {
        // Initialize the database
        asignacionCajaRepository.save(asignacionCaja);

        // Get all the asignacionCajaList
        restAsignacionCajaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(asignacionCaja.getId())))
            .andExpect(jsonPath("$.[*].codAsignacion").value(hasItem(DEFAULT_COD_ASIGNACION)))
            .andExpect(jsonPath("$.[*].mntoInicialSoles").value(hasItem(DEFAULT_MNTO_INICIAL_SOLES.doubleValue())))
            .andExpect(jsonPath("$.[*].mntoFinalSoles").value(hasItem(DEFAULT_MNTO_FINAL_SOLES.doubleValue())))
            .andExpect(jsonPath("$.[*].montoMaximoSoles").value(hasItem(DEFAULT_MONTO_MAXIMO_SOLES.doubleValue())))
            .andExpect(jsonPath("$.[*].diferenciaSoles").value(hasItem(DEFAULT_DIFERENCIA_SOLES.doubleValue())))
            .andExpect(jsonPath("$.[*].mntoInicialDolares").value(hasItem(DEFAULT_MNTO_INICIAL_DOLARES.doubleValue())))
            .andExpect(jsonPath("$.[*].mntoFinalDolares").value(hasItem(DEFAULT_MNTO_FINAL_DOLARES.doubleValue())))
            .andExpect(jsonPath("$.[*].montoMaximoDolares").value(hasItem(DEFAULT_MONTO_MAXIMO_DOLARES.doubleValue())))
            .andExpect(jsonPath("$.[*].diferenciaDolares").value(hasItem(DEFAULT_DIFERENCIA_DOLARES.doubleValue())))
            .andExpect(jsonPath("$.[*].observacionApertura").value(hasItem(DEFAULT_OBSERVACION_APERTURA)))
            .andExpect(jsonPath("$.[*].observacionCierre").value(hasItem(DEFAULT_OBSERVACION_CIERRE)))
            .andExpect(jsonPath("$.[*].fecAsignacion").value(hasItem(sameInstant(DEFAULT_FEC_ASIGNACION))))
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
    void getAsignacionCaja() throws Exception {
        // Initialize the database
        asignacionCajaRepository.save(asignacionCaja);

        // Get the asignacionCaja
        restAsignacionCajaMockMvc
            .perform(get(ENTITY_API_URL_ID, asignacionCaja.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(asignacionCaja.getId()))
            .andExpect(jsonPath("$.codAsignacion").value(DEFAULT_COD_ASIGNACION))
            .andExpect(jsonPath("$.mntoInicialSoles").value(DEFAULT_MNTO_INICIAL_SOLES.doubleValue()))
            .andExpect(jsonPath("$.mntoFinalSoles").value(DEFAULT_MNTO_FINAL_SOLES.doubleValue()))
            .andExpect(jsonPath("$.montoMaximoSoles").value(DEFAULT_MONTO_MAXIMO_SOLES.doubleValue()))
            .andExpect(jsonPath("$.diferenciaSoles").value(DEFAULT_DIFERENCIA_SOLES.doubleValue()))
            .andExpect(jsonPath("$.mntoInicialDolares").value(DEFAULT_MNTO_INICIAL_DOLARES.doubleValue()))
            .andExpect(jsonPath("$.mntoFinalDolares").value(DEFAULT_MNTO_FINAL_DOLARES.doubleValue()))
            .andExpect(jsonPath("$.montoMaximoDolares").value(DEFAULT_MONTO_MAXIMO_DOLARES.doubleValue()))
            .andExpect(jsonPath("$.diferenciaDolares").value(DEFAULT_DIFERENCIA_DOLARES.doubleValue()))
            .andExpect(jsonPath("$.observacionApertura").value(DEFAULT_OBSERVACION_APERTURA))
            .andExpect(jsonPath("$.observacionCierre").value(DEFAULT_OBSERVACION_CIERRE))
            .andExpect(jsonPath("$.fecAsignacion").value(sameInstant(DEFAULT_FEC_ASIGNACION)))
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
    void getNonExistingAsignacionCaja() throws Exception {
        // Get the asignacionCaja
        restAsignacionCajaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingAsignacionCaja() throws Exception {
        // Initialize the database
        asignacionCajaRepository.save(asignacionCaja);

        int databaseSizeBeforeUpdate = asignacionCajaRepository.findAll().size();

        // Update the asignacionCaja
        AsignacionCaja updatedAsignacionCaja = asignacionCajaRepository.findById(asignacionCaja.getId()).get();
        updatedAsignacionCaja
            .codAsignacion(UPDATED_COD_ASIGNACION)
            .mntoInicialSoles(UPDATED_MNTO_INICIAL_SOLES)
            .mntoFinalSoles(UPDATED_MNTO_FINAL_SOLES)
            .montoMaximoSoles(UPDATED_MONTO_MAXIMO_SOLES)
            .diferenciaSoles(UPDATED_DIFERENCIA_SOLES)
            .mntoInicialDolares(UPDATED_MNTO_INICIAL_DOLARES)
            .mntoFinalDolares(UPDATED_MNTO_FINAL_DOLARES)
            .montoMaximoDolares(UPDATED_MONTO_MAXIMO_DOLARES)
            .diferenciaDolares(UPDATED_DIFERENCIA_DOLARES)
            .observacionApertura(UPDATED_OBSERVACION_APERTURA)
            .observacionCierre(UPDATED_OBSERVACION_CIERRE)
            .fecAsignacion(UPDATED_FEC_ASIGNACION)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restAsignacionCajaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAsignacionCaja.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAsignacionCaja))
            )
            .andExpect(status().isOk());

        // Validate the AsignacionCaja in the database
        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeUpdate);
        AsignacionCaja testAsignacionCaja = asignacionCajaList.get(asignacionCajaList.size() - 1);
        assertThat(testAsignacionCaja.getCodAsignacion()).isEqualTo(UPDATED_COD_ASIGNACION);
        assertThat(testAsignacionCaja.getMntoInicialSoles()).isEqualTo(UPDATED_MNTO_INICIAL_SOLES);
        assertThat(testAsignacionCaja.getMntoFinalSoles()).isEqualTo(UPDATED_MNTO_FINAL_SOLES);
        assertThat(testAsignacionCaja.getMontoMaximoSoles()).isEqualTo(UPDATED_MONTO_MAXIMO_SOLES);
        assertThat(testAsignacionCaja.getDiferenciaSoles()).isEqualTo(UPDATED_DIFERENCIA_SOLES);
        assertThat(testAsignacionCaja.getMntoInicialDolares()).isEqualTo(UPDATED_MNTO_INICIAL_DOLARES);
        assertThat(testAsignacionCaja.getMntoFinalDolares()).isEqualTo(UPDATED_MNTO_FINAL_DOLARES);
        assertThat(testAsignacionCaja.getMontoMaximoDolares()).isEqualTo(UPDATED_MONTO_MAXIMO_DOLARES);
        assertThat(testAsignacionCaja.getDiferenciaDolares()).isEqualTo(UPDATED_DIFERENCIA_DOLARES);
        assertThat(testAsignacionCaja.getObservacionApertura()).isEqualTo(UPDATED_OBSERVACION_APERTURA);
        assertThat(testAsignacionCaja.getObservacionCierre()).isEqualTo(UPDATED_OBSERVACION_CIERRE);
        assertThat(testAsignacionCaja.getFecAsignacion()).isEqualTo(UPDATED_FEC_ASIGNACION);
        assertThat(testAsignacionCaja.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testAsignacionCaja.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testAsignacionCaja.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testAsignacionCaja.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testAsignacionCaja.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testAsignacionCaja.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testAsignacionCaja.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testAsignacionCaja.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testAsignacionCaja.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingAsignacionCaja() throws Exception {
        int databaseSizeBeforeUpdate = asignacionCajaRepository.findAll().size();
        asignacionCaja.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAsignacionCajaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, asignacionCaja.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the AsignacionCaja in the database
        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAsignacionCaja() throws Exception {
        int databaseSizeBeforeUpdate = asignacionCajaRepository.findAll().size();
        asignacionCaja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAsignacionCajaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the AsignacionCaja in the database
        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAsignacionCaja() throws Exception {
        int databaseSizeBeforeUpdate = asignacionCajaRepository.findAll().size();
        asignacionCaja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAsignacionCajaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asignacionCaja)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AsignacionCaja in the database
        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAsignacionCajaWithPatch() throws Exception {
        // Initialize the database
        asignacionCajaRepository.save(asignacionCaja);

        int databaseSizeBeforeUpdate = asignacionCajaRepository.findAll().size();

        // Update the asignacionCaja using partial update
        AsignacionCaja partialUpdatedAsignacionCaja = new AsignacionCaja();
        partialUpdatedAsignacionCaja.setId(asignacionCaja.getId());

        partialUpdatedAsignacionCaja
            .codAsignacion(UPDATED_COD_ASIGNACION)
            .montoMaximoSoles(UPDATED_MONTO_MAXIMO_SOLES)
            .mntoInicialDolares(UPDATED_MNTO_INICIAL_DOLARES)
            .montoMaximoDolares(UPDATED_MONTO_MAXIMO_DOLARES)
            .diferenciaDolares(UPDATED_DIFERENCIA_DOLARES)
            .observacionApertura(UPDATED_OBSERVACION_APERTURA)
            .fecAsignacion(UPDATED_FEC_ASIGNACION)
            .estado(UPDATED_ESTADO)
            .ipCrea(UPDATED_IP_CREA)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restAsignacionCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAsignacionCaja.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAsignacionCaja))
            )
            .andExpect(status().isOk());

        // Validate the AsignacionCaja in the database
        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeUpdate);
        AsignacionCaja testAsignacionCaja = asignacionCajaList.get(asignacionCajaList.size() - 1);
        assertThat(testAsignacionCaja.getCodAsignacion()).isEqualTo(UPDATED_COD_ASIGNACION);
        assertThat(testAsignacionCaja.getMntoInicialSoles()).isEqualTo(DEFAULT_MNTO_INICIAL_SOLES);
        assertThat(testAsignacionCaja.getMntoFinalSoles()).isEqualTo(DEFAULT_MNTO_FINAL_SOLES);
        assertThat(testAsignacionCaja.getMontoMaximoSoles()).isEqualTo(UPDATED_MONTO_MAXIMO_SOLES);
        assertThat(testAsignacionCaja.getDiferenciaSoles()).isEqualTo(DEFAULT_DIFERENCIA_SOLES);
        assertThat(testAsignacionCaja.getMntoInicialDolares()).isEqualTo(UPDATED_MNTO_INICIAL_DOLARES);
        assertThat(testAsignacionCaja.getMntoFinalDolares()).isEqualTo(DEFAULT_MNTO_FINAL_DOLARES);
        assertThat(testAsignacionCaja.getMontoMaximoDolares()).isEqualTo(UPDATED_MONTO_MAXIMO_DOLARES);
        assertThat(testAsignacionCaja.getDiferenciaDolares()).isEqualTo(UPDATED_DIFERENCIA_DOLARES);
        assertThat(testAsignacionCaja.getObservacionApertura()).isEqualTo(UPDATED_OBSERVACION_APERTURA);
        assertThat(testAsignacionCaja.getObservacionCierre()).isEqualTo(DEFAULT_OBSERVACION_CIERRE);
        assertThat(testAsignacionCaja.getFecAsignacion()).isEqualTo(UPDATED_FEC_ASIGNACION);
        assertThat(testAsignacionCaja.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testAsignacionCaja.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testAsignacionCaja.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testAsignacionCaja.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testAsignacionCaja.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testAsignacionCaja.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testAsignacionCaja.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testAsignacionCaja.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testAsignacionCaja.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void fullUpdateAsignacionCajaWithPatch() throws Exception {
        // Initialize the database
        asignacionCajaRepository.save(asignacionCaja);

        int databaseSizeBeforeUpdate = asignacionCajaRepository.findAll().size();

        // Update the asignacionCaja using partial update
        AsignacionCaja partialUpdatedAsignacionCaja = new AsignacionCaja();
        partialUpdatedAsignacionCaja.setId(asignacionCaja.getId());

        partialUpdatedAsignacionCaja
            .codAsignacion(UPDATED_COD_ASIGNACION)
            .mntoInicialSoles(UPDATED_MNTO_INICIAL_SOLES)
            .mntoFinalSoles(UPDATED_MNTO_FINAL_SOLES)
            .montoMaximoSoles(UPDATED_MONTO_MAXIMO_SOLES)
            .diferenciaSoles(UPDATED_DIFERENCIA_SOLES)
            .mntoInicialDolares(UPDATED_MNTO_INICIAL_DOLARES)
            .mntoFinalDolares(UPDATED_MNTO_FINAL_DOLARES)
            .montoMaximoDolares(UPDATED_MONTO_MAXIMO_DOLARES)
            .diferenciaDolares(UPDATED_DIFERENCIA_DOLARES)
            .observacionApertura(UPDATED_OBSERVACION_APERTURA)
            .observacionCierre(UPDATED_OBSERVACION_CIERRE)
            .fecAsignacion(UPDATED_FEC_ASIGNACION)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restAsignacionCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAsignacionCaja.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAsignacionCaja))
            )
            .andExpect(status().isOk());

        // Validate the AsignacionCaja in the database
        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeUpdate);
        AsignacionCaja testAsignacionCaja = asignacionCajaList.get(asignacionCajaList.size() - 1);
        assertThat(testAsignacionCaja.getCodAsignacion()).isEqualTo(UPDATED_COD_ASIGNACION);
        assertThat(testAsignacionCaja.getMntoInicialSoles()).isEqualTo(UPDATED_MNTO_INICIAL_SOLES);
        assertThat(testAsignacionCaja.getMntoFinalSoles()).isEqualTo(UPDATED_MNTO_FINAL_SOLES);
        assertThat(testAsignacionCaja.getMontoMaximoSoles()).isEqualTo(UPDATED_MONTO_MAXIMO_SOLES);
        assertThat(testAsignacionCaja.getDiferenciaSoles()).isEqualTo(UPDATED_DIFERENCIA_SOLES);
        assertThat(testAsignacionCaja.getMntoInicialDolares()).isEqualTo(UPDATED_MNTO_INICIAL_DOLARES);
        assertThat(testAsignacionCaja.getMntoFinalDolares()).isEqualTo(UPDATED_MNTO_FINAL_DOLARES);
        assertThat(testAsignacionCaja.getMontoMaximoDolares()).isEqualTo(UPDATED_MONTO_MAXIMO_DOLARES);
        assertThat(testAsignacionCaja.getDiferenciaDolares()).isEqualTo(UPDATED_DIFERENCIA_DOLARES);
        assertThat(testAsignacionCaja.getObservacionApertura()).isEqualTo(UPDATED_OBSERVACION_APERTURA);
        assertThat(testAsignacionCaja.getObservacionCierre()).isEqualTo(UPDATED_OBSERVACION_CIERRE);
        assertThat(testAsignacionCaja.getFecAsignacion()).isEqualTo(UPDATED_FEC_ASIGNACION);
        assertThat(testAsignacionCaja.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testAsignacionCaja.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testAsignacionCaja.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testAsignacionCaja.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testAsignacionCaja.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testAsignacionCaja.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testAsignacionCaja.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testAsignacionCaja.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testAsignacionCaja.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingAsignacionCaja() throws Exception {
        int databaseSizeBeforeUpdate = asignacionCajaRepository.findAll().size();
        asignacionCaja.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAsignacionCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, asignacionCaja.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the AsignacionCaja in the database
        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAsignacionCaja() throws Exception {
        int databaseSizeBeforeUpdate = asignacionCajaRepository.findAll().size();
        asignacionCaja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAsignacionCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the AsignacionCaja in the database
        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAsignacionCaja() throws Exception {
        int databaseSizeBeforeUpdate = asignacionCajaRepository.findAll().size();
        asignacionCaja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAsignacionCajaMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(asignacionCaja))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AsignacionCaja in the database
        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAsignacionCaja() throws Exception {
        // Initialize the database
        asignacionCajaRepository.save(asignacionCaja);

        int databaseSizeBeforeDelete = asignacionCajaRepository.findAll().size();

        // Delete the asignacionCaja
        restAsignacionCajaMockMvc
            .perform(delete(ENTITY_API_URL_ID, asignacionCaja.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AsignacionCaja> asignacionCajaList = asignacionCajaRepository.findAll();
        assertThat(asignacionCajaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
