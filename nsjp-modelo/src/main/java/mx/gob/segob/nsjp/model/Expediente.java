package mx.gob.segob.nsjp.model;

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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;

/**
 * Expediente entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Expediente")
@Inheritance(strategy = InheritanceType.JOINED)
public class Expediente implements java.io.Serializable {

    // Fields
    // TODO VAP revisar lo dela bitacora
    private Long expedienteId;
    /**
     * Propiedad calificada como <code>@Transient</code> por lo que se
     * recomienda usar la entidad <code>NumeroExpediente</code>.
     */
    private String numeroExpediente;

    /**
     * Propiedad calificada como <code>@Transient</code> por lo que se
     * recomienda usar la entidad <code>NumeroExpediente</code>.<br>
     * Usado para la consultas
     */
    private Long numeroExpedienteId;

    private Caso caso;
    private Hecho hecho = null;

    private java.util.Date fechaCreacion;
    private java.util.Date fechaCierre;

    private Valor estatus= new Valor(EstatusExpediente.ABIERTO.getValorId());
    private Valor origen;
    private List<Documento> documentos;
    private List<SeguimientoLI> seguimientos;

    /**
     * Narraiva.
     */
    private String descNarrativa;
    private Set<Elemento> elementos = new HashSet<Elemento>(0);
    private Set<Actividad> actividads = new HashSet<Actividad>(0);
    private Set<Delito> delitos = new HashSet<Delito>(0);

    private Set<NumeroExpediente> numeroExpedientes = new HashSet<NumeroExpediente>(
            0);
    private Set<NotaExpediente> notas = new HashSet<NotaExpediente>(0);
    private ConfInstitucion pertenceConfInst;
    private Set<AvisoDetencion> avisoDetencion;
    private Set<AvisoDesignacion> avisoDesignaciones;
    private SeguimientoLI seguimientoLI;
    private CatDiscriminante discriminante;
    //Es utilizado para indicar si el Expediente fue replicado en las instituciones
    private Boolean esReplicado;  

    // Constructors

    /** default constructor */
    public Expediente() {
    }

    /** minimal constructor */
    public Expediente(Long expedienteId) {
        this.expedienteId = expedienteId;
    }

    /** minimal constructor */
    public Expediente(Long expedienteId, String numeroExpediente) {
        this.expedienteId = expedienteId;
        this.numeroExpediente = numeroExpediente;
    }
    /** minimal constructor */
    public Expediente(Long expedienteId, String numeroExpediente, Long noExpId) {
        this.expedienteId = expedienteId;
        this.numeroExpediente = numeroExpediente;
        this.numeroExpedienteId = noExpId;
    }
    
    /**
	 * @param expedienteId
	 * @param numeroExpediente
	 * @param caso
	 */
	public Expediente(Long expedienteId, String numeroExpediente, Caso caso) {
		super();
		this.expedienteId = expedienteId;
		this.numeroExpediente = numeroExpediente;
		this.caso = caso;
	}
	
	

	/**
	 * @param expedienteId
	 * @param numeroExpediente
	 * @param numeroExpedienteId
	 * @param caso
	 */
	public Expediente(Long expedienteId, String numeroExpediente,
			Long numeroExpedienteId, Caso caso) {
		super();
		this.expedienteId = expedienteId;
		this.numeroExpediente = numeroExpediente;
		this.numeroExpedienteId = numeroExpedienteId;
		this.caso = caso;
	}

	// Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Expediente_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getExpedienteId() {
        return this.expedienteId;
    }

    public void setExpedienteId(Long expedienteId) {
        this.expedienteId = expedienteId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Caso_id")
    public Caso getCaso() {
        return this.caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    /**
     * Usado como Transient
     */
    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    @Column(name = "dFechaCreacion", length = 23)
    public java.util.Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(java.util.Date fechaApertura) {
        this.fechaCreacion = fechaApertura;
    }

    @Column(name = "dFechaCierre", length = 23)
    public java.util.Date getFechaCierre() {
        return this.fechaCierre;
    }

    public void setFechaCierre(java.util.Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "expediente")
    public Set<Elemento> getElementos() {
        return this.elementos;
    }

    public void setElementos(Set<Elemento> elementos) {
        this.elementos = elementos;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "expediente")
    public Set<Actividad> getActividads() {
        return this.actividads;
    }

    public void setActividads(Set<Actividad> actividads) {
        this.actividads = actividads;
    }

    /**
     * Método de acceso al campo descNarrativa.
     * 
     * @return El valor del campo descNarrativa
     */
    @Column(name = "cDescNarrativa")
    public String getDescNarrativa() {
        return descNarrativa;
    }

    /**
     * Asigna el valor al campo descNarrativa.
     * 
     * @param descNarrativa
     *            el valor descNarrativa a asignar
     */
    public void setDescNarrativa(String descNarrativa) {
        this.descNarrativa = descNarrativa;
    }
    // @Where(clause=" bEsProbable =  1")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "expediente")
    public Set<Delito> getDelitos() {
        return this.delitos;
    }

    public void setDelitos(Set<Delito> delitos) {
        this.delitos = delitos;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Estatus_val")
    public Valor getEstatus() {
        return estatus;
    }
    
    public void setEstatus(Valor estatus) {
        this.estatus = estatus;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "expediente")
    public Set<NumeroExpediente> getNumeroExpedientes() {
        return this.numeroExpedientes;
    }

    public void setNumeroExpedientes(Set<NumeroExpediente> numeroExpedientes) {
        this.numeroExpedientes = numeroExpedientes;
    }

    /**
     * Método de acceso al campo hecho.
     * 
     * @return El valor del campo hecho
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "expediente")
    public Hecho getHecho() {
        return hecho;
    }

    /**
     * Asigna el valor al campo hecho.
     * 
     * @param hecho
     *            el valor hecho a asignar
     */
    public void setHecho(Hecho hecho) {
        this.hecho = hecho;
    }

    /**
     * Propiedad calificada como <code>@Transient</code> por lo que se
     * recomienda usar la entidad <code>NumeroExpediente</code> .
     */
    @Transient
    public String getNumeroExpediente() {
        return this.numeroExpediente;
    }
    /**
     * Propiedad calificada como <code>@Transient</code> por lo que se
     * recomienda usar la entidad <code>NumeroExpediente</code> .
     * 
     * @return El valor del campo numeroExpedienteId
     */
    @Transient
    public Long getNumeroExpedienteId() {
        return numeroExpedienteId;
    }

    /**
     * Asigna el valor al campo documentos.
     * 
     * @param documentos
     *            el valor documentos a asignar
     */
    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    /**
     * Método de acceso al campo documentos.
     * 
     * @return El valor del campo documentos
     */
    @Transient
    public List<Documento> getDocumentos() {
        return documentos;
    }

    /**
     * Método de acceso al campo origen.
     * 
     * @return El valor del campo origen
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origen_val", nullable = true)
    public Valor getOrigen() {
        return origen;
    }

    /**
     * Asigna el valor al campo origen.
     * 
     * @param origen
     *            el valor origen a asignar
     */
    public void setOrigen(Valor origen) {
        this.origen = origen;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "expediente")
    public Set<NotaExpediente> getNotas() {
        return notas;
    }

    public void setNotas(Set<NotaExpediente> notas) {
        this.notas = notas;
    }
	 /**
     * Método de acceso al campo pertenceConfInst.
     * 
     * @return El valor del campo pertenceConfInst
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ConfInstitucion_id")
    public ConfInstitucion getPertenceConfInst() {
        return pertenceConfInst;
    }

    /**
     * Asigna el valor al campo pertenceConfInst.
     * 
     * @param pertenceConfInst
     *            el valor pertenceConfInst a asignar
     */
    public void setPertenceConfInst(ConfInstitucion pertenceConfInst) {
        this.pertenceConfInst = pertenceConfInst;
    }

    /**
     * Método de acceso al campo avisoDetencion.
     * @return El valor del campo avisoDetencion
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "expediente")
    public  Set<AvisoDetencion>  getAvisoDetencion() {
        return avisoDetencion;
    }

    /**
     * Asigna el valor al campo avisoDetencion.
     * @param avisoDetencion el valor avisoDetencion a asignar
     */
    public void setAvisoDetencion( Set<AvisoDetencion>  avisoDetencion) {
        this.avisoDetencion = avisoDetencion;
    }

    /**
     * Método de acceso al campo avisoDesignaciones.
     * @return El valor del campo avisoDesignaciones
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "expediente")
    public Set<AvisoDesignacion> getAvisoDesignaciones() {
        return avisoDesignaciones;
    }

    /**
     * Asigna el valor al campo avisoDesignaciones.
     * @param avisoDesignaciones el valor avisoDesignaciones a asignar
     */
    public void setAvisoDesignaciones(Set<AvisoDesignacion> avisoDesignacion) {
        this.avisoDesignaciones = avisoDesignacion;
    }
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "expediente")
	public SeguimientoLI getSeguimientoLI() {
		return seguimientoLI;
	}
	public void setSeguimientoLI(SeguimientoLI seguimientoLI) {
		this.seguimientoLI = seguimientoLI;
	}

	/**
	 * Método de acceso al campo seguimientos.
	 * @return El valor del campo seguimientos
	 */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "expediente")
	public List<SeguimientoLI> getSeguimientos() {
		return seguimientos;
	}

	/**
	 * Asigna el valor al campo seguimientos.
	 * @param seguimientos el valor seguimientos a asignar
	 */
	public void setSeguimientos(List<SeguimientoLI> seguimientos) {
		this.seguimientos = seguimientos;
	}

	/**
	 * @return the discriminante
	 */
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catDiscriminante_id")
	public CatDiscriminante getDiscriminante() {
		return discriminante;
	}

	/**
	 * @param discriminante the discriminante to set
	 */
	public void setDiscriminante(CatDiscriminante discriminante) {
		this.discriminante = discriminante;
	}

	@Column(name = "bEsReplicado", precision = 1, scale = 0)
	public Boolean getEsReplicado() {
		return esReplicado;
	}

	public void setEsReplicado(Boolean esReplicado) {
		this.esReplicado = esReplicado;
	}
}
