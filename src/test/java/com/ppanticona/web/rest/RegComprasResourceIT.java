package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.RegCompras;
import com.ppanticona.repository.RegComprasRepository;
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
 * Integration tests for the {@link RegComprasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RegComprasResourceIT {

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

    private static final String DEFAULT_ANHO_EMISION_DUA = "AAAAAAAAAA";
    private static final String UPDATED_ANHO_EMISION_DUA = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_COMP = "AAAAAAAAAA";
    private static final String UPDATED_NRO_COMP = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_FINAL = "AAAAAAAAAA";
    private static final String UPDATED_NRO_FINAL = "BBBBBBBBBB";

    private static final String DEFAULT_TIP_DOC_PROV = "AAAAAAAAAA";
    private static final String UPDATED_TIP_DOC_PROV = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_DOC_PROV = "AAAAAAAAAA";
    private static final String UPDATED_NRO_DOC_PROV = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_APE_RAZ_SOC_PROV = "AAAAAAAAAA";
    private static final String UPDATED_NOM_APE_RAZ_SOC_PROV = "BBBBBBBBBB";

    private static final Double DEFAULT_BASE_IMPONIBLE = 1D;
    private static final Double UPDATED_BASE_IMPONIBLE = 2D;

    private static final Double DEFAULT_MONTO_IGV = 1D;
    private static final Double UPDATED_MONTO_IGV = 2D;

    private static final Double DEFAULT_BASE_IMPONIBLE_2 = 1D;
    private static final Double UPDATED_BASE_IMPONIBLE_2 = 2D;

    private static final Double DEFAULT_MONTO_IGV_2 = 1D;
    private static final Double UPDATED_MONTO_IGV_2 = 2D;

    private static final Double DEFAULT_BASE_IMPONIBLE_3 = 1D;
    private static final Double UPDATED_BASE_IMPONIBLE_3 = 2D;

    private static final Double DEFAULT_MONTO_IGV_3 = 1D;
    private static final Double UPDATED_MONTO_IGV_3 = 2D;

    private static final Double DEFAULT_MONTO_NO_GRAVADO = 1D;
    private static final Double UPDATED_MONTO_NO_GRAVADO = 2D;

    private static final Double DEFAULT_MONTO_ISC = 1D;
    private static final Double UPDATED_MONTO_ISC = 2D;

    private static final Double DEFAULT_IMP_CONS_BOLS = 1D;
    private static final Double UPDATED_IMP_CONS_BOLS = 2D;

    private static final Double DEFAULT_OTROS_CARGOS = 1D;
    private static final Double UPDATED_OTROS_CARGOS = 2D;

    private static final Double DEFAULT_IMPORTE_TOTAL = 1D;
    private static final Double UPDATED_IMPORTE_TOTAL = 2D;

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

    private static final String DEFAULT_COD_DUA_REF = "AAAAAAAAAA";
    private static final String UPDATED_COD_DUA_REF = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_COMP_MODIF = "AAAAAAAAAA";
    private static final String UPDATED_NRO_COMP_MODIF = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_EMI_DETRACC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_EMI_DETRACC = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_NRO_CONST_DETRACC = "AAAAAAAAAA";
    private static final String UPDATED_NRO_CONST_DETRACC = "BBBBBBBBBB";

    private static final String DEFAULT_IND_COMP_SUJ_RETENC = "AAAAAAAAAA";
    private static final String UPDATED_IND_COMP_SUJ_RETENC = "BBBBBBBBBB";

    private static final String DEFAULT_CLAS_BIENESY_SERVICIOS = "AAAAAAAAAA";
    private static final String UPDATED_CLAS_BIENESY_SERVICIOS = "BBBBBBBBBB";

    private static final String DEFAULT_IDENT_CONTRATO = "AAAAAAAAAA";
    private static final String UPDATED_IDENT_CONTRATO = "BBBBBBBBBB";

    private static final String DEFAULT_ERR_TIP_UNO = "AAAAAAAAAA";
    private static final String UPDATED_ERR_TIP_UNO = "BBBBBBBBBB";

    private static final String DEFAULT_ERR_TIP_DOS = "AAAAAAAAAA";
    private static final String UPDATED_ERR_TIP_DOS = "BBBBBBBBBB";

    private static final String DEFAULT_ERR_TIP_TRES = "AAAAAAAAAA";
    private static final String UPDATED_ERR_TIP_TRES = "BBBBBBBBBB";

    private static final String DEFAULT_ERR_TIP_CUATRO = "AAAAAAAAAA";
    private static final String UPDATED_ERR_TIP_CUATRO = "BBBBBBBBBB";

    private static final String DEFAULT_IND_COMP_PAGO_MED_PAGO = "AAAAAAAAAA";
    private static final String UPDATED_IND_COMP_PAGO_MED_PAGO = "BBBBBBBBBB";

    private static final Integer DEFAULT_ESTADO = 1;
    private static final Integer UPDATED_ESTADO = 2;

    private static final String DEFAULT_CAMPO_LIBRE = "AAAAAAAAAA";
    private static final String UPDATED_CAMPO_LIBRE = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/reg-compras";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private RegComprasRepository regComprasRepository;

    @Autowired
    private MockMvc restRegComprasMockMvc;

    private RegCompras regCompras;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegCompras createEntity() {
        RegCompras regCompras = new RegCompras()
            .periodo(DEFAULT_PERIODO)
            .cuo(DEFAULT_CUO)
            .asientContab(DEFAULT_ASIENT_CONTAB)
            .fecEmiComp(DEFAULT_FEC_EMI_COMP)
            .fecVencComp(DEFAULT_FEC_VENC_COMP)
            .tipComp(DEFAULT_TIP_COMP)
            .nroSerieComp(DEFAULT_NRO_SERIE_COMP)
            .anhoEmisionDua(DEFAULT_ANHO_EMISION_DUA)
            .nroComp(DEFAULT_NRO_COMP)
            .nroFinal(DEFAULT_NRO_FINAL)
            .tipDocProv(DEFAULT_TIP_DOC_PROV)
            .nroDocProv(DEFAULT_NRO_DOC_PROV)
            .nomApeRazSocProv(DEFAULT_NOM_APE_RAZ_SOC_PROV)
            .baseImponible(DEFAULT_BASE_IMPONIBLE)
            .montoIgv(DEFAULT_MONTO_IGV)
            .baseImponible2(DEFAULT_BASE_IMPONIBLE_2)
            .montoIgv2(DEFAULT_MONTO_IGV_2)
            .baseImponible3(DEFAULT_BASE_IMPONIBLE_3)
            .montoIgv3(DEFAULT_MONTO_IGV_3)
            .montoNoGravado(DEFAULT_MONTO_NO_GRAVADO)
            .montoIsc(DEFAULT_MONTO_ISC)
            .impConsBols(DEFAULT_IMP_CONS_BOLS)
            .otrosCargos(DEFAULT_OTROS_CARGOS)
            .importeTotal(DEFAULT_IMPORTE_TOTAL)
            .codMoneda(DEFAULT_COD_MONEDA)
            .tipCambio(DEFAULT_TIP_CAMBIO)
            .fecEmiModif(DEFAULT_FEC_EMI_MODIF)
            .tipCompModif(DEFAULT_TIP_COMP_MODIF)
            .nroSerieCompModif(DEFAULT_NRO_SERIE_COMP_MODIF)
            .codDuaRef(DEFAULT_COD_DUA_REF)
            .nroCompModif(DEFAULT_NRO_COMP_MODIF)
            .fecEmiDetracc(DEFAULT_FEC_EMI_DETRACC)
            .nroConstDetracc(DEFAULT_NRO_CONST_DETRACC)
            .indCompSujRetenc(DEFAULT_IND_COMP_SUJ_RETENC)
            .clasBienesyServicios(DEFAULT_CLAS_BIENESY_SERVICIOS)
            .identContrato(DEFAULT_IDENT_CONTRATO)
            .errTipUno(DEFAULT_ERR_TIP_UNO)
            .errTipDos(DEFAULT_ERR_TIP_DOS)
            .errTipTres(DEFAULT_ERR_TIP_TRES)
            .errTipCuatro(DEFAULT_ERR_TIP_CUATRO)
            .indCompPagoMedPago(DEFAULT_IND_COMP_PAGO_MED_PAGO)
            .estado(DEFAULT_ESTADO)
            .campoLibre(DEFAULT_CAMPO_LIBRE)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return regCompras;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegCompras createUpdatedEntity() {
        RegCompras regCompras = new RegCompras()
            .periodo(UPDATED_PERIODO)
            .cuo(UPDATED_CUO)
            .asientContab(UPDATED_ASIENT_CONTAB)
            .fecEmiComp(UPDATED_FEC_EMI_COMP)
            .fecVencComp(UPDATED_FEC_VENC_COMP)
            .tipComp(UPDATED_TIP_COMP)
            .nroSerieComp(UPDATED_NRO_SERIE_COMP)
            .anhoEmisionDua(UPDATED_ANHO_EMISION_DUA)
            .nroComp(UPDATED_NRO_COMP)
            .nroFinal(UPDATED_NRO_FINAL)
            .tipDocProv(UPDATED_TIP_DOC_PROV)
            .nroDocProv(UPDATED_NRO_DOC_PROV)
            .nomApeRazSocProv(UPDATED_NOM_APE_RAZ_SOC_PROV)
            .baseImponible(UPDATED_BASE_IMPONIBLE)
            .montoIgv(UPDATED_MONTO_IGV)
            .baseImponible2(UPDATED_BASE_IMPONIBLE_2)
            .montoIgv2(UPDATED_MONTO_IGV_2)
            .baseImponible3(UPDATED_BASE_IMPONIBLE_3)
            .montoIgv3(UPDATED_MONTO_IGV_3)
            .montoNoGravado(UPDATED_MONTO_NO_GRAVADO)
            .montoIsc(UPDATED_MONTO_ISC)
            .impConsBols(UPDATED_IMP_CONS_BOLS)
            .otrosCargos(UPDATED_OTROS_CARGOS)
            .importeTotal(UPDATED_IMPORTE_TOTAL)
            .codMoneda(UPDATED_COD_MONEDA)
            .tipCambio(UPDATED_TIP_CAMBIO)
            .fecEmiModif(UPDATED_FEC_EMI_MODIF)
            .tipCompModif(UPDATED_TIP_COMP_MODIF)
            .nroSerieCompModif(UPDATED_NRO_SERIE_COMP_MODIF)
            .codDuaRef(UPDATED_COD_DUA_REF)
            .nroCompModif(UPDATED_NRO_COMP_MODIF)
            .fecEmiDetracc(UPDATED_FEC_EMI_DETRACC)
            .nroConstDetracc(UPDATED_NRO_CONST_DETRACC)
            .indCompSujRetenc(UPDATED_IND_COMP_SUJ_RETENC)
            .clasBienesyServicios(UPDATED_CLAS_BIENESY_SERVICIOS)
            .identContrato(UPDATED_IDENT_CONTRATO)
            .errTipUno(UPDATED_ERR_TIP_UNO)
            .errTipDos(UPDATED_ERR_TIP_DOS)
            .errTipTres(UPDATED_ERR_TIP_TRES)
            .errTipCuatro(UPDATED_ERR_TIP_CUATRO)
            .indCompPagoMedPago(UPDATED_IND_COMP_PAGO_MED_PAGO)
            .estado(UPDATED_ESTADO)
            .campoLibre(UPDATED_CAMPO_LIBRE)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return regCompras;
    }

    @BeforeEach
    public void initTest() {
        regComprasRepository.deleteAll();
        regCompras = createEntity();
    }

    @Test
    void createRegCompras() throws Exception {
        int databaseSizeBeforeCreate = regComprasRepository.findAll().size();
        // Create the RegCompras
        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isCreated());

        // Validate the RegCompras in the database
        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeCreate + 1);
        RegCompras testRegCompras = regComprasList.get(regComprasList.size() - 1);
        assertThat(testRegCompras.getPeriodo()).isEqualTo(DEFAULT_PERIODO);
        assertThat(testRegCompras.getCuo()).isEqualTo(DEFAULT_CUO);
        assertThat(testRegCompras.getAsientContab()).isEqualTo(DEFAULT_ASIENT_CONTAB);
        assertThat(testRegCompras.getFecEmiComp()).isEqualTo(DEFAULT_FEC_EMI_COMP);
        assertThat(testRegCompras.getFecVencComp()).isEqualTo(DEFAULT_FEC_VENC_COMP);
        assertThat(testRegCompras.getTipComp()).isEqualTo(DEFAULT_TIP_COMP);
        assertThat(testRegCompras.getNroSerieComp()).isEqualTo(DEFAULT_NRO_SERIE_COMP);
        assertThat(testRegCompras.getAnhoEmisionDua()).isEqualTo(DEFAULT_ANHO_EMISION_DUA);
        assertThat(testRegCompras.getNroComp()).isEqualTo(DEFAULT_NRO_COMP);
        assertThat(testRegCompras.getNroFinal()).isEqualTo(DEFAULT_NRO_FINAL);
        assertThat(testRegCompras.getTipDocProv()).isEqualTo(DEFAULT_TIP_DOC_PROV);
        assertThat(testRegCompras.getNroDocProv()).isEqualTo(DEFAULT_NRO_DOC_PROV);
        assertThat(testRegCompras.getNomApeRazSocProv()).isEqualTo(DEFAULT_NOM_APE_RAZ_SOC_PROV);
        assertThat(testRegCompras.getBaseImponible()).isEqualTo(DEFAULT_BASE_IMPONIBLE);
        assertThat(testRegCompras.getMontoIgv()).isEqualTo(DEFAULT_MONTO_IGV);
        assertThat(testRegCompras.getBaseImponible2()).isEqualTo(DEFAULT_BASE_IMPONIBLE_2);
        assertThat(testRegCompras.getMontoIgv2()).isEqualTo(DEFAULT_MONTO_IGV_2);
        assertThat(testRegCompras.getBaseImponible3()).isEqualTo(DEFAULT_BASE_IMPONIBLE_3);
        assertThat(testRegCompras.getMontoIgv3()).isEqualTo(DEFAULT_MONTO_IGV_3);
        assertThat(testRegCompras.getMontoNoGravado()).isEqualTo(DEFAULT_MONTO_NO_GRAVADO);
        assertThat(testRegCompras.getMontoIsc()).isEqualTo(DEFAULT_MONTO_ISC);
        assertThat(testRegCompras.getImpConsBols()).isEqualTo(DEFAULT_IMP_CONS_BOLS);
        assertThat(testRegCompras.getOtrosCargos()).isEqualTo(DEFAULT_OTROS_CARGOS);
        assertThat(testRegCompras.getImporteTotal()).isEqualTo(DEFAULT_IMPORTE_TOTAL);
        assertThat(testRegCompras.getCodMoneda()).isEqualTo(DEFAULT_COD_MONEDA);
        assertThat(testRegCompras.getTipCambio()).isEqualTo(DEFAULT_TIP_CAMBIO);
        assertThat(testRegCompras.getFecEmiModif()).isEqualTo(DEFAULT_FEC_EMI_MODIF);
        assertThat(testRegCompras.getTipCompModif()).isEqualTo(DEFAULT_TIP_COMP_MODIF);
        assertThat(testRegCompras.getNroSerieCompModif()).isEqualTo(DEFAULT_NRO_SERIE_COMP_MODIF);
        assertThat(testRegCompras.getCodDuaRef()).isEqualTo(DEFAULT_COD_DUA_REF);
        assertThat(testRegCompras.getNroCompModif()).isEqualTo(DEFAULT_NRO_COMP_MODIF);
        assertThat(testRegCompras.getFecEmiDetracc()).isEqualTo(DEFAULT_FEC_EMI_DETRACC);
        assertThat(testRegCompras.getNroConstDetracc()).isEqualTo(DEFAULT_NRO_CONST_DETRACC);
        assertThat(testRegCompras.getIndCompSujRetenc()).isEqualTo(DEFAULT_IND_COMP_SUJ_RETENC);
        assertThat(testRegCompras.getClasBienesyServicios()).isEqualTo(DEFAULT_CLAS_BIENESY_SERVICIOS);
        assertThat(testRegCompras.getIdentContrato()).isEqualTo(DEFAULT_IDENT_CONTRATO);
        assertThat(testRegCompras.getErrTipUno()).isEqualTo(DEFAULT_ERR_TIP_UNO);
        assertThat(testRegCompras.getErrTipDos()).isEqualTo(DEFAULT_ERR_TIP_DOS);
        assertThat(testRegCompras.getErrTipTres()).isEqualTo(DEFAULT_ERR_TIP_TRES);
        assertThat(testRegCompras.getErrTipCuatro()).isEqualTo(DEFAULT_ERR_TIP_CUATRO);
        assertThat(testRegCompras.getIndCompPagoMedPago()).isEqualTo(DEFAULT_IND_COMP_PAGO_MED_PAGO);
        assertThat(testRegCompras.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testRegCompras.getCampoLibre()).isEqualTo(DEFAULT_CAMPO_LIBRE);
        assertThat(testRegCompras.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testRegCompras.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testRegCompras.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testRegCompras.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testRegCompras.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testRegCompras.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testRegCompras.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createRegComprasWithExistingId() throws Exception {
        // Create the RegCompras with an existing ID
        regCompras.setId("existing_id");

        int databaseSizeBeforeCreate = regComprasRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        // Validate the RegCompras in the database
        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkPeriodoIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setPeriodo(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCuoIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setCuo(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecEmiCompIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setFecEmiComp(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTipCompIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setTipComp(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNroSerieCompIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setNroSerieComp(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNroCompIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setNroComp(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTipDocProvIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setTipDocProv(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNroDocProvIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setNroDocProv(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNomApeRazSocProvIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setNomApeRazSocProv(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBaseImponibleIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setBaseImponible(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMontoIgvIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setMontoIgv(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBaseImponible2IsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setBaseImponible2(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMontoIgv2IsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setMontoIgv2(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMontoIgv3IsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setMontoIgv3(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setIndDel(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setFecCrea(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setUsuCrea(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = regComprasRepository.findAll().size();
        // set the field null
        regCompras.setIpCrea(null);

        // Create the RegCompras, which fails.

        restRegComprasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isBadRequest());

        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllRegCompras() throws Exception {
        // Initialize the database
        regComprasRepository.save(regCompras);

        // Get all the regComprasList
        restRegComprasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(regCompras.getId())))
            .andExpect(jsonPath("$.[*].periodo").value(hasItem(DEFAULT_PERIODO)))
            .andExpect(jsonPath("$.[*].cuo").value(hasItem(DEFAULT_CUO)))
            .andExpect(jsonPath("$.[*].asientContab").value(hasItem(DEFAULT_ASIENT_CONTAB)))
            .andExpect(jsonPath("$.[*].fecEmiComp").value(hasItem(sameInstant(DEFAULT_FEC_EMI_COMP))))
            .andExpect(jsonPath("$.[*].fecVencComp").value(hasItem(sameInstant(DEFAULT_FEC_VENC_COMP))))
            .andExpect(jsonPath("$.[*].tipComp").value(hasItem(DEFAULT_TIP_COMP)))
            .andExpect(jsonPath("$.[*].nroSerieComp").value(hasItem(DEFAULT_NRO_SERIE_COMP)))
            .andExpect(jsonPath("$.[*].anhoEmisionDua").value(hasItem(DEFAULT_ANHO_EMISION_DUA)))
            .andExpect(jsonPath("$.[*].nroComp").value(hasItem(DEFAULT_NRO_COMP)))
            .andExpect(jsonPath("$.[*].nroFinal").value(hasItem(DEFAULT_NRO_FINAL)))
            .andExpect(jsonPath("$.[*].tipDocProv").value(hasItem(DEFAULT_TIP_DOC_PROV)))
            .andExpect(jsonPath("$.[*].nroDocProv").value(hasItem(DEFAULT_NRO_DOC_PROV)))
            .andExpect(jsonPath("$.[*].nomApeRazSocProv").value(hasItem(DEFAULT_NOM_APE_RAZ_SOC_PROV)))
            .andExpect(jsonPath("$.[*].baseImponible").value(hasItem(DEFAULT_BASE_IMPONIBLE.doubleValue())))
            .andExpect(jsonPath("$.[*].montoIgv").value(hasItem(DEFAULT_MONTO_IGV.doubleValue())))
            .andExpect(jsonPath("$.[*].baseImponible2").value(hasItem(DEFAULT_BASE_IMPONIBLE_2.doubleValue())))
            .andExpect(jsonPath("$.[*].montoIgv2").value(hasItem(DEFAULT_MONTO_IGV_2.doubleValue())))
            .andExpect(jsonPath("$.[*].baseImponible3").value(hasItem(DEFAULT_BASE_IMPONIBLE_3.doubleValue())))
            .andExpect(jsonPath("$.[*].montoIgv3").value(hasItem(DEFAULT_MONTO_IGV_3.doubleValue())))
            .andExpect(jsonPath("$.[*].montoNoGravado").value(hasItem(DEFAULT_MONTO_NO_GRAVADO.doubleValue())))
            .andExpect(jsonPath("$.[*].montoIsc").value(hasItem(DEFAULT_MONTO_ISC.doubleValue())))
            .andExpect(jsonPath("$.[*].impConsBols").value(hasItem(DEFAULT_IMP_CONS_BOLS.doubleValue())))
            .andExpect(jsonPath("$.[*].otrosCargos").value(hasItem(DEFAULT_OTROS_CARGOS.doubleValue())))
            .andExpect(jsonPath("$.[*].importeTotal").value(hasItem(DEFAULT_IMPORTE_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].codMoneda").value(hasItem(DEFAULT_COD_MONEDA)))
            .andExpect(jsonPath("$.[*].tipCambio").value(hasItem(DEFAULT_TIP_CAMBIO.doubleValue())))
            .andExpect(jsonPath("$.[*].fecEmiModif").value(hasItem(sameInstant(DEFAULT_FEC_EMI_MODIF))))
            .andExpect(jsonPath("$.[*].tipCompModif").value(hasItem(DEFAULT_TIP_COMP_MODIF)))
            .andExpect(jsonPath("$.[*].nroSerieCompModif").value(hasItem(DEFAULT_NRO_SERIE_COMP_MODIF)))
            .andExpect(jsonPath("$.[*].codDuaRef").value(hasItem(DEFAULT_COD_DUA_REF)))
            .andExpect(jsonPath("$.[*].nroCompModif").value(hasItem(DEFAULT_NRO_COMP_MODIF)))
            .andExpect(jsonPath("$.[*].fecEmiDetracc").value(hasItem(sameInstant(DEFAULT_FEC_EMI_DETRACC))))
            .andExpect(jsonPath("$.[*].nroConstDetracc").value(hasItem(DEFAULT_NRO_CONST_DETRACC)))
            .andExpect(jsonPath("$.[*].indCompSujRetenc").value(hasItem(DEFAULT_IND_COMP_SUJ_RETENC)))
            .andExpect(jsonPath("$.[*].clasBienesyServicios").value(hasItem(DEFAULT_CLAS_BIENESY_SERVICIOS)))
            .andExpect(jsonPath("$.[*].identContrato").value(hasItem(DEFAULT_IDENT_CONTRATO)))
            .andExpect(jsonPath("$.[*].errTipUno").value(hasItem(DEFAULT_ERR_TIP_UNO)))
            .andExpect(jsonPath("$.[*].errTipDos").value(hasItem(DEFAULT_ERR_TIP_DOS)))
            .andExpect(jsonPath("$.[*].errTipTres").value(hasItem(DEFAULT_ERR_TIP_TRES)))
            .andExpect(jsonPath("$.[*].errTipCuatro").value(hasItem(DEFAULT_ERR_TIP_CUATRO)))
            .andExpect(jsonPath("$.[*].indCompPagoMedPago").value(hasItem(DEFAULT_IND_COMP_PAGO_MED_PAGO)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].campoLibre").value(hasItem(DEFAULT_CAMPO_LIBRE)))
            .andExpect(jsonPath("$.[*].indDel").value(hasItem(DEFAULT_IND_DEL.booleanValue())))
            .andExpect(jsonPath("$.[*].fecCrea").value(hasItem(sameInstant(DEFAULT_FEC_CREA))))
            .andExpect(jsonPath("$.[*].usuCrea").value(hasItem(DEFAULT_USU_CREA)))
            .andExpect(jsonPath("$.[*].ipCrea").value(hasItem(DEFAULT_IP_CREA)))
            .andExpect(jsonPath("$.[*].fecModif").value(hasItem(sameInstant(DEFAULT_FEC_MODIF))))
            .andExpect(jsonPath("$.[*].usuModif").value(hasItem(DEFAULT_USU_MODIF)))
            .andExpect(jsonPath("$.[*].ipModif").value(hasItem(DEFAULT_IP_MODIF)));
    }

    @Test
    void getRegCompras() throws Exception {
        // Initialize the database
        regComprasRepository.save(regCompras);

        // Get the regCompras
        restRegComprasMockMvc
            .perform(get(ENTITY_API_URL_ID, regCompras.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(regCompras.getId()))
            .andExpect(jsonPath("$.periodo").value(DEFAULT_PERIODO))
            .andExpect(jsonPath("$.cuo").value(DEFAULT_CUO))
            .andExpect(jsonPath("$.asientContab").value(DEFAULT_ASIENT_CONTAB))
            .andExpect(jsonPath("$.fecEmiComp").value(sameInstant(DEFAULT_FEC_EMI_COMP)))
            .andExpect(jsonPath("$.fecVencComp").value(sameInstant(DEFAULT_FEC_VENC_COMP)))
            .andExpect(jsonPath("$.tipComp").value(DEFAULT_TIP_COMP))
            .andExpect(jsonPath("$.nroSerieComp").value(DEFAULT_NRO_SERIE_COMP))
            .andExpect(jsonPath("$.anhoEmisionDua").value(DEFAULT_ANHO_EMISION_DUA))
            .andExpect(jsonPath("$.nroComp").value(DEFAULT_NRO_COMP))
            .andExpect(jsonPath("$.nroFinal").value(DEFAULT_NRO_FINAL))
            .andExpect(jsonPath("$.tipDocProv").value(DEFAULT_TIP_DOC_PROV))
            .andExpect(jsonPath("$.nroDocProv").value(DEFAULT_NRO_DOC_PROV))
            .andExpect(jsonPath("$.nomApeRazSocProv").value(DEFAULT_NOM_APE_RAZ_SOC_PROV))
            .andExpect(jsonPath("$.baseImponible").value(DEFAULT_BASE_IMPONIBLE.doubleValue()))
            .andExpect(jsonPath("$.montoIgv").value(DEFAULT_MONTO_IGV.doubleValue()))
            .andExpect(jsonPath("$.baseImponible2").value(DEFAULT_BASE_IMPONIBLE_2.doubleValue()))
            .andExpect(jsonPath("$.montoIgv2").value(DEFAULT_MONTO_IGV_2.doubleValue()))
            .andExpect(jsonPath("$.baseImponible3").value(DEFAULT_BASE_IMPONIBLE_3.doubleValue()))
            .andExpect(jsonPath("$.montoIgv3").value(DEFAULT_MONTO_IGV_3.doubleValue()))
            .andExpect(jsonPath("$.montoNoGravado").value(DEFAULT_MONTO_NO_GRAVADO.doubleValue()))
            .andExpect(jsonPath("$.montoIsc").value(DEFAULT_MONTO_ISC.doubleValue()))
            .andExpect(jsonPath("$.impConsBols").value(DEFAULT_IMP_CONS_BOLS.doubleValue()))
            .andExpect(jsonPath("$.otrosCargos").value(DEFAULT_OTROS_CARGOS.doubleValue()))
            .andExpect(jsonPath("$.importeTotal").value(DEFAULT_IMPORTE_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.codMoneda").value(DEFAULT_COD_MONEDA))
            .andExpect(jsonPath("$.tipCambio").value(DEFAULT_TIP_CAMBIO.doubleValue()))
            .andExpect(jsonPath("$.fecEmiModif").value(sameInstant(DEFAULT_FEC_EMI_MODIF)))
            .andExpect(jsonPath("$.tipCompModif").value(DEFAULT_TIP_COMP_MODIF))
            .andExpect(jsonPath("$.nroSerieCompModif").value(DEFAULT_NRO_SERIE_COMP_MODIF))
            .andExpect(jsonPath("$.codDuaRef").value(DEFAULT_COD_DUA_REF))
            .andExpect(jsonPath("$.nroCompModif").value(DEFAULT_NRO_COMP_MODIF))
            .andExpect(jsonPath("$.fecEmiDetracc").value(sameInstant(DEFAULT_FEC_EMI_DETRACC)))
            .andExpect(jsonPath("$.nroConstDetracc").value(DEFAULT_NRO_CONST_DETRACC))
            .andExpect(jsonPath("$.indCompSujRetenc").value(DEFAULT_IND_COMP_SUJ_RETENC))
            .andExpect(jsonPath("$.clasBienesyServicios").value(DEFAULT_CLAS_BIENESY_SERVICIOS))
            .andExpect(jsonPath("$.identContrato").value(DEFAULT_IDENT_CONTRATO))
            .andExpect(jsonPath("$.errTipUno").value(DEFAULT_ERR_TIP_UNO))
            .andExpect(jsonPath("$.errTipDos").value(DEFAULT_ERR_TIP_DOS))
            .andExpect(jsonPath("$.errTipTres").value(DEFAULT_ERR_TIP_TRES))
            .andExpect(jsonPath("$.errTipCuatro").value(DEFAULT_ERR_TIP_CUATRO))
            .andExpect(jsonPath("$.indCompPagoMedPago").value(DEFAULT_IND_COMP_PAGO_MED_PAGO))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.campoLibre").value(DEFAULT_CAMPO_LIBRE))
            .andExpect(jsonPath("$.indDel").value(DEFAULT_IND_DEL.booleanValue()))
            .andExpect(jsonPath("$.fecCrea").value(sameInstant(DEFAULT_FEC_CREA)))
            .andExpect(jsonPath("$.usuCrea").value(DEFAULT_USU_CREA))
            .andExpect(jsonPath("$.ipCrea").value(DEFAULT_IP_CREA))
            .andExpect(jsonPath("$.fecModif").value(sameInstant(DEFAULT_FEC_MODIF)))
            .andExpect(jsonPath("$.usuModif").value(DEFAULT_USU_MODIF))
            .andExpect(jsonPath("$.ipModif").value(DEFAULT_IP_MODIF));
    }

    @Test
    void getNonExistingRegCompras() throws Exception {
        // Get the regCompras
        restRegComprasMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingRegCompras() throws Exception {
        // Initialize the database
        regComprasRepository.save(regCompras);

        int databaseSizeBeforeUpdate = regComprasRepository.findAll().size();

        // Update the regCompras
        RegCompras updatedRegCompras = regComprasRepository.findById(regCompras.getId()).get();
        updatedRegCompras
            .periodo(UPDATED_PERIODO)
            .cuo(UPDATED_CUO)
            .asientContab(UPDATED_ASIENT_CONTAB)
            .fecEmiComp(UPDATED_FEC_EMI_COMP)
            .fecVencComp(UPDATED_FEC_VENC_COMP)
            .tipComp(UPDATED_TIP_COMP)
            .nroSerieComp(UPDATED_NRO_SERIE_COMP)
            .anhoEmisionDua(UPDATED_ANHO_EMISION_DUA)
            .nroComp(UPDATED_NRO_COMP)
            .nroFinal(UPDATED_NRO_FINAL)
            .tipDocProv(UPDATED_TIP_DOC_PROV)
            .nroDocProv(UPDATED_NRO_DOC_PROV)
            .nomApeRazSocProv(UPDATED_NOM_APE_RAZ_SOC_PROV)
            .baseImponible(UPDATED_BASE_IMPONIBLE)
            .montoIgv(UPDATED_MONTO_IGV)
            .baseImponible2(UPDATED_BASE_IMPONIBLE_2)
            .montoIgv2(UPDATED_MONTO_IGV_2)
            .baseImponible3(UPDATED_BASE_IMPONIBLE_3)
            .montoIgv3(UPDATED_MONTO_IGV_3)
            .montoNoGravado(UPDATED_MONTO_NO_GRAVADO)
            .montoIsc(UPDATED_MONTO_ISC)
            .impConsBols(UPDATED_IMP_CONS_BOLS)
            .otrosCargos(UPDATED_OTROS_CARGOS)
            .importeTotal(UPDATED_IMPORTE_TOTAL)
            .codMoneda(UPDATED_COD_MONEDA)
            .tipCambio(UPDATED_TIP_CAMBIO)
            .fecEmiModif(UPDATED_FEC_EMI_MODIF)
            .tipCompModif(UPDATED_TIP_COMP_MODIF)
            .nroSerieCompModif(UPDATED_NRO_SERIE_COMP_MODIF)
            .codDuaRef(UPDATED_COD_DUA_REF)
            .nroCompModif(UPDATED_NRO_COMP_MODIF)
            .fecEmiDetracc(UPDATED_FEC_EMI_DETRACC)
            .nroConstDetracc(UPDATED_NRO_CONST_DETRACC)
            .indCompSujRetenc(UPDATED_IND_COMP_SUJ_RETENC)
            .clasBienesyServicios(UPDATED_CLAS_BIENESY_SERVICIOS)
            .identContrato(UPDATED_IDENT_CONTRATO)
            .errTipUno(UPDATED_ERR_TIP_UNO)
            .errTipDos(UPDATED_ERR_TIP_DOS)
            .errTipTres(UPDATED_ERR_TIP_TRES)
            .errTipCuatro(UPDATED_ERR_TIP_CUATRO)
            .indCompPagoMedPago(UPDATED_IND_COMP_PAGO_MED_PAGO)
            .estado(UPDATED_ESTADO)
            .campoLibre(UPDATED_CAMPO_LIBRE)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restRegComprasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRegCompras.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRegCompras))
            )
            .andExpect(status().isOk());

        // Validate the RegCompras in the database
        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeUpdate);
        RegCompras testRegCompras = regComprasList.get(regComprasList.size() - 1);
        assertThat(testRegCompras.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testRegCompras.getCuo()).isEqualTo(UPDATED_CUO);
        assertThat(testRegCompras.getAsientContab()).isEqualTo(UPDATED_ASIENT_CONTAB);
        assertThat(testRegCompras.getFecEmiComp()).isEqualTo(UPDATED_FEC_EMI_COMP);
        assertThat(testRegCompras.getFecVencComp()).isEqualTo(UPDATED_FEC_VENC_COMP);
        assertThat(testRegCompras.getTipComp()).isEqualTo(UPDATED_TIP_COMP);
        assertThat(testRegCompras.getNroSerieComp()).isEqualTo(UPDATED_NRO_SERIE_COMP);
        assertThat(testRegCompras.getAnhoEmisionDua()).isEqualTo(UPDATED_ANHO_EMISION_DUA);
        assertThat(testRegCompras.getNroComp()).isEqualTo(UPDATED_NRO_COMP);
        assertThat(testRegCompras.getNroFinal()).isEqualTo(UPDATED_NRO_FINAL);
        assertThat(testRegCompras.getTipDocProv()).isEqualTo(UPDATED_TIP_DOC_PROV);
        assertThat(testRegCompras.getNroDocProv()).isEqualTo(UPDATED_NRO_DOC_PROV);
        assertThat(testRegCompras.getNomApeRazSocProv()).isEqualTo(UPDATED_NOM_APE_RAZ_SOC_PROV);
        assertThat(testRegCompras.getBaseImponible()).isEqualTo(UPDATED_BASE_IMPONIBLE);
        assertThat(testRegCompras.getMontoIgv()).isEqualTo(UPDATED_MONTO_IGV);
        assertThat(testRegCompras.getBaseImponible2()).isEqualTo(UPDATED_BASE_IMPONIBLE_2);
        assertThat(testRegCompras.getMontoIgv2()).isEqualTo(UPDATED_MONTO_IGV_2);
        assertThat(testRegCompras.getBaseImponible3()).isEqualTo(UPDATED_BASE_IMPONIBLE_3);
        assertThat(testRegCompras.getMontoIgv3()).isEqualTo(UPDATED_MONTO_IGV_3);
        assertThat(testRegCompras.getMontoNoGravado()).isEqualTo(UPDATED_MONTO_NO_GRAVADO);
        assertThat(testRegCompras.getMontoIsc()).isEqualTo(UPDATED_MONTO_ISC);
        assertThat(testRegCompras.getImpConsBols()).isEqualTo(UPDATED_IMP_CONS_BOLS);
        assertThat(testRegCompras.getOtrosCargos()).isEqualTo(UPDATED_OTROS_CARGOS);
        assertThat(testRegCompras.getImporteTotal()).isEqualTo(UPDATED_IMPORTE_TOTAL);
        assertThat(testRegCompras.getCodMoneda()).isEqualTo(UPDATED_COD_MONEDA);
        assertThat(testRegCompras.getTipCambio()).isEqualTo(UPDATED_TIP_CAMBIO);
        assertThat(testRegCompras.getFecEmiModif()).isEqualTo(UPDATED_FEC_EMI_MODIF);
        assertThat(testRegCompras.getTipCompModif()).isEqualTo(UPDATED_TIP_COMP_MODIF);
        assertThat(testRegCompras.getNroSerieCompModif()).isEqualTo(UPDATED_NRO_SERIE_COMP_MODIF);
        assertThat(testRegCompras.getCodDuaRef()).isEqualTo(UPDATED_COD_DUA_REF);
        assertThat(testRegCompras.getNroCompModif()).isEqualTo(UPDATED_NRO_COMP_MODIF);
        assertThat(testRegCompras.getFecEmiDetracc()).isEqualTo(UPDATED_FEC_EMI_DETRACC);
        assertThat(testRegCompras.getNroConstDetracc()).isEqualTo(UPDATED_NRO_CONST_DETRACC);
        assertThat(testRegCompras.getIndCompSujRetenc()).isEqualTo(UPDATED_IND_COMP_SUJ_RETENC);
        assertThat(testRegCompras.getClasBienesyServicios()).isEqualTo(UPDATED_CLAS_BIENESY_SERVICIOS);
        assertThat(testRegCompras.getIdentContrato()).isEqualTo(UPDATED_IDENT_CONTRATO);
        assertThat(testRegCompras.getErrTipUno()).isEqualTo(UPDATED_ERR_TIP_UNO);
        assertThat(testRegCompras.getErrTipDos()).isEqualTo(UPDATED_ERR_TIP_DOS);
        assertThat(testRegCompras.getErrTipTres()).isEqualTo(UPDATED_ERR_TIP_TRES);
        assertThat(testRegCompras.getErrTipCuatro()).isEqualTo(UPDATED_ERR_TIP_CUATRO);
        assertThat(testRegCompras.getIndCompPagoMedPago()).isEqualTo(UPDATED_IND_COMP_PAGO_MED_PAGO);
        assertThat(testRegCompras.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testRegCompras.getCampoLibre()).isEqualTo(UPDATED_CAMPO_LIBRE);
        assertThat(testRegCompras.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testRegCompras.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testRegCompras.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testRegCompras.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testRegCompras.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testRegCompras.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testRegCompras.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingRegCompras() throws Exception {
        int databaseSizeBeforeUpdate = regComprasRepository.findAll().size();
        regCompras.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegComprasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, regCompras.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(regCompras))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegCompras in the database
        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchRegCompras() throws Exception {
        int databaseSizeBeforeUpdate = regComprasRepository.findAll().size();
        regCompras.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegComprasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(regCompras))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegCompras in the database
        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamRegCompras() throws Exception {
        int databaseSizeBeforeUpdate = regComprasRepository.findAll().size();
        regCompras.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegComprasMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(regCompras)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RegCompras in the database
        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateRegComprasWithPatch() throws Exception {
        // Initialize the database
        regComprasRepository.save(regCompras);

        int databaseSizeBeforeUpdate = regComprasRepository.findAll().size();

        // Update the regCompras using partial update
        RegCompras partialUpdatedRegCompras = new RegCompras();
        partialUpdatedRegCompras.setId(regCompras.getId());

        partialUpdatedRegCompras
            .periodo(UPDATED_PERIODO)
            .fecEmiComp(UPDATED_FEC_EMI_COMP)
            .tipComp(UPDATED_TIP_COMP)
            .nroSerieComp(UPDATED_NRO_SERIE_COMP)
            .anhoEmisionDua(UPDATED_ANHO_EMISION_DUA)
            .nroComp(UPDATED_NRO_COMP)
            .nroDocProv(UPDATED_NRO_DOC_PROV)
            .nomApeRazSocProv(UPDATED_NOM_APE_RAZ_SOC_PROV)
            .baseImponible(UPDATED_BASE_IMPONIBLE)
            .montoIgv2(UPDATED_MONTO_IGV_2)
            .montoIsc(UPDATED_MONTO_ISC)
            .fecEmiModif(UPDATED_FEC_EMI_MODIF)
            .tipCompModif(UPDATED_TIP_COMP_MODIF)
            .nroSerieCompModif(UPDATED_NRO_SERIE_COMP_MODIF)
            .nroCompModif(UPDATED_NRO_COMP_MODIF)
            .fecEmiDetracc(UPDATED_FEC_EMI_DETRACC)
            .nroConstDetracc(UPDATED_NRO_CONST_DETRACC)
            .errTipUno(UPDATED_ERR_TIP_UNO)
            .indCompPagoMedPago(UPDATED_IND_COMP_PAGO_MED_PAGO)
            .estado(UPDATED_ESTADO)
            .campoLibre(UPDATED_CAMPO_LIBRE)
            .indDel(UPDATED_IND_DEL)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restRegComprasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRegCompras.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRegCompras))
            )
            .andExpect(status().isOk());

        // Validate the RegCompras in the database
        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeUpdate);
        RegCompras testRegCompras = regComprasList.get(regComprasList.size() - 1);
        assertThat(testRegCompras.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testRegCompras.getCuo()).isEqualTo(DEFAULT_CUO);
        assertThat(testRegCompras.getAsientContab()).isEqualTo(DEFAULT_ASIENT_CONTAB);
        assertThat(testRegCompras.getFecEmiComp()).isEqualTo(UPDATED_FEC_EMI_COMP);
        assertThat(testRegCompras.getFecVencComp()).isEqualTo(DEFAULT_FEC_VENC_COMP);
        assertThat(testRegCompras.getTipComp()).isEqualTo(UPDATED_TIP_COMP);
        assertThat(testRegCompras.getNroSerieComp()).isEqualTo(UPDATED_NRO_SERIE_COMP);
        assertThat(testRegCompras.getAnhoEmisionDua()).isEqualTo(UPDATED_ANHO_EMISION_DUA);
        assertThat(testRegCompras.getNroComp()).isEqualTo(UPDATED_NRO_COMP);
        assertThat(testRegCompras.getNroFinal()).isEqualTo(DEFAULT_NRO_FINAL);
        assertThat(testRegCompras.getTipDocProv()).isEqualTo(DEFAULT_TIP_DOC_PROV);
        assertThat(testRegCompras.getNroDocProv()).isEqualTo(UPDATED_NRO_DOC_PROV);
        assertThat(testRegCompras.getNomApeRazSocProv()).isEqualTo(UPDATED_NOM_APE_RAZ_SOC_PROV);
        assertThat(testRegCompras.getBaseImponible()).isEqualTo(UPDATED_BASE_IMPONIBLE);
        assertThat(testRegCompras.getMontoIgv()).isEqualTo(DEFAULT_MONTO_IGV);
        assertThat(testRegCompras.getBaseImponible2()).isEqualTo(DEFAULT_BASE_IMPONIBLE_2);
        assertThat(testRegCompras.getMontoIgv2()).isEqualTo(UPDATED_MONTO_IGV_2);
        assertThat(testRegCompras.getBaseImponible3()).isEqualTo(DEFAULT_BASE_IMPONIBLE_3);
        assertThat(testRegCompras.getMontoIgv3()).isEqualTo(DEFAULT_MONTO_IGV_3);
        assertThat(testRegCompras.getMontoNoGravado()).isEqualTo(DEFAULT_MONTO_NO_GRAVADO);
        assertThat(testRegCompras.getMontoIsc()).isEqualTo(UPDATED_MONTO_ISC);
        assertThat(testRegCompras.getImpConsBols()).isEqualTo(DEFAULT_IMP_CONS_BOLS);
        assertThat(testRegCompras.getOtrosCargos()).isEqualTo(DEFAULT_OTROS_CARGOS);
        assertThat(testRegCompras.getImporteTotal()).isEqualTo(DEFAULT_IMPORTE_TOTAL);
        assertThat(testRegCompras.getCodMoneda()).isEqualTo(DEFAULT_COD_MONEDA);
        assertThat(testRegCompras.getTipCambio()).isEqualTo(DEFAULT_TIP_CAMBIO);
        assertThat(testRegCompras.getFecEmiModif()).isEqualTo(UPDATED_FEC_EMI_MODIF);
        assertThat(testRegCompras.getTipCompModif()).isEqualTo(UPDATED_TIP_COMP_MODIF);
        assertThat(testRegCompras.getNroSerieCompModif()).isEqualTo(UPDATED_NRO_SERIE_COMP_MODIF);
        assertThat(testRegCompras.getCodDuaRef()).isEqualTo(DEFAULT_COD_DUA_REF);
        assertThat(testRegCompras.getNroCompModif()).isEqualTo(UPDATED_NRO_COMP_MODIF);
        assertThat(testRegCompras.getFecEmiDetracc()).isEqualTo(UPDATED_FEC_EMI_DETRACC);
        assertThat(testRegCompras.getNroConstDetracc()).isEqualTo(UPDATED_NRO_CONST_DETRACC);
        assertThat(testRegCompras.getIndCompSujRetenc()).isEqualTo(DEFAULT_IND_COMP_SUJ_RETENC);
        assertThat(testRegCompras.getClasBienesyServicios()).isEqualTo(DEFAULT_CLAS_BIENESY_SERVICIOS);
        assertThat(testRegCompras.getIdentContrato()).isEqualTo(DEFAULT_IDENT_CONTRATO);
        assertThat(testRegCompras.getErrTipUno()).isEqualTo(UPDATED_ERR_TIP_UNO);
        assertThat(testRegCompras.getErrTipDos()).isEqualTo(DEFAULT_ERR_TIP_DOS);
        assertThat(testRegCompras.getErrTipTres()).isEqualTo(DEFAULT_ERR_TIP_TRES);
        assertThat(testRegCompras.getErrTipCuatro()).isEqualTo(DEFAULT_ERR_TIP_CUATRO);
        assertThat(testRegCompras.getIndCompPagoMedPago()).isEqualTo(UPDATED_IND_COMP_PAGO_MED_PAGO);
        assertThat(testRegCompras.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testRegCompras.getCampoLibre()).isEqualTo(UPDATED_CAMPO_LIBRE);
        assertThat(testRegCompras.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testRegCompras.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testRegCompras.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testRegCompras.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testRegCompras.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testRegCompras.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testRegCompras.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void fullUpdateRegComprasWithPatch() throws Exception {
        // Initialize the database
        regComprasRepository.save(regCompras);

        int databaseSizeBeforeUpdate = regComprasRepository.findAll().size();

        // Update the regCompras using partial update
        RegCompras partialUpdatedRegCompras = new RegCompras();
        partialUpdatedRegCompras.setId(regCompras.getId());

        partialUpdatedRegCompras
            .periodo(UPDATED_PERIODO)
            .cuo(UPDATED_CUO)
            .asientContab(UPDATED_ASIENT_CONTAB)
            .fecEmiComp(UPDATED_FEC_EMI_COMP)
            .fecVencComp(UPDATED_FEC_VENC_COMP)
            .tipComp(UPDATED_TIP_COMP)
            .nroSerieComp(UPDATED_NRO_SERIE_COMP)
            .anhoEmisionDua(UPDATED_ANHO_EMISION_DUA)
            .nroComp(UPDATED_NRO_COMP)
            .nroFinal(UPDATED_NRO_FINAL)
            .tipDocProv(UPDATED_TIP_DOC_PROV)
            .nroDocProv(UPDATED_NRO_DOC_PROV)
            .nomApeRazSocProv(UPDATED_NOM_APE_RAZ_SOC_PROV)
            .baseImponible(UPDATED_BASE_IMPONIBLE)
            .montoIgv(UPDATED_MONTO_IGV)
            .baseImponible2(UPDATED_BASE_IMPONIBLE_2)
            .montoIgv2(UPDATED_MONTO_IGV_2)
            .baseImponible3(UPDATED_BASE_IMPONIBLE_3)
            .montoIgv3(UPDATED_MONTO_IGV_3)
            .montoNoGravado(UPDATED_MONTO_NO_GRAVADO)
            .montoIsc(UPDATED_MONTO_ISC)
            .impConsBols(UPDATED_IMP_CONS_BOLS)
            .otrosCargos(UPDATED_OTROS_CARGOS)
            .importeTotal(UPDATED_IMPORTE_TOTAL)
            .codMoneda(UPDATED_COD_MONEDA)
            .tipCambio(UPDATED_TIP_CAMBIO)
            .fecEmiModif(UPDATED_FEC_EMI_MODIF)
            .tipCompModif(UPDATED_TIP_COMP_MODIF)
            .nroSerieCompModif(UPDATED_NRO_SERIE_COMP_MODIF)
            .codDuaRef(UPDATED_COD_DUA_REF)
            .nroCompModif(UPDATED_NRO_COMP_MODIF)
            .fecEmiDetracc(UPDATED_FEC_EMI_DETRACC)
            .nroConstDetracc(UPDATED_NRO_CONST_DETRACC)
            .indCompSujRetenc(UPDATED_IND_COMP_SUJ_RETENC)
            .clasBienesyServicios(UPDATED_CLAS_BIENESY_SERVICIOS)
            .identContrato(UPDATED_IDENT_CONTRATO)
            .errTipUno(UPDATED_ERR_TIP_UNO)
            .errTipDos(UPDATED_ERR_TIP_DOS)
            .errTipTres(UPDATED_ERR_TIP_TRES)
            .errTipCuatro(UPDATED_ERR_TIP_CUATRO)
            .indCompPagoMedPago(UPDATED_IND_COMP_PAGO_MED_PAGO)
            .estado(UPDATED_ESTADO)
            .campoLibre(UPDATED_CAMPO_LIBRE)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restRegComprasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRegCompras.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRegCompras))
            )
            .andExpect(status().isOk());

        // Validate the RegCompras in the database
        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeUpdate);
        RegCompras testRegCompras = regComprasList.get(regComprasList.size() - 1);
        assertThat(testRegCompras.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testRegCompras.getCuo()).isEqualTo(UPDATED_CUO);
        assertThat(testRegCompras.getAsientContab()).isEqualTo(UPDATED_ASIENT_CONTAB);
        assertThat(testRegCompras.getFecEmiComp()).isEqualTo(UPDATED_FEC_EMI_COMP);
        assertThat(testRegCompras.getFecVencComp()).isEqualTo(UPDATED_FEC_VENC_COMP);
        assertThat(testRegCompras.getTipComp()).isEqualTo(UPDATED_TIP_COMP);
        assertThat(testRegCompras.getNroSerieComp()).isEqualTo(UPDATED_NRO_SERIE_COMP);
        assertThat(testRegCompras.getAnhoEmisionDua()).isEqualTo(UPDATED_ANHO_EMISION_DUA);
        assertThat(testRegCompras.getNroComp()).isEqualTo(UPDATED_NRO_COMP);
        assertThat(testRegCompras.getNroFinal()).isEqualTo(UPDATED_NRO_FINAL);
        assertThat(testRegCompras.getTipDocProv()).isEqualTo(UPDATED_TIP_DOC_PROV);
        assertThat(testRegCompras.getNroDocProv()).isEqualTo(UPDATED_NRO_DOC_PROV);
        assertThat(testRegCompras.getNomApeRazSocProv()).isEqualTo(UPDATED_NOM_APE_RAZ_SOC_PROV);
        assertThat(testRegCompras.getBaseImponible()).isEqualTo(UPDATED_BASE_IMPONIBLE);
        assertThat(testRegCompras.getMontoIgv()).isEqualTo(UPDATED_MONTO_IGV);
        assertThat(testRegCompras.getBaseImponible2()).isEqualTo(UPDATED_BASE_IMPONIBLE_2);
        assertThat(testRegCompras.getMontoIgv2()).isEqualTo(UPDATED_MONTO_IGV_2);
        assertThat(testRegCompras.getBaseImponible3()).isEqualTo(UPDATED_BASE_IMPONIBLE_3);
        assertThat(testRegCompras.getMontoIgv3()).isEqualTo(UPDATED_MONTO_IGV_3);
        assertThat(testRegCompras.getMontoNoGravado()).isEqualTo(UPDATED_MONTO_NO_GRAVADO);
        assertThat(testRegCompras.getMontoIsc()).isEqualTo(UPDATED_MONTO_ISC);
        assertThat(testRegCompras.getImpConsBols()).isEqualTo(UPDATED_IMP_CONS_BOLS);
        assertThat(testRegCompras.getOtrosCargos()).isEqualTo(UPDATED_OTROS_CARGOS);
        assertThat(testRegCompras.getImporteTotal()).isEqualTo(UPDATED_IMPORTE_TOTAL);
        assertThat(testRegCompras.getCodMoneda()).isEqualTo(UPDATED_COD_MONEDA);
        assertThat(testRegCompras.getTipCambio()).isEqualTo(UPDATED_TIP_CAMBIO);
        assertThat(testRegCompras.getFecEmiModif()).isEqualTo(UPDATED_FEC_EMI_MODIF);
        assertThat(testRegCompras.getTipCompModif()).isEqualTo(UPDATED_TIP_COMP_MODIF);
        assertThat(testRegCompras.getNroSerieCompModif()).isEqualTo(UPDATED_NRO_SERIE_COMP_MODIF);
        assertThat(testRegCompras.getCodDuaRef()).isEqualTo(UPDATED_COD_DUA_REF);
        assertThat(testRegCompras.getNroCompModif()).isEqualTo(UPDATED_NRO_COMP_MODIF);
        assertThat(testRegCompras.getFecEmiDetracc()).isEqualTo(UPDATED_FEC_EMI_DETRACC);
        assertThat(testRegCompras.getNroConstDetracc()).isEqualTo(UPDATED_NRO_CONST_DETRACC);
        assertThat(testRegCompras.getIndCompSujRetenc()).isEqualTo(UPDATED_IND_COMP_SUJ_RETENC);
        assertThat(testRegCompras.getClasBienesyServicios()).isEqualTo(UPDATED_CLAS_BIENESY_SERVICIOS);
        assertThat(testRegCompras.getIdentContrato()).isEqualTo(UPDATED_IDENT_CONTRATO);
        assertThat(testRegCompras.getErrTipUno()).isEqualTo(UPDATED_ERR_TIP_UNO);
        assertThat(testRegCompras.getErrTipDos()).isEqualTo(UPDATED_ERR_TIP_DOS);
        assertThat(testRegCompras.getErrTipTres()).isEqualTo(UPDATED_ERR_TIP_TRES);
        assertThat(testRegCompras.getErrTipCuatro()).isEqualTo(UPDATED_ERR_TIP_CUATRO);
        assertThat(testRegCompras.getIndCompPagoMedPago()).isEqualTo(UPDATED_IND_COMP_PAGO_MED_PAGO);
        assertThat(testRegCompras.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testRegCompras.getCampoLibre()).isEqualTo(UPDATED_CAMPO_LIBRE);
        assertThat(testRegCompras.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testRegCompras.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testRegCompras.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testRegCompras.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testRegCompras.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testRegCompras.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testRegCompras.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingRegCompras() throws Exception {
        int databaseSizeBeforeUpdate = regComprasRepository.findAll().size();
        regCompras.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegComprasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, regCompras.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(regCompras))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegCompras in the database
        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchRegCompras() throws Exception {
        int databaseSizeBeforeUpdate = regComprasRepository.findAll().size();
        regCompras.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegComprasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(regCompras))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegCompras in the database
        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamRegCompras() throws Exception {
        int databaseSizeBeforeUpdate = regComprasRepository.findAll().size();
        regCompras.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegComprasMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(regCompras))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RegCompras in the database
        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteRegCompras() throws Exception {
        // Initialize the database
        regComprasRepository.save(regCompras);

        int databaseSizeBeforeDelete = regComprasRepository.findAll().size();

        // Delete the regCompras
        restRegComprasMockMvc
            .perform(delete(ENTITY_API_URL_ID, regCompras.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RegCompras> regComprasList = regComprasRepository.findAll();
        assertThat(regComprasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
