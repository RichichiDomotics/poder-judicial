/**
* Nombre del Programa : IngresarPersonaServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar la creacion de una Persona
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
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.enums.relacion.TipoRelacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.persona.PersonaDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.model.MedioDeContacto;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Persona;
import mx.gob.segob.nsjp.service.domicilio.IngresarDomicilioService;
import mx.gob.segob.nsjp.service.persona.IngresarPersonaService;
import mx.gob.segob.nsjp.service.persona.impl.transform.MedioDeContactoTransformer;
import mx.gob.segob.nsjp.service.persona.impl.transform.NombreDemograficoTransformer;
import mx.gob.segob.nsjp.service.persona.impl.transform.PersonaTransformer;
import mx.gob.segob.nsjp.service.relacion.GenerarRelacionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion del servicio para realizar la creacion de una Persona.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
public class IngresarPersonaServiceImpl implements IngresarPersonaService {
	
	/**
	 * 
	 */
	private final static Logger logger = Logger.getLogger(IngresarPersonaServiceImpl.class);
	
	@Autowired
	private PersonaDAO personaDAO;
	
	@Autowired
	private IngresarDomicilioService ingresarDomicilioService;
	
	@Autowired
	private GenerarRelacionService generarRelacionService;
		

	@Override
	public Long ingresarPersona(PersonaDTO personaDTO)  throws NSJPNegocioException  {		
		
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GENERAR UNA PERSONA ****/");
					
		Long idDomicilio = 0L;
		Long idDomicilioNot = 0L;
		Long idPersona = 0L;
		
		Set<NombreDemografico> setNomDemografico = new HashSet<NombreDemografico>();
		Set<MedioDeContacto> colMedContacto = new HashSet<MedioDeContacto>();
		Persona persona = PersonaTransformer.transformarPersona(personaDTO);

		//DATOS GENERALES
		if (personaDTO.getNombresDemograficoDTO()!=null) {
			for (NombreDemograficoDTO nomDemograficoDTO : personaDTO.getNombresDemograficoDTO()) {
				NombreDemografico nomDemografico = NombreDemograficoTransformer.transformarNombreDemografico(nomDemograficoDTO);
				nomDemografico.setPersona(persona);
				setNomDemografico.add(nomDemografico);
				//nombreDemograficoDAO.create(nomDemografico);
			}
			persona.setNombreDemograficos(setNomDemografico);
		}
		
		//MEDIOS DE CONTACTO -  Telefono y/o correo
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
		
		//DOMICILIO - COORDENADAS GEOGRAFICAS
		if (personaDTO.getDomicilio()!=null)
			idDomicilio = ingresarDomicilioService.ingresarDomicilio(personaDTO.getDomicilio());
			
		//DOMICILIO DE NOTIFICACION - COORDENADAS GEOGRAFICAS
		if (personaDTO.getDomicilioNotificacion()!=null)	
			idDomicilioNot = ingresarDomicilioService.ingresarDomicilio(personaDTO.getDomicilioNotificacion());		

		idPersona = personaDAO.create(persona);
		
		//RELACIONES
		if (idDomicilio>0) 
			generarRelacionService.generarRelacion(idPersona, idDomicilio, Relaciones.RESIDENCIA, TipoRelacion.IMPLICITA.getValorId().shortValue());
			
		if (idDomicilioNot>0)
			generarRelacionService.generarRelacion(idPersona, idDomicilioNot, Relaciones.NOTIFICACION, TipoRelacion.IMPLICITA.getValorId().shortValue());
		
		return idPersona;
	}


	@Override
	public PersonaDTO ingresarPersonaOrganizacion(PersonaDTO personaDTO,
			OrganizacionDTO organizacionDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("/**** SERVICIO PARA INGRESAR UNA PERSONA RELACIONADA A UNA ORGANIZACION ****/");
			logger.debug("organizacionDTO==null :: " + organizacionDTO);
			logger.debug("organizacionDTO.getElementoId() ::" + organizacionDTO.getElementoId());
			logger.debug("personaDTO :: " + personaDTO);
		}
		
		if (organizacionDTO==null || organizacionDTO.getElementoId()==null
				|| personaDTO.getCalidadDTO()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		personaDTO.setEsVivo(new Boolean(true));
		personaDTO.setValorIdElemento(new ValorDTO(Elementos.PERSONA.getValorId()));
		Long personaId = ingresarPersona(personaDTO);
		
		if (personaId>0 && personaDTO.getCalidadDTO().getCalidades().equals(Calidades.CONTACTO_ORGANIZACION)) {
			generarRelacionService.generarRelacion(organizacionDTO.getElementoId(), personaId, Relaciones.CONTACTO, TipoRelacion.IMPLICITA.getValorId().shortValue());
		} else if (personaId>0 && personaDTO.getCalidadDTO().getCalidades().equals(Calidades.REPRESENTANTE_LEGAL)) {
			generarRelacionService.generarRelacion(organizacionDTO.getElementoId(), personaId, Relaciones.REPRESENTANTE_LEGAL, TipoRelacion.IMPLICITA.getValorId().shortValue());
		}
	
		personaDTO.setElementoId(personaId);
		return personaDTO;

	}

}
