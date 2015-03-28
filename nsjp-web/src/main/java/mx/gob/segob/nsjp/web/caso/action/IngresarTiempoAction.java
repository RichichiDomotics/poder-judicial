/**
* Nombre del Programa : IngresarTiempoAction.java                                    
* Autor: Cuauhtemoc Paredes Serrano
* Compania: Ultrasist                                                
* Proyecto: NSJP 
* Fecha: 02/03/2011 
* Marca de cambio: N/A                                                     
* Descripcion General: Integración xxxxxxxxxxx                      
* Programa Dependiente: N/A                                                      
* Programa Subsecuente: N/A                                                      
* Cond. de ejecucion: N/A                                                      
* Dias de ejecucion: N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.web.caso.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.caso.form.IngresarTiempoForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.thoughtworks.xstream.XStream;

/**
 * Clase Action para Ingresar Tiempo
 * 
 * @version 1.0
 * @author Cuauhtemoc Paredes Serrano
 */

public class IngresarTiempoAction extends GenericAction{
	/*Log de clase */
	private static final Logger log = Logger.getLogger(IngresarTiempoAction.class);

	/**Clase para la conversion de los objetos a xml*/
	private XStream converter; 
	
	public ActionForward guardarTiempo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action Guardar Tiempo");
			/**hacemos el cast para obtener los datos de el tiempo*/
			IngresarTiempoForm forma=(IngresarTiempoForm)form;
			/**llenamos el tipo de ingresarTiempo*/
									
			
		} catch (Exception e) {		
			log.info(e);
		}
		return null;
		
		}

	/**
	 * @param converter el converter a asignar
	 */
	public void setConverter(XStream converter) {
		this.converter = converter;
	}

	
	
}
