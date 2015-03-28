/**
 * Nombre del Programa : IngresarIndividuoServiceImpl.java                                    
 * Autor: Vladimir Aguirre Piedragil
 * Compania: Ultrasist                                                
 * Proyecto: NSJP 
 * Fecha: 29 Mar 2011 
 * Marca de cambio: N/A                                                     
 * Descripcion General: Implementación del servicio para registrar individuo.
 * Programa Dependiente: N/A                                                      
 * Programa Subsecuente: N/A                                                      
 * Cond. de ejecucion: N/A                                                      
 * Dias de ejecucion: N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */
package mx.gob.segob.nsjp.service.involucrado.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.enums.relacion.TipoRelacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoNacionalidadDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoOcupacionDAO;
import mx.gob.segob.nsjp.dao.involucrado.ServidorPublicoDAO;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.model.AliasInvolucrado;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.CatRelacion;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.InvolucradoNacionalidad;
import mx.gob.segob.nsjp.model.InvolucradoNacionalidadId;
import mx.gob.segob.nsjp.model.InvolucradoOcupacion;
import mx.gob.segob.nsjp.model.InvolucradoOcupacionId;
import mx.gob.segob.nsjp.model.MediaFiliacion;
import mx.gob.segob.nsjp.model.MedioDeContacto;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Organizacion;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.model.SeniaParticular;
import mx.gob.segob.nsjp.model.ServidorPublico;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoPersonaTransfromer;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoTransfromer;
import mx.gob.segob.nsjp.service.domicilio.IngresarDomicilioService;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.ModificarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.AliasInvolucradoTransfromer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.organizacion.IngresarOrganizacionService;
import mx.gob.segob.nsjp.service.organizacion.impl.transform.OrganizacionTransformer;
import mx.gob.segob.nsjp.service.persona.impl.transform.MedioDeContactoTransformer;
import mx.gob.segob.nsjp.service.persona.impl.transform.NombreDemograficoTransformer;
import mx.gob.segob.nsjp.service.relacion.GenerarRelacionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación para ingresar los datos del individuo.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional
public class IngresarIndividuoServiceImpl implements IngresarIndividuoService {
	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(IngresarIndividuoServiceImpl.class);

	/**
	 * Objeto de Acceso a Datos para la entidad Involucrado.
	 */
	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired
	private InvolucradoOcupacionDAO involucradoOcupacionDAO;
	@Autowired
	private InvolucradoNacionalidadDAO involucradoNacionalidadDAO;
	@Autowired
	private ServidorPublicoDAO servidorPublicoDAO;
	@Autowired
	private IngresarOrganizacionService ingresarOrganizacionService;
	@Autowired
	private GenerarRelacionService generarRelacionService;
	@Autowired
	private IngresarDomicilioService ingresarDomicilioService;
	@Autowired
	private AsignarNumeroExpedienteService asignarExpedienteService;
	@Autowired
	private ModificarIndividuoService modificarIndService;
	@Autowired
	private RelacionDAO relacionDAO;

	@Override
	public Long ingresarIndividuoInterInstitucion(
			InvolucradoDTO involucradoDTO, boolean siExisteAcutaliza)
			throws NSJPNegocioException {
		if (involucradoDTO.getFolioElemento() == null) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		final Involucrado involucrado = involucradoDAO
				.consultarInvolucradoPorFolioElemento(involucradoDTO
						.getFolioElemento());
		if (involucrado == null) {
			return ingresarIndividuo(involucradoDTO);
		} else {
			if (siExisteAcutaliza) {
				involucradoDTO.setElementoId(involucrado.getElementoId());
				modificarIndService.actualizarIndividuo(involucradoDTO);
			}
		}
		return involucrado.getElementoId();
	}

