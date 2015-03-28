/**
* Nombre del Programa : RelacionService.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 May 2011
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
package mx.gob.segob.nsjp.service.relacion;

import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesar
 *
 */
public interface GenerarRelacionService {

	/**
	 * Genara la relacion deseada en base al tipo de relacion y los elementos recibidos como parametros
	 * @param sujeto
	 * @param complemento
	 * @param relacion
	 * @return
	 */
	public Long generarRelacion(Long sujeto, Long complemento, Relaciones relacion, Short tipoRelacion) throws NSJPNegocioException ;

	
}
