/**
* Nombre del Programa : AdministrarCarpetaEjecucionAction.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/07/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.web.carpetaejecucion.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoExpediente;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.audiencia.AudienciaDelegate;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.CarpetaEjecucionDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.medidasalternas.MedidasAlternasDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.EventoDTO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Action para atender las solicitudes de la adminsitración de una
 * carpeta de ejecución
 * @version 1.0 
 * @author Emigdio Hernández
 *
 */
public class AdministrarCarpetaEjecucionAction extends GenericAction {
	
	@Autowired
	DocumentoDelegate documentoDelegate;
	@Autowired
	ExpedienteDelegate expedienteDelegate;
	@Autowired
	CarpetaEjecucionDelegate carpetaEjecucionDelegate;
	@Autowired
	SolicitudDelegate solicitudDelegate;
	@Autowired
	AudienciaDelegate audienciaDelegate;
	@Autowired
	MedidasAlternasDelegate medidasAlternasDelegate;
	
	
	/** Log de clase */
	private static final Logger log = Logger.getLogger(AdministrarCarpetaEjecucionAction.class);

	/**
	 * Método para obtener los datos para rellenar el grid de los documentos asociados a la carpeta
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 */
	public ActionForward obtenerListaDocumentosDeCarpetaGrid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		 
		Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"));
		ExpedienteDTO expediente = new ExpedienteDTO();
		expediente.setNumeroExpedienteId(numeroExpedienteId);
		List<DocumentoDTO> documentos = documentoDelegate.consultarDocumentosPorNumeroExpediente(expediente);
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter writer = response.getWriter();

		log.debug("Recuperando documentos de carpeta para grid: elementos: " + documentos.size());
		writer.print("<rows>");
	
		writer.print("<records>" + documentos.size() + "</records>");

		for (DocumentoDTO documento : documentos) {
			writer.print("<row id='" + documento.getDocumentoId()+ "'>");
			writer.print("<cell>" + DateUtils.formatear(documento.getFechaCreacion())+ "</cell>");
			writer.print("<cell>" + documento.getNombreDocumento()+ "</cell>");
			writer.print("</row>");
		}			
		
