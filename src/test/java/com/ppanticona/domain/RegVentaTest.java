package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RegVentaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegVenta.class);
        RegVenta regVenta1 = new RegVenta();
        regVenta1.setId("id1");
        RegVenta regVenta2 = new RegVenta();
        regVenta2.setId(regVenta1.getId());
        assertThat(regVenta1).isEqualTo(regVenta2);
        regVenta2.setId("id2");
        assertThat(regVenta1).isNotEqualTo(regVenta2);
        regVenta1.setId(null);
        assertThat(regVenta1).isNotEqualTo(regVenta2);
    }
}
