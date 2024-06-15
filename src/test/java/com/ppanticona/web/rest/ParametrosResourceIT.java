package com.ppanticona.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.Parametros;
import com.ppanticona.repository.ParametrosRepository;
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
 * Integration tests for the {@link ParametrosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ParametrosResourceIT {

    private static final String DEFAULT_COD_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_COD_PARAM = "BBBBBBBBBB";

    private static final String DEFAULT_DET_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_DET_PARAM = "BBBBBBBBBB";

    private static final String DEFAULT_PRIM_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_PRIM_PARAM = "BBBBBBBBBB";

    private static final String DEFAULT_SEG_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_SEG_PARAM = "BBBBBBBBBB";

    private static final String DEFAULT_TERC_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_TERC_PARAM = "BBBBBBBBBB";

    private static final String DEFAULT_CUAR_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_CUAR_PARAM = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Integer DEFAULT_SEQUENCE = 1;
    private static final Integer UPDATED_SEQUENCE = 2;

    private static final String ENTITY_API_URL = "/api/parametros";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ParametrosRepository parametrosRepository;

    @Autowired
    private MockMvc restParametrosMockMvc;

    private Parametros parametros;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parametros createEntity() {
        Parametros parametros = new Parametros()
            .codParam(DEFAULT_COD_PARAM)
            .detParam(DEFAULT_DET_PARAM)
            .primParam(DEFAULT_PRIM_PARAM)
            .segParam(DEFAULT_SEG_PARAM)
            .tercParam(DEFAULT_TERC_PARAM)
            .cuarParam(DEFAULT_CUAR_PARAM)
            .descripcion(DEFAULT_DESCRIPCION)
            .sequence(DEFAULT_SEQUENCE);
        return parametros;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parametros createUpdatedEntity() {
        Parametros parametros = new Parametros()
            .codParam(UPDATED_COD_PARAM)
            .detParam(UPDATED_DET_PARAM)
            .primParam(UPDATED_PRIM_PARAM)
            .segParam(UPDATED_SEG_PARAM)
            .tercParam(UPDATED_TERC_PARAM)
            .cuarParam(UPDATED_CUAR_PARAM)
            .descripcion(UPDATED_DESCRIPCION)
            .sequence(UPDATED_SEQUENCE);
        return parametros;
    }

    @BeforeEach
    public void initTest() {
        parametrosRepository.deleteAll();
        parametros = createEntity();
    }

    @Test
    void createParametros() throws Exception {
        int databaseSizeBeforeCreate = parametrosRepository.findAll().size();
        // Create the Parametros
        restParametrosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(parametros)))
            .andExpect(status().isCreated());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeCreate + 1);
        Parametros testParametros = parametrosList.get(parametrosList.size() - 1);
        assertThat(testParametros.getCodParam()).isEqualTo(DEFAULT_COD_PARAM);
        assertThat(testParametros.getDetParam()).isEqualTo(DEFAULT_DET_PARAM);
        assertThat(testParametros.getPrimParam()).isEqualTo(DEFAULT_PRIM_PARAM);
        assertThat(testParametros.getSegParam()).isEqualTo(DEFAULT_SEG_PARAM);
        assertThat(testParametros.getTercParam()).isEqualTo(DEFAULT_TERC_PARAM);
        assertThat(testParametros.getCuarParam()).isEqualTo(DEFAULT_CUAR_PARAM);
        assertThat(testParametros.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testParametros.getSequence()).isEqualTo(DEFAULT_SEQUENCE);
    }

    @Test
    void createParametrosWithExistingId() throws Exception {
        // Create the Parametros with an existing ID
        parametros.setId("existing_id");

        int databaseSizeBeforeCreate = parametrosRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restParametrosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(parametros)))
            .andExpect(status().isBadRequest());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllParametros() throws Exception {
        // Initialize the database
        parametrosRepository.save(parametros);

        // Get all the parametrosList
        restParametrosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parametros.getId())))
            .andExpect(jsonPath("$.[*].codParam").value(hasItem(DEFAULT_COD_PARAM)))
            .andExpect(jsonPath("$.[*].detParam").value(hasItem(DEFAULT_DET_PARAM)))
            .andExpect(jsonPath("$.[*].primParam").value(hasItem(DEFAULT_PRIM_PARAM)))
            .andExpect(jsonPath("$.[*].segParam").value(hasItem(DEFAULT_SEG_PARAM)))
            .andExpect(jsonPath("$.[*].tercParam").value(hasItem(DEFAULT_TERC_PARAM)))
            .andExpect(jsonPath("$.[*].cuarParam").value(hasItem(DEFAULT_CUAR_PARAM)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].sequence").value(hasItem(DEFAULT_SEQUENCE)));
    }

    @Test
    void getParametros() throws Exception {
        // Initialize the database
        parametrosRepository.save(parametros);

        // Get the parametros
        restParametrosMockMvc
            .perform(get(ENTITY_API_URL_ID, parametros.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(parametros.getId()))
            .andExpect(jsonPath("$.codParam").value(DEFAULT_COD_PARAM))
            .andExpect(jsonPath("$.detParam").value(DEFAULT_DET_PARAM))
            .andExpect(jsonPath("$.primParam").value(DEFAULT_PRIM_PARAM))
            .andExpect(jsonPath("$.segParam").value(DEFAULT_SEG_PARAM))
            .andExpect(jsonPath("$.tercParam").value(DEFAULT_TERC_PARAM))
            .andExpect(jsonPath("$.cuarParam").value(DEFAULT_CUAR_PARAM))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.sequence").value(DEFAULT_SEQUENCE));
    }

    @Test
    void getNonExistingParametros() throws Exception {
        // Get the parametros
        restParametrosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingParametros() throws Exception {
        // Initialize the database
        parametrosRepository.save(parametros);

        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();

        // Update the parametros
        Parametros updatedParametros = parametrosRepository.findById(parametros.getId()).get();
        updatedParametros
            .codParam(UPDATED_COD_PARAM)
            .detParam(UPDATED_DET_PARAM)
            .primParam(UPDATED_PRIM_PARAM)
            .segParam(UPDATED_SEG_PARAM)
            .tercParam(UPDATED_TERC_PARAM)
            .cuarParam(UPDATED_CUAR_PARAM)
            .descripcion(UPDATED_DESCRIPCION)
            .sequence(UPDATED_SEQUENCE);

        restParametrosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedParametros.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedParametros))
            )
            .andExpect(status().isOk());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
        Parametros testParametros = parametrosList.get(parametrosList.size() - 1);
        assertThat(testParametros.getCodParam()).isEqualTo(UPDATED_COD_PARAM);
        assertThat(testParametros.getDetParam()).isEqualTo(UPDATED_DET_PARAM);
        assertThat(testParametros.getPrimParam()).isEqualTo(UPDATED_PRIM_PARAM);
        assertThat(testParametros.getSegParam()).isEqualTo(UPDATED_SEG_PARAM);
        assertThat(testParametros.getTercParam()).isEqualTo(UPDATED_TERC_PARAM);
        assertThat(testParametros.getCuarParam()).isEqualTo(UPDATED_CUAR_PARAM);
        assertThat(testParametros.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testParametros.getSequence()).isEqualTo(UPDATED_SEQUENCE);
    }

    @Test
    void putNonExistingParametros() throws Exception {
        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();
        parametros.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParametrosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, parametros.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(parametros))
            )
            .andExpect(status().isBadRequest());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchParametros() throws Exception {
        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();
        parametros.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restParametrosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(parametros))
            )
            .andExpect(status().isBadRequest());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamParametros() throws Exception {
        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();
        parametros.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restParametrosMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(parametros)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateParametrosWithPatch() throws Exception {
        // Initialize the database
        parametrosRepository.save(parametros);

        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();

        // Update the parametros using partial update
        Parametros partialUpdatedParametros = new Parametros();
        partialUpdatedParametros.setId(parametros.getId());

        partialUpdatedParametros
            .codParam(UPDATED_COD_PARAM)
            .detParam(UPDATED_DET_PARAM)
            .primParam(UPDATED_PRIM_PARAM)
            .segParam(UPDATED_SEG_PARAM);

        restParametrosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedParametros.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedParametros))
            )
            .andExpect(status().isOk());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
        Parametros testParametros = parametrosList.get(parametrosList.size() - 1);
        assertThat(testParametros.getCodParam()).isEqualTo(UPDATED_COD_PARAM);
        assertThat(testParametros.getDetParam()).isEqualTo(UPDATED_DET_PARAM);
        assertThat(testParametros.getPrimParam()).isEqualTo(UPDATED_PRIM_PARAM);
        assertThat(testParametros.getSegParam()).isEqualTo(UPDATED_SEG_PARAM);
        assertThat(testParametros.getTercParam()).isEqualTo(DEFAULT_TERC_PARAM);
        assertThat(testParametros.getCuarParam()).isEqualTo(DEFAULT_CUAR_PARAM);
        assertThat(testParametros.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testParametros.getSequence()).isEqualTo(DEFAULT_SEQUENCE);
    }

    @Test
    void fullUpdateParametrosWithPatch() throws Exception {
        // Initialize the database
        parametrosRepository.save(parametros);

        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();

        // Update the parametros using partial update
        Parametros partialUpdatedParametros = new Parametros();
        partialUpdatedParametros.setId(parametros.getId());

        partialUpdatedParametros
            .codParam(UPDATED_COD_PARAM)
            .detParam(UPDATED_DET_PARAM)
            .primParam(UPDATED_PRIM_PARAM)
            .segParam(UPDATED_SEG_PARAM)
            .tercParam(UPDATED_TERC_PARAM)
            .cuarParam(UPDATED_CUAR_PARAM)
            .descripcion(UPDATED_DESCRIPCION)
            .sequence(UPDATED_SEQUENCE);

        restParametrosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedParametros.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedParametros))
            )
            .andExpect(status().isOk());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
        Parametros testParametros = parametrosList.get(parametrosList.size() - 1);
        assertThat(testParametros.getCodParam()).isEqualTo(UPDATED_COD_PARAM);
        assertThat(testParametros.getDetParam()).isEqualTo(UPDATED_DET_PARAM);
        assertThat(testParametros.getPrimParam()).isEqualTo(UPDATED_PRIM_PARAM);
        assertThat(testParametros.getSegParam()).isEqualTo(UPDATED_SEG_PARAM);
        assertThat(testParametros.getTercParam()).isEqualTo(UPDATED_TERC_PARAM);
        assertThat(testParametros.getCuarParam()).isEqualTo(UPDATED_CUAR_PARAM);
        assertThat(testParametros.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testParametros.getSequence()).isEqualTo(UPDATED_SEQUENCE);
    }

    @Test
    void patchNonExistingParametros() throws Exception {
        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();
        parametros.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParametrosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, parametros.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(parametros))
            )
            .andExpect(status().isBadRequest());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchParametros() throws Exception {
        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();
        parametros.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restParametrosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(parametros))
            )
            .andExpect(status().isBadRequest());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamParametros() throws Exception {
        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();
        parametros.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restParametrosMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(parametros))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteParametros() throws Exception {
        // Initialize the database
        parametrosRepository.save(parametros);

        int databaseSizeBeforeDelete = parametrosRepository.findAll().size();

        // Delete the parametros
        restParametrosMockMvc
            .perform(delete(ENTITY_API_URL_ID, parametros.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
