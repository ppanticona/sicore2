package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.Caja;
import com.ppanticona.repository.CajaRepository;
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
 * Integration tests for the {@link CajaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CajaResourceIT {

    private static final String DEFAULT_TIP_CAJA = "AAAAAAAAAA";
    private static final String UPDATED_TIP_CAJA = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/cajas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private CajaRepository cajaRepository;

    @Autowired
    private MockMvc restCajaMockMvc;

    private Caja caja;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Caja createEntity() {
        Caja caja = new Caja()
            .tipCaja(DEFAULT_TIP_CAJA)
            .descripcion(DEFAULT_DESCRIPCION)
            .estado(DEFAULT_ESTADO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return caja;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Caja createUpdatedEntity() {
        Caja caja = new Caja()
            .tipCaja(UPDATED_TIP_CAJA)
            .descripcion(UPDATED_DESCRIPCION)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return caja;
    }

    @BeforeEach
    public void initTest() {
        cajaRepository.deleteAll();
        caja = createEntity();
    }

    @Test
    void createCaja() throws Exception {
        int databaseSizeBeforeCreate = cajaRepository.findAll().size();
        // Create the Caja
        restCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(caja)))
            .andExpect(status().isCreated());

        // Validate the Caja in the database
        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeCreate + 1);
        Caja testCaja = cajaList.get(cajaList.size() - 1);
        assertThat(testCaja.getTipCaja()).isEqualTo(DEFAULT_TIP_CAJA);
        assertThat(testCaja.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testCaja.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testCaja.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testCaja.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testCaja.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testCaja.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testCaja.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testCaja.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testCaja.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testCaja.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createCajaWithExistingId() throws Exception {
        // Create the Caja with an existing ID
        caja.setId("existing_id");

        int databaseSizeBeforeCreate = cajaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(caja)))
            .andExpect(status().isBadRequest());

        // Validate the Caja in the database
        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTipCajaIsRequired() throws Exception {
        int databaseSizeBeforeTest = cajaRepository.findAll().size();
        // set the field null
        caja.setTipCaja(null);

        // Create the Caja, which fails.

        restCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(caja)))
            .andExpect(status().isBadRequest());

        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cajaRepository.findAll().size();
        // set the field null
        caja.setDescripcion(null);

        // Create the Caja, which fails.

        restCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(caja)))
            .andExpect(status().isBadRequest());

        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cajaRepository.findAll().size();
        // set the field null
        caja.setEstado(null);

        // Create the Caja, which fails.

        restCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(caja)))
            .andExpect(status().isBadRequest());

        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cajaRepository.findAll().size();
        // set the field null
        caja.setVersion(null);

        // Create the Caja, which fails.

        restCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(caja)))
            .andExpect(status().isBadRequest());

        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = cajaRepository.findAll().size();
        // set the field null
        caja.setIndDel(null);

        // Create the Caja, which fails.

        restCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(caja)))
            .andExpect(status().isBadRequest());

        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = cajaRepository.findAll().size();
        // set the field null
        caja.setFecCrea(null);

        // Create the Caja, which fails.

        restCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(caja)))
            .andExpect(status().isBadRequest());

        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = cajaRepository.findAll().size();
        // set the field null
        caja.setUsuCrea(null);

        // Create the Caja, which fails.

        restCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(caja)))
            .andExpect(status().isBadRequest());

        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = cajaRepository.findAll().size();
        // set the field null
        caja.setIpCrea(null);

        // Create the Caja, which fails.

        restCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(caja)))
            .andExpect(status().isBadRequest());

        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllCajas() throws Exception {
        // Initialize the database
        cajaRepository.save(caja);

        // Get all the cajaList
        restCajaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caja.getId())))
            .andExpect(jsonPath("$.[*].tipCaja").value(hasItem(DEFAULT_TIP_CAJA)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
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
    void getCaja() throws Exception {
        // Initialize the database
        cajaRepository.save(caja);

        // Get the caja
        restCajaMockMvc
            .perform(get(ENTITY_API_URL_ID, caja.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(caja.getId()))
            .andExpect(jsonPath("$.tipCaja").value(DEFAULT_TIP_CAJA))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
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
    void getNonExistingCaja() throws Exception {
        // Get the caja
        restCajaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingCaja() throws Exception {
        // Initialize the database
        cajaRepository.save(caja);

        int databaseSizeBeforeUpdate = cajaRepository.findAll().size();

        // Update the caja
        Caja updatedCaja = cajaRepository.findById(caja.getId()).get();
        updatedCaja
            .tipCaja(UPDATED_TIP_CAJA)
            .descripcion(UPDATED_DESCRIPCION)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restCajaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCaja.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCaja))
            )
            .andExpect(status().isOk());

        // Validate the Caja in the database
        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeUpdate);
        Caja testCaja = cajaList.get(cajaList.size() - 1);
        assertThat(testCaja.getTipCaja()).isEqualTo(UPDATED_TIP_CAJA);
        assertThat(testCaja.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testCaja.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testCaja.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testCaja.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testCaja.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testCaja.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testCaja.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testCaja.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testCaja.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testCaja.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingCaja() throws Exception {
        int databaseSizeBeforeUpdate = cajaRepository.findAll().size();
        caja.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCajaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, caja.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(caja))
            )
            .andExpect(status().isBadRequest());

        // Validate the Caja in the database
        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCaja() throws Exception {
        int databaseSizeBeforeUpdate = cajaRepository.findAll().size();
        caja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCajaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(caja))
            )
            .andExpect(status().isBadRequest());

        // Validate the Caja in the database
        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCaja() throws Exception {
        int databaseSizeBeforeUpdate = cajaRepository.findAll().size();
        caja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCajaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(caja)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Caja in the database
        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCajaWithPatch() throws Exception {
        // Initialize the database
        cajaRepository.save(caja);

        int databaseSizeBeforeUpdate = cajaRepository.findAll().size();

        // Update the caja using partial update
        Caja partialUpdatedCaja = new Caja();
        partialUpdatedCaja.setId(caja.getId());

        partialUpdatedCaja.tipCaja(UPDATED_TIP_CAJA).fecCrea(UPDATED_FEC_CREA).usuCrea(UPDATED_USU_CREA).ipModif(UPDATED_IP_MODIF);

        restCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCaja.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCaja))
            )
            .andExpect(status().isOk());

        // Validate the Caja in the database
        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeUpdate);
        Caja testCaja = cajaList.get(cajaList.size() - 1);
        assertThat(testCaja.getTipCaja()).isEqualTo(UPDATED_TIP_CAJA);
        assertThat(testCaja.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testCaja.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testCaja.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testCaja.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testCaja.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testCaja.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testCaja.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testCaja.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testCaja.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testCaja.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void fullUpdateCajaWithPatch() throws Exception {
        // Initialize the database
        cajaRepository.save(caja);

        int databaseSizeBeforeUpdate = cajaRepository.findAll().size();

        // Update the caja using partial update
        Caja partialUpdatedCaja = new Caja();
        partialUpdatedCaja.setId(caja.getId());

        partialUpdatedCaja
            .tipCaja(UPDATED_TIP_CAJA)
            .descripcion(UPDATED_DESCRIPCION)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCaja.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCaja))
            )
            .andExpect(status().isOk());

        // Validate the Caja in the database
        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeUpdate);
        Caja testCaja = cajaList.get(cajaList.size() - 1);
        assertThat(testCaja.getTipCaja()).isEqualTo(UPDATED_TIP_CAJA);
        assertThat(testCaja.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testCaja.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testCaja.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testCaja.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testCaja.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testCaja.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testCaja.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testCaja.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testCaja.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testCaja.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingCaja() throws Exception {
        int databaseSizeBeforeUpdate = cajaRepository.findAll().size();
        caja.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, caja.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(caja))
            )
            .andExpect(status().isBadRequest());

        // Validate the Caja in the database
        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCaja() throws Exception {
        int databaseSizeBeforeUpdate = cajaRepository.findAll().size();
        caja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(caja))
            )
            .andExpect(status().isBadRequest());

        // Validate the Caja in the database
        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCaja() throws Exception {
        int databaseSizeBeforeUpdate = cajaRepository.findAll().size();
        caja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCajaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(caja)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Caja in the database
        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCaja() throws Exception {
        // Initialize the database
        cajaRepository.save(caja);

        int databaseSizeBeforeDelete = cajaRepository.findAll().size();

        // Delete the caja
        restCajaMockMvc
            .perform(delete(ENTITY_API_URL_ID, caja.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Caja> cajaList = cajaRepository.findAll();
        assertThat(cajaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
