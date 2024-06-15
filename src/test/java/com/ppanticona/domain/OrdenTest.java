package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrdenTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Orden.class);
        Orden orden1 = new Orden();
        orden1.setId("id1");
        Orden orden2 = new Orden();
        orden2.setId(orden1.getId());
        assertThat(orden1).isEqualTo(orden2);
        orden2.setId("id2");
        assertThat(orden1).isNotEqualTo(orden2);
        orden1.setId(null);
        assertThat(orden1).isNotEqualTo(orden2);
    }
}
