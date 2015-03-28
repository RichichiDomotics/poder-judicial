package mx.gob.segob.nsjp.dto.archivo;

import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;


public class SolicitudAdjuntosDTO {

    private SolicitudAdjuntosIdDTO id;
    private SolicitudDTO solicitud;
    private ArchivoDigitalDTO archivoDigital;
    
	/**
	 * @return the id
	 */
	public SolicitudAdjuntosIdDTO getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(SolicitudAdjuntosIdDTO id) {
		this.id = id;
	}
	
	/**
	 * @return the solicitud
	 */
	public SolicitudDTO getSolicitud() {
		return solicitud;
	}
	
	/**
	 * @param solicitud the solicitud to set
	 */
	public void setSolicitud(SolicitudDTO solicitud) {
		this.solicitud = solicitud;
	}
	
	/**
	 * @return the archivoDigital
	 */
	public ArchivoDigitalDTO getArchivoDigital() {
		return archivoDigital;
	}
	
	/**
	 * @param archivoDigital the archivoDigital to set
	 */
	public void setArchivoDigital(ArchivoDigitalDTO archivoDigital) {
		this.archivoDigital = archivoDigital;
	}
}
