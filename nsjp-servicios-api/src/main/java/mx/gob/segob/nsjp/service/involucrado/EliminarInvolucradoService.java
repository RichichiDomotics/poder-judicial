/**
* Nombre del Programa : EliminarInvolucradoService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para el servicio de eliminar un involucrado
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
package mx.gob.segob.nsjp.service.involucrado;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * Contrato para el servicio de eliminar un involucrado.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface EliminarInvolucradoService {

	/**
	 * Metodo para eliminar toda la informacion de un involucrado
	 * @param involucradoDTO
	 * @throws NSJPNegocioException
	 */
	public void eliminarInvolucrado (InvolucradoDTO involucradoDTO) throws NSJPNegocioException;
	
}
