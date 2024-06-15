package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MovimientoCajaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MovimientoCaja.class);
        MovimientoCaja movimientoCaja1 = new MovimientoCaja();
        movimientoCaja1.setId("id1");
        MovimientoCaja movimientoCaja2 = new MovimientoCaja();
        movimientoCaja2.setId(movimientoCaja1.getId());
        assertThat(movimientoCaja1).isEqualTo(movimientoCaja2);
        movimientoCaja2.setId("id2");
        assertThat(movimientoCaja1).isNotEqualTo(movimientoCaja2);
        movimientoCaja1.setId(null);
        assertThat(movimientoCaja1).isNotEqualTo(movimientoCaja2);
    }
}
