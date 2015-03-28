/**
 * 
 */
package mx.gob.segob.nsjp.service.actividad;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * @author adrian
 *
 */
public interface EliminarAsociacionExpedienteDocumentoService {

	/**
	 * Operación que realiza la funcionalidad de elimninar la asociación de un archivo digital a un expediente.
	 * @param expedienteDTO: numeroExpediente
	 * @param documentoDTO: idDocumento
	 * @throws NSJPNegocioException
	 */
	void eliminarAsocArchivoAExpediente(ExpedienteDTO expedienteDTO,
			DocumentoDTO documentoDTO)throws NSJPNegocioException;

}
