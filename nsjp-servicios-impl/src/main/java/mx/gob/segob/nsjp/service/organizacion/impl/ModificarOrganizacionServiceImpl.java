/**
* Nombre del Programa : ModificarOrganizacionServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar la actualizacion 
* 							de la informacion de una organizacion
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
package mx.gob.segob.nsjp.service.organizacion.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.organizacion.TipoOrganizacion;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.enums.relacion.TipoRelacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dao.organizacion.OrganizacionDAO;
import mx.gob.segob.nsjp.dao.persona.PersonaDAO;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.model.Organizacion;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.service.domicilio.IngresarDomicilioService;
import mx.gob.segob.nsjp.service.domicilio.ModificarDomicilioServicio;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.ModificarIndividuoService;
import mx.gob.segob.nsjp.service.organizacion.ModificarOrganizacionService;
import mx.gob.segob.nsjp.service.organizacion.impl.transform.OrganizacionTransformer;
import mx.gob.segob.nsjp.service.relacion.GenerarRelacionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para realizar la actualizacion 
 * de la informacion de una organizacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ModificarOrganizacionServiceImpl implements
		ModificarOrganizacionService {
	
	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(ModificarOrganizacionServiceImpl.class);
	
	@Autowired
	private OrganizacionDAO organizacionDAO;
	
	@Autowired
	private ModificarDomicilioServicio modificarDomicilioServicio;
	@Autowired 
	private RelacionDAO relacionDAO;
	@Autowired
	private GenerarRelacionService generarRelacionService;
	@Autowired
	private IngresarDomicilioService ingresarDomicilioService;
	@Autowired
	private PersonaDAO personaDAO;
	@Autowired
	private DomicilioDAO domicilioDAO;
	@Autowired
	private IngresarIndividuoService ingresarIndividuoService; 
	@Autowired
	private ModificarIndividuoService modificarIndividuoService; 

	
	@Override
	public void modificarOrganizacion(OrganizacionDTO organizacionDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REALIZAR LA MODIFICACION DE UNA ORGANIZACION ****/");
		
		if( organizacionDTO== null && organizacionDTO.getElementoId()== null )
			new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES );
		
		Organizacion organizacion = organizacionDAO.read(organizacionDTO.getElementoId());
		Organizacion organizacionMod = OrganizacionTransformer.transformarOrganizacion(organizacionDTO);
		
		organizacion = OrganizacionTransformer.transformarOrganizacionUpdate(organizacion, organizacionMod);
		
		//Actualizar organizacion
		organizacionDAO.update(organizacion);
		
		//Buscar relaciones. Si no existen crearlas, en su defecto modificar los elementos
		List<Relacion> lRelaciones = null;
		
		//Si es Organizacion Formal   
		if( organizacionDTO.getCalidadDTO()!= null  &&
				(organizacionDTO.getCalidadDTO().getCalidades().equals(Calidades.DENUNCIANTE_ORGANIZACION ) ||
						organizacionDTO.getCalidadDTO().getCalidades().equals(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION ) )  &&
				organizacionDTO.getValorByTipoOrganizacionVal().getIdCampo().equals(TipoOrganizacion.FORMAL.getValorId())) {
			
			//Verificar y actualizar Representante Legal
			lRelaciones = relacionDAO.obtenerRelacionSimple(organizacionDTO.getElementoId(), new Long(Relaciones.REPRESENTANTE_LEGAL.ordinal()));
			//Revisar si se actualiza o se crea uno nuevo
			if (organizacionDTO.getRepresentanteLegal()!=null){
				//Si existe un Representante legal, se actualiza con los nuevos datos y se mantienen la relacion
				if(!lRelaciones.isEmpty() && lRelaciones.get(0).getElementoByComplementoId()!= null ){
					InvolucradoDTO representanteLegal = organizacionDTO.getRepresentanteLegal();
					representanteLegal.setElementoId(lRelaciones.get(0).getElementoByComplementoId().getElementoId());
					//Persona Fisica
					representanteLegal.setTipoPersona(new Long(1));
	//				modificarPersonaService.modificarPersona(personaDTO);
					modificarIndividuoService.actualizarIndividuo(representanteLegal);
				}
				else{ //Se da de alta el Representante Legal 
					organizacionDTO.getRepresentanteLegal().setEsVivo(new Boolean(true));
					organizacionDTO.getRepresentanteLegal().setTipoPersona(new Long(1));
					organizacionDTO.getRepresentanteLegal().setFechaCreacionElemento(new Date());
	
					//Long representante = ingresarPersonaService.ingresarPersona(organizacionDTO.getRepresentanteLegal());
					Long representante = ingresarIndividuoService.ingresarIndividuo(organizacionDTO.getRepresentanteLegal());
					//Se crea la relación entre Organizacion y representante
					generarRelacionService.generarRelacion(organizacionDTO.getElementoId(), representante, Relaciones.REPRESENTANTE_LEGAL, TipoRelacion.IMPLICITA.getValorId().shortValue());
				}			
			}else if(!lRelaciones.isEmpty() && lRelaciones.get(0).getElementoByComplementoId()!= null ){
				// Se elimina el elemento en particular  Evita tener que extraer el elemento de BD
				Long   ids[] = {lRelaciones.get(0).getElementoByComplementoId().getElementoId()};
				personaDAO.deleteAll(ids);
				//No es necesario eliminar la relación relacionDAO.delete(lRelaciones.get(0));		
			}
		}
		
		//NO APLICA CONTACTO Verificar y actualizar Contacto
