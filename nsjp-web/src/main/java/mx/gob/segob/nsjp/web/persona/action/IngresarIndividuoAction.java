/**
 * Nombre del Programa : IngresarIndividuoAction.java
 * Autor               : Arturo Leon
 * Compania            : Ultrasist
 * Proyecto            : NSJP                    Fecha: 23/02/2011
 * Marca de cambio     : N/A
 * Descripcion General : Clase Action para Ingresar Individuo
 * Programa Dependiente: N/A
 * Programa Subsecuente: N/A
 * Cond. de ejecucion  : N/A
 * Dias de ejecucion   : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor               :N/A
 * Compania            :N/A
 * Proyecto            :N/A                                   Fecha: N/A
 * Modificacion        :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.web.persona.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioExtranjeroDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.domicilio.TipoAsentamientoDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ActaCircunstanciadaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.dto.involucrado.MediaFiliacionDTO;
import mx.gob.segob.nsjp.dto.involucrado.SeniaParticularDTO;
import mx.gob.segob.nsjp.dto.involucrado.ServidorPublicoDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.caso.form.IngresarActaCircunstanciadaForm;
import mx.gob.segob.nsjp.web.caso.form.IngresarIndividuoForm;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase Action para Ingresar Calidad
 * 
 * @version 1.0
 * @author Arturo Leon Galicia - Ultrasist
 * 
 * 
 */
public class IngresarIndividuoAction extends GenericAction {
	/* Log de clase */
	private static final Logger logger = Logger
			.getLogger(IngresarIndividuoAction.class);
	private Short CALIDAD_PROBABLE_RESPONSABLE = new Short((short) 0);
	private Short CALIDAD_CONTACTO_ORGANIZACIONAL = new Short((short) 1);
	private Short CALIDAD_VICTIMA_PERSONA = new Short((short) 2);
	private Short CALIDAD_TUTOR = new Short((short) 3);
	private Short CALIDAD_DENUNCIANTE = new Short((short) 4);
	private Short CALIDAD_TESTIGO = new Short((short) 5);
	private Short CALIDAD_REPRESENTANTE_LEGAL = new Short((short) 6);
	private Short CALIDAD_TRADUCTOR = new Short((short) 7);
	private Short CALIDAD_QUIEN_DETUVO = new Short((short) 8);
	private Short CALIDAD_DEFENSOR = new Short((short) 9);
	private Short CALIDAD_DEFENSOR_PRIVADO = new Short((short) 10);
	private Short CALIDAD_SOLICITANTE = new Short((short) 11);
	private Long TIPO_PERSONA_MORAL = new Long(0L);
	private Long TIPO_PERSONA_FISICA = new Long(1L);
	private Long IDCAMPO_TIPOORGANIZACION = new Long(419L);

	@Autowired
	public InvolucradoDelegate involucradoDelegate;
	@Autowired
	public ExpedienteDelegate expedienteDelegate;

