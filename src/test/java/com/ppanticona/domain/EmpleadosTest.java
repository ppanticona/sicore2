package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EmpleadosTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Empleados.class);
        Empleados empleados1 = new Empleados();
        empleados1.setId("id1");
        Empleados empleados2 = new Empleados();
        empleados2.setId(empleados1.getId());
        assertThat(empleados1).isEqualTo(empleados2);
        empleados2.setId("id2");
        assertThat(empleados1).isNotEqualTo(empleados2);
        empleados1.setId(null);
        assertThat(empleados1).isNotEqualTo(empleados2);
    }
}
