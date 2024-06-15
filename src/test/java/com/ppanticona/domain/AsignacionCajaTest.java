package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AsignacionCajaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AsignacionCaja.class);
        AsignacionCaja asignacionCaja1 = new AsignacionCaja();
        asignacionCaja1.setId("id1");
        AsignacionCaja asignacionCaja2 = new AsignacionCaja();
        asignacionCaja2.setId(asignacionCaja1.getId());
        assertThat(asignacionCaja1).isEqualTo(asignacionCaja2);
        asignacionCaja2.setId("id2");
        assertThat(asignacionCaja1).isNotEqualTo(asignacionCaja2);
        asignacionCaja1.setId(null);
        assertThat(asignacionCaja1).isNotEqualTo(asignacionCaja2);
    }
}