	/**
	 * Metodo utilizado para realizar la carga del combo Calidad
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward cargarCalidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return null;
	}

	/**
	 * Metodo utilizado para guardar los datos de un individuo dependiendo su
	 * calidad
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward guardarIndividuo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			IngresarIndividuoForm retorno = new IngresarIndividuoForm();
			logger.info("ejecutando Action guardarIndividuo");
			String numeroExpediente=request.getParameter("numeroExpediente");
			String esMismoDomicilio=request.getParameter("mismoDomicilioNotificaciones");
			
			logger.info("ejecutando Action guardarIndividuo mas numero de expediente=#####"+numeroExpediente);
			logger.info("ejecutando Action guardarIndividuo esMismoDomicilio "+ esMismoDomicilio);
			IngresarIndividuoForm forma = (IngresarIndividuoForm) form;
			InvolucradoDTO involucradoDTO = new InvolucradoDTO();
			involucradoDTO.setExpedienteDTO(super.getExpedienteTrabajo(request,numeroExpediente));
			if(esMismoDomicilio != null && esMismoDomicilio.equals("1"))
				involucradoDTO.setEsMismoDomicilio(true);
			else
				involucradoDTO.setEsMismoDomicilio(false);
			
			logger.info("#########$$$$$$$$$Numero expedinte:"+super.getExpedienteTrabajo(request,numeroExpediente));
			logger.info("&&&&&&&&&&&&&Forma:"+forma);
			Long idDetencion = NumberUtils.toLong(request.getParameter("idDetencion"),0L);
			logger.info("id de la detencion:"+idDetencion);
			String idIndividuo=request.getParameter("idIndividuo");
			String idPropParaDefensor=request.getParameter("idPropParaDefensor");
			logger.info("&&&&&&&&&&&&&Elemnto id:"+idIndividuo);
			involucradoDTO.setElementoId(NumberUtils.toLong(idIndividuo,0L));
			CalidadDTO calidadDTO = new CalidadDTO();
			if(forma.getCalidadDelIndividuo().equals(CALIDAD_QUIEN_DETUVO)){
				//agregamos los alias del involucrado
				if(StringUtils.isNotBlank(forma.getAlias()))
				{
					List<AliasInvolucradoDTO> listaAliasDTO = new ArrayList<AliasInvolucradoDTO>();
					//obtenemos los alias
					String[] arrAliasInvlocurado=forma.getAlias().split(",");
					logger.info("alias_del_involucrado:: "+arrAliasInvlocurado);	
					for (String alias : arrAliasInvlocurado) {
						AliasInvolucradoDTO aliasDTO = new AliasInvolucradoDTO();
	                    aliasDTO.setAlias(alias);
	                    listaAliasDTO.add(aliasDTO);
	                    aliasDTO=null;
					}
                    involucradoDTO.setAliasInvolucradoDTO(listaAliasDTO);
				}							
			}
			
			if (forma.getCalidadDelIndividuo().equals(
					CALIDAD_PROBABLE_RESPONSABLE)) {
				if (forma.getEsPersonaMoral()) {
					logger.info("ingresaremos un probable responsable moral");
					involucradoDTO.setTipoPersona(TIPO_PERSONA_MORAL);
					calidadDTO
							.setCalidades(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
					// Datos propios de la pantalla de probable responsable
					// fisico
					OrganizacionDTO organizacionDTO = new OrganizacionDTO();
					organizacionDTO.setNombreOrganizacion(forma.getNombreOrg());
					organizacionDTO.setNombreCorto(forma.getNombreCortoOrg());
					organizacionDTO
							.setDireccionInternet(forma.getDirInternet());

					ValorDTO valorByTipoOrganizacionVal = new ValorDTO();
					valorByTipoOrganizacionVal
							.setIdCampo(IDCAMPO_TIPOORGANIZACION);

					// TODO: Setear las propiedades del tipo de organización
					// para que sean guardadas
					if (!forma.getTipoOrganizacion().equals("-1")) {
						valorByTipoOrganizacionVal.setValor(forma
								.getTipoOrganizacion());
					}

					organizacionDTO
							.setValorByTipoOrganizacionVal(valorByTipoOrganizacionVal);
					organizacionDTO.setFechaCreacionElemento(DateUtils
							.obtener(forma.getFechaIngreso()));
					CalidadDTO calidadOrg = new CalidadDTO();
					calidadOrg
							.setCalidades(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
					organizacionDTO.setCalidadDTO(calidadOrg);
					involucradoDTO.setOrganizacionDTO(organizacionDTO);

				} else {
					logger.info("ingresaremos un probable responsable fisico");
					involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
					calidadDTO
							.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
					// Datos propios de la pantalla de probable responsable
					// fisico
					involucradoDTO.setCondicion(forma.getCalidadDelIndividuo());
					logger.info("AAAAAAAAAAAAAAAAAAAAAAAAnonimo:"+forma.getEsDesconocido());
					involucradoDTO.setEsVivo(forma.getEsVivo());
					logger.info("¡ATENCION! Es Vivo: " + forma.getEsVivo());
					if (forma.getEsDesconocido()) {
						involucradoDTO.setDesconocido("true");
						forma.setNombre("Desconocido");
						forma.setApellidoPaterno(" ");
						forma.setApellidoMaterno(" ");
					} else {
						involucradoDTO.setDesconocido("false");
					}
					if (forma.getEsMenorDeEdad()) {

					}
					involucradoDTO.setEsDetenido(forma.getEstaDetenido());
					
					logger.info("Afuera de detenido:"+forma.getEstaDetenido());		
					
					if(forma.getEstaDetenido()){
						
						logger.info("Entra a setear datos de esta detenido:"+forma.getEstaDetenido());						
						ArrayList<DetencionDTO> detencionDTOs = new ArrayList<DetencionDTO>();
						DetencionDTO detencionDTO = new DetencionDTO();
						detencionDTO.setFechaInicioDetencion(forma.getFechaInicioLapso());
						detencionDTO.setHoraInicioDetencion(forma.getHoraInicioLapso());
						detencionDTO.setFechaRecepcionDetencion(forma.getFechaFinLapso());
						detencionDTO.setHoraRecepcionDetencion(forma.getHoraFinLapso());
						if(idDetencion!=0){
							logger.info("Entra a setear el id del objeto detencion:"+ idDetencion);		
							detencionDTO.setDetencionId(idDetencion);
						}
						detencionDTOs.add(detencionDTO);
						involucradoDTO.setDetenciones(detencionDTOs);
												
					}
					//agregamos los alias del involucrado
					if(StringUtils.isNotBlank(forma.getAlias()))
					{
						List<AliasInvolucradoDTO> listaAliasDTO = new ArrayList<AliasInvolucradoDTO>();
						//obtenemos los alias
						String[] arrAliasInvlocurado=forma.getAlias().split(",");
						logger.info("alias_del_involucrado:: "+arrAliasInvlocurado);	
						for (String alias : arrAliasInvlocurado) {
							AliasInvolucradoDTO aliasDTO = new AliasInvolucradoDTO();
		                    aliasDTO.setAlias(alias);
		                    listaAliasDTO.add(aliasDTO);
		                    aliasDTO=null;
						}
	                    involucradoDTO.setAliasInvolucradoDTO(listaAliasDTO);
					}
					
					//Guardo objeto media filiacion en el involucrado
					involucradoDTO.setMediaFiliacionDTO(obtenerMediaFiliacion(forma));
				}
			} else if (forma.getCalidadDelIndividuo().equals(
					CALIDAD_CONTACTO_ORGANIZACIONAL)) {
				logger.info("ingresaremos un contacto de organización");
				calidadDTO.setCalidades(Calidades.CONTACTO_ORGANIZACION);
			} else if (forma.getCalidadDelIndividuo().equals(
					CALIDAD_VICTIMA_PERSONA)) {
				logger.info("ingresaremos una victima");
				calidadDTO.setCalidades(Calidades.VICTIMA_PERSONA);
				
					logger.info("ingresaremos un victima fisica");
					involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
					
					//F24.11 - Cambio para que guarde el estado de una víctima, saludos
					involucradoDTO.setEsVivo(forma.getEsVivo());
				
				//agregamos los alias del involucrado
				if(StringUtils.isNotBlank(forma.getAlias()))
				{
					List<AliasInvolucradoDTO> listaAliasDTO = new ArrayList<AliasInvolucradoDTO>();
					//obtenemos los alias
					String[] arrAliasInvlocurado=forma.getAlias().split(",");
					logger.info("alias_del_involucrado:: "+arrAliasInvlocurado);	
					for (String alias : arrAliasInvlocurado) {
						AliasInvolucradoDTO aliasDTO = new AliasInvolucradoDTO();
	                    aliasDTO.setAlias(alias);
	                    listaAliasDTO.add(aliasDTO);
	                    aliasDTO=null;
					}
                    involucradoDTO.setAliasInvolucradoDTO(listaAliasDTO);
				}
				
				//Guardo objeto media filiacion en el involucrado
				involucradoDTO.setMediaFiliacionDTO(obtenerMediaFiliacion(forma));
				involucradoDTO.setEsServidor(forma.getEsServidorPublico());
				if (forma.getEsDesconocido()) {
					involucradoDTO.setDesconocido("true");
					forma.setNombre("Desconocido");
					forma.setApellidoPaterno("");
					forma.setApellidoMaterno("");
				} else {
					involucradoDTO.setDesconocido("false");
				}
					logger.info("ingresaremos un victima fisica");
					involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
				
				
			} else if (forma.getCalidadDelIndividuo().equals(CALIDAD_TUTOR)) {
				logger.info("ingresaremos un tutor");
				
					logger.info("ingresaremos un tutor fisica");
					involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
				
				calidadDTO.setCalidades(Calidades.TUTOR);
				
					logger.info("ingresaremos un tutor fisica");
					involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
				
			} else if (forma.getCalidadDelIndividuo().equals(
					CALIDAD_DENUNCIANTE)) {
				logger.info("ingresaremos un denunciante");
				involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
				involucradoDTO.setCondicion((short)0);
				if(forma.getEsVictimayDenunciante()){
					involucradoDTO.setCondicion((short)1);
				}
				involucradoDTO.setEsServidor(forma.getEsServidorPublico());
				involucradoDTO.setDesconocido("");
				if (forma.getEsAnonimo()) {
					involucradoDTO.setDesconocido("true");
				} else {
					involucradoDTO.setDesconocido("false");
				}
				if (!forma.getEsPersonaMoral()) {
					logger.info("ingresaremos un Denunciante fisica");
					involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
				}
				
				calidadDTO.setCalidades(Calidades.DENUNCIANTE);
				if (!forma.getEsPersonaMoral()) {
					logger.info("ingresaremos un Denunciante fisica");
					involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
				}
			} else if (forma.getCalidadDelIndividuo().equals(CALIDAD_TESTIGO)) {
				logger.info("ingresaremos un testigo");
				involucradoDTO.setEsProtegido(BooleanUtils.isTrue(forma.getEsProtegido()));
				involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
				calidadDTO.setCalidades(Calidades.TESTIGO);
				
					logger.info("ingresaremos un testigo fisica");
					involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
				
			} else if (forma.getCalidadDelIndividuo().equals(
					CALIDAD_REPRESENTANTE_LEGAL)) {
				logger.info("ingresaremos un representante legal");
				calidadDTO.setCalidades(Calidades.REPRESENTANTE_LEGAL);
				if (!forma.getEsPersonaMoral()) {
					logger.info("ingresaremos un testigo fisica");
					involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
				}
			} else if (forma.getCalidadDelIndividuo().equals(CALIDAD_TRADUCTOR)) {
				logger.info("ingresaremos un traductor");
				calidadDTO.setCalidades(Calidades.TRADUCTOR);
				involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
			} else if(forma.getCalidadDelIndividuo().equals(CALIDAD_QUIEN_DETUVO)){
				logger.info("ingresaremos un traductor");
				calidadDTO.setCalidades(Calidades.QUIEN_DETUVO);
				involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
				
				List<Long> lstDetenidos = new ArrayList<Long>();
				String[] idsDetenidos = forma.getStrDetenidos().split(",");
				for (int i = 0; i < idsDetenidos.length; i++) {
					Long detenido = Long.parseLong(idsDetenidos[i]);
					lstDetenidos.add(detenido);
				}
				involucradoDTO.setIdsDetenidos(lstDetenidos);

			}else if(forma.getCalidadDelIndividuo().equals(CALIDAD_DEFENSOR)){
				logger.info("ingresaremos un defensor");
				calidadDTO.setCalidades(Calidades.DEFENSOR_PUBLICO);
				involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
				//Linea para agregar el id del defensor
				//idPropParaDefensor
			}else if(forma.getCalidadDelIndividuo().equals(CALIDAD_DEFENSOR_PRIVADO)){
				logger.info("ingresaremos un defensor");
				calidadDTO.setCalidades(Calidades.DEFENSOR_PRIVADO);
				involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
			}else if(forma.getCalidadDelIndividuo().equals(CALIDAD_SOLICITANTE)){
				logger.info("INGRESAREMOS UN SOLICITANTE CIUDADANO");
				calidadDTO.setCalidades(Calidades.SOLICITANTE);
				involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
			}
			
			involucradoDTO.setCalidadDTO(calidadDTO);
			
			if(BooleanUtils.isTrue(forma.getEsServidorPublico())){
				logger.info("Datos Servidor Publico" + forma.getNumEpmleadoServPublico());
				ServidorPublicoDTO servidorPublicoDTO = new ServidorPublicoDTO();
				if(forma.getTipoServidorPublico()==0)
				{
					//FIXME falta definir los campos correctos para setear la informacion de un servidor publico interno
					if(StringUtils.isNotBlank(forma.getAreaServPublico()) && !forma.getAreaServPublico().equals("-1"))
					{
						servidorPublicoDTO.setNivelDependencia(new ValorDTO(Long.parseLong(forma.getAreaServPublico())));
					}
					if(StringUtils.isNotBlank(forma.getDeptoServPublico()) && !forma.getDeptoServPublico().equals("-1"))
					{
						servidorPublicoDTO.setTipoDependencia(new ValorDTO(Long.parseLong(forma.getDeptoServPublico())));
					}
					if(StringUtils.isNotBlank(forma.getNombreServPublicoInt()) && !forma.getNombreServPublicoInt().equals("-1"))
					{
						servidorPublicoDTO.setNumeroEmpleado(forma.getNombreServPublicoInt());
					}
					if(StringUtils.isNotBlank(forma.getPuestoServPublicoInt()))
					{
						servidorPublicoDTO.setPuesto(forma.getPuestoServPublicoInt());
					}
				}
				else//servidor publico externo
				{	
					if(StringUtils.isNotBlank(forma.getNivelDepServPublico()) && !forma.getNivelDepServPublico().equals("-1"))
					{
						servidorPublicoDTO.setNivelDependencia(new ValorDTO(Long.parseLong(forma.getNivelDepServPublico())));
					}
					if(StringUtils.isNotBlank(forma.getTipoDepServPublico()) && !forma.getTipoDepServPublico().equals("-1"))
					{
						servidorPublicoDTO.setTipoDependencia(new ValorDTO(Long.parseLong(forma.getTipoDepServPublico())));
					}
					if(StringUtils.isNotBlank(forma.getDependenciaServPublico()) && !forma.getDependenciaServPublico().equals("-1"))
					{
						servidorPublicoDTO.setDependencia(forma.getDependenciaServPublico());
					}
					if(StringUtils.isNotBlank(forma.getPuestoServPublico()))
					{
						servidorPublicoDTO.setPuesto(forma.getPuestoServPublico());
					}
					if(StringUtils.isNotBlank(forma.getNumEpmleadoServPublico()))
					{
						servidorPublicoDTO.setNumeroEmpleado(forma.getNumEpmleadoServPublico());
					}
					//FIXME Falta definir los campos paara guardar nombre y apéllidos para un servidor publico externo  
					if(StringUtils.isNotBlank(forma.getNombreServPublicoExt()))
					{
					}
					if(StringUtils.isNotBlank(forma.getAppaternoServPublicoExt()))
					{}
					if(StringUtils.isNotBlank(forma.getApmaternoServPublico()))
					{}
				}
			
				involucradoDTO.setServidorPublicoDTO(servidorPublicoDTO);
				logger.info("seteo informacion de servidor publico EXP******.........");
			}
			List<NombreDemograficoDTO> lstDatosGenerales = new ArrayList<NombreDemograficoDTO>();
			NombreDemograficoDTO datosGenerales = new NombreDemograficoDTO();
			datosGenerales.setNombre(forma.getNombre());
			datosGenerales.setApellidoPaterno(forma.getApellidoPaterno());
			datosGenerales.setApellidoMaterno(forma.getApellidoMaterno());
			retorno.setNombre(forma.getNombre()+" "+forma.getApellidoPaterno()+" "+forma.getApellidoMaterno());
			
			if(forma.getNombre()==null){
				retorno.setNombre("Anónimo");
			}else if(forma.getNombre().equals("")){
				retorno.setNombre("Anónimo");
			}
			if (forma.getCalidadDelIndividuo().equals(CALIDAD_VICTIMA_PERSONA)|| forma.getCalidadDelIndividuo().equals(CALIDAD_PROBABLE_RESPONSABLE)){
				if(forma.getNombre()==null){
					retorno.setNombre("Desconocido");
				}else if(forma.getNombre().equals("")){
					retorno.setNombre("Desconocido");
				}
			}
			datosGenerales.setCurp(forma.getCurp());
			datosGenerales.setRfc(forma.getRfc());
			datosGenerales.setSexo(forma.getSexo());
			if (forma.getFechaNacimiento() != null && !forma.getFechaNacimiento().equals("")) {
				datosGenerales.setFechaNacimiento(DateUtils.obtener(forma
						.getFechaNacimiento()));
			}
			datosGenerales.setEdadAproximada(forma.getEdadAproximada());
			
			//seteamos el lugar de nacimiento
			if (!(forma.getEsPersonaMoral() !=null && forma.getEsPersonaMoral()))
			{
				if(forma.getPaisNacimiento()!=null && !forma.getPaisNacimiento().equals("-1"))
				{
					datosGenerales.setPaisValorDTO(new ValorDTO(Long.parseLong(forma.getPaisNacimiento())));
					logger.info("ID_PAIS_NAC::"+forma.getPaisNacimiento());
					if(forma.getEntFederativaNacimiento()!=null && !forma.getEntFederativaNacimiento().equals("-1"))
					{
						datosGenerales.setEntidadFederativaDTO(new EntidadFederativaDTO(Long.parseLong(forma.getEntFederativaNacimiento())));
						logger.info("ID_ENTFED_NAC::"+forma.getEntFederativaNacimiento());
						if(forma.getMunicipioNacimiento()!=null && !forma.getMunicipioNacimiento().equals("-1"))
						{
							datosGenerales.setMunicipioDTO(new MunicipioDTO(Long.parseLong(forma.getMunicipioNacimiento())));
							logger.info("ID_MUN_NAC::"+forma.getMunicipioNacimiento());
						}
						else
						{
							datosGenerales.setMunicipioDTO(null);
						}
					}
					else
					{
						datosGenerales.setEntidadFederativaDTO(null);
					}
				}
				else
				{
					datosGenerales.setPaisValorDTO(null);
				}
			}
			
			//datosGenerales.setPaisValorDTO(new ValorDTO());
			
			
			lstDatosGenerales.add(datosGenerales);
			involucradoDTO.setFechaCreacionElemento(DateUtils.obtener(forma
					.getFechaIngreso()));

			ValorDTO valorGenerico = new ValorDTO();
			//valorGenerico.setIdCampo(IDCAMPO_TIPOORGANIZACION);
			//valorGenerico.setValor(forma.getIdioma());
			// log.info("+++++++++++++++++++++++++Idioma forma:" +
			// forma.getIdioma());
			// valorGenerico.setIdCampo(idCampo)
			// involucradoDTO.setValorIdIdioma(valorGenerico);

			valorGenerico = new ValorDTO();
//			Long religion=Long.parseLong(forma.getReligion());
//			valorGenerico.setIdCampo(religion);
			// log.info("+++++++++++++++++++++++++getReligion forma:" +
			// forma.getReligion());
//			involucradoDTO.setValorIdReligion(valorGenerico);

			valorGenerico = new ValorDTO();
			Long escolaridad=0L;
			if(forma.getEscolaridad()!=null && ! forma.getEscolaridad().equals("- Selecciona -")){
				escolaridad=Long.parseLong(forma.getEscolaridad());
			}else{
				escolaridad=null;
			}
			
			valorGenerico.setIdCampo(escolaridad);
			// log.info("+++++++++++++++++++++++++getEscolaridad forma:" +
			// forma.getEscolaridad());
			involucradoDTO.setValorIdEscolaridad(valorGenerico);

			valorGenerico = new ValorDTO();
			Long estadoCivil=0L;
			if(forma.getEstadoCivil()!=null && ! forma.getEstadoCivil().equals("- Selecciona -")){
				 estadoCivil=Long.parseLong(forma.getEstadoCivil());
			}else{
				estadoCivil=null;
			}
			
			valorGenerico.setIdCampo(estadoCivil);
			// log.info("+++++++++++++++++++++++++getEstadoCivil forma:" +
			// forma.getEstadoCivil());
			involucradoDTO.setValorIdEstadoCivil(valorGenerico);
			
			valorGenerico = new ValorDTO();
			Long idioma= 0L;
			if(forma.getIdioma()!=null && ! forma.getIdioma().equals("- Selecciona -")){
				idioma=Long.parseLong(forma.getIdioma());
			}else{
				idioma=null;
			}
			valorGenerico.setIdCampo(idioma);
			involucradoDTO.setValorIdIdioma(valorGenerico);
			
			List<ValorDTO> listaValor=new ArrayList<ValorDTO>();
			String[] idsOcupaciones = forma.getOcupacion().split(",");
			if(idsOcupaciones!=null && idsOcupaciones[0]!=""){
				for (int i = 0; i < idsOcupaciones.length; i++) {
					valorGenerico = new ValorDTO();
					Long ocupacion=Long.parseLong(idsOcupaciones[i]);
					valorGenerico.setIdCampo(ocupacion);
					listaValor.add(valorGenerico);
				}
			}
			involucradoDTO.setValorIdOcupacion(listaValor);
			
			listaValor=new ArrayList<ValorDTO>();
			String[] idsNacionalidades = forma.getNacionalidad().split(",");
			if(idsNacionalidades!=null && idsNacionalidades[0]!=""){
				for (int i = 0; i < idsNacionalidades.length; i++) {
					valorGenerico = new ValorDTO();
					Long nacionalidad=Long.parseLong(idsNacionalidades[i]);
					valorGenerico.setIdCampo(nacionalidad);
					listaValor.add(valorGenerico);
				}
			}
			involucradoDTO.setValorIdNacionalidad(listaValor);

			// TODO: Falta a de alias hasta nacionalidad
			involucradoDTO.setNombresDemograficoDTO(lstDatosGenerales);

			DomicilioDTO domicilio = new DomicilioDTO();
			DomicilioExtranjeroDTO domExtranjero = new DomicilioExtranjeroDTO();
			EntidadFederativaDTO estado = new EntidadFederativaDTO();
			AsentamientoDTO asentamientoDTO = new AsentamientoDTO();
			CiudadDTO ciudad = new CiudadDTO();

			if (!forma.getPais().equals("") && !forma.getPais().equals("-1")){
				logger.info("/**** Pais :: "+forma.getPais());
				calidadDTO = new CalidadDTO();
				calidadDTO.setCalidades(Calidades.DOMICILIO);				
				
				if (forma.getPais().equals("10")) {
					
					valorGenerico = new ValorDTO();
					valorGenerico.setValor(forma.getPais());
					estado.setValorIdPais(valorGenerico);
					
					if (!forma.getEntidadFederativa().equals("")
							&& !forma.getEntidadFederativa().equals("-1")) {
						estado.setEntidadFederativaId(new Long(forma.getEntidadFederativa()));
						domicilio.setEntidadDTO(estado);
					}
					if (!forma.getDelegacionMunicipio().equals("")
							&& !forma.getDelegacionMunicipio().equals("-1")) {
						MunicipioDTO municipio = new MunicipioDTO(new Long(forma.getDelegacionMunicipio()), "", estado);
						asentamientoDTO.setMunicipioDTO(municipio);
						domicilio.setMunicipioDTO(municipio);
					}										
					if (!forma.getCiudad().equals("")
							&& !forma.getCiudad().equals("-1")) {
						ciudad.setCiudadId(new Long(forma.getCiudad()));
						domicilio.setCiudadDTO(ciudad);
						asentamientoDTO.setCiudadDTO(ciudad);
					}					
					
					if (!forma.getAsentamientoColonia().equals("")
							&& !forma.getAsentamientoColonia().equals("-1")) {
						asentamientoDTO.setAsentamientoId(new Long(forma.getAsentamientoColonia()));
					}
					if (!forma.getTipoAsentamiento().equals("")
							&& !forma.getTipoAsentamiento().equals("-1")) {
						TipoAsentamientoDTO tipoAsentamientoDTO=new TipoAsentamientoDTO(Long.parseLong(forma.getTipoAsentamiento()), "");
						asentamientoDTO.setTipoAsentamientoDTO(tipoAsentamientoDTO);
					}
					if (!(forma.getLatitudN()== null) && !forma.getLatitudN().equals("")) {
						String lat= forma.getLatitudN()+forma.getLatitudGrados()+"°"+forma.getLatitudMinutos()+"'"+forma.getLatitudSegundos()+"\"";
						domicilio.setLatitud(lat);
					}
					if (!(forma.getLongitudE()== null) && !forma.getLongitudE().equals("")) {
						String longitud= forma.getLongitudE()+forma.getLongitudGrados()+"°"+forma.getLongitudMinutos()+"'"+forma.getLongitudSegundos()+"\"";
						domicilio.setLongitud(longitud);
					}
								
					domicilio.setAsentamientoDTO(asentamientoDTO);
					domicilio.setCalle(forma.getCalle());
					domicilio.setNumeroExterior(forma.getNumExterior());
					domicilio.setNumeroInterior(forma.getNumInterior());
					domicilio.setEntreCalle1(forma.getEntreCalle());
					domicilio.setEntreCalle2(forma.getYcalle());
					domicilio.setAlias(forma.getAliasDomicilio());
					domicilio.setEdificio(forma.getEdificio());
					domicilio.setReferencias(forma.getReferencias());
					domicilio.setFechaCreacionElemento(DateUtils.obtener(forma.getFechaIngreso()));
					if (!forma.getTipoCalle().equals("")
							&& !forma.getTipoCalle().equals("-1")) {
						domicilio.setValorCalleId(new ValorDTO(new Long(forma.getTipoCalle())));// Tipo de calle
					}
					
					domicilio.setCalidadDTO(calidadDTO);
					domicilio.setExpedienteDTO(super.getExpedienteTrabajo(request,numeroExpediente));
					involucradoDTO.setDomicilio(domicilio);
				} else {
					
					domExtranjero.setPaisValor(new ValorDTO(new Long(forma.getPais()), forma.getPais()));
					domExtranjero.setEstado(forma.getEntidadFederativa());
					domExtranjero.setCiudad(forma.getCiudad());
					domExtranjero.setMunicipio(forma.getDelegacionMunicipio());
					domExtranjero.setCodigoPostal(forma.getCodigoPostal());
					domExtranjero.setAsentamientoExt(forma.getAsentamientoColonia());
					
					if (!(forma.getLatitudN()== null) && !forma.getLatitudN().equals("")) {
						String lat= forma.getLatitudN()+forma.getLatitudGrados()+"°"+forma.getLatitudMinutos()+"'"+forma.getLatitudSegundos()+"\"";
						domExtranjero.setLatitud(lat);
					}
					if (!(forma.getLongitudE()== null) && !forma.getLongitudE().equals("")) {
						String longitud= forma.getLongitudE()+forma.getLongitudGrados()+"°"+forma.getLongitudMinutos()+"'"+forma.getLongitudSegundos()+"\"";
						domExtranjero.setLongitud(longitud);
					}
					
					domExtranjero.setCalle(forma.getCalle());
					domExtranjero.setNumeroExterior(forma.getNumExterior());
					domExtranjero.setNumeroInterior(forma.getNumInterior());
					domExtranjero.setEntreCalle1(forma.getEntreCalle());
					domExtranjero.setEntreCalle2(forma.getYcalle());
					domExtranjero.setAlias(forma.getAliasDomicilio());
					domExtranjero.setEdificio(forma.getEdificio());
					domExtranjero.setReferencias(forma.getReferencias());
					domExtranjero.setFechaCreacionElemento(DateUtils.obtener(forma.getFechaIngreso()));
//					if (!forma.getTipoCalle().equals("")
//							&& !forma.getTipoCalle().equals("-1")) {
//						domicilio.setValorCalleId(new ValorDTO(new Long(forma.getTipoCalle())));// Tipo de calle
//					}
					
					domExtranjero.setCalidadDTO(calidadDTO);
					domExtranjero.setExpedienteDTO(super.getExpedienteTrabajo(request,numeroExpediente));
					involucradoDTO.setDomicilio(domExtranjero);
				}
			}									

//			if (!forma.getPais().equals("") && !forma.getPais().equals("-1")) {
//				valorGenerico = new ValorDTO();
//				valorGenerico.setValor(forma.getPais());
//				estado.setValorIdPais(valorGenerico);
//			}			
			
			// TODO:Coordenadas geograficas
//			involucradoDTO.setDomicilio(domicilio);

			DomicilioDTO domicilioNotif = new DomicilioDTO();
			asentamientoDTO = new AsentamientoDTO();

			if (!forma.getPaisNotif().equals("") && !forma.getPaisNotif().equals("-1")) {
				logger.info("/**** Pais NOT :: "+forma.getPaisNotif());
				calidadDTO = new CalidadDTO();
				calidadDTO.setCalidades(Calidades.DOMICILIO);
				
				if (forma.getPaisNotif().equals("10")) {
					logger.info("/**** Domicilio NOT Normal");
					
					valorGenerico = new ValorDTO();
					valorGenerico.setValor(forma.getPaisNotif());
					estado.setValorIdPais(valorGenerico);
					
					estado = new EntidadFederativaDTO();
					if (!forma.getEntidadFederativaNotif().equals("")
							&& !forma.getEntidadFederativaNotif().equals("-1")) {
						estado.setEntidadFederativaId(new Long(forma.getEntidadFederativaNotif()));
						domicilioNotif.setEntidadDTO(estado);
						
					}
					if (!forma.getDelegacionMunicipioNotif().equals("")
							&& !forma.getAsentamientoColoniaNotif().equals("-1")) {
						MunicipioDTO municipio = new MunicipioDTO(new Long(
								forma.getDelegacionMunicipioNotif()), "", estado);
						asentamientoDTO.setMunicipioDTO(municipio);
						domicilioNotif.setMunicipioDTO(municipio);
					}

					ciudad = new CiudadDTO();
					if (!forma.getCiudadNotif().equals("")
							&& !forma.getCiudadNotif().equals("-1")) {
						ciudad.setCiudadId(new Long(forma.getCiudadNotif()));
						domicilioNotif.setCiudadDTO(ciudad);				
					}
					asentamientoDTO.setCiudadDTO(ciudad);

					if (!forma.getAsentamientoColoniaNotif().equals("")
							&& !forma.getAsentamientoColoniaNotif().equals("-1")) {
						asentamientoDTO.setAsentamientoId(new Long(forma
								.getAsentamientoColoniaNotif()));
					}
					
					/**** Latitud de Notificaciones*****/
					if (!(forma.getLatitudNNotif()== null) && !forma.getLatitudNNotif().equals("")) {
						String latNotif= forma.getLatitudNNotif()+forma.getLatitudGradosNotif()+"°"+forma.getLatitudMinutosNotif()+"'"+forma.getLatitudSegundosNotif()+"\"";
						domicilioNotif.setLatitud(latNotif);
						logger.info("LATITUD:: "+latNotif);
					}
					if (!(forma.getLongitudENotif()== null) && !forma.getLongitudENotif().equals("")) {
						String longitudNotif= forma.getLongitudENotif()+forma.getLongitudGradosNotif()+"°"+forma.getLongitudMinutosNotif()+"'"+forma.getLongitudSegundosNotif()+"\"";
						domicilioNotif.setLongitud(longitudNotif);
						logger.info("LONGITUD:: "+longitudNotif);
					}
					/**** FIN Latitud de Notificaciones*****/
					domicilioNotif.setAsentamientoDTO(asentamientoDTO);
					domicilioNotif.setCalle(forma.getCalleNotif());
					domicilioNotif.setNumeroExterior(forma.getNumExteriorNotif());
					domicilioNotif.setNumeroInterior(forma.getNumInteriorNotif());
					domicilioNotif.setEntreCalle1(forma.getEntreCalleNotif());
					domicilioNotif.setEntreCalle2(forma.getYcalleNotif());
					domicilioNotif.setAlias(forma.getAliasDomicilioNotif());
					domicilioNotif.setEdificio(forma.getEdificioNotif());
					domicilioNotif.setReferencias(forma.getReferenciasNotif());
					domicilioNotif.setFechaCreacionElemento(DateUtils.obtener(forma.getFechaIngreso()));
					
