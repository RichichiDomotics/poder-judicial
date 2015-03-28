/**
 * 
 */
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * @author adrian
 *
 */
public interface AdjuntarArchivoDigitalAExpedienteService {

	/**
	 * Operación que permite adjuntar un archivo digital y asociarlo a un expediente mediante una actividad
	 * @param expedienteDTO
	 * @param archivoDigitalDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long adjuntarArchivoDigitalAExpediente(ExpedienteDTO expedienteDTO,
			ArchivoDigitalDTO archivoDigitalDTO, FuncionarioDTO funcionarioDTO, Actividades actividad)throws NSJPNegocioException;
	
	
	Long adjuntarDocumentoAExpediente(ExpedienteDTO expedienteDTO,
			DocumentoDTO documentoDTO, FuncionarioDTO funcionarioDTO, Actividades actividad, TipoDocumento tipoDocumento) throws NSJPNegocioException;

}
