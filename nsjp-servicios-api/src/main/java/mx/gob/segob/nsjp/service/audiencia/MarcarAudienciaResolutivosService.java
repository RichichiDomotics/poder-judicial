/**
* Nombre del Programa : MarcarAudienciaResolutivosService.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23/06/2011
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
public interface MarcarAudienciaResolutivosService {

	/**
	 * Operación que realiza la funcionalidad de marcar la Audiencia con la transcripción del resolutivo de audiencia.
	 * 
	 * @param Recibe el identificador de la Audiencia.
	 * @return Devuelve un valo boleano, verdadero en caso de que haya realizado la marca, falso en caso contrario
	 * @throws NSJPNegocioException
	 */
	void marcarAudienciaResolutivos(Long idAudiencia) throws NSJPNegocioException;

}
