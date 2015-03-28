/**
* Nombre del Programa : GuardarCadenaCustodiaService.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18/07/2011
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
package mx.gob.segob.nsjp.service.cadenadecustodia;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface GuardarCadenaCustodiaService {

	/**
     * Operación que realiza la funcionalidad de guardar la cadena de custodia y la funcionalidad de asociar la cadena de custodia a un expediente.
     * @param custodiaDTO: Objeto
     * @param expedienteDTO: Id Expediente
     * @return
     * @throws NSJPNegocioException
     */
	CadenaDeCustodiaDTO guardarCadenaCustodia(CadenaDeCustodiaDTO custodiaDTO,
			ExpedienteDTO expedienteDTO)throws NSJPNegocioException;

}
