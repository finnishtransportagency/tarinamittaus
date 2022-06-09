package fi.tarina.tarinamittaus.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"asennettuAnturiDto"})
public class AnturikohtaisetTunnusarvotDto implements Serializable {

    private Integer tunnusarvo_id;

    @ApiModelProperty(value = "value to show", example = "X")
    private char mittaussuunta_xyz;
    private Double tarinan_maksimiarvo;
    private Double hallitseva_taajuus;
    private Double tarinan_tunnusluku_vw95_rms;
    private AsennettuAnturiDto asennettuAnturiDto;

    public Integer getTunnusarvo_id() {
        return tunnusarvo_id;
    }

    public void setTunnusarvo_id(Integer tunnusarvo_id) {
        this.tunnusarvo_id = tunnusarvo_id;
    }

    public char getMittaussuunta_xyz() {
        return mittaussuunta_xyz;
    }

    public void setMittaussuunta_xyz(char mittaussuunta_xyz) {
        this.mittaussuunta_xyz = mittaussuunta_xyz;
    }

    public Double getTarinan_maksimiarvo() {
        return tarinan_maksimiarvo;
    }

    public void setTarinan_maksimiarvo(Double tarinan_maksimiarvo) {
        this.tarinan_maksimiarvo = tarinan_maksimiarvo;
    }

    public Double getHallitseva_taajuus() {
        return hallitseva_taajuus;
    }

    public void setHallitseva_taajuus(Double hallitseva_taajuus) {
        this.hallitseva_taajuus = hallitseva_taajuus;
    }

    public Double getTarinan_tunnusluku_vw95_rms() {
        return tarinan_tunnusluku_vw95_rms;
    }

    public void setTarinan_tunnusluku_vw95_rms(Double tarinan_tunnusluku_vw95_rms) {
        this.tarinan_tunnusluku_vw95_rms = tarinan_tunnusluku_vw95_rms;
    }

    public AsennettuAnturiDto getAsennettuAnturiDto() {
        return asennettuAnturiDto;
    }

    public void setAsennettuAnturiDto(AsennettuAnturiDto asennettuAnturiDto) {
        this.asennettuAnturiDto = asennettuAnturiDto;
    }

    public AnturikohtaisetTunnusarvotDto() {
    }

    public AnturikohtaisetTunnusarvotDto(Integer tunnusarvo_id, char mittaussuunta_xyz, Double tarinan_maksimiarvo, Double hallitseva_taajuus, Double tarinan_tunnusluku_vw95_rms, AsennettuAnturiDto asennettuAnturiDto) {
        this.tunnusarvo_id = tunnusarvo_id;
        this.mittaussuunta_xyz = mittaussuunta_xyz;
        this.tarinan_maksimiarvo = tarinan_maksimiarvo;
        this.hallitseva_taajuus = hallitseva_taajuus;
        this.tarinan_tunnusluku_vw95_rms = tarinan_tunnusluku_vw95_rms;
        this.asennettuAnturiDto = asennettuAnturiDto;
    }

    @Override
    public String toString() {
        return "AnturikohtaisetTunnusarvotDto{" +
                "tunnusarvo_id=" + tunnusarvo_id +
                ", mittaussuunta_xyz=" + mittaussuunta_xyz +
                ", tarinan_maksimiarvo=" + tarinan_maksimiarvo +
                ", hallitseva_taajuus=" + hallitseva_taajuus +
                ", tarinan_tunnusluku_vw95_rms=" + tarinan_tunnusluku_vw95_rms +
                ", asennettuAnturiDto=" + asennettuAnturiDto +
                '}';
    }
}
