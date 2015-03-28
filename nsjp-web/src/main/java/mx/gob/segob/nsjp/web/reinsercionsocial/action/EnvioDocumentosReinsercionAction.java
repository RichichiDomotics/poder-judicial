/**
 * Nombre del Programa : EnvioDocumentosReinsercionAction.java
 * Autor                            : AntonioBV
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 27/01/2012
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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.actividad.ActividadesRS;
import mx.gob.segob.nsjp.comun.enums.centrosdetencion.TipoCentroDetencion;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.detencion.CentroDetencionDelegate;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.dto.programas.AsignacionCentroDetencionDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.reinsercionsocial.form.EnvioDocumentosReinsercionForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author AntonioBV
 * 
 */
@SuppressWarnings("unused")
public class EnvioDocumentosReinsercionAction extends GenericAction {

	/* Log de clase */
	private static final Logger LOG = Logger
			.getLogger(EnvioDocumentosReinsercionAction.class);

	@Autowired
	private AsignacionProgramaDelegate asignacionProgramaDelegate;
	@Autowired
	private CentroDetencionDelegate centroDetencionDelegate;

	/***
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward obtenerDatosAdicionales (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

			ActividadesRS actividad = null;
		try {
			LOG.info("ejecutando Action EnvioDocumentosReinsercionAction en metodo obtenerDatosAdicionales:#####");

			EnvioDocumentosReinsercionForm envDocsForm = (EnvioDocumentosReinsercionForm) form;

			String actividadId = request.getParameter("actividadId") != null ? request
					.getParameter("actividadId") : "-1";
			String tipoSolicitud = request.getParameter("tipoSoliciutd");
			String estatusSolicitud = request.getParameter("estatus");
			String areaSolicitud = request.getParameter("idArea");
			String tipoConsulta = request.getParameter("tipoConsulta");
			String idNumeroExpediente = request
					.getParameter("numeroExpedienteId");
			String cNumeroExpediente = request
					.getParameter("cNumeroExpediente");

			actividad = ActividadesRS.getByValor(Long
					.parseLong(actividadId));
			
			switch (actividad) {
				case GENERAR_OFICIO_DE_INGRESO_A_REINSERCION_SOCIAL:
					List<CentroDetencionDTO> lstCeresosDTO = centroDetencionDelegate
							.consultarCentrosDetencionPorTipo(
									TipoCentroDetencion.CERESO.getId());
					envDocsForm.setLstCentrosDetencion(lstCeresosDTO);
					break;
				case OPCION1:
					break;
				default:
					break;
			}

		} catch (Exception e) {
			LOG.info(e.getCause(), e);
		}
		return mapping.findForward(actividad.getcForward());
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward asignarCentroDetencionASentencia (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			LOG.info("ejecutando Action EnvioDocumentosReinsercionAction en metodo asignarCentroDetencionASentencia:#####");

			String sentenciaId = request.getParameter("sentenciaId") != null ? request
					.getParameter("sentenciaId") : "-1";
			String centroDetencionId = request.getParameter("centroDetencionId") != null ? request
							.getParameter("centroDetencionId") : "-1";
			String fechaDeIngreso = request.getParameter("fechaDeIngreso") != null ? request
									.getParameter("fechaDeIngreso") : "-1";
							

			if(sentenciaId != null && !sentenciaId.equals("-1")){
				if(centroDetencionId != null && !centroDetencionId.equals("-1")){	
					
					SentenciaDTO sentenciaDTO = new SentenciaDTO();
					sentenciaDTO.setSentenciaId(Long.parseLong(sentenciaId));
					CentroDetencionDTO centroDetencionDTO = new CentroDetencionDTO();
					centroDetencionDTO.setCentroDetencionId(Long.parseLong(centroDetencionId));
					
					AsignacionCentroDetencionDTO asignacionCentroDetencionDTO = new AsignacionCentroDetencionDTO();
					asignacionCentroDetencionDTO.setBarraigado(Boolean.TRUE);
					asignacionCentroDetencionDTO.setDfechaIngreso(DateUtils.obtener(fechaDeIngreso));
					asignacionCentroDetencionDTO.setDfechaSalida(null);
					asignacionCentroDetencionDTO.setSentenciaDTO(sentenciaDTO);
					asignacionCentroDetencionDTO.setCentroDetencionDTO(centroDetencionDTO);
					asignacionCentroDetencionDTO.setCmotivo(null);
					asignacionCentroDetencionDTO =  asignacionProgramaDelegate.asignarCentroDetencionaSentencia(asignacionCentroDetencionDTO);
					
					converter.alias("respuesta",AsignacionCentroDetencionDTO.class);
					String respuesta = converter.toXML(asignacionCentroDetencionDTO);
					escribirRespuesta(response, respuesta);												
				}
			}
		} catch (Exception e) {
			LOG.info(e.getCause(), e);
		}
		return null;
	}	
	
}
