package com.ppanticona.web.rest;

import static com.ppanticona.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.Producto;
import com.ppanticona.repository.ProductoRepository;
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
 * Integration tests for the {@link ProductoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProductoResourceIT {

    private static final String DEFAULT_COD_PRODUCTO = "AAAAAAAAAA";
    private static final String UPDATED_COD_PRODUCTO = "BBBBBBBBBB";

    private static final String DEFAULT_TIP_PRODUCTO = "AAAAAAAAAA";
    private static final String UPDATED_TIP_PRODUCTO = "BBBBBBBBBB";

    private static final String DEFAULT_COD_QR = "AAAAAAAAAA";
    private static final String UPDATED_COD_QR = "BBBBBBBBBB";

    private static final String DEFAULT_COD_BARRA = "AAAAAAAAAA";
    private static final String UPDATED_COD_BARRA = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_DETALLE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DETALLE_DESC = "BBBBBBBBBB";

    private static final Double DEFAULT_VALOR = 1D;
    private static final Double UPDATED_VALOR = 2D;

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_SUB_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIA_MENU = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA_MENU = "BBBBBBBBBB";

    private static final String DEFAULT_URL_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_URL_IMAGE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_INI_VIG = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_INI_VIG = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FEC_FIN_VIG = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_FIN_VIG = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_COSTO_PROD = 1D;
    private static final Double UPDATED_COSTO_PROD = 2D;

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

    private static final String ENTITY_API_URL = "/api/productos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MockMvc restProductoMockMvc;

    private Producto producto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Producto createEntity() {
        Producto producto = new Producto()
            .codProducto(DEFAULT_COD_PRODUCTO)
            .tipProducto(DEFAULT_TIP_PRODUCTO)
            .codQr(DEFAULT_COD_QR)
            .codBarra(DEFAULT_COD_BARRA)
            .descripcion(DEFAULT_DESCRIPCION)
            .detalleDesc(DEFAULT_DETALLE_DESC)
            .valor(DEFAULT_VALOR)
            .categoria(DEFAULT_CATEGORIA)
            .subCategoria(DEFAULT_SUB_CATEGORIA)
            .categoriaMenu(DEFAULT_CATEGORIA_MENU)
            .urlImage(DEFAULT_URL_IMAGE)
            .fecIniVig(DEFAULT_FEC_INI_VIG)
            .fecFinVig(DEFAULT_FEC_FIN_VIG)
            .costoProd(DEFAULT_COSTO_PROD)
            .estado(DEFAULT_ESTADO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return producto;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Producto createUpdatedEntity() {
        Producto producto = new Producto()
            .codProducto(UPDATED_COD_PRODUCTO)
            .tipProducto(UPDATED_TIP_PRODUCTO)
            .codQr(UPDATED_COD_QR)
            .codBarra(UPDATED_COD_BARRA)
            .descripcion(UPDATED_DESCRIPCION)
            .detalleDesc(UPDATED_DETALLE_DESC)
            .valor(UPDATED_VALOR)
            .categoria(UPDATED_CATEGORIA)
            .subCategoria(UPDATED_SUB_CATEGORIA)
            .categoriaMenu(UPDATED_CATEGORIA_MENU)
            .urlImage(UPDATED_URL_IMAGE)
            .fecIniVig(UPDATED_FEC_INI_VIG)
            .fecFinVig(UPDATED_FEC_FIN_VIG)
            .costoProd(UPDATED_COSTO_PROD)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return producto;
    }

    @BeforeEach
    public void initTest() {
        productoRepository.deleteAll();
        producto = createEntity();
    }

    @Test
    void createProducto() throws Exception {
        int databaseSizeBeforeCreate = productoRepository.findAll().size();
        // Create the Producto
        restProductoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(producto)))
            .andExpect(status().isCreated());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeCreate + 1);
        Producto testProducto = productoList.get(productoList.size() - 1);
        assertThat(testProducto.getCodProducto()).isEqualTo(DEFAULT_COD_PRODUCTO);
        assertThat(testProducto.getTipProducto()).isEqualTo(DEFAULT_TIP_PRODUCTO);
        assertThat(testProducto.getCodQr()).isEqualTo(DEFAULT_COD_QR);
        assertThat(testProducto.getCodBarra()).isEqualTo(DEFAULT_COD_BARRA);
        assertThat(testProducto.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testProducto.getDetalleDesc()).isEqualTo(DEFAULT_DETALLE_DESC);
        assertThat(testProducto.getValor()).isEqualTo(DEFAULT_VALOR);
        assertThat(testProducto.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testProducto.getSubCategoria()).isEqualTo(DEFAULT_SUB_CATEGORIA);
        assertThat(testProducto.getCategoriaMenu()).isEqualTo(DEFAULT_CATEGORIA_MENU);
        assertThat(testProducto.getUrlImage()).isEqualTo(DEFAULT_URL_IMAGE);
        assertThat(testProducto.getFecIniVig()).isEqualTo(DEFAULT_FEC_INI_VIG);
        assertThat(testProducto.getFecFinVig()).isEqualTo(DEFAULT_FEC_FIN_VIG);
        assertThat(testProducto.getCostoProd()).isEqualTo(DEFAULT_COSTO_PROD);
        assertThat(testProducto.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testProducto.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testProducto.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testProducto.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testProducto.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testProducto.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testProducto.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testProducto.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testProducto.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createProductoWithExistingId() throws Exception {
        // Create the Producto with an existing ID
        producto.setId("existing_id");

        int databaseSizeBeforeCreate = productoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(producto)))
            .andExpect(status().isBadRequest());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = productoRepository.findAll().size();
        // set the field null
        producto.setDescripcion(null);

        // Create the Producto, which fails.

        restProductoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(producto)))
            .andExpect(status().isBadRequest());

        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = productoRepository.findAll().size();
        // set the field null
        producto.setEstado(null);

        // Create the Producto, which fails.

        restProductoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(producto)))
            .andExpect(status().isBadRequest());

        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = productoRepository.findAll().size();
        // set the field null
        producto.setVersion(null);

        // Create the Producto, which fails.

        restProductoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(producto)))
            .andExpect(status().isBadRequest());

        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = productoRepository.findAll().size();
        // set the field null
        producto.setIndDel(null);

        // Create the Producto, which fails.

        restProductoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(producto)))
            .andExpect(status().isBadRequest());

        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = productoRepository.findAll().size();
        // set the field null
        producto.setFecCrea(null);

        // Create the Producto, which fails.

        restProductoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(producto)))
            .andExpect(status().isBadRequest());

        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = productoRepository.findAll().size();
        // set the field null
        producto.setUsuCrea(null);

        // Create the Producto, which fails.

        restProductoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(producto)))
            .andExpect(status().isBadRequest());

        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = productoRepository.findAll().size();
        // set the field null
        producto.setIpCrea(null);

        // Create the Producto, which fails.

        restProductoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(producto)))
            .andExpect(status().isBadRequest());

        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllProductos() throws Exception {
        // Initialize the database
        productoRepository.save(producto);

        // Get all the productoList
        restProductoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(producto.getId())))
            .andExpect(jsonPath("$.[*].codProducto").value(hasItem(DEFAULT_COD_PRODUCTO)))
            .andExpect(jsonPath("$.[*].tipProducto").value(hasItem(DEFAULT_TIP_PRODUCTO)))
            .andExpect(jsonPath("$.[*].codQr").value(hasItem(DEFAULT_COD_QR)))
            .andExpect(jsonPath("$.[*].codBarra").value(hasItem(DEFAULT_COD_BARRA)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].detalleDesc").value(hasItem(DEFAULT_DETALLE_DESC)))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.doubleValue())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA)))
            .andExpect(jsonPath("$.[*].subCategoria").value(hasItem(DEFAULT_SUB_CATEGORIA)))
            .andExpect(jsonPath("$.[*].categoriaMenu").value(hasItem(DEFAULT_CATEGORIA_MENU)))
            .andExpect(jsonPath("$.[*].urlImage").value(hasItem(DEFAULT_URL_IMAGE)))
            .andExpect(jsonPath("$.[*].fecIniVig").value(hasItem(sameInstant(DEFAULT_FEC_INI_VIG))))
            .andExpect(jsonPath("$.[*].fecFinVig").value(hasItem(sameInstant(DEFAULT_FEC_FIN_VIG))))
            .andExpect(jsonPath("$.[*].costoProd").value(hasItem(DEFAULT_COSTO_PROD.doubleValue())))
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
    void getProducto() throws Exception {
        // Initialize the database
        productoRepository.save(producto);

        // Get the producto
        restProductoMockMvc
            .perform(get(ENTITY_API_URL_ID, producto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(producto.getId()))
            .andExpect(jsonPath("$.codProducto").value(DEFAULT_COD_PRODUCTO))
            .andExpect(jsonPath("$.tipProducto").value(DEFAULT_TIP_PRODUCTO))
            .andExpect(jsonPath("$.codQr").value(DEFAULT_COD_QR))
            .andExpect(jsonPath("$.codBarra").value(DEFAULT_COD_BARRA))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.detalleDesc").value(DEFAULT_DETALLE_DESC))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.doubleValue()))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA))
            .andExpect(jsonPath("$.subCategoria").value(DEFAULT_SUB_CATEGORIA))
            .andExpect(jsonPath("$.categoriaMenu").value(DEFAULT_CATEGORIA_MENU))
            .andExpect(jsonPath("$.urlImage").value(DEFAULT_URL_IMAGE))
            .andExpect(jsonPath("$.fecIniVig").value(sameInstant(DEFAULT_FEC_INI_VIG)))
            .andExpect(jsonPath("$.fecFinVig").value(sameInstant(DEFAULT_FEC_FIN_VIG)))
            .andExpect(jsonPath("$.costoProd").value(DEFAULT_COSTO_PROD.doubleValue()))
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
    void getNonExistingProducto() throws Exception {
        // Get the producto
        restProductoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingProducto() throws Exception {
        // Initialize the database
        productoRepository.save(producto);

        int databaseSizeBeforeUpdate = productoRepository.findAll().size();

        // Update the producto
        Producto updatedProducto = productoRepository.findById(producto.getId()).get();
        updatedProducto
            .codProducto(UPDATED_COD_PRODUCTO)
            .tipProducto(UPDATED_TIP_PRODUCTO)
            .codQr(UPDATED_COD_QR)
            .codBarra(UPDATED_COD_BARRA)
            .descripcion(UPDATED_DESCRIPCION)
            .detalleDesc(UPDATED_DETALLE_DESC)
            .valor(UPDATED_VALOR)
            .categoria(UPDATED_CATEGORIA)
            .subCategoria(UPDATED_SUB_CATEGORIA)
            .categoriaMenu(UPDATED_CATEGORIA_MENU)
            .urlImage(UPDATED_URL_IMAGE)
            .fecIniVig(UPDATED_FEC_INI_VIG)
            .fecFinVig(UPDATED_FEC_FIN_VIG)
            .costoProd(UPDATED_COSTO_PROD)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restProductoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProducto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedProducto))
            )
            .andExpect(status().isOk());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
        Producto testProducto = productoList.get(productoList.size() - 1);
        assertThat(testProducto.getCodProducto()).isEqualTo(UPDATED_COD_PRODUCTO);
        assertThat(testProducto.getTipProducto()).isEqualTo(UPDATED_TIP_PRODUCTO);
        assertThat(testProducto.getCodQr()).isEqualTo(UPDATED_COD_QR);
        assertThat(testProducto.getCodBarra()).isEqualTo(UPDATED_COD_BARRA);
        assertThat(testProducto.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testProducto.getDetalleDesc()).isEqualTo(UPDATED_DETALLE_DESC);
        assertThat(testProducto.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testProducto.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testProducto.getSubCategoria()).isEqualTo(UPDATED_SUB_CATEGORIA);
        assertThat(testProducto.getCategoriaMenu()).isEqualTo(UPDATED_CATEGORIA_MENU);
        assertThat(testProducto.getUrlImage()).isEqualTo(UPDATED_URL_IMAGE);
        assertThat(testProducto.getFecIniVig()).isEqualTo(UPDATED_FEC_INI_VIG);
        assertThat(testProducto.getFecFinVig()).isEqualTo(UPDATED_FEC_FIN_VIG);
        assertThat(testProducto.getCostoProd()).isEqualTo(UPDATED_COSTO_PROD);
        assertThat(testProducto.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testProducto.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testProducto.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testProducto.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testProducto.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testProducto.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testProducto.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testProducto.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testProducto.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingProducto() throws Exception {
        int databaseSizeBeforeUpdate = productoRepository.findAll().size();
        producto.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, producto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(producto))
            )
            .andExpect(status().isBadRequest());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchProducto() throws Exception {
        int databaseSizeBeforeUpdate = productoRepository.findAll().size();
        producto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(producto))
            )
            .andExpect(status().isBadRequest());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamProducto() throws Exception {
        int databaseSizeBeforeUpdate = productoRepository.findAll().size();
        producto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(producto)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateProductoWithPatch() throws Exception {
        // Initialize the database
        productoRepository.save(producto);

        int databaseSizeBeforeUpdate = productoRepository.findAll().size();

        // Update the producto using partial update
        Producto partialUpdatedProducto = new Producto();
        partialUpdatedProducto.setId(producto.getId());

        partialUpdatedProducto
            .codProducto(UPDATED_COD_PRODUCTO)
            .tipProducto(UPDATED_TIP_PRODUCTO)
            .descripcion(UPDATED_DESCRIPCION)
            .valor(UPDATED_VALOR)
            .categoria(UPDATED_CATEGORIA)
            .categoriaMenu(UPDATED_CATEGORIA_MENU)
            .urlImage(UPDATED_URL_IMAGE)
            .fecIniVig(UPDATED_FEC_INI_VIG)
            .fecFinVig(UPDATED_FEC_FIN_VIG)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .usuModif(UPDATED_USU_MODIF);

        restProductoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProducto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProducto))
            )
            .andExpect(status().isOk());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
        Producto testProducto = productoList.get(productoList.size() - 1);
        assertThat(testProducto.getCodProducto()).isEqualTo(UPDATED_COD_PRODUCTO);
        assertThat(testProducto.getTipProducto()).isEqualTo(UPDATED_TIP_PRODUCTO);
        assertThat(testProducto.getCodQr()).isEqualTo(DEFAULT_COD_QR);
        assertThat(testProducto.getCodBarra()).isEqualTo(DEFAULT_COD_BARRA);
        assertThat(testProducto.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testProducto.getDetalleDesc()).isEqualTo(DEFAULT_DETALLE_DESC);
        assertThat(testProducto.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testProducto.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testProducto.getSubCategoria()).isEqualTo(DEFAULT_SUB_CATEGORIA);
        assertThat(testProducto.getCategoriaMenu()).isEqualTo(UPDATED_CATEGORIA_MENU);
        assertThat(testProducto.getUrlImage()).isEqualTo(UPDATED_URL_IMAGE);
        assertThat(testProducto.getFecIniVig()).isEqualTo(UPDATED_FEC_INI_VIG);
        assertThat(testProducto.getFecFinVig()).isEqualTo(UPDATED_FEC_FIN_VIG);
        assertThat(testProducto.getCostoProd()).isEqualTo(DEFAULT_COSTO_PROD);
        assertThat(testProducto.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testProducto.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testProducto.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testProducto.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testProducto.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testProducto.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testProducto.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testProducto.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testProducto.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void fullUpdateProductoWithPatch() throws Exception {
        // Initialize the database
        productoRepository.save(producto);

        int databaseSizeBeforeUpdate = productoRepository.findAll().size();

        // Update the producto using partial update
        Producto partialUpdatedProducto = new Producto();
        partialUpdatedProducto.setId(producto.getId());

        partialUpdatedProducto
            .codProducto(UPDATED_COD_PRODUCTO)
            .tipProducto(UPDATED_TIP_PRODUCTO)
            .codQr(UPDATED_COD_QR)
            .codBarra(UPDATED_COD_BARRA)
            .descripcion(UPDATED_DESCRIPCION)
            .detalleDesc(UPDATED_DETALLE_DESC)
            .valor(UPDATED_VALOR)
            .categoria(UPDATED_CATEGORIA)
            .subCategoria(UPDATED_SUB_CATEGORIA)
            .categoriaMenu(UPDATED_CATEGORIA_MENU)
            .urlImage(UPDATED_URL_IMAGE)
            .fecIniVig(UPDATED_FEC_INI_VIG)
            .fecFinVig(UPDATED_FEC_FIN_VIG)
            .costoProd(UPDATED_COSTO_PROD)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restProductoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProducto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProducto))
            )
            .andExpect(status().isOk());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
        Producto testProducto = productoList.get(productoList.size() - 1);
        assertThat(testProducto.getCodProducto()).isEqualTo(UPDATED_COD_PRODUCTO);
        assertThat(testProducto.getTipProducto()).isEqualTo(UPDATED_TIP_PRODUCTO);
        assertThat(testProducto.getCodQr()).isEqualTo(UPDATED_COD_QR);
        assertThat(testProducto.getCodBarra()).isEqualTo(UPDATED_COD_BARRA);
        assertThat(testProducto.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testProducto.getDetalleDesc()).isEqualTo(UPDATED_DETALLE_DESC);
        assertThat(testProducto.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testProducto.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testProducto.getSubCategoria()).isEqualTo(UPDATED_SUB_CATEGORIA);
        assertThat(testProducto.getCategoriaMenu()).isEqualTo(UPDATED_CATEGORIA_MENU);
        assertThat(testProducto.getUrlImage()).isEqualTo(UPDATED_URL_IMAGE);
        assertThat(testProducto.getFecIniVig()).isEqualTo(UPDATED_FEC_INI_VIG);
        assertThat(testProducto.getFecFinVig()).isEqualTo(UPDATED_FEC_FIN_VIG);
        assertThat(testProducto.getCostoProd()).isEqualTo(UPDATED_COSTO_PROD);
        assertThat(testProducto.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testProducto.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testProducto.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testProducto.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testProducto.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testProducto.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testProducto.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testProducto.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testProducto.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingProducto() throws Exception {
        int databaseSizeBeforeUpdate = productoRepository.findAll().size();
        producto.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, producto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(producto))
            )
            .andExpect(status().isBadRequest());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchProducto() throws Exception {
        int databaseSizeBeforeUpdate = productoRepository.findAll().size();
        producto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(producto))
            )
            .andExpect(status().isBadRequest());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamProducto() throws Exception {
        int databaseSizeBeforeUpdate = productoRepository.findAll().size();
        producto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(producto)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteProducto() throws Exception {
        // Initialize the database
        productoRepository.save(producto);

        int databaseSizeBeforeDelete = productoRepository.findAll().size();

        // Delete the producto
        restProductoMockMvc
            .perform(delete(ENTITY_API_URL_ID, producto.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
