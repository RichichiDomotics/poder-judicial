/**
* Nombre del Programa : ConsultarResumenExpedienteParaDocumentoService.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Consulta la información de un expediente y la coloca de forma
* que la capa de presentación pueda dar un resumen al usuario del expediente y todos sus elementos
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
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.ParametrosDocumentoDTO;

/**
 * Consulta la información de un expediente y la coloca de forma
 * que la capa de presentación pueda dar un resumen al usuario del expediente y todos sus elementos
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface ConsultarResumenExpedienteParaDocumentoService {

	/**
	 * Consulta la información de un expediente y la coloca de forma
	 * que la capa de presentación pueda dar un resumen al usuario del expediente y todos sus elementos
	 * @param expediente Expediente a consultar
	 * @return Objeto de expediente debidamente procesado para la capa de vista
	 */
	ParametrosDocumentoDTO consultarResumenExpedienteParaDocumento(ExpedienteDTO expediente) throws NSJPNegocioException;
	
	public ParametrosDocumentoDTO consultarResumenExpedienteParaDocumento (AudienciaDTO audiencia) throws NSJPNegocioException;
	
}
