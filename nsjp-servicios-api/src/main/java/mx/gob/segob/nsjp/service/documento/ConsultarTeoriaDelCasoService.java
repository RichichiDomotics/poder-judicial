/**
 * 
 */
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarTeoriaDelCasoService {

	/**
	 * Operación que realiza la consulta de las teoría de caso por expedientes
	 * @param expedienteDTO: idExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	DocumentoDTO consultarTeoriasDelCasoXExpediente(
			ExpedienteDTO expedienteDTO)throws NSJPNegocioException;

}
