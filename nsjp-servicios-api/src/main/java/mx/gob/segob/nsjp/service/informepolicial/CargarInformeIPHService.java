/**
 * 
 */
package mx.gob.segob.nsjp.service.informepolicial;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;

/**
 * @author adrian
 *
 */
public interface CargarInformeIPHService {

	/**
	 * Operación que pérmite crear el documento de IPH
	 * @param homologadoDTO: idHomologado, expediente: idExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	DocumentoDTO cargarInformeIPH(InformePolicialHomologadoDTO homologadoDTO)throws NSJPNegocioException;

}
