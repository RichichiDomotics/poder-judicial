package mx.gob.segob.nsjp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;

/**
 * NumeroExpediente<br>
 * La combinación ExpedienteId y Area existe una sola vez por
 * <code>Expediente</code>.<br>
 * La combinación Area y Funcionario existe <i>n</i> veces para el
 * <code>Expediente</code>, donde <i>n</i> es el número de areas en las que
 * opera (tiene permisos) el Funcionario.<br>
 * 
 * @author vaguirre
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "NumeroExpediente")
public class NumeroExpediente implements java.io.Serializable {

    // Fields

    private Long numeroExpedienteId;
    private Funcionario funcionario;
    private Expediente expediente;
    /**
     * Desde la pantalla se debe <b>asignar el Area</b>, no se puede asumir que
     * usa el area a la que pertenece el usuario y que se cargo al momento del
     * login.
     */
    private JerarquiaOrganizacional jerarquiaOrganizacional;
    private String numeroExpediente;
    private Date fechaApertura;
    private Boolean esPar;
    private Set<Solicitud> solicituds = new HashSet<Solicitud>(0);
    private Set<Audiencia> audiencias = new HashSet<Audiencia>(0);
    private Valor etapa;
    private List<Convenio> convenios=new ArrayList<Convenio>();
    /*
     * Usado (en PJ) para la relación de Causa-TOCA, donde la Causa es el
     * <code>NumeroExpediente</code> padre y la TOCA son los hijos.
     */
    private NumeroExpediente numeroExpedientePadre;

    /**
     * Usado en PJ.
     * 
     * @see numeroExpedientePadre
     */
    private Set<NumeroExpediente> numeroExpedientes = new HashSet<NumeroExpediente>(
            0);
    private Set<Medida> medidaCautelars = new HashSet<Medida>(0);

    /**
     * @see mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente
     */
    private Valor estatus = new Valor(EstatusExpediente.ABIERTO.getValorId());
    private Valor tipoExpediente;

    private Almacen almacen;
    
    private Sentencia sentencia;
   
    private DefensaInvolucrado defensaInvolucrado;
    
    private List<RelNumExpedienteAuditoria> numerosAuditorias;
    
    private Set<PermisoExpediente> permisoExpedientes = new HashSet<PermisoExpediente>();
    private Set<RegistroBitacora> registroBitacoras = new HashSet<RegistroBitacora>(
            0);
    // Constructors

    /** default constructor */
    public NumeroExpediente() {
    }
    /**
     * 
     * @param id
     */
    public NumeroExpediente(Long id) {
        this.numeroExpedienteId = id;
    }

    /**
     * 
     * @param id
     * @param cnumeroExpediente
     * @param dfechaApertura
     */
    public NumeroExpediente(Long id, String cnumeroExpediente,
            Date dfechaApertura) {
        this.numeroExpedienteId = id;
        this.numeroExpediente = cnumeroExpediente;
        this.fechaApertura = dfechaApertura;
    }

    /**
     * 
     * @param id
     * @param cnumeroExpediente
     * @param dfechaApertura
     */
    public NumeroExpediente(Long id, String cnumeroExpediente,
            Date dfechaApertura, Long cveFuncionario) {
        this.numeroExpedienteId = id;
        this.numeroExpediente = cnumeroExpediente;
        this.fechaApertura = dfechaApertura;
        this.funcionario = new Funcionario(cveFuncionario);
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NumeroExpediente_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getNumeroExpedienteId() {
        return this.numeroExpedienteId;
    }

    public void setNumeroExpedienteId(Long numeroExpedienteId) {
        this.numeroExpedienteId = numeroExpedienteId;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JerarquiaOrganizacional_id", nullable = false)
    public JerarquiaOrganizacional getJerarquiaOrganizacional() {
        return this.jerarquiaOrganizacional;
    }

    public void setJerarquiaOrganizacional(
            JerarquiaOrganizacional jerarquiaOrganizacional) {
        this.jerarquiaOrganizacional = jerarquiaOrganizacional;
    }

    @Column(name = "cNumeroExpediente", nullable = false, length = 23)
    public String getNumeroExpediente() {
        return this.numeroExpediente;
    }

    public void setNumeroExpediente(String cnumeroExpediente) {
        this.numeroExpediente = cnumeroExpediente;
    }

    @Column(name = "dFechaApertura", nullable = false, length = 23)
    public Date getFechaApertura() {
        return this.fechaApertura;
    }

    public void setFechaApertura(Date dfechaApertura) {
        this.fechaApertura = dfechaApertura;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "numeroExpediente")
    public Set<Solicitud> getSolicituds() {
        return this.solicituds;
    }

    public void setSolicituds(Set<Solicitud> solicituds) {
        this.solicituds = solicituds;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "numeroExpediente")
    public Set<Audiencia> getAudiencias() {
        return this.audiencias;
    }

    public void setAudiencias(Set<Audiencia> audiencias) {
        this.audiencias = audiencias;
    }
    /**
     * Método de acceso al campo numeroExpedientePadre.
     * 
     * @return El valor del campo numeroExpedientePadre
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumExpedientePadre_id")
    public NumeroExpediente getNumeroExpedientePadre() {
        return numeroExpedientePadre;
    }
    /**
     * Asigna el valor al campo numeroExpedientePadre.
     * 
     * @param numeroExpedientePadre
     *            el valor numeroExpedientePadre a asignar
     */
    public void setNumeroExpedientePadre(NumeroExpediente numeroExpedientePadre) {
        this.numeroExpedientePadre = numeroExpedientePadre;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "numeroExpedientePadre")
    public Set<NumeroExpediente> getNumeroExpedientes() {
        return this.numeroExpedientes;
    }

    public void setNumeroExpedientes(Set<NumeroExpediente> numeroExpedientes) {
        this.numeroExpedientes = numeroExpedientes;
    }
    /**
     * Método de acceso al campo esPar.
     * 
     * @return El valor del campo esPar
     */
    @Column(name = "bEsPar", precision = 1, scale = 0)
    public Boolean getEsPar() {
        return esPar;
    }
    /**
     * Asigna el valor al campo esPar.
     * 
     * @param esPar
     *            el valor esPar a asignar
     */
    public void setEsPar(Boolean esPar) {
        this.esPar = esPar;
    }
    /**
     * Método de acceso al campo etapa.
     * 
     * @return El valor del campo etapa
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Etapa_val")
    public Valor getEtapa() {
        return etapa;
    }

    /**
     * Asigna el valor al campo etapa.
     * 
     * @param etapa
     *            el valor etapa a asignar
     */
    public void setEtapa(Valor etapa) {
        this.etapa = etapa;
    }
    /**
     * Método de acceso al campo medidaCautelars.
     * 
     * @return El valor del campo medidaCautelars
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "numeroExpediente")
    public Set<Medida> getMedidaCautelars() {
        return medidaCautelars;
    }
    /**
     * Asigna el valor al campo medidaCautelars.
     * 
     * @param medidaCautelars
     *            el valor medidaCautelars a asignar
     */
    public void setMedidaCautelars(Set<Medida> medidaCautelars) {
        this.medidaCautelars = medidaCautelars;
    }
    /**
     * Método de acceso al campo estatus.
     * 
     * @return El valor del campo estatus
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Estatus_val")
    public Valor getEstatus() {
        return estatus;
    }
    /**
     * Asigna el valor al campo estatus.
     * 
     * @param estatus
     *            el valor estatus a asignar
     */
    public void setEstatus(Valor estatus) {
        this.estatus = estatus;
    }
    /**
     * Método de acceso al campo tipoExpediente.
     * 
     * @return El valor del campo tipoExpediente
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoExpediente_val")
    public Valor getTipoExpediente() {
        return tipoExpediente;
    }
    /**
     * Asigna el valor al campo tipoExpediente.
     * 
     * @param tipoExpediente
     *            el valor tipoExpediente a asignar
     */
    public void setTipoExpediente(Valor tipoExpediente) {
        this.tipoExpediente = tipoExpediente;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iIdentificadorAlmacen")
    public Almacen getAlmacen() {
        return this.almacen;
    }
	/**
	 * @param acuerdoRestaurativos the acuerdoRestaurativos to set
	 */
	public void setConvenios(List<Convenio> acuerdoRestaurativos) {
		this.convenios = acuerdoRestaurativos;
	}
	/**
	 * @return the acuerdoRestaurativos
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "numeroExpediente")
	public List<Convenio> getConvenios() {
		return convenios;
	}

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }
    /**
     * Método de acceso al campo sentencia.
     * @return El valor del campo sentencia
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "numeroExpediente")
    public Sentencia getSentencia() {
        return sentencia;
    }
    /**
     * Asigna el valor al campo sentencia.
     * @param sentencia el valor sentencia a asignar
     */
    public void setSentencia(Sentencia sentencia) {
        this.sentencia = sentencia;
    }

    /**
     * Método de acceso al campo defensaInvolucrado.
     * @return El valor del campo defensaInvolucrado
     */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "numeroExpediente")
    public DefensaInvolucrado getDefensaInvolucrado() {
        return defensaInvolucrado;
    }
    /**
     * Asigna el valor al campo defensaInvolucrado.
     * @param defensaInvolucrado el valor defensaInvolucrado a asignar
     */
    public void setDefensaInvolucrado(DefensaInvolucrado defensaInvolucrado) {
        this.defensaInvolucrado = defensaInvolucrado;
    }
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "numeroExpediente")
	public List<RelNumExpedienteAuditoria> getNumerosAuditorias() {
		return numerosAuditorias;
	}
	public void setNumerosAuditorias(
			List<RelNumExpedienteAuditoria> numerosAuditorias) {
		this.numerosAuditorias = numerosAuditorias;
	}
	/**
	 * @return the permisoExpedientes
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "numeroExpediente")
	public Set<PermisoExpediente> getPermisoExpedientes() {
		return permisoExpedientes;
	}
	/**
	 * @param permisoExpedientes the permisoExpedientes to set
	 */
	public void setPermisoExpedientes(Set<PermisoExpediente> permisoExpedientes) {
		this.permisoExpedientes = permisoExpedientes;
	}
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "numeroExpediente")
    public Set<RegistroBitacora> getRegistroBitacoras() {
        return this.registroBitacoras;
    }

    public void setRegistroBitacoras(Set<RegistroBitacora> registroBitacoras) {
        this.registroBitacoras = registroBitacoras;
    }	
	
}