/**
* Nombre del Programa : IngresarServidorPublicoAction.java
* Autor               : Arturo Leon
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 09/05/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Action para Ingresar Servidor Publico
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
import mx.gob.segob.nsjp.web.caso.form.IngresarServidorPublicoForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Clase Action para Ingresar servidor publico
*
* @version 1.0
* @author Arturo León Galicia
 */
public class IngresarServidorPublicoAction extends GenericAction{ 
	/** Log de clase*/
	private static final Logger log  = Logger.getLogger(IngresarServidorPublicoAction.class);
	
	/**
	 * Método utilizado para realizar la carga del combo Nivel Dependencias
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward cargarNivelDependencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action cargarNivelDependencia");
			List<CatalogoDTO> lstDatosCombo = null;
			lstDatosCombo = catDelegate.recuperarCatalogo(Catalogos.NIVEL_DEPENDENCIA);
			converter.alias("lstDatosCombo", java.util.List.class);
			converter.alias("catalogoDTO", CatalogoDTO.class);
			response.setContentType("text/xml");
			escribirRespuesta(response, converter.toXML(lstDatosCombo));
		} catch (Exception e) {		
			log.info(e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la carga del combo Tipo Dependencias
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward cargarTipoDependencia(ActionMapping mapping,ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			log.info("ejecutando Action cargarTipoDependencia");
			IngresarServidorPublicoForm forma = (IngresarServidorPublicoForm) form;
			List<CatalogoDTO> lstDatosCombo = null;
			lstDatosCombo = catDelegate.recuperarCatalogoDependiente(Catalogos.TIPO_DEPENDENCIA,new Long(forma.getNivelDependenciaServPublico()));
			converter.alias("lstDatosCombo", java.util.List.class);
			converter.alias("catalogoDTO", CatalogoDTO.class);
			response.setContentType("text/xml");
			escribirRespuesta(response, converter.toXML(lstDatosCombo));
		} catch (Exception e) {
			log.info("RRRRRRRRRRRRRRRRRRRRRRRR ejecutando Action cargarTipoDependencia error"+e.getCause());
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la carga del combo Dependencias
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward cargarDependencias(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action cargarDependencias");
			IngresarServidorPublicoForm forma = (IngresarServidorPublicoForm) form;
			List<CatalogoDTO> lstDatosCombo = null;
			log.info("tipo dependencia en action:" +forma.getTipoDependenciaServPublico());
			lstDatosCombo = catDelegate.recuperarCatalogoDependiente(Catalogos.ORGANIZACION_SECTOR_PUBLICO,new Long(forma.getTipoDependenciaServPublico()));
			converter.alias("lstDatosCombo", java.util.List.class);
			converter.alias("catalogoDTO", CatalogoDTO.class);
			response.setContentType("text/xml");
			escribirRespuesta(response, converter.toXML(lstDatosCombo));
		} catch (Exception e) {		
			log.info(e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la carga del combo Puestos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward cargarPuestos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action cargarPuestos");
			List<CatalogoDTO> lstPuestos = null;
			//TODO:Cambiar por catalogo de puestos
			lstPuestos = catDelegate.recuperarCatalogo(Catalogos.PUESTO_SERVIDOR_PUBLICO);
			converter.alias("lstDependencias", java.util.List.class);
			converter.alias("catalogoDTO", CatalogoDTO.class);
			response.setContentType("text/xml");
			escribirRespuesta(response, converter.toXML(lstPuestos));
		} catch (Exception e) {		
			log.info(e);
		}
		return null;
	}
}