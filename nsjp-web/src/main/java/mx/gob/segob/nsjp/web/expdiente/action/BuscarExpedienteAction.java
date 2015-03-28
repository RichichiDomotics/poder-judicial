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
package mx.gob.segob.nsjp.web.expdiente.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.bitacora.BitacoraMovimientoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.bitacora.BitacoraMovimientoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.FiltroExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
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
 *Clase que implementa las acciones para el CU Buscar Expediente Por Alias
 * @version 1.0
 * @author AndresGG
 */

public class BuscarExpedienteAction extends GenericAction{
	private static final Logger log  = Logger.getLogger(BuscarExpedienteAction.class);
		
	@Autowired
	ExpedienteDelegate expedienteDelegate;
	@Autowired
	BitacoraMovimientoDelegate bitacoraMovimientosDelegate;
	
	
	
	
	
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

			String noExpediente = request.getParameter("noExpediente");
			if (log.isDebugEnabled()) {
				log.debug("##################llega noExpediente:::::::::"
						+ noExpediente);
			}
			FiltroExpedienteDTO filtrosBusquedaExpediente = new FiltroExpedienteDTO();
			filtrosBusquedaExpediente.setNumeroExpediente(noExpediente);
			filtrosBusquedaExpediente.setUsuario(super
					.getUsuarioFirmado(request));
			// List<ExpedienteDTO>
			// listExpedienteDTOs=expedienteDelegate.buscarExpedientes(filtrosBusquedaExpediente);
			List<ExpedienteDTO> listExpedienteDTOs = expedienteDelegate
					.buscarExpedientesPorNumExpDiscriminanteArea(filtrosBusquedaExpediente);

			log.debug("TAMAÑO LISTA DE EXPEDIENTES="
					+ listExpedienteDTOs.size());

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");

			final PaginacionDTO pag = PaginacionThreadHolder.get();
			log.debug("pag :: " + pag);
			if (pag != null && pag.getTotalRegistros() != null) {
				writer.print("<total>" + pag.getTotalPaginas() + "</total>");
				writer.print("<records>" + pag.getTotalRegistros()
						+ "</records>");
			} else {
				writer.print("<total>0</total>");
				writer.print("<records>0</records>");
			}

			for (ExpedienteDTO expedienteDTO : listExpedienteDTOs) {
				// se cambio por numeroExpedienteId dado que el solo Id no era
				// el correcto
				writer.print("<row id='"
						+ expedienteDTO.getNumeroExpedienteId() + "'>");
				if (expedienteDTO.getCasoDTO() != null) {
					writer.print("<cell>"
							+ expedienteDTO.getCasoDTO().getNumeroGeneralCaso()
							+ "</cell>");
				} else {
					writer.print("<cell> -- </cell>");
				}
				writer.print("<cell>" + expedienteDTO.getNumeroExpediente()
						+ "</cell>");
				writer.print("</row>");
			}

			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.info(e.getCause(), e);

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
			
			if (log.isDebugEnabled()) {
				log.debug("##################llega evidencia id :::::::::" + evidencia);
				log.debug("##################llega evidencia:::::::::" + palabraUno);
				log.debug("##################llega evidencia:::::::::" + palabraDos);
				log.debug("##################llega evidencia:::::::::" + palabraTres);
				log.debug("##################llega evidencia:::::::::" + palabraCuatro);
				log.debug("##################llega evidencia:::::::::" + palabraCinco);
			}			
			
			List<String> lstPalabra = new ArrayList<String>();
			lstPalabra.add(palabraUno);
			lstPalabra.add(palabraDos);
			lstPalabra.add(palabraTres);
			lstPalabra.add(palabraCuatro);
			lstPalabra.add(palabraCinco);			
			
			Objetos tipoObjeto = Objetos.getByValor(new Long(evidencia));
			
