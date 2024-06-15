package com.ppanticona.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MesasTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mesas.class);
        Mesas mesas1 = new Mesas();
        mesas1.setId("id1");
        Mesas mesas2 = new Mesas();
        mesas2.setId(mesas1.getId());
        assertThat(mesas1).isEqualTo(mesas2);
        mesas2.setId("id2");
        assertThat(mesas1).isNotEqualTo(mesas2);
        mesas1.setId(null);
        assertThat(mesas1).isNotEqualTo(mesas2);
    }
}
