package mx.gob.segob.nsjp.web.defensor.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class AdministrarDefensaEjecucionAction extends GenericAction {
	
	private static final Logger log = Logger.getLogger(AdministrarDefensaEjecucionAction.class);
	@Autowired
	private FuncionarioDelegate funcionarioDelegate;
	
	@Autowired
	private ExpedienteDelegate expedienteDelegate;
	
	public ActionForward consultarDefensaEjecucion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			List<ExpedienteDTO> expedienteDTOs = new ArrayList<ExpedienteDTO>();
			UsuarioDTO usuarioDTO = super.getUsuarioFirmado(request);
			//usuarioDTO.setIdUsuario(1L);
			expedienteDTOs = expedienteDelegate.consultarExpedientesPorUsuarioYEtapa(usuarioDTO, EtapasExpediente.EJECUCION.getValorId());
			
			log.info("DEFENSORES Tareas");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		
			List<InvolucradoDTO> involucradoDTOs = new ArrayList<InvolucradoDTO>();
			int lTotalRegistros = expedienteDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			//NSJYUCPROC2011333WT								

			for(ExpedienteDTO expedienteDTO : expedienteDTOs){
				//for(InvolucradoDTO involucradoDTO : expedienteDTO.addInvolucradoDTO(involucradoDTO.getExpedienteDTO().getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA) )){
				
				List<InvolucradoDTO> probablesResponsables = expedienteDTO.getInvolucradosDTO();
				 log.info("NumeroExpediente :: " + expedienteDTO.getNumeroExpediente() +" ::,DEFENSORES Tareas res :::"+ probablesResponsables);
				
				 
				 //no esta definido falta en el grid
				 // - Dirección General de Ejecución de Penas y Medidas Judiciales donde se encuentra el sentenciado (Nombre de la DGEPMJ).
					writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+"-"+expedienteDTO.getEtapa().getIdCampo()+ "-" + expedienteDTO.getEtapa().getValor() + "'>"); 
					writer.print("<cell>");
					if(expedienteDTO.getCausaPadre()!=null){
						writer.print(expedienteDTO.getCausaPadre());
							}
					writer.print("</cell>");
					
					writer.print("<cell>");
					if(expedienteDTO.getNumeroExpediente()!=null){
						writer.print(expedienteDTO.getNumeroExpediente());
					}
					writer.print("</cell>");
					
					writer.print("<cell>");
				//	if(expedienteDTO.getUsuario().getFuncionario().getNombreFuncionario() !=null){
						writer.print("");
						//	}
					writer.print("</cell>");
					
					
					
					writer.print("<cell>");
					for (InvolucradoDTO involucradoDTO : probablesResponsables) {
					if(involucradoDTO.getNombreCompleto()!=null){
						
					writer.print(involucradoDTO.getNombreCompleto());
						}
					}
				    writer.print("</cell>");
				    
				    writer.print("<cell>");					   
					if(expedienteDTO.getDelitoPrincipal()!=null){							
					writer.print(expedienteDTO.getDelitoPrincipal());						
					}
				    writer.print("</cell>");
				    
				    writer.print("<cell>");					   
					//if(involucradoDTO.getInstitucionPresenta().getClave()!=null){							
					writer.print("");						
					//}
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
