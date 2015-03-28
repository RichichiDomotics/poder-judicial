package mx.gob.segob.nsjp.service.solicitud;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;

public interface AutorizarSolicitudEvidenciaService {
	public Long autorizarSolicitudEvidencia(SolicitudPericialDTO evidencia) throws NSJPNegocioException;
}
