/**
* Nombre del Programa : GenerarDocumentoDeMedidaIncumplidaManualmenteService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 04 Nov 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para generar docuemento de Medida incumplida
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
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;

/**
 * Contrato del servicio para generar docueneto de medida incumplida.
 * @version 1.0
 * @author rgama
 *
 */
public interface GenerarDocumentoDeMedidaIncumplidaManualmenteService {

	/**
	 * Permite generar Documento de Medida incumplida
	 * @return List<DocumentoDTO> 
	 * @throws NSJPNegocioException
	 */
	public DocumentoDTO generarDocumentoDeMedidaIncumplida(Long idMedida) throws NSJPNegocioException;
	
}
