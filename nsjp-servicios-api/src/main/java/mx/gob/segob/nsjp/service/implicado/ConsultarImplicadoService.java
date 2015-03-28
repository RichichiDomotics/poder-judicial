package mx.gob.segob.nsjp.service.implicado;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.ImplicadoDTO;

/**
 * @author rgama
 *
 */
public interface ConsultarImplicadoService {

	/**
	 * Operación que permite consultar un implicado por id
	 * @param implicadoId (Obligatorio)
	 * @return ImplicadoDTO
	 * @throws NSJPNegocioException
	 */
    ImplicadoDTO consultarImplicadoXId(ImplicadoDTO aoImplicado)throws NSJPNegocioException;

}
