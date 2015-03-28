/**
* Nombre del Programa : DelitoAction.java
* Autor               : Arturo Leon
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 01/04/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Action con los metodos usados para dar funcionalidad a la pantalla Ingresar Delito
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.actividad.ActividadDelegate;
import mx.gob.segob.nsjp.delegate.delito.DelitoDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;


/**
* Clase Action con los metodos usados para dar funcionalidad a la pantalla Ingresar Delito.
*
* @version 1.0
* @author Arturo Leon
*/

public class DelitoAction extends GenericAction{
	/**Log de clase*/
	private static final Logger log  = Logger.getLogger(DelitoAction.class);

	@Autowired
	private DelitoDelegate delitoDelegate;
	
	@Autowired
	private ActividadDelegate actividadDelegate;
	/**
	 * Metodo utilizado para realizar la carga del combo delitos 
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward cargarDelitos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando DelitoAction - Carga combo delitos");
			List<CatDelitoDTO> listaDelitos=catDelegate.consultarDelito();
			//parseo todos los delitos a XML
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catDelitoDTO", CatDelitoDTO.class);
			String delitosXml = converter.toXML(listaDelitos);
			request.getSession().setAttribute("delitosXML", delitosXml);//subo los delitos a sesion
						
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
			final PaginacionDTO pag = PaginacionThreadHolder.get();
            log.debug("pag :: " + pag);
            if (pag != null && pag.getTotalRegistros() != null) {
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
            } else {
                writer.print("<total>0</total>");
                writer.print("<records>0</records>");
            }		
			

		
			int i=0;
			for (CatDelitoDTO catDelitoDTO : listaDelitos) {
				
				writer.print("<row id='" + catDelitoDTO.getCatDelitoId()+ "'>");
				writer.print("<cell>" + catDelitoDTO.getClaveDelito()+ "</cell>");
				writer.print("<cell>" + catDelitoDTO.getNombre()+ "</cell>");				
				if(catDelitoDTO.getEsGrave()==true)
				{
					writer.print("<cell>" + "true"+ "</cell>");
				}
				else
				{
					writer.print("<cell>" + "false"+ "</cell>");
				}
				//Formatea la clasificacion del delito
				if(catDelitoDTO.getEsGrave()==true)
					writer.print("<cell>" + "Si"+ "</cell>");
				else				
					writer.print("<cell>" + "No" + "</cell>");
				writer.print("<cell><![CDATA[<div>"+"<input type='radio' name='gridDelitos' id='rdb_"+catDelitoDTO.getCatDelitoId()+"' onclick='revisaEsDelitoGraveUno("+catDelitoDTO.getCatDelitoId()+");'> </div>]]></cell>");
				writer.print("<cell>" +catDelitoDTO.getCatDelitoId() +"_0_I"+ "</cell>");
				writer.print("<cell>" + catDelitoDTO.getCatDelitoId()+ "</cell>");
				writer.print("</row>");
				i++;
			}			
			
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}	
	
	/**
	 * Metodo utilizado para realizar la carga del combo delitos 
	 * exceptuando los que estan en el grid de delitos denunciados
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward cargarDelitosFiltrados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			int i=0;

			/*if(request.getParameter("idNumeroExpediente")!=null && !request.getParameter("idNumeroExpediente").equals("null") && !request.getParameter("idNumeroExpediente").equals("")){
				idNumeroExpediente=request.getParameter("idNumeroExpediente");
				Long idnum=Long.parseLong(idNumeroExpediente);			
			}*/
			
			String valor="";
						
			if(request.getParameter("idsDelitos")!=null && !request.getParameter("idsDelitos").equals("null") && !request.getParameter("idsDelitos").equals("undefined"))
				valor=request.getParameter("idsDelitos");	
							
			List<CatDelitoDTO> listaDelitos=catDelegate.
			consultarDelitosSeleccionables(valor);			
			
