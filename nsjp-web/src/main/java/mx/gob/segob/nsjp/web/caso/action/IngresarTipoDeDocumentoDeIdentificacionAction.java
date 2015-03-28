/**
* Nombre del Programa 	: IngresarTipoDeDocumentoDeIdentificacionAction.java                                    
* Autor               	: Andres Gomez                                              
* Compania            	: Ultrasist                                                
* Proyecto            	: NSJP              			Fecha:05/03/2011 
* Marca de cambio     	: N/A                                                     
* Descripcion General   : Clase action para ingresar el tipo de documento de identificacion
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
 * Clase action de ingresar tipo de documento
 * @version 1.0
 * @author AndresGG
 */

public class IngresarTipoDeDocumentoDeIdentificacionAction extends GenericAction{
	/** Log de clase */
	private static final Logger log = Logger
			.getLogger(IngresarTipoDeDocumentoDeIdentificacionAction.class);

	/**
	 * Metodo utilizado para realizar la carga de catalogo de tipo de identificacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward cargaTipoDeDocumentoDeIdentificacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("Ejecutando Action cargaTipoDeDocumentoDeIdentificacion");
			List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.TIPOS_DOCUMENTO_IDENTIFICACION);
//			CatalogoDTO catalogoDTO=new CatalogoDTO((long)1, "gsad");
//			listaCatalogo.add(catalogoDTO);
//			ArrayList <CatTipoIdentificacionDTO> listaCatTipoIdentificacionDto= new ArrayList <CatTipoIdentificacionDTO>();
//			listaCatTipoIdentificacionDto=iCatTipoIdentificacionBDelegate.consultarTiposDocumentoIdentificacion();
//			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTipoIdentificacion", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
			
			response.setContentType("text/xml");
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();	
			
		}
		catch (Exception e) {		
			log.error(e);
		}
		return null;
	}
		
}
