/**
 * 
 */
package mx.gob.segob.nsjp.service.expediente;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ActaCircunstanciadaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarActaCircunstanciadaService {

	/**
	 * Operación que permite realizar la consulta de un Acta Circunstanciada
	 * @param expedienteDTO: idExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	ActaCircunstanciadaDTO consultarActaCircunstaciada(
			ExpedienteDTO expedienteDTO)throws NSJPNegocioException;

}
