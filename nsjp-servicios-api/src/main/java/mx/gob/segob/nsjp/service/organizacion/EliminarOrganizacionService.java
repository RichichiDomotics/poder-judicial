/**
* Nombre del Programa : EliminarOrganizacionService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para elimianar una organizacion
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
package mx.gob.segob.nsjp.service.organizacion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;

/**
 * Contrato del servicio para elimianar una organizacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface EliminarOrganizacionService {

	/**
	 * 
	 * @param organizacionDTO
	 * @throws NSJPNegocioException
	 */
	public void eliminarOrganizacion (OrganizacionDTO organizacionDTO) throws NSJPNegocioException;
}
