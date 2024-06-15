package com.ppanticona.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Secuencias.
 */
@Document(collection = "secuencias")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Secuencias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("seqid")
    private String seqid;

    @Field("descripcion")
    private String descripcion;

    @Field("sequence")
    private Integer sequence;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Secuencias id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeqid() {
        return this.seqid;
    }

    public Secuencias seqid(String seqid) {
        this.setSeqid(seqid);
        return this;
    }

    public void setSeqid(String seqid) {
        this.seqid = seqid;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Secuencias descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getSequence() {
        return this.sequence;
    }

    public Secuencias sequence(Integer sequence) {
        this.setSequence(sequence);
        return this;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Secuencias)) {
            return false;
        }
        return id != null && id.equals(((Secuencias) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Secuencias{" +
            "id=" + getId() +
            ", seqid='" + getSeqid() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", sequence=" + getSequence() +
            "}";
    }
}
