package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DetallePrecuentaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetallePrecuenta.class);
        DetallePrecuenta detallePrecuenta1 = new DetallePrecuenta();
        detallePrecuenta1.setId("id1");
        DetallePrecuenta detallePrecuenta2 = new DetallePrecuenta();
        detallePrecuenta2.setId(detallePrecuenta1.getId());
        assertThat(detallePrecuenta1).isEqualTo(detallePrecuenta2);
        detallePrecuenta2.setId("id2");
        assertThat(detallePrecuenta1).isNotEqualTo(detallePrecuenta2);
        detallePrecuenta1.setId(null);
        assertThat(detallePrecuenta1).isNotEqualTo(detallePrecuenta2);
    }
}
