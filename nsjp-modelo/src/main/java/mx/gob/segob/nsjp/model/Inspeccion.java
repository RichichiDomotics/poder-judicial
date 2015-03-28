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
 * Inspeccion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Inspeccion")
public class Inspeccion implements java.io.Serializable {

    // Fields

    private Long inspeccionId;
    private NumeroExpediente numeroExpediente;
    private Funcionario funcionarioInspeccionado;
    private Funcionario funcionarioRegistra;
    private Valor estatus;
    private String folioInspeccion;
    private Date fechaRegistro;
    private String descripcion;
    private String motivo;
    private MultaSancion multaSancion = null;

    // Constructors

    /** default constructor */
    public Inspeccion() {
    }
    public Inspeccion(Long id) {
        this.inspeccionId = id;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Inspeccion_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getInspeccionId() {
        return this.inspeccionId;
    }

    public void setInspeccionId(Long inspeccionId) {
        this.inspeccionId = inspeccionId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumeroExpediente_id")
    public NumeroExpediente getNumeroExpediente() {
        return this.numeroExpediente;
    }

    public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionarioInspeccionado", nullable = false)
    public Funcionario getFuncionarioInspeccionado() {
        return this.funcionarioInspeccionado;
    }

    public void setFuncionarioInspeccionado(
            Funcionario funcionarioByIclaveFuncionarioInspeccionado) {
        this.funcionarioInspeccionado = funcionarioByIclaveFuncionarioInspeccionado;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionarioRegistra", nullable = false)
    public Funcionario getFuncionarioRegistra() {
        return this.funcionarioRegistra;
    }

    public void setFuncionarioRegistra(
            Funcionario funcionarioByIclaveFuncionarioRegistra) {
        this.funcionarioRegistra = funcionarioByIclaveFuncionarioRegistra;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Estatus_val", nullable = false)
    public Valor getEstatus() {
        return this.estatus;
    }

    public void setEstatus(Valor valor) {
        this.estatus = valor;
    }

    @Column(name = "cFolioInspeccion", nullable = false, length = 15)
    public String getFolioInspeccion() {
        return this.folioInspeccion;
    }

    public void setFolioInspeccion(String cfolioInspeccion) {
        this.folioInspeccion = cfolioInspeccion;
    }

    @Column(name = "dFechaRegistro", nullable = false, length = 23)
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date dfechaRegistro) {
        this.fechaRegistro = dfechaRegistro;
    }

    @Column(name = "cDescripcion")
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String cdescripcion) {
        this.descripcion = cdescripcion;
    }

    @Column(name = "cMotivo", nullable = false, length = 50)
    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String cmotivo) {
        this.motivo = cmotivo;
    }

    /**
     * Método de acceso al campo multaSancion.
     * 
     * @return El valor del campo multaSancion
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "inspeccion")
    public MultaSancion getMultaSancion() {
        return multaSancion;
    }

    /**
     * Asigna el valor al campo multaSancion.
     * 
     * @param multaSancion
     *            el valor multaSancion a asignar
     */
    public void setMultaSancion(MultaSancion multaSancion) {
        this.multaSancion = multaSancion;
    }

}