package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Resolutivo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Resolutivo")
public class Resolutivo implements java.io.Serializable {

    // Fields

    private Long resolutivoId;
    private Audiencia audiencia;
    private Date temporizador;
    private String detalle;
    private Mandamiento documento;

    // Constructors

    /** default constructor */
    public Resolutivo() {
    }

    public Resolutivo(long id, Date time, String texto) {
        this.resolutivoId = id;
        this.temporizador = time;
        this.detalle = texto;
        
        
    }
    
    public Resolutivo(long id, Date time, String texto, Mandamiento documento) {
        this.resolutivoId = id;
        this.temporizador = time;
        this.detalle = texto;
        this.documento = documento;
        
        
        
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Resolutivo_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getResolutivoId() {
        return this.resolutivoId;
    }

    public void setResolutivoId(Long resolutivoId) {
        this.resolutivoId = resolutivoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Audiencia_id", nullable = false)
    public Audiencia getAudiencia() {
        return this.audiencia;
    }

    public void setAudiencia(Audiencia audiencia) {
        this.audiencia = audiencia;
    }

    @Column(name = "dTemporizador", nullable = false, length = 23)
    public Date getTemporizador() {
        return this.temporizador;
    }

    public void setTemporizador(Date dtemporizador) {
        this.temporizador = dtemporizador;
    }

    @Column(name = "cDetalle", nullable = false)
    public String getDetalle() {
        return this.detalle;
    }

    public void setDetalle(String cdetalle) {
        this.detalle = cdetalle;
    }
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resolutivo")
    public Mandamiento getDocumento() {
        return this.documento;
    }

    public void setDocumento(Mandamiento documento) {
        this.documento = documento;
    }
}