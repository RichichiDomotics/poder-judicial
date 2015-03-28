package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * AcuseRecibo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AcuseRecibo")
@PrimaryKeyJoinColumn(name = "AcuseRecibo_id")
public class AcuseRecibo extends Documento {

    // Fields

    private Solicitud solicitud;

    private Date fechaAcuse;
    private String nombreRecibe;

    // Constructors

    /** default constructor */
    public AcuseRecibo() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Solicitud_id", nullable = false)
    public Solicitud getSolicitud() {
        return this.solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    @Column(name = "dfechaAcuse", nullable = false, length = 23)
    public Date getFechaAcuse() {
        return this.fechaAcuse;
    }

    public void setFechaAcuse(Date dfechaAcuse) {
        this.fechaAcuse = dfechaAcuse;
    }

    @Column(name = "cNombreRecibe", nullable = false, length = 60)
    public String getNombreRecibe() {
        return this.nombreRecibe;
    }

    public void setNombreRecibe(String cnombreRecibe) {
        this.nombreRecibe = cnombreRecibe;
    }

}