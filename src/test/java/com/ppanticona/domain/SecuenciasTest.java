package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecuenciasTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Secuencias.class);
        Secuencias secuencias1 = new Secuencias();
        secuencias1.setId("id1");
        Secuencias secuencias2 = new Secuencias();
        secuencias2.setId(secuencias1.getId());
        assertThat(secuencias1).isEqualTo(secuencias2);
        secuencias2.setId("id2");
        assertThat(secuencias1).isNotEqualTo(secuencias2);
        secuencias1.setId(null);
        assertThat(secuencias1).isNotEqualTo(secuencias2);
    }
}
