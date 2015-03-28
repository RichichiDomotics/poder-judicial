/**
* Nombre del Programa : IngresarOrganizacionServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servico para realizar el ingreso de una nueva organizacion
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

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.organizacion.TipoOrganizacion;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.enums.relacion.TipoRelacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.organizacion.OrganizacionDAO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.model.Organizacion;
import mx.gob.segob.nsjp.service.domicilio.IngresarDomicilioService;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.organizacion.IngresarOrganizacionService;
import mx.gob.segob.nsjp.service.organizacion.impl.transform.OrganizacionTransformer;
import mx.gob.segob.nsjp.service.relacion.GenerarRelacionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servico para realizar el ingreso de una nueva organizacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class IngresarOrganizacionServiceImpl implements
		IngresarOrganizacionService {

	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(IngresarOrganizacionServiceImpl.class);
	
	@Autowired
	private OrganizacionDAO organizacionDAO;
	@Autowired
	private GenerarRelacionService generarRelacionService;
	@Autowired
	private IngresarDomicilioService ingresarDomicilioService;
	@Autowired
	private IngresarIndividuoService ingresarIndividuoService; 
	
	@Override
	public OrganizacionDTO ingresarOrganizacion(OrganizacionDTO organizacionDTO)
			throws NSJPNegocioException {
	
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REALIZAR LA CREACION DE UNA ORGANIZACION ****/");
		
		Long idIndividuo = new Long(0);
		Long idDomicilio = new Long(0);
		logger.info (" Calidad: " + organizacionDTO);
		if (organizacionDTO == null || organizacionDTO.getCalidadDTO()==null || organizacionDTO.getExpedienteDTO()== null )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		logger.info (" Calidad: "+ organizacionDTO.getCalidadDTO() + " Expediente: "+ organizacionDTO.getExpedienteDTO());
		
		//Convertir la OrganizacionDTO a Organizacion
		Organizacion organizacion = OrganizacionTransformer.transformarOrganizacion(organizacionDTO);
		// Insertar Organizacion
		Long idOrganizacion = organizacionDAO.create(organizacion);		
		
		//Verificar y generar domicilio
		if (organizacionDTO.getDomicilioDTO()!=null){
			if(organizacionDTO.getDomicilioDTO().getExpedienteDTO()==null)
				organizacionDTO.getDomicilioDTO().setExpedienteDTO( organizacionDTO.getExpedienteDTO());
			
			idDomicilio = ingresarDomicilioService.ingresarDomicilio(organizacionDTO.getDomicilioDTO());
		}		
		
		//Representante Legal -  Solo si la organizacion es DENUNCIANTE_ORGANIZACION o PROBABLE_RESPONSABLE_ORGANIZACION  y 
		//Si es Organizacion Formal  y tienen un Representante Legal 
		if( organizacionDTO.getCalidadDTO()!= null  &&
				(organizacionDTO.getCalidadDTO().getCalidades().equals(Calidades.DENUNCIANTE_ORGANIZACION ) ||
						organizacionDTO.getCalidadDTO().getCalidades().equals(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION ) )  &&
				organizacionDTO.getValorByTipoOrganizacionVal().getIdCampo().equals(TipoOrganizacion.FORMAL.getValorId()) &&
				organizacionDTO.getRepresentanteLegal()!=null &&
				organizacionDTO.getRepresentanteLegal().getCalidadDTO().getCalidades().equals(Calidades.REPRESENTANTE_LEGAL)) {
			
			InvolucradoDTO representanteLegalDTO = organizacionDTO.getRepresentanteLegal();
			representanteLegalDTO.setEsVivo(new Boolean(true));
			//Representante Legal como persona Fisica - Evita, al ser persona moral, que se solicite una organizacion
			representanteLegalDTO.setTipoPersona(new Long(1));
			
			if(representanteLegalDTO.getExpedienteDTO()==null)
				representanteLegalDTO.setExpedienteDTO(organizacionDTO.getExpedienteDTO());
			
			idIndividuo = ingresarIndividuoService.ingresarIndividuo(representanteLegalDTO); 
			
			//idIndividuo = ingresarPersonaService.ingresarPersona(organizacionDTO.getRepresentanteLegal());
			
			
		}
		
		//NO APLICA contacto para Organizacion
//		if(organizacionDTO.getContacto()!=null) {
//			organizacionDTO.getContacto().setEsVivo(new Long(1));
//			contacto = ingresarPersonaService.ingresarPersona(organizacionDTO.getContacto());
//			//Generar relacion con el contacto
//			if (contacto>0)
//				generarRelacionService.generarRelacion(idOrganizacion, contacto, Relaciones.CONTACTO, (short)0);
//		}

		//Generar relacion con representante legal
		if (idIndividuo>0)
			generarRelacionService.generarRelacion(idOrganizacion, idIndividuo, Relaciones.REPRESENTANTE_LEGAL, TipoRelacion.IMPLICITA.getValorId().shortValue());
		
		//Generar relacion con el domicilio
		if (idDomicilio>0)
			generarRelacionService.generarRelacion(idOrganizacion, idDomicilio, Relaciones.UBICACION, TipoRelacion.IMPLICITA.getValorId().shortValue());
		
		OrganizacionDTO loOrganizacion = new OrganizacionDTO();
		loOrganizacion.setNombreOrganizacion(organizacion.getNombreOrganizacion());
		loOrganizacion.setElementoId(idOrganizacion);
		loOrganizacion.setOrganizacionId(idOrganizacion);
		return loOrganizacion;
	}

}