//		lRelaciones = relacionDAO.obtenerRelacionSimple(organizacionDTO.getElementoId(), new Long(Relaciones.CONTACTO.ordinal()));
//		//Revisar si se actualiza o se crea uno nuevo
//		if (organizacionDTO.getContacto()!=null){
//			//Si existe un Contacto, se actualiza con los nuevos datos y se mantienen la relacion
//			if(!lRelaciones.isEmpty() && lRelaciones.get(0).getElementoByComplementoId()!= null ){
//				PersonaDTO personaDTO = organizacionDTO.getContacto();
//				personaDTO.setElementoId(lRelaciones.get(0).getElementoByComplementoId().getElementoId());
//				modificarPersonaService.modificarPersona(personaDTO);
//			}
//			else{  //Se da de alta el Contacto 
//				organizacionDTO.getContacto().setEsVivo(new Long(1));
//				organizacionDTO.getContacto().setFechaCreacionElemento(new Date());
//				Long contacto = ingresarPersonaService.ingresarPersona(organizacionDTO.getContacto());
//				//Se crea la relación entre Organizacion y representante
//				generarRelacionService.generarRelacion(organizacionDTO.getElementoId(), contacto, Relaciones.CONTACTO, (short)0);
//			}			
//		}else if(!lRelaciones.isEmpty() && lRelaciones.get(0).getElementoByComplementoId()!= null ){  
//			// Se elimina el elemento en particular  Evita tener que extraer el elemento de BD
//			Long   ids[] = {lRelaciones.get(0).getElementoByComplementoId().getElementoId()};
//			personaDAO.deleteAll(ids);
//			//No es necesario eliminar la relación relacionDAO.delete(lRelaciones.get(0));		
//		}
		
		//Verificar y actualizar Domicilio
		lRelaciones = relacionDAO.obtenerRelacionSimple(organizacionDTO.getElementoId(), new Long(Relaciones.UBICACION.ordinal()));
		//Revisar si se actualiza o se crea uno nuevo
		if (organizacionDTO.getDomicilioDTO()!=null){
			//Si existe un Contacto, se actualiza con los nuevos datos y se mantienen la relacion
			if(!lRelaciones.isEmpty() && lRelaciones.get(0).getElementoByComplementoId()!= null ){
				DomicilioDTO domicilioDTO = organizacionDTO.getDomicilioDTO();
				domicilioDTO.setElementoId(lRelaciones.get(0).getElementoByComplementoId().getElementoId());
				modificarDomicilioServicio.modificarDomicilio(domicilioDTO);
			}
			else{  //Se da de alta el Domicilio 
				Long idDomicilio = ingresarDomicilioService.ingresarDomicilio(organizacionDTO.getDomicilioDTO());
				//Se crea la relación entre Organizacion y representante
				generarRelacionService.generarRelacion(organizacionDTO.getElementoId(), idDomicilio, Relaciones.UBICACION, (short)0);
			}			
		}else if(!lRelaciones.isEmpty() && lRelaciones.get(0).getElementoByComplementoId()!= null ){  
			// Se elimina el elemento en particular  Evita tener q extrar el elemento de BD
			Long   ids[] = {lRelaciones.get(0).getElementoByComplementoId().getElementoId()};
			domicilioDAO.deleteAll(ids);
			//No es necesario eliminar la relación relacionDAO.delete(lRelaciones.get(0));		
		}
	}
}
