/**
* Nombre del Programa : IngresarPersonaService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Abril 2012
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar los metodos para consulta de Persona
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
 * Contrato del servicio para realizar los metodos para consulta de Persona.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ConsultarPersonaService {
	
	/**
	 * Obtiene la informacion de Persona, de acuerdo al identificador de Persona. 
	 * @param personaDTO.elementoId
	 * @return
	 * @throws NSJPNegocioException
	 */
	public PersonaDTO consultaPersonaPorId(PersonaDTO personaDTO) throws NSJPNegocioException;

}
