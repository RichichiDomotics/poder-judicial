package mx.gob.segob.nsjp.web.pericial.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.seguridad.Roles;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.cadenadecustodia.CadenaDeCustodiaDelegate;
import mx.gob.segob.nsjp.delegate.cadenadecustodia.ConsultarCadenaDeCustodiaXFolioDelegate;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.evidencia.EvidenciaDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.delegate.rol.RolDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudPericialDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.solicitud.RegistrarActualizarServicioPericialService;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
/**
 * @author AlejandroGA
 *
 */
public class SolicitarServiciosPericialesAction extends GenericAction {
	
	/**Log de clase*/
	private static final Logger log  = Logger.getLogger(SolicitarServiciosPericialesAction.class);
	@Autowired
	private SolicitudDelegate solicitudDelegate;
	
	@Autowired
	FuncionarioDelegate funcionarioDelegate;
	
	@Autowired
	private SolicitudPericialDelegate solicitudPericialDelegate;
	@Autowired
	private EvidenciaDelegate evidenciaDelegate;
	@Autowired
	ConsultarCadenaDeCustodiaXFolioDelegate consultarCadenaDelegate;
	
	@Autowired
	RegistrarActualizarServicioPericialService pericialService;
	
	@Autowired
	CadenaDeCustodiaDelegate cadenaDeCustodiaDelegate;
	
	@Autowired
	DocumentoDelegate documentoDelegate;
	
	@Autowired
	RolDelegate rolDelegate;
	
	/**
	 * Metodo utilizado para consultar los datos del usuario loggeado
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarDatosUsuario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO ACTION CONSULTAR DATOS DEL USUARIO");
			UsuarioDTO usuario = getUsuarioFirmado(request);
			log.info("USUARIO"+usuario);
			converter.alias("usuarioDTO", UsuarioDTO.class);
			String xml = converter.toXML(usuario);
			log.info("consultarDatosUsuario() ... " + xml);
			escribir(response, xml,null);
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	/**
	 * Dependiendo del servico a solicitar consulta la información
	 * del funcionario con el puesto correspondiente:
	 * Para Asesorías: Coordinador de periciales
	 * Para Dictamen: Coordinador de defensores
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarFuncionarioANotificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws IOException {
				
		try {
			
			Long puestoId = NumberUtils.toLong(request.getParameter("puestoId"));
			/*Long tipoSolicitudServicio = NumberUtils.toLong(request.getParameter("tipoSolicitud"), 0);
			if(tipoSolicitudServicio>0){*/
				List<FuncionarioDTO> funcs  = null;
				/*if(TiposSolicitudes.ASESORIA.getValorId().equals(tipoSolicitudServicio)){
					funcs = funcionarioDelegate.consultarFuncionariosPorRol(Puestos.COORDINADOR_DE_SERVICIOS_PERICIALES.getValorId());
				}else if(TiposSolicitudes.DICTAMEN.getValorId().equals(tipoSolicitudServicio)){
					funcs = funcionarioDelegate.consultarFuncionariosPorRol(Puestos.COORDINADOR_DE_DEFENSORES.getValorId());
				}*/
				funcs = funcionarioDelegate.consultarFuncionariosPorRol(puestoId);
				
