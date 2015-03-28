/**
* Nombre del Programa : CalcularCargaTrabajoService.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/06/2011
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
package mx.gob.segob.nsjp.service.audiencia;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface CalcularCargaTrabajoService {

	/**
	 * Operación que realiza la funcionalidad de calcular la carga de trabajo de la audiencia, mediante la siguiente fórmula:
	 * 
	 * Carga de trabajo = complejidad de la audiencia + ln (sumatoria relaciones de probables responsable - delito)
	 *  
	 * @param complejidadAudiencia
	 * @param sumatoriaPRDelito
	 * @return Devuelve la carga de trabajo de la audiencia, en  caso contrario, regresa null.
	 * @throws NSJPNegocioException
	 */
	Double calcularCargaTrabajo(Long complejidadAudiencia, Long sumatoriaPRDelito)throws NSJPNegocioException;

}
