/**
* Nombre del Programa : ModificarPersonaServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para modificar la informacion de una Persona
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.persona.NombreDemograficoDAO;
import mx.gob.segob.nsjp.dao.persona.PersonaDAO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.model.MedioDeContacto;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Persona;
import mx.gob.segob.nsjp.service.persona.ModificarPersonaService;
import mx.gob.segob.nsjp.service.persona.impl.transform.MedioDeContactoTransformer;
import mx.gob.segob.nsjp.service.persona.impl.transform.NombreDemograficoTransformer;
import mx.gob.segob.nsjp.service.persona.impl.transform.PersonaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para modificar la informacion de una Persona.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ModificarPersonaServiceImpl implements ModificarPersonaService {

	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(ModificarPersonaServiceImpl.class);
	
	@Autowired
	private PersonaDAO personaDAO; 
	@Autowired 
	private NombreDemograficoDAO nombreDemograficoDAO; 
	
	@Override
	public void modificarPersona(PersonaDTO personaDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA MODIFICAR LA INFORMACION DE PERSONA ****/");
		if(personaDTO== null && personaDTO.getElementoId()== null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		
		Set<MedioDeContacto> colMedContacto = new HashSet<MedioDeContacto>();
		
		Persona persona = personaDAO.read(personaDTO.getElementoId());
		Persona personaMod = PersonaTransformer.transformarPersona(personaDTO);
		persona = PersonaTransformer.transformarPersona(persona, personaMod);
		
		Set<NombreDemografico> setNomDemografico = new HashSet<NombreDemografico>();
		//ACtualizar el Nombre demografico de la persona (es único)
		List<NombreDemografico> nombresDemograficos = nombreDemograficoDAO.consutarNombresByInvolucrado(personaDTO.getElementoId());
				
		//Verificar si se crea o modifica el Nombre Demografico		
		if (personaDTO.getNombresDemograficoDTO()!=null || !personaDTO.getNombresDemograficoDTO().isEmpty()) {
			
			NombreDemograficoDTO nombreDemograficoDTO = personaDTO.getNombresDemograficoDTO().get(0);
			NombreDemografico nombresDemograficoBD = null;
			
			//Si existe en BD, se actualiza con los nuevos datos
			if(!nombresDemograficos.isEmpty() && nombresDemograficos.get(0).getNombreDemograficoId()!= null ){
				nombresDemograficoBD = nombresDemograficos.get(0);
				nombresDemograficoBD = NombreDemograficoTransformer.transformarNombreDemograficoUpdate(nombresDemograficoBD,nombreDemograficoDTO);	
			}
			else{ // Es nuevo
				nombresDemograficoBD =  NombreDemograficoTransformer.transformarNombreDemografico(nombreDemograficoDTO);
				nombresDemograficoBD.setPersona(persona);
				setNomDemografico.add(nombresDemograficoBD);
				persona.setNombreDemograficos(setNomDemografico);
			}	
		}

		if (personaDTO.getTelefonosDTO()!=null || personaDTO.getCorreosDTO()!=null) {
			for (TelefonoDTO telefonoDTO : personaDTO.getTelefonosDTO()) {
				MedioDeContacto telefono = MedioDeContactoTransformer.transformarTelefono(telefonoDTO);
				telefono.setPersona(persona);
				colMedContacto.add(telefono);
			}
			
			for (CorreoElectronicoDTO correoDTO : personaDTO.getCorreosDTO()) {
				MedioDeContacto correo = MedioDeContactoTransformer.transformarCorreo(correoDTO);
				correo.setPersona(persona);
				colMedContacto.add(correo);
			}
			persona.setMedioDeContactos(colMedContacto);
		}		
		
		//Actualizar Persona
		personaDAO.update(persona);
	}

}
