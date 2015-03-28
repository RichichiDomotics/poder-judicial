/**
* Nombre del Programa : IngresarMediosContactoAction.java
* Autor               : Andres Gomez Godinez
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 01/03/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Action para Ingresar Medios de contacto
* Programa Dependiente: N/A
* Programa Subsecuente: N/A
* Cond. de ejecucion  : Se manda llamar las acciones desde IngresarMedioDeContacto.jsp 
* Dias de ejecucion   : N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor               :N/A
* Compania            :N/A
* Proyecto            :N/A                                   Fecha: N/A
* Modificacion        :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.caso.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.thoughtworks.xstream.XStream;

/**
* Esta clase se utiliza para ingresar medios de contacto : telefono, correo electronico.
*
* @version 1.0
* @author Andres Gomez Godinez
*/

public class IngresarMediosDeContactoAction extends GenericAction{
	private static final Logger log  = Logger.getLogger(IngresarMediosDeContactoAction.class);
	private XStream converter; 
	/**
	 * Metodo utilizado para realizar la carga del combo tipo telefono 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	
	public ActionForward cargarTipoTelefono(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		try {
//			log.info("Ejecutando Action cargarTipoTelefono"); 
//						
//			ArrayList<CatTipoTelefonoDTO> listaTipoTelefonoDto = null;
//			listaTipoTelefonoDto = iMedioDeContactoBDelegate.consultarTiposDeTelefono();
//			
//			converter.alias("listaTipoTelefono", java.util.List.class);
//			converter.alias("telefono", CatTipoTelefonoDTO.class);
//			
//			String xml = converter.toXML(listaTipoTelefonoDto);
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
	 * @param converter valor de a signar
	 */
	public void setConverter(XStream converter) {
		this.converter = converter;
	}


	

}
