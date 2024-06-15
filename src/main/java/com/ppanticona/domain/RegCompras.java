package com.ppanticona.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A RegCompras.
 */
@Document(collection = "reg_compras")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RegCompras implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("periodo")
    private String periodo;

    @NotNull
    @Field("cuo")
    private String cuo;

    @Field("asient_contab")
    private String asientContab;

    @NotNull
    @Field("fec_emi_comp")
    private ZonedDateTime fecEmiComp;

    @Field("fec_venc_comp")
    private ZonedDateTime fecVencComp;

    @NotNull
    @Field("tip_comp")
    private String tipComp;

    @NotNull
    @Field("nro_serie_comp")
    private String nroSerieComp;

    @Field("anho_emision_dua")
    private String anhoEmisionDua;

    @NotNull
    @Field("nro_comp")
    private String nroComp;

    @Field("nro_final")
    private String nroFinal;

    @NotNull
    @Field("tip_doc_prov")
    private String tipDocProv;

    @NotNull
    @Field("nro_doc_prov")
    private String nroDocProv;

    @NotNull
    @Field("nom_ape_raz_soc_prov")
    private String nomApeRazSocProv;

    @NotNull
    @Field("base_imponible")
    private Double baseImponible;

    @NotNull
    @Field("monto_igv")
    private Double montoIgv;

    @NotNull
    @Field("base_imponible_2")
    private Double baseImponible2;

    @NotNull
    @Field("monto_igv_2")
    private Double montoIgv2;

    @Field("base_imponible_3")
    private Double baseImponible3;

    @NotNull
    @Field("monto_igv_3")
    private Double montoIgv3;

    @Field("monto_no_gravado")
    private Double montoNoGravado;

    @Field("monto_isc")
    private Double montoIsc;

    @Field("imp_cons_bols")
    private Double impConsBols;

    @Field("otros_cargos")
    private Double otrosCargos;

    @Field("importe_total")
    private Double importeTotal;

    @Field("cod_moneda")
    private String codMoneda;

    @Field("tip_cambio")
    private Double tipCambio;

    @Field("fec_emi_modif")
    private ZonedDateTime fecEmiModif;

    @Field("tip_comp_modif")
    private String tipCompModif;

    @Field("nro_serie_comp_modif")
    private String nroSerieCompModif;

    @Field("cod_dua_ref")
    private String codDuaRef;

    @Field("nro_comp_modif")
    private String nroCompModif;

    @Field("fec_emi_detracc")
    private ZonedDateTime fecEmiDetracc;

    @Field("nro_const_detracc")
    private String nroConstDetracc;

    @Field("ind_comp_suj_retenc")
    private String indCompSujRetenc;

    @Field("clas_bienesy_servicios")
    private String clasBienesyServicios;

    @Field("ident_contrato")
    private String identContrato;

    @Field("err_tip_uno")
    private String errTipUno;

    @Field("err_tip_dos")
    private String errTipDos;

    @Field("err_tip_tres")
    private String errTipTres;

    @Field("err_tip_cuatro")
    private String errTipCuatro;

    @Field("ind_comp_pago_med_pago")
    private String indCompPagoMedPago;

    @Field("estado")
    private Integer estado;

    @Field("campo_libre")
    private String campoLibre;

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

    public RegCompras id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeriodo() {
        return this.periodo;
    }

    public RegCompras periodo(String periodo) {
        this.setPeriodo(periodo);
        return this;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getCuo() {
        return this.cuo;
    }

    public RegCompras cuo(String cuo) {
        this.setCuo(cuo);
        return this;
    }

    public void setCuo(String cuo) {
        this.cuo = cuo;
    }

    public String getAsientContab() {
        return this.asientContab;
    }

    public RegCompras asientContab(String asientContab) {
        this.setAsientContab(asientContab);
        return this;
    }

    public void setAsientContab(String asientContab) {
        this.asientContab = asientContab;
    }

    public ZonedDateTime getFecEmiComp() {
        return this.fecEmiComp;
    }

    public RegCompras fecEmiComp(ZonedDateTime fecEmiComp) {
        this.setFecEmiComp(fecEmiComp);
        return this;
    }

    public void setFecEmiComp(ZonedDateTime fecEmiComp) {
        this.fecEmiComp = fecEmiComp;
    }

    public ZonedDateTime getFecVencComp() {
        return this.fecVencComp;
    }

    public RegCompras fecVencComp(ZonedDateTime fecVencComp) {
        this.setFecVencComp(fecVencComp);
        return this;
    }

    public void setFecVencComp(ZonedDateTime fecVencComp) {
        this.fecVencComp = fecVencComp;
    }

    public String getTipComp() {
        return this.tipComp;
    }

    public RegCompras tipComp(String tipComp) {
        this.setTipComp(tipComp);
        return this;
    }

    public void setTipComp(String tipComp) {
        this.tipComp = tipComp;
    }

    public String getNroSerieComp() {
        return this.nroSerieComp;
    }

    public RegCompras nroSerieComp(String nroSerieComp) {
        this.setNroSerieComp(nroSerieComp);
        return this;
    }

    public void setNroSerieComp(String nroSerieComp) {
        this.nroSerieComp = nroSerieComp;
    }

    public String getAnhoEmisionDua() {
        return this.anhoEmisionDua;
    }

    public RegCompras anhoEmisionDua(String anhoEmisionDua) {
        this.setAnhoEmisionDua(anhoEmisionDua);
        return this;
    }

    public void setAnhoEmisionDua(String anhoEmisionDua) {
        this.anhoEmisionDua = anhoEmisionDua;
    }

    public String getNroComp() {
        return this.nroComp;
    }

    public RegCompras nroComp(String nroComp) {
        this.setNroComp(nroComp);
        return this;
    }

    public void setNroComp(String nroComp) {
        this.nroComp = nroComp;
    }

    public String getNroFinal() {
        return this.nroFinal;
    }

    public RegCompras nroFinal(String nroFinal) {
        this.setNroFinal(nroFinal);
        return this;
    }

    public void setNroFinal(String nroFinal) {
        this.nroFinal = nroFinal;
    }

    public String getTipDocProv() {
        return this.tipDocProv;
    }

    public RegCompras tipDocProv(String tipDocProv) {
        this.setTipDocProv(tipDocProv);
        return this;
    }

    public void setTipDocProv(String tipDocProv) {
        this.tipDocProv = tipDocProv;
    }

    public String getNroDocProv() {
        return this.nroDocProv;
    }

    public RegCompras nroDocProv(String nroDocProv) {
        this.setNroDocProv(nroDocProv);
        return this;
    }

    public void setNroDocProv(String nroDocProv) {
        this.nroDocProv = nroDocProv;
    }

    public String getNomApeRazSocProv() {
        return this.nomApeRazSocProv;
    }

    public RegCompras nomApeRazSocProv(String nomApeRazSocProv) {
        this.setNomApeRazSocProv(nomApeRazSocProv);
        return this;
    }

    public void setNomApeRazSocProv(String nomApeRazSocProv) {
        this.nomApeRazSocProv = nomApeRazSocProv;
    }

    public Double getBaseImponible() {
        return this.baseImponible;
    }

    public RegCompras baseImponible(Double baseImponible) {
        this.setBaseImponible(baseImponible);
        return this;
    }

    public void setBaseImponible(Double baseImponible) {
        this.baseImponible = baseImponible;
    }

    public Double getMontoIgv() {
        return this.montoIgv;
    }

    public RegCompras montoIgv(Double montoIgv) {
        this.setMontoIgv(montoIgv);
        return this;
    }

    public void setMontoIgv(Double montoIgv) {
        this.montoIgv = montoIgv;
    }

    public Double getBaseImponible2() {
        return this.baseImponible2;
    }

    public RegCompras baseImponible2(Double baseImponible2) {
        this.setBaseImponible2(baseImponible2);
        return this;
    }

    public void setBaseImponible2(Double baseImponible2) {
        this.baseImponible2 = baseImponible2;
    }

    public Double getMontoIgv2() {
        return this.montoIgv2;
    }

    public RegCompras montoIgv2(Double montoIgv2) {
        this.setMontoIgv2(montoIgv2);
        return this;
    }

    public void setMontoIgv2(Double montoIgv2) {
        this.montoIgv2 = montoIgv2;
    }

    public Double getBaseImponible3() {
        return this.baseImponible3;
    }

    public RegCompras baseImponible3(Double baseImponible3) {
        this.setBaseImponible3(baseImponible3);
        return this;
    }

    public void setBaseImponible3(Double baseImponible3) {
        this.baseImponible3 = baseImponible3;
    }

    public Double getMontoIgv3() {
        return this.montoIgv3;
    }

    public RegCompras montoIgv3(Double montoIgv3) {
        this.setMontoIgv3(montoIgv3);
        return this;
    }

    public void setMontoIgv3(Double montoIgv3) {
        this.montoIgv3 = montoIgv3;
    }

    public Double getMontoNoGravado() {
        return this.montoNoGravado;
    }

    public RegCompras montoNoGravado(Double montoNoGravado) {
        this.setMontoNoGravado(montoNoGravado);
        return this;
    }

    public void setMontoNoGravado(Double montoNoGravado) {
        this.montoNoGravado = montoNoGravado;
    }

    public Double getMontoIsc() {
        return this.montoIsc;
    }

    public RegCompras montoIsc(Double montoIsc) {
        this.setMontoIsc(montoIsc);
        return this;
    }

    public void setMontoIsc(Double montoIsc) {
        this.montoIsc = montoIsc;
    }

    public Double getImpConsBols() {
        return this.impConsBols;
    }

    public RegCompras impConsBols(Double impConsBols) {
        this.setImpConsBols(impConsBols);
        return this;
    }

    public void setImpConsBols(Double impConsBols) {
        this.impConsBols = impConsBols;
    }

    public Double getOtrosCargos() {
        return this.otrosCargos;
    }

    public RegCompras otrosCargos(Double otrosCargos) {
        this.setOtrosCargos(otrosCargos);
        return this;
    }

    public void setOtrosCargos(Double otrosCargos) {
        this.otrosCargos = otrosCargos;
    }

    public Double getImporteTotal() {
        return this.importeTotal;
    }

    public RegCompras importeTotal(Double importeTotal) {
        this.setImporteTotal(importeTotal);
        return this;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getCodMoneda() {
        return this.codMoneda;
    }

    public RegCompras codMoneda(String codMoneda) {
        this.setCodMoneda(codMoneda);
        return this;
    }

    public void setCodMoneda(String codMoneda) {
        this.codMoneda = codMoneda;
    }

    public Double getTipCambio() {
        return this.tipCambio;
    }

    public RegCompras tipCambio(Double tipCambio) {
        this.setTipCambio(tipCambio);
        return this;
    }

    public void setTipCambio(Double tipCambio) {
        this.tipCambio = tipCambio;
    }

    public ZonedDateTime getFecEmiModif() {
        return this.fecEmiModif;
    }

    public RegCompras fecEmiModif(ZonedDateTime fecEmiModif) {
        this.setFecEmiModif(fecEmiModif);
        return this;
    }

    public void setFecEmiModif(ZonedDateTime fecEmiModif) {
        this.fecEmiModif = fecEmiModif;
    }

    public String getTipCompModif() {
        return this.tipCompModif;
    }

    public RegCompras tipCompModif(String tipCompModif) {
        this.setTipCompModif(tipCompModif);
        return this;
    }

    public void setTipCompModif(String tipCompModif) {
        this.tipCompModif = tipCompModif;
    }

    public String getNroSerieCompModif() {
        return this.nroSerieCompModif;
    }

    public RegCompras nroSerieCompModif(String nroSerieCompModif) {
        this.setNroSerieCompModif(nroSerieCompModif);
        return this;
    }

    public void setNroSerieCompModif(String nroSerieCompModif) {
        this.nroSerieCompModif = nroSerieCompModif;
    }

    public String getCodDuaRef() {
        return this.codDuaRef;
    }

    public RegCompras codDuaRef(String codDuaRef) {
        this.setCodDuaRef(codDuaRef);
        return this;
    }

    public void setCodDuaRef(String codDuaRef) {
        this.codDuaRef = codDuaRef;
    }

    public String getNroCompModif() {
        return this.nroCompModif;
    }

    public RegCompras nroCompModif(String nroCompModif) {
        this.setNroCompModif(nroCompModif);
        return this;
    }

    public void setNroCompModif(String nroCompModif) {
        this.nroCompModif = nroCompModif;
    }

    public ZonedDateTime getFecEmiDetracc() {
        return this.fecEmiDetracc;
    }

    public RegCompras fecEmiDetracc(ZonedDateTime fecEmiDetracc) {
        this.setFecEmiDetracc(fecEmiDetracc);
        return this;
    }

    public void setFecEmiDetracc(ZonedDateTime fecEmiDetracc) {
        this.fecEmiDetracc = fecEmiDetracc;
    }

    public String getNroConstDetracc() {
        return this.nroConstDetracc;
    }

    public RegCompras nroConstDetracc(String nroConstDetracc) {
        this.setNroConstDetracc(nroConstDetracc);
        return this;
    }

    public void setNroConstDetracc(String nroConstDetracc) {
        this.nroConstDetracc = nroConstDetracc;
    }

    public String getIndCompSujRetenc() {
        return this.indCompSujRetenc;
    }

    public RegCompras indCompSujRetenc(String indCompSujRetenc) {
        this.setIndCompSujRetenc(indCompSujRetenc);
        return this;
    }

    public void setIndCompSujRetenc(String indCompSujRetenc) {
        this.indCompSujRetenc = indCompSujRetenc;
    }

    public String getClasBienesyServicios() {
        return this.clasBienesyServicios;
    }

    public RegCompras clasBienesyServicios(String clasBienesyServicios) {
        this.setClasBienesyServicios(clasBienesyServicios);
        return this;
    }

    public void setClasBienesyServicios(String clasBienesyServicios) {
        this.clasBienesyServicios = clasBienesyServicios;
    }

    public String getIdentContrato() {
        return this.identContrato;
    }

    public RegCompras identContrato(String identContrato) {
        this.setIdentContrato(identContrato);
        return this;
    }

    public void setIdentContrato(String identContrato) {
        this.identContrato = identContrato;
    }

    public String getErrTipUno() {
        return this.errTipUno;
    }

    public RegCompras errTipUno(String errTipUno) {
        this.setErrTipUno(errTipUno);
        return this;
    }

    public void setErrTipUno(String errTipUno) {
        this.errTipUno = errTipUno;
    }

    public String getErrTipDos() {
        return this.errTipDos;
    }

    public RegCompras errTipDos(String errTipDos) {
        this.setErrTipDos(errTipDos);
        return this;
    }

    public void setErrTipDos(String errTipDos) {
        this.errTipDos = errTipDos;
    }

    public String getErrTipTres() {
        return this.errTipTres;
    }

    public RegCompras errTipTres(String errTipTres) {
        this.setErrTipTres(errTipTres);
        return this;
    }

    public void setErrTipTres(String errTipTres) {
        this.errTipTres = errTipTres;
    }

    public String getErrTipCuatro() {
        return this.errTipCuatro;
    }

    public RegCompras errTipCuatro(String errTipCuatro) {
        this.setErrTipCuatro(errTipCuatro);
        return this;
    }

    public void setErrTipCuatro(String errTipCuatro) {
        this.errTipCuatro = errTipCuatro;
    }

    public String getIndCompPagoMedPago() {
        return this.indCompPagoMedPago;
    }

    public RegCompras indCompPagoMedPago(String indCompPagoMedPago) {
        this.setIndCompPagoMedPago(indCompPagoMedPago);
        return this;
    }

    public void setIndCompPagoMedPago(String indCompPagoMedPago) {
        this.indCompPagoMedPago = indCompPagoMedPago;
    }

    public Integer getEstado() {
        return this.estado;
    }

    public RegCompras estado(Integer estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getCampoLibre() {
        return this.campoLibre;
    }

    public RegCompras campoLibre(String campoLibre) {
        this.setCampoLibre(campoLibre);
        return this;
    }

    public void setCampoLibre(String campoLibre) {
        this.campoLibre = campoLibre;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public RegCompras indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public RegCompras fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public RegCompras usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public RegCompras ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public RegCompras fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public RegCompras usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public RegCompras ipModif(String ipModif) {
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
        if (!(o instanceof RegCompras)) {
            return false;
        }
        return id != null && id.equals(((RegCompras) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RegCompras{" +
            "id=" + getId() +
            ", periodo='" + getPeriodo() + "'" +
            ", cuo='" + getCuo() + "'" +
            ", asientContab='" + getAsientContab() + "'" +
            ", fecEmiComp='" + getFecEmiComp() + "'" +
            ", fecVencComp='" + getFecVencComp() + "'" +
            ", tipComp='" + getTipComp() + "'" +
            ", nroSerieComp='" + getNroSerieComp() + "'" +
            ", anhoEmisionDua='" + getAnhoEmisionDua() + "'" +
            ", nroComp='" + getNroComp() + "'" +
            ", nroFinal='" + getNroFinal() + "'" +
            ", tipDocProv='" + getTipDocProv() + "'" +
            ", nroDocProv='" + getNroDocProv() + "'" +
            ", nomApeRazSocProv='" + getNomApeRazSocProv() + "'" +
            ", baseImponible=" + getBaseImponible() +
            ", montoIgv=" + getMontoIgv() +
            ", baseImponible2=" + getBaseImponible2() +
            ", montoIgv2=" + getMontoIgv2() +
            ", baseImponible3=" + getBaseImponible3() +
            ", montoIgv3=" + getMontoIgv3() +
            ", montoNoGravado=" + getMontoNoGravado() +
            ", montoIsc=" + getMontoIsc() +
            ", impConsBols=" + getImpConsBols() +
            ", otrosCargos=" + getOtrosCargos() +
            ", importeTotal=" + getImporteTotal() +
            ", codMoneda='" + getCodMoneda() + "'" +
            ", tipCambio=" + getTipCambio() +
            ", fecEmiModif='" + getFecEmiModif() + "'" +
            ", tipCompModif='" + getTipCompModif() + "'" +
            ", nroSerieCompModif='" + getNroSerieCompModif() + "'" +
            ", codDuaRef='" + getCodDuaRef() + "'" +
            ", nroCompModif='" + getNroCompModif() + "'" +
            ", fecEmiDetracc='" + getFecEmiDetracc() + "'" +
            ", nroConstDetracc='" + getNroConstDetracc() + "'" +
            ", indCompSujRetenc='" + getIndCompSujRetenc() + "'" +
            ", clasBienesyServicios='" + getClasBienesyServicios() + "'" +
            ", identContrato='" + getIdentContrato() + "'" +
            ", errTipUno='" + getErrTipUno() + "'" +
            ", errTipDos='" + getErrTipDos() + "'" +
            ", errTipTres='" + getErrTipTres() + "'" +
            ", errTipCuatro='" + getErrTipCuatro() + "'" +
            ", indCompPagoMedPago='" + getIndCompPagoMedPago() + "'" +
            ", estado=" + getEstado() +
            ", campoLibre='" + getCampoLibre() + "'" +
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
