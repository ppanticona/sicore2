package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.MovimientoCaja;
import com.ppanticona.repository.MovimientoCajaRepository;
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
 * Integration tests for the {@link MovimientoCajaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MovimientoCajaResourceIT {

    private static final String DEFAULT_TIP_MOVIMIENTO = "AAAAAAAAAA";
    private static final String UPDATED_TIP_MOVIMIENTO = "BBBBBBBBBB";

    private static final String DEFAULT_CONCEPTO = "AAAAAAAAAA";
    private static final String UPDATED_CONCEPTO = "BBBBBBBBBB";

    private static final Double DEFAULT_MONTO = 1D;
    private static final Double UPDATED_MONTO = 2D;

    private static final String DEFAULT_TIP_MONEDA = "AAAAAAAAAA";
    private static final String UPDATED_TIP_MONEDA = "BBBBBBBBBB";

    private static final String DEFAULT_FORM_PAGO = "AAAAAAAAAA";
    private static final String UPDATED_FORM_PAGO = "BBBBBBBBBB";

    private static final String DEFAULT_COMPROBANTE = "AAAAAAAAAA";
    private static final String UPDATED_COMPROBANTE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_MOVIMIENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_MOVIMIENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

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

    private static final String ENTITY_API_URL = "/api/movimiento-cajas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private MovimientoCajaRepository movimientoCajaRepository;

    @Autowired
    private MockMvc restMovimientoCajaMockMvc;

    private MovimientoCaja movimientoCaja;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MovimientoCaja createEntity() {
        MovimientoCaja movimientoCaja = new MovimientoCaja()
            .tipMovimiento(DEFAULT_TIP_MOVIMIENTO)
            .concepto(DEFAULT_CONCEPTO)
            .monto(DEFAULT_MONTO)
            .tipMoneda(DEFAULT_TIP_MONEDA)
            .formPago(DEFAULT_FORM_PAGO)
            .comprobante(DEFAULT_COMPROBANTE)
            .fecMovimiento(DEFAULT_FEC_MOVIMIENTO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return movimientoCaja;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MovimientoCaja createUpdatedEntity() {
        MovimientoCaja movimientoCaja = new MovimientoCaja()
            .tipMovimiento(UPDATED_TIP_MOVIMIENTO)
            .concepto(UPDATED_CONCEPTO)
            .monto(UPDATED_MONTO)
            .tipMoneda(UPDATED_TIP_MONEDA)
            .formPago(UPDATED_FORM_PAGO)
            .comprobante(UPDATED_COMPROBANTE)
            .fecMovimiento(UPDATED_FEC_MOVIMIENTO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return movimientoCaja;
    }

    @BeforeEach
    public void initTest() {
        movimientoCajaRepository.deleteAll();
        movimientoCaja = createEntity();
    }

    @Test
    void createMovimientoCaja() throws Exception {
        int databaseSizeBeforeCreate = movimientoCajaRepository.findAll().size();
        // Create the MovimientoCaja
        restMovimientoCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoCaja))
            )
            .andExpect(status().isCreated());

        // Validate the MovimientoCaja in the database
        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeCreate + 1);
        MovimientoCaja testMovimientoCaja = movimientoCajaList.get(movimientoCajaList.size() - 1);
        assertThat(testMovimientoCaja.getTipMovimiento()).isEqualTo(DEFAULT_TIP_MOVIMIENTO);
        assertThat(testMovimientoCaja.getConcepto()).isEqualTo(DEFAULT_CONCEPTO);
        assertThat(testMovimientoCaja.getMonto()).isEqualTo(DEFAULT_MONTO);
        assertThat(testMovimientoCaja.getTipMoneda()).isEqualTo(DEFAULT_TIP_MONEDA);
        assertThat(testMovimientoCaja.getFormPago()).isEqualTo(DEFAULT_FORM_PAGO);
        assertThat(testMovimientoCaja.getComprobante()).isEqualTo(DEFAULT_COMPROBANTE);
        assertThat(testMovimientoCaja.getFecMovimiento()).isEqualTo(DEFAULT_FEC_MOVIMIENTO);
        assertThat(testMovimientoCaja.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testMovimientoCaja.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testMovimientoCaja.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testMovimientoCaja.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testMovimientoCaja.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testMovimientoCaja.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testMovimientoCaja.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testMovimientoCaja.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createMovimientoCajaWithExistingId() throws Exception {
        // Create the MovimientoCaja with an existing ID
        movimientoCaja.setId("existing_id");

        int databaseSizeBeforeCreate = movimientoCajaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMovimientoCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the MovimientoCaja in the database
        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = movimientoCajaRepository.findAll().size();
        // set the field null
        movimientoCaja.setVersion(null);

        // Create the MovimientoCaja, which fails.

        restMovimientoCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoCaja))
            )
            .andExpect(status().isBadRequest());

        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = movimientoCajaRepository.findAll().size();
        // set the field null
        movimientoCaja.setIndDel(null);

        // Create the MovimientoCaja, which fails.

        restMovimientoCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoCaja))
            )
            .andExpect(status().isBadRequest());

        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = movimientoCajaRepository.findAll().size();
        // set the field null
        movimientoCaja.setFecCrea(null);

        // Create the MovimientoCaja, which fails.

        restMovimientoCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoCaja))
            )
            .andExpect(status().isBadRequest());

        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = movimientoCajaRepository.findAll().size();
        // set the field null
        movimientoCaja.setUsuCrea(null);

        // Create the MovimientoCaja, which fails.

        restMovimientoCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoCaja))
            )
            .andExpect(status().isBadRequest());

        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = movimientoCajaRepository.findAll().size();
        // set the field null
        movimientoCaja.setIpCrea(null);

        // Create the MovimientoCaja, which fails.

        restMovimientoCajaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoCaja))
            )
            .andExpect(status().isBadRequest());

        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllMovimientoCajas() throws Exception {
        // Initialize the database
        movimientoCajaRepository.save(movimientoCaja);

        // Get all the movimientoCajaList
        restMovimientoCajaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movimientoCaja.getId())))
            .andExpect(jsonPath("$.[*].tipMovimiento").value(hasItem(DEFAULT_TIP_MOVIMIENTO)))
            .andExpect(jsonPath("$.[*].concepto").value(hasItem(DEFAULT_CONCEPTO)))
            .andExpect(jsonPath("$.[*].monto").value(hasItem(DEFAULT_MONTO.doubleValue())))
            .andExpect(jsonPath("$.[*].tipMoneda").value(hasItem(DEFAULT_TIP_MONEDA)))
            .andExpect(jsonPath("$.[*].formPago").value(hasItem(DEFAULT_FORM_PAGO)))
            .andExpect(jsonPath("$.[*].comprobante").value(hasItem(DEFAULT_COMPROBANTE)))
            .andExpect(jsonPath("$.[*].fecMovimiento").value(hasItem(sameInstant(DEFAULT_FEC_MOVIMIENTO))))
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
    void getMovimientoCaja() throws Exception {
        // Initialize the database
        movimientoCajaRepository.save(movimientoCaja);

        // Get the movimientoCaja
        restMovimientoCajaMockMvc
            .perform(get(ENTITY_API_URL_ID, movimientoCaja.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(movimientoCaja.getId()))
            .andExpect(jsonPath("$.tipMovimiento").value(DEFAULT_TIP_MOVIMIENTO))
            .andExpect(jsonPath("$.concepto").value(DEFAULT_CONCEPTO))
            .andExpect(jsonPath("$.monto").value(DEFAULT_MONTO.doubleValue()))
            .andExpect(jsonPath("$.tipMoneda").value(DEFAULT_TIP_MONEDA))
            .andExpect(jsonPath("$.formPago").value(DEFAULT_FORM_PAGO))
            .andExpect(jsonPath("$.comprobante").value(DEFAULT_COMPROBANTE))
            .andExpect(jsonPath("$.fecMovimiento").value(sameInstant(DEFAULT_FEC_MOVIMIENTO)))
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
    void getNonExistingMovimientoCaja() throws Exception {
        // Get the movimientoCaja
        restMovimientoCajaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingMovimientoCaja() throws Exception {
        // Initialize the database
        movimientoCajaRepository.save(movimientoCaja);

        int databaseSizeBeforeUpdate = movimientoCajaRepository.findAll().size();

        // Update the movimientoCaja
        MovimientoCaja updatedMovimientoCaja = movimientoCajaRepository.findById(movimientoCaja.getId()).get();
        updatedMovimientoCaja
            .tipMovimiento(UPDATED_TIP_MOVIMIENTO)
            .concepto(UPDATED_CONCEPTO)
            .monto(UPDATED_MONTO)
            .tipMoneda(UPDATED_TIP_MONEDA)
            .formPago(UPDATED_FORM_PAGO)
            .comprobante(UPDATED_COMPROBANTE)
            .fecMovimiento(UPDATED_FEC_MOVIMIENTO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restMovimientoCajaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMovimientoCaja.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMovimientoCaja))
            )
            .andExpect(status().isOk());

        // Validate the MovimientoCaja in the database
        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeUpdate);
        MovimientoCaja testMovimientoCaja = movimientoCajaList.get(movimientoCajaList.size() - 1);
        assertThat(testMovimientoCaja.getTipMovimiento()).isEqualTo(UPDATED_TIP_MOVIMIENTO);
        assertThat(testMovimientoCaja.getConcepto()).isEqualTo(UPDATED_CONCEPTO);
        assertThat(testMovimientoCaja.getMonto()).isEqualTo(UPDATED_MONTO);
        assertThat(testMovimientoCaja.getTipMoneda()).isEqualTo(UPDATED_TIP_MONEDA);
        assertThat(testMovimientoCaja.getFormPago()).isEqualTo(UPDATED_FORM_PAGO);
        assertThat(testMovimientoCaja.getComprobante()).isEqualTo(UPDATED_COMPROBANTE);
        assertThat(testMovimientoCaja.getFecMovimiento()).isEqualTo(UPDATED_FEC_MOVIMIENTO);
        assertThat(testMovimientoCaja.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testMovimientoCaja.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testMovimientoCaja.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testMovimientoCaja.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testMovimientoCaja.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testMovimientoCaja.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testMovimientoCaja.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testMovimientoCaja.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingMovimientoCaja() throws Exception {
        int databaseSizeBeforeUpdate = movimientoCajaRepository.findAll().size();
        movimientoCaja.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMovimientoCajaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, movimientoCaja.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(movimientoCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the MovimientoCaja in the database
        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchMovimientoCaja() throws Exception {
        int databaseSizeBeforeUpdate = movimientoCajaRepository.findAll().size();
        movimientoCaja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMovimientoCajaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(movimientoCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the MovimientoCaja in the database
        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamMovimientoCaja() throws Exception {
        int databaseSizeBeforeUpdate = movimientoCajaRepository.findAll().size();
        movimientoCaja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMovimientoCajaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoCaja)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MovimientoCaja in the database
        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateMovimientoCajaWithPatch() throws Exception {
        // Initialize the database
        movimientoCajaRepository.save(movimientoCaja);

        int databaseSizeBeforeUpdate = movimientoCajaRepository.findAll().size();

        // Update the movimientoCaja using partial update
        MovimientoCaja partialUpdatedMovimientoCaja = new MovimientoCaja();
        partialUpdatedMovimientoCaja.setId(movimientoCaja.getId());

        partialUpdatedMovimientoCaja
            .concepto(UPDATED_CONCEPTO)
            .tipMoneda(UPDATED_TIP_MONEDA)
            .comprobante(UPDATED_COMPROBANTE)
            .version(UPDATED_VERSION)
            .fecCrea(UPDATED_FEC_CREA)
            .fecModif(UPDATED_FEC_MODIF);

        restMovimientoCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMovimientoCaja.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMovimientoCaja))
            )
            .andExpect(status().isOk());

        // Validate the MovimientoCaja in the database
        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeUpdate);
        MovimientoCaja testMovimientoCaja = movimientoCajaList.get(movimientoCajaList.size() - 1);
        assertThat(testMovimientoCaja.getTipMovimiento()).isEqualTo(DEFAULT_TIP_MOVIMIENTO);
        assertThat(testMovimientoCaja.getConcepto()).isEqualTo(UPDATED_CONCEPTO);
        assertThat(testMovimientoCaja.getMonto()).isEqualTo(DEFAULT_MONTO);
        assertThat(testMovimientoCaja.getTipMoneda()).isEqualTo(UPDATED_TIP_MONEDA);
        assertThat(testMovimientoCaja.getFormPago()).isEqualTo(DEFAULT_FORM_PAGO);
        assertThat(testMovimientoCaja.getComprobante()).isEqualTo(UPDATED_COMPROBANTE);
        assertThat(testMovimientoCaja.getFecMovimiento()).isEqualTo(DEFAULT_FEC_MOVIMIENTO);
        assertThat(testMovimientoCaja.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testMovimientoCaja.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testMovimientoCaja.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testMovimientoCaja.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testMovimientoCaja.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testMovimientoCaja.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testMovimientoCaja.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testMovimientoCaja.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void fullUpdateMovimientoCajaWithPatch() throws Exception {
        // Initialize the database
        movimientoCajaRepository.save(movimientoCaja);

        int databaseSizeBeforeUpdate = movimientoCajaRepository.findAll().size();

        // Update the movimientoCaja using partial update
        MovimientoCaja partialUpdatedMovimientoCaja = new MovimientoCaja();
        partialUpdatedMovimientoCaja.setId(movimientoCaja.getId());

        partialUpdatedMovimientoCaja
            .tipMovimiento(UPDATED_TIP_MOVIMIENTO)
            .concepto(UPDATED_CONCEPTO)
            .monto(UPDATED_MONTO)
            .tipMoneda(UPDATED_TIP_MONEDA)
            .formPago(UPDATED_FORM_PAGO)
            .comprobante(UPDATED_COMPROBANTE)
            .fecMovimiento(UPDATED_FEC_MOVIMIENTO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restMovimientoCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMovimientoCaja.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMovimientoCaja))
            )
            .andExpect(status().isOk());

        // Validate the MovimientoCaja in the database
        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeUpdate);
        MovimientoCaja testMovimientoCaja = movimientoCajaList.get(movimientoCajaList.size() - 1);
        assertThat(testMovimientoCaja.getTipMovimiento()).isEqualTo(UPDATED_TIP_MOVIMIENTO);
        assertThat(testMovimientoCaja.getConcepto()).isEqualTo(UPDATED_CONCEPTO);
        assertThat(testMovimientoCaja.getMonto()).isEqualTo(UPDATED_MONTO);
        assertThat(testMovimientoCaja.getTipMoneda()).isEqualTo(UPDATED_TIP_MONEDA);
        assertThat(testMovimientoCaja.getFormPago()).isEqualTo(UPDATED_FORM_PAGO);
        assertThat(testMovimientoCaja.getComprobante()).isEqualTo(UPDATED_COMPROBANTE);
        assertThat(testMovimientoCaja.getFecMovimiento()).isEqualTo(UPDATED_FEC_MOVIMIENTO);
        assertThat(testMovimientoCaja.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testMovimientoCaja.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testMovimientoCaja.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testMovimientoCaja.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testMovimientoCaja.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testMovimientoCaja.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testMovimientoCaja.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testMovimientoCaja.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingMovimientoCaja() throws Exception {
        int databaseSizeBeforeUpdate = movimientoCajaRepository.findAll().size();
        movimientoCaja.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMovimientoCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, movimientoCaja.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(movimientoCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the MovimientoCaja in the database
        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchMovimientoCaja() throws Exception {
        int databaseSizeBeforeUpdate = movimientoCajaRepository.findAll().size();
        movimientoCaja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMovimientoCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(movimientoCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the MovimientoCaja in the database
        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamMovimientoCaja() throws Exception {
        int databaseSizeBeforeUpdate = movimientoCajaRepository.findAll().size();
        movimientoCaja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMovimientoCajaMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(movimientoCaja))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MovimientoCaja in the database
        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteMovimientoCaja() throws Exception {
        // Initialize the database
        movimientoCajaRepository.save(movimientoCaja);

        int databaseSizeBeforeDelete = movimientoCajaRepository.findAll().size();

        // Delete the movimientoCaja
        restMovimientoCajaMockMvc
            .perform(delete(ENTITY_API_URL_ID, movimientoCaja.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MovimientoCaja> movimientoCajaList = movimientoCajaRepository.findAll();
        assertThat(movimientoCajaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
