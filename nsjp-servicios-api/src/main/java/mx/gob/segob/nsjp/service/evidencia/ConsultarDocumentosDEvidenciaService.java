/**
 * 
 */
package mx.gob.segob.nsjp.service.evidencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarDocumentosDEvidenciaService {

	/**
	 * Operación que permite ver los documentos asociados a los eslabones de una evidencia dada
	 * @param evidenciaDTO: idEvidencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<DocumentoDTO> consultarDocumentosXEslabonesDEvidencia(
			EvidenciaDTO evidenciaDTO)throws NSJPNegocioException;

}
