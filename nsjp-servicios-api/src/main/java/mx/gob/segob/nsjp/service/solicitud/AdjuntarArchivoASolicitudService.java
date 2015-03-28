package mx.gob.segob.nsjp.service.solicitud;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;

public interface AdjuntarArchivoASolicitudService {

	void adjuntarArchivoASolicitud(SolicitudDTO solicitud, ArchivoDigitalDTO adjunto) throws NSJPNegocioException;

	/**
	 * Crea y adjunta un archivo digital a una solicitud que ya existe
	 * @param solicitud Solicitud ya existente
	 * @param adjunto Archivo adjunto a crear y asociar
	 * @throws NSJPNegocioException
	 */
	void adjuntarArchivoASolicitudBasico(SolicitudDTO solicitud,ArchivoDigitalDTO adjunto) throws NSJPNegocioException;
	
	
	
}
