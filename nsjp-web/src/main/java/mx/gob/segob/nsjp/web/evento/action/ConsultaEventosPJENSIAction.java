
package mx.gob.segob.nsjp.web.evento.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.expediente.TipoExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.web.base.action.ReporteBaseAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author AlejandroGA
 *
 */
public class ConsultaEventosPJENSIAction extends ReporteBaseAction{

	/**Log de clase*/
	private static final Logger log  = Logger.getLogger(ConsultaEventosPJENSIAction.class);
	
	@Autowired
	public SolicitudDelegate solicitudDelegate;
	
	@Autowired
	public ExpedienteDelegate expedienteDelegate;
	
	@Autowired
	public DocumentoDelegate documentoDelegate;
				
	/**
	 * Metodo utilizado para consultar las solicitudes de recursos
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultaSolicitudesRecursos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO CONSULTA DE SOLICITUDES DE RECURSO");
			
			List<SolicitudDTO> solicitudDTOs = new ArrayList<SolicitudDTO>();
			
			solicitudDTOs = (List<SolicitudDTO>) solicitudDelegate.consultarSolicitudXEstatus(EstatusSolicitud.ABIERTA, super.getUsuarioFirmado(request), TiposSolicitudes.RECURSO_APELACION);
						
			log.info("LISTA DE RECURSOS"+ solicitudDTOs);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");

			int lTotalRegistros = solicitudDTOs.size();

			writer.print("<records>" + lTotalRegistros + "</records>");
			for (SolicitudDTO solicitudDTO : solicitudDTOs) {

				log.info("FOR EACH" + solicitudDTO);

				writer.print("<row id='"+ solicitudDTO.getDocumentoId()+ "'>");
				writer.print("<cell>"+ solicitudDTO.getExpedienteDTO().getNumeroExpediente() + "</cell>");
				writer.print("<cell>"+ solicitudDTO.getNombreSolicitante() + "</cell>");
				writer.print("<cell>"+ solicitudDTO.getStrFechaCreacion()+" - "+solicitudDTO.getStrHoraCreacion() + "</cell>");
				writer.print("<cell>"+ solicitudDTO.getNombreInstitucionSolicitante()+ "</cell>");
				writer.print("<cell>"+ solicitudDTO.getNombreDocumento() + "</cell>");				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
						
		} catch (Exception e) {		
			log.info("ERROR AL CONSULTAR LAS LEYES");
			log.info(e.getCause(),e);
			escribir(response, "ERROR AL CONSULTAR LAS LEYES",null);
			
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para realizar el paso del parametro id del evento
	 * a la JSP de atencionSolicitudAudienciaNotificador
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward visorRecursoPJENSI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando action visor recurso PJENSI");
			
			String idDocumento = request.getParameter("idDocumento");
			
			log.info("id documento:::"+ idDocumento);
			
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return mapping.findForward("succes");
	}
	
	/**
	 * Metodo utilizado para realizar del detalle de eventos
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward detalleSolicitudRecurso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO CONSULTA DETALLE SOLICITUD RECURSO");
			
			//Se obtiene el id del evento a consultar a detalle
			String idDocumento = request.getParameter("idDocumento");
						
			log.info("idDocumento::::::::::"+ idDocumento);
						
			SolicitudDTO solicitudDTO = new SolicitudDTO(); 
			solicitudDTO.setDocumentoId(Long.parseLong(idDocumento));
								
			solicitudDTO = solicitudDelegate.obtenerDetalleSolicitud(solicitudDTO);
			
			log.info("depues del delegate::: solicitudDTO"+ solicitudDTO);
			
			converter.alias("solicitudDTO", SolicitudDTO.class);
			String xml = converter.toXML(solicitudDTO);
			escribir(response, xml,null);
				
			
		} catch (Exception e) {		
			log.info("ERROR AL CONSULTAR LA SOLICITUD");
			log.info(e.getCause(),e);
			escribir(response, "consultaDetalleNotificaciones",null);
			
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para realizar del detalle de eventos
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward asignarNumeroExpedienteTOCA(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO ASIGNAR NUMERO EXPEDIENTE-TOCA");
			
			//Se obtiene el id del expediente
			String expedienteId = request.getParameter("expedienteId");
						
			log.info("expedienteId::::::::::"+ expedienteId);
			
			//Se obtiene el numero de expediente id
			String numeroExpedienteId = request.getParameter("numeroExpedienteId");
						
			log.info("numeroExpedienteId::::::::::"+ numeroExpedienteId);
						
			
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			expedienteDTO.setUsuario(super.getUsuarioFirmado(request));
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PJ));
			expedienteDTO.setExpedienteId(Long.parseLong(expedienteId));
			ExpedienteDTO causa = new ExpedienteDTO();
			causa.setNumeroExpedienteId(Long.parseLong(numeroExpedienteId));
			expedienteDTO.setCausaPadre(causa);
			expedienteDTO.setFechaApertura(new Date());
			expedienteDTO.setTipoExpediente(new ValorDTO(TipoExpediente.TOCA.getValorId()));
								
			expedienteDTO = expedienteDelegate.asignarNumeroExpediente(expedienteDTO);
			
			log.info("depues del delegate::: solicitudDTO"+ expedienteDTO);
			
			converter.alias("expedienteDTO", ExpedienteDTO.class);
			String xml = converter.toXML(expedienteDTO);
			escribir(response, xml,null);
				
			
		} catch (Exception e) {		
			log.info("ERROR AL CONSULTAR LA SOLICITUD");
			log.info(e.getCause(),e);
			escribir(response, "consultaDetalleNotificaciones",null);
			
		}
		return null;
	}
		
	/**
	 * Metodo utilizado para consultar las solicitudes de recursos por expediente almacenadas en historico
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultaSolicitudesRecursoHistorico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("Muestra Documentos de medidas Cautelares");
			
			
			String numeroExpedienteId = request.getParameter("numeroExpedienteId");
			
			log.info("numero expediente id" + numeroExpedienteId);
			
			List<SolicitudDTO> solicitudDTOs = new ArrayList<SolicitudDTO>();
			
			solicitudDTOs = solicitudDelegate.consultarSolicitudesPorNumeroExpedienteYTipo(Long.parseLong(numeroExpedienteId), TiposSolicitudes.RECURSO_APELACION);
						
			log.info("LISTA DE RECURSOS HISTORICO"+ solicitudDTOs);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");

			int lTotalRegistros = solicitudDTOs.size();

			writer.print("<records>" + lTotalRegistros + "</records>");
			for (SolicitudDTO solicitudDTO : solicitudDTOs) {

				log.info("FOR EACH" + solicitudDTO);

				writer.print("<row id='"+ solicitudDTO.getDocumentoId()+ "'>");
				writer.print("<cell>"+ solicitudDTO.getExpedienteDTO().getNumeroExpediente() + "</cell>");
				writer.print("<cell>"+ solicitudDTO.getExpedienteDTO().getNumeroExpediente() + "</cell>");
				writer.print("<cell>"+ solicitudDTO.getStrFechaCreacion()+" - "+solicitudDTO.getStrHoraCreacion() + "</cell>");
				writer.print("<cell>"+ solicitudDTO.getNombreInstitucionSolicitante()+ "</cell>");
				writer.print("<cell>"+ solicitudDTO.getNombreSolicitanteExternoInterno() + "</cell>");
				writer.print("<cell>"+ solicitudDTO.getFechaLimite() + "</cell>");
				writer.print("<cell>"+ solicitudDTO.getEstatus().getValor() + "</cell>");
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
						
		} catch (Exception e) {		
			
			log.info(e.getCause(),e);
			
			
		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para consultar las solicitudes de recursos por expediente almacenadas en historico
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultaGridDocumentosMedidasCautelares(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO ACTION consultaGridDocumentosMedidasCautelares ");
			log.info("***********VERIFICANDO PARAMETROS");
			
			String numeroExpedienteId = request.getParameter("numexpedienteid");
			log.info("numero expediente id para documento" + numeroExpedienteId);
			
			if( numeroExpedienteId != null && !numeroExpedienteId.equals("")){
				
				ExpedienteDTO expediente = expedienteDelegate.obtenerExpedientePorNumeroExpediente(numeroExpedienteId);
				List<DocumentoDTO> documentoDTOs = new ArrayList<DocumentoDTO>();
				documentoDTOs =documentoDelegate.consultarDocumentosPorNumeroExpediente(expediente);
							
				log.info("LISTA DE RECURSOS documentoDTOs"+ documentoDTOs);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();

				writer.print("<rows>");

				final PaginacionDTO pag = PaginacionThreadHolder.get();
				log.debug("pag :: " + pag);
				if (pag != null && pag.getTotalRegistros() != null) {
					writer.print("<total>" + pag.getTotalPaginas() + "</total>");
					writer.print("<records>" + pag.getTotalRegistros() + "</records>");
				} else {
					writer.print("<total>0</total>");
					writer.print("<records>0</records>");
				}
				
//				writer.print("<records>" + lTotalRegistros + "</records>");
				for (DocumentoDTO documentoDTO : documentoDTOs) {

					log.info("FOR EACH" + documentoDTOs);

					writer.print("<row id='"+ documentoDTO.getDocumentoId()+ "'>");
					writer.print("<cell>"+ documentoDTO.getStrFechaCreacion() + "</cell>");
					writer.print("<cell>"+ documentoDTO.getNombreDocumento() + "</cell>");
					
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
			}						
		} catch (Exception e) {		
			
			log.info(e.getCause(),e);
		
			
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consulta Documentos Medidas Cautelares
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultaDocumentosMedidasCautelares(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("documentos para medidas cautelares");
			
			
			String numeroExpedienteId = request.getParameter("numeroExpedienteId");
			
			log.info("numero expediente id" + numeroExpedienteId);
			MedidaDTO medidaDTO = new MedidaDTO();
			List<MedidaDTO> medidaDTOs = new ArrayList<MedidaDTO>();
			documentoDelegate.consultarArchivoDigitalPorMedida(medidaDTO);
			
			
						
			log.info("LISTA DE RECURSOS HISTORICO"+ medidaDTOs);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");

			int lTotalRegistros = medidaDTOs.size();
			
			writer.print("<records>" + lTotalRegistros + "</records>");
			for (MedidaDTO medidaDTO2 : medidaDTOs) {

				log.info("FOR EACH" + medidaDTO);

				writer.print("<row id='"+ medidaDTO2.getDocumentoId()+ "'>");
				writer.print("<cell>"+ medidaDTO2.getStrFechaCreacion() + "</cell>");
				writer.print("<cell>"+ medidaDTO2.getNombreDocumento() + "</cell>");
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
						
		} catch (Exception e) {		
			
			log.info(e.getCause(),e);
			
			
		}
		return null;
	}
}
