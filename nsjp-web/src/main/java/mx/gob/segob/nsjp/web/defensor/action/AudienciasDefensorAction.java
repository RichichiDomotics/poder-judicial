package mx.gob.segob.nsjp.web.defensor.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.delegate.audiencia.AudienciaDelegate;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class AudienciasDefensorAction extends GenericAction {
	
	private static final Logger log = Logger.getLogger(AdministrarDefensaEjecucionAction.class);
	
	@Autowired
	private AudienciaDelegate audienciaDelegate;
	
	public ActionForward consultarDetalleAudienciaDefensoria(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			Long idAudiencia = NumberUtils.toLong(request.getParameter("idAudiencia"), -1);
			AudienciaDTO audiencia = new AudienciaDTO();
			audiencia.setId(idAudiencia);
			audiencia = audienciaDelegate.consultarAudienciaFromPoderJudicial(audiencia);
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("audiencia", AudienciaDTO.class);
			converter.alias("involucrado", InvolucradoViewDTO.class);
			xml = converter.toXML(audiencia);
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();

		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public ActionForward consultarAudienciasDefensor(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			String fechaInicio = request.getParameter("inicio");
			String fechaFin = request.getParameter("fin");		
			
			log.info("ejecuntando consultarAudienciasDefensor....");
			Calendar calendario = Calendar.getInstance();
			AudienciaDTO audiencia = new AudienciaDTO();
			audiencia.setEstatusAudiencia(new ValorDTO(EstatusAudiencia.PROGRAMADA.getValorId()));
				
			DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaCreacionInicio = null;
			Date fechaCreacionFin = null;
			
			if(fechaInicio == null || fechaInicio == ""){
				audiencia.setFechaFiltroInicio(calendario.getTime());
			}
			else{
				fechaCreacionInicio = formato.parse(fechaInicio);
				audiencia.setFechaFiltroInicio(fechaCreacionInicio);
			}
			
			if(fechaFin == null || fechaInicio == ""){
				audiencia.setFechaFiltroFin(calendario.getTime());
			}
			else{
				fechaCreacionFin = formato.parse(fechaFin);
				audiencia.setFechaFiltroFin(fechaCreacionFin);
			}										
			
			//Se obtiene el distrito del usuario firmado en session
			UsuarioDTO usuarioFirmado = getUsuarioFirmado(request);
            if (usuarioFirmado.getFuncionario() != null
             && usuarioFirmado.getFuncionario().getDiscriminante() != null
             && usuarioFirmado.getFuncionario().getDiscriminante().getDistrito() != null
             && usuarioFirmado.getFuncionario().getDiscriminante().getDistrito().getCatDistritoId() != null) {
            	audiencia.setIdDistritoFiltroAudiencias(usuarioFirmado.getFuncionario().getDiscriminante().getDistrito().getCatDistritoId());
            }			
			
			
			List<AudienciaDTO> audienciaDTOs = audienciaDelegate.consultarAudienciasFromPoderJudicial(audiencia);
			log.info("Audiencias "+audienciaDTOs);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		
			
			int lTotalRegistros = audienciaDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			
			String caso = " - ", tipo = " - ", caracter = " - ";
			String fecha = " - ", sala = " - ", defensor = " NO ";
			String causa = " - ";
			
			for(AudienciaDTO audienciaDTO : audienciaDTOs){
			 
				if(audienciaDTO.getExpediente() !=null && audienciaDTO.getExpediente().getCasoDTO()!=null ){
					caso = audienciaDTO.getExpediente().getCasoDTO().getNumeroGeneralCaso();
				} else {
				    caso = " - ";
				}
				
				causa = audienciaDTO.getNumeroCausa();
				caracter = audienciaDTO.getCaracter();	
				tipo = audienciaDTO.getTipoAudiencia().getValor();
				
				if(audienciaDTO.getStrHoraEvento()!=null && audienciaDTO.getStrFechaEvento()!=null ){
					fecha= audienciaDTO.getStrFechaEvento() +" - "+ audienciaDTO.getStrHoraEvento();
				}
					
				sala = audienciaDTO.getSala().getNombreSala();
				for(FuncionarioDTO funcionario :  audienciaDTO.getFuncionarios()){
					if(funcionario.getPuesto().getIdCampo() == Puestos.ABOGADO_DEFENSOR.getValorId()){
						defensor += " SI ";
					}
				}
				
				writer.print("<row id='"+audienciaDTO.getId()+ "'>");
				writer.print("<cell>" + caso + "</cell>");		
				writer.print("<cell>" + caracter + "</cell>");
				writer.print("<cell>" + tipo + "</cell>");
				writer.print("<cell>" + fecha + "</cell>");
				writer.print("<cell>" + sala + "</cell>");
				writer.print("<cell>" + defensor + "</cell>");
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
