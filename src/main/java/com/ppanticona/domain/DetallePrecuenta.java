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
 * A DetallePrecuenta.
 */
@Document(collection = "detalle_precuenta")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DetallePrecuenta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("correlativo")
    private Integer correlativo;

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
    @Field("orden")
    @JsonIgnoreProperties(value = { "regVentas" }, allowSetters = true)
    private Orden orden;

    @DBRef
    @Field("autorizacion")
    @JsonIgnoreProperties(value = { "producto" }, allowSetters = true)
    private Autorizacion autorizacion;

    @DBRef
    @Field("regVenta")
    @JsonIgnoreProperties(value = { "orden" }, allowSetters = true)
    private RegVenta regVenta;

    @DBRef
    @Field("precuenta")
    @JsonIgnoreProperties(value = { "orden" }, allowSetters = true)
    private Precuenta precuenta;

    @DBRef
    @Field("detalleOrden")
    @JsonIgnoreProperties(value = { "orden", "promocion", "autorizacion", "producto" }, allowSetters = true)
    private DetalleOrden detalleOrden;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DetallePrecuenta id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCorrelativo() {
        return this.correlativo;
    }

    public DetallePrecuenta correlativo(Integer correlativo) {
        this.setCorrelativo(correlativo);
        return this;
    }

    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }

    public String getEstado() {
        return this.estado;
    }

    public DetallePrecuenta estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return this.version;
    }

    public DetallePrecuenta version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public DetallePrecuenta indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public DetallePrecuenta fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public DetallePrecuenta usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public DetallePrecuenta ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public DetallePrecuenta fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public DetallePrecuenta usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public DetallePrecuenta ipModif(String ipModif) {
        this.setIpModif(ipModif);
        return this;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    public Orden getOrden() {
        return this.orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public DetallePrecuenta orden(Orden orden) {
        this.setOrden(orden);
        return this;
    }

    public Autorizacion getAutorizacion() {
        return this.autorizacion;
    }

    public void setAutorizacion(Autorizacion autorizacion) {
        this.autorizacion = autorizacion;
    }

    public DetallePrecuenta autorizacion(Autorizacion autorizacion) {
        this.setAutorizacion(autorizacion);
        return this;
    }

    public RegVenta getRegVenta() {
        return this.regVenta;
    }

    public void setRegVenta(RegVenta regVenta) {
        this.regVenta = regVenta;
    }

    public DetallePrecuenta regVenta(RegVenta regVenta) {
        this.setRegVenta(regVenta);
        return this;
    }

    public Precuenta getPrecuenta() {
        return this.precuenta;
    }

    public void setPrecuenta(Precuenta precuenta) {
        this.precuenta = precuenta;
    }

    public DetallePrecuenta precuenta(Precuenta precuenta) {
        this.setPrecuenta(precuenta);
        return this;
    }

    public DetalleOrden getDetalleOrden() {
        return this.detalleOrden;
    }

    public void setDetalleOrden(DetalleOrden detalleOrden) {
        this.detalleOrden = detalleOrden;
    }

    public DetallePrecuenta detalleOrden(DetalleOrden detalleOrden) {
        this.setDetalleOrden(detalleOrden);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetallePrecuenta)) {
            return false;
        }
        return id != null && id.equals(((DetallePrecuenta) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetallePrecuenta{" +
            "id=" + getId() +
            ", correlativo=" + getCorrelativo() +
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
