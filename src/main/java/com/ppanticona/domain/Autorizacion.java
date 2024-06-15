package com.ppanticona.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Autorizacion.
 */
@Document(collection = "autorizacion")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Autorizacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("tip_autorizacion")
    private String tipAutorizacion;

    @NotNull
    @Field("sub_tip_autorizacion")
    private String subTipAutorizacion;

    @Field("concepto")
    private String concepto;

    @Field("comentario")
    private String comentario;

    @Field("monto")
    private Double monto;

    @Field("moneda")
    private String moneda;

    @NotNull
    @Field("token")
    private String token;

    @NotNull
    @Field("fec_ini_vig")
    private ZonedDateTime fecIniVig;

    @NotNull
    @Field("fec_fin_vig")
    private ZonedDateTime fecFinVig;

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
    @Field("producto")
    private Producto producto;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Autorizacion id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipAutorizacion() {
        return this.tipAutorizacion;
    }

    public Autorizacion tipAutorizacion(String tipAutorizacion) {
        this.setTipAutorizacion(tipAutorizacion);
        return this;
    }

    public void setTipAutorizacion(String tipAutorizacion) {
        this.tipAutorizacion = tipAutorizacion;
    }

    public String getSubTipAutorizacion() {
        return this.subTipAutorizacion;
    }

    public Autorizacion subTipAutorizacion(String subTipAutorizacion) {
        this.setSubTipAutorizacion(subTipAutorizacion);
        return this;
    }

    public void setSubTipAutorizacion(String subTipAutorizacion) {
        this.subTipAutorizacion = subTipAutorizacion;
    }

    public String getConcepto() {
        return this.concepto;
    }

    public Autorizacion concepto(String concepto) {
        this.setConcepto(concepto);
        return this;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getComentario() {
        return this.comentario;
    }

    public Autorizacion comentario(String comentario) {
        this.setComentario(comentario);
        return this;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Double getMonto() {
        return this.monto;
    }

    public Autorizacion monto(Double monto) {
        this.setMonto(monto);
        return this;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return this.moneda;
    }

    public Autorizacion moneda(String moneda) {
        this.setMoneda(moneda);
        return this;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getToken() {
        return this.token;
    }

    public Autorizacion token(String token) {
        this.setToken(token);
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ZonedDateTime getFecIniVig() {
        return this.fecIniVig;
    }

    public Autorizacion fecIniVig(ZonedDateTime fecIniVig) {
        this.setFecIniVig(fecIniVig);
        return this;
    }

    public void setFecIniVig(ZonedDateTime fecIniVig) {
        this.fecIniVig = fecIniVig;
    }

    public ZonedDateTime getFecFinVig() {
        return this.fecFinVig;
    }

    public Autorizacion fecFinVig(ZonedDateTime fecFinVig) {
        this.setFecFinVig(fecFinVig);
        return this;
    }

    public void setFecFinVig(ZonedDateTime fecFinVig) {
        this.fecFinVig = fecFinVig;
    }

    public String getEstado() {
        return this.estado;
    }

    public Autorizacion estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Autorizacion version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public Autorizacion indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public Autorizacion fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public Autorizacion usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public Autorizacion ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public Autorizacion fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public Autorizacion usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public Autorizacion ipModif(String ipModif) {
        this.setIpModif(ipModif);
        return this;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Autorizacion producto(Producto producto) {
        this.setProducto(producto);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autorizacion)) {
            return false;
        }
        return id != null && id.equals(((Autorizacion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autorizacion{" +
            "id=" + getId() +
            ", tipAutorizacion='" + getTipAutorizacion() + "'" +
            ", subTipAutorizacion='" + getSubTipAutorizacion() + "'" +
            ", concepto='" + getConcepto() + "'" +
            ", comentario='" + getComentario() + "'" +
            ", monto=" + getMonto() +
            ", moneda='" + getMoneda() + "'" +
            ", token='" + getToken() + "'" +
            ", fecIniVig='" + getFecIniVig() + "'" +
            ", fecFinVig='" + getFecFinVig() + "'" +
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
