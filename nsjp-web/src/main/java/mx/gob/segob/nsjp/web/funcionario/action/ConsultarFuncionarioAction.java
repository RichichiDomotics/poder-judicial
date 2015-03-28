/**
 * Nombre del Programa 	: ConsultarFuncionarioAction                                    
 * Autor               	: Cuauhtémoc Paredes                                              
 * Compania            	: Ultrasist                                                
 * Proyecto            	: NSJP              			Fecha:20/07/2011 
 * Marca de cambio     	: N/A                                                     
 * Descripcion General   : Clase para consultar los funcionario
 * Programa Dependiente  : N/A                                                      
 * Programa Subsecuente  : N/A                                                      
 * Cond. de ejecucion    : N/A                                                  
 * Dias de ejecucion     : N/A                             Horario: N/A
 *                               MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                 :N/A                                                           
 * Compania              :N/A                                                           
 * Proyecto              :N/A                                   Fecha: N/A       
 * Modificacion          :N/A                                                           
 *------------------------------------------------------------------------------      
 */
package mx.gob.segob.nsjp.web.funcionario.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.seguridad.Roles;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase que implementa las acciones para consultar funcionarios.
 * @version 1.0
 * @author Cuauhtemoc Paredes
 * 
 */
public class ConsultarFuncionarioAction extends GenericAction {

	/** Log de clase */
	private static final Logger log = Logger
			.getLogger(ConsultarFuncionarioAction.class);

	@Autowired
	public FuncionarioDelegate funcionarioDelegate;
	
		
	/**
	 * Metodo utilizado para consultar el funcionario Comandate de Policia Ministerial
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 * En caso de obtener una exception
	 */
	public ActionForward consultarFuncionarioRolComandantePoliciaMinisterial(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("EJECUTANDO ACTION CONSULTAR COMANDANTE POLICIA MINISTERIAL");
			
			List<FuncionarioDTO> funcionarioDTOs = funcionarioDelegate.consultarFuncionariosPorRol(Puestos.COMANDANTE_POLICIA_MINISTERIAL.getValorId());
			
			log.info("Funcionarios"+funcionarioDTOs);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");
			
			int lTotalRegistros=funcionarioDTOs.size();
			
			writer.print("<records>" + lTotalRegistros + "</records>");
				for (FuncionarioDTO funcionarioDTO : funcionarioDTOs) {
					
					log.info("Funcionario"+funcionarioDTO);
					
						writer.print("<row id='" + funcionarioDTO.getClaveFuncionario()+ "'>");
						writer.print("<cell>" +  funcionarioDTO.getNombreFuncionario() + "</cell>");
						writer.print("<cell>" + funcionarioDTO.getApellidoPaternoFuncionario()+ "</cell>");
						writer.print("<cell>" + funcionarioDTO.getApellidoMaternoFuncionario()+ "</cell>");
						writer.print("<cell>" + funcionarioDTO.getPuesto().getValor()+ "</cell>");
						writer.print("<cell>" + funcionarioDTO.getNombreInstitucion()+ "</cell>");
						writer.print("</row>");
				}			
			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.info(e.getCause(), e);

		}
		return null;
	}
	
	/**
	 * Metodo utilizado para obtener los datos de un defensor
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	public ActionForward buscarFuncionarioPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			log.info("***********************Ejecutando Action buscarFuncionarioPorId***************************************+");
			String funcionarioId=request.getParameter("funcionarioId");
			
			log.info("funcionarioId buscarFuncionarioPorId :" + funcionarioId);
			
			FuncionarioDTO funcionarioConsulta = new FuncionarioDTO();
			funcionarioConsulta.setClaveFuncionario(Long.parseLong(funcionarioId));
			//log.info("IdExpediente buscarIndividuosConCalidadDetenidos :::" + expediente.getExpedienteId());
			
			FuncionarioDTO funcionario = funcionarioDelegate.obtenerInformacionDefensor(funcionarioConsulta);
			log.info("funcionario RESP :::" + funcionario.getNombreCompleto());
			
			converter.alias("funcionario", FuncionarioDTO.class);
			
			String xml = converter.toXML(funcionario);
			log.info("funcionario xml :::" + xml);
			
			response.setContentType("text/xml");
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();	
			
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar Agentes del Ministerio Publico	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 * En caso de obtener una exception
	 */
	public ActionForward consultarAgentesDelMinisterioPublico(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("EJECUTANDO ACTION CONSULTAR AGENTES DEL MINISTERIO PUBLICO");
			List<FuncionarioDTO> funcionarioDTOs = funcionarioDelegate.consultarFuncionariosPorRol(Puestos.FISCAL.getValorId());
			
			log.info("Funcionarios"+funcionarioDTOs);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");
			
			int lTotalRegistros=funcionarioDTOs.size();
			
			writer.print("<records>" + lTotalRegistros + "</records>");
				for (FuncionarioDTO funcionarioDTO : funcionarioDTOs) {					
						writer.print("<row id='" + funcionarioDTO.getClaveFuncionario()+ "'>");
						writer.print("<cell>" + funcionarioDTO.getClaveFuncionario()+ "</cell>");
						writer.print("<cell>" + funcionarioDTO.getNombreCompleto()+ "</cell>");						
						writer.print("</row>");
				}			
			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.info(e.getCause(), e);

		}
		return null;
	}

	
	public ActionForward consultaContactosTelefonoFuncionario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			String idInvolucrado=request.getParameter("idInvolucrado");
			log.info("%%%%%%%%%%%%Este es el id del funcionario a consultar: "+idInvolucrado);
			FuncionarioDTO funcionarioConsulta = new FuncionarioDTO();
			funcionarioConsulta.setClaveFuncionario(Long.parseLong(idInvolucrado));
