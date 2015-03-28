/**
 * 
 */
package mx.gob.segob.nsjp.service.evidencia;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;

/**
 * @author adrian
 *
 */
public interface CambiarEstatusEvidenciaService {

	/**
	 * Operación que permite cambiar el estatus de una evidencia
	 * @param evidenciaDTO: idEvidencia, estatusDeseado
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long cambiarEstatusEvidencia(EvidenciaDTO evidenciaDTO)throws NSJPNegocioException;

}