	@Override
	public Long ingresarIndividuo(InvolucradoDTO involucradoDTO)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled()) {
			logger.debug("Inicia almacenamiento de individuo");
			logger.debug("involucradoDTO.getCalidadDTO() :: "
					+ involucradoDTO.getCalidadDTO());
			logger.debug("involucradoDTO.getElementoId() :: "
					+ involucradoDTO.getElementoId());
			logger.debug("involucradoDTO.getFolioElemento() :: "
					+ involucradoDTO.getFolioElemento());
			logger.debug("involucradoDTO.getDomicilio() :: "
					+ involucradoDTO.getDomicilio());
			logger.debug("##########################DTO ORGANIZACION: "
					+ involucradoDTO.getOrganizacionDTO());
		}

		OrganizacionDTO organizacionDTO = new OrganizacionDTO();

		Set<AliasInvolucrado> colAliasInvolucrado = new HashSet<AliasInvolucrado>();
		Set<NombreDemografico> colNombreDemografico = new HashSet<NombreDemografico>();
		Set<MedioDeContacto> colMedioContacto = new HashSet<MedioDeContacto>();
		Set<Delito> colDelitos = new HashSet<Delito>();

		Involucrado involucrado = new Involucrado();
		Calidad calidad = new Calidad();
		Organizacion organizacion = new Organizacion();

		Long idInvolucrado = 0L;
		Long idDomicilio = 0L;
		Long idDomicilioNotificacion = 0L;
		Long idOrganizacion = 0L;

		if (!(involucradoDTO.getExpedienteDTO() != null && involucradoDTO
				.getExpedienteDTO().getExpedienteId() != null)) {
			logger.error("El expediente es requerido para generar al involucrado");
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}

		if (involucradoDTO.getElementoId() != null
				&& involucradoDTO.getElementoId() > 0) {
			return modificarIndService.actualizarIndividuo(involucradoDTO);
		}

		// Transforma el InvolucradoDTO a Involucrado
		involucrado = InvolucradoTransformer
				.transformarInvolucrado(involucradoDTO);
		involucrado.setFechaCreacionElemento(new Date());

		// DATOS GENERALES
		// Nombre Demografico (Uno solo)
		if (involucradoDTO.getNombresDemograficoDTO() != null
				&& involucradoDTO.getNombresDemograficoDTO().size() > 0) {
			logger.debug("/**** Se obtuvieron nombres demograficos ****/");
			for (NombreDemograficoDTO nombreDemograficoDTO : involucradoDTO
					.getNombresDemograficoDTO()) {
				NombreDemografico nomDemografico = NombreDemograficoTransformer
						.transformarNombreDemografico(nombreDemograficoDTO);
				// Cambio involucrado DTO por LOMG
				if (involucradoDTO.getEsVivo() != null) {
						involucrado.setEsVivo(involucradoDTO.getEsVivo());
				}
				nomDemografico.setPersona(involucrado);

				nomDemografico.setEsVerdadero(true);
				colNombreDemografico.add(nomDemografico);
			}
			involucrado.setNombreDemograficos(colNombreDemografico);
		}
		// ALIAS
		if (involucradoDTO.getAliasInvolucradoDTO() != null
				&& involucradoDTO.getAliasInvolucradoDTO().size() > 0) {
			logger.debug("/**** Se obtuvieron alias ****/");
			for (AliasInvolucradoDTO aliasDTO : involucradoDTO
					.getAliasInvolucradoDTO()) {
				AliasInvolucrado aliasInv = AliasInvolucradoTransfromer
						.transformarAliasInv(aliasDTO);
				aliasInv.setInvolucrado(involucrado);
				colAliasInvolucrado.add(aliasInv);
			}
			involucrado.setAliasInvolucrados(colAliasInvolucrado);
		}

		// MEDIOS DE CONTACTO - Correo electronico - Telefono
		if ((involucradoDTO.getCorreosDTO() != null && involucradoDTO
				.getCorreosDTO().size() > 0)
				|| (involucradoDTO.getTelefonosDTO() != null && involucradoDTO
						.getTelefonosDTO().size() > 0)) {
			logger.debug("/**** Se obtuvieron medios de contacto ****/");
			if (involucradoDTO.getCorreosDTO() != null
					&& involucradoDTO.getCorreosDTO().size() > 0)
				for (CorreoElectronicoDTO correoDTO : involucradoDTO
						.getCorreosDTO()) {
					MedioDeContacto correo = MedioDeContactoTransformer
							.transformarCorreo(correoDTO);
					correo.setPersona(involucrado);
					colMedioContacto.add(correo);
				}
			if (involucradoDTO.getTelefonosDTO() != null
					&& involucradoDTO.getTelefonosDTO().size() > 0)
				for (TelefonoDTO telefonoDTO : involucradoDTO.getTelefonosDTO()) {
					MedioDeContacto telefono = MedioDeContactoTransformer
							.transformarTelefono(telefonoDTO);
					telefono.setPersona(involucrado);
					colMedioContacto.add(telefono);
				}
			involucrado.setMedioDeContactos(colMedioContacto);
		}

		// SITUACION JURIDICA
		if (involucradoDTO.getCalidadDTO().getCalidades()
				.equals(Calidades.PROBABLE_RESPONSABLE_PERSONA)
				|| involucradoDTO.getCalidadDTO().getCalidades()
						.equals(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION))
			involucrado.setSituacionJuridica(new Valor(
					SituacionJuridica.INDICIADO.getValorId()));

		// MEDIA FILIACION - SOLO PARA VICTIMA Y PROBABLE RESPOSABLE
		logger.info("/****  PROBABLE_RESPONSABLE_PERSONA: "
				+ involucradoDTO.getCalidadDTO().getCalidades()
						.equals(Calidades.PROBABLE_RESPONSABLE_PERSONA)
				+ " /**** VICTIMA_PERSONA:"
				+ involucradoDTO.getCalidadDTO().getCalidades()
						.equals(Calidades.VICTIMA_PERSONA));
		if (involucradoDTO.getCalidadDTO().getCalidades()
				.equals(Calidades.PROBABLE_RESPONSABLE_PERSONA)
				|| involucradoDTO.getCalidadDTO().getCalidades()
						.equals(Calidades.VICTIMA_PERSONA)) {
			if (involucradoDTO.getMediaFiliacionDTO() != null) {
				logger.debug("/**** El individuo tiene media filiacion ****/");
				Set<MediaFiliacion> filiaciones = new HashSet<MediaFiliacion>();

				MediaFiliacion mediaFiliacion = InvolucradoTransformer
						.transformarMediaFilacion(involucradoDTO
								.getMediaFiliacionDTO());

				if (involucradoDTO.getMediaFiliacionDTO()
						.getSeniaParticularDTO() != null) {
					logger.debug("/**** Le mediafiliacion contiene Señas Particulares ****/");
					SeniaParticular seniaParticular = InvolucradoTransformer
							.transformarSeniaParticularDTO(involucradoDTO
									.getMediaFiliacionDTO()
									.getSeniaParticularDTO());
					seniaParticular.setMediaFiliacion(mediaFiliacion);
					mediaFiliacion.setSeniaParticular(seniaParticular);
				}

				mediaFiliacion.setInvolucrado(involucrado);
				filiaciones.add(mediaFiliacion);
				involucrado.setMediaFiliacions(filiaciones);
			}
		}

		// ORGANIZACION (PERSONA MORAL) O PERSONA FISICA
		if (involucradoDTO.getTipoPersona() != null
				&& involucradoDTO.getTipoPersona().intValue() == 0
				&& involucradoDTO.getOrganizacionDTO() != null
		// && !
		// involucradoDTO.getCalidadDTO().getCalidades().equals(Calidades.REPRESENTANTE_LEGAL)
		) {
			logger.debug("/**** Persona moral, se obtiene organizacion ****/");
			organizacion = OrganizacionTransformer
					.transformarOrganizacion(involucradoDTO
							.getOrganizacionDTO());
			organizacionDTO = involucradoDTO.getOrganizacionDTO();

			// Obtener calidad
			if (organizacionDTO.getCalidadDTO() != null)
				calidad.setDescripcionEstadoFisico(organizacionDTO
						.getCalidadDTO().getDescripcionEstadoFisico() != null ? organizacionDTO
						.getCalidadDTO().getDescripcionEstadoFisico() : null);
			// Tipo de Organización
			calidad.setTipoCalidad(new Valor(involucradoDTO.getCalidadDTO()
					.getCalidades().getValorId()));

			organizacion.setCalidad(calidad);
			involucrado.setCalidad(calidad);

			// Insertar involucrado
			idInvolucrado = involucradoDAO.create(involucrado);

			logger.debug("/**** Se genero el involucrado con id: "
					+ idInvolucrado + " ****/");

			// Ingresar Organización
			if (organizacionDTO.getExpedienteDTO() == null)
				organizacionDTO.setExpedienteDTO(involucradoDTO
						.getExpedienteDTO());

			OrganizacionDTO nuevaOrganizacionDTO = ingresarOrganizacionService
					.ingresarOrganizacion(organizacionDTO);
			idOrganizacion = nuevaOrganizacionDTO.getElementoId();

			logger.debug("/**** Se genero la organizacion con id"
					+ idOrganizacion + " ****/");

			// Generar relacion involucrado - organizacion
			if (idOrganizacion > 0)
				generarRelacionService.generarRelacion(idInvolucrado,
						idOrganizacion, Relaciones.ORGANIZACION_INVOLUCRADA,
						TipoRelacion.IMPLICITA.getValorId().shortValue());

			// Se desarrolla en Ingresar OrganizacionServiceImpl L 94
			// if (involucradoDTO.getOrganizacionDTO().getContacto()!=null)
			// idContacto =
			// ingresarPersonaService.ingresarPersona(involucradoDTO.getOrganizacionDTO().getContacto());
			// Se desarrolla en Ingresar OrganizacionServiceImpl L 89
			// if
			// (involucradoDTO.getOrganizacionDTO().getRepresentanteLegal()!=null)
			// idRepresentante =
			// ingresarPersonaService.ingresarPersona(involucradoDTO.getOrganizacionDTO().getRepresentanteLegal());
		} else {
			// Obtener calidad
			logger.debug("/**** Persona fisica, se obtiene la calidad del individuo ****/");
			if (involucradoDTO.getCalidadDTO() != null
					&& involucradoDTO.getCalidadDTO()
							.getDescripcionEstadoFisico() != null) {
				calidad.setDescripcionEstadoFisico(involucradoDTO
						.getCalidadDTO().getDescripcionEstadoFisico());
			}
			if (involucradoDTO.getCalidadDTO() != null
					&& involucradoDTO.getCalidadDTO().getCalidades() != null) {
				calidad.setTipoCalidad(new Valor(involucradoDTO.getCalidadDTO()
						.getCalidades().getValorId()));
			}

			involucrado.setCalidad(calidad);
			// Insertar involucrado

			idInvolucrado = involucradoDAO.create(involucrado);

			logger.debug("/**** Se genero el involucrado con id: "
					+ idInvolucrado + " ****/");
		}

		// SERVIDOR PUBLICO
		if (involucradoDTO.getEsServidor() != null
				&& involucradoDTO.getEsServidor()
				&& involucradoDTO.getServidorPublicoDTO() != null) {
			logger.debug("/**** El individuo es servidor publico ****/");
			ServidorPublico servidorPublico = InvolucradoTransformer
					.transformarServidorPublico(involucradoDTO
							.getServidorPublicoDTO());
			servidorPublico.setInvolucrado(new Involucrado(idInvolucrado));
			servidorPublicoDAO.create(servidorPublico);
		}

		// DATOS GENERALES
		// OCUPACIONES
		if (involucradoDTO.getValorIdOcupacion() != null
				&& involucradoDTO.getValorIdOcupacion().size() > 0) {
			logger.debug("Se obtuvo ocupaciones");
			for (ValorDTO invOcupacionDTO : involucradoDTO
					.getValorIdOcupacion()) {
				if (invOcupacionDTO.getIdCampo() != null) {
					InvolucradoOcupacion invOcupacion = new InvolucradoOcupacion();
					InvolucradoOcupacionId invOcId = new InvolucradoOcupacionId();
					invOcId.setInvolucradoId(idInvolucrado);
					invOcId.setValorId(invOcupacionDTO.getIdCampo());
					invOcupacion.setId(invOcId);
					invOcupacion.setInvolucrado(new Involucrado(idInvolucrado));
					invOcupacion.setValor(new Valor(invOcupacionDTO
							.getIdCampo()));
					involucradoOcupacionDAO.create(invOcupacion);
				}
			}
		}
		// NACIONALIDADES
		if (involucradoDTO.getValorIdNacionalidad() != null
				&& involucradoDTO.getValorIdNacionalidad().size() > 0) {
			logger.debug("Se obtuvo nacionalidades");
			for (ValorDTO invNacionalidadDTO : involucradoDTO
					.getValorIdNacionalidad()) {
				if (invNacionalidadDTO.getIdCampo() != null) {
					InvolucradoNacionalidad invNacionalidad = new InvolucradoNacionalidad();
					InvolucradoNacionalidadId invNacId = new InvolucradoNacionalidadId();
					invNacId.setInvolucradoId(idInvolucrado);
					invNacId.setValorId(invNacionalidadDTO.getIdCampo());
					invNacionalidad.setId(invNacId);
					invNacionalidad.setInvolucrado(new Involucrado(
							idInvolucrado));
					invNacionalidad.setValor(new Valor(invNacionalidadDTO
							.getIdCampo()));
					involucradoNacionalidadDAO.create(invNacionalidad);
				}
			}
		}
		// DOMICILIO - COORDENADAS GEOGRAFICAS
		if (involucradoDTO.getDomicilio() != null
				&& involucradoDTO.getDomicilio().getCalidadDTO() != null) {
			logger.debug("Se obtuvo el domicilio");

			if (involucradoDTO.getDomicilio().getExpedienteDTO() == null)
				involucradoDTO.getDomicilio().setExpedienteDTO(
						involucradoDTO.getExpedienteDTO());

			// Insertar domicilio
			idDomicilio = ingresarDomicilioService
					.ingresarDomicilio(involucradoDTO.getDomicilio());
			// Generar su Relación
			generarRelacionService.generarRelacion(idInvolucrado, idDomicilio,
					Relaciones.RESIDENCIA, (short) 1);
		}
		// DOMICILIO DE NOTIFICACION
		if (involucradoDTO.getDomicilioNotificacion() != null
				&& involucradoDTO.getDomicilioNotificacion().getCalidadDTO() != null) {
			logger.debug("/**** Se obtuvo el domicilio notificacion ****/");

			if (involucradoDTO.getDomicilioNotificacion().getExpedienteDTO() == null)
				involucradoDTO.getDomicilioNotificacion().setExpedienteDTO(
						involucradoDTO.getExpedienteDTO());

			// Insertar domicilio notificacion
			idDomicilioNotificacion = ingresarDomicilioService
					.ingresarDomicilio(involucradoDTO
							.getDomicilioNotificacion());
			// Generar relaciones involucrado - domicilioNotificacion
			generarRelacionService
					.generarRelacion(idInvolucrado, idDomicilioNotificacion,
							Relaciones.NOTIFICACION, (short) 1);
		}

		// DELITOS DEBE MANJERSE DE MANARA INDEPENDIENTE