			/* Esta sección era para descartar los delitos que están en el grid de delitos denunciados
			 * esto ya se realiza a nivel de consulta a base de datos
			 * 
			 * 
			 if(request.getParameter("idsDelitos")!=null && !request.getParameter("idsDelitos").equals("null") && !request.getParameter("idsDelitos").equals("")
			   && !request.getParameter("idsDelitos").equals("undefined")){
				
				idsDelitosDenunciados = request.getParameter("idsDelitos");
				
				String delitosIds[] = idsDelitosDenunciados.split(",");
								
				for(CatDelitoDTO delito:listaDelitos)
				{
					i=0;
					while(i<delitosIds.length && delito.getCatDelitoId()!=Long.parseLong(delitosIds[i]))
					{
						i++;						
					}
					
					if(i<delitosIds.length){					
						listaDelitos.remove(j); 						
					}
					
					j=j++;
				}
			}*/						
			
			//parseo todos los delitos a XML
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catDelitoDTO", CatDelitoDTO.class);                                                
			String delitosXml = converter.toXML(listaDelitos);
			request.getSession().setAttribute("delitosXML", delitosXml);//subo los delitos a sesion
						
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
			final PaginacionDTO pag = PaginacionThreadHolder.get();
            log.debug("pag :: " + pag);
            if (pag != null && pag.getTotalRegistros() != null) {
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
            } else {
                writer.print("<total>0</total>");
                writer.print("<records>0</records>");
            }		
			           
