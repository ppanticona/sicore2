package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.Proveedores;
import com.ppanticona.repository.ProveedoresRepository;
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
 * Integration tests for the {@link ProveedoresResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProveedoresResourceIT {

    private static final String DEFAULT_TIP_DOC = "AAAAAAAAAA";
    private static final String UPDATED_TIP_DOC = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_DOC = "AAAAAAAAAA";
    private static final String UPDATED_NRO_DOC = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRES = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRES = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDOS = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDOS = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_1 = "AAAAAAAAAA";
    private static final String UPDATED_TEL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_2 = "AAAAAAAAAA";
    private static final String UPDATED_TEL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CORREO = "AAAAAAAAAA";
    private static final String UPDATED_CORREO = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_REF_DIRECC = "AAAAAAAAAA";
    private static final String UPDATED_REF_DIRECC = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRITO = "AAAAAAAAAA";
    private static final String UPDATED_DISTRITO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_NAC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_NAC = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/proveedores";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ProveedoresRepository proveedoresRepository;

    @Autowired
    private MockMvc restProveedoresMockMvc;

    private Proveedores proveedores;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Proveedores createEntity() {
        Proveedores proveedores = new Proveedores()
            .tipDoc(DEFAULT_TIP_DOC)
            .nroDoc(DEFAULT_NRO_DOC)
            .nombres(DEFAULT_NOMBRES)
            .apellidos(DEFAULT_APELLIDOS)
            .categoria(DEFAULT_CATEGORIA)
            .tel1(DEFAULT_TEL_1)
            .tel2(DEFAULT_TEL_2)
            .correo(DEFAULT_CORREO)
            .direccion(DEFAULT_DIRECCION)
            .refDirecc(DEFAULT_REF_DIRECC)
            .distrito(DEFAULT_DISTRITO)
            .fecNac(DEFAULT_FEC_NAC)
            .userId(DEFAULT_USER_ID)
            .estado(DEFAULT_ESTADO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return proveedores;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Proveedores createUpdatedEntity() {
        Proveedores proveedores = new Proveedores()
            .tipDoc(UPDATED_TIP_DOC)
            .nroDoc(UPDATED_NRO_DOC)
            .nombres(UPDATED_NOMBRES)
            .apellidos(UPDATED_APELLIDOS)
            .categoria(UPDATED_CATEGORIA)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDirecc(UPDATED_REF_DIRECC)
            .distrito(UPDATED_DISTRITO)
            .fecNac(UPDATED_FEC_NAC)
            .userId(UPDATED_USER_ID)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return proveedores;
    }

    @BeforeEach
    public void initTest() {
        proveedoresRepository.deleteAll();
        proveedores = createEntity();
    }

    @Test
    void createProveedores() throws Exception {
        int databaseSizeBeforeCreate = proveedoresRepository.findAll().size();
        // Create the Proveedores
        restProveedoresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isCreated());

        // Validate the Proveedores in the database
        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeCreate + 1);
        Proveedores testProveedores = proveedoresList.get(proveedoresList.size() - 1);
        assertThat(testProveedores.getTipDoc()).isEqualTo(DEFAULT_TIP_DOC);
        assertThat(testProveedores.getNroDoc()).isEqualTo(DEFAULT_NRO_DOC);
        assertThat(testProveedores.getNombres()).isEqualTo(DEFAULT_NOMBRES);
        assertThat(testProveedores.getApellidos()).isEqualTo(DEFAULT_APELLIDOS);
        assertThat(testProveedores.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testProveedores.getTel1()).isEqualTo(DEFAULT_TEL_1);
        assertThat(testProveedores.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testProveedores.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testProveedores.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testProveedores.getRefDirecc()).isEqualTo(DEFAULT_REF_DIRECC);
        assertThat(testProveedores.getDistrito()).isEqualTo(DEFAULT_DISTRITO);
        assertThat(testProveedores.getFecNac()).isEqualTo(DEFAULT_FEC_NAC);
        assertThat(testProveedores.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testProveedores.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testProveedores.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testProveedores.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testProveedores.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testProveedores.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testProveedores.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testProveedores.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testProveedores.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testProveedores.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createProveedoresWithExistingId() throws Exception {
        // Create the Proveedores with an existing ID
        proveedores.setId("existing_id");

        int databaseSizeBeforeCreate = proveedoresRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProveedoresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isBadRequest());

        // Validate the Proveedores in the database
        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTipDocIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedoresRepository.findAll().size();
        // set the field null
        proveedores.setTipDoc(null);

        // Create the Proveedores, which fails.

        restProveedoresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isBadRequest());

        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNroDocIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedoresRepository.findAll().size();
        // set the field null
        proveedores.setNroDoc(null);

        // Create the Proveedores, which fails.

        restProveedoresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isBadRequest());

        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNombresIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedoresRepository.findAll().size();
        // set the field null
        proveedores.setNombres(null);

        // Create the Proveedores, which fails.

        restProveedoresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isBadRequest());

        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkApellidosIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedoresRepository.findAll().size();
        // set the field null
        proveedores.setApellidos(null);

        // Create the Proveedores, which fails.

        restProveedoresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isBadRequest());

        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecNacIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedoresRepository.findAll().size();
        // set the field null
        proveedores.setFecNac(null);

        // Create the Proveedores, which fails.

        restProveedoresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isBadRequest());

        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedoresRepository.findAll().size();
        // set the field null
        proveedores.setEstado(null);

        // Create the Proveedores, which fails.

        restProveedoresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isBadRequest());

        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedoresRepository.findAll().size();
        // set the field null
        proveedores.setVersion(null);

        // Create the Proveedores, which fails.

        restProveedoresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isBadRequest());

        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedoresRepository.findAll().size();
        // set the field null
        proveedores.setIndDel(null);

        // Create the Proveedores, which fails.

        restProveedoresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isBadRequest());

        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedoresRepository.findAll().size();
        // set the field null
        proveedores.setFecCrea(null);

        // Create the Proveedores, which fails.

        restProveedoresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isBadRequest());

        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedoresRepository.findAll().size();
        // set the field null
        proveedores.setUsuCrea(null);

        // Create the Proveedores, which fails.

        restProveedoresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isBadRequest());

        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedoresRepository.findAll().size();
        // set the field null
        proveedores.setIpCrea(null);

        // Create the Proveedores, which fails.

        restProveedoresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isBadRequest());

        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllProveedores() throws Exception {
        // Initialize the database
        proveedoresRepository.save(proveedores);

        // Get all the proveedoresList
        restProveedoresMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proveedores.getId())))
            .andExpect(jsonPath("$.[*].tipDoc").value(hasItem(DEFAULT_TIP_DOC)))
            .andExpect(jsonPath("$.[*].nroDoc").value(hasItem(DEFAULT_NRO_DOC)))
            .andExpect(jsonPath("$.[*].nombres").value(hasItem(DEFAULT_NOMBRES)))
            .andExpect(jsonPath("$.[*].apellidos").value(hasItem(DEFAULT_APELLIDOS)))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA)))
            .andExpect(jsonPath("$.[*].tel1").value(hasItem(DEFAULT_TEL_1)))
            .andExpect(jsonPath("$.[*].tel2").value(hasItem(DEFAULT_TEL_2)))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].refDirecc").value(hasItem(DEFAULT_REF_DIRECC)))
            .andExpect(jsonPath("$.[*].distrito").value(hasItem(DEFAULT_DISTRITO)))
            .andExpect(jsonPath("$.[*].fecNac").value(hasItem(sameInstant(DEFAULT_FEC_NAC))))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
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
    void getProveedores() throws Exception {
        // Initialize the database
        proveedoresRepository.save(proveedores);

        // Get the proveedores
        restProveedoresMockMvc
            .perform(get(ENTITY_API_URL_ID, proveedores.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(proveedores.getId()))
            .andExpect(jsonPath("$.tipDoc").value(DEFAULT_TIP_DOC))
            .andExpect(jsonPath("$.nroDoc").value(DEFAULT_NRO_DOC))
            .andExpect(jsonPath("$.nombres").value(DEFAULT_NOMBRES))
            .andExpect(jsonPath("$.apellidos").value(DEFAULT_APELLIDOS))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA))
            .andExpect(jsonPath("$.tel1").value(DEFAULT_TEL_1))
            .andExpect(jsonPath("$.tel2").value(DEFAULT_TEL_2))
            .andExpect(jsonPath("$.correo").value(DEFAULT_CORREO))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.refDirecc").value(DEFAULT_REF_DIRECC))
            .andExpect(jsonPath("$.distrito").value(DEFAULT_DISTRITO))
            .andExpect(jsonPath("$.fecNac").value(sameInstant(DEFAULT_FEC_NAC)))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
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
    void getNonExistingProveedores() throws Exception {
        // Get the proveedores
        restProveedoresMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingProveedores() throws Exception {
        // Initialize the database
        proveedoresRepository.save(proveedores);

        int databaseSizeBeforeUpdate = proveedoresRepository.findAll().size();

        // Update the proveedores
        Proveedores updatedProveedores = proveedoresRepository.findById(proveedores.getId()).get();
        updatedProveedores
            .tipDoc(UPDATED_TIP_DOC)
            .nroDoc(UPDATED_NRO_DOC)
            .nombres(UPDATED_NOMBRES)
            .apellidos(UPDATED_APELLIDOS)
            .categoria(UPDATED_CATEGORIA)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDirecc(UPDATED_REF_DIRECC)
            .distrito(UPDATED_DISTRITO)
            .fecNac(UPDATED_FEC_NAC)
            .userId(UPDATED_USER_ID)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restProveedoresMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProveedores.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedProveedores))
            )
            .andExpect(status().isOk());

        // Validate the Proveedores in the database
        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeUpdate);
        Proveedores testProveedores = proveedoresList.get(proveedoresList.size() - 1);
        assertThat(testProveedores.getTipDoc()).isEqualTo(UPDATED_TIP_DOC);
        assertThat(testProveedores.getNroDoc()).isEqualTo(UPDATED_NRO_DOC);
        assertThat(testProveedores.getNombres()).isEqualTo(UPDATED_NOMBRES);
        assertThat(testProveedores.getApellidos()).isEqualTo(UPDATED_APELLIDOS);
        assertThat(testProveedores.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testProveedores.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testProveedores.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testProveedores.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testProveedores.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testProveedores.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testProveedores.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testProveedores.getFecNac()).isEqualTo(UPDATED_FEC_NAC);
        assertThat(testProveedores.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testProveedores.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testProveedores.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testProveedores.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testProveedores.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testProveedores.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testProveedores.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testProveedores.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testProveedores.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testProveedores.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingProveedores() throws Exception {
        int databaseSizeBeforeUpdate = proveedoresRepository.findAll().size();
        proveedores.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProveedoresMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proveedores.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proveedores))
            )
            .andExpect(status().isBadRequest());

        // Validate the Proveedores in the database
        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchProveedores() throws Exception {
        int databaseSizeBeforeUpdate = proveedoresRepository.findAll().size();
        proveedores.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProveedoresMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proveedores))
            )
            .andExpect(status().isBadRequest());

        // Validate the Proveedores in the database
        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamProveedores() throws Exception {
        int databaseSizeBeforeUpdate = proveedoresRepository.findAll().size();
        proveedores.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProveedoresMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedores)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Proveedores in the database
        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateProveedoresWithPatch() throws Exception {
        // Initialize the database
        proveedoresRepository.save(proveedores);

        int databaseSizeBeforeUpdate = proveedoresRepository.findAll().size();

        // Update the proveedores using partial update
        Proveedores partialUpdatedProveedores = new Proveedores();
        partialUpdatedProveedores.setId(proveedores.getId());

        partialUpdatedProveedores
            .tipDoc(UPDATED_TIP_DOC)
            .categoria(UPDATED_CATEGORIA)
            .tel1(UPDATED_TEL_1)
            .direccion(UPDATED_DIRECCION)
            .refDirecc(UPDATED_REF_DIRECC)
            .distrito(UPDATED_DISTRITO)
            .userId(UPDATED_USER_ID)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF);

        restProveedoresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProveedores.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProveedores))
            )
            .andExpect(status().isOk());

        // Validate the Proveedores in the database
        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeUpdate);
        Proveedores testProveedores = proveedoresList.get(proveedoresList.size() - 1);
        assertThat(testProveedores.getTipDoc()).isEqualTo(UPDATED_TIP_DOC);
        assertThat(testProveedores.getNroDoc()).isEqualTo(DEFAULT_NRO_DOC);
        assertThat(testProveedores.getNombres()).isEqualTo(DEFAULT_NOMBRES);
        assertThat(testProveedores.getApellidos()).isEqualTo(DEFAULT_APELLIDOS);
        assertThat(testProveedores.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testProveedores.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testProveedores.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testProveedores.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testProveedores.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testProveedores.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testProveedores.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testProveedores.getFecNac()).isEqualTo(DEFAULT_FEC_NAC);
        assertThat(testProveedores.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testProveedores.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testProveedores.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testProveedores.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testProveedores.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testProveedores.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testProveedores.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testProveedores.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testProveedores.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testProveedores.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void fullUpdateProveedoresWithPatch() throws Exception {
        // Initialize the database
        proveedoresRepository.save(proveedores);

        int databaseSizeBeforeUpdate = proveedoresRepository.findAll().size();

        // Update the proveedores using partial update
        Proveedores partialUpdatedProveedores = new Proveedores();
        partialUpdatedProveedores.setId(proveedores.getId());

        partialUpdatedProveedores
            .tipDoc(UPDATED_TIP_DOC)
            .nroDoc(UPDATED_NRO_DOC)
            .nombres(UPDATED_NOMBRES)
            .apellidos(UPDATED_APELLIDOS)
            .categoria(UPDATED_CATEGORIA)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDirecc(UPDATED_REF_DIRECC)
            .distrito(UPDATED_DISTRITO)
            .fecNac(UPDATED_FEC_NAC)
            .userId(UPDATED_USER_ID)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restProveedoresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProveedores.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProveedores))
            )
            .andExpect(status().isOk());

        // Validate the Proveedores in the database
        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeUpdate);
        Proveedores testProveedores = proveedoresList.get(proveedoresList.size() - 1);
        assertThat(testProveedores.getTipDoc()).isEqualTo(UPDATED_TIP_DOC);
        assertThat(testProveedores.getNroDoc()).isEqualTo(UPDATED_NRO_DOC);
        assertThat(testProveedores.getNombres()).isEqualTo(UPDATED_NOMBRES);
        assertThat(testProveedores.getApellidos()).isEqualTo(UPDATED_APELLIDOS);
        assertThat(testProveedores.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testProveedores.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testProveedores.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testProveedores.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testProveedores.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testProveedores.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testProveedores.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testProveedores.getFecNac()).isEqualTo(UPDATED_FEC_NAC);
        assertThat(testProveedores.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testProveedores.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testProveedores.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testProveedores.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testProveedores.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testProveedores.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testProveedores.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testProveedores.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testProveedores.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testProveedores.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingProveedores() throws Exception {
        int databaseSizeBeforeUpdate = proveedoresRepository.findAll().size();
        proveedores.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProveedoresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, proveedores.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proveedores))
            )
            .andExpect(status().isBadRequest());

        // Validate the Proveedores in the database
        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchProveedores() throws Exception {
        int databaseSizeBeforeUpdate = proveedoresRepository.findAll().size();
        proveedores.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProveedoresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proveedores))
            )
            .andExpect(status().isBadRequest());

        // Validate the Proveedores in the database
        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamProveedores() throws Exception {
        int databaseSizeBeforeUpdate = proveedoresRepository.findAll().size();
        proveedores.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProveedoresMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(proveedores))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Proveedores in the database
        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteProveedores() throws Exception {
        // Initialize the database
        proveedoresRepository.save(proveedores);

        int databaseSizeBeforeDelete = proveedoresRepository.findAll().size();

        // Delete the proveedores
        restProveedoresMockMvc
            .perform(delete(ENTITY_API_URL_ID, proveedores.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Proveedores> proveedoresList = proveedoresRepository.findAll();
        assertThat(proveedoresList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
