package mx.gob.segob.nsjp.service.sesion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;

/**
 * @author rgama
 *
 */
public interface ConsultarEntrevistaInicialPorIdService {
	
	/**
	 * Consulta EntrevistaInicial por identificador
	 * @param aoEntrevistaInicialDTO
	 * 		Obligatorio <b>sesionId</b>.
	 * @return
	 * @throws NSJPNegocioException
	 */
    EntrevistaInicialDTO consultarEntrevistaInicialPorId(EntrevistaInicialDTO aoEntrevistaInicialDTO)
            throws NSJPNegocioException;

}
