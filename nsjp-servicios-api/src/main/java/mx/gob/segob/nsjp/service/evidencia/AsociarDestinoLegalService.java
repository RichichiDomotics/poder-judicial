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
public interface AsociarDestinoLegalService {

	/**
	 * Operación que realiza la funcionalidad de asociar el destino legal de una Evidencia
	 * @param evidenciaDTO: idEvidencia
	 * @param destinoLegal
	 * @throws NSJPNegocioException
	 */
	void asociarDestinoLegal(EvidenciaDTO evidenciaDTO, Long destinoLegal)throws NSJPNegocioException;

}
