package mx.gob.segob.nsjp.service.sesion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.sesion.SesionDTO;

/**
 * @author rgama
 *
 */
public interface ConsultarSesionesPorIdNumeroExpedienteService {
	
	/**
	 * Consulta todas las sesiones asociados a un Numero de Expediente
	 * @param aoExpedienteDTO
	 * 		Obligatorio <b>numeroExpedienteId</b>.
	 * @return List<SesionDTO>
	 * @throws NSJPNegocioException
	 */
    List<SesionDTO> consultarSesionesPorIdNumeroExpediente(ExpedienteDTO aoExpedienteDTO)
            throws NSJPNegocioException;

}
