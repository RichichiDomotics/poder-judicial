/**
* Nombre del Programa : IngresarPersonaService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar la creacion de una Persona
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
package mx.gob.segob.nsjp.service.persona;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;

/**
 * Contrato del servicio para realizar la creacion de una Persona.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface IngresarPersonaService {

	/**
	 * 
	 * @param personaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long ingresarPersona(PersonaDTO personaDTO) throws NSJPNegocioException ;

	/**
	 * Realiza el ingreso de la informacion  de una persona relacionada a una organizacion
	 * @param personaDTO
	 * @param organizacionDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public PersonaDTO ingresarPersonaOrganizacion(PersonaDTO personaDTO,
			OrganizacionDTO organizacionDTO) throws NSJPNegocioException;
	
}