					if (!forma.getTipoCalleNotif().equals("")
							&& !forma.getTipoCalleNotif().equals("-1")) {
						domicilioNotif.setValorCalleId(new ValorDTO(new Long(forma.getTipoCalleNotif())));// Tipo de calle
					}
					
					domicilioNotif.setCalidadDTO(calidadDTO);
					domicilioNotif.setExpedienteDTO(super.getExpedienteTrabajo(request,numeroExpediente));
					involucradoDTO.setDomicilioNotificacion(domicilioNotif);
				} else {
					DomicilioExtranjeroDTO domExtranjeroNot = new DomicilioExtranjeroDTO();
					
					domExtranjeroNot.setPaisValor(new ValorDTO(new Long(forma.getPais()), forma.getPaisNotif()));			
					domExtranjeroNot.setEstado(forma.getEntidadFederativaNotif());					
					domExtranjeroNot.setCiudad(forma.getCiudadNotif());					
					domExtranjeroNot.setMunicipio(forma.getDelegacionMunicipioNotif());					
					domExtranjeroNot.setCodigoPostal(forma.getCodigoPostalNotif());					
					domExtranjeroNot.setAsentamientoExt(forma.getAsentamientoColoniaNotif());
					
					if (!(forma.getLatitudNNotif()== null) && !forma.getLatitudNNotif().equals("")) {
						String latNotif= forma.getLatitudNNotif()+forma.getLatitudGradosNotif()+"°"+forma.getLatitudMinutosNotif()+"'"+forma.getLatitudSegundosNotif()+"\"";
						domExtranjeroNot.setLatitud(latNotif);						
					}
					if (!(forma.getLongitudENotif()== null) && !forma.getLongitudENotif().equals("")) {
						String longitudNotif= forma.getLongitudENotif()+forma.getLongitudGradosNotif()+"°"+forma.getLongitudMinutosNotif()+"'"+forma.getLongitudSegundosNotif()+"\"";
						domExtranjeroNot.setLongitud(longitudNotif);						
					}
					
					domExtranjeroNot.setCalle(forma.getCalleNotif());
					domExtranjeroNot.setNumeroExterior(forma.getNumExteriorNotif());
					domExtranjeroNot.setNumeroInterior(forma.getNumInteriorNotif());
					domExtranjeroNot.setEntreCalle1(forma.getEntreCalleNotif());
					domExtranjeroNot.setEntreCalle2(forma.getYcalleNotif());
					domExtranjeroNot.setAlias(forma.getAliasDomicilioNotif());
					domExtranjeroNot.setEdificio(forma.getEdificioNotif());
					domExtranjeroNot.setReferencias(forma.getReferenciasNotif());
					domExtranjeroNot.setFechaCreacionElemento(DateUtils.obtener(forma.getFechaIngreso()));
//					if (!forma.getTipoCalle().equals("")
//							&& !forma.getTipoCalle().equals("-1")) {
//						domicilio.setValorCalleId(new ValorDTO(new Long(forma.getTipoCalle())));// Tipo de calle
//					}
					
					domExtranjeroNot.setCalidadDTO(calidadDTO);
					domExtranjeroNot.setExpedienteDTO(super.getExpedienteTrabajo(request,numeroExpediente));
					involucradoDTO.setDomicilioNotificacion(domExtranjeroNot);
				}				
			}

//			if (!forma.getPais().equals("") && !forma.getPais().equals("-1")) {
//				valorGenerico = new ValorDTO();
//				valorGenerico.setValor(forma.getPaisNotif());
//				estado.setValorIdPais(valorGenerico);
//			}

			List<TelefonoDTO> lstTelefonos = new ArrayList<TelefonoDTO>();
			String strTelefonos = forma.getMedioContactoTelefono();
			StringTokenizer lstStrTelefonos = new StringTokenizer(strTelefonos,
					"|");
			while (lstStrTelefonos.hasMoreElements()) {
				String strTelefono = (String) lstStrTelefonos.nextElement();
				String[] datosTelefono = strTelefono.split(",");

				TelefonoDTO telefono = new TelefonoDTO();

				ValorDTO valorTipoTelefono = new ValorDTO();
				if(datosTelefono.length!=0){
					valorTipoTelefono.setIdCampo(new Long(datosTelefono[0]));
					logger.info("&&&&Telefono:"+datosTelefono[0]);
					telefono.setValorTipoTelefono(valorTipoTelefono);
					telefono.setCodigoPais(datosTelefono[1]);
					logger.info("&&&&Telefono:"+datosTelefono[1]);
					telefono.setCodigoArea(datosTelefono[2]);
					logger.info("&&&&Telefono:"+datosTelefono[2]);
					telefono.setNumeroTelefonico(datosTelefono[3]);
					logger.info("&&&&Telefono:"+datosTelefono[3]);
					lstTelefonos.add(telefono);
				}
				
			}
			involucradoDTO.setTelefonosDTO(lstTelefonos);

			List<CorreoElectronicoDTO> lstCorreos = new ArrayList<CorreoElectronicoDTO>();
			if(!forma.getMedioContactoCorreo().trim().isEmpty()){
				String[] datosCorreo = forma.getMedioContactoCorreo().split(",");
				for (int i = 0; i < datosCorreo.length; i++) {
					CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
					correo.setDireccionElectronica(datosCorreo[i]);
					lstCorreos.add(correo);
				}
			}
			involucradoDTO.setCorreosDTO(lstCorreos);
			logger.info("Ingresasr individuo antes del null pointer:");
			if (!(forma.getDocIdentificacion() == null)) {
				// valorIdIdentificacion.setValor(forma.getDocIdentificacion());
				if (!forma.getDocIdentificacion().equals("")&& !forma.getDocIdentificacion().equals("-1")) {
					ValorDTO valorIdIdentificacion = new ValorDTO();
					valorIdIdentificacion.setIdCampo(new Long(forma.getDocIdentificacion()));
					involucradoDTO.setValorIdIdentificaion(valorIdIdentificacion);
					involucradoDTO.setFolioIdentificacion(forma.getFolioDoc());
				}
			}
			if(forma.getAnular()!=null){
				involucradoDTO.setEsActivo(!forma.getAnular());
			}
