/**
* Nombre del Programa : BuscarInvolucradoServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para consultar un individuo de acuerdo a ciertos parametros de busqueda
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
package mx.gob.segob.nsjp.service.involucrado.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.involucrado.Condicion;
import mx.gob.segob.nsjp.comun.enums.organizacion.TipoOrganizacion;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.AliasInvolucradoDAO;
import mx.gob.segob.nsjp.dao.involucrado.BiometricoDAO;
import mx.gob.segob.nsjp.dao.involucrado.DefensaInvolucradoDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.organizacion.OrganizacionDAO;
import mx.gob.segob.nsjp.dao.persona.CorreoElectronicoDAO;
import mx.gob.segob.nsjp.dao.persona.NombreDemograficoDAO;
import mx.gob.segob.nsjp.dao.persona.TelefonoDAO;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.domicilio.TipoAsentamientoDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DefensaInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.MediaFiliacionDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDetencionDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.AliasInvolucrado;
import mx.gob.segob.nsjp.model.AvisoDetencion;
import mx.gob.segob.nsjp.model.Biometrico;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.DefensaInvolucrado;
import mx.gob.segob.nsjp.model.Detencion;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.MediaFiliacion;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Organizacion;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.model.ServidorPublico;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.service.avisodetencion.impl.transform.AvisoDetencionTransformer;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.AliasInvolucradoTransfromer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.BiometricoTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.DetencionTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.organizacion.impl.transform.OrganizacionTransformer;
import mx.gob.segob.nsjp.service.persona.impl.transform.MedioDeContactoTransformer;
import mx.gob.segob.nsjp.service.persona.impl.transform.NombreDemograficoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para consultar un individuo de acuerdo a ciertos parametros de busqueda.
 * @version 1.0
 * @author cesarAgustin
 *
 */

