/**
* Nombre del Programa 		: AdministrarAcumulacionPuntosAction.java
* Autor                  	: EdgarTE
* Compania               	: Ultrasist
* Proyecto             		: NSJP 							Fecha: 22 Mar 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Describir el objetivo de la clase de manera breve
* Programa Dependiente  	: N/A
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO;
import mx.gob.segob.nsjp.dto.programas.PeriodoAcumulacionPuntosDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.reinsercionsocial.form.AdministrarAcumulacionPuntosForm;

/**
 * Action de struts que lleva a cabo la ejecuci&oacute;n de los m&eacute;todos
 * del back-end para la administraci&oacute;n de la acumulaci&oacute;n de los
 * puntos derivados por actos de buena conducta.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class AdministrarAcumulacionPuntosAction extends GenericAction {
	
	private static final Logger LOG = Logger.getLogger(AdministrarAcumulacionPuntosAction.class);
	
	private static final String ABC_DISPONIBLES = "abcDisponibles";
	private static final String FORMATO_BASICO_FECHA = "dd/MM/yyyy";
	private static final String ATTR_SENTENCIA = "sentenciaConsultada";
	
	private static final String PARAM_ID_SENTENCIA = "sentenciaId";
	private static final String PARAM_ACUMULACION_EXITO = "acumulacionExitosa";
	
	private static final String FWD_ADMIN_ACUMULACION = "administrar.acumulacionPuntos.page";
	
	private static final int POS_SIN_ESTILOS_PERIODOS = 4;
	private static final int POS_SIN_ESTILOS_ACTOS = 3;
	
	@Autowired
	public AsignacionProgramaDelegate asignacionProgramaDelegate;
	
	public ActionForward administrarAcumulacionPuntos (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws IOException {
		if (form instanceof AdministrarAcumulacionPuntosForm){
			AdministrarAcumulacionPuntosForm forma = (AdministrarAcumulacionPuntosForm) form;
			try {
				String paramSentencia = request.getParameter(PARAM_ID_SENTENCIA);
				if (paramSentencia != null && !paramSentencia.isEmpty() ){
					forma.setSentenciaId(Long.parseLong(paramSentencia));
					SentenciaDTO sentencia = new SentenciaDTO();
					sentencia.setSentenciaId(forma.getSentenciaId());
					sentencia = asignacionProgramaDelegate.consultarSentenciaPorId(sentencia);
					request.getSession().setAttribute(ATTR_SENTENCIA, sentencia);
				}
			} catch (NSJPNegocioException e) {
				LOG.error(e.getStackTrace());
			}
		}
		return mapping.findForward(FWD_ADMIN_ACUMULACION);
	}
	
	public ActionForward llenarGridAcumulacionPuntos (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws IOException {

		String grid = request.getParameter("gridID");
		List<ActoBuenaConductaDTO> listaAbc = null;
		SentenciaDTO sentenciaDTO = obtenerSentencia(request);

		final PaginacionDTO pag = PaginacionThreadHolder.get();
		if (pag != null){
			pag.setCampoOrd(" abc.cnombre ");
			PaginacionThreadHolder.set(pag);

			if(grid.equals(ABC_DISPONIBLES)){
				try {
					listaAbc = asignacionProgramaDelegate.consultarActosBuenaConductaSinAcumular(sentenciaDTO);
				} catch (NSJPNegocioException e) {
					LOG.error(e.getStackTrace());
				}
			}else{

			}
		}

		List<List<String>> tablaValores = new ArrayList<List<String>>();
		if (listaAbc != null && !listaAbc.isEmpty()){
			for (ActoBuenaConductaDTO abc : listaAbc) {
				tablaValores.add(crearFilaParaGrid(abc));
			}				
		}

		escribirXml(response, tablaValores, POS_SIN_ESTILOS_ACTOS);

		return null;
	}
	
	public ActionForward acumularActosBuenaConducta (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){

		ActionForward fwd = null;

		if (form instanceof AdministrarAcumulacionPuntosForm){
			DateFormat sdf = new SimpleDateFormat(FORMATO_BASICO_FECHA);
			
			AdministrarAcumulacionPuntosForm forma = (AdministrarAcumulacionPuntosForm) form;
			String[] idsAbcAgregar = forma.getIdsAbcConcatenados().split(",");
			
			PeriodoAcumulacionPuntosDTO periodoAcumulacion = new PeriodoAcumulacionPuntosDTO();
			periodoAcumulacion.setcNombrePeriodo(forma.getNombrePeriodo());
			try {
				periodoAcumulacion.setDfechaInicio(sdf.parse(forma.getFechaInicio()));
				periodoAcumulacion.setDfechaTermino(sdf.parse(forma.getFechaTermino()));
			} catch (ParseException e) {
				LOG.error(e.getStackTrace());
			}
			
			periodoAcumulacion = asignacionProgramaDelegate.crearPeriodoAcumulacionPuntos(periodoAcumulacion);
			SentenciaDTO sentencia = obtenerSentencia(request);
			
			for(String idAbc : idsAbcAgregar){
				ActoBuenaConductaDTO actoModificar = obtenerAbcDeSentencia(sentencia, idAbc);
				actoModificar.setPeriodoAcumulacionPuntos(periodoAcumulacion);
				asignacionProgramaDelegate.actualizarActoBuenaConducta(actoModificar);
			}
			
			fwd = new ActionForward(mapping.findForward(FWD_ADMIN_ACUMULACION).getPath()+"?"+PARAM_ID_SENTENCIA+"=" + sentencia.getSentenciaId()
					+"&"+PARAM_ACUMULACION_EXITO+"="+Boolean.TRUE, false);
		}

		return fwd;

	}
	
	public ActionForward llenarGridPeriodosAcumulacion (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws IOException {

		List<PeriodoAcumulacionPuntosDTO> listaPeriodos = null;
		SentenciaDTO sentenciaDTO = obtenerSentencia(request);

		final PaginacionDTO pag = PaginacionThreadHolder.get();
		if (pag != null){
			pag.setCampoOrd(" pap.dfechaInicio ");
			PaginacionThreadHolder.set(pag);
			try {
				listaPeriodos = asignacionProgramaDelegate.consultarPeriodosAcumulacionPuntosPorSentencia(sentenciaDTO);
			} catch (NSJPNegocioException e) {
				LOG.error(e.getStackTrace());
			}
		}

		List<List<String>> tablaValores = new ArrayList<List<String>>();
		if (listaPeriodos != null && !listaPeriodos.isEmpty()){
			for (PeriodoAcumulacionPuntosDTO per : listaPeriodos) {
				tablaValores.add(crearFilaParaGrid(per));
			}				
		}

		escribirXml(response, tablaValores, POS_SIN_ESTILOS_PERIODOS);

		return null;
	}
	
	private SentenciaDTO obtenerSentencia(HttpServletRequest request){
		Object attr = request.getSession().getAttribute(ATTR_SENTENCIA);
		SentenciaDTO sentencia = null;
		if (attr != null && attr instanceof SentenciaDTO){
			sentencia = (SentenciaDTO) attr;
		}
		return sentencia;
	}
	
	private void escribirXml(HttpServletResponse response, List<List<String>> tablaValores, int posSinEstilos){
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
			String datos = crearDatosGrid(tablaValores, posSinEstilos);
			writer.print(datos);
			writer.print("</rows>");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOG.error(e.getStackTrace());
		}

	}
	
	private String crearDatosGrid(List<List<String>> matrizValores,int posSinEstilos){
		StringBuffer buffer = new StringBuffer();
		if (matrizValores != null && !matrizValores.isEmpty()){
			for (List<String> fila : matrizValores){
				if (fila != null && !fila.isEmpty()){
					for (int i=0; i<fila.size();i++){
						if (i==0){
							buffer.append("<row id='"+ fila.get(i)+"'>");
						}else if (i == posSinEstilos){
							buffer.append("<cell><![CDATA[");
							if (fila.get(i) != null){
								buffer.append(fila.get(i));
							}else{
								buffer.append("&nbsp;");
							}
							buffer.append("]]></cell>");
						}
						else{
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
	
	private List<String> crearFilaParaGrid(ActoBuenaConductaDTO actoBuenaConductaDTO){
		List<String> fila = new ArrayList<String>();
		fila.add(actoBuenaConductaDTO.getActoBuenaConductaId().toString());
		fila.add(actoBuenaConductaDTO.getCnombre());
		fila.add(actoBuenaConductaDTO.getCdescripcion());
		fila.add(actoBuenaConductaDTO.getIpuntosOtorgados().toString());
		return fila;
	}
	
	private List<String> crearFilaParaGrid(PeriodoAcumulacionPuntosDTO periodoAcumulacionPuntosDTO){
		List<String> fila = new ArrayList<String>();
		DateFormat sdf = new SimpleDateFormat(FORMATO_BASICO_FECHA);
		fila.add(periodoAcumulacionPuntosDTO.getPeriodoAcumulacionPuntosId().toString());
		if (periodoAcumulacionPuntosDTO.getcNombrePeriodo() != null){
			fila.add(periodoAcumulacionPuntosDTO.getcNombrePeriodo());			
		}else{
			fila.add("");
		}
		fila.add(sdf.format(periodoAcumulacionPuntosDTO.getDfechaInicio()));
		fila.add(sdf.format(periodoAcumulacionPuntosDTO.getDfechaTermino()));
		fila.add(periodoAcumulacionPuntosDTO.getTotalPuntosPeriodo().toString());
		return fila;
	}
	
	private ActoBuenaConductaDTO obtenerAbcDeSentencia (SentenciaDTO sentencia, String idAbc){
		ActoBuenaConductaDTO actoPersistido = null;
		if (sentencia.getActoBuenaConductas() != null && !sentencia.getActoBuenaConductas().isEmpty()){
			for (ActoBuenaConductaDTO acto : sentencia.getActoBuenaConductas()){
				if (acto.getActoBuenaConductaId().equals(Long.parseLong(idAbc))){
					actoPersistido = acto;
				}
			}
		}
		return actoPersistido;
	}

}
