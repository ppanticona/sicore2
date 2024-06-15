package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.Clientes;
import com.ppanticona.repository.ClientesRepository;
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
 * Integration tests for the {@link ClientesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientesResourceIT {

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

    private static final String ENTITY_API_URL = "/api/clientes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private MockMvc restClientesMockMvc;

    private Clientes clientes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Clientes createEntity() {
        Clientes clientes = new Clientes()
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
        return clientes;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Clientes createUpdatedEntity() {
        Clientes clientes = new Clientes()
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
        return clientes;
    }

    @BeforeEach
    public void initTest() {
        clientesRepository.deleteAll();
        clientes = createEntity();
    }

    @Test
    void createClientes() throws Exception {
        int databaseSizeBeforeCreate = clientesRepository.findAll().size();
        // Create the Clientes
        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isCreated());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeCreate + 1);
        Clientes testClientes = clientesList.get(clientesList.size() - 1);
        assertThat(testClientes.getTipDoc()).isEqualTo(DEFAULT_TIP_DOC);
        assertThat(testClientes.getNroDoc()).isEqualTo(DEFAULT_NRO_DOC);
        assertThat(testClientes.getNombres()).isEqualTo(DEFAULT_NOMBRES);
        assertThat(testClientes.getApellidos()).isEqualTo(DEFAULT_APELLIDOS);
        assertThat(testClientes.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testClientes.getTel1()).isEqualTo(DEFAULT_TEL_1);
        assertThat(testClientes.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testClientes.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testClientes.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testClientes.getRefDirecc()).isEqualTo(DEFAULT_REF_DIRECC);
        assertThat(testClientes.getDistrito()).isEqualTo(DEFAULT_DISTRITO);
        assertThat(testClientes.getFecNac()).isEqualTo(DEFAULT_FEC_NAC);
        assertThat(testClientes.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testClientes.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testClientes.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testClientes.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testClientes.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testClientes.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testClientes.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testClientes.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testClientes.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testClientes.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createClientesWithExistingId() throws Exception {
        // Create the Clientes with an existing ID
        clientes.setId("existing_id");

        int databaseSizeBeforeCreate = clientesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isBadRequest());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTipDocIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientesRepository.findAll().size();
        // set the field null
        clientes.setTipDoc(null);

        // Create the Clientes, which fails.

        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isBadRequest());

        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNroDocIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientesRepository.findAll().size();
        // set the field null
        clientes.setNroDoc(null);

        // Create the Clientes, which fails.

        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isBadRequest());

        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNombresIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientesRepository.findAll().size();
        // set the field null
        clientes.setNombres(null);

        // Create the Clientes, which fails.

        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isBadRequest());

        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkApellidosIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientesRepository.findAll().size();
        // set the field null
        clientes.setApellidos(null);

        // Create the Clientes, which fails.

        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isBadRequest());

        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecNacIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientesRepository.findAll().size();
        // set the field null
        clientes.setFecNac(null);

        // Create the Clientes, which fails.

        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isBadRequest());

        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientesRepository.findAll().size();
        // set the field null
        clientes.setEstado(null);

        // Create the Clientes, which fails.

        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isBadRequest());

        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientesRepository.findAll().size();
        // set the field null
        clientes.setVersion(null);

        // Create the Clientes, which fails.

        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isBadRequest());

        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientesRepository.findAll().size();
        // set the field null
        clientes.setIndDel(null);

        // Create the Clientes, which fails.

        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isBadRequest());

        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientesRepository.findAll().size();
        // set the field null
        clientes.setFecCrea(null);

        // Create the Clientes, which fails.

        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isBadRequest());

        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientesRepository.findAll().size();
        // set the field null
        clientes.setUsuCrea(null);

        // Create the Clientes, which fails.

        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isBadRequest());

        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientesRepository.findAll().size();
        // set the field null
        clientes.setIpCrea(null);

        // Create the Clientes, which fails.

        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isBadRequest());

        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllClientes() throws Exception {
        // Initialize the database
        clientesRepository.save(clientes);

        // Get all the clientesList
        restClientesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientes.getId())))
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
    void getClientes() throws Exception {
        // Initialize the database
        clientesRepository.save(clientes);

        // Get the clientes
        restClientesMockMvc
            .perform(get(ENTITY_API_URL_ID, clientes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clientes.getId()))
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
    void getNonExistingClientes() throws Exception {
        // Get the clientes
        restClientesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingClientes() throws Exception {
        // Initialize the database
        clientesRepository.save(clientes);

        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();

        // Update the clientes
        Clientes updatedClientes = clientesRepository.findById(clientes.getId()).get();
        updatedClientes
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

        restClientesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedClientes.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedClientes))
            )
            .andExpect(status().isOk());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
        Clientes testClientes = clientesList.get(clientesList.size() - 1);
        assertThat(testClientes.getTipDoc()).isEqualTo(UPDATED_TIP_DOC);
        assertThat(testClientes.getNroDoc()).isEqualTo(UPDATED_NRO_DOC);
        assertThat(testClientes.getNombres()).isEqualTo(UPDATED_NOMBRES);
        assertThat(testClientes.getApellidos()).isEqualTo(UPDATED_APELLIDOS);
        assertThat(testClientes.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testClientes.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testClientes.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testClientes.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testClientes.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testClientes.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testClientes.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testClientes.getFecNac()).isEqualTo(UPDATED_FEC_NAC);
        assertThat(testClientes.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testClientes.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testClientes.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testClientes.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testClientes.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testClientes.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testClientes.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testClientes.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testClientes.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testClientes.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingClientes() throws Exception {
        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();
        clientes.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientes.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchClientes() throws Exception {
        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();
        clientes.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamClientes() throws Exception {
        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();
        clientes.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateClientesWithPatch() throws Exception {
        // Initialize the database
        clientesRepository.save(clientes);

        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();

        // Update the clientes using partial update
        Clientes partialUpdatedClientes = new Clientes();
        partialUpdatedClientes.setId(clientes.getId());

        partialUpdatedClientes
            .nroDoc(UPDATED_NRO_DOC)
            .nombres(UPDATED_NOMBRES)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDirecc(UPDATED_REF_DIRECC)
            .fecNac(UPDATED_FEC_NAC)
            .userId(UPDATED_USER_ID)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .fecCrea(UPDATED_FEC_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .ipModif(UPDATED_IP_MODIF);

        restClientesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientes.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientes))
            )
            .andExpect(status().isOk());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
        Clientes testClientes = clientesList.get(clientesList.size() - 1);
        assertThat(testClientes.getTipDoc()).isEqualTo(DEFAULT_TIP_DOC);
        assertThat(testClientes.getNroDoc()).isEqualTo(UPDATED_NRO_DOC);
        assertThat(testClientes.getNombres()).isEqualTo(UPDATED_NOMBRES);
        assertThat(testClientes.getApellidos()).isEqualTo(DEFAULT_APELLIDOS);
        assertThat(testClientes.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testClientes.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testClientes.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testClientes.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testClientes.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testClientes.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testClientes.getDistrito()).isEqualTo(DEFAULT_DISTRITO);
        assertThat(testClientes.getFecNac()).isEqualTo(UPDATED_FEC_NAC);
        assertThat(testClientes.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testClientes.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testClientes.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testClientes.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testClientes.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testClientes.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testClientes.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testClientes.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testClientes.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testClientes.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void fullUpdateClientesWithPatch() throws Exception {
        // Initialize the database
        clientesRepository.save(clientes);

        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();

        // Update the clientes using partial update
        Clientes partialUpdatedClientes = new Clientes();
        partialUpdatedClientes.setId(clientes.getId());

        partialUpdatedClientes
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

        restClientesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientes.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientes))
            )
            .andExpect(status().isOk());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
        Clientes testClientes = clientesList.get(clientesList.size() - 1);
        assertThat(testClientes.getTipDoc()).isEqualTo(UPDATED_TIP_DOC);
        assertThat(testClientes.getNroDoc()).isEqualTo(UPDATED_NRO_DOC);
        assertThat(testClientes.getNombres()).isEqualTo(UPDATED_NOMBRES);
        assertThat(testClientes.getApellidos()).isEqualTo(UPDATED_APELLIDOS);
        assertThat(testClientes.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testClientes.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testClientes.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testClientes.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testClientes.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testClientes.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testClientes.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testClientes.getFecNac()).isEqualTo(UPDATED_FEC_NAC);
        assertThat(testClientes.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testClientes.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testClientes.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testClientes.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testClientes.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testClientes.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testClientes.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testClientes.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testClientes.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testClientes.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingClientes() throws Exception {
        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();
        clientes.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, clientes.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchClientes() throws Exception {
        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();
        clientes.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamClientes() throws Exception {
        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();
        clientes.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteClientes() throws Exception {
        // Initialize the database
        clientesRepository.save(clientes);

        int databaseSizeBeforeDelete = clientesRepository.findAll().size();

        // Delete the clientes
        restClientesMockMvc
            .perform(delete(ENTITY_API_URL_ID, clientes.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
