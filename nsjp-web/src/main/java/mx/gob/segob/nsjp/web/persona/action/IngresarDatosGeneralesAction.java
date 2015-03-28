/**
* Nombre del Programa : IngresarIndividuoAction.java
* Autor               : Erick Arturo
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 23/02/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Action para Ingresar Individuo
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
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * 
 * @author arturo
 *
 *  Action para ingresar datos generales
 *
 */
public class IngresarDatosGeneralesAction extends GenericAction{

private static final Logger log  = Logger.getLogger(IngresarDatosGeneralesAction.class);
//    private ICatEscolaridadBDelegate  catEscolaridadBDelegate;
//    private ICatEstadoCivilBDelegate  catEstadoCivilBDelegate;
//    private ICatIdiomaBDelegate catIdiomaBDelegate;
//    private ICatNacionalidadBDelegate catNacionalidadBDelegate;
//    private ICatOcupacionBDelegate catOcupacionBDelegate;
//    private ICatReligionBDelegate catReligionBDelegate;

public ActionForward consultarCatalogoIdioma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		
    	try {
    		log.info("ejecutando Action Cargar Combo idioma"); 
    		List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.IDIOMA);
    		
    		
    		
    	
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catIdioma", CatalogoDTO.class);
			
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
			
			
//			e.printStackTrace();
//		}
		return null;

		
		
		
	}
    
    public ActionForward consultarCatalogoEstadoCivil(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
    	try {
    		log.info("ejecutando Action Cargar Combo Estado civil"); 
    		List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.ESTADO_CIVIL);
    		
    		
    		
    	
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catEstadoCivil", CatalogoDTO.class);
			
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
			
			
//			e.printStackTrace();
//		}

		return null;
	}
    
	public ActionForward consultarCatalogoEscolaridad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
    		log.info("ejecutando Action Cargar Combo Escolaridad"); 
    		List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.ESCOLARIDAD);
    		
    		
    		
    	
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catEscolaridad", CatalogoDTO.class);
			
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
			
			
//			e.printStackTrace();
//		}

		return null;
		
		
	}
	
	public ActionForward consultarCatalogoNacionalidad (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
    		log.info("ejecutando Action Cargar Combo Nacionalidad"); 
    		List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.NACIONALIDAD);
    		
    		
    		
    	
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catNacionalidad", CatalogoDTO.class);
			
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
			
			
//			e.printStackTrace();
//		}

		return null;
		
		
	}
	
	
	public ActionForward consultarCatalogoReligion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
    		log.info("ejecutando Action Cargar Combo Religion"); 
    		List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.RELIGION);
    		
    		
    		
    	
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catReligion", CatalogoDTO.class);
			
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
			
			
//			e.printStackTrace();
//		}

		return null;
		
		
	}
	
	public ActionForward consultarCatalogoOcupacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
    		log.info("ejecutando Action Cargar Combo Ocupacion"); 
    		List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.OCUPACION);
    		
    		
    		
    	
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catOcupacion", CatalogoDTO.class);
			
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
			
			
//			e.printStackTrace();
//		}
		return null;
		
		
	}

	public ActionForward consultarCatalogoServicioPericial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
    		log.info("ejecutando Action Cargar Combo Servicios Periciales"); 
    		List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.TIPO_SOLICITUD);
    		
    		
    		
    	
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catServicioPericial", CatalogoDTO.class);
			
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

	public ActionForward consultarCatalogoEspecialidadPericial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
    		log.info("ejecutando Action Cargar Combo Especialidad Pericial"); 
    		List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.TIPO_ESPECIALIDAD_FUNCIONARIO);
    		
    		
    		
    	
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catEspecialidadPericial", CatalogoDTO.class);
			
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
	
	public ActionForward consultarFechaCaptura(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
    		log.info("ejecutando Action Fecha actual"); 
    		
			Date fechaCaptura=new Date();
			SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy");
			String fech=formato.format(fechaCaptura);
			    	
			converter.alias("fechaActual", java.lang.String.class);
						
			String xml = converter.toXML(fech);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			}
		catch (Exception e) {
			log.info(e);
		}
			
			
//			e.printStackTrace();
//		}
		return null;
		
		
	}
	
	public ActionForward consultarHoraCaptura(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
    		log.info("ejecutando Action Hora actual"); 
    		
			Date horaCaptura=new Date();
			SimpleDateFormat formato= new SimpleDateFormat("HH:mm:ss");
			String hora=formato.format(horaCaptura);
			
			converter.alias("horaActual", java.lang.String.class);
			
			String xml = converter.toXML(hora);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			}
		catch (Exception e) {
			log.info(e);
		}
			
			
//			e.printStackTrace();
//		}
		return null;
		
		
	}
	
		
		
		
		
}
