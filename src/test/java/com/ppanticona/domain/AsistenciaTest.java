package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AsistenciaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Asistencia.class);
        Asistencia asistencia1 = new Asistencia();
        asistencia1.setId("id1");
        Asistencia asistencia2 = new Asistencia();
        asistencia2.setId(asistencia1.getId());
        assertThat(asistencia1).isEqualTo(asistencia2);
        asistencia2.setId("id2");
        assertThat(asistencia1).isNotEqualTo(asistencia2);
        asistencia1.setId(null);
        assertThat(asistencia1).isNotEqualTo(asistencia2);
    }
}