			i=0;
			for (CatDelitoDTO catDelitoDTO : listaDelitos) {
				
				writer.print("<row id='" + catDelitoDTO.getCatDelitoId()+ "'>");
				writer.print("<cell>" + catDelitoDTO.getClaveDelito()+ "</cell>");
				writer.print("<cell>" + catDelitoDTO.getClaveDelito()+ "</cell>");
				writer.print("<cell>" + catDelitoDTO.getNombre()+ "</cell>");				
				if(catDelitoDTO.getEsGrave()==true)
				{
					writer.print("<cell>" + "true"+ "</cell>");
				}
				else
				{
					writer.print("<cell>" + "false"+ "</cell>");
				}
				//Formatea la clasificacion del delito
				if(catDelitoDTO.getEsGrave()==true)
					writer.print("<cell>" + "Si"+ "</cell>");
				else				
					writer.print("<cell>" + "No" + "</cell>");
				writer.print("<cell><![CDATA[<div>"+"<input type='radio' name='gridDelitos' id='rdb_"+catDelitoDTO.getCatDelitoId()+"' onclick='revisaEsDelitoGraveUno("+catDelitoDTO.getCatDelitoId()+");'> </div>]]></cell>");
				writer.print("<cell>" +catDelitoDTO.getCatDelitoId() +"_0_I"+ "</cell>");
				writer.print("<cell>" + catDelitoDTO.getCatDelitoId()+ "</cell>");
				writer.print("</row>");
				i++;
			}			
			
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}	
	
	/**
	 * Metodo utilizado para realizar la carga del combo delitos 
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward cargarDelitosPJENC(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando DelitoAction - Carga combo delitos");
			
			CatDelitoDTO catDelitoFiltroDTO = new CatDelitoDTO();
			catDelitoFiltroDTO.setEsAccionPenPriv(true);

			List<CatDelitoDTO> listaDelitos=catDelegate.consultarCatDelitoPorFilro(catDelitoFiltroDTO);
			//parseo todos los delitos a XML
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catDelitoDTO", CatDelitoDTO.class);
			String delitosXml = converter.toXML(listaDelitos);
			request.getSession().setAttribute("delitosXML", delitosXml);//subo los delitos a sesion
						
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
			final PaginacionDTO pag = PaginacionThreadHolder.get();
            log.debug("pag :: " + pag);
            if (pag != null && pag.getTotalRegistros() != null) {
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
            } else {
                writer.print("<total>0</total>");
                writer.print("<records>0</records>");
            }		
			

		
			int i=0;
			for (CatDelitoDTO catDelitoDTO : listaDelitos) {
				
				writer.print("<row id='" + catDelitoDTO.getCatDelitoId()+ "'>");
				writer.print("<cell>" + catDelitoDTO.getClaveDelito()+ "</cell>");
				writer.print("<cell>" + catDelitoDTO.getNombre()+ "</cell>");				
				if(catDelitoDTO.getEsGrave()==true)
				{
					writer.print("<cell>" + "true"+ "</cell>");
				}
				else
				{
					writer.print("<cell>" + "false"+ "</cell>");
				}
				//Formatea la clasificacion del delito
				if(catDelitoDTO.getEsGrave()==true)
					writer.print("<cell>" + "Si"+ "</cell>");
				else				
					writer.print("<cell>" + "No" + "</cell>");
				writer.print("<cell><![CDATA[<div>"+"<input type='radio' name='gridDelitos' id='rdb_"+catDelitoDTO.getCatDelitoId()+"' onclick='revisaEsDelitoGraveUno("+catDelitoDTO.getCatDelitoId()+");'> </div>]]></cell>");
				writer.print("<cell>" +catDelitoDTO.getCatDelitoId() +"_0_I"+ "</cell>");
				writer.print("<cell>" + catDelitoDTO.getCatDelitoId()+ "</cell>");
				writer.print("</row>");
				i++;
			}			
			
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}	

	
	/**
	 * Metodo utilizado para generar un nuevo turno
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */ 
	 public ActionForward asociarDelitosAgraviadosExpediente(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws IOException {		 		
	    	try {
				log.info("ejecutando Action asociarDelitosAgraviadosExpediente DELITOS::: "+request.getParameter("delitos")); 
				
				//obtengo los delitos del grid completo
				String[] delitos=request.getParameter("delitos").split("-");
				String numeroExpediente=request.getParameter("numExp");
				//obtengo el ID del delito principal
				String delitoPrincipal=delitos[0];
				//separo los delitos normales
				String[] delitosNoPrincipales=null;
				if(delitos.length>1)
				{
					delitosNoPrincipales=delitos[1].split(",");
				}
								
				if (log.isDebugEnabled()) {
					
					log.debug("delito_principal::" + delitoPrincipal);
					if((delitosNoPrincipales!= null) && (delitosNoPrincipales.length>0))
						log.debug("delitosNormales::" + delitos[1]);
				}
				//creamos la lista de delitos
				List<DelitoDTO> listaDelitos=new ArrayList<DelitoDTO>();
				DelitoDTO delitoDTO=new DelitoDTO();
				//agregamos el delito principal a la lista
				//delitoDTO.setDelitoId(Long.parseLong(delitoPrincipal));
				String[] delitoPrincipalArr=delitoPrincipal.split("_");
				CatDelitoDTO catDelitoDTO = new CatDelitoDTO();
				//catDelitoDTO.setClaveDelito(delitoPrincipalArr[1]);
				delitoDTO.setCatDelitoDTO(catDelitoDTO);
				//delitoDTO.setNombreDelito(delitoPrincipalArr[0]);
				if(delitoPrincipalArr[2].equals("C"))
				{
					delitoDTO.setDelitoId(Long.parseLong(delitoPrincipalArr[1]));//seteo el ID del delitoExpediente
					CatDelitoDTO catDelito= new CatDelitoDTO();
					catDelito.setCatDelitoId(Long.parseLong(delitoPrincipalArr[0]));//agrego el ID del catalogo de delitos
					delitoDTO.setCatDelitoDTO(catDelito);
					log.info("DEL_PRI_C::: "+delitoPrincipalArr[0]);
				}
				else
				{
					delitoDTO.setDelitoId(null);//seteo el id del delitoExpediente
					CatDelitoDTO catDelito= new CatDelitoDTO();
					catDelito.setCatDelitoId(Long.parseLong(delitoPrincipalArr[0]));//agrego el ID del catalogo de delitos
					delitoDTO.setCatDelitoDTO(catDelito);
					log.info("DEL_PRI_I::: "+delitoPrincipalArr[0]);
				}
				delitoDTO.setEsPrincipal(true);
				ExpedienteDTO expedienteDTO=super.getExpedienteTrabajo(request,numeroExpediente);
				delitoDTO.setExpedienteDTO(expedienteDTO);
				listaDelitos.add(delitoDTO);
				delitoDTO=null;//destruyo el objeto
				//ahora barreremos los delitos no principales si es que existen
				//y los agregamos en la lista
				if((delitosNoPrincipales!= null) && (delitosNoPrincipales.length>0))
				{
					for (String delitoString: delitosNoPrincipales)
					{
						delitoDTO=new DelitoDTO();
						//delitoDTO.setDelitoId(Long.parseLong(delitoString));
						String[] delitoNormalArr=delitoString.split("_");
						//catDelitoDTO=new CatDelitoDTO();
						//catDelitoDTO.setClaveDelito(delitoNormalArr[0]);
						//delitoDTO.setCatDelitoDTO(catDelitoDTO);
						//delitoDTO.setNombreDelito(delitoNormalArr[0]);
						if(delitoNormalArr[2].equals("C"))
						{
							delitoDTO.setDelitoId(Long.parseLong(delitoNormalArr[1]));//seteo el ID del delitoExpediente
							CatDelitoDTO catDelito= new CatDelitoDTO();
							catDelito.setCatDelitoId(Long.parseLong(delitoNormalArr[0]));//agrego el ID del catalogo de delitos
							delitoDTO.setCatDelitoDTO(catDelito);
							log.info("DEL_NOR_C::: "+delitoNormalArr[1]);
						}
						else
						{
							delitoDTO.setDelitoId(null);//Seteo null el catalogo del delito expediente xq es una insercion
							CatDelitoDTO catDelito= new CatDelitoDTO();
							catDelito.setCatDelitoId(Long.parseLong(delitoNormalArr[0]));//agrego el ID del catalogo de delitos
							delitoDTO.setCatDelitoDTO(catDelito);
							log.info("DEL_NOR_I::: "+delitoNormalArr[0]);
						}
						delitoDTO.setEsPrincipal(false);
						delitoDTO.setExpedienteDTO(expedienteDTO);
						listaDelitos.add(delitoDTO);
						delitoDTO=null;
					}
				}
				log.info("DELITOS_AGRAVIADOS    LISTA:: "+listaDelitos.size());
				log.info("DELITOS_AGRAVIADOS    numeroExpediente:: "+expedienteDTO.getExpedienteId());
				delitoDelegate.guardarDelito(listaDelitos, expedienteDTO);
				log.info("FIN ejecutando Action asociarDelitosAgraviadosExpediente");
				
				converter.alias("listaDelitosDTO", java.util.List.class);
				converter.alias("delitoDTO", DelitoDTO.class);
				String xml = converter.toXML(listaDelitos);
				if (log.isDebugEnabled()) {
					log.info("DELITOS_AGRAVIADOS    numeroExpediente:: "+expedienteDTO.getExpedienteId());
					log.info("DELITOS ::::   "+xml);
				}
				escribirRespuesta(response, xml);
			}
			catch (NSJPNegocioException e) {		
				log.info(e.getCause(),e);
				escribirError(response, e);
			}
			return null;
	 }
	
		/**
		 * Metodo utilizado para consultar las actividades a realizar dependiendo de los delitos
		 * registrados en un expediente
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 */ 
		 public ActionForward consultarActividadesPorDelitosDelExpediente(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws IOException {		 		
		    	try {
					log.info("ejecutando Action consultarActividadesPorDelitosDelExpediente DELITOS::: "+request.getParameter("delitos")); 
					
					//obtengo los delitos del grid completo
					String[] delitos=request.getParameter("delitos").split("-");
					
					//obtengo el ID del delito principal
					String delitoPrincipal=delitos[0];
					//separo los delitos normales
					String[] delitosNoPrincipales=null;
					if(delitos.length>1)
					{
						delitosNoPrincipales=delitos[1].split(",");
					}
									
					if (log.isDebugEnabled()) {
						
						log.debug("delito_principal::" + delitoPrincipal);
						if((delitosNoPrincipales!= null) && (delitosNoPrincipales.length>0))
							log.debug("delitosNormales::" + delitos[1]);
					}
					//creamos la lista de los IDs  de los delitos
					List<Long> delitosIDs=new ArrayList<Long>();
					//agregamos el delito principal a la lista
					String[] delitoPrincipalArr=delitoPrincipal.split("_");
					delitosIDs.add(Long.parseLong(delitoPrincipalArr[0]));
					//ahora barreremos los delitos no principales si es que existen
					//y los agregamos en la lista
					if((delitosNoPrincipales!= null) && (delitosNoPrincipales.length>0))
					{
						for (String delitoString: delitosNoPrincipales)
						{
							String[] delitoNormalArr=delitoString.split("_");
							delitosIDs.add(Long.parseLong(delitoNormalArr[0]));//agregamos el ID del delito q tiene en el catalogo correspondiente
						}
					}
					
					log.info("DELITOS_AGRAVIADOS    LISTA:: "+delitosIDs.size());
					List<ValorDTO> listaActividadesDTO=actividadDelegate.consultarActividadesPorIdsCatDelito(delitosIDs);
					log.info("FIN ejecutando Action consultarActividadesPorDelitosDelExpediente");
					
					converter.alias("listaActividadesDTO", java.util.List.class);
					converter.alias("ValorDTO", ValorDTO.class);
					String xml = converter.toXML(listaActividadesDTO);
					if (log.isDebugEnabled()) {
						log.info("ACTIVIDADES_DELITOS ::::   "+xml);
					}
					escribirRespuesta(response, xml);
				}
				catch (NSJPNegocioException e) {		
					log.info(e.getCause(),e);
					escribirError(response, e);
				}
				return null;
		 }
		 
	/**
	 * Método utilizado para realizar la consulta de delitos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoDelitos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos de delitos");	

			List<CatDelitoDTO> listaDelitos=catDelegate.consultarDelito();
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("delitos", CatDelitoDTO.class);
			String xml = converter.toXML(listaDelitos);
			log.info("ejecutando Action consultar catalogos de delitos xml . "+xml);	

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
	 * funcion que permite anular la relacion de delito con Persona
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward anularRelacionDelitoPersona(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("	:::: PARAMETROS RECUPERADOS anularRelacionDelitoPersona :::::::::: "+request.getParameter("idsRelacionesSeleccionados"));
			String idCatRelacion= request.getParameter("idsRelacionesSeleccionados");
			
			//creamos la lista de los IDs  de las relaciones
			String[] relaciones =null;
			String xml = "<respuesta><bandera>0</bandera></respuesta>";
			
			if(idCatRelacion != null && idCatRelacion.length()>0){
				relaciones = idCatRelacion.split(",");
				log.info("	::::::::::::::::: El total de Id´s recibidos es: ::::::::::::::::::::: " + relaciones.length);
				//barremos el arreglo de lows IDs
				for (String relacion: relaciones)
				{
					delitoDelegate.eliminarDelitoPersona(Long.parseLong(relacion));
					//Se efectua el eliminado fisico para evitar porblemas cuando se se desea crear nuevas relaciones.
					//delitoDelegate.desactivarDelitoPersona(Long.parseLong(relacion));
				}
				//si se eliminaron todas las relaciones
				xml="<respuesta><bandera>1</bandera></respuesta>";		
			}
			else
			{
				xml="<respuesta><bandera>0</bandera></respuesta>";
			}
			escribirRespuesta(response,xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	public ActionForward consultarRelacionProbRespConDelito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action DelitoAction en metodo consultarRelacionProbRespConDelito");
			try {
				String idExpediente=request.getParameter("idExpediente");
				String idDelito=request.getParameter("idDelito");

				log.info("Relacionar_Delito_Del idExpediente::: "+idExpediente);
				log.info("Relacionar_Delito_Del idDelito::: "+idDelito);

				//verificamos la existencia del delito 
				Boolean delito = delitoDelegate.existeRelacionPersonaDelitoExpediente(Long.parseLong(idDelito),Long.parseLong(idExpediente));
				log.info("delito :::: "+delito);

				//converter.alias("delito", java..class);
				String xml = "<respuesta>"+delito+"</respuesta>";
				log.info("ejecutando delitoAction consultarRelacionProbRespConDelito xml - "+xml);	

				response.setContentType("text/xml");
				PrintWriter pw = response.getWriter();
				pw.print(xml);
				pw.flush();
				pw.close();
				
			log.info(" FIN consultarRelacionProbRespConDelito");
		
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
}