package mx.gob.segob.nsjp.web.elemento.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.elemento.ElementoDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.elemento.RelacionReincidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class BuscarReincidenciasDeElementoAction extends GenericAction{
	/* Log de clase*/
	private static final Logger log  = Logger.getLogger(BuscarReincidenciasDeElementoAction.class);

	@Autowired
	public ElementoDelegate elementoDelegate;
	@Autowired
	public InvolucradoDelegate involucradoDelegate;
	
	/**
	 * Permite buscar las reincidencias del elemento Vehiculo:
	 *  permitira filtrar por el numero de serie y por el numero de placas
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarReincidenciaDeVehiculo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {		
		try {
			String noPlacas=request.getParameter("noPlacas");			
			String noSerie=request.getParameter("noSerie");
			String numeroExpediente=request.getParameter("numeroExpediente");
			Long idElemento=Long.parseLong(request.getParameter("idVehiculo"));
			ExpedienteDTO expedienteDTO = super.getExpedienteTrabajo(request, numeroExpediente);
			
			log.info("$$$$ noPlacas $$$ : " + noPlacas);
			log.info("$$$$ noSerie $$$ : "+noSerie);
			log.info("$$$$ numeroExpediente $$$ : "+numeroExpediente);
			log.info("$$$$ expedienteDTO $$$ : "+expedienteDTO);

			log.info("$$$$ noCaso $$$ : "+ (expedienteDTO != null && expedienteDTO.getCasoDTO() != null && expedienteDTO.getCasoDTO().getCasoId() != null?
					expedienteDTO.getCasoDTO().getCasoId():"-"));
			
			VehiculoDTO loVehiculo = new VehiculoDTO();
			loVehiculo.setElementoId(idElemento);
			loVehiculo.setPlaca(noPlacas);
			loVehiculo.setNoSerie(noSerie);
			CasoDTO temp = new CasoDTO();
						
			List<CasoDTO> listaObjetosDTOs= elementoDelegate.buscarReincidenciaDeElementos(loVehiculo);
			for (CasoDTO casoDTO : listaObjetosDTOs) {
				if(casoDTO.getNumeroGeneralCaso().trim().equals(expedienteDTO.getCasoDTO().getNumeroGeneralCaso().trim())){
					temp = casoDTO;
					log.info("$$$$ Se elimina el numero de caso $$$ : "+casoDTO.getNumeroGeneralCaso());	
				}else
					log.info("$$$$ NO Se elimina el numero de caso $$$ : "+casoDTO.getNumeroGeneralCaso());
			}
			listaObjetosDTOs.remove(temp);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
		
			writer.print("<rows>");
			int lTotalRegistros=listaObjetosDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");	
			
			for (CasoDTO objetoDTO : listaObjetosDTOs) {
				writer.print("<row id='"+objetoDTO.getCasoId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+objetoDTO.getNumeroGeneralCaso()+" </div>]]></cell>");				
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (objetoDTO.getFechaApertura() != null? DateUtils.formatear(objetoDTO.getFechaApertura()):"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (objetoDTO.getEstatus() != null && objetoDTO.getEstatus().getNombre() != null? objetoDTO.getEstatus().getNombre():"-") +" </div>]]></cell>");
				writer.print("</row>");
			}
		writer.print("</rows>");
		writer.flush();
		writer.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	
	/**
	 * Permite buscar las reincidencias del elemento Persona:
	 *  permitira filtrar por el nombre, apellido paterno y materno
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarReincidenciaDePersona(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {		
		try {
			String nombre=request.getParameter("nombre");			
			String apPaterno=request.getParameter("paterno");
			String apMaterno=request.getParameter("materno");
			Long idElemento= NumberUtils.toLong(request.getParameter("idElemento"),0);	
			String numeroExpediente=request.getParameter("numeroExpediente");

			ExpedienteDTO expedienteDTO = super.getExpedienteTrabajo(request, numeroExpediente);
			
			log.info("$$$$ nombre $$$ : " + nombre);
			log.info("$$$$ apPaterno $$$ : "+apPaterno);
			log.info("$$$$ apMaterno $$$ : "+apMaterno);
			log.info("$$$$ numeroExpediente $$$ : "+numeroExpediente);
			log.info("$$$$ noCaso $$$ : "+ (expedienteDTO != null && expedienteDTO.getCasoDTO() != null && expedienteDTO.getCasoDTO().getCasoId() != null?
					expedienteDTO.getCasoDTO().getCasoId():"-"));

			
			InvolucradoDTO loPersona = new InvolucradoDTO();
			
			List<NombreDemograficoDTO> listaNombresDem = new ArrayList<NombreDemograficoDTO>();
			NombreDemograficoDTO loNombreCompleto = new NombreDemograficoDTO();
			loNombreCompleto.setNombre(nombre);
			loNombreCompleto.setApellidoPaterno(apPaterno);
			loNombreCompleto.setApellidoMaterno(apMaterno);
			listaNombresDem.add(loNombreCompleto);
			loPersona.setNombresDemograficoDTO(listaNombresDem);
			loPersona.setElementoId(idElemento);
			
			List<CasoDTO> listaObjetosDTOs= elementoDelegate.buscarReincidenciaDeElementos(loPersona);
			
			CasoDTO temp = new CasoDTO();
			for (CasoDTO casoDTO : listaObjetosDTOs) {
				if(casoDTO.getNumeroGeneralCaso() != null && casoDTO.getNumeroGeneralCaso().trim().equals(expedienteDTO.getCasoDTO().getNumeroGeneralCaso().trim())){
					temp = casoDTO;
					log.info("$$$$ Se elimina el numero de caso $$$ : "+casoDTO.getNumeroGeneralCaso());
				}else
					log.info("$$$$ NO Se elimina el numero de caso $$$ : "+casoDTO.getNumeroGeneralCaso());
			}
			listaObjetosDTOs.remove(temp);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
		
			writer.print("<rows>");
			int lTotalRegistros=listaObjetosDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");	
			
			for (CasoDTO objetoDTO : listaObjetosDTOs) {
				writer.print("<row id='"+objetoDTO.getCasoId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+objetoDTO.getNumeroGeneralCaso()+" </div>]]></cell>");				
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (objetoDTO.getFechaApertura() != null? DateUtils.formatear(objetoDTO.getFechaApertura()):"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (objetoDTO.getEstatus() != null && objetoDTO.getEstatus().getNombre() != null? objetoDTO.getEstatus().getNombre():"-") +" </div>]]></cell>");
				writer.print("</row>");
			}
		writer.print("</rows>");
		writer.flush();
		writer.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}	
	
	/**
	 * Permite buscar las reincidencias de Probables responsables en un expediente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward existeReincidenciaDeProbablesResponsables(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {		
		try {			
			String numeroExpediente=request.getParameter("numeroExpediente");
			ExpedienteDTO expedienteDTO = super.getExpedienteTrabajo(request, numeroExpediente);
	
			List<InvolucradoDTO> listaInvolucrados=new ArrayList<InvolucradoDTO>();
			UsuarioDTO usuario = super.getUsuarioFirmado(request);
			
			Boolean existeReincidencias = false;

			log.info("$$$$ numeroExpediente $$$ : "+numeroExpediente);
			log.info("$$$$ noCaso $$$ : "+ (expedienteDTO != null && expedienteDTO.getCasoDTO() != null && expedienteDTO.getCasoDTO().getCasoId() != null?
					expedienteDTO.getCasoDTO().getCasoId():"-"));
			
			//primero buscamos a los probables responsables que tenga el expediente
			listaInvolucrados = involucradoDelegate.consultarInvolucradoExpediente(expedienteDTO, Calidades.PROBABLE_RESPONSABLE_PERSONA, usuario);
			log.info("$$$$ numero de involucrados encontrados listaInvolucrados.size(): "+listaInvolucrados.size());
			
			//para cada responsable buscamos sus reincidencias, si encuentra cambia la bandera 'existeReincidencias' a true
			for(InvolucradoDTO loPersona: listaInvolucrados){				
				List<CasoDTO> listaObjetosDTOs = elementoDelegate.buscarReincidenciaDeElementos(loPersona);
				
				CasoDTO temp = new CasoDTO();
				for (CasoDTO casoDTO : listaObjetosDTOs) {
					if(casoDTO.getNumeroGeneralCaso() != null && casoDTO.getNumeroGeneralCaso().trim().equals(expedienteDTO.getCasoDTO().getNumeroGeneralCaso().trim())){
						temp = casoDTO;
						log.info("$$$$ Se elimina el numero de caso $$$ : "+casoDTO.getNumeroGeneralCaso());
					}else
						log.info("$$$$ NO Se elimina el numero de caso $$$ : "+casoDTO.getNumeroGeneralCaso());
				}
				listaObjetosDTOs.remove(temp);
				if(listaObjetosDTOs != null && listaObjetosDTOs.size()>0){
					existeReincidencias = true;		//si hay reincidencias del probable responsable
					break;
				}
			}
			
			log.info(":::EXISTEN REINCIDENCIAS: "+existeReincidencias);
			
			converter.alias("reincidencia", java.lang.Boolean.class);		
			String xml = converter.toXML(existeReincidencias);
			if(log.isDebugEnabled())
			{
				log.debug(xml);
			}
			
			escribirRespuesta(response, xml);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}	
	
	/**
	 * Permite consultar las reincidencias registradas por elemento
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarReincidenciaXElemento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {		
		try {
			Long idElemento= NumberUtils.toLong(request.getParameter("idElemento"),0);	
			String numeroExpediente=request.getParameter("numeroExpediente");
			
			log.info("$$$$ idElemento $$$ : " + idElemento);			
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			
			List<RelacionReincidenciaDTO> llReincidencias = elementoDelegate.consultarReincidenciasXElemento(idElemento);

			if(numeroExpediente!=null && numeroExpediente!=""){
				ExpedienteDTO expedienteDTO = super.getExpedienteTrabajo(request, numeroExpediente);
				RelacionReincidenciaDTO temp=null;
				
				for (RelacionReincidenciaDTO relacionReincidenciaDTO : llReincidencias) {					
					if(relacionReincidenciaDTO.getCaso() != null && relacionReincidenciaDTO.getCaso().getNumeroGeneralCaso()!= null &&
					   relacionReincidenciaDTO.getCaso().getNumeroGeneralCaso().trim().equals(expedienteDTO.getCasoDTO().getNumeroGeneralCaso().trim())){
						temp = relacionReincidenciaDTO;
					}
				}
				if(temp!=null){
					llReincidencias.remove(temp);	
				}
			}
			
			writer.print("<rows>");
			int lTotalRegistros=llReincidencias.size();
			writer.print("<records>" + lTotalRegistros + "</records>");	
			
			for (RelacionReincidenciaDTO relacionReincidenciaDTO : llReincidencias) {
				CasoDTO objetoDTO = relacionReincidenciaDTO.getCaso();
				writer.print("<row id='"+objetoDTO.getCasoId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+objetoDTO.getNumeroGeneralCaso()+" </div>]]></cell>");				
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (objetoDTO.getFechaApertura() != null? DateUtils.formatear(objetoDTO.getFechaApertura()):"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (objetoDTO.getEstatus() != null && objetoDTO.getEstatus().getNombre() != null? objetoDTO.getEstatus().getNombre():"-") +" </div>]]></cell>");
				writer.print("</row>");
			}
		writer.print("</rows>");
		writer.flush();
		writer.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}	
	
	/**
	 * Permite registrar reincidencias para un elemento
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward registrarReincidenciasXElemento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {		
		try {
			Long idElemento= NumberUtils.toLong(request.getParameter("idElemento"),0);	
			Long idFuncionario= NumberUtils.toLong(request.getParameter("idFuncionario"),0);				
			String idsCasos = request.getParameter("idCasos");
			String[] lsIdCasos = null;
			if(idsCasos != null && !idsCasos.equals(""))
				lsIdCasos = idsCasos.split(",");
			
			List<Long> idCasosPersisitir = new ArrayList<Long>();
			for(int i= 0; i< lsIdCasos.length; i++){
				idCasosPersisitir.add(Long.parseLong(lsIdCasos[i]));
			}
			
			log.info("$$$$ idElemento $$$ : " + idElemento);			
			log.info("$$$$ idFuncionario $$$ : " + idFuncionario);			
			log.info("$$$$ idCasos $$$ : " + idsCasos);			
	
			List<RelacionReincidenciaDTO> llReincidencias = elementoDelegate.registrarReinicidencias(idElemento, idCasosPersisitir, idFuncionario);
			
			if (llReincidencias != null){
				converter.alias("RelacionReincidenciaDTO", RelacionReincidenciaDTO.class);
				String xml = converter.toXML(llReincidencias);
				log.info(xml);
				escribirRespuesta(response, xml);
			} else {
				converter.alias("RelacionReincidenciaDTO", RelacionReincidenciaDTO.class);
				String xml = converter.toXML(0);
				log.info(xml);
				escribirRespuesta(response, xml);
			}
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}	

	
}
