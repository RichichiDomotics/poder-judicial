/**
* Nombre del Programa : RegistrarMedidaAlternaService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 6 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para registrar una medida alterna
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;

/**
 * Contrato del servicio para registrar una medida alterna.
 * @author cesarAgustin
 *
 */
public interface RegistrarMedidaAlternaPJService {

	/**
	 * Resgistra una medida alterna a su carpeta de ejecucion correspondiente
	 * @author cesarAgustin
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long resgistrarMedidaAlterna(MedidaAlternaDTO medidaAlternaDTO) throws NSJPNegocioException;
	
}
