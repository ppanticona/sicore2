package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ParametrosTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Parametros.class);
        Parametros parametros1 = new Parametros();
        parametros1.setId("id1");
        Parametros parametros2 = new Parametros();
        parametros2.setId(parametros1.getId());
        assertThat(parametros1).isEqualTo(parametros2);
        parametros2.setId("id2");
        assertThat(parametros1).isNotEqualTo(parametros2);
        parametros1.setId(null);
        assertThat(parametros1).isNotEqualTo(parametros2);
    }
}
