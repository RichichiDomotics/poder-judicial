/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarSeguimientoService {

	/**
	 * Operación que permite consultar un seguimiento por su ID
	 * @param seguimientoLIDTO: ID
	 * @return
	 * @throws NSJPNegocioException
	 */
	SeguimientoLIDTO consultarSeguimientoLI(SeguimientoLIDTO seguimientoLIDTO)throws NSJPNegocioException;

	/**
	 * Operación que permite consultar los seguimientos que pertenezcan a un numero de expediente
	 * @param expedienteDTO: NumeroExpedienteID / NumeroExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<SeguimientoLIDTO> consultarSeguimientosLIXExpedienteId(
			ExpedienteDTO expedienteDTO)throws NSJPNegocioException;

}
