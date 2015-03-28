package mx.gob.segob.nsjp.service.sesion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.NotaEvolucionDTO;

/**
 * @author rgama
 *
 */
public interface ConsultarNotaEvolucionPorIdService {
	
	/**
	 * Consulta NotaEvolucion por identificador
	 * @param aoNotaEvolucionDTO
	 * 		Obligatorio <b>sesionId</b>.
	 * @return
	 * @throws NSJPNegocioException
	 */
    NotaEvolucionDTO consultarNotaEvolucionPorId(NotaEvolucionDTO aoNotaEvolucionDTO)
            throws NSJPNegocioException;   

}
