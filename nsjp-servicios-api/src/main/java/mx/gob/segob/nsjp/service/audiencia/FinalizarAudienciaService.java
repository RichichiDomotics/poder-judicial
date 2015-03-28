package mx.gob.segob.nsjp.service.audiencia;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;

public interface FinalizarAudienciaService {

	public void finalizarAudienciaService(AudienciaDTO audiencia) throws NSJPNegocioException;
}
