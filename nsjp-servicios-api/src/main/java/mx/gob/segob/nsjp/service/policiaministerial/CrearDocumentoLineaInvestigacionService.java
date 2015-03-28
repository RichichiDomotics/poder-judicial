/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;

/**
 * @author adrian
 *
 */
public interface CrearDocumentoLineaInvestigacionService {

	/**
	 * Operación que permite generar el PDF de una Línea de Investigación y sus comentarios
	 * @param investigacionDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	DocumentoDTO crearDocumentoLineaInvestigacion(
			LineaInvestigacionDTO investigacionDTO, Boolean esGuardado)throws NSJPNegocioException;

}
