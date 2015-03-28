/**
* Nombre del Programa : IngresarPersonaServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Abril 2012
* Marca de cambio        : N/A
* Descripcion General    : Metodos para realizar las pruebas unitarias de las consultas de Persona
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
package mx.gob.segob.nsjp.service.test.persona.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.service.persona.ConsultarPersonaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Metodos para realizar las pruebas unitarias de las consultas de Persona
 * @author cesaragustin
 *
 */
public class ConsultarPersonaServiceImplTest extends
		BaseTestServicios<ConsultarPersonaService> {
	
	public void testConsultarPersonaPorId () {
		PersonaDTO personaDTO = new PersonaDTO(8072L);
				
		try {
			PersonaDTO resp = service.consultaPersonaPorId(personaDTO);
			assertNotNull("El resultado no puede ser nulo",resp);			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
		
	}

}
