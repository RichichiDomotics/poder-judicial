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
 * SolicitudDiligencia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SolicitudDiligencia")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "SolicitudDiligencia_id")
public class SolicitudDiligencia extends Solicitud {

    // Fields

    private Valor tipoDiligencia;

    // Constructors

    /** default constructor */
    public SolicitudDiligencia() {
    }

   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoDiligencia_val")
    public Valor getTipoDiligencia() {
        return this.tipoDiligencia;
    }

    public void setTipoDiligencia(Valor valor) {
        this.tipoDiligencia = valor;
    }

}