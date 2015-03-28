/**
* Nombre del Programa : ObtenerMedidasAlternasService.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 3 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : 
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
package mx.gob.segob.nsjp.service.medidasalternas;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;

/**
 * Contrato Service para los prsuntos responsables por causa
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface ObtenerMedidasAlternasService {
	
	/**
	 * Permite consultar las medidas alternas que cuentan con una fecha de incumplimiento
	 * del dia anterior a la fecha actual
	 * @return List<MedidaAlternaDTO>
	 * @throws NSJPNegocioException
	 */
	public List<MedidaAlternaDTO> obtenerMedidasAlternasIncumplidasDelDiaAnterior() throws NSJPNegocioException; 
}
