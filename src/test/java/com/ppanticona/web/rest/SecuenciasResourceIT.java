package com.ppanticona.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.IntegrationTest;
import com.ppanticona.domain.Secuencias;
import com.ppanticona.repository.SecuenciasRepository;
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
 * Integration tests for the {@link SecuenciasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SecuenciasResourceIT {

    private static final String DEFAULT_SEQID = "AAAAAAAAAA";
    private static final String UPDATED_SEQID = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Integer DEFAULT_SEQUENCE = 1;
    private static final Integer UPDATED_SEQUENCE = 2;

    private static final String ENTITY_API_URL = "/api/secuencias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SecuenciasRepository secuenciasRepository;

    @Autowired
    private MockMvc restSecuenciasMockMvc;

    private Secuencias secuencias;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Secuencias createEntity() {
        Secuencias secuencias = new Secuencias().seqid(DEFAULT_SEQID).descripcion(DEFAULT_DESCRIPCION).sequence(DEFAULT_SEQUENCE);
        return secuencias;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Secuencias createUpdatedEntity() {
        Secuencias secuencias = new Secuencias().seqid(UPDATED_SEQID).descripcion(UPDATED_DESCRIPCION).sequence(UPDATED_SEQUENCE);
        return secuencias;
    }

    @BeforeEach
    public void initTest() {
        secuenciasRepository.deleteAll();
        secuencias = createEntity();
    }

    @Test
    void createSecuencias() throws Exception {
        int databaseSizeBeforeCreate = secuenciasRepository.findAll().size();
        // Create the Secuencias
        restSecuenciasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(secuencias)))
            .andExpect(status().isCreated());

        // Validate the Secuencias in the database
        List<Secuencias> secuenciasList = secuenciasRepository.findAll();
        assertThat(secuenciasList).hasSize(databaseSizeBeforeCreate + 1);
        Secuencias testSecuencias = secuenciasList.get(secuenciasList.size() - 1);
        assertThat(testSecuencias.getSeqid()).isEqualTo(DEFAULT_SEQID);
        assertThat(testSecuencias.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testSecuencias.getSequence()).isEqualTo(DEFAULT_SEQUENCE);
    }

    @Test
    void createSecuenciasWithExistingId() throws Exception {
        // Create the Secuencias with an existing ID
        secuencias.setId("existing_id");

        int databaseSizeBeforeCreate = secuenciasRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecuenciasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(secuencias)))
            .andExpect(status().isBadRequest());

        // Validate the Secuencias in the database
        List<Secuencias> secuenciasList = secuenciasRepository.findAll();
        assertThat(secuenciasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSecuencias() throws Exception {
        // Initialize the database
        secuenciasRepository.save(secuencias);

        // Get all the secuenciasList
        restSecuenciasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(secuencias.getId())))
            .andExpect(jsonPath("$.[*].seqid").value(hasItem(DEFAULT_SEQID)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].sequence").value(hasItem(DEFAULT_SEQUENCE)));
    }

    @Test
    void getSecuencias() throws Exception {
        // Initialize the database
        secuenciasRepository.save(secuencias);

        // Get the secuencias
        restSecuenciasMockMvc
            .perform(get(ENTITY_API_URL_ID, secuencias.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(secuencias.getId()))
            .andExpect(jsonPath("$.seqid").value(DEFAULT_SEQID))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.sequence").value(DEFAULT_SEQUENCE));
    }

    @Test
    void getNonExistingSecuencias() throws Exception {
        // Get the secuencias
        restSecuenciasMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingSecuencias() throws Exception {
        // Initialize the database
        secuenciasRepository.save(secuencias);

        int databaseSizeBeforeUpdate = secuenciasRepository.findAll().size();

        // Update the secuencias
        Secuencias updatedSecuencias = secuenciasRepository.findById(secuencias.getId()).get();
        updatedSecuencias.seqid(UPDATED_SEQID).descripcion(UPDATED_DESCRIPCION).sequence(UPDATED_SEQUENCE);

        restSecuenciasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSecuencias.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSecuencias))
            )
            .andExpect(status().isOk());

        // Validate the Secuencias in the database
        List<Secuencias> secuenciasList = secuenciasRepository.findAll();
        assertThat(secuenciasList).hasSize(databaseSizeBeforeUpdate);
        Secuencias testSecuencias = secuenciasList.get(secuenciasList.size() - 1);
        assertThat(testSecuencias.getSeqid()).isEqualTo(UPDATED_SEQID);
        assertThat(testSecuencias.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testSecuencias.getSequence()).isEqualTo(UPDATED_SEQUENCE);
    }

    @Test
    void putNonExistingSecuencias() throws Exception {
        int databaseSizeBeforeUpdate = secuenciasRepository.findAll().size();
        secuencias.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecuenciasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, secuencias.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secuencias))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secuencias in the database
        List<Secuencias> secuenciasList = secuenciasRepository.findAll();
        assertThat(secuenciasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSecuencias() throws Exception {
        int databaseSizeBeforeUpdate = secuenciasRepository.findAll().size();
        secuencias.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecuenciasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(secuencias))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secuencias in the database
        List<Secuencias> secuenciasList = secuenciasRepository.findAll();
        assertThat(secuenciasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSecuencias() throws Exception {
        int databaseSizeBeforeUpdate = secuenciasRepository.findAll().size();
        secuencias.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecuenciasMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(secuencias)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Secuencias in the database
        List<Secuencias> secuenciasList = secuenciasRepository.findAll();
        assertThat(secuenciasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSecuenciasWithPatch() throws Exception {
        // Initialize the database
        secuenciasRepository.save(secuencias);

        int databaseSizeBeforeUpdate = secuenciasRepository.findAll().size();

        // Update the secuencias using partial update
        Secuencias partialUpdatedSecuencias = new Secuencias();
        partialUpdatedSecuencias.setId(secuencias.getId());

        partialUpdatedSecuencias.seqid(UPDATED_SEQID).sequence(UPDATED_SEQUENCE);

        restSecuenciasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecuencias.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSecuencias))
            )
            .andExpect(status().isOk());

        // Validate the Secuencias in the database
        List<Secuencias> secuenciasList = secuenciasRepository.findAll();
        assertThat(secuenciasList).hasSize(databaseSizeBeforeUpdate);
        Secuencias testSecuencias = secuenciasList.get(secuenciasList.size() - 1);
        assertThat(testSecuencias.getSeqid()).isEqualTo(UPDATED_SEQID);
        assertThat(testSecuencias.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testSecuencias.getSequence()).isEqualTo(UPDATED_SEQUENCE);
    }

    @Test
    void fullUpdateSecuenciasWithPatch() throws Exception {
        // Initialize the database
        secuenciasRepository.save(secuencias);

        int databaseSizeBeforeUpdate = secuenciasRepository.findAll().size();

        // Update the secuencias using partial update
        Secuencias partialUpdatedSecuencias = new Secuencias();
        partialUpdatedSecuencias.setId(secuencias.getId());

        partialUpdatedSecuencias.seqid(UPDATED_SEQID).descripcion(UPDATED_DESCRIPCION).sequence(UPDATED_SEQUENCE);

        restSecuenciasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecuencias.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSecuencias))
            )
            .andExpect(status().isOk());

        // Validate the Secuencias in the database
        List<Secuencias> secuenciasList = secuenciasRepository.findAll();
        assertThat(secuenciasList).hasSize(databaseSizeBeforeUpdate);
        Secuencias testSecuencias = secuenciasList.get(secuenciasList.size() - 1);
        assertThat(testSecuencias.getSeqid()).isEqualTo(UPDATED_SEQID);
        assertThat(testSecuencias.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testSecuencias.getSequence()).isEqualTo(UPDATED_SEQUENCE);
    }

    @Test
    void patchNonExistingSecuencias() throws Exception {
        int databaseSizeBeforeUpdate = secuenciasRepository.findAll().size();
        secuencias.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecuenciasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, secuencias.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(secuencias))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secuencias in the database
        List<Secuencias> secuenciasList = secuenciasRepository.findAll();
        assertThat(secuenciasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSecuencias() throws Exception {
        int databaseSizeBeforeUpdate = secuenciasRepository.findAll().size();
        secuencias.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecuenciasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(secuencias))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secuencias in the database
        List<Secuencias> secuenciasList = secuenciasRepository.findAll();
        assertThat(secuenciasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSecuencias() throws Exception {
        int databaseSizeBeforeUpdate = secuenciasRepository.findAll().size();
        secuencias.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecuenciasMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(secuencias))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Secuencias in the database
        List<Secuencias> secuenciasList = secuenciasRepository.findAll();
        assertThat(secuenciasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSecuencias() throws Exception {
        // Initialize the database
        secuenciasRepository.save(secuencias);

        int databaseSizeBeforeDelete = secuenciasRepository.findAll().size();

        // Delete the secuencias
        restSecuenciasMockMvc
            .perform(delete(ENTITY_API_URL_ID, secuencias.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Secuencias> secuenciasList = secuenciasRepository.findAll();
        assertThat(secuenciasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
