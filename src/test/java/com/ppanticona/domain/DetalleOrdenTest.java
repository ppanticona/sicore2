package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DetalleOrdenTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetalleOrden.class);
        DetalleOrden detalleOrden1 = new DetalleOrden();
        detalleOrden1.setId("id1");
        DetalleOrden detalleOrden2 = new DetalleOrden();
        detalleOrden2.setId(detalleOrden1.getId());
        assertThat(detalleOrden1).isEqualTo(detalleOrden2);
        detalleOrden2.setId("id2");
        assertThat(detalleOrden1).isNotEqualTo(detalleOrden2);
        detalleOrden1.setId(null);
        assertThat(detalleOrden1).isNotEqualTo(detalleOrden2);
    }
}
