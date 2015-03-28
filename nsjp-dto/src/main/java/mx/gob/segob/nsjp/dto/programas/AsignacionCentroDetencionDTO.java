package mx.gob.segob.nsjp.dto.programas;

import java.util.Date;

import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;

public class AsignacionCentroDetencionDTO implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 4036445403680035624L;
	private Long asignacionCentroDetencionId;
	private CentroDetencionDTO centroDetencionDTO;
	private SentenciaDTO sentenciaDTO;
	private Date dfechaIngreso;
	private Date dfechaSalida;
	private Boolean barraigado;
	private String cmotivo;
	/**
	 * @return the asignacionCentroDetencionId
	 */
	public Long getAsignacionCentroDetencionId() {
		return asignacionCentroDetencionId;
	}
	/**
	 * @param asignacionCentroDetencionId the asignacionCentroDetencionId to set
	 */
	public void setAsignacionCentroDetencionId(Long asignacionCentroDetencionId) {
		this.asignacionCentroDetencionId = asignacionCentroDetencionId;
	}
	/**
	 * @return the centroDetencionDTO
	 */
	public CentroDetencionDTO getCentroDetencionDTO() {
		return centroDetencionDTO;
	}
	/**
	 * @param centroDetencionDTO the centroDetencionDTO to set
	 */
	public void setCentroDetencionDTO(CentroDetencionDTO centroDetencionDTO) {
		this.centroDetencionDTO = centroDetencionDTO;
	}
	/**
	 * @return the sentenciaDTO
	 */
	public SentenciaDTO getSentenciaDTO() {
		return sentenciaDTO;
	}
	/**
	 * @param sentenciaDTO the sentenciaDTO to set
	 */
	public void setSentenciaDTO(SentenciaDTO sentenciaDTO) {
		this.sentenciaDTO = sentenciaDTO;
	}
	/**
	 * @return the dfechaIngreso
	 */
	public Date getDfechaIngreso() {
		return dfechaIngreso;
	}
	/**
	 * @param dfechaIngreso the dfechaIngreso to set
	 */
	public void setDfechaIngreso(Date dfechaIngreso) {
		this.dfechaIngreso = dfechaIngreso;
	}
	/**
	 * @return the dfechaSalida
	 */
	public Date getDfechaSalida() {
		return dfechaSalida;
	}
	/**
	 * @param dfechaSalida the dfechaSalida to set
	 */
	public void setDfechaSalida(Date dfechaSalida) {
		this.dfechaSalida = dfechaSalida;
	}
	/**
	 * @return the barraigado
	 */
	public Boolean getBarraigado() {
		return barraigado;
	}
	/**
	 * @param barraigado the barraigado to set
	 */
	public void setBarraigado(Boolean barraigado) {
		this.barraigado = barraigado;
	}
	/**
	 * @return the cmotivo
	 */
	public String getCmotivo() {
		return cmotivo;
	}
	/**
	 * @param cmotivo the cmotivo to set
	 */
	public void setCmotivo(String cmotivo) {
		this.cmotivo = cmotivo;
	}

}