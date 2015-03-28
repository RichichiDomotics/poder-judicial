/**
* Nombre del Programa 		: CoordenadasGPSAction.java                                    
* Autor                     : JorgeFO
* Compania                  : Ultrasist                                                
* Proyecto                  : NSJP                    Fecha: 23/03/2011 
* Marca de cambio        	: N/A                                                     
* Descripcion General    	: Integracion xxxxxxxxxxx                      
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
package mx.gob.segob.nsjp.web.caso.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Clase action que ejecuta la accion en la pantalla de coordenadas de gps
 * @version 1.0
 * @author JorgeFO
 */
public class CoordenadasGPSAction extends Action{
	
	
	/**
	 * Metodo utilizado para realizar la consulta de una narrativa 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return mostrarNarrativa - En caso de que el proceso de consulta sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		DatosCoordenadasGPSForm gpsForm=(DatosCoordenadasGPSForm)form;
//		DatosCoordenadasGPSDTO datosCoordenadasGPSDTO = new DatosCoordenadasGPSDTO();
//		datosCoordenadasGPSDTO.setLatitud(gpsForm.getLatitud());
//		datosCoordenadasGPSDTO.setLongitud(gpsForm.getLongitud());
//		gpsForm.setLatitud("si"+coordenadasGPSBDelegate.validaDatos(datosCoordenadasGPSDTO));
//		gpsForm.setLongitud("no"+coordenadasGPSBDelegate.validaDatos(datosCoordenadasGPSDTO));
		return null;
////		return mapping.findForward("ok");
	}
}
