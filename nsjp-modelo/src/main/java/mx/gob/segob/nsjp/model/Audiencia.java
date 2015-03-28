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
 * Audiencia entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Audiencia")
public class Audiencia implements java.io.Serializable {

    // Fields

    private Long audienciaId;
    /**
     * Debe soportar la referencia a <code>NumeroExpediente</code> como
     * <i>nulleable</i> para cuando hay más de un expediente ante la recepción
     * de una solicitud de audiencia entre institucines.
     */
    private NumeroExpediente numeroExpediente;
    private Valor tipo;
    private Short consecutivo;
    private Date fechaAudiencia;
    private Date fechaAsignacionSala;

    private Integer duracionEstimada;

    private Set<InvolucradoAudiencia> invlucradoAudiencias = new HashSet<InvolucradoAudiencia>(
            0);
    private SalaTemporal salaTemporal;
    private SalaAudiencia salaAudiencia;
    private Sala sala;
    private SolicitudAudiencia solicitud;
    private Set<FuncionarioAudiencia> funcionarioAudiencias = new HashSet<FuncionarioAudiencia>(
            0);
    private Set<AudienciaEvidencia> audienciaEvidencias = new HashSet<AudienciaEvidencia>(
            0);
    private Set<Resolutivo> resolutivos = new HashSet<Resolutivo>(0);

    private Valor estatus;
    private Boolean conResolutivos;

    private Date fechaHoraFin;
    private String situacionesEspeciales;
    private String folioAudiencia;
    private Set<SolicitudTrasladoImputado> solicitudesTraslados = new HashSet<SolicitudTrasladoImputado>(
            0);
    private Set<Notificacion> notificacions = new HashSet<Notificacion>();
    
    
    // Constructors

    /** default constructor */
    public Audiencia() {
    }

    /** minimal constructor */
    public Audiencia(Long audienciaI) {
        this.audienciaId = audienciaI;
    }

