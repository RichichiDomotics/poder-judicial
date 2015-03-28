/**
 * 
 */
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * @author AlejandroGA
 *
 */
public interface ConsultarPliegoDeConsignacionService {
	
	/**
	 * Operación que realiza la consulta de los pliegos de consignacion de un expediente
	 * @param expedienteDTO: idExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	DocumentoDTO consultarPliegoDeConsignacionXExpediente(
			ExpedienteDTO expedienteDTO)throws NSJPNegocioException;

}