			FiltroExpedienteDTO filtrosBusquedaExpediente=new FiltroExpedienteDTO();
			//Se realiza la siguiente validacion dado que para no afectar el arbol de elementos de los
			//editores de texto para la cnsulta del objeto otro y solucionar la consulta de expedientes por evidencias
			if(new Long(evidencia)== Objetos.OTRO.getValorId().longValue())
			{
				filtrosBusquedaExpediente.setNombreEvidencia("Objeto");
				log.debug("##################Entity_nombre_evidencias OTRO::::::");
			}
			else
			{
				filtrosBusquedaExpediente.setNombreEvidencia(tipoObjeto.getNombreEntity());
				log.debug("##################Entity_nombre_evidencias ::::::" + tipoObjeto.getNombreEntity() +" - ID TipoObjeto:: "+ evidencia +" -  enum:: "+Objetos.OTRO.getValorId());
			}
			
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
				writer.print("<row id='"+ expedienteDTO.getNumeroExpedienteId()+"'>");//se cambio por numeroExpedienteId dado que el solo Id no era el correcto
				writer.print("<cell>" + expedienteDTO.getCasoDTO().getNumeroGeneralCaso()+ "</cell>");
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
				
				writer.print("<row id='" + involucradoDTO.getExpedienteDTO().getNumeroExpedienteId()+ "'>");//se cambio por numeroExpedienteId dado que el solo Id no era el correcto
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
				log.debug("llega apellido materno " + forma.getApellidoMat() );
			}
			FiltroExpedienteDTO filtrosBusquedaExpediente=new FiltroExpedienteDTO();
			filtrosBusquedaExpediente.setNombre(forma.getNombre());
			filtrosBusquedaExpediente.setApellidos(forma.getApellido());
			filtrosBusquedaExpediente.setApellidoMat(forma.getApellidoMat());
			
			UsuarioDTO usuarioFirmado = super.getUsuarioFirmado(request);
			
			List<InvolucradoDTO> listInvolucradoDTOs =expedienteDelegate.consultarExpedientesPorFiltrosYDiscriminante(filtrosBusquedaExpediente,usuarioFirmado);

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
				
				writer.print("<row id='" + involucradoDTO.getExpedienteDTO().getNumeroExpedienteId() + "'>");//se cambio por numeroExpedienteId dado que el solo Id no era el correcto
				
					if (involucradoDTO.getExpedienteDTO() != null
							&& involucradoDTO.getExpedienteDTO().getCasoDTO() != null
							&& involucradoDTO.getExpedienteDTO().getCasoDTO()
									.getNumeroGeneralCaso() != null) {
						writer.print("<cell>"
								+ involucradoDTO.getExpedienteDTO()
										.getCasoDTO().getNumeroGeneralCaso()
								+ "</cell>");
					}
					else{
						writer.print("<cell>"+" "+"</cell>");
					}
				
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
	 * Metodo utilizado para realizar la consulta de expedientes en forma de xml
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarExpedientePorNumeroDeExpedienteComparaExpediente(ActionMapping mapping,
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
			
			UsuarioDTO usuarioDTO = super.getUsuarioFirmado(request);
			
			if(usuarioDTO.getAreaActual().getAreaId().longValue() == Areas.COORDINACION_DEFENSORIA.parseLong().longValue()){
				filtrosBusquedaExpediente.setUsuario(new UsuarioDTO());
			}else{
				filtrosBusquedaExpediente.setUsuario(usuarioDTO);
			}
			
			List<ExpedienteDTO> listExpedienteDTOs=expedienteDelegate.buscarExpedientes(filtrosBusquedaExpediente);
			log.debug("##################listExpedienteDTOs::::::::: " + listExpedienteDTOs.size());
			

			converter.alias("listExpedienteDTOs", java.util.List.class);
			converter.alias("expedienteDTO", ExpedienteDTO.class);
			String xml = converter.toXML(listExpedienteDTOs);

			log.debug("##################listExpedienteDTOs   XML::::::::: " + xml);
			
			response.setContentType("text/xml");
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();	
			
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para realizar la consulta de expedientes
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarExpedientePorNumeroDeExpedienteParaDocumentos(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			
			UsuarioDTO usuario = getUsuarioFirmado(request);
			
			 //Para que no busque por area
			
			if (usuario != null && usuario.getAreaActual() != null
					&& usuario.getAreaActual().getAreaId() != null) {
				usuario.getAreaActual().setAreaId(null);
			}
			
			log.info(":::::::::::::VERIFICANDO PARAMETROS:::::::::::::::::::::");			
			log.info("Numero de Expediente=" + request.getParameter("numeroExpedienteId"));
			log.info("FechaIni=" + request.getParameter("fechaIni"));
			log.info("FechaFin=" + request.getParameter("fechaFin"));

			String noExpediente = request.getParameter("numeroExpedienteId");
			
			FiltroExpedienteDTO filtrosBusquedaExpediente = new FiltroExpedienteDTO();
			
			List<ExpedienteDTO> listExpedienteDTOs = new ArrayList<ExpedienteDTO>();
			
			filtrosBusquedaExpediente.setNumeroExpediente(noExpediente);
			filtrosBusquedaExpediente.setUsuario(usuario);
			
			if (noExpediente != null && noExpediente != "") {
				listExpedienteDTOs = expedienteDelegate
						.buscarExpedientes(filtrosBusquedaExpediente);
			}else{
				
				if (usuario.getFuncionario() != null
						&& usuario.getFuncionario().getClaveFuncionario() != null) {
					usuario.getFuncionario().setClaveFuncionario(null);
				}
				
				Date fechaInicio = new Date();
				Date fechaFinal = new Date();
				
				if (request.getParameter("fechaIni") != null
						&& request.getParameter("fechaIni") != ""
						&& request.getParameter("fechaFin") != null
						&& request.getParameter("fechaFin") != "") {
					
					fechaInicio = DateUtils.obtener(request.getParameter("fechaIni"),"00:00");
					fechaFinal = DateUtils.obtener(request.getParameter("fechaFin"),"00:00");
					
					listExpedienteDTOs = expedienteDelegate
							.consultarExpedientesPorFiltro(fechaInicio,
									fechaFinal, usuario.getFuncionario(), null, null);
					
				}else{
					
					fechaInicio = new Date();
					Calendar calTempDec = Calendar.getInstance();
					
					calTempDec.setTime(fechaInicio);
					calTempDec.add(Calendar.DATE, 1);
					log.info(calTempDec + "tiempo despues tiempo antes"	+ fechaInicio);
					listExpedienteDTOs = expedienteDelegate
							.consultarExpedientesPorFiltro(fechaInicio,
									calTempDec.getTime(), usuario.getFuncionario(), null,
									null);
				}
			}
			
			log.info("lista de expedientes else" + listExpedienteDTOs);
			
			//List<DocumentoDTO> documentoDTOs = new ArrayList<DocumentoDTO>();

			List<BitacoraMovimientoDTO> bitacoraMovimientoDTOs = new ArrayList<BitacoraMovimientoDTO>();
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");

			final PaginacionDTO pag = PaginacionThreadHolder.get();
			
			log.debug("pag :: " + pag);
			
			if (pag != null && pag.getTotalRegistros() != null) {
				writer.print("<total>" + pag.getTotalPaginas() + "</total>");
				writer.print("<records>" + pag.getTotalRegistros()
						+ "</records>");
			} else {
				writer.print("<total>0</total>");
				writer.print("<records>0</records>");
			}

			for (ExpedienteDTO expedienteDTO : listExpedienteDTOs) {
				writer.print("<row id='"+ expedienteDTO.getNumeroExpedienteId() + "'>");
				writer.print("<cell>");
					if (expedienteDTO.getNumeroExpediente() != null) {
						writer.print(expedienteDTO.getNumeroExpediente());
					} else {
						writer.print("-");
					}
				writer.print("</cell>");

				writer.print("<cell>");
					if (expedienteDTO.getCasoDTO() != null) {
						writer.print(expedienteDTO.getCasoDTO()
								.getNumeroGeneralCaso());
					} else {
						writer.print("-");
					}
				writer.print("</cell>");

				writer.print("<cell>");
					if (expedienteDTO.getStrFechaApertura() != null) {
						writer.print(expedienteDTO.getStrFechaApertura());
					} else {
						writer.print("-");
					}
				writer.print("</cell>");

				writer.print("<cell>");
					if (expedienteDTO.getNumeroExpediente() != null) {
	
						bitacoraMovimientoDTOs = bitacoraMovimientosDelegate
								.consultarBitacoraMovimientoPorNumeroExpedienteCategoria(
										expedienteDTO.getNumeroExpediente(), null);
						if (bitacoraMovimientoDTOs.isEmpty()) {
							writer.print("-");
						} else {
	
							for (BitacoraMovimientoDTO bitacoraMovimientoDTO : bitacoraMovimientoDTOs) {
								if (bitacoraMovimientoDTO.getFechaMovimiento() != null) {
									writer.print(bitacoraMovimientoDTO
											.getFechaMovimiento());
	
								} else {
									writer.print("-");
									writer.print("</cell>");
								}
							}
						}
	
					} else {
						writer.print("-");
					}
				writer.print("</cell>");

				writer.print("<cell>");
					if (expedienteDTO.getDocumentosDTO() != null) {
						for (DocumentoDTO documentoDTO : expedienteDTO
								.getDocumentosDTO()) {
							if (documentoDTO.getArchivoDigital() != null) {
								writer.print("Si");
							} else {
	
								writer.print("no");
							}
						}
					} else {
						writer.print("-");
					}
				writer.print("</cell>");
				writer.print("</row>");
			}

			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.info(e.getCause(), e);

		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para realizar la consulta de expedientes por area, por
	 * catDiscriminante del Usuario y por fecha de creacion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward busquedaExpedientesXEstatusXFechaXAreaXDiscriminante(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {

			if (log.isDebugEnabled()) {
				log.debug("::::::EJECUNTANDO ACTION busquedaExpedientesXEstatusXFechaXAreaXDiscriminante:::::");
			}

			String fechaInicio = request.getParameter("fechaInicio");
			String fechaFin = request.getParameter("fechaFin");
			String estatusExpediente = request
					.getParameter("estatusExpediente");

			if (log.isDebugEnabled()) {
				log.debug("VERIFICANDO PARAMETROS:::::::::");
				log.debug("fechaInicio=" + fechaInicio);
				log.debug("fechaFin=" + fechaFin);
				log.debug("estatusExpediente=" + estatusExpediente);
			}

			Date iniDate = null;
			Date finDate = null;
			
			if (fechaInicio != null) {
				iniDate = DateUtils.obtener(fechaInicio);
			}
			if (fechaFin != null) {
				finDate = DateUtils.obtener(fechaFin);
			}
			
			Long estatus = NumberUtils.toLong(estatusExpediente, 0);
			

			List<ExpedienteDTO> listExpedienteDTOs = expedienteDelegate
					.consultarExpedientesPorFiltroST(iniDate, finDate,
							super.getUsuarioFirmado(request),
							estatus);

			log.debug("TAMAÑO LISTA DE EXPEDIENTES="
					+ listExpedienteDTOs.size());

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

//			int lTotalRegistros = listExpedienteDTOs.size();
//
//			writer.print("<records>" + lTotalRegistros + "</records>");
			
			for (ExpedienteDTO expedienteDTO : listExpedienteDTOs) {

				//Id del renglon
				writer.print("<row id='" + expedienteDTO.getNumeroExpedienteId()
						+ "'>");

				//Numero de Expediente
				writer.print("<cell>" + expedienteDTO.getNumeroExpediente()
						+ "</cell>");

				//Fecha de apertura del expediente
				if (expedienteDTO.getStrFechaApertura() != null) {
					writer.print("<cell>" + expedienteDTO.getStrFechaApertura()
							+ "</cell>");
				} else {
					writer.print("<cell>" + " " + "</cell>");
				}
				
				//Columna Denunciante
				log.info("Este es el expediente con calidad de denunciante"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE));
				log.info("invol tamaño"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
				log.info("invol tamaño de"+expedienteDTO.getInvolucradosDTO().size());
				 boolean op=true;
				for (InvolucradoDTO involucradoDTO : expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE)) {
					log.info("numero de involucrado nombre completo perdon:"+involucradoDTO.getNombreCompleto());
					for (NombreDemograficoDTO nombreDemograficoDTO : involucradoDTO.getNombresDemograficoDTO()) {
						log.info("Verdadero nombre:"+nombreDemograficoDTO.getEsVerdadero());
						if(nombreDemograficoDTO.getEsVerdadero()!=null && nombreDemograficoDTO.getEsVerdadero()){
							writer.print("<cell>"+nombreDemograficoDTO.getNombre()+" "+nombreDemograficoDTO.getApellidoPaterno()+" "+nombreDemograficoDTO.getApellidoMaterno() +"</cell>");
							op=false;
						}
					}
				}
				if(op){
					writer.print("<cell>"+"Anónimo"+"</cell>");
				}


				
				//Delito principal
				if (expedienteDTO.getDelitoPrincipal() != null
						&& expedienteDTO.getDelitoPrincipal().getCatDelitoDTO() != null
						&& expedienteDTO.getDelitoPrincipal().getCatDelitoDTO()
								.getNombre() != null) {
					writer.print("<cell>"
							+ expedienteDTO.getDelitoPrincipal()
									.getCatDelitoDTO().getNombre().toLowerCase() + "</cell>");
				} else {
					writer.print("<cell>" + "---" + "</cell>");

				}
				
				//Columna Origen 
				if(expedienteDTO.getOrigen()!=null){
					writer.print("<cell>"+expedienteDTO.getOrigen().getValor().toLowerCase()+"</cell>");
				}else{
					writer.print("<cell>"+"Denuncia"+"</cell>");
				}
				
				//Estatus
				if(expedienteDTO.getEstatus()!=null && expedienteDTO.getEstatus().getValor() !=null){
					writer.print("<cell>"+ expedienteDTO.getEstatus().getValor().toLowerCase() +"</cell>");
				}else{
					writer.print("<cell>"+"---"+"</cell>");
				}
				writer.print("</row>");
				
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.info(e.getCause(), e);

		}
		return null;
	}
	
	
	public ActionForward consultarExpedientesSistemaTradicional(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {

			log.info("EJECUTANDO ACTION consultarExpedientesSistemaTradicional");

			String idNumeroExpediente = request
					.getParameter("idNumeroExpediente");

			log.info("VERIFICANDO PARAMETROS ExpedienteId: "
					+ idNumeroExpediente);

			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(
					Areas.AGENCIA_DEL_MINISTERIO_PUBLICO));

			if (idNumeroExpediente != null) {
				expedienteDTO.setNumeroExpedienteId(Long
						.parseLong(idNumeroExpediente));
			}
			expedienteDTO = expedienteDelegate.obtenerExpediente(expedienteDTO);

			expedienteDTO.setConsulta(true);
			log.info("ACTION- NUMERO EXPEDIENTE ENCONTRADO:"
					+ expedienteDTO.getNumeroExpediente());

			request.getSession().removeAttribute("numExpConsul");
			request.getSession().setAttribute("numExpConsul",
					expedienteDTO.getNumeroExpediente());
			request.getSession().setAttribute("numeroExpediente",
					expedienteDTO.getNumeroExpediente());
			request.getSession().setAttribute("idNumeroExpedienteop",
					expedienteDTO.getNumeroExpedienteId());
			request.getSession().setAttribute("idExpedienteop",
					expedienteDTO.getExpedienteId());
			request.getSession().setAttribute("idExpedienteConsulop",
					expedienteDTO.getExpedienteId());

			// IMPLEMENTACION ANTERIOR
			// request.getSession().removeAttribute("numExpConsul");
			// request.getSession().setAttribute("numExpConsul",
			// expedienteDTO.getNumeroExpediente());
			// request.getSession().setAttribute("idExpedienteConsul",
			// expedienteDTO.getNumeroExpedienteId());
			// request.getSession().setAttribute("idExpedienteConsulop",
			// expedienteDTO.getExpedienteId());
			// request.getSession().setAttribute("numeroCasoConsul",
			// expedienteDTO.getCasoDTO().getNumeroGeneralCaso());

			log.info("area_exp:: " + expedienteDTO.getArea().getAreaId());

			// asignamos la pantalla solicitada
			if (expedienteDTO.getArea().getAreaId().longValue() == Areas.ATENCION_TEMPRANA_PG_PENAL
					.parseLong()) {
				log.info("area_enum:: "
						+ Areas.ATENCION_TEMPRANA_PG_PENAL.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 1);
			} else if (expedienteDTO.getArea().getAreaId().longValue() == Areas.JUSTICIA_ALTERNATIVA_RESTAURATIVA
					.parseLong()) {
				log.info("area_enum:: "
						+ Areas.JUSTICIA_ALTERNATIVA_RESTAURATIVA.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 2);
			} else if (expedienteDTO.getArea().getAreaId().longValue() == Areas.UNIDAD_INVESTIGACION
					.parseLong()) {
				log.info("area_enum:: "
						+ Areas.UNIDAD_INVESTIGACION.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 3);
			} else if (expedienteDTO.getArea().getAreaId().longValue() == Areas.CORRDINACION_UNIDAD_INVESTIGACION
					.parseLong()) {
				log.info("area_enum:: "
						+ Areas.UNIDAD_INVESTIGACION.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 4);
			} else if (expedienteDTO.getArea().getAreaId().longValue() == Areas.FISCAL_FACILITADOR
					.parseLong()) {
				log.info("area_enum:: " + Areas.FISCAL_FACILITADOR.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 5);
			} else if (expedienteDTO.getArea().getAreaId().longValue() == Areas.POLICIA_MINISTERIAL
					.parseLong()) {
				log.info("area_enum:: " + Areas.POLICIA_MINISTERIAL.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 6);
			} else if (expedienteDTO.getArea().getAreaId().longValue() == Areas.COORDINACION_VISITADURIA
					.parseLong()) {
				log.info("area_enum:: "
						+ Areas.COORDINACION_VISITADURIA.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 7L);
			} else if (expedienteDTO.getArea().getAreaId().longValue() == Areas.VISITADURIA
					.parseLong()) {
				log.info("area_enum:: " + Areas.VISITADURIA.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 8);
			} else if (expedienteDTO.getArea().getAreaId().longValue() == Areas.AGENCIA_DEL_MINISTERIO_PUBLICO
					.parseLong()) {
				log.info("area_enum:: "
						+ Areas.AGENCIA_DEL_MINISTERIO_PUBLICO.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 9);
			}
			super.setExpedienteTrabajo(request, expedienteDTO);
			log.info("SUBE A SESION EL EXPEDIENTE DE TRABAJO: " + expedienteDTO);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return mapping.findForward("succes");
	}

	/**
	 * Metodo utilizado para realizar la consulta de expedientes en forma de xml
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward actualizarCatDiscriminanteDeExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
        	Long catDiscriminanteId = NumberUtils.toLong(request.getParameter("catDiscriminanteId"),0L);
        	Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"),0L);
        	
        	ExpedienteDTO expedienteDTO = new ExpedienteDTO();
        	CatDiscriminanteDTO discriminante = new CatDiscriminanteDTO();
        	discriminante.setCatDiscriminanteId(catDiscriminanteId);
        	
        	expedienteDTO.setNumeroExpedienteId(numeroExpedienteId);
        	expedienteDTO.setDiscriminante(discriminante);
        	
        	Boolean respuesta = expedienteDelegate.actualizarCatDiscriminanteDeExpediente(expedienteDTO);

        	if(respuesta!=null){
				log.info("respuesta: " + respuesta);
			}else{
				log.info("respuesta: NULO");
}
			converter.alias("boolean", java.lang.Boolean.class);
			String xml = converter.toXML(respuesta);
			escribir(response, xml,null);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}	               	
        	
        } catch (Exception e) {
            log.error(e.getMessage(), e);               	        	
        }   
		return null;
	}

}
