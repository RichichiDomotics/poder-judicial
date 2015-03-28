package mx.gob.segob.nsjp.web.evento.form;

import org.apache.struts.upload.FormFile;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Clase que implementa la forma para registrar una solicitud en la ventanilla de atención 
 * al público de poder judicial
 * 
 * @version 1.0 
 * @author ArmandoCT
 */
public class RegistrarSolicitudPJForm extends GenericForm {

	private static final long serialVersionUID = 1L;
	
	/** archivo adjunto */
	private FormFile archivoAdjunto;
	

	/** fecha solicitud */
	private String fechaSolicitud;
	
	/** hora solicitud */
	private String horaSolicitud;
	
	/** institucion solicitante */
	private Long institucionSolicitante;
	
	/** solicitante */
	private String NombreSolicitante;
	
	/** tipo solicitud */
	private Long tipoSolicitud;
	
	/** numero expediente id */
	private Long numeroExpedienteId;
	
	/** tipo recurso */
	private Long tipoRecurso;
	
	/** forma Id */
	private String formaId;
	
	private String numeroCausa;
	
	private String numeroCaso;
	
	private String fechaLimite;
	
	private String horaLimite;
	
	private String tipoAudiencia;
	
	private String motivoSolicitud;
	
	private String situacionesEspeciales;
	
	private String involucradosAudiencia;

	private String audienciaId;
	/**
	 * Método de acceso al campo archivoAdjunto.
	 * @return El valor del campo archivoAdjunto
	 */
	public FormFile getArchivoAdjunto() {
		return archivoAdjunto;
	}

	/**
	 * Asigna el valor al campo archivoAdjunto.
	 * @param archivoAdjunto el valor archivoAdjunto a asignar
	 */
	public void setArchivoAdjunto(FormFile archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}

	/**
	 * Método de acceso al campo fechaSolicitud.
	 * @return El valor del campo fechaSolicitud
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * Asigna el valor al campo fechaSolicitud.
	 * @param fechaSolicitud el valor fechaSolicitud a asignar
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * Método de acceso al campo horaSolicitud.
	 * @return El valor del campo horaSolicitud
	 */
	public String getHoraSolicitud() {
		return horaSolicitud;
	}

	/**
	 * Asigna el valor al campo horaSolicitud.
	 * @param horaSolicitud el valor horaSolicitud a asignar
	 */
	public void setHoraSolicitud(String horaSolicitud) {
		this.horaSolicitud = horaSolicitud;
	}

	/**
	 * Método de acceso al campo institucionSolicitante.
	 * @return El valor del campo institucionSolicitante
	 */
	public Long getInstitucionSolicitante() {
		return institucionSolicitante;
	}

	/**
	 * Asigna el valor al campo institucionSolicitante.
	 * @param institucionSolicitante el valor institucionSolicitante a asignar
	 */
	public void setInstitucionSolicitante(Long institucionSolicitante) {
		this.institucionSolicitante = institucionSolicitante;
	}

	/**
	 * Método de acceso al campo nombreSolicitante.
	 * @return El valor del campo nombreSolicitante
	 */
	public String getNombreSolicitante() {
		return NombreSolicitante;
	}

	/**
	 * Asigna el valor al campo nombreSolicitante.
	 * @param nombreSolicitante el valor nombreSolicitante a asignar
	 */
	public void setNombreSolicitante(String nombreSolicitante) {
		NombreSolicitante = nombreSolicitante;
	}

	/**
	 * Método de acceso al campo tipoSolicitud.
	 * @return El valor del campo tipoSolicitud
	 */
	public Long getTipoSolicitud() {
		return tipoSolicitud;
	}

