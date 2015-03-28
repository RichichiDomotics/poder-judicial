/**
 * 
 */
package mx.gob.segob.nsjp.delegate.policiaministerial;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO;

/**
 * @author adrian
 *
 */
public interface LineaInvestigacionDelegate {
	
	/**
	 * Operación que permite guardar / actualizar una Línea de investigación
	 * guardar: Id = 0 o NULL
	 * @param investigacionDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long guardarLineaInvestigacion(LineaInvestigacionDTO investigacionDTO)throws NSJPNegocioException;
	
	/**
	 * Operación que permite consultar una línea de investigación por su ID
	 * @param investigacionDTO: ID
	 * @return
	 * @throws NSJPNegocioException
	 */
	public LineaInvestigacionDTO consultarLineaInvestigacion(LineaInvestigacionDTO investigacionDTO)throws NSJPNegocioException;
	
	/**
	 * Operación que permite consultar las líneas de investigación que pertenezcan a un ExpedienteId
	 * @param ExpedienteDTO: ID
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<LineaInvestigacionDTO> consultarLineasInvestigacionXExpedienteId(ExpedienteDTO expedienteDTO)throws NSJPNegocioException;
	
	/**
	 * Operación que permite generar el PDF de una Línea de Investigación y sus comentarios
	 * @param investigacionDTO
	 * @param esGuardado: True=Si se guarda en BD, False=No se guarda en BD
	 * @return
	 * @throws NSJPNegocioException
	 */
	public DocumentoDTO crearDocumentoLineaInvestigacion(LineaInvestigacionDTO investigacionDTO,Boolean esGuardado)throws NSJPNegocioException;

}
