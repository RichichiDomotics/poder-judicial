/**
* Nombre del Programa 	: AsociarIndividuoAction.java                                    
* Autor               	: Jorge Fernandez                                              
* Compania            	: Ultrasist                                                
* Proyecto            	: NSJP              			Fecha:28/04/2011 
* Marca de cambio     	: N/A                                                     
* Descripcion General   : Clase action para la asociacion de individuos por persona o delito
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
package mx.gob.segob.nsjp.web.caso.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Clase action de asociacion de individuos
 * @version 1.0
 * @author JorgeFO
 */

public class AsociarIndividuoAction extends GenericAction{
	/** Log de clase */
	private static final Logger log = Logger.getLogger(AsociarIndividuoAction.class);

	/**
	 * Metodo utilizado para realizar la carga de catalogo 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward cargaCatalogos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("Ejecutando Action cargaCatalogos");
			List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.VACIO);
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catCatalogo", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
			
			response.setContentType("text/xml");
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();	
			
		}
		catch (Exception e) {		
			log.error(e.getCause(),e);
		}
		return null;
	}
		
}
