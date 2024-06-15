package com.ppanticona.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Sede.
 */
@Document(collection = "sede")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Sede implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("cod_sede")
    private Integer codSede;

    @NotNull
    @Field("descripcion")
    private Integer descripcion;

    @Field("categoria")
    private String categoria;

    @Field("tel_1")
    private String tel1;

    @Field("tel_2")
    private String tel2;

    @Field("correo")
    private String correo;

    @Field("direccion")
    private String direccion;

    @Field("ref_direcc")
    private String refDirecc;

    @Field("distrito")
    private String distrito;

    @NotNull
    @Field("fec_aper")
    private ZonedDateTime fecAper;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Sede id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCodSede() {
        return this.codSede;
    }

    public Sede codSede(Integer codSede) {
        this.setCodSede(codSede);
        return this;
    }

    public void setCodSede(Integer codSede) {
        this.codSede = codSede;
    }

    public Integer getDescripcion() {
        return this.descripcion;
    }

    public Sede descripcion(Integer descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(Integer descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public Sede categoria(String categoria) {
        this.setCategoria(categoria);
        return this;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTel1() {
        return this.tel1;
    }

    public Sede tel1(String tel1) {
        this.setTel1(tel1);
        return this;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return this.tel2;
    }

    public Sede tel2(String tel2) {
        this.setTel2(tel2);
        return this;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getCorreo() {
        return this.correo;
    }

    public Sede correo(String correo) {
        this.setCorreo(correo);
        return this;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public Sede direccion(String direccion) {
        this.setDireccion(direccion);
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRefDirecc() {
        return this.refDirecc;
    }

    public Sede refDirecc(String refDirecc) {
        this.setRefDirecc(refDirecc);
        return this;
    }

    public void setRefDirecc(String refDirecc) {
        this.refDirecc = refDirecc;
    }

    public String getDistrito() {
        return this.distrito;
    }

    public Sede distrito(String distrito) {
        this.setDistrito(distrito);
        return this;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public ZonedDateTime getFecAper() {
        return this.fecAper;
    }

    public Sede fecAper(ZonedDateTime fecAper) {
        this.setFecAper(fecAper);
        return this;
    }

    public void setFecAper(ZonedDateTime fecAper) {
        this.fecAper = fecAper;
    }

    public String getEstado() {
        return this.estado;
    }

    public Sede estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Sede version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public Sede indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public Sede fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public Sede usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public Sede ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public Sede fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public Sede usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public Sede ipModif(String ipModif) {
        this.setIpModif(ipModif);
        return this;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sede)) {
            return false;
        }
        return id != null && id.equals(((Sede) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sede{" +
            "id=" + getId() +
            ", codSede=" + getCodSede() +
            ", descripcion=" + getDescripcion() +
            ", categoria='" + getCategoria() + "'" +
            ", tel1='" + getTel1() + "'" +
            ", tel2='" + getTel2() + "'" +
            ", correo='" + getCorreo() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", refDirecc='" + getRefDirecc() + "'" +
            ", distrito='" + getDistrito() + "'" +
            ", fecAper='" + getFecAper() + "'" +
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
