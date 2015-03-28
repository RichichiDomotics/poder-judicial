/**
* Nombre del Programa 	: BuscarExpedientePorAliasAction.java                                    
* Autor               	: Andres Gomez Godinez                                              
* Compania            	: Ultrasist                                                
* Proyecto            	: NSJP              			Fecha:09/03/2011 
* Marca de cambio     	: N/A                                                     
* Descripcion General   : Clase action que implementa las acciones para el CU Buscar Expediente Por Alias
* Programa Dependiente  : N/A                                                      
* Programa Subsecuente 	: N/A                                                      
* Cond. de ejecucion    : struts-config.xml                                                    
* Dias de ejecucion     : N/A                             Horario: N/A
*                               MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                 :N/A                                                           
* Compania              :N/A                                                           
* Proyecto              :N/A                                   Fecha: N/A       
* Modificacion          :N/A                                                           
*------------------------------------------------------------------------------      
*/
package mx.gob.segob.nsjp.web.caso.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.caso.CasoDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.caso.FiltroCasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.FiltroExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.expediente.form.BuscarExpedientePorAliasForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *Clase que implementa las acciones para el CU Buscar Expediente Por Alias
 * @version 1.0
 * @author AndresGG
 */

public class BuscarCasoAction extends GenericAction{
	private static final Logger log  = Logger.getLogger(BuscarCasoAction.class);
		
	@Autowired
	CasoDelegate casoDelegate;
	
