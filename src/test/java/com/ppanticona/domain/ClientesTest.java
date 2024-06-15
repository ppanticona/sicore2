package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClientesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Clientes.class);
        Clientes clientes1 = new Clientes();
        clientes1.setId("id1");
        Clientes clientes2 = new Clientes();
        clientes2.setId(clientes1.getId());
        assertThat(clientes1).isEqualTo(clientes2);
        clientes2.setId("id2");
        assertThat(clientes1).isNotEqualTo(clientes2);
        clientes1.setId(null);
        assertThat(clientes1).isNotEqualTo(clientes2);
    }
}
