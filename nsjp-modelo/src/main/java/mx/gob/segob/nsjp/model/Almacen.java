package mx.gob.segob.nsjp.model;

import java.util.Date;
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
import javax.persistence.Transient;

/**
 * Almacen entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Almacen")
public class Almacen implements java.io.Serializable {

    // Fields

    private Long identificadorAlmacen;
    private Boolean estatusActivo;
    private Set<Objeto> objetos = new HashSet<Objeto>(0);
    private String nombreAlmacen;
    private String descripcion;
    /**
     * Cuando el almacen resguarda evidencias: Atributo que indica si se trata
     * de un almacén fijo o virtual. Cuando el almacen resguarda expedientes: Es
     * virtual cuando el almacen e temporal, en otro caso es fijo.<br>
     * real = fijo<br>
     * virtual = temporal
     */
    private Boolean esVirtual = Boolean.FALSE;
    private Date fechaAlta;
    private Domicilio domicilio;
    private Funcionario funcionarioAlta;
    private Funcionario funcionarioAutoriza;
    /**
     * @deprecated
     */
    private NumeroExpediente numeroExpediente;
    /**
     * Para saber si es un almacen de evidencias o de expedientes.
     */
    private Boolean resguardaEvidencias;

    /**
     * Para saber de que expediente funciona como almacen virtual.
     */
    private Expediente expediente;

    private Set<NumeroExpediente> expedientesResguardados = new HashSet<NumeroExpediente>(
            0);
    // Constructors

    /** default constructor */
    public Almacen() {
    }

    /** minimal constructor */
    public Almacen(Long iidentificadorAlmacen) {
        this.identificadorAlmacen = iidentificadorAlmacen;
    }

    /** full constructor */
    public Almacen(Long iidentificadorAlmacen, Boolean bestatusActivo,
            Set<Objeto> objetos) {
        this.identificadorAlmacen = iidentificadorAlmacen;
        this.estatusActivo = bestatusActivo;
        this.objetos = objetos;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iIdentificadorAlmacen", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getIdentificadorAlmacen() {
        return this.identificadorAlmacen;
    }

    public void setIdentificadorAlmacen(Long iidentificadorAlmacen) {
        this.identificadorAlmacen = iidentificadorAlmacen;
    }

    @Column(name = "bEstatusActivo", precision = 1, scale = 0)
    public Boolean getEstatusActivo() {
        return this.estatusActivo;
    }

    public void setEstatusActivo(Boolean bestatusActivo) {
        this.estatusActivo = bestatusActivo;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "almacen")
    public Set<Objeto> getObjetos() {
        return this.objetos;
    }

    public void setObjetos(Set<Objeto> objetos) {
        this.objetos = objetos;
    }

    @Column(name = "cNombreAlmacen", length = 20, nullable = true)
    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    @Column(name = "cDescripcion", length = 100, nullable = true)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "Domicilio_id", nullable = true)
    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @Column(name = "dFechaAlta", length = 23)
    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionario_alta", nullable = true)
    public Funcionario getFuncionarioAlta() {
        return funcionarioAlta;
    }

    public void setFuncionarioAlta(Funcionario funcionarioAlta) {
        this.funcionarioAlta = funcionarioAlta;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionario_autoriza", nullable = true)
    public Funcionario getFuncionarioAutoriza() {
        return funcionarioAutoriza;
    }

    public void setFuncionarioAutoriza(Funcionario funcionarioAutoriza) {
        this.funcionarioAutoriza = funcionarioAutoriza;
    }

    @Column(name = "bEsVirtual", precision = 1, scale = 0)
    public Boolean getEsVirtual() {
        return esVirtual;
    }

    public void setEsVirtual(Boolean esVirtual) {
        this.esVirtual = esVirtual;
    }

    @Transient
    public NumeroExpediente getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    /**
     * Método de acceso al campo resguardaEvidencias.
     * 
     * @return El valor del campo resguardaEvidencias
     */
    @Column(name = "bResguardaEvidencias", precision = 1, scale = 0)
    public Boolean getResguardaEvidencias() {
        return resguardaEvidencias;
    }

    /**
     * Asigna el valor al campo resguardaEvidencias.
     * 
     * @param resguardaEvidencias
     *            el valor resguardaEvidencias a asignar
     */
    public void setResguardaEvidencias(Boolean resguardaEvidencias) {
        this.resguardaEvidencias = resguardaEvidencias;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AlmacenVirtualExpediente_id", nullable = true)
    public Expediente getExpediente() {
        return this.expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "almacen")
    public Set<NumeroExpediente> getExpedientesResguardados() {
        return this.expedientesResguardados;
    }

    public void setExpedientesResguardados(
            Set<NumeroExpediente> numeroExpedientes) {
        this.expedientesResguardados = numeroExpedientes;
    }

}