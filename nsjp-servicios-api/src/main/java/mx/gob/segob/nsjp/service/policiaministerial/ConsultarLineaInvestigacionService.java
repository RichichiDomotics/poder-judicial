/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarLineaInvestigacionService {

	/**
	 * Operación que permite consultar una línea de investigación por su ID
	 * @param investigacionDTO: ID
	 * @return
	 * @throws NSJPNegocioException
	 */
	LineaInvestigacionDTO consultarLineaInvestigacion(
			LineaInvestigacionDTO investigacionDTO)throws NSJPNegocioException;

	/**
	 * Operación que permite consultar las líneas de investigación que pertenezcan a un Expediente
	 * @param expedienteDTO: idexpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<LineaInvestigacionDTO> consultarLineasInvestigacionXExpedienteId(
			ExpedienteDTO expedienteDTO)throws NSJPNegocioException;

}
