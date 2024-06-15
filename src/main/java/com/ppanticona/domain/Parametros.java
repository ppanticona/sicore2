package com.ppanticona.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Parametros.
 */
@Document(collection = "parametros")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("cod_param")
    private String codParam;

    @Field("det_param")
    private String detParam;

    @Field("prim_param")
    private String primParam;

    @Field("seg_param")
    private String segParam;

    @Field("terc_param")
    private String tercParam;

    @Field("cuar_param")
    private String cuarParam;

    @Field("descripcion")
    private String descripcion;

    @Field("sequence")
    private Integer sequence;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Parametros id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodParam() {
        return this.codParam;
    }

    public Parametros codParam(String codParam) {
        this.setCodParam(codParam);
        return this;
    }

    public void setCodParam(String codParam) {
        this.codParam = codParam;
    }

    public String getDetParam() {
        return this.detParam;
    }

    public Parametros detParam(String detParam) {
        this.setDetParam(detParam);
        return this;
    }

    public void setDetParam(String detParam) {
        this.detParam = detParam;
    }

    public String getPrimParam() {
        return this.primParam;
    }

    public Parametros primParam(String primParam) {
        this.setPrimParam(primParam);
        return this;
    }

    public void setPrimParam(String primParam) {
        this.primParam = primParam;
    }

    public String getSegParam() {
        return this.segParam;
    }

    public Parametros segParam(String segParam) {
        this.setSegParam(segParam);
        return this;
    }

    public void setSegParam(String segParam) {
        this.segParam = segParam;
    }

    public String getTercParam() {
        return this.tercParam;
    }

    public Parametros tercParam(String tercParam) {
        this.setTercParam(tercParam);
        return this;
    }

    public void setTercParam(String tercParam) {
        this.tercParam = tercParam;
    }

    public String getCuarParam() {
        return this.cuarParam;
    }

    public Parametros cuarParam(String cuarParam) {
        this.setCuarParam(cuarParam);
        return this;
    }

    public void setCuarParam(String cuarParam) {
        this.cuarParam = cuarParam;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Parametros descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getSequence() {
        return this.sequence;
    }

    public Parametros sequence(Integer sequence) {
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
        if (!(o instanceof Parametros)) {
            return false;
        }
        return id != null && id.equals(((Parametros) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Parametros{" +
            "id=" + getId() +
            ", codParam='" + getCodParam() + "'" +
            ", detParam='" + getDetParam() + "'" +
            ", primParam='" + getPrimParam() + "'" +
            ", segParam='" + getSegParam() + "'" +
            ", tercParam='" + getTercParam() + "'" +
            ", cuarParam='" + getCuarParam() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", sequence=" + getSequence() +
            "}";
    }
}
