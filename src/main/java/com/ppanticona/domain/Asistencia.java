package com.ppanticona.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Asistencia.
 */
@Document(collection = "asistencia")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("tip_asistente")
    private String tipAsistente;

    @Field("resultado")
    private String resultado;

    @Field("ano_asistencia")
    private Integer anoAsistencia;

    @Field("mes_asistencia")
    private Integer mesAsistencia;

    @Field("dia_asistencia")
    private Integer diaAsistencia;

    @Field("observacion")
    private String observacion;

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
    @Field("userId")
    private Empleados userId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Asistencia id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipAsistente() {
        return this.tipAsistente;
    }

    public Asistencia tipAsistente(String tipAsistente) {
        this.setTipAsistente(tipAsistente);
        return this;
    }

    public void setTipAsistente(String tipAsistente) {
        this.tipAsistente = tipAsistente;
    }

    public String getResultado() {
        return this.resultado;
    }

    public Asistencia resultado(String resultado) {
        this.setResultado(resultado);
        return this;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Integer getAnoAsistencia() {
        return this.anoAsistencia;
    }

    public Asistencia anoAsistencia(Integer anoAsistencia) {
        this.setAnoAsistencia(anoAsistencia);
        return this;
    }

    public void setAnoAsistencia(Integer anoAsistencia) {
        this.anoAsistencia = anoAsistencia;
    }

    public Integer getMesAsistencia() {
        return this.mesAsistencia;
    }

    public Asistencia mesAsistencia(Integer mesAsistencia) {
        this.setMesAsistencia(mesAsistencia);
        return this;
    }

    public void setMesAsistencia(Integer mesAsistencia) {
        this.mesAsistencia = mesAsistencia;
    }

    public Integer getDiaAsistencia() {
        return this.diaAsistencia;
    }

    public Asistencia diaAsistencia(Integer diaAsistencia) {
        this.setDiaAsistencia(diaAsistencia);
        return this;
    }

    public void setDiaAsistencia(Integer diaAsistencia) {
        this.diaAsistencia = diaAsistencia;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public Asistencia observacion(String observacion) {
        this.setObservacion(observacion);
        return this;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getComentarios() {
        return this.comentarios;
    }

    public Asistencia comentarios(String comentarios) {
        this.setComentarios(comentarios);
        return this;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getEstado() {
        return this.estado;
    }

    public Asistencia estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Asistencia version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public Asistencia indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public Asistencia fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public Asistencia usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public Asistencia ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public Asistencia fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public Asistencia usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public Asistencia ipModif(String ipModif) {
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

    public Asistencia userId(Empleados empleados) {
        this.setUserId(empleados);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Asistencia)) {
            return false;
        }
        return id != null && id.equals(((Asistencia) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Asistencia{" +
            "id=" + getId() +
            ", tipAsistente='" + getTipAsistente() + "'" +
            ", resultado='" + getResultado() + "'" +
            ", anoAsistencia=" + getAnoAsistencia() +
            ", mesAsistencia=" + getMesAsistencia() +
            ", diaAsistencia=" + getDiaAsistencia() +
            ", observacion='" + getObservacion() + "'" +
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