				if(funcs != null & funcs.size()>0){
					converter.alias("funcionarioDTO", FuncionarioDTO.class);
					String xml = converter.toXML(funcs.get(0));
					escribir(response, xml,null);
				}
			//}
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	/**
	 * Atiende la petición para almacenar una solicitud de servicios periciales
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward guardarSolicitudServicioPericial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
	    log.debug("Solicitando pericial por el funcionario :: " + getUsuarioFirmado(request).getFuncionario().getClaveFuncionario());
		Long tipoSolicitudServicio = NumberUtils.toLong(request.getParameter("tipoSolicitud"), 0);
		Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"), 0);
		Integer area = NumberUtils.toInt(request.getParameter("area"));
		log.info("AREA ... " + area);
		SolicitudPericialDTO solicitud = new SolicitudPericialDTO();
		boolean envio = Boolean.parseBoolean(request.getParameter("envio"));
		try{
			if(StringUtils.isNotBlank(request.getParameter("documentoId"))){
				solicitud.setDocumentoId(NumberUtils.toLong(request.getParameter("documentoId"), 0));
				if(solicitud.getDocumentoId() == 0){
					solicitud.setDocumentoId(null);
				}
			}
			solicitud.setUsuarioSolicitante(getUsuarioFirmado(request).getFuncionario());
			
			solicitud.setFechaCreacion(new Date());
			solicitud.setTipoSolicitudDTO(new ValorDTO(tipoSolicitudServicio));
			solicitud.setFechaLimite(DateUtils.obtener(request.getParameter("fechaLimite")));
			solicitud.setEspecialidad(new ValorDTO(NumberUtils.toLong(request.getParameter("especialidad"), 0)));
			solicitud.setMotivo(request.getParameter("motivo"));
			//TODO: VAP revisar cuando la solicitud se genera en el mismo servidor
			solicitud.setEstatus(new ValorDTO(EstatusSolicitud.CERRADA.getValorId()));
			solicitud.setExpedienteDTO(new ExpedienteDTO());
			solicitud.getExpedienteDTO().setNumeroExpedienteId(numeroExpedienteId);
			List<FuncionarioDTO> funcs  = null;
			//Asesoria para Defensoria
			if(TiposSolicitudes.ASESORIA.getValorId().equals(solicitud.getTipoSolicitudDTO().getIdCampo())){
				funcs = funcionarioDelegate.consultarFuncionariosPorRol(Puestos.COORDINADOR_PERICIAL_DE_DEFENSORIA.getValorId());
			}else if(TiposSolicitudes.DICTAMEN.getValorId().equals(tipoSolicitudServicio)){
				//Dictamen tanto para defensoria como para procuraduria
				funcs = 
					area == 1 ? 
						funcionarioDelegate.consultarFuncionariosPorRol(Puestos.COORDINADOR_DE_SERVICIOS_PERICIALES.getValorId())
						: funcionarioDelegate.consultarFuncionariosPorRol(Puestos.COORDINADOR_DE_DEFENSORES.getValorId());
			}
			if(funcs!=null && !funcs.isEmpty()){
				solicitud.setDestinatario(funcs.get(0));
			}
			
			solicitudPericialDelegate.registrarActulizarSolicitudPericial(solicitud);
			if(envio){
				//TODO: Caso de uso envio de solicitud pericial
			}
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.write("<documentoId>"+solicitud.getDocumentoId()+"</documentoId>");
			writer.flush();
			writer.close();
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		
		return null;
	}
	
	/**
	 * Atiende la petición para consultar el detalle de una solicitud de pericial
	 * consultándola por su identificador
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward consultaDetalleSolicitudPericial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		log.info("EJECUTANDO ACTION CONSULTAR DETALLE SOLICITUD PERICIAL");
		Long solicitudPericialId =  NumberUtils.toLong(request.getParameter("solicitudPericialId"),0);
				
		log.info("EJECUTANDO ACTION CONSULTAR DETALLE SOLICITUD PERICIAL, solicitudPericialId================"+solicitudPericialId);
		
		if(solicitudPericialId >0){
			
			try {
				SolicitudPericialDTO solicitud = solicitudPericialDelegate.
				consultarSolicitudPericialPorId(solicitudPericialId);
				
				if(solicitud != null){
					escribirRespuesta(response,converter.toXML(solicitud));
					log.info("CONSULTAR DETALLE SOLICITUD PERICIAL, escribirRespuesta================"+converter.toXML(solicitud));
				}
				
			} catch (NSJPNegocioException e) {
				log.error(e);
			}
			
		}
		
		
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar las solicitudes periciales en status de 
	 * no atendidas
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesPericialesNoAtendidas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR CONSULTAR SOLICITUDES PERICIALES NO ATENDIDAS");
			String tipoSolicitud = request.getParameter("tipoSolicitud");
			Long multiRol = NumberUtils.toLong(request.getParameter("multiRol"), 0L);
			
			int area =  NumberUtils.toInt(request.getParameter("area"));
			
			if(tipoSolicitud != null){
				
				Long tipoSol = Long.parseLong(tipoSolicitud);
				log.debug("tipoSol :: " + tipoSol);
				log.debug("area :: " + area);
				Puestos puesto = null;
				Long rolId = 0L;
				
				if(TiposSolicitudes.ASESORIA.getValorId().equals(tipoSol)){
					puesto = Puestos.COORDINADOR_PERICIAL_DE_DEFENSORIA;
					rolId = Roles.COORDINADORPER.getValorId();
				}else if(TiposSolicitudes.DICTAMEN.getValorId().equals(tipoSol)){
					puesto = area == 1 ? Puestos.COORDINADOR_DE_SERVICIOS_PERICIALES : Puestos.COORDINADOR_DE_DEFENSORES;
					rolId = area == 1 ? Roles.COORDPERFIS.getValorId() : Roles.COORDINADORDEF.getValorId(); 
				}
				
				List<SolicitudPericialDTO> listaSolicitudesNoAtendidas = null;
				
				if(multiRol.equals(0L)){
					listaSolicitudesNoAtendidas = solicitudPericialDelegate.consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(TiposSolicitudes.getByValor(tipoSol),EstatusSolicitud.ABIERTA,puesto);
				}else{
					//Agregado para req Zac
					RolDTO rol = new RolDTO();
					rol.setRolId(rolId);
					rol = rolDelegate.consultarRol(rol);
						
					if(rol != null && rol.getJerarquiaOrganizacionalDTO() != null && rol.getJerarquiaOrganizacionalDTO().getJerarquiaOrganizacionalId() != null){
						listaSolicitudesNoAtendidas = solicitudPericialDelegate
								.consultarSolicitudesPericialesPorTipoEstatusAreaDestino(
										TiposSolicitudes.getByValor(tipoSol),
										EstatusSolicitud.ABIERTA, rol
												.getJerarquiaOrganizacionalDTO()
												.getJerarquiaOrganizacionalId());
					}
					//Agregado para req Zac
				}

				log.info("EJECUTANDO ACTION CONSULTAR CONSULTAR SOLICITUDES PERICIALES NO ATENDIDAS listaSolicitudesNoAtendidas ---- "+listaSolicitudesNoAtendidas.size());
				
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
                
                for (SolicitudPericialDTO solicitudPericialDTO : listaSolicitudesNoAtendidas) {
				    if (log.isDebugEnabled()){
				        log.debug("SOLICITUD PERICIAL DTO" + solicitudPericialDTO);
				    }
					writer.print("<row id='"+ solicitudPericialDTO.getDocumentoId() + "'>");
					writer.print("<cell>"+ solicitudPericialDTO.getFolioSolicitud()+  "</cell>");
					writer.print("<cell>"+ this.getNoCaso(solicitudPericialDTO)+  "</cell>");
					writer.print("<cell>"+ ((solicitudPericialDTO.getExpedienteDTO()!=null && solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente()!=null) ? solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente():"-") +  "</cell>");
					if (solicitudPericialDTO.getUsuarioSolicitante()!=null) {
					    writer.print("<cell>"+ (solicitudPericialDTO.getUsuarioSolicitante()!=null ? solicitudPericialDTO.getUsuarioSolicitante().getNombreCompleto():"-") +  "</cell>");
					} else {
					    writer.print("<cell>"+ (solicitudPericialDTO.getNombreSolicitante()!=null ? solicitudPericialDTO.getNombreSolicitante():"-") +  "</cell>");
					}
					writer.print("<cell>"+ (solicitudPericialDTO.getEspecialidad()!=null && solicitudPericialDTO.getEspecialidad().getValor()!=null? solicitudPericialDTO.getEspecialidad().getValor():"-") +  "</cell>");
					if(solicitudPericialDTO.getFechaLimite() != null){
						String fechaLimite=DateUtils.formatear(solicitudPericialDTO.getFechaLimite());
						writer.print("<cell>"+ fechaLimite + "</cell>");
					}
					else{
						writer.print("<cell>"+ "---" + "</cell>");
					}
					if(solicitudPericialDTO.getFechaCreacion() != null){
						String fechaSolicitud=DateUtils.formatear(solicitudPericialDTO.getFechaCreacion());
						writer.print("<cell>"+ fechaSolicitud + "</cell>");
					}
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
			}
			
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar las solicitudes periciales en status de 
	 * no atendidas para coordinadores periciales defensoria
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesPericialesNoAtendidasCoordinadorPerDef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR CONSULTAR SOLICITUDES PERICIALES NO ATENDIDAS COORDINADOR PERICIAL DEFENSORIA");
			
			List<SolicitudPericialDTO> listaSolicitudesNoAtendidas = 
				solicitudPericialDelegate.consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(
					TiposSolicitudes.getByValor(TiposSolicitudes.DICTAMEN.getValorId()),EstatusSolicitud.ABIERTA,Puestos.COORDINADOR_PERICIAL_DE_DEFENSORIA);

			log.info("EJECUTANDO ACTION CONSULTAR CONSULTAR SOLICITUDES PERICIALES NO ATENDIDAS COORD PER DEF listaSolicitudesNoAtendidas ---- "+listaSolicitudesNoAtendidas.size());
			
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
            
            for (SolicitudPericialDTO solicitudPericialDTO : listaSolicitudesNoAtendidas) {
			    if (log.isDebugEnabled()){
			        log.debug("SOLICITUD PERICIAL DTO" + solicitudPericialDTO);
			    }
				writer.print("<row id='"+ solicitudPericialDTO.getDocumentoId() + "'>");
				writer.print("<cell>"+ solicitudPericialDTO.getFolioSolicitud()+  "</cell>");
				writer.print("<cell>"+ this.getNoCaso(solicitudPericialDTO)+  "</cell>");
				writer.print("<cell>"+ ((solicitudPericialDTO.getExpedienteDTO()!=null && solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente()!=null) ? solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente():"-") +  "</cell>");
				if (solicitudPericialDTO.getUsuarioSolicitante()!=null) {
				    writer.print("<cell>"+ (solicitudPericialDTO.getUsuarioSolicitante()!=null ? solicitudPericialDTO.getUsuarioSolicitante().getNombreCompleto():"-") +  "</cell>");
				} else {
				    writer.print("<cell>"+ (solicitudPericialDTO.getNombreSolicitante()!=null ? solicitudPericialDTO.getNombreSolicitante():"-") +  "</cell>");
				}
				writer.print("<cell>"+ (solicitudPericialDTO.getEspecialidad()!=null && solicitudPericialDTO.getEspecialidad().getValor()!=null? solicitudPericialDTO.getEspecialidad().getValor():"-") +  "</cell>");
				if(solicitudPericialDTO.getFechaLimite() != null){
					String fechaLimite=DateUtils.formatear(solicitudPericialDTO.getFechaLimite());
					writer.print("<cell>"+ fechaLimite + "</cell>");
				}
				else{
					writer.print("<cell>"+ "---" + "</cell>");
				}
				if(solicitudPericialDTO.getFechaCreacion() != null){
					String fechaSolicitud=DateUtils.formatear(solicitudPericialDTO.getFechaCreacion());
					writer.print("<cell>"+ fechaSolicitud + "</cell>");
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

	private String getNoCaso(SolicitudPericialDTO inputDto) {
        if (inputDto != null
                && inputDto.getExpedienteDTO() != null
                && inputDto.getExpedienteDTO().getCasoDTO() != null
                && StringUtils.isNotBlank(inputDto.getExpedienteDTO()
                        .getCasoDTO().getNumeroGeneralCaso())) {
            inputDto.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso();
        }
        return "---";
	}
	
	/**
	 * Metodo utilizado para consultar las solicitudes periciales en status de 
	 * pendientes, o en proceso
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesPericialesEnProceso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO ACTION CONSULTAR CONSULTAR SOLICITUDES PERICIALES EN PROCESO");
			String tipoSolicitud = request.getParameter("tipoSolicitud");
			int area = new Integer(request.getParameter("area"));
			Long multiRol = NumberUtils.toLong(request.getParameter("multiRol"), 0L);
			
			if(tipoSolicitud != null){
				Puestos puesto = null;
				Long rolId = 0L;
				
				Long tipoSol = Long.parseLong(tipoSolicitud);
				if(TiposSolicitudes.ASESORIA.getValorId().equals(tipoSol)){
					puesto = Puestos.COORDINADOR_PERICIAL_DE_DEFENSORIA;
					rolId = Roles.COORDINADORPER.getValorId();
				}else if(TiposSolicitudes.DICTAMEN.getValorId().equals(tipoSol)){
					puesto = area == 1 ? Puestos.COORDINADOR_DE_SERVICIOS_PERICIALES : Puestos.COORDINADOR_DE_DEFENSORES;
					rolId = area == 1 ? Roles.COORDPERFIS.getValorId() : Roles.COORDINADORDEF.getValorId(); 
				}
				
				List<SolicitudPericialDTO> listaSolicitudesEnProceso = null;
				
				if(multiRol.equals(0L)){

					listaSolicitudesEnProceso = solicitudPericialDelegate.
					consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(TiposSolicitudes.getByValor(tipoSol),EstatusSolicitud.EN_PROCESO,puesto);
				}else{
					//Agregado para req Zac
					RolDTO rol = new RolDTO();
					rol.setRolId(rolId);
					rol = rolDelegate.consultarRol(rol);
						
					if(rol != null && rol.getJerarquiaOrganizacionalDTO() != null && rol.getJerarquiaOrganizacionalDTO().getJerarquiaOrganizacionalId() != null){
						listaSolicitudesEnProceso = solicitudPericialDelegate
								.consultarSolicitudesPericialesPorTipoEstatusAreaDestino(
										TiposSolicitudes.getByValor(tipoSol),
										EstatusSolicitud.EN_PROCESO, rol
												.getJerarquiaOrganizacionalDTO()
												.getJerarquiaOrganizacionalId());
					}
					//Agregado para req Zac
				}
				
				if (log.isDebugEnabled()){
				    log.debug("SOLICITUDES EN PROCESO" + listaSolicitudesEnProceso);
				}
				
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
                
                for (SolicitudPericialDTO solicitudPericialDTO : listaSolicitudesEnProceso) {
				    if (log.isDebugEnabled()){
				        log.debug("SOLICITUD PERICIAL DTO EN PROCESO" + solicitudPericialDTO);
				    }
					writer.print("<row id='"+ solicitudPericialDTO.getDocumentoId() + "'>");
						writer.print("<cell>"+ solicitudPericialDTO.getFolioSolicitud()+  "</cell>");
						writer.print("<cell>"+ this.getNoCaso(solicitudPericialDTO) +  "</cell>");
						writer.print("<cell>"+ ((solicitudPericialDTO.getExpedienteDTO()!=null && solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente()!=null) ? solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente():"-") +  "</cell>");
		                  if (solicitudPericialDTO.getUsuarioSolicitante()!=null) {
	                            writer.print("<cell>"+ (solicitudPericialDTO.getUsuarioSolicitante()!=null ? solicitudPericialDTO.getUsuarioSolicitante().getNombreCompleto():"-") +  "</cell>");
	                        } else {
	                            writer.print("<cell>"+ (solicitudPericialDTO.getNombreSolicitante()!=null ? solicitudPericialDTO.getNombreSolicitante():"-") +  "</cell>");
	                        }
						writer.print("<cell>"+ (solicitudPericialDTO.getEspecialidad()!=null && solicitudPericialDTO.getEspecialidad().getValor()!=null? solicitudPericialDTO.getEspecialidad().getValor():"-") +  "</cell>");
						if(solicitudPericialDTO.getFechaLimite() != null){
							String fechaLimite=DateUtils.formatear(solicitudPericialDTO.getFechaLimite());
							writer.print("<cell>"+ fechaLimite + "</cell>");
						}
						else{
							writer.print("<cell>"+ "---" + "</cell>");
						}
						if(solicitudPericialDTO.getFechaModificacion() != null){
							String fechaUltimaModificacion=DateUtils.formatear(solicitudPericialDTO.getFechaModificacion());
							writer.print("<cell>"+ fechaUltimaModificacion + "</cell>");
						}
						else{
							writer.print("<cell>"+ "---" + "</cell>");
						}	
						if(solicitudPericialDTO.getFechaCreacion() != null){
							String fechaSolicitud=DateUtils.formatear(solicitudPericialDTO.getFechaCreacion());
							writer.print("<cell>"+ fechaSolicitud + "</cell>");
						}
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
			}
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar las solicitudes periciales en status de 
	 * pendientes, o en proceso para coordinador pericial defensoria
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesPericialesEnProcesoCoordinadorPerDef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO ACTION CONSULTAR CONSULTAR SOLICITUDES PERICIALES EN PROCESO COORDINADOR PER DEF");
		
			List<SolicitudPericialDTO> listaSolicitudesEnProceso = solicitudPericialDelegate.
				consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(
					TiposSolicitudes.getByValor(TiposSolicitudes.DICTAMEN.getValorId()),EstatusSolicitud.EN_PROCESO,Puestos.COORDINADOR_PERICIAL_DE_DEFENSORIA);
			if (log.isDebugEnabled()){
			    log.debug("SOLICITUDES EN PROCESO" + listaSolicitudesEnProceso);
			}
			
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
            
            for (SolicitudPericialDTO solicitudPericialDTO : listaSolicitudesEnProceso) {
			    if (log.isDebugEnabled()){
			        log.debug("SOLICITUD PERICIAL DTO EN PROCESO" + solicitudPericialDTO);
			    }
				writer.print("<row id='"+ solicitudPericialDTO.getDocumentoId() + "'>");
					writer.print("<cell>"+ solicitudPericialDTO.getFolioSolicitud()+  "</cell>");
					writer.print("<cell>"+ this.getNoCaso(solicitudPericialDTO) +  "</cell>");
					writer.print("<cell>"+ ((solicitudPericialDTO.getExpedienteDTO()!=null && solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente()!=null) ? solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente():"-") +  "</cell>");
	                  if (solicitudPericialDTO.getUsuarioSolicitante()!=null) {
                            writer.print("<cell>"+ (solicitudPericialDTO.getUsuarioSolicitante()!=null ? solicitudPericialDTO.getUsuarioSolicitante().getNombreCompleto():"-") +  "</cell>");
                        } else {
                            writer.print("<cell>"+ (solicitudPericialDTO.getNombreSolicitante()!=null ? solicitudPericialDTO.getNombreSolicitante():"-") +  "</cell>");
                        }
					writer.print("<cell>"+ (solicitudPericialDTO.getEspecialidad()!=null && solicitudPericialDTO.getEspecialidad().getValor()!=null? solicitudPericialDTO.getEspecialidad().getValor():"-") +  "</cell>");
					if(solicitudPericialDTO.getFechaLimite() != null){
						String fechaLimite=DateUtils.formatear(solicitudPericialDTO.getFechaLimite());
						writer.print("<cell>"+ fechaLimite + "</cell>");
					}
					else{
						writer.print("<cell>"+ "---" + "</cell>");
					}
					if(solicitudPericialDTO.getFechaModificacion() != null){
						String fechaUltimaModificacion=DateUtils.formatear(solicitudPericialDTO.getFechaModificacion());
						writer.print("<cell>"+ fechaUltimaModificacion + "</cell>");
					}
					else{
						writer.print("<cell>"+ "---" + "</cell>");
					}	
					if(solicitudPericialDTO.getFechaCreacion() != null){
						String fechaSolicitud=DateUtils.formatear(solicitudPericialDTO.getFechaCreacion());
						writer.print("<cell>"+ fechaSolicitud + "</cell>");
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
	 * Metodo utilizado para consultar las solicitudes periciales en status de 
	 * terminadas o cerradas
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesPericialesTerminadas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO ACTION CONSULTAR CONSULTAR SOLICITUDES PERICIALES TERMINADAS");
			String tipoSolicitud = request.getParameter("tipoSolicitud");
			int area = new Integer(request.getParameter("area"));
			Long multiRol = NumberUtils.toLong(request.getParameter("multiRol"), 0L);
			
			if(tipoSolicitud != null){
				
				Long tipoSol = Long.parseLong(tipoSolicitud);
				Puestos puesto = null;
				Long rolId = 0L;
				
				if(TiposSolicitudes.ASESORIA.getValorId().equals(tipoSol)){
					puesto = Puestos.COORDINADOR_PERICIAL_DE_DEFENSORIA;
					rolId = Roles.COORDINADORPER.getValorId();
				}else if(TiposSolicitudes.DICTAMEN.getValorId().equals(tipoSol)){
					puesto = area == 1 ? Puestos.COORDINADOR_DE_SERVICIOS_PERICIALES : Puestos.COORDINADOR_DE_DEFENSORES;
					rolId = area == 1 ? Roles.COORDPERFIS.getValorId() : Roles.COORDINADORDEF.getValorId(); 
				}
				
				List<SolicitudPericialDTO> listaSolicitudesTerminadas = null;
				
				if(multiRol.equals(0L)){
					listaSolicitudesTerminadas = solicitudPericialDelegate.
					consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(TiposSolicitudes.getByValor(tipoSol),EstatusSolicitud.CERRADA,puesto);
				}else{
					//Agregado para req Zac
					RolDTO rol = new RolDTO();
					rol.setRolId(rolId);
					rol = rolDelegate.consultarRol(rol);
						
					if(rol != null && rol.getJerarquiaOrganizacionalDTO() != null && rol.getJerarquiaOrganizacionalDTO().getJerarquiaOrganizacionalId() != null){
						listaSolicitudesTerminadas = solicitudPericialDelegate
								.consultarSolicitudesPericialesPorTipoEstatusAreaDestino(
										TiposSolicitudes.getByValor(tipoSol),
										EstatusSolicitud.CERRADA, rol
												.getJerarquiaOrganizacionalDTO()
												.getJerarquiaOrganizacionalId());
					}
					//Agregado para req Zac
				}
				
				
				if (log.isDebugEnabled()){
				    log.debug("SOLICITUDES TERMINADAS" + listaSolicitudesTerminadas);
				}
				
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
                
                for (SolicitudPericialDTO solicitudPericialDTO : listaSolicitudesTerminadas) {
				    if (log.isDebugEnabled()){
				        log.debug("SOLICITUD PERICIAL DTO TERMINADA" + solicitudPericialDTO);
				    }
					writer.print("<row id='"+ solicitudPericialDTO.getDocumentoId() + "'>");
					writer.print("<cell>"+ solicitudPericialDTO.getFolioSolicitud()+  "</cell>");
					writer.print("<cell>"+ this.getNoCaso(solicitudPericialDTO)+  "</cell>");
					writer.print("<cell>"+ ((solicitudPericialDTO.getExpedienteDTO()!=null && solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente()!=null) ? solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente():"-") +  "</cell>");
                        if (solicitudPericialDTO.getUsuarioSolicitante()!=null) {
                            writer.print("<cell>"+ (solicitudPericialDTO.getUsuarioSolicitante()!=null ? solicitudPericialDTO.getUsuarioSolicitante().getNombreCompleto():"-") +  "</cell>");
                        } else {
                            writer.print("<cell>"+ (solicitudPericialDTO.getNombreSolicitante()!=null ? solicitudPericialDTO.getNombreSolicitante():"-") +  "</cell>");
                        }
						writer.print("<cell>"+ (solicitudPericialDTO.getEspecialidad()!=null && solicitudPericialDTO.getEspecialidad().getValor()!=null? solicitudPericialDTO.getEspecialidad().getValor():"-") +  "</cell>");
						if(solicitudPericialDTO.getFechaLimite() != null){
							String fechaLimite=DateUtils.formatear(solicitudPericialDTO.getFechaLimite());
							writer.print("<cell>"+ fechaLimite + "</cell>");
						}
						else{
							writer.print("<cell>"+ "---" + "</cell>");
						}
						if(solicitudPericialDTO.getFechaCierre() != null){
							String fechaCierre=DateUtils.formatear(solicitudPericialDTO.getFechaCierre());
							writer.print("<cell>"+ fechaCierre + "</cell>");
						}
						else{
							writer.print("<cell>"+ "---" + "</cell>");
						}
						if(solicitudPericialDTO.getFechaCreacion() != null){
							String fechaSolicitud=DateUtils.formatear(solicitudPericialDTO.getFechaCreacion());
							writer.print("<cell>"+ fechaSolicitud + "</cell>");
						}
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
			}			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar las solicitudes periciales en status de 
	 * terminadas o cerradas para el coordinador pericial defensoria
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesPericialesTerminadasCoordinadorPerDef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO ACTION CONSULTAR CONSULTAR SOLICITUDES PERICIALES TERMINADAS COORDINADOR PER DEF");

			List<SolicitudPericialDTO> listaSolicitudesTerminadas = solicitudPericialDelegate.
				consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(
					TiposSolicitudes.getByValor(TiposSolicitudes.DICTAMEN.getValorId()),EstatusSolicitud.CERRADA,Puestos.COORDINADOR_PERICIAL_DE_DEFENSORIA);
			if (log.isDebugEnabled()){
			    log.debug("SOLICITUDES TERMINADAS" + listaSolicitudesTerminadas);
			}
			
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
            
            for (SolicitudPericialDTO solicitudPericialDTO : listaSolicitudesTerminadas) {
			    if (log.isDebugEnabled()){
			        log.debug("SOLICITUD PERICIAL DTO TERMINADA" + solicitudPericialDTO);
			    }
				writer.print("<row id='"+ solicitudPericialDTO.getDocumentoId() + "'>");
				writer.print("<cell>"+ solicitudPericialDTO.getFolioSolicitud()+  "</cell>");
				writer.print("<cell>"+ this.getNoCaso(solicitudPericialDTO)+  "</cell>");
				writer.print("<cell>"+ ((solicitudPericialDTO.getExpedienteDTO()!=null && solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente()!=null) ? solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente():"-") +  "</cell>");
                    if (solicitudPericialDTO.getUsuarioSolicitante()!=null) {
                        writer.print("<cell>"+ (solicitudPericialDTO.getUsuarioSolicitante()!=null ? solicitudPericialDTO.getUsuarioSolicitante().getNombreCompleto():"-") +  "</cell>");
                    } else {
                        writer.print("<cell>"+ (solicitudPericialDTO.getNombreSolicitante()!=null ? solicitudPericialDTO.getNombreSolicitante():"-") +  "</cell>");
                    }
					writer.print("<cell>"+ (solicitudPericialDTO.getEspecialidad()!=null && solicitudPericialDTO.getEspecialidad().getValor()!=null? solicitudPericialDTO.getEspecialidad().getValor():"-") +  "</cell>");
					if(solicitudPericialDTO.getFechaLimite() != null){
						String fechaLimite=DateUtils.formatear(solicitudPericialDTO.getFechaLimite());
						writer.print("<cell>"+ fechaLimite + "</cell>");
					}
					else{
						writer.print("<cell>"+ "---" + "</cell>");
					}
					if(solicitudPericialDTO.getFechaCierre() != null){
						String fechaCierre=DateUtils.formatear(solicitudPericialDTO.getFechaCierre());
						writer.print("<cell>"+ fechaCierre + "</cell>");
					}
					else{
						writer.print("<cell>"+ "---" + "</cell>");
					}
					if(solicitudPericialDTO.getFechaCreacion() != null){
						String fechaSolicitud=DateUtils.formatear(solicitudPericialDTO.getFechaCreacion());
						writer.print("<cell>"+ fechaSolicitud + "</cell>");
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
	
	public ActionForward solicitudEvidenciasPorEstatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			int estatus = Integer.parseInt(request.getParameter("estatus"));
			int areaSolicitante = Integer.parseInt(request.getParameter("areaSolicitante"));
			log.info("Solicitudes Evidencias estatus---- "+estatus);
			log.info("Solicitudes Evidencias areaSolicitante---- "+areaSolicitante);
			UsuarioDTO usuario = super.getUsuarioFirmado(request);
			if(areaSolicitante == 1){
				usuario.setCoordinador(true);
			}else{
				usuario.setCoordinador(false);
			}
			EstatusSolicitud estatusConsulta = null;
			
			if(estatus == 1){
				estatusConsulta = EstatusSolicitud.ABIERTA;
			}else if(estatus == 2){
				estatusConsulta = EstatusSolicitud.EN_PROCESO;
			}else if(estatus == 3){
				estatusConsulta = EstatusSolicitud.CERRADA;
			}
			log.info("Solicitudes Evidencias estatusConsulta---- "+estatusConsulta);
			
			List<SolicitudPericialDTO> solicitudDTOs = new ArrayList<SolicitudPericialDTO>();
			solicitudDTOs = (List<SolicitudPericialDTO>) solicitudDelegate.consultarSolicitudXEstatus(estatusConsulta, usuario, TiposSolicitudes.EVIDENCIA);
			log.info("Evidencias -- " + solicitudDTOs.size());
			
			
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
            
			for(SolicitudPericialDTO solicitudDTO : solicitudDTOs){

				writer.print("<row id='"+solicitudDTO.getDocumentoId()+ "'>");
				writer.print("<cell>"+ solicitudDTO.getFolioSolicitud()+  "</cell>");
				
				writer.print("<cell>");
				if(solicitudDTO.getExpedienteDTO() != null){
					if(solicitudDTO.getExpedienteDTO().getCasoDTO() != null){
						writer.print(solicitudDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso());
					}else{
						writer.print("---");
					}
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(solicitudDTO.getExpedienteDTO() != null){
					if(solicitudDTO.getExpedienteDTO().getNumeroExpediente() != null){
						writer.print(solicitudDTO.getExpedienteDTO().getNumeroExpediente());
					}else{
						writer.print("---");
					}
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(solicitudDTO.getElementos() != null && !solicitudDTO.getElementos().isEmpty()){
					ObjetoDTO objeto = (ObjetoDTO) solicitudDTO.getElementos().get(0);
					writer.print(objeto.getEvidencia().getCadenaDeCustodia().getFolio());
				}
				writer.print("</cell>");

				if(areaSolicitante == 3){
					writer.print("<cell>");
					if(solicitudDTO.getElementos() != null && !solicitudDTO.getElementos().isEmpty()){
						writer.print(solicitudDTO.getElementos().size());
					}
					writer.print("</cell>");

				}else{
					writer.print("<cell>");
					if(solicitudDTO.getElementos() != null && !solicitudDTO.getElementos().isEmpty()){
						ObjetoDTO objeto = (ObjetoDTO) solicitudDTO.getElementos().get(0);
						writer.print(objeto.getEvidencia().getNumeroEvidencia());
					}
					writer.print("</cell>");

				}

				writer.print("<cell>");
				if(solicitudDTO.getUsuarioSolicitante() != null){
					writer.print(solicitudDTO.getUsuarioSolicitante().getNombreCompleto());
				}else{
					writer.print(solicitudDTO.getNombreSolicitante());
				}
				writer.print("</cell>");
				
				if(estatus == 3 && areaSolicitante != 3){
					writer.print("<cell>");
					if(solicitudDTO.getPeritoAsignado() != null){
					writer.print(solicitudDTO.getPeritoAsignado().getNombreCompleto());
					}
				    writer.print("</cell>");
				}
			    
				if(areaSolicitante == 3){
					writer.print("<cell>");
					if(solicitudDTO.getFechaInicioPrestamoStr() != null){
						writer.print(solicitudDTO.getFechaInicioPrestamoStr());
					}	
					writer.print("</cell>");

					writer.print("<cell>");
					if(solicitudDTO.getFechaFinPrestamoStr() != null){
						writer.print(solicitudDTO.getFechaFinPrestamoStr());
					}	
					writer.print("</cell>");
				}
				
				writer.print("<cell>");
				if(solicitudDTO.getFechaLimite() != null){
					writer.print(solicitudDTO.getFechaLimiteStr());
				}
			    writer.print("</cell>");

			    if(estatus == 1){
					writer.print("<cell>");
					if(solicitudDTO.getAcusesReciboDTO() != null){
						writer.print(solicitudDTO.getFolioDocumento());
					}
				    writer.print("</cell>");
			    }else if(estatus == 2){
					writer.print("<cell>");
					if(solicitudDTO.getFechaModificacion() != null){
						writer.print(solicitudDTO.getFechaModificacionStr());
					}
				    writer.print("</cell>");
			    }else if(estatus == 3){
					writer.print("<cell>");
					if(solicitudDTO.getFechaCierre() != null){
						writer.print(solicitudDTO.getFechaCierre());
					}
				    writer.print("</cell>");
			    }
				
			    writer.print("<cell>"+""+ "</cell>");
				
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
	
	public ActionForward solicitudesEvidenciaConcluidasPerito(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			int estatus = Integer.parseInt(request.getParameter("estatus"));
			int areaSolicitante = Integer.parseInt(request.getParameter("areaSolicitante"));
			log.info("Solicitudes Evidencias estatus---- "+estatus);
			log.info("Solicitudes Evidencias areaSolicitante---- "+areaSolicitante);
			UsuarioDTO usuario = super.getUsuarioFirmado(request);
			if(areaSolicitante == 2){
				usuario.setCoordinador(false);
			}else{
				usuario.setCoordinador(true);
			}
			EstatusSolicitud estatusConsulta = null;
			
			if(estatus == 1){
				estatusConsulta = EstatusSolicitud.ABIERTA;
			}else if(estatus == 2){
				estatusConsulta = EstatusSolicitud.EN_PROCESO;
			}else if(estatus == 3){
				estatusConsulta = EstatusSolicitud.CERRADA;
			}
			log.info("Solicitudes Evidencias estatusConsulta---- "+estatusConsulta);
			
			List<SolicitudPericialDTO> solicitudDTOs = new ArrayList<SolicitudPericialDTO>();
			solicitudDTOs = (List<SolicitudPericialDTO>) solicitudDelegate.consultarSolicitudXEstatus(estatusConsulta, usuario, TiposSolicitudes.EVIDENCIA);
			if (log.isDebugEnabled()){
			log.debug("Evidencias -- " + solicitudDTOs);
			}
			
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

			for(SolicitudPericialDTO solicitudDTO : solicitudDTOs){

				writer.print("<row id='"+solicitudDTO.getDocumentoId()+ "'>");
				
				writer.print("<cell>");
				if(solicitudDTO.getExpedienteDTO().getNumeroExpediente() != null){
					writer.print(solicitudDTO.getExpedienteDTO().getNumeroExpediente());
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(solicitudDTO.isDictamenTerminado()){
					writer.print(solicitudDTO.getDictamenes().get(0).getTipoDocumentoDTO().getValor());
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(solicitudDTO.isDictamenTerminado()){
					writer.print(solicitudDTO.getDictamenes().get(0).getNombreDocumento());
				}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(solicitudDTO.getFechaCierre() != null){
					writer.print(solicitudDTO.getFechaCierre());
				}
			    writer.print("</cell>");

				writer.print("<cell>");
				if(solicitudDTO.isDictamenTerminado()){
					writer.print("Si");
				}else{
					writer.print("No");
				}
			    writer.print("</cell>");

			    writer.print("<cell>"+""+ "</cell>");
				
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

	public ActionForward consultaResguardoEvidencias(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			UsuarioDTO usuario = super.getUsuarioFirmado(request);
			usuario.setCoordinador(false);

			List<EvidenciaDTO> evidenciasDTOs = new ArrayList<EvidenciaDTO>();
			evidenciasDTOs =  evidenciaDelegate.consultarEvidenciasResguardadasPorUsario(usuario);
			log.info("Evidencias -- " + evidenciasDTOs);
			
			
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

			for(EvidenciaDTO eviDTO : evidenciasDTOs){

				writer.print("<row id='"+eviDTO.getCadenaDeCustodia().getCadenaDeCustodiaId()+ "'>");
				
				writer.print("<cell>");
				if(eviDTO.getObjeto().getExpedienteDTO().getCasoDTO() != null){
					writer.print(eviDTO.getObjeto().getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso());
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(eviDTO.getDuenioEvidencia() != null){//Nombre AMP
					writer.print(eviDTO.getDuenioEvidencia());
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(eviDTO.getCadenaDeCustodia() != null){//folio cadena custodia
					writer.print(eviDTO.getCadenaDeCustodia().getFolio());
				}
				writer.print("</cell>");
				
				writer.print("<cell>");
					writer.print(eviDTO.getCantEvidenciasResguardadas());
			    writer.print("</cell>");

				writer.print("<cell>");
				if(eviDTO.getObjeto().getAlmacen() != null){//nombre almacen
					writer.print(eviDTO.getObjeto().getAlmacen().getIdentificadorAlmacen());
				}
			    writer.print("</cell>");

			    writer.print("<cell>"+""+ "</cell>");
				
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

	public ActionForward consultaDetalleResguardoEvidencias(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			String folioCadena = request.getParameter("folioCadena");
			log.info("folioCadena -- " + folioCadena);
			
			CadenaDeCustodiaDTO cadena = new CadenaDeCustodiaDTO();
			cadena.setFolio(folioCadena);
			
			List<EvidenciaDTO> evidenciasDTOs = new ArrayList<EvidenciaDTO>();
			
			evidenciasDTOs =  evidenciaDelegate.consultarEvidenciasXCadenaCustodia(cadena);
			log.info("Evidencias Detalle-- " + evidenciasDTOs);
			
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
            
			for(EvidenciaDTO eviDTO : evidenciasDTOs){

				writer.print("<row id='"+eviDTO.getCadenaDeCustodia().getCadenaDeCustodiaId()+ "'>");
				
				writer.print("<cell>");
				if(eviDTO.getNumeroEvidencia() != null){
					writer.print(eviDTO.getNumeroEvidencia());
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(eviDTO.getObjeto() != null){
					writer.print(eviDTO.getObjeto().getTipoObjeto());
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(eviDTO.getObjeto() != null){
					writer.print(eviDTO.getObjeto().getValorIdElemento().getNombreCampo());//objeto
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(eviDTO.getObjeto() != null){
					writer.print(eviDTO.getObjeto().getDescripcion());
				}
				writer.print("</cell>");
				
				writer.print("<cell>");
					writer.print(eviDTO.getCodigoBarras());
			    writer.print("</cell>");

				writer.print("<cell>");
					writer.print(eviDTO.getObjeto());//fecha inicio prestamo
			    writer.print("</cell>");

				writer.print("<cell>");
					writer.print(eviDTO.getObjeto());//fecha fin prestamo
		        writer.print("</cell>");

			    writer.print("<cell>");
			    if(eviDTO.getObjeto().getAlmacen() != null){//nombre almacen
				    writer.print(eviDTO.getObjeto().getAlmacen().getIdentificadorAlmacen());
			    }
		        writer.print("</cell>");

		        writer.print("<cell>"+""+ "</cell>");
				
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
	
	public ActionForward solicitudAsesoriasPorEstatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			int estatus = Integer.parseInt(request.getParameter("estatus"));
			log.info("Solicitudes Asesorias estatus---- "+estatus);
			UsuarioDTO usuario = super.getUsuarioFirmado(request);
			EstatusSolicitud estatusConsulta = null;
			
			if(estatus == 1){
				estatusConsulta = EstatusSolicitud.ABIERTA;
			}else if(estatus == 2){
				estatusConsulta = EstatusSolicitud.EN_PROCESO;
			}else if(estatus == 3){
				estatusConsulta = EstatusSolicitud.CERRADA;
			}
			log.info("Solicitudes Asesorias estatusConsulta---- "+estatusConsulta);
			
			List<SolicitudPericialDTO> solicitudDTOs = new ArrayList<SolicitudPericialDTO>();
			solicitudDTOs = (List<SolicitudPericialDTO>) solicitudDelegate.consultarSolicitudXEstatus(estatusConsulta, usuario, TiposSolicitudes.ASESORIA);
			log.info("Asesorias -- " + solicitudDTOs);
			
			
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

			for(SolicitudPericialDTO solicitudDTO : solicitudDTOs){

				writer.print("<row id='"+solicitudDTO.getDocumentoId()+ "'>");

				writer.print("<cell>"+ solicitudDTO.getFolioSolicitud()+  "</cell>");
				
				writer.print("<cell>");
				if(solicitudDTO.getExpedienteDTO().getCasoDTO() != null){
					writer.print(solicitudDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso());
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(solicitudDTO.getExpedienteDTO().getNumeroExpediente() != null){
					writer.print(solicitudDTO.getExpedienteDTO().getNumeroExpediente());
				}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(solicitudDTO.getUsuarioSolicitante() != null){
					writer.print(solicitudDTO.getUsuarioSolicitante().getNombreCompleto());
				}else {
					writer.print(solicitudDTO.getNombreSolicitante());
				}
				writer.print("</cell>");
				
				if(estatus == 3){
					writer.print("<cell>");
					if(solicitudDTO.getPeritoAsignado() != null){//Ajustar al valor real del perito designado
						writer.print(solicitudDTO.getPeritoAsignado().getNombreCompleto());
					}
					writer.print("</cell>");

				}
				
				writer.print("<cell>");
				if(solicitudDTO.getUsuarioSolicitante() != null){
					writer.print(solicitudDTO.getUsuarioSolicitante().getEspecialidad().getValor());
				}
				writer.print("</cell>");
				

				writer.print("<cell>");
				if(solicitudDTO.getFechaLimite() != null){
				writer.print(solicitudDTO.getFechaLimite());
				}
			    writer.print("</cell>");
			    
			    if(estatus == 1){
					writer.print("<cell>");
					if(solicitudDTO.getAcusesReciboDTO() != null){
						writer.print(solicitudDTO.getFolioDocumento());
					}
				    writer.print("</cell>");
			    }else if(estatus == 2){
					writer.print("<cell>");
					if(solicitudDTO.getFechaModificacion() != null){
						writer.print(solicitudDTO.getFechaModificacion());
					}
				    writer.print("</cell>");
			    }else if(estatus == 3){
					writer.print("<cell>");
					if(solicitudDTO.getFechaCierre() != null){
						writer.print(solicitudDTO.getFechaCierre());
					}
				    writer.print("</cell>");
			    }

			    writer.print("<cell>"+""+ "</cell>");
				
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
	 * Metodo para consultar el detalle de una solicitud
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultaSolicitudesEvidenciasPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO CONSULTA DETALLE SOLICITUDES EVIDENCIAS");
			
			//Se obtiene el id de la solicitud a consultar 
			String solicitudId = request.getParameter("solicitudId");
			
			log.info("id de la solicitud::::::::::"+ solicitudId);
						
			SolicitudDTO solicitudDTO = new SolicitudDTO(); 
			solicitudDTO.setDocumentoId(Long.parseLong(solicitudId));
											
			SolicitudDTO solicitudDTO2 = solicitudDelegate.obtenerDetalleSolicitud(solicitudDTO);
			log.info("depues del delegate::: solicitudDTO" + solicitudDTO2);
			log.info("Folio de la solicitud: " + solicitudDTO2.getFolioSolicitud());
			converter.alias("solicitudDTO", SolicitudDTO.class);
			String xml = converter.toXML(solicitudDTO2);
			log.info("xml de la solicitud respuesta: :::::::::"+ xml);
			
			escribir(response, xml,null);												
			
		} catch (Exception e) {		
			log.info("ERROR AL CONSULTAR LA SOLICITUD ---- consultaSolicitudesEvidenciasPorId");
			log.info(e.getCause(),e);
			escribir(response, "consultaSolicitudesEvidenciasPorId",null);
			
		}
		return null;
	}
	
	
	/**
	 * Metodo que consulta las evidencias que se van a devolver por parte de perito
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarEvidenciasADevolver(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR EVIDENCIAS A DEVOLVER");
			String folioCadenaCustodia = request.getParameter("folioCadenaCustodia");
			log.info("folioCadenadeCustodia -- " + folioCadenaCustodia);
			
			CadenaDeCustodiaDTO cadena = new CadenaDeCustodiaDTO();
			cadena.setFolio(folioCadenaCustodia);
			
			List<EvidenciaDTO> evidenciasDTOs = new ArrayList<EvidenciaDTO>();
			
			UsuarioDTO usuarioFirmado = getUsuarioFirmado(request);
			
			evidenciasDTOs =  evidenciaDelegate.consultarEvidenciasResguardadasPorUsario(usuarioFirmado, cadena);
			log.info("Evidencias Detalle-- " + evidenciasDTOs);
			
			
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

			for(EvidenciaDTO eviDTO : evidenciasDTOs){

				if(eviDTO.getCadenaDeCustodia() != null && eviDTO.getCadenaDeCustodia().getCadenaDeCustodiaId() != null){
					writer.print("<row id='"+eviDTO.getCadenaDeCustodia().getCadenaDeCustodiaId()+ "'>");
				}else{
					writer.print("<row id='"+" "+ "'>");
				}
				//Numero de evidencia
				writer.print("<cell>");
					if(eviDTO.getNumeroEvidencia() != null){
						writer.print(eviDTO.getNumeroEvidencia());
					}
				writer.print("</cell>");
				
				//Tipo de Objeto
				writer.print("<cell>");
					if(eviDTO.getObjeto() != null && eviDTO.getObjeto().getTipoObjeto() != null){
						writer.print(eviDTO.getObjeto().getTipoObjeto());
					}
				writer.print("</cell>");
				
				//Nombre objeto
				writer.print("<cell>");
					if(eviDTO.getObjeto() != null && eviDTO.getObjeto().getValorIdElemento() != null && eviDTO.getObjeto().getValorIdElemento().getNombreCampo() != null){
						writer.print(eviDTO.getObjeto().getValorIdElemento().getNombreCampo());//objeto
					}
				writer.print("</cell>");
				
				//descripcion 
				writer.print("<cell>");
					if(eviDTO.getObjeto() != null && eviDTO.getObjeto().getDescripcion() != null){
						writer.print(eviDTO.getObjeto().getDescripcion());
					}
				writer.print("</cell>");
				
				//codigo de barras
				writer.print("<cell>");
					if(eviDTO.getCodigoBarras() != null){
						writer.print(eviDTO.getCodigoBarras());
					}
				writer.print("</cell>");
				
//			    writer.print("<cell>");
//				    if(eviDTO.getObjeto().getAlmacen() != null){//nombre almacen
//					    writer.print(eviDTO.getObjeto().getAlmacen().getIdentificadorAlmacen());
//				    }
//		        writer.print("</cell>");
		        
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
	 * Metodo utilizado para consultar las solicitudes periciales en status de 
	 * no atendidas
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesPericialesDefensor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR CONSULTAR SOLICITUDES PERICIALES NO ATENDIDAS");
			String tipoSolicitud = request.getParameter("tipoSolicitud");
			if(tipoSolicitud != null){
				
				Long tipoSol = Long.parseLong(tipoSolicitud);
				log.debug("tipoSol :: " + tipoSol);
				Puestos puesto = null;
				if(TiposSolicitudes.ASESORIA.getValorId().equals(tipoSol)){
					puesto = Puestos.COORDINADOR_DE_SERVICIOS_PERICIALES;
				}else if(TiposSolicitudes.DICTAMEN.getValorId().equals(tipoSol)){
					puesto = Puestos.COORDINADOR_DE_DEFENSORES;
				}
				List<SolicitudPericialDTO> listaSolicitudesNoAtendidas = solicitudPericialDelegate.
				consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(TiposSolicitudes.getByValor(tipoSol),EstatusSolicitud.ABIERTA,puesto);
				
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
                
                for (SolicitudPericialDTO solicitudPericialDTO : listaSolicitudesNoAtendidas) {
				    if (log.isDebugEnabled()){
				        log.debug("SOLICITUD PERICIAL DTO" + solicitudPericialDTO);
				    }
					writer.print("<row id='"+ solicitudPericialDTO.getDocumentoId() + "'>");
						writer.print("<cell>"+ ((solicitudPericialDTO.getExpedienteDTO()!=null && solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente()!=null) ? solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente():"-") +  "</cell>");
						writer.print("<cell>"+ this.getNoCaso(solicitudPericialDTO)+  "</cell>");
						if (solicitudPericialDTO.getUsuarioSolicitante()!=null) {
						    writer.print("<cell>"+ (solicitudPericialDTO.getUsuarioSolicitante()!=null ? solicitudPericialDTO.getUsuarioSolicitante().getNombreCompleto():"-") +  "</cell>");
						} else {
						    writer.print("<cell>"+ (solicitudPericialDTO.getNombreSolicitante()!=null ? solicitudPericialDTO.getNombreSolicitante():"-") +  "</cell>");
						}
						writer.print("<cell>"+ (solicitudPericialDTO.getEspecialidad()!=null && solicitudPericialDTO.getEspecialidad().getValor()!=null? solicitudPericialDTO.getEspecialidad().getValor():"-") +  "</cell>");
						if(solicitudPericialDTO.getFechaLimite() != null){
							String fechaLimite=DateUtils.formatear(solicitudPericialDTO.getFechaLimite());
							writer.print("<cell>"+ fechaLimite + "</cell>");
						}
						else{
							writer.print("<cell>"+ "---" + "</cell>");
						}
						writer.print("<cell>"+  "true" +  "</cell>");
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
			}
			
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	/**
	 * Metodo que consulta las evidencias relacionadas a una cadena de custodia
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarEvidenciasCadenaDeCustodia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR EVIDENCIAS DE CADENA DE CUSTODIA");
			String folioCadenaCustodia = request.getParameter("folioCadenaCustodia");
			log.info("folioCadenadeCustodia -- " + folioCadenaCustodia);
			
			CadenaDeCustodiaDTO cadena = new CadenaDeCustodiaDTO();			
			cadena = consultarCadenaDelegate.consultarCadenaDeCustodiaXFolio(folioCadenaCustodia);
			log.info("Id de cadena de custodia: " + cadena.getCadenaDeCustodiaId());
			List<EvidenciaDTO> evidenciasDTOs = new ArrayList<EvidenciaDTO>();								
			evidenciasDTOs =  evidenciaDelegate.consultarEvidenciasXCadenaCustodia(cadena);
			log.info("Evidencias Detalle-- " + evidenciasDTOs);
			
			
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

			for(EvidenciaDTO eviDTO : evidenciasDTOs){

				//Id de evidencia
				
					writer.print("<row id='"+eviDTO.getEvidenciaId()+ "'>");							
				
				//Numero de evidencia
				writer.print("<cell>");
					if(eviDTO.getNumeroEvidencia() != null){
						writer.print(eviDTO.getNumeroEvidencia());
					}
				writer.print("</cell>");
				
				//Cadena de Custodia
				writer.print("<cell>");
					if(eviDTO.getCadenaDeCustodia() != null && eviDTO.getCadenaDeCustodia().getFolio() != null){
						writer.print(eviDTO.getCadenaDeCustodia().getFolio());
					}
				writer.print("</cell>");
				
				//Tipo de Objeto
				writer.print("<cell>");
					if(eviDTO.getObjeto() != null && eviDTO.getObjeto().getTipoObjeto() != null){
						writer.print(eviDTO.getObjeto().getTipoObjeto());
					}
				writer.print("</cell>");
				
				//codigo de barras
				writer.print("<cell>");
					if(eviDTO.getCodigoBarras() != null){
						writer.print(eviDTO.getCodigoBarras());
					}
				writer.print("</cell>");
				
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
	 * Guardar Solicitud
	 * Cambia el estatus de la solicitud de Abierta a En proceso	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NSJPNegocioException 
	 * @throws IOException
	 */
	
