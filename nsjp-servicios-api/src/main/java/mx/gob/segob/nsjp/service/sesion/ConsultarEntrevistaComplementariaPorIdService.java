package mx.gob.segob.nsjp.service.sesion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;

/**
 * @author rgama
 *
 */
public interface ConsultarEntrevistaComplementariaPorIdService {
	
	/**
	 * Consulta EntrevistaComplementaria por identificador
	 * @param aoEntrevistaComplementariaDTO
	 * 		Obligatorio <b>sesionId</b>.
	 * @return
	 * @throws NSJPNegocioException
	 */
    EntrevistaComplementariaDTO consultarEntrevistaComplementariaPorId(EntrevistaComplementariaDTO aoEntrevistaComplementariaDTO)
            throws NSJPNegocioException;

}