		writer.print("</rows>");
		writer.flush();
		writer.close();
		
		
		return null;
	}
	

	/**
	 * Método para obtener los datos generales de la carpeta de ejcución:
	 * Datos del expediente carpeta y datos del sentenciado
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward obtenerDatosGeneralesCarpeta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"));
		ExpedienteDTO expediente = new ExpedienteDTO();
		expediente.setNumeroExpedienteId(numeroExpedienteId);
		
		try {
			expediente = carpetaEjecucionDelegate.consultarDatosGeneralesExpedienteCarpetaDeEjecucion(numeroExpedienteId);
				log.info("Datos Generales para carpeta ..."+expediente);		
			converter.alias("expedienteDTO",ExpedienteDTO.class);
			String xml = converter.toXML(expediente);
			escribir(response, xml,null);
			
		} catch (NSJPNegocioException e) {
			log.error(e);
		}
		
		
		return null;
		
	}
	
	
	/**
	 * Método para obtener las solicitudes de beneficios de preeliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarSolicitudesDeBeneficiosDePreliberacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"));
		ExpedienteDTO expediente = new ExpedienteDTO();
		expediente.setNumeroExpedienteId(numeroExpedienteId);
		
		try {
			
			List<SolicitudDTO> solicitudes =solicitudDelegate.
			consultarSolicitudesPorNumeroExpedienteYTipo(numeroExpedienteId, TiposSolicitudes.BENEFICIO_PRELIBERACION);
		
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
				writer.print("<records>" + solicitudes.size() + "</records>");
				
				for (SolicitudDTO solicitud : solicitudes) {
					writer.print("<row id='" + solicitud.getDocumentoId() +"'>");
						writer.print("<cell>"+ DateUtils.formatear(solicitud.getFechaCreacion()) + " "+
								DateUtils.formatearHora(solicitud.getFechaCreacion()) + "</cell>");
						writer.print("<cell>"+ solicitud.getEstatus().getValor() + "</cell>");
						writer.print("<cell>"+ solicitud.getTipoSolicitudDTO().getValor() + "</cell>");
					writer.print("</row>");
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			log.error(e);
		}
		
		
		return null;
		
	}
	/**
	 * Realiza el registro de una solicitud de estudio del sentenciado y retorna la información
	 * necesaria para emitir un documento
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward registrarSolicitudEstudio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"));
		ExpedienteDTO expediente = new ExpedienteDTO();
		expediente.setNumeroExpedienteId(numeroExpedienteId);
		
		try {
			expediente = carpetaEjecucionDelegate.consultarDatosGeneralesExpedienteCarpetaDeEjecucion(numeroExpedienteId);
			SolicitudDTO solicitud = new SolicitudDTO();
			
			solicitud.setEstatus(new ValorDTO(EstatusSolicitud.ABIERTA.getValorId()));
			solicitud.setExpedienteDTO(expediente);
			solicitud.setFechaCreacion(new Date());
			solicitud.setFormaDTO(new FormaDTO(Formas.SOLICITUD_DE_ESTUDIO.getValorId()));
			solicitud.setNombreDocumento(org.apache.commons.lang.StringUtils.EMPTY);
			solicitud.setTipoDocumentoDTO(new ValorDTO(82L));
			solicitud.setTipoSolicitudDTO(new ValorDTO(TiposSolicitudes.ESTUDIOS.getValorId()));
			solicitud.setUsuarioSolicitante(getUsuarioFirmado(request)!=null?getUsuarioFirmado(request).getFuncionario():null);
			solicitud.setFolioDocumento("");
			solicitud.setNombreDocumento("");
			
			solicitud.setVersion(1.0);
			solicitud.setNumeroFojas("");
			
			solicitud.setDocumentoId(solicitudDelegate.registrarSolicitud(solicitud).getDocumentoId());
			
			
			String respuesta = "<documentoId>"+solicitud.getDocumentoId()+"</documentoId>";
			escribirRespuesta(response, respuesta);
			
		} catch (NSJPNegocioException e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
		
	}
		
	
	
	/**
	 * Método para obtener las solicitudes de beneficios de preeliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarSolicitudesPorTipoYEstatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			Long clave = getUsuarioFirmado(request).getFuncionario().getClaveFuncionario();
			UsuarioDTO usuario = getUsuarioFirmado(request);
			
			List<SolicitudDTO> solicitudes = solicitudDelegate.consultarSolicitudesPorTipoYEstatus(TiposSolicitudes.BENEFICIO_PRELIBERACION, EstatusSolicitud.ABIERTA, null,usuario);
			//consultarSolicitudesPorNumeroExpedienteYTipo(numeroExpedienteId, TiposSolicitudes.BENEFICIOS_PRELIBERACION);		
			log.debug("/**** Solicitudes :. "+solicitudes.size());
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
				writer.print("<records>" + solicitudes.size() + "</records>");
//				Número de causa
//				- Número de carpeta de ejecución
//				- Solicitante
//				- Institución solicitante
//				- Sentenciado
//				- Fecha - hora de solicitud (Nota 1)
				for (SolicitudDTO solicitud : solicitudes) {
					
					if (solicitud instanceof SolicitudAudienciaDTO) {
						log.debug("/**** Instancia Solicitud Audiencia");
						SolicitudAudienciaDTO newSolAud = (SolicitudAudienciaDTO) solicitud;
						log.debug("/********/" + newSolAud);
						writer.print("<row id='"+newSolAud.getDocumentoId()+"*-*"+solicitud.getExpedienteDTO().getNumeroExpedienteId()+"*-*"+newSolAud.getAudiencia().getId()+"'>");
					} else {
						log.debug("/**** Instancia SOlicitud ");
						writer.print("<row id='"+solicitud.getDocumentoId()+"*-*"+solicitud.getExpedienteDTO().getNumeroExpedienteId()+"'>");
					}
					writer.print("<cell>"+(solicitud.getNumCausa()==null?"-":solicitud.getNumCausa())+ "</cell>");
					writer.print("<cell>"+solicitud.getNumCarpetaEjecucion() + "</cell>");				
					writer.print("<cell>"+solicitud.getNombreSolicitante() + "</cell>");
					writer.print("<cell>"+solicitud.getNombreInstitucionSolicitante() + "</cell>");
					if (solicitud.getInvolucradoDTO()!=null)
						writer.print("<cell>"+solicitud.getInvolucradoDTO().getNombreCompleto()+ "</cell>");
					else
						writer.print("<cell>"+"-"+ "</cell>");
					
						writer.print("<cell>"+ DateUtils.formatear(solicitud.getFechaCreacion()) + " "+
								DateUtils.formatearHora(solicitud.getFechaCreacion()) + "</cell>");
						
					writer.print("</row>");
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			log.error(e);
		}
		
		
		return null;
		
	}

	/**
	 * Método para obtener las solicitudes de beneficios de preeliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarAudienciasByTipoYEstatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			AudienciaDTO audienciaDTO = new AudienciaDTO();
			
			Calendar calTemp = Calendar.getInstance();
			audienciaDTO.setFechaEvento(calTemp.getTime());
			UsuarioDTO usuario = getUsuarioFirmado(request);
			List<AudienciaDTO> audienciasEj = audienciaDelegate.consultarAudienciasByTipoYFecha(audienciaDTO, TipoAudiencia.EJECUCION,usuario);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
//			writer.print("<records>" + audienciasEj.size() + "</records>");	
			final PaginacionDTO pag = PaginacionThreadHolder.get();
			log.debug("pag :: " + pag);
			if (pag != null && pag.getTotalRegistros() != null) {
				writer.print("<total>" + pag.getTotalPaginas() + "</total>");
				writer.print("<records>" + pag.getTotalRegistros() + "</records>");
			} else {
				writer.print("<total>0</total>");
				writer.print("<records>0</records>");
			}
//				- Número de causa
//				- Número de carpeta de ejecución				
//				- Sentenciado
//				- Fecha Audiencia 
//				- Sala Audiencia (Nota 1)
			for (AudienciaDTO audRespDTO : audienciasEj) {
				log.info("/********/" + audRespDTO);
				writer.print("<row id='"+audRespDTO.getId()+"*-*"+audRespDTO.getTocaOrCarpeta().getNumeroExpedienteId()+"'>");			
				if (audRespDTO.getCausa()!=null)
					writer.print("<cell>"+ audRespDTO.getCausa().getNumeroExpediente() + "</cell>");
				else
					writer.print("<cell>"+ "-" + "</cell>");
				
				if (audRespDTO.getTocaOrCarpeta()!=null)
					writer.print("<cell>"+ audRespDTO.getTocaOrCarpeta().getNumeroExpediente() + "</cell>");
				else
					writer.print("<cell>"+ "-" + "</cell>");
				
				if (audRespDTO.getSentenciado()!=null)
					writer.print("<cell>"+ audRespDTO.getSentenciado().getNombreCompleto() + "</cell>");
				else 
					writer.print("<cell>"+ "-" + "</cell>");
				
				writer.print("<cell>"+ DateUtils.formatear(audRespDTO.getFechaEvento()) + "</cell>");
				writer.print("<cell>"+ audRespDTO.getSala().getNombreSala() + "</cell>");
				writer.print("</row>");				
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();			
		} catch (Exception e) {
			log.error(e);
		}
		
	
		
