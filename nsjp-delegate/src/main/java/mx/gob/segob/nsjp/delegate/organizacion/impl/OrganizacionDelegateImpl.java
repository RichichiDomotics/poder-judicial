/**
* Nombre del Programa : OrganizacionDelegateImpl.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.delegate.organizacion.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.organizacion.OrganizacionDelegate;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.service.organizacion.IngresarOrganizacionService;
import mx.gob.segob.nsjp.service.persona.IngresarPersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesar
 *
 */
@Service ("organizacionDelegate")
public class OrganizacionDelegateImpl implements OrganizacionDelegate {

	@Autowired
	private IngresarOrganizacionService ingresarOrganizacionService;
	@Autowired
	private IngresarPersonaService ingresarPersonaService;
	
	@Override
	public OrganizacionDTO ingresarOrganizacion(OrganizacionDTO organizacionDTO)
			throws NSJPNegocioException {
		
		return this.ingresarOrganizacionService.ingresarOrganizacion(organizacionDTO);
	}

	@Override
	public PersonaDTO ingresarPersonaOrganizacion(PersonaDTO personaDTO,
			OrganizacionDTO organizacionDTO) throws NSJPNegocioException {		
		return this.ingresarPersonaService.ingresarPersonaOrganizacion(personaDTO, organizacionDTO);
	}

}
