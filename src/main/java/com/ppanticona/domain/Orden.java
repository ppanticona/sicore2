package com.ppanticona.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Orden.
 */
@Document(collection = "orden")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("num_orden")
    private Integer numOrden;

    @Field("observacion")
    private String observacion;

    @Field("fec_ingreso")
    private ZonedDateTime fecIngreso;

    @Field("fec_salida")
    private ZonedDateTime fecSalida;

    @NotNull
    @Field("cod_canal")
    private String codCanal;

    @NotNull
    @Field("tip_orden")
    private String tipOrden;

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
    @Field("regVenta")
    @JsonIgnoreProperties(value = { "orden" }, allowSetters = true)
    private Set<RegVenta> regVentas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Orden id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumOrden() {
        return this.numOrden;
    }

    public Orden numOrden(Integer numOrden) {
        this.setNumOrden(numOrden);
        return this;
    }

    public void setNumOrden(Integer numOrden) {
        this.numOrden = numOrden;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public Orden observacion(String observacion) {
        this.setObservacion(observacion);
        return this;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public ZonedDateTime getFecIngreso() {
        return this.fecIngreso;
    }

    public Orden fecIngreso(ZonedDateTime fecIngreso) {
        this.setFecIngreso(fecIngreso);
        return this;
    }

    public void setFecIngreso(ZonedDateTime fecIngreso) {
        this.fecIngreso = fecIngreso;
    }

    public ZonedDateTime getFecSalida() {
        return this.fecSalida;
    }

    public Orden fecSalida(ZonedDateTime fecSalida) {
        this.setFecSalida(fecSalida);
        return this;
    }

    public void setFecSalida(ZonedDateTime fecSalida) {
        this.fecSalida = fecSalida;
    }

    public String getCodCanal() {
        return this.codCanal;
    }

    public Orden codCanal(String codCanal) {
        this.setCodCanal(codCanal);
        return this;
    }

    public void setCodCanal(String codCanal) {
        this.codCanal = codCanal;
    }

    public String getTipOrden() {
        return this.tipOrden;
    }

    public Orden tipOrden(String tipOrden) {
        this.setTipOrden(tipOrden);
        return this;
    }

    public void setTipOrden(String tipOrden) {
        this.tipOrden = tipOrden;
    }

    public String getEstado() {
        return this.estado;
    }

    public Orden estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Orden version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public Orden indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public Orden fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public Orden usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public Orden ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public Orden fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public Orden usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public Orden ipModif(String ipModif) {
        this.setIpModif(ipModif);
        return this;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    public Set<RegVenta> getRegVentas() {
        return this.regVentas;
    }

    public void setRegVentas(Set<RegVenta> regVentas) {
        if (this.regVentas != null) {
            this.regVentas.forEach(i -> i.setOrden(null));
        }
        if (regVentas != null) {
            regVentas.forEach(i -> i.setOrden(this));
        }
        this.regVentas = regVentas;
    }

    public Orden regVentas(Set<RegVenta> regVentas) {
        this.setRegVentas(regVentas);
        return this;
    }

    public Orden addRegVenta(RegVenta regVenta) {
        this.regVentas.add(regVenta);
        regVenta.setOrden(this);
        return this;
    }

    public Orden removeRegVenta(RegVenta regVenta) {
        this.regVentas.remove(regVenta);
        regVenta.setOrden(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Orden)) {
            return false;
        }
        return id != null && id.equals(((Orden) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Orden{" +
            "id=" + getId() +
            ", numOrden=" + getNumOrden() +
            ", observacion='" + getObservacion() + "'" +
            ", fecIngreso='" + getFecIngreso() + "'" +
            ", fecSalida='" + getFecSalida() + "'" +
            ", codCanal='" + getCodCanal() + "'" +
            ", tipOrden='" + getTipOrden() + "'" +
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