	/**
	 * Metodo utilizado para realizar la consulta de caso 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarCasoPorNumeroDeCaso(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
			
			String noCaso=request.getParameter("noCaso");
			if (log.isDebugEnabled()) {
				log.debug("##################llega noCaso:::::::::" + noCaso);
			}
			
			
			List<NombreDemograficoDTO> listNombresPorcaso=new ArrayList<NombreDemograficoDTO>();
			
			CasoDTO casoDTO=new CasoDTO();
			casoDTO.setNumeroGeneralCaso(noCaso);
			casoDTO.setUsuario(getUsuarioFirmado(request));
			List<CasoDTO> listCasoDTOs=casoDelegate.consultarCasoPorNumero(casoDTO);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");
			int lTotalRegistros=listCasoDTOs.size();

			writer.print("<records>" + lTotalRegistros + "</records>");			
			
			for (CasoDTO casoDTO2 : listCasoDTOs) {
				List<ExpedienteDTO> listExpedienteDTOs=casoDTO2.getExpedintesDTO();
				for (ExpedienteDTO expedienteDTO : listExpedienteDTOs) {
					List<InvolucradoDTO> listInvolucradoDTOs=expedienteDTO.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA);
					for (InvolucradoDTO involucradoDTO : listInvolucradoDTOs) {
						List<NombreDemograficoDTO> listNombres=involucradoDTO.getNombresDemograficoDTO();
						for (NombreDemograficoDTO nombreDemograficoDTO : listNombres) {
							listNombresPorcaso.add(nombreDemograficoDTO);{
								
								writer.print("<row id='"+ casoDTO2.getExpedintesDTO().get(0).getNumeroExpedienteId() + "'>");
								writer.print("<cell>" + casoDTO2.getNumeroGeneralCaso()+ "</cell>");
								writer.print("<cell>" + nombreDemograficoDTO.getNombre() + "</cell>");
								writer.print("<cell>" + nombreDemograficoDTO.getApellidoPaterno() + "</cell>");
								writer.print("<cell>" + nombreDemograficoDTO.getApellidoMaterno() + "</cell>");
								writer.print("</row>");						
								
								
							}							
							
						}
						
					}
				}
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
	 * Metodo utilizado para realizar la consulta de casos por fecha
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarCasoPorFecha(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
			
			String fechaInicio=request.getParameter("fechaInicio");
			String fechaFin=request.getParameter("fechaFin");
			
			DateFormat formato = new SimpleDateFormat("dd/MM/yy");
			Date fechaCreacionInicio = null;
			Date fechaCreacionFin = null;
			try {
				fechaCreacionInicio = formato.parse(fechaInicio);
				fechaCreacionFin = formato.parse(fechaFin);		
			
			} catch (ParseException e) {
			
				e.printStackTrace();
			}
						
			FiltroCasoDTO filtrosBusquedaCaso=new FiltroCasoDTO();
			filtrosBusquedaCaso.setFechaCreacionInicio(fechaCreacionInicio);
			filtrosBusquedaCaso.setFechaCreacionFin(fechaCreacionFin);
					
			List<CasoDTO> listCasoDTOs=casoDelegate.buscarCasoPorFiltros(filtrosBusquedaCaso);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			
			writer.print("<rows>");
			int lTotalRegistros=listCasoDTOs.size();

			writer.print("<records>" + lTotalRegistros + "</records>");

			for (CasoDTO casoDTO : listCasoDTOs) {
				writer.print("<row id='1'>");
				writer.print("<cell>" + casoDTO.getNumeroGeneralCaso()+ "</cell>");
				writer.print("</row>");
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
	 * Metodo utilizado para realizar la consulta de caso por alias
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarCasoPorAlias(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
			
			BuscarExpedientePorAliasForm forma=(BuscarExpedientePorAliasForm) form;
			String alias=forma.getAlias();
			Long tipo=forma.getTipo();
			if (log.isDebugEnabled()) {
				log.debug("llega alias " + alias );
				log.debug("llega tipo " + tipo );
			}
			FiltroExpedienteDTO filtrosBusquedaExpediente=new FiltroExpedienteDTO();
			filtrosBusquedaExpediente.setAlias(alias);
			filtrosBusquedaExpediente.setTipoBusqueda(tipo);
			List<InvolucradoDTO> listInvolucradoDTOs=casoDelegate.buscarCasoPorFiltros(filtrosBusquedaExpediente);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
						
			List<NombreDemograficoDTO> listaNombreDemograficoDto = null;
			
			writer.print("<rows>");
			
			int lTotalRegistros=0;
						
			for (InvolucradoDTO involucradoDTO : listInvolucradoDTOs) {
				lTotalRegistros=+involucradoDTO.getNombresDemograficoDTO().size();
			}
			writer.print("<records>" + lTotalRegistros + "</records>");
			
			for (InvolucradoDTO involucradoDTO : listInvolucradoDTOs) {
				listaNombreDemograficoDto = involucradoDTO.getNombresDemograficoDTO();
								
				writer.print("<row id='"+ involucradoDTO.getExpedienteDTO().getCasoDTO().getCasoId()+"'>");
				writer.print("<cell>" + involucradoDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso()+ "</cell>");
				
				for (NombreDemograficoDTO nombreDemograficoDTO : listaNombreDemograficoDto) {
					
									
				writer.print("<cell>" + nombreDemograficoDTO.getNombre()+ "</cell>");
				writer.print("<cell>" + nombreDemograficoDTO.getApellidoPaterno() + "</cell>");
				writer.print("<cell>" + nombreDemograficoDTO.getApellidoMaterno() + "</cell>");
				
				}			
				writer.print("</row>");
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
	 * Metodo utilizado para buscar caso por nombre de persona
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarCasoPorNombreDePersona(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {		
		try {
			log.info("ejecutando Action BuscarCasoPorNombreDePersona");
			
			String nombre=request.getParameter("nombre");
			String apellido=request.getParameter("apellido");
			
			if (nombre.equals("")){
				nombre=null;
				
			}

			if (apellido.equals("")){
				apellido=null;
				
			}		
			
			if (log.isDebugEnabled()) {
				log.debug("llega nombre " + nombre );
				log.debug("llega apellido " + apellido );
			}
						
			FiltroExpedienteDTO filtrosDTO=new FiltroExpedienteDTO();
			filtrosDTO.setNombre(nombre);
			filtrosDTO.setApellidos(apellido);
			List<InvolucradoDTO> listInvolucradoDTOs=casoDelegate.buscarCasoPorFiltros(filtrosDTO);
				
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
						
			writer.print("<rows>");
			int lTotalRegistros=listInvolucradoDTOs.size();
			for (InvolucradoDTO involucradoDTO : listInvolucradoDTOs) {
				lTotalRegistros=+involucradoDTO.getNombresDemograficoDTO().size();
			}
			writer.print("<records>" + lTotalRegistros + "</records>");
			for (InvolucradoDTO involucradoDTO : listInvolucradoDTOs) {
				List<NombreDemograficoDTO> listaNombreDemograficoDto = involucradoDTO.getNombresDemograficoDTO();
				for (NombreDemograficoDTO nombreDemograficoDTO : listaNombreDemograficoDto) {
					writer.print("<row id='1'>");
					writer.print("<cell>" + involucradoDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso()+ "</cell>");
					writer.print("<cell>" + nombreDemograficoDTO.getNombre() + "</cell>");
					writer.print("<cell>" + nombreDemograficoDTO.getApellidoPaterno() + "</cell>");
					writer.print("<cell>" + nombreDemograficoDTO.getApellidoMaterno() + "</cell>");
					writer.print("</row>");
				}
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
	 * Metodo utilizado para realizar la carga del combo tipo de evidencia 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws IOException En caso de obtener una exception
	 */
	
