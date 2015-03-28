package mx.gob.segob.nsjp.dto.solicitud;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

public class SolicitudMandamientoDTO extends SolicitudDTO {

	public ValorDTO tipoMandamiento;
	
	/**
	 * @return the mandamiento
	 */
	public ValorDTO getTipoMandamiento() {
		return tipoMandamiento;
	}
	
	/**
	 * @param mandamiento the mandamiento to set
	 */
	public void setTipoMandamiento(ValorDTO mandamiento) {
		this.tipoMandamiento = mandamiento;
	}	
}
