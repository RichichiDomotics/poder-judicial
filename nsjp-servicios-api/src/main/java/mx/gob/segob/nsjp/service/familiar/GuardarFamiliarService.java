package mx.gob.segob.nsjp.service.familiar;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;

/**
 * @author rgama
 *
 */
public interface GuardarFamiliarService {
	
	/**
	 * Permite guardar/actualizar la informacion de un Familiar
	 * @param aoFamiliarDTO
	 * 	 Obligatorio <b>entrevistaComplementaria.sesionId</b>.
	 * @return FamiliarDTO
	 * @throws NSJPNegocioException
	 */
	FamiliarDTO guardarFamiliar(FamiliarDTO aoFamiliarDTO)
		throws NSJPNegocioException;

}