	public ActionForward cargarTipoEvidencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action Buscar Expediente Por Evidencia");
			
			List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.TIPO_OBJETO);

			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catEvidencia", CatalogoDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
			
			response.setContentType("text/xml");
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();	
			
			
		} catch (Exception e) {		
			log.info(e);
		}
		return null;
	}	
	/**
	 * Metodo utilizado para realizar la consulta de casos por delito
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarCasoPorDelito(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
			
			String delito=request.getParameter("delito");
			
			if (log.isDebugEnabled()) {
				
				log.debug("##################llega delito:::::::::" + delito);
			}

			FiltroCasoDTO filtrosBusquedaCaso=new FiltroCasoDTO();
			filtrosBusquedaCaso.setDelito(delito);					
			List<CasoDTO> listCasoDTOs=casoDelegate.buscarCasoPorFiltros(filtrosBusquedaCaso);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");
			final PaginacionDTO pag = PaginacionThreadHolder.get();
            if (pag != null && pag.getTotalRegistros() != null) {
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
            } else {
                writer.print("<total>0</total>");
                writer.print("<records>0</records>");
            }

			for (CasoDTO casoDTO : listCasoDTOs) {
				writer.print("<row id='" + casoDTO.getNumeroGeneralCaso() + "'>");
				writer.print("<cell>" + casoDTO.getNumeroGeneralCaso()+ "</cell>");
				writer.print("</row>");
			}			
			
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
		
	}
	public ActionForward generarDetalleCaso(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
			
			String idCaso=request.getParameter("idRow");
			
			if (log.isDebugEnabled()) {
				
				log.debug("##################llega idCaso:::::::::" + idCaso);
			}

//			FiltroCasoDTO filtrosBusquedaCaso=new FiltroCasoDTO();
//			filtrosBusquedaCaso.setDelito(delito);					
//			List<CasoDTO> listCasoDTOs=casoDelegate.buscarCasoPorFiltros(filtrosBusquedaCaso);
//
//			response.setContentType("text/xml; charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache");
//			PrintWriter writer = response.getWriter();
//
//			writer.print("<rows>");
//			int lTotalRegistros=listCasoDTOs.size();
//
//			writer.print("<records>" + lTotalRegistros + "</records>");
//
//			for (CasoDTO casoDTO : listCasoDTOs) {
//				writer.print("<row id='1'>");
//				writer.print("<cell>" + casoDTO.getNumeroGeneralCaso()+ "</cell>");
//				writer.print("</row>");
//			}			
//			
//			writer.print("</rows>");
//			writer.flush();
//			writer.close();
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	public ActionForward generarDetalleDeBusquedaDeCaso(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
			
			String idRow=request.getParameter("idRow");
			
			if (log.isDebugEnabled()) {
				
				log.debug("##################llega fila:::::::::" + idRow);
			}

			request.setAttribute("idRow", idRow);

			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return mapping.findForward("generarDetalle");
	}

	
}
