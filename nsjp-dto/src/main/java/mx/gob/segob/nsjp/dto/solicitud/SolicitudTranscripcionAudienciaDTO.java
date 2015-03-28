package mx.gob.segob.nsjp.dto.solicitud;

import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;

public class SolicitudTranscripcionAudienciaDTO extends SolicitudDTO {
    
	private AudienciaDTO audiencia;

	/**
	 * Regresa el valor de la propiedad audiencia
	 * @return the audiencia
	 */
	public AudienciaDTO getAudiencia() {
		return audiencia;
	}

	/**
	 * Establece el valor de la propiedad audiencia
	 * @param audiencia valo audiencia a almacenar
	 */
	public void setAudiencia(AudienciaDTO audiencia) {
		this.audiencia = audiencia;
	}
}
