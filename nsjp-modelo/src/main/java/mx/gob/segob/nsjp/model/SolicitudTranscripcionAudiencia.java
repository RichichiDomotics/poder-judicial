package mx.gob.segob.nsjp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * SolicitudTranscripcionAudiencia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SolicitudTranscripcionAudiencia")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "SolicitudTranscripcionAudiencia_id")
public class SolicitudTranscripcionAudiencia extends Solicitud {

    // Fields

    private Audiencia audiencia;

    // Constructors

    /** default constructor */
    public SolicitudTranscripcionAudiencia() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Audiencia_id", nullable = false)
    public Audiencia getAudiencia() {
        return this.audiencia;
    }

    public void setAudiencia(Audiencia audiencia) {
        this.audiencia = audiencia;
    }

}