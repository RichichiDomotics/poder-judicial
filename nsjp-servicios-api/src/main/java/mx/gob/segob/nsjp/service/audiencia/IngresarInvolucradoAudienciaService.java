/**
* Nombre del Programa : IngresarInvolucradoAudienciaService.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21/09/2011
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
 * Interfaz del servicio de negocio para asociar involucrados a una audiencia
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface IngresarInvolucradoAudienciaService {
	/**
	 * Asocia un involucrado existente a una audiencia existente, si 
	 * el involucrado ya está asociado a la audiencia no envía error, se hace una validación primero
	 * @param involucradoId ID del involucrado a asociar
	 * @param AudienciaId ID de la audiencia a asociar
	 * @throws NSJPNegocioException
	 */
	void asociarInvolucradoAAudiencia(Long involucradoId,Long AudienciaId) throws NSJPNegocioException;
	
}
