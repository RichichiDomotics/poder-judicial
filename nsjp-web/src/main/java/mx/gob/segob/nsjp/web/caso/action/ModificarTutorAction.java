/**
* Nombre del Programa : ModificarTutorAction.java
* Autor               : Arturo Leon
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 07/03/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Action para Modificar Tutor
* Programa Dependiente: N/A
* Programa Subsecuente: N/A
* Cond. de ejecucion  : N/A
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

/**
 * Clase Action para Modificar Tutor
 * @version 1.0 
 * @author Arturo Leon Galicia
 */

public class ModificarTutorAction extends GenericAction{
	/** Log de clase*/
	private static final Logger log  = Logger.getLogger(ModificarTutorAction.class);
		
	/**
	 * Metodo utilizado para realizar la consulta de los tipos de narrativa
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward mostrarPantallaModificarTutor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		try {
//			log.info("ejecutando Action Modificar Tutor - Mostrar Pantalla Modificar Tutor");
//			//Declaramos las variables locales
//			ArrayList<InvolucradoDTO> lista;
//			String xml;
//			PrintWriter pw;
//			InvolucradoDTO tutor;
//			//Inicializamos las variables
//			lista=null;
//			xml=null;
//			pw=null;
//			tutor= new InvolucradoDTO();
//			//Vamos a consultar los involucrados
//			lista=iInvolucradoBDelegate.consultarIndividuos(null, null, null);
//			//obtengo solo el tutor
//			tutor=lista.get(2);
//			
//			//declaro los alias para los objetos a convertir a xml
//			//converter.alias("listaInvolucrados", java.util.List.class);
//			converter.alias("nombreDG", NombreDemograficoDTO.class);
//			converter.alias("tutor", InvolucradoDTO.class);
//			//convertimos los objetos y creamos la salida xml regresada por AJAX
//			xml = converter.toXML(tutor);
//			response.setContentType("text/xml");
//			pw = response.getWriter();
//			pw.print(xml);
//			pw.flush();
//			pw.close();
//		} catch (Exception e) {		
//			log.info(e);
//		}
		return null;//mapping.findForward("ingresarNarrativa");
	}	
}
