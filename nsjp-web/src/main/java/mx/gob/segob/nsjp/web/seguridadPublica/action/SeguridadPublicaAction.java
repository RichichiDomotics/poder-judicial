package mx.gob.segob.nsjp.web.seguridadPublica.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.delegate.avisodetencion.AvisoDetencionDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.informepolicial.InformePolicialHomologadoDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudTrasladoImputadoDelegate;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InvolucradoIPHDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

public class SeguridadPublicaAction extends GenericAction{
	private static final Logger log  = Logger.getLogger(SeguridadPublicaAction.class);
	
	@Autowired
	private InformePolicialHomologadoDelegate informePolicialHomologadoDelegate;

	@Autowired
	ExpedienteDelegate expedienteDelegate;
	
	@Autowired
	InvolucradoDelegate involucradoDelegate;

	@Autowired
	public AvisoDetencionDelegate avisoDetencionDelegate;
	
	@Autowired
	public SolicitudTrasladoImputadoDelegate solicitudTrasladoDelegate;
	
	
	public ActionForward generarExpedienteSSP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action generar expediente");
			
			UsuarioDTO usuario = getUsuarioFirmado(request);
			ExpedienteDTO expediente = new ExpedienteDTO();
			expediente.setUsuario(usuario);
			
			expediente.setFechaApertura(new Date());
			expediente.setArea(new AreaDTO(Areas.POLICIA_SSP));
			ExpedienteDTO respuesta = expedienteDelegate.asignarNumeroExpediente(expediente);
			
			
			converter.alias("expedienteDTO", ExpedienteDTO.class);
			String xml = converter.toXML(respuesta);
			log.info("Expediente SSP------- "+xml);

			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * MEtodo para Enviar los avisos de detencion de los probables responsables de un IPH
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward enviarAvisosDetencionIPH(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("ejecutando Action SeguridadPublicaAction folioIPH "+request.getParameter("folioIPH"));
			Long folioIPH =NumberUtils.toLong(request.getParameter("folioIPH"),0) ;
			Long idIndividuo=null;
			
			if(folioIPH > 0L){
				InformePolicialHomologadoDTO dtoIPH = informePolicialHomologadoDelegate.consultarInformePorFolio(folioIPH);
				log.info("ejecutando Action SeguridadPublicaAction dtoIPH.getExpediente() ... "+dtoIPH.getExpediente());

				DetencionDTO detencionDTO=new DetencionDTO();
				for(InvolucradoDTO involucradoIPH : dtoIPH.getExpediente().getInvolucradosDTO()){
					log.info("ejecutando Action SeguridadPublicaAction en metodo involucradoIPH "+involucradoIPH.getNombreCompleto());
					if(involucradoIPH.getCalidadDTO().getValorIdCalidad().getIdCampo().longValue()== Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId().longValue()){
						log.info("ejecutando Action SeguridadPublicaAction en metodo getCalidadDTO "+involucradoIPH.getElementoId());
						idIndividuo=involucradoIPH.getElementoId();
					}
				}
				UsuarioDTO usuarioDTO=super.getUsuarioFirmado(request);
				
				InvolucradoDTO involucradoDTO=new InvolucradoDTO();
				Long idIndi=0L;
				if(idIndividuo !=null){
					idIndi=idIndividuo;
				}
				involucradoDTO.setElementoId(idIndi);
				
				InvolucradoDTO involucradoIPHDTO = involucradoDelegate.obtenerInvolucrado(involucradoDTO);
				
				detencionDTO.setUsuario(usuarioDTO);
				detencionDTO.setInvolucradoDTO(involucradoIPHDTO);
				//Envia a servicio el aviso
				avisoDetencionDelegate.enviarAvisoDetencion(detencionDTO,null,null,null);
			}
			
			String xml = "";
			if (log.isDebugEnabled()) {
				log.debug(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	public ActionForward consultarSolicitudesTrasladoPorEstatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			EstatusSolicitud estatusSolicitud = EstatusSolicitud.getByValor(Long.parseLong(request.getParameter("estatus")));
			
			List<SolicitudTrasladoImputadoDTO> solicitudDTOs = new ArrayList<SolicitudTrasladoImputadoDTO>();
			solicitudDTOs = (List<SolicitudTrasladoImputadoDTO>) solicitudTrasladoDelegate.consultarSolicitudTrasladoPorEstatus(estatusSolicitud);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		

			int lTotalRegistros = solicitudDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");

			for(SolicitudTrasladoImputadoDTO solicitudDTO : solicitudDTOs){

				writer.print("<row id='"+solicitudDTO.getDocumentoId()+ "'>");
				writer.print("<cell>"+ solicitudDTO.getNumeroCasoAsociado()+  "</cell>");
				
				writer.print("<cell>");
				if(solicitudDTO.getDestinatario() != null){
					writer.print(solicitudDTO.getDestinatario().getNombreCompleto());
				}else{
					writer.print("---");
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(solicitudDTO.getInvolucrado() != null){
					writer.print(solicitudDTO.getInvolucrado().getNombreCompleto());
				}else{
					writer.print("---");
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(solicitudDTO.getAudiencia() != null){
					writer.print(solicitudDTO.getAudiencia().getFechaEvento());
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(solicitudDTO.getAudiencia() != null){
					writer.print(solicitudDTO.getAudiencia().getLugarEvento());
				}else{
					writer.print("---");
				}
				writer.print("</cell>");
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			

		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
}
