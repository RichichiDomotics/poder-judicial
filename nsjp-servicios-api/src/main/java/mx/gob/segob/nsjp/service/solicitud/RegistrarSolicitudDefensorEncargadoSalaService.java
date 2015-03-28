package mx.gob.segob.nsjp.service.solicitud;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;

public interface RegistrarSolicitudDefensorEncargadoSalaService {

	public void registrarSolicitudDefensorEncargadoSala(AudienciaDTO audiencia, Long imputadoId) throws NSJPNegocioException;
	
}
