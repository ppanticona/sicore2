package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrecuentaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Precuenta.class);
        Precuenta precuenta1 = new Precuenta();
        precuenta1.setId("id1");
        Precuenta precuenta2 = new Precuenta();
        precuenta2.setId(precuenta1.getId());
        assertThat(precuenta1).isEqualTo(precuenta2);
        precuenta2.setId("id2");
        assertThat(precuenta1).isNotEqualTo(precuenta2);
        precuenta1.setId(null);
        assertThat(precuenta1).isNotEqualTo(precuenta2);
    }
}
