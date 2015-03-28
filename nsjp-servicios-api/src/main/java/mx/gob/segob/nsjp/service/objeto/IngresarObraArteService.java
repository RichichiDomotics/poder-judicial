/**
* Nombre del Programa : IngresarObraArteService.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para ingresar Obra de Arte.
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
package mx.gob.segob.nsjp.service.objeto;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.objeto.ObraArteDTO;

/**
 * Contrato del servicio para ingresar Obra de Arte.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarObraArteService {
	
	/**
	 * 
	 * @param obraArteDto
	 * @return Long
	 * @throws NSJPNegocioException
	 */
	Long ingresarObraArte(ObraArteDTO obraArteDto)throws NSJPNegocioException;

}
