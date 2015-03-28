package mx.gob.segob.nsjp.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * DefensaInvolucrado entity. @author MyEclipse Persistence Tools<br>
 * Entity usado para relacionar al probable responsable principal al número de expediente.
 */
@Entity
@Table(name = "DefensaInvolucrado")
public class DefensaInvolucrado implements java.io.Serializable {

    // Fields

    private DefensaInvolucradoId id;
    private NumeroExpediente numeroExpediente;
    private Involucrado involucrado;
    /**
     * Involucrado recibido desde PG.
     */
    private Involucrado involucradoPg;

    // Constructors

    /** default constructor */
    public DefensaInvolucrado() {
    }

    /** minimal constructor */
    public DefensaInvolucrado(DefensaInvolucradoId id,
            NumeroExpediente numeroExpediente, Involucrado involucrado) {
        this.id = id;
        this.numeroExpediente = numeroExpediente;
        this.involucrado = involucrado;
    }

    // Property accessors
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "numeroExpedienteId", column = @Column(name = "NumeroExpediente_id", nullable = false, precision = 18, scale = 0)),
            @AttributeOverride(name = "involucradoId", column = @Column(name = "Involucrado_id", nullable = false, precision = 18, scale = 0))})
    public DefensaInvolucradoId getId() {
        return this.id;
    }

    public void setId(DefensaInvolucradoId id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumeroExpediente_id", nullable = false, insertable = false, updatable = false)
    public NumeroExpediente getNumeroExpediente() {
        return this.numeroExpediente;
    }

    public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Involucrado_id", nullable = false, insertable = false, updatable = false)
    public Involucrado getInvolucrado() {
        return this.involucrado;
    }

    public void setInvolucrado(Involucrado involucrado) {
        this.involucrado = involucrado;
    }

    /**
     * Método de acceso al campo involucradoPg.
     * @return El valor del campo involucradoPg
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InvolucradoPG_id")
    public Involucrado getInvolucradoPg() {
        return involucradoPg;
    }

    /**
     * Asigna el valor al campo involucradoPg.
     * @param involucradoPg el valor involucradoPg a asignar
     */
    public void setInvolucradoPg(Involucrado involucradoPg) {
        this.involucradoPg = involucradoPg;
    }

}