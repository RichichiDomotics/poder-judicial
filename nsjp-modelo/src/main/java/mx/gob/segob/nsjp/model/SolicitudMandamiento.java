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
 * SolicitudMandamiento entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SolicitudMandamiento")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "SolicitudMandamiento_id")
public class SolicitudMandamiento extends Solicitud{

    // Fields

    private Valor tipoMandamiento;

    // Constructors

    /** default constructor */
    public SolicitudMandamiento() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoMandamiento_val")
    public Valor getTipoMandamiento() {
        return this.tipoMandamiento;
    }

    public void setTipoMandamiento(Valor valor) {
        this.tipoMandamiento = valor;
    }

}