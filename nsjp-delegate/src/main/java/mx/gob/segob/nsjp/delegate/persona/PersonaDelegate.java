/**
* Nombre del Programa : PersonaDelegate.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del delegate para los servicios relacionados con Persona
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
package mx.gob.segob.nsjp.delegate.persona;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;

/**
 * Contrato del delegate para los servicios relacionados con Persona.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface PersonaDelegate {

	/**
	 * 
	 * @param personaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long ingresarPersona (PersonaDTO personaDTO) throws NSJPNegocioException;
		/**
	 * Método para conectar el servicio para la consulta de la Persona
	 * @param persona
	 * @return
	 * @throws NSJPNegocioException
	 */
	public PersonaDTO consultaPersonaPorID(PersonaDTO persona) throws NSJPNegocioException;
	
}
