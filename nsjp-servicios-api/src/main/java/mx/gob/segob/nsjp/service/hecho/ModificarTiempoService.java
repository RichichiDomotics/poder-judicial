/**
* Nombre del Programa : ModificarTiempoService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 04/10/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
 * Contrato del servicio para realizar la modificación del Tiempo, asociado al hecho.
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface ModificarTiempoService {

	/**
	 * Servicio que modifica el Tiempo.
	 * 
	 * @param tiempoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	TiempoDTO modificarTiempo(TiempoDTO tiempoDTO) throws NSJPNegocioException;
	
}
