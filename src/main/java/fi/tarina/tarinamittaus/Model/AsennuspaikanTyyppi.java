package fi.tarina.tarinamittaus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.tarina.tarinamittaus.validator.Selite;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ASENNUSPAIKANTYYPPI", schema = "TARINAM")
@AllArgsConstructor
@ToString
public class AsennuspaikanTyyppi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PAIKKATYYPPI_ID", unique = true, nullable = false)
    @SequenceGenerator(
            name = "seq_gen",
            sequenceName = "paikka_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_gen"
    )
    private Integer paikkatyyppi_id;

    @Column(name = "SELITE")
    @ApiModelProperty(value = "maa", example = "maa")
    @Selite
    private String selite;

    @Column(name = "LISATIEDOT" )
    private String lisatiedot;

    @JsonIgnore
    @OneToOne(mappedBy = "asennuspaikanTyyppi")
    private AsennettuAnturi asennettuAnturiSet;


    public AsennuspaikanTyyppi(String selite, String lisatiedot) {
        this.selite = selite;
        this.lisatiedot = lisatiedot;
    }

    public AsennuspaikanTyyppi() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public void setAsennettuAnturiSet(AsennettuAnturi asennettuAnturiSet) {
        this.asennettuAnturiSet = asennettuAnturiSet;
    }

    public AsennettuAnturi getAsennettuAnturiSet() {
        return asennettuAnturiSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AsennuspaikanTyyppi)) return false;
        AsennuspaikanTyyppi that = (AsennuspaikanTyyppi) o;
        return getPaikkatyyppi_id().equals(that.getPaikkatyyppi_id()) && Objects.equals(getSelite(),
                                                                                        that.getSelite()) && Objects.equals(
                getLisatiedot(), that.getLisatiedot()) && getAsennettuAnturiSet().equals(
                that.getAsennettuAnturiSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPaikkatyyppi_id(), getSelite(), getLisatiedot(), getAsennettuAnturiSet());
    }
}