    /**
     * @param audienciaId
     * @param fechaAudiencia
     * @param duracionEstimada
     */
    public Audiencia(Long audienciaId, Date fechaAudiencia,
            Integer duracionEstimada) {
        super();
        this.audienciaId = audienciaId;
        this.fechaAudiencia = fechaAudiencia;
        this.duracionEstimada = duracionEstimada;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Audiencia_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getAudienciaId() {
        return this.audienciaId;
    }

    public void setAudienciaId(Long audienciaId) {
        this.audienciaId = audienciaId;
    }

    @Transient
    public Expediente getExpediente() {
        if (getNumeroExpediente() != null) {
            return this.getNumeroExpediente().getExpediente();
        }
        return null;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TipoAudiencia_val", nullable = false)
    public Valor getTipo() {
        return this.tipo;
    }

    public void setTipo(Valor valor) {
        this.tipo = valor;
    }

    @Column(name = "iConsecutivo", nullable = false, precision = 4, scale = 0)
    public Short getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(Short iconsecutivo) {
        this.consecutivo = iconsecutivo;
    }

    @Column(name = "dFechaAudiencia", length = 23)
    public Date getFechaAudiencia() {
        return this.fechaAudiencia;
    }

    public void setFechaAudiencia(Date dfechaAudiencia) {
        this.fechaAudiencia = dfechaAudiencia;
    }

    @Column(name = "dFechaAsignacionSala", length = 23)
    public Date getFechaAsignacionSala() {
        return this.fechaAsignacionSala;
    }

    public void setFechaAsignacionSala(Date dfechaAsignacionSala) {
        this.fechaAsignacionSala = dfechaAsignacionSala;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "audiencia")
    public Set<InvolucradoAudiencia> getInvlucradoAudiencias() {
        return invlucradoAudiencias;
    }

    public void setInvlucradoAudiencias(
            Set<InvolucradoAudiencia> invlucradoAudiencias) {
        this.invlucradoAudiencias = invlucradoAudiencias;
    }

    /**
     * Método de acceso al campo solicitud.
     * 
     * @return El valor del campo solicitud
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "audiencia")
    public SolicitudAudiencia getSolicitud() {
        return solicitud;
    }

    /**
     * Asigna el valor al campo solicitud.
     * 
     * @param solicitud
     *            el valor solicitud a asignar
     */
    public void setSolicitud(SolicitudAudiencia solicitud) {
        this.solicitud = solicitud;
    }

    /**
     * Método de acceso al campo juezAudiencias.
     * 
     * @return El valor del campo juezAudiencias
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "audiencia")
    public Set<FuncionarioAudiencia> getFuncionarioAudiencias() {
        return funcionarioAudiencias;
    }

    /**
     * Asigna el valor al campo juezAudiencias.
     * 
     * @param juezAudiencias
     *            el valor juezAudiencias a asignar
     */
    public void setFuncionarioAudiencias(
            Set<FuncionarioAudiencia> funcionarioAudiencias) {
        this.funcionarioAudiencias = funcionarioAudiencias;
    }

    /**
     * Método de acceso al campo salaTemporal.
     * 
     * @return El valor del campo salaTemporal
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SalaTemporal_id")
    public SalaTemporal getSalaTemporal() {
        return salaTemporal;
    }

    /**
     * Asigna el valor al campo salaTemporal.
     * 
     * @param salaTemporal
     *            el valor salaTemporal a asignar
     */
    public void setSalaTemporal(SalaTemporal salaTemporal) {
        this.salaTemporal = salaTemporal;
    }

    /**
     * Método de acceso al campo salaAudiencia.
     * 
     * @return El valor del campo salaAudiencia
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SalaAudiencia_id")
    public SalaAudiencia getSalaAudiencia() {
        return salaAudiencia;
    }

    /**
     * Asigna el valor al campo salaAudiencia.
     * 
     * @param salaAudiencia
     *            el valor salaAudiencia a asignar
     */

    public void setSalaAudiencia(SalaAudiencia salaAudiencia) {
        this.salaAudiencia = salaAudiencia;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sala_id")
    public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	/**
     * Método de acceso al campo duracionEstimada.
     * 
     * @return El valor del campo duracionEstimada
     */
    @Column(name = "iDuracionEstimada", precision = 4, scale = 0)
    public Integer getDuracionEstimada() {
        return duracionEstimada;
    }

    /**
     * Asigna el valor al campo duracionEstimada.
     * 
     * @param duracionEstimada
     *            el valor duracionEstimada a asignar
     */
    public void setDuracionEstimada(Integer duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumeroExpediente_id", nullable = true)
    public NumeroExpediente getNumeroExpediente() {
        return this.numeroExpediente;
    }

    public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    /**
     * Método de acceso al campo estatus.
     * 
     * @return El valor del campo estatus
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Estatus_val", nullable = false)
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
     * Método de acceso al campo resolutivos.
     * 
     * @return El valor del campo resolutivos
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "audiencia")
    public Set<Resolutivo> getResolutivos() {
        return resolutivos;
    }

    /**
     * Asigna el valor al campo resolutivos.
     * 
     * @param resolutivos
     *            el valor resolutivos a asignar
     */
    public void setResolutivos(Set<Resolutivo> resolutivos) {
        this.resolutivos = resolutivos;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "audiencia")
    public Set<AudienciaEvidencia> getAudienciaEvidencias() {
        return this.audienciaEvidencias;
    }

    public void setAudienciaEvidencias(
            Set<AudienciaEvidencia> audienciaEvidencias) {
        this.audienciaEvidencias = audienciaEvidencias;
    }

    /**
     * Método de acceso al campo conResolutivos.
     * 
     * @return El valor del campo conResolutivos
     */
    @Column(name = "bConResolutivos", precision = 1, scale = 0)
    public Boolean getConResolutivos() {
        return conResolutivos;
    }

    /**
     * Asigna el valor al campo conResolutivos.
     * 
     * @param conResolutivos
     *            el valor conResolutivos a asignar
     */
    public void setConResolutivos(Boolean conResolutivos) {
        this.conResolutivos = conResolutivos;
    }

    /**
     * Método de acceso al campo fechaHoraFin.
     * 
     * @return El valor del campo fechaHoraFin
     */
    @Column(name = "dFechaHoraFin", length = 23)
    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    /**
     * Asigna el valor al campo fechaHoraFin.
     * 
     * @param fechaHoraFin
     *            el valor fechaHoraFin a asignar
     */
    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    /**
     * Método de acceso al campo situacionesEspeciales.
     * @return El valor del campo situacionesEspeciales
     */
    @Column(name = "cSituacionesEspeciales")
    public String getSituacionesEspeciales() {
        return situacionesEspeciales;
    }

    /**
     * Asigna el valor al campo situacionesEspeciales.
     * @param situacionesEspeciales el valor situacionesEspeciales a asignar
     */
    public void setSituacionesEspeciales(String situacionesEspeciales) {
        this.situacionesEspeciales = situacionesEspeciales;
    }

    /**
     * Método de acceso al campo folioAudiencia.
     * @return El valor del campo folioAudiencia
     */
    @Column(name = "cFolioAudiencia", length = 15, nullable = true)
    public String getFolioAudiencia() {
        return folioAudiencia;
    }

    /**
     * Asigna el valor al campo folioAudiencia.
     * @param folioAudiencia el valor folioAudiencia a asignar
     */
    public void setFolioAudiencia(String folioAudiencia) {
        this.folioAudiencia = folioAudiencia;
    }

    /**
     * Método de acceso al campo solicitudesTraslados.
     * @return El valor del campo solicitudesTraslados
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "audiencia")
    public Set<SolicitudTrasladoImputado> getSolicitudesTraslados() {
        return solicitudesTraslados;
    }

    /**
     * Asigna el valor al campo solicitudesTraslados.
     * @param solicitudesTraslados el valor solicitudesTraslados a asignar
     */
    public void setSolicitudesTraslados(
            Set<SolicitudTrasladoImputado> solicitudesTraslados) {
        this.solicitudesTraslados = solicitudesTraslados;
    }

	/**
	 * @return the notificacions
	 */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "audiencia")
	public Set<Notificacion> getNotificacions() {
		return notificacions;
	}

	/**
	 * @param notificacions the notificacions to set
	 */
	public void setNotificacions(Set<Notificacion> notificacions) {
		this.notificacions = notificacions;
	}

    
    
}