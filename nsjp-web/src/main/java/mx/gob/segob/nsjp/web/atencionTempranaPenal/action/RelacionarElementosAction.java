package mx.gob.segob.nsjp.web.atencionTempranaPenal.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.elemento.ElementoDelegate;
import mx.gob.segob.nsjp.delegate.relacion.RelacionDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;
import mx.gob.segob.nsjp.dto.relacion.CatCategoriaRelacionDTO;
import mx.gob.segob.nsjp.dto.relacion.CatRelacionDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class RelacionarElementosAction extends GenericAction{
	/* Log de clase*/
	private static final Logger log  = Logger.getLogger(RelacionarElementosAction.class);
	
	@Autowired
	public ElementoDelegate elementoDelegate;
	@Autowired
	public RelacionDelegate relacionDelegate;
	
	/**
	 * funcion para mandar a la vista el XML con la informacion de los elementos asociados a un idExpediente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarElementosPorExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		
		String numeroExpediente=request.getParameter("numeroExpediente");
		Long idCategoriaRelacion=Long.parseLong(request.getParameter("idCategoriaRelacion"));		
		Boolean esSujeto= null;
		ExpedienteDTO expedienteDTO = super.getExpedienteTrabajo(request,numeroExpediente);		
		
		if(request.getParameter("esSujeto").equals("true"))
			esSujeto = true;
		else
			esSujeto = false;
			
		
		log.info(" numeroExpediente: " + numeroExpediente);
		log.info(" idExpediente: " + expedienteDTO.getExpedienteId());
		log.info("  idCategoriaRelacion: " + idCategoriaRelacion);
		log.info("  esSujeto: " + esSujeto);
		log.info("	::::::::::::::::::::::::::::::::::::::");	
		
		try {
			
			StringBuffer sb= new StringBuffer();
			String xml = "";
			
			List<ElementoDTO> listaElementoDTOs=new ArrayList<ElementoDTO>();
			
			//recuperamos los elementos		
			listaElementoDTOs = elementoDelegate.consultarElementosXIdExpedienteCatRelacion(expedienteDTO.getExpedienteId(), idCategoriaRelacion, esSujeto);
			converter.alias("listaElementoDTOs", java.util.List.class);
			converter.alias("ElementoDTO", ElementoDTO.class);
			
			xml = converter.toXML(listaElementoDTOs);
			sb.append(xml);
			
			if(log.isDebugEnabled())
			{
				log.debug(sb.toString());
			}
			escribirRespuesta(response, sb.toString());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * funcion para mandar a la vista el XML elementos del catalogo de CatRelacion:
	 * Abogado, Hermano, Abuela, etc.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarCatRelacionesXCatCategoria(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {			
			Long idCategoriaRelacion=Long.parseLong(request.getParameter("idCategoriaRelacion"));
			log.info(" ::::::: El idCategoriaRelacion:::::::" + idCategoriaRelacion);
			
			CatCategoriaRelacionDTO catCategoriaRelacionDTO = new CatCategoriaRelacionDTO();
			catCategoriaRelacionDTO.setCatCategoriaRelacionId(idCategoriaRelacion);
			
			StringBuffer sb= new StringBuffer();
			String xml = "";
			
			List<CatRelacionDTO> listaCatRelacionesDTOs=new ArrayList<CatRelacionDTO>();
			
			//recupera los catRelacion		
			listaCatRelacionesDTOs = relacionDelegate.consultarCatRelacionesXCatCategoriaRelacion(catCategoriaRelacionDTO);
			converter.alias("listaCatRelacionesDTOs", java.util.List.class);
			converter.alias("CatRelacionDTO", CatRelacionDTO.class);

			
			xml = converter.toXML(listaCatRelacionesDTOs);
			sb.append(xml);
			
			if(log.isDebugEnabled())
			{
				log.debug(sb.toString());
			}
			escribirRespuesta(response, sb.toString());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * funcion para mandar a la vista el XML con los catalogos de categorias de una relacion
	 * p.e.: persona-persona, persona -objet, persona - lugar etc
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarCatalogosDeCategoriasDeRelacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			
			StringBuffer sb= new StringBuffer();
			String xml = "";
			
			List<CatCategoriaRelacionDTO> listaCatCategoriaRelacionDTO= new ArrayList<CatCategoriaRelacionDTO>();
			listaCatCategoriaRelacionDTO = relacionDelegate.consultarCatCategoriaRelacionSiDocumento(false);
			log.info("CatCategoriaRelacionDTO.size(): " + listaCatCategoriaRelacionDTO.size());
			converter.alias("listaCatCategoriaRelacionDTO", java.util.List.class);
			converter.alias("CatCategoriaRelacionDTO", CatCategoriaRelacionDTO.class);
			xml = converter.toXML(listaCatCategoriaRelacionDTO);
			sb.append(xml);
			
			if(log.isDebugEnabled())
			{
				log.debug(sb.toString());
			}
			escribirRespuesta(response, sb.toString());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * funcion que permite verificar si existe un registro en la tabla Relacion que corresponda
	 * con los siguientes parametros:
	 * {@code idCatRelacion, idElementoSujeto y idElementoComplemento}
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward validarRelacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("	:::: PARAMETROS RECUPERADOS ::::::::::");
		Long idCatRelacion=Long.parseLong(request.getParameter("idCatRelacion"));
		Long idElementoSujeto=Long.parseLong(request.getParameter("idElementoSujeto"));
		Long idElementoComplemento=Long.parseLong(request.getParameter("idElementoComplemento"));
		log.info(" idCatRelacion: " + idCatRelacion);		
		log.info(" IdElementoSujeto: " + idElementoSujeto);
		log.info("  idElementoComplemento: " + idElementoComplemento);
		log.info("	::::::::::::::::::::::::::::::::::::::");		
		
		try {
			
			StringBuffer sb= new StringBuffer();
			String xml = "";
			
			Boolean esValida = relacionDelegate.validarRelacion(idCatRelacion, idElementoSujeto, idElementoComplemento);
			converter.alias("RelacionDTO", RelacionDTO.class);
			converter.alias("esValida", Boolean.class);			
			xml = converter.toXML(esValida);
			sb.append(xml);
			
			if(log.isDebugEnabled())
			{
				log.debug(sb.toString());
			}
			escribirRespuesta(response, sb.toString());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * funcion que permite registrar un registro en la tabla Relacion que corresponda
	 * con los siguientes parametros:
	 * {@code idCatRelacion, idElementoSujeto y idElementoComplemento}
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward registrarRelacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("	:::: PARAMETROS RECUPERADOS ::::::::::");
		Long idCatRelacion=Long.parseLong(request.getParameter("idCatRelacion"));
		Long idElementoSujeto=Long.parseLong(request.getParameter("idElementoSujeto"));
		Long idElementoComplemento=Long.parseLong(request.getParameter("idElementoComplemento"));
		log.info(" idCatRelacion: " + idCatRelacion);		
		log.info(" IdElementoSujeto: " + idElementoSujeto);
		log.info("  idElementoComplemento: " + idElementoComplemento);
		log.info("	::::::::::::::::::::::::::::::::::::::");		
		
		try {
			
			StringBuffer sb= new StringBuffer();
			String xml = "";
			
			relacionDelegate.registrarRelacion(idCatRelacion, idElementoSujeto, idElementoComplemento);
			converter.alias("true", Boolean.class);
			xml = converter.toXML(true);
			sb.append(xml);
			
			if(log.isDebugEnabled())
			{
				log.debug(sb.toString());
			}
			escribirRespuesta(response, sb.toString());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	
	/**
	 * Permite consultar las relaciones por expediente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarRelacionesXExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {		
		try {
			String numeroExpediente = request.getParameter("numeroExpediente");			
			
			log.info("$$$$ numeroExpediente $$$ : " + numeroExpediente );
			
			VehiculoDTO loVehiculo = new VehiculoDTO();
			loVehiculo.setPlaca("123ABC");
			loVehiculo.setNoSerie("null");
			
			List<RelacionDTO> listaObjetosDTOs= elementoDelegate.consultarElementosXIdExpediente(numeroExpediente);

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
			
			for (RelacionDTO relacionDTO : listaObjetosDTOs) {
				writer.print("<row id='"+relacionDTO.getRelacionId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (relacionDTO.getCatRelacion() != null && relacionDTO.getCatRelacion().getDescripcionRelacion() != null? relacionDTO.getCatRelacion().getDescripcionRelacion():"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (relacionDTO.getCatRelacion() != null && relacionDTO.getCatRelacion().getDesCategoriaRelacion() != null? relacionDTO.getCatRelacion().getDesCategoriaRelacion():"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (relacionDTO.getElementoBySujetoId() != null && relacionDTO.getElementoBySujetoId().getStrDescripcionRelacionarElemento() != null? relacionDTO.getElementoBySujetoId().getStrDescripcionRelacionarElemento():"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (relacionDTO.getElementoByComplementoId() != null && relacionDTO.getElementoByComplementoId().getStrDescripcionRelacionarElemento() != null? relacionDTO.getElementoByComplementoId().getStrDescripcionRelacionarElemento():"-") +" </div>]]></cell>");
				writer.print("</row>");
			}
		writer.print("</rows>");
		writer.flush();
		writer.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * funcion que permite anular la relacion de elementos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward anularRelacionDeElementos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("	:::: PARAMETROS RECUPERADOS ::::::::::");
		String idCatRelacion= request.getParameter("idsRelacionesSeleccionados");
		
		//creamos la lista de los IDs  de las relaciones
		List<Long> idsRelacion=new ArrayList<Long>();
		String[] relaciones =null;
		
		if(idCatRelacion != null && idCatRelacion.length()>0){
			relaciones = idCatRelacion.split(",");
			for (String relacion: relaciones)
				idsRelacion.add(Long.parseLong(relacion));//agregamos el ID de la relacion correspondiente			
		}	
		
		log.info("	::::::::::::::::: El total de Id´s recibidos es: ::::::::::::::::::::: " + relaciones.length);		
		
		try {
			
			StringBuffer sb= new StringBuffer();
			String xml = "";
			
			relacionDelegate.actualizarEsActivoRelaciones(idsRelacion);
			converter.alias("true", Boolean.class);
			xml = converter.toXML(true);
			sb.append(xml);
			
			if(log.isDebugEnabled())
			{
				log.debug(sb.toString());
			}
			escribirRespuesta(response, sb.toString());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	
	
}
