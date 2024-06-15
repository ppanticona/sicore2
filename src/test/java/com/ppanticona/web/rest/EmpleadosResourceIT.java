package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.Empleados;
import com.ppanticona.repository.EmpleadosRepository;
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
 * Integration tests for the {@link EmpleadosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EmpleadosResourceIT {

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

    private static final ZonedDateTime DEFAULT_FEC_INGRESO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_INGRESO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FEC_NAC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_NAC = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_IMAGEN = "AAAAAAAAAA";
    private static final String UPDATED_IMAGEN = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/empleados";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private EmpleadosRepository empleadosRepository;

    @Autowired
    private MockMvc restEmpleadosMockMvc;

    private Empleados empleados;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Empleados createEntity() {
        Empleados empleados = new Empleados()
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
            .fecIngreso(DEFAULT_FEC_INGRESO)
            .fecNac(DEFAULT_FEC_NAC)
            .imagen(DEFAULT_IMAGEN)
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
        return empleados;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Empleados createUpdatedEntity() {
        Empleados empleados = new Empleados()
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
            .fecIngreso(UPDATED_FEC_INGRESO)
            .fecNac(UPDATED_FEC_NAC)
            .imagen(UPDATED_IMAGEN)
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
        return empleados;
    }

    @BeforeEach
    public void initTest() {
        empleadosRepository.deleteAll();
        empleados = createEntity();
    }

    @Test
    void createEmpleados() throws Exception {
        int databaseSizeBeforeCreate = empleadosRepository.findAll().size();
        // Create the Empleados
        restEmpleadosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isCreated());

        // Validate the Empleados in the database
        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeCreate + 1);
        Empleados testEmpleados = empleadosList.get(empleadosList.size() - 1);
        assertThat(testEmpleados.getTipDoc()).isEqualTo(DEFAULT_TIP_DOC);
        assertThat(testEmpleados.getNroDoc()).isEqualTo(DEFAULT_NRO_DOC);
        assertThat(testEmpleados.getNombres()).isEqualTo(DEFAULT_NOMBRES);
        assertThat(testEmpleados.getApellidos()).isEqualTo(DEFAULT_APELLIDOS);
        assertThat(testEmpleados.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testEmpleados.getTel1()).isEqualTo(DEFAULT_TEL_1);
        assertThat(testEmpleados.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testEmpleados.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testEmpleados.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testEmpleados.getRefDirecc()).isEqualTo(DEFAULT_REF_DIRECC);
        assertThat(testEmpleados.getDistrito()).isEqualTo(DEFAULT_DISTRITO);
        assertThat(testEmpleados.getFecIngreso()).isEqualTo(DEFAULT_FEC_INGRESO);
        assertThat(testEmpleados.getFecNac()).isEqualTo(DEFAULT_FEC_NAC);
        assertThat(testEmpleados.getImagen()).isEqualTo(DEFAULT_IMAGEN);
        assertThat(testEmpleados.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testEmpleados.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testEmpleados.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testEmpleados.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testEmpleados.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testEmpleados.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testEmpleados.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testEmpleados.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testEmpleados.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testEmpleados.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createEmpleadosWithExistingId() throws Exception {
        // Create the Empleados with an existing ID
        empleados.setId("existing_id");

        int databaseSizeBeforeCreate = empleadosRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmpleadosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isBadRequest());

        // Validate the Empleados in the database
        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTipDocIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadosRepository.findAll().size();
        // set the field null
        empleados.setTipDoc(null);

        // Create the Empleados, which fails.

        restEmpleadosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isBadRequest());

        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNroDocIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadosRepository.findAll().size();
        // set the field null
        empleados.setNroDoc(null);

        // Create the Empleados, which fails.

        restEmpleadosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isBadRequest());

        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNombresIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadosRepository.findAll().size();
        // set the field null
        empleados.setNombres(null);

        // Create the Empleados, which fails.

        restEmpleadosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isBadRequest());

        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkApellidosIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadosRepository.findAll().size();
        // set the field null
        empleados.setApellidos(null);

        // Create the Empleados, which fails.

        restEmpleadosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isBadRequest());

        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecNacIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadosRepository.findAll().size();
        // set the field null
        empleados.setFecNac(null);

        // Create the Empleados, which fails.

        restEmpleadosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isBadRequest());

        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadosRepository.findAll().size();
        // set the field null
        empleados.setEstado(null);

        // Create the Empleados, which fails.

        restEmpleadosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isBadRequest());

        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadosRepository.findAll().size();
        // set the field null
        empleados.setVersion(null);

        // Create the Empleados, which fails.

        restEmpleadosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isBadRequest());

        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadosRepository.findAll().size();
        // set the field null
        empleados.setIndDel(null);

        // Create the Empleados, which fails.

        restEmpleadosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isBadRequest());

        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadosRepository.findAll().size();
        // set the field null
        empleados.setFecCrea(null);

        // Create the Empleados, which fails.

        restEmpleadosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isBadRequest());

        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadosRepository.findAll().size();
        // set the field null
        empleados.setUsuCrea(null);

        // Create the Empleados, which fails.

        restEmpleadosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isBadRequest());

        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadosRepository.findAll().size();
        // set the field null
        empleados.setIpCrea(null);

        // Create the Empleados, which fails.

        restEmpleadosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isBadRequest());

        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllEmpleados() throws Exception {
        // Initialize the database
        empleadosRepository.save(empleados);

        // Get all the empleadosList
        restEmpleadosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(empleados.getId())))
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
            .andExpect(jsonPath("$.[*].fecIngreso").value(hasItem(sameInstant(DEFAULT_FEC_INGRESO))))
            .andExpect(jsonPath("$.[*].fecNac").value(hasItem(sameInstant(DEFAULT_FEC_NAC))))
            .andExpect(jsonPath("$.[*].imagen").value(hasItem(DEFAULT_IMAGEN)))
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
    void getEmpleados() throws Exception {
        // Initialize the database
        empleadosRepository.save(empleados);

        // Get the empleados
        restEmpleadosMockMvc
            .perform(get(ENTITY_API_URL_ID, empleados.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(empleados.getId()))
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
            .andExpect(jsonPath("$.fecIngreso").value(sameInstant(DEFAULT_FEC_INGRESO)))
            .andExpect(jsonPath("$.fecNac").value(sameInstant(DEFAULT_FEC_NAC)))
            .andExpect(jsonPath("$.imagen").value(DEFAULT_IMAGEN))
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
    void getNonExistingEmpleados() throws Exception {
        // Get the empleados
        restEmpleadosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingEmpleados() throws Exception {
        // Initialize the database
        empleadosRepository.save(empleados);

        int databaseSizeBeforeUpdate = empleadosRepository.findAll().size();

        // Update the empleados
        Empleados updatedEmpleados = empleadosRepository.findById(empleados.getId()).get();
        updatedEmpleados
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
            .fecIngreso(UPDATED_FEC_INGRESO)
            .fecNac(UPDATED_FEC_NAC)
            .imagen(UPDATED_IMAGEN)
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

        restEmpleadosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEmpleados.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedEmpleados))
            )
            .andExpect(status().isOk());

        // Validate the Empleados in the database
        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeUpdate);
        Empleados testEmpleados = empleadosList.get(empleadosList.size() - 1);
        assertThat(testEmpleados.getTipDoc()).isEqualTo(UPDATED_TIP_DOC);
        assertThat(testEmpleados.getNroDoc()).isEqualTo(UPDATED_NRO_DOC);
        assertThat(testEmpleados.getNombres()).isEqualTo(UPDATED_NOMBRES);
        assertThat(testEmpleados.getApellidos()).isEqualTo(UPDATED_APELLIDOS);
        assertThat(testEmpleados.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testEmpleados.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testEmpleados.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testEmpleados.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testEmpleados.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testEmpleados.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testEmpleados.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testEmpleados.getFecIngreso()).isEqualTo(UPDATED_FEC_INGRESO);
        assertThat(testEmpleados.getFecNac()).isEqualTo(UPDATED_FEC_NAC);
        assertThat(testEmpleados.getImagen()).isEqualTo(UPDATED_IMAGEN);
        assertThat(testEmpleados.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testEmpleados.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testEmpleados.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testEmpleados.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testEmpleados.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testEmpleados.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testEmpleados.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testEmpleados.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testEmpleados.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testEmpleados.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingEmpleados() throws Exception {
        int databaseSizeBeforeUpdate = empleadosRepository.findAll().size();
        empleados.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmpleadosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, empleados.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(empleados))
            )
            .andExpect(status().isBadRequest());

        // Validate the Empleados in the database
        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchEmpleados() throws Exception {
        int databaseSizeBeforeUpdate = empleadosRepository.findAll().size();
        empleados.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleadosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(empleados))
            )
            .andExpect(status().isBadRequest());

        // Validate the Empleados in the database
        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamEmpleados() throws Exception {
        int databaseSizeBeforeUpdate = empleadosRepository.findAll().size();
        empleados.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleadosMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleados)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Empleados in the database
        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEmpleadosWithPatch() throws Exception {
        // Initialize the database
        empleadosRepository.save(empleados);

        int databaseSizeBeforeUpdate = empleadosRepository.findAll().size();

        // Update the empleados using partial update
        Empleados partialUpdatedEmpleados = new Empleados();
        partialUpdatedEmpleados.setId(empleados.getId());

        partialUpdatedEmpleados
            .nombres(UPDATED_NOMBRES)
            .tel1(UPDATED_TEL_1)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDirecc(UPDATED_REF_DIRECC)
            .fecIngreso(UPDATED_FEC_INGRESO)
            .fecNac(UPDATED_FEC_NAC)
            .imagen(UPDATED_IMAGEN)
            .estado(UPDATED_ESTADO)
            .indDel(UPDATED_IND_DEL)
            .fecModif(UPDATED_FEC_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restEmpleadosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmpleados.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmpleados))
            )
            .andExpect(status().isOk());

        // Validate the Empleados in the database
        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeUpdate);
        Empleados testEmpleados = empleadosList.get(empleadosList.size() - 1);
        assertThat(testEmpleados.getTipDoc()).isEqualTo(DEFAULT_TIP_DOC);
        assertThat(testEmpleados.getNroDoc()).isEqualTo(DEFAULT_NRO_DOC);
        assertThat(testEmpleados.getNombres()).isEqualTo(UPDATED_NOMBRES);
        assertThat(testEmpleados.getApellidos()).isEqualTo(DEFAULT_APELLIDOS);
        assertThat(testEmpleados.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testEmpleados.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testEmpleados.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testEmpleados.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testEmpleados.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testEmpleados.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testEmpleados.getDistrito()).isEqualTo(DEFAULT_DISTRITO);
        assertThat(testEmpleados.getFecIngreso()).isEqualTo(UPDATED_FEC_INGRESO);
        assertThat(testEmpleados.getFecNac()).isEqualTo(UPDATED_FEC_NAC);
        assertThat(testEmpleados.getImagen()).isEqualTo(UPDATED_IMAGEN);
        assertThat(testEmpleados.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testEmpleados.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testEmpleados.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testEmpleados.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testEmpleados.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testEmpleados.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testEmpleados.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testEmpleados.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testEmpleados.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testEmpleados.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void fullUpdateEmpleadosWithPatch() throws Exception {
        // Initialize the database
        empleadosRepository.save(empleados);

        int databaseSizeBeforeUpdate = empleadosRepository.findAll().size();

        // Update the empleados using partial update
        Empleados partialUpdatedEmpleados = new Empleados();
        partialUpdatedEmpleados.setId(empleados.getId());

        partialUpdatedEmpleados
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
            .fecIngreso(UPDATED_FEC_INGRESO)
            .fecNac(UPDATED_FEC_NAC)
            .imagen(UPDATED_IMAGEN)
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

        restEmpleadosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmpleados.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmpleados))
            )
            .andExpect(status().isOk());

        // Validate the Empleados in the database
        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeUpdate);
        Empleados testEmpleados = empleadosList.get(empleadosList.size() - 1);
        assertThat(testEmpleados.getTipDoc()).isEqualTo(UPDATED_TIP_DOC);
        assertThat(testEmpleados.getNroDoc()).isEqualTo(UPDATED_NRO_DOC);
        assertThat(testEmpleados.getNombres()).isEqualTo(UPDATED_NOMBRES);
        assertThat(testEmpleados.getApellidos()).isEqualTo(UPDATED_APELLIDOS);
        assertThat(testEmpleados.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testEmpleados.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testEmpleados.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testEmpleados.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testEmpleados.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testEmpleados.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testEmpleados.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testEmpleados.getFecIngreso()).isEqualTo(UPDATED_FEC_INGRESO);
        assertThat(testEmpleados.getFecNac()).isEqualTo(UPDATED_FEC_NAC);
        assertThat(testEmpleados.getImagen()).isEqualTo(UPDATED_IMAGEN);
        assertThat(testEmpleados.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testEmpleados.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testEmpleados.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testEmpleados.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testEmpleados.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testEmpleados.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testEmpleados.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testEmpleados.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testEmpleados.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testEmpleados.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingEmpleados() throws Exception {
        int databaseSizeBeforeUpdate = empleadosRepository.findAll().size();
        empleados.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmpleadosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, empleados.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(empleados))
            )
            .andExpect(status().isBadRequest());

        // Validate the Empleados in the database
        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchEmpleados() throws Exception {
        int databaseSizeBeforeUpdate = empleadosRepository.findAll().size();
        empleados.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleadosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(empleados))
            )
            .andExpect(status().isBadRequest());

        // Validate the Empleados in the database
        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamEmpleados() throws Exception {
        int databaseSizeBeforeUpdate = empleadosRepository.findAll().size();
        empleados.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleadosMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(empleados))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Empleados in the database
        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteEmpleados() throws Exception {
        // Initialize the database
        empleadosRepository.save(empleados);

        int databaseSizeBeforeDelete = empleadosRepository.findAll().size();

        // Delete the empleados
        restEmpleadosMockMvc
            .perform(delete(ENTITY_API_URL_ID, empleados.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Empleados> empleadosList = empleadosRepository.findAll();
        assertThat(empleadosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
