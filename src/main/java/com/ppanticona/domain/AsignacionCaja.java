package com.ppanticona.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A AsignacionCaja.
 */
@Document(collection = "asignacion_caja")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AsignacionCaja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("cod_asignacion")
    private String codAsignacion;

    @Field("mnto_inicial_soles")
    private Double mntoInicialSoles;

    @Field("mnto_final_soles")
    private Double mntoFinalSoles;

    @Field("monto_maximo_soles")
    private Double montoMaximoSoles;

    @Field("diferencia_soles")
    private Double diferenciaSoles;

    @Field("mnto_inicial_dolares")
    private Double mntoInicialDolares;

    @Field("mnto_final_dolares")
    private Double mntoFinalDolares;

    @Field("monto_maximo_dolares")
    private Double montoMaximoDolares;

    @Field("diferencia_dolares")
    private Double diferenciaDolares;

    @Field("observacion_apertura")
    private String observacionApertura;

    @Field("observacion_cierre")
    private String observacionCierre;

    @Field("fec_asignacion")
    private ZonedDateTime fecAsignacion;

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
    @Field("userId")
    private Empleados userId;

    @DBRef
    @Field("caja")
    private Caja caja;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public AsignacionCaja id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodAsignacion() {
        return this.codAsignacion;
    }

    public AsignacionCaja codAsignacion(String codAsignacion) {
        this.setCodAsignacion(codAsignacion);
        return this;
    }

    public void setCodAsignacion(String codAsignacion) {
        this.codAsignacion = codAsignacion;
    }

    public Double getMntoInicialSoles() {
        return this.mntoInicialSoles;
    }

    public AsignacionCaja mntoInicialSoles(Double mntoInicialSoles) {
        this.setMntoInicialSoles(mntoInicialSoles);
        return this;
    }

    public void setMntoInicialSoles(Double mntoInicialSoles) {
        this.mntoInicialSoles = mntoInicialSoles;
    }

    public Double getMntoFinalSoles() {
        return this.mntoFinalSoles;
    }

    public AsignacionCaja mntoFinalSoles(Double mntoFinalSoles) {
        this.setMntoFinalSoles(mntoFinalSoles);
        return this;
    }

    public void setMntoFinalSoles(Double mntoFinalSoles) {
        this.mntoFinalSoles = mntoFinalSoles;
    }

    public Double getMontoMaximoSoles() {
        return this.montoMaximoSoles;
    }

    public AsignacionCaja montoMaximoSoles(Double montoMaximoSoles) {
        this.setMontoMaximoSoles(montoMaximoSoles);
        return this;
    }

    public void setMontoMaximoSoles(Double montoMaximoSoles) {
        this.montoMaximoSoles = montoMaximoSoles;
    }

    public Double getDiferenciaSoles() {
        return this.diferenciaSoles;
    }

    public AsignacionCaja diferenciaSoles(Double diferenciaSoles) {
        this.setDiferenciaSoles(diferenciaSoles);
        return this;
    }

    public void setDiferenciaSoles(Double diferenciaSoles) {
        this.diferenciaSoles = diferenciaSoles;
    }

    public Double getMntoInicialDolares() {
        return this.mntoInicialDolares;
    }

    public AsignacionCaja mntoInicialDolares(Double mntoInicialDolares) {
        this.setMntoInicialDolares(mntoInicialDolares);
        return this;
    }

    public void setMntoInicialDolares(Double mntoInicialDolares) {
        this.mntoInicialDolares = mntoInicialDolares;
    }

    public Double getMntoFinalDolares() {
        return this.mntoFinalDolares;
    }

    public AsignacionCaja mntoFinalDolares(Double mntoFinalDolares) {
        this.setMntoFinalDolares(mntoFinalDolares);
        return this;
    }

    public void setMntoFinalDolares(Double mntoFinalDolares) {
        this.mntoFinalDolares = mntoFinalDolares;
    }

    public Double getMontoMaximoDolares() {
        return this.montoMaximoDolares;
    }

    public AsignacionCaja montoMaximoDolares(Double montoMaximoDolares) {
        this.setMontoMaximoDolares(montoMaximoDolares);
        return this;
    }

    public void setMontoMaximoDolares(Double montoMaximoDolares) {
        this.montoMaximoDolares = montoMaximoDolares;
    }

    public Double getDiferenciaDolares() {
        return this.diferenciaDolares;
    }

    public AsignacionCaja diferenciaDolares(Double diferenciaDolares) {
        this.setDiferenciaDolares(diferenciaDolares);
        return this;
    }

    public void setDiferenciaDolares(Double diferenciaDolares) {
        this.diferenciaDolares = diferenciaDolares;
    }

    public String getObservacionApertura() {
        return this.observacionApertura;
    }

    public AsignacionCaja observacionApertura(String observacionApertura) {
        this.setObservacionApertura(observacionApertura);
        return this;
    }

    public void setObservacionApertura(String observacionApertura) {
        this.observacionApertura = observacionApertura;
    }

    public String getObservacionCierre() {
        return this.observacionCierre;
    }

    public AsignacionCaja observacionCierre(String observacionCierre) {
        this.setObservacionCierre(observacionCierre);
        return this;
    }

    public void setObservacionCierre(String observacionCierre) {
        this.observacionCierre = observacionCierre;
    }

    public ZonedDateTime getFecAsignacion() {
        return this.fecAsignacion;
    }

    public AsignacionCaja fecAsignacion(ZonedDateTime fecAsignacion) {
        this.setFecAsignacion(fecAsignacion);
        return this;
    }

    public void setFecAsignacion(ZonedDateTime fecAsignacion) {
        this.fecAsignacion = fecAsignacion;
    }

    public String getEstado() {
        return this.estado;
    }

    public AsignacionCaja estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return this.version;
    }

    public AsignacionCaja version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public AsignacionCaja indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public AsignacionCaja fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public AsignacionCaja usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public AsignacionCaja ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public AsignacionCaja fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public AsignacionCaja usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public AsignacionCaja ipModif(String ipModif) {
        this.setIpModif(ipModif);
        return this;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    public Empleados getUserId() {
        return this.userId;
    }

    public void setUserId(Empleados empleados) {
        this.userId = empleados;
    }

    public AsignacionCaja userId(Empleados empleados) {
        this.setUserId(empleados);
        return this;
    }

    public Caja getCaja() {
        return this.caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public AsignacionCaja caja(Caja caja) {
        this.setCaja(caja);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AsignacionCaja)) {
            return false;
        }
        return id != null && id.equals(((AsignacionCaja) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AsignacionCaja{" +
            "id=" + getId() +
            ", codAsignacion='" + getCodAsignacion() + "'" +
            ", mntoInicialSoles=" + getMntoInicialSoles() +
            ", mntoFinalSoles=" + getMntoFinalSoles() +
            ", montoMaximoSoles=" + getMontoMaximoSoles() +
            ", diferenciaSoles=" + getDiferenciaSoles() +
            ", mntoInicialDolares=" + getMntoInicialDolares() +
            ", mntoFinalDolares=" + getMntoFinalDolares() +
            ", montoMaximoDolares=" + getMontoMaximoDolares() +
            ", diferenciaDolares=" + getDiferenciaDolares() +
            ", observacionApertura='" + getObservacionApertura() + "'" +
            ", observacionCierre='" + getObservacionCierre() + "'" +
            ", fecAsignacion='" + getFecAsignacion() + "'" +
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
