/**
* Nombre del Programa 	: ConsultarIndividuoAction.java                                    
* Autor               	: Alejandro Galaviz, Cuauhtemoc Paredes, Jose Luis Perez, Jorge Fernández                                              
* Compania            	: Ultrasist                                                
* Proyecto            	: NSJP              			Fecha:03/03/2011 
* Marca de cambio     	: N/A                                                     
* Descripcion General   : Acciones para el CU consultar individuo
* Programa Dependiente  : N/A                                                      
* Programa Subsecuente 	: N/A                                                      
* Cond. de ejecucion    : N/A                                                   
* Dias de ejecucion     : N/A                           Horario: N/A
*                               MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                 :Jorge Ignacio Fernandez Ortiz                                                           
* Compania              :Ultrasist                                                           
* Proyecto              :NSJP                           Fecha: 08/03/2011       
* Modificacion          :Se modifica el for que llena el grid para que incluya todos los 
* 						 nombres de todos los involucrados.                                                          
*------------------------------------------------------------------------------      
*/
package mx.gob.segob.nsjp.web.persona.action;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.MediaFiliacionDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDetencionDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase que implementa las acciones para el CU consultar individuo
 * @version 1.0
 * @author Alejandro Galavíz
 */
public class ConsultarIndividuoAction extends GenericAction{
	/**Log de clase*/
	private static final Logger log  = Logger.getLogger(
			ConsultarIndividuoAction.class);
	private Short CALIDAD_DENUNCIANTE = new Short((short) 4);

	@Autowired
	public InvolucradoDelegate involucradoDelegate;
	