	public ActionForward guardarSolicitudEvidencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws NSJPNegocioException
	{
		try{
			
			String arrayIds = request.getParameter("arrayIds");
			String observaciones = request.getParameter("observaciones");
			Long destinatarioId = NumberUtils.toLong(request.getParameter("destinatarioId"));
			Long tipoSolicitudServicio = NumberUtils.toLong(request.getParameter("tipoServicio"), 0);
			Long numeroExpedienteId = new Long(request.getParameter("numeroExpedienteId"));
			//Long solicitudId = NumberUtils.toLong(request.getParameter("solicitudId"), 0);
			
			SolicitudPericialDTO solicitudPericialDTO = new SolicitudPericialDTO();
			solicitudPericialDTO.setTipoSolicitudDTO(new ValorDTO(tipoSolicitudServicio));
			
			List<FuncionarioDTO> funcionariosDTOs = new ArrayList<FuncionarioDTO>();
			funcionariosDTOs = funcionarioDelegate.consultarFuncionariosPorRol(Puestos.PERITO.getValorId()); //coordinadorPer
			solicitudPericialDTO.setUsuarioSolicitante(funcionariosDTOs.get(0)); 
			//Por el momento esta hardcoded, pero debe descomentarse la linea siguiente			
			//solicitudPericialDTO.setUsuarioSolicitante(getUsuarioFirmado(request).getFuncionario());
			solicitudPericialDTO.setFechaCreacion(new Date());
			solicitudPericialDTO.setEstatus(new ValorDTO(EstatusSolicitud.EN_PROCESO.getValorId()));
			solicitudPericialDTO.setExpedienteDTO(new ExpedienteDTO());
			solicitudPericialDTO.getExpedienteDTO().setNumeroExpedienteId(numeroExpedienteId);
			solicitudPericialDTO.setObservaciones(observaciones);

			List<FuncionarioDTO> funcs  = null;
			funcs = funcionarioDelegate.consultarFuncionariosPorRol(destinatarioId);
			if(funcs!=null && !funcs.isEmpty()){
				solicitudPericialDTO.setDestinatario(funcs.get(0));
			}
			
			String[] arrEvidencias = arrayIds.split(",");
			List<EvidenciaDTO> evidenciasDTOs = new ArrayList<EvidenciaDTO>();
			for(int i=0; i<arrEvidencias.length; i++){
				EvidenciaDTO dto = new EvidenciaDTO();
				dto.setEvidenciaId(new Long(arrEvidencias[i]));
				evidenciasDTOs.add(dto);
			}
			
			long documentoId = solicitudPericialDelegate.asignarEvidenciaASolicitudPericial(evidenciasDTOs, solicitudPericialDTO);
			log.debug("SOLICITUD PERICIAL ID::::::::::::::::::::::::::::::::::::::::::"+documentoId);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.write("<documentoId>" + documentoId + "</documentoId>");
			writer.flush();
			writer.close();
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Guarda Autorizar Evidencia o la envia a los funcionarios
	 * Cambia el estatus de la solicitud de Abierta a En proceso	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NSJPNegocioException 
	 * @throws IOException
	 */	
	public ActionForward guardaAutorizarEvidencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws NSJPNegocioException
	{
		try{			
			int area = NumberUtils.toInt(request.getParameter("area"), 0);
			String nombreAutorizado = request.getParameter("nombreAutorizado");
			Long solicitudId = NumberUtils.toLong(request.getParameter("solicitudId"), 0);
			String docIdentificacion = request.getParameter("docIdentificacion");
			String folioDoc = request.getParameter("folioDoc");			

			SolicitudPericialDTO solicitudPericialDTO = new SolicitudPericialDTO();
			solicitudPericialDTO.setObservaciones(request.getParameter("observaciones"));
			solicitudPericialDTO.setFechaInicioPrestamo(DateUtils.obtener(request.getParameter("fechaInicioPrestamo")));
			solicitudPericialDTO.setFechaFinPrestamo(DateUtils.obtener(request.getParameter("fechaFinPrestamo")));
			solicitudPericialDTO.setFechaInicioPrestamoStr(request.getParameter("fechaInicioPrestamo"));
			solicitudPericialDTO.setFechaFinPrestamoStr(request.getParameter("fechaFinPrestamo")); 
			solicitudPericialDTO.setDocumentoId(solicitudId);
			solicitudPericialDTO.setEstatus(new ValorDTO(EstatusSolicitud.EN_PROCESO.getValorId()));
			/*solicitudPericialDTO.setDocIdentificacion();
			solicitudPericialDTO.setFolioDoc();
			solicitudPericialDTO.setNombreAutorizado();*/

			List<FuncionarioDTO> funcs  = null;
			funcs = funcionarioDelegate.consultarFuncionariosPorRol(NumberUtils.toLong(request.getParameter("puestoId")));
			if(funcs!=null && !funcs.isEmpty()){
				solicitudPericialDTO.setDestinatario(funcs.get(0));
			}

			Long documentoId = solicitudDelegate.autorizarSolicitudDeEvidencia(solicitudPericialDTO);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.write("<documentoId>" + documentoId + "</documentoId>");
			writer.flush();
			writer.close();
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Metodo que consulta las evidencias relacionadas a una cadena de custodia en base a un Expediente
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarCadenaCustodiaXNumeroExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        try {

            log.info("EJECUTANDO ACTION CONSULTAR EVIDENCIAS DE CADENA DE CUSTODIA");
            String numeroExpedienteId = request
                    .getParameter("numeroExpedienteId");
            String folioCadenaCustodia = request
                    .getParameter("folioCadenaCustodia");
            log.info("folioCadenaCustodia [antes]-- " + folioCadenaCustodia);
            folioCadenaCustodia = folioCadenaCustodia.equals("")
                    ? null
                    : folioCadenaCustodia;
            log.info("numeroExpedienteId -- " + numeroExpedienteId);
            log.info("folioCadenaCustodia -- " + folioCadenaCustodia);

            List<CadenaDeCustodiaDTO> cadenasDTOs = new ArrayList<CadenaDeCustodiaDTO>();
            cadenasDTOs = cadenaDeCustodiaDelegate
                    .consultarCadenaCustodiaXNumeroExpediente(new Long(
                            numeroExpedienteId), folioCadenaCustodia);

            response.setContentType("text/xml; charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter writer = response.getWriter();

            writer.print("<rows>");

            // int iTotalRegistros = 0;
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

            for (CadenaDeCustodiaDTO cadenaDTO : cadenasDTOs) {
                List<EvidenciaDTO> evidenciasDTOs = new ArrayList<EvidenciaDTO>();
                evidenciasDTOs = cadenaDTO.getEvidencias();

                for (EvidenciaDTO eviDTO : evidenciasDTOs) {
                    // Id de evidencia

                    writer.print("<row id='" + eviDTO.getEvidenciaId() + "'>");

                    // Cadena de Custodia
                    writer.print("<cell>");
                    writer.print(cadenaDTO.getFolio());
                    writer.print("</cell>");

                    // Objeto
                    writer.print("<cell>");
                    if (eviDTO.getObjeto() != null
                            && eviDTO.getObjeto().getTipoObjeto() != null) {
                        writer.print(eviDTO.getObjeto().getTipoObjeto());
                    }
                    writer.print("</cell>");

                    // Tipo de objeto
                    writer.print("<cell>");
                    if (eviDTO.getObjeto() != null) {
                        if (StringUtils.isNotBlank(eviDTO.getObjeto()
                                .getDescripcion())) {
                            writer.print(eviDTO.getObjeto().getDescripcion());
                        } else {
                            writer.print("");
                        }
                    }
                    writer.print("</cell>");

                    // codigo de barras
                    writer.print("<cell>");
                    if (eviDTO.getCodigoBarras() != null) {
                        writer.print(eviDTO.getCodigoBarras());
                    }
                    writer.print("</cell>");

                    writer.print("</row>");

                }

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
	 * Guardar Solicitud
	 * Cambia el estatus de la solicitud de Abierta a En proceso	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NSJPNegocioException 
	 * @throws IOException
	 */
	
	public ActionForward enviarEvidenciasSolicitudServicioPericial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws NSJPNegocioException
	{
		try{
			log.debug("EJECUTANDO ACTION ENVIAR EVIDENCIAS SOLICITUD SERVICIO PERICIAL::::::::::::::::::::::::::::::::::::::::::");
			String arrayIds = request.getParameter("arrayIds");
			String observaciones = request.getParameter("observaciones");
			Long destinatarioId = NumberUtils.toLong(request.getParameter("destinatarioId"));			
			Long tipoSolicitudServicio = NumberUtils.toLong(request.getParameter("tipoServicio"), 0);
			Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"), 0);
			Long especialidad = NumberUtils.toLong(request.getParameter("especialidad"));
			String idsDoctsSelecc = request.getParameter("idsDoctsSelecc");
			Long funcDestinatario = NumberUtils.toLong(request.getParameter("funcDestinatario"),0L);
			Long rolId = NumberUtils.toLong(request.getParameter("rolId"),0L);
			
			
			log.debug("Documentos seleccionados: " + idsDoctsSelecc);
			
			SolicitudPericialDTO solicitudPericialDTO = new SolicitudPericialDTO();
			solicitudPericialDTO.setEspecialidad(new ValorDTO(especialidad));
			solicitudPericialDTO.setFechaLimite(DateUtils.obtener(request.getParameter("fechaLimite")));
			solicitudPericialDTO.setTipoSolicitudDTO(new ValorDTO(tipoSolicitudServicio));
			solicitudPericialDTO.setUsuarioSolicitante(getUsuarioFirmado(request).getFuncionario());
			solicitudPericialDTO.setFechaCreacion(new Date());
			solicitudPericialDTO.setEstatus(new ValorDTO(EstatusSolicitud.EN_PROCESO.getValorId()));
			solicitudPericialDTO.setExpedienteDTO(new ExpedienteDTO());
			solicitudPericialDTO.getExpedienteDTO().setNumeroExpedienteId(numeroExpedienteId);
			solicitudPericialDTO.setObservaciones(observaciones);

			if(funcDestinatario != null && funcDestinatario > 0L && rolId != null  && rolId > 0L){
				//Buscar el funcionario y traer el area correspondiente al ROL
			
				FuncionarioDTO filtroDTO = new FuncionarioDTO();
				filtroDTO.setClaveFuncionario(funcDestinatario);
				List<FuncionarioDTO> listaFunc = funcionarioDelegate.consultarFuncionarioXNumeroEmpleado(filtroDTO);
				
				if(listaFunc!=null && !listaFunc.isEmpty()){
					
					//SE NECESITA UNA CONSULTA A LA BD PARA CONSULTAR A QUE AREA LE CORRESPONDE QUE ROL???
					JerarquiaOrganizacionalDTO jerarquiaFunc = new JerarquiaOrganizacionalDTO(); 
					
//					
//					if(rolId.equals(Roles.COORDINADORPER.getValorId())){
//						jerarquiaFunc.setJerarquiaOrganizacionalId(Areas.COORDINACION_PERICIALES_DEF.parseLong());
//					}else if(rolId.equals(Roles.COORDPERFIS.getValorId())){
//						jerarquiaFunc.setJerarquiaOrganizacionalId(Areas.COORDINACION_PERICIALES_PG.parseLong());
//					}else if(rolId.equals(Roles.COORDINADORDEF.getValorId())){
//						jerarquiaFunc.setJerarquiaOrganizacionalId(Areas.COORDINACION_DEFENSORIA.parseLong());
//					}
					RolDTO rol = new RolDTO();
					rol.setRolId(rolId);
					rol = rolDelegate.consultarRol(rol);
					
					if(rol != null && rol.getJerarquiaOrganizacionalDTO() != null){
						listaFunc.get(0).setJerarquiaOrganizacional(rol.getJerarquiaOrganizacionalDTO());
					}
					else{
						listaFunc.get(0).setJerarquiaOrganizacional(jerarquiaFunc);
					}
					solicitudPericialDTO.setDestinatario(listaFunc.get(0));
				}
			}
			else{
				//Usado para solicitud policial
				List<FuncionarioDTO> funcs  = null;
				funcs = funcionarioDelegate.consultarFuncionariosPorRol(destinatarioId);
				if(funcs!=null && !funcs.isEmpty()){
					solicitudPericialDTO.setDestinatario(funcs.get(0));
				}
			}
			
			log.info("/**** Arrays :: "+arrayIds);
			
			List<EvidenciaDTO> evidenciasDTOs = new ArrayList<EvidenciaDTO>();
			if (arrayIds!=null && !arrayIds.isEmpty()) {
				String[] arrEvidencias = arrayIds.split(",");				
				for(int i=0; i<arrEvidencias.length; i++){
					EvidenciaDTO dto = new EvidenciaDTO();
					dto.setEvidenciaId(new Long(arrEvidencias[i]));
					evidenciasDTOs.add(dto);
				}
			}
						
			long documentoId = solicitudPericialDelegate.asignarEvidenciaASolicitudPericial(evidenciasDTOs, solicitudPericialDTO);
			log.debug("SOLICITUD PERICIAL ID::::::::::::::::::::::::::::::::::::::::::"+documentoId);
			//la siguiente linea estaba comentada
			Boolean respuesta = documentoDelegate.asociarArchivosDigitalesASolicitud(documentoId, idsDoctsSelecc);
			log.debug("Boolean Asociación de archivos digitales: " + respuesta);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.write("<documentoId>" + documentoId + "</documentoId>");
			writer.flush();
			writer.close();
			
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Metodo que consulta las evidencias relacionadas a una solicitud
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarEvidenciasSolicitud(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.debug("EJECUTANDO ACTION CONSULTAR EVIDENCIAS DE SOLICITUD PERICIAL");
			long solicitudId = new Long(request.getParameter("solicitudId"));
			log.debug("solicitudId --- " + solicitudId);

			List<EvidenciaDTO> evidenciasDTOs = new ArrayList<EvidenciaDTO>();
			evidenciasDTOs = evidenciaDelegate.consultarEvidenciasXIdSolicitud(solicitudId);
			
			log.debug("evidencias.size() ... " +evidenciasDTOs.size());
			
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
			
			for(EvidenciaDTO evidenciaDTO : evidenciasDTOs){
				//Id de evidencia					
				writer.print("<row id='"+evidenciaDTO.getEvidenciaId() + "'>");							
				
				//Numero de evidencia
				writer.print("<cell>");
				if(evidenciaDTO.getNumeroEvidencia() != null){
					writer.print(evidenciaDTO.getNumeroEvidencia());
				}
				writer.print("</cell>");
			
				//Cadena de Custodia
				writer.print("<cell>");
				log.debug("fuera if ... " + evidenciaDTO.getCadenaDeCustodia().getFolio());
				if(evidenciaDTO.getCadenaDeCustodia() != null && evidenciaDTO.getCadenaDeCustodia().getFolio() != null){
					log.debug("dentro if ... " + evidenciaDTO.getCadenaDeCustodia().getFolio());
					writer.print(evidenciaDTO.getCadenaDeCustodia().getFolio());
				}
				writer.print("</cell>");
			
				//Tipo de Objeto
				writer.print("<cell>");
				if(evidenciaDTO.getObjeto() != null && evidenciaDTO.getObjeto().getTipoObjeto() != null){
					writer.print(evidenciaDTO.getObjeto().getTipoObjeto());
				}
				writer.print("</cell>");
			
				//codigo de barras
				/*writer.print("<cell>");
				if(evidenciaDTO.getCodigoBarras() != null){
					writer.print(evidenciaDTO.getCodigoBarras());
				}
				writer.print("</cell>");*/
			
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
	 * Metodo que envia la solicitud con el perito designado.
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws NSJPNegocioException En caso de obtener una exception
	 */
	public ActionForward enviarSolicitudDesignarPerito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws NSJPNegocioException
	{
		try{
			
			Long solicitudId = new Long(request.getParameter("solicitudPericialId"));			
			Long peritoId = new Long(request.getParameter("peritoId"));
			log.debug("solicitudId ... " + solicitudId);
			log.debug("peritoId ... " + peritoId);
			
			long documentoId = solicitudPericialDelegate.asignarFuncionarioASolicitud(peritoId, solicitudId);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.write("<documentoId>" + documentoId + "</documentoId>");
			writer.flush();
			writer.close();
			
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Metodo que envia la solicitud al coordinador pericial de defensoria.
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws NSJPNegocioException En caso de obtener una exception
	 */
	public ActionForward enviarSolicitudPeritoCoordPerDef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws NSJPNegocioException
	{
		try{
			
			Long solicitudId = new Long(request.getParameter("solicitudPericialId"));
			Long funcionarioId = 0L;
			
			List<FuncionarioDTO> funcionariosDTOs  = new ArrayList<FuncionarioDTO>();
			funcionariosDTOs = funcionarioDelegate.consultarFuncionariosPorRol(Puestos.COORDINADOR_PERICIAL_DE_DEFENSORIA.getValorId()); //coordinadorPer
			funcionarioId = funcionariosDTOs.size() > 0 ? funcionariosDTOs.get(0).getClaveFuncionario() : funcionarioId;
			
			log.debug("solicitudId ... " + solicitudId);
			log.debug("peritoId ... " + funcionariosDTOs.get(0));

			long documentoId = solicitudPericialDelegate.asignarFuncionarioASolicitud(funcionarioId, solicitudId);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.write("<documentoId>" + documentoId + "</documentoId>");
			writer.flush();
			writer.close();
			
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Metodo que realiza la consulta de solicitudes periciales relacionadas con
	 * un numero de causa, originalemte usado en el visor de elementos.
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws NSJPNegocioException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesPericialesPorExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws NSJPNegocioException
	{
		try{
			
			Long numeroExpedienteId = new Long(request.getParameter("numeroExpedienteId"));			
			Long estatus = new Long(request.getParameter("estatus"));
			log.debug("numeroExpedienteId ... " + numeroExpedienteId);
			log.debug("estatus ... " + estatus);
			UsuarioDTO usuario = getUsuarioFirmado(request);
			
			List<SolicitudPericialDTO> solicitudes = solicitudPericialDelegate.
			consultarSolicitudesPericialesPorPuestoSolicitanteEstatusYNumeroExpediente
			(Puestos.getByValor(usuario.getFuncionario().getPuesto().getIdCampo()),
			 new EstatusSolicitud[]{EstatusSolicitud.getByValor(estatus)} , numeroExpedienteId);
			
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
			
			for(SolicitudPericialDTO solicitud : solicitudes){
							
				writer.print("<row id='"+solicitud.getDocumentoId() + "'>");							
				
				
				writer.print("<cell>" + (solicitud.getExpedienteDTO()!=null && 
										solicitud.getExpedienteDTO().getCasoDTO() != null?
										solicitud.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso():"-")
										+  "</cell>");
				
				writer.print("<cell>" + (solicitud.getExpedienteDTO()!=null?
						solicitud.getExpedienteDTO().getNumeroExpediente():"-")
						+  "</cell>");
				
				writer.print("<cell>" + (solicitud.getFolioSolicitud())
						+  "</cell>");
				
				writer.print("<cell>" + (solicitud.getEstatus().getValor())
						+  "</cell>");
				
				writer.print("<cell>" + DateUtils.formatear(solicitud.getFechaCreacion())
						+  "</cell>");
				
				writer.print("<cell>" + DateUtils.formatear(solicitud.getFechaLimite())
						+  "</cell>");
				writer.print("<cell>" + (solicitud.getUsuarioSolicitante()!=null?
						solicitud.getUsuarioSolicitante().getNombreInstitucion():"-")
						+  "</cell>");
				writer.print("<cell>" + (solicitud.getDestinatario()!=null?
										solicitud.getDestinatario().getNombreCompleto():"")
						+  "</cell>");
			
				
			
				writer.print("</row>");				
			}
						
			writer.print("</rows>");
			writer.flush();
			writer.close();
						
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
}
