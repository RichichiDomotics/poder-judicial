package mx.gob.segob.nsjp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;

/**
 * Solicitud entity.
 * <ul>
 * <li>
 * Si <code>funcionarioSolicitante</code> tiene valor significa que la solicitud
 * es Interna, en caso contrario es externa.</li>
 * <li>Si <code>areaSolicitanteExterna</code> o <code>solicitanteExterno</code>
 * tiene valor significa que la solicitud es Externa generada por otra
 * institución a través de su instancia de NSJP.</li>
 * <li><code>nombreSolicitante</code> siempre tendrá valor cuando sea externa la
 * solicitud.</li>
 * </ul>
 * Por otro lado, la propiedad <code>tipoSolicitud</code> será el valor que nos
 * permitirá identificar el destinatario de la solicitud.
 * 
 * @author vaguirre
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Solicitud")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Solicitud_id")
public class Solicitud extends Documento {
	//Si se modifica/agrega alguna tambien se sebe modificar en SolicitudDTO
    public final static String POR_ATENDER = "Solicitudes Por Atender";
	public final static String GENERADAS = "Solicitudes Generadas";
	public final static String TODAS = "Todas las Solicitudes";
	public final static String PARCIALES = "Documentos Parciales";
	public final static String NUMEROCASOASOCIADO = "cNumeroCasoAsociado";

	
    // Fields
    /**
     * Debe soportar la referencia a <code>NumeroExpediente</code> como
     * <i>nulleable</i> para cuando hay más de un expediente ante la recepción
     * de una solicitud entre instituciones.
     */
    private NumeroExpediente numeroExpediente;
    /**
     * Especifica el tipo de solicitud.
     */
    private Valor tipoSolicitud;
    /**
     * Usado cuando la solicitud es interna, y es un funcionario el que la
     * realiza, ejemplo: una solicitud de periciales.
     */
    private Funcionario funcionarioSolicitante;
    /**
     * Cuando la solicitud es de otra institución, se asocia con la el ID de la
     * <code>JerarquiaOrganizacional</code> de la otra institución, basicamente
     * pasa saber a <b>donde</b> regresar el acuse o la notificación.
     */
    private Long areaOrigen;
    /**
     * 
     */
    private Long areaDestino;
    
    /**
     * Cuando la solicitud es de otra institución, se asocia con la el ID del
     * funcionario de la otra institución, basicamente pasa saber a <b>quien</b>
     * regresar el acuse o la notificación.
     */

    private Long solicitanteExterno;
    /**
     * Usado para cuando la solicitud es externa, contiene el nombre del
     * ciudadano (no involucrado) o del funcionario (de otra institución) que
     * realizo la solicitud.
     */
    private String nombreSolicitante;

    /**
     * Usado para cuando la solicitud es por parte de un ciudadano y éste se
     * captura de manera completa como un <code>Involucrado</code>.
     */
    private Involucrado involucradoSolicitante;

    /**
     * @see EstatusSolicitud
     * @param valorByEstatusVal
     */
    private Valor estatus;
    /**
     * Usado en solicitudes externas, cuando el sistema necesita saber el numero
     * de caso asociado, nunca se muestra al sistema.
     */
    private String numeroCasoAsociado;
    private String motivo;
    private Date fechaLimite;
    private Date fechaModificacion;
    private Date fechaCierre;
    private CadenaDeCustodia cadenaCustodia;
    private Boolean esUrgente;

    /**
     * Usado en:
     * <ul>
     * <li>SSP, en la orden de detención el <b>Comandate</b> a quien va dirigida
     * la orden.</li>
     * <li>Periciales, para el <b>Perito</b> que atiende la solicitud.</li>
     * <li>Defensoria, para el <b>Defensor</b> que atiende la solicitud</li>
     * </ul>
     * <i>Las demás solicitudes van dirigidas a <b>áreas</b> por lo tanto el
     * valor es <code>null</code></i>
     */
    private Funcionario destinatario;

    private String folioSolicitud;

    /**
     * Los acuse de recibo de la solicitus.
     */
    private Set<AcuseRecibo> acuseRecibos = new HashSet<AcuseRecibo>(0);

    private Set<SolicitudAdjuntos> archivosAdjuntos = new HashSet<SolicitudAdjuntos>(
            0);
    
    /**
     * Atributo que representa el asunto de la solicitud.
     */
    private String asuntoSolicitud;
    
    /**
     * Observaciones o situaciones especiales a considerar, etc.
     */
    private String observaciones;
    
    /**
     * Funcionario que pertenece a otra instituci&oacute;n qui&eacute;n es
     * el remitente de la solicitud.
     */
    private FuncionarioExterno funcionarioSolExt;
    
    /**
     * Funcionario que pertenece a otra instituci&oacute;n qui&eacute;n es
     * el destinatario de la solicitud.
     */
    private FuncionarioExterno funcionarioDestExt;
    
    // Constructors

    /** default constructor */
    public Solicitud() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumeroExpediente_id", nullable = true)
    public NumeroExpediente getNumeroExpediente() {
        return this.numeroExpediente;
    }

    public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TipoSolicitud_val", nullable = false)
    public Valor getTipoSolicitud() {
        return this.tipoSolicitud;
    }

    public void setTipoSolicitud(Valor valorByTipoSolicitudVal) {
        this.tipoSolicitud = valorByTipoSolicitudVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iFuncionarioSolicitante")
    public Funcionario getFuncionarioSolicitante() {
        return this.funcionarioSolicitante;
    }

    public void setFuncionarioSolicitante(Funcionario funcionario) {
        this.funcionarioSolicitante = funcionario;
    }



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Estatus_val", nullable = true)
    public Valor getEstatus() {
        return this.estatus;
    }
    /**
     * @see EstatusSolicitud
     * @param valorByEstatusVal
     */
    public void setEstatus(Valor valorByEstatusVal) {
        this.estatus = valorByEstatusVal;
    }

    @Column(name = "cNumeroCasoAsociado", length = 28)
    public String getNumeroCasoAsociado() {
        return this.numeroCasoAsociado;
    }

    public void setNumeroCasoAsociado(String cnumeroCasoAsociado) {
        this.numeroCasoAsociado = cnumeroCasoAsociado;
    }

    @Column(name = "cMotivo")
    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String cmotivo) {
        this.motivo = cmotivo;
    }

    @Column(name = "dFechaLimite", length = 23)
    public Date getFechaLimite() {
        return this.fechaLimite;
    }

    public void setFechaLimite(Date dfechaLimite) {
        this.fechaLimite = dfechaLimite;
    }

    @Column(name = "dFechaModificacion", length = 23)
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date dfechaModificacion) {
        this.fechaModificacion = dfechaModificacion;
    }

    @Column(name = "dFechaCierre", length = 23)
    public Date getFechaCierre() {
        return this.fechaCierre;
    }

    public void setFechaCierre(Date dfechaCierre) {
        this.fechaCierre = dfechaCierre;
    }

    @Column(name = "iAreaOrigen", precision = 18, scale = 0)
    public Long getAreaOrigen() {
        return this.areaOrigen;
    }

    public void setAreaOrigen(Long iAreaOrigen) {
        this.areaOrigen = iAreaOrigen;
    }

    @Column(name = "iAreaDestino", precision = 18, scale = 0)
	public Long getAreaDestino() {
		return areaDestino;
	}

	public void setAreaDestino(Long areaDestino) {
		this.areaDestino = areaDestino;
	}

	@Column(name = "iSolicitanteExterno", precision = 18, scale = 0)
    public Long getSolicitanteExterno() {
        return this.solicitanteExterno;
    }

    public void setSolicitanteExterno(Long isolicitanteExterno) {
        this.solicitanteExterno = isolicitanteExterno;
    }

    @Column(name = "cNombreSolicitante", length = 60)
    public String getNombreSolicitante() {
        return this.nombreSolicitante;
    }

    public void setNombreSolicitante(String cnombreSolicitante) {
        this.nombreSolicitante = cnombreSolicitante;
    }

    /**
     * Navega a través de <code>numeroExpediente</code> para obtener el
     * <code>Expediente</code>.
     * 
     * @return
     */
    @Transient
    public Expediente getExpediente() {
        if (this.numeroExpediente != null) {
            return this.numeroExpediente.getExpediente();
        }
        return null;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "solicitud")
    public Set<AcuseRecibo> getAcuseRecibos() {
        return this.acuseRecibos;
    }

    public void setAcuseRecibos(Set<AcuseRecibo> acuseRecibos) {
        this.acuseRecibos = acuseRecibos;
    }

    /**
     * Asigna el valor al campo cadenaCustodia.
     * 
     * @param cadenaCustodia
     *            el valor cadenaCustodia a asignar
     */
    public void setCadenaCustodia(CadenaDeCustodia cadenaCustodia) {
        this.cadenaCustodia = cadenaCustodia;
    }

    /**
     * Método de acceso al campo cadenaCustodia.
     * 
     * @return El valor del campo cadenaCustodia
     */
    @Transient
    public CadenaDeCustodia getCadenaCustodia() {
        return cadenaCustodia;
    }

    /**
     * Método de acceso al campo esUrgente.
     * 
     * @return El valor del campo esUrgente
     */
    @Column(name = "bEsUrgente", precision = 1, scale = 0)
    public Boolean getEsUrgente() {
        return esUrgente;
    }

    /**
     * Asigna el valor al campo esUrgente.
     * 
     * @param esUrgente
     *            el valor esUrgente a asignar
     */
    public void setEsUrgente(Boolean esUrgente) {
        this.esUrgente = esUrgente;
    }

    /**
     * Método de acceso al campo involucradoSolicitante.
     * 
     * @return El valor del campo involucradoSolicitante
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InvolucradoSolicitante_id")
    public Involucrado getInvolucradoSolicitante() {
        return involucradoSolicitante;
    }

    /**
     * Asigna el valor al campo involucradoSolicitante.
     * 
     * @param involucradoSolicitante
     *            el valor involucradoSolicitante a asignar
     */
    public void setInvolucradoSolicitante(Involucrado involucradoSolicitante) {
        this.involucradoSolicitante = involucradoSolicitante;
    }

    /**
     * Método de acceso al campo archivosAdjuntos.
     * 
     * @return El valor del campo archivosAdjuntos
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "solicitud")
    public Set<SolicitudAdjuntos> getArchivosAdjuntos() {
        return archivosAdjuntos;
    }

    /**
     * Asigna el valor al campo archivosAdjuntos.
     * 
     * @param archivosAdjuntos
     *            el valor archivosAdjuntos a asignar
     */
    public void setArchivosAdjuntos(Set<SolicitudAdjuntos> archivosAdjuntos) {
        this.archivosAdjuntos = archivosAdjuntos;
    }

    /**
     * Método de acceso al campo funcionarioDestinatario.
     * 
     * @return El valor del campo funcionarioDestinatario
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iFuncionarioDestinatario", nullable = true)
    public Funcionario getDestinatario() {
        return destinatario;
    }

    /**
     * Asigna el valor al campo funcionarioDestinatario.
     * 
     * @param funcionarioDestinatario
     *            el valor funcionarioDestinatario a asignar
     */
    public void setDestinatario(Funcionario funcionarioDestinatario) {
        this.destinatario = funcionarioDestinatario;
    }

    /**
     * Método de acceso al campo folioSolicitud.
     * 
     * @return El valor del campo folioSolicitud
     */
    @Column(name = "cFolioSolicitud", length = 12, nullable = false)
    public String getFolioSolicitud() {
        return folioSolicitud;
    }

    /**
     * Asigna el valor al campo folioSolicitud.
     * 
     * @param folioSolicitud
     *            el valor folioSolicitud a asignar
     */
    public void setFolioSolicitud(String folioSolicitud) {
        this.folioSolicitud = folioSolicitud;
    }

	/**
	 * Método de acceso al campo asuntoSolicitud.
	 * @return El valor del campo asuntoSolicitud
	 */
    @Column(name = "cAsuntoSolicitud", length = 50)
	public String getAsuntoSolicitud() {
		return asuntoSolicitud;
	}

	/**
	 * Asigna el valor al campo asuntoSolicitud.
	 * @param asuntoSolicitud el valor asuntoSolicitud a asignar
	 */
	public void setAsuntoSolicitud(String asuntoSolicitud) {
		this.asuntoSolicitud = asuntoSolicitud;
	}

	/**
	 * Método de acceso al campo observaciones.
	 * @return El valor del campo observaciones
	 */
	@Column(name = "cObservaciones")
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Asigna el valor al campo observaciones.
	 * @param observaciones el valor observaciones a asignar
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Método de acceso al campo funcionarioSolExt.
	 * @return El valor del campo funcionarioSolExt
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iFuncionarioSolExt")
	public FuncionarioExterno getFuncionarioSolExt() {
		return funcionarioSolExt;
	}

	/**
	 * Asigna el valor al campo funcionarioSolExt.
	 * @param funcionarioSolExt el valor funcionarioSolExt a asignar
	 */
	public void setFuncionarioSolExt(FuncionarioExterno funcionarioSolExt) {
		this.funcionarioSolExt = funcionarioSolExt;
	}

	/**
	 * Método de acceso al campo funcionarioDestExt.
	 * @return El valor del campo funcionarioDestExt
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iFuncionarioDestExt")
	public FuncionarioExterno getFuncionarioDestExt() {
		return funcionarioDestExt;
	}

	/**
	 * Asigna el valor al campo funcionarioDestExt.
	 * @param funcionarioDestExt el valor funcionarioDestExt a asignar
	 */
	public void setFuncionarioDestExt(FuncionarioExterno funcionarioDestExt) {
		this.funcionarioDestExt = funcionarioDestExt;
	}
}