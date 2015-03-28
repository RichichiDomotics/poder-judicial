/**
* Nombre del Programa : IngresarPersonaService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Abril 2012
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar los metodos para consulta de Persona
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
package mx.gob.segob.nsjp.service.persona.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dao.persona.PersonaDAO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Persona;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;
import mx.gob.segob.nsjp.service.persona.ConsultarPersonaService;
import mx.gob.segob.nsjp.service.persona.impl.transform.PersonaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Implementacion del servicio para realizar los metodos para consulta de Persona
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ConsultarPersonaServiceImpl implements ConsultarPersonaService {

	private final static Logger LOGGER = Logger.getLogger(ConsultarPersonaServiceImpl.class); 
	
	@Autowired
	private PersonaDAO personaDAO;	
	@Autowired
	private DomicilioDAO domicilioDAO;
	
	@Override
	public PersonaDTO consultaPersonaPorId(PersonaDTO personaDTO)
			throws NSJPNegocioException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("/**** SERVICIO PARA CONSULTAR PERSONA POR ID ****/");
		}
		
		if(personaDTO.getElementoId()==null) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		Persona respPersona = personaDAO.read(personaDTO.getElementoId());
				
		if(respPersona!=null) {
			PersonaDTO retPersonaDto = PersonaTransformer.transformarPersona(respPersona);;
			
			Domicilio residencia = domicilioDAO.obtenerDomicilioByRelacion(retPersonaDto.getElementoId(), new Long(Relaciones.RESIDENCIA.ordinal()));
			Domicilio notificacion = domicilioDAO.obtenerDomicilioByRelacion(retPersonaDto.getElementoId(), new Long(Relaciones.NOTIFICACION.ordinal()));
			
			if (residencia!=null) {
				retPersonaDto.setDomicilio(DomicilioTransformer.transformarDomicilio(residencia));
			}
			
			if (notificacion!=null) {
				retPersonaDto.setDomicilioNotificacion(DomicilioTransformer.transformarDomicilio(notificacion));
			}
			return retPersonaDto;
		}
		
		return null;
	}

}
