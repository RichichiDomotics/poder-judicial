/**
* Nombre del Programa 		: ConsultarNarrativaAction.java                                    
* Autor                     : Armando Castaneda
* Compania                  : Ultrasist                                                
* Proyecto                  : NSJP                    Fecha: 24/02/2011 
* Marca de cambio        	: N/A                                                     
* Descripcion General    	: Integración xxxxxxxxxxx                      
* Programa Dependiente  	: N/A                                                      
* Programa Subsecuente 		: N/A                                                      
* Cond. de ejecucion        : N/A                                                      
* Dias de ejecucion         : N/A                     Horario: N/A       
*                             MODIFICACIONES                                                              
*------------------------------------------------------------------------------           
* Autor                     :N/A                                                           
* Compania               	:N/A                                                           
* Proyecto                 	:N/A                      Fecha: N/A       
* Modificacion           	:N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.web.expdiente.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Clase Action para Consultar Narrativa
 * @version 1.0
 * @author Armando Castaneda Tenango
 */
public class ConsultarNarrativaAction extends GenericAction{
	
	/** Log de clase*/
	private static final Logger log  = Logger.getLogger(ConsultarNarrativaAction.class);
	
		
	/**
	 * Metodo utilizado para realizar la consulta de una narrativa 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return mostrarNarrativa - En caso de que el proceso de consulta sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarNarrativa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		try {
//			log.info("ejecutando Action Consultar Narrativa");
//			//Declaramos nuestras variables locales
//			NarrativaDTO narrativa;
//			String xml;
//			PrintWriter pw ;
//
//			//inicializamos las variables
//			narrativa=null;
//			xml=null;
//			pw=null ;
//			//consultamos la narrativa
//			narrativa=iNarrativaBDelegate.consultarNarrativa(null);
//			//declaro los alias para los objetos a convertir a xml
//			converter.alias("narrativa", NarrativaDTO.class);
//			//convertimos los objetos y creamos la salida xml regresada por AJAX
//			xml = converter.toXML(narrativa);
//			response.setContentType("text/xml");
//			pw = response.getWriter();
//			pw.print(xml);
//			pw.flush();
//			pw.close();
//		} catch (Exception e) {		
//			log.info(e);
//		}
		return null;
	}
}
