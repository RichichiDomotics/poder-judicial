/**
* Nombre del Programa : GuardarEvidenciaService.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19/07/2011
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
package mx.gob.segob.nsjp.service.evidencia;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface GuardarEvidenciaService {

	/**
     * Operación que realiza la funcionalidad de guardar los datos capturados de la evidencia
     * Los datos qye guarda son
     * - Descripcion o información de la evidencia
     * - Fecha de levantamiento
     * - Origen de la evidencia
     * - Cadena de Custodia a la que pertenece
     * 
     * @param evidenciaDTO
     * @return
     * @throws NSJPNegocioException
     */
	Long guardarEvidencia(EvidenciaDTO evidenciaDTO) throws NSJPNegocioException;
	
	

}