//		try {
//			
//			List<SolicitudDTO> solicitudes = audienciaDelegate.consultarAudienciasByTipoYEstatus(tipoAudiencia, estatusAudiencia);
//			//consultarSolicitudesPorNumeroExpedienteYTipo(numeroExpedienteId, TiposSolicitudes.BENEFICIOS_PRELIBERACION);
//		
//			
//			response.setContentType("text/xml; charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache");
//			PrintWriter writer = response.getWriter();
//			writer.print("<rows>");
//				writer.print("<records>" + solicitudes.size() + "</records>");
////				Número de causa
////				- Número de carpeta de ejecución
////				- Solicitante
////				- Institución solicitante
////				- Sentenciado
////				- Fecha - hora de solicitud (Nota 1)
//				for (SolicitudDTO solicitud : solicitudes) {
//					log.info("/********/" + solicitud);
//					writer.print("<row id='" + solicitud.getDocumentoId() +"'>");					
//					writer.print("<cell>"+solicitud.getNumCausa() + "</cell>");
//					writer.print("<cell>"+solicitud.getNumCarpetaEjecucion() + "</cell>");				
//					writer.print("<cell>"+solicitud.getNombreSolicitante() + "</cell>");
//					writer.print("<cell>"+solicitud.getNombreInstitucionSolicitante()+ "</cell>");
//					if (solicitud.getInvolucradoDTO()!=null)
//						writer.print("<cell>"+solicitud.getInvolucradoDTO().getNombreCompleto()+ "</cell>");
//					else
//						writer.print("<cell>"+"-"+ "</cell>");
//					
//						writer.print("<cell>"+ DateUtils.formatear(solicitud.getFechaCreacion()) + " "+
//								DateUtils.formatearHora(solicitud.getFechaCreacion()) + "</cell>");
//						
//					writer.print("</row>");
//				}
//			writer.print("</rows>");
//			writer.flush();
//			writer.close();
//			
//		} catch (Exception e) {
//			log.error(e);
		//}
		
		
		return null;
		
	}

	/**
	 * Método para obtener las solicitudes de beneficios de preeliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward gridCarpetaEjecucionCarpetaEjecucion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			
			List<ExpedienteDTO> numExpedientes = expedienteDelegate.consultarNumeroExpedienteByTipoYEstatus(TipoExpediente.CARPETA_DE_EJECUCION, EstatusExpediente.ABIERTO);
		
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
				writer.print("<records>" + numExpedientes.size() + "</records>");
//				Número de causa
//				- Número de carpeta de ejecución
			ExpedienteDTO dto = new ExpedienteDTO();
			ValorDTO valorDTO = new ValorDTO();
				valorDTO.setIdCampo(Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId());
				Long cali=valorDTO.getIdCampo().longValue();
				for (ExpedienteDTO expedienteDTO : numExpedientes) {
					
					
					dto = carpetaEjecucionDelegate.consultarDatosGeneralesExpedienteCarpetaDeEjecucion(expedienteDTO.getNumeroExpedienteId());
					Long idAudEj = new Long(0);
					if (expedienteDTO.getAudiencias()!=null) {
						for (AudienciaDTO audEjec : expedienteDTO.getAudiencias()) {
							if (audEjec.getTipoAudiencia().getIdCampo().equals(TipoAudiencia.EJECUCION.getValorId())) {
								idAudEj = audEjec.getId();
							}
						} 
					} 
					
					if (!idAudEj.equals(0))
						writer.print("<row id='" + expedienteDTO.getNumeroExpedienteId() +"*-*"+ idAudEj +"'>");
					else{
						writer.print("<row id='" + expedienteDTO.getNumeroExpedienteId() +"*-*"+0+"'>");}
					
					writer.print("<cell>");
						if(expedienteDTO.getCasoDTO()!=null){
						writer.print(expedienteDTO.getCasoDTO().getNumeroGeneralCaso());
						}else{
						writer.print("-");
						}
					writer.print("</cell>");
					
					writer.print("<cell>");
						if(expedienteDTO.getNumeroExpediente()!=null){
						writer.print(expedienteDTO.getNumeroExpediente());
						}else{
						writer.print("-");
						}
					writer.print("</cell>");
					
					writer.print("<cell>");
						if(dto.getNumeroExpediente()!=null){
						writer.print(dto.getNumeroExpediente());
						}else{
						writer.print("-");
						}
					writer.print("</cell>");
				
				writer.print("<cell>");
				if(dto.getInputado()!=null){
					writer.print(dto.getInputado().getNombreCompleto());
					}else{
					writer.print("-");
					}
				writer.print("</cell>");
				
				writer.print("<cell>");
					if(dto.getStrFechaApertura()!=null){
					writer.print(dto.getStrFechaApertura());
					}else{
					writer.print("-");
					}
				writer.print("</cell>");
				
				writer.print("</row>");
				} 
//					log.info("/******** ::::: /" + expedienteDTO);
//					Long idAudEj = new Long(0);
//					if (expedienteDTO.getAudiencias()!=null) {
//						for (AudienciaDTO audEjec : expedienteDTO.getAudiencias()) {
//							if (audEjec.getTipoAudiencia().getIdCampo().equals(TipoAudiencia.EJECUCION.getValorId())) {
//								idAudEj = audEjec.getId();
//							}
//						} 
//					} 
//					
//					if (!idAudEj.equals(0))
//						writer.print("<row id='" + expedienteDTO.getNumeroExpedienteId() +"*-*"+ idAudEj +"'>");
//					else{
//						writer.print("<row id='" + expedienteDTO.getNumeroExpedienteId() +"'>");}
//					
//					writer.print("<cell>"+expedienteDTO.getCasoDTO().getNumeroGeneralCaso() + "</cell>");	
//									
					
	//				writer.print("</row>");
		//		} 
									
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			log.error(e);
		}
		
		
		return null;
		
	}
	
	/**
	 * Método para obtener las solicitudes de beneficios de preeliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward gridHistoricoCarpetaEjecucion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			
			UsuarioDTO usuario = getUsuarioFirmado(request);
			
			List<ExpedienteDTO> numExpedientes = expedienteDelegate.consultarNumeroExpedienteHistorico(usuario);
			//consultarSolicitudesPorNumeroExpedienteYTipo(numeroExpedienteId, TiposSolicitudes.BENEFICIOS_PRELIBERACION);
		
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
				writer.print("<records>" + numExpedientes.size() + "</records>");
//				Número de causa
//				- Número de carpeta de ejecución				
				for (ExpedienteDTO expedienteDTO : numExpedientes) {
					log.info("/******** ::: /" + expedienteDTO);
					writer.print("<row id='" + expedienteDTO.getNumeroExpedienteId() +"'>");					
					writer.print("<cell>"+expedienteDTO.getNumeroExpediente() + "</cell>");
//					writer.print("<cell>"+expedienteDTO.getNumeroExpediente() + "</cell>");				
//					writer.print("<cell>"+expedienteDTO.getNombreSolicitante() + "</cell>");
//					writer.print("<cell>"+solicitud.getNombreInstitucionSolicitante()+ "</cell>");
//					if (solicitud.getInvolucradoDTO()!=null)
//						writer.print("<cell>"+solicitud.getInvolucradoDTO().getNombreCompleto()+ "</cell>");
//					else
//						writer.print("<cell>"+"-"+ "</cell>");
//					
//						writer.print("<cell>"+ DateUtils.formatear(solicitud.getFechaCreacion()) + " "+
//								DateUtils.formatearHora(solicitud.getFechaCreacion()) + "</cell>");
						
					writer.print("</row>");
				} 
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			log.error(e);
		}
		
		
		return null;
		
	}
	
	/**
	 * Método para obtener las solicitudes de beneficios de preeliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarSolicitudBeneficiosPreliberacionCE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action de Consultar beneficios datos:::::");
		String numeroExpedienteId = request.getParameter("numeroExpedienteId");
		log.info("id numeroExpedienteId...."+numeroExpedienteId);
		String documentoID = request.getParameter("documentoID");
		log.info("id documentoID...."+documentoID);
		String audienciaID = request.getParameter("idAudiencia");
		log.info("id audienciaID...."+audienciaID);
		try {
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			SolicitudDTO solicitudDTO = new SolicitudDTO();
			solicitudDTO.setDocumentoId(Long.parseLong(documentoID));
			expedienteDTO.setNumeroExpedienteId(Long.parseLong(numeroExpedienteId));
			solicitudDTO.setExpedienteDTO(expedienteDTO);
						
			expedienteDTO = solicitudDelegate.atenderSolicitudPreliberacion(solicitudDTO);
			log.debug("/**** Expediente DTO :: "+expedienteDTO);
			super.setExpedienteTrabajo(request, expedienteDTO);
			//consultarSolicitudesPorNumeroExpedienteYTipo(numeroExpedienteId, TiposSolicitudes.BENEFICIOS_PRELIBERACION);		
			
			String xml = null;
			PrintWriter pw = null;
			//converter.alias("expedienteDTO",  java.util.List.class);
			converter.alias("expedienteDTO", ExpedienteDTO.class);
			xml = converter.toXML(expedienteDTO);
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			log.info("lista de solicitudes no atendidas" + expedienteDTO );	
						
			
		} catch (Exception e) {
			log.error(e);
		}		
		
		return null;
		
	}
	
	
	/**
	 * Método para obtener las solicitudes de Carpeta de beneficios de preeliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward gridSolicitaCarpetas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action de Consultar beneficios datos:::::");
		String numeroExpedienteId = request.getParameter("numeroExpedienteId");
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(Long.parseLong(numeroExpedienteId));
		
//		
		//try {
			List<SolicitudDTO> solicitudDTOs = new ArrayList<SolicitudDTO>();
			
			try {
				solicitudDTOs =	solicitudDelegate.consultarSolicitudesPorNumeroExpedienteYTipo(expedienteDTO.getNumeroExpedienteId(), TiposSolicitudes.BENEFICIO_PRELIBERACION);
			
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
				writer.print("<records>" + solicitudDTOs.size() + "</records>");
//				Número de causa
//				- Número de carpeta de ejecución
				for (SolicitudDTO solicitudDTO : solicitudDTOs) {
					log.info("/******** ::::: /" + solicitudDTO);
					writer.print("<row id='" + solicitudDTO.getDocumentoId() +"'>");					
					writer.print("<cell>"+solicitudDTO.getStrHoraCreacion() +" "+solicitudDTO.getStrFechaCreacion()+ "</cell>");
					writer.print("<cell>"+solicitudDTO.getEstatus().getNombreCampo()+ "</cell>");
					writer.print("<cell>"+solicitudDTO.getTipoSolicitudDTO().getNombreCampo()+ "</cell>");
					writer.print("</row>");
				} 
									
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (NSJPNegocioException e) {
			log.error(e);
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	/**
	 * Método para obtener las solicitar Actuaciones Preliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward solicitarActuacionesPreliberacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action de Consultar beneficios datos:::::");
		String numeroExpedienteId = request.getParameter("numeroExpedienteId");
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(Long.parseLong(numeroExpedienteId));
		
//		
		//try {
//			
			
		//	solicitudDelegate.consultarSolicitudesPorNumeroExpedienteYTipo(numeroExpedienteId, TiposSolicitudes.BENEFICIOS_PRELIBERACION);
//			List<ExpedienteDTO> numExpedientes = expedienteDelegate.consultarNumeroExpedienteByTipoYEstatus(TipoExpediente.CARPETA_EJECUCION, EstatusExpediente.ABIERTO);
//		
//			response.setContentType("text/xml; charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache");
//			PrintWriter writer = response.getWriter();
//			writer.print("<rows>");
//				writer.print("<records>" + numExpedientes.size() + "</records>");
////				Número de causa
////				- Número de carpeta de ejecución
//				for (ExpedienteDTO expedienteDTO : numExpedientes) {
//					log.info("/******** ::::: /" + expedienteDTO);
//					writer.print("<row id='" + expedienteDTO.getNumeroExpedienteId() +"'>");					
//					writer.print("<cell>"+expedienteDTO.getNumeroExpediente() + "</cell>");											
//					writer.print("</row>");
//				} 
//									
//			writer.print("</rows>");
//			writer.flush();
//			writer.close();
//			
//		} catch (Exception e) {
//			log.error(e);
//		}
		
		
		return null;
		
	}

	/**
	 * Método para obtener las solicitar Actuaciones Preliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward documentosCarpetaPreliberacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action de Documentos para beneficios Pre liberacion:::::");
		String numeroExpedienteId = request.getParameter("numeroExpedienteId");
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(Long.parseLong(numeroExpedienteId));
		
		
		try {
			
			List<DocumentoDTO> documentoDTOs = new ArrayList<DocumentoDTO>();
			documentoDTOs = documentoDelegate.consultarDocumentosPorNumeroExpediente(expedienteDTO);
			
		
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
				writer.print("<records>" + documentoDTOs.size() + "</records>");
//				Número de causa
//				- Número de carpeta de ejecución
				for (DocumentoDTO documentoDTO : documentoDTOs) {
					log.info("/******** ::::: /" + documentoDTO);
					writer.print("<row id='" + documentoDTO.getDocumentoId() +"'>");					
					writer.print("<cell>"+documentoDTO.getStrFechaCreacion() + "</cell>");	
					writer.print("<cell>"+documentoDTO.getNombreDocumento() + "</cell>");	
					writer.print("</row>");
				} 
									
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			log.error(e);
		}
		
		
		return null;
		
	}

	/**
	 * Método para obtener las solicitar programarAudienciasCarpetaPreliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward programarAudienciasCarpetaPreliberacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action consulta de medidas alternas:::::");
		String numeroExpedienteId = request.getParameter("numeroExpedienteId");
		log.info("Expediente ID :::::"+numeroExpedienteId);
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(Long.parseLong(numeroExpedienteId));
		
		try {
			List<MedidaAlternaDTO> medidasAlternas = medidasAlternasDelegate.consultarMedidasAlternasPorNumeroExpediente(expedienteDTO);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
				writer.print("<records>" + medidasAlternas.size() + "</records>");
//				Número de causa
//				- Número de carpeta de ejecución
//				- Nombre
//				- Descripcion
//				- Periodicidad
				for (MedidaAlternaDTO medidaAlterna : medidasAlternas) {
					log.info("/******** ::::: /" + medidaAlterna);
					writer.print("<row id='" + medidaAlterna.getDocumentoId() +"'>");	
					writer.print("<cell>"+medidaAlterna.getInvolucrado().getNombreCompleto() + "</cell>");
					writer.print("<cell>"+medidaAlterna.getNombreDocumento() + "</cell>");
					writer.print("<cell>"+medidaAlterna.getDescripcionMedida() + "</cell>");	
					writer.print("<cell>"+medidaAlterna.getValorPeriodicidad().getValor() + "</cell>");
					writer.print("<cell>"+(medidaAlterna.getCompromisoPeriodico()==null?"-":medidaAlterna.getCompromisoPeriodico()) + "</cell>");	
					writer.print("<cell>"+medidaAlterna.getSeguimiento() + "</cell>");
					
					writer.print("</row>");
				} 
									
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (NSJPNegocioException e) {
			log.error(e);
		}
//		
		//try {
//			
			
		//	solicitudDelegate.consultarSolicitudesPorNumeroExpedienteYTipo(numeroExpedienteId, TiposSolicitudes.BENEFICIOS_PRELIBERACION);
//			List<ExpedienteDTO> numExpedientes = expedienteDelegate.consultarNumeroExpedienteByTipoYEstatus(TipoExpediente.CARPETA_EJECUCION, EstatusExpediente.ABIERTO);
//		
//			response.setContentType("text/xml; charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache");
//			PrintWriter writer = response.getWriter();
//			writer.print("<rows>");
//				writer.print("<records>" + numExpedientes.size() + "</records>");
////				Número de causa
////				- Número de carpeta de ejecución
//				for (ExpedienteDTO expedienteDTO : numExpedientes) {
//					log.info("/******** ::::: /" + expedienteDTO);
//					writer.print("<row id='" + expedienteDTO.getNumeroExpedienteId() +"'>");					
//					writer.print("<cell>"+expedienteDTO.getNumeroExpediente() + "</cell>");											
//					writer.print("</row>");
//				} 
//									
//			writer.print("</rows>");
//			writer.flush();
//			writer.close();
//			
//		} catch (Exception e) {
//			log.error(e);
//		}
		
		
		return null;
		
	}
	
	
	/**
	 * Método para obtener las solicitudes de beneficios de preeliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarSolicitudesDeBeneficiosDePreliberacionEstudios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"));
		ExpedienteDTO expediente = new ExpedienteDTO();
		expediente.setNumeroExpedienteId(numeroExpedienteId);
		
		try {
			
			List<SolicitudDTO> solicitudes =solicitudDelegate.
			consultarSolicitudesPorNumeroExpedienteYTipo(numeroExpedienteId, TiposSolicitudes.ESTUDIOS);
		
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
				writer.print("<records>" + solicitudes.size() + "</records>");
				
				for (SolicitudDTO solicitud : solicitudes) {
					writer.print("<row id='" + solicitud.getDocumentoId() +"'>");
						writer.print("<cell>"+ DateUtils.formatear(solicitud.getFechaCreacion()) + " "+
								DateUtils.formatearHora(solicitud.getFechaCreacion()) + "</cell>");
						writer.print("<cell>"+ solicitud.getEstatus().getValor() + "</cell>");
						writer.print("<cell>"+ solicitud.getTipoSolicitudDTO().getValor() + "</cell>");
					writer.print("</row>");
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			log.error(e);
		}
		
		
		return null;
		
	}
	
	
	/**
	 * Método para obtener las solicitudes de beneficios de preeliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward actualizaEstatusSolicitudAudiencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"));
		ExpedienteDTO expediente = new ExpedienteDTO();
		expediente.setNumeroExpedienteId(numeroExpedienteId);
		String documentoID = request.getParameter("documentoID");
		log.info("id documentoID...."+documentoID);
		try {
			SolicitudDTO solicitudDTO = new SolicitudDTO();
			solicitudDTO.setDocumentoId(Long.parseLong(documentoID));
			
			solicitudDelegate.actualizaEstatusSolicitud(solicitudDTO, EstatusSolicitud.EN_PROCESO);
								
			
			
			
		} catch (Exception e) {
			log.error(e);
		}
		
		
		return null;
		
	}
	
	/**
	 * Método para obtener las solicitudes de beneficios de preeliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward gridConsultaObjetos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		Long idAudiencia = NumberUtils.toLong(request.getParameter("audienciaId"));
		
		
		try {
			EventoDTO input = new EventoDTO();
			input.setId(idAudiencia);
			
			List<EvidenciaDTO> objetosAud = audienciaDelegate.consultarObjetosAudiencia(input);
								
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
				writer.print("<records>" + objetosAud.size() + "</records>");
				
				for (EvidenciaDTO evidenciaDTO : objetosAud) {
					writer.print("<row id='" + evidenciaDTO.getEvidenciaId() +"'>");
						writer.print("<cell>"+ "Otorgante" + " " + "" + "</cell>");
						writer.print("<cell>"+ evidenciaDTO.getCadenaDeCustodia()+ "</cell>");
						writer.print("<cell>"+ evidenciaDTO.getNumeroEvidencia() + "</cell>");
						writer.print("<cell>"+ evidenciaDTO.getDescripcion() + "</cell>");
						writer.print("<cell>"+ "Aceptada" + "</cell>");
					writer.print("</row>");
				}							
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			log.error(e);
		}				
		return null;
		
	}
	
	/**
	 * Método para obtener las solicitudes de beneficios de preeliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward guardaObjetosCarpetaEjecucion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		
		String campoObjeto = request.getParameter("campoObjeto");
		log.info("campoObjeto::::"+campoObjeto);
		String campoPrueba = request.getParameter("campoPrueba");
		log.info("guardar campoPrueba::"+campoPrueba);
		String campoCadena = request.getParameter("campoCadena");
		log.info("guardar campoCadena::"+campoCadena);
		String campoDescripciona = request.getParameter("campoDescripciona");
		log.info("guardar campoDescripciona::"+campoDescripciona);
		Long idAudiencia = NumberUtils.toLong(request.getParameter("idAudiencia"));
		log.info("guardar idAudiencia::"+idAudiencia);
		Long campoEstado = NumberUtils.toLong(request.getParameter("campoEstado"));
		log.info("guardar Objeto Preliberacion::"+campoEstado);
		
		try {
			EventoDTO input = new EventoDTO();
			input.setId(idAudiencia);
			
			audienciaDelegate.registrarObjetoEnAudiencia(idAudiencia, Instituciones.PJ.getValorId(), 
											campoDescripciona, campoEstado, campoCadena, NumberUtils.createLong(campoPrueba));
								
//			response.setContentType("text/xml; charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache");
//			PrintWriter writer = response.getWriter();
//			writer.print("<rows>");
//				writer.print("<records>" + objetosAud.size() + "</records>");
//				
//				for (EvidenciaDTO evidenciaDTO : objetosAud) {
//					writer.print("<row id='" + evidenciaDTO.getEvidenciaId() +"'>");
//						writer.print("<cell>"+ "Otorgante" + " " + "" + "</cell>");
//						writer.print("<cell>"+ evidenciaDTO.getCadenaDeCustodia()+ "</cell>");
//						writer.print("<cell>"+ evidenciaDTO.getNumeroEvidencia() + "</cell>");
//						writer.print("<cell>"+ evidenciaDTO.getDescripcion() + "</cell>");
//						writer.print("<cell>"+ "Aceptada" + "</cell>");
//					writer.print("</row>");
//				}							
//			writer.print("</rows>");
//			writer.flush();
//			writer.close();
			
		} catch (Exception e) {
			log.error(e);
		}				
		return null;
		
	}
	
	
	/**
	 * Método para consultar las sentencias por estatus
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarSentenciasPorEstatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
			try {
				log.info("EJECUTANDO ACTION CONSULTAR SENTENCIAS POR ESTATUS::::");
				log.info("VERIFICANDO PARAMETROS::::::::::::::::::::::::::::::::");
				log.info("estadoSentencia"+request.getParameter("estadoSentencia"));
				
				String estadoSentencia = request.getParameter("estadoSentencia");
				
				EstatusExpediente[] estadosEnum = null;
				List<ValorDTO> estatusExpediente = new ArrayList<ValorDTO>();
				
				if(estadoSentencia != null){

					String []estadoSeparados = estadoSentencia.split(",");
					estadosEnum = new EstatusExpediente[estadoSeparados.length];
					for(int i=0;i<estadoSeparados.length;i++){
						estadosEnum[i] = EstatusExpediente.getByValor(NumberUtils.toLong(estadoSeparados[i]));
						estatusExpediente.add(new ValorDTO(EstatusExpediente.getByValor(NumberUtils.toLong(estadoSeparados[i])).getValorId()));
					}
				}
				
			UsuarioDTO usuario = getUsuarioFirmado(request);
		
			List<ExpedienteDTO> listaExpedientes = carpetaEjecucionDelegate.consultarCarpetasEjecucionPorEstatus(estatusExpediente,usuario);
								
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
				writer.print("<records>" + listaExpedientes.size() + "</records>");
				
				for (ExpedienteDTO expedienteDTO : listaExpedientes) {
					writer.print("<row id='" + expedienteDTO.getNumeroExpedienteId()+"'>");
						writer.print("<cell>"+ ((expedienteDTO.getInputado()!= null && expedienteDTO.getInputado().getNombreCompleto() != null) ? expedienteDTO.getInputado().getNombreCompleto():" ") +"</cell>");
						writer.print("<cell>"+ ((expedienteDTO.getCasoDTO() != null && expedienteDTO.getCasoDTO().getNumeroGeneralCaso() != null) ? expedienteDTO.getCasoDTO().getNumeroGeneralCaso():" ") +"</cell>");
						writer.print("<cell>"+ ((expedienteDTO.getCausaPadre() != null && expedienteDTO.getCausaPadre().getNumeroExpediente() != null )? expedienteDTO.getCausaPadre().getNumeroExpediente():"") + "</cell>");
						writer.print("<cell>"+ (expedienteDTO.getNumeroExpediente() != null ? expedienteDTO.getNumeroExpediente():"")+ "</cell>");
						writer.print("<cell>"+ (expedienteDTO.getStrFechaApertura() != null ? expedienteDTO.getStrFechaApertura() :"") + "</cell>");
					writer.print("</row>");
				}							
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			log.error(e);
		}				
		return null;	
	}
	
}
