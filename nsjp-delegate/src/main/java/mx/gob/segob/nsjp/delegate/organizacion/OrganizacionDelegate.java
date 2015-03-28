/**
* Nombre del Programa : OrganizacionDelegate.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contarato del delegate para los servicios relacionados con Organizacion
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
package mx.gob.segob.nsjp.delegate.organizacion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;

/**
 * Contarato del delegate para los servicios relacionados con Organizacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface OrganizacionDelegate {
	
	/**
	 * Realiza el ingreso de la informacion de una nueva organizacion
	 * @param organizacionDTO
	 * @return El obejto con el id de la organizacion generada
	 * @throws NSJPNegocioException
	 */
	public OrganizacionDTO ingresarOrganizacion (OrganizacionDTO organizacionDTO) throws NSJPNegocioException;

	/**
	 * Realiza el ingreso de la informacion  de una persona relacionada a una organizacion
	 * @param personaDTO
	 * @param organizacionDTO
	 * @return PersonaDTO con el id de la persona generada
	 * @throws NSJPNegocioException
	 */
	public PersonaDTO ingresarPersonaOrganizacion (PersonaDTO personaDTO, OrganizacionDTO organizacionDTO) throws NSJPNegocioException;
	
}
