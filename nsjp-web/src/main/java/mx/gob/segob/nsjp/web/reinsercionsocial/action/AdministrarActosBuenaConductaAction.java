/**
* Nombre del Programa : AdministrarActosBuenaConductaAction.java
* Autor                            : EdgarTE
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Mar 2012
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.reinsercionsocial.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.reinsercionsocial.form.AdministrarActosBuenaConductaForm;

/**
 * Action de struts que modela las distintas interacciones que se pueden ejecutar al momento de 
 * administrar los actos de buena conducta.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class AdministrarActosBuenaConductaAction extends GenericAction {

	private static final Logger LOG  = Logger.getLogger(AdministrarActosBuenaConductaAction.class);
	
	private static final String PARAM_ID_SENTENCIA = "sentenciaId";
	private static final String PARAM_ID = "id";
	private static final String PARAM_CAMBIO_EXITO = "cambioExitoso";
	private static final String FORMATO_BASICO_FECHA = "dd/MM/yyyy";
	private static final String ATTR_SENTENCIA = "sentenciaConsultada";
	private static final String FWD_ADMINISTRAR_ACTOS = "administrar.actosBuenaConducta.page";
	private static final String FWD_ADMINISTRAR_ACTOS_BUENA_CONDUCTA = "administrar.actosBuenaConducta.do";
	private static final String SIN_ACUMULADOS = "No";
	private static final String CON_ACUMULADOS = "S&iacute;";
	
	
	@Autowired
	public AsignacionProgramaDelegate asignacionProgramaDelegate;

	public ActionForward administrarActosBuenaConducta(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		if (form instanceof AdministrarActosBuenaConductaForm){
			AdministrarActosBuenaConductaForm forma = (AdministrarActosBuenaConductaForm) form;
			try {
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
			} catch (NSJPNegocioException e) {
				e.printStackTrace();
			}
		}
		return mapping.findForward(FWD_ADMINISTRAR_ACTOS);
	}
	
	public ActionForward llenarGridActosBuenaConducta(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			List<ActoBuenaConductaDTO> listaActos = null;
			
			SentenciaDTO sentencia = obtenerSentencia(request);
			final PaginacionDTO pag = PaginacionThreadHolder.get();
			if (pag != null){
				pag.setCampoOrd(" abc.cnombre ");
				PaginacionThreadHolder.set(pag);
			}
			
			listaActos = asignacionProgramaDelegate.consultarActosBuenaConductaPorSentencia(sentencia);

			response.setContentType("text/xml; charset=ISO-8859-1");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");
			final PaginacionDTO pag2 = PaginacionThreadHolder.get();
			if (pag2 != null){
				//Caso particular para considerar el total de registros
				if (pag2 != null && pag2.getTotalRegistros() != null) {
					writer.print("<total>" + pag2.getTotalPaginas() + "</total>");
					writer.print("<records>" + pag2.getTotalRegistros() + "</records>");
				} else {
					writer.print("<total>0</total>");
					writer.print("<records>0</records>");
				}
			}
			List<List<String>> tablaValores = new ArrayList<List<String>>();
			for (ActoBuenaConductaDTO abc : listaActos) {
				tablaValores.add(crearFilaParaGrid(abc));
			}
			String datos = crearDatosGrid(tablaValores);
			writer.print(datos);
			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {		
			LOG.info(e.getCause(),e);

		}
		return null;
	}
	
	public ActionForward obtenerDetallesActoBuenaConducta (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
		
			String id = request.getParameter(PARAM_ID);
			if(id!=null){
				ActoBuenaConductaDTO abc = new ActoBuenaConductaDTO();
				abc.setActoBuenaConductaId(Long.parseLong(id));
				abc = asignacionProgramaDelegate.consultarActoBuenaConductaPorId(abc);	
				
				response.setContentType("text/javascript; charset=ISO-8859-1");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print(dto2Json(abc));
				writer.flush();
				writer.close();
			}
		
		} catch (Exception e) {		
			LOG.info(e.getCause(),e);
		}
		return null;
	}
	
	public ActionForward guardarActoBuenaConducta(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
	
		AdministrarActosBuenaConductaForm forma = (AdministrarActosBuenaConductaForm) form;
		
		SentenciaDTO sentencia = obtenerSentencia(request);
	
		ActoBuenaConductaDTO actoAGuardar = obtenerActoParaGuardar(forma, sentencia);
		actoAGuardar.setActoBuenaConductaId(null);
		asignacionProgramaDelegate.crearActoBuenaConducta(actoAGuardar);
		
		return new ActionForward(mapping.findForward(FWD_ADMINISTRAR_ACTOS_BUENA_CONDUCTA).getPath()+"?"
				+PARAM_ID_SENTENCIA+"="+ sentencia.getSentenciaId()+"&"+PARAM_CAMBIO_EXITO+"="+Boolean.TRUE, false);
		
	}
	
	public ActionForward actualizarActoBuenaConducta(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
	
		AdministrarActosBuenaConductaForm forma = (AdministrarActosBuenaConductaForm) form;
		
		SentenciaDTO sentencia = obtenerSentencia(request);
	
		ActoBuenaConductaDTO actoActualizar = obtenerActoParaGuardar(forma, sentencia);
		ActoBuenaConductaDTO actoPersistido = obtenerAbcDeSentencia(sentencia, actoActualizar.getActoBuenaConductaId());
		if (actoPersistido != null){
			actoActualizar.setPeriodoAcumulacionPuntos(actoPersistido.getPeriodoAcumulacionPuntos());
		}
		asignacionProgramaDelegate.actualizarActoBuenaConducta(actoActualizar);
		
		return new ActionForward(mapping.findForward(FWD_ADMINISTRAR_ACTOS_BUENA_CONDUCTA).getPath()+"?"
				+PARAM_ID_SENTENCIA+"="+ sentencia.getSentenciaId()+"&"+PARAM_CAMBIO_EXITO+"="+Boolean.TRUE, false);
		
	}
	
	public ActionForward eliminarActoBuenaConducta(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
	
		AdministrarActosBuenaConductaForm forma = (AdministrarActosBuenaConductaForm) form;
		
		SentenciaDTO sentencia = obtenerSentencia(request);
	
		ActoBuenaConductaDTO actoEliminar = obtenerActoParaGuardar(forma, sentencia);
		asignacionProgramaDelegate.eliminarActoBuenaConducta(actoEliminar);
		
		return new ActionForward(mapping.findForward(FWD_ADMINISTRAR_ACTOS_BUENA_CONDUCTA).getPath()+"?"
				+PARAM_ID_SENTENCIA+"="+ sentencia.getSentenciaId()+"&"+PARAM_CAMBIO_EXITO+"="+Boolean.TRUE, false);
		
	}
	
	private SentenciaDTO obtenerSentencia(HttpServletRequest request){
		Object attr = request.getSession().getAttribute(ATTR_SENTENCIA);
		SentenciaDTO sentencia = null;
		if (attr != null && attr instanceof SentenciaDTO){
			sentencia = (SentenciaDTO) attr;
		}
		return sentencia;
	}
	
	private List<String> crearFilaParaGrid(ActoBuenaConductaDTO acto){
		List<String> fila = new ArrayList<String>();
		fila.add(acto.getActoBuenaConductaId().toString());
		fila.add(acto.getCnombre());
		fila.add(acto.getCdescripcion());
		fila.add(acto.getIpuntosOtorgados().toString());
		if (acto.getPeriodoAcumulacionPuntos() == null){
			fila.add(SIN_ACUMULADOS);
		}else{
			fila.add(CON_ACUMULADOS);
		}
		return fila;
	}
	
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
	 * @param writer
	 */
	private String dto2Json(ActoBuenaConductaDTO actoBuenaConductaDTO) {
		StringWriter out = null;
		String jsonText = "";
		JSONObject jsonObject = convertirJson(actoBuenaConductaDTO);
		out = new StringWriter();
		try {
			jsonObject.writeJSONString(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		jsonText = out.toString();
		return jsonText;		
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject convertirJson (ActoBuenaConductaDTO actoBuenaConductaDTO){
		JSONObject json = null;
		if (actoBuenaConductaDTO != null){
			json = new JSONObject();
			json.put("idActoBuenaConducta", actoBuenaConductaDTO.getActoBuenaConductaId());
			json.put("nombreActoBuenaConducta", actoBuenaConductaDTO.getCnombre());
			json.put("descripcionActoBuenaConducta", actoBuenaConductaDTO.getCdescripcion());
			json.put("puntosActoBuenaConducta", actoBuenaConductaDTO.getIpuntosOtorgados());
			if (actoBuenaConductaDTO.getPeriodoAcumulacionPuntos() != null){
				json.put("acumulado", "Si");
			}else{
				json.put("acumulado", "No");
			}
		}
		return json;
	}
	
	private ActoBuenaConductaDTO obtenerActoParaGuardar (AdministrarActosBuenaConductaForm forma, SentenciaDTO sentencia){
		ActoBuenaConductaDTO acto = new ActoBuenaConductaDTO();
		acto.setActoBuenaConductaId(forma.getActoBuenaConductaId());
		acto.setCdescripcion(forma.getDescABC());
		acto.setCnombre(forma.getNombreABC());
		acto.setIpuntosOtorgados(forma.getPuntosABC());
		acto.setSentenciaDTO(sentencia);
		return acto;
	}
	
	private ActoBuenaConductaDTO obtenerAbcDeSentencia (SentenciaDTO sentencia, Long idAbc){
		ActoBuenaConductaDTO actoPersistido = null;
		if (sentencia.getActoBuenaConductas() != null && !sentencia.getActoBuenaConductas().isEmpty()){
			for (ActoBuenaConductaDTO acto : sentencia.getActoBuenaConductas()){
				if (acto.getActoBuenaConductaId().equals(idAbc)){
					actoPersistido = acto;
				}
			}
		}
		return actoPersistido;
	}
}
