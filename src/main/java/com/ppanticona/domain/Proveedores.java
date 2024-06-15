package com.ppanticona.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Proveedores.
 */
@Document(collection = "proveedores")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Proveedores implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("tip_doc")
    private String tipDoc;

    @NotNull
    @Field("nro_doc")
    private String nroDoc;

    @NotNull
    @Field("nombres")
    private String nombres;

    @NotNull
    @Field("apellidos")
    private String apellidos;

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
    @Field("fec_nac")
    private ZonedDateTime fecNac;

    @Field("user_id")
    private String userId;

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

    public Proveedores id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipDoc() {
        return this.tipDoc;
    }

    public Proveedores tipDoc(String tipDoc) {
        this.setTipDoc(tipDoc);
        return this;
    }

    public void setTipDoc(String tipDoc) {
        this.tipDoc = tipDoc;
    }

    public String getNroDoc() {
        return this.nroDoc;
    }

    public Proveedores nroDoc(String nroDoc) {
        this.setNroDoc(nroDoc);
        return this;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getNombres() {
        return this.nombres;
    }

    public Proveedores nombres(String nombres) {
        this.setNombres(nombres);
        return this;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public Proveedores apellidos(String apellidos) {
        this.setApellidos(apellidos);
        return this;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public Proveedores categoria(String categoria) {
        this.setCategoria(categoria);
        return this;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTel1() {
        return this.tel1;
    }

    public Proveedores tel1(String tel1) {
        this.setTel1(tel1);
        return this;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return this.tel2;
    }

    public Proveedores tel2(String tel2) {
        this.setTel2(tel2);
        return this;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getCorreo() {
        return this.correo;
    }

    public Proveedores correo(String correo) {
        this.setCorreo(correo);
        return this;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public Proveedores direccion(String direccion) {
        this.setDireccion(direccion);
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRefDirecc() {
        return this.refDirecc;
    }

    public Proveedores refDirecc(String refDirecc) {
        this.setRefDirecc(refDirecc);
        return this;
    }

    public void setRefDirecc(String refDirecc) {
        this.refDirecc = refDirecc;
    }

    public String getDistrito() {
        return this.distrito;
    }

    public Proveedores distrito(String distrito) {
        this.setDistrito(distrito);
        return this;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public ZonedDateTime getFecNac() {
        return this.fecNac;
    }

    public Proveedores fecNac(ZonedDateTime fecNac) {
        this.setFecNac(fecNac);
        return this;
    }

    public void setFecNac(ZonedDateTime fecNac) {
        this.fecNac = fecNac;
    }

    public String getUserId() {
        return this.userId;
    }

    public Proveedores userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEstado() {
        return this.estado;
    }

    public Proveedores estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Proveedores version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public Proveedores indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public Proveedores fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public Proveedores usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public Proveedores ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public Proveedores fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public Proveedores usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public Proveedores ipModif(String ipModif) {
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
        if (!(o instanceof Proveedores)) {
            return false;
        }
        return id != null && id.equals(((Proveedores) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Proveedores{" +
            "id=" + getId() +
            ", tipDoc='" + getTipDoc() + "'" +
            ", nroDoc='" + getNroDoc() + "'" +
            ", nombres='" + getNombres() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", categoria='" + getCategoria() + "'" +
            ", tel1='" + getTel1() + "'" +
            ", tel2='" + getTel2() + "'" +
            ", correo='" + getCorreo() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", refDirecc='" + getRefDirecc() + "'" +
            ", distrito='" + getDistrito() + "'" +
            ", fecNac='" + getFecNac() + "'" +
            ", userId='" + getUserId() + "'" +
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
