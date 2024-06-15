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
 * A RegVenta.
 */
@Document(collection = "reg_venta")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RegVenta implements Serializable {

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

    @NotNull
    @Field("nro_comp")
    private String nroComp;

    @Field("conso_dia")
    private String consoDia;

    @NotNull
    @Field("tip_doc_cli")
    private String tipDocCli;

    @NotNull
    @Field("nro_doc_cli")
    private String nroDocCli;

    @NotNull
    @Field("ape_raz_soc_cli")
    private String apeRazSocCli;

    @Field("val_fac_expor")
    private Double valFacExpor;

    @NotNull
    @Field("base_imp_oper_grav")
    private Double baseImpOperGrav;

    @NotNull
    @Field("dscto_base_imp")
    private Double dsctoBaseImp;

    @NotNull
    @Field("igv_ipm")
    private Double igvIpm;

    @NotNull
    @Field("dscto_igv_ipm")
    private Double dsctoIgvIpm;

    @NotNull
    @Field("imp_ope_exo")
    private Double impOpeExo;

    @NotNull
    @Field("imp_tot_ope_inafec")
    private Double impTotOpeInafec;

    @Field("imp_sec_cons")
    private Double impSecCons;

    @Field("base_imp_arroz")
    private Double baseImpArroz;

    @Field("imp_vent_arroz")
    private Double impVentArroz;

    @Field("otros_no_base_imp")
    private Double otrosNoBaseImp;

    @NotNull
    @Field("importe_total_comp")
    private Double importeTotalComp;

    @NotNull
    @Field("cod_moneda")
    private String codMoneda;

    @NotNull
    @Field("tip_cambio")
    private Double tipCambio;

    @Field("fec_emi_modif")
    private ZonedDateTime fecEmiModif;

    @Field("tip_comp_modif")
    private String tipCompModif;

    @Field("nro_serie_comp_modif")
    private String nroSerieCompModif;

    @Field("nro_comp_modif")
    private String nroCompModif;

    @Field("ident_contrato")
    private String identContrato;

    @Field("err_tip_uno")
    private String errTipUno;

    @Field("ind_comp_vanc_mp")
    private String indCompVancMp;

    @Field("estado_opera_canc_mp")
    private String estadoOperaCancMp;

    @Field("campo_libre")
    private String campoLibre;

    @Field("form_pago")
    private String formPago;

    @Field("moneda")
    private String moneda;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public RegVenta id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeriodo() {
        return this.periodo;
    }

    public RegVenta periodo(String periodo) {
        this.setPeriodo(periodo);
        return this;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getCuo() {
        return this.cuo;
    }

    public RegVenta cuo(String cuo) {
        this.setCuo(cuo);
        return this;
    }

    public void setCuo(String cuo) {
        this.cuo = cuo;
    }

    public String getAsientContab() {
        return this.asientContab;
    }

    public RegVenta asientContab(String asientContab) {
        this.setAsientContab(asientContab);
        return this;
    }

    public void setAsientContab(String asientContab) {
        this.asientContab = asientContab;
    }

    public ZonedDateTime getFecEmiComp() {
        return this.fecEmiComp;
    }

    public RegVenta fecEmiComp(ZonedDateTime fecEmiComp) {
        this.setFecEmiComp(fecEmiComp);
        return this;
    }

    public void setFecEmiComp(ZonedDateTime fecEmiComp) {
        this.fecEmiComp = fecEmiComp;
    }

    public ZonedDateTime getFecVencComp() {
        return this.fecVencComp;
    }

    public RegVenta fecVencComp(ZonedDateTime fecVencComp) {
        this.setFecVencComp(fecVencComp);
        return this;
    }

    public void setFecVencComp(ZonedDateTime fecVencComp) {
        this.fecVencComp = fecVencComp;
    }

    public String getTipComp() {
        return this.tipComp;
    }

    public RegVenta tipComp(String tipComp) {
        this.setTipComp(tipComp);
        return this;
    }

    public void setTipComp(String tipComp) {
        this.tipComp = tipComp;
    }

    public String getNroSerieComp() {
        return this.nroSerieComp;
    }

    public RegVenta nroSerieComp(String nroSerieComp) {
        this.setNroSerieComp(nroSerieComp);
        return this;
    }

    public void setNroSerieComp(String nroSerieComp) {
        this.nroSerieComp = nroSerieComp;
    }

    public String getNroComp() {
        return this.nroComp;
    }

    public RegVenta nroComp(String nroComp) {
        this.setNroComp(nroComp);
        return this;
    }

    public void setNroComp(String nroComp) {
        this.nroComp = nroComp;
    }

    public String getConsoDia() {
        return this.consoDia;
    }

    public RegVenta consoDia(String consoDia) {
        this.setConsoDia(consoDia);
        return this;
    }

    public void setConsoDia(String consoDia) {
        this.consoDia = consoDia;
    }

    public String getTipDocCli() {
        return this.tipDocCli;
    }

    public RegVenta tipDocCli(String tipDocCli) {
        this.setTipDocCli(tipDocCli);
        return this;
    }

    public void setTipDocCli(String tipDocCli) {
        this.tipDocCli = tipDocCli;
    }

    public String getNroDocCli() {
        return this.nroDocCli;
    }

    public RegVenta nroDocCli(String nroDocCli) {
        this.setNroDocCli(nroDocCli);
        return this;
    }

    public void setNroDocCli(String nroDocCli) {
        this.nroDocCli = nroDocCli;
    }

    public String getApeRazSocCli() {
        return this.apeRazSocCli;
    }

    public RegVenta apeRazSocCli(String apeRazSocCli) {
        this.setApeRazSocCli(apeRazSocCli);
        return this;
    }

    public void setApeRazSocCli(String apeRazSocCli) {
        this.apeRazSocCli = apeRazSocCli;
    }

    public Double getValFacExpor() {
        return this.valFacExpor;
    }

    public RegVenta valFacExpor(Double valFacExpor) {
        this.setValFacExpor(valFacExpor);
        return this;
    }

    public void setValFacExpor(Double valFacExpor) {
        this.valFacExpor = valFacExpor;
    }

    public Double getBaseImpOperGrav() {
        return this.baseImpOperGrav;
    }

    public RegVenta baseImpOperGrav(Double baseImpOperGrav) {
        this.setBaseImpOperGrav(baseImpOperGrav);
        return this;
    }

    public void setBaseImpOperGrav(Double baseImpOperGrav) {
        this.baseImpOperGrav = baseImpOperGrav;
    }

    public Double getDsctoBaseImp() {
        return this.dsctoBaseImp;
    }

    public RegVenta dsctoBaseImp(Double dsctoBaseImp) {
        this.setDsctoBaseImp(dsctoBaseImp);
        return this;
    }

    public void setDsctoBaseImp(Double dsctoBaseImp) {
        this.dsctoBaseImp = dsctoBaseImp;
    }

    public Double getIgvIpm() {
        return this.igvIpm;
    }

    public RegVenta igvIpm(Double igvIpm) {
        this.setIgvIpm(igvIpm);
        return this;
    }

    public void setIgvIpm(Double igvIpm) {
        this.igvIpm = igvIpm;
    }

    public Double getDsctoIgvIpm() {
        return this.dsctoIgvIpm;
    }

    public RegVenta dsctoIgvIpm(Double dsctoIgvIpm) {
        this.setDsctoIgvIpm(dsctoIgvIpm);
        return this;
    }

    public void setDsctoIgvIpm(Double dsctoIgvIpm) {
        this.dsctoIgvIpm = dsctoIgvIpm;
    }

    public Double getImpOpeExo() {
        return this.impOpeExo;
    }

    public RegVenta impOpeExo(Double impOpeExo) {
        this.setImpOpeExo(impOpeExo);
        return this;
    }

    public void setImpOpeExo(Double impOpeExo) {
        this.impOpeExo = impOpeExo;
    }

    public Double getImpTotOpeInafec() {
        return this.impTotOpeInafec;
    }

    public RegVenta impTotOpeInafec(Double impTotOpeInafec) {
        this.setImpTotOpeInafec(impTotOpeInafec);
        return this;
    }

    public void setImpTotOpeInafec(Double impTotOpeInafec) {
        this.impTotOpeInafec = impTotOpeInafec;
    }

    public Double getImpSecCons() {
        return this.impSecCons;
    }

    public RegVenta impSecCons(Double impSecCons) {
        this.setImpSecCons(impSecCons);
        return this;
    }

    public void setImpSecCons(Double impSecCons) {
        this.impSecCons = impSecCons;
    }

    public Double getBaseImpArroz() {
        return this.baseImpArroz;
    }

    public RegVenta baseImpArroz(Double baseImpArroz) {
        this.setBaseImpArroz(baseImpArroz);
        return this;
    }

    public void setBaseImpArroz(Double baseImpArroz) {
        this.baseImpArroz = baseImpArroz;
    }

    public Double getImpVentArroz() {
        return this.impVentArroz;
    }

    public RegVenta impVentArroz(Double impVentArroz) {
        this.setImpVentArroz(impVentArroz);
        return this;
    }

    public void setImpVentArroz(Double impVentArroz) {
        this.impVentArroz = impVentArroz;
    }

    public Double getOtrosNoBaseImp() {
        return this.otrosNoBaseImp;
    }

    public RegVenta otrosNoBaseImp(Double otrosNoBaseImp) {
        this.setOtrosNoBaseImp(otrosNoBaseImp);
        return this;
    }

    public void setOtrosNoBaseImp(Double otrosNoBaseImp) {
        this.otrosNoBaseImp = otrosNoBaseImp;
    }

    public Double getImporteTotalComp() {
        return this.importeTotalComp;
    }

    public RegVenta importeTotalComp(Double importeTotalComp) {
        this.setImporteTotalComp(importeTotalComp);
        return this;
    }

    public void setImporteTotalComp(Double importeTotalComp) {
        this.importeTotalComp = importeTotalComp;
    }

    public String getCodMoneda() {
        return this.codMoneda;
    }

    public RegVenta codMoneda(String codMoneda) {
        this.setCodMoneda(codMoneda);
        return this;
    }

    public void setCodMoneda(String codMoneda) {
        this.codMoneda = codMoneda;
    }

    public Double getTipCambio() {
        return this.tipCambio;
    }

    public RegVenta tipCambio(Double tipCambio) {
        this.setTipCambio(tipCambio);
        return this;
    }

    public void setTipCambio(Double tipCambio) {
        this.tipCambio = tipCambio;
    }

    public ZonedDateTime getFecEmiModif() {
        return this.fecEmiModif;
    }

    public RegVenta fecEmiModif(ZonedDateTime fecEmiModif) {
        this.setFecEmiModif(fecEmiModif);
        return this;
    }

    public void setFecEmiModif(ZonedDateTime fecEmiModif) {
        this.fecEmiModif = fecEmiModif;
    }

    public String getTipCompModif() {
        return this.tipCompModif;
    }

    public RegVenta tipCompModif(String tipCompModif) {
        this.setTipCompModif(tipCompModif);
        return this;
    }

    public void setTipCompModif(String tipCompModif) {
        this.tipCompModif = tipCompModif;
    }

    public String getNroSerieCompModif() {
        return this.nroSerieCompModif;
    }

    public RegVenta nroSerieCompModif(String nroSerieCompModif) {
        this.setNroSerieCompModif(nroSerieCompModif);
        return this;
    }

    public void setNroSerieCompModif(String nroSerieCompModif) {
        this.nroSerieCompModif = nroSerieCompModif;
    }

    public String getNroCompModif() {
        return this.nroCompModif;
    }

    public RegVenta nroCompModif(String nroCompModif) {
        this.setNroCompModif(nroCompModif);
        return this;
    }

    public void setNroCompModif(String nroCompModif) {
        this.nroCompModif = nroCompModif;
    }

    public String getIdentContrato() {
        return this.identContrato;
    }

    public RegVenta identContrato(String identContrato) {
        this.setIdentContrato(identContrato);
        return this;
    }

    public void setIdentContrato(String identContrato) {
        this.identContrato = identContrato;
    }

    public String getErrTipUno() {
        return this.errTipUno;
    }

    public RegVenta errTipUno(String errTipUno) {
        this.setErrTipUno(errTipUno);
        return this;
    }

    public void setErrTipUno(String errTipUno) {
        this.errTipUno = errTipUno;
    }

    public String getIndCompVancMp() {
        return this.indCompVancMp;
    }

    public RegVenta indCompVancMp(String indCompVancMp) {
        this.setIndCompVancMp(indCompVancMp);
        return this;
    }

    public void setIndCompVancMp(String indCompVancMp) {
        this.indCompVancMp = indCompVancMp;
    }

    public String getEstadoOperaCancMp() {
        return this.estadoOperaCancMp;
    }

    public RegVenta estadoOperaCancMp(String estadoOperaCancMp) {
        this.setEstadoOperaCancMp(estadoOperaCancMp);
        return this;
    }

    public void setEstadoOperaCancMp(String estadoOperaCancMp) {
        this.estadoOperaCancMp = estadoOperaCancMp;
    }

    public String getCampoLibre() {
        return this.campoLibre;
    }

    public RegVenta campoLibre(String campoLibre) {
        this.setCampoLibre(campoLibre);
        return this;
    }

    public void setCampoLibre(String campoLibre) {
        this.campoLibre = campoLibre;
    }

    public String getFormPago() {
        return this.formPago;
    }

    public RegVenta formPago(String formPago) {
        this.setFormPago(formPago);
        return this;
    }

    public void setFormPago(String formPago) {
        this.formPago = formPago;
    }

    public String getMoneda() {
        return this.moneda;
    }

    public RegVenta moneda(String moneda) {
        this.setMoneda(moneda);
        return this;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public RegVenta indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public RegVenta fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public RegVenta usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public RegVenta ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public RegVenta fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public RegVenta usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public RegVenta ipModif(String ipModif) {
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

    public RegVenta orden(Orden orden) {
        this.setOrden(orden);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegVenta)) {
            return false;
        }
        return id != null && id.equals(((RegVenta) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RegVenta{" +
            "id=" + getId() +
            ", periodo='" + getPeriodo() + "'" +
            ", cuo='" + getCuo() + "'" +
            ", asientContab='" + getAsientContab() + "'" +
            ", fecEmiComp='" + getFecEmiComp() + "'" +
            ", fecVencComp='" + getFecVencComp() + "'" +
            ", tipComp='" + getTipComp() + "'" +
            ", nroSerieComp='" + getNroSerieComp() + "'" +
            ", nroComp='" + getNroComp() + "'" +
            ", consoDia='" + getConsoDia() + "'" +
            ", tipDocCli='" + getTipDocCli() + "'" +
            ", nroDocCli='" + getNroDocCli() + "'" +
            ", apeRazSocCli='" + getApeRazSocCli() + "'" +
            ", valFacExpor=" + getValFacExpor() +
            ", baseImpOperGrav=" + getBaseImpOperGrav() +
            ", dsctoBaseImp=" + getDsctoBaseImp() +
            ", igvIpm=" + getIgvIpm() +
            ", dsctoIgvIpm=" + getDsctoIgvIpm() +
            ", impOpeExo=" + getImpOpeExo() +
            ", impTotOpeInafec=" + getImpTotOpeInafec() +
            ", impSecCons=" + getImpSecCons() +
            ", baseImpArroz=" + getBaseImpArroz() +
            ", impVentArroz=" + getImpVentArroz() +
            ", otrosNoBaseImp=" + getOtrosNoBaseImp() +
            ", importeTotalComp=" + getImporteTotalComp() +
            ", codMoneda='" + getCodMoneda() + "'" +
            ", tipCambio=" + getTipCambio() +
            ", fecEmiModif='" + getFecEmiModif() + "'" +
            ", tipCompModif='" + getTipCompModif() + "'" +
            ", nroSerieCompModif='" + getNroSerieCompModif() + "'" +
            ", nroCompModif='" + getNroCompModif() + "'" +
            ", identContrato='" + getIdentContrato() + "'" +
            ", errTipUno='" + getErrTipUno() + "'" +
            ", indCompVancMp='" + getIndCompVancMp() + "'" +
            ", estadoOperaCancMp='" + getEstadoOperaCancMp() + "'" +
            ", campoLibre='" + getCampoLibre() + "'" +
            ", formPago='" + getFormPago() + "'" +
            ", moneda='" + getMoneda() + "'" +
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
