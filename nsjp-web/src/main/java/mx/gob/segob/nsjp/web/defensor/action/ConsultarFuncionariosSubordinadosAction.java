/**
 * 
 */
package mx.gob.segob.nsjp.web.defensor.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author EdgarAG
 *
 */
public class ConsultarFuncionariosSubordinadosAction extends GenericAction{
	private static final Logger log = Logger.getLogger(ConsultarFuncionariosSubordinadosAction.class);
	@Autowired
	private FuncionarioDelegate funcionarioDelegate;
	@Autowired
	private ExpedienteDelegate expedienteDelegate;
	
	public ActionForward consultarFuncionariosSubordinadosEtapa(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			log.info("ConsultarFuncionariosSubordinados");
			Long id = 1L;
			
			FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
			funcionarioDTO.setClaveFuncionario(id);
			List<FuncionarioDTO> funcionarioDTOs = new ArrayList<FuncionarioDTO>();
			funcionarioDTOs = funcionarioDelegate.consultarFuncionariosSubordinados(funcionarioDTO);

			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			int lTotalRegistros = funcionarioDTOs.size();
			writer.print("<rows>");
			writer.print("<records>" + lTotalRegistros + "</records>");			
		
			for (FuncionarioDTO funcionarioDTO2 : funcionarioDTOs) {
			
			
				writer.print("<row id='"+ funcionarioDTO2.getClaveFuncionario() +  "'>");
				
				log.info("ConsultarFuncionariosSubordinados res"+funcionarioDTO2.getNombreCompleto());
				writer.print("<cell>");
				if(funcionarioDTO2.getNombreCompleto()!=null){
				writer.print(funcionarioDTO2.getNombreCompleto());
				}
				writer.print( "</cell>"); 
				writer.print("</row>");
						}
					
			// }
			writer.print("</rows>");
			writer.flush();
			writer.close();
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public ActionForward consultarExpedientesPorUsuarioYEtapa(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		log.debug("/**** OBTENER LOS EXPEDIENTES QUE SE ENCUENTRAN EN ETAPA DE INTEGRACION DE UN FUNCIONARIO ****/");		
		try {	
			String etapa= request.getParameter("etapa");
			log.info("Etapa del Expediente"+ etapa);
			
			String idFuncionario= request.getParameter("idFuncionario");
			log.info("Funcionario "+ idFuncionario);
			log.info("consultar por todos? request "+request.getParameter("expTodos"));
			Boolean expTodos =Boolean.parseBoolean(request.getParameter("expTodos")) ;
			log.info("consultar por todos? "+ expTodos);
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			
			usuarioDTO.setIdUsuario(Long.parseLong(idFuncionario));
		    List<ExpedienteDTO> expedientesRes = new ArrayList<ExpedienteDTO>();
			
			if(expTodos.equals(false)){
		    
				if(etapa.equals(EtapasExpediente.EJECUCION.getValorId().toString())){
			 expedientesRes = expedienteDelegate.consultarExpedientesPorUsuarioYEtapa(usuarioDTO, EtapasExpediente.EJECUCION.getValorId());
			 log.info("Respeuesta de consulta consultarExpedientesPorUsuarioYEtapa :::"+ expedientesRes);
				}
				if(etapa.equals(EtapasExpediente.INTEGRACION.getValorId().toString())){
					 expedientesRes = expedienteDelegate.consultarExpedientesPorUsuarioYEtapa(usuarioDTO, EtapasExpediente.INTEGRACION.getValorId());
					 log.info("Respeuesta de consulta consultarExpedientesPorUsuarioYEtapa :::"+ expedientesRes);		
				}
				if(etapa.equals(EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId().toString())){
					 expedientesRes = expedienteDelegate.consultarExpedientesPorUsuarioYEtapa(usuarioDTO, EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId());
					 log.info("Respeuesta de consulta consultarExpedientesPorUsuarioYEtapa :::"+ expedientesRes);		
				}
				if(etapa.equals(EtapasExpediente.TECNICA.getValorId().toString())){
					 expedientesRes = expedienteDelegate.consultarExpedientesPorUsuarioYEtapa(usuarioDTO, EtapasExpediente.TECNICA.getValorId());
					 log.info("Respeuesta de consulta consultarExpedientesPorUsuarioYEtapa :::"+ expedientesRes);		
				}
				
				}
			else{
				FuncionarioDTO funcionario = new FuncionarioDTO();
				funcionario.setClaveFuncionario(new Long(idFuncionario));
				expedientesRes = expedienteDelegate.consultarExpedientesPorFiltro(null,null,funcionario,null,null);
//				expedientesRes = expedienteDelegate.consultarExpedientesUsuarioArea(usuarioDTO);
				log.info("Respuesta de consulta consultar todos los ExpedientesPorUsuario :::" + expedientesRes);
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
            
//            int lTotalRegistros = expedientesRes.size();
//			writer.print("<records>" + lTotalRegistros + "</records>");					
			
		    List<DelitoDTO> delitoDTOs = new ArrayList<DelitoDTO>();
			for (ExpedienteDTO expedienteDTO : expedientesRes) {			
			
				writer.print("<row id='"+ expedienteDTO.getNumeroExpedienteId()+  "'>");				
				
				writer.print("<cell>");
				if(expedienteDTO.getCasoDTO()!= null && expedienteDTO.getCasoDTO().getNumeroGeneralCaso() !=null){
				writer.print(expedienteDTO.getCasoDTO().getNumeroGeneralCaso());
				}else{
					writer.print("-");
				}
				writer.print( "</cell>"); 
				
				writer.print("<cell>");
				if(expedienteDTO.getNumeroExpediente() !=null){
				writer.print(expedienteDTO.getNumeroExpediente());
				}else{
					writer.print("-");					
				}
				writer.print( "</cell>"); 
				
				writer.print("<cell>");
				if(expedienteDTO.getEtapa()!=null && expedienteDTO.getEtapa().getValor() != null ){
				writer.print(expedienteDTO.getEtapa().getValor());
				}else{
					writer.print("-");
				}
				writer.print( "</cell>"); 
				
				writer.print("<cell>");
				//if(expedienteDTO.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA) !=null){
					//for(InvolucradoDTO involucradoDTO : expedienteDTO.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA)){
							//writer.print(involucradoDTO.getNombreCompleto());
					//}
				if(expedienteDTO.getInvolucradoByCalidad(Calidades.DEFENDIDO) !=null){
					InvolucradoDTO involucradoDTO = expedienteDTO.getInvolucradoByCalidad(Calidades.DEFENDIDO).get(0);
					writer.print(involucradoDTO.getNombreCompleto());
				}else{
					writer.print("-");
				}
				writer.print( "</cell>"); 
				
				writer.print("<cell>");
				for(DelitoDTO delitoDTO : delitoDTOs ){
				if(delitoDTO.getCatDelitoDTO() !=null){
				writer.print(" "+delitoDTO.getCatDelitoDTO().getNombre()+" ");
				}
				}
				writer.print( "</cell>"); 
				
				writer.print("</row>");
						}
					
			// }
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (NSJPNegocioException e) {
			log.error(e.getMessage());
		}
		
		return null;
	}
	

}
