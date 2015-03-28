package mx.gob.segob.nsjp.web.solicitud.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.avisodetencion.AvisoDetencionDelegate;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.delegate.notificacion.NotificacionDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDesignacionDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioExtranjeroDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.solicitud.form.solicitanteForm;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class SolicitudDefensorAction extends GenericAction {

	@Autowired
	private SolicitudDelegate solicitudDelegate;
	@Autowired
	private DocumentoDelegate documentoDelegate;
	@Autowired
	private FuncionarioDelegate funcionarioDelegate;
	@Autowired
	private AvisoDetencionDelegate avisoDetencionDelegate;
	@Autowired
	private InvolucradoDelegate involucradoDelegate;
	@Autowired
	private NotificacionDelegate notificacionDelegate;

	private static final Logger log = Logger
			.getLogger(SolicitudDefensorAction.class);

	/**
	 * Metodo utilizado para la consulta de avisos de detencion por Estatus No
	 * atendida.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 *             en caso de obtener una exception
	 */

	public ActionForward consultarAvisosDeDetencion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("Solicitudes no Atendidas");

			List<AvisoDetencionDTO> avisoDetencionDTOs = avisoDetencionDelegate
					.obtenerAvisosDetencionPorEstatus(EstatusNotificacion.NO_ATENDIDA);
			log.info("consultar avisos de detencion" + avisoDetencionDTOs);

			String xml = null;

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");

			int lTotalRegistros = avisoDetencionDTOs.size();

			writer.print("<records>" + lTotalRegistros + "</records>");

			for (AvisoDetencionDTO avisoDetencionDTO : avisoDetencionDTOs) {

				writer.print("<row id='" + avisoDetencionDTO.getDocumentoId()
						+ "'>");
				writer.print("<cell>" + avisoDetencionDTO.getFolioDocumento()
						+ "</cell>");

				if (avisoDetencionDTO.getExpedienteDTO() != null
						&& avisoDetencionDTO.getExpedienteDTO().getCasoDTO() != null
						&& avisoDetencionDTO.getExpedienteDTO().getCasoDTO()
								.getNumeroGeneralCaso() != null) {

					writer.print("<cell>"
							+ avisoDetencionDTO.getExpedienteDTO().getCasoDTO()
									.getNumeroGeneralCaso() + "</cell>");
				} else {
					writer.print("<cell>" + "-" + "</cell>");
				}
				writer.print("<cell>" + avisoDetencionDTO.getDetenido()
						+ "</cell>");
				writer.print("<cell>" + "-" + "</cell>");
				writer.print("<cell>" + "-" + "</cell>");
				writer.print("<cell>"
						+ DateUtils.formatear(avisoDetencionDTO
								.getFechaDetencion())
						+ "-"
						+ DateUtils.formatearHora(avisoDetencionDTO
								.getFechaDetencion()) + "</cell>");
				writer.print("<cell>"
						+ DateUtils.formatear(avisoDetencionDTO
								.getFechaCreacion())
						+ "-"
						+ DateUtils.formatearHora(avisoDetencionDTO
								.getFechaCreacion()) + "</cell>");
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Metodo utilizado para la consulta de solicitudes de defensor.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 *             en caso de obtener una exception
	 */

	public ActionForward consultarSolicitudesDeDefensor(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("Consultar Solicitudes De Defensor");

			List<SolicitudDTO> solicitudDTOs = (List<SolicitudDTO>) solicitudDelegate
					.consultarSolicitudXEstatus(EstatusSolicitud.ABIERTA,
							super.getUsuarioFirmado(request),
							TiposSolicitudes.DEFENSOR);
			if (log.isDebugEnabled()) {
				log.debug("lista de solicitudes  de defensor" + solicitudDTOs);
			}
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");

			int lTotalRegistros = solicitudDTOs.size();

			writer.print("<records>" + lTotalRegistros + "</records>");

			for (SolicitudDTO solicitudDTO : solicitudDTOs) {

				writer.print("<row id='" + solicitudDTO.getDocumentoId() + "-"
						+ solicitudDTO.getExpedienteDTO() != null ? solicitudDTO
						.getExpedienteDTO().getNumeroExpediente() : "" + "'>");
				writer.print("<cell>" + solicitudDTO.getAreaOrigen()
						+ "</cell>");
				writer.print("<cell>" + solicitudDTO.getNumeroCasoAsociado()
						+ "</cell>");
				writer.print("<cell>"
						+ solicitudDTO.getExpedienteDTO().getNumeroExpediente()
						+ "</cell>");
				writer.print("<cell>"
						+ solicitudDTO.getExpedienteDTO().getEtapa()
						+ "</cell>");

				for (InvolucradoDTO involucradoDTOs : solicitudDTO
						.getExpedienteDTO().getInvolucradoByCalidad(
								Calidades.PROBABLE_RESPONSABLE_PERSONA)) {

					writer.print("<cell>" + involucradoDTOs.getNombreCompleto()
							+ "</cell>");

					for (DetencionDTO detencionDTO : involucradoDTOs
							.getDetenciones()) {

						writer.print("<cell>"
								+ detencionDTO.getStrFechaInicioDetencion()
								+ "-" + detencionDTO.getHoraInicioDetencion()
								+ "</cell>");

					}

					writer.print("<cell>");

					for (DelitoDTO delitoDTO : involucradoDTOs
							.getDelitosCometidos()) {

						writer.print(delitoDTO.getCatDelitoDTO().getNombre()
								+ " ");

					}
					writer.print("</cell>");

				}
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Metodo utilizado para la consulta de defensor por id.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 *             en caso de obtener una exception
	 */

	public ActionForward consultarDefensorAsignado(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("Consultar Defensor asignado");

			String idExpediente = request.getParameter("idExpediente");
			log.info("llega idExpediente" + idExpediente);

			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			expedienteDTO.setExpedienteId(NumberUtils.toLong(idExpediente));

			FuncionarioDTO funcionario = funcionarioDelegate
					.obtenerDefensorAsignadoAExpediente(expedienteDTO);
			log.info("llega funcionario" + funcionario);

			if (funcionario != null) {
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
				writer.print("<rows>");
				writer.print("<records>1</records>");
				writer.print("<row id='" + funcionario.getClaveFuncionario()+ "'>");
				writer.print("<cell>"+ funcionario.getNombreFuncionario()+ "</cell>");
				writer.print("<cell>"+ funcionario.getApellidoPaternoFuncionario()+ "</cell>");
				writer.print("<cell>"+ funcionario.getApellidoMaternoFuncionario()+ "</cell>");
				writer.print("</row>");
				writer.print("</rows>");
				writer.flush();
				writer.close();

			} else {

				String xml = converter.toXML("El expediente aún no tiene defensor designado, favor de verificar");
				escribir(response, xml, null);

			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Metodo utilizado para el cambio de un defensor asignado.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 *             en caso de obtener una exception
	 */
	public ActionForward cambiarDefensorAsignado(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("cambiar Defensor Asignado");

			Long idExpediente = NumberUtils.toLong(request.getParameter("idExpediente"), -1L);
			log.info("llega idExpediente" + idExpediente);

			if(idExpediente > 0){
				ExpedienteDTO input = new ExpedienteDTO();
				input.setNumeroExpedienteId(idExpediente);
				input.setUsuario(getUsuarioFirmado(request));
				AvisoDesignacionDTO aviso = notificacionDelegate.registrarAvisoDesignacionPorReasignacionDefensor(input);
				String xml = converter.toXML(aviso);
				response.setContentType("text/xml");
				PrintWriter pw = response.getWriter();
				pw.print(xml);
				pw.flush();
				pw.close();
			}else{
				String xml = converter.toXML("Error");
				response.setContentType("text/xml");
				PrintWriter pw = response.getWriter();
				pw.print(xml);
				pw.flush();
				pw.close();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Metodo utilizado para la consulta de las solicitudes asignadas a un
	 * defensor por id.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 *             en caso de obtener una exception
	 */

	public ActionForward consultarSolicitudesDefensorAsignadas(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("Consultar las solicitudes de defensor asignadas");

			FuncionarioDTO funcionario = super.getUsuarioFirmado(request)
					.getFuncionario();
			log.info("Funcionario" + funcionario);

			List<AvisoDesignacionDTO> designaciones = notificacionDelegate
					.consultarAvisosDesignacion(
							EstatusNotificacion.NO_ATENDIDA, funcionario);

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


			Long idNumeroExpediente = null;
            for (AvisoDesignacionDTO designacion : designaciones) {
                Long area;
                List<DelitoDTO> delitos =  null;
                String folioAvisoD = " - ";
                String es_Detenido = " - ";
                // String nombrDelito = "";
                String numeroGCaso = " - ";
                String numeroExped = " - ";
                String tineAudienc = "NO";
                String etapaExpedt = " - ";
                String nombreImput = "-";
                String sfuncionari = " Sin Defensor ";
                folioAvisoD = designacion.getFolioNotificacion();
                if (designacion.getSolicitudDefensor() != null) {
                    delitos = designacion.getSolicitudDefensor().getDelitos();
                    if (designacion.getSolicitudDefensor().isEsDetenido()) {
                        es_Detenido = "SI";
                    } else {
                        es_Detenido = "NO";
                    }
                    if (designacion.getSolicitudDefensor().getAudiencia() != null) {
                        tineAudienc = "SI";
                    }
                    if (designacion.getSolicitudDefensor().getDetenido() != null) {
                        nombreImput = designacion.getSolicitudDefensor().getDetenido();
                    }
                    numeroGCaso = designacion.getSolicitudDefensor()
                            .getNumeroCasoAsociado();
                } else {
                    if (designacion.getAvisoDetencion() != null) {
                        delitos = designacion.getAvisoDetencion().getDelitos();
                        es_Detenido = "SI";
                        if (designacion.getAvisoDetencion().getDetenido() != null) {
                            nombreImput = designacion.getAvisoDetencion().getDetenido();
                        }
                        numeroGCaso = designacion.getAvisoDetencion()
                                .getNumeroCasoAsociado();
                    }
                }
                if (nombreImput == null || nombreImput.equals("-")) {
                    List<InvolucradoDTO> imputados = null;
                	log.info("designacion.getSolicitudDefensor() - "+designacion.getSolicitudDefensor());
/*                	ATE - 08/05/2012, Se comentan las siguientes lineas debido a que en el caso de que la solicitud de defensor venga nula se lanza
 * 					una excepcion.
 * 					log.info("designacion.getSolicitudDefensor().getTipoAsesoria() - "+designacion.getSolicitudDefensor().getTipoAsesoria());
                    if (designacion.getSolicitudDefensor() == null && designacion.getSolicitudDefensor().getTipoAsesoria() == null) {*/
                	if (designacion.getSolicitudDefensor() == null) {
                        imputados = involucradoDelegate
                                .consultarInvolucradoExpediente(
                                        designacion.getExpediente(),
                                        Calidades.PROBABLE_RESPONSABLE_PERSONA,
                                        getUsuarioFirmado(request));
                    	log.info("imputados - "+imputados.size());
                    } else {
                        imputados = involucradoDelegate
                                .consultarInvolucradoExpediente(
                                        designacion.getExpediente(),
                                        Calidades.DEFENDIDO,
                                        getUsuarioFirmado(request));
                    	log.info("imputados 2- "+imputados.size());
                    }
                    if (imputados != null && !imputados.isEmpty()) {
                        nombreImput = imputados.get(0).getNombreCompleto();
                    }
                }
                if (numeroGCaso == null) {
                    numeroGCaso = " - ";
                }

                if (designacion.getFuncionario() != null) {
                    sfuncionari = designacion.getFuncionario()
                            .getNombreCompleto();
                }

                if (designacion.getExpediente() != null) {
                    if (designacion.getExpediente().getEtapa() != null) {
                        etapaExpedt = designacion.getExpediente().getEtapa()
                                .getValor();
                        idNumeroExpediente = designacion.getExpediente()
                                .getNumeroExpedienteId();

                    } else {
                        etapaExpedt = " - ";
                    }
                    numeroExped = designacion.getExpediente()
                            .getNumeroExpediente();
                }

                String fechaCreacion = DateUtils.formatear(designacion
                        .getFechaCreacion());
                String horaCreacion = DateUtils.formatearHora(designacion
                        .getFechaCreacion());

                writer.print("<row id='" + designacion.getDocumentoId() + "'>");
                writer.print("<cell>" + folioAvisoD + "</cell>");
                writer.print("<cell>" + numeroGCaso + "</cell>");
                writer.print("<cell>" + numeroExped + "</cell>");
                writer.print("<cell>" + nombreImput + "</cell>");
                writer.print("<cell>" + etapaExpedt + "</cell>");
                writer.print("<cell>" + tineAudienc + "</cell>");
                writer.print("<cell>" + es_Detenido + "</cell>");
                writer.print("<cell>" + fechaCreacion + " - " + horaCreacion
                        + "</cell>");
                writer.print("<cell>" + idNumeroExpediente + "</cell>");
                writer.print("</row>");
            }

			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Metodo utilizado para la consulta de las solicitudes asignadas a un
	 * defensor por id.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 *             en caso de obtener una exception
	 */

	public ActionForward consultarSolicitudesPorAtender(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("Consultar las solicitudes de carpeta de investigacion por atender");
			String tipo = request.getParameter("tipoSolicitud");
			Long tipoSolicitud =  NumberUtils.toLong(tipo, TiposSolicitudes.CARPETA_DE_INVESTIGACION.getValorId());
			Long claveFuncionario = getUsuarioFirmado(request).getFuncionario().getClaveFuncionario();
			List<SolicitudDTO> solicitudes = solicitudDelegate
					.consultarSolicitudesPorTipoYNoEstatus(TiposSolicitudes.getByValor(tipoSolicitud),
							EstatusSolicitud.CERRADA, claveFuncionario);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");
			writer.print("<records>" + solicitudes.size() + "</records>");

			String caso = "", expediente = "", folio = "", estatus = "";
			String fechaCreacion = "", fechaLimite = "", institucion = "";
			String destinatario = "";

			for (SolicitudDTO solicitud : solicitudes) {

				caso = solicitud.getNumeroCasoAsociado();
				if(solicitud.getExpedienteDTO() !=null){
					expediente = solicitud.getExpedienteDTO().getNumeroExpediente();
				}
				folio = solicitud.getFolioSolicitud();
				if(solicitud.getEstatus() != null){
					estatus = solicitud.getEstatus().getValor();
				}
				Date fecha = solicitud.getFechaCreacion();
				fechaCreacion = DateUtils.formatear(fecha) + " - "+ DateUtils.formatearHora(fecha);
				fecha = solicitud.getFechaLimite();
				fechaLimite = DateUtils.formatear(fecha) + " - "+ DateUtils.formatearHora(fecha);
				if(solicitud.getInstitucion() != null){
					institucion = solicitud.getInstitucion().getNombreInst();
				}
				destinatario = "Coordinacion MP";

				writer.print("<row id='" + solicitud.getDocumentoId() + "'>");
				writer.print("<cell>" + caso + "</cell>");
				writer.print("<cell>" + expediente + "</cell>");
				writer.print("<cell>" + folio + "</cell>");
				writer.print("<cell>" + estatus + "</cell>");
				writer.print("<cell>" + fechaCreacion + "</cell>");
				writer.print("<cell>" + fechaLimite + "</cell>");
				writer.print("<cell>" + institucion + "</cell>");
				writer.print("<cell>" + destinatario + "</cell>");
				writer.print("</row>");
			}

			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Metodo utilizado para obtener el detalle de la solicitud del defensor.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 * 
	 */

	public ActionForward obtenerDetalleDefensoria(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("Obtiene el detalle de la solicitud del defensor");

			int tipoDocumento = NumberUtils.toInt(request.getParameter("tipoDocumento"), 0);
			Long idDocumento = NumberUtils.toLong(request.getParameter("idDocumento"), 0);
			if (log.isDebugEnabled()) {
			log.debug("documento id  ::: " + idDocumento);
			log.debug("tipoDocumento ::: " + tipoDocumento);
			
			}

			String xml = null;
			PrintWriter pw = null;

			switch(tipoDocumento){
				case 3: //AVISO DE DESIGNACION
					AvisoDesignacionDTO designacion = notificacionDelegate.consultarAvisoDesignacion(idDocumento);
					converter.alias("designacion", AvisoDesignacionDTO.class);
					converter.alias("nombreDemograficoDTO", NombreDemograficoDTO.class);
					converter.alias("involucradoDTO", InvolucradoDTO.class);
					super.setExpedienteTrabajo(request, designacion.getExpediente());
					xml = converter.toXML(designacion);
					break;
				case 2: // SOLICITUD DE DEFENSOR DE ATENCION TEMPRANA
				case 4: // SOLICITUD DE DEFENSOR DE PODER JUDICIAL
				case 5: // ASESORIA LEGAL
					SolicitudDefensorDTO solicitud = new SolicitudDefensorDTO();
					solicitud.setDocumentoId(idDocumento);
					solicitud= solicitudDelegate.obtenerSolicitudDefensor(solicitud);
					converter.alias("solicitud", SolicitudDefensorDTO.class);
					converter.alias("involucradoDTO", InvolucradoDTO.class);
					converter.alias("nombreDemograficoDTO", NombreDemograficoDTO.class);
					if (solicitud.getExpedienteDTO() != null){
						super.setExpedienteTrabajo(request, solicitud.getExpedienteDTO());						
					}
					xml = converter.toXML(solicitud);
					break;
				case 1: // AVISO DE DETENCION
					AvisoDetencionDTO avisoDetencionDTO = new AvisoDetencionDTO();
					avisoDetencionDTO.setDocumentoId(idDocumento);
					avisoDetencionDTO = avisoDetencionDelegate.obtenerAvisoDetencion(avisoDetencionDTO);
					converter.alias("avisoDetencion", AvisoDetencionDTO.class);
					converter.alias("involucradoDTO", InvolucradoDTO.class);
					super.setExpedienteTrabajo(request, avisoDetencionDTO.getExpedienteDTO());
					xml = converter.toXML(avisoDetencionDTO);
					break;
			}			
			log.info("xml ::: "+xml);
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public ActionForward guardaSolicitanteAsesoria(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			log.info("/**** GUARDAR SOLICITANTE DE ASESORIA ****/");
			
			solicitanteForm formaSolicitante = (solicitanteForm) form;
			
			String motivoAsesoria = request.getParameter("motivoAsesoria");
			log.info("motivo de asesoria:"+motivoAsesoria);
			String idIndividuo=request.getParameter("idIndividuo");
			log.info("ElemEnto id:"+idIndividuo);
			String idExpediente = request.getParameter("idExpediente");
			log.info("idExpediente====" + idExpediente);			
			Long idTipoAsesoria = NumberUtils.toLong(request.getParameter("idTipoAsesoria"), 0);
			log.info("idTipoAsesoria====" + idTipoAsesoria);
			String idNumeroExpediente = request.getParameter("idNumeroExpediente");
			log.info("idNumeroExpediente====" + idNumeroExpediente);
									
			//domicilio
			String pais = request.getParameter("pais");
			log.info("PAIS="+pais);
			String codigoPostal = request.getParameter("codigoPostal");
			log.info("CP="+codigoPostal);
			String entidadFederativa = request.getParameter("entidadFederativa");
			log.info("ENTIDAD FEDERATIVA="+entidadFederativa);
			String ciudad = request.getParameter("ciudad");
			log.info("CIUDAD="+ciudad);
			String delegacionMunicipio = request.getParameter("delegacionMunicipio");
			log.info("DELEGACION-MUNICIPIO="+delegacionMunicipio);
			String asentamientoColonia = request.getParameter("asentamientoColonia");
			log.info("ASENTAMIENTO COLONIA="+asentamientoColonia);
			String tipoAsentamiento = request.getParameter("tipoAsentamiento");
			log.info("TIPO ASENTAMIENTO="+tipoAsentamiento);
			String tipoCalle = request.getParameter("tipoCalle");
			log.info("TIPO CALLE="+tipoCalle);
			String calle = request.getParameter("calle");
			log.info("CALLE="+calle);
			String numExterior = request.getParameter("numExterior");
			log.info("NUMERO EXTERIOR="+numExterior);
			String numInterior = request.getParameter("numInterior");
			log.info("NUMERO INTERIOR="+numInterior);
			String referencias = request.getParameter("referencias");
			log.info("REFERENCIAS="+referencias);
			String entreCalle = request.getParameter("entreCalle");
			log.info("ENTRE CALLE="+entreCalle);
			String ycalle = request.getParameter("ycalle");
			log.info("Y CALLE="+ycalle);
			String aliasDomicilio = request.getParameter("aliasDomicilio");
			log.info("ALIAS DOMICILIO="+aliasDomicilio);
			String edificio = request.getParameter("edificio");
			log.info("EDIFICIO="+edificio);
			String longitud = request.getParameter("longitud");
			log.info("LONGITUD="+longitud);
			String latitud = request.getParameter("latitud");
			log.info("LATITUD="+latitud);						
					
			//medios de contacto
			String medioContactoTelefono = request.getParameter("medioContactoTelefono");
			log.info("horaIngreso" + medioContactoTelefono);
									
			String medioContactoCorreo = request.getParameter("medioContactoCorreo");
			log.info("horaIngreso" + medioContactoCorreo);
									
			InvolucradoDTO involucradoAsesoiaLegal = new InvolucradoDTO();
						
			//seteamos id del involucrado
			//TODO GBP Quitar mensajes de log
			log.info("idIndividuo-"+idIndividuo+"-");
			if(idIndividuo != null && !idIndividuo.equals("null")){
				involucradoAsesoiaLegal.setElementoId(NumberUtils.toLong(idIndividuo));
				log.info("involucradoAsesoiaLegal.getElementoId()-" + involucradoAsesoiaLegal.getElementoId());
			}
			log.info("involucradoAsesoiaLegal.getElementoId()-" + involucradoAsesoiaLegal.getElementoId());
			
			//seteamos el motivo de la asesoria
			involucradoAsesoiaLegal.setMotivoComparecencia(motivoAsesoria);
			
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			expedienteDTO.setExpedienteId(NumberUtils.toLong(idExpediente));
			expedienteDTO.setNumeroExpedienteId(NumberUtils.toLong(idNumeroExpediente));
			AreaDTO areaDTO = new AreaDTO(Areas.DEFENSORIA);
			expedienteDTO.setArea(areaDTO);
			involucradoAsesoiaLegal.setExpedienteDTO(expedienteDTO);
						
			CalidadDTO calidadDTO = new CalidadDTO();
			calidadDTO.setCalidades(Calidades.SOLICITANTE);	
						
			involucradoAsesoiaLegal.setCalidadDTO(calidadDTO);
			involucradoAsesoiaLegal.setTipoPersona(new Long(1));

			DelitoDTO pDelitoDTO = new DelitoDTO();			
			
			expedienteDTO.setDelitoPrincipal(pDelitoDTO);
		
			NombreDemograficoDTO nombresDemograficoDTO = new NombreDemograficoDTO();

			ValorDTO valorIdiomaDTO = new ValorDTO();
			ValorDTO valorEscolaridadDTO = new ValorDTO();
			ValorDTO valorEstadoCivilDTO = new ValorDTO();
			ValorDTO valorOcupacionDTO = new ValorDTO();
			ValorDTO valorNacionalidadDTO = new ValorDTO();

			nombresDemograficoDTO.setNombre(formaSolicitante.getNombre());
			nombresDemograficoDTO.setApellidoPaterno(formaSolicitante.getApellidoPaterno());
			nombresDemograficoDTO.setApellidoMaterno(formaSolicitante.getApellidoMaterno());
			nombresDemograficoDTO.setCurp(formaSolicitante.getCurp());
			nombresDemograficoDTO.setRfc(formaSolicitante.getRfc());
			
			if (formaSolicitante.getFechaNacimiento() != null && !formaSolicitante.getFechaNacimiento().equals("")) {
				nombresDemograficoDTO.setFechaNacimiento(DateUtils.obtener(formaSolicitante.getFechaNacimiento()));
			}
			nombresDemograficoDTO.setEdadAproximada(formaSolicitante.getEdadAproximada());
			
			if(formaSolicitante.getPaisNacimiento()!=null && !formaSolicitante.getPaisNacimiento().equals("-1")){
				nombresDemograficoDTO.setPaisValorDTO(new ValorDTO(NumberUtils.toLong(formaSolicitante.getPaisNacimiento())));
				log.info("ID_PAIS_NAC::"+formaSolicitante.getPaisNacimiento());
				if(formaSolicitante.getEntFederativaNacimiento()!=null && !formaSolicitante.getEntFederativaNacimiento().equals("-1")){
					nombresDemograficoDTO.setEntidadFederativaDTO(new EntidadFederativaDTO(NumberUtils.toLong(formaSolicitante.getEntFederativaNacimiento())));
					log.info("ID_ENTFED_NAC::"+formaSolicitante.getEntFederativaNacimiento());
					if(formaSolicitante.getMunicipioNacimiento()!=null && !formaSolicitante.getMunicipioNacimiento().equals("-1")){
						nombresDemograficoDTO.setMunicipioDTO(new MunicipioDTO(NumberUtils.toLong(formaSolicitante.getMunicipioNacimiento())));
						log.info("ID_MUN_NAC::"+formaSolicitante.getMunicipioNacimiento());
					}
					else{
						nombresDemograficoDTO.setMunicipioDTO(null);
					}
				}
				else{
					nombresDemograficoDTO.setEntidadFederativaDTO(null);
				}
			}
			else{
				nombresDemograficoDTO.setPaisValorDTO(null);
			}

			List<NombreDemograficoDTO> listaNombre = new ArrayList<NombreDemograficoDTO>();
			listaNombre.add(nombresDemograficoDTO);
			involucradoAsesoiaLegal.setNombresDemograficoDTO(listaNombre);

			//URG - 004 Se corrige incidencia ya que no se guarda el dato del interesado de la asesoria legal
			//FIXME Estandarizar el value en los combobox, ya que en el jsp datosGeneralesView, no se tiene
			//seteado el value "<option>- Selecciona -</option>". Revisar el jsp de ingrresarDomicilioView
			//ya que si tiene seteado el value "<option value="-1">-Seleccione-</option>"
			if(formaSolicitante.getIdioma() != null && !formaSolicitante.getIdioma().equals("- Selecciona -")){
				valorIdiomaDTO.setIdCampo(NumberUtils.toLong(formaSolicitante.getIdioma()));
			}
			
			if(formaSolicitante.getEscolaridad() != null && !formaSolicitante.getEscolaridad().equals( "- Selecciona -")){
				valorEscolaridadDTO.setIdCampo(NumberUtils.toLong(formaSolicitante.getEscolaridad()));
			}
			if(formaSolicitante.getEstadoCivil() != null && !formaSolicitante.getEstadoCivil().equals("- Selecciona -")){
				valorEstadoCivilDTO.setIdCampo(NumberUtils.toLong(formaSolicitante.getEstadoCivil()));
			}
			if(formaSolicitante.getOcupacion() != null && !formaSolicitante.getOcupacion().equals("- Selecciona -")){
				valorOcupacionDTO.setIdCampo(NumberUtils.toLong(formaSolicitante.getOcupacion()));
			}
			if(formaSolicitante.getNacionalidad() != null && !formaSolicitante.getNacionalidad() .equals("- Selecciona -")){
				valorNacionalidadDTO.setIdCampo(NumberUtils.toLong(formaSolicitante.getNacionalidad()));
			}

			/**
			 * INCORPORANDO A INVOLUCRADO
			 */
			involucradoAsesoiaLegal.setValorIdIdioma(valorIdiomaDTO);
			involucradoAsesoiaLegal.setValorIdEscolaridad(valorEscolaridadDTO);
			involucradoAsesoiaLegal.setValorIdEstadoCivil(valorEstadoCivilDTO);
			nombresDemograficoDTO.setSexo(formaSolicitante.getSexo());

			if(valorOcupacionDTO!=null && valorOcupacionDTO.getIdCampo()!=null && valorOcupacionDTO.getIdCampo() > 0){
				List<ValorDTO> listaOcupacion = new ArrayList<ValorDTO>();
				listaOcupacion.add(valorOcupacionDTO);
				involucradoAsesoiaLegal.setValorIdOcupacion(listaOcupacion);
			}

			if( valorNacionalidadDTO!=null && valorNacionalidadDTO.getIdCampo()!=null && valorNacionalidadDTO.getIdCampo() > 0){
				List<ValorDTO> listaNacionalidad = new ArrayList<ValorDTO>();
				listaNacionalidad.add(valorNacionalidadDTO);
				involucradoAsesoiaLegal.setValorIdNacionalidad(listaNacionalidad);
			}
			
			DomicilioDTO domicilioDTO = new DomicilioDTO();
						
		//CUANDO EL PAIS ES MEXICO
		if((NumberUtils.toLong(pais)==10 || pais.equalsIgnoreCase("mexico") || pais.equalsIgnoreCase("méxico")) && (NumberUtils.toLong(pais)!= -1L)){
							
			//parte izquierda de la pantalla ingresar domicilio				
				//entidad federativa
			if(!entidadFederativa.equalsIgnoreCase("")){
				
				if(NumberUtils.toLong(entidadFederativa)!= -1L ){
					EntidadFederativaDTO entidadDTO = new EntidadFederativaDTO();
					entidadDTO.setEntidadFederativaId(NumberUtils.toLong(entidadFederativa));
					domicilioDTO.setEntidadDTO(entidadDTO);
				}
			}
			
				//ciudad
			if(!ciudad.equalsIgnoreCase("")){
				
				if(NumberUtils.toLong(ciudad)!= -1L ){
					CiudadDTO ciudadDTO = new CiudadDTO();
					ciudadDTO.setCiudadId(NumberUtils.toLong(ciudad));
					domicilioDTO.setCiudadDTO(ciudadDTO);
				}
			}
				//delegacion-municipio
			if(!delegacionMunicipio.equalsIgnoreCase("")){
				
				if(NumberUtils.toLong(delegacionMunicipio)!= -1L ){
					MunicipioDTO municipioDTO = new MunicipioDTO();
					municipioDTO.setMunicipioId(NumberUtils.toLong(delegacionMunicipio));
					domicilioDTO.setMunicipioDTO(municipioDTO);
				}
			}
				
				//asentamiento-colonia
			if(!asentamientoColonia.equalsIgnoreCase("")){
				
				if(NumberUtils.toLong(asentamientoColonia)!= -1L ){
					AsentamientoDTO asentamientoDTO = new AsentamientoDTO();
					asentamientoDTO.setAsentamientoId(NumberUtils.toLong(asentamientoColonia));
					domicilioDTO.setAsentamientoDTO(asentamientoDTO);		
				}
			}
				
				//tipo de calle
			if(!tipoCalle.equalsIgnoreCase("")){
				
				if(NumberUtils.toLong(tipoCalle) != -1){
					
					ValorDTO valorCalleId = new ValorDTO(NumberUtils.toLong(tipoCalle));
					domicilioDTO.setValorCalleId(valorCalleId);
				}
			}					
			
			//parte derecha de la pantalla ingresar domicilio
			domicilioDTO.setCalle(calle);
			domicilioDTO.setNumeroExterior(numExterior);
			domicilioDTO.setNumeroInterior(numInterior);
			domicilioDTO.setEntreCalle1(entreCalle);
			domicilioDTO.setEntreCalle2(ycalle);
			domicilioDTO.setAlias(aliasDomicilio);
			domicilioDTO.setEdificio(edificio);
			domicilioDTO.setReferencias(referencias);
			
			if( longitud != null && !longitud.equalsIgnoreCase("")){
				domicilioDTO.setLongitud(longitud);
			}
			else{
				domicilioDTO.setLongitud(null);
			}
			if(latitud != null && !latitud.isEmpty()){
				domicilioDTO.setLatitud(latitud);
			}
			else{
				domicilioDTO.setLatitud(null);
			}
							
			//Seteamos la calidad del domicilio
			CalidadDTO calidadDomicilioDTO = new CalidadDTO();
			calidadDomicilioDTO.setCalidades(Calidades.DOMICILIO);
			domicilioDTO.setCalidadDTO(calidadDomicilioDTO);				
			domicilioDTO.setFechaCreacionElemento(new Date());

			//Seteamos el testigo con su domicilio
			involucradoAsesoiaLegal.setDomicilio(domicilioDTO);				

		}
		//CUANDO EL PAIS NO ES MEXICO
		else{
			
			DomicilioExtranjeroDTO domicilioExtranjeroDTO = new DomicilioExtranjeroDTO();
			
			//Parte izq de la pantalla ingresar domicilio
			if(!pais.equalsIgnoreCase("")){
				if(NumberUtils.toLong(pais)!= -1L){
				
						//id del pais
					domicilioExtranjeroDTO.setPais(pais);
				}
			}
			
			domicilioExtranjeroDTO.setCodigoPostal(codigoPostal);
			domicilioExtranjeroDTO.setEstado(entidadFederativa);
			domicilioExtranjeroDTO.setCiudad(ciudad);
			domicilioExtranjeroDTO.setMunicipio(delegacionMunicipio);
			domicilioExtranjeroDTO.setAsentamientoExt(asentamientoColonia);
			
			//parte derecha de la pantalla ingresar domicilio
			domicilioExtranjeroDTO.setCalle(calle);
			domicilioExtranjeroDTO.setNumeroExterior(numExterior);
			domicilioExtranjeroDTO.setNumeroInterior(numInterior);
			domicilioExtranjeroDTO.setEntreCalle1(entreCalle);
			domicilioExtranjeroDTO.setEntreCalle2(ycalle);
			domicilioExtranjeroDTO.setAlias(aliasDomicilio);
			domicilioExtranjeroDTO.setEdificio(edificio);
			domicilioExtranjeroDTO.setReferencias(referencias);
			
			if(!longitud.equalsIgnoreCase("")){
				domicilioExtranjeroDTO.setLongitud(longitud);
			}
			else{
				domicilioExtranjeroDTO.setLongitud(null);
			}
			if(!latitud.equalsIgnoreCase("")){
				domicilioExtranjeroDTO.setLatitud(latitud);
			}
			else{
				domicilioExtranjeroDTO.setLatitud(null);
			}
							
			//Seteamos la calidad del domicilio
			
			CalidadDTO calidadDomicilioExtranjeroDTO = new CalidadDTO();
			calidadDomicilioExtranjeroDTO.setCalidades(Calidades.DOMICILIO);
			domicilioExtranjeroDTO.setCalidadDTO(calidadDomicilioExtranjeroDTO);
			domicilioExtranjeroDTO.setFechaCreacionElemento(new Date());
			//domicilioExtranjeroDTO.setElementoId(domicilioId);
							
			//Seteamos el domicilio extranjero de notificaciones a la persona
			involucradoAsesoiaLegal.setDomicilio(domicilioExtranjeroDTO);
			}				
		
			List<TelefonoDTO> lstTelefonos = new ArrayList<TelefonoDTO>();
			String strTelefonos = medioContactoTelefono;
			StringTokenizer lstStrTelefonos = new StringTokenizer(strTelefonos,	"|");
			
			while (lstStrTelefonos.hasMoreElements()) {
				
				String strTelefono = (String) lstStrTelefonos.nextElement();
				String[] datosTelefono = strTelefono.split(",");
	
				TelefonoDTO telefono = new TelefonoDTO();
	
				ValorDTO valorTipoTelefono = new ValorDTO();
				valorTipoTelefono.setIdCampo(new Long(datosTelefono[0]));
				log.info("&&&&Telefono:"+datosTelefono[0]);
				telefono.setValorTipoTelefono(valorTipoTelefono);
				telefono.setCodigoPais(datosTelefono[1]);
				log.info("&&&&Telefono:"+datosTelefono[1]);
				telefono.setCodigoArea(datosTelefono[2]);
				log.info("&&&&Telefono:"+datosTelefono[2]);
				telefono.setNumeroTelefonico(datosTelefono[3]);
				log.info("&&&&Telefono:"+datosTelefono[3]);
				lstTelefonos.add(telefono);
			}
			involucradoAsesoiaLegal.setTelefonosDTO(lstTelefonos);
	
			List<CorreoElectronicoDTO> lstCorreos = new ArrayList<CorreoElectronicoDTO>();
			
			if(!medioContactoTelefono.trim().isEmpty()){
				String[] datosCorreo = medioContactoTelefono.split(",");
				for (int i = 0; i < datosCorreo.length; i++) {
					CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
					correo.setDireccionElectronica(datosCorreo[i]);
					lstCorreos.add(correo);
				}
			}
			involucradoAsesoiaLegal.setCorreosDTO(lstCorreos);
			
			Long recuperaInvolucrado;
			involucradoAsesoiaLegal = solicitudDelegate.guardarDefendidoSolicitudDefensoria(involucradoAsesoiaLegal,idTipoAsesoria,null);
			recuperaInvolucrado = involucradoAsesoiaLegal.getElementoId();
			log.info("regresa del servicio Inv" +recuperaInvolucrado);
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("involucrado", InvolucradoDTO.class);
			
			xml = converter.toXML(recuperaInvolucrado);
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}


	/**
	 * funcion para consultar los datos Documentos generados para el visor de elementos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarDocumentosDefensoria(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		
		try {
			
			log.info("Entra a consultar los documentos de defensoria");
			String idExpediente=request.getParameter("idExpedienteop");			
			log.info("$$$$ ID de Expediente: "+idExpediente);
			
			String tipo=request.getParameter("tipo");			
			log.info("$$$$ Tipo : "+tipo);

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();		
			
			if(tipo.equals("1")){				
				expedienteDTO.setArea(new AreaDTO(Areas.DEF));
				log.info("tipo uno");
			}
			if(tipo.equals("2")){				
				expedienteDTO.setArea(new AreaDTO(Areas.COORDINACION_DEFENSORIA));
				log.info("tipo dos");
			}
			if(tipo.equals("3")){				
				expedienteDTO.setArea(new AreaDTO(Areas.DEFENSORIA));
				log.info("tipo 3");
			}
			
			expedienteDTO.setNumeroExpedienteId(NumberUtils.toLong(idExpediente));
			List<DocumentoDTO> listaDocumentoDTOs=documentoDelegate.consultarDocumentosExpediente(expedienteDTO);
			request.getSession().setAttribute("totalRegistrosDocumentos", listaDocumentoDTOs.size());
			request.setAttribute("totalRegistrosDocumentos", listaDocumentoDTOs.size());
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

			for (DocumentoDTO documentoDTO : listaDocumentoDTOs) {
				writer.print("<row id='"+documentoDTO.getDocumentoId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getTipoDocumentoDTO().getValor()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (documentoDTO.getActividadDTO() != null && documentoDTO.getActividadDTO().getFechaCreacion() != null? DateUtils.formatear(documentoDTO.getActividadDTO().getFechaCreacion()):"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getNombreDocumento()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (documentoDTO.getActividadDTO() != null && documentoDTO.getActividadDTO().getNombre() != null? documentoDTO.getActividadDTO().getNombre():"-") +" </div>]]></cell>");
				log.info("valor actividad"+documentoDTO.getActividadDTO().getActividadId());
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getStrFechaCreacion()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (documentoDTO.getActividadDTO() != null && documentoDTO.getActividadDTO().getUsuario() != null && documentoDTO.getActividadDTO().getUsuario().getArea()!= null
						&& documentoDTO.getActividadDTO().getUsuario().getArea().getNombre()!= null ? documentoDTO.getActividadDTO().getUsuario().getArea().getNombre():"-") +" </div>]]></cell>");
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

	
}
