package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SalaTemporal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SalaTemporal")
public class SalaTemporal implements java.io.Serializable {

    // Fields

    private Long salaTemporalId;
    private String motivo;
    private String domicilioSala;
    private String ubicacionSala;
    private Set<Audiencia> audiencias = new HashSet<Audiencia>(0);

    // Constructors

    /** default constructor */
    public SalaTemporal() {
    }

    /** minimal constructor */
    public SalaTemporal(Long salaTemporalId, String cmotivo,
            String cdomicilioSala, String cubicacionSala) {
        this.salaTemporalId = salaTemporalId;
        this.motivo = cmotivo;
        this.domicilioSala = cdomicilioSala;
        this.ubicacionSala = cubicacionSala;
    }

    /** full constructor */
    public SalaTemporal(Long salaTemporalId, String cmotivo,
            String cdomicilioSala, String cubicacionSala,
            Set<Audiencia> audiencias) {
        this.salaTemporalId = salaTemporalId;
        this.motivo = cmotivo;
        this.domicilioSala = cdomicilioSala;
        this.ubicacionSala = cubicacionSala;
        this.audiencias = audiencias;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SalaTemporal_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getSalaTemporalId() {
        return this.salaTemporalId;
    }

    public void setSalaTemporalId(Long salaTemporalId) {
        this.salaTemporalId = salaTemporalId;
    }

    @Column(name = "cMotivo", nullable = false, length = 100)
    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String cmotivo) {
        this.motivo = cmotivo;
    }

    @Column(name = "cDomicilioSala", nullable = false, length = 150)
    public String getDomicilioSala() {
        return this.domicilioSala;
    }

    public void setDomicilioSala(String cdomicilioSala) {
        this.domicilioSala = cdomicilioSala;
    }

    @Column(name = "cUbicacionSala", nullable = false, length = 100)
    public String getUbicacionSala() {
        return this.ubicacionSala;
    }

    public void setUbicacionSala(String cubicacionSala) {
        this.ubicacionSala = cubicacionSala;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "salaTemporal")
    public Set<Audiencia> getAudiencias() {
        return this.audiencias;
    }

    public void setAudiencias(Set<Audiencia> audiencias) {
        this.audiencias = audiencias;
    }

}