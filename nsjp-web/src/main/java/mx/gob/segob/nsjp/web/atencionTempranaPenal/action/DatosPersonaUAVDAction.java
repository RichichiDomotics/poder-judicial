package mx.gob.segob.nsjp.web.atencionTempranaPenal.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.sesion.TipoSesion;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.sesion.SesionDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;
import mx.gob.segob.nsjp.dto.sesion.NotaEvolucionDTO;
import mx.gob.segob.nsjp.dto.sesion.SesionDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class DatosPersonaUAVDAction extends GenericAction{
	/* Log de clase*/
	private static final Logger log  = Logger.getLogger(DatosPersonaUAVDAction.class);
	
	@Autowired
	private SolicitudDelegate solicitudDelegate;
	@Autowired
	private ExpedienteDelegate expedienteDelegate;
	@Autowired
	private SesionDelegate sesionDelegate;
	
	public ActionForward redireccionaDatosUAVD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String idInvolucrado = request.getParameter("idDatoPersona");
		String pantalla=request.getParameter("pantalla");
		String idNumeroExpediente=request.getParameter("idNumeroExpediente");
		log.info("idDatoPersona UAVD :: [" + idInvolucrado+"]");
		log.info("pantalla UAVD :: [" + pantalla+"]");
		ExpedienteDTO expedienteDTO=new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
		try {
			Long idExpe=expedienteDelegate.obtenerExpedienteIdPorNumExpId(expedienteDTO);
			expedienteDTO.setExpedienteId(idExpe);
			expedienteDTO=expedienteDelegate.obtenerExpediente(expedienteDTO);
			super.setExpedienteTrabajo(request, expedienteDTO);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		request.setAttribute("pantalla", pantalla);
		request.setAttribute("idNumeroExpediente", idNumeroExpediente);
		if (idInvolucrado.equalsIgnoreCase("1")) {
			return mapping.findForward("succes");
		}else{ 
			return mapping.findForward("succesDos");
		}
	}
	
	/***
	 * Funcion para cambiar de status la solicitud de uavd
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward modificaSolicitudUAVD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			try {
				log.info("ejecutando Action DatosPersonaUAVDAction en metodo modificaSolicitudUAVD");
				String idSolicitud=request.getParameter("idSolicitud");
				log.info("id solicitud para modificar el estatus: "+idSolicitud);
				SolicitudDTO solicitud=new SolicitudDTO();
				if(idSolicitud!=null && !idSolicitud.equals("")){
					solicitud.setDocumentoId(Long.parseLong(idSolicitud));
				}
				solicitudDelegate.actualizaEstatusSolicitud(solicitud, EstatusSolicitud.EN_PROCESO);
				if(log.isDebugEnabled())
				{
					log.info("id solicitud modificado:: "+idSolicitud);
				}
				escribirRespuesta(response, "");
			} catch (Exception e) {		
				log.error(e.getMessage(),e);
			}
			return null;
	}
	
	/***
	 * Funcion para cambiar de status la solicitud de uavd a CERRADA
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward cerrarSolicitudUAVD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			try {
				log.info("ejecutando Action DatosPersonaUAVDAction en metodo modificaSolicitudUAVD");
				String idSolicitud=request.getParameter("idSolicitud");
				log.info("id solicitud para cerrar el estatus: "+idSolicitud);
				SolicitudDTO solicitud=new SolicitudDTO();
				if(idSolicitud!=null && !idSolicitud.equals("")){
					solicitud.setDocumentoId(Long.parseLong(idSolicitud));
				}
				solicitudDelegate.actualizaEstatusSolicitud(solicitud, EstatusSolicitud.CERRADA);
				if(log.isDebugEnabled())
				{
					log.info("id solicitud cerrada:: "+idSolicitud);
				}
				escribirRespuesta(response, "");
			} catch (Exception e) {		
				log.error(e.getMessage(),e);
			}
			return null;
	}
	
	

	/**
	 * funcion para crear sesion y mostrar los datos de ella en uavd
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarSesionesUAVD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			log.info("$$$$ Metodo consultarSesionesUAVD  ");
			String idNumeroExpediente=request.getParameter("idNumeroExpediente");
			log.info("$$$$ Metodo consultarSesionesUAVD :: idNumeroExpediente :"+idNumeroExpediente);
			
			UsuarioDTO usuarioDTO=super.getUsuarioFirmado(request);
			
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			if(idNumeroExpediente!=null && !idNumeroExpediente.equals("")){
				expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
			}
			
			
			List<SesionDTO> lisSesionDTOs=sesionDelegate.consultarSesionesPorIdNumeroExpediente(expedienteDTO);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			if(lisSesionDTOs!=null && lisSesionDTOs.size()!=0){
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
				
				
				for (SesionDTO sesionDTO : lisSesionDTOs) {
					log.info("$$$$ Metodo consultarSesionesUAVD  existente :"+sesionDTO);
					
					if(sesionDTO.getTipoSesion().getIdCampo().equals(TipoSesion.ENTREVISTA_INICIAL.getValorId())){
						writer.print("<row id='"+"1,"+sesionDTO.getSesionId()+"'>");
						EntrevistaInicialDTO entrevistaInicialDTO=new EntrevistaInicialDTO();
						entrevistaInicialDTO.setSesionId(sesionDTO.getSesionId());
						entrevistaInicialDTO=sesionDelegate.consultarEntrevistaInicialPorId(entrevistaInicialDTO);
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+entrevistaInicialDTO.getNumeroSesion()+" </div>]]></cell>");
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+sesionDTO.getTipoSesion().getValor() +" </div>]]></cell>");
						String fecha=DateUtils.formatear(entrevistaInicialDTO.getFechaSesion());
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+fecha +" </div>]]></cell>");
						if(entrevistaInicialDTO.getEsPresente())
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Si"+" </div>]]></cell>");
						else
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"No"+" </div>]]></cell>");
					}else if(sesionDTO.getTipoSesion().getIdCampo().equals(TipoSesion.ENTREVISTA_COMPLEMENTARIA.getValorId())){
						writer.print("<row id='"+"2,"+sesionDTO.getSesionId()+"'>");
						EntrevistaComplementariaDTO entrevistaComplementariaDTO=new EntrevistaComplementariaDTO();
						entrevistaComplementariaDTO.setSesionId(sesionDTO.getSesionId());
						entrevistaComplementariaDTO=sesionDelegate.consultarEntrevistaComplementariaPorId(entrevistaComplementariaDTO);
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+entrevistaComplementariaDTO.getNumeroSesion()+" </div>]]></cell>");
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+sesionDTO.getTipoSesion().getValor()+" </div>]]></cell>");
						String fecha=DateUtils.formatear(entrevistaComplementariaDTO.getFechaSesion());
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+fecha +" </div>]]></cell>");
						if(entrevistaComplementariaDTO.getEsPresente())
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Si"+" </div>]]></cell>");
						else
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"No"+" </div>]]></cell>");
					}else if(sesionDTO.getTipoSesion().getIdCampo().equals(TipoSesion.NOTAS_EVOLUCION.getValorId())){
						writer.print("<row id='"+"3,"+sesionDTO.getSesionId()+"'>");
						NotaEvolucionDTO notaEvolucionDTO=new NotaEvolucionDTO();
						notaEvolucionDTO.setSesionId(sesionDTO.getSesionId());
						notaEvolucionDTO=sesionDelegate.consultarNotaEvolucionPorId(notaEvolucionDTO);
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+notaEvolucionDTO.getNumeroSesion()+" </div>]]></cell>");
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+sesionDTO.getTipoSesion().getValor() +" </div>]]></cell>");
						String fecha=DateUtils.formatear(notaEvolucionDTO.getFechaSesion());
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+fecha +" </div>]]></cell>");
						if(notaEvolucionDTO.getEsPresente())
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Si"+" </div>]]></cell>");
						else
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"No"+" </div>]]></cell>");
					}
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
				
			}else{
				writer.print("<rows>");
				int lTotalRegistros=3;
				writer.print("<records>" + lTotalRegistros + "</records>");
					
					EntrevistaInicialDTO entrevistaInicialDTO=new EntrevistaInicialDTO();
					entrevistaInicialDTO.setNumeroExpediente(expedienteDTO);
					entrevistaInicialDTO.setEsPresente(false);
					entrevistaInicialDTO.setEsVictimaDirecta(true);
//					entrevistaInicialDTO.setFechaSesion(new Date());
					entrevistaInicialDTO.setNumeroSesion((short)1);
					entrevistaInicialDTO.setTipoSesion(new ValorDTO(TipoSesion.ENTREVISTA_INICIAL.getValorId(), TipoSesion.ENTREVISTA_INICIAL.toString(), TipoSesion.ENTREVISTA_INICIAL.name()));
					entrevistaInicialDTO.setUsuario(super.getUsuarioFirmado(request));
					entrevistaInicialDTO=sesionDelegate.guardarEntrevistaInicial(entrevistaInicialDTO);
					log.info("$$$$ Metodo consultarSesionesUAVD  nuevo entrevistaInicialDTO :"+entrevistaInicialDTO);
					log.info("$$$$ Metodo consultarSesionesUAVD  nuevo entrevistaInicialDTO :"+entrevistaInicialDTO.getSesionId());
					writer.print("<row id='"+"1,"+entrevistaInicialDTO.getSesionId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+entrevistaInicialDTO.getNumeroSesion()+" </div>]]></cell>");
					log.info("$$$$ Metodo consultarSesionesUAVD  nuevo entrevistaInicialDTO :"+entrevistaInicialDTO.getTipoSesion());
					if(entrevistaInicialDTO.getTipoSesion()!=null && entrevistaInicialDTO.getTipoSesion().getValor()!= null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+entrevistaInicialDTO.getTipoSesion().getValor() +" </div>]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Entrevista Inicial" +" </div>]]></cell>");
					}
					String fecha=DateUtils.formatear(entrevistaInicialDTO.getFechaSesion());
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+fecha +" </div>]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"No"+" </div>]]></cell>");
					writer.print("</row>");
					
					EntrevistaComplementariaDTO entrevistaComplementariaDTO=new EntrevistaComplementariaDTO();
					entrevistaComplementariaDTO.setNumeroExpediente(expedienteDTO);
					
					entrevistaComplementariaDTO.setEsPresente(false);
//					entrevistaComplementariaDTO.setFechaSesion(new Date());
					entrevistaComplementariaDTO.setNumeroSesion((short)2);
					entrevistaComplementariaDTO.setTipoSesion(new ValorDTO(TipoSesion.ENTREVISTA_COMPLEMENTARIA.getValorId(), TipoSesion.ENTREVISTA_COMPLEMENTARIA.toString(), TipoSesion.ENTREVISTA_COMPLEMENTARIA.name()));
					entrevistaComplementariaDTO.setUsuario(super.getUsuarioFirmado(request));
					
					entrevistaComplementariaDTO=sesionDelegate.guardarEntrevistaComplementaria(entrevistaComplementariaDTO);
					writer.print("<row id='"+"2,"+entrevistaComplementariaDTO.getSesionId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+entrevistaComplementariaDTO.getNumeroSesion()+" </div>]]></cell>");
					if(entrevistaInicialDTO.getTipoSesion()!=null && entrevistaInicialDTO.getTipoSesion().getValor()!= null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+entrevistaInicialDTO.getTipoSesion().getValor() +" </div>]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Entrevista Complementaria" +" </div>]]></cell>");
					}
					fecha=DateUtils.formatear(entrevistaComplementariaDTO.getFechaSesion());
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+fecha +" </div>]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"No"+" </div>]]></cell>");
					writer.print("</row>");
					
					NotaEvolucionDTO notaEvolucionDTO=new NotaEvolucionDTO();
					notaEvolucionDTO.setNumeroExpediente(expedienteDTO);
					
					notaEvolucionDTO.setEsPresente(false);
//					notaEvolucionDTO.setFechaSesion(new Date());
					notaEvolucionDTO.setNumeroSesion((short)3);
					notaEvolucionDTO.setTipoSesion(new ValorDTO(TipoSesion.NOTAS_EVOLUCION.getValorId(), TipoSesion.NOTAS_EVOLUCION.toString(), TipoSesion.NOTAS_EVOLUCION.name()));
					notaEvolucionDTO.setUsuario(super.getUsuarioFirmado(request));
					notaEvolucionDTO=sesionDelegate.guardarNotaEvolucion(notaEvolucionDTO);
					writer.print("<row id='"+"3,"+notaEvolucionDTO.getSesionId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+notaEvolucionDTO.getNumeroSesion()+" </div>]]></cell>");
					if(entrevistaInicialDTO.getTipoSesion()!=null && entrevistaInicialDTO.getTipoSesion().getValor()!= null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+entrevistaInicialDTO.getTipoSesion().getValor() +" </div>]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Nota de Evaluación" +" </div>]]></cell>");
					}
					fecha=DateUtils.formatear(notaEvolucionDTO.getFechaSesion());
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+fecha +" </div>]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"No"+" </div>]]></cell>");
					writer.print("</row>");
					
				writer.print("</rows>");
				writer.flush();
				writer.close();
				
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;	
			
		
			
	}
	
	
	
}
