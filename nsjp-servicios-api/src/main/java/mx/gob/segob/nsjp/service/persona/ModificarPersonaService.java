/**
* Nombre del Programa : ModificarPersonaService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para modificar la informacion de una Persona
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
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;

/**
 * Contrato del servicio para modificar la informacion de una Persona.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ModificarPersonaService {

	/**
	 * 
	 * @param personaDTO
	 * @throws NSJPNegocioException
	 */
	public void modificarPersona (PersonaDTO personaDTO) throws NSJPNegocioException;
}
