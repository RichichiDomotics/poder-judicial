/**
*
* Nombre del Programa : IngresarOrganizacionAction.java                                    
* Autor                            : Armando Castaneda
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 07/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Integración xxxxxxxxxxx                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
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
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.caso.form.IngresarOrganizacionForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Clase Action para Ingresar Organizacion
 * 
 * @version 1.0
 * @author Armando Castaneda Tenango
 */

public class IngresarOrganizacionAction extends GenericAction{
	private static final Logger log  = Logger.getLogger(IngresarOrganizacionAction.class);
	/**
	 * Método utilizado para realizar la consulta de los tipos de organizacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarTiposOrganizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
    		log.info("ejecutando Action Cargar Combo Tipo Organizacion"); 
    		List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.TIPO_ORGANIZACION);
    		    	
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTipoOrganizacion", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			}
		catch (Exception e) {
			log.info(e);
		}
		return null;
	}	
	
	//consultarCatalogosIngOrganizacion
	
	/**
	 * Método utilizado para realizar la consulta de los catalogos no dependientes de los
	 * distintos tipos de organizacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarCatalogosIngOrganizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
    		log.info("ejecutando Action Cargar Combo Tipo Organizacion"); 
    		StringBuilder sb=new StringBuilder();
    		
    		//Tipos de Organizacion
    		sb.append("<raiz>");
    		List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.TIPO_ORGANIZACION);
    		converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTipoOrganizacion", CatalogoDTO.class);
			sb.append(converter.toXML(listaCatalogo));
			
			//Organizacion Formal
			log.info("ejecutando Action Cargar Combo Tipo Organizacion Formal "); 
			listaCatalogo=null;
    		listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.TIPO_ORGANIZACION_FORMAL);//FORMAL
    		converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTipoOrganizacionFormal", CatalogoDTO.class);
			sb.append(converter.toXML(listaCatalogo));
			
			//Organizacion Comunidad Virtual
			log.info("ejecutando Action Cargar Combo Tipo Organizacion Comunidad Virtual "); 
			listaCatalogo=null;
    		listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.TIPO_COMUNIDAD_VIRTUAL);//COMUNIDAD VIRTUAL
    		converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTipoOrganizacionVirtual", CatalogoDTO.class);
			sb.append(converter.toXML(listaCatalogo));
			
			//Organizacion Nivel Dependencia
			log.info("ejecutando Action Cargar Combo Tipo Organizacion - Niveles de dependencias "); 
			listaCatalogo=null;
    		listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.NIVEL_DEPENDENCIA);//NIVELES DEPENDENCIAS
    		converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catNivelesDependencias", CatalogoDTO.class);
			sb.append(converter.toXML(listaCatalogo));
			
			sb.append("</raiz>");
			
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(sb.toString());
			pw.flush();
			pw.close();
			}
		catch (Exception e) {
			log.info(e);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la carga de Tipos de Dependencias
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarTiposDependencias(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			IngresarOrganizacionForm forma = (IngresarOrganizacionForm) form;
			log.info("ejecutando Action Cargar Combo Tipo DEPENDENCIAS");
			log.info("Nivel dependencia:: "+forma.getGlNivelDependencia()); 
			List<CatalogoDTO> listaTiposDependencias = catDelegate
					.recuperarCatalogoDependiente(Catalogos.TIPO_DEPENDENCIA,
							forma.getGlNivelDependencia());
			converter.alias("listaTipoDependencias", java.util.List.class);
			converter.alias("catTipoDependencias", CatalogoDTO.class);
			log.info("FIN ejecutando Action Cargar Combo Tipo DEPENDENCIAS");
			String xml = converter.toXML(listaTiposDependencias);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la carga de las organizaciones del sector publico
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarOrgsSectorPublico(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			IngresarOrganizacionForm forma = (IngresarOrganizacionForm) form;
			log.info("ejecutando Action Cargar Combo Organizaciones Sector Publico");
			List<CatalogoDTO> listaTiposDependencias = catDelegate
					.recuperarCatalogoDependiente(Catalogos.ORGANIZACION_SECTOR_PUBLICO,
							forma.getGlTipoDependencia());
			converter.alias("listaOrgsSectorPublico", java.util.List.class);
			converter.alias("catOrgsSectorPublico", CatalogoDTO.class);
			log.info("FIN ejecutando Action Cargar Combo Organizaciones Sector Publico");
			String xml = converter.toXML(listaTiposDependencias);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
}
