package mx.gob.segob.nsjp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * SolicitudAudiencia entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SolicitudAudiencia")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "SolicitudAudiencia_id")
public class SolicitudAudiencia extends Solicitud {

    // Fields

    private Audiencia audiencia;

    // Constructors

    /** default constructor */
    public SolicitudAudiencia() {
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Audiencia_id",  unique = true, nullable = false)
    public Audiencia getAudiencia() {
        return this.audiencia;
    }

    public void setAudiencia(Audiencia audiencia) {
        this.audiencia = audiencia;
    }

}