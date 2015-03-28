/**
* Nombre del Programa : ConsultarResolutivosAudienciaService.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.audiencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoViewDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface ConsultarResolutivosAudienciaService {

	/**
	 * Operación que realiza la funcionalidad de consultar los resolutivos de una audiencia.
	 * 
	 * @param Recibe el identificador de la Audiencia 
	 * @return Devuelve un listado de textos que corresponden a los resolutivos.
	 * @throws NSJPNegocioException
	 */
	List<ResolutivoDTO> consultarResolutivosAudiencia(Long idAudiencia) throws NSJPNegocioException;

	/**
	 * Operación que realiza la funcionalidad de consultar los resolutivos de una audiencia.
	 * 
	 * @param Recibe el identificador de la Audiencia 
	 * @return Devuelve un listado de textos que corresponden a los resolutivos.
	 * @throws NSJPNegocioException
	 */
	List<ResolutivoViewDTO> consultarResolutivosByAudienciaId(Long idAudiencia) throws NSJPNegocioException;
	
	/**
	 * Operación que consulta los documentos asociados a un resolutivo pero sin archivo digítal asociao
	 * @param idAudiencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<ResolutivoViewDTO> consultarResolutivosByAudienciaIdWithDocumento (Long idAudiencia) throws NSJPNegocioException;
	
	/**
	 * Operación que consulta los documentos asociados a un resolutivo pero CON archivo digítal asociao
	 * @param idAudiencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<ResolutivoViewDTO> consultarResolutivosByAudienciaIdWithDocumentoAndArchDigital (Long idAudiencia) throws NSJPNegocioException;
	
}
