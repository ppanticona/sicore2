package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.Mesas;
import com.ppanticona.repository.MesasRepository;
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
 * Integration tests for the {@link MesasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MesasResourceIT {

    private static final Integer DEFAULT_SEQ_MESA = 1;
    private static final Integer UPDATED_SEQ_MESA = 2;

    private static final Integer DEFAULT_NRO_MESA = 1;
    private static final Integer UPDATED_NRO_MESA = 2;

    private static final String DEFAULT_COD_GRUPO = "AAAAAAAAAA";
    private static final String UPDATED_COD_GRUPO = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final Integer DEFAULT_CAPACIDAD = 1;
    private static final Integer UPDATED_CAPACIDAD = 2;

    private static final Boolean DEFAULT_IND_MESA_JUNTA = false;
    private static final Boolean UPDATED_IND_MESA_JUNTA = true;

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

    private static final String ENTITY_API_URL = "/api/mesas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private MesasRepository mesasRepository;

    @Autowired
    private MockMvc restMesasMockMvc;

    private Mesas mesas;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mesas createEntity() {
        Mesas mesas = new Mesas()
            .seqMesa(DEFAULT_SEQ_MESA)
            .nroMesa(DEFAULT_NRO_MESA)
            .codGrupo(DEFAULT_COD_GRUPO)
            .categoria(DEFAULT_CATEGORIA)
            .capacidad(DEFAULT_CAPACIDAD)
            .indMesaJunta(DEFAULT_IND_MESA_JUNTA)
            .estado(DEFAULT_ESTADO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return mesas;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mesas createUpdatedEntity() {
        Mesas mesas = new Mesas()
            .seqMesa(UPDATED_SEQ_MESA)
            .nroMesa(UPDATED_NRO_MESA)
            .codGrupo(UPDATED_COD_GRUPO)
            .categoria(UPDATED_CATEGORIA)
            .capacidad(UPDATED_CAPACIDAD)
            .indMesaJunta(UPDATED_IND_MESA_JUNTA)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return mesas;
    }

    @BeforeEach
    public void initTest() {
        mesasRepository.deleteAll();
        mesas = createEntity();
    }

    @Test
    void createMesas() throws Exception {
        int databaseSizeBeforeCreate = mesasRepository.findAll().size();
        // Create the Mesas
        restMesasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mesas)))
            .andExpect(status().isCreated());

        // Validate the Mesas in the database
        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeCreate + 1);
        Mesas testMesas = mesasList.get(mesasList.size() - 1);
        assertThat(testMesas.getSeqMesa()).isEqualTo(DEFAULT_SEQ_MESA);
        assertThat(testMesas.getNroMesa()).isEqualTo(DEFAULT_NRO_MESA);
        assertThat(testMesas.getCodGrupo()).isEqualTo(DEFAULT_COD_GRUPO);
        assertThat(testMesas.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testMesas.getCapacidad()).isEqualTo(DEFAULT_CAPACIDAD);
        assertThat(testMesas.getIndMesaJunta()).isEqualTo(DEFAULT_IND_MESA_JUNTA);
        assertThat(testMesas.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testMesas.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testMesas.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testMesas.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testMesas.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testMesas.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testMesas.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testMesas.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testMesas.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createMesasWithExistingId() throws Exception {
        // Create the Mesas with an existing ID
        mesas.setId("existing_id");

        int databaseSizeBeforeCreate = mesasRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMesasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mesas)))
            .andExpect(status().isBadRequest());

        // Validate the Mesas in the database
        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkSeqMesaIsRequired() throws Exception {
        int databaseSizeBeforeTest = mesasRepository.findAll().size();
        // set the field null
        mesas.setSeqMesa(null);

        // Create the Mesas, which fails.

        restMesasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mesas)))
            .andExpect(status().isBadRequest());

        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNroMesaIsRequired() throws Exception {
        int databaseSizeBeforeTest = mesasRepository.findAll().size();
        // set the field null
        mesas.setNroMesa(null);

        // Create the Mesas, which fails.

        restMesasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mesas)))
            .andExpect(status().isBadRequest());

        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndMesaJuntaIsRequired() throws Exception {
        int databaseSizeBeforeTest = mesasRepository.findAll().size();
        // set the field null
        mesas.setIndMesaJunta(null);

        // Create the Mesas, which fails.

        restMesasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mesas)))
            .andExpect(status().isBadRequest());

        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = mesasRepository.findAll().size();
        // set the field null
        mesas.setEstado(null);

        // Create the Mesas, which fails.

        restMesasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mesas)))
            .andExpect(status().isBadRequest());

        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mesasRepository.findAll().size();
        // set the field null
        mesas.setVersion(null);

        // Create the Mesas, which fails.

        restMesasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mesas)))
            .andExpect(status().isBadRequest());

        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mesasRepository.findAll().size();
        // set the field null
        mesas.setIndDel(null);

        // Create the Mesas, which fails.

        restMesasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mesas)))
            .andExpect(status().isBadRequest());

        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = mesasRepository.findAll().size();
        // set the field null
        mesas.setFecCrea(null);

        // Create the Mesas, which fails.

        restMesasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mesas)))
            .andExpect(status().isBadRequest());

        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = mesasRepository.findAll().size();
        // set the field null
        mesas.setUsuCrea(null);

        // Create the Mesas, which fails.

        restMesasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mesas)))
            .andExpect(status().isBadRequest());

        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = mesasRepository.findAll().size();
        // set the field null
        mesas.setIpCrea(null);

        // Create the Mesas, which fails.

        restMesasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mesas)))
            .andExpect(status().isBadRequest());

        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllMesas() throws Exception {
        // Initialize the database
        mesasRepository.save(mesas);

        // Get all the mesasList
        restMesasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mesas.getId())))
            .andExpect(jsonPath("$.[*].seqMesa").value(hasItem(DEFAULT_SEQ_MESA)))
            .andExpect(jsonPath("$.[*].nroMesa").value(hasItem(DEFAULT_NRO_MESA)))
            .andExpect(jsonPath("$.[*].codGrupo").value(hasItem(DEFAULT_COD_GRUPO)))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA)))
            .andExpect(jsonPath("$.[*].capacidad").value(hasItem(DEFAULT_CAPACIDAD)))
            .andExpect(jsonPath("$.[*].indMesaJunta").value(hasItem(DEFAULT_IND_MESA_JUNTA.booleanValue())))
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
    void getMesas() throws Exception {
        // Initialize the database
        mesasRepository.save(mesas);

        // Get the mesas
        restMesasMockMvc
            .perform(get(ENTITY_API_URL_ID, mesas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mesas.getId()))
            .andExpect(jsonPath("$.seqMesa").value(DEFAULT_SEQ_MESA))
            .andExpect(jsonPath("$.nroMesa").value(DEFAULT_NRO_MESA))
            .andExpect(jsonPath("$.codGrupo").value(DEFAULT_COD_GRUPO))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA))
            .andExpect(jsonPath("$.capacidad").value(DEFAULT_CAPACIDAD))
            .andExpect(jsonPath("$.indMesaJunta").value(DEFAULT_IND_MESA_JUNTA.booleanValue()))
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
    void getNonExistingMesas() throws Exception {
        // Get the mesas
        restMesasMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingMesas() throws Exception {
        // Initialize the database
        mesasRepository.save(mesas);

        int databaseSizeBeforeUpdate = mesasRepository.findAll().size();

        // Update the mesas
        Mesas updatedMesas = mesasRepository.findById(mesas.getId()).get();
        updatedMesas
            .seqMesa(UPDATED_SEQ_MESA)
            .nroMesa(UPDATED_NRO_MESA)
            .codGrupo(UPDATED_COD_GRUPO)
            .categoria(UPDATED_CATEGORIA)
            .capacidad(UPDATED_CAPACIDAD)
            .indMesaJunta(UPDATED_IND_MESA_JUNTA)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restMesasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMesas.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMesas))
            )
            .andExpect(status().isOk());

        // Validate the Mesas in the database
        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeUpdate);
        Mesas testMesas = mesasList.get(mesasList.size() - 1);
        assertThat(testMesas.getSeqMesa()).isEqualTo(UPDATED_SEQ_MESA);
        assertThat(testMesas.getNroMesa()).isEqualTo(UPDATED_NRO_MESA);
        assertThat(testMesas.getCodGrupo()).isEqualTo(UPDATED_COD_GRUPO);
        assertThat(testMesas.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testMesas.getCapacidad()).isEqualTo(UPDATED_CAPACIDAD);
        assertThat(testMesas.getIndMesaJunta()).isEqualTo(UPDATED_IND_MESA_JUNTA);
        assertThat(testMesas.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testMesas.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testMesas.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testMesas.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testMesas.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testMesas.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testMesas.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testMesas.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testMesas.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingMesas() throws Exception {
        int databaseSizeBeforeUpdate = mesasRepository.findAll().size();
        mesas.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMesasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, mesas.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(mesas))
            )
            .andExpect(status().isBadRequest());

        // Validate the Mesas in the database
        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchMesas() throws Exception {
        int databaseSizeBeforeUpdate = mesasRepository.findAll().size();
        mesas.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMesasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(mesas))
            )
            .andExpect(status().isBadRequest());

        // Validate the Mesas in the database
        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamMesas() throws Exception {
        int databaseSizeBeforeUpdate = mesasRepository.findAll().size();
        mesas.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMesasMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mesas)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Mesas in the database
        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateMesasWithPatch() throws Exception {
        // Initialize the database
        mesasRepository.save(mesas);

        int databaseSizeBeforeUpdate = mesasRepository.findAll().size();

        // Update the mesas using partial update
        Mesas partialUpdatedMesas = new Mesas();
        partialUpdatedMesas.setId(mesas.getId());

        partialUpdatedMesas
            .codGrupo(UPDATED_COD_GRUPO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restMesasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMesas.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMesas))
            )
            .andExpect(status().isOk());

        // Validate the Mesas in the database
        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeUpdate);
        Mesas testMesas = mesasList.get(mesasList.size() - 1);
        assertThat(testMesas.getSeqMesa()).isEqualTo(DEFAULT_SEQ_MESA);
        assertThat(testMesas.getNroMesa()).isEqualTo(DEFAULT_NRO_MESA);
        assertThat(testMesas.getCodGrupo()).isEqualTo(UPDATED_COD_GRUPO);
        assertThat(testMesas.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testMesas.getCapacidad()).isEqualTo(DEFAULT_CAPACIDAD);
        assertThat(testMesas.getIndMesaJunta()).isEqualTo(DEFAULT_IND_MESA_JUNTA);
        assertThat(testMesas.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testMesas.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testMesas.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testMesas.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testMesas.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testMesas.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testMesas.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testMesas.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testMesas.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void fullUpdateMesasWithPatch() throws Exception {
        // Initialize the database
        mesasRepository.save(mesas);

        int databaseSizeBeforeUpdate = mesasRepository.findAll().size();

        // Update the mesas using partial update
        Mesas partialUpdatedMesas = new Mesas();
        partialUpdatedMesas.setId(mesas.getId());

        partialUpdatedMesas
            .seqMesa(UPDATED_SEQ_MESA)
            .nroMesa(UPDATED_NRO_MESA)
            .codGrupo(UPDATED_COD_GRUPO)
            .categoria(UPDATED_CATEGORIA)
            .capacidad(UPDATED_CAPACIDAD)
            .indMesaJunta(UPDATED_IND_MESA_JUNTA)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restMesasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMesas.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMesas))
            )
            .andExpect(status().isOk());

        // Validate the Mesas in the database
        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeUpdate);
        Mesas testMesas = mesasList.get(mesasList.size() - 1);
        assertThat(testMesas.getSeqMesa()).isEqualTo(UPDATED_SEQ_MESA);
        assertThat(testMesas.getNroMesa()).isEqualTo(UPDATED_NRO_MESA);
        assertThat(testMesas.getCodGrupo()).isEqualTo(UPDATED_COD_GRUPO);
        assertThat(testMesas.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testMesas.getCapacidad()).isEqualTo(UPDATED_CAPACIDAD);
        assertThat(testMesas.getIndMesaJunta()).isEqualTo(UPDATED_IND_MESA_JUNTA);
        assertThat(testMesas.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testMesas.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testMesas.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testMesas.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testMesas.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testMesas.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testMesas.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testMesas.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testMesas.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingMesas() throws Exception {
        int databaseSizeBeforeUpdate = mesasRepository.findAll().size();
        mesas.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMesasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, mesas.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(mesas))
            )
            .andExpect(status().isBadRequest());

        // Validate the Mesas in the database
        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchMesas() throws Exception {
        int databaseSizeBeforeUpdate = mesasRepository.findAll().size();
        mesas.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMesasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(mesas))
            )
            .andExpect(status().isBadRequest());

        // Validate the Mesas in the database
        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamMesas() throws Exception {
        int databaseSizeBeforeUpdate = mesasRepository.findAll().size();
        mesas.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMesasMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(mesas)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Mesas in the database
        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteMesas() throws Exception {
        // Initialize the database
        mesasRepository.save(mesas);

        int databaseSizeBeforeDelete = mesasRepository.findAll().size();

        // Delete the mesas
        restMesasMockMvc
            .perform(delete(ENTITY_API_URL_ID, mesas.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Mesas> mesasList = mesasRepository.findAll();
        assertThat(mesasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
