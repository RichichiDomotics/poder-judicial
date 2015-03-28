/**
* Nombre del Programa 	: BuscarExpedienteGeneralAction.java                                    
* Autor               	: Emigdio HErnández                                           
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
package mx.gob.segob.nsjp.web.expdiente.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.FiltroExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.expediente.form.BuscarExpedientePorAliasForm;
import mx.gob.segob.nsjp.web.expediente.form.BuscarExpedientePorNombreDePersonaForm;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Action general para realizar busquedas de expediente por filtro
 * @version 1.0
 * @author Emigdio Hernández
 */

public class BuscarExpedienteGeneralAction extends GenericAction{
	private static final Logger log  = Logger.getLogger(BuscarExpedienteGeneralAction.class);
		
	@Autowired
	ExpedienteDelegate expedienteDelegate;
	
	
	
	
	/**
	 * Metodo utilizado para realizar la consulta de expedientes
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarExpedientePorNumeroDeExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
			
			String noExpediente=request.getParameter("noExpediente");
			if (log.isDebugEnabled()) {
				log.debug("##################llega noExpediente:::::::::" + noExpediente);
			}
			FiltroExpedienteDTO filtrosBusquedaExpediente=new FiltroExpedienteDTO();
			filtrosBusquedaExpediente.setNumeroExpediente(noExpediente);
			//filtrosBusquedaExpediente.setUsuario(super.getUsuarioFirmado(request));
			List<ExpedienteDTO> listExpedienteDTOs=expedienteDelegate.buscarExpedientes(filtrosBusquedaExpediente);


			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
//			List<NombreDemograficoDTO> listaNombreDemograficoDto = null;
			
			writer.print("<rows>");
			int lTotalRegistros=listExpedienteDTOs.size();

			writer.print("<records>" + lTotalRegistros + "</records>");

			for (ExpedienteDTO expedienteDTO : listExpedienteDTOs) {
				writer.print("<row id='" + expedienteDTO.getNumeroExpedienteId()+ ","+
						expedienteDTO.getNumeroExpediente()+"'>");
				writer.print("<cell>" + expedienteDTO.getNumeroExpediente()+ "</cell>");
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
	 * Metodo utilizado para realizar la consulta de expedientes
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarExpedientePorEvidencia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
			
			
			String evidencia=request.getParameter("evidencia");
			String palabraUno=request.getParameter("palabraUno");
			String palabraDos=request.getParameter("palabraDos");
			String palabraTres=request.getParameter("palabraTres");
			String palabraCuatro=request.getParameter("palabraCuatro");
			String palabraCinco=request.getParameter("palabraCinco");
			
			
			if (palabraUno.equals("")){
				palabraUno=null;
				
			}

			if (palabraDos.equals("")){
				palabraDos=null;
				
			}				
			
			if (palabraTres.equals("")){
				palabraTres=null;
				
			}

			if (palabraCuatro.equals("")){
				palabraCuatro=null;
				
			}		
			
			if (palabraCinco.equals("")){
				palabraCinco=null;
				
			}
			
			
			
			List<String> lstPalabra = new ArrayList<String>();
			lstPalabra.add(palabraUno);
			lstPalabra.add(palabraDos);
			lstPalabra.add(palabraTres);
			lstPalabra.add(palabraCuatro);
			lstPalabra.add(palabraCinco);			
			
			FiltroExpedienteDTO filtrosBusquedaExpediente=new FiltroExpedienteDTO();
			filtrosBusquedaExpediente.setNombreEvidencia(Objetos.valueOf(evidencia.toUpperCase()).getNombreEntity());
			filtrosBusquedaExpediente.setPalabrasClave(lstPalabra);
			filtrosBusquedaExpediente.setUsuario(super.getUsuarioFirmado(request));
			List<ExpedienteDTO> listExpedienteDTOs=expedienteDelegate.buscarExpedientes(filtrosBusquedaExpediente);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
//			List<NombreDemograficoDTO> listaNombreDemograficoDto = null;
			
			writer.print("<rows>");
			int lTotalRegistros=listExpedienteDTOs.size();

			writer.print("<records>" + lTotalRegistros + "</records>");

			for (ExpedienteDTO expedienteDTO : listExpedienteDTOs) {
				writer.print("<row id='"+ expedienteDTO.getNumeroExpedienteId()+","+
						expedienteDTO.getNumeroExpediente()+"'>");
				writer.print("<cell>" + expedienteDTO.getNumeroExpediente()+ "</cell>");
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
	 * Metodo utilizado para realizar la consulta de expedientes
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarExpedientePorAlias(ActionMapping mapping,
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
			List<InvolucradoDTO> listInvolucradoDTOs=expedienteDelegate.consultarExpedientesPorFiltros(filtrosBusquedaExpediente);

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
				for (NombreDemograficoDTO nombreDemograficoDTO : listaNombreDemograficoDto) {
				
				writer.print("<row id='" + involucradoDTO.getExpedienteDTO().getNumeroExpedienteId()+ ","+
											involucradoDTO.getExpedienteDTO().getNumeroExpediente()+"'>");
				writer.print("<cell>" + involucradoDTO.getExpedienteDTO().getNumeroExpediente()+ "</cell>");
				writer.print("<cell>" + nombreDemograficoDTO.getNombre()+ "</cell>");
				writer.print("<cell>" + nombreDemograficoDTO.getApellidoPaterno() + "</cell>");
				writer.print("<cell>" + nombreDemograficoDTO.getApellidoMaterno() + "</cell>");
				writer.print("<cell>" + involucradoDTO.getCalidadDTO().getValorIdCalidad().getValor() + "</cell>");
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
	 * Metodo utilizado para realizar la carga de la tabla que se presenta
	 * en la JSP BuscarExpedientePorNombreDePersona
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarExpedientePorNombreDePersona(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {		
		try {
			
			BuscarExpedientePorNombreDePersonaForm forma=(BuscarExpedientePorNombreDePersonaForm) form;
			//String nombre=forma.getNombre();
			//String apellido=forma.getApellido();
			
			if (forma.getNombre().equals("")){
				forma.setNombre(null);
				log.info(forma.getNombre());
			}

			if (forma.getApellido().equals("")){
				forma.setApellido(null);
				log.info(forma.getApellido());
			}						
			
			if (log.isDebugEnabled()) {
				log.debug("llega nombre " + forma.getNombre() );
				log.debug("llega apellido " + forma.getApellido() );
			}
			FiltroExpedienteDTO filtrosBusquedaExpediente=new FiltroExpedienteDTO();
			filtrosBusquedaExpediente.setNombre(forma.getNombre());
			filtrosBusquedaExpediente.setApellidos(forma.getApellido());
			List<InvolucradoDTO> listInvolucradoDTOs=expedienteDelegate.consultarExpedientesPorFiltros(filtrosBusquedaExpediente);

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
				for (NombreDemograficoDTO nombreDemograficoDTO : listaNombreDemograficoDto) {
				
				writer.print("<row id='" + involucradoDTO.getExpedienteDTO().getNumeroExpedienteId() + ","+
						involucradoDTO.getExpedienteDTO().getNumeroExpediente()
						+"'>");
				writer.print("<cell>" + involucradoDTO.getExpedienteDTO().getNumeroExpediente()+ "</cell>");
				writer.print("<cell>" + nombreDemograficoDTO.getNombre()+ "</cell>");
				writer.print("<cell>" + nombreDemograficoDTO.getApellidoPaterno() + "</cell>");
				writer.print("<cell>" + nombreDemograficoDTO.getApellidoMaterno() + "</cell>");
				writer.print("<cell>" + involucradoDTO.getCalidadDTO().getValorIdCalidad().getValor() + "</cell>");
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
	 * Metodo utilizado para la búsqueda general de expedietes por numero de caso
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarExpedientePorNumeroCaso(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {		
		try {
			
			String caso = request.getParameter("noCaso");
			
			
			List<ExpedienteDTO> expedientes=expedienteDelegate.consultarNumeroExpedientePorNumeroCaso(caso);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");
			
			int lTotalRegistros=expedientes.size();
			
			
			writer.print("<records>" + lTotalRegistros + "</records>");
			for (ExpedienteDTO exp : expedientes) {
				
				
				writer.print("<row id='" + exp.getNumeroExpedienteId() + ","+
						exp.getNumeroExpediente()
						+"'>");
				writer.print("<cell>" + exp.getCasoDTO().getNumeroGeneralCaso()+ "</cell>");
			
				writer.print("<cell>" + exp.getNumeroExpediente() + "</cell>");
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
	
	public ActionForward generarDetalleExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
			
			Long idExpediente=Long.parseLong(request.getParameter("idRow"));
			
			if (log.isDebugEnabled()) {
				
				log.debug("##################llega idExpediente:::::::::" + idExpediente);
			}

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setExpedienteId(idExpediente);
			expedienteDTO.setArea(super.getUsuarioFirmado(request).getAreaActual());
			ExpedienteDTO expedienteDTO2=expedienteDelegate.obtenerExpediente(expedienteDTO);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			if (log.isDebugEnabled()) {
				
				log.debug("##################llega grid detalle:::::::::" + expedienteDTO2 );
			}

			List<NombreDemograficoDTO> list=new ArrayList<NombreDemograficoDTO>();
			
			for (InvolucradoDTO dos : expedienteDTO2.getInvolucradosDTO()) {
				for (NombreDemograficoDTO nombreDemograficoDTO : dos.getNombresDemograficoDTO()) {
					list.add(nombreDemograficoDTO);
				}
			}
			
			writer.print("<rows>");
			int lTotalRegistros=list.size();

			writer.print("<records>" + lTotalRegistros + "</records>");
			
			for (InvolucradoDTO dos : expedienteDTO2.getInvolucradosDTO()) {
				for (NombreDemograficoDTO demograficoDTO : list) {
					
				writer.print("<row id='1'>");
				writer.print("<cell>" + demograficoDTO.getNombre() + "</cell>");
				writer.print("<cell>" + demograficoDTO.getApellidoPaterno()  + "</cell>");
				writer.print("<cell>" + demograficoDTO.getApellidoMaterno()  + "</cell>");
				writer.print("<cell>" + dos.getCalidadDTO().getValorIdCalidad().getValor() + "</cell>");
				writer.print("<cell>" + dos.getExpedienteDTO().getDelitoPrincipal() + "</cell>");
				writer.print("<cell>" + dos.getExpedienteDTO().getCasoDTO()+ "</cell>");
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
	
	public ActionForward generarDetalleDeBusquedaDeExpediente(ActionMapping mapping,
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
		return mapping.findForward("generarDetalleExp");
	}
	
	public ActionForward cargarTitulo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action carga titulo");
			
			Long idExp=Long.parseLong(request.getParameter("idRow"));
			
			if (log.isDebugEnabled()) {
				
				log.debug("##################llega idExpediente  titulo:::::::::" + idExp);
			}
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setExpedienteId(idExp);
			ExpedienteDTO expedienteDTO2=expedienteDelegate.obtenerExpediente(expedienteDTO);
			
			converter.alias("expediente", ExpedienteDTO.class);
			
			String xml = converter.toXML(expedienteDTO2);
			
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
	 * Metodo utilizado para pasar parametros y diferenciar de donde se hace el llamado del caso d euso buscar expediente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action buscar expediente");
			
			String tipo = request.getParameter("tipo");
			log.info("LLEGA TIPO:::"+ tipo);
			request.setAttribute("tipo",tipo);
			
			
		} catch (Exception e) {		
			log.info(e);
		}
		return mapping.findForward("succes");
	}		

	
	
	/**
	 * Metodo utilizado para realizar la consulta de expedientes
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarExpedientesPorFuncionarioAreaEstatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
			
			Long idFuncionario = NumberUtils.toLong(request.getParameter("idFuncionario"),0);			
			if (log.isDebugEnabled()) {
				log.debug("##################llega idFuncionario:::::::::" + idFuncionario);
			}
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			FuncionarioDTO funcionarioDTO = new FuncionarioDTO(idFuncionario);
			usuarioDTO.setFuncionario(funcionarioDTO);

			List<ExpedienteDTO> listExpedienteDTOs=expedienteDelegate.consultarExpedientesPorUsuarioAreaEstatus(usuarioDTO, null);

			int lTotalRegistros=listExpedienteDTOs.size();

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
			//writer.print("<records>" + lTotalRegistros + "</records>");

			for (ExpedienteDTO expedienteDTO : listExpedienteDTOs) {
				writer.print("<row id='" + expedienteDTO.getNumeroExpedienteId()+"'>");
				writer.print("<cell>" + (expedienteDTO.getNumeroExpediente() != null? expedienteDTO.getNumeroExpediente(): "-") +  "</cell>");
				writer.print("<cell>" + (expedienteDTO.getCasoDTO() != null && expedienteDTO.getCasoDTO().getNumeroGeneralCaso() != null? 
						expedienteDTO.getCasoDTO().getNumeroGeneralCaso(): "-") +  "</cell>");
				writer.print("<cell>" + (expedienteDTO.getDelitoPrincipal() != null &&
						expedienteDTO.getDelitoPrincipal().getNombreDelito() != null ? expedienteDTO.getDelitoPrincipal().getNombreDelito(): "-") +  "</cell>");
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
}
