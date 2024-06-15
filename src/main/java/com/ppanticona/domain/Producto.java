package com.ppanticona.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Producto.
 */
@Document(collection = "producto")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("cod_producto")
    private String codProducto;

    @Field("tip_producto")
    private String tipProducto;

    @Field("cod_qr")
    private String codQr;

    @Field("cod_barra")
    private String codBarra;

    @NotNull
    @Field("descripcion")
    private String descripcion;

    @Field("detalle_desc")
    private String detalleDesc;

    @Field("valor")
    private Double valor;

    @Field("categoria")
    private String categoria;

    @Field("sub_categoria")
    private String subCategoria;

    @Field("categoria_menu")
    private String categoriaMenu;

    @Field("url_image")
    private String urlImage;

    @Field("fec_ini_vig")
    private ZonedDateTime fecIniVig;

    @Field("fec_fin_vig")
    private ZonedDateTime fecFinVig;

    @Field("costo_prod")
    private Double costoProd;

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

    public Producto id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodProducto() {
        return this.codProducto;
    }

    public Producto codProducto(String codProducto) {
        this.setCodProducto(codProducto);
        return this;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getTipProducto() {
        return this.tipProducto;
    }

    public Producto tipProducto(String tipProducto) {
        this.setTipProducto(tipProducto);
        return this;
    }

    public void setTipProducto(String tipProducto) {
        this.tipProducto = tipProducto;
    }

    public String getCodQr() {
        return this.codQr;
    }

    public Producto codQr(String codQr) {
        this.setCodQr(codQr);
        return this;
    }

    public void setCodQr(String codQr) {
        this.codQr = codQr;
    }

    public String getCodBarra() {
        return this.codBarra;
    }

    public Producto codBarra(String codBarra) {
        this.setCodBarra(codBarra);
        return this;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Producto descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalleDesc() {
        return this.detalleDesc;
    }

    public Producto detalleDesc(String detalleDesc) {
        this.setDetalleDesc(detalleDesc);
        return this;
    }

    public void setDetalleDesc(String detalleDesc) {
        this.detalleDesc = detalleDesc;
    }

    public Double getValor() {
        return this.valor;
    }

    public Producto valor(Double valor) {
        this.setValor(valor);
        return this;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public Producto categoria(String categoria) {
        this.setCategoria(categoria);
        return this;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubCategoria() {
        return this.subCategoria;
    }

    public Producto subCategoria(String subCategoria) {
        this.setSubCategoria(subCategoria);
        return this;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public String getCategoriaMenu() {
        return this.categoriaMenu;
    }

    public Producto categoriaMenu(String categoriaMenu) {
        this.setCategoriaMenu(categoriaMenu);
        return this;
    }

    public void setCategoriaMenu(String categoriaMenu) {
        this.categoriaMenu = categoriaMenu;
    }

    public String getUrlImage() {
        return this.urlImage;
    }

    public Producto urlImage(String urlImage) {
        this.setUrlImage(urlImage);
        return this;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public ZonedDateTime getFecIniVig() {
        return this.fecIniVig;
    }

    public Producto fecIniVig(ZonedDateTime fecIniVig) {
        this.setFecIniVig(fecIniVig);
        return this;
    }

    public void setFecIniVig(ZonedDateTime fecIniVig) {
        this.fecIniVig = fecIniVig;
    }

    public ZonedDateTime getFecFinVig() {
        return this.fecFinVig;
    }

    public Producto fecFinVig(ZonedDateTime fecFinVig) {
        this.setFecFinVig(fecFinVig);
        return this;
    }

    public void setFecFinVig(ZonedDateTime fecFinVig) {
        this.fecFinVig = fecFinVig;
    }

    public Double getCostoProd() {
        return this.costoProd;
    }

    public Producto costoProd(Double costoProd) {
        this.setCostoProd(costoProd);
        return this;
    }

    public void setCostoProd(Double costoProd) {
        this.costoProd = costoProd;
    }

    public String getEstado() {
        return this.estado;
    }

    public Producto estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Producto version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public Producto indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public Producto fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public Producto usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public Producto ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public Producto fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public Producto usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public Producto ipModif(String ipModif) {
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
        if (!(o instanceof Producto)) {
            return false;
        }
        return id != null && id.equals(((Producto) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Producto{" +
            "id=" + getId() +
            ", codProducto='" + getCodProducto() + "'" +
            ", tipProducto='" + getTipProducto() + "'" +
            ", codQr='" + getCodQr() + "'" +
            ", codBarra='" + getCodBarra() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", detalleDesc='" + getDetalleDesc() + "'" +
            ", valor=" + getValor() +
            ", categoria='" + getCategoria() + "'" +
            ", subCategoria='" + getSubCategoria() + "'" +
            ", categoriaMenu='" + getCategoriaMenu() + "'" +
            ", urlImage='" + getUrlImage() + "'" +
            ", fecIniVig='" + getFecIniVig() + "'" +
            ", fecFinVig='" + getFecFinVig() + "'" +
            ", costoProd=" + getCostoProd() +
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
