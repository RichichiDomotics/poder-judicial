package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Detencion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Detencion")
public class Detencion implements java.io.Serializable {

    // Fields

    private Long detencionId;
    private Funcionario funcionarioByFuncionarioTraslada;
    private Lugar lugar;
    /**
     * El involucrado puede ser nulo para cuando existe un aviso de detención,
     * para este caso si es necesario saber el nombre del deenido se consulta
     * <code>avisoDetencion.detenido</code>
     */
    private Involucrado involucrado;
    private Funcionario funcionarioByFuncionarioDetiene;
    private java.util.Date fechaInicioDetencion;
    private java.util.Date fechaFinDetencion;
    private String motivoDetencion;
    private java.util.Date fechaRecepcionDetencion;
    private String observaciones;
    private String lugarDetencionProvisional;
    private AvisoDetencion avisoDetencion;
    private Set<Pertenencia> pertenencias = new HashSet<Pertenencia>(0);

    // Constructors

    /** default constructor */
    public Detencion() {
    }
    /** minimal constructor */
    public Detencion(Long detencionId) {
        this.detencionId = detencionId;
    }
    /** minimal constructor */
    public Detencion(Long detencionId, Lugar lugar, Involucrado involucrado,
            Funcionario funcionarioByFuncionarioDetiene) {
        this.detencionId = detencionId;
        this.lugar = lugar;
        this.involucrado = involucrado;
        this.funcionarioByFuncionarioDetiene = funcionarioByFuncionarioDetiene;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Detencion_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getDetencionId() {
        return this.detencionId;
    }

    public void setDetencionId(Long detencionId) {
        this.detencionId = detencionId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Funcionario_traslada")
    public Funcionario getFuncionarioByFuncionarioTraslada() {
        return this.funcionarioByFuncionarioTraslada;
    }

    public void setFuncionarioByFuncionarioTraslada(
            Funcionario funcionarioByFuncionarioTraslada) {
        this.funcionarioByFuncionarioTraslada = funcionarioByFuncionarioTraslada;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Lugar_id", nullable = true)
    public Lugar getLugar() {
        return this.lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Involucrado_id", nullable = true)
    public Involucrado getInvolucrado() {
        return this.involucrado;
    }

    public void setInvolucrado(Involucrado involucrado) {
        this.involucrado = involucrado;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Funcionario_detiene", nullable = true)
    public Funcionario getFuncionarioByFuncionarioDetiene() {
        return this.funcionarioByFuncionarioDetiene;
    }

    public void setFuncionarioByFuncionarioDetiene(
            Funcionario funcionarioByFuncionarioDetiene) {
        this.funcionarioByFuncionarioDetiene = funcionarioByFuncionarioDetiene;
    }

    @Column(name = "dFechaInicioDetencion", length = 23)
    public java.util.Date getFechaInicioDetencion() {
        return this.fechaInicioDetencion;
    }

    public void setFechaInicioDetencion(java.util.Date fechaInicioDetencion) {
        this.fechaInicioDetencion = fechaInicioDetencion;
    }

    @Column(name = "dFechaFinDetencion", length = 23)
    public java.util.Date getFechaFinDetencion() {
        return this.fechaFinDetencion;
    }

    public void setFechaFinDetencion(java.util.Date fechaFinDetencion) {
        this.fechaFinDetencion = fechaFinDetencion;
    }

    @Column(name = "cMotivoDetencion")
    public String getMotivoDetencion() {
        return this.motivoDetencion;
    }

    public void setMotivoDetencion(String motivoDetencion) {
        this.motivoDetencion = motivoDetencion;
    }

    @Column(name = "dFechaRecepcionDetencion", length = 23)
    public java.util.Date getFechaRecepcionDetencion() {
        return this.fechaRecepcionDetencion;
    }

    public void setFechaRecepcionDetencion(
            java.util.Date fechaRecepcionDetencion) {
        this.fechaRecepcionDetencion = fechaRecepcionDetencion;
    }

    @Column(name = "cObservaciones")
    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Método de acceso al campo lugarDetencionProvisional.
     * 
     * @return El valor del campo lugarDetencionProvisional
     */
    @Column(name = "cLugarDetencionProvisional", length = 150)
    public String getLugarDetencionProvisional() {
        return lugarDetencionProvisional;
    }

    /**
     * Asigna el valor al campo lugarDetencionProvisional.
     * 
     * @param lugarDetencionProvisional
     *            el valor lugarDetencionProvisional a asignar
     */
    public void setLugarDetencionProvisional(String lugarDetencionProvisional) {
        this.lugarDetencionProvisional = lugarDetencionProvisional;
    }

    /**
     * Método de acceso al campo avisoDetencion.
     * 
     * @return El valor del campo avisoDetencion
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detencion")
    public AvisoDetencion getAvisoDetencion() {
        return avisoDetencion;
    }

    /**
     * Asigna el valor al campo avisoDetencion.
     * 
     * @param avisoDetencion
     *            el valor avisoDetencion a asignar
     */
    public void setAvisoDetencion(AvisoDetencion avisoDetencion) {
        this.avisoDetencion = avisoDetencion;
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detencion")
    public Set<Pertenencia> getPertenencias() {
        return this.pertenencias;
    }

    public void setPertenencias(Set<Pertenencia> pertenencias) {
        this.pertenencias = pertenencias;
    }
}
