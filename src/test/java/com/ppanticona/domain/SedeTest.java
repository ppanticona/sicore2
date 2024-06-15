package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SedeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sede.class);
        Sede sede1 = new Sede();
        sede1.setId("id1");
        Sede sede2 = new Sede();
        sede2.setId(sede1.getId());
        assertThat(sede1).isEqualTo(sede2);
        sede2.setId("id2");
        assertThat(sede1).isNotEqualTo(sede2);
        sede1.setId(null);
        assertThat(sede1).isNotEqualTo(sede2);
    }
}
