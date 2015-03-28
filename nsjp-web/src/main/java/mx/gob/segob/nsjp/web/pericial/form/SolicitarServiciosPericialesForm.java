package mx.gob.segob.nsjp.web.pericial.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

public class SolicitarServiciosPericialesForm extends GenericForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fechaLimite;
	private String motivo;
	private String cadenasRegistradas;
	private String solicitudId;
	private String envio;
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
	 * Método de acceso al campo motivo.
	 * @return El valor del campo motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * Asigna el valor al campo motivo.
	 * @param motivo el valor motivo a asignar
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * Método de acceso al campo cadenasRegistradas.
	 * @return El valor del campo cadenasRegistradas
	 */
	public String getCadenasRegistradas() {
		return cadenasRegistradas;
	}
	/**
	 * Asigna el valor al campo cadenasRegistradas.
	 * @param cadenasRegistradas el valor cadenasRegistradas a asignar
	 */
	public void setCadenasRegistradas(String cadenasRegistradas) {
		this.cadenasRegistradas = cadenasRegistradas;
	}
	/**
	 * Método de acceso al campo solicitudId.
	 * @return El valor del campo solicitudId
	 */
	public String getSolicitudId() {
		return solicitudId;
	}
	/**
	 * Asigna el valor al campo solicitudId.
	 * @param solicitudId el valor solicitudId a asignar
	 */
	public void setSolicitudId(String solicitudId) {
		this.solicitudId = solicitudId;
	}
	/**
	 * Método de acceso al campo envio.
	 * @return El valor del campo envio
	 */
	public String getEnvio() {
		return envio;
	}
	/**
	 * Asigna el valor al campo envio.
	 * @param envio el valor envio a asignar
	 */
	public void setEnvio(String envio) {
		this.envio = envio;
	}
	

}
