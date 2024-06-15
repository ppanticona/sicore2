package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProveedoresTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Proveedores.class);
        Proveedores proveedores1 = new Proveedores();
        proveedores1.setId("id1");
        Proveedores proveedores2 = new Proveedores();
        proveedores2.setId(proveedores1.getId());
        assertThat(proveedores1).isEqualTo(proveedores2);
        proveedores2.setId("id2");
        assertThat(proveedores1).isNotEqualTo(proveedores2);
        proveedores1.setId(null);
        assertThat(proveedores1).isNotEqualTo(proveedores2);
    }
}
