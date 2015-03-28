package mx.gob.segob.nsjp.dto.resolutivo;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

public class ResolutivoViewDTO extends GenericDTO {

	private Long resolutivoId;
	private String temporizador;
	private String detalle;
	
	/**
	 * @return the resolutivoId
	 */
	public Long getResolutivoId() {
		return resolutivoId;
	}
	
	/**
	 * @param resolutivoId the resolutivoId to set
	 */
	public void setResolutivoId(Long resolutivoId) {
		this.resolutivoId = resolutivoId;
	}
	
	/**
	 * @return the temporizador
	 */
	public String getTemporizador() {
		return temporizador;
	}
	
	/**
	 * @param temporizador the temporizador to set
	 */
	public void setTemporizador(String temporizador) {
		this.temporizador = temporizador;
	}
	
	/**
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}
	
	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
}