	/**
	 * Metodo utilizado para realizar la carga del combo Calidad 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward cargarCalidadIndividuo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
//		
//		try {
//			log.info("ejecutando Action Cargar Combo");			
//			ArrayList<CalidadDTO> lstCalidadDTO = null;
//			lstCalidadDTO = calidadBDelegate.obtenerCalidadCmb();
//			log.info("lstCalidad::::IngresarIndividuoAction"+lstCalidadDTO);
//			log.info("lista"+lstCalidadDTO.get(0).getGcDescripcion());
//			converter.alias("listaCalidades", java.util.List.class);
//			converter.alias("calidades", CalidadDTO.class);				
//			String xml = converter.toXML(lstCalidadDTO);				
//			response.setContentType("text/xml");				
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
	 * Metodo utilizado para realizar la carga de la tabla que se presenta
	 * en la JSP consultar individuo en calidad de traductor-interprete
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarCalidadIndividuo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
//		
//		try {
//			log.info("ejecutando Action Consultar");
//			
//			ArrayList<InvolucradoDTO> lstInvolucradoDTO = 
//				new  ArrayList<InvolucradoDTO>();
//			/**FORMA EN LA QUE SE RECIBIRA EL NO. DE EXPEDIENTE Y 
//			 * EL ID DE LA CALIDAD
//			 * 
//			 */
//			ConsultarIndividuoForm forma=(ConsultarIndividuoForm) form;
//			log.info("NO DE EXPEDIENTE="+forma.getNoExpediente());
//			log.info("CALIDAD DEL INDIVIDUO="+forma.getCalidadIndividuo());
//			
//			lstInvolucradoDTO = involucradoBDelegate.consultarIndividuos(null, null, null);		
//			response.setContentType("text/xml; charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache");
//			PrintWriter writer = response.getWriter();
//			List<NombreDemograficoDTO> lstNombreDemograficoDTO = null;
//			
//			writer.print("<rows>");
//			int lTotalRegistros=0;
//			for (InvolucradoDTO involucradoDTO : lstInvolucradoDTO) {
//				lTotalRegistros=+involucradoDTO.getListaNombreDemograficoDTO().size();
//			}
//			writer.print("<records>" + lTotalRegistros + "</records>");
//			for (InvolucradoDTO involucradoDTO : lstInvolucradoDTO) {
//				lstNombreDemograficoDTO = involucradoDTO.getListaNombreDemograficoDTO();
//				for (NombreDemograficoDTO nombreDemograficoDTO : lstNombreDemograficoDTO) {
//					writer.print("<row id='" + nombreDemograficoDTO.getGlNombreDemograficoId() + "'>");
//					writer.print("<cell><![CDATA[<a href='#' onclick='consultaDetalleIndividuo(" + involucradoDTO.getGlInvolucradoId() +","+nombreDemograficoDTO.getGlNombreDemograficoId()+ "  )' title='Consulta a detalle individuo'>" + nombreDemograficoDTO.getGcNombre() + "</a>]]></cell>");
//					writer.print("<cell>" + nombreDemograficoDTO.getGcApellidoPaterno() + "</cell>");
//					writer.print("<cell>" + nombreDemograficoDTO.getGcApellidoMaterno() + "</cell>");
//					writer.print("</row>");
//				}
//			}
//			
//			
//			
//			writer.print("</rows>");
//			writer.flush();
//			writer.close();
//			
//			
//		} catch (Exception e) {		
//			log.info(e);
//			log.info("#####"+e.getCause());
//		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para consultar los datos del involucrado
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarDetalleInvolucrado(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
//		try {
//			
//			log.info("ejecutando ConsultarIndividuoAction.consultarDetalleInvolucrado");
//			ConsultarIndividuoForm forma=(ConsultarIndividuoForm) form;
//			InvolucradoDTO involucrado=new InvolucradoDTO();
//			involucrado.setGlInvolucradoId(Long.parseLong(forma.getIdIndividuo()));
//			InvolucradoDTO invol=involucradoBDelegate.consultarDetalleInvolucrado(involucrado);
//			Date lFechaActual=involucradoBDelegate.obtenerFechaActual();
//			Long idNombreDemografico=Long.parseLong(forma.getIdNombreDemografico());
//			ArrayList<NombreDemograficoDTO> listaNombreDemograficoDTOs=invol.getListaNombreDemograficoDTO();
//			NombreDemograficoDTO seleccionNombreDemograficoDTO=null;
//			for (NombreDemograficoDTO nombreDemograficoDTO : listaNombreDemograficoDTOs) {
//				if(nombreDemograficoDTO.getGlNombreDemograficoId()==idNombreDemografico){
//					seleccionNombreDemograficoDTO=nombreDemograficoDTO;
//				}
//			}
//			//Queda pendiente el caso de uso.
//			
//		}catch (Exception e) {
//			log.error(e);
//			log.info(e.getMessage());
//		}
//		
//		
		return null;
	}
	
	
	/**
	 * Metodo utilizado para consultar los datos del involucrado
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarInvolucrado(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
		try{
			String idInvolucrado=request.getParameter("idInvolucrado");
			log.info("%%%%%%%%%%%%Este es el id del involucrado a consultar: "+idInvolucrado);
			InvolucradoDTO involucradoDTO=new InvolucradoDTO();
			involucradoDTO.setElementoId(Long.parseLong(idInvolucrado));
//			involucradoDTO.setCalidadDTO(calidadDTO);
			involucradoDTO=involucradoDelegate.obtenerInvolucrado(involucradoDTO);
			if(involucradoDTO.getDomicilio()!=null){
				if(involucradoDTO.getDomicilio().getLatitud()!=null && !involucradoDTO.getDomicilio().getLatitud().equals("")){
					String latitud=involucradoDTO.getDomicilio().getLatitud();
					
					String subLatitud=latitud.substring(1,latitud.length());//quitamos la letra de la cadena
					String[] arr=subLatitud.split("°");//separlo los grados de los minutos y segundos
					String[] arrDos=arr[1].split("'");//separamos los minutos y segundos
					String segundos=arrDos[1].substring(0,arrDos[1].length()-1);
					//seteamos los valores
					involucradoDTO.getDomicilio().setLatitudN(latitud.substring(0,1));log.info("domicilio hechoDTO NO NULL!!! 1");
					involucradoDTO.getDomicilio().setLatitudGrados(arr[0]);log.info("domicilio hechoDTO NO NULL!!! 2");
					involucradoDTO.getDomicilio().setLatitudMinutos(arrDos[0]);log.info("domicilio hechoDTO NO NULL!!! 3");
					involucradoDTO.getDomicilio().setLatitudSegundos(segundos);log.info("domicilio hechoDTO NO NULL!!! 4");
				}
				if(involucradoDTO.getDomicilio().getLongitud()!=null && !involucradoDTO.getDomicilio().getLongitud().equals("")){
					String longitud=involucradoDTO.getDomicilio().getLongitud();
					
					String subLongitud=longitud.substring(1,longitud.length());//quitamos la letra de la cadena
					String[] arr=subLongitud.split("°");//separlo los grados de los minutos y segundos
					String[] arrDos=arr[1].split("'");//separamos los minutos y segundos
					String segundos=arrDos[1].substring(0,arrDos[1].length()-1);
					
					involucradoDTO.getDomicilio().setLongitudE(longitud.substring(0,1));log.info("domicilio hechoDTO NO NULL!!! 5");
					involucradoDTO.getDomicilio().setLongitudGrados(arr[0]);log.info("domicilio hechoDTO NO NULL!!! 6");
					involucradoDTO.getDomicilio().setLongitudMinutos(arrDos[0]);log.info("domicilio hechoDTO NO NULL!!! 7");
					involucradoDTO.getDomicilio().setLongitudSegundos(segundos);log.info("domicilio hechoDTO NO NULL!!! 8");
				}
			}
			
			if(involucradoDTO.getDomicilioNotificacion()!=null){
				if(involucradoDTO.getDomicilioNotificacion().getLatitud()!=null && !involucradoDTO.getDomicilioNotificacion().getLatitud().equals("")){
					String latitud=involucradoDTO.getDomicilioNotificacion().getLatitud();
					
					String subLatitud=latitud.substring(1,latitud.length());//quitamos la letra de la cadena
					String[] arr=subLatitud.split("°");//separlo los grados de los minutos y segundos
					String[] arrDos=arr[1].split("'");//separamos los minutos y segundos
					String segundos=arrDos[1].substring(0,arrDos[1].length()-1);
					//seteamos los valores
					involucradoDTO.getDomicilioNotificacion().setLatitudN(latitud.substring(0,1));log.info("domicilio hechoDTO NO NULL!!! 1");
					involucradoDTO.getDomicilioNotificacion().setLatitudGrados(arr[0]);log.info("domicilio hechoDTO NO NULL!!! 2");
					involucradoDTO.getDomicilioNotificacion().setLatitudMinutos(arrDos[0]);log.info("domicilio hechoDTO NO NULL!!! 3");
					involucradoDTO.getDomicilioNotificacion().setLatitudSegundos(segundos);log.info("domicilio hechoDTO NO NULL!!! 4");
				}
				if(involucradoDTO.getDomicilioNotificacion().getLongitud()!=null && !involucradoDTO.getDomicilioNotificacion().getLongitud().equals("")){
					String longitud=involucradoDTO.getDomicilioNotificacion().getLongitud();
					
					String subLongitud=longitud.substring(1,longitud.length());//quitamos la letra de la cadena
					String[] arr=subLongitud.split("°");//separlo los grados de los minutos y segundos
					String[] arrDos=arr[1].split("'");//separamos los minutos y segundos
					String segundos=arrDos[1].substring(0,arrDos[1].length()-1);
					
					involucradoDTO.getDomicilioNotificacion().setLongitudE(longitud.substring(0,1));log.info("domicilio hechoDTO NO NULL!!! 5");
					involucradoDTO.getDomicilioNotificacion().setLongitudGrados(arr[0]);log.info("domicilio hechoDTO NO NULL!!! 6");
					involucradoDTO.getDomicilioNotificacion().setLongitudMinutos(arrDos[0]);log.info("domicilio hechoDTO NO NULL!!! 7");
					involucradoDTO.getDomicilioNotificacion().setLongitudSegundos(segundos);log.info("domicilio hechoDTO NO NULL!!! 8");
				}
			}
			
			if (log.isDebugEnabled()) {
				log.debug("::::::::::::::INVOLUCRADO:::::::::Consulta:::::::::::"+ involucradoDTO);
			}
			//Esta linea se aplica ya ke como carga el DTO de datos demograficos al consultar el xml duplica los nombres
			//involucradoDTO.setDetenciones(null);
			String xml = null;
			PrintWriter pw = null;
			converter.alias("involucradoDTO",InvolucradoDTO.class);
			converter.alias("AsentamientoDTO",AsentamientoDTO.class);
			converter.alias("mediaFiliacionDTO",MediaFiliacionDTO.class);
			converter.alias("MunicipioDTO",MunicipioDTO.class);
			converter.alias("domicilioDTO",DomicilioDTO.class);
			converter.alias("EntidadFederativaDTO",EntidadFederativaDTO.class);
			converter.alias("aliasInvolucradoDTO",AliasInvolucradoDTO.class);
			converter.alias("DetencionDTO",DetencionDTO.class);
			//Agregados para la consulta de medios de contacto
			converter.alias("TelefonoDTO",TelefonoDTO.class);
			converter.alias("CorreoElectronicoDTO",CorreoElectronicoDTO.class);
			converter.alias("relacionDetencionDTO",RelacionDetencionDTO.class);
			
			
			xml = converter.toXML(involucradoDTO);
			log.info("consulta_involucrado:: "+xml);
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			
		}catch (Exception e) {
			log.error(e.getCause(),e);
		}
		return null;
	}
		
}
