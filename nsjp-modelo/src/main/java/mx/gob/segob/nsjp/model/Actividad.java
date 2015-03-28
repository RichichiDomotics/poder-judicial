package mx.gob.segob.nsjp.model;

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
 * Actividad entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Actividad")
public class Actividad implements java.io.Serializable {

    // Fields

    private Long actividadId;
    private Funcionario funcionario;
    private Expediente expediente;
    private Documento documento;
    private Valor tipoActividad;
    private java.util.Date fechaVencimiento;
    private java.util.Date fechaCreacion;

    // Constructors

    /** default constructor */
    public Actividad() {
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Actividad_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getActividadId() {
        return this.actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionario", nullable = false)
    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Expediente_id", nullable = false)
    public Expediente getExpediente() {
        return this.expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Documento_id")
    public Documento getDocumento() {
        return this.documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoActividad_val", nullable = false)
    public Valor getTipoActividad() {
        return this.tipoActividad;
    }

    public void setTipoActividad(Valor valor) {
        this.tipoActividad = valor;
    }

    @Column(name = "dFechaVencimiento", length = 23)
    public java.util.Date getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public void setFechaVencimiento(java.util.Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Column(name = "dFechaCreacion", nullable = false, length = 23)
    public java.util.Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(java.util.Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
