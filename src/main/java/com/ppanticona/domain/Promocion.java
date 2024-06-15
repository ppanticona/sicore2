package com.ppanticona.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Promocion.
 */
@Document(collection = "promocion")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Promocion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("tip_promocion")
    private String tipPromocion;

    @Field("val_1")
    private String val1;

    @Field("val_2")
    private String val2;

    @Field("val_3")
    private String val3;

    @Field("val_4")
    private String val4;

    @Field("val_5")
    private String val5;

    @Field("max_prom")
    private String maxProm;

    @NotNull
    @Field("fec_ini_vig")
    private ZonedDateTime fecIniVig;

    @NotNull
    @Field("fec_fin_vig")
    private ZonedDateTime fecFinVig;

    @Field("comentarios")
    private String comentarios;

    @NotNull
    @Field("estado")
    private String estado;

    @NotNull
    @Field("version")
    private Integer version;

    @NotNull
    @Field("ind_del")
    private Boolean indDel;

    @NotNull
    @Field("fec_crea")
    private ZonedDateTime fecCrea;

    @NotNull
    @Field("usu_crea")
    private String usuCrea;

    @NotNull
    @Field("ip_crea")
    private String ipCrea;

    @Field("fec_modif")
    private ZonedDateTime fecModif;

    @Field("usu_modif")
    private String usuModif;

    @Field("ip_modif")
    private String ipModif;

    @DBRef
    @Field("autorizacion")
    private Autorizacion autorizacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Promocion id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipPromocion() {
        return this.tipPromocion;
    }

    public Promocion tipPromocion(String tipPromocion) {
        this.setTipPromocion(tipPromocion);
        return this;
    }

    public void setTipPromocion(String tipPromocion) {
        this.tipPromocion = tipPromocion;
    }

    public String getVal1() {
        return this.val1;
    }

    public Promocion val1(String val1) {
        this.setVal1(val1);
        return this;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    public String getVal2() {
        return this.val2;
    }

    public Promocion val2(String val2) {
        this.setVal2(val2);
        return this;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public String getVal3() {
        return this.val3;
    }

    public Promocion val3(String val3) {
        this.setVal3(val3);
        return this;
    }

    public void setVal3(String val3) {
        this.val3 = val3;
    }

    public String getVal4() {
        return this.val4;
    }

    public Promocion val4(String val4) {
        this.setVal4(val4);
        return this;
    }

    public void setVal4(String val4) {
        this.val4 = val4;
    }

    public String getVal5() {
        return this.val5;
    }

    public Promocion val5(String val5) {
        this.setVal5(val5);
        return this;
    }

    public void setVal5(String val5) {
        this.val5 = val5;
    }

    public String getMaxProm() {
        return this.maxProm;
    }

    public Promocion maxProm(String maxProm) {
        this.setMaxProm(maxProm);
        return this;
    }

    public void setMaxProm(String maxProm) {
        this.maxProm = maxProm;
    }

    public ZonedDateTime getFecIniVig() {
        return this.fecIniVig;
    }

    public Promocion fecIniVig(ZonedDateTime fecIniVig) {
        this.setFecIniVig(fecIniVig);
        return this;
    }

    public void setFecIniVig(ZonedDateTime fecIniVig) {
        this.fecIniVig = fecIniVig;
    }

    public ZonedDateTime getFecFinVig() {
        return this.fecFinVig;
    }

    public Promocion fecFinVig(ZonedDateTime fecFinVig) {
        this.setFecFinVig(fecFinVig);
        return this;
    }

    public void setFecFinVig(ZonedDateTime fecFinVig) {
        this.fecFinVig = fecFinVig;
    }

    public String getComentarios() {
        return this.comentarios;
    }

    public Promocion comentarios(String comentarios) {
        this.setComentarios(comentarios);
        return this;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getEstado() {
        return this.estado;
    }

    public Promocion estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Promocion version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public Promocion indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public Promocion fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public Promocion usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public Promocion ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public Promocion fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public Promocion usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public Promocion ipModif(String ipModif) {
        this.setIpModif(ipModif);
        return this;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    public Autorizacion getAutorizacion() {
        return this.autorizacion;
    }

    public void setAutorizacion(Autorizacion autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Promocion autorizacion(Autorizacion autorizacion) {
        this.setAutorizacion(autorizacion);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Promocion)) {
            return false;
        }
        return id != null && id.equals(((Promocion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Promocion{" +
            "id=" + getId() +
            ", tipPromocion='" + getTipPromocion() + "'" +
            ", val1='" + getVal1() + "'" +
            ", val2='" + getVal2() + "'" +
            ", val3='" + getVal3() + "'" +
            ", val4='" + getVal4() + "'" +
            ", val5='" + getVal5() + "'" +
            ", maxProm='" + getMaxProm() + "'" +
            ", fecIniVig='" + getFecIniVig() + "'" +
            ", fecFinVig='" + getFecFinVig() + "'" +
            ", comentarios='" + getComentarios() + "'" +
            ", estado='" + getEstado() + "'" +
            ", version=" + getVersion() +
            ", indDel='" + getIndDel() + "'" +
            ", fecCrea='" + getFecCrea() + "'" +
            ", usuCrea='" + getUsuCrea() + "'" +
            ", ipCrea='" + getIpCrea() + "'" +
            ", fecModif='" + getFecModif() + "'" +
            ", usuModif='" + getUsuModif() + "'" +
            ", ipModif='" + getIpModif() + "'" +
            "}";
    }
}
