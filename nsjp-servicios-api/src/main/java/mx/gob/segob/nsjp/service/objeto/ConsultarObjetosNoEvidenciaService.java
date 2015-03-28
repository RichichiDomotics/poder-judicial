/**
 * 
 */
package mx.gob.segob.nsjp.service.objeto;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarObjetosNoEvidenciaService {

	/**
     * Operación que realiza la funcionalidad de consultar los objetos no asociados a una evidencia
     * @param expedienteDTO
     * @return
     * @throws NSJPNegocioException
     */
	List<ObjetoDTO> consultarObjetosNoEvidencia(ExpedienteDTO expedienteDTO)throws NSJPNegocioException;

}
