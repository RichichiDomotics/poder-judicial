/**
* Nombre del Programa : IngresarOrganizacionService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servico para realizar el ingreso de una nueva organizacion
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
 * Contrato del servico para realizar el ingreso de una nueva organizacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface IngresarOrganizacionService {

	/**
	 * Metodo para generar el registro de una nueva organizacion.
	 * @param organizacionDTO
	 * @return La organizacion creada con su identificador asignado.
	 * @throws NSJPNegocioException
	 */
	public OrganizacionDTO ingresarOrganizacion (OrganizacionDTO organizacionDTO) throws NSJPNegocioException; 
}