//			involucradoDTO.setFolioIdentificacion(forma.getFolioDoc());
//			ValorDTO valorTipoDoc = new ValorDTO();
//			Long doc=0L;
//			if(forma.getDocIdentificacion()!=null && ! forma.getDocIdentificacion().equals("- Selecciona -")){
//				doc=Long.parseLong(forma.getDocIdentificacion());
//			}else{
//				doc=null;
//			}
//			valorTipoDoc.setIdCampo(doc);
//			involucradoDTO.setValorIdIdentificaion(valorTipoDoc);
			
			if (logger.isDebugEnabled()) {
				logger.debug("::::::::::::::INVOLUCRADO:::::::::Ingresar:::::::::::"+ involucradoDTO);
			}
			// TODO:Setear los valores de servidor publico en el dto que es
			// pasado para guardar los datos del individuo
			Long resp=null;
			if(forma.getCalidadDelIndividuo().equals(CALIDAD_DEFENSOR)||forma.getCalidadDelIndividuo().equals(CALIDAD_DEFENSOR_PRIVADO)){
				resp = involucradoDelegate.guardarDefensorAsignadoInvolucrado(involucradoDTO, Long.parseLong(idPropParaDefensor));
			}else{
				resp = involucradoDelegate.guardarInvolucrado(involucradoDTO);
				logger.info(".::Aquí se manda a guardar el individuo::.");
				logger.info("Nombre: " + involucradoDTO.getNombreCompleto());
				logger.info("Estado: " + involucradoDTO.getEsVivo());
				logger.info(".::::::::::::::::FIN:::::::::::::::::::::.");
			}
			
			logger.info("el valor de la respuesta es:" + resp);

			String xml = null;
			PrintWriter pw = null;
			converter.alias("IngresarIndividuoForm",IngresarIndividuoForm.class);
			if(involucradoDTO.getEsDetenido() != null && involucradoDTO.getEsDetenido()){
				retorno.setEstaDetenido(involucradoDTO.getEsDetenido());
			}
			retorno.setIdIndividuo(resp);
			xml = converter.toXML(retorno);
			if(logger.isDebugEnabled())
			{
				logger.info("Ingresar Individuo::" + xml);
			}
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			logger.info(e.getCause(), e);
			IngresarIndividuoForm retorno = new IngresarIndividuoForm();
			retorno.setIdIndividuo(0L);
			String xml = converter.toXML(retorno);
			PrintWriter pw = null;
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		}
		return null;
	}

	/**
	 * Metodo utilizado para guardar los datos de un ciudadano con
	 * calidad de denunciante  CU Registrar Datos Ciudadano
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward guardarCiudadano(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			logger.info("ejecutando Action guardarCiudadano");
			String numeroExpediente=request.getParameter("numeroExpediente");
			logger.info("ejecutando Action guardarCiudadano mas numero de expediente=#####"+numeroExpediente);
			IngresarIndividuoForm forma = (IngresarIndividuoForm) form;
			InvolucradoDTO involucradoDTO = new InvolucradoDTO();
			involucradoDTO.setExpedienteDTO(super.getExpedienteTrabajo(request,numeroExpediente));
			logger.info("#########$$$$$$$$$Numero expedinte:"+super.getExpedienteTrabajo(request,numeroExpediente));
			logger.info("&&&&&&&&&&&&&Forma:"+forma);
			CalidadDTO calidadDTO = new CalidadDTO();

			involucradoDTO.setTipoPersona(TIPO_PERSONA_FISICA);
			involucradoDTO.setElementoId(Elementos.PERSONA.getValorId());

			calidadDTO.setCalidades(Calidades.DENUNCIANTE);			
			involucradoDTO.setCalidadDTO(calidadDTO);
			involucradoDTO.setMotivoComparecencia(forma.getMotivoComparecencia());

			List<NombreDemograficoDTO> lstDatosGenerales = new ArrayList<NombreDemograficoDTO>();
			NombreDemograficoDTO datosGenerales = new NombreDemograficoDTO();
			datosGenerales.setNombre(forma.getNombre());
			datosGenerales.setApellidoPaterno(forma.getApellidoPaterno());
			datosGenerales.setApellidoMaterno(forma.getApellidoMaterno());
			datosGenerales.setCurp(forma.getCurp());
			datosGenerales.setRfc(forma.getRfc());
			datosGenerales.setSexo(forma.getSexo());
			
			if (forma.getFechaNacimiento() != null && !forma.getFechaNacimiento().trim().equals("")) {
				datosGenerales.setFechaNacimiento(DateUtils.obtener(forma
						.getFechaNacimiento()));
			}

			datosGenerales.setEdadAproximada(forma.getEdadAproximada());
			lstDatosGenerales.add(datosGenerales);

			
			ValorDTO valorGenerico = new ValorDTO();

			valorGenerico = new ValorDTO();
			Long estadoCivil=Long.parseLong(forma.getEstadoCivil());
			valorGenerico.setIdCampo(estadoCivil);
			// log.info("+++++++++++++++++++++++++getEstadoCivil forma:" +
			// forma.getEstadoCivil());
			involucradoDTO.setValorIdEstadoCivil(valorGenerico);

			// TODO: Falta a de alias hasta nacionalidad
			
			involucradoDTO.setNombresDemograficoDTO(lstDatosGenerales);
			involucradoDTO.setEsVivo(forma.getEsVivo());
			DomicilioDTO domicilio = new DomicilioDTO();
			AsentamientoDTO asentamientoDTO = new AsentamientoDTO();

			EntidadFederativaDTO estado = new EntidadFederativaDTO();
			if (!forma.getEntidadFederativa().equals("")
					&& !forma.getEntidadFederativa().equals("-1")) {
				estado.setEntidadFederativaId(new Long(forma
						.getEntidadFederativa()));
			}

			if (!forma.getPais().equals("") && !forma.getPais().equals("-1")) {
				valorGenerico = new ValorDTO();
				valorGenerico.setValor(forma.getPais());
				estado.setValorIdPais(valorGenerico);
			}
			if (!forma.getDelegacionMunicipio().equals("")
					&& !forma.getDelegacionMunicipio().equals("-1")) {
				MunicipioDTO municipio = new MunicipioDTO(new Long(
						forma.getDelegacionMunicipio()), "", estado);
				asentamientoDTO.setMunicipioDTO(municipio);
				domicilio.setMunicipioDTO(municipio);
			}

			CiudadDTO ciudad = new CiudadDTO();
			if (!forma.getCiudad().equals("")
					&& !forma.getCiudad().equals("-1")) {
				ciudad.setCiudadId(new Long(forma.getCiudad()));
			}
			asentamientoDTO.setCiudadDTO(ciudad);

			if (!forma.getAsentamientoColonia().equals("")
					&& !forma.getAsentamientoColonia().equals("-1")) {
				asentamientoDTO.setAsentamientoId(new Long(forma
						.getAsentamientoColonia()));
			}
			domicilio.setAsentamientoDTO(asentamientoDTO);
			domicilio.setCalle(forma.getCalle());
			domicilio.setNumeroExterior(forma.getNumExterior());
			domicilio.setNumeroInterior(forma.getNumInterior());
			domicilio.setEntreCalle1(forma.getEntreCalle());
			domicilio.setEntreCalle2(forma.getYcalle());
			domicilio.setAlias(forma.getAliasDomicilio());
			domicilio.setEdificio(forma.getEdificio());
			domicilio.setReferencias(forma.getReferencias());

			Date fechaCaptura=new Date();
			SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy");
			String fech=formato.format(fechaCaptura);

			domicilio.setFechaCreacionElemento(DateUtils.obtener(fech));
			if (!forma.getTipoCalle().equals("")
					&& !forma.getTipoCalle().equals("-1")) {
				domicilio.setValorCalleId(new ValorDTO(new Long(forma
						.getTipoCalle())));// Tipo de calle
			}

			calidadDTO = new CalidadDTO();
			calidadDTO.setCalidades(Calidades.DOMICILIO);
			domicilio.setCalidadDTO(calidadDTO);
			domicilio.setExpedienteDTO(super.getExpedienteTrabajo(request,numeroExpediente));
			involucradoDTO.setDomicilio(domicilio);

			if (logger.isDebugEnabled()) {
				logger.debug("::::::::::::::CIUDADANO:::::::::Ingresar:::::::::::"+ involucradoDTO);
			}
			// TODO:Setear los valores de servidor publico en el dto que es
			// pasado para guardar los datos del individuo
			Long resp = involucradoDelegate.guardarInvolucrado(involucradoDTO);
			logger.info("el valor de la respuesta es:" + resp);

			String xml = null;
			PrintWriter pw = null;
			converter.alias("IngresarIndividuoForm",IngresarIndividuoForm.class);
			IngresarIndividuoForm retorno = new IngresarIndividuoForm();
			xml = converter.toXML(retorno);
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			logger.info(e.getCause(), e);
		}
		return null;
	}

	
	/**
	 * Metodo utilizado para realizar la carga del combo Calidad
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward obtenerListaIndividuos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			logger.info("ejecutando Action obtenerListaIndividuos");
			IngresarIndividuoForm forma = (IngresarIndividuoForm) form;
			List<InvolucradoViewDTO> lstInvolucrados = new ArrayList<InvolucradoViewDTO>();
			ExpedienteDTO expedienteDTO = expedienteDelegate
					.obtenerExpediente(super.getExpedienteTrabajo(request,"OBTENER_EL_NUMERO_DEL_FRONT"));
			if (forma.getCalidadDelIndividuo().equals(
					CALIDAD_PROBABLE_RESPONSABLE)) {
				logger.info("Es probable responsable");

				List<InvolucradoDTO> lstFisicos = expedienteDTO
						.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA);
				for (InvolucradoDTO involucradoDTO : lstFisicos) {
					InvolucradoViewDTO involucradoView = new InvolucradoViewDTO();
					List<NombreDemograficoDTO> lstNombresDemografico = involucradoDTO
							.getNombresDemograficoDTO();
					for (NombreDemograficoDTO nombreDemograficoDTO : lstNombresDemografico) {
						involucradoView.setNombre(nombreDemograficoDTO
								.getNombre());
						involucradoView.setApellidoPaterno(nombreDemograficoDTO
								.getApellidoPaterno());
						involucradoView.setApellidoMaterno(nombreDemograficoDTO
								.getApellidoMaterno());
					}
					involucradoView.setInvolucradoId(involucradoDTO
							.getElementoId());
					lstInvolucrados.add(involucradoView);
				}
				List<InvolucradoDTO> lstMorales = expedienteDTO
						.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
				for (InvolucradoDTO involucradoDTO : lstMorales) {
					InvolucradoViewDTO involucradoView = new InvolucradoViewDTO();
					List<NombreDemograficoDTO> lstNombresDemografico = involucradoDTO
							.getNombresDemograficoDTO();
					for (NombreDemograficoDTO nombreDemograficoDTO : lstNombresDemografico) {
						involucradoView.setNombre(nombreDemograficoDTO
								.getNombre());
						involucradoView.setApellidoPaterno(nombreDemograficoDTO
								.getApellidoPaterno());
						involucradoView.setApellidoMaterno(nombreDemograficoDTO
								.getApellidoMaterno());
					}
					involucradoView.setInvolucradoId(involucradoDTO
							.getElementoId());
					lstInvolucrados.add(involucradoView);
				}
			} else if (forma.getCalidadDelIndividuo().equals(
					CALIDAD_VICTIMA_PERSONA)) {
				logger.info("Es victima");

				List<InvolucradoDTO> lstVictimas = expedienteDTO
						.getInvolucradoByCalidad(Calidades.VICTIMA_PERSONA);
				for (InvolucradoDTO involucradoDTO : lstVictimas) {
					InvolucradoViewDTO involucradoView = new InvolucradoViewDTO();
					List<NombreDemograficoDTO> lstNombresDemografico = involucradoDTO
							.getNombresDemograficoDTO();
					for (NombreDemograficoDTO nombreDemograficoDTO : lstNombresDemografico) {
						involucradoView.setNombre(nombreDemograficoDTO
								.getNombre());
						involucradoView.setApellidoPaterno(nombreDemograficoDTO
								.getApellidoPaterno());
						involucradoView.setApellidoMaterno(nombreDemograficoDTO
								.getApellidoMaterno());
					}
					involucradoDTO.setEsVivo(forma.getEsVivo());
					logger.info("¡ATENCION! Es Vivo: " + forma.getEsVivo());
					involucradoView.setInvolucradoId(involucradoDTO
							.getElementoId());
					lstInvolucrados.add(involucradoView);
				}
			} else if (forma.getCalidadDelIndividuo().equals(
					CALIDAD_DENUNCIANTE)) {
				logger.info("Es denunciante");

				List<InvolucradoDTO> lstVictimas = expedienteDTO
						.getInvolucradoByCalidad(Calidades.DENUNCIANTE);
				for (InvolucradoDTO involucradoDTO : lstVictimas) {
					InvolucradoViewDTO involucradoView = new InvolucradoViewDTO();
					List<NombreDemograficoDTO> lstNombresDemografico = involucradoDTO
							.getNombresDemograficoDTO();
					for (NombreDemograficoDTO nombreDemograficoDTO : lstNombresDemografico) {
						involucradoView.setNombre(nombreDemograficoDTO
								.getNombre());
						involucradoView.setApellidoPaterno(nombreDemograficoDTO
								.getApellidoPaterno());
						involucradoView.setApellidoMaterno(nombreDemograficoDTO
								.getApellidoMaterno());
					}
					involucradoView.setInvolucradoId(involucradoDTO
							.getElementoId());
					lstInvolucrados.add(involucradoView);
				}
			} else if (forma.getCalidadDelIndividuo().equals(CALIDAD_TESTIGO)) {
				logger.info("Es testigo");

				List<InvolucradoDTO> lstVictimas = expedienteDTO
						.getInvolucradoByCalidad(Calidades.TESTIGO);
				for (InvolucradoDTO involucradoDTO : lstVictimas) {
					InvolucradoViewDTO involucradoView = new InvolucradoViewDTO();
					List<NombreDemograficoDTO> lstNombresDemografico = involucradoDTO
							.getNombresDemograficoDTO();
					for (NombreDemograficoDTO nombreDemograficoDTO : lstNombresDemografico) {
						involucradoView.setNombre(nombreDemograficoDTO
								.getNombre());
						involucradoView.setApellidoPaterno(nombreDemograficoDTO
								.getApellidoPaterno());
						involucradoView.setApellidoMaterno(nombreDemograficoDTO
								.getApellidoMaterno());
					}
					involucradoView.setInvolucradoId(involucradoDTO
							.getElementoId());
					lstInvolucrados.add(involucradoView);
				}
			} else if (forma.getCalidadDelIndividuo().equals(CALIDAD_TRADUCTOR)) {
				logger.info("Es traductor");

				List<InvolucradoDTO> lstVictimas = expedienteDTO
						.getInvolucradoByCalidad(Calidades.TRADUCTOR);
				for (InvolucradoDTO involucradoDTO : lstVictimas) {
					InvolucradoViewDTO involucradoView = new InvolucradoViewDTO();
					List<NombreDemograficoDTO> lstNombresDemografico = involucradoDTO
							.getNombresDemograficoDTO();
					for (NombreDemograficoDTO nombreDemograficoDTO : lstNombresDemografico) {
						involucradoView.setNombre(nombreDemograficoDTO
								.getNombre());
						involucradoView.setApellidoPaterno(nombreDemograficoDTO
								.getApellidoPaterno());
						involucradoView.setApellidoMaterno(nombreDemograficoDTO
								.getApellidoMaterno());
					}
					involucradoView.setInvolucradoId(involucradoDTO
							.getElementoId());
					lstInvolucrados.add(involucradoView);
				}
			} else if (forma.getCalidadDelIndividuo().equals(
					CALIDAD_QUIEN_DETUVO)) {
				logger.info("********************++Es quien detuvo**************************************");

				List<InvolucradoDTO> lstVictimas = expedienteDTO
						.getInvolucradoByCalidad(Calidades.QUIEN_DETUVO);
				System.out.println("victimas quien detuvo:"
						+ lstVictimas.size());
				for (InvolucradoDTO involucradoDTO : lstVictimas) {
					InvolucradoViewDTO involucradoView = new InvolucradoViewDTO();
					List<NombreDemograficoDTO> lstNombresDemografico = involucradoDTO
							.getNombresDemograficoDTO();
					for (NombreDemograficoDTO nombreDemograficoDTO : lstNombresDemografico) {
						involucradoView.setNombre(nombreDemograficoDTO
								.getNombre());
						involucradoView.setApellidoPaterno(nombreDemograficoDTO
								.getApellidoPaterno());
						involucradoView.setApellidoMaterno(nombreDemograficoDTO
								.getApellidoMaterno());
					}
					involucradoView.setInvolucradoId(involucradoDTO
							.getElementoId());
					lstInvolucrados.add(involucradoView);
				}
			}
			converter.alias("lstInvolucrados", java.util.List.class);
			converter.alias("involucradoViewDTO", InvolucradoViewDTO.class);
			response.setContentType("text/xml");
			logger.info(converter.toXML(lstInvolucrados));
			// request.getSession().setAttribute("expediente", expedienteDTO);
			escribirRespuesta(response, converter.toXML(lstInvolucrados));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	public MediaFiliacionDTO obtenerMediaFiliacion(IngresarIndividuoForm forma){
		//Datos de Media Filiacion Cara
		MediaFiliacionDTO mediaFiliacionDTO = new MediaFiliacionDTO();
		ValorDTO valorGenericoMF = new ValorDTO();
		Long tamanoBoca = 0L;
		if(forma.getTamanoBoca() != null &&  ! forma.getTamanoBoca() .equals("-1")){
			tamanoBoca=Long.parseLong(forma.getTamanoBoca());
		}else{
			tamanoBoca=null;
		}
		valorGenericoMF.setIdCampo(tamanoBoca);
		mediaFiliacionDTO.setTamanioBoca(valorGenericoMF);
		
		valorGenericoMF = new ValorDTO();
		Long tipoCara = 0L;
		if(forma.getTipoCara() != null &&  ! forma.getTipoCara() .equals("-1")){
			tipoCara=Long.parseLong(forma.getTipoCara());
		}else{
			tipoCara=null;
		}
		valorGenericoMF.setIdCampo(tipoCara);
		mediaFiliacionDTO.setTipoCara(valorGenericoMF);
		
		valorGenericoMF = new ValorDTO();
		Long formaMenton = 0L;
		if(forma.getFormaMenton() != null &&  ! forma.getFormaMenton() .equals("-1")){
			formaMenton=Long.parseLong(forma.getFormaMenton());
		}else{
			formaMenton=null;
		}
		valorGenericoMF.setIdCampo(formaMenton);
		mediaFiliacionDTO.setFormaMenton(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long tipoMenton = 0L;
		if(forma.getTipoMenton() != null &&  ! forma.getTipoMenton() .equals("-1")){
			tipoMenton=Long.parseLong(forma.getTipoMenton());
		}else{
			tipoMenton=null;
		}
		valorGenericoMF.setIdCampo(tipoMenton);
		mediaFiliacionDTO.setTipoMenton(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long tez = 0L;
		if(forma.getTez() != null &&  ! forma.getTez() .equals("-1")){
			tez=Long.parseLong(forma.getTez());
		}else{
			tez=null;
		}
		valorGenericoMF.setIdCampo(tez);
		mediaFiliacionDTO.setTez(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long inclinacionMenton = 0L;
		if(forma.getInclinacionMenton() != null &&  ! forma.getInclinacionMenton() .equals("-1")){
			inclinacionMenton=Long.parseLong(forma.getInclinacionMenton());
		}else{
			inclinacionMenton=null;
		}
		valorGenericoMF.setIdCampo(inclinacionMenton);
		mediaFiliacionDTO.setInclinacionMenton(valorGenericoMF);

		//Datos de Media Filiacion Cabello
		valorGenericoMF = new ValorDTO();
		Long colorCabello = 0L;
		if(forma.getColorCabello() != null &&  ! forma.getColorCabello() .equals("-1")){
			colorCabello=Long.parseLong(forma.getColorCabello());
		}else{
			colorCabello=null;
		}
		valorGenericoMF.setIdCampo(colorCabello);
		mediaFiliacionDTO.setColorCabello(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long formaCabello = 0L;
		if(forma.getFormaCabello() != null &&  ! forma.getFormaCabello() .equals("-1")){
			formaCabello=Long.parseLong(forma.getFormaCabello());
		}else{
			formaCabello=null;
		}
		valorGenericoMF.setIdCampo(formaCabello);
		mediaFiliacionDTO.setFormaCabello(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long calvieTipo = 0L;
		if(forma.getCalvieTipo() != null &&  ! forma.getCalvieTipo() .equals("-1")){
			calvieTipo=Long.parseLong(forma.getCalvieTipo());
		}else{
			calvieTipo=null;
		}
		valorGenericoMF.setIdCampo(calvieTipo);
		mediaFiliacionDTO.setCalvicieTipo(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long cabelloImplantacion = 0L;
		if(forma.getCabelloImplantacion() != null &&  ! forma.getCabelloImplantacion() .equals("-1")){
			cabelloImplantacion=Long.parseLong(forma.getCabelloImplantacion());
		}else{
			cabelloImplantacion=null;
		}
		valorGenericoMF.setIdCampo(cabelloImplantacion);
		mediaFiliacionDTO.setCabelloImplantacion(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long cantidadCabello = 0L;
		if(forma.getCantidadCabello() != null &&  ! forma.getCantidadCabello() .equals("-1")){
			cantidadCabello=Long.parseLong(forma.getCantidadCabello());
		}else{
			cantidadCabello=null;
		}
		valorGenericoMF.setIdCampo(cantidadCabello);
		mediaFiliacionDTO.setCabelloCantidad(valorGenericoMF);

		//Datos de Media Filiacion Oreja
		valorGenericoMF = new ValorDTO();
		Long tamanoOreja = 0L;
		if(forma.getTamanoOreja() != null &&  ! forma.getTamanoOreja() .equals("-1")){
			tamanoOreja=Long.parseLong(forma.getTamanoOreja());
		}else{
			tamanoOreja=null;
		}
		valorGenericoMF.setIdCampo(tamanoOreja);
		mediaFiliacionDTO.setOrejaTamanio(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long lobuloParticularidad = 0L;
		if(forma.getLobuloParticularidad() != null &&  ! forma.getLobuloParticularidad() .equals("-1")){
			lobuloParticularidad=Long.parseLong(forma.getLobuloParticularidad());
		}else{
			lobuloParticularidad=null;
		}
		valorGenericoMF.setIdCampo(lobuloParticularidad);
		mediaFiliacionDTO.setOrejaLobParticularidad(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long lobuloDimension = 0L;
		if(forma.getLobuloDimension() != null &&  ! forma.getLobuloDimension() .equals("-1")){
			lobuloDimension=Long.parseLong(forma.getLobuloDimension());
		}else{
			lobuloDimension=null;
		}
		valorGenericoMF.setIdCampo(lobuloDimension);
		mediaFiliacionDTO.setOrejaLobDimension(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long lobuloAdherencia = 0L;
		if(forma.getLobuloAdherencia() != null &&  ! forma.getLobuloAdherencia() .equals("-1")){
			lobuloAdherencia=Long.parseLong(forma.getLobuloAdherencia());
		}else{
			lobuloAdherencia=null;
		}
		valorGenericoMF.setIdCampo(lobuloAdherencia);
		mediaFiliacionDTO.setOrejaLobAdherencia(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long helixAnterior = 0L;
		if(forma.getHelixAnterior() != null &&  ! forma.getHelixAnterior() .equals("-1")){
			helixAnterior=Long.parseLong(forma.getHelixAnterior());
		}else{
			helixAnterior=null;
		}
		valorGenericoMF.setIdCampo(helixAnterior);
		mediaFiliacionDTO.setHelixSuperior(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long helixPosterior = 0L;
		if(forma.getHelixPosterior() != null &&  ! forma.getHelixPosterior() .equals("-1")){
			helixPosterior=Long.parseLong(forma.getHelixPosterior());
		}else{
			helixPosterior=null;
		}
		valorGenericoMF.setIdCampo(helixPosterior);
		mediaFiliacionDTO.setHelixPosterior(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long helixContorno = 0L;
		if(forma.getHelixContorno() != null &&  ! forma.getHelixContorno() .equals("-1")){
			helixContorno=Long.parseLong(forma.getHelixContorno());
		}else{
			helixContorno=null;
		}
		valorGenericoMF.setIdCampo(helixContorno);
		mediaFiliacionDTO.setHelixContorno(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long helixAdherencia = 0L;
		if(forma.getHelixAdherencia() != null &&  ! forma.getHelixAdherencia() .equals("-1")){
			helixAdherencia=Long.parseLong(forma.getHelixAdherencia());
		}else{
			helixAdherencia=null;
		}
		valorGenericoMF.setIdCampo(helixAdherencia);
		mediaFiliacionDTO.setHelixAdherencia(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long formaOreja = 0L;
		if(forma.getFormaOreja() != null &&  ! forma.getFormaOreja() .equals("-1")){
			formaOreja=Long.parseLong(forma.getFormaOreja());
		}else{
			formaOreja=null;
		}
		valorGenericoMF.setIdCampo(formaOreja);
		mediaFiliacionDTO.setFormaOreja(valorGenericoMF);

		//Datos de Media Filiacion Ojos
		valorGenericoMF = new ValorDTO();
		Long formaOjos = 0L;
		if(forma.getFormaOjos() != null &&  ! forma.getFormaOjos() .equals("-1")){
			formaOjos=Long.parseLong(forma.getFormaOjos());
		}else{
			formaOjos=null;
		}
		valorGenericoMF.setIdCampo(formaOjos);
		mediaFiliacionDTO.setFormaOjos(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long colorOjos = 0L;
		if(forma.getColorOjos() != null &&  ! forma.getColorOjos() .equals("-1")){
			colorOjos=Long.parseLong(forma.getColorOjos());
		}else{
			colorOjos=null;
		}
		valorGenericoMF.setIdCampo(colorOjos);
		mediaFiliacionDTO.setColorOjos(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long tamanoOjos = 0L;
		if(forma.getTamanoOjos() != null &&  ! forma.getTamanoOjos() .equals("-1")){
			tamanoOjos=Long.parseLong(forma.getTamanoOjos());
		}else{
			tamanoOjos=null;
		}
		valorGenericoMF.setIdCampo(tamanoOjos);
		mediaFiliacionDTO.setTamanioOjo(valorGenericoMF);
		
		
		//cejas
		valorGenericoMF = new ValorDTO();
		Long implantacionCejas = 0L;
		if(forma.getImplantacionCejas() != null &&  ! forma.getImplantacionCejas() .equals("-1")){
			implantacionCejas=Long.parseLong(forma.getImplantacionCejas());
		}else{
			implantacionCejas=null;
		}
		valorGenericoMF.setIdCampo(implantacionCejas);
		mediaFiliacionDTO.setImplantacionCeja(valorGenericoMF);
		
		valorGenericoMF = new ValorDTO();
		Long formaCejas = 0L;
		if(forma.getFormaCejas() != null &&  ! forma.getFormaCejas() .equals("-1")){
			formaCejas=Long.parseLong(forma.getFormaCejas());
		}else{
			formaCejas=null;
		}
		valorGenericoMF.setIdCampo(formaCejas);
		mediaFiliacionDTO.setFormaCeja(valorGenericoMF);
		
		valorGenericoMF = new ValorDTO();
		Long tamanoCejas = 0L;
		if(forma.getTamanoCejas() != null &&  ! forma.getTamanoCejas() .equals("-1")){
			tamanoCejas=Long.parseLong(forma.getTamanoCejas());
		}else{
			tamanoCejas=null;
		}
		valorGenericoMF.setIdCampo(tamanoCejas);
		mediaFiliacionDTO.setTamanioCeja(valorGenericoMF);
		
		SeniaParticularDTO seniaParticularDTO=new SeniaParticularDTO();
		if(forma.getCicatricesSenas().equals("1")){
			seniaParticularDTO.setTieneCicatrices(true);
		}else{
			seniaParticularDTO.setTieneCicatrices(false);
		}
		seniaParticularDTO.setDescripcionCicatrices(forma.getCicatricesSenasText());
		
		if(forma.getProtesisSenas().equals("1")){
			seniaParticularDTO.setTieneProtesis(true);
		}else{
			seniaParticularDTO.setTieneProtesis(false);
		}
		seniaParticularDTO.setDescripcionProtesis(forma.getProtesisSenasText());
		
		if(forma.getTatuajeSenas().equals("1")){
			seniaParticularDTO.setTieneTatuajes(true);
		}else{
			seniaParticularDTO.setTieneTatuajes(false);
		}
		seniaParticularDTO.setDescripcionTatuajes(forma.getTatuajeSenasText());
		seniaParticularDTO.setDescripcionOtraSenia(forma.getOtraSenasText());
		logger.info("Lunares:::::::::::::::::"+forma.getLunaresSenas());
		if(forma.getLunaresSenas().equals("1")){
			seniaParticularDTO.setTieneLunares(true);
		}else{
			seniaParticularDTO.setTieneLunares(false);
		}
		seniaParticularDTO.setDescripcionLunares(forma.getLunaresSenasText());
		if(forma.getDefectosSenas().equals("1")){
			seniaParticularDTO.setTieneDefectosFisicos(true);
		}else{
			seniaParticularDTO.setTieneDefectosFisicos(false);
		}
		seniaParticularDTO.setDescripcionDefectosFisicos(forma.getDefectosSenasText());
		mediaFiliacionDTO.setSeniaParticularDTO(seniaParticularDTO);
		if(forma.getLentesOtros().equals("1")){
			mediaFiliacionDTO.setUsaLentes(true);
		}else{
			mediaFiliacionDTO.setUsaLentes(false);
		}
		if(forma.getBarbaOtros().equals("1")){
			mediaFiliacionDTO.setTieneBarba(true);
		}else{
			mediaFiliacionDTO.setTieneBarba(false);
		}
		if(forma.getBigoteOtros().equals("1")){
			mediaFiliacionDTO.setTieneBigote(true);
		}else{
			mediaFiliacionDTO.setTieneBigote(false);
		}
		if(forma.getEstauraOtros()!=null && forma.getEstauraOtros()!=""){
			mediaFiliacionDTO.setEstatura(Double.parseDouble(forma.getEstauraOtros()));
		}
		if(forma.getPesoOtros()!=null && forma.getPesoOtros()!=""){
			mediaFiliacionDTO.setPeso(Double.parseDouble(forma.getPesoOtros()));
		}
		valorGenericoMF = new ValorDTO();
		Long tipoSangre = 0L;
		if(forma.getTipoSangreOtros() != null &&  ! forma.getTipoSangreOtros() .equals("-1")){
			tipoSangre=Long.parseLong(forma.getTipoSangreOtros());
		}else{
			tipoSangre=null;
		}
		valorGenericoMF.setIdCampo(tipoSangre);
		mediaFiliacionDTO.setTipoSangre(valorGenericoMF);
		if(forma.getFactorrhOtros().equals("1")){
			mediaFiliacionDTO.setFactorRH("Positivo");
		}else if(forma.getFactorrhOtros().equals("0")){
			mediaFiliacionDTO.setFactorRH("Negativo");
		}
		
		valorGenericoMF = new ValorDTO();
		Long complexion = 0L;
		if(forma.getComplexion() != null &&  ! forma.getComplexion() .equals("-1")){
			complexion=Long.parseLong(forma.getComplexion());
		}else{
			complexion=null;
		}
		valorGenericoMF.setIdCampo(complexion);
		mediaFiliacionDTO.setComplexion(valorGenericoMF);
		logger.info(valorGenericoMF+":::compexion art");
		
		 
		valorGenericoMF = new ValorDTO();
		Long direccionCeja = 0L;
		if(forma.getDireccionCeja() != null &&  ! forma.getDireccionCeja() .equals("-1")){
			direccionCeja=Long.parseLong(forma.getDireccionCeja());
		}else{
			direccionCeja=null;
		}
		valorGenericoMF.setIdCampo(direccionCeja);
		mediaFiliacionDTO.setDireccionCeja(valorGenericoMF);
		logger.info(valorGenericoMF+":::direccionCeja art");
		
		  
		valorGenericoMF = new ValorDTO();
		Long helixOriginal = 0L;
		if(forma.getHelixOriginal() != null &&  ! forma.getHelixOriginal() .equals("-1")){
			helixOriginal=Long.parseLong(forma.getHelixOriginal());
		}else{
			helixOriginal=null;
		}
		valorGenericoMF.setIdCampo(helixOriginal);
		mediaFiliacionDTO.setHelixOriginal(valorGenericoMF);
		logger.info(valorGenericoMF+":::helixOriginal art");
		
		valorGenericoMF = new ValorDTO();
		Long orejaLobContorno = 0L;
		if(forma.getOrejaLobContorno() != null &&  ! forma.getOrejaLobContorno() .equals("-1")){
			orejaLobContorno=Long.parseLong(forma.getOrejaLobContorno());
		}else{
			orejaLobContorno=null;
		}
		valorGenericoMF.setIdCampo(orejaLobContorno);
		mediaFiliacionDTO.setOrejaLobContorno(valorGenericoMF);
		logger.info(valorGenericoMF+":::orejaLobContorno art");
		
		
		
		
		
                // Jacob
                mapeaFormaEnDto(forma, "comisuras", mediaFiliacionDTO, "labioComisuras");
                mapeaFormaEnDto(forma, "alturaNasoLabial", mediaFiliacionDTO, "alturaNasoLabial");
                mapeaFormaEnDto(forma, "espesorLabioInferior", mediaFiliacionDTO, "espesorLabioInf");
                mapeaFormaEnDto(forma, "espesorLabioSuperior", mediaFiliacionDTO, "espesorLabioSup");
                mapeaFormaEnDto(forma, "prominencia", mediaFiliacionDTO, "labiosProminencia");
                mapeaFormaEnDto(forma, "anchoNariz", mediaFiliacionDTO, "anchoNariz");
                mapeaFormaEnDto(forma, "alturaNariz", mediaFiliacionDTO, "alturaNariz");
                mapeaFormaEnDto(forma, "baseNariz", mediaFiliacionDTO, "baseNariz");
                mapeaFormaEnDto(forma, "raizNariz", mediaFiliacionDTO, "raizNariz");
                mapeaFormaEnDto(forma, "frenteAltura", mediaFiliacionDTO, "frenteAltura");
                mapeaFormaEnDto(forma, "frenteAncho", mediaFiliacionDTO, "frenteAncho");
                mapeaFormaEnDto(forma, "inclinacionFrente", mediaFiliacionDTO, "frenteInclinacion");
		return mediaFiliacionDTO;
	}

    /**
     * Actualiza el campo {@code campoDelDto} del {@code dtoActualizable}
     * con la informacion del campo {@code campoDeLaForma} tomado del Form
     * {@code formaActualizadora}. Actualmente solo actualiza los campos
     * del tipo ValorDTO, Long y String del dto. <p/>
     * Si el tipo del dto es ValorDTO, se crea un nuevo idCampo de tipo Long
     * a partir del campo indicado de la forma; si el tipo de regreso de
     * la forma es un String, se valida que no sea null y distinto de "-1"
     * y se trata de parsear a un Long, si no es parseable se deja como null;
     * si el tipo de regreso de la forma es un Long este es asignado al
     * idCampo del ValorDTO.<p/>
     * Si el tipo del dto es un Long se obtiene uno a partir del campo
     * de la forma de la misma manera que se obtiene el idCampo de para un
     * ValorDTO. <p/>
     * Si el tipo del dto es un String, se obtiene el campo de la forma y
     * si es distinto de null, se invoca a su metodo toString() y este valor
     * se le asigna al dto.
     * @param formaActualizadora La forma de donde se toma el campo para
     * actualizar al DTO.
     * @param campoDeLaForma El campo de donde se tomara el valor para
     * actualizar el dto.
     * @param dtoActualizable El DTO que sera actualizado.
     * @param campoDelDto El campo del DTO que se actualizara.
     */
    public static void mapeaFormaEnDto(ActionForm formaActualizadora,
            String campoDeLaForma, GenericDTO dtoActualizable, String campoDelDto) {
        boolean mapeado = false;
        Method[] metodos = dtoActualizable.getClass().getMethods();
        for (Method metodo : metodos) {
            metodo.setAccessible(true);
            try {
                String nombreMetodoDto = metodo.getName();
                if (nombreMetodoDto.equals("set" + nombreDelCampoToPrimerUpper(campoDelDto))) {
                    Class<?>[] tipos = metodo.getParameterTypes();
                    if (logger.isDebugEnabled()) {
                        logger.debug("tipos.length = " + tipos.length);
                    }
                    if (tipos.length == 1) {
                        Class tipoParametro = tipos[0];
                        if (logger.isDebugEnabled()) {
                            logger.debug("tipoParametro = " + tipoParametro);
                        }
                        // si el set de dto requiere un ValorDTO, lo creamos
                        if (tipoParametro.equals(ValorDTO.class)) {
                            ValorDTO valorDTO = new ValorDTO();
                            Long idCampo = obtenLongDeForma(formaActualizadora, campoDeLaForma);
                            valorDTO.setIdCampo(idCampo);
                            metodo.invoke(dtoActualizable, valorDTO);
                            mapeado = true;
                        }
                        if (tipoParametro.equals(Long.class)) {
                            Long l = obtenLongDeForma(formaActualizadora, campoDeLaForma);
                            metodo.invoke(dtoActualizable, l);
                            mapeado = true;
                        }
                        if (tipoParametro.equals(String.class)) {
                            String parametro = obtenStringDeForma(formaActualizadora, campoDeLaForma);
                            metodo.invoke(dtoActualizable, parametro);
                            mapeado = true;
                        }
                    }
                }
            } catch (IllegalAccessException ex) {
                logger.debug(ex);
            } catch (IllegalArgumentException ex) {
                logger.debug(ex);
            } catch (InvocationTargetException ex) {
                logger.debug(ex);
            }
        }
        if (!mapeado) {
            /**
             * Si se llega a este punto es porque el nombre de un atributo de
             * un ActionForm que se mapeaba con un DTO no corresponde a lo
             * esperado. Se debe verificar cual es el nombre de los campos
             * mapeados y el tipo que espera el setter del dto ya que este metodo
             * solo funciona con los tipos documentados.
             */
            throw new IllegalArgumentException("No fue posible mapear el atributo :"
                    + campoDeLaForma
                    + " de la forma :" + formaActualizadora + " en el campo: "
                    + campoDelDto + " del dto: " + dtoActualizable);
        }
    }

    private static String nombreDelCampoToPrimerUpper(String campoDelDto) {
        return "" + campoDelDto.substring(0, 1).toUpperCase() + campoDelDto.substring(1);
    }

    private static Long obtenLongDeForma(ActionForm formaActualizadora, String campoDeLaForma) {
        Method[] metodos = formaActualizadora.getClass().getMethods();
        Long idCampo = null;
        try {
            for (Method metodo : metodos) {
                metodo.setAccessible(true);
                if (metodo.getName().equals("get" + nombreDelCampoToPrimerUpper(campoDeLaForma))) {
                    Class<?> tipoDeRegreso = metodo.getReturnType();
                    // Si el tipo de regreso de la forma es un String.
                    if (tipoDeRegreso != null) {
                        Object valorDeRegreso = metodo.invoke(formaActualizadora);
                        if (tipoDeRegreso.equals(String.class)) {
                            // ... tratamos de parsear el string a un long en caso que el string sea
                            // distinto de -1
                            if (valorDeRegreso != null && !valorDeRegreso.equals("-1")) {
                                try {
                                    idCampo = Long.parseLong((String) valorDeRegreso);
                                } catch (NumberFormatException nfe) {
                                    logger.debug(nfe);
                                }
                            }
                        }
                        if (tipoDeRegreso.equals(Long.class)) {
                            idCampo = (Long) valorDeRegreso;
                        }
                    }
                }
            }
        } catch (IllegalAccessException ex) {
            logger.debug(ex);
        } catch (IllegalArgumentException ex) {
            logger.debug(ex);
        } catch (InvocationTargetException ex) {
            logger.debug(ex);
        }
        return idCampo;
    }
    
    private static String obtenStringDeForma(ActionForm formaActualizadora, String campoDeLaForma) {
        Method[] metodos = formaActualizadora.getClass().getMethods();
        String string = null;
        try {
            for (Method metodo : metodos) {
                metodo.setAccessible(true);
                if (metodo.getName().equals("get" + nombreDelCampoToPrimerUpper(campoDeLaForma))) {
                    Class<?> tipoDeRegreso = metodo.getReturnType();
                    // Si el tipo de regreso de la forma es un String.
                    if (tipoDeRegreso != null) {
                        Object valorDeRegreso = metodo.invoke(formaActualizadora);
                        if (valorDeRegreso != null) {
                            string = valorDeRegreso.toString();
                        }
                    }
                }
            }
        } catch (IllegalAccessException ex) {
            logger.debug(ex);
        } catch (IllegalArgumentException ex) {
            logger.debug(ex);
        } catch (InvocationTargetException ex) {
            logger.debug(ex);
        }
        return string;
    }
    
	/**
	 * Metodo utilizado para guardar un Acta Circunstancial
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward registrarActaCircunstanciada(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			logger.info("ejecutando Action guardar Acta Circunstancial - registrarActaCircunstanciada");
			// hacemos el cast de la forma de Contacto Organizacion
			IngresarActaCircunstanciadaForm formaContOrg = (IngresarActaCircunstanciadaForm) form;

			logger.info("FORMA ACTA CIRCUNSTANCIAL:::::::::::::::::::::::");
			
			//revisamos q los datos generales no vengan nulos
			if (StringUtils.isBlank(formaContOrg.getNombre())) {
				formaContOrg.setNombre("");
			}
			if (StringUtils.isBlank(formaContOrg.getApellidoPaterno())) {
				formaContOrg.setApellidoPaterno("");
			}
			if (StringUtils.isBlank(formaContOrg.getApellidoMaterno())) {
				formaContOrg.setApellidoMaterno("");
			}
			if (StringUtils.isBlank(formaContOrg.getCurp())) {
				formaContOrg.setCurp("");
			}
			if (StringUtils.isBlank(formaContOrg.getRfc())) {
				formaContOrg.setRfc("");
			}

			if (StringUtils.isBlank(formaContOrg.getFechaIngreso())) {
				formaContOrg.setFechaIngreso("");
			}
			if (StringUtils.isBlank(formaContOrg.getIdioma())) {
				formaContOrg.setIdioma("");
			}
			if (StringUtils.isBlank(formaContOrg.getEscolaridad())) {
				formaContOrg.setEscolaridad("");
			}
			if (StringUtils.isBlank(formaContOrg.getEstadoCivil())) {
				formaContOrg.setEstadoCivil("");
			}
			if (StringUtils.isBlank(formaContOrg.getSexo())) {
				formaContOrg.setSexo("");
			}
			if (StringUtils.isBlank(formaContOrg.getFechaNacimiento())) {
				formaContOrg.setFechaNacimiento("");
			}
			if (formaContOrg.getEdadAproximada()==null) {
				formaContOrg.setEdadAproximada(Short.parseShort("0"));
			}
			if (StringUtils.isBlank(formaContOrg.getAlias())) {
				formaContOrg.setAlias("");
			}
			if (StringUtils.isBlank(formaContOrg.getOcupacion())) {
				formaContOrg.setOcupacion("");
			}
			if (StringUtils.isBlank(formaContOrg.getNacionalidad())) {
				formaContOrg.setNacionalidad("");
			}
			if (StringUtils.isBlank(formaContOrg.getPaisNacimiento())) {
				formaContOrg.setPaisNacimiento("");
			}
			if (StringUtils.isBlank(formaContOrg.getEntFederativaNacimiento())) {
				formaContOrg.setEntFederativaNacimiento("");
			}
			if (StringUtils.isBlank(formaContOrg.getMunicipioNacimiento())) {
				formaContOrg.setMunicipioNacimiento("");
			}
			logger.info("ACTA CIRCUNSTANCIAL:::: Revision datos generales....");
			//FIN revisamos q los datos generales no vengan nulos

			// revisamos que los datos de domicilio no sea nula

			if (StringUtils.isBlank(formaContOrg.getCodigoPostal())) {
				formaContOrg.setCodigoPostal("");
			}


			if (StringUtils.isBlank(formaContOrg.getCalle())) {
				formaContOrg.setCalle("");
			}

			if (StringUtils.isBlank(formaContOrg.getNumExterior())) {
				formaContOrg.setNumExterior("");
			}

			if (StringUtils.isBlank(formaContOrg.getNumInterior())) {
				formaContOrg.setNumInterior("");
			}

			if (StringUtils.isBlank(formaContOrg.getReferencias())) {
				formaContOrg.setReferencias("");
			}

			if (StringUtils.isBlank(formaContOrg.getEntreCalle())) {
				formaContOrg.setEntreCalle("");
			}

			if (StringUtils.isBlank(formaContOrg.getYcalle())) {
				formaContOrg.setYcalle("");
			}

			if (StringUtils.isBlank(formaContOrg.getAliasDomicilio())) {
				formaContOrg.setAliasDomicilio("");
			}

			if (StringUtils.isBlank(formaContOrg.getEdificio())) {
				formaContOrg.setEdificio("");
			}

			if (StringUtils.isBlank(formaContOrg.getLongitud())) {
				formaContOrg.setLongitud("");
			}

			if (StringUtils.isBlank(formaContOrg.getLatitud())) {
				formaContOrg.setLatitud("");
			}
			logger.info("ACTA CIRCUNSTANCIAL:::: domicilio....");
			// FIN revisamos que los datos de domicilio no sea nula
			
			// Declaramos la instancia a guardar en BD
			
			//revisamos que los datos del documento de identificacion no vengan vacios
			if (StringUtils.isBlank(formaContOrg.getDocIdentificacion())) {
				//formaContOrg.setDocIdentificacion("");
				formaContOrg.setDocIdentificacion(null);
			}
			if (StringUtils.isBlank(formaContOrg.getFolioDoc())) {
				//formaContOrg.setFolioDoc("");
				formaContOrg.setFolioDoc(null);
			}
			
			InvolucradoDTO contactoOrgDTO=new InvolucradoDTO();
			
			//seteo el expediente
			logger.info("getExpedienteTrabajo_Ing_ActaCirc :: ["+formaContOrg.getNumExpediente().trim()+"]");
			ExpedienteDTO expedienteDTO = super.getExpedienteTrabajo(request,formaContOrg.getNumExpediente().trim());
			logger.info("expediente obtenido de sesion Ing_ActaCirc :: "+expedienteDTO);
			contactoOrgDTO.setExpedienteDTO(expedienteDTO);
			contactoOrgDTO.setFechaCreacionElemento(new Date());
			logger.info("ACTA CIRCUNSTANCIAL:::: Seteo expediente....");
			//seteo el usuario 				
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setIdUsuario(Long.parseLong(formaContOrg.getIdUsuario()));
			contactoOrgDTO.setUsuario(usuarioDTO);
			logger.info("ACTA CIRCUNSTANCIAL:::: Seteo usuario....");
					
			// Seteamos el domicilio del contacto
			CalidadDTO calidadDTO = new CalidadDTO();
			calidadDTO.setCalidades(Calidades.DOMICILIO);
			
			// Encapsulamos la informacion del domicilio
			if (Long.parseLong(formaContOrg.getPais()) == 10)// Mexico
			{
				DomicilioDTO domicilioDTO = new DomicilioDTO();

				domicilioDTO.setLatitud(formaContOrg.getLatitud());
				domicilioDTO
						.setLongitud(formaContOrg.getLongitud());
				domicilioDTO.setEdificio(formaContOrg.getEdificio());
				domicilioDTO.setAlias(formaContOrg.getAliasDomicilio());
				domicilioDTO.setEntreCalle2(formaContOrg.getYcalle());
				domicilioDTO.setEntreCalle1(formaContOrg.getEntreCalle());
				domicilioDTO.setReferencias(formaContOrg.getReferencias());
				domicilioDTO.setNumeroInterior(formaContOrg.getNumInterior());
				domicilioDTO.setNumeroExterior(formaContOrg.getNumExterior());
				domicilioDTO.setCalle(formaContOrg.getCalle());
				if (StringUtils.isNotBlank(formaContOrg.getTipoCalle() )&& !formaContOrg.getTipoCalle().equals("-1")) {
					domicilioDTO.setValorCalleId(new ValorDTO(Long
							.parseLong(formaContOrg.getTipoCalle())));
				}
				domicilioDTO.setCalidadDTO(calidadDTO);
				domicilioDTO.setExpedienteDTO(expedienteDTO);
				domicilioDTO.setFechaCreacionElemento(new Date());
				// delcaramos el nuevo asentamiento
				AsentamientoDTO asentamientoDTO = new AsentamientoDTO();
				if (StringUtils.isNotBlank(formaContOrg.getAsentamientoColonia()) && !formaContOrg.getAsentamientoColonia().equals("-1")) {
					asentamientoDTO.setAsentamientoId(Long.parseLong(formaContOrg
							.getAsentamientoColonia()));
				}
				asentamientoDTO.setCodigoPostal(formaContOrg.getCodigoPostal());

				// Declaramos el tipo de asentamiento
				if (StringUtils.isNotBlank(formaContOrg.getTipoAsentamiento()) && !formaContOrg.getTipoAsentamiento().equals("-1")) {
					TipoAsentamientoDTO tipoAsentamientoDTO = new TipoAsentamientoDTO(
							Long.parseLong(formaContOrg.getTipoAsentamiento()), "");
					asentamientoDTO.setTipoAsentamientoDTO(tipoAsentamientoDTO);
				}
				// Declaramos el municipio
				MunicipioDTO municipioDTO = new MunicipioDTO();
				if (StringUtils.isNotBlank(formaContOrg.getDelegacionMunicipio()) && !formaContOrg.getDelegacionMunicipio().equals("-1")) {
					municipioDTO.setMunicipioId(Long.parseLong(formaContOrg
							.getDelegacionMunicipio()));
				}
				
				if (!(formaContOrg.getLatitudN()== null) && !formaContOrg.getLatitudN().equals("")) {
					String lat= formaContOrg.getLatitudN()+formaContOrg.getLatitudGrados()+"°"+formaContOrg.getLatitudMinutos()+"'"+formaContOrg.getLatitudSegundos()+"\"";
					domicilioDTO.setLatitud(lat);
				}
				if (!(formaContOrg.getLongitudE()== null) && !formaContOrg.getLongitudE().equals("")) {
					String longitud= formaContOrg.getLongitudE()+formaContOrg.getLongitudGrados()+"°"+formaContOrg.getLongitudMinutos()+"'"+formaContOrg.getLongitudSegundos()+"\"";
					domicilioDTO.setLongitud(longitud);
				}
				
				asentamientoDTO.setMunicipioDTO(municipioDTO);

				// declaramos la Ciudad
				CiudadDTO ciudadDTO = new CiudadDTO();
				if (StringUtils.isNotBlank(formaContOrg.getCiudad()) && !formaContOrg.getCiudad().equals("-1")) {
					ciudadDTO.setCiudadId(Long.parseLong(formaContOrg.getCiudad()));
				}
				// declaramos la entidad federativa
				EntidadFederativaDTO entidadFederativaDTO = new EntidadFederativaDTO();
				if (StringUtils.isNotBlank(formaContOrg.getEntidadFederativa()) && !formaContOrg.getEntidadFederativa().equals("-1")) {
					entidadFederativaDTO.setEntidadFederativaId(Long
							.parseLong(formaContOrg.getEntidadFederativa()));
				}
				if (StringUtils.isNotBlank(formaContOrg.getPais()) && !formaContOrg.getPais().equals("-1")) {
					entidadFederativaDTO.setValorIdPais(new ValorDTO(Long
							.parseLong(formaContOrg.getPais())));
				}
				ciudadDTO.setEntidadFederativaDTO(entidadFederativaDTO);
				asentamientoDTO.setCiudadDTO(ciudadDTO);
				domicilioDTO.setCiudadDTO(ciudadDTO);
				domicilioDTO.setEntidadDTO(entidadFederativaDTO);
				domicilioDTO.setAsentamientoDTO(asentamientoDTO);
				domicilioDTO.setMunicipioDTO(municipioDTO);
				
				if(formaContOrg.getIdDomicilio()!=0)
				{
					domicilioDTO.setElementoId(formaContOrg.getIdDomicilio());
				}
				
				// seteamos el domicilio al Hecho
				contactoOrgDTO.setDomicilio(domicilioDTO);
				
				//REVISAMOS SI ES LA MISMA DIRECCION PARA EL DOMICILIO DE NOTIFICACIONES
				if(formaContOrg.getMismoDomicilioNotificaciones().equals("true"))
				{
					contactoOrgDTO.setDomicilioNotificacion(domicilioDTO);
				}
				
			} else// Otro pais
			{
				DomicilioExtranjeroDTO domExtranjreoDTO = new DomicilioExtranjeroDTO();

				domExtranjreoDTO.setLatitud(formaContOrg
						.getLatitud());
				domExtranjreoDTO.setLongitud(formaContOrg
						.getLongitud());
				domExtranjreoDTO.setEdificio(formaContOrg.getEdificio());
				domExtranjreoDTO.setAlias(formaContOrg.getAliasDomicilio());
				domExtranjreoDTO.setEntreCalle2(formaContOrg.getYcalle());
				domExtranjreoDTO.setEntreCalle1(formaContOrg.getEntreCalle());
				domExtranjreoDTO.setReferencias(formaContOrg.getReferencias());
				domExtranjreoDTO.setNumeroInterior(formaContOrg.getNumInterior());
				domExtranjreoDTO.setNumeroExterior(formaContOrg.getNumExterior());
				domExtranjreoDTO.setCalle(formaContOrg.getCalle());
				domExtranjreoDTO.setPais(formaContOrg.getPais());
				domExtranjreoDTO.setCodigoPostal(formaContOrg.getCodigoPostal());
				domExtranjreoDTO.setCiudad(formaContOrg.getCiudad());
				domExtranjreoDTO.setMunicipio(formaContOrg.getDelegacionMunicipio());
				domExtranjreoDTO.setAsentamientoExt(formaContOrg
						.getAsentamientoColonia());
				domExtranjreoDTO.setEstado(formaContOrg.getEntidadFederativa());
				domExtranjreoDTO.setCalidadDTO(calidadDTO);
				domExtranjreoDTO.setExpedienteDTO(expedienteDTO);
				domExtranjreoDTO.setFechaCreacionElemento(new Date());
				domExtranjreoDTO.setCalidadDTO(calidadDTO);
				// seteamos el domicilio extranjero al Hecho
				contactoOrgDTO.setDomicilio(domExtranjreoDTO);
				
				//REVISAMOS SI ES LA MISMA DIRECCION PARA EL DOMICILIO DE NOTIFICACIONES
				if(formaContOrg.getMismoDomicilioNotificaciones().equals("true"))
				{
					contactoOrgDTO.setDomicilioNotificacion(domExtranjreoDTO);
				}
			}
			logger.info("REP LEGAL:::: Seteo domicilio notificaciones....");
			//FIN seteo domicilio
						
			NombreDemograficoDTO nombre= new NombreDemograficoDTO();
			nombre.setNombre(formaContOrg.getNombre());
			nombre.setApellidoPaterno(formaContOrg.getApellidoPaterno());
			nombre.setApellidoMaterno(formaContOrg.getApellidoMaterno());
			nombre.setRfc(formaContOrg.getRfc());
			nombre.setCurp(formaContOrg.getCurp());
			nombre.setSexo(formaContOrg.getSexo());
			
			if(StringUtils.isNotBlank(formaContOrg.getFechaNacimiento()))
			{
				nombre.setFechaNacimiento(DateUtils.obtener(formaContOrg.getFechaNacimiento()));
			}
			else
			{
				nombre.setFechaNacimiento(null);
			}
			
			nombre.setEdadAproximada(formaContOrg.getEdadAproximada());
			
			
			List<NombreDemograficoDTO> nombreL= new ArrayList<NombreDemograficoDTO>();
			nombreL.add(nombre);
			contactoOrgDTO.setNombresDemograficoDTO(nombreL);
			contactoOrgDTO.setFechaCreacionElemento(new Date());
			
			if(formaContOrg.getPais()!= null && !formaContOrg.getPais().isEmpty() && !formaContOrg.getPais().equals("-1")){
				ValorDTO paisValorDTO=new ValorDTO(Long.parseLong(formaContOrg.getPais()));
				nombre.setPaisValorDTO(paisValorDTO);
				EntidadFederativaDTO entidadFederativaDTO=new EntidadFederativaDTO();
				if(formaContOrg.getEntFederativaNacimiento()!= null && !formaContOrg.getEntFederativaNacimiento().isEmpty() && !formaContOrg.getEntFederativaNacimiento().equals("-1"))
				{
					entidadFederativaDTO.setEntidadFederativaId(Long.parseLong(formaContOrg.getEntFederativaNacimiento()));
					nombre.setEntidadFederativaDTO(entidadFederativaDTO);
				}
				else
				{
					nombre.setEntidadFederativaDTO(null);
				}
				
				MunicipioDTO municipioDTO = new MunicipioDTO();
				if(formaContOrg.getMunicipioNacimiento()!= null && !formaContOrg.getMunicipioNacimiento().isEmpty() &&  !formaContOrg.getMunicipioNacimiento().equals("-1"))
				{
					municipioDTO.setMunicipioId((Long.parseLong(formaContOrg.getMunicipioNacimiento())));
					nombre.setMunicipioDTO(municipioDTO);
				}
				else
				{
					nombre.setMunicipioDTO(null);
				}
				
			}else{
				nombre.setPaisValorDTO(null);
			}
			//seteo la calidad
			CalidadDTO calidadInvDTO=new CalidadDTO();
			calidadInvDTO.setCalidades(Calidades.DENUNCIANTE);
			contactoOrgDTO.setCalidadDTO(calidadInvDTO);
			
			ValorDTO idiomaDTO= new ValorDTO(Long.parseLong(formaContOrg.getIdioma()));
			
			contactoOrgDTO.setValorIdIdioma(idiomaDTO);
			//ValorDTO religionDTO=new ValorDTO(Long.parseLong(formaContOrg.getReligion()));
			//contactoOrgDTO.setValorIdReligion(religionDTO);
			if(formaContOrg.getEscolaridad() != null && !formaContOrg.getEscolaridad().isEmpty() && !formaContOrg.getEscolaridad().equals("- Selecciona -")){
				ValorDTO escolaridadDTO= new ValorDTO(Long.parseLong(formaContOrg.getEscolaridad()));
				contactoOrgDTO.setValorIdEscolaridad(escolaridadDTO);
			}else{
				contactoOrgDTO.setValorIdEscolaridad(null);
			}
			
			if(formaContOrg.getEstadoCivil() != null && !formaContOrg.getEstadoCivil().isEmpty() && !formaContOrg.getEstadoCivil().equals("- Selecciona -"))
			{
				ValorDTO estadoCivilDTO = new ValorDTO(Long.parseLong(formaContOrg.getEstadoCivil()));
				contactoOrgDTO.setValorIdEstadoCivil(estadoCivilDTO);
			}
			else
			{
				contactoOrgDTO.setValorIdEstadoCivil(null);
			}
			//private String alias; NO APLICA PARA CONTACTO ORGANIZACIONAL

			//seteo las ocupaciones
			if(!formaContOrg.getOcupacion().equalsIgnoreCase("") && formaContOrg.getOcupacion()!=null && !formaContOrg.getOcupacion().equalsIgnoreCase("undefined"))
			{
				//barremos las ocupaciones
				String[] ocupaciones=formaContOrg.getOcupacion().split(",");
				List<ValorDTO> ocupacionesL=new ArrayList<ValorDTO>();
				for (String ocupacion : ocupaciones) 
				{
				 ValorDTO ocupacionV=new ValorDTO(Long.parseLong(ocupacion));
				 ocupacionesL.add(ocupacionV);
				}
				contactoOrgDTO.setValorIdOcupacion(ocupacionesL);
			}
			//seteo las nacionalidades
			if(!formaContOrg.getNacionalidad().equalsIgnoreCase(""))
			{
				//barremos las ocupaciones
				String[] nacionalidades=formaContOrg.getNacionalidad().split(",");
				List<ValorDTO> nacionalidadesL=new ArrayList<ValorDTO>();
				for (String nacionalidad : nacionalidades) 
				{
				 ValorDTO nacionalidadV=new ValorDTO(Long.parseLong(nacionalidad));
				 nacionalidadesL.add(nacionalidadV);
				}
				contactoOrgDTO.setValorIdNacionalidad(nacionalidadesL);
			}
			logger.info("ACTA CIRCUNSTANCIAL:::: Seteo datos generales....");
			//FIN seteo datos generales
			
			//seteamos los medio de contacto
			List<TelefonoDTO> lstTelefonos = new ArrayList<TelefonoDTO>();
			String strTelefonos = formaContOrg.getMedioContactoTelefono();
			StringTokenizer lstStrTelefonos = new StringTokenizer(strTelefonos,"|");
			while (lstStrTelefonos.hasMoreElements()) {
				String strTelefono = (String) lstStrTelefonos.nextElement();
				String[] datosTelefono = strTelefono.split(",");

				TelefonoDTO telefono = new TelefonoDTO();

				ValorDTO valorTipoTelefono = new ValorDTO();
				if(datosTelefono.length!=0){
					valorTipoTelefono.setIdCampo(new Long(datosTelefono[0]));
					logger.info("&&&&Telefono:"+datosTelefono[0]);
					telefono.setValorTipoTelefono(valorTipoTelefono);
					telefono.setCodigoPais(datosTelefono[1]);
					logger.info("&&&&Telefono:"+datosTelefono[1]);
					telefono.setCodigoArea(datosTelefono[2]);
					logger.info("&&&&Telefono:"+datosTelefono[2]);
					telefono.setNumeroTelefonico(datosTelefono[3]);
					logger.info("&&&&Telefono:"+datosTelefono[3]);
					lstTelefonos.add(telefono);
				}
				
			}
			contactoOrgDTO.setTelefonosDTO(lstTelefonos);

			List<CorreoElectronicoDTO> lstCorreos = new ArrayList<CorreoElectronicoDTO>();
			if(!formaContOrg.getMedioContactoCorreo().trim().isEmpty()){
				String[] datosCorreo = formaContOrg.getMedioContactoCorreo().split(",");
				for (int i = 0; i < datosCorreo.length; i++) {
					CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
					correo.setDireccionElectronica(datosCorreo[i]);
					lstCorreos.add(correo);
				}
			}
			contactoOrgDTO.setCorreosDTO(lstCorreos);
			//fin seteo medios de contacto
			
			//guardamos el acta circunstanciada
			HechoDTO hechoDTO=new HechoDTO();
			hechoDTO.setDescNarrativa(formaContOrg.getMotivoComparecencia());
			hechoDTO.setExpediente(expedienteDTO);
			
			//revisamos si es un insert o un update
			if(formaContOrg.getIdHecho()!=0)
			{
				hechoDTO.setHechoId(formaContOrg.getIdHecho());
			}
			
			//INICIO asignación documento de identificación
			if(formaContOrg.getDocIdentificacion() != null){
			
				ValorDTO docIdentificacion=new ValorDTO();
				Long docIdentificacionId = NumberUtils.toLong(formaContOrg.getDocIdentificacion(),0L);
				
				if(docIdentificacionId > 0){
					docIdentificacion.setIdCampo(docIdentificacionId);
					contactoOrgDTO.setValorIdIdentificaion(docIdentificacion);
					if(isNotBlank(formaContOrg.getFolioDoc())){
						contactoOrgDTO.setFolioIdentificacion(formaContOrg.getFolioDoc());
					}
				}
			}
			//FIN asignación documento de identificación
						
			
			ActaCircunstanciadaDTO actaDTO= new ActaCircunstanciadaDTO(contactoOrgDTO, hechoDTO);
			logger.info("A punto de guardar ACTA CIRCUNSTANCIAL - FIN ");
			
			expedienteDTO=expedienteDelegate.registrarActaCircunstanciada(actaDTO, expedienteDTO);
			
			// revisamos si el guardado fue exitoso para mandar el xml
			// correspondiente
			if (expedienteDTO != null
					&& expedienteDTO.getExpedienteId() != null) {
				logger.info("numeroExpediente_insercion_actaCircunstanciada:: "+expedienteDTO.getNumeroExpediente());
				//consultamos el Acta recien ingresada
				ExpedienteDTO expedienteDTOConsul=new ExpedienteDTO();
				expedienteDTOConsul.setNumeroExpediente(expedienteDTO.getNumeroExpediente());
				expedienteDTOConsul=expedienteDelegate.obtenerNumeroExpedienteByNumExp(expedienteDTOConsul,null);
				actaDTO=expedienteDelegate.consultarActaCircunstaciada(expedienteDTOConsul);
				// regresamos el XML del acata circunstanciada recien ingresada
				if (actaDTO != null && actaDTO.getHechoDTO()!= null && actaDTO.getHechoDTO().getHechoId()>0) {
					converter.alias("actaDTO", ActaCircunstanciadaDTO.class);
					String xml = converter.toXML(actaDTO);
					logger.info("acta_circunstanciada:: "+xml);
					escribirRespuesta(response, xml);
				} else {
					ActaCircunstanciadaDTO actadosDTO=new ActaCircunstanciadaDTO(null, new HechoDTO(0L));
					converter.alias("actaDTO", ActaCircunstanciadaDTO.class);
					String xml = converter.toXML(actadosDTO);
					logger.info("acta_circunstanciada_vacia:: "+xml);
					escribirRespuesta(response, xml);
				}
				
//				converter.alias("expedienteDTO", ExpedienteDTO.class);
//				String xml = converter.toXML(expedienteDTO);
//				logger.info("XML_registrarActaCircunstanciada:: "+xml);
//				escribirRespuesta(response, xml);
			} else {
				expedienteDTO.setExpedienteId(0L);
				converter.alias("expedienteDTO", ExpedienteDTO.class);
				String xml = converter.toXML(expedienteDTO);
				logger.info("XML_registrarActaCircunstanciada:: "+xml);
				escribirRespuesta(response, xml);
			}
			logger.info("Termina ejecucion Action guardar ACTA CIRCUNSTANCIAL - FIN ");
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
			escribir(response, "", e);
		}
		return null;
	}


	/**
	 * Metodo utilizado para consultar un Acta Circunstancial
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward consultarActaCircunstanciada(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			 
			logger.info("ejecutando Action consultar Acta Circunstancial - consultarActaCircunstanciada");
			String idExpAtAdmin=request.getParameter("idExpAtAdmin");
			String numExpAtAdmin=request.getParameter("numExpAtAdmin");
			//obtenemos el expediente en sesion
			//ExpedienteDTO expedienteDTO=super.getExpedienteTrabajo(request, numExpAtAdmin);
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setNumeroExpediente(numExpAtAdmin);
			expedienteDTO=expedienteDelegate.obtenerNumeroExpedienteByNumExp(expedienteDTO,null);
			//subimos el expediente a la sesion
			super.setExpedienteTrabajo(request, expedienteDTO);
			ActaCircunstanciadaDTO actaDTO=expedienteDelegate.consultarActaCircunstaciada(expedienteDTO);
			logger.info("numExpAdminAt :: "+numExpAtAdmin);
			
			if(actaDTO.getInvolucradoDTO().getDomicilio()!=null){
				if(actaDTO.getInvolucradoDTO().getDomicilio().getLatitud()!=null && !actaDTO.getInvolucradoDTO().getDomicilio().getLatitud().equals("")){
					String latitud=actaDTO.getInvolucradoDTO().getDomicilio().getLatitud();
					
					String subLatitud=latitud.substring(1,latitud.length());//quitamos la letra de la cadena
					String[] arr=subLatitud.split("°");//separlo los grados de los minutos y segundos
					String[] arrDos=arr[1].split("'");//separamos los minutos y segundos
					String segundos=arrDos[1].substring(0,arrDos[1].length()-1);
					//seteamos los valores
					actaDTO.getInvolucradoDTO().getDomicilio().setLatitudN(latitud.substring(0,1));logger.info("domicilio hechoDTO NO NULL!!! 1");
					actaDTO.getInvolucradoDTO().getDomicilio().setLatitudGrados(arr[0]);logger.info("domicilio hechoDTO NO NULL!!! 2");
					actaDTO.getInvolucradoDTO().getDomicilio().setLatitudMinutos(arrDos[0]);logger.info("domicilio hechoDTO NO NULL!!! 3");
					actaDTO.getInvolucradoDTO().getDomicilio().setLatitudSegundos(segundos);logger.info("domicilio hechoDTO NO NULL!!! 4");
				}
				if(actaDTO.getInvolucradoDTO().getDomicilio().getLongitud()!=null && !actaDTO.getInvolucradoDTO().getDomicilio().getLongitud().equals("")){
					String longitud=actaDTO.getInvolucradoDTO().getDomicilio().getLongitud();
					
					String subLongitud=longitud.substring(1,longitud.length());//quitamos la letra de la cadena
					String[] arr=subLongitud.split("°");//separlo los grados de los minutos y segundos
					String[] arrDos=arr[1].split("'");//separamos los minutos y segundos
					String segundos=arrDos[1].substring(0,arrDos[1].length()-1);
					
					actaDTO.getInvolucradoDTO().getDomicilio().setLongitudE(longitud.substring(0,1));logger.info("domicilio hechoDTO NO NULL!!! 5");
					actaDTO.getInvolucradoDTO().getDomicilio().setLongitudGrados(arr[0]);logger.info("domicilio hechoDTO NO NULL!!! 6");
					actaDTO.getInvolucradoDTO().getDomicilio().setLongitudMinutos(arrDos[0]);logger.info("domicilio hechoDTO NO NULL!!! 7");
					actaDTO.getInvolucradoDTO().getDomicilio().setLongitudSegundos(segundos);logger.info("domicilio hechoDTO NO NULL!!! 8");
				}
			}
			
			// correspondiente
			if (actaDTO != null && actaDTO.getHechoDTO()!= null && actaDTO.getHechoDTO().getHechoId()>0) {
				converter.alias("actaDTO", ActaCircunstanciadaDTO.class);
				String xml = converter.toXML(actaDTO);
				logger.info("acta_circunstanciada:: "+xml);
				escribirRespuesta(response, xml);
			} else {
				ActaCircunstanciadaDTO actadosDTO=new ActaCircunstanciadaDTO(null, new HechoDTO(0L));
				converter.alias("actaDTO", ActaCircunstanciadaDTO.class);
				String xml = converter.toXML(actadosDTO);
				logger.info("acta_circunstanciada_vacia:: "+xml);
				escribirRespuesta(response, xml);
			}
			logger.info("Termina ejecucion Action guardar ACTA CIRCUNSTANCIAL - FIN ");
		} catch (NSJPNegocioException e) {
			ActaCircunstanciadaDTO actadosDTO=new ActaCircunstanciadaDTO(null, new HechoDTO(0L));
			converter.alias("actaDTO", ActaCircunstanciadaDTO.class);
			String xml = converter.toXML(actadosDTO);
			logger.info(xml);
			logger.error(e.getMessage(), e);
			escribir(response, "", e);
		}
		return null;
	}
	
	public ActionForward consultaContactosTelefono(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			String idInvolucrado=request.getParameter("idInvolucrado");
			logger.info("%%%%%%%%%%%%Este es el id del involucrado a consultar: "+idInvolucrado);
			InvolucradoDTO involucradoDTO=new InvolucradoDTO();
			involucradoDTO.setElementoId(Long.parseLong(idInvolucrado));
//			involucradoDTO.setCalidadDTO(calidadDTO);
			involucradoDTO=involucradoDelegate.obtenerInvolucrado(involucradoDTO);
			List<TelefonoDTO> telefonoDTOs = new ArrayList<TelefonoDTO>();
			telefonoDTOs= involucradoDTO.getTelefonosDTO();
				
			converter.alias("listaTelefonos", java.util.ArrayList.class);
			converter.alias("TelefonoDTO", TelefonoDTO.class);
			logger.info("tels_medios_contacto:: "+converter.toXML(telefonoDTOs));
			
			logger.info("Lista de Telefonos" + telefonoDTOs);
			
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");		

			int lTotalRegistros = telefonoDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			//NSJYUCPROC2011333WT

			for(TelefonoDTO tareaDTO2 : telefonoDTOs){

				writer.print("<row id='"+tareaDTO2.getMedioDeContadoId()+ "'>");
				
				writer.print("<cell>");
				if(tareaDTO2.getValorTipoTelefono().getIdCampo() !=null){
					writer.print(tareaDTO2.getValorTipoTelefono().getIdCampo());
						}
				writer.print("</cell>");
				writer.print("<cell>");
				if(tareaDTO2.getValorTipoTelefono() !=null){
					writer.print(tareaDTO2.getValorTipoTelefono().getValor());
						}
				writer.print("</cell>");
				writer.print("<cell>");
				if(tareaDTO2.getCodigoPais() !=null){
					writer.print(tareaDTO2.getCodigoPais());
						}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(tareaDTO2.getCodigoArea() !=null){
					writer.print(tareaDTO2.getCodigoArea());
						}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(tareaDTO2.getNumeroTelefonico() !=null){
					writer.print(tareaDTO2.getNumeroTelefonico());
						}
				writer.print("</cell>");
				
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public ActionForward consultaContactosCorreo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			logger.info("Consulta Correos");
			String idInvolucrado=request.getParameter("idInvolucrado");
			logger.info("%%%%%%%%%%%%Este es el id del involucrado a consultar: "+idInvolucrado);
			InvolucradoDTO involucradoDTO=new InvolucradoDTO();
			involucradoDTO.setElementoId(Long.parseLong(idInvolucrado));
//			involucradoDTO.setCalidadDTO(calidadDTO);
			involucradoDTO=involucradoDelegate.obtenerInvolucrado(involucradoDTO);
			List<CorreoElectronicoDTO> correoElectronicoDTOs = new ArrayList<CorreoElectronicoDTO>();
			correoElectronicoDTOs= involucradoDTO.getCorreosDTO();
			
			converter.alias("listaCorreos", java.util.ArrayList.class);
			converter.alias("CorreoElectronicoDTO", CorreoElectronicoDTO.class);
			logger.info("tels_medios_contacto:: "+converter.toXML(correoElectronicoDTOs));
			
			logger.info("Lista de Telefonos" + correoElectronicoDTOs);
			
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");		

			int lTotalRegistros = correoElectronicoDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			//NSJYUCPROC2011333WT

			for(CorreoElectronicoDTO  tareaDTO2 : correoElectronicoDTOs){

				writer.print("<row id='"+tareaDTO2.getMedioDeContadoId()+ "'>");
				
				writer.print("<cell>");
				if(tareaDTO2.getDireccionElectronica() !=null){
					writer.print(tareaDTO2.getDireccionElectronica());
						}
				writer.print("</cell>");
				
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}