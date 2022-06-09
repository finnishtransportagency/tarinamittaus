package fi.tarina.tarinamittaus.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"asennettuAnturiDto"})
public class AsennuspaikanTyyppiDto implements Serializable {

    private Integer paikkatyyppi_id;
    @ApiModelProperty(value = "maa", example = "maa")
    private String selite;
    private String lisatiedot;
    private AsennettuAnturiDto asennettuAnturiDto;

    public Integer getPaikkatyyppi_id() {
        return paikkatyyppi_id;
    }

    public void setPaikkatyyppi_id(Integer paikkatyyppi_id) {
        this.paikkatyyppi_id = paikkatyyppi_id;
    }

    public String getSelite() {
        return selite;
    }

    public void setSelite(String selite) {
        this.selite = selite;
    }

    public String getLisatiedot() {
        return lisatiedot;
    }

    public void setLisatiedot(String lisatiedot) {
        this.lisatiedot = lisatiedot;
    }

    public AsennettuAnturiDto getAsennettuAnturiDto() {
        return asennettuAnturiDto;
    }

    public void setAsennettuAnturiDto(AsennettuAnturiDto asennettuAnturiDto) {
        this.asennettuAnturiDto = asennettuAnturiDto;
    }

    public AsennuspaikanTyyppiDto() {
    }

    public AsennuspaikanTyyppiDto(Integer paikkatyyppi_id, String selite, String lisatiedot, AsennettuAnturiDto asennettuAnturiDto) {
        this.paikkatyyppi_id = paikkatyyppi_id;
        this.selite = selite;
        this.lisatiedot = lisatiedot;
        this.asennettuAnturiDto = asennettuAnturiDto;
    }

    @Override
    public String toString() {
        return "AsennuspaikanTyyppiDto{" +
                "paikkatyyppi_id=" + paikkatyyppi_id +
                ", selite='" + selite + '\'' +
                ", lisatiedot='" + lisatiedot + '\'' +
                ", asennettuAnturiDto=" + asennettuAnturiDto +
                '}';
    }
}
