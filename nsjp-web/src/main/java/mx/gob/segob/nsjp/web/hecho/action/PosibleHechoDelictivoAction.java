/**
 * Nombre del Programa 		: PosibleHechoDelictivoAction.java
 * Autor                     : ArmandoCT
 * Compania                  : Ultrasist
 * Proyecto                  : NSJP                    Fecha: 27/julio/2011
 * Marca de cambio        	: N/A
 * Descripcion General    	: Clase Action para avisos de hechos delictivos
 * Programa Dependiente  	: N/A
 * Programa Subsecuente 		: N/A
 * Cond. de ejecucion        : N/A
 * Dias de ejecucion         : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                     : N/A
 * Compania              	: N/A
 * Proyecto                 	: N/A                                 Fecha: N/A
 * Modificacion           	: N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.web.hecho.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.actividad.ActividadDelegate;
import mx.gob.segob.nsjp.delegate.delito.DelitoDelegate;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.expediente.TurnoDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.delegate.objeto.ObjetoDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.atencionTempranaPenal.action.AtencionTempranaPenalAction;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase Action para ingresar hechos.
 * 
 * @version 1.0
 * @author ArmandoCT
 * 
 */
public class PosibleHechoDelictivoAction extends GenericAction {
	
	/* Log de clase*/
	private static final Logger LOG  = Logger.getLogger(AtencionTempranaPenalAction.class);
	
	@Autowired
	public TurnoDelegate turnoDelegate;
	
	@Autowired
	public ExpedienteDelegate expedienteDelegate;
	
	@Autowired
	public InvolucradoDelegate involucradoDelegate;
	
	@Autowired
	public DelitoDelegate delitoDelegate;
	
	@Autowired
	public ObjetoDelegate objetoDelegate;
	
	@Autowired
	public ActividadDelegate actividadDelegate;
	
	@Autowired
	public DocumentoDelegate documentoDelegate;
	
	@Autowired
	public SolicitudDelegate solicitudDelegate;
	
