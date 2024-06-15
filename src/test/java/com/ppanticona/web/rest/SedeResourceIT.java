package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.Sede;
import com.ppanticona.repository.SedeRepository;
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
 * Integration tests for the {@link SedeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SedeResourceIT {

    private static final Integer DEFAULT_COD_SEDE = 1;
    private static final Integer UPDATED_COD_SEDE = 2;

    private static final Integer DEFAULT_DESCRIPCION = 1;
    private static final Integer UPDATED_DESCRIPCION = 2;

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

    private static final ZonedDateTime DEFAULT_FEC_APER = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_APER = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

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

    private static final String ENTITY_API_URL = "/api/sedes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private MockMvc restSedeMockMvc;

    private Sede sede;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sede createEntity() {
        Sede sede = new Sede()
            .codSede(DEFAULT_COD_SEDE)
            .descripcion(DEFAULT_DESCRIPCION)
            .categoria(DEFAULT_CATEGORIA)
            .tel1(DEFAULT_TEL_1)
            .tel2(DEFAULT_TEL_2)
            .correo(DEFAULT_CORREO)
            .direccion(DEFAULT_DIRECCION)
            .refDirecc(DEFAULT_REF_DIRECC)
            .distrito(DEFAULT_DISTRITO)
            .fecAper(DEFAULT_FEC_APER)
            .estado(DEFAULT_ESTADO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return sede;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sede createUpdatedEntity() {
        Sede sede = new Sede()
            .codSede(UPDATED_COD_SEDE)
            .descripcion(UPDATED_DESCRIPCION)
            .categoria(UPDATED_CATEGORIA)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDirecc(UPDATED_REF_DIRECC)
            .distrito(UPDATED_DISTRITO)
            .fecAper(UPDATED_FEC_APER)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return sede;
    }

    @BeforeEach
    public void initTest() {
        sedeRepository.deleteAll();
        sede = createEntity();
    }

    @Test
    void createSede() throws Exception {
        int databaseSizeBeforeCreate = sedeRepository.findAll().size();
        // Create the Sede
        restSedeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sede)))
            .andExpect(status().isCreated());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeCreate + 1);
        Sede testSede = sedeList.get(sedeList.size() - 1);
        assertThat(testSede.getCodSede()).isEqualTo(DEFAULT_COD_SEDE);
        assertThat(testSede.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testSede.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testSede.getTel1()).isEqualTo(DEFAULT_TEL_1);
        assertThat(testSede.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testSede.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testSede.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testSede.getRefDirecc()).isEqualTo(DEFAULT_REF_DIRECC);
        assertThat(testSede.getDistrito()).isEqualTo(DEFAULT_DISTRITO);
        assertThat(testSede.getFecAper()).isEqualTo(DEFAULT_FEC_APER);
        assertThat(testSede.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testSede.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testSede.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testSede.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testSede.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testSede.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testSede.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testSede.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testSede.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createSedeWithExistingId() throws Exception {
        // Create the Sede with an existing ID
        sede.setId("existing_id");

        int databaseSizeBeforeCreate = sedeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSedeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sede)))
            .andExpect(status().isBadRequest());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkCodSedeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setCodSede(null);

        // Create the Sede, which fails.

        restSedeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sede)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setDescripcion(null);

        // Create the Sede, which fails.

        restSedeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sede)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecAperIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setFecAper(null);

        // Create the Sede, which fails.

        restSedeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sede)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setEstado(null);

        // Create the Sede, which fails.

        restSedeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sede)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setVersion(null);

        // Create the Sede, which fails.

        restSedeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sede)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setIndDel(null);

        // Create the Sede, which fails.

        restSedeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sede)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setFecCrea(null);

        // Create the Sede, which fails.

        restSedeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sede)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setUsuCrea(null);

        // Create the Sede, which fails.

        restSedeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sede)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setIpCrea(null);

        // Create the Sede, which fails.

        restSedeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sede)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllSedes() throws Exception {
        // Initialize the database
        sedeRepository.save(sede);

        // Get all the sedeList
        restSedeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sede.getId())))
            .andExpect(jsonPath("$.[*].codSede").value(hasItem(DEFAULT_COD_SEDE)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA)))
            .andExpect(jsonPath("$.[*].tel1").value(hasItem(DEFAULT_TEL_1)))
            .andExpect(jsonPath("$.[*].tel2").value(hasItem(DEFAULT_TEL_2)))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].refDirecc").value(hasItem(DEFAULT_REF_DIRECC)))
            .andExpect(jsonPath("$.[*].distrito").value(hasItem(DEFAULT_DISTRITO)))
            .andExpect(jsonPath("$.[*].fecAper").value(hasItem(sameInstant(DEFAULT_FEC_APER))))
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
    void getSede() throws Exception {
        // Initialize the database
        sedeRepository.save(sede);

        // Get the sede
        restSedeMockMvc
            .perform(get(ENTITY_API_URL_ID, sede.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sede.getId()))
            .andExpect(jsonPath("$.codSede").value(DEFAULT_COD_SEDE))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA))
            .andExpect(jsonPath("$.tel1").value(DEFAULT_TEL_1))
            .andExpect(jsonPath("$.tel2").value(DEFAULT_TEL_2))
            .andExpect(jsonPath("$.correo").value(DEFAULT_CORREO))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.refDirecc").value(DEFAULT_REF_DIRECC))
            .andExpect(jsonPath("$.distrito").value(DEFAULT_DISTRITO))
            .andExpect(jsonPath("$.fecAper").value(sameInstant(DEFAULT_FEC_APER)))
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
    void getNonExistingSede() throws Exception {
        // Get the sede
        restSedeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingSede() throws Exception {
        // Initialize the database
        sedeRepository.save(sede);

        int databaseSizeBeforeUpdate = sedeRepository.findAll().size();

        // Update the sede
        Sede updatedSede = sedeRepository.findById(sede.getId()).get();
        updatedSede
            .codSede(UPDATED_COD_SEDE)
            .descripcion(UPDATED_DESCRIPCION)
            .categoria(UPDATED_CATEGORIA)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDirecc(UPDATED_REF_DIRECC)
            .distrito(UPDATED_DISTRITO)
            .fecAper(UPDATED_FEC_APER)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restSedeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSede.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSede))
            )
            .andExpect(status().isOk());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeUpdate);
        Sede testSede = sedeList.get(sedeList.size() - 1);
        assertThat(testSede.getCodSede()).isEqualTo(UPDATED_COD_SEDE);
        assertThat(testSede.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testSede.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testSede.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testSede.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testSede.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testSede.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testSede.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testSede.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testSede.getFecAper()).isEqualTo(UPDATED_FEC_APER);
        assertThat(testSede.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testSede.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testSede.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testSede.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testSede.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testSede.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testSede.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testSede.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testSede.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingSede() throws Exception {
        int databaseSizeBeforeUpdate = sedeRepository.findAll().size();
        sede.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSedeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sede.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sede))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSede() throws Exception {
        int databaseSizeBeforeUpdate = sedeRepository.findAll().size();
        sede.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSedeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sede))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSede() throws Exception {
        int databaseSizeBeforeUpdate = sedeRepository.findAll().size();
        sede.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSedeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sede)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSedeWithPatch() throws Exception {
        // Initialize the database
        sedeRepository.save(sede);

        int databaseSizeBeforeUpdate = sedeRepository.findAll().size();

        // Update the sede using partial update
        Sede partialUpdatedSede = new Sede();
        partialUpdatedSede.setId(sede.getId());

        partialUpdatedSede
            .codSede(UPDATED_COD_SEDE)
            .categoria(UPDATED_CATEGORIA)
            .tel1(UPDATED_TEL_1)
            .refDirecc(UPDATED_REF_DIRECC)
            .distrito(UPDATED_DISTRITO)
            .indDel(UPDATED_IND_DEL)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF);

        restSedeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSede.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSede))
            )
            .andExpect(status().isOk());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeUpdate);
        Sede testSede = sedeList.get(sedeList.size() - 1);
        assertThat(testSede.getCodSede()).isEqualTo(UPDATED_COD_SEDE);
        assertThat(testSede.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testSede.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testSede.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testSede.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testSede.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testSede.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testSede.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testSede.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testSede.getFecAper()).isEqualTo(DEFAULT_FEC_APER);
        assertThat(testSede.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testSede.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testSede.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testSede.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testSede.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testSede.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testSede.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testSede.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testSede.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void fullUpdateSedeWithPatch() throws Exception {
        // Initialize the database
        sedeRepository.save(sede);

        int databaseSizeBeforeUpdate = sedeRepository.findAll().size();

        // Update the sede using partial update
        Sede partialUpdatedSede = new Sede();
        partialUpdatedSede.setId(sede.getId());

        partialUpdatedSede
            .codSede(UPDATED_COD_SEDE)
            .descripcion(UPDATED_DESCRIPCION)
            .categoria(UPDATED_CATEGORIA)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDirecc(UPDATED_REF_DIRECC)
            .distrito(UPDATED_DISTRITO)
            .fecAper(UPDATED_FEC_APER)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restSedeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSede.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSede))
            )
            .andExpect(status().isOk());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeUpdate);
        Sede testSede = sedeList.get(sedeList.size() - 1);
        assertThat(testSede.getCodSede()).isEqualTo(UPDATED_COD_SEDE);
        assertThat(testSede.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testSede.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testSede.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testSede.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testSede.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testSede.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testSede.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testSede.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testSede.getFecAper()).isEqualTo(UPDATED_FEC_APER);
        assertThat(testSede.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testSede.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testSede.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testSede.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testSede.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testSede.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testSede.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testSede.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testSede.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingSede() throws Exception {
        int databaseSizeBeforeUpdate = sedeRepository.findAll().size();
        sede.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSedeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sede.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sede))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSede() throws Exception {
        int databaseSizeBeforeUpdate = sedeRepository.findAll().size();
        sede.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSedeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sede))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSede() throws Exception {
        int databaseSizeBeforeUpdate = sedeRepository.findAll().size();
        sede.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSedeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sede)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSede() throws Exception {
        // Initialize the database
        sedeRepository.save(sede);

        int databaseSizeBeforeDelete = sedeRepository.findAll().size();

        // Delete the sede
        restSedeMockMvc
            .perform(delete(ENTITY_API_URL_ID, sede.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
