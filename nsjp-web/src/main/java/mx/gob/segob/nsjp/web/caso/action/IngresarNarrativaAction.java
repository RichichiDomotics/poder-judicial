/**
*
* Nombre del Programa: IngresarNarrativaAction.java                                    
* Autor: Armando Castaneda
* Compania: Ultrasist                                                
* Proyecto: NSJP                    Fecha: 25/02/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Integracion xxxxxxxxxxx                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor: Cuauhtemoc Paredes Serrano                                              
* Compania:Ultrasist                                               
* Proyecto:NSJP                                  Fecha: 1/04/2011       
* Modificacion: Inclusion del catalogo con base de datos.                                                           
*------------------------------------------------------------------------------           
*/

package mx.gob.segob.nsjp.web.caso.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
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
 * Clase Action para Ingresar Narrativa
 * @version 1.0
 * @author Armando Castaneda Tenango
 */

public class IngresarNarrativaAction extends GenericAction{
	/** Log de clase*/
	private static final Logger log  = Logger.getLogger(IngresarNarrativaAction.class);
	/**
	 * Metodo utilizado para realizar la consulta de los tipos de narrativa
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarTiposNarrativa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action Ingresar Narrativa - Consultar Tipos Narrativas");
			
//			List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.TIPO_NARRATIVA);
//
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTipoNarrativa", CatalogoDTO.class);
//			
			String xml = converter.toXML(Collections.EMPTY_LIST);
			
			response.setContentType("text/xml");
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();	
			
			
		} catch (Exception e) {		
			log.info(e);
		}
		return null;
	}	
	
	/**
	 * Metodo utilizado para guardar la narrativa
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward guardarNarrativa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		/**Clase DTO para el catalogo de tipo de narrativa*/
//		CatTipoNarrativaDTO catTipoNarrativaDTO = new CatTipoNarrativaDTO();
//		/**Clase DTO para una narrativa*/
//		NarrativaDTO narrativaDTO = new NarrativaDTO();
//		try {
//			log.info("ejecutando Action Guardar Narrativa.");
//			//Delcaramos las variables locales
//			IngresarNarrativaForm forma;
//			boolean bandera;
//			//Inicializmaos las variables locales
//			forma=null;
//			bandera=false;
//			//hacemos el cast para obtener los datos de la narrativa
//			forma=(IngresarNarrativaForm) form;
//			//llenamos el tipo de narativa
//			catTipoNarrativaDTO.setGcDescripcion(forma.getTipoNarrativa());
//			catTipoNarrativaDTO.setGcEstatus("alta");
//			catTipoNarrativaDTO.setGcNombre(forma.getTipoNarrativa());
//			//seteamos el objeto narrativa DTO
//			narrativaDTO.setTipoNarrativaDTO(catTipoNarrativaDTO);
//			narrativaDTO.setGcDescNarrativa(forma.getTexto());
//			//mandamos guardar la narrativa
//			bandera=iNarrativaBDelegate.guardarNarrativa(forma.getNumeroExpediente(),narrativaDTO);
//		} catch (Exception e) {		
//			log.info(e);
//		}
		return null;
	}	

}