	/***
	 * Metodo para mandar consultar los avisos de posibles hechos delictivos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarAvisosPosiblesHechosDlctvs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			try {
				LOG.info("ejecutando Action PosibleHechoDelictivoAction en metodo consultarAvisosPosiblesHechosDlctvs:#####"+expedienteDelegate);
				String estatusAviso=request.getParameter("estatus");
				
				//recuperamos los aviso de posbiles hechos delictivos no atendidos
				List<AvisoHechoDelictivoDTO> listaAvisosNuevos=new ArrayList<AvisoHechoDelictivoDTO>();
				
				if(estatusAviso.equals("NO_ATENDIDA"))
				{//AVISOS NO ATENDIDOS
					listaAvisosNuevos = documentoDelegate.consultarAvisosHDelictivoPorEstatus(EstatusNotificacion.NO_ATENDIDA.getValorId());
				}
				else
				{//AVISOS YA ATENDIDOS
					listaAvisosNuevos = documentoDelegate.consultarAvisosHDelictivoPorEstatus(EstatusNotificacion.ATENDIDA.getValorId());
				}
				
				if (LOG.isDebugEnabled()) {
					LOG.debug("##################lista de Posibles Hechos Delictivos :::::::::" + listaAvisosNuevos.size());
				}
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=listaAvisosNuevos.size();
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (AvisoHechoDelictivoDTO avisoPosHechoDelDTO : listaAvisosNuevos) {
					writer.print("<row id='"+avisoPosHechoDelDTO.getDocumentoId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+avisoPosHechoDelDTO.getHechoDTO().getLugar().getDescripcion()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+avisoPosHechoDelDTO.getCatDelito().getNombre()+" </div]]></cell>");
					if(avisoPosHechoDelDTO.getFechaAtencion()==null)
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'></div]]></cell>");
					}
					else
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+DateUtils.formatear(avisoPosHechoDelDTO.getFechaAtencion())+
								" " + DateUtils.formatearHora(avisoPosHechoDelDTO.getFechaAtencion()) +" </div]]></cell>");
					}
					writer.print("</row>");	
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
		
		} catch (Exception e) {		
			LOG.info(e.getCause(),e);
		}
		return null;
	}
	
	
	/***
	 * Metodo para mandar consultar los avisos de posibles hechos delictivos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward actualizarAvisoPosibleHechoDelictivo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			LOG.info("ejecutando Action PosibleHechoDelictivoAction en metodo actualizarAvisoPosibleHechoDelictivo");
			try {
				
				String estatusAviso=request.getParameter("estatus");
				String idNotificacion=request.getParameter("idAviso");
				
				NotificacionDTO notificacionDTO=new NotificacionDTO();
				notificacionDTO.setDocumentoId(Long.parseLong(idNotificacion));
				
				if(estatusAviso.equals("NO_ATENDIDA"))
				{//AVISOS NO ATENDIDOS
					notificacionDTO=documentoDelegate.actualizarEstatusNotificacion(notificacionDTO,EstatusNotificacion.ATENDIDA.getValorId());
				}
				else
				{//AVISOS YA ATENDIDOS
					notificacionDTO=documentoDelegate.actualizarEstatusNotificacion(notificacionDTO, EstatusNotificacion.NO_ATENDIDA.getValorId());
				}
				
				if(notificacionDTO!=null && notificacionDTO.getDocumentoId()!=null)
				{
					converter.alias("notificacionDTO", NotificacionDTO.class);
					String xml = converter.toXML(notificacionDTO);
					LOG.info(xml);
					escribirRespuesta(response, xml);
				}
				else
				{
					notificacionDTO.setDocumentoId(0L);
					converter.alias("notificacionDTO", NotificacionDTO.class);
					String xml = converter.toXML(notificacionDTO);
					escribirRespuesta(response, xml);
				}
		} catch (Exception e) {		
			LOG.info(e.getCause(),e);
		}
		return null;
	}
	
	/***
	 * Metodo para mandar consultar los avisos de posibles hechos delictivos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaPosibleHechoDelictivoPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			LOG.info("ejecutando Action PosibleHechoDelictivoAction en metodo consultaPosibleHechoDelictivoPorId");
			try {
								
				String idNotificacion=request.getParameter("idAviso");
				LOG.info("ejecutando Action PosibleHechoDelictivoAction en metodo consultarAvisosPosiblesHechosDlctvs:#####"+expedienteDelegate);
				
				if (LOG.isDebugEnabled()) {
					LOG.debug("##################Consulta aviso de posible hecho delictivo por Id :::::::::");
				}
			
				AvisoHechoDelictivoDTO avisoHechoDelictivoDTO= new AvisoHechoDelictivoDTO();
				avisoHechoDelictivoDTO.setDocumentoId(Long.parseLong(idNotificacion));
				avisoHechoDelictivoDTO = documentoDelegate.consultarAvisoHDelictivo(avisoHechoDelictivoDTO);
				
				//obtenemos el domicilio consultado
				DomicilioDTO domicilioDTO=avisoHechoDelictivoDTO.getHechoDTO().getDomicilio();
				
				if(domicilioDTO!=null && domicilioDTO.getElementoId()!=null)
				{
					converter.alias("domicilioDTO", DomicilioDTO.class);
					String xml = converter.toXML(domicilioDTO);
					if(LOG.isDebugEnabled())
					{
						LOG.debug(xml);	
					}
					escribirRespuesta(response, xml);
				}
				else
				{
					domicilioDTO.setElementoId(0L);
					converter.alias("domicilioDTO", DomicilioDTO.class);
					String xml = converter.toXML(domicilioDTO);
					escribirRespuesta(response, xml);
				}
		} catch (Exception e) {		
			LOG.info(e.getCause(),e);
		}
		return null;
	}
	
	
	/***
	 * Metodo para mandar consultar las solicitudes por atender con cierto estatus,tipo y area
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaTiposSolsXArea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			try {
				LOG.info("ejecutando Action PosibleHechoDelictivoAction en metodo consultaTiposSolsXArea:#####");
				UsuarioDTO user = super.getUsuarioFirmado(request);
				FuncionarioDTO funcionario=user.getFuncionario();
				LOG.info("area_fun_tipo_sol::"+funcionario.getJerarquiaOrganizacional().getJerarquiaOrganizacionalId());
				
				//recuperamos los tipos de solicitud
				//List<ValorDTO> listaTipos=solicitudDelegate.consultarTipoSolictudesPorJerarquiaOrganizacional(funcionario.getJerarquiaOrganizacional().getJerarquiaOrganizacionalId());
				Long areaSolicitudId = funcionario.getJerarquiaOrganizacional().getJerarquiaOrganizacionalId();
				if(areaSolicitudId==null || areaSolicitudId==0)
					areaSolicitudId  = NumberUtils.toLong(request.getParameter("idArea"), 0);
				
				List<ValorDTO> listaTipos=solicitudDelegate.consultarTipoSolictudesPorJerarquiaOrganizacional(areaSolicitudId);
				
				converter.alias("ListaTipos", java.util.List.class);
				converter.alias("ValorDTO", ValorDTO.class);
				String xml = converter.toXML(listaTipos);
				if(LOG.isDebugEnabled())
				{
					LOG.debug("listaTipos:: "+xml);	
				}
				escribirRespuesta(response, xml);
				
		} catch (Exception e) {		
			LOG.info(e.getCause(),e);
			escribirRespuesta(response, "");
		}
		return null;
	}
	
	/***
	 * Metodo para consultar el detalle de una solicitud
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaDetalleSolicitudXAtender(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			LOG.info("ejecutando Action PosibleHechoDelictivoAction en metodo consultaDetalleSolicitudXAtender");
			try {
								
				String idSolicitud=request.getParameter("idSolicitud");
				
				if (LOG.isDebugEnabled()) {
					LOG.debug("##################Consulta detalle de solicitud:::::::::");
				}
			
				SolicitudDTO solicitudDTO= new SolicitudDTO();
				solicitudDTO.setDocumentoId(Long.parseLong(idSolicitud));
				
				//consultamos la solicitud
				solicitudDTO=solicitudDelegate.obtenerDetalleSolicitud(solicitudDTO);
				
				if(solicitudDTO!=null)
				{
					converter.alias("SolicitudDTO", SolicitudDTO.class);
					String xml = converter.toXML(solicitudDTO);
					if(LOG.isDebugEnabled())
					{
						LOG.debug(xml);	
					}
					escribirRespuesta(response, xml);
				}
				else
				{
					solicitudDTO=new SolicitudDTO();
					solicitudDTO.setDocumentoId(0L);
					converter.alias("SolicitudDTO", SolicitudDTO.class);
					String xml = converter.toXML(solicitudDTO);
					escribirRespuesta(response, xml);
				}
		} catch (Exception e) {		
			LOG.info(e.getCause(),e);
		}
		return null;
	}
	
	/***
	 * Metodo para consultar el detalle de una solicitud
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaDetalleSolicitudGenerada(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			LOG.info("ejecutando Action PosibleHechoDelictivoAction en metodo consultaDetalleSolicitudXAtender");
			try {
								
				String idSolicitud=request.getParameter("idSolicitud");
				
				if (LOG.isDebugEnabled()) {
					LOG.debug("##################Consulta detalle de solicitud:::::::::");
				}
			
				SolicitudDTO solicitudDTO= new SolicitudDTO();
				solicitudDTO.setDocumentoId(Long.parseLong(idSolicitud));
				
				//consultamos la solicitud
				solicitudDTO=solicitudDelegate.obtenerDetalleSolicitud(solicitudDTO);
				
				if(solicitudDTO!=null)
				{
					converter.alias("SolicitudDTO", SolicitudDTO.class);
					String xml = converter.toXML(solicitudDTO);
					if(LOG.isDebugEnabled())
					{
						LOG.debug(xml);	
					}
					escribirRespuesta(response, xml);
				}
				else
				{
					solicitudDTO=new SolicitudDTO();
					solicitudDTO.setDocumentoId(0L);
					converter.alias("SolicitudDTO", SolicitudDTO.class);
					String xml = converter.toXML(solicitudDTO);
					escribirRespuesta(response, xml);
				}
		} catch (Exception e) {		
			LOG.info(e.getCause(),e);
		}
		return null;
	}
	
	/***
	 * Metodo para mandar consultar las solicitudes por atender con cierto estatus,tipo y area
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaSolicitudesXAtender(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			try {
				LOG.info("ejecutando Action PosibleHechoDelictivoAction en metodo consultaSolicitudesXAtenderUI:#####");
				String tipoSolicitud=request.getParameter("tipoSoliciutd");
				String estatusSolicitud=request.getParameter("estatus");
				String areaSolicitud=request.getParameter("idArea");
				//construimos la lista de los tipos de solicitud a consultar
				String[] idTiposols=tipoSolicitud.split(",");
				LOG.info("idsTipoSols:: "+tipoSolicitud);
				List<Long> idsTipSols=new ArrayList<Long>();LOG.info("arrIDsTipoSols:: "+idTiposols);
				for (String long1 : idTiposols) {
					idsTipSols.add(Long.parseLong(long1));
				}
				
				//construimos la lista de los estatus a consultar
				String[] idEstatus=estatusSolicitud.split(",");
				List<Long> idsEstatus=new ArrayList<Long>();LOG.info("arrIDsEstatus:: "+idEstatus);
				for (String long1 : idEstatus) {
					idsEstatus.add(Long.parseLong(long1));
				}
				//consultamos las solicitudes por atender
				UsuarioDTO loUsuario = super.getUsuarioFirmado(request);
				Long claveAgencia = null;
				if(loUsuario.getFuncionario() != null && loUsuario.getFuncionario().getDiscriminante() != null  && loUsuario.getFuncionario().getDiscriminante().getCatDiscriminanteId() != null)
					claveAgencia = loUsuario.getFuncionario().getDiscriminante().getCatDiscriminanteId();
			    List<SolicitudDTO> listaSolicitudes= solicitudDelegate.consultarSolicitudesParaAtender(idsEstatus,idsTipSols, loUsuario.getAreaActual().getAreaId(), loUsuario.getFuncionario().getClaveFuncionario(),claveAgencia);
			    converter.alias("ListaSols", java.util.List.class);
				converter.alias("SolicitudDTO", SolicitudDTO.class);
				String xml = converter.toXML(listaSolicitudes);
				if(LOG.isDebugEnabled())
				{
					LOG.debug("listaSOLS:: "+xml);	
				}
				//generamos el HTML del grid
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				
				final PaginacionDTO pag = PaginacionThreadHolder.get();
				
                if (pag != null && pag.getTotalRegistros() != null) {
                    writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                    writer.print("<records>" + pag.getTotalRegistros() + "</records>");
                } else {
                    writer.print("<total>0</total>");
                    writer.print("<records>0</records>");
                }
				
				for (SolicitudDTO solicitudDTO : listaSolicitudes) {
					writer.print("<row id='"+solicitudDTO.getDocumentoId()+"'>");
					
					if(solicitudDTO.getExpedienteDTO()==null || solicitudDTO.getExpedienteDTO().getCasoDTO()==null || solicitudDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso()==null)
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
					}
					else
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso()+" </div]]></cell>");
					}
					
					if(solicitudDTO.getExpedienteDTO()==null)
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
					}
					else
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getExpedienteDTO().getNumeroExpediente()+" </div]]></cell>");
					}
					
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getFolioSolicitud()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getEstatus().getValor()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+DateUtils.formatear(solicitudDTO.getFechaCreacion())+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+DateUtils.formatear(solicitudDTO.getFechaLimite())+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+(solicitudDTO.getInstitucion() != null && solicitudDTO.getInstitucion().getNombreInst() != null? solicitudDTO.getInstitucion().getNombreInst(): "-")+" </div]]></cell>");
					if(solicitudDTO.getDestinatario()==null)
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
					}
					else
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getDestinatario().getNombreCompleto()+" </div]]></cell>");
					}
					if(solicitudDTO.getExpedienteDTO()==null)
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
					}
					else
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getExpedienteDTO().getExpedienteId()+"</div]]></cell>");
					}
					writer.print("</row>");	
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
		
		} catch (Exception e) {		
			LOG.info(e.getCause(),e);
		}
		return null;
	}
	
	/***
	 * Metodo para mandar consultar las solicitudes generadas con cierto estatus,tipo y area
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaSolsGeneradas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			try {
				LOG.info("ejecutando Action PosibleHechoDelictivoAction en metodo consultaSolsGeneradas:#####");
				String tipoSolicitud=request.getParameter("tipoSoliciutd");
				String estatusSolicitud=request.getParameter("estatus");
				String areaSolicitud=request.getParameter("idArea");
				LOG.info("sols_gen tipoSol:: "+tipoSolicitud);
				LOG.info("sols_gen statusSol:: "+estatusSolicitud);
				LOG.info("sols_gen areaSol:: "+super.getUsuarioFirmado(request).getAreaActual().getAreaId());
				LOG.info("sols_gen cveFunc:: "+super.getUsuarioFirmado(request).getFuncionario().getClaveFuncionario());
				//construimos la lista de los tipos de solicitud a consultar
				String[] idTiposols=tipoSolicitud.split(",");
				List<Long> idsTipSols=new ArrayList<Long>();LOG.info("arrIDsTipoSols:: "+idTiposols);
				for (String long1 : idTiposols) {
					idsTipSols.add(Long.parseLong(long1));
				}
				
				//construimos la lista de los estatus a consultar
				String[] idEstatus=estatusSolicitud.split(",");
				List<Long> idsEstatus=new ArrayList<Long>();LOG.info("arrIDsEstatus:: "+idEstatus);
				for (String long1 : idEstatus) {
					idsEstatus.add(Long.parseLong(long1));
				}
				//consultamos las solicitudes por atender
			    List<SolicitudDTO> listaSolicitudes= solicitudDelegate.consultarSolicitudesGeneradas(idsEstatus,idsTipSols, super.getUsuarioFirmado(request).getAreaActual().getAreaId(), super.getUsuarioFirmado(request).getFuncionario().getClaveFuncionario());
			    converter.alias("ListaSols", java.util.List.class);
				converter.alias("SolicitudDTO", SolicitudDTO.class);
				String xml = converter.toXML(listaSolicitudes);
				if(LOG.isDebugEnabled())
				{
					LOG.debug("listaSOLS:: "+xml);	
				}
				
				//generamos el HTML del grid
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				
				final PaginacionDTO pag = PaginacionThreadHolder.get();
				
                if (pag != null && pag.getTotalRegistros() != null) {
                    writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                    writer.print("<records>" + pag.getTotalRegistros() + "</records>");
                } else {
                    writer.print("<total>0</total>");
                    writer.print("<records>0</records>");
                }
                
				for (SolicitudDTO solicitudDTO : listaSolicitudes) {
					writer.print("<row id='" + solicitudDTO.getDocumentoId() + "'>");
	
					if (solicitudDTO.getNumeroCasoAsociado() == null) {
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
					} else {
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
								+ solicitudDTO.getNumeroCasoAsociado()
								+ " </div]]></cell>");
					}
	
					if (solicitudDTO.getExpedienteDTO() == null) {
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
					} else {
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
								+ solicitudDTO.getExpedienteDTO()
										.getNumeroExpediente() + " </div]]></cell>");
					}
	
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
							+ solicitudDTO.getFolioSolicitud() + " </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
							+ solicitudDTO.getEstatus().getValor()
							+ " </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
							+ DateUtils.formatear(solicitudDTO.getFechaCreacion())
							+ " </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
							+ DateUtils.formatear(solicitudDTO.getFechaLimite())
							+ " </div]]></cell>");
					if (solicitudDTO.getInstitucion() != null) {
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
								+ solicitudDTO.getInstitucion().getNombreInst()
								+ " </div]]></cell>");
					} else {
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
								+ "Procuraduria" + " </div]]></cell>");
					}
	
					if (solicitudDTO.getDestinatario() == null) {
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
					} else {
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
								+ solicitudDTO.getDestinatario()
										.getNombreCompleto() + " </div]]></cell>");
					}
	
					writer.print("</row>");
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
		
		} catch (Exception e) {		
			LOG.info(e.getCause(),e);
		}
		return null;
	}
}
