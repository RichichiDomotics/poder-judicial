/**
* Nombre del Programa : IngresarDomicilioService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar la insercion de Domicilio
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
 * Contrato del servicio para realizar la insercion de Domicilio.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface IngresarDomicilioService {

	/**
	 * Metodo para realizar la creacion de un nuevo domicilio
	 * @param domicilioDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long ingresarDomicilio (DomicilioDTO domicilioDTO) throws NSJPNegocioException;
}
