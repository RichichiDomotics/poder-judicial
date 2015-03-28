package mx.gob.segob.nsjp.service.solicitud;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;


public interface ActualizarStatusSolicitudEvidenciaService {
	SolicitudDTO actualizarStatusSolicitudEvidencia(SolicitudDTO solicitud) throws NSJPNegocioException;	
}
