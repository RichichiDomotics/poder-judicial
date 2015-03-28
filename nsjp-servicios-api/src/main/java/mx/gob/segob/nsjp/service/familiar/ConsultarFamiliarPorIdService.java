package mx.gob.segob.nsjp.service.familiar;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;

/**
 * @author rgama
 *
 */
public interface ConsultarFamiliarPorIdService {
	
	/**
	 * Consulta Familiar por identificador
	 * @param aoFamiliarDTO
	 * 		Obligatorio <b>familiarId</b>.
	 * @return
	 * @throws NSJPNegocioException
	 */
    FamiliarDTO consultarFamiliarPorId(FamiliarDTO aoFamiliarDTO)
            throws NSJPNegocioException;

}
