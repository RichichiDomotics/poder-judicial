/**
*
* Nombre del Programa: IngresarMediaFiliacionAction.java                                    
* Autor: Cuauhtemoc Paredes Serrano
* Compania: Ultrasist                                                
* Proyecto: NSJP                    Fecha: 03/03/2011 
* Marca de cambio: N/A                                                     
* Descripcion General: Action que carga informacion de media filiacion                    
* Programa Dependiente:N/A                                                      
* Programa Subsecuente:N/A                                                      
* Cond. de ejecucion:N/A                                                      
* Dias de ejecucion:N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor:N/A                                                           
* Compania:N/A                                                           
* Proyecto:N/A                                   Fecha: N/A       
* Modificacion:N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.web.persona.action;

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

import com.thoughtworks.xstream.XStream;

/**
 *  Clase Action que llena combos de media filiacion
 *  
 * @version 1.0
 * @author CuauhtemocPS Ultrasist
 *
 * 
 */
public class IngresarMediaFiliacionAction extends GenericAction{
	/* Log de clase */
	private static final Logger log = Logger
			.getLogger(IngresarMediaFiliacionAction.class);

	
	public ActionForward consultarCatalogoTamanoBoca(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR TamanoBoca------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TAMANIO_BOCA);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTamanoBoca", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	
	public ActionForward consultarCatalogoTipoCara(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR TipoCara------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_CARA);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTipoCara", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	
	public ActionForward consultarCatalogoFormaMenton(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR FormaMenton------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.FORMA_MENTON);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catFormaMenton", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	
	public ActionForward consultarCatalogoTipoMenton(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR TipoMenton------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_MENTON);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTipoMenton", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	
	public ActionForward consultarCatalogoTez(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR Tez------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TEZ);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTez", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	
	public ActionForward consultarCatalogoInclinacionMenton(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR InclinacionMenton------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.INCLINIACION_MENTON);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catInclinacionMenton", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	
	public ActionForward consultarCatalogoColorCabello(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR ColorCabello------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.COLOR_CABELLO);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catColorCabello", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoFormaCabello(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR FormaCabello------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.FORMA_CABELLO);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catFormaCabello", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoCalvieTipo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR CalvieTipo------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.CALVICIE_TIPO);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catCalvieTipo", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoCabelloImplantacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR CabelloImplantacion------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.CABELLO_IMPLANTACION);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catCabelloImplantacion", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoCantidadCabello(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR CantidadCabello------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.CANTIDAD_CABELLO);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catCantidadCabello", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoOreja(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR Oreja------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.FORMA_OREJA);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catOreja", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoTamanoOreja(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR tamañoOreja------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.OREJA_TAMANIO);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTamanoOreja", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoLobuloParticularidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR LobuloParticularidad------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.OREJA_LOBULO_PARTICULARIDAD);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catLobuloParticularidad", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoLobuloDimension(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR LobuloDimension------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.OREJA_LOBULO_DIMENSION);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catLobuloDimension", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoLobuloAdherencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR LobuloAdherencia------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.OREJA_LOBULO_ADHERENCIA);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catLobuloAdherencia", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoHelixAnterior(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR HelixAnterior------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.HELIX_SUPERIOR);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catHelixAnterior", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoHelixPosterior(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR HelixPosterior------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.HELIX_POSTERIOR);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catHelixPosterior", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoHelixContorno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR HelixContorno------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.LOBULO_CONTORNO);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catHelixContorno", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoHelixAdherencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR HelixAdherencia------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.HELIX_ADHERENCIA);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catHelixAdherencia", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoFormaOreja(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR FormaOreja------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.FORMA_OREJA);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catFormaOreja", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoFormaOjos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR FormaOjos------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.FORMA_OJO);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catFormaOjos", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoAlturaNasoLabial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			log.info("-------------EJECUTANDO ACTION CONSULTAR CatalogoAlturaNasoLabial------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.ALTURA_NASO_LABIAL);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catAlturaNasoLabial", CatalogoDTO.class);

			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoComisuras(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			log.info("-------------EJECUTANDO ACTION CONSULTAR FormaOjos------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.COMISURAS_LABIOS);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catCatalogoComisuras", CatalogoDTO.class);

			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoEspesorLabioInferior(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			log.info("-------------EJECUTANDO ACTION CONSULTAR ConsultarCatalogoEspesorLabioInferior------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.ESPESOR_LABIO);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catEspesorLabioInferior", CatalogoDTO.class);

			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoEspesorLabioSuperior(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			log.info("-------------EJECUTANDO ACTION CONSULTAR ConsultarCatalogoEspesorLabioSuperior------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.ESPESOR_LABIO);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catEspesorLabioSuperior", CatalogoDTO.class);

			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoProminencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			log.info("-------------EJECUTANDO ACTION CONSULTAR ConsultarCatalogoProminencia------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.PROMINENCIA_LABIOS);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catProminencia", CatalogoDTO.class);

			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoAnchoNariz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			log.info("-------------EJECUTANDO ACTION CONSULTAR ConsultarCatalogoAnchoNariz------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.ANCHO_NARIZ);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catAnchoNariz", CatalogoDTO.class);

			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoAlturaNariz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			log.info("-------------EJECUTANDO ACTION CONSULTAR ConsultarCatalogoAlturaNariz------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.ALTURA_NARIZ);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catAlturaNariz", CatalogoDTO.class);

			String xml = converter.toXML(listaCatalogo);
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
	public ActionForward consultarCatalogoBaseNariz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			log.info("-------------EJECUTANDO ACTION CONSULTAR consultarCatalogoBaseNariz------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.BASE_NARIZ);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catBaseNariz", CatalogoDTO.class);

			String xml = converter.toXML(listaCatalogo);
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
        
	public ActionForward consultarCatalogoRaizNariz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			log.info("-------------EJECUTANDO ACTION CONSULTAR consultarCatalogoRaizNariz------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.RAIZ_NARIZ);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catRaizNariz", CatalogoDTO.class);

			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoFrenteAltura(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			log.info("-------------EJECUTANDO ACTION CONSULTAR consultarCatalogoFrenteAltura------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.FRENTE_ALTURA);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catFrenteAltura", CatalogoDTO.class);

			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoFrenteAncho(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			log.info("-------------EJECUTANDO ACTION CONSULTAR ConsultarCatalogoFrenteAncho------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.FRENTE_ANCHO);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catFrenteAncho", CatalogoDTO.class);

			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoInclinacionFrente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			log.info("-------------EJECUTANDO ACTION CONSULTAR ConsultarCatalogoInclinacionFrente------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.FRENTE_INCLINACION);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catInclinacionFrente", CatalogoDTO.class);

			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoTamanoOjos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR TamanoOjos------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TAMANIO_OJO);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTamanoOjos", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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

	public ActionForward consultarCatalogoColorOjos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR ColorOjos------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.COLOR_OJO);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catColorOjos", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	 * Metodo utilizado para realizar la carga de los combos media filiacion en Implantacion ceja
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarCatalogoImplantacionCeja(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR Implantacion------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.IMPLANTACION_CEJA);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catImplantacionCeja", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	 * Metodo utilizado para realizar la carga de los combos media filiacion en Forma ceja
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarCatalogoFormaCeja(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR Forma------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.FORMA_CEJA);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catFormaCeja", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	 * Metodo utilizado para realizar la carga de los combos media filiacion en tamaño ceja
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarCatalogoTamanoCeja(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR Forma------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TAMANIO_CEJA);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTamanoCeja", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	 * Metodo utilizado para realizar la carga de los combos media filiacion en Tipo de sangre
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarCatalogoTipoSangre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR Forma------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.TIPO_SANGRE);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTipoSangre", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	 * Metodo utilizado para realizar la carga de los combos media filiacion en Tipo de Complexion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarComplexion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR Forma------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.COMPLEXION);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catComplexion", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	 * Metodo utilizado para realizar la carga de los combos media filiacion en Tipo de direccionCeja
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente 
	 */
	public ActionForward consultarDireccionCeja(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR Forma------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.DIRECCION_CEJA);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catdireccionCeja", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	 * Metodo utilizado para realizar la carga de los combos media filiacion en Tipo de direccionCeja
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente 
	 */
	public ActionForward consultarHelixOriginal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR Forma------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.HELIX_ORIGINAL);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("cathelixOriginal", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	 * Metodo utilizado para realizar la carga de los combos media filiacion en Tipo de direccionCeja
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente 
	 */
	public ActionForward consultarOrejaLobContorno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("-------------EJECUTANDO ACTION CONSULTAR Forma------------");
			List<CatalogoDTO> listaCatalogo = catDelegate.recuperarCatalogo(Catalogos.HELIX_ORIGINAL);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catOrejaLobContorno", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
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
	 * Metodo utilizado para realizar la carga de los combos media filiacion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente 
	 */
	public ActionForward cargarCombos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		try {
//			log.info("ejecutando Action llenarcomboMediaFiliacion cargarCombos");
//			
//			ArrayList<CatColorCabelloDTO> listaCatColorCabello = null;
//			listaCatColorCabello = mediaFiliacionBDelegate.consultarCatalogoColorCabello();
//			
//			ArrayList<CatColorOjosDTO> listaCatColorOjos = null;
//			listaCatColorOjos = mediaFiliacionBDelegate.consultarCatalogoColorOjos();
//			
//			ArrayList<CatComplexionDTO> listaCatComplexion = null;
//			listaCatComplexion = mediaFiliacionBDelegate.consultarCatalogoComplexion();
//			
//			ArrayList<CatFormaBocaDTO> listaCatFormaBoca = null;
//			listaCatFormaBoca = mediaFiliacionBDelegate.consultarCatalogoFormaBoca();
//			
//			ArrayList<CatFormaCabelloDTO> listaCatFormaCabello = null;
//			listaCatFormaCabello = mediaFiliacionBDelegate.consultarCatalogoFormaCabello();
//			
//			ArrayList<CatFormaCaraDTO> listaCatFormaCara = null;
//			listaCatFormaCara = mediaFiliacionBDelegate.consultarCatalogoFormaCara();
//			
//			ArrayList<CatFormaCejasDTO> listaCatFormaCejas = null;
//			listaCatFormaCejas = mediaFiliacionBDelegate.consultarCatalogoFormaCejas();
//			
//			ArrayList<CatFormaFrenteDTO> listaCatFormaFrente = null;
//			listaCatFormaFrente = mediaFiliacionBDelegate.consultarCatalogoFormaFrente();
//			
//			ArrayList<CatFormaMentonDTO> listaCatFormaMenton = null;
//			listaCatFormaMenton = mediaFiliacionBDelegate.consultarCatalogoFormaMenton();
//			
//			ArrayList<CatFormaNarizDTO> listaCatFormaNariz = null;
//			listaCatFormaNariz = mediaFiliacionBDelegate.consultarCatalogoFormaNariz();
//			
//			ArrayList<CatFormaOjosDTO> listaCatFormaOjos = null;
//			listaCatFormaOjos = mediaFiliacionBDelegate.consultarCatalogoFormaOjos();
//			
//			ArrayList<CatFormaOrejaDTO> listaCatFormaOreja = null;
//			listaCatFormaOreja = mediaFiliacionBDelegate.consultarCatalogoFormaOreja();
//			
//			ArrayList<CatGrosorCejasDTO> listaCatGrosorCejas = null;
//			listaCatGrosorCejas = mediaFiliacionBDelegate.consultarCatalogoGrosorCejas();
//			
//			ArrayList<CatTezDTO> listaCatTez = null;
//			listaCatTez = mediaFiliacionBDelegate.consultarCatalogoTez();
//			
//			ArrayList<CatTipoCabelloDTO> listaCatTipoCabello = null;
//			listaCatTipoCabello = mediaFiliacionBDelegate.consultarCatalogoTipoCabello();
//			
//			ArrayList<CatTipoCaraDTO> listaCatTipoCara = null;
//			listaCatTipoCara = mediaFiliacionBDelegate.consultarCatalogoTipoCara();
//			
//			StringBuilder sb=new StringBuilder();
//			converter.alias("catalogo", java.util.List.class);
//			
//			//agregamos el catalogo  de color de cabello XML
//			converter.alias("CatColorCabello", CatColorCabelloDTO.class);
//			sb.append(converter.toXML(listaCatColorCabello));
//			
//			//agregamos el catalogo  de color de ojos al XML
//			converter.alias("CatColorOjos", CatColorOjosDTO.class);
//			sb.append(converter.toXML(listaCatColorOjos));
//		
//			//agregamos el catalogo  de complexion al XML
//			converter.alias("CatComplexion", CatComplexionDTO.class);
//			sb.append(converter.toXML(listaCatComplexion));
//			
//			//agregamos el catalogo  de forma de boca al XML
//			converter.alias("CatFormaBoca", CatFormaBocaDTO.class);
//			sb.append(converter.toXML(listaCatFormaBoca));
//			
//			//agregamos el catalogo  de la forma de cabello al XML
//			converter.alias("CatFormaCabello", CatFormaCabelloDTO.class);
//			sb.append(converter.toXML(listaCatFormaCabello));
//			
//			//agregamos el catalogo  de forma cara al XML
//			converter.alias("CatFormaCara", CatFormaCaraDTO.class);
//			sb.append(converter.toXML(listaCatFormaCara));
//			
//			//agregamos el catalogo  de forma cejas al XML
//			converter.alias("CatFormaCejas", CatFormaCejasDTO.class);
//			sb.append(converter.toXML(listaCatFormaCejas));
//			
//			//agregamos el catalogo  de Forma de la frente al XML
//			converter.alias("CatFormaFrente", CatFormaFrenteDTO.class);
//			sb.append(converter.toXML(listaCatFormaFrente));
//			
//			//agregamos el catalogo  de la forma el menton
//			converter.alias("CatFormaMenton", CatFormaMentonDTO.class);
//			sb.append(converter.toXML(listaCatFormaMenton));
//			
//			//agregamos el catalogo  de nariz XML
//			converter.alias("CatFormaNariz", CatFormaNarizDTO.class);
//			sb.append(converter.toXML(listaCatFormaNariz));
//			
//			//agregamos el catalogo  de forma de ojos al XML
//			converter.alias("CatFormaOjos", CatFormaOjosDTO.class);
//			sb.append(converter.toXML(listaCatFormaOjos));
//			
//			//agregamos el catalogo  de forma de orejas al XML
//			converter.alias("CatFormaOreja", CatFormaOrejaDTO.class);
//			sb.append(converter.toXML(listaCatFormaOreja));
//			
//			//agregamos el catalogo  del grosor de cejas al XML
//			converter.alias("CatGrosorCejas", CatGrosorCejasDTO.class);
//			sb.append(converter.toXML(listaCatGrosorCejas));
//			
//			//agregamos el catalogo  de tez al XML
//			converter.alias("CatTez", CatTezDTO.class);
//			sb.append(converter.toXML(listaCatTez));
//			
//			//agregamos el catalogo  de tipo cabello al XML
//			converter.alias("CatTipoCabello", CatTipoCabelloDTO.class);
//			sb.append(converter.toXML(listaCatTipoCabello));
//			
//			//agregamos el catalogo  de tipo cara al XML
//			converter.alias("CatTipoCara", CatTipoCaraDTO.class);
//			sb.append(converter.toXML(listaCatTipoCara));
//			
//			//Cerramos el XML						
//			String xml="<catalogoRaiz>"+sb.toString()+"</catalogoRaiz>";
//						 
//			response.setContentType("text/xml");				
//			PrintWriter pw = response.getWriter();
//			pw.print(xml);
//			pw.flush();
//			pw.close();
//		}
//		catch (Exception e) {		
//			log.error(e);
//		}
		return null;
}

	/**
	 * Metodo utilizado para guardar datos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward guardarDatos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		//Guarda datos
		try {
			log.info("ejecutando Action guardarDatos");

//			IngresarMediaFiliacionForm forma = (IngresarMediaFiliacionForm) form;
//			forma.getBarba();
//			forma.getBigote();
//			forma.getEstatura();
//			forma.getLentes();
//			forma.getPerfil();
//			forma.getPeso();
//			forma.getColorCabelloDesc();

			// ArrayList<MediaFiliacionDTO> lstCatCabello = null;
			// System.out.println(mediaFiliacionBDelegate);
			// lstCatCabello = mediaFiliacionBDelegate.obtenerCatColorOjos();
			//
			// converter.alias("listaCatOjos", java.util.List.class);
			// converter.alias("ojos", MediaFiliacionDTO.class);
			// System.out.println("action 1");
			// String xml = converter.toXML(lstCatCabello);
			// System.out.println("action 2");
			// response.setContentType("text/xml");
			// System.out.println("action 3");
			// PrintWriter pw = response.getWriter();
			// pw.print(xml);
			// pw.flush();
			// pw.close();
			// System.out.println("action 4");

		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * @param converter
	 *            the converter to set
	 */
	public void setConverter(XStream converter) {
		this.converter = converter;
	}

}
