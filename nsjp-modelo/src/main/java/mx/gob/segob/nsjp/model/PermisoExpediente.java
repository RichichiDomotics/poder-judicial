package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PermisoExpediente entity. @author MyEclipse Persistence Tools <br>
 * Entity para asignar pemisos a un funcioario para el permiso de lectura o
 * escritura del expediente.
 */
@Entity
@Table(name = "PermisoExpediente")
public class PermisoExpediente implements java.io.Serializable {

    // Fields

    private PermisoExpedienteId id;
    private Funcionario funcionario;
    private NumeroExpediente numeroExpediente;
    private Date fechaLimite;
    private Boolean esEscritura;

    // Constructors

    /** default constructor */
    public PermisoExpediente() {
    }

    // Property accessors
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "numeroExpedienteId", column = @Column(name = "NumeroExpediente_id", nullable = false, precision = 18, scale = 0)),
            @AttributeOverride(name = "iclaveFuncionario", column = @Column(name = "iClaveFuncionario", nullable = false, precision = 18, scale = 0))})
    public PermisoExpedienteId getId() {
        return this.id;
    }

    public void setId(PermisoExpedienteId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionario", nullable = false, insertable = false, updatable = false)
    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumeroExpediente_id", nullable = false, insertable = false, updatable = false)
    public NumeroExpediente getNumeroExpediente() {
        return this.numeroExpediente;
    }

    public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    @Column(name = "dFechaLimite", nullable = false, length = 23)
    public Date getFechaLimite() {
        return this.fechaLimite;
    }

    public void setFechaLimite(Date dfechaLimite) {
        this.fechaLimite = dfechaLimite;
    }

    /**
     * Método de acceso al campo esEscritura.
     * 
     * @return El valor del campo esEscritura
     */
    @Column(name = "bEsEscritura", precision = 1, scale = 0)
    public Boolean getEsEscritura() {
        return esEscritura;
    }

    /**
     * Asigna el valor al campo esEscritura.
     * 
     * @param esEscritura
     *            el valor esEscritura a asignar
     */
    public void setEsEscritura(Boolean esEscritura) {
        this.esEscritura = esEscritura;
    }

}