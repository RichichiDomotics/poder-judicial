/**
* Nombre del Programa : PersonaDelegateImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del delegate para los servicios relacionados con Persona
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
package mx.gob.segob.nsjp.delegate.persona.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.persona.PersonaDelegate;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.service.persona.ConsultarPersonaService;
import mx.gob.segob.nsjp.service.persona.IngresarPersonaService;

/**
 * Implementacion del delegate para los servicios relacionados con Persona.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service("personaDelegate")
public class PersonaDelegateImpl implements PersonaDelegate {

	@Autowired
	private IngresarPersonaService ingresarPersonaService;
	@Autowired
	private ConsultarPersonaService consultarPersonaService;
	
	@Override
	public Long ingresarPersona(PersonaDTO personaDTO)
			throws NSJPNegocioException {
		
		return this.ingresarPersonaService.ingresarPersona(personaDTO);
	}

	@Override
	public PersonaDTO consultaPersonaPorID(PersonaDTO personaDTO)
			throws NSJPNegocioException {		
		return this.consultarPersonaService.consultaPersonaPorId(personaDTO);
	}

}
