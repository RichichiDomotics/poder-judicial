/**
* Nombre del Programa : PersonaTransformer.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para transfomar objetos Persona y PersonaDTO
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
package mx.gob.segob.nsjp.service.persona.impl.transform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.MedioDeContacto;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Persona;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Clase para transfomar objetos Persona y PersonaDTO.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class PersonaTransformer {

	public static Persona transformarPersona (PersonaDTO personaDTO) {		
		Persona persona = new Persona();
		Calidad calidad = new Calidad();
		
		if (personaDTO.getEsVivo()!=null)
			persona.setEsVivo(personaDTO.getEsVivo().equals(new Long(1))?true:false);
		else 
			persona.setEsVivo(true);
		
		persona.setFolioIdentificacion(personaDTO.getFolioIdentificacion());
		if (personaDTO.getValorIdIdentificaion()!=null) 
			persona.setTipoDoctoId(new Valor(personaDTO.getValorIdIdentificaion().getIdCampo()));							
						
		persona.setFechaCreacionElemento(personaDTO.getFechaCreacionElemento());		
		if (personaDTO.getCalidadDTO()!=null) {
			calidad.setDescripcionEstadoFisico(personaDTO.getCalidadDTO().getDescripcionEstadoFisico());
			calidad.setTipoCalidad(new Valor(personaDTO.getCalidadDTO().getCalidades().getValorId()));			
			persona.setCalidad(calidad);
		}
		if (personaDTO.getExpedienteDTO()!=null) 
			persona.setExpediente(new Expediente(personaDTO.getExpedienteDTO().getExpedienteId()));		
		if (personaDTO.getValorIdElemento()!=null) 
			persona.setTipoElemento(new Valor(personaDTO.getValorIdElemento().getIdCampo()));
		else 
			persona.setTipoElemento(new Valor(Elementos.PERSONA.getValorId()));
				
		return persona;
	}

	public static Persona transformarPersona(Persona persona, Persona personaMod) {		
		
		persona.setEsVivo(personaMod.getEsVivo());
		persona.setFolioIdentificacion(personaMod.getFolioIdentificacion());		
		persona.setTipoDoctoId(personaMod.getTipoDoctoId());													
		persona.setFechaCreacionElemento(personaMod.getFechaCreacionElemento());							
		persona.setCalidad(personaMod.getCalidad());				
		persona.setExpediente(personaMod.getExpediente());				
		persona.setTipoElemento(personaMod.getTipoElemento());
				
		return persona;
	}

	public static PersonaDTO transformarPersona(Persona persona) {
		PersonaDTO personaDTO = new PersonaDTO();
		
		//Propiedades persona
		if (persona.getTipoDoctoId()!=null) {
			personaDTO.setValorIdIdentificaion(new ValorDTO(persona.getTipoDoctoId().getValorId(), persona.getTipoDoctoId().getValor()));
		}
		personaDTO.setEsVivo(persona.getEsVivo());
		personaDTO.setFolioIdentificacion(persona.getFolioIdentificacion()!=null?persona.getFolioIdentificacion():"");
		
		List<TelefonoDTO> telefonosDTO = new ArrayList<TelefonoDTO>();
		List<CorreoElectronicoDTO> correosElecDTO = new ArrayList<CorreoElectronicoDTO>();		
		for (MedioDeContacto medContacto : persona.getMedioDeContactos()) {
			
			if (medContacto instanceof Telefono) {
				telefonosDTO.add(MedioDeContactoTransformer.transformarTelefono((Telefono)medContacto));
			} else if (medContacto instanceof CorreoElectronico) {
				correosElecDTO.add(MedioDeContactoTransformer.transformarCorreo((CorreoElectronico)medContacto));
			}			
		}
		personaDTO.setTelefonosDTO(telefonosDTO);
		personaDTO.setCorreosDTO(correosElecDTO);
		
		List<NombreDemograficoDTO> nombresDTO = new ArrayList<NombreDemograficoDTO>();
		for(NombreDemografico nombre : persona.getNombreDemograficos()) {
			nombresDTO.add(NombreDemograficoTransformer.transformarNombreDemografico(nombre));
		}
		personaDTO.setNombresDemograficoDTO(nombresDTO);
		
		//Propiedades elemento
		personaDTO.setElementoId(persona.getElementoId());
		
		CalidadDTO calidadDto = new CalidadDTO();
		Calidad calidad = persona.getCalidad();
		calidadDto.setCalidadId(calidad.getCalidadId());
		calidadDto.setValorIdCalidad(new ValorDTO(calidad.getTipoCalidad()
				.getValorId(), calidad.getTipoCalidad().getValor()));
		calidadDto.setCalidades(Calidades.getByValor(calidad.getTipoCalidad().getValorId()));
		calidadDto.setDescripcionEstadoFisico(calidad.getDescripcionEstadoFisico());
		personaDTO.setCalidadDTO(calidadDto);
		
		return personaDTO;
	}
}
