package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AutorizacionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autorizacion.class);
        Autorizacion autorizacion1 = new Autorizacion();
        autorizacion1.setId("id1");
        Autorizacion autorizacion2 = new Autorizacion();
        autorizacion2.setId(autorizacion1.getId());
        assertThat(autorizacion1).isEqualTo(autorizacion2);
        autorizacion2.setId("id2");
        assertThat(autorizacion1).isNotEqualTo(autorizacion2);
        autorizacion1.setId(null);
        assertThat(autorizacion1).isNotEqualTo(autorizacion2);
    }
}
