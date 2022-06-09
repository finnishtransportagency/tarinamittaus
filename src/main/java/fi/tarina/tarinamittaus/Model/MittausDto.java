package fi.tarina.tarinamittaus.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class MittausDto implements Serializable {

    private Integer kohde_id;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Europe/Helsinki")
    @ApiModelProperty(value = "value to show", example = "2021-04-27")
    private Timestamp alkuaika;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Europe/Helsinki")
    @ApiModelProperty(value = "value to show", example = "2021-04-27")
    private Timestamp loppuaika;

    private String mittaus_asianhallinta_id;
    private String pdf_raportin_linkki;
    private Double rakennuksen_pinta_ala;
    private String perustamistapa;
    private String julkisivumateriaali;
    private String runkomateriaali;
    private Integer rakennusvuosi;
    private String katuosoite;
    private String postinumero;
    private String created_by_lx;
    @JsonProperty("asennettuAnturi")
    private List<AsennettuAnturiDto> asennettuAnturiDtos = new ArrayList<>();

    public Integer getKohde_id() {
        return kohde_id;
    }

    public void setKohde_id(Integer kohde_id) {
        this.kohde_id = kohde_id;
    }

    public Timestamp getAlkuaika() {
        return alkuaika;
    }

    public void setAlkuaika(Timestamp alkuaika) {
        this.alkuaika = alkuaika;
    }

    public Timestamp getLoppuaika() {
        return loppuaika;
    }

    public void setLoppuaika(Timestamp loppuaika) {
        this.loppuaika = loppuaika;
    }

    public String getMittaus_asianhallinta_id() {
        return mittaus_asianhallinta_id;
    }

    public void setMittaus_asianhallinta_id(String mittaus_asianhallinta_id) {
        this.mittaus_asianhallinta_id = mittaus_asianhallinta_id;
    }

    public String getPdf_raportin_linkki() {
        return pdf_raportin_linkki;
    }

    public void setPdf_raportin_linkki(String pdf_raportin_linkki) {
        this.pdf_raportin_linkki = pdf_raportin_linkki;
    }

    public Double getRakennuksen_pinta_ala() {
        return rakennuksen_pinta_ala;
    }

    public void setRakennuksen_pinta_ala(Double rakennuksen_pinta_ala) {
        this.rakennuksen_pinta_ala = rakennuksen_pinta_ala;
    }

    public String getPerustamistapa() {
        return perustamistapa;
    }

    public void setPerustamistapa(String perustamistapa) {
        this.perustamistapa = perustamistapa;
    }

    public String getJulkisivumateriaali() {
        return julkisivumateriaali;
    }

    public void setJulkisivumateriaali(String julkisivumateriaali) {
        this.julkisivumateriaali = julkisivumateriaali;
    }

    public String getRunkomateriaali() {
        return runkomateriaali;
    }

    public void setRunkomateriaali(String runkomateriaali) {
        this.runkomateriaali = runkomateriaali;
    }

    public Integer getRakennusvuosi() {
        return rakennusvuosi;
    }

    public void setRakennusvuosi(Integer rakennusvuosi) {
        this.rakennusvuosi = rakennusvuosi;
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }

    public String getCreated_by_lx() {
        return created_by_lx;
    }

    public void setCreated_by_lx(String created_by_lx) {
        this.created_by_lx = created_by_lx;
    }

    public List<AsennettuAnturiDto> getAsennettuAnturiDtos() {
        return asennettuAnturiDtos;
    }

    public void setAsennettuAnturiDtos(List<AsennettuAnturiDto> asennettuAnturiDtos) {
        this.asennettuAnturiDtos = asennettuAnturiDtos;
    }

    public MittausDto() {
    }

    public MittausDto(Integer kohde_id, Timestamp alkuaika, Timestamp loppuaika, String mittaus_asianhallinta_id, String pdf_raportin_linkki, Double rakennuksen_pinta_ala, String perustamistapa, String julkisivumateriaali, String runkomateriaali, Integer rakennusvuosi, String katuosoite, String postinumero, String created_by_lx, List<AsennettuAnturiDto> asennettuAnturiDtos) {
        this.kohde_id = kohde_id;
        this.alkuaika = alkuaika;
        this.loppuaika = loppuaika;
        this.mittaus_asianhallinta_id = mittaus_asianhallinta_id;
        this.pdf_raportin_linkki = pdf_raportin_linkki;
        this.rakennuksen_pinta_ala = rakennuksen_pinta_ala;
        this.perustamistapa = perustamistapa;
        this.julkisivumateriaali = julkisivumateriaali;
        this.runkomateriaali = runkomateriaali;
        this.rakennusvuosi = rakennusvuosi;
        this.katuosoite = katuosoite;
        this.postinumero = postinumero;
        this.created_by_lx = created_by_lx;
        this.asennettuAnturiDtos = asennettuAnturiDtos;
    }

    @Override
    public String toString() {
        return "MittausDto{" +
                "kohde_id=" + kohde_id +
                ", alkuaika=" + alkuaika +
                ", loppuaika=" + loppuaika +
                ", mittaus_asianhallinta_id='" + mittaus_asianhallinta_id + '\'' +
                ", pdf_raportin_linkki='" + pdf_raportin_linkki + '\'' +
                ", rakennuksen_pinta_ala=" + rakennuksen_pinta_ala +
                ", perustamistapa='" + perustamistapa + '\'' +
                ", julkisivumateriaali='" + julkisivumateriaali + '\'' +
                ", runkomateriaali='" + runkomateriaali + '\'' +
                ", rakennusvuosi=" + rakennusvuosi +
                ", katuosoite='" + katuosoite + '\'' +
                ", postinumero='" + postinumero + '\'' +
                ", created_by_lx='" + created_by_lx + '\'' +
                ", asennettuAnturiDtos=" + asennettuAnturiDtos +
                '}';
    }
}
