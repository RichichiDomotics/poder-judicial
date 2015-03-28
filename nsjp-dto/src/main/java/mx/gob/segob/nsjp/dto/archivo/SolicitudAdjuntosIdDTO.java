package mx.gob.segob.nsjp.dto.archivo;

public class SolicitudAdjuntosIdDTO {


	private Long solicitudId;
	private Long archivoDigitalId;
	
	/**
	 * @return the solicitudId
	 */
	public Long getSolicitudId() {
		return solicitudId;
	}
	
	/**
	 * @param solicitudId the solicitudId to set
	 */
	public void setSolicitudId(Long solicitudId) {
		this.solicitudId = solicitudId;
	}
	
	/**
	 * @return the archivoDigitalId
	 */
	public Long getArchivoDigitalId() {
		return archivoDigitalId;
	}
	
	/**
	 * @param archivoDigitalId the archivoDigitalId to set
	 */
	public void setArchivoDigitalId(Long archivoDigitalId) {
		this.archivoDigitalId = archivoDigitalId;
	}
	
}