@Service
@Transactional
public class ConsultarIndividuoServiceImpl implements ConsultarIndividuoService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger.getLogger(ConsultarIndividuoServiceImpl.class);
		
	 /**
	     * Objeto de Acceso a Datos para la entidad Involucrado.
	     */
	 @Autowired
	 private InvolucradoDAO involucradoDAO;
	 @Autowired
	 private DefensaInvolucradoDAO defensaInvolucradoDAO;
	 @Autowired
	 private BiometricoDAO biometricoInvolucradoDAO;
	 @Autowired
	 private NombreDemograficoDAO nombreDemograficoDAO;
	 @Autowired
	 private AliasInvolucradoDAO aliasInvolucradoDAO;
	 @Autowired
	 private TelefonoDAO telefonoDAO;
	 @Autowired
	 private CorreoElectronicoDAO correoElectronicoDAO;
	 @Autowired
	 private DomicilioDAO domicilioDAO;
	 @Autowired
	 private OrganizacionDAO organizacionDAO;
	 @Autowired
	 private ExpedienteDAO expDao;
	 @Autowired
	 private RelacionDAO relacionDAO;
	 @Autowired
	 private NumeroExpedienteDAO numeroExpedienteDAO;
	 
	public List<InvolucradoDTO> obtenerInvolucradosPorExpedienteYCalidades(String numeroExpediente, Calidades[] calidades) throws NSJPNegocioException{
		logger.debug("Servicio obtenerInvolucradosPorExpedienteYCalidades :: " + numeroExpediente + "-" + calidades);
		List<InvolucradoDTO> involucradosDTO = new ArrayList<InvolucradoDTO>();
		if(numeroExpediente == null || numeroExpediente.trim().isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Involucrado> involucrados = new ArrayList<Involucrado>(); 		
		
		
		Long expedienteId = expDao.consultarExpedienteIdPorNumeroExpediente(numeroExpediente);
		logger.info(" ExpedienteID::"+ expedienteId);
		if(expedienteId==null )
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		
		involucrados = involucradoDAO.obtenerInvolucradosPorExpedienteYCalidades(expedienteId, calidades);
		
		for (Involucrado involucrado : involucrados) 
			involucradosDTO.add(InvolucradoTransformer.transformarInvolucrado(involucrado)); 

		return involucradosDTO;
	}
	
	
	
	public InvolucradoDTO obtenerIndividuoDTO(InvolucradoDTO involucradoDto) throws NSJPNegocioException {
	    logger.debug("involucradoDto.id :: " + involucradoDto.getElementoId());
			List<NombreDemografico> nombres = nombreDemograficoDAO.consutarNombresByInvolucrado(involucradoDto.getElementoId());       			       			
			involucradoDto.setNombresDemograficoDTO(NombreDemograficoTransformer.transformarNombreDemografico(nombres));
			
			List<AliasInvolucrado> alias = aliasInvolucradoDAO.consultarAliasByInvolucrado(involucradoDto.getElementoId());				
			involucradoDto.setAliasInvolucradoDTO(AliasInvolucradoTransfromer.transformarAlias(alias));
			
			List<Telefono> telefonos = telefonoDAO.consultarTelefonosByPersona(involucradoDto.getElementoId());
			involucradoDto.setTelefonosDTO(MedioDeContactoTransformer.transformarTelefonos(telefonos));
			
			List<CorreoElectronico> correos = correoElectronicoDAO.consultarCorreosByPersona(involucradoDto.getElementoId()); 
			involucradoDto.setCorreosDTO(MedioDeContactoTransformer.tranformarCorreos(correos));
		
			Domicilio domicilio = domicilioDAO.obtenerDomicilioByRelacion(involucradoDto.getElementoId(), new Long(Relaciones.RESIDENCIA.ordinal())); 
			Domicilio domicilioNotificacion = domicilioDAO.obtenerDomicilioByRelacion(involucradoDto.getElementoId(), new Long(Relaciones.NOTIFICACION.ordinal())); 
			if (domicilio!=null) {
				involucradoDto.setDomicilio(DomicilioTransformer.transformarDomicilio(domicilio));				
			}
			if (domicilioNotificacion!=null) {
				involucradoDto.setDomicilioNotificacion(DomicilioTransformer.transformarDomicilio(domicilioNotificacion));
			}		
			
			List<Biometrico> biometricos = biometricoInvolucradoDAO.consultarBiometricoByInvolucrado(involucradoDto.getElementoId());
				involucradoDto.setBiometricosDTO(BiometricoTransformer.transformarBiometrico(biometricos));
			
			if(involucradoDto.getTipoPersona()!= null && involucradoDto.getTipoPersona().equals(0L)) {
				Organizacion organizacion = organizacionDAO.obtenerOrganizacionByRelacion(involucradoDto.getElementoId(), new Long(Relaciones.ORGANIZACION_INVOLUCRADA.ordinal()));
				involucradoDto.setOrganizacionDTO(OrganizacionTransformer.transformarOrganizacion(organizacion) );
			}
			
		return involucradoDto;
	}

	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService#obtenerInvolucrado(mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO)
	 */
	@Override
	public InvolucradoDTO obtenerInvolucrado(InvolucradoDTO involucradoDTO) throws NSJPNegocioException {
		if (involucradoDTO==null || involucradoDTO.getElementoId()== null || involucradoDTO.getElementoId()<=0) 
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
	    logger.debug("involucradoDTO.getElementoId() :: " + involucradoDTO.getElementoId());
		Involucrado involucradoBD =  involucradoDAO.read(involucradoDTO.getElementoId());
		logger.info("LEEEIIDO + "+involucradoBD);
		
		if (involucradoBD==null) 
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);						
		
		InvolucradoDTO involucradoDTORetorno= new InvolucradoDTO();
		
		if(involucradoBD.getEsActivo().equals(true)){
			involucradoDTORetorno = InvolucradoTransformer.transformarInvolucrado(involucradoBD);		
			involucradoDTORetorno = obtenerIndividuoDTO(involucradoDTORetorno);
			if (involucradoBD.getCalidad().getTipoCalidad().getValorId().equals(Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId())
					|| involucradoBD.getCalidad().getTipoCalidad().getValorId().equals(Calidades.VICTIMA_PERSONA.getValorId())) {
				MediaFiliacionDTO mediaFiliacionDTO = null;
				for (MediaFiliacion mediaFiliacion : involucradoBD.getMediaFiliacions()) {
					mediaFiliacionDTO = InvolucradoTransformer.transformarMediaFilacion(mediaFiliacion);
					//SeniaParticular
					if ( mediaFiliacion.getSeniaParticular()!= null){
						mediaFiliacionDTO.setSeniaParticularDTO(InvolucradoTransformer.transformarSeniaParticular(mediaFiliacion.getSeniaParticular()));
					}	
				}				
				involucradoDTORetorno.setMediaFiliacionDTO(mediaFiliacionDTO);
							
			}
			logger.debug("involucradoDTORetorno.getTipoPersona() :: " + involucradoDTORetorno.getTipoPersona()+ involucradoDTORetorno.getDetenciones());
			
			//Si es persona Moral 
			if (involucradoDTORetorno.getTipoPersona()!= null && involucradoDTORetorno.getTipoPersona().equals(0L) && involucradoDTORetorno.getOrganizacionDTO()!= null){
				logger.debug("/**** Persona moral y se consulta la organizacion "+ involucradoDTORetorno.getOrganizacionDTO().getOrganizacionId() +"****/");
				//Obtener el domicilio asociado a la Organización
				OrganizacionDTO organizacionDTO = involucradoDTORetorno.getOrganizacionDTO();
				Domicilio domicilio = domicilioDAO.obtenerDomicilioByRelacion( organizacionDTO.getElementoId(), new Long(Relaciones.UBICACION.ordinal()));
				if(domicilio!= null ){
					logger.debug(" Organizacion - Domicilio "+ domicilio);
					DomicilioDTO domicilioDTO = DomicilioTransformer.transformarDomicilio(domicilio);
					organizacionDTO.setDomicilioDTO(domicilioDTO);
				}
				
				//Representante Legal - Solo si la organizacion es DENUNCIANTE_ORGANIZACION o PROBABLE_RESPONSABLE_ORGANIZACION  y 
				//Si es Organizacion Formal   
				if( organizacionDTO.getCalidadDTO()!= null  &&
						(organizacionDTO.getCalidadDTO().getValorIdCalidad().getIdCampo().equals(Calidades.DENUNCIANTE_ORGANIZACION.getValorId() ) ||
								organizacionDTO.getCalidadDTO().getValorIdCalidad().getIdCampo().equals(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION.getValorId() ) )  &&
						organizacionDTO.getValorByTipoOrganizacionVal().getIdCampo().equals(TipoOrganizacion.FORMAL.getValorId())) {
					//Obtener la relacion de Representante Legal de la empresa
					List<Relacion> lRelaciones = relacionDAO.obtenerRelacionSimple(organizacionDTO.getElementoId(), new Long(Relaciones.REPRESENTANTE_LEGAL.ordinal()));
					if(!lRelaciones.isEmpty() && lRelaciones.get(0).getElementoByComplementoId()!= null ){
						
						InvolucradoDTO representanteLegal =  new InvolucradoDTO(lRelaciones.get(0).getElementoByComplementoId().getElementoId());
						representanteLegal  = obtenerInvolucrado(representanteLegal);
						organizacionDTO.setRepresentanteLegal(representanteLegal);
					}
				}
				involucradoDTORetorno.setOrganizacionDTO(organizacionDTO);
				
			}
			if (involucradoBD.getEsServidor()!=null && involucradoBD.getEsServidor()) {
				ServidorPublico servidorPublico = involucradoBD.getServidorPublico();
				involucradoDTORetorno.setServidorPublicoDTO(InvolucradoTransformer.transformarServidorPublico(servidorPublico));
			}
					
			logger.debug("involucradoBd getdetencions :: " + involucradoBD.getDetencions().size());
			if(!involucradoBD.getDetencions().isEmpty()){
				DetencionDTO detencionDTO =  null;
				ArrayList<DetencionDTO> detencionDTOs = new ArrayList<DetencionDTO>(); 
				for (Detencion detencion : involucradoBD.getDetencions()) {
					detencionDTO = DetencionTransformer.transformarDetencion(detencion);				
					
					if(detencion.getAvisoDetencion()!= null){
						AvisoDetencionDTO avisoDetencionDTO =  null;
						AvisoDetencion aviso = detencion.getAvisoDetencion();
						avisoDetencionDTO = AvisoDetencionTransformer.transformarAvisoDetencion(aviso);
						detencionDTO.setAvisoDetencion(avisoDetencionDTO);					
					}
					detencionDTOs.add(detencionDTO);				
				}
				involucradoDTORetorno.setDetenciones(detencionDTOs);
			}
			
			
			
			if (logger.isDebugEnabled()) {
				logger.debug("involucradoDTORetorno :: " + involucradoDTORetorno);
			}
			logger.info("Fin - obtenerInvolucrado(...)");
			
		}

		List<Relacion> relaciones = relacionDAO.obtenerRelacionSimple(involucradoDTORetorno.getElementoId(), new Long(Relaciones.QUIEN_DETUVO.ordinal()));			
		if(relaciones!=null){
			List<RelacionDetencionDTO> relsDetDTO =	new ArrayList<RelacionDetencionDTO>();			
			for (Relacion relacion: relaciones) {
				if(relacion.getElementoByComplementoId()!=null && relacion.getElementoByComplementoId().getElementoId()!=null){
					RelacionDetencionDTO relDetDTO = new RelacionDetencionDTO();
					relDetDTO.setIdCmplemento(relacion.getElementoByComplementoId().getElementoId());
					relsDetDTO.add(relDetDTO);
				}
			}					
			
			involucradoDTORetorno.setRelsDetencion(relsDetDTO);
		}

		return involucradoDTORetorno;
	}
	
	@Override
	@Deprecated
	public InvolucradoDTO consultarIndividuo(InvolucradoDTO involucradoDTO) 
		throws NSJPNegocioException {
		
		ExpedienteDTO expediente = new ExpedienteDTO();
		CalidadDTO calidad = new CalidadDTO();
		InvolucradoDTO involucrado = new InvolucradoDTO();		
		DomicilioDTO domicilio = new DomicilioDTO();
		DomicilioDTO domicilioNotificacion = new DomicilioDTO();
		List<NombreDemograficoDTO> nombres = new ArrayList<NombreDemograficoDTO>();
		NombreDemograficoDTO nombre = new NombreDemograficoDTO();
		List<AliasInvolucradoDTO> aliasDTO = new ArrayList<AliasInvolucradoDTO>();
		AliasInvolucradoDTO alias = new AliasInvolucradoDTO();
		AliasInvolucradoDTO alias2 = new AliasInvolucradoDTO();
		OrganizacionDTO organizacion = new OrganizacionDTO();
		DomicilioDTO domicilioOrganizacion = new DomicilioDTO();
		AsentamientoDTO asentamiento = new AsentamientoDTO();
		AsentamientoDTO asentamiento2 = new AsentamientoDTO();
		AsentamientoDTO asentamiento3 = new AsentamientoDTO();
		EntidadFederativaDTO entidad1 = new EntidadFederativaDTO();
		EntidadFederativaDTO entidad2 = new EntidadFederativaDTO();
		EntidadFederativaDTO entidad3 = new EntidadFederativaDTO();
		
		int calidadId = involucradoDTO.getCalidadDTO().getCalidades().ordinal();
		
		if (calidadId==Calidades.PROBABLE_RESPONSABLE_PERSONA.ordinal()) {
			calidad.setDescripcionEstadoFisico(Calidades.PROBABLE_RESPONSABLE_PERSONA.name());
			involucrado.setCalidadDTO(calidad);
			involucrado.setMotivoComparecencia("Pobable responsable");
			involucrado.setEsDetenido(true);
			involucrado.setEsServidor(false);
			involucrado.setCondicion(Short.decode("1"));
			involucrado.setTipoPersona(1L);
			involucrado.setFechaCreacionElemento(new Date());
			involucrado.setEsVivo(true);
			involucrado.setFolioIdentificacion("000001");
								
			involucrado.setValorIdIdentificaion(new ValorDTO(11L, "Cartilla Militar"));			
			
			entidad1.setEntidadFederativaId(17L);
			entidad1.setAbreviacion("AGS");
			entidad1.setCzonaGeografica("NORTE");			
			entidad1.setValorIdPais(new ValorDTO(10L, "Mexico"));
			
			entidad2.setEntidadFederativaId(27L);
			entidad2.setAbreviacion("COL");
			entidad2.setCzonaGeografica("SUR");
			entidad2.setValorIdPais(new ValorDTO(10L, "Mexico"));
						
			entidad3.setAbreviacion("");
			entidad3.setCzonaGeografica("");
			entidad3.setValorIdPais(new ValorDTO(5L, "Alemania"));
			
			asentamiento2.setCodigoPostal("07250");
			asentamiento2.setNombreAsentamiento("Asentamiento 2");
			asentamiento2.setCiudadDTO(new CiudadDTO(2L, "COL", "Colima", entidad2));
			asentamiento2.setMunicipioDTO(new MunicipioDTO(1L, "Mun colima", entidad2));
			asentamiento2.setTipoAsentamientoDTO(new TipoAsentamientoDTO(2L, "Irregular"));	
			
			asentamiento.setCodigoPostal("07440");
			asentamiento.setNombreAsentamiento("Asentamiento 1");
			asentamiento.setCiudadDTO(new CiudadDTO(1L, "AGS", "Aguascalientes", entidad1));
			asentamiento.setMunicipioDTO(new MunicipioDTO(1L, "Algo", entidad1));
			asentamiento.setTipoAsentamientoDTO(new TipoAsentamientoDTO(1L, "Irregular"));	
			
			asentamiento3.setCodigoPostal("07250");
			asentamiento3.setNombreAsentamiento("Asentamiento 2");
			asentamiento3.setCiudadDTO(new CiudadDTO(2L, "COL", "Colima", entidad3));
			asentamiento3.setMunicipioDTO(new MunicipioDTO(1L, "Mun colima", entidad3));
			asentamiento3.setTipoAsentamientoDTO(new TipoAsentamientoDTO(2L, "Irregular"));	
			
			domicilio.setCalle("calle1");
			domicilio.setDescripcion("descripcion domicilio 1");
			domicilio.setFechaCreacionElemento(new Date());
			domicilio.setEntreCalle1("entre calle 1");
			domicilio.setEntreCalle2("entre calle 2");	
			domicilio.setLatitud("205.15");
			domicilio.setLongitud("14.25");
			domicilio.setAsentamientoDTO(asentamiento2);
			involucrado.setDomicilio(domicilio);
			
			domicilioNotificacion.setCalle("calle notificacion 1");
			domicilioNotificacion.setDescripcion("descripcion domicilio notificacion 1");
			domicilioNotificacion.setFechaCreacionElemento(new Date());
			domicilioNotificacion.setEntreCalle1("entre calle notificacin 1");
			domicilioNotificacion.setEntreCalle2("entre calle notificacion 2");
			domicilioNotificacion.setLatitud("205.15");
			domicilioNotificacion.setLongitud("14.25");
			domicilioNotificacion.setAsentamientoDTO(asentamiento3);
			involucrado.setDomicilioNotificacion(domicilioNotificacion);			
						
			domicilioOrganizacion.setCalle("calle organizacion 1");
			domicilioOrganizacion.setDescripcion("descripcion domicilio organizacion 1");
			domicilioOrganizacion.setFechaCreacionElemento(new Date());
			domicilioOrganizacion.setEntreCalle1("entre calle organizacion 1");
			domicilioOrganizacion.setEntreCalle2("entre calle organizacion 2");
			domicilioOrganizacion.setLatitud("205.15");
			domicilioOrganizacion.setLongitud("14.25");
			domicilioOrganizacion.setAsentamientoDTO(asentamiento);
			
			nombre.setApellidoMaterno("Robelto");
			nombre.setApellidoPaterno("Soto");
			nombre.setCurp("iuju896745hdfiur");
			nombre.setRfc("iuju896745ui7");
			nombre.setFechaNacimiento(new Date());
			nombre.setEsVerdadero(false);
			nombre.setEdadAproximada((short)25);
			nombre.setLugarNacimiento("Cuernavaca");
			nombre.setNombre("Victorino");
			nombre.setSexo("masculino");
			nombre.setPaisValorDTO(new ValorDTO(10L));
			nombre.setMunicipioDTO(new MunicipioDTO(895L));
			nombre.setEntidadFederativaDTO(new EntidadFederativaDTO(17L));
			nombres.add(nombre);
			involucrado.setNombresDemograficoDTO(nombres);
			
			expediente.setFechaApertura(new Date());
			expediente.setFechaCierre(new Date());
			expediente.setNumeroExpediente("EXP0000001");
			expediente.setNarrativa("narra los hechos");
			involucrado.setExpedienteDTO(expediente);
			
			if (involucradoDTO.getTipoPersona()!=null && involucradoDTO.getTipoPersona()==0) {
			organizacion.setNombreCorto("ORG");
			organizacion.setNombreOrganizacion("ORGANIZACION 1");
			organizacion.setNumeroActaConstitutiva("1234568");
			organizacion.setDireccionInternet("www.org1.com");
			organizacion.setAreaDeInfluencia("Metropolitana");
			organizacion.setFechaCreacionElemento(new Date());
			organizacion.setGiro("Financiero");
			organizacion.setPropositoCiberespacio("Desconocido");
			organizacion.setDomicilioDTO(domicilioOrganizacion);
			organizacion.setValorByComunidadVirtualVal(new ValorDTO(228L));
			organizacion.setValorByOrganizacionFormalVal(new ValorDTO(229L));
			organizacion.setValorBySectorGubernamentalVal(new ValorDTO(230L));
			organizacion.setValorByTipoOrganizacionVal(new ValorDTO(231L));
			involucrado.setOrganizacionDTO(organizacion);
			}
			
			alias.setAliasInvolucradoId(new Long(1));
			alias.setAlias("Chompi");
			
			alias2.setAliasInvolucradoId(2L);
			alias2.setAlias("Trompas");
			
			aliasDTO.add(alias);
			aliasDTO.add(alias2);
			involucrado.setAliasInvolucradoDTO(aliasDTO);		
			
		} else if (calidadId==Calidades.VICTIMA_PERSONA.ordinal()) {
			calidad.setDescripcionEstadoFisico(Calidades.VICTIMA_PERSONA.name());
			involucrado.setCalidadDTO(calidad);
			involucrado.setMotivoComparecencia("Fue robado");
			involucrado.setEsDetenido(true);
			involucrado.setEsServidor(false);
			involucrado.setCondicion(Short.decode("1"));
			involucrado.setTipoPersona(0L);
			involucrado.setFechaCreacionElemento(new Date());
			involucrado.setEsVivo(true);
			involucrado.setFolioIdentificacion("000002");
			
			domicilio.setCalle("calle2");
			domicilio.setDescripcion("descripcion domicilio 2");
			domicilio.setFechaCreacionElemento(new Date());
			domicilio.setEntreCalle1("entre calle 12");
			domicilio.setEntreCalle2("entre calle 22");								
			involucrado.setDomicilio(domicilio);
			
			domicilioOrganizacion.setCalle("calle organizacion 1");
			domicilioOrganizacion.setDescripcion("descripcion domicilio organizacion 1");
			domicilioOrganizacion.setFechaCreacionElemento(new Date());
			domicilioOrganizacion.setEntreCalle1("entre calle organizacion 1");
			domicilioOrganizacion.setEntreCalle2("entre calle organizacion 2");
			domicilioOrganizacion.setLatitud("205.15");
			domicilioOrganizacion.setLongitud("14.25");
			involucrado.setDomicilioNotificacion(domicilioNotificacion);
			
			involucrado.setValorIdIdentificaion(new ValorDTO(11L, "Cartilla Militar"));
			
			domicilioNotificacion.setCalle("calle notificacion 2");
			domicilioNotificacion.setDescripcion("descripcion domicilio notificacion 2");
			domicilioNotificacion.setFechaCreacionElemento(new Date());
			domicilioNotificacion.setEntreCalle1("entre calle notificacin 12");
			domicilioNotificacion.setEntreCalle2("entre calle notificacion 22");						
			involucrado.setDomicilioNotificacion(domicilioNotificacion);
			
			nombre.setApellidoMaterno("Aguilar");
			nombre.setApellidoPaterno("Carrillo");
			nombre.setCurp("iuju896745hdfiur");
			nombre.setRfc("iuju896745ui7");
			nombre.setFechaNacimiento(new Date());
			nombre.setEsVerdadero(false);
			nombre.setEdadAproximada((short)25);
			nombre.setLugarNacimiento("Michoacan Mexico");
			nombre.setNombre("Fulgencio");
			nombre.setSexo("masculino");
			nombres.add(nombre);
			involucrado.setNombresDemograficoDTO(nombres);
			
			expediente.setFechaApertura(new Date());
			expediente.setFechaCierre(new Date());
			expediente.setNumeroExpediente("EXP0000002");
			involucrado.setExpedienteDTO(expediente);
			
			organizacion.setNombreCorto("ORG");
			organizacion.setNombreOrganizacion("ORGANIZACION 1");
			organizacion.setNumeroActaConstitutiva("1234568");
			organizacion.setDireccionInternet("www.org1.com");
			organizacion.setAreaDeInfluencia("Metropolitana");
			organizacion.setFechaCreacionElemento(new Date());
			organizacion.setGiro("Financiero");
			organizacion.setPropositoCiberespacio("Desconocido");
			organizacion.setDomicilioDTO(domicilioOrganizacion);
			organizacion.setValorByComunidadVirtualVal(new ValorDTO(228L));
			organizacion.setValorByOrganizacionFormalVal(new ValorDTO(229L));
			organizacion.setValorBySectorGubernamentalVal(new ValorDTO(230L));
			organizacion.setValorByTipoOrganizacionVal(new ValorDTO(231L));
			involucrado.setOrganizacionDTO(organizacion);
			
			alias.setAliasInvolucradoId(1L);
			alias.setAlias("Chompi");
			
			alias2.setAliasInvolucradoId(2L);
			alias2.setAlias("Trompas");
			
			aliasDTO.add(alias);
			aliasDTO.add(alias2);
			involucrado.setAliasInvolucradoDTO(aliasDTO);
		}		
		
		return involucrado;		
	}


	@Override
	public List<InvolucradoDTO> consultarInvolucradoExpediente(
			ExpedienteDTO expedienteDTO, Calidades calidad, UsuarioDTO usuario)
			throws NSJPNegocioException {
		
		if (expedienteDTO==null || expedienteDTO.getExpedienteId()== null || 
				expedienteDTO.getExpedienteId() < 0 || calidad==null || calidad.getValorId()==null ||
				calidad.getValorId()< 0 || usuario== null || usuario.getAreaActual()==null || 
				usuario.getAreaActual().getAreaId()==null  || usuario.getAreaActual().getAreaId() <0 )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA OBTENER LOS INVOLUCRADOS POR CALIDAD Y EXPEDIENTE ****/");
		
		List<Involucrado> involucrados =null;
		involucrados = involucradoDAO.obtenerInvByIdExpAndCalidad(expedienteDTO.getExpedienteId(), calidad.getValorId(),null);
		//En caso de que se desea consultar las Victimas asociadas al expediente
		//Se requiere que se regresa los denunciantes que sean victimas (condición =1)
		if(calidad.getValorId().equals(Calidades.VICTIMA_PERSONA.getValorId()) ){
			List<Involucrado> denunciantesVictima = involucradoDAO.obtenerInvByIdExpAndCalidad(expedienteDTO.getExpedienteId(), Calidades.DENUNCIANTE.getValorId(),null);
			for (Involucrado involucrado : denunciantesVictima) {
				//Se usa la mismo campo de Condicion, para indicar que el PR esat vivo, muerto o desconocido, pero para Denunciante es para saber si es víctima.
				if(involucrado.getCondicion()!= null && involucrado.getCondicion().intValue() == Condicion.VIVO.ordinal() ){ //Comparación sobre tipos primitivos.   
					involucrados.add(involucrado);
				}
			}
		}
		
		List<InvolucradoDTO> involucradosDTO = new ArrayList<InvolucradoDTO>();
		InvolucradoDTO inv = null;
		NumeroExpediente numeroExpediente = numeroExpedienteDAO.obtenerNumeroExpediente(expedienteDTO.getExpedienteId(), usuario.getAreaActual().getAreaId());
		if(numeroExpediente != null){
			expedienteDTO = ExpedienteTransformer.transformarExpedienteBasico(numeroExpediente);
		}
		for (Involucrado involucrado : involucrados) {
			inv = InvolucradoTransformer.transformarInvolucradoBasico(involucrado);
			inv.setExpedienteDTO(expedienteDTO);
			involucradosDTO.add(inv);
		}						
		return involucradosDTO;
	}



	@Override
	public List<InvolucradoDTO> consultarProbablesResponsablesDetenidos(
			ExpedienteDTO expedienteDTO, Boolean esDetenido)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA OBTENER LOS INVOLUCRADOS POR CALIDAD Y EXPEDIENTE Y DETENIDOS ****/");
		List<Involucrado> involucrados=involucradoDAO.obtenerInvByIdExpAndCalidad(expedienteDTO.getExpedienteId(), Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId(), esDetenido);
		List<InvolucradoDTO> involucradosDTO = new ArrayList<InvolucradoDTO>();
		for (Involucrado involucrado : involucrados) {
			involucradosDTO.add(InvolucradoTransformer.transformarInvolucrado(involucrado));
		}
		return involucradosDTO;
	}



	@Override
	public List<InvolucradoDTO> consultarInvolucradoPorCalidadCaso(
			CasoDTO casoDTO, CalidadDTO calidadDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA OBTENER LOS INVOLUCRADOS POR CALIDAD Y CASO ****/");
		
		/*Verificar parámetros*/
		if(calidadDTO==null || casoDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(casoDTO.getCasoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Operación*/
		List<Expediente> expedientes = expDao.consultarExpedientesPorId(casoDTO.getCasoId(), null);
		List<Involucrado> involucrados =new ArrayList<Involucrado>();
		for (Expediente exp : expedientes) {
			involucrados.addAll(involucradoDAO.obtenerInvByIdExpAndCalidad(exp.getExpedienteId(), calidadDTO.getCalidadId(), null));
		}
		List<InvolucradoDTO> invDTOs=new ArrayList<InvolucradoDTO>();
		for (Involucrado inv : involucrados) {
			invDTOs.add(InvolucradoTransformer.transformarInvolucrado(inv));
		}
		
		return invDTOs;
	}


	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService#obtenerInvolucradosByCasoYCalidades(java.lang.String, mx.gob.segob.nsjp.comun.enums.calidad.Calidades[])
	 */
	@Override
	public List<InvolucradoDTO> obtenerInvolucradosByCasoYCalidades(
			String numeroCaso, Calidades[] calidades) {
		List <Involucrado> involucrados = involucradoDAO.
		obtenerInvolucradosByCasoYCalidades(numeroCaso, calidades);
		List<InvolucradoDTO> resultado = new ArrayList<InvolucradoDTO>();
		for(Involucrado inv:involucrados){
			resultado.add(InvolucradoTransformer.transformarInvolucradoBasico(inv));
		}
		return resultado;
	}



	@Override
	public List<DefensaInvolucradoDTO> consultarInvolucradoExpedienteDefensoria(
			ExpedienteDTO expedienteDTO, Calidades calidad, UsuarioDTO usuario) throws NSJPNegocioException {
		final List<DefensaInvolucrado> defensaInvolucrados = defensaInvolucradoDAO.consultarInvolucradoExpedienteDefensoria(expedienteDTO.getExpedienteId(), calidad.getValorId());
		List<DefensaInvolucradoDTO> involucrados = new LinkedList<DefensaInvolucradoDTO>();
		DefensaInvolucradoDTO temp = null;
		for (DefensaInvolucrado defensaInvolucrado : defensaInvolucrados) {
			temp = new DefensaInvolucradoDTO();
			temp.setExpediente(ExpedienteTransformer.transformarExpedienteBasico(defensaInvolucrado.getNumeroExpediente()));
			temp.setInvolucrado(InvolucradoTransformer.transformarInvolucradoBasico(defensaInvolucrado.getInvolucrado()));
			involucrados.add(temp);
		}
		return involucrados;
	}



	
}
