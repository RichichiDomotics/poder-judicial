/**
* Nombre del Programa : IngresarMediosContactoAction.java
* Autor               : Arturo Leon
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 01/04/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Action para Ingresar Medios de contacto
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
package mx.gob.segob.nsjp.web.persona.action;

import java.io.IOException;
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
 * Clase Action para Ingresar Medios de contacto
*
* @version 1.0
* @author Arturo León Galicia
 */
public class IngresarMediosContactoAction extends GenericAction{ 
	/** Log de clase*/
	private static final Logger log  = Logger.getLogger(IngresarMediosContactoAction.class);
	
	/**
	 * Metodo utilizado para realizar la carga del combo Calidad 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward cargarTipoTelefono(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action cargarTipoTelefono");
			List<CatalogoDTO> lstTiposTelefono = null;
			lstTiposTelefono = catDelegate.recuperarCatalogo(Catalogos.TIPO_TELEFONO);
			converter.alias("lstTiposTelefono", java.util.List.class);
			converter.alias("catalogoDTO", CatalogoDTO.class);
			response.setContentType("text/xml");
			escribirRespuesta(response, converter.toXML(lstTiposTelefono));
		} catch (Exception e) {		
			log.info(e);
		}
		return null;
	}
}