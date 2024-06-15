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
 * A Mesas.
 */
@Document(collection = "mesas")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Mesas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("seq_mesa")
    private Integer seqMesa;

    @NotNull
    @Field("nro_mesa")
    private Integer nroMesa;

    @Field("cod_grupo")
    private String codGrupo;

    @Field("categoria")
    private String categoria;

    @Field("capacidad")
    private Integer capacidad;

    @NotNull
    @Field("ind_mesa_junta")
    private Boolean indMesaJunta;

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
    @Field("sede")
    private Sede sede;

    @DBRef
    @Field("empleado")
    private Empleados empleado;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Mesas id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSeqMesa() {
        return this.seqMesa;
    }

    public Mesas seqMesa(Integer seqMesa) {
        this.setSeqMesa(seqMesa);
        return this;
    }

    public void setSeqMesa(Integer seqMesa) {
        this.seqMesa = seqMesa;
    }

    public Integer getNroMesa() {
        return this.nroMesa;
    }

    public Mesas nroMesa(Integer nroMesa) {
        this.setNroMesa(nroMesa);
        return this;
    }

    public void setNroMesa(Integer nroMesa) {
        this.nroMesa = nroMesa;
    }

    public String getCodGrupo() {
        return this.codGrupo;
    }

    public Mesas codGrupo(String codGrupo) {
        this.setCodGrupo(codGrupo);
        return this;
    }

    public void setCodGrupo(String codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public Mesas categoria(String categoria) {
        this.setCategoria(categoria);
        return this;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getCapacidad() {
        return this.capacidad;
    }

    public Mesas capacidad(Integer capacidad) {
        this.setCapacidad(capacidad);
        return this;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Boolean getIndMesaJunta() {
        return this.indMesaJunta;
    }

    public Mesas indMesaJunta(Boolean indMesaJunta) {
        this.setIndMesaJunta(indMesaJunta);
        return this;
    }

    public void setIndMesaJunta(Boolean indMesaJunta) {
        this.indMesaJunta = indMesaJunta;
    }

    public String getEstado() {
        return this.estado;
    }

    public Mesas estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Mesas version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public Mesas indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public Mesas fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public Mesas usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public Mesas ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public Mesas fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public Mesas usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public Mesas ipModif(String ipModif) {
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

    public Mesas orden(Orden orden) {
        this.setOrden(orden);
        return this;
    }

    public Sede getSede() {
        return this.sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Mesas sede(Sede sede) {
        this.setSede(sede);
        return this;
    }

    public Empleados getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleados empleados) {
        this.empleado = empleados;
    }

    public Mesas empleado(Empleados empleados) {
        this.setEmpleado(empleados);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mesas)) {
            return false;
        }
        return id != null && id.equals(((Mesas) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Mesas{" +
            "id=" + getId() +
            ", seqMesa=" + getSeqMesa() +
            ", nroMesa=" + getNroMesa() +
            ", codGrupo='" + getCodGrupo() + "'" +
            ", categoria='" + getCategoria() + "'" +
            ", capacidad=" + getCapacidad() +
            ", indMesaJunta='" + getIndMesaJunta() + "'" +
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
