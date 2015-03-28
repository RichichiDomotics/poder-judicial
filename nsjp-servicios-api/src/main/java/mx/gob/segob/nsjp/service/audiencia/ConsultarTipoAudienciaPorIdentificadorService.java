/**
* Nombre del Programa : ConsultarTipoAudienciaPorIdentificadorService.java
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
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface ConsultarTipoAudienciaPorIdentificadorService {

	/**
	 * Operación que realiza la funcionalidad de consultar el tipo de audiencia asociado al identificador de la audiencia.
	 * 
	 * @param audiencia: El identificador de la audiencia.
	 * @return Devuelve el tipo de audiencia asociado al identificador de la audiencia, en  caso contrario, regresa null.
	 * @throws NSJPNegocioException
	 */
	ValorDTO consultarTipoAudienciaPorIdentificador(AudienciaDTO audienciaDTO)throws NSJPNegocioException;

}
