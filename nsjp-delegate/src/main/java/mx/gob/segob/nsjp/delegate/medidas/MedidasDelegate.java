/**
* Nombre del Programa : MedidasDelegate.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 03 Nov 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para delegate de Medidas
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
package mx.gob.segob.nsjp.delegate.medidas;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;

/**
 * Contrato para delegate de Medidas.
 * @version 1.0
 * @author rgama
 *
 */
public interface MedidasDelegate {
	/**
	 * Cambia el estatus de la(s) Medida(s) ya sea Cautelar o Alternativa las cuales
	 * tengan "Fecha de fin" vencida  
	 * @return List<MedidaDTO> 
	 * @throws NSJPNegocioException
	 */
	List<MedidaDTO> cambiarEstatusAMedidasConFechaFinVencidaService() throws NSJPNegocioException;
	

	/**
	 * Permite generar y enviar el documento de una medida incumplida a PJ(desde SSP)
	 * @param idMedida Identificador de la medida
	 * @return DocumentoDTO El identificador del documento generado
	 * @throws NSJPNegocioException
	 */
	public DocumentoDTO generarDocumentoDeMedidaIncumplida(Long idMedida) throws NSJPNegocioException;
	
}