//		if (involucradoDTO.getDelitosCometidos() != null
//				&& involucradoDTO.getDelitosCometidos().size() > 0) {
//			logger.debug("/**** Se obtuvieron los delitos ****/");
//			for (DelitoDTO delitoDTO : involucradoDTO.getDelitosCometidos()) {
//				Delito delitoInv = DelitoTransfromer
//						.transformarDelito(delitoDTO);
//				colDelitos.add(delitoInv);
//			}
//			involucrado
//					.setDelitosCometidos(DelitoPersonaTransfromer
//							.transformarDelitosADelitosPersona(colDelitos,
//									involucrado));
//		}
		// Generar relaciones quien detuvo
		if (involucradoDTO.getCalidadDTO().getCalidades()
				.equals(Calidades.QUIEN_DETUVO)) {
			for (Long idDetenido : involucradoDTO.getIdsDetenidos()) {
				generarRelacionService.generarRelacion(idInvolucrado,
						idDetenido, Relaciones.QUIEN_DETUVO,
						TipoRelacion.EXPLICITA.getValorId().shortValue());
			}
		}

		logger.debug("idInvolucrado creado :: " + idInvolucrado);
		logger.info("Fin - ingresarIndividuo(...)");
		return idInvolucrado;
	}

	@Override
	public Long ingresarVictima(InvolucradoDTO victimaDTO)
			throws NSJPNegocioException {
		Long idVictima = 0L;
		CalidadDTO calidadDTO = new CalidadDTO();

		if (logger.isDebugEnabled())
			logger.debug("Inicia almacenamiento de victima");

		// Obtener calidad
		if (victimaDTO.getCalidadDTO() != null) {
			calidadDTO = victimaDTO.getCalidadDTO();

			logger.error("La calidad es requerida para generar al involucrado");
			throw new NSJPNegocioException(CodigoError.FORMATO);
		}

		ExpedienteDTO expedienteDto = new ExpedienteDTO();
		expedienteDto.setFechaApertura(new Date());
		expedienteDto.setUsuario(victimaDTO.getUsuario());
		expedienteDto = asignarExpedienteService
				.asignarNumeroExpediente(expedienteDto);

		idVictima = this.ingresarIndividuo(victimaDTO);

		return idVictima;
	}

	@Override
	public Long guardarDefensorAsignadoInvolucrado(
			InvolucradoDTO involucradoDTO, Long probableResponsableId)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR DEFENSOR Y ASOCIAR A INVOLUCRADO ****/");

		if (involucradoDTO == null || probableResponsableId == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		/* Guarda Involucrado como normalmente */
		Long idDefensor = this.ingresarIndividuo(involucradoDTO);

		if(involucradoDTO.getElementoId()==null || involucradoDTO.getElementoId()<=0){
			
			Long idRelacion = null;
			/* Crea la relación */
			Relacion relacion = new Relacion();
			relacion.setElementoBySujetoId(new Elemento(idDefensor));
			relacion.setElementoByComplementoId(new Elemento(probableResponsableId));
			relacion.setTipoRelacion((short) 1);
			relacion.setEsActivo(true);
			relacion.setCatRelacion(new CatRelacion(new Long(Relaciones.ABOGADO
					.ordinal())));
			idRelacion = relacionDAO.create(relacion);
			
		}
		else{
			if(involucradoDTO.getEsActivo()!= null && involucradoDTO.getEsActivo().equals(false)){
				
				 List<Relacion> relaciones  = relacionDAO.obtenerRelacionSimple(involucradoDTO.getElementoId().longValue(), (long) Relaciones.ABOGADO.ordinal());
				
				 for (Relacion relacion : relaciones) {
					  relacion.setEsActivo(false);
					  					 
					  relacionDAO.update(relacion);
				 }
				
			}
		}
		
		return idDefensor;
	}

}