	/**
	 * Asigna el valor al campo tipoSolicitud.
	 * @param tipoSolicitud el valor tipoSolicitud a asignar
	 */
	public void setTipoSolicitud(Long tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	/**
	 * Método de acceso al campo numeroExpedienteId.
	 * @return El valor del campo numeroExpedienteId
	 */
	public Long getNumeroExpedienteId() {
		return numeroExpedienteId;
	}

	/**
	 * Asigna el valor al campo numeroExpedienteId.
	 * @param numeroExpedienteId el valor numeroExpedienteId a asignar
	 */
	public void setNumeroExpedienteId(Long numeroExpedienteId) {
		this.numeroExpedienteId = numeroExpedienteId;
	}

	/**
	 * Método de acceso al campo tipoRecurso.
	 * @return El valor del campo tipoRecurso
	 */
	public Long getTipoRecurso() {
		return tipoRecurso;
	}

	/**
	 * Asigna el valor al campo tipoRecurso.
	 * @param tipoRecurso el valor tipoRecurso a asignar
	 */
	public void setTipoRecurso(Long tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	/**
	 * Método de acceso al campo formaId.
	 * @return El valor del campo formaId
	 */
	public String getFormaId() {
		return formaId;
	}

	/**
	 * Asigna el valor al campo formaId.
	 * @param formaId el valor formaId a asignar
	 */
	public void setFormaId(String formaId) {
		this.formaId = formaId;
	}

	/**
	 * Método de acceso al campo numeroCausa.
	 * @return El valor del campo numeroCausa
	 */
	public String getNumeroCausa() {
		return numeroCausa;
	}

	/**
	 * Asigna el valor al campo numeroCausa.
	 * @param numeroCausa el valor numeroCausa a asignar
	 */
	public void setNumeroCausa(String numeroCausa) {
		this.numeroCausa = numeroCausa;
	}

	/**
	 * Método de acceso al campo numeroCaso.
	 * @return El valor del campo numeroCaso
	 */
	public String getNumeroCaso() {
		return numeroCaso;
	}

	/**
	 * Asigna el valor al campo numeroCaso.
	 * @param numeroCaso el valor numeroCaso a asignar
	 */
	public void setNumeroCaso(String numeroCaso) {
		this.numeroCaso = numeroCaso;
	}

	/**
	 * Método de acceso al campo fechaLimite.
	 * @return El valor del campo fechaLimite
	 */
	public String getFechaLimite() {
		return fechaLimite;
	}

	/**
	 * Asigna el valor al campo fechaLimite.
	 * @param fechaLimite el valor fechaLimite a asignar
	 */
	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	/**
	 * Método de acceso al campo horaLimite.
	 * @return El valor del campo horaLimite
	 */
	public String getHoraLimite() {
		return horaLimite;
	}

	/**
	 * Asigna el valor al campo horaLimite.
	 * @param horaLimite el valor horaLimite a asignar
	 */
	public void setHoraLimite(String horaLimite) {
		this.horaLimite = horaLimite;
	}

	/**
	 * Método de acceso al campo tipoAudiencia.
	 * @return El valor del campo tipoAudiencia
	 */
	public String getTipoAudiencia() {
		return tipoAudiencia;
	}

	/**
	 * Asigna el valor al campo tipoAudiencia.
	 * @param tipoAudiencia el valor tipoAudiencia a asignar
	 */
	public void setTipoAudiencia(String tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
	}

	/**
	 * Método de acceso al campo motivoSolicitud.
	 * @return El valor del campo motivoSolicitud
	 */
	public String getMotivoSolicitud() {
		return motivoSolicitud;
	}

	/**
	 * Asigna el valor al campo motivoSolicitud.
	 * @param motivoSolicitud el valor motivoSolicitud a asignar
	 */
	public void setMotivoSolicitud(String motivoSolicitud) {
		this.motivoSolicitud = motivoSolicitud;
	}

	/**
	 * Método de acceso al campo situacionesEspeciales.
	 * @return El valor del campo situacionesEspeciales
	 */
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
	 * Método de acceso al campo involucradosAudiencia.
	 * @return El valor del campo involucradosAudiencia
	 */
	public String getInvolucradosAudiencia() {
		return involucradosAudiencia;
	}

	/**
	 * Asigna el valor al campo involucradosAudiencia.
	 * @param involucradosAudiencia el valor involucradosAudiencia a asignar
	 */
	public void setInvolucradosAudiencia(String involucradosAudiencia) {
		this.involucradosAudiencia = involucradosAudiencia;
	}

	/**
	 * Método de acceso al campo audienciaId.
	 * @return El valor del campo audienciaId
	 */
	public String getAudienciaId() {
		return audienciaId;
	}

	/**
	 * Asigna el valor al campo audienciaId.
	 * @param audienciaId el valor audienciaId a asignar
	 */
	public void setAudienciaId(String audienciaId) {
		this.audienciaId = audienciaId;
	}
	
	
}
