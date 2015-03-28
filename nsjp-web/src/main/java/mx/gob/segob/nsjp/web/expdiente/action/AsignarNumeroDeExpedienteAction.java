/**
* Nombre del Programa 	: AsignarNumeroDeExpedienteAction.java                                    
* Autor               	: Andres Gomez Godinez                                              
* Compania            	: Ultrasist                                                
* Proyecto            	: NSJP              			Fecha:15/03/2011 
* Marca de cambio     	: N/A                                                     
* Descripcion General   : Clase action para asignar un numero de expediente
* Programa Dependiente  : N/A                                                      
* Programa Subsecuente 	: N/A                                                      
* Cond. de ejecucion    : struts-config.xml                                                    
* Dias de ejecucion     : N/A                             Horario: N/A
*                               MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                 :N/A                                                           
* Compania              :N/A                                                           
* Proyecto              :N/A                                   Fecha: N/A       
* Modificacion          :N/A                                                           
*------------------------------------------------------------------------------      
*/
package mx.gob.segob.nsjp.web.expdiente.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Clase action para asignar un numero de expediente
 * @version 1.0
 * @author AndresGG 
 */
public class AsignarNumeroDeExpedienteAction extends GenericAction{
	
	
	/**
	 * Metodo utilizado para consultar el ultimo expediente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	
	
	public ActionForward consultarUltimoExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		try {
//			log.info("Ejecutando Action consultarUltimoExpediente"); 
//		
//			String ultimoExpediente="";
//			ultimoExpediente=iExpedienteBDelegate.consultarUltimoExpediente();
//			log.info(ultimoExpediente); 
//			converter.alias("ultimoExpediente", String.class);
//			
//			String xml = converter.toXML(ultimoExpediente);
//			
//			response.setContentType("text/xml");
//			
//			PrintWriter pw = response.getWriter();
//			pw.print(xml);
//			pw.flush();
//			pw.close();		
//		} catch (Exception e) {		
//			log.info(e);
//		}
		return null;
	}
	
	/**
	 * Metodo utilizado para asignar un nuevo expediente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward asignarNuevoexpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		try {
//			log.info("Ejecutando Action Asignar Nuevo Expediente"); 
						
//			String nuevoExpediente="";
//			nuevoExpediente=expedienteDelegate.asignarNumeroExpediente(expedienteDTO);
//			converter.alias("nuevoExpediente", String.class);
//			
//			String xml = converter.toXML(nuevoExpediente);
//			
//			response.setContentType("text/xml");
//			
//			PrintWriter pw = response.getWriter();
//			pw.print(xml);
//			pw.flush();
//			pw.close();		
//		} catch (Exception e) {		
//			log.info(e);
//		}
		return null;
	}
	
}
