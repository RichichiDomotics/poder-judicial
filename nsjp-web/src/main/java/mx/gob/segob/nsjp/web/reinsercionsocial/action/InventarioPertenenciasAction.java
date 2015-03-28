/**
* Nombre del Programa 		: InventarioPertenenciasAction.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 							Fecha: 29 Mar 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Describir el objetivo de la clase de manera breve
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 							Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 							Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.reinsercionsocial.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.catalogo.CatalogoDelegate;
import mx.gob.segob.nsjp.delegate.detencion.DetencionDelegate;
import mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate;
import mx.gob.segob.nsjp.delegate.reinsercion.ReinsercionDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.reinsercionsocial.form.InventarioPertenenciasForm;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class InventarioPertenenciasAction extends GenericAction {
	
	private static final String PARAM_ID_SENTENCIA = "sentenciaId";
	private static final String PARAM_ID = "id";
	private static final String PARAM_CAMBIO_EXITO = "cambioExitoso";
	private static final String ATTR_INV_PER = "inventarioConsultado";
	private static final String ATTR_SENTENCIA = "sentenciaConsultada";
	
	private static final String FORMATO_BASICO_FECHA = "dd/MM/yyyy";
	
	private static final String FWD_INV_PER = "administrar.inventarioPertenencias.page";
	
	private static final Logger LOG = Logger.getLogger(InventarioPertenenciasAction.class);
	
	@Autowired
	private AsignacionProgramaDelegate asignacionProgramaDelegate;
	
	@Autowired
	private ReinsercionDelegate reinsercionDelegate;
	
	@Autowired
	private CatalogoDelegate catalogoDelegate;
	
	@Autowired
	private DetencionDelegate detencionDelegate;
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarInventarioPertenencias(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		if (form instanceof InventarioPertenenciasForm){
			InventarioPertenenciasForm forma = (InventarioPertenenciasForm) form;
			try {
				String sentenciaId = request.getParameter(PARAM_ID_SENTENCIA);
				if (sentenciaId != null){					
					forma.setSentenciaId(Long.parseLong(request.getParameter(PARAM_ID_SENTENCIA)));
					SentenciaDTO sentencia = new SentenciaDTO();
					sentencia.setSentenciaId(forma.getSentenciaId());
					DateFormat sdf = new SimpleDateFormat(FORMATO_BASICO_FECHA);
					sentencia = asignacionProgramaDelegate.consultarSentenciaPorId(sentencia);
					request.getSession().setAttribute(ATTR_SENTENCIA, sentencia);
					forma.setFechaFinPena(sdf.format(sentencia.getDfechaFinPena()));
					forma.setFechaInicioPena(sdf.format(sentencia.getDfechaInicioPena()));
					forma.setNombreSentenciado(sentencia.getInvolucradoDTO().getNombreCompleto());
					forma.setNus(sentencia.getCnus());
					forma.setTipoSentencia(sentencia.getValorDTO().getValor());
					if (sentencia.getCentroDetencionActual() != null){
						forma.setNombreCereso(sentencia.getCentroDetencionActual().getNombre());
					}else{
						forma.setNombreCereso("");
					}
					forma.setPuntosPorAcumular(sentencia.getIpuntosPorAcumular());
					forma.setPuntosAcumulados(sentencia.getTotalPuntosObtenidos());
					forma.setLstCatCondicionPertDTO(catalogoDelegate.recuperarCatalogo(Catalogos.CONDICION_FISICA_OBJETO));
					forma.setLstCatTipoPertDTO(catalogoDelegate.recuperarCatalogo(Catalogos.TIPO_PERTENENCIA));
					forma.setLstCatUnidadPertDTO(catalogoDelegate.recuperarCatalogo(Catalogos.UNIDAD_MEDIDA));
					InventarioPertenenciaDTO invPerDTO = sentencia.getInventarioPertenenciaDTO();
					if (invPerDTO == null){
						invPerDTO = crearInventarioPertenencia(sentencia);
					}
					forma.setDefinitivo(invPerDTO.getDefinitivo());
					request.getSession().setAttribute(ATTR_INV_PER, invPerDTO);
				}
			} catch (NSJPNegocioException e) {
				e.printStackTrace();
			}
		}
		return mapping.findForward(FWD_INV_PER);
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward llenarGridInventarioPertenencias(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		List<PertenenciaDTO> pertenenciasDTO = null;
		InventarioPertenenciaDTO invPerDTO = obtenerInventarioPertenencias(request);

		final PaginacionDTO pag = PaginacionThreadHolder.get();
		if (pag != null){
			pag.setCampoOrd(" p.tipoPertenencia.valor ");
			PaginacionThreadHolder.set(pag);
				try {
					pertenenciasDTO = reinsercionDelegate.consultarPertenenciasPorInventario(invPerDTO);
				} catch (NSJPNegocioException e) {
					LOG.error(e.getStackTrace());
				}
			}		

		List<List<String>> tablaValores = new ArrayList<List<String>>();
		if (pertenenciasDTO != null && !pertenenciasDTO.isEmpty()){
			for (PertenenciaDTO perDTO : pertenenciasDTO) {
				tablaValores.add(crearFilaParaGrid(perDTO));
			}				
		}

		escribirXml(response, tablaValores);
		
		return null;
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward obtenerDetallesPertenencia (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
		
			String id = request.getParameter(PARAM_ID);
			if(id!=null){
				PertenenciaDTO per = new PertenenciaDTO();
				per.setPertenenciaId(Long.parseLong(id));
				per = detencionDelegate.consultarPertenenciaPorId(per);	
				
				response.setContentType("text/javascript; charset=ISO-8859-1");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print(dto2Json(per));
				writer.flush();
				writer.close();
			}
		
		} catch (Exception e) {		
			LOG.error(e.getCause(),e);
		}
		return null;
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward guardarPertenenciaInventario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
	
		InventarioPertenenciasForm forma = (InventarioPertenenciasForm) form;
		
		SentenciaDTO sentencia = obtenerSentencia(request);
		
		InventarioPertenenciaDTO invPer = obtenerInventarioPertenencias(request);
		
		try {
			DetencionDTO detencion = detencionDelegate.consultarDetencion(sentencia.getInvolucradoDTO().getElementoId(), 
					sentencia.getNumeroExpedienteDTO().getNumeroExpediente());
			
			PertenenciaDTO pertenenciaAGuardar = obtenerPertenenciaParaGuardar(forma, invPer, detencion);
			pertenenciaAGuardar.setPertenenciaId(null);
			detencionDelegate.registrarPertenecia(pertenenciaAGuardar);
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
		return new ActionForward(mapping.findForward(FWD_INV_PER).getPath()+"?"
				+PARAM_ID_SENTENCIA+"="+ sentencia.getSentenciaId()+"&"+PARAM_CAMBIO_EXITO+"="+Boolean.TRUE, false);
		
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward actualizarPertenenciaInventario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
	
		InventarioPertenenciasForm forma = (InventarioPertenenciasForm) form;
		
		SentenciaDTO sentencia = obtenerSentencia(request);
	
		InventarioPertenenciaDTO invPer = obtenerInventarioPertenencias(request);
		
		try {
			DetencionDTO detencion = detencionDelegate.consultarDetencion(sentencia.getInvolucradoDTO().getElementoId(), 
					sentencia.getNumeroExpedienteDTO().getNumeroExpediente());
			
			PertenenciaDTO pertenenciaActualizar = obtenerPertenenciaParaGuardar(forma, invPer, detencion);
			detencionDelegate.actualizarPertenencia(pertenenciaActualizar);
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
		return new ActionForward(mapping.findForward(FWD_INV_PER).getPath()+"?"
				+PARAM_ID_SENTENCIA+"="+ sentencia.getSentenciaId()+"&"+PARAM_CAMBIO_EXITO+"="+Boolean.TRUE, false);
		
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward eliminarPertenenciaInventario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
	
		InventarioPertenenciasForm forma = (InventarioPertenenciasForm) form;
		
		SentenciaDTO sentencia = obtenerSentencia(request);
		
		InventarioPertenenciaDTO invPer = obtenerInventarioPertenencias(request);
		
		try {
			DetencionDTO detencion = detencionDelegate.consultarDetencion(sentencia.getInvolucradoDTO().getElementoId(), 
					sentencia.getNumeroExpedienteDTO().getNumeroExpediente());
			
			PertenenciaDTO pertenenciaEliminar = obtenerPertenenciaParaGuardar(forma, invPer, detencion);
			detencionDelegate.eliminarPertenencia(pertenenciaEliminar);
				
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}	
		
		return new ActionForward(mapping.findForward(FWD_INV_PER).getPath()+"?"
				+PARAM_ID_SENTENCIA+"="+ sentencia.getSentenciaId()+"&"+PARAM_CAMBIO_EXITO+"="+Boolean.TRUE, false);
		
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward generarReciboInventarioPertenencias(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		SentenciaDTO sentencia = obtenerSentencia(request);
		
		InventarioPertenenciaDTO invPerDTO = obtenerInventarioPertenencias(request);
		invPerDTO.setDefinitivo(Boolean.TRUE);
		
		reinsercionDelegate.actualizarInventarioPertenencias(invPerDTO);
		
		return new ActionForward(mapping.findForward(FWD_INV_PER).getPath()+"?"
				+PARAM_ID_SENTENCIA+"="+ sentencia.getSentenciaId()+"&"+PARAM_CAMBIO_EXITO+"="+Boolean.TRUE, false);
	}
	
	/**
	 * @param request
	 * @return
	 */
	private SentenciaDTO obtenerSentencia(HttpServletRequest request){
		Object attr = request.getSession().getAttribute(ATTR_SENTENCIA);
		SentenciaDTO sentencia = null;
		if (attr != null && attr instanceof SentenciaDTO){
			sentencia = (SentenciaDTO) attr;
		}
		return sentencia;
	}
	
	/**
	 * M&eacute;todo encargado de llevar a cabo la creaci&oacute;n de un inventario
	 * de pertenencias inicial al momento de ingresar a la pantalla de inventarios de 
	 * pertenencias. 
	 * @param sentenciaDTO - La sentencia de la cual se obtendr&aacute; el inventario.
	 * @return inventarioPertenenciaDTO - el inventario creado.
	 */
	private InventarioPertenenciaDTO crearInventarioPertenencia(SentenciaDTO sentenciaDTO){
		InventarioPertenenciaDTO invPer = new InventarioPertenenciaDTO();
		invPer.setSentenciaDTO(sentenciaDTO);
		invPer.setCentroDetencionDTO(sentenciaDTO.getCentroDetencionActual());
		invPer.setFechaIngreso(new Date());
		invPer.setDefinitivo(false);
		invPer.setEntregado(false);
		
		invPer = reinsercionDelegate.crearInventarioPertenencia(invPer);
		return invPer;
	}
	
	/**
	 * M&eacute;todo utilitario que se encarga de llevar a cabo la recuperaci&oacute;n
	 * del InventarioPertenenciaDTO de la sesi&oacute;n sobre la cual se est&aacute; 
	 * trabajando.  
	 * @param request - petici&oacute;n de Http de d&oacute;nde se recupera el objeto.
	 * @return InventarioPertenenciaDTO - El inventario subido previamente a la sesi&oacute;n
	 */
	private InventarioPertenenciaDTO obtenerInventarioPertenencias (HttpServletRequest request){
		Object attr = request.getSession().getAttribute(ATTR_INV_PER);
		InventarioPertenenciaDTO invPer = null;
		if (attr != null && attr instanceof InventarioPertenenciaDTO){
			invPer = (InventarioPertenenciaDTO) attr;
		}
		return invPer;
	}
	
	/**
	 * M&eacute;todo utilitario que crea una fila con los datos que se muestran 
	 * para el grid de pertenencias.
	 * @param perDTO - La pertenencia de la cual se obtienen los atributos para 
	 * 				   el grid.
	 * @return List<String> - Lista con los atributos en orden para el grid.
	 */
	private List<String> crearFilaParaGrid(PertenenciaDTO perDTO){
		List<String> fila = new ArrayList<String>();
		fila.add(perDTO.getPertenenciaId().toString());
		fila.add(perDTO.getTipoPertenencia().getValor());
		fila.add(perDTO.getDescripcion());
		if (perDTO.getCantidad() != null ){
			fila.add(perDTO.getCantidad().toString());
		}else{
			fila.add(null);
		}
		if (perDTO.getUnidadMedida() != null){			
			fila.add(perDTO.getUnidadMedida().getValor());
		}else{
			fila.add(null);
		}
		fila.add(perDTO.getCondicionFisica().getValor());
		return fila;
	}
	
	/**
	 * M&eacute;todo utilitario que agrega la informaci&oacute;n del paginado
	 * y escribe el xml regresado para que sea interpretado por el grid.
	 * @param response - respuesta de HTTP en donde se escribir&aacute; el xml
	 * @param tablaValores - tabla con los datos a escribir en el xml.
	 */
	private void escribirXml(HttpServletResponse response, List<List<String>> tablaValores){
		response.setContentType("text/xml; charset=ISO-8859-1");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print("<rows>");
			final PaginacionDTO pag2 = PaginacionThreadHolder.get();
			if (pag2 != null){
				//Caso particular para considerar el total de registros
				//pag2.setTotalRegistros(new Long(listaObjetos.size()));
				LOG.debug("pag :: " + pag2);
				if (pag2 != null && pag2.getTotalRegistros() != null) {
					writer.print("<total>" + pag2.getTotalPaginas() + "</total>");
					writer.print("<records>" + pag2.getTotalRegistros() + "</records>");
				} else {
					writer.print("<total>0</total>");
					writer.print("<records>0</records>");
				}
			}
			String datos = crearDatosGrid(tablaValores);
			writer.print(datos);
			writer.print("</rows>");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOG.error(e.getStackTrace());
		}

	}
	
	/**
	 * M&eacute;todo utilitario que escribe un xml dentro del 
	 * response de HTTP para que sea interpretado por el grid 
	 * para mostrar los datos. 
	 * @param matrizValores - Los datos a ingresar en el xml.
	 * @return String - Cadena que representa un xml con los 
	 * 					datos a mostrar en el grid.
	 */
	private String crearDatosGrid(List<List<String>> matrizValores){
		StringBuffer buffer = new StringBuffer();
		if (matrizValores != null && !matrizValores.isEmpty()){
			for (List<String> fila : matrizValores){
				if (fila != null && !fila.isEmpty()){
					for (int i=0; i<fila.size();i++){
						if (i==0){
							buffer.append("<row id='"+ fila.get(i)+"'>");
						}else{
							buffer.append("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>");
							if (fila.get(i) != null){
								buffer.append(fila.get(i));
							}else{
								buffer.append("&nbsp;");
							}
							buffer.append("</div>]]></cell>");
						}
					}
					buffer.append("</row>");
				}
			}
		}
		return buffer.toString();
	}
	
	/**
	 * M&eacute;todo utilitario que transforma un dto a notaci&oacute;n de JSON
	 * para que &eacute;ste sea enviado dentro del respuesta HTTP. 
	 * @param pertenenciaDTO - el DTO a transformar.
	 * @return String - Cadena con formato de JSON regresado al navegador.
	 */
	private String dto2Json(PertenenciaDTO pertenenciaDTO) {
		StringWriter out = null;
		String jsonText = "";
		JSONObject jsonObject = convertirJson(pertenenciaDTO);
		out = new StringWriter();
		try {
			jsonObject.writeJSONString(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		jsonText = out.toString();
		return jsonText;		
	}
	
	
	/**
	 * M&eacute;todo utilitario que transforma un dto a una representaci&oacute;n
	 * interna de JSON.
	 * @param pertenenciaDTO - El DTO a transformar.
	 * @return JSONObject - Objeto de JSON transformado.
	 */
	@SuppressWarnings("unchecked")
	private JSONObject convertirJson (PertenenciaDTO pertenenciaDTO){
		JSONObject json = null;
		if (pertenenciaDTO != null){
			json = new JSONObject();
			json.put("idPertenencia", pertenenciaDTO.getPertenenciaId());
			json.put("tipoPertenenciaId", pertenenciaDTO.getTipoPertenencia().getIdCampo());
			json.put("descripcionPertenencia", pertenenciaDTO.getDescripcion());
			json.put("cantidadPertenencia", pertenenciaDTO.getCantidad());
			if (pertenenciaDTO.getUnidadMedida() != null && pertenenciaDTO.getUnidadMedida().getValor() != null){
				json.put("unidadPertenenciaId", pertenenciaDTO.getUnidadMedida().getIdCampo());
			}else{
				json.put("unidadPertenenciaId", "");
			}
			json.put("condicionPertenenciaId", pertenenciaDTO.getCondicionFisica().getIdCampo());
		}
		return json;
	}
	
	/**
	 * @param forma
	 * @param inventarioPertenenciaDTO
	 * @param detencionDTO
	 * @return
	 */
	private PertenenciaDTO obtenerPertenenciaParaGuardar (InventarioPertenenciasForm forma, 
			InventarioPertenenciaDTO inventarioPertenenciaDTO, DetencionDTO detencionDTO){
		PertenenciaDTO pert = new PertenenciaDTO();
		pert.setPertenenciaId(forma.getPertenenciaId());
		pert.setCantidad(forma.getCantidadPert());
		ValorDTO condicion = new ValorDTO();
		condicion.setIdCampo(forma.getCondicionPertId());
		pert.setCondicionFisica(condicion);
		pert.setDescripcion(forma.getDescPert());
		pert.setDetencion(detencionDTO);
		pert.setEsDevuelto(Boolean.FALSE);
		pert.setInventarioPertenenciaDTO(inventarioPertenenciaDTO);
		ValorDTO tipoPertenencia = new ValorDTO();
		tipoPertenencia.setIdCampo(forma.getTipoPertId());
		pert.setTipoPertenencia(tipoPertenencia);
		if (forma.getUnidadPertId() > 0L){
			ValorDTO unidad = new ValorDTO();
			unidad.setIdCampo(forma.getUnidadPertId());
			pert.setUnidadMedida(unidad);
		}
		
		return pert;
	}

}
