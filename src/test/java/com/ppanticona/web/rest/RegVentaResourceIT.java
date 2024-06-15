package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.RegVenta;
import com.ppanticona.repository.RegVentaRepository;
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
 * Integration tests for the {@link RegVentaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RegVentaResourceIT {

    private static final String DEFAULT_PERIODO = "AAAAAAAAAA";
    private static final String UPDATED_PERIODO = "BBBBBBBBBB";

    private static final String DEFAULT_CUO = "AAAAAAAAAA";
    private static final String UPDATED_CUO = "BBBBBBBBBB";

    private static final String DEFAULT_ASIENT_CONTAB = "AAAAAAAAAA";
    private static final String UPDATED_ASIENT_CONTAB = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_EMI_COMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_EMI_COMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FEC_VENC_COMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_VENC_COMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TIP_COMP = "AAAAAAAAAA";
    private static final String UPDATED_TIP_COMP = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_SERIE_COMP = "AAAAAAAAAA";
    private static final String UPDATED_NRO_SERIE_COMP = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_COMP = "AAAAAAAAAA";
    private static final String UPDATED_NRO_COMP = "BBBBBBBBBB";

    private static final String DEFAULT_CONSO_DIA = "AAAAAAAAAA";
    private static final String UPDATED_CONSO_DIA = "BBBBBBBBBB";

    private static final String DEFAULT_TIP_DOC_CLI = "AAAAAAAAAA";
    private static final String UPDATED_TIP_DOC_CLI = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_DOC_CLI = "AAAAAAAAAA";
    private static final String UPDATED_NRO_DOC_CLI = "BBBBBBBBBB";

    private static final String DEFAULT_APE_RAZ_SOC_CLI = "AAAAAAAAAA";
    private static final String UPDATED_APE_RAZ_SOC_CLI = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_FAC_EXPOR = 1D;
    private static final Double UPDATED_VAL_FAC_EXPOR = 2D;

    private static final Double DEFAULT_BASE_IMP_OPER_GRAV = 1D;
    private static final Double UPDATED_BASE_IMP_OPER_GRAV = 2D;

    private static final Double DEFAULT_DSCTO_BASE_IMP = 1D;
    private static final Double UPDATED_DSCTO_BASE_IMP = 2D;

    private static final Double DEFAULT_IGV_IPM = 1D;
    private static final Double UPDATED_IGV_IPM = 2D;

    private static final Double DEFAULT_DSCTO_IGV_IPM = 1D;
    private static final Double UPDATED_DSCTO_IGV_IPM = 2D;

    private static final Double DEFAULT_IMP_OPE_EXO = 1D;
    private static final Double UPDATED_IMP_OPE_EXO = 2D;

    private static final Double DEFAULT_IMP_TOT_OPE_INAFEC = 1D;
    private static final Double UPDATED_IMP_TOT_OPE_INAFEC = 2D;

    private static final Double DEFAULT_IMP_SEC_CONS = 1D;
    private static final Double UPDATED_IMP_SEC_CONS = 2D;

    private static final Double DEFAULT_BASE_IMP_ARROZ = 1D;
    private static final Double UPDATED_BASE_IMP_ARROZ = 2D;

    private static final Double DEFAULT_IMP_VENT_ARROZ = 1D;
    private static final Double UPDATED_IMP_VENT_ARROZ = 2D;

    private static final Double DEFAULT_OTROS_NO_BASE_IMP = 1D;
    private static final Double UPDATED_OTROS_NO_BASE_IMP = 2D;

    private static final Double DEFAULT_IMPORTE_TOTAL_COMP = 1D;
    private static final Double UPDATED_IMPORTE_TOTAL_COMP = 2D;

    private static final String DEFAULT_COD_MONEDA = "AAAAAAAAAA";
    private static final String UPDATED_COD_MONEDA = "BBBBBBBBBB";

    private static final Double DEFAULT_TIP_CAMBIO = 1D;
    private static final Double UPDATED_TIP_CAMBIO = 2D;

    private static final ZonedDateTime DEFAULT_FEC_EMI_MODIF = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_EMI_MODIF = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TIP_COMP_MODIF = "AAAAAAAAAA";
    private static final String UPDATED_TIP_COMP_MODIF = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_SERIE_COMP_MODIF = "AAAAAAAAAA";
    private static final String UPDATED_NRO_SERIE_COMP_MODIF = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_COMP_MODIF = "AAAAAAAAAA";
    private static final String UPDATED_NRO_COMP_MODIF = "BBBBBBBBBB";

    private static final String DEFAULT_IDENT_CONTRATO = "AAAAAAAAAA";
    private static final String UPDATED_IDENT_CONTRATO = "BBBBBBBBBB";

    private static final String DEFAULT_ERR_TIP_UNO = "AAAAAAAAAA";
    private static final String UPDATED_ERR_TIP_UNO = "BBBBBBBBBB";

    private static final String DEFAULT_IND_COMP_VANC_MP = "AAAAAAAAAA";
    private static final String UPDATED_IND_COMP_VANC_MP = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO_OPERA_CANC_MP = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO_OPERA_CANC_MP = "BBBBBBBBBB";

    private static final String DEFAULT_CAMPO_LIBRE = "AAAAAAAAAA";
    private static final String UPDATED_CAMPO_LIBRE = "BBBBBBBBBB";

    private static final String DEFAULT_FORM_PAGO = "AAAAAAAAAA";
    private static final String UPDATED_FORM_PAGO = "BBBBBBBBBB";

    private static final String DEFAULT_MONEDA = "AAAAAAAAAA";
    private static final String UPDATED_MONEDA = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/reg-ventas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private RegVentaRepository regVentaRepository;

    @Autowired
    private MockMvc restRegVentaMockMvc;

    private RegVenta regVenta;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegVenta createEntity() {
        RegVenta regVenta = new RegVenta()
            .periodo(DEFAULT_PERIODO)
            .cuo(DEFAULT_CUO)
            .asientContab(DEFAULT_ASIENT_CONTAB)
            .fecEmiComp(DEFAULT_FEC_EMI_COMP)
            .fecVencComp(DEFAULT_FEC_VENC_COMP)
            .tipComp(DEFAULT_TIP_COMP)
            .nroSerieComp(DEFAULT_NRO_SERIE_COMP)
            .nroComp(DEFAULT_NRO_COMP)
            .consoDia(DEFAULT_CONSO_DIA)
            .tipDocCli(DEFAULT_TIP_DOC_CLI)
            .nroDocCli(DEFAULT_NRO_DOC_CLI)
            .apeRazSocCli(DEFAULT_APE_RAZ_SOC_CLI)
            .valFacExpor(DEFAULT_VAL_FAC_EXPOR)
            .baseImpOperGrav(DEFAULT_BASE_IMP_OPER_GRAV)
            .dsctoBaseImp(DEFAULT_DSCTO_BASE_IMP)
            .igvIpm(DEFAULT_IGV_IPM)
            .dsctoIgvIpm(DEFAULT_DSCTO_IGV_IPM)
            .impOpeExo(DEFAULT_IMP_OPE_EXO)
            .impTotOpeInafec(DEFAULT_IMP_TOT_OPE_INAFEC)
            .impSecCons(DEFAULT_IMP_SEC_CONS)
            .baseImpArroz(DEFAULT_BASE_IMP_ARROZ)
            .impVentArroz(DEFAULT_IMP_VENT_ARROZ)
            .otrosNoBaseImp(DEFAULT_OTROS_NO_BASE_IMP)
            .importeTotalComp(DEFAULT_IMPORTE_TOTAL_COMP)
            .codMoneda(DEFAULT_COD_MONEDA)
            .tipCambio(DEFAULT_TIP_CAMBIO)
            .fecEmiModif(DEFAULT_FEC_EMI_MODIF)
            .tipCompModif(DEFAULT_TIP_COMP_MODIF)
            .nroSerieCompModif(DEFAULT_NRO_SERIE_COMP_MODIF)
            .nroCompModif(DEFAULT_NRO_COMP_MODIF)
            .identContrato(DEFAULT_IDENT_CONTRATO)
            .errTipUno(DEFAULT_ERR_TIP_UNO)
            .indCompVancMp(DEFAULT_IND_COMP_VANC_MP)
            .estadoOperaCancMp(DEFAULT_ESTADO_OPERA_CANC_MP)
            .campoLibre(DEFAULT_CAMPO_LIBRE)
            .formPago(DEFAULT_FORM_PAGO)
            .moneda(DEFAULT_MONEDA)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return regVenta;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegVenta createUpdatedEntity() {
        RegVenta regVenta = new RegVenta()
            .periodo(UPDATED_PERIODO)
            .cuo(UPDATED_CUO)
            .asientContab(UPDATED_ASIENT_CONTAB)
            .fecEmiComp(UPDATED_FEC_EMI_COMP)
            .fecVencComp(UPDATED_FEC_VENC_COMP)
            .tipComp(UPDATED_TIP_COMP)
            .nroSerieComp(UPDATED_NRO_SERIE_COMP)
            .nroComp(UPDATED_NRO_COMP)
            .consoDia(UPDATED_CONSO_DIA)
            .tipDocCli(UPDATED_TIP_DOC_CLI)
            .nroDocCli(UPDATED_NRO_DOC_CLI)
            .apeRazSocCli(UPDATED_APE_RAZ_SOC_CLI)
            .valFacExpor(UPDATED_VAL_FAC_EXPOR)
            .baseImpOperGrav(UPDATED_BASE_IMP_OPER_GRAV)
            .dsctoBaseImp(UPDATED_DSCTO_BASE_IMP)
            .igvIpm(UPDATED_IGV_IPM)
            .dsctoIgvIpm(UPDATED_DSCTO_IGV_IPM)
            .impOpeExo(UPDATED_IMP_OPE_EXO)
            .impTotOpeInafec(UPDATED_IMP_TOT_OPE_INAFEC)
            .impSecCons(UPDATED_IMP_SEC_CONS)
            .baseImpArroz(UPDATED_BASE_IMP_ARROZ)
            .impVentArroz(UPDATED_IMP_VENT_ARROZ)
            .otrosNoBaseImp(UPDATED_OTROS_NO_BASE_IMP)
            .importeTotalComp(UPDATED_IMPORTE_TOTAL_COMP)
            .codMoneda(UPDATED_COD_MONEDA)
            .tipCambio(UPDATED_TIP_CAMBIO)
            .fecEmiModif(UPDATED_FEC_EMI_MODIF)
            .tipCompModif(UPDATED_TIP_COMP_MODIF)
            .nroSerieCompModif(UPDATED_NRO_SERIE_COMP_MODIF)
            .nroCompModif(UPDATED_NRO_COMP_MODIF)
            .identContrato(UPDATED_IDENT_CONTRATO)
            .errTipUno(UPDATED_ERR_TIP_UNO)
            .indCompVancMp(UPDATED_IND_COMP_VANC_MP)
            .estadoOperaCancMp(UPDATED_ESTADO_OPERA_CANC_MP)
            .campoLibre(UPDATED_CAMPO_LIBRE)
            .formPago(UPDATED_FORM_PAGO)
            .moneda(UPDATED_MONEDA)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return regVenta;
    }

    @BeforeEach
    public void initTest() {
        regVentaRepository.deleteAll();
        regVenta = createEntity();
    }

    @Test
    void createRegVenta() throws Exception {
        int databaseSizeBeforeCreate = regVentaRepository.findAll().size();
        // Create the RegVenta
        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isCreated());

        // Validate the RegVenta in the database
        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeCreate + 1);
        RegVenta testRegVenta = regVentaList.get(regVentaList.size() - 1);
        assertThat(testRegVenta.getPeriodo()).isEqualTo(DEFAULT_PERIODO);
        assertThat(testRegVenta.getCuo()).isEqualTo(DEFAULT_CUO);
        assertThat(testRegVenta.getAsientContab()).isEqualTo(DEFAULT_ASIENT_CONTAB);
        assertThat(testRegVenta.getFecEmiComp()).isEqualTo(DEFAULT_FEC_EMI_COMP);
        assertThat(testRegVenta.getFecVencComp()).isEqualTo(DEFAULT_FEC_VENC_COMP);
        assertThat(testRegVenta.getTipComp()).isEqualTo(DEFAULT_TIP_COMP);
        assertThat(testRegVenta.getNroSerieComp()).isEqualTo(DEFAULT_NRO_SERIE_COMP);
        assertThat(testRegVenta.getNroComp()).isEqualTo(DEFAULT_NRO_COMP);
        assertThat(testRegVenta.getConsoDia()).isEqualTo(DEFAULT_CONSO_DIA);
        assertThat(testRegVenta.getTipDocCli()).isEqualTo(DEFAULT_TIP_DOC_CLI);
        assertThat(testRegVenta.getNroDocCli()).isEqualTo(DEFAULT_NRO_DOC_CLI);
        assertThat(testRegVenta.getApeRazSocCli()).isEqualTo(DEFAULT_APE_RAZ_SOC_CLI);
        assertThat(testRegVenta.getValFacExpor()).isEqualTo(DEFAULT_VAL_FAC_EXPOR);
        assertThat(testRegVenta.getBaseImpOperGrav()).isEqualTo(DEFAULT_BASE_IMP_OPER_GRAV);
        assertThat(testRegVenta.getDsctoBaseImp()).isEqualTo(DEFAULT_DSCTO_BASE_IMP);
        assertThat(testRegVenta.getIgvIpm()).isEqualTo(DEFAULT_IGV_IPM);
        assertThat(testRegVenta.getDsctoIgvIpm()).isEqualTo(DEFAULT_DSCTO_IGV_IPM);
        assertThat(testRegVenta.getImpOpeExo()).isEqualTo(DEFAULT_IMP_OPE_EXO);
        assertThat(testRegVenta.getImpTotOpeInafec()).isEqualTo(DEFAULT_IMP_TOT_OPE_INAFEC);
        assertThat(testRegVenta.getImpSecCons()).isEqualTo(DEFAULT_IMP_SEC_CONS);
        assertThat(testRegVenta.getBaseImpArroz()).isEqualTo(DEFAULT_BASE_IMP_ARROZ);
        assertThat(testRegVenta.getImpVentArroz()).isEqualTo(DEFAULT_IMP_VENT_ARROZ);
        assertThat(testRegVenta.getOtrosNoBaseImp()).isEqualTo(DEFAULT_OTROS_NO_BASE_IMP);
        assertThat(testRegVenta.getImporteTotalComp()).isEqualTo(DEFAULT_IMPORTE_TOTAL_COMP);
        assertThat(testRegVenta.getCodMoneda()).isEqualTo(DEFAULT_COD_MONEDA);
        assertThat(testRegVenta.getTipCambio()).isEqualTo(DEFAULT_TIP_CAMBIO);
        assertThat(testRegVenta.getFecEmiModif()).isEqualTo(DEFAULT_FEC_EMI_MODIF);
        assertThat(testRegVenta.getTipCompModif()).isEqualTo(DEFAULT_TIP_COMP_MODIF);
        assertThat(testRegVenta.getNroSerieCompModif()).isEqualTo(DEFAULT_NRO_SERIE_COMP_MODIF);
        assertThat(testRegVenta.getNroCompModif()).isEqualTo(DEFAULT_NRO_COMP_MODIF);
        assertThat(testRegVenta.getIdentContrato()).isEqualTo(DEFAULT_IDENT_CONTRATO);
        assertThat(testRegVenta.getErrTipUno()).isEqualTo(DEFAULT_ERR_TIP_UNO);
        assertThat(testRegVenta.getIndCompVancMp()).isEqualTo(DEFAULT_IND_COMP_VANC_MP);
        assertThat(testRegVenta.getEstadoOperaCancMp()).isEqualTo(DEFAULT_ESTADO_OPERA_CANC_MP);
        assertThat(testRegVenta.getCampoLibre()).isEqualTo(DEFAULT_CAMPO_LIBRE);
        assertThat(testRegVenta.getFormPago()).isEqualTo(DEFAULT_FORM_PAGO);
        assertThat(testRegVenta.getMoneda()).isEqualTo(DEFAULT_MONEDA);
        assertThat(testRegVenta.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testRegVenta.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testRegVenta.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testRegVenta.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testRegVenta.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testRegVenta.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testRegVenta.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createRegVentaWithExistingId() throws Exception {
        // Create the RegVenta with an existing ID
        regVenta.setId("existing_id");

        int databaseSizeBeforeCreate = regVentaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        // Validate the RegVenta in the database
        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkPeriodoIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setPeriodo(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCuoIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setCuo(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecEmiCompIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setFecEmiComp(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTipCompIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setTipComp(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNroSerieCompIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setNroSerieComp(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNroCompIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setNroComp(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTipDocCliIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setTipDocCli(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNroDocCliIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setNroDocCli(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkApeRazSocCliIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setApeRazSocCli(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBaseImpOperGravIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setBaseImpOperGrav(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDsctoBaseImpIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setDsctoBaseImp(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIgvIpmIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setIgvIpm(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDsctoIgvIpmIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setDsctoIgvIpm(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkImpOpeExoIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setImpOpeExo(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkImpTotOpeInafecIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setImpTotOpeInafec(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkImporteTotalCompIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setImporteTotalComp(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCodMonedaIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setCodMoneda(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTipCambioIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setTipCambio(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setIndDel(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setFecCrea(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setUsuCrea(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = regVentaRepository.findAll().size();
        // set the field null
        regVenta.setIpCrea(null);

        // Create the RegVenta, which fails.

        restRegVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isBadRequest());

        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllRegVentas() throws Exception {
        // Initialize the database
        regVentaRepository.save(regVenta);

        // Get all the regVentaList
        restRegVentaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(regVenta.getId())))
            .andExpect(jsonPath("$.[*].periodo").value(hasItem(DEFAULT_PERIODO)))
            .andExpect(jsonPath("$.[*].cuo").value(hasItem(DEFAULT_CUO)))
            .andExpect(jsonPath("$.[*].asientContab").value(hasItem(DEFAULT_ASIENT_CONTAB)))
            .andExpect(jsonPath("$.[*].fecEmiComp").value(hasItem(sameInstant(DEFAULT_FEC_EMI_COMP))))
            .andExpect(jsonPath("$.[*].fecVencComp").value(hasItem(sameInstant(DEFAULT_FEC_VENC_COMP))))
            .andExpect(jsonPath("$.[*].tipComp").value(hasItem(DEFAULT_TIP_COMP)))
            .andExpect(jsonPath("$.[*].nroSerieComp").value(hasItem(DEFAULT_NRO_SERIE_COMP)))
            .andExpect(jsonPath("$.[*].nroComp").value(hasItem(DEFAULT_NRO_COMP)))
            .andExpect(jsonPath("$.[*].consoDia").value(hasItem(DEFAULT_CONSO_DIA)))
            .andExpect(jsonPath("$.[*].tipDocCli").value(hasItem(DEFAULT_TIP_DOC_CLI)))
            .andExpect(jsonPath("$.[*].nroDocCli").value(hasItem(DEFAULT_NRO_DOC_CLI)))
            .andExpect(jsonPath("$.[*].apeRazSocCli").value(hasItem(DEFAULT_APE_RAZ_SOC_CLI)))
            .andExpect(jsonPath("$.[*].valFacExpor").value(hasItem(DEFAULT_VAL_FAC_EXPOR.doubleValue())))
            .andExpect(jsonPath("$.[*].baseImpOperGrav").value(hasItem(DEFAULT_BASE_IMP_OPER_GRAV.doubleValue())))
            .andExpect(jsonPath("$.[*].dsctoBaseImp").value(hasItem(DEFAULT_DSCTO_BASE_IMP.doubleValue())))
            .andExpect(jsonPath("$.[*].igvIpm").value(hasItem(DEFAULT_IGV_IPM.doubleValue())))
            .andExpect(jsonPath("$.[*].dsctoIgvIpm").value(hasItem(DEFAULT_DSCTO_IGV_IPM.doubleValue())))
            .andExpect(jsonPath("$.[*].impOpeExo").value(hasItem(DEFAULT_IMP_OPE_EXO.doubleValue())))
            .andExpect(jsonPath("$.[*].impTotOpeInafec").value(hasItem(DEFAULT_IMP_TOT_OPE_INAFEC.doubleValue())))
            .andExpect(jsonPath("$.[*].impSecCons").value(hasItem(DEFAULT_IMP_SEC_CONS.doubleValue())))
            .andExpect(jsonPath("$.[*].baseImpArroz").value(hasItem(DEFAULT_BASE_IMP_ARROZ.doubleValue())))
            .andExpect(jsonPath("$.[*].impVentArroz").value(hasItem(DEFAULT_IMP_VENT_ARROZ.doubleValue())))
            .andExpect(jsonPath("$.[*].otrosNoBaseImp").value(hasItem(DEFAULT_OTROS_NO_BASE_IMP.doubleValue())))
            .andExpect(jsonPath("$.[*].importeTotalComp").value(hasItem(DEFAULT_IMPORTE_TOTAL_COMP.doubleValue())))
            .andExpect(jsonPath("$.[*].codMoneda").value(hasItem(DEFAULT_COD_MONEDA)))
            .andExpect(jsonPath("$.[*].tipCambio").value(hasItem(DEFAULT_TIP_CAMBIO.doubleValue())))
            .andExpect(jsonPath("$.[*].fecEmiModif").value(hasItem(sameInstant(DEFAULT_FEC_EMI_MODIF))))
            .andExpect(jsonPath("$.[*].tipCompModif").value(hasItem(DEFAULT_TIP_COMP_MODIF)))
            .andExpect(jsonPath("$.[*].nroSerieCompModif").value(hasItem(DEFAULT_NRO_SERIE_COMP_MODIF)))
            .andExpect(jsonPath("$.[*].nroCompModif").value(hasItem(DEFAULT_NRO_COMP_MODIF)))
            .andExpect(jsonPath("$.[*].identContrato").value(hasItem(DEFAULT_IDENT_CONTRATO)))
            .andExpect(jsonPath("$.[*].errTipUno").value(hasItem(DEFAULT_ERR_TIP_UNO)))
            .andExpect(jsonPath("$.[*].indCompVancMp").value(hasItem(DEFAULT_IND_COMP_VANC_MP)))
            .andExpect(jsonPath("$.[*].estadoOperaCancMp").value(hasItem(DEFAULT_ESTADO_OPERA_CANC_MP)))
            .andExpect(jsonPath("$.[*].campoLibre").value(hasItem(DEFAULT_CAMPO_LIBRE)))
            .andExpect(jsonPath("$.[*].formPago").value(hasItem(DEFAULT_FORM_PAGO)))
            .andExpect(jsonPath("$.[*].moneda").value(hasItem(DEFAULT_MONEDA)))
            .andExpect(jsonPath("$.[*].indDel").value(hasItem(DEFAULT_IND_DEL.booleanValue())))
            .andExpect(jsonPath("$.[*].fecCrea").value(hasItem(sameInstant(DEFAULT_FEC_CREA))))
            .andExpect(jsonPath("$.[*].usuCrea").value(hasItem(DEFAULT_USU_CREA)))
            .andExpect(jsonPath("$.[*].ipCrea").value(hasItem(DEFAULT_IP_CREA)))
            .andExpect(jsonPath("$.[*].fecModif").value(hasItem(sameInstant(DEFAULT_FEC_MODIF))))
            .andExpect(jsonPath("$.[*].usuModif").value(hasItem(DEFAULT_USU_MODIF)))
            .andExpect(jsonPath("$.[*].ipModif").value(hasItem(DEFAULT_IP_MODIF)));
    }

    @Test
    void getRegVenta() throws Exception {
        // Initialize the database
        regVentaRepository.save(regVenta);

        // Get the regVenta
        restRegVentaMockMvc
            .perform(get(ENTITY_API_URL_ID, regVenta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(regVenta.getId()))
            .andExpect(jsonPath("$.periodo").value(DEFAULT_PERIODO))
            .andExpect(jsonPath("$.cuo").value(DEFAULT_CUO))
            .andExpect(jsonPath("$.asientContab").value(DEFAULT_ASIENT_CONTAB))
            .andExpect(jsonPath("$.fecEmiComp").value(sameInstant(DEFAULT_FEC_EMI_COMP)))
            .andExpect(jsonPath("$.fecVencComp").value(sameInstant(DEFAULT_FEC_VENC_COMP)))
            .andExpect(jsonPath("$.tipComp").value(DEFAULT_TIP_COMP))
            .andExpect(jsonPath("$.nroSerieComp").value(DEFAULT_NRO_SERIE_COMP))
            .andExpect(jsonPath("$.nroComp").value(DEFAULT_NRO_COMP))
            .andExpect(jsonPath("$.consoDia").value(DEFAULT_CONSO_DIA))
            .andExpect(jsonPath("$.tipDocCli").value(DEFAULT_TIP_DOC_CLI))
            .andExpect(jsonPath("$.nroDocCli").value(DEFAULT_NRO_DOC_CLI))
            .andExpect(jsonPath("$.apeRazSocCli").value(DEFAULT_APE_RAZ_SOC_CLI))
            .andExpect(jsonPath("$.valFacExpor").value(DEFAULT_VAL_FAC_EXPOR.doubleValue()))
            .andExpect(jsonPath("$.baseImpOperGrav").value(DEFAULT_BASE_IMP_OPER_GRAV.doubleValue()))
            .andExpect(jsonPath("$.dsctoBaseImp").value(DEFAULT_DSCTO_BASE_IMP.doubleValue()))
            .andExpect(jsonPath("$.igvIpm").value(DEFAULT_IGV_IPM.doubleValue()))
            .andExpect(jsonPath("$.dsctoIgvIpm").value(DEFAULT_DSCTO_IGV_IPM.doubleValue()))
            .andExpect(jsonPath("$.impOpeExo").value(DEFAULT_IMP_OPE_EXO.doubleValue()))
            .andExpect(jsonPath("$.impTotOpeInafec").value(DEFAULT_IMP_TOT_OPE_INAFEC.doubleValue()))
            .andExpect(jsonPath("$.impSecCons").value(DEFAULT_IMP_SEC_CONS.doubleValue()))
            .andExpect(jsonPath("$.baseImpArroz").value(DEFAULT_BASE_IMP_ARROZ.doubleValue()))
            .andExpect(jsonPath("$.impVentArroz").value(DEFAULT_IMP_VENT_ARROZ.doubleValue()))
            .andExpect(jsonPath("$.otrosNoBaseImp").value(DEFAULT_OTROS_NO_BASE_IMP.doubleValue()))
            .andExpect(jsonPath("$.importeTotalComp").value(DEFAULT_IMPORTE_TOTAL_COMP.doubleValue()))
            .andExpect(jsonPath("$.codMoneda").value(DEFAULT_COD_MONEDA))
            .andExpect(jsonPath("$.tipCambio").value(DEFAULT_TIP_CAMBIO.doubleValue()))
            .andExpect(jsonPath("$.fecEmiModif").value(sameInstant(DEFAULT_FEC_EMI_MODIF)))
            .andExpect(jsonPath("$.tipCompModif").value(DEFAULT_TIP_COMP_MODIF))
            .andExpect(jsonPath("$.nroSerieCompModif").value(DEFAULT_NRO_SERIE_COMP_MODIF))
            .andExpect(jsonPath("$.nroCompModif").value(DEFAULT_NRO_COMP_MODIF))
            .andExpect(jsonPath("$.identContrato").value(DEFAULT_IDENT_CONTRATO))
            .andExpect(jsonPath("$.errTipUno").value(DEFAULT_ERR_TIP_UNO))
            .andExpect(jsonPath("$.indCompVancMp").value(DEFAULT_IND_COMP_VANC_MP))
            .andExpect(jsonPath("$.estadoOperaCancMp").value(DEFAULT_ESTADO_OPERA_CANC_MP))
            .andExpect(jsonPath("$.campoLibre").value(DEFAULT_CAMPO_LIBRE))
            .andExpect(jsonPath("$.formPago").value(DEFAULT_FORM_PAGO))
            .andExpect(jsonPath("$.moneda").value(DEFAULT_MONEDA))
            .andExpect(jsonPath("$.indDel").value(DEFAULT_IND_DEL.booleanValue()))
            .andExpect(jsonPath("$.fecCrea").value(sameInstant(DEFAULT_FEC_CREA)))
            .andExpect(jsonPath("$.usuCrea").value(DEFAULT_USU_CREA))
            .andExpect(jsonPath("$.ipCrea").value(DEFAULT_IP_CREA))
            .andExpect(jsonPath("$.fecModif").value(sameInstant(DEFAULT_FEC_MODIF)))
            .andExpect(jsonPath("$.usuModif").value(DEFAULT_USU_MODIF))
            .andExpect(jsonPath("$.ipModif").value(DEFAULT_IP_MODIF));
    }

    @Test
    void getNonExistingRegVenta() throws Exception {
        // Get the regVenta
        restRegVentaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingRegVenta() throws Exception {
        // Initialize the database
        regVentaRepository.save(regVenta);

        int databaseSizeBeforeUpdate = regVentaRepository.findAll().size();

        // Update the regVenta
        RegVenta updatedRegVenta = regVentaRepository.findById(regVenta.getId()).get();
        updatedRegVenta
            .periodo(UPDATED_PERIODO)
            .cuo(UPDATED_CUO)
            .asientContab(UPDATED_ASIENT_CONTAB)
            .fecEmiComp(UPDATED_FEC_EMI_COMP)
            .fecVencComp(UPDATED_FEC_VENC_COMP)
            .tipComp(UPDATED_TIP_COMP)
            .nroSerieComp(UPDATED_NRO_SERIE_COMP)
            .nroComp(UPDATED_NRO_COMP)
            .consoDia(UPDATED_CONSO_DIA)
            .tipDocCli(UPDATED_TIP_DOC_CLI)
            .nroDocCli(UPDATED_NRO_DOC_CLI)
            .apeRazSocCli(UPDATED_APE_RAZ_SOC_CLI)
            .valFacExpor(UPDATED_VAL_FAC_EXPOR)
            .baseImpOperGrav(UPDATED_BASE_IMP_OPER_GRAV)
            .dsctoBaseImp(UPDATED_DSCTO_BASE_IMP)
            .igvIpm(UPDATED_IGV_IPM)
            .dsctoIgvIpm(UPDATED_DSCTO_IGV_IPM)
            .impOpeExo(UPDATED_IMP_OPE_EXO)
            .impTotOpeInafec(UPDATED_IMP_TOT_OPE_INAFEC)
            .impSecCons(UPDATED_IMP_SEC_CONS)
            .baseImpArroz(UPDATED_BASE_IMP_ARROZ)
            .impVentArroz(UPDATED_IMP_VENT_ARROZ)
            .otrosNoBaseImp(UPDATED_OTROS_NO_BASE_IMP)
            .importeTotalComp(UPDATED_IMPORTE_TOTAL_COMP)
            .codMoneda(UPDATED_COD_MONEDA)
            .tipCambio(UPDATED_TIP_CAMBIO)
            .fecEmiModif(UPDATED_FEC_EMI_MODIF)
            .tipCompModif(UPDATED_TIP_COMP_MODIF)
            .nroSerieCompModif(UPDATED_NRO_SERIE_COMP_MODIF)
            .nroCompModif(UPDATED_NRO_COMP_MODIF)
            .identContrato(UPDATED_IDENT_CONTRATO)
            .errTipUno(UPDATED_ERR_TIP_UNO)
            .indCompVancMp(UPDATED_IND_COMP_VANC_MP)
            .estadoOperaCancMp(UPDATED_ESTADO_OPERA_CANC_MP)
            .campoLibre(UPDATED_CAMPO_LIBRE)
            .formPago(UPDATED_FORM_PAGO)
            .moneda(UPDATED_MONEDA)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restRegVentaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRegVenta.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRegVenta))
            )
            .andExpect(status().isOk());

        // Validate the RegVenta in the database
        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeUpdate);
        RegVenta testRegVenta = regVentaList.get(regVentaList.size() - 1);
        assertThat(testRegVenta.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testRegVenta.getCuo()).isEqualTo(UPDATED_CUO);
        assertThat(testRegVenta.getAsientContab()).isEqualTo(UPDATED_ASIENT_CONTAB);
        assertThat(testRegVenta.getFecEmiComp()).isEqualTo(UPDATED_FEC_EMI_COMP);
        assertThat(testRegVenta.getFecVencComp()).isEqualTo(UPDATED_FEC_VENC_COMP);
        assertThat(testRegVenta.getTipComp()).isEqualTo(UPDATED_TIP_COMP);
        assertThat(testRegVenta.getNroSerieComp()).isEqualTo(UPDATED_NRO_SERIE_COMP);
        assertThat(testRegVenta.getNroComp()).isEqualTo(UPDATED_NRO_COMP);
        assertThat(testRegVenta.getConsoDia()).isEqualTo(UPDATED_CONSO_DIA);
        assertThat(testRegVenta.getTipDocCli()).isEqualTo(UPDATED_TIP_DOC_CLI);
        assertThat(testRegVenta.getNroDocCli()).isEqualTo(UPDATED_NRO_DOC_CLI);
        assertThat(testRegVenta.getApeRazSocCli()).isEqualTo(UPDATED_APE_RAZ_SOC_CLI);
        assertThat(testRegVenta.getValFacExpor()).isEqualTo(UPDATED_VAL_FAC_EXPOR);
        assertThat(testRegVenta.getBaseImpOperGrav()).isEqualTo(UPDATED_BASE_IMP_OPER_GRAV);
        assertThat(testRegVenta.getDsctoBaseImp()).isEqualTo(UPDATED_DSCTO_BASE_IMP);
        assertThat(testRegVenta.getIgvIpm()).isEqualTo(UPDATED_IGV_IPM);
        assertThat(testRegVenta.getDsctoIgvIpm()).isEqualTo(UPDATED_DSCTO_IGV_IPM);
        assertThat(testRegVenta.getImpOpeExo()).isEqualTo(UPDATED_IMP_OPE_EXO);
        assertThat(testRegVenta.getImpTotOpeInafec()).isEqualTo(UPDATED_IMP_TOT_OPE_INAFEC);
        assertThat(testRegVenta.getImpSecCons()).isEqualTo(UPDATED_IMP_SEC_CONS);
        assertThat(testRegVenta.getBaseImpArroz()).isEqualTo(UPDATED_BASE_IMP_ARROZ);
        assertThat(testRegVenta.getImpVentArroz()).isEqualTo(UPDATED_IMP_VENT_ARROZ);
        assertThat(testRegVenta.getOtrosNoBaseImp()).isEqualTo(UPDATED_OTROS_NO_BASE_IMP);
        assertThat(testRegVenta.getImporteTotalComp()).isEqualTo(UPDATED_IMPORTE_TOTAL_COMP);
        assertThat(testRegVenta.getCodMoneda()).isEqualTo(UPDATED_COD_MONEDA);
        assertThat(testRegVenta.getTipCambio()).isEqualTo(UPDATED_TIP_CAMBIO);
        assertThat(testRegVenta.getFecEmiModif()).isEqualTo(UPDATED_FEC_EMI_MODIF);
        assertThat(testRegVenta.getTipCompModif()).isEqualTo(UPDATED_TIP_COMP_MODIF);
        assertThat(testRegVenta.getNroSerieCompModif()).isEqualTo(UPDATED_NRO_SERIE_COMP_MODIF);
        assertThat(testRegVenta.getNroCompModif()).isEqualTo(UPDATED_NRO_COMP_MODIF);
        assertThat(testRegVenta.getIdentContrato()).isEqualTo(UPDATED_IDENT_CONTRATO);
        assertThat(testRegVenta.getErrTipUno()).isEqualTo(UPDATED_ERR_TIP_UNO);
        assertThat(testRegVenta.getIndCompVancMp()).isEqualTo(UPDATED_IND_COMP_VANC_MP);
        assertThat(testRegVenta.getEstadoOperaCancMp()).isEqualTo(UPDATED_ESTADO_OPERA_CANC_MP);
        assertThat(testRegVenta.getCampoLibre()).isEqualTo(UPDATED_CAMPO_LIBRE);
        assertThat(testRegVenta.getFormPago()).isEqualTo(UPDATED_FORM_PAGO);
        assertThat(testRegVenta.getMoneda()).isEqualTo(UPDATED_MONEDA);
        assertThat(testRegVenta.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testRegVenta.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testRegVenta.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testRegVenta.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testRegVenta.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testRegVenta.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testRegVenta.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingRegVenta() throws Exception {
        int databaseSizeBeforeUpdate = regVentaRepository.findAll().size();
        regVenta.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegVentaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, regVenta.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(regVenta))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegVenta in the database
        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchRegVenta() throws Exception {
        int databaseSizeBeforeUpdate = regVentaRepository.findAll().size();
        regVenta.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegVentaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(regVenta))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegVenta in the database
        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamRegVenta() throws Exception {
        int databaseSizeBeforeUpdate = regVentaRepository.findAll().size();
        regVenta.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegVentaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RegVenta in the database
        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateRegVentaWithPatch() throws Exception {
        // Initialize the database
        regVentaRepository.save(regVenta);

        int databaseSizeBeforeUpdate = regVentaRepository.findAll().size();

        // Update the regVenta using partial update
        RegVenta partialUpdatedRegVenta = new RegVenta();
        partialUpdatedRegVenta.setId(regVenta.getId());

        partialUpdatedRegVenta
            .asientContab(UPDATED_ASIENT_CONTAB)
            .fecEmiComp(UPDATED_FEC_EMI_COMP)
            .nroSerieComp(UPDATED_NRO_SERIE_COMP)
            .nroComp(UPDATED_NRO_COMP)
            .tipDocCli(UPDATED_TIP_DOC_CLI)
            .nroDocCli(UPDATED_NRO_DOC_CLI)
            .valFacExpor(UPDATED_VAL_FAC_EXPOR)
            .baseImpOperGrav(UPDATED_BASE_IMP_OPER_GRAV)
            .dsctoBaseImp(UPDATED_DSCTO_BASE_IMP)
            .dsctoIgvIpm(UPDATED_DSCTO_IGV_IPM)
            .impTotOpeInafec(UPDATED_IMP_TOT_OPE_INAFEC)
            .impSecCons(UPDATED_IMP_SEC_CONS)
            .baseImpArroz(UPDATED_BASE_IMP_ARROZ)
            .impVentArroz(UPDATED_IMP_VENT_ARROZ)
            .otrosNoBaseImp(UPDATED_OTROS_NO_BASE_IMP)
            .importeTotalComp(UPDATED_IMPORTE_TOTAL_COMP)
            .codMoneda(UPDATED_COD_MONEDA)
            .tipCompModif(UPDATED_TIP_COMP_MODIF)
            .nroCompModif(UPDATED_NRO_COMP_MODIF)
            .identContrato(UPDATED_IDENT_CONTRATO)
            .campoLibre(UPDATED_CAMPO_LIBRE)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .usuModif(UPDATED_USU_MODIF);

        restRegVentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRegVenta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRegVenta))
            )
            .andExpect(status().isOk());

        // Validate the RegVenta in the database
        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeUpdate);
        RegVenta testRegVenta = regVentaList.get(regVentaList.size() - 1);
        assertThat(testRegVenta.getPeriodo()).isEqualTo(DEFAULT_PERIODO);
        assertThat(testRegVenta.getCuo()).isEqualTo(DEFAULT_CUO);
        assertThat(testRegVenta.getAsientContab()).isEqualTo(UPDATED_ASIENT_CONTAB);
        assertThat(testRegVenta.getFecEmiComp()).isEqualTo(UPDATED_FEC_EMI_COMP);
        assertThat(testRegVenta.getFecVencComp()).isEqualTo(DEFAULT_FEC_VENC_COMP);
        assertThat(testRegVenta.getTipComp()).isEqualTo(DEFAULT_TIP_COMP);
        assertThat(testRegVenta.getNroSerieComp()).isEqualTo(UPDATED_NRO_SERIE_COMP);
        assertThat(testRegVenta.getNroComp()).isEqualTo(UPDATED_NRO_COMP);
        assertThat(testRegVenta.getConsoDia()).isEqualTo(DEFAULT_CONSO_DIA);
        assertThat(testRegVenta.getTipDocCli()).isEqualTo(UPDATED_TIP_DOC_CLI);
        assertThat(testRegVenta.getNroDocCli()).isEqualTo(UPDATED_NRO_DOC_CLI);
        assertThat(testRegVenta.getApeRazSocCli()).isEqualTo(DEFAULT_APE_RAZ_SOC_CLI);
        assertThat(testRegVenta.getValFacExpor()).isEqualTo(UPDATED_VAL_FAC_EXPOR);
        assertThat(testRegVenta.getBaseImpOperGrav()).isEqualTo(UPDATED_BASE_IMP_OPER_GRAV);
        assertThat(testRegVenta.getDsctoBaseImp()).isEqualTo(UPDATED_DSCTO_BASE_IMP);
        assertThat(testRegVenta.getIgvIpm()).isEqualTo(DEFAULT_IGV_IPM);
        assertThat(testRegVenta.getDsctoIgvIpm()).isEqualTo(UPDATED_DSCTO_IGV_IPM);
        assertThat(testRegVenta.getImpOpeExo()).isEqualTo(DEFAULT_IMP_OPE_EXO);
        assertThat(testRegVenta.getImpTotOpeInafec()).isEqualTo(UPDATED_IMP_TOT_OPE_INAFEC);
        assertThat(testRegVenta.getImpSecCons()).isEqualTo(UPDATED_IMP_SEC_CONS);
        assertThat(testRegVenta.getBaseImpArroz()).isEqualTo(UPDATED_BASE_IMP_ARROZ);
        assertThat(testRegVenta.getImpVentArroz()).isEqualTo(UPDATED_IMP_VENT_ARROZ);
        assertThat(testRegVenta.getOtrosNoBaseImp()).isEqualTo(UPDATED_OTROS_NO_BASE_IMP);
        assertThat(testRegVenta.getImporteTotalComp()).isEqualTo(UPDATED_IMPORTE_TOTAL_COMP);
        assertThat(testRegVenta.getCodMoneda()).isEqualTo(UPDATED_COD_MONEDA);
        assertThat(testRegVenta.getTipCambio()).isEqualTo(DEFAULT_TIP_CAMBIO);
        assertThat(testRegVenta.getFecEmiModif()).isEqualTo(DEFAULT_FEC_EMI_MODIF);
        assertThat(testRegVenta.getTipCompModif()).isEqualTo(UPDATED_TIP_COMP_MODIF);
        assertThat(testRegVenta.getNroSerieCompModif()).isEqualTo(DEFAULT_NRO_SERIE_COMP_MODIF);
        assertThat(testRegVenta.getNroCompModif()).isEqualTo(UPDATED_NRO_COMP_MODIF);
        assertThat(testRegVenta.getIdentContrato()).isEqualTo(UPDATED_IDENT_CONTRATO);
        assertThat(testRegVenta.getErrTipUno()).isEqualTo(DEFAULT_ERR_TIP_UNO);
        assertThat(testRegVenta.getIndCompVancMp()).isEqualTo(DEFAULT_IND_COMP_VANC_MP);
        assertThat(testRegVenta.getEstadoOperaCancMp()).isEqualTo(DEFAULT_ESTADO_OPERA_CANC_MP);
        assertThat(testRegVenta.getCampoLibre()).isEqualTo(UPDATED_CAMPO_LIBRE);
        assertThat(testRegVenta.getFormPago()).isEqualTo(DEFAULT_FORM_PAGO);
        assertThat(testRegVenta.getMoneda()).isEqualTo(DEFAULT_MONEDA);
        assertThat(testRegVenta.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testRegVenta.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testRegVenta.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testRegVenta.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testRegVenta.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testRegVenta.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testRegVenta.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void fullUpdateRegVentaWithPatch() throws Exception {
        // Initialize the database
        regVentaRepository.save(regVenta);

        int databaseSizeBeforeUpdate = regVentaRepository.findAll().size();

        // Update the regVenta using partial update
        RegVenta partialUpdatedRegVenta = new RegVenta();
        partialUpdatedRegVenta.setId(regVenta.getId());

        partialUpdatedRegVenta
            .periodo(UPDATED_PERIODO)
            .cuo(UPDATED_CUO)
            .asientContab(UPDATED_ASIENT_CONTAB)
            .fecEmiComp(UPDATED_FEC_EMI_COMP)
            .fecVencComp(UPDATED_FEC_VENC_COMP)
            .tipComp(UPDATED_TIP_COMP)
            .nroSerieComp(UPDATED_NRO_SERIE_COMP)
            .nroComp(UPDATED_NRO_COMP)
            .consoDia(UPDATED_CONSO_DIA)
            .tipDocCli(UPDATED_TIP_DOC_CLI)
            .nroDocCli(UPDATED_NRO_DOC_CLI)
            .apeRazSocCli(UPDATED_APE_RAZ_SOC_CLI)
            .valFacExpor(UPDATED_VAL_FAC_EXPOR)
            .baseImpOperGrav(UPDATED_BASE_IMP_OPER_GRAV)
            .dsctoBaseImp(UPDATED_DSCTO_BASE_IMP)
            .igvIpm(UPDATED_IGV_IPM)
            .dsctoIgvIpm(UPDATED_DSCTO_IGV_IPM)
            .impOpeExo(UPDATED_IMP_OPE_EXO)
            .impTotOpeInafec(UPDATED_IMP_TOT_OPE_INAFEC)
            .impSecCons(UPDATED_IMP_SEC_CONS)
            .baseImpArroz(UPDATED_BASE_IMP_ARROZ)
            .impVentArroz(UPDATED_IMP_VENT_ARROZ)
            .otrosNoBaseImp(UPDATED_OTROS_NO_BASE_IMP)
            .importeTotalComp(UPDATED_IMPORTE_TOTAL_COMP)
            .codMoneda(UPDATED_COD_MONEDA)
            .tipCambio(UPDATED_TIP_CAMBIO)
            .fecEmiModif(UPDATED_FEC_EMI_MODIF)
            .tipCompModif(UPDATED_TIP_COMP_MODIF)
            .nroSerieCompModif(UPDATED_NRO_SERIE_COMP_MODIF)
            .nroCompModif(UPDATED_NRO_COMP_MODIF)
            .identContrato(UPDATED_IDENT_CONTRATO)
            .errTipUno(UPDATED_ERR_TIP_UNO)
            .indCompVancMp(UPDATED_IND_COMP_VANC_MP)
            .estadoOperaCancMp(UPDATED_ESTADO_OPERA_CANC_MP)
            .campoLibre(UPDATED_CAMPO_LIBRE)
            .formPago(UPDATED_FORM_PAGO)
            .moneda(UPDATED_MONEDA)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restRegVentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRegVenta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRegVenta))
            )
            .andExpect(status().isOk());

        // Validate the RegVenta in the database
        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeUpdate);
        RegVenta testRegVenta = regVentaList.get(regVentaList.size() - 1);
        assertThat(testRegVenta.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testRegVenta.getCuo()).isEqualTo(UPDATED_CUO);
        assertThat(testRegVenta.getAsientContab()).isEqualTo(UPDATED_ASIENT_CONTAB);
        assertThat(testRegVenta.getFecEmiComp()).isEqualTo(UPDATED_FEC_EMI_COMP);
        assertThat(testRegVenta.getFecVencComp()).isEqualTo(UPDATED_FEC_VENC_COMP);
        assertThat(testRegVenta.getTipComp()).isEqualTo(UPDATED_TIP_COMP);
        assertThat(testRegVenta.getNroSerieComp()).isEqualTo(UPDATED_NRO_SERIE_COMP);
        assertThat(testRegVenta.getNroComp()).isEqualTo(UPDATED_NRO_COMP);
        assertThat(testRegVenta.getConsoDia()).isEqualTo(UPDATED_CONSO_DIA);
        assertThat(testRegVenta.getTipDocCli()).isEqualTo(UPDATED_TIP_DOC_CLI);
        assertThat(testRegVenta.getNroDocCli()).isEqualTo(UPDATED_NRO_DOC_CLI);
        assertThat(testRegVenta.getApeRazSocCli()).isEqualTo(UPDATED_APE_RAZ_SOC_CLI);
        assertThat(testRegVenta.getValFacExpor()).isEqualTo(UPDATED_VAL_FAC_EXPOR);
        assertThat(testRegVenta.getBaseImpOperGrav()).isEqualTo(UPDATED_BASE_IMP_OPER_GRAV);
        assertThat(testRegVenta.getDsctoBaseImp()).isEqualTo(UPDATED_DSCTO_BASE_IMP);
        assertThat(testRegVenta.getIgvIpm()).isEqualTo(UPDATED_IGV_IPM);
        assertThat(testRegVenta.getDsctoIgvIpm()).isEqualTo(UPDATED_DSCTO_IGV_IPM);
        assertThat(testRegVenta.getImpOpeExo()).isEqualTo(UPDATED_IMP_OPE_EXO);
        assertThat(testRegVenta.getImpTotOpeInafec()).isEqualTo(UPDATED_IMP_TOT_OPE_INAFEC);
        assertThat(testRegVenta.getImpSecCons()).isEqualTo(UPDATED_IMP_SEC_CONS);
        assertThat(testRegVenta.getBaseImpArroz()).isEqualTo(UPDATED_BASE_IMP_ARROZ);
        assertThat(testRegVenta.getImpVentArroz()).isEqualTo(UPDATED_IMP_VENT_ARROZ);
        assertThat(testRegVenta.getOtrosNoBaseImp()).isEqualTo(UPDATED_OTROS_NO_BASE_IMP);
        assertThat(testRegVenta.getImporteTotalComp()).isEqualTo(UPDATED_IMPORTE_TOTAL_COMP);
        assertThat(testRegVenta.getCodMoneda()).isEqualTo(UPDATED_COD_MONEDA);
        assertThat(testRegVenta.getTipCambio()).isEqualTo(UPDATED_TIP_CAMBIO);
        assertThat(testRegVenta.getFecEmiModif()).isEqualTo(UPDATED_FEC_EMI_MODIF);
        assertThat(testRegVenta.getTipCompModif()).isEqualTo(UPDATED_TIP_COMP_MODIF);
        assertThat(testRegVenta.getNroSerieCompModif()).isEqualTo(UPDATED_NRO_SERIE_COMP_MODIF);
        assertThat(testRegVenta.getNroCompModif()).isEqualTo(UPDATED_NRO_COMP_MODIF);
        assertThat(testRegVenta.getIdentContrato()).isEqualTo(UPDATED_IDENT_CONTRATO);
        assertThat(testRegVenta.getErrTipUno()).isEqualTo(UPDATED_ERR_TIP_UNO);
        assertThat(testRegVenta.getIndCompVancMp()).isEqualTo(UPDATED_IND_COMP_VANC_MP);
        assertThat(testRegVenta.getEstadoOperaCancMp()).isEqualTo(UPDATED_ESTADO_OPERA_CANC_MP);
        assertThat(testRegVenta.getCampoLibre()).isEqualTo(UPDATED_CAMPO_LIBRE);
        assertThat(testRegVenta.getFormPago()).isEqualTo(UPDATED_FORM_PAGO);
        assertThat(testRegVenta.getMoneda()).isEqualTo(UPDATED_MONEDA);
        assertThat(testRegVenta.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testRegVenta.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testRegVenta.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testRegVenta.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testRegVenta.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testRegVenta.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testRegVenta.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingRegVenta() throws Exception {
        int databaseSizeBeforeUpdate = regVentaRepository.findAll().size();
        regVenta.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegVentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, regVenta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(regVenta))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegVenta in the database
        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchRegVenta() throws Exception {
        int databaseSizeBeforeUpdate = regVentaRepository.findAll().size();
        regVenta.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegVentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(regVenta))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegVenta in the database
        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamRegVenta() throws Exception {
        int databaseSizeBeforeUpdate = regVentaRepository.findAll().size();
        regVenta.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegVentaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(regVenta)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RegVenta in the database
        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteRegVenta() throws Exception {
        // Initialize the database
        regVentaRepository.save(regVenta);

        int databaseSizeBeforeDelete = regVentaRepository.findAll().size();

        // Delete the regVenta
        restRegVentaMockMvc
            .perform(delete(ENTITY_API_URL_ID, regVenta.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RegVenta> regVentaList = regVentaRepository.findAll();
        assertThat(regVentaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
