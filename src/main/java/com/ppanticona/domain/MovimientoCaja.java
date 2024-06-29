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
 * A MovimientoCaja.
 */
@Document(collection = "movimiento_caja")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MovimientoCaja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tip_movimiento")
    private String tipMovimiento;

    @Field("concepto")
    private String concepto;

    @Field("monto")
    private Double monto;

    @Field("tip_moneda")
    private String tipMoneda;

    @Field("form_pago")
    private String formPago;

    @Field("comprobante")
    private String comprobante;

    @Field("fec_movimiento")
    private ZonedDateTime fecMovimiento;

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
    @Field("asignacionCaja")
    @JsonIgnoreProperties(value = { "user", "caja" }, allowSetters = true)
    private AsignacionCaja asignacionCaja;

    @DBRef
    @Field("autorizacion")
    @JsonIgnoreProperties(value = { "producto" }, allowSetters = true)
    private Autorizacion autorizacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public MovimientoCaja id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipMovimiento() {
        return this.tipMovimiento;
    }

    public MovimientoCaja tipMovimiento(String tipMovimiento) {
        this.setTipMovimiento(tipMovimiento);
        return this;
    }

    public void setTipMovimiento(String tipMovimiento) {
        this.tipMovimiento = tipMovimiento;
    }

    public String getConcepto() {
        return this.concepto;
    }

    public MovimientoCaja concepto(String concepto) {
        this.setConcepto(concepto);
        return this;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getMonto() {
        return this.monto;
    }

    public MovimientoCaja monto(Double monto) {
        this.setMonto(monto);
        return this;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getTipMoneda() {
        return this.tipMoneda;
    }

    public MovimientoCaja tipMoneda(String tipMoneda) {
        this.setTipMoneda(tipMoneda);
        return this;
    }

    public void setTipMoneda(String tipMoneda) {
        this.tipMoneda = tipMoneda;
    }

    public String getFormPago() {
        return this.formPago;
    }

    public MovimientoCaja formPago(String formPago) {
        this.setFormPago(formPago);
        return this;
    }

    public void setFormPago(String formPago) {
        this.formPago = formPago;
    }

    public String getComprobante() {
        return this.comprobante;
    }

    public MovimientoCaja comprobante(String comprobante) {
        this.setComprobante(comprobante);
        return this;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public ZonedDateTime getFecMovimiento() {
        return this.fecMovimiento;
    }

    public MovimientoCaja fecMovimiento(ZonedDateTime fecMovimiento) {
        this.setFecMovimiento(fecMovimiento);
        return this;
    }

    public void setFecMovimiento(ZonedDateTime fecMovimiento) {
        this.fecMovimiento = fecMovimiento;
    }

    public Integer getVersion() {
        return this.version;
    }

    public MovimientoCaja version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public MovimientoCaja indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public MovimientoCaja fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public MovimientoCaja usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public MovimientoCaja ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public MovimientoCaja fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public MovimientoCaja usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public MovimientoCaja ipModif(String ipModif) {
        this.setIpModif(ipModif);
        return this;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    public AsignacionCaja getAsignacionCaja() {
        return this.asignacionCaja;
    }

    public void setAsignacionCaja(AsignacionCaja asignacionCaja) {
        this.asignacionCaja = asignacionCaja;
    }

    public MovimientoCaja asignacionCaja(AsignacionCaja asignacionCaja) {
        this.setAsignacionCaja(asignacionCaja);
        return this;
    }

    public Autorizacion getAutorizacion() {
        return this.autorizacion;
    }

    public void setAutorizacion(Autorizacion autorizacion) {
        this.autorizacion = autorizacion;
    }

    public MovimientoCaja autorizacion(Autorizacion autorizacion) {
        this.setAutorizacion(autorizacion);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovimientoCaja)) {
            return false;
        }
        return id != null && id.equals(((MovimientoCaja) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MovimientoCaja{" +
            "id=" + getId() +
            ", tipMovimiento='" + getTipMovimiento() + "'" +
            ", concepto='" + getConcepto() + "'" +
            ", monto=" + getMonto() +
            ", tipMoneda='" + getTipMoneda() + "'" +
            ", formPago='" + getFormPago() + "'" +
            ", comprobante='" + getComprobante() + "'" +
            ", fecMovimiento='" + getFecMovimiento() + "'" +
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
