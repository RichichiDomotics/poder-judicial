/**
* Nombre del Programa : IngresarProbableResponsableAction.java
* Autor               : Arturo Leon
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 03/03/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Action para Ingresar Probable responsable
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

import com.thoughtworks.xstream.XStream;

/**
 * 
 * Clase Action para Ingresar Probable responsable
 * 
 * @version 1.0
 * @author Arturo Leon Galicia
 */

public class IngresarProbableResponsableAction extends GenericAction{
	/*Log de clase*/
	private static final Logger log  = Logger.getLogger(IngresarProbableResponsableAction.class);
	/** Clase XStream utilizada en los retornos de XML */
	private XStream converter;
	/**Clase delegadora para Calidad*/
	
	
	//private ICalidadBDelegate calidadBDelegate;

	/**
	 * Metodo utilizado para realizar la carga de los radio button de Condicon del probable responsable 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param responses
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward cargarCondicionProbableResponsable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		try {
//			try{
//				StringTokenizer tknCondicion = null;
//				Properties prop = new Properties();
//				ArrayList<CatCondicionProbableResponsableDTO> lstCondicion = new ArrayList<CatCondicionProbableResponsableDTO>();
//				prop.load(this.getClass().getClassLoader().getResourceAsStream("vista.properties"));
//				tknCondicion = new StringTokenizer(prop.getProperty("ingresarProbableResponsable.condicion"), "|");
//				while (tknCondicion.hasMoreTokens()) {
//					CatCondicionProbableResponsableDTO condicion = new CatCondicionProbableResponsableDTO();
//					String[] valores = tknCondicion.nextToken().split("-");
//					condicion.setGlCatalogoId(Long.valueOf(valores[0]));
//					condicion.setGcDescripcion(valores[1]);
//					lstCondicion.add(condicion);
//				}
//				if (lstCondicion.size()!=0){
//					String xml = null;
//					PrintWriter pw = null;
//					converter.alias("lstCondicion", java.util.List.class);
//					converter.alias("condicionDTO", CatCondicionProbableResponsableDTO.class);
//					xml = converter.toXML(lstCondicion);
//					response.setContentType("text/xml");
//					pw = response.getWriter();
//					pw.print(xml);
//					pw.flush();
//					pw.close();
//				}
//			}catch (Exception e) {
//				e.printStackTrace();
//				log.error(e.getStackTrace());
//			}
//		} catch (Exception e) {		
//			log.info(e);
//		}
		return null;
	}

//	/**
//	 * @param calidadBDelegate el valor de calidadBDelegate a asignar
//	 */
//	public void setCalidadBDelegate(ICalidadBDelegate calidadBDelegate) {
//		this.calidadBDelegate = calidadBDelegate;
//	}

	/**
	 * @param converter el valor de converter a asignar
	 */
	public void setConverter(XStream converter) {
		this.converter = converter;
	}
	
}