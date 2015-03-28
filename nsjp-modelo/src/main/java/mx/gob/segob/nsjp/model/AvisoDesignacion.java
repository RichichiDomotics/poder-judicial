package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * AvisoDesignacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AvisoDesignacion")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "AvisoDesignacion_id")
public class AvisoDesignacion extends Notificacion {

    // Fields
    /**
     * Funcionario al que se asigna.
     */
    private Funcionario funcionario;
    /**
     * Expediente
     */
    private Expediente expediente;
    private AvisoDetencion avisoDetencion;
    private SolicitudDefensor solicitudDefensor;

    // Constructors

    /** default constructor */
    public AvisoDesignacion() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionario", nullable = true)
    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Expediente_id")
    public Expediente getExpediente() {
        return this.expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AvisoDetencion_id")
    public AvisoDetencion getAvisoDetencion() {
        return this.avisoDetencion;
    }

    public void setAvisoDetencion(AvisoDetencion avisoDetencion) {
        this.avisoDetencion = avisoDetencion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SolicitudDefensor_id", nullable = true)
    public SolicitudDefensor getSolicitudDefensor() {
        return this.solicitudDefensor;
    }

    public void setSolicitudDefensor(SolicitudDefensor solicitudDefensor) {
        this.solicitudDefensor = solicitudDefensor;
    }

}