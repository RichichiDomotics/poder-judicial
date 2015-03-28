/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;

/**
 * @author adrian
 *
 */
public interface GuardarLineaInvestigacionService {

	Long guardarLineaInvestigacion(LineaInvestigacionDTO investigacionDTO)throws NSJPNegocioException;

	void actualizarLineaConImpreso(Long lineaInvestigacionId)throws NSJPNegocioException;

}
