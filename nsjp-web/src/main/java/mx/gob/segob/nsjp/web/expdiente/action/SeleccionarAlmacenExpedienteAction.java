/**
* Nombre del Programa 	: SeleccionarAlmacenExpedienteAction.java                                    
* Autor               	: Eduardo Gonzalez                                              
* Compania            	: Ultrasist                                                
* Proyecto            	: NSJP              			Fecha:03/08/2011 
* Marca de cambio     	: N/A                                                     
* Descripcion General   : Clase action que implementa las acciones para el CU Seleccionar almacen de expedientes
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.delegate.almacen.AlmacenDelegate;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *Clase que implementa las acciones para el CU Seleccionar almacen de expedientes
 * @version 1.0
 * @author EduardoG
 */

public class SeleccionarAlmacenExpedienteAction extends GenericAction{
	private static final Logger log  = Logger.getLogger(SeleccionarAlmacenExpedienteAction.class);
		
	@Autowired
	AlmacenDelegate almacenDelegate;
	
	/**
	 * Metodo utilizado para modal de CU Seleccionar almacen de expedientes
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward seleccionarAlmacenExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {			
			log.debug("redireccionando ...");			
		} catch (Exception e) {		
			log.error(e.getCause(),e);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Metodo utilizado para realizar la consulta del catalogo de almacenes activos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarAlmacenes(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
			List<AlmacenDTO> lista = new ArrayList<AlmacenDTO>();
			lista = almacenDelegate.consultarAlmacenesExpedientePorEstatus(true, false); 
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		

			int lTotalRegistros = lista.size();
			writer.print("<records>" + lTotalRegistros + "</records>");

			for(AlmacenDTO dto : lista){

				writer.print("<row id='"+dto.getIdentificadorAlmacen()+ "'>");
				
				writer.print("<cell>");
				if(dto.getNombreAlmacen() != null){
					writer.print(dto.getNombreAlmacen());
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(dto.getDomicilio() != null){
					writer.print(dto.getDomicilio().getDescripcion() 
								 + " " + dto.getDomicilio().getCalle() 
								 + " No. Ext " + dto.getDomicilio().getNumeroExterior());
				}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(dto.getDescripcion() != null){
					writer.print(dto.getDescripcion());
				}
				writer.print("</cell>");
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {		
			log.error(e.getCause(),e);
			
		}
		return null;
	}

}
