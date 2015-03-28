/**
* Nombre del Programa : IngresarHechoService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar el ingreso de un Hecho
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
package mx.gob.segob.nsjp.service.hecho;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;

/**
 * Contrato del servicio para realizar el ingreso de un Hecho.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface IngresarHechoService {
	
	/**
	 * Ingresa un hecho para asociarlo con el expedinte correspondiente
	 * @param hechoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public HechoDTO ingresarHecho (HechoDTO hechoDTO) throws NSJPNegocioException;
}
