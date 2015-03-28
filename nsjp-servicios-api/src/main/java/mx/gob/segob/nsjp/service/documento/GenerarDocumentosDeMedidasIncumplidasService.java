/**
* Nombre del Programa : GenerarDocumentosDeMedidasIncumplidasService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 04 Nov 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar las consultas de Documento
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;

/**
 * Contrato del servicio para generar documentos de medidas incumplidas.
 * @version 1.0
 * @author rgama
 *
 */
public interface GenerarDocumentosDeMedidasIncumplidasService {

	/**
	 * Permite generar Documentos de Medidas incumplidas
	 * @return List<DocumentoDTO> 
	 * @throws NSJPNegocioException
	 */
	List<DocumentoDTO> generarDocumentosDeMedidasIncumplidas() throws NSJPNegocioException;
	
}
