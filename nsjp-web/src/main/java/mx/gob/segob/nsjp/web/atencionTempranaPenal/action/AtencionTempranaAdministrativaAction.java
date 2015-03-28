package mx.gob.segob.nsjp.web.atencionTempranaPenal.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.actividad.ActividadDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.expediente.TurnoDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class AtencionTempranaAdministrativaAction extends GenericAction{
	/* Log de clase*/
	private static final Logger log  = Logger.getLogger(AtencionTempranaAdministrativaAction.class);
	
	@Autowired
	public TurnoDelegate turnoDelegate;
	
	@Autowired
	public ExpedienteDelegate expedienteDelegate;
	
	@Autowired
	public ActividadDelegate actividadDelegate;
	

	public ActionForward busquedaInicialTurnosGrid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaAdministrativaAction en metodo busquedaInicialTurnosGrid");
			try {
				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
				//usuarioDTO.setAreaActual(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaInicialTurnosGrid:#####"+turnoDelegate);
				List<TurnoDTO> listTurnoDTOs=turnoDelegate.consultarTurnosAtendidosPorUsuario(usuarioDTO,true);
				if (log.isDebugEnabled()) {
					log.debug("##################lista de Casos:::::::::" + listTurnoDTOs.size());
				}
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
				
				for (TurnoDTO turnoDTO : listTurnoDTOs) {
					ExpedienteDTO expedienteDTO=turnoDTO.getExpediente();
					
					writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getNumeroExpediente()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getStrFechaApertura()+" </div]]></cell>");
					log.info("Este es el expediente con calidad de denunciante"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE));
					log.info("invol tamaño"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
					log.info("invol tamaño de"+expedienteDTO.getInvolucradosDTO().size());
					 boolean op=true;
					for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE)) {
						log.info("numero de involucrado nombre completo perdon:"+involucradoDTO.getNombreCompleto());
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getNombreCompleto() +" </div]]></cell>");
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getElementoId() +" </div]]></cell>");
								op=false;
						
					}
					if(op){
						boolean op2=true;
						for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE_ORGANIZACION)) {
							log.info("numero de involucrado nombre completo de organizacion:"+involucradoDTO.getOrganizacionDTO().getNombreOrganizacion());
									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getOrganizacionDTO().getNombreOrganizacion() +" </div]]></cell>");
									op2=false;
							
						}
						if(op2)
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An&oacute;nimo"+" </div]]></cell>");
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"-"+" </div]]></cell>");
					}
					DelitoDTO delito=expedienteDTO.getDelitoPrincipal();
					if(delito!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+delito.getCatDelitoDTO().getNombre()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Sin delito"+" </div]]></cell>");
					}
					
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

	//Busqueda de trnos para atencion administrativa
	public ActionForward busquedaSiguienteTurnoAministrativo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaSiguienteTurno");
			try {
				final TurnoDTO filtro = new TurnoDTO();
				filtro.setTipoTurno(TipoTurno.ADMINISTRATIVO);
				
				//Para las agencias de PGJ y PJ
	            UsuarioDTO usuario = getUsuarioFirmado(request);
	            filtro.setUsuario(usuario);
	            
				TurnoDTO turnDTO=turnoDelegate.obtenerTurnoParaAtencion(filtro);
								
				converter.alias("turnoDTO", TurnoDTO.class);
				String xml = converter.toXML(turnDTO);
				//mandamos la respuesta al cliente
				escribir(response, xml,null);	
		} catch (Exception e) {	
			log.info("TurnoSiguienteError");
			log.info(e.getCause(),e);
			escribir(response, "TurnoSiguienteError",null);
		}
		return null;
	}
	
	public ActionForward busquedaInicialTurnosGridSinFecha(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaAdministrativaAction en metodo busquedaInicialTurnosGrid");
			try {
				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
				//usuarioDTO.setAreaActual(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaInicialTurnosGrid:#####"+turnoDelegate);
				List<TurnoDTO> listTurnoDTOs=turnoDelegate.consultarTurnosAtendidosPorUsuario(usuarioDTO,false);
				if (log.isDebugEnabled()) {
					log.debug("##################lista de Casos:::::::::" + listTurnoDTOs.size());
				}
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
				
				for (TurnoDTO turnoDTO : listTurnoDTOs) {
					ExpedienteDTO expedienteDTO=turnoDTO.getExpediente();
					
					writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getNumeroExpediente()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getStrFechaApertura()+" </div]]></cell>");
					log.info("Este es el expediente con calidad de denunciante"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE));
					log.info("invol tamaño"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
					log.info("invol tamaño de"+expedienteDTO.getInvolucradosDTO().size());
					 boolean op=true;
					for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE)) {
						log.info("numero de involucrado nombre completo perdon:"+involucradoDTO.getNombreCompleto());
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getNombreCompleto() +" </div]]></cell>");
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getElementoId() +" </div]]></cell>");
								op=false;
						
					}
					if(op){
						boolean op2=true;
						for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE_ORGANIZACION)) {
							log.info("numero de involucrado nombre completo de organizacion:"+involucradoDTO.getOrganizacionDTO().getNombreOrganizacion());
									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getOrganizacionDTO().getNombreOrganizacion() +" </div]]></cell>");
									op2=false;
							
						}
						if(op2)
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An&oacute;nimo"+" </div]]></cell>");
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"-"+" </div]]></cell>");
					}
					DelitoDTO delito=expedienteDTO.getDelitoPrincipal();
					if(delito!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+delito.getCatDelitoDTO().getNombre()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Sin delito"+" </div]]></cell>");
					}
					
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
	
	
	public ActionForward consultaExpedienteAdminAt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			log.info("ejecutando Action AtencionTempranaPenalAction en el metodo consultarExpedientes");
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			expedienteDTO.setNumeroExpedienteId(Long.parseLong(request.getParameter("idExpedienteTempAdmin")));
			String numeroExpediente=request.getParameter("numeroExpedienteTempAdmin");
			
			log.info("#$%%$$##$%%$# numeroExpediente_actaCirc_coonsul es: "+numeroExpediente);
			log.info("#$%%$$##$%%$# idExpediente_actaCirc_coonsul es: "+request.getParameter("idExpedienteTempAdmin"));
			log.info("#$%%$$##$%%$# operacion_actaCirc_coonsul es: "+request.getParameter("operacion"));
			log.info("#$%%$$##$%%$# formaId_actaCirc_coonsul es: "+request.getParameter("formaId"));
			
			//expedienteDTO = expedienteDelegate.consultarExpedienteRelacionadoAArea(numeroExpediente.trim(), Areas.ATENCION_TEMPRANA_PG_NO_PENAL.parseLong());
			
			expedienteDTO.setExpedienteId(expedienteDelegate.obtenerExpedienteIdPorNumExpId(expedienteDTO));
			expedienteDTO= expedienteDelegate.obtenerExpediente(expedienteDTO);
			log.info("*** numeroExpedienteId_ActaCirc :: "+expedienteDTO.getNumeroExpedienteId());
			
			expedienteDTO.setConsulta(true);
			
			log.info("$$$$ numero el Expediente $$$ : "+expedienteDTO.getNumeroExpediente());
			log.info("$$$$ numero del caso  $$$ : "+expedienteDTO.getCasoDTO());
			request.getSession().removeAttribute("numExpConsul");
			request.getSession().setAttribute("numExpConsul", expedienteDTO.getNumeroExpediente());
			request.getSession().setAttribute("idExpedienteConsul", expedienteDTO.getNumeroExpedienteId());
			request.getSession().setAttribute("idExpedienteConsulop", expedienteDTO.getExpedienteId());
			//request.getSession().setAttribute("idNumeroExpedienteopTempAdmin", expedienteDTO.getNumeroExpedienteId());
			//request.getSession().setAttribute("numeroCasoConsul", expedienteDTO.getCasoDTO().getNumeroGeneralCaso());
			//request.getSession().setAttribute("numeroCasoConsul", expedienteDTO.getCasoDTO().getNumeroGeneralCaso());
			
			log.info("subiendo_expediente_Acta_Circ ... :: "+expedienteDTO.getNumeroExpediente());
			super.setExpedienteTrabajo(request, expedienteDTO);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return mapping.findForward("succes");
	}
	
	public ActionForward consultaTiposAtencionSeleccionadas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			
			log.info("EJECUTANDO ACTION CONSULTA TIPOS ATENCION SELECCIONADAS");
			
			log.info("VERIFICANDO PARAMETROS");
			
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			String expedienteId=request.getParameter("expedienteId");
			String numeroExpediente=request.getParameter("numeroExpediente");
			String tipoAtencion=request.getParameter("tipoAtencion");
		
			log.info("expedienteId="+expedienteId);
			log.info("numeroExpediente="+numeroExpediente);
			log.info("tipoAtencion="+tipoAtencion);
			
			Long numeroOcurrencias = actividadDelegate
					.consultarNumeroActividadesPorTipoAtencionExpedienteId(
							NumberUtils.toLong(expedienteId, 0L),
							NumberUtils.toLong(tipoAtencion, 0L));
			
			log.info("RESULTADO numeroOcurrencias="+numeroOcurrencias);
			converter.alias("numeroRegistros",java.lang.Long.class);
			String xml = converter.toXML(numeroOcurrencias);
			//mandamos la respuesta al cliente
			escribir(response, xml,null);	
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
