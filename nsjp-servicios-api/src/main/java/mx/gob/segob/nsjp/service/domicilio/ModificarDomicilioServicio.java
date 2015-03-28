/**
* Nombre del Programa : ModificarDomicilioServicio.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para modificar la informacion de un domicilio
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
package mx.gob.segob.nsjp.service.domicilio;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;

/**
 * Contrato del servicio para modificar la informacion de un domicilio.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ModificarDomicilioServicio {

	/**
	 * 
	 * @param domicilioDTO
	 * @throws NSJPNegocioException
	 */
	public void modificarDomicilio (DomicilioDTO domicilioDTO) throws NSJPNegocioException;
}