//			involucradoDTO.setCalidadDTO(calidadDTO);
			
			FuncionarioDTO funcionario = funcionarioDelegate.obtenerInformacionDefensor(funcionarioConsulta);
			
			List<MedioDeContactoDTO> medioDeContactoDTOs = new ArrayList<MedioDeContactoDTO>();
			 medioDeContactoDTOs= funcionario.getMediosContacto();
			
			converter.alias("medioDeContactoDTOs", java.util.ArrayList.class);
			converter.alias("medioDeContactoDTO", MedioDeContactoDTO.class);
			log.info("tels_medios_contacto:: "+converter.toXML(medioDeContactoDTOs));
			
			log.info("Lista de Telefonos" + medioDeContactoDTOs);
			
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");		

			int lTotalRegistros = medioDeContactoDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			//NSJYUCPROC2011333WT

			for(MedioDeContactoDTO tareaDTO : medioDeContactoDTOs){
				if( tareaDTO instanceof TelefonoDTO){
					TelefonoDTO tareaDTO2 = (TelefonoDTO) tareaDTO;
				writer.print("<row id='"+tareaDTO2.getMedioDeContadoId()+ "'>");
				
				writer.print("<cell>");
				if(tareaDTO2.getValorTipoTelefono() !=null){
					writer.print(tareaDTO2.getValorTipoTelefono().getIdCampo());
						}
				writer.print("</cell>");
				writer.print("<cell>");
				if(tareaDTO2.getValorTipoTelefono() !=null){
					writer.print(tareaDTO2.getValorTipoTelefono().getValor());
						}
				writer.print("</cell>");
				writer.print("<cell>");
				if(tareaDTO2.getCodigoPais() !=null){
					writer.print(tareaDTO2.getCodigoPais());
						}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(tareaDTO2.getCodigoArea() !=null){
					writer.print(tareaDTO2.getCodigoArea());
						}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(tareaDTO2.getNumeroTelefonico() !=null){
					writer.print(tareaDTO2.getNumeroTelefonico());
						}
				writer.print("</cell>");
				
				
				writer.print("</row>");
				}
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public ActionForward consultaContactosCorreoFuncionario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("Consulta Correos");
			String idInvolucrado=request.getParameter("idInvolucrado");
			log.info("%%%%%%%%%%%%Este es el id del funcionario a consultar: "+idInvolucrado);
			FuncionarioDTO funcionarioConsulta = new FuncionarioDTO();
			funcionarioConsulta.setClaveFuncionario(Long.parseLong(idInvolucrado));
//			involucradoDTO.setCalidadDTO(calidadDTO);
			
			FuncionarioDTO funcionario = funcionarioDelegate.obtenerInformacionDefensor(funcionarioConsulta);
			
			List<MedioDeContactoDTO> medioDeContactoDTOs = new ArrayList<MedioDeContactoDTO>();
			medioDeContactoDTOs=funcionario.getMediosContacto();
			//correoElectronicoDTOs= involucradoDTO.getCorreosDTO();
			
			converter.alias("medioDeContactoDTOs", java.util.ArrayList.class);
			converter.alias("MedioDeContactoDTO", CorreoElectronicoDTO.class);
			log.info("tels_medios_contacto:: "+converter.toXML(MedioDeContactoDTO.class));
			
			log.info("Lista de Correo" + medioDeContactoDTOs);
			
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");		

			int lTotalRegistros = medioDeContactoDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			//NSJYUCPROC2011333WT

			for(MedioDeContactoDTO tareaDTO : medioDeContactoDTOs){
				if( tareaDTO instanceof CorreoElectronicoDTO){
					CorreoElectronicoDTO tareaDTO2 = (CorreoElectronicoDTO) tareaDTO;
				writer.print("<row id='"+tareaDTO2.getMedioDeContadoId()+ "'>");
				
				writer.print("<cell>");
				if(tareaDTO2.getDireccionElectronica() !=null){
					writer.print(tareaDTO2.getDireccionElectronica());
						}
				writer.print("</cell>");
				
				
				writer.print("</row>");
				}
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			


		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para obtener la imagen de un funcionario 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward obtenImagenDeFuncionario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try {
        	String idFuncionario = request.getParameter("idFuncionario");
        	FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        	funcionarioDTO.setClaveFuncionario(NumberUtils.toLong(idFuncionario));
        	
        	List<FuncionarioDTO> funcionariosDTO=funcionarioDelegate.consultarFuncionarioXNumeroEmpleado(funcionarioDTO);
        	if(funcionarioDTO != null){
        		FuncionarioDTO primerFuncionario=funcionariosDTO.get(0);	
        	        	
        		response.setContentType("image/gif");
        		if (primerFuncionario.getArchivoDigital()!=null && primerFuncionario.getArchivoDigital().getContenido() != null) {
        			byte[] imag = primerFuncionario.getArchivoDigital().getContenido();
        			if (imag != null) {
        				ServletOutputStream out = response.getOutputStream();
        				out.write(imag);
        				out.close();
        			}
        		}
        	}
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
	
	public ActionForward tieneImagenFuncionario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

		Long valor=0L;
		
        try {
        	String idFuncionario = request.getParameter("idFuncionario");
        
        	FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        	funcionarioDTO.setClaveFuncionario(NumberUtils.toLong(idFuncionario));
        	        	
        	List<FuncionarioDTO> funcionariosDTO=funcionarioDelegate.consultarFuncionarioXNumeroEmpleado(funcionarioDTO);
        	if(funcionarioDTO != null){
        		FuncionarioDTO primerFuncionario=funcionariosDTO.get(0);	
        	        	
        		if (primerFuncionario.getArchivoDigital()!=null && primerFuncionario.getArchivoDigital().getContenido() != null) {
        			valor=1L;
        		}
        	}
        	
        	String xml;
        	xml = "<respuesta>" + valor + "</respuesta>";
        	super.escribirRespuesta(response, xml);                	
        	
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            super.escribirRespuesta(response,"<respuesta>-1</respuesta>" );                	        	
        }   
        
        return null;
    }
	
	public ActionForward consultarFuncionariosCoordinadoresXDicriminante(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

		
        try {
        	Long catDiscriminanteId = NumberUtils.toLong(request.getParameter("catDiscriminanteId"),0L);
        	Long idRol = Roles.COORDINADORAMP.getValorId();
        	Long idUIE = NumberUtils.toLong(request.getParameter("idUIE"),0L);
        
        	        	
        	List<FuncionarioDTO> funcionariosDTO=funcionarioDelegate.consultarFuncionariosPorDicriminanteYRol(catDiscriminanteId, idRol, idUIE);

        	
        	converter.alias("funcionariosDTO", java.util.List.class);
        	converter.alias("funcionarioDTO", FuncionarioDTO.class);
			
			String xml = converter.toXML(funcionariosDTO);
			log.info("funcionarios xml :::" + xml);
			
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
	
	public ActionForward consultarFuncionariosXDicriminanteYRol(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

		
        try {
        	Long catDiscriminanteId = NumberUtils.toLong(request.getParameter("catDiscriminanteId"),0L);
        	Long rolId = NumberUtils.toLong(request.getParameter("rolId"),0L);
       
        	List<FuncionarioDTO> funcionariosDTO=funcionarioDelegate.consultarFuncionariosPorDicriminanteYRol(catDiscriminanteId, rolId, null);

        	
        	converter.alias("funcionariosDTO", java.util.List.class);
        	converter.alias("funcionarioDTO", FuncionarioDTO.class);
			
			String xml = converter.toXML(funcionariosDTO);
			log.info("funcionarios xml :::" + xml);
			
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
}