package fi.tarina.tarinamittaus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "ASENNETTUANTURI", schema = "TARINAM")
@AllArgsConstructor
@ToString
public class AsennettuAnturi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ASENNUSKOHTAINEN_ID", nullable = false, unique = true)
    @SequenceGenerator(
            name = "seq_gen",
            sequenceName = "asennettuanturi_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_gen"
    )
    private Integer asennuskohtainen_id;

    @Column(name = "MALLI")
    private String malli;

    @Column(name = "GPS_LAT")
    @JsonProperty("gps_lat")
    private Double gpsLat;

    @Column(name = "GPS_LONG")
    @JsonProperty("gps_long")
    private Double gpsLong;

    @Column(name = "ETAISYYS_RADASTA_JOS_ERI")
    @JsonProperty("etaisyys_radasta_jos_eri")
    private Double etaisyysRadastaJosEri;

    @Column(name = "KERROS")
    private Integer kerros;

    @Column(name = "SIJOITUSPAIKAN_LISASELITE")
    @JsonProperty("sijoituspaikan_lisaselite")
    private String sijoituspaikanLisaselite;

    // MITTAUS is foreign key in the table
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "MITTAUS")
    private Mittaus mittaus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ASENNUSPAIKKA")
    private AsennuspaikanTyyppi asennuspaikanTyyppi;

    @OneToMany(mappedBy = "asennettuAnturi",
            fetch = FetchType.LAZY,
            cascade = javax.persistence.CascadeType.ALL,
            orphanRemoval = true)
    @JsonProperty("anturikohtaisetTunnusarvot")
    private List<AnturikohtaisetTunnusarvot> anturikohtaisetTunnusarvotSet = new ArrayList<>();

    public AsennettuAnturi(AsennuspaikanTyyppi asennuspaikanTyyppi) {
        this.asennuspaikanTyyppi = asennuspaikanTyyppi;
    }

    public void addTunnusarvotToSet(AnturikohtaisetTunnusarvot arvot) {
        this.anturikohtaisetTunnusarvotSet.add(arvot);
    }

    public AsennettuAnturi() {
    }

    public AsennettuAnturi(String malli,
                           Double gpsLat,
                           Double gpsLong,
                           Double etaisyysRadastaJosEri,
                           Integer kerros,
                           String sijoituspaikanLisaselite) {
        this.malli = malli;
        this.gpsLat = gpsLat;
        this.gpsLong = gpsLong;
        this.etaisyysRadastaJosEri = etaisyysRadastaJosEri;
        this.kerros = kerros;
        this.sijoituspaikanLisaselite = sijoituspaikanLisaselite;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getAsennuskohtainen_id() {
        return asennuskohtainen_id;
    }

    public void setAsennuskohtainen_id(Integer asennuskohtainen_id) {
        this.asennuskohtainen_id = asennuskohtainen_id;
    }

    public String getMalli() {
        return malli;
    }

    public void setMalli(String malli) {
        this.malli = malli;
    }

    public Double getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(Double gpsLat) {
        this.gpsLat = gpsLat;
    }

    public Double getGpsLong() {
        return gpsLong;
    }

    public void setGpsLong(Double gpsLong) {
        this.gpsLong = gpsLong;
    }

    public Double getEtaisyysRadastaJosEri() {
        return etaisyysRadastaJosEri;
    }

    public void setEtaisyysRadastaJosEri(Double etaisyysRadastaJosEri) {
        this.etaisyysRadastaJosEri = etaisyysRadastaJosEri;
    }

    public Integer getKerros() {
        return kerros;
    }

    public void setKerros(Integer kerros) {
        this.kerros = kerros;
    }

    public String getSijoituspaikanLisaselite() {
        return sijoituspaikanLisaselite;
    }

    public void setSijoituspaikanLisaselite(String sijoituspaikanLisaselite) {
        this.sijoituspaikanLisaselite = sijoituspaikanLisaselite;
    }

    public Mittaus getMittaus() {
        return mittaus;
    }

    public void setMittaus(Mittaus mittaus) {
        this.mittaus = mittaus;
    }

    public AsennuspaikanTyyppi getAsennuspaikanTyyppi() {
        return asennuspaikanTyyppi;
    }

    public void setAsennuspaikanTyyppi(AsennuspaikanTyyppi asennuspaikanTyyppi) {
        this.asennuspaikanTyyppi = asennuspaikanTyyppi;
    }

    public List<AnturikohtaisetTunnusarvot> getAnturikohtaisetTunnusarvotSet() {
        return anturikohtaisetTunnusarvotSet;
    }

    public void setAnturikohtaisetTunnusarvotSet(List<AnturikohtaisetTunnusarvot> anturikohtaisetTunnusarvotSet) {
        this.anturikohtaisetTunnusarvotSet.clear();
        if(anturikohtaisetTunnusarvotSet != null) {
            this.anturikohtaisetTunnusarvotSet.addAll(anturikohtaisetTunnusarvotSet);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AsennettuAnturi)) return false;
        AsennettuAnturi anturi = (AsennettuAnturi) o;
        return getAsennuskohtainen_id().equals(anturi.getAsennuskohtainen_id()) && Objects.equals(
                getMalli(), anturi.getMalli()) && Objects.equals(getGpsLat(),
                                                                 anturi.getGpsLat()) && Objects.equals(
                getGpsLong(), anturi.getGpsLong()) && Objects.equals(getEtaisyysRadastaJosEri(),
                                                                     anturi.getEtaisyysRadastaJosEri()) && Objects.equals(
                getKerros(), anturi.getKerros()) && Objects.equals(getSijoituspaikanLisaselite(),
                                                                   anturi.getSijoituspaikanLisaselite()) && getMittaus().equals(
                anturi.getMittaus()) && Objects.equals(getAsennuspaikanTyyppi(),
                                                       anturi.getAsennuspaikanTyyppi()) && Objects.equals(
                getAnturikohtaisetTunnusarvotSet(), anturi.getAnturikohtaisetTunnusarvotSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAsennuskohtainen_id(), getMalli(), getGpsLat(), getGpsLong(),
                            getEtaisyysRadastaJosEri(), getKerros(), getSijoituspaikanLisaselite(),
                            getMittaus(),
                            getAsennuspaikanTyyppi(), getAnturikohtaisetTunnusarvotSet());
    }
}
