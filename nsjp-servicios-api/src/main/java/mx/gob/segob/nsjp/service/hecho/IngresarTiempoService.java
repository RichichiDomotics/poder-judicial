/**
* Nombre del Programa : IngresarTiempoService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar el ingreso de un Tiempo
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
import mx.gob.segob.nsjp.dto.hecho.TiempoDTO;

/**
 * Contrato del servicio para realizar el ingreso de un Tiempo.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface IngresarTiempoService {

	/**
	 * Ingresar un Tiempo determinado.
	 * @param tiempoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public TiempoDTO ingresarTiempo (TiempoDTO tiempoDTO) throws NSJPNegocioException;
}
