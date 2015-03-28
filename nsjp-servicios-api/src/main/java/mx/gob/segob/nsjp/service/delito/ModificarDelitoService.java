/**
* Nombre del Programa : ModificarDelitoService.java
* Autor                            : cesarAgutin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para actualizar la informacion del Delito
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
package mx.gob.segob.nsjp.service.delito;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;

/**
 * Contrato del servicio para actualizar la informacion del Delito.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ModificarDelitoService {

	/**
	 * Realiza la actualizacion de la informacion del delito 
	 * @param delitosDTO
	 * @throws NSJPNegocioException
	 */
	public void modificarDelito (List<DelitoDTO> delitosDTO) throws NSJPNegocioException;
}
