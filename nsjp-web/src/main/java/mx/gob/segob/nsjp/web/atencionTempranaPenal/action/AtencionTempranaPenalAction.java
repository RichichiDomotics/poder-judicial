package mx.gob.segob.nsjp.web.atencionTempranaPenal.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.comun.enums.audiencia.Eventos;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.OrigenExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.enums.seguridad.Roles;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.actividad.ActividadDelegate;
import mx.gob.segob.nsjp.delegate.actividad.ConfActividadDocumentoDelegate;
import mx.gob.segob.nsjp.delegate.alarma.AlarmaDelegate;
import mx.gob.segob.nsjp.delegate.almacen.AlmacenDelegate;
import mx.gob.segob.nsjp.delegate.audiencia.AudienciaDelegate;
import mx.gob.segob.nsjp.delegate.avisodetencion.AvisoDetencionDelegate;
import mx.gob.segob.nsjp.delegate.bitacora.BitacoraConsultaDelegate;
import mx.gob.segob.nsjp.delegate.bitacora.BitacoraMovimientoDelegate;
import mx.gob.segob.nsjp.delegate.caso.CasoDelegate;
import mx.gob.segob.nsjp.delegate.catalogo.CatalogoDelegate;
import mx.gob.segob.nsjp.delegate.delito.DelitoDelegate;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.eslabon.EslabonDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.expediente.TurnoDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.delegate.objeto.ObjetoDelegate;
import mx.gob.segob.nsjp.delegate.relacion.RelacionDelegate;
import mx.gob.segob.nsjp.delegate.rol.RolDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudPericialDelegate;
import mx.gob.segob.nsjp.delegate.usuario.UsuarioDelegate;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.ConfActividadDocumentoDTO;
import mx.gob.segob.nsjp.dto.alarmas.AlarmaDTO;
import mx.gob.segob.nsjp.dto.alarmas.AlertaDTO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.bitacora.BitacoraConsultaDTO;
import mx.gob.segob.nsjp.dto.bitacora.BitacoraMovimientoDTO;
import mx.gob.segob.nsjp.dto.bitacora.FiltroBitacoraConsultaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.documento.CuerpoOficioEstructuradoDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.IndiceEstructuradoDTO;
import mx.gob.segob.nsjp.dto.documento.OficioEstructuradoDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.DatosGeneralesExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoPersonaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.FiltroExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.NotaExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.institucion.DepartamentoDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.dto.objeto.AeronaveDTO;
import mx.gob.segob.nsjp.dto.objeto.AnimalDTO;
import mx.gob.segob.nsjp.dto.objeto.ArmaDTO;
import mx.gob.segob.nsjp.dto.objeto.DocumentoOficialDTO;
import mx.gob.segob.nsjp.dto.objeto.EmbarcacionDTO;
import mx.gob.segob.nsjp.dto.objeto.EquipoComputoDTO;
import mx.gob.segob.nsjp.dto.objeto.ExplosivoDTO;
import mx.gob.segob.nsjp.dto.objeto.InmuebleDTO;
import mx.gob.segob.nsjp.dto.objeto.JoyaDTO;
import mx.gob.segob.nsjp.dto.objeto.NumerarioDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObraArteDTO;
import mx.gob.segob.nsjp.dto.objeto.SustanciaDTO;
import mx.gob.segob.nsjp.dto.objeto.TelefoniaDTO;
import mx.gob.segob.nsjp.dto.objeto.VegetalDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.actividad.ConsultarConfActividadDocumentoService;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.caso.form.IngresarIndividuoForm;
import mx.gob.segob.nsjp.web.expediente.form.NotaForm;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class AtencionTempranaPenalAction extends GenericAction{
	/* Log de clase*/
	private static final Logger log  = Logger.getLogger(AtencionTempranaPenalAction.class);
	
	@Autowired
	public TurnoDelegate turnoDelegate;
	
	@Autowired
	public ExpedienteDelegate expedienteDelegate;
	
	@Autowired
	public DocumentoDelegate documentoDelegate;
	
	@Autowired
	public InvolucradoDelegate involucradoDelegate;
	
	@Autowired
	public DelitoDelegate delitoDelegate;
	
	@Autowired
	public ObjetoDelegate objetoDelegate;
	
	@Autowired
	public ActividadDelegate actividadDelegate;
	
	@Autowired
	public AlarmaDelegate alarmaDelegate;
	
	@Autowired
	public AvisoDetencionDelegate avisoDetencionDelegate;
	
	@Autowired
	public ConsultarConfActividadDocumentoService confActividadDocumentoService;
	
	@Autowired
	public ConfActividadDocumentoDelegate confActividadDocumentoDelegate;
	
	@Autowired
	private CasoDelegate casoDelegate;
	
	@Autowired
	private AlmacenDelegate almacenDelegate;
	
	@Autowired
	private EslabonDelegate eslabonDelegate;
	
	@Autowired
	private BitacoraConsultaDelegate bitacoraConsultaDelegate;
	
	@Autowired
	private BitacoraMovimientoDelegate bitacoraMovimientoDelegate;
	
	@Autowired
	private CatalogoDelegate catalogoDelegate;
	
	@Autowired
	private SolicitudDelegate solicitudDelegate;
	
	@Autowired
	private RelacionDelegate relacionDelegate;
	
	@Autowired
	private SolicitudPericialDelegate solicitudPericialDelegate;
	
	@Autowired
	private FuncionarioDelegate funcionarioDelegate;
	
	@Autowired
	private UsuarioDelegate usuarioDelegate;
	
	@Autowired
	private AudienciaDelegate audienciaDelegate;
	
	@Autowired
	private RolDelegate rolDelegate;

	public ActionForward busquedaInicialTurnosGrid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaInicialTurnosGrid");
			try {
				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
//				usuarioDTO.setAreaActual(new AreaDTO(Areas.ATENCION_TEMPRANA_PG));
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaInicialTurnosGrid:#####"+turnoDelegate);
				List<TurnoDTO> listTurnoDTOs=turnoDelegate.consultarTurnosAtendidosPorUsuario(usuarioDTO,true);
				if (log.isDebugEnabled()) {
					log.debug("##################lista de Casos:::::::::" + listTurnoDTOs.size());
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
				for (TurnoDTO turnoDTO : listTurnoDTOs) {
					if(turnoDTO.getExpediente().getCasoDTO() !=  null) {
						log.debug(turnoDTO.getExpediente() + " este es el expediente baby");
						ExpedienteDTO expedienteDTO = turnoDTO.getExpediente();
						log.info("caso en expediente art" + expedienteDTO.getCasoDTO().getNumeroGeneralCaso());
						writer.print("<row id='" + expedienteDTO.getNumeroExpedienteId() + "'>");
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + expedienteDTO.getNumeroExpediente() + " </div]]></cell>");
						if (expedienteDTO.getOrigen() != null && expedienteDTO.getOrigen().getValor() != null) {
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + expedienteDTO.getOrigen().getValor() + " </div]]></cell>");
						} else {
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + "---" + " </div]]></cell>");
						}
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + expedienteDTO.getStrFechaApertura() + " </div]]></cell>");
						log.info("Este es el expediente con calidad de denunciante" + expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE));
						log.info("invol tama�o" + expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
						log.info("invol tama�o de" + expedienteDTO.getInvolucradosDTO().size());
						boolean op = true;
						for (InvolucradoDTO involucradoDTO : expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE)) {
							log.info("numero de involucrado nombre completo perdon:" + involucradoDTO.getNombreCompleto());
							if (involucradoDTO.getNombreCompleto() != null && !involucradoDTO.getNombreCompleto().equals("")) {
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + involucradoDTO.getNombreCompleto() + " </div]]></cell>");
							} else {
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + "An\u00f3nimo" + " </div]]></cell>");
							}

							op = false;

						}
						if (op) {
							boolean op2 = true;
							for (InvolucradoDTO involucradoDTO : expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE_ORGANIZACION)) {
								log.info("numero de involucrado nombre completo de organizacion:" + involucradoDTO.getOrganizacionDTO().getNombreOrganizacion());
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + involucradoDTO.getOrganizacionDTO().getNombreOrganizacion() + " </div]]></cell>");
								op2 = false;

							}
							if (op2)
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + "An&oacute;nimo" + " </div]]></cell>");
						}
						DelitoDTO delito = expedienteDTO.getDelitoPrincipal();
						if (delito != null) {
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + delito.getCatDelitoDTO().getNombre() + " </div]]></cell>");
						} else {
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + "Sin delito" + " </div]]></cell>");
						}

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
	
	/***
	 * Metodo para llenar la bandeja de expedientes compartidos en AtPenal
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward busquedaExpCompartidosFuncionarioLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaExpCompartidosFuncionarioLog");
			try {
				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
				List<TurnoDTO> listTurnoDTOs=turnoDelegate.consultarTurnosConPermisosFuncionario(usuarioDTO);
				if (log.isDebugEnabled()) {
					log.debug("##################lista de Casos Compartidos:::::::::" + listTurnoDTOs.size());
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
				for (TurnoDTO turnoDTO : listTurnoDTOs) {
					ExpedienteDTO expedienteDTO=turnoDTO.getExpediente();
					log.info("caso en expediente art" + expedienteDTO.getCasoDTO().getNumeroGeneralCaso());
					writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+"'>");
					if(expedienteDTO.getOrigen() != null && expedienteDTO.getOrigen().getValor() != null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getOrigen().getValor()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ "---" +" </div]]></cell>");
					}
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getNumeroExpediente()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getStrFechaApertura()+" </div]]></cell>");
					log.info("Este es el expediente con calidad de denunciante"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE));
					log.info("invol tama�o"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
					log.info("invol tama�o de"+expedienteDTO.getInvolucradosDTO().size());
					 boolean op=true;
					for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE)) {
						log.info("numero de involucrado nombre completo perdon:"+involucradoDTO.getNombreCompleto());
								if(involucradoDTO.getNombreCompleto()!= null && !involucradoDTO.getNombreCompleto().equals("")){
									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getNombreCompleto() +" </div]]></cell>");
								}else{
									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An�nimo"+" </div]]></cell>");
								}
								
								op=false;
						
					}
					if(op){
						boolean op2=true;
						for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE_ORGANIZACION)) {
							log.info("numero de involucrado nombre completo de organizacion:"+involucradoDTO.getOrganizacionDTO().getNombreOrganizacion());
									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getOrganizacionDTO().getNombreOrganizacion() +" </div]]></cell>");
									op2=false;
							
						}
						if(op2)
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An�nimo"+" </div]]></cell>");
					}
					DelitoDTO delito=expedienteDTO.getDelitoPrincipal();
					if(delito!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+delito.getCatDelitoDTO().getNombre()+" </div]]></cell>");
					}else{	
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Sin delito"+" </div]]></cell>");
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
	
	public ActionForward busquedaInicialTurnosGridRestaurativa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaInicialTurnosGridRestaurativa");
			try {
				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
//				usuarioDTO.setAreaActual(new AreaDTO(Areas.COORDINACION_ATENCION_TEMPRANA_PG));
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaInicialTurnosGrid:#####"+turnoDelegate);
				List<TurnoDTO> listTurnoDTOs=turnoDelegate.consultarTurnosAtendidosPorUsuario(usuarioDTO,true);
				if (log.isDebugEnabled()) {
					log.debug("##################lista de Casos:::::::::" + listTurnoDTOs.size());
				}
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=listTurnoDTOs.size();
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (TurnoDTO turnoDTO : listTurnoDTOs) {
					ExpedienteDTO expedienteDTO=turnoDTO.getExpediente();
					
					writer.print("<row id='"+expedienteDTO.getCasoDTO().getCasoId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getCasoDTO().getNumeroGeneralCaso()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getStrFechaApertura()+" </div]]></cell>");
					log.info("Este es el expediente con calidad de denunciante"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE));
					log.info("invol tama�o"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
					log.info("invol tama�o de"+expedienteDTO.getInvolucradosDTO().size());
					 boolean op=true;
					for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE)) {
						log.info("numero de involucrado nombre completo perdon:"+involucradoDTO.getNombreCompleto());
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getNombreCompleto() +" </div]]></cell>");
								op=false;
						
					}
					if(op){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An�nimo"+" </div]]></cell>");
					}
					DelitoDTO delito=expedienteDTO.getDelitoPrincipal();
					if(delito!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+delito.getCatDelitoDTO().getNombre()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Homicidio"+" </div]]></cell>");
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
	
	
	public ActionForward busquedaCanalizadosRestaurativa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaCanalizadosRestaurativa");
			try {
				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
				
				String area = request.getParameter("area");
				String actividad = request.getParameter("actividad");
				String bandera = request.getParameter("bandera");
				
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaCanalizadosRestaurativa:#####"+expedienteDelegate);
				FiltroExpedienteDTO filtroExpedienteDTO=new FiltroExpedienteDTO();
				filtroExpedienteDTO.setAnio(new Long(Calendar.getInstance().get(Calendar.YEAR)));
				
				//AREA PARA AMP JAR
				if(area.equals("JAR"))
				{
					filtroExpedienteDTO.setIdArea(new Long(Areas.ATENCION_TEMPRANA_PG_PENAL.ordinal()));
					if(actividad.equals("ATENDER_CANALIZACION"))
					{//facilitador
						filtroExpedienteDTO.setIdActividad(Actividades.ATENDER_CANALIZACION_JAR.getValorId());
						filtroExpedienteDTO.setIdArea(new Long(Areas.JUSTICIA_ALTERNATIVA_RESTAURATIVA.ordinal()));
//						filtroExpedienteDTO.setIdArea(new Long(Areas.ATENCION_TEMPRANA_PG_PENAL.ordinal()));
					}
					else
					{//coordinador
						filtroExpedienteDTO.setIdActividad(Actividades.RECIBIR_CANALIZACION_JAR.getValorId());
//						filtroExpedienteDTO.setIdArea(new Long(Areas.JUSTICIA_ALTERNATIVA_RESTAURATIVA.ordinal()));
						filtroExpedienteDTO.setIdArea(new Long(Areas.ATENCION_TEMPRANA_PG_PENAL.ordinal()));
					}
				}
				else if(area.equals("UI"))
				{
					filtroExpedienteDTO.setIdArea(new Long(Areas.ATENCION_TEMPRANA_PG_PENAL.ordinal()));
					if(actividad.equals("ATENDER_CANALIZACION"))
					{//agentemp
						filtroExpedienteDTO.setIdActividad(Actividades.ATENDER_CANALIZACION_UI.getValorId());
						filtroExpedienteDTO.setIdArea(new Long(Areas.UNIDAD_INVESTIGACION.ordinal()));
//						filtroExpedienteDTO.setIdArea(new Long(Areas.ATENCION_TEMPRANA_PG_PENAL.ordinal()));
					}
					else
					{//coordinador
						filtroExpedienteDTO.setIdActividad(Actividades.RECIBIR_CANALIZACION_UI.getValorId());
//						filtroExpedienteDTO.setIdArea(new Long(Areas.CORRDINACION_UNIDAD_INVESTIGACION.ordinal()));
						filtroExpedienteDTO.setIdArea(new Long(Areas.ATENCION_TEMPRANA_PG_PENAL.ordinal()));
//						filtroExpedienteDTO.setIdActividad(Actividades.RECIBIR_CANALIZACION_UI.getValorId());
					}
				}
				
				filtroExpedienteDTO.setUsuario(usuarioDTO);
				
				List<ExpedienteDTO> listaExpedienteDTOs=expedienteDelegate.consultarExpedienteActividadAreaAnio(filtroExpedienteDTO);
				if (log.isDebugEnabled()) {
					log.debug("##################lista de Casos:::::::::" + listaExpedienteDTOs.size());
				}
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				final PaginacionDTO pag = PaginacionThreadHolder.get();
								
				//vemos los valores del paginador
				log.debug("------------- DATOS PAGINACION ---------------");
				log.debug("pag_actual_a_mostrar :: " + pag.getPage());
				log.debug("pag_rows_a_mostrar :: " + pag.getRows());
				log.debug("pag_total_paginas :: " + pag.getTotalPaginas());
				log.debug("pag_total_registros :: " + pag.getTotalRegistros());
                log.debug("pag_from_to :: " + ((pag.getRows()*pag.getPage())- pag.getRows())+"  to  "+pag.getRows()*pag.getPage());
                log.debug("pag_total_registros_lista :: " + listaExpedienteDTOs.size());
                log.debug("------------- FIN DATOS PAGINACION ---------------");
                
                if (pag != null && pag.getTotalRegistros() != null) {
                    writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                    writer.print("<records>" + pag.getTotalRegistros() + "</records>");
                } else {
                    writer.print("<total>0</total>");
                    writer.print("<records>0</records>");
                }
				
				for (ExpedienteDTO expedienteDTO : listaExpedienteDTOs) {
					if(actividad.equals("ATENDER_CANALIZACION")){
						writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+"'>");
					}else{
						if(area.equals("JAR"))
						{
							writer.print("<row id='"+expedienteDTO.getExpedienteId()+"'>");
						}
						else
						{
							writer.print("<row id='"+expedienteDTO.getExpedienteId()+"'>");
						}
					}
					
//					log.debug("##################Entra en ATENDER_CANALIZACION_UI:::::::::"+expedienteDTO.getActividadActual().getTipoActividad().getValorId() );
//					if(expedienteDTO.getActividadActual().getTipoActividad().getValorId().equals(Actividades.ATENDER_CANALIZACION_UI.getValorId())){
//						log.debug("##################Entra en ATENDER_CANALIZACION_UI:::::::::" );
//						if(expedienteDTO.getNumExpHijos()!=null && expedienteDTO.getNumExpHijos().size()!=0){
//							log.debug("##################Entra en ATENDER_CANALIZACION_UI tama�o:::::::::"+expedienteDTO.getNumExpHijos().size() );
//							for (ExpedienteDTO expedienteDTOhijo : expedienteDTO.getNumExpHijos()) {
//								log.debug("##################Entra en ATENDER_CANALIZACION_UI numeros:::::::::"+expedienteDTOhijo.getNumeroExpediente() );
//								if(expedienteDTOhijo.getActividadActual().getTipoActividad().getValorId().equals(Actividades.ATENDER_CANALIZACION_UI.getValorId())){
//									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTOhijo.getNumeroExpediente()+" </div]]></cell>");
//									log.debug("##################Entra en ATENDER_CANALIZACION_UI pinta:::::::::"+expedienteDTOhijo.getNumeroExpediente() );
//									break;
//								}
//							}
//						}else{
//							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getNumeroExpediente()+" </div]]></cell>");
//						}
//					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getNumeroExpediente()+" </div]]></cell>");
//					}
					
					if(expedienteDTO.getOrigen() != null && expedienteDTO.getOrigen().getValor() != null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getOrigen().getValor()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ "---" +" </div]]></cell>");
					}
					
					if(expedienteDTO.getStrFechaApertura()==null)
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>01/07/11</div]]></cell>");
					}
					else
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getStrFechaApertura()+" </div]]></cell>");
					}
					log.info("Este es el expediente con calidad de denunciante"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE));
					log.info("invol tama�o"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
					log.info("invol tama�o de"+expedienteDTO.getInvolucradosDTO().size());
					 boolean op=true;
					for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE)) {
						log.info("numero de involucrado nombre completo perdon:"+involucradoDTO.getNombreCompleto());
						for (NombreDemograficoDTO nombreDemograficoDTO : involucradoDTO.getNombresDemograficoDTO()) {
							log.info("Verdadero nombre:"+nombreDemograficoDTO.getEsVerdadero());
							
							if(nombreDemograficoDTO.getEsVerdadero()!=null && nombreDemograficoDTO.getEsVerdadero()){
								//writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+nombreDemograficoDTO.getNombre()+" "+nombreDemograficoDTO.getApellidoPaterno()+" "+nombreDemograficoDTO.getApellidoMaterno() +" </div]]></cell>");
								if(involucradoDTO.getNombreCompleto()!= null && !involucradoDTO.getNombreCompleto().equals("")){
									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getNombreCompleto() +" </div]]></cell>");
								}else{
									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An�nimo"+" </div]]></cell>");
								}
								
								op=false;
							}
						}
						
					}
					if(op){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An�nimo"+" </div]]></cell>");
					}
					DelitoDTO delito=expedienteDTO.getDelitoPrincipal();
					if(delito!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+delito.getCatDelitoDTO().getNombre()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Sin Delito"+" </div]]></cell>");
					}
					if(expedienteDTO.getOrigen()!=null &&
					   expedienteDTO.getOrigen().getIdCampo()!=null &&
					   expedienteDTO.getOrigen().getIdCampo().equals(OrigenExpediente.QUERELLA.getValorId())){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Querrella"+" </div]]></cell>");
					}else
					if(expedienteDTO.getOrigen()!=null &&
						   expedienteDTO.getOrigen().getIdCampo()!=null &&
						   expedienteDTO.getOrigen().getIdCampo().equals(OrigenExpediente.DENUNCIA.getValorId())){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Denuncia"+" </div]]></cell>");
					}
					// Se hizo una clasificaci�n de expedientes por denuncias y querellas, de existir
					// más tipos de expedientes, se necesita descomponer el else
					else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Denuncia"+" </div]]></cell>");
					}
						
					log.info("etapa ex padre"+expedienteDTO.getEstatusExpedientePadre());
					if(expedienteDTO.getEstatusExpedientePadre()!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getEstatusExpedientePadre().getValor()+" </div]]></cell>");log.info("etapa ex padre"+expedienteDTO.getEstatusExpedientePadre());
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Desconocido"+" </div]]></cell>");
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
	
	
	/***
	 * M&eacute;todo para llenar la bandeja de expedientes compartidos para el usuario de agente mp
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward busquedaExpCompartidosFuncionarioLogAMP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaExpCompartidosFuncionarioLogAMP");
			try {
	
				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
				List<TurnoDTO> listTurnoDTOs=turnoDelegate.consultarTurnosConPermisosFuncionario(usuarioDTO);
				if (log.isDebugEnabled()) {
					log.debug("##################lista de Casos Compartidos:::::::::" + listTurnoDTOs.size());
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
				for (TurnoDTO turnoDTO : listTurnoDTOs) {
					ExpedienteDTO expedienteDTO=turnoDTO.getExpediente();
					log.info("caso en expediente art" + expedienteDTO.getCasoDTO().getNumeroGeneralCaso());
					writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getNumeroExpediente()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getStrFechaApertura()+" </div]]></cell>");
					log.info("Este es el expediente con calidad de denunciante"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE));
					log.info("invol tama�o"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
					log.info("invol tama�o de"+expedienteDTO.getInvolucradosDTO().size());
					 boolean op=true;
					for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE)) {
						log.info("numero de involucrado nombre completo perdon:"+involucradoDTO.getNombreCompleto());
								if(involucradoDTO.getNombreCompleto()!= null && !involucradoDTO.getNombreCompleto().equals("")){
									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getNombreCompleto() +" </div]]></cell>");
								}else{
									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An�nimo"+" </div]]></cell>");
								}
								
								op=false;
						
					}
					if(op){
						boolean op2=true;
						for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE_ORGANIZACION)) {
							log.info("numero de involucrado nombre completo de organizacion:"+involucradoDTO.getOrganizacionDTO().getNombreOrganizacion());
									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getOrganizacionDTO().getNombreOrganizacion() +" </div]]></cell>");
									op2=false;
							
						}
						if(op2)
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An�nimo"+" </div]]></cell>");
					}
					DelitoDTO delito=expedienteDTO.getDelitoPrincipal();
					if(delito!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+delito.getCatDelitoDTO().getNombre()+" </div]]></cell>");
					}else{	
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Sin delito"+" </div]]></cell>");
					}
					
					if(expedienteDTO.getOrigen()!=null &&
							   expedienteDTO.getOrigen().getIdCampo()!=null &&
							   expedienteDTO.getOrigen().getIdCampo().equals(OrigenExpediente.QUERELLA.getValorId())){
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Querrella"+" </div]]></cell>");
					}else
					if(expedienteDTO.getOrigen()!=null &&
								   expedienteDTO.getOrigen().getIdCampo()!=null &&
								   expedienteDTO.getOrigen().getIdCampo().equals(OrigenExpediente.DENUNCIA.getValorId())){
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Denuncia"+" </div]]></cell>");
					}
					// Se hizo una clasificaci�n de expedientes por denuncias y querellas, de existir
					// más tipos de expedientes, se necesita descomponer el else
					else{
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Denuncia"+" </div]]></cell>");
					}
						
					log.info("etapa ex padre"+expedienteDTO.getEstatusExpedientePadre());
					if(expedienteDTO.getEstatusExpedientePadre()!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getEstatusExpedientePadre().getValor()+" </div]]></cell>");log.info("etapa ex padre"+expedienteDTO.getEstatusExpedientePadre());
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Desconocido"+" </div]]></cell>");
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
	 * Metodo usado para cargar la consulta de remisiones para recibir los IPH
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	
	public ActionForward remisionesIPH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

			try {
				log.info("EJECUTANDO ACTION REMISIONES IPH ");
				
				List<ExpedienteDTO> listaExpedienteDTOs=expedienteDelegate.buscarRemisionesConIPH(EstatusExpediente.PENDIENTE_REVISION_COMO_IPH);
				if (log.isDebugEnabled()) {
					log.debug("##################LISTA DE EXPEDIENTES:::::::::" + listaExpedienteDTOs.size());
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
				
				for (ExpedienteDTO expedienteDTO : listaExpedienteDTOs) {
					
					writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+"'>");
					
				
					//NUMERO DE CASO
					if(expedienteDTO.getCasoDTO()==null || expedienteDTO.getCasoDTO().getNumeroGeneralCaso()==null)
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+" "+"</div]]></cell>");
					}
					else
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getCasoDTO().getNumeroGeneralCaso()+" </div]]></cell>");
					}
						
					//FECHA
					if(expedienteDTO.getStrFechaApertura()==null)
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+" "+"</div]]></cell>");
					}
					else
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getStrFechaApertura()+" </div]]></cell>");
					}
					
					
					//DENUNCIANTE
					log.info("Este es el expediente con calidad de denunciante"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE));
					log.info("invol tama�o"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
					log.info("invol tama�o de"+expedienteDTO.getInvolucradosDTO().size());
					 boolean op=true;
					for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE)) {
						log.info("numero de involucrado nombre completo perdon:"+involucradoDTO.getNombreCompleto());
						for (NombreDemograficoDTO nombreDemograficoDTO : involucradoDTO.getNombresDemograficoDTO()) {
							log.info("Verdadero nombre:"+nombreDemograficoDTO.getEsVerdadero());
							if(nombreDemograficoDTO.getEsVerdadero()!=null && nombreDemograficoDTO.getEsVerdadero()){
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+nombreDemograficoDTO.getNombre()+" "+nombreDemograficoDTO.getApellidoPaterno()+" "+nombreDemograficoDTO.getApellidoMaterno() +" </div]]></cell>");
								op=false;
							}
						}
						
					}
					if(op){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An�nimo"+" </div]]></cell>");
					}
					
					
					//DELITO
					DelitoDTO delito=expedienteDTO.getDelitoPrincipal();
					if(delito!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+delito.getCatDelitoDTO().getNombre()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Sin Delito"+" </div]]></cell>");
					}
					
					//ORIGEN
					if(expedienteDTO.getOrigen()!=null &&
							   expedienteDTO.getOrigen().getIdCampo()!=null &&
							   expedienteDTO.getOrigen().getIdCampo().equals(OrigenExpediente.QUERELLA.getValorId())){
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Querrella"+" </div]]></cell>");
					}else
					if(expedienteDTO.getOrigen()!=null &&
								   expedienteDTO.getOrigen().getIdCampo()!=null &&
								   expedienteDTO.getOrigen().getIdCampo().equals(OrigenExpediente.DENUNCIA.getValorId())){
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Denuncia"+" </div]]></cell>");
					}
					// Se hizo una clasificaci�n de expedientes por denuncias y querellas, de existir
					// más tipos de expedientes, se necesita descomponer el else
					else{
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Denuncia"+" </div]]></cell>");
					}
						
					//ESTATUS
					if(expedienteDTO.getEstatus()!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getEstatus().getValor()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Desconocido"+" </div]]></cell>");
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
	
	
	public ActionForward busquedaCanalizadosUnidadInvestigacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaCanalizadosRestaurativa");
			try {
				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaCanalizadosRestaurativa:#####"+expedienteDelegate);
				FiltroExpedienteDTO filtroExpedienteDTO=new FiltroExpedienteDTO();
				filtroExpedienteDTO.setAnio((long)2011);
				filtroExpedienteDTO.setIdArea(new Long(Areas.ATENCION_TEMPRANA_PG_PENAL.ordinal()));
				filtroExpedienteDTO.setIdActividad(Actividades.RECIBIR_CANALIZACION_UI.getValorId());
				filtroExpedienteDTO.setUsuario(usuarioDTO);
				List<ExpedienteDTO> listaExpedienteDTOs=expedienteDelegate.consultarExpedienteActividadAreaAnio(filtroExpedienteDTO);
				if (log.isDebugEnabled()) {
					log.debug("##################lista de Casos:::::::::" + listaExpedienteDTOs.size());
				}
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=listaExpedienteDTOs.size();
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (ExpedienteDTO expedienteDTO : listaExpedienteDTOs) {
					writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getNumeroExpediente()+" </div]]></cell>");
					
					if(expedienteDTO.getOrigen() != null && expedienteDTO.getOrigen().getValor() != null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getOrigen().getValor()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ "---" +" </div]]></cell>");
					}
					
					if(expedienteDTO.getStrFechaApertura()!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getStrFechaApertura()+" </div]]></cell>");
					}else {
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getStrFechaApertura()+" </div]]></cell>");
					}
					
					log.info("Este es el expediente con calidad de denunciante"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE));
					log.info("invol tama�o"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
					log.info("invol tama�o de"+expedienteDTO.getInvolucradosDTO().size());
					 boolean op=true;
					for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE)) {
						log.info("numero de involucrado nombre completo perdon:"+involucradoDTO.getNombreCompleto());
						for (NombreDemograficoDTO nombreDemograficoDTO : involucradoDTO.getNombresDemograficoDTO()) {
							log.info("Verdadero nombre:"+nombreDemograficoDTO.getEsVerdadero());
							if(nombreDemograficoDTO.getEsVerdadero()!=null && nombreDemograficoDTO.getEsVerdadero()){
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+nombreDemograficoDTO.getNombre()+" "+nombreDemograficoDTO.getApellidoPaterno()+" "+nombreDemograficoDTO.getApellidoMaterno() +" </div]]></cell>");
								op=false;
							}
						}
						
					}
					if(op){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An�nimo"+" </div]]></cell>");
					}
					DelitoDTO delito=expedienteDTO.getDelitoPrincipal();
					if(delito!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+delito.getCatDelitoDTO().getNombre()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Sin Delito"+" </div]]></cell>");
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
	
	
	//busquedaGenerarTurnosGrid
	public ActionForward busquedaSiguienteTurno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaSiguienteTurno");
			try {
				final TurnoDTO filtro = new TurnoDTO();
				filtro.setTipoTurno(TipoTurno.PENAL);
				
				//Para las agencias de PGJ
	            UsuarioDTO usuario = getUsuarioFirmado(request);
	            filtro.setUsuario(usuario);
	            
				TurnoDTO turnDTO=turnoDelegate.obtenerTurnoParaAtencion(filtro);
								
				converter.alias("turnoDTO", TurnoDTO.class);
				String xml = converter.toXML(turnDTO);
				log.info("TURNO:: "+xml);
				//mandamos la respuesta al cliente
				escribir(response, xml,null);	
		} catch (Exception e) {	
			log.info("TurnoSiguienteError");
			log.info(e.getCause(),e);
			escribir(response, "TurnoSiguienteError",null);
		}
		return null;
	}
	
	
	//Metodo Para cancelar el turno entrante o solicitado
	public ActionForward cancelarTurno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo cancelarTurno");
			try {
				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
				String turno=request.getParameter("turno");
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo cancelarTurno id de turno a cancelar:"+turno);
				final TurnoDTO turnoDTO = new TurnoDTO();
				turnoDTO.setTurnoId(Long.parseLong(turno));
				turnoDelegate.cancelarTurnoParaAtencion(turnoDTO,usuarioDTO);
				//mandamos la respuesta al cliente
				escribir(response, "Turno Cancelado",null);	
		} catch (Exception e) {	
			log.info("TurnoCanceladoError");
			log.info(e.getCause(),e);
			escribir(response, "TurnoCanceladoError",null);
		}
		return null;
	}
	
	
	//Busqueda de trnos para atencion administrativa
	public ActionForward busquedaSiguienteTurnoAministrativa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaSiguienteTurno");
			try {
				final TurnoDTO filtro = new TurnoDTO();
				filtro.setTipoTurno(TipoTurno.ADMINISTRATIVO);
				TurnoDTO turnDTO=turnoDelegate.obtenerTurnoParaAtencion(filtro);
								
				converter.alias("turnoDTO", TurnoDTO.class);
				String xml = converter.toXML(turnDTO);
				//mandamos la respuesta al cliente
				escribir(response, xml,null);	
		} catch (Exception e) {	
			log.info("TurnoSiguienteError");
			log.info(e.getCause(),e);
			escribir(response, "TurnoSiguienteError",null);
		}
		return null;
	}
	
	public ActionForward cancelarTurnosGrid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo cancelarTurnosGrid");
			try {
//				log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaInicialTurnosGrid:#####"+turnoDelegate);
//				List<CasoDTO> listaCasos = casoDelegate.consultarCasosPorUsuario(usuarioDTO);
				if (log.isDebugEnabled()) {
//					log.debug("##################lista de Casos:::::::::" + listaCasos.size());
				}
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				//int lTotalRegistros=listaCasos.size();
				int lTotalRegistros=5;
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (int i = 0; i < 6; i++) {
					writer.print("<row id='"+i+"'>");
					
					if(i==0){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#FFA500;'>"+"00"+i+" </div]]></cell>");
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#FFA500;'>"+"13:30 hrs"+" </div]]></cell>");
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#FFA500;'>"+"Javier Hern�ndez"+" </div]]></cell>");
					}
					
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"00"+i+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"13:30 hrs"+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Javier Hern�ndez"+" </div]]></cell>");
					writer.print("</row>");	
				}
				
//				for (CasoDTO casoDTO : listaCasos) {
//					writer.print("<row id='"+casoDTO.getCasoId()+"'>");
//					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + casoDTO.getNumeroGeneralCaso()+ " </div]]></cell>");
//					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + "13/05/2011"+ " </div]]></cell>");
////					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + casoDTO.getFechaApertura()+ " </div]]></cell>");
//					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + "Arturo Le�n Galicia"+ " </div]]></cell>");
////					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + casoDTO.getImputado()+ " </div]]></cell>");
////					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + casoDTO.getDelito()+ " </div]]></cell>");
//					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + "Violaci�n"+ " </div]]></cell>");
//					writer.print("</row>");						
//				}
			
			
			writer.print("</rows>");
			writer.flush();
			writer.close();
		
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	public ActionForward busquedaInicialExpedientesGrid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaInicialExpedientesGrid");
			try {
				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaInicialExpedientesGrid:#####"+turnoDelegate);
//				List<CasoDTO> listaCasos = casoDelegate.consultarCasosPorUsuario(usuarioDTO);
				if (log.isDebugEnabled()) {
//					log.debug("##################lista de Casos:::::::::" + listaCasos.size());
				}
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				//int lTotalRegistros=listaCasos.size();
				int lTotalRegistros=4;
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (int i = 0; i < 5; i++) {
					writer.print("<row id='"+i+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"XX00"+i+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"25/05/11"+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Javier Hern�ndez"+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Narcotraficante"+" </div]]></cell>");
					writer.print("</row>");	
				}
				
//				for (CasoDTO casoDTO : listaCasos) {
//					writer.print("<row id='"+casoDTO.getCasoId()+"'>");
//					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + casoDTO.getNumeroGeneralCaso()+ " </div]]></cell>");
//					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + "13/05/2011"+ " </div]]></cell>");
////					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + casoDTO.getFechaApertura()+ " </div]]></cell>");
//					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + "Arturo Le�n Galicia"+ " </div]]></cell>");
////					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + casoDTO.getImputado()+ " </div]]></cell>");
////					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + casoDTO.getDelito()+ " </div]]></cell>");
//					writer.print("<cell> <![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>" + "Violaci�n"+ " </div]]></cell>");
//					writer.print("</row>");						
//				}
			
			
			writer.print("</rows>");
			writer.flush();
			writer.close();
		
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	public ActionForward busquedaExpedientesMesGrid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaInicialExpedientesGrid");
			try {
//				log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaInicialExpedientesGrid:#####"+turnoDelegate);
//				List<CasoDTO> listaCasos = casoDelegate.consultarCasosPorUsuario(usuarioDTO);
				if (log.isDebugEnabled()) {
//					log.debug("##################lista de Casos:::::::::" + listaCasos.size());
				}
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				//int lTotalRegistros=listaCasos.size();
				int lTotalRegistros=4;
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (int i = 0; i < 9; i++) {
					writer.print("<row id='"+i+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"XX00"+i+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"25/01/11"+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Chicharito Hern�ndez"+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Pateador Artero"+" </div]]></cell>");
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
	
	
	public ActionForward consultarExpedientes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			log.info("ejecutando Action AtencionTempranaPenalAction en el metodo consultarExpedientes");
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			String idNumeroExpediente=request.getParameter("idNumeroExpediente");
			log.info("#$%%$$##$%%$# idNumeroExpediente el id es: "+idNumeroExpediente);
			if(idNumeroExpediente != null){
				expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
			}
			expedienteDTO = expedienteDelegate.obtenerExpediente(expedienteDTO);
			expedienteDTO.setConsulta(true);
			CasoDTO casoDTO = new CasoDTO();
//			request.setAttribute("numExpConsul", expedienteDTO.getNumeroExpediente());
			log.info("$$$$ numero el Expediente $$$ : "+expedienteDTO.getNumeroExpediente());
			log.info("$$$$ numero del caso  $$$ : "+expedienteDTO.getCasoDTO());
			request.getSession().removeAttribute("numExpConsul");
			request.getSession().setAttribute("numExpConsul", expedienteDTO.getNumeroExpediente());
			request.getSession().setAttribute("idExpedienteConsul", expedienteDTO.getNumeroExpedienteId());
			request.getSession().setAttribute("idExpedienteConsulop", expedienteDTO.getExpedienteId());
			request.getSession().setAttribute("numeroCasoConsul", expedienteDTO.getCasoDTO().getNumeroGeneralCaso());
			Boolean banderaFac = Boolean.FALSE;
			log.info(":::::: Actividad deacuerdo al id del Expediente::::");
			if(expedienteDTO.getExpedienteId()!=null || expedienteDTO.getExpedienteId().equals("")){
				ActividadDTO respActividad = actividadDelegate.obtenerActPorExpTipoAct(expedienteDTO.getExpedienteId(), Actividades.ATENDER_CANALIZACION_JAR);
				if (respActividad!=null && respActividad.getActividadId()!=null) {
					banderaFac = Boolean.TRUE;					
					request.getSession().setAttribute("banderaFac", banderaFac);
				}
			}
			
			log.info("area_exp:: "+expedienteDTO.getArea().getAreaId());
			//asignamos la pantalla solicitada
			if(expedienteDTO.getArea().getAreaId().longValue()==Areas.ATENCION_TEMPRANA_PG_PENAL.parseLong())
			{
				log.info("area_enum:: "+Areas.ATENCION_TEMPRANA_PG_PENAL.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 1);
			}
			else if(expedienteDTO.getArea().getAreaId().longValue()==Areas.JUSTICIA_ALTERNATIVA_RESTAURATIVA.parseLong())
			{
				log.info("area_enum:: "+Areas.JUSTICIA_ALTERNATIVA_RESTAURATIVA.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 2);
			}
			else if(expedienteDTO.getArea().getAreaId().longValue()==Areas.UNIDAD_INVESTIGACION.parseLong())
			{
				log.info("area_enum:: "+Areas.UNIDAD_INVESTIGACION.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 3);
			}
			else if(expedienteDTO.getArea().getAreaId().longValue()==Areas.CORRDINACION_UNIDAD_INVESTIGACION.parseLong())
			{
				log.info("area_enum:: "+Areas.UNIDAD_INVESTIGACION.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 4);
			}
			else if(expedienteDTO.getArea().getAreaId().longValue()==Areas.FISCAL_FACILITADOR.parseLong())
			{
				log.info("area_enum:: "+Areas.FISCAL_FACILITADOR.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 5);
			}
			else if(expedienteDTO.getArea().getAreaId().longValue()==Areas.POLICIA_MINISTERIAL.parseLong())
			{
				log.info("area_enum:: "+Areas.POLICIA_MINISTERIAL.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 6);
			}
			else if(expedienteDTO.getArea().getAreaId().longValue()==Areas.COORDINACION_VISITADURIA.parseLong())
			{
				log.info("area_enum:: "+Areas.COORDINACION_VISITADURIA.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 7L);
			}
			else if(expedienteDTO.getArea().getAreaId().longValue()==Areas.VISITADURIA.parseLong())
			{
				log.info("area_enum:: "+Areas.VISITADURIA.parseLong());
				request.getSession().setAttribute("pantallaSolicitada", 8);
			}
			
			request.getSession().setAttribute("numeroCasoConsul", expedienteDTO.getCasoDTO().getNumeroGeneralCaso());
			//request.setAttribute("numeroExpediente",  expedienteDTO.getNumeroExpediente());
			//request.setAttribute("idNumeroExpedienteop",  expedienteDTO.getNumeroExpedienteId());
			//request.setAttribute("idExpedienteop",  expedienteDTO.getExpedienteId());
			
			
//			request.getSession().setAttribute("casoId", expedienteDTO.getCasoDTO().getCasoId());
			
			
			super.setExpedienteTrabajo(request, expedienteDTO);
			log.info("$$$$ el Expediente $$$ : "+expedienteDTO);
			

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return mapping.findForward("succes");
	}
	
	public ActionForward consultarInvolucradosExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idNumeroExpediente=request.getParameter("idNumeroExpediente");
			log.info("$$$$ numero DE Expediente consultar involucrados $$$ : "+idNumeroExpediente);

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
			expedienteDTO.setInvolucradosSolicitados(true);
			expedienteDTO = expedienteDelegate.obtenerExpediente(expedienteDTO);

			List<InvolucradoViewDTO> listaInvolucrados=new ArrayList<InvolucradoViewDTO>();

			for(int i=0;i<expedienteDTO.getInvolucradosDTO().size(); i++){
				InvolucradoDTO involucrado = expedienteDTO.getInvolucradosDTO().get(i);
				InvolucradoViewDTO involucradoView = new InvolucradoViewDTO();
				log.info("$$$$ Calidad a pintar del involucrado : "+involucrado.getCalidadDTO().getValorIdCalidad().getIdCampo());
				involucradoView.setCalidad((involucrado.getCalidadDTO().getValorIdCalidad().getIdCampo()).toString());
				log.info("&&&& Condicion del invoolucrado:"+involucrado.getCondicion());
				log.info("&&&& Condicion del invoolucrado:"+involucrado.getCalidadDTO().getValorIdCalidad().getIdCampo());
					if(involucrado.getCondicion()!= null && involucrado.getCondicion()==(short)1){
						log.info("#################################33333");
						involucradoView.setEsVictima(true);
					}
				
				log.info("$$$$ id a pintar del involucrado : "+involucrado.getElementoId());
				involucradoView.setInvolucradoId(involucrado.getElementoId());
				if(involucrado.getTipoPersona().equals(0L) && !(involucrado.getCalidadDTO().getValorIdCalidad().getIdCampo().equals(Calidades.TRADUCTOR.getValorId()))){
					//log.info("$$$$ nombre de la organizaci�n a pintar del involucrado : "+involucrado.getOrganizacionDTO().getNombreOrganizacion());
					involucradoView.setNombre(((involucrado.getOrganizacionDTO()!=null && involucrado.getOrganizacionDTO().getNombreOrganizacion()!=null)?involucrado.getOrganizacionDTO().getNombreOrganizacion():""));
				}else{
					if(involucrado.getCalidadDTO().getValorIdCalidad().getIdCampo().equals(Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId())){
						involucradoView.setNombre(involucrado.getNombreCompleto()+" - "+involucrado.getValorSituacionJuridica().getValor());
						log.info("PROBABLE RESPONSABLE::::: "+involucradoView.getNombre());
					}else{
						involucradoView.setNombre(involucrado.getNombreCompleto());
					}
					log.info("PROBABLE RESPONSABLE::::: "+involucrado.getCalidadDTO().getValorIdCalidad().getIdCampo()+"::::"+Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId());
					log.info("PROBABLE RESPONSABLE::::: "+involucradoView.getNombre());
				}
				listaInvolucrados.add(involucradoView);
			}
			log.info("$$$$ numero el Expediente consultar involucrados  listaInvolucrados.size()$$$ : "+listaInvolucrados.size());
			
			converter.alias("listaInvolucrados", java.util.List.class);
			converter.alias("involucradoViewDTO", InvolucradoViewDTO.class);
			
			String xml = converter.toXML(listaInvolucrados);

//			if(expedienteDTO.getDocumentosDTO() != null && !expedienteDTO.getDocumentosDTO().isEmpty()){
//				List<DocumentoDTO> listaDocumentoDTOs=new ArrayList<DocumentoDTO>();
//				listaDocumentoDTOs=expedienteDTO.getDocumentosDTO();
//
//				converter.alias("listaDocumentoDTOs", java.util.List.class);
//				converter.alias("documentoDTO", DocumentoDTO.class);
//				xml += converter.toXML(listaDocumentoDTOs);
//				
//			}
    		
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			pw.print(xml);
			pw.flush();
			pw.close();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	
	
	
	/**
	 * M&eacute;todo utilizado para realizar la carga del combo Actuaciones
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward cargarActuaciones(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.debug("EJECUTANDO CARGAR ACTUACIONES..................");
			
			String idCategoria=request.getParameter("idCategoria");
			log.debug("ID DE LA CATEGORIA="+idCategoria);
		
			String numeroExpediente = request.getParameter("numeroExpediente");
			Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"),0);
			log.debug("ejecutando MetodoAction cargarActuaciones numeroExpediente ###"+numeroExpediente);
			//Codigo que implementa la funcionalidad de sub rol y filtra sus actuaciones
			UsuarioDTO usuarioDTO=super.getUsuarioFirmado(request);
			RolDTO rolDTO=null;
			List<ConfActividadDocumentoDTO> listConfActividadDocumentoDTOs=new ArrayList<ConfActividadDocumentoDTO>();
			if(usuarioDTO.getRolACtivo()!=null && usuarioDTO.getRolACtivo().getRol()!=null && usuarioDTO.getRolACtivo().getRol().getRolPadre()!=null){
				rolDTO=usuarioDTO.getRolACtivo().getRol();
				rolDTO=rolDelegate.consultarRol(rolDTO);
				if(rolDTO.getConfActividadDocumentoDTO()!=null && rolDTO.getConfActividadDocumentoDTO().size()>0){
					listConfActividadDocumentoDTOs=rolDTO.getConfActividadDocumentoDTO();
                    log.debug("Devuelve_el_numero_total_de_actuaciones: "+rolDTO.getConfActividadDocumentoDTO().size());
				}
			}
			//fin
			ExpedienteDTO expedienteDto=super.getExpedienteTrabajo(request, numeroExpediente);
			if(expedienteDto ==null){
				expedienteDto = new ExpedienteDTO();
			}
			
			if(expedienteDto.getNumeroExpedienteId() == null ||
			  (expedienteDto.getNumeroExpedienteId() != null && numeroExpedienteId.longValue()>0 &&
			   expedienteDto.getNumeroExpedienteId().longValue() != numeroExpedienteId.longValue())){
				expedienteDto.setNumeroExpedienteId(numeroExpedienteId);
			}
			log.debug("ejecutando MetodoAction cargarActuaciones ExpedienteDTO"+expedienteDto);
			UsuarioDTO usuario=super.getUsuarioFirmado(request);
			List<CatalogoDTO> listaCatalogo =new ArrayList<CatalogoDTO>();
			
			List<ConfActividadDocumentoDTO> listActividadDocumentoDTOs =new ArrayList<ConfActividadDocumentoDTO>();
			if(idCategoria==null){
				log.debug("ENTRA A ID DE LA CATEGORIA NULA:::::::idCategoria="+idCategoria);
				listActividadDocumentoDTOs=confActividadDocumentoDelegate.consultarConfActividadDocumento(usuario, expedienteDto, null);
                log.debug(listActividadDocumentoDTOs+"Richardo");
			}
			else{
				log.debug("ENTRA A ID DE LA CATEGORIA NO NULA::::idCategoria="+idCategoria);
				listActividadDocumentoDTOs=confActividadDocumentoDelegate.consultarConfActividadDocumento(usuario, expedienteDto, Long.parseLong(idCategoria));

			}
			log.debug("ejecutando MetodoAction cargarActuaciones lista respuesta tama�o"+listActividadDocumentoDTOs.size());
			log.debug("ejecutando MetodoAction cargarActuaciones lista respuesta "+listActividadDocumentoDTOs);
			listaCatalogo=new ArrayList<CatalogoDTO>();
			//Codigo que implementa la funcionalidad de sub rol y filtra sus actuaciones
			if(rolDTO!=null && listConfActividadDocumentoDTOs!=null && listConfActividadDocumentoDTOs.size()>0){
				for (ConfActividadDocumentoDTO confActividadDocumentoDTO : listActividadDocumentoDTOs) {
					CatalogoDTO catalogo=new CatalogoDTO();
					for (ConfActividadDocumentoDTO confActividadDocumentoDTO2 : listConfActividadDocumentoDTOs) {
						if(confActividadDocumentoDTO.getConfActividadDocumentoId().compareTo(confActividadDocumentoDTO2.getConfActividadDocumentoId())==0){
							catalogo.setClave(confActividadDocumentoDTO.getConfActividadDocumentoId());
                            log.debug(confActividadDocumentoDTO.getConfActividadDocumentoId()+"idActividadDocumentoRichardo");
							catalogo.setValor(confActividadDocumentoDTO.getNombreActividad());
							listaCatalogo.add(catalogo);
							log.debug("ejecutando MetodoAction cargarActuaciones actuaciones de BD:"+confActividadDocumentoDTO.getNombreActividad());
						}
					}
					
				}
			}else{
				for (ConfActividadDocumentoDTO confActividadDocumentoDTO : listActividadDocumentoDTOs) {
					CatalogoDTO catalogo=new CatalogoDTO();
					catalogo.setClave(confActividadDocumentoDTO.getConfActividadDocumentoId());
                    log.debug(confActividadDocumentoDTO.getConfActividadDocumentoId()+"idActividadDocumentoRichi");
                    //catalogo.setClave(Long.parseLong(""));
					catalogo.setValor(confActividadDocumentoDTO.getNombreActividad());
					listaCatalogo.add(catalogo);
					log.debug("ejecutando MetodoAction cargarActuaciones actuaciones de BD:"+confActividadDocumentoDTO.getNombreActividad());
				}

			}
			//Fin
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catActuaciones", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * funcion para mandar a la vista el XML con la informacion de los objetos del expediente
	 * que se desea consultar
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarObjetosExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idNumeroExpediente=request.getParameter("idNumeroExpediente");
			
			log.info("$$$$ ID de Expediente consultar Objetos $$$ : "+idNumeroExpediente);

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
			expedienteDTO.setObjetosSolicitados(true);
			//consultamos el expediente
			expedienteDTO = expedienteDelegate.obtenerExpediente(expedienteDTO); 
			
			StringBuffer sb= new StringBuffer();
			sb.append("<ObjetosDTO>");
			String xml = "";
			List<ObjetoDTO> listaObjetoDTOs=new ArrayList<ObjetoDTO>();
			
			//recuperamos los objetos Equipo de Computo del DTO
			/*listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.EQUIPO_COMPUTO);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("EquipoComputoDTO",EquipoComputoDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);*/
			
			//recuperamos los objetos Equipo de Computo del DTO
			listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.EQUIPO_TELEFONICO);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("TelefoniaDTO",TelefoniaDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);
			
			//recuperamos los objetos Arma del DTO
			/*listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.ARMA);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("ArmaDTO",ArmaDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);*/
			
			//recuperamos los objetos Explosivos del DTO
			listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.EXPLOSIVO);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("ExplosivoDTO",ExplosivoDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);
			
			//recuperamos los objetos Sustancia del DTO
			listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.SUSTANCIA);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("SustanciaDTO",SustanciaDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);
			
			//recuperamos los objetos Animal del DTO
			listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.ANIMAL);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("AnimalDTO",AnimalDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);
			
			//recuperamos los objetos Vehiculo del DTO
			/*listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.VEHICULO);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("VehiculoDTO", VehiculoDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);*/
			
			
			//recuperamos los objetos Aeronave del DTO
			listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.AERONAVE);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("AeronaveDTO",AeronaveDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);
			
			//recuperamos los objetos Embarcacion del DTO
			listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.EMBARCACION);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("EmbarcacionDTO",EmbarcacionDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);
			
			//recuperamos los objetos Numerario del DTO
			listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.NUMERARIO);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("NumerarioDTO",NumerarioDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);
			
			//recuperamos los objetos Vegetal del DTO
			listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.VEGETAL);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("VegetalDTO",VegetalDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);
			
			//recuperamos los objetos Doc Oficial del DTO
			listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.DOCUMENTO_OFICIAL);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("DocumentoOficialDTO",DocumentoOficialDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);
			
			//recuperamos los objetos Joya del DTO
			listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.JOYA);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("JoyaDTO",JoyaDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);
			
			//recuperamos los objetos Obra Arte del DTO
			listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.OBRA_DE_ARTE);
			converter.alias("listaObjetosDTO", java.util.List.class);
			converter.alias("ObraArteDTO",ObraArteDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);
			
			//recuperamos los objetos Obra Arte del DTO
			listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.OTRO);
			converter.alias("OtrosDTO", java.util.List.class);
			converter.alias("OtroDTO",ObjetoDTO.class);
			xml = converter.toXML(listaObjetoDTOs);
			sb.append(xml);
			
			sb.append("</ObjetosDTO>");
			
			if(log.isDebugEnabled())
			{
				log.info("lista_objetos_carga_exp:: "+sb.toString());
			}
			escribirRespuesta(response, sb.toString());
		} catch (Exception e) {
			log.error("ERROR_lista_otros_carga_exp:: "+e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * funcion para mandar a la vista el XML con la informacion del hecho del expediente
	 * que se desea consultar
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarHechoExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idNumeroExpediente=request.getParameter("idNumeroExpediente");
			
			log.info("$$$$ ID de Expediente consultar hecho $$$ : "+idNumeroExpediente);

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
			expedienteDTO.setHechoSolicitado(true);
			expedienteDTO = expedienteDelegate.obtenerExpediente(expedienteDTO); 
			HechoDTO hechoDTO=expedienteDTO.getHechoDTO();
			if(expedienteDTO!=null && expedienteDTO.getOrigen()!=null){
				hechoDTO.getExpediente().setOrigen(expedienteDTO.getOrigen());
			}
			
			if(expedienteDTO !=null && hechoDTO!= null && hechoDTO.getLugar() !=null){
				if(hechoDTO.getLugar().getLatitud()!=null && !hechoDTO.getLugar().getLatitud().equals("")){
					String latitud=hechoDTO.getLugar().getLatitud();
					
					String subLatitud=latitud.substring(1,latitud.length());//quitamos la letra de la cadena
					String[] arr=subLatitud.split("�");//separlo los grados de los minutos y segundos
					String[] arrDos=arr[1].split("'");//separamos los minutos y segundos
					String segundos=arrDos[1].substring(0,arrDos[1].length()-1);
					//seteamos los valores
					hechoDTO.getLugar().setLatitudN(latitud.substring(0,1));log.info("domicilio hechoDTO NO NULL!!! 1");
					hechoDTO.getLugar().setLatitudGrados(arr[0]);log.info("domicilio hechoDTO NO NULL!!! 2");
					hechoDTO.getLugar().setLatitudMinutos(arrDos[0]);log.info("domicilio hechoDTO NO NULL!!! 3");
					hechoDTO.getLugar().setLatitudSegundos(segundos);log.info("domicilio hechoDTO NO NULL!!! 4");

				}
				if(hechoDTO.getLugar().getLongitud()!=null && !hechoDTO.getLugar().getLongitud().equals("")){
					String longitud=hechoDTO.getLugar().getLongitud();
					
					String subLongitud=longitud.substring(1,longitud.length());//quitamos la letra de la cadena
					String[] arr=subLongitud.split("�");//separlo los grados de los minutos y segundos
					String[] arrDos=arr[1].split("'");//separamos los minutos y segundos
					String segundos=arrDos[1].substring(0,arrDos[1].length()-1);
					
					hechoDTO.getLugar().setLongitudE(longitud.substring(0,1));log.info("domicilio hechoDTO NO NULL!!! 5");
					hechoDTO.getLugar().setLongitudGrados(arr[0]);log.info("domicilio hechoDTO NO NULL!!! 6");
					hechoDTO.getLugar().setLongitudMinutos(arrDos[0]);log.info("domicilio hechoDTO NO NULL!!! 7");
					hechoDTO.getLugar().setLongitudSegundos(segundos);log.info("domicilio hechoDTO NO NULL!!! 8");
				}log.info("domicilio hechoDTO (2) NO NULL!!!");
			}
			
			converter.alias("hechoDTO", HechoDTO.class);
			String xml = converter.toXML(hechoDTO);
			if(log.isDebugEnabled())
			{
				log.info("hechoDTO_exp::"+xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * funcion para consultar los datos generales del visor de elementos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarDatosGenerales(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idNumeroExpediente=request.getParameter("idNumeroExpedienteOp");
			log.info("$$$$ ID de Expediente consultarDatosGenerales $$$ : "+idNumeroExpediente);

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
			DatosGeneralesExpedienteDTO datosGeneralesExpedienteDTO = expedienteDelegate.obtenerDatosGeneralesDeExpediente(expedienteDTO); 
			if(!(datosGeneralesExpedienteDTO.getEstatusNumeroExpediente()!= null) && datosGeneralesExpedienteDTO.getEstatusNumeroExpediente().equals("")){
				datosGeneralesExpedienteDTO.setEstatusNumeroExpediente("Abierto");
			}
			log.info("$$$"+datosGeneralesExpedienteDTO.getEsDesconocido()+"$$$");
			if(datosGeneralesExpedienteDTO.getEsDesconocido().equals("true")){
				datosGeneralesExpedienteDTO.setEsDesconocido("El denunciante es An�nimo: Si");
			}else{
				datosGeneralesExpedienteDTO.setEsDesconocido("El denunciante es An�nimo: No");
			}
			List<String> vehiculos=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.VEHICULO.getValorId());
			if(vehiculos!=null && vehiculos.size()!=0){
				datosGeneralesExpedienteDTO.setVehiculos(vehiculos.get(0));
				if(vehiculos.size()>1){
					datosGeneralesExpedienteDTO.setVe("1");
				}
			}
			List<String> equipoComputo=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.EQUIPO_DE_COMPUTO.getValorId());
			if(equipoComputo!=null && equipoComputo.size()!=0){
				datosGeneralesExpedienteDTO.setEquiposComputo(equipoComputo.get(0));
				if(equipoComputo.size()>1){
					datosGeneralesExpedienteDTO.setEquiCom("1");
				}
			}
			List<String> equipoTelefonico=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.EQUIPO_TELEFONICO.getValorId());
			if(equipoTelefonico!=null && equipoTelefonico.size()!=0){
				datosGeneralesExpedienteDTO.setEquiposTelefonicos(equipoTelefonico.get(0));
				if(equipoTelefonico.size()>1){
					datosGeneralesExpedienteDTO.setEquiTel("1");
				}
			}
			List<String> arma=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.ARMA.getValorId());
			if(arma!=null && arma.size()!=0){
				datosGeneralesExpedienteDTO.setArmas(arma.get(0));
				if(arma.size()>1){
					datosGeneralesExpedienteDTO.setArm("1");
				}
			}
			List<String> explosivo=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.EXPLOSIVO.getValorId());
			if(explosivo!=null && explosivo.size()!=0){
				datosGeneralesExpedienteDTO.setExplosivos(explosivo.get(0));
				if(explosivo.size()>1){
					datosGeneralesExpedienteDTO.setExpl("1");
				}
			}
			List<String> sustancias=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.SUSTANCIA.getValorId());
			if(sustancias!=null && sustancias.size()!=0){
				datosGeneralesExpedienteDTO.setSustancias(sustancias.get(0));
				if(sustancias.size()>1){
					datosGeneralesExpedienteDTO.setSus("1");
				}
			}
			List<String> animales=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.ANIMAL.getValorId());
			if(animales!=null && animales.size()!=0){
				datosGeneralesExpedienteDTO.setAnimales(animales.get(0));
				if(animales.size()>1){
					datosGeneralesExpedienteDTO.setAnim("1");
				}
			}
			List<String> aeronaves=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.AERONAVE.getValorId());
			if(aeronaves!=null && aeronaves.size()!=0){
				datosGeneralesExpedienteDTO.setAeronaves(aeronaves.get(0));
				if(aeronaves.size()>1){
					datosGeneralesExpedienteDTO.setAero("1");
				}
			}
			List<String> envarcacion=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.EMBARCACION.getValorId());
			if(envarcacion!=null && envarcacion.size()!=0){
				datosGeneralesExpedienteDTO.setEmbarcaciones(envarcacion.get(0));
				if(envarcacion.size()>1){
					datosGeneralesExpedienteDTO.setEmbar("1");
				}
			}
			List<String> inmueble=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.INMUEBLE.getValorId());
			if(inmueble!=null && inmueble.size()!=0){
				datosGeneralesExpedienteDTO.setInmuelbes(inmueble.get(0));
				if(inmueble.size()>1){
					datosGeneralesExpedienteDTO.setInmu("1");
				}
			}
			List<String> numerario=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.NUMERARIO.getValorId());
			if(numerario!=null && numerario.size()!=0){
				datosGeneralesExpedienteDTO.setNumerarios(numerario.get(0));
				if(numerario.size()>1){
					datosGeneralesExpedienteDTO.setNume("1");
				}
			}
			List<String> vegetal=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.VEGETAL.getValorId());
			if(vegetal!=null && vegetal.size()!=0){
				datosGeneralesExpedienteDTO.setVegetales(vegetal.get(0));
				if(vegetal.size()>1){
					datosGeneralesExpedienteDTO.setVege("1");
				}
			}
			List<String> docOficial=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.DOCUMENTO_OFICIAL.getValorId());
			if(docOficial!=null && docOficial.size()!=0){
				datosGeneralesExpedienteDTO.setDocumentosOficiales(docOficial.get(0));
				if(docOficial.size()>1){
					datosGeneralesExpedienteDTO.setDocuOfi("1");
				}
			}
			List<String> joya=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.JOYA.getValorId());
			if(joya!=null && joya.size()!=0){
				datosGeneralesExpedienteDTO.setJoyas(joya.get(0));
				if(joya.size()>1){
					datosGeneralesExpedienteDTO.setJoy("1");
				}
			}
			List<String> obraArte=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.OBRA_DE_ARTE.getValorId());
			if(obraArte!=null && obraArte.size()!=0){
				datosGeneralesExpedienteDTO.setObrasDeArte(obraArte.get(0));
				if(obraArte.size()>1){
					datosGeneralesExpedienteDTO.setObrArte("1");
				}
			}
			List<String> otro=datosGeneralesExpedienteDTO.getObjetos().get(Objetos.OTRO.getValorId());
			if(otro!=null && otro.size()!=0){
				datosGeneralesExpedienteDTO.setOtrosObjestos(otro.get(0));
				if(otro.size()>1){
					datosGeneralesExpedienteDTO.setOtrObj("1");
				}
			}
			List<String> denunciante=datosGeneralesExpedienteDTO.getInvolucrados().get(Calidades.DENUNCIANTE.getValorId());
			if(denunciante!=null && denunciante.size()!=0){
				datosGeneralesExpedienteDTO.setDenunciantes(denunciante.get(0));
				if(denunciante.size()>1){
					datosGeneralesExpedienteDTO.setDenun("1");
				}
			}
			List<String> victimas=datosGeneralesExpedienteDTO.getInvolucrados().get(Calidades.VICTIMA_PERSONA.getValorId());
			if(victimas!=null && victimas.size()!=0){
				datosGeneralesExpedienteDTO.setVictimas(victimas.get(0));
				if(victimas.size()>1){
					datosGeneralesExpedienteDTO.setVic("1");
				}
			}
			List<String> proResponsable=datosGeneralesExpedienteDTO.getInvolucrados().get(Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId());
			if(proResponsable!=null && proResponsable.size()!=0){
				datosGeneralesExpedienteDTO.setProbablesResposables(proResponsable.get(0));
				if(proResponsable.size()>1){
					datosGeneralesExpedienteDTO.setProba("1");
				}
			}
			List<String> testigo=datosGeneralesExpedienteDTO.getInvolucrados().get(Calidades.TESTIGO.getValorId());
			if(testigo!=null && testigo.size()!=0){
				datosGeneralesExpedienteDTO.setTestigos(testigo.get(0));
				if(testigo.size()>1){
					datosGeneralesExpedienteDTO.setTest("1");
				}
			}
			List<String> traductor=datosGeneralesExpedienteDTO.getInvolucrados().get(Calidades.TRADUCTOR.getValorId());
			if(traductor!=null && traductor.size()!=0){
				datosGeneralesExpedienteDTO.setTraductores(traductor.get(0));
				if(traductor.size()>1){
					datosGeneralesExpedienteDTO.setTradu("1");
				}
			}
			List<String> detuvo=datosGeneralesExpedienteDTO.getInvolucrados().get(Calidades.QUIEN_DETUVO.getValorId());
			if(detuvo!=null && detuvo.size()!=0){
				datosGeneralesExpedienteDTO.setQuienDetuvoNombre(detuvo.get(0));
				if(detuvo.size()>1){
					datosGeneralesExpedienteDTO.setQuienDetu("1");
				}
			}
			
			converter.alias("datosGeneralesExpedienteDTO", DatosGeneralesExpedienteDTO.class);
			String xml = converter.toXML(datosGeneralesExpedienteDTO);
			if(log.isDebugEnabled())
			{
				log.debug(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
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
	public ActionForward consultarDocumentos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idExpediente=request.getParameter("idExpedienteop");
			
			log.info("$$$$ ID de Expediente consultarDocumentos $$$ : "+idExpediente);

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			expedienteDTO.setNumeroExpedienteId(Long.parseLong(idExpediente));
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
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (documentoDTO.getActividadDTO() != null && documentoDTO.getActividadDTO().getUsuario() != null && documentoDTO.getActividadDTO().getUsuario().getArea()!= null
						&& documentoDTO.getActividadDTO().getUsuario().getArea().getNombre()!= null ? documentoDTO.getActividadDTO().getUsuario().getArea().getNombre():"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (documentoDTO.getActividadDTO() != null && documentoDTO.getActividadDTO().getFechaCreacion() != null? DateUtils.formatear(documentoDTO.getActividadDTO().getFechaCreacion()):"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (documentoDTO.getActividadDTO() != null && documentoDTO.getActividadDTO().getNombre() != null? documentoDTO.getActividadDTO().getNombre():"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getTipoDocumentoDTO().getValor()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getNombreDocumento()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getStrFechaCreacion()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getStrEsGuardadoParcial()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getEsGuardadoParcial()+" </div>]]></cell>");
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
	 * Funcion para consultar los Documentos generados en una Audiencia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarDocumentosXAudiencia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			String idAudiencia = request.getParameter("idAudiencia");

			log.info("consultarDocumentosXAudiencia - ID Audiencia: "
					+ idAudiencia);

			AudienciaDTO audienciaDTO = new AudienciaDTO();
			audienciaDTO.setId(Long.parseLong(idAudiencia));
			audienciaDTO.setTipoEvento(Eventos.AUDENCIA);

			audienciaDTO = audienciaDelegate.obtenerAudiencia(audienciaDTO);

			List<DocumentoDTO> listaDocumentoDTOs = documentoDelegate
					.consultarDocumentosAudiencia(audienciaDTO);

			request.getSession().setAttribute("totalRegistrosDocumentos",
					listaDocumentoDTOs.size());
			request.setAttribute("totalRegistrosDocumentos",
					listaDocumentoDTOs.size());
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

			for (DocumentoDTO documentoDTO : listaDocumentoDTOs) {
				writer.print("<row id='" + documentoDTO.getDocumentoId() + "'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
						+ (documentoDTO.getActividadDTO() != null
								&& documentoDTO.getActividadDTO().getUsuario() != null
								&& documentoDTO.getActividadDTO().getUsuario()
										.getArea() != null
								&& documentoDTO.getActividadDTO().getUsuario()
										.getArea().getNombre() != null ? documentoDTO
								.getActividadDTO().getUsuario().getArea()
								.getNombre()
								: "-") + " </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
						+ (documentoDTO.getActividadDTO() != null
								&& documentoDTO.getActividadDTO()
										.getFechaCreacion() != null ? DateUtils
								.formatear(documentoDTO.getActividadDTO()
										.getFechaCreacion()) : "-")
						+ " </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
						+ (documentoDTO.getActividadDTO() != null
								&& documentoDTO.getActividadDTO().getNombre() != null ? documentoDTO
								.getActividadDTO().getNombre() : "-")
						+ " </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
						+ documentoDTO.getTipoDocumentoDTO().getValor()
						+ " </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
						+ documentoDTO.getNombreDocumento()
						+ " </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
						+ documentoDTO.getStrFechaCreacion()
						+ " </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
						+ documentoDTO.getStrEsGuardadoParcial()
						+ " </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"
						+ documentoDTO.getEsGuardadoParcial()
						+ " </div>]]></cell>");
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
	 * funcion para mandar a la vista el XML con la informacion de los objetos del expediente
	 * que se desea consultar
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarObjetosExpedientePorTipo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idNumeroExpediente=request.getParameter("numeroExpediente");
			String tipoObjeto=request.getParameter("tipoObjeto");
			
			log.info("$$$$ ID de Expediente consultar Objetos POR TIPO $$$ : "+idNumeroExpediente);
			
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO=super.getExpedienteTrabajo(request,idNumeroExpediente);
			
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			
			//expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
			//consultamos el expediente
			//expedienteDTO = expedienteDelegate.obtenerExpediente(expedienteDTO); 
			
			StringBuffer sb= new StringBuffer();
			String xml = "";
			
			List<ObjetoDTO> listaObjetoDTOs=new ArrayList<ObjetoDTO>();
			converter.alias("listaObjetosDTO", java.util.List.class);
			//recuperamos los objetos Equipo de Computo del DTO
			if(tipoObjeto.equalsIgnoreCase("EQUIPO_COMPUTO"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.EQUIPO_DE_COMPUTO);
				converter.alias("EquipoComputoDTO",EquipoComputoDTO.class);
			}
			else if(tipoObjeto.equalsIgnoreCase("EQUIPO_TELEFONICO"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.EQUIPO_TELEFONICO);
				converter.alias("TelefoniaDTO",TelefoniaDTO.class);
			}
			else if(tipoObjeto.equalsIgnoreCase("ARMA"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.ARMA);
				converter.alias("ArmaDTO",ArmaDTO.class);
			}
			else if(tipoObjeto.equalsIgnoreCase("EXPLOSIVO"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.EXPLOSIVO);
				converter.alias("ExplosivoDTO",ExplosivoDTO.class);
			}
			else if(tipoObjeto.equalsIgnoreCase("SUSTANCIA"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.SUSTANCIA);
				converter.alias("SustanciaDTO",SustanciaDTO.class);
			}
			else if(tipoObjeto.equalsIgnoreCase("ANIMAL"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.ANIMAL);
				converter.alias("AnimalDTO",AnimalDTO.class);
			}
			else if(tipoObjeto.equalsIgnoreCase("VEHICULO"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.VEHICULO);
				converter.alias("VehiculoDTO", VehiculoDTO.class);
			}
			else if(tipoObjeto.equalsIgnoreCase("AERONAVE"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.AERONAVE);
				converter.alias("AeronaveDTO",AeronaveDTO.class);
			}
			else if(tipoObjeto.equalsIgnoreCase("EMBARCACION"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.EMBARCACION);
				converter.alias("EmbarcacionDTO",EmbarcacionDTO.class);
			}
			else if(tipoObjeto.equalsIgnoreCase("NUMERARIO"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.NUMERARIO);
				converter.alias("NumerarioDTO",NumerarioDTO.class);
			}
			else if(tipoObjeto.equalsIgnoreCase("VEGETAL"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.VEGETAL);
				converter.alias("VegetalDTO",VegetalDTO.class);
			}
			else if(tipoObjeto.equalsIgnoreCase("DOCUMENTO_OFICIAL"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.DOCUMENTO_OFICIAL);
				converter.alias("DocumentoOficialDTO",DocumentoOficialDTO.class);
			}
			else if(tipoObjeto.equalsIgnoreCase("JOYA"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.JOYA);
				converter.alias("JoyaDTO",JoyaDTO.class);
			}
			else if(tipoObjeto.equalsIgnoreCase("OBRA_ARTE"))
			{
				listaObjetoDTOs=expedienteDTO.getObjetosByTipo(Objetos.OBRA_DE_ARTE);
				converter.alias("ObraArteDTO",ObraArteDTO.class);
			}
			
			xml = converter.toXML(listaObjetoDTOs);
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
	
	public ActionForward consultarInvolucradosPorCalidadExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idNumeroExpediente=request.getParameter("idNumeroExpediente");
			String calidadInvolucrado=request.getParameter("calidadInvolucrado");
			
			log.info("$$$$ numero DE Expediente consultar involucrados por calidad $$$ : "+idNumeroExpediente);

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
			expedienteDTO.setUsuario(super.getUsuarioFirmado(request));
			expedienteDTO.setExpedienteId(this.expedienteDelegate.obtenerExpedienteIdPorNumExpId(expedienteDTO));
			
			List<InvolucradoDTO> listaInvolucrados=new ArrayList<InvolucradoDTO>();
			UsuarioDTO usuario = super.getUsuarioFirmado(request);
			//recuperamos los involucrados por la calidad
			if(calidadInvolucrado.equals("PROBABLE_RESPONSABLE")){
				listaInvolucrados=involucradoDelegate.consultarInvolucradoExpediente(expedienteDTO, Calidades.PROBABLE_RESPONSABLE_PERSONA,usuario);
			}
			else if(calidadInvolucrado.equals("VICTIMA"))
			{
				//Dentro del servicio se evalua si la v�ctima, puede serlo, pero la calidad es Denunciante con condici�n 1				
				listaInvolucrados=involucradoDelegate.consultarInvolucradoExpediente(expedienteDTO, Calidades.VICTIMA_PERSONA, usuario);

//				List<InvolucradoDTO> denunciante=involucradoDelegate.consultarInvolucradoExpediente(expedienteDTO, Calidades.DENUNCIANTE, usuario);
//				for (InvolucradoDTO involucradoDTO : denunciante) {
//					if(involucradoDTO.isVictima())
//					{
//						listaInvolucrados.add(involucradoDTO);
//					}
//				}
			}

			log.info("$$$$ numero el Expediente consultar involucrados  listaInvolucrados.size()$$$ : "+listaInvolucrados.size());
			
			converter.alias("nombreDemografico", NombreDemograficoDTO.class);
			converter.alias("listaInvolucrados", java.util.List.class);
			converter.alias("involucradoDTO", InvolucradoDTO.class);
			
			String xml = converter.toXML(listaInvolucrados);
			if(log.isDebugEnabled())
			{
				log.debug(xml);
			}
			
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	
	public ActionForward consultarDelitosAsociadosInvolucrado(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo consultarDelitosAsociadosInvolucrado");
			try {
				String idExpediente=request.getParameter("idExpediente");
				String idPR=request.getParameter("idPR");
				String nombreCompleto="";

				log.info("Ejecutando Action : AtencionTempranaPenalAction en el metodo:::: consultarDelitosAsociadosInvolucrado");
				log.info("Relacionar_Delito_PR idExpediente::: "+idExpediente);
				log.info("Relacionar_Delito_PR idPR::: "+idPR);
				//Defino la lista de los delitos
				List<DelitoPersonaDTO> listaDelitos=new ArrayList<DelitoPersonaDTO>();
				
				//recuperamos los delitos 
				listaDelitos = delitoDelegate.consultarDelitosVictimaPorImputado(Long.parseLong(idPR),Long.parseLong(idExpediente));
				
				log.info("tamano listaDelitos_PR:::: "+listaDelitos.size());
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=listaDelitos.size();
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (DelitoPersonaDTO delitoDTO : listaDelitos) {
					writer.print("<row id='"+delitoDTO.getDelitoPersonaId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+(delitoDTO.getDelito() != null && delitoDTO.getDelito().getCatDelitoDTO() != null && delitoDTO.getDelito().getCatDelitoDTO().getNombre() != null?delitoDTO.getDelito().getCatDelitoDTO().getNombre():"-")+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+(delitoDTO.getFormaParticipacion() != null && delitoDTO.getFormaParticipacion().getValor() != null?delitoDTO.getFormaParticipacion().getValor():"-")+"</div]]></cell>");
					if( delitoDTO.getVictima() != null && delitoDTO.getVictima().getNombreCompleto() != null ){
						nombreCompleto=delitoDTO.getVictima().getNombreCompleto();
						if(nombreCompleto.equals("null") || nombreCompleto.equals("") || nombreCompleto.equals("   ")){
							nombreCompleto="An�nimo";
						}
					}
					else {
						nombreCompleto="-";
					}
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+nombreCompleto+" </div]]></cell>");
					//log.info("$$$$  Este es el delito del PR con id ::::: "+delitoDTO.getDelito().getCatDelitoDTO().getNombre());
					writer.print("</row>");	
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			log.info("CONSULTA_DELITOS_INVOLUCRADO");
		
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	public ActionForward consultarDelitosAsociadosPorTodos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo consultarDelitosAsociadosPorTodos");
			try {
				String idExpediente=request.getParameter("idExpediente");
				String nombreCompleto="";

				log.info("Ejecutando Action : AtencionTempranaPenalAction en el metodo:::: consultarDelitosAsociadosPorTodos");
				log.info("Relacionar_Delito_PR idExpediente::: "+idExpediente);
				//Defino la lista de los delitos
				List<DelitoPersonaDTO> listaDelitos=new ArrayList<DelitoPersonaDTO>();
				
				//recuperamos los delitos 
				listaDelitos = delitoDelegate.consultarDelitosImputadoPorExpediente(Long.parseLong(idExpediente));
				
				log.info("tamano listaDelitos_Todos:::: "+listaDelitos.size());
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=listaDelitos.size();
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (DelitoPersonaDTO delitoDTO : listaDelitos) {
					writer.print("<row id='"+delitoDTO.getDelitoPersonaId()+'_'+ delitoDTO.getProbableResponsable().getElementoId()+"_"+delitoDTO.getVictima().getElementoId()+"_"+delitoDTO.getDelito().getDelitoId()+"'>");
					writer.print("<cell><![CDATA[<div> "+delitoDTO.getProbableResponsable().getNombreCompleto()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div>"+delitoDTO.getDelito().getCatDelitoDTO().getNombre()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div> "+delitoDTO.getFormaParticipacion().getValor()+"</div]]></cell>");
					nombreCompleto=delitoDTO.getVictima().getNombreCompleto();
					if(nombreCompleto.equals("null") || nombreCompleto.equals("") || nombreCompleto.equals("   ")){							
						nombreCompleto="An�nimo";
					}
					writer.print("<cell><![CDATA[<div> "+nombreCompleto+" </div]]></cell>");
					log.info("$$$$  Este es el delito del PR con id ::::: "+delitoDTO.getDelito().getCatDelitoDTO().getNombre());
					writer.print("</row>");	
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			log.info("CONSULTA_DELITOS_POR_TODOS");
		
		} catch (Exception e) {		
			log.info(e.getCause(),e);
		}
		return null;
	}
	
	/***
	 * Funcion para consultar el tama�o de la consulta de la relacion de todos los delitos relacionados de 
	 * un expediente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarTamanoDelitosAsociadosPorTodos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo consultarTamanoDelitosAsociadosPorTodos");
			try {
				String idExpediente=request.getParameter("idExpediente");

				log.info("Ejecutando Action : AtencionTempranaPenalAction en el metodo:::: consultarTamanoDelitosAsociadosPorTodos");
				log.info("Relacionar_Delito_PR idExpediente::: "+idExpediente);
				//Defino la lista de los delitos
				List<DelitoPersonaDTO> listaDelitos=new ArrayList<DelitoPersonaDTO>();
				
				//recuperamos los delitos 
				listaDelitos = delitoDelegate.consultarDelitosImputadoPorExpediente(Long.parseLong(idExpediente));
				
				log.info("tamano_listaDelitos_Todos:::: "+listaDelitos.size());
				StringBuilder sb=new StringBuilder();
				sb.append("<relacionTodosLosDelitos>");
				sb.append("<tamanoLista>");
				sb.append(listaDelitos.size());
				sb.append("</tamanoLista>");
				sb.append("</relacionTodosLosDelitos>");
						
				if(log.isDebugEnabled())
				{
					log.info(sb.toString());
				}
				//mandamos la respuesta a la vista
				escribirRespuesta(response, sb.toString());
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			StringBuilder sb=new StringBuilder();
			sb.append("<relacionTodosLosDelitos>");
			sb.append("<tamanoLista>");
			sb.append("0");
			sb.append("</tamanoLista>");
			sb.append("</relacionTodosLosDelitos>");
			escribirRespuesta(response, sb.toString());
		}
		return null;
	}

	
	public ActionForward consultarDelitosExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idNumeroExpediente=request.getParameter("idNumeroExpediente");
						
			log.info("$$$$ numero DE Expediente consultar delito por expediente$$$ : "+idNumeroExpediente);

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
			expedienteDTO.setUsuario(super.getUsuarioFirmado(request));
			expedienteDTO.setExpedienteId(this.expedienteDelegate.obtenerExpedienteIdPorNumExpId(expedienteDTO));
			List<DelitoDTO> listaDelitos=new ArrayList<DelitoDTO>();
			listaDelitos=delitoDelegate.consultarDelitosExpediente(expedienteDTO);
									
			converter.alias("listaDelitos", java.util.List.class);
			converter.alias("DelitoDTO", DelitoDTO.class);
			
			String xml = converter.toXML(listaDelitos);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	
	public ActionForward asociarDelitoProbableResponsable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idPR=request.getParameter("idPR");
			String idAsociacion=request.getParameter("idAsociacion");
			String idDelito=request.getParameter("idDelito");
			String idVictima=request.getParameter("idVictima");
			String idFormaP=request.getParameter("idFormaP");
						
			Long idClasificacion = (request.getParameter("idClasificacion")!=null && StringUtils.isNotBlank(request.getParameter("idClasificacion")) && !request.getParameter("idClasificacion").equals("-1") ) ? Long.parseLong(request.getParameter("idClasificacion")) : null;
			Long idLugar = (request.getParameter("idLugar")!=null && StringUtils.isNotBlank(request.getParameter("idLugar")) && !request.getParameter("idLugar").equals("-1") ) ? Long.parseLong(request.getParameter("idLugar")) : null;
			Long idModalidad = (request.getParameter("idModalidad")!=null && StringUtils.isNotBlank(request.getParameter("idModalidad")) && !request.getParameter("idModalidad").equals("-1") ) ? Long.parseLong(request.getParameter("idModalidad")) : null;
			Long idModus = (request.getParameter("idModus")!=null && StringUtils.isNotBlank(request.getParameter("idModus")) && !request.getParameter("idModus").equals("-1") ) ? Long.parseLong(request.getParameter("idModus")) : null;
			Long idCausa = (request.getParameter("idCausa")!=null && StringUtils.isNotBlank(request.getParameter("idCausa"))&& !request.getParameter("idCausa").equals("-1") ) ? Long.parseLong(request.getParameter("idCausa")) : null;
						
			log.info("$$$$ Ejecutar ACTION Asociar_delito al probable responsable $$$");
			
			log.info("Asociar_delito idPR:::: "+idPR);
			log.info("Asociar_delito idAsociacion:::: "+idAsociacion);
			log.info("Asociar_delito idDelito:::: "+idDelito);
			log.info("Asociar_delito idVictima:::: "+idVictima);
			log.info("Asociar_delito idFormaP:::: "+idFormaP);
			
			log.info("Asociar_delito idClasificacion:::: "+idClasificacion);
			log.info("Asociar_delito idLugar:::: "+idLugar);
			log.info("Asociar_delito idModalidad:::: "+idModalidad);
			log.info("Asociar_delito idModus:::: "+idModus);
			log.info("Asociar_delito idCausa:::: "+idCausa);
			
			Long asociacionId=null;

			if(Long.parseLong(idAsociacion)==0)
			{
				DelitoDTO delitoDTO=new DelitoDTO(Long.parseLong(idDelito));
				
				InvolucradoDTO involucradoDTO=new InvolucradoDTO(Long.parseLong(idPR));
				CalidadDTO calidadPRDTO= new CalidadDTO();
				calidadPRDTO.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
				involucradoDTO.setCalidadDTO(calidadPRDTO);
				
				InvolucradoDTO victimaDTO= new InvolucradoDTO(Long.parseLong(idVictima));
				CalidadDTO calidadVDTO= new CalidadDTO();
				calidadVDTO.setCalidades(Calidades.VICTIMA_PERSONA);
				victimaDTO.setCalidadDTO(calidadVDTO);
				
				asociacionId=delitoDelegate.asociarDelitoResponsableVictima(null, delitoDTO, involucradoDTO, victimaDTO, Long.parseLong(idFormaP), null, idClasificacion, idLugar, idModalidad, idModus, idCausa);
			}
			StringBuilder sb=new StringBuilder();
			sb.append("<Asociacion>");
			sb.append("<elementoId>");
			sb.append(asociacionId);
			sb.append("</elementoId>");
			sb.append("</Asociacion>");
					
			if(log.isDebugEnabled())
			{
				log.info(sb.toString());
			}
			
			escribirRespuesta(response, sb.toString());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	public ActionForward existeRelacionProbableResponsableVictimaDelito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			Long idPR = (request.getParameter("idPR") != null
					&& StringUtils.isNotBlank(request.getParameter("idPR")) && !request
					.getParameter("idPR").equals("-1")) ? Long
					.parseLong(request.getParameter("idPR")) : null;
					
			Long idDelito = (request.getParameter("idDelito") != null
					&& StringUtils.isNotBlank(request.getParameter("idDelito")) && !request
					.getParameter("idDelito").equals("-1")) ? Long
					.parseLong(request.getParameter("idDelito")) : null;
					
			Long idVictima = (request.getParameter("idVictima") != null
					&& StringUtils
							.isNotBlank(request.getParameter("idVictima")) && !request
					.getParameter("idVictima").equals("-1")) ? Long
					.parseLong(request.getParameter("idVictima")) : null;
					
			Long idFormaP = (request.getParameter("idFormaP") != null
					&& StringUtils
							.isNotBlank(request.getParameter("idFormaP")) && !request
					.getParameter("idFormaP").equals("-1")) ? Long
					.parseLong(request.getParameter("idFormaP")) : null;
				
			log.info(" Ejecutar ACTION existeRelacionProbableResponsableVictimaDelito");
			
			log.info("existeRelacionProbableResponsableVictimaDelito idPR:::: "+idPR);
			log.info("existeRelacionProbableResponsableVictimaDelito idDelito:::: "+idDelito);
			log.info("existeRelacionProbableResponsableVictimaDelito idVictima:::: "+idVictima);
			log.info("existeRelacionProbableResponsableVictimaDelito idFormaP:::: "+idFormaP);
			
			Boolean existeRelacion = false;

			existeRelacion = delitoDelegate
					.existeRelacionProbableResponsableVictimaDelitoFormaParticipacion(
							idDelito, idPR, idVictima, idFormaP);

			if(existeRelacion){
				escribirRespuesta(response, "<bandera>1</bandera>");
				log.info("::::::::::: Existe Relaci�n :::::::::::");
			}
			else{
				escribirRespuesta(response, "<bandera>0</bandera>");
				log.info("::::::::::: No existe relaci�n es posible crear una nueva. :::::::::::");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	public ActionForward registrarNotasExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo registrarNotasExpediente");
			try {
				
				NotaForm forma = (NotaForm) form;
				
				//Revisamos que el id de la nota no sea nulo
				if(StringUtils.isBlank(forma.getIdNota()))
				{
					forma.setIdNota("0");
					log.info("Id Nota --->  ES:null");
				}
				
				//Revisamos que la descripcion de la nota no sea nula
				if(forma.getNotas().equalsIgnoreCase("") ){
					forma.setNotas("");
					log.info("Descripcion NOTA, ES:null");
				}
				
				//Revisamos que el id de la nota no sea nulo
				if(StringUtils.isBlank(forma.getIdNumeroExpediente()))
				{
					forma.setIdNumeroExpediente(null);
					log.info("numeroExpediente NOTA --->  ES:null");
				}
				
				String idNumeroExpediente=forma.getIdNumeroExpediente();//request.getParameter("idNumeroExpediente");
				String notas=forma.getNotas();//request.getParameter("notas");
				String idNota=forma.getIdNota();//request.getParameter("idNota");
				
				
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registrarNotasExpediente parametro idnumero expediente:"+idNumeroExpediente);
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registrarNotasExpediente parametro notas:"+notas);
				NotaExpedienteDTO nota=new NotaExpedienteDTO();
				if(idNota.equals("0"))
				{
					nota.setIdNota(null);
				}
				else
				{
					nota.setIdNota(Long.parseLong(idNota));
				}
				nota.setDescripcion(notas);
				List<NotaExpedienteDTO> listaNotas=new ArrayList<NotaExpedienteDTO>();
				listaNotas.add(nota);
				ExpedienteDTO expedienteDTO=new ExpedienteDTO();
				expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
				listaNotas=expedienteDelegate.registrarActualizarNotasExpediente(listaNotas, expedienteDTO);
				
				converter.alias("listaNotas", java.util.List.class);
				converter.alias("NotaExpedienteDTO", NotaExpedienteDTO.class);
				String xml = converter.toXML(listaNotas);
				if(log.isDebugEnabled())
				{
					log.info(xml);
					log.info("JJJ_KKK:: "+listaNotas.get(0).getIdNota());
				}
				
				escribirRespuesta(response, xml);
			} catch (Exception e) {		
				log.info(e.getCause(),e);
				escribirRespuesta(response, "");
			}
			return null;
	}
	
	
	public ActionForward consultarNotasExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo ConsultarNotasExpediente");
			try {
				String idNumeroExpediente=request.getParameter("idNumeroExpediente");
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo ConsultarNotasExpediente el numero de expediente es:"+idNumeroExpediente);
				List<NotaExpedienteDTO> listaNotas=null;
				ExpedienteDTO expedienteDTO=new ExpedienteDTO();
				expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
				listaNotas=expedienteDelegate.consultarNotasPorExpediente(expedienteDTO);
				converter.alias("listaNotas", java.util.List.class);
				converter.alias("notaExpedienteDTO", NotaExpedienteDTO.class);
				String xml = converter.toXML(listaNotas);
				if(log.isDebugEnabled())
				{
					log.info(xml);
				}
				escribirRespuesta(response, xml);
				
				
			} catch (Exception e) {		
				log.info(e.getCause(),e);
			}
			return null;
	}
	
	public ActionForward consultarNotaXId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo ConsultarNotasExpediente");
			try {
				String idNota=request.getParameter("idNota");
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo consultarNotaXId :: "+idNota);
				NotaExpedienteDTO nota=expedienteDelegate.consultarNotaPorId(Long.parseLong(idNota));
				
				converter.alias("notaExpedienteDTO", NotaExpedienteDTO.class);
				String xml = converter.toXML(nota);
				if(log.isDebugEnabled())
				{
					log.info(xml);
				}
				escribirRespuesta(response, xml);
				
				
			} catch (Exception e) {		
				log.info(e.getCause(),e);
			}
			return null;
	}
	
	public ActionForward consultarDelitosExpedienteGrid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			
			log.info("$$$$ numero DE Expediente consultar delito por expedienteGRID $$$ : ");
			
			String idNumeroExpediente=request.getParameter("idNumeroExpediente");
			log.info("idNumeroExpediente" + idNumeroExpediente);
			
			String numeroExpedienteId = request.getParameter("numeroExpedienteId");
			log.info("numeroExpedienteId" + numeroExpedienteId);			

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			
			if(numeroExpedienteId != null && StringUtils.isNotEmpty(numeroExpedienteId))
				expedienteDTO.setNumeroExpedienteId(Long.parseLong(numeroExpedienteId));
			if(idNumeroExpediente != null && StringUtils.isNotEmpty(idNumeroExpediente))
				expedienteDTO.setExpedienteId(Long.parseLong(idNumeroExpediente));
			
			expedienteDTO = expedienteDelegate.obtenerExpediente(expedienteDTO);
			
			List<DelitoDTO> listaDelitos=new ArrayList<DelitoDTO>();
			listaDelitos=delitoDelegate.consultarDelitosExpediente(expedienteDTO);
			
			converter.alias("listaDelitos", java.util.List.class);
			converter.alias("delitoDTO", DelitoDTO.class);
			String xml = converter.toXML(listaDelitos);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
						
			int lTotalRegistros=listaDelitos.size();

			final PaginacionDTO pag = PaginacionThreadHolder.get();
            log.debug("pag :: " + pag);
    if (pag != null && pag.getTotalRegistros() != null) {
        writer.print("<total>" + pag.getTotalPaginas() + "</total>");
        writer.print("<records>" + pag.getTotalRegistros() + "</records>");
    } else {
        writer.print("<total>0</total>");
        writer.print("<records>0</records>");
    }

			int i=0;
			for (DelitoDTO catDelitoDTO : listaDelitos) {
				
				writer.print("<row id='" + catDelitoDTO.getCatDelitoDTO().getCatDelitoId()+ "'>");
				writer.print("<cell>" + catDelitoDTO.getDelitoId()+ "</cell>");
				writer.print("<cell>" + catDelitoDTO.getCatDelitoDTO().getClaveDelito()+ "</cell>");
				writer.print("<cell>" + catDelitoDTO.getCatDelitoDTO().getNombre()+ "</cell>");				
				if(catDelitoDTO.getCatDelitoDTO().getEsGrave()==true)
				{
					writer.print("<cell>" + "true"+ "</cell>");
				}
				else
				{
					writer.print("<cell>" + "false"+ "</cell>");
				}
				//Formatea la clasificacion del delito
				if(catDelitoDTO.getCatDelitoDTO().getEsGrave()==true)
					writer.print("<cell>" + "Si"+ "</cell>");
				else				
					writer.print("<cell>" + "No" + "</cell>");				
				if(catDelitoDTO.getEsPrincipal())
				{
					writer.print("<cell><![CDATA[<div>"+"<input type='radio' name='gridDelitos' checked='checked' id='rdb_"+catDelitoDTO.getCatDelitoDTO().getCatDelitoId()+"' onclick='revisaEsDelitoGraveUno("+catDelitoDTO.getCatDelitoDTO().getCatDelitoId()+");'> </div>]]></cell>");
				}
				else
				{
					writer.print("<cell><![CDATA[<div>"+"<input type='radio' name='gridDelitos' id='rdb_"+catDelitoDTO.getCatDelitoDTO().getCatDelitoId()+"' onclick='revisaEsDelitoGraveUno("+catDelitoDTO.getCatDelitoDTO().getCatDelitoId()+");'> </div>]]></cell>");	
				}
				writer.print("<cell>"+catDelitoDTO.getCatDelitoDTO().getCatDelitoId()+ "_" +catDelitoDTO.getDelitoId() +"_C"+ "</cell>");
				writer.print("<cell>" + catDelitoDTO.getCatDelitoDTO().getCatDelitoId()+ "</cell>");
				writer.print("</row>");
				i++;
			}			
			
			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			//escribirError(response, null);
		}
		return null;
	}
	
	public ActionForward consultarProbRespsAsociadosAlDelito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo consultarDelitosAsociadosInvolucrado");
			try {
				String idExpediente=request.getParameter("idExpediente");
				String idDelito=request.getParameter("idDelito");
				String nombreCompleto="";

				log.info("Ejecutando Action : AtencionTempranaPenalAction en el metodo:::: consultarProbRespsAsociadosAlDelito");
				log.info("Relacionar_Delito_Del idExpediente::: "+idExpediente);
				log.info("Relacionar_Delito_Del idDelito::: "+idDelito);

				//Defino la lista de los delitos
				List<DelitoPersonaDTO> listaDelitos=new ArrayList<DelitoPersonaDTO>();
				
				//recuperamos los delitos 
				listaDelitos = delitoDelegate.consultarVictimaImputadoPorDelito(Long.parseLong(idDelito),Long.parseLong(idExpediente));
				
				log.info("tamano listaDelitos_Del:::: "+listaDelitos.size());
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=listaDelitos.size();
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (DelitoPersonaDTO delitoDTO : listaDelitos) {
					writer.print("<row id='"+delitoDTO.getDelitoPersonaId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+(delitoDTO.getProbableResponsable() != null && delitoDTO.getProbableResponsable().getNombreCompleto() != null?delitoDTO.getProbableResponsable().getNombreCompleto():"-")+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+(delitoDTO.getFormaParticipacion() != null && delitoDTO.getFormaParticipacion().getValor() != null?delitoDTO.getFormaParticipacion().getValor():"-")+"</div]]></cell>");
					if(delitoDTO.getVictima() != null && delitoDTO.getVictima().getNombreCompleto() != null){
						nombreCompleto=delitoDTO.getVictima().getNombreCompleto();					
						if(nombreCompleto.equals("null") || nombreCompleto.equals("") || nombreCompleto.equals("   ")){
							nombreCompleto="An�nimo";
						}
					}else{
						nombreCompleto="-";
					}
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+nombreCompleto+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+delitoDTO.getDelitoPersonaId()+" </div]]></cell>");
					log.info("$$$$  Este es el delito del PR con id ::::: "+delitoDTO.getDelito().getCatDelitoDTO().getNombre());
					writer.print("</row>");	
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			log.info("CONSULTA_DELITOS_POR_DELITOS");
		
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	/**
	 * funcion para mandar a la vista el XML con la informacion del objeto
	 * solicitado
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarObjetoPorId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			String idObjeto = request.getParameter("idObjeto");

			log.info("$$$$ ID de Objeto solicitado$$$ : " + idObjeto);
			ObjetoDTO objetoDTO = new ObjetoDTO(Long.parseLong(idObjeto));
			objetoDTO = objetoDelegate.obtenerObjeto(objetoDTO);
			String xml = "";
			//alias para vehiculo
			converter.alias("Vehiculo", ObjetoDTO.class);
			converter.alias("VehiculoDTO", VehiculoDTO.class);
			//alias para equipo de computo
			converter.alias("EquipoComputo", ObjetoDTO.class);
			converter.alias("EquipoComputoDTO", EquipoComputoDTO.class);
			//alias para equipo telefonico
			converter.alias("Telefonia", ObjetoDTO.class);
			converter.alias("TelefoniaDTO", TelefoniaDTO.class);
			//alias para arma
			converter.alias("Arma", ObjetoDTO.class);
			converter.alias("ArmaDTO", ArmaDTO.class);
			//alias para explosivo
			converter.alias("Explosivo", ObjetoDTO.class);
			converter.alias("ExplosivoDTO", ExplosivoDTO.class);
			//alias para sustancia
			converter.alias("Sustancia", ObjetoDTO.class);
			converter.alias("SustanciaDTO", SustanciaDTO.class);
			//alias para animal
			converter.alias("Animal", ObjetoDTO.class);
			converter.alias("AnimalDTO", AnimalDTO.class);
			//alias para aeronave
			converter.alias("Aeronave", ObjetoDTO.class);
			converter.alias("AeronaveDTO", AeronaveDTO.class);
			//alias para embarcacion
			converter.alias("Embarcacion", ObjetoDTO.class);
			converter.alias("EmbarcacionDTO", EmbarcacionDTO.class);
			//alias para numerario
			converter.alias("Numerario", ObjetoDTO.class);
			converter.alias("NumerarioDTO", NumerarioDTO.class);
			//alias para vegetal
			converter.alias("Vegetal", ObjetoDTO.class);
			converter.alias("VegetalDTO", VegetalDTO.class);
			//alias para documento
			converter.alias("DocumentoOficial", ObjetoDTO.class);
			converter.alias("DocumentoOficialDTO", DocumentoOficialDTO.class);
			//alias para joya
			converter.alias("Joya", ObjetoDTO.class);
			converter.alias("JoyaDTO", JoyaDTO.class);
			//alias para obra de arte
			converter.alias("ObraArte", ObjetoDTO.class);
			converter.alias("ObraArteDTO", ObraArteDTO.class);
			
			//alias para Otro Objeto
			//converter.alias("OtroObjeto", ObjetoDTO.class);
			converter.alias("ObjetoDTO", ObjetoDTO.class);
			
			xml = converter.toXML(objetoDTO);
			if (log.isDebugEnabled()) {
				log.debug(xml);
			}
			log.info("consulta_objeto_por_id : " + idObjeto);
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	public ActionForward registraActividadExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo registraActividadExpediente");
			try {
				String idExpediente=request.getParameter("idExpediente");
				String actuacion=request.getParameter("actuacion");
				String idNumeroExpediente=request.getParameter("idNumeroExpediente");
				String estatus=request.getParameter("estatus");
				String numExpe=request.getParameter("numExpe");
				String cveFuncionarioAsignado=request.getParameter("cveFuncionarioAsignado");
				
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registraActividadExpediente el id de expediente es:"+idExpediente);
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registraActividadExpediente la actuacion es:"+actuacion);
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registraActividadExpediente el idNumeroExpediente es:"+idNumeroExpediente);
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registraActividadExpediente el NumeroExpediente es:"+numExpe);
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registraActividadExpediente el NumeroExpediente es:"+cveFuncionarioAsignado);
				
				// Se cambio la lÃ³gica para obtener una instancia de ExpedienteDTO 
				ExpedienteDTO expediente = super.getExpedienteTrabajo(request, numExpe);
				if(expediente == null){
					expediente = new ExpedienteDTO(); 	
				}
				
				if(idExpediente!=null && !idExpediente.equals("")){
					expediente.setExpedienteId(Long.parseLong(idExpediente));
				}
				if(idNumeroExpediente!=null && !idNumeroExpediente.equals("")){
					expediente.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
				}
				if(estatus!=null && !estatus.equals("")){
					ValorDTO valorDTO=new ValorDTO();
					valorDTO.setIdCampo(Long.parseLong(estatus));
					expediente.setEstatus(valorDTO);
				}
				
				boolean asignarFunc=false;
				
				Long actividad=0L;
				if(actuacion!=null && !actuacion.equals("")){
					if(actuacion.equals(""+Actividades.DIRIGIR_A_LA_UNIDAD_DE_FISCALES_INVESTIGADORES.getValorId())){
						actividad=Actividades.RECIBIR_CANALIZACION_UI.getValorId();
					}else if(actuacion.equals(""+Actividades.DIRIGIR_A_LA_UNIDAD_DE_SOLUCION_DE_CONTROVERSIAS.getValorId())){
						actividad=Actividades.RECIBIR_CANALIZACION_JAR.getValorId();
					}else if(actuacion.equals("151")){
						actividad=Actividades.ATENDER_CANALIZACION_UI.getValorId();
					}else{
						
						if(actuacion.equals(""+Actividades.ASIGNAR_SOLICITUD_DE_AYUDA.getValorId())
								|| actuacion.equals(""+Actividades.ENVIAR_ESTUDIO_SOCIOECONOMICO.getValorId())
								|| actuacion.equals(""+Actividades.PROPORCIONAR_APOYO_LEGAL.getValorId())){
							
							asignarFunc=true;
						}
						actividad=Long.parseLong(actuacion);
					}
				}
				 
				Long idActividad=0L;
				
				//Usado para las areas de uavd, para designar el funcionario al que se le asigna la solicitud
				if(asignarFunc == true){
					Long cveFunc = NumberUtils.toLong(cveFuncionarioAsignado,0L);
					UsuarioDTO usuarioDestino = new UsuarioDTO();
					
					if(!cveFunc.equals(0L)){
						usuarioDestino=usuarioDelegate.consultarUsuarioPorClaveFuncionario(cveFunc);
					}
					log.info("El usuario Obtenido es:"+usuarioDelegate);
					if(usuarioDestino != null && usuarioDestino.getFuncionario() != null){
						idActividad=actividadDelegate.registrarActividad(expediente,usuarioDestino.getFuncionario(),actividad);
					}
					else{
						idActividad=actividadDelegate.registrarActividad(expediente,null,actividad);
					}
				}else{
					//Funcionamiento normal
					UsuarioDTO usuarioDTO=super.getUsuarioFirmado(request);
					idActividad=actividadDelegate.registrarActividad(expediente, usuarioDTO.getFuncionario(),actividad);
				}
				
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registraActividadExpediente la actividad es:"+idActividad);
				
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registraActividadExpediente el expediente es:"+expediente);
				if(actividad!=Actividades.SOLICITAR_ATENCION_PSICOLOGICA_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId())
				{
					expedienteDelegate.actualizarEstatusExpediente(expediente);
				}
				
				ActividadDTO actividadDTO=new ActividadDTO();
				actividadDTO.setActividadId(idActividad);
				converter.alias("actividadDTO", ActividadDTO.class);
				String xml = converter.toXML(actividadDTO);
				if(log.isDebugEnabled())
				{
					log.info(xml);
				}
				escribirRespuesta(response, xml);
				
				
			} catch (Exception e) {		
				log.error(e.getMessage(),e);
			}
			return null;
	}
	
	
	/**
	 * MEtodo para consultar los indices estructurados de teoria del caso
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarIndiceEstructurado(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo consultarIndiceEstructurado");
			
			String tipoOficio = request.getParameter("tipoOficio");
			
			Long tipoOficioEstructurado = null;
			
			if(tipoOficio != null){
				tipoOficioEstructurado= NumberUtils.toLong(tipoOficio);
			}
			
			List<IndiceEstructuradoDTO> listIndiceEstructuradoDTO= documentoDelegate.consultarIndicesEstructuradosPorTipoOficio(tipoOficioEstructurado);
			List<IndiceEstructuradoDTO> listIndiceEstructuradoDTO2=new ArrayList<IndiceEstructuradoDTO>();
			
			for (IndiceEstructuradoDTO indiceEstructuradoDTO : listIndiceEstructuradoDTO) {
				indiceEstructuradoDTO.setIndiceEstructuradoIdHijo(indiceEstructuradoDTO.getIndiceEstructuradoId());
				indiceEstructuradoDTO.setNombreEtiquetaHijo(indiceEstructuradoDTO.getNombreEtiqueta());
				listIndiceEstructuradoDTO2.add(indiceEstructuradoDTO);
			}
			String xml = "";
			converter.alias("listIndiceEstructuradoDTO", java.util.List.class);
			converter.alias("indiceEstructuradoDTO", IndiceEstructuradoDTO.class);
			xml = converter.toXML(listIndiceEstructuradoDTO2);
			if (log.isDebugEnabled()) {
				log.debug(xml);
			}
			log.info("consultarIndiceEstructurado lista : " + listIndiceEstructuradoDTO);
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	
	/**
	 * MEtodo para ingresar teorias del caso del expediente
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward ingresaTeoriaCaso(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo ingresaTeoriaCaso");
			
			DocumentoDTO documentoDTO=new DocumentoDTO();
			String idsIndices=request.getParameter("idsIndices");
			String idExpediente=request.getParameter("idExpediente");
			String idIndice=request.getParameter("idIndice");
			String textoIndice=request.getParameter("texto");
			log.info("Texto de la teoria del caso : " + textoIndice);
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			Long idexpe=0L;
			if(idExpediente !=null && !(idExpediente.equals(""))){
				idexpe=Long.parseLong(idExpediente);
			}
			expedienteDTO.setExpedienteId(idexpe);
			log.info("Cadena de indices lista : " + idsIndices);
			String[] indices = {};
			if( idsIndices!=null && !idsIndices.trim().isEmpty() )
				indices = idsIndices.split(",");
			List<CuerpoOficioEstructuradoDTO> listCuerpoOficioEstructuradoDTO=new ArrayList<CuerpoOficioEstructuradoDTO>();
			int indiceSecuencia=1;
			for (String string : indices) {
				log.info("Cadena de indices lista : " + string);
				String[]  indiCuerp=string.split("#");
				CuerpoOficioEstructuradoDTO cuerposOficio= new CuerpoOficioEstructuradoDTO();
				int i=0;
				IndiceEstructuradoDTO indiceEstructuradoDTO=new IndiceEstructuradoDTO();
				for (String string2 : indiCuerp) {
					switch (i) {
					case 0:
						indiceEstructuradoDTO.setIndiceEstructuradoId(Long.parseLong(string2));
						log.info("Cadena de indices indice estructurado : " + string2);
						indiceEstructuradoDTO.setNivel((short)0);
						indiceEstructuradoDTO.setTipoOficio(new ValorDTO(1L));
						String[]  idIndices=idIndice.split("#");
						log.info("Cadena de indices indice estructurado texto: " + idIndices[0]);
						if(idIndices[0].equals(string2)){
							cuerposOficio.setTexto(textoIndice);
							cuerposOficio.setInteresa(true);
						}
						
						break;
					case 1:
						if(!string2.equals("-1")){
							cuerposOficio.setCuerpoOficioEstructuradoId(Long.parseLong(string2));
							log.info("Cadena de indices cuerpoOficio : " + string2);
							if(idIndice!=null && !idIndice.equals("")){
								if(idIndice.equals(string2)){
									cuerposOficio.setTexto(textoIndice);
									cuerposOficio.setInteresa(true);
								}
							}
							
						}
						break;
					default:
						indiceEstructuradoDTO.setIndiceEstructuradoId(Long.parseLong(string2));
						log.info("Cadena de indices indice estructurado default: " + string2);
						indiceEstructuradoDTO.setNivel((short)0);
						indiceEstructuradoDTO.setTipoOficio(new ValorDTO(1L));
						break;
					}
					cuerposOficio.setIndiceEstructurado(indiceEstructuradoDTO);
					cuerposOficio.setNumeracion(indiceSecuencia);
					cuerposOficio.setSecuencia(indiceSecuencia);
					i++;
				}
				cuerposOficio.setTexto(textoIndice);	
				listCuerpoOficioEstructuradoDTO.add(cuerposOficio);
				indiceSecuencia++;
			}
			log.info("Cadena de indices lista : " + indices.length);
			OficioEstructuradoDTO oficioEstructuradoDTO=new OficioEstructuradoDTO();
			oficioEstructuradoDTO.setCuerposOficio(listCuerpoOficioEstructuradoDTO);
			documentoDTO.setOficioEstructuradoDTO(oficioEstructuradoDTO);
			documentoDTO.setExpedienteDTO(expedienteDTO);
			log.info("DTO de documento a grabar  : " + documentoDTO);
			Long idDocumento = documentoDelegate.guardarTeoriaDeCaso(documentoDTO);
			documentoDTO.setDocumentoId(idDocumento);
			String xml = "";
			converter.alias("documentoDTO", DocumentoDTO.class);
			xml = converter.toXML(documentoDTO);
			if (log.isDebugEnabled()) {
				log.debug(xml);
			}
			log.info("consultarIndiceEstructurado lista : " + documentoDTO);
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	
	
	/**
	 * MEtodo para ingresar pliego de consignacion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward ingresaPliegoConsignacion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("EJECUTANDO ACTION INGRESAR PLIEGO DE CONSIGNACION");
			
			DocumentoDTO documentoDTO=new DocumentoDTO();
			String idsIndices=request.getParameter("idsIndices");
			String idExpediente=request.getParameter("idExpediente");
			String idIndice=request.getParameter("idIndice");
			String textoIndice=request.getParameter("texto");
			log.info("Texto del Pliego de cosignacion: " + textoIndice);
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			Long idexpe=0L;
			if(idExpediente !=null && !(idExpediente.equals(""))){
				idexpe=Long.parseLong(idExpediente);
			}
			expedienteDTO.setExpedienteId(idexpe);
			log.info("Cadena de indices lista : " + idsIndices);
			String[] indices = {};
			if( idsIndices!=null && !idsIndices.trim().isEmpty() )
				indices = idsIndices.split(",");
			List<CuerpoOficioEstructuradoDTO> listCuerpoOficioEstructuradoDTO=new ArrayList<CuerpoOficioEstructuradoDTO>();
			int indiceSecuencia=1;
			for (String string : indices) {
				log.info("Cadena de indices lista : " + string);
				String[]  indiCuerp=string.split("#");
				CuerpoOficioEstructuradoDTO cuerposOficio= new CuerpoOficioEstructuradoDTO();
				int i=0;
				IndiceEstructuradoDTO indiceEstructuradoDTO=new IndiceEstructuradoDTO();
				for (String string2 : indiCuerp) {
					switch (i) {
					case 0:
						indiceEstructuradoDTO.setIndiceEstructuradoId(Long.parseLong(string2));
						log.info("Cadena de indices indice estructurado : " + string2);
						indiceEstructuradoDTO.setNivel((short)0);
						indiceEstructuradoDTO.setTipoOficio(new ValorDTO(1L));
						String[]  idIndices=idIndice.split("#");
						log.info("Cadena de indices indice estructurado texto: " + idIndices[0]);
						if(idIndices[0].equals(string2)){
							cuerposOficio.setTexto(textoIndice);
							cuerposOficio.setInteresa(true);
						}
						
						break;
					case 1:
						if(!string2.equals("-1")){
							cuerposOficio.setCuerpoOficioEstructuradoId(Long.parseLong(string2));
							log.info("Cadena de indices cuerpoOficio : " + string2);
							if(idIndice!=null && !idIndice.equals("")){
								if(idIndice.equals(string2)){
									cuerposOficio.setTexto(textoIndice);
									cuerposOficio.setInteresa(true);
								}
							}
							
						}
						break;
					default:
						indiceEstructuradoDTO.setIndiceEstructuradoId(Long.parseLong(string2));
						log.info("Cadena de indices indice estructurado default: " + string2);
						indiceEstructuradoDTO.setNivel((short)0);
						indiceEstructuradoDTO.setTipoOficio(new ValorDTO(1L));
						break;
					}
					cuerposOficio.setIndiceEstructurado(indiceEstructuradoDTO);
					cuerposOficio.setNumeracion(indiceSecuencia);
					cuerposOficio.setSecuencia(indiceSecuencia);
					i++;
				}
				cuerposOficio.setTexto(textoIndice);	
				listCuerpoOficioEstructuradoDTO.add(cuerposOficio);
				indiceSecuencia++;
			}
			log.info("Cadena de indices lista : " + indices.length);
			OficioEstructuradoDTO oficioEstructuradoDTO=new OficioEstructuradoDTO();
			oficioEstructuradoDTO.setCuerposOficio(listCuerpoOficioEstructuradoDTO);
			documentoDTO.setOficioEstructuradoDTO(oficioEstructuradoDTO);
			documentoDTO.setExpedienteDTO(expedienteDTO);
			log.info("DTO de documento a grabar  : " + documentoDTO);
			Long idDocumento = documentoDelegate.guardarPliegoConsignacion(documentoDTO);
			documentoDTO.setDocumentoId(idDocumento);
			String xml = "";
			converter.alias("documentoDTO", DocumentoDTO.class);
			xml = converter.toXML(documentoDTO);
			if (log.isDebugEnabled()) {
				log.debug(xml);
			}
			log.info("consultarIndiceEstructurado lista : " + documentoDTO);
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * MEtodo para consultar cuerpo del oficio de una teorias del caso del expediente
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaCuerpoOficioTeoriaCaso(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo consultaCuerpoOficioTeoriaCaso");
			
			Long idCuepoOficioEStructurado=0L;
			String idCuepoOficio=request.getParameter("idIndiceOficio");
			CuerpoOficioEstructuradoDTO cuerpoOficioEstructuradoDTO=new CuerpoOficioEstructuradoDTO();
			log.info("indiceOficio::::"+idCuepoOficio);
			boolean op=false;
			if(idCuepoOficio !=null && !idCuepoOficio.equals("")){
				String[]  indiCuerp=idCuepoOficio.split("#");
				int i=0;
				IndiceEstructuradoDTO indiceEstructuradoDTO=new IndiceEstructuradoDTO();
				
				for (String string2 : indiCuerp) {
					log.info("Cadena for: " + string2+" ::::"+i);
					switch (i) {
					case 0:
						indiceEstructuradoDTO.setIndiceEstructuradoId(Long.parseLong(string2));
						log.info("Cadena de indices indice estructurado : " + string2);
//						indiceEstructuradoDTO.setNivel((short)0);
//						indiceEstructuradoDTO.setTipoOficio(new ValorDTO(1L));
						break;
					case 1:
						if(!string2.equals("-1")){
							log.info("Cadena de indices cuerpo estructurado : " + string2);
							cuerpoOficioEstructuradoDTO.setCuerpoOficioEstructuradoId(Long.parseLong(string2));
							op=true;
						}
						break;
					default:
						log.info("Cadena de indices default estructurado : " + string2);
						cuerpoOficioEstructuradoDTO.setCuerpoOficioEstructuradoId(Long.parseLong(string2));
						break;
					}
					cuerpoOficioEstructuradoDTO.setIndiceEstructurado(indiceEstructuradoDTO);
					i++;
				}
			}
			
			log.info("DTO cuerpo Ofico eStructu####### " + cuerpoOficioEstructuradoDTO);
			cuerpoOficioEstructuradoDTO=documentoDelegate.consultarCuerpoOficio(cuerpoOficioEstructuradoDTO);
			if(op){
				cuerpoOficioEstructuradoDTO.getIndiceEstructurado().setTextoEtiquetaHijo(cuerpoOficioEstructuradoDTO.getTexto());	
			}else{
				cuerpoOficioEstructuradoDTO.getIndiceEstructurado().setTextoEtiquetaHijo(cuerpoOficioEstructuradoDTO.getIndiceEstructurado().getTextoEtiqueta());
			}
			
			String xml = "";
			converter.alias("documentoDTO", CuerpoOficioEstructuradoDTO.class);
			xml = converter.toXML(cuerpoOficioEstructuradoDTO);
			if (log.isDebugEnabled()) {
				log.debug(xml);
			}
			log.info("consultarIndiceEstructurado lista : " + cuerpoOficioEstructuradoDTO);
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	
	/**
	 * MEtodo para consultar la teoria del caso por expediente
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarTeoriasDelCasoExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo consultarTeoriasDelCasoExpediente");
			
			String numeroExpediente=request.getParameter("numeroExpediente");
			log.info("consultarTeoriasDelCasoExpediente### Numero expediente::::"+numeroExpediente);
			ExpedienteDTO expedienteDTO=super.getExpedienteTrabajo(request, numeroExpediente);
			log.info("consultarTeoriasDelCasoExpediente### expediente::::"+expedienteDTO);
			DocumentoDTO documentoDTO=documentoDelegate.consultarTeoriasDelCasoXExpediente(expedienteDTO);
			if(documentoDTO.getOficioEstructuradoDTO()!=null){
				if(documentoDTO.getOficioEstructuradoDTO().getCuerposOficio()!=null && documentoDTO.getOficioEstructuradoDTO().getCuerposOficio().size()!=0){
					for (int i = 0; i < documentoDTO.getOficioEstructuradoDTO().getCuerposOficio().size(); i++) {
						Long id=documentoDTO.getOficioEstructuradoDTO().getCuerposOficio().get(i).getIndiceEstructurado().getIndiceEstructuradoId();
						documentoDTO.getOficioEstructuradoDTO().getCuerposOficio().get(i).getIndiceEstructurado().setIndiceEstructuradoIdHijo(id);	
						String nombre=documentoDTO.getOficioEstructuradoDTO().getCuerposOficio().get(i).getIndiceEstructurado().getNombreEtiqueta();
						documentoDTO.getOficioEstructuradoDTO().getCuerposOficio().get(i).getIndiceEstructurado().setNombreEtiquetaHijo(nombre);						
					}
				}
			}
			
			
			log.info("consultarTeoriasDelCasoExpediente### documento::::"+documentoDTO);
			String xml = "";
			converter.alias("documentoDTO", DocumentoDTO.class);
			converter.alias("cuerpoOficioEstructuradoDTO", CuerpoOficioEstructuradoDTO.class);
			xml = converter.toXML(documentoDTO);
			if (log.isDebugEnabled()) {
				log.debug(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	
	
	/**
	 * funcion para consultar las alertas que se muestran en el visor de elementos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarAlertas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idExpediente=request.getParameter("idExpedienteop");
			
			log.info("$$$$ ID de Expediente consultarAlertas $$$ : "+idExpediente);

			//ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			UsuarioDTO usuarioDTO= super.getUsuarioFirmado(request);
		
			List<AlarmaDTO> listaAlarmas= alarmaDelegate.consultarAlarmasXFuncionario(usuarioDTO.getFuncionario().getClaveFuncionario(), EstatusAlarmaAlerta.NO_EJECUTADA.getValorId());
			
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
			
			
			for (AlarmaDTO alarmaDTO : listaAlarmas) {
				writer.print("<row id='"+alarmaDTO.getAlarmaId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+DateUtils.formatear(alarmaDTO.getFechaAlarma())+" "+DateUtils.formatearHora(alarmaDTO.getFechaAlarma()) +"</div]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+alarmaDTO.getMotivo() +"</div]]></cell>");
				//writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getNombreDocumento()+" </div]]></cell>");
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
	 * MEtodo para Enviar los avisos de detencion de los probables responsables
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward enviarAvisosDetencion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo enviarAvisosDetencion");
			
			//Se obtiene el expediente de sesion
			String numeroExpediente=request.getParameter("numeroExpediente");
			log.info("numero expediente: "+numeroExpediente);
			ExpedienteDTO expedienteDTO=super.getExpedienteTrabajo(request, numeroExpediente);
			log.info("ExpedienteDTO: "+expedienteDTO);
			
			//Se obtiene el id del individuo a consultar
			Long idIndividuo=NumberUtils.toLong(request.getParameter("idIndividuo"), 0);
			log.info("Id del individuo "+idIndividuo);	
			
			//Se obtiene el id del objeto detencion
			Long idDetencion=NumberUtils.toLong(request.getParameter("idDetencion"), 0);
			log.info(" idDetencion "+idDetencion);	
			
			//Se obtiene el usuario firmado
			UsuarioDTO usuarioDTO=super.getUsuarioFirmado(request);
			
			Long idAgencia = null;
			String claveAgencia = "---";
			
			if(usuarioDTO != null && usuarioDTO.getFuncionario()!= null && usuarioDTO.getFuncionario().getDiscriminante()!= null){
				idAgencia = usuarioDTO.getFuncionario().getDiscriminante().getCatDiscriminanteId();
				claveAgencia = usuarioDTO.getFuncionario().getDiscriminante().getClave();
			}
			
			//se declara la forma
			IngresarIndividuoForm forma = (IngresarIndividuoForm) form;
			log.debug("forma.getFechaFinLapso() :: "+forma.getFechaFinLapso());
			
			//se asigna el id del involucrado a consultar
			InvolucradoDTO involucradoDTO=new InvolucradoDTO();			
			involucradoDTO.setElementoId(idIndividuo);
			
			//Se consulta el involucrado que se enviara en el aviso de detencion
			involucradoDTO=involucradoDelegate.obtenerInvolucrado(involucradoDTO);
			//Se asigna el expediente al involucrado consultado
			involucradoDTO.setExpedienteDTO(expedienteDTO);
			involucradoDTO.setUsuario(usuarioDTO);
			//Se setean los valores al objeto detencion que se enviara como aviso de detencion
			DetencionDTO detencionDTO=new DetencionDTO();
			detencionDTO.setDetencionId(idDetencion);
			detencionDTO.setUsuario(usuarioDTO);
			detencionDTO.setInvolucradoDTO(involucradoDTO);
			detencionDTO.setFechaInicioDetencion(DateUtils.obtener(forma.getFechaInicioLapso(), forma.getHoraInicioLapso()));
			detencionDTO.setFechaRecepcionDetencion(DateUtils.obtener(forma.getFechaFinLapso(), forma.getHoraFinLapso()));
			//Envia a servicio el aviso
			avisoDetencionDelegate.enviarAvisoDetencion(detencionDTO,idAgencia,claveAgencia,
					(usuarioDTO.getFuncionario()!= null ? usuarioDTO.getFuncionario().getClaveFuncionario(): null)
					);
			
			String xml = "";
			if (log.isDebugEnabled()) {
				log.debug(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	
	public ActionForward obtenerNumeroCasoPorExpedienteCarpeta(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			
			String numExpediente = request.getParameter("numExpediente");
			log.info("numExpedienteid====" + numExpediente);
			
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			
			expedienteDTO.setExpedienteId(Long.parseLong(numExpediente));
			
			CasoDTO casoDTO = new CasoDTO();
			casoDTO = casoDelegate.consultarCasoPorExpediente(expedienteDTO);
		
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("CasoDTO", CasoDTO.class);
			xml = converter.toXML(casoDTO);
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
	
	public ActionForward obtenerConfActividadDocumento(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando Action AtencionTempranaPenalAction en metodo obtenerConfActividadDocumento");
			String idConf = request.getParameter("idConf");
			log.info("numExpedienteid====" + idConf);
			ConfActividadDocumentoDTO confActividadDocumentoDTO=null;
				if(idConf!=null && !idConf.equals("")){
					confActividadDocumentoDTO=confActividadDocumentoDelegate.consultaConfActividadDocumentoPorId(Long.parseLong(idConf));
				}
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo registraActividadExpediente el Estatus es:"+confActividadDocumentoDTO.getEstadoCambioExpediente());
			String xml = null;
			PrintWriter pw = null;
			converter.alias("confActividadDocumentoDTO", ConfActividadDocumentoDTO.class);
			xml = converter.toXML(confActividadDocumentoDTO);
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
	
	public ActionForward enviarReplicaCaso(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando Action AtencionTempranaPenalAction en metodo enviarReplicaCaso");
			String idExpediente = request.getParameter("idExpediente");
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo enviarReplicaCaso el id de expediente es:"+idExpediente);
			ExpedienteDTO expdienteConCaso=new ExpedienteDTO();
			if(idExpediente!=null && !idExpediente.equals("")){
				expdienteConCaso.setExpedienteId(Long.parseLong(idExpediente));
			}
			casoDelegate.enviarReplicaCaso(expdienteConCaso);
			String xml = null;
			PrintWriter pw = null;
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
	
	
	public ActionForward consultaBitacoraIAM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo consultaBitacoraIAM");
			try {
				String numeroExpediente=request.getParameter("numeroExpediente");
				String tipoElemento=request.getParameter("elemnto");
				Long id=null;
				if(tipoElemento!=null && !tipoElemento.equals("")){
					id=Long.parseLong(tipoElemento);
				}
				List<BitacoraMovimientoDTO> liBitacoraMovimientoDTO=bitacoraMovimientoDelegate.consultarBitacoraMovimientoPorNumeroExpedienteCategoria(numeroExpediente, id);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=0;
				if(liBitacoraMovimientoDTO!=null){
					lTotalRegistros=liBitacoraMovimientoDTO.size();
				}
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (BitacoraMovimientoDTO bitacoraMovimientoDTO : liBitacoraMovimientoDTO) {
					writer.print("<row id='"+bitacoraMovimientoDTO.getBitacoraMovimientoId()+"'>");
					String fecha="";
					String hora="";
					if(bitacoraMovimientoDTO.getFechaMovimiento()!=null){
						fecha=DateUtils.formatear(bitacoraMovimientoDTO.getFechaMovimiento());
						hora=DateUtils.formatearHora(bitacoraMovimientoDTO.getFechaMovimiento());
					}
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+fecha+"</div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+hora+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+bitacoraMovimientoDTO.getCategoriaElemento().getNombre()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+bitacoraMovimientoDTO.getElemento()+" </div]]></cell>");
//					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+"Expedientes"+"</div]]></cell>");
//					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+"1234"+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+bitacoraMovimientoDTO.getAccion()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+bitacoraMovimientoDTO.getUsuario().getClaveUsuario()+"</div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+bitacoraMovimientoDTO.getUsuario().getFuncionario().getNombreCompleto()+" </div]]></cell>");
					writer.print("</row>");	
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			log.info("CONSULTA_DELITOS_POR_DELITOS");
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	public ActionForward consultaBitacoraINQ(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo consultaBitacoraINQ");
			try {
				String numeroExpediente=request.getParameter("numeroExpediente");
				String fecha=request.getParameter("fechaInicio");
				String hora=request.getParameter("horaInicio");
				String nombre=request.getParameter("nombreFuncionario");
				FiltroBitacoraConsultaDTO filtroBitacoraConsultaDTO=new FiltroBitacoraConsultaDTO();
				if((fecha!=null && !fecha.equals(""))&&(hora!=null && !hora.equals(""))){
					Date fechaConsulta=DateUtils.obtener(fecha, hora);
					filtroBitacoraConsultaDTO.setFechaConsulta(fechaConsulta);
					filtroBitacoraConsultaDTO.setHoraConsulta(fechaConsulta);
				}
				filtroBitacoraConsultaDTO.setNumeroExpediente(numeroExpediente);
				FuncionarioDTO funcionario=new FuncionarioDTO();
				funcionario.setNombreFuncionario(nombre);
				filtroBitacoraConsultaDTO.setFuncionario(funcionario);
				List<BitacoraConsultaDTO> listBitacoraConsultaDTO=bitacoraConsultaDelegate.consultarBitacoraConsultaPorFiltro(filtroBitacoraConsultaDTO);
				
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=0;
				if(listBitacoraConsultaDTO!=null){
					lTotalRegistros=listBitacoraConsultaDTO.size();
				}
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (BitacoraConsultaDTO bitacoraConsultaDTO : listBitacoraConsultaDTO) {
					writer.print("<row id='"+bitacoraConsultaDTO.getBitacoraConsultaId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+bitacoraConsultaDTO.getNumeroExpediente()+"</div]]></cell>");
					String fe=DateUtils.formatear(bitacoraConsultaDTO.getFechaConsulta());
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+fe+" </div]]></cell>");
					String hor=DateUtils.formatearHora(bitacoraConsultaDTO.getFechaConsulta());
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+hor+"</div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+bitacoraConsultaDTO.getUsuario().getClaveUsuario()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+bitacoraConsultaDTO.getUsuario().getFuncionario().getNombreCompleto()+" </div]]></cell>");
					String es="";
					if(bitacoraConsultaDTO.getEsPermitida()){
						es="SI";
					}else{
						es="NO";
					}
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+es+"</div]]></cell>");
					writer.print("</row>");	
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			log.info("CONSULTA_DELITOS_POR_DELITOS");
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	public ActionForward consultaBitacoraMovObjetosAlmacen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo consultaBitacoraMovObjetosAlmacen");
			try {
//				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
				String numeroExpediente=request.getParameter("numeroExpediente");
				log.info("en metodo consultaBitacoraMovObjetosAlmacen numeroExpediente es:"+numeroExpediente);
				List<EvidenciaDTO> listEvidenciaDTO=almacenDelegate.consultarEvidenciasAsociadasCadenaCustodiaPorNumeroExpediente(numeroExpediente);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=0;
				if(listEvidenciaDTO!=null){
					lTotalRegistros=listEvidenciaDTO.size();
				
					writer.print("<records>" + lTotalRegistros + "</records>");	
					
					for (EvidenciaDTO evidenciaDTO : listEvidenciaDTO) {
						writer.print("<row id='"+evidenciaDTO.getEvidenciaId()+"'>");
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+evidenciaDTO.getNumeroEvidencia()+"</div]]></cell>");
						if(evidenciaDTO.getObjeto()!=null){
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+evidenciaDTO.getObjeto().getTipoObjeto().getNombreEntity()+" </div]]></cell>");
							String texto="Desconocido";
							//Columna para mostrar la informacion del objeto
							log.info("$$$$  Tipo Objeto::::: "+evidenciaDTO.getObjeto().getTipoObjeto().toString());
							if(evidenciaDTO.getObjeto().getTipoObjeto().toString().equals("EQUIPO_TELEFONICO"))
							{
								texto=((TelefoniaDTO)evidenciaDTO.getObjeto()).getTipoTelefono().getValor();
							}
							else if(evidenciaDTO.getObjeto().getTipoObjeto().toString().equals("VEHICULO"))
							{
								texto=((VehiculoDTO)evidenciaDTO.getObjeto()).getPlaca() +((VehiculoDTO)evidenciaDTO.getObjeto()).getValorByTipoVehiculo().getValor();
							}
							else if(evidenciaDTO.getObjeto().getTipoObjeto().toString().equals("ARMA"))
							{
								texto=((ArmaDTO)evidenciaDTO.getObjeto()).getTipoArma().getValor();
							}
							else if(evidenciaDTO.getObjeto().getTipoObjeto().toString().equals("EQUIPO_COMPUTO"))
							{
								texto=((EquipoComputoDTO)evidenciaDTO.getObjeto()).getTipoEquipo().getValor();
							}
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+texto+"</div]]></cell>");
						}else{
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Desconicido"+" </div]]></cell>");
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+"Desconicido"+"</div]]></cell>");
						}
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'> "+evidenciaDTO.getDescripcion()+" </div]]></cell>");
//						log.info("$$$$  Este es el delito del PR con id ::::: "+i);
						writer.print("</row>");	
					}
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			log.info("CONSULTA_DELITOS_POR_DELITOS");
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	public ActionForward consultarEslabonesEvidencia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando Action AtencionTempranaPenalAction en metodo consultarEslabonesEvidencia");
			String idEvidencia = request.getParameter("idEvidencia");
			log.info("idEvidencia====" + idEvidencia);
			List<EslabonDTO> listEslabonDTO=new ArrayList<EslabonDTO>();
			if(idEvidencia!=null && !idEvidencia.equals("")){
				EvidenciaDTO evidenciaDTO=new EvidenciaDTO();
				evidenciaDTO.setEvidenciaId(Long.parseLong(idEvidencia));
				listEslabonDTO=eslabonDelegate.consultarEslabonesPorEvidencia(evidenciaDTO);
			}
			String xml = null;
			PrintWriter pw = null;
			converter.alias("listEslabonDTO", ArrayList.class);
			converter.alias("eslabonDTO", EslabonDTO.class);
			xml = converter.toXML(listEslabonDTO);
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
	
	public ActionForward consultarCatalogoElemento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
    	try {
    		log.info("ejecutando Action Cargar Combo Elemento"); 
    		List<CatalogoDTO> listaCatalogo=catalogoDelegate.recuperarCatalogo(Catalogos.TIPO_ELEMENTO);
    		converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catElemnto", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			}
		catch (Exception e) {
			log.info(e);
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
	public ActionForward consultarDocumentosPorOficioInvestigacionPolicial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idExpediente=request.getParameter("idExpedienteop");
			
			log.info("$$$$ ID de Expediente consultarDocumentos $$$ : "+idExpediente);

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			expedienteDTO.setNumeroExpedienteId(Long.parseLong(idExpediente));
			List<DocumentoDTO> listaDocumentoDTOs=documentoDelegate.consultarDocumentosXTipoDocumento(expedienteDTO,TipoDocumento.OFICIO_DE_INVESTIGACION_POLICIAL.getValorId());
			request.getSession().setAttribute("totalRegistrosDocumentos", listaDocumentoDTOs.size());
			request.setAttribute("totalRegistrosDocumentos", listaDocumentoDTOs.size());
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
		
			writer.print("<rows>");
			int lTotalRegistros=listaDocumentoDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");	
			
			for (DocumentoDTO documentoDTO : listaDocumentoDTOs) {				
				writer.print("<row id='"+documentoDTO.getDocumentoId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (documentoDTO.getActividadDTO() != null && documentoDTO.getActividadDTO().getUsuario() != null && documentoDTO.getActividadDTO().getUsuario().getArea()!= null
						&& documentoDTO.getActividadDTO().getUsuario().getArea().getNombre()!= null ? documentoDTO.getActividadDTO().getUsuario().getArea().getNombre():"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (documentoDTO.getActividadDTO() != null && documentoDTO.getActividadDTO().getFechaCreacion() != null? DateUtils.formatear(documentoDTO.getActividadDTO().getFechaCreacion()):"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (documentoDTO.getActividadDTO() != null && documentoDTO.getActividadDTO().getNombre() != null? documentoDTO.getActividadDTO().getNombre():"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getTipoDocumentoDTO().getValor()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getNombreDocumento()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getStrFechaCreacion()+" </div>]]></cell>");
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
	
	public ActionForward consultarAlarmas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
    	try {
    		log.info("ejecutando Action AtencionTempranaPenal metodo consultarAlarmas");
    		UsuarioDTO usuario=super.getUsuarioFirmado(request);
    		List<AlertaDTO> listAlertaDTO=alarmaDelegate.consultarAlertasXUsuario(usuario, EstatusAlarmaAlerta.NO_EJECUTADA);
    		converter.alias("listAlertaDTO", java.util.List.class);
			converter.alias("alertaDTO", AlertaDTO.class);
			for (AlertaDTO alertaDTO : listAlertaDTO) {
				log.info("ejecutando Action AtencionTempranaPenal metodo consultarAlarmas:"+alertaDTO);
			}
			log.info("ejecutando Action AtencionTempranaPenal metodo consultarAlarmas:"+listAlertaDTO.size());
			String xml = converter.toXML(listAlertaDTO);
			log.info("Alarma_atpenal:: "+xml);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			}
		catch (Exception e) {
			log.info(e);
		}
		return null;
	}
	
	
	public ActionForward actualizarAlarma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
    	try {
    		log.info("ejecutando Action AtencionTempranaPenal metodo actualizarAlarma");
    		UsuarioDTO usuario=super.getUsuarioFirmado(request);
    		String estatus=request.getParameter("estatus");
    		String idAlerta=request.getParameter("idAlerta");
    		String lapsoTiempo=request.getParameter("tiempo");
    		String unidadTiempo=request.getParameter("unidad");
    		AlertaDTO alerta=new AlertaDTO();
    		alerta.setAlertaId(Long.parseLong(idAlerta));
    		alerta.setUsuario(usuario);
    		if(estatus.equals("aceptar")){
    			alarmaDelegate.actualizaEstatusyFechaAlerta(alerta, EstatusAlarmaAlerta.EJECUTADA);
    		}else if(estatus.equals("posponer")){
    			log.info("*******unidadTiempo:"+unidadTiempo);
    			log.info("*******lapsoTiempo:"+lapsoTiempo);
    			
    			if((lapsoTiempo!=null && !lapsoTiempo.equals(""))){
    				Integer tiempo=null;
    				if(unidadTiempo.equals("2")){ //horas
    					tiempo = Integer.parseInt(lapsoTiempo)*60;
    				} else { //minutos
    					tiempo = Integer.parseInt(lapsoTiempo);
    				}
    				log.info("*******tiempo pospuesto(mins):"+tiempo);
    				Date fecha=DateUtils.sumarMinutos(new Date(), tiempo);
    				log.info("*******fecha:"+fecha);
    				alerta.setFechaAlerta(fecha);
        		}
    			alarmaDelegate.actualizaEstatusyFechaAlerta(alerta, EstatusAlarmaAlerta.NO_EJECUTADA);
    		}else{
    			alarmaDelegate.actualizaEstatusyFechaAlerta(alerta, EstatusAlarmaAlerta.CANCELADA);
    		}
    		String xml = "ok";
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			}
		catch (Exception e) {
			log.info(e);
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
	public ActionForward solicitarTranscripcionPG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo solicitarTranscripcionPG");
			try {
				String fechaSol=request.getParameter("fechaSol");
				String horaSol = request.getParameter("horaSol");
				String institucionSol = request.getParameter("instSol");
				String nombreSol = request.getParameter("nombreSol");
				String tipoSol = request.getParameter("tipoSol");
				String idAudienciaSol = request.getParameter("idAudienciaSol");
				String numCausaSol = request.getParameter("numCausaSol");
				String numCasoSol = request.getParameter("numCasoSol");
				Long idNumeroExpediente = NumberUtils.toLong(request.getParameter("idNumeroExpediente"),0);
				
				if(log.isDebugEnabled())
				{
					log.info("fechaSol:: "+fechaSol);
					log.info("horaSol:: "+horaSol);
					log.info("institucionSol:: "+institucionSol);
					log.info("nombreSol:: "+nombreSol);
					log.info("tipoSol:: "+tipoSol);
					log.info("idAudienciaSol:: "+idAudienciaSol);
					log.info("numCausaSol:: "+numCausaSol);
					log.info("numCasoSol:: "+numCasoSol);
				}
				
				SolicitudTranscripcionAudienciaDTO solicitudDTO = new SolicitudTranscripcionAudienciaDTO();
	            //Tipo de Solicitud
				solicitudDTO.setTipoSolicitudDTO(new ValorDTO(Long.parseLong(tipoSol)));
	            //Nombre solicitante
				solicitudDTO.setNombreSolicitante(nombreSol);
	            //Audiencia ID
				solicitudDTO.setAudiencia(new AudienciaDTO(idAudienciaSol));
	            //Fecha de creacion
				solicitudDTO.setFechaCreacion(DateUtils.obtener(fechaSol, horaSol));
				//institucionSol
				solicitudDTO.setInstitucion(new ConfInstitucionDTO(Long.parseLong(institucionSol)));
				//numCausaSol
				solicitudDTO.setNumCausa(numCausaSol);
				solicitudDTO.setUsuarioSolicitante(getUsuarioFirmado(request).getFuncionario());
				if(idNumeroExpediente > 0){
					solicitudDTO.setNumeroCasoAsociado(numCasoSol);
					ExpedienteDTO expediente = new ExpedienteDTO();
					expediente.setNumeroExpedienteId(idNumeroExpediente);
					solicitudDTO.setExpedienteDTO(expediente);
				}
				//Mandare la solicitud de transcipcion
				solicitudDelegate.enviarSolicitudDeTranscripcionAAreaExterna(solicitudDTO);
				log.info("Solicite_TranscripcionPG:: solicitarTranscripcionPG");
				
				String xml = "<respuesta><bandera>1</bandera></respuesta>";
				
				escribirRespuesta(response, xml);
			} catch (Exception e) {		
				log.info(e.getCause(),e);
				String xml = "<respuesta><bandera>0</bandera></respuesta>";
				escribirRespuesta(response, xml);
			}
			return null;
	}
	
	public ActionForward consultarRelacionDefensorInvolucrado(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			String idProbableResponsable=request.getParameter("idProbableResponsable");
			log.info("$$$$ numero DE idProbableResponsable$$$ : "+idProbableResponsable);
			Long id=null;
			int posicion=0;
			if(idProbableResponsable!=null && !idProbableResponsable.equals("")){
				id=Long.parseLong(idProbableResponsable);
			}
			List<RelacionDTO> listRelacionDTO=relacionDelegate.consultarRelacionesByComplementoIdAndTipoRelacion(id,(long) Relaciones.ABOGADO.ordinal());
			InvolucradoDTO involucradoDTO=new InvolucradoDTO();	
			log.info("$$$$ numero DE idProbableResponsable$$$ tama�o lista: "+listRelacionDTO.size());
			if(listRelacionDTO!=null && listRelacionDTO.size()!=0){
				
				while(posicion<listRelacionDTO.size() && !listRelacionDTO.get(posicion).getEsActivo().equals(true)){
					posicion++;
				}
				
				if(posicion<listRelacionDTO.size()){
					involucradoDTO.setElementoId(listRelacionDTO.get(posicion).getElementoBySujetoId().getElementoId());
				}
				
				log.info("$$$$ numero DE idProbableResponsable$$$ id de defensor asociado: "+listRelacionDTO.get(0).getElementoBySujetoId().getElementoId());
			}
			CalidadDTO calidadDTO=new CalidadDTO();
			calidadDTO.setCalidades(Calidades.DEFENSOR_PRIVADO);
			involucradoDTO.setCalidadDTO(calidadDTO);
			InvolucradoDTO invo;
			invo=involucradoDelegate.consultarIndividuo(involucradoDTO);
			
			
			if(invo== null ){
				calidadDTO.setCalidades(Calidades.DEFENSOR_PRIVADO);
				involucradoDTO.setCalidadDTO(calidadDTO);
				invo=involucradoDelegate.obtenerInvolucrado(involucradoDTO);
			}else if(invo.getElementoId()==null){
				calidadDTO.setCalidades(Calidades.DEFENSOR_PRIVADO);
				involucradoDTO.setCalidadDTO(calidadDTO);
				invo=involucradoDelegate.obtenerInvolucrado(involucradoDTO);
			}
			invo.setInvolucradoIdDefensor(invo.getElementoId());
//			converter.alias("listaDelitos", java.util.List.class);
			converter.alias("involucradoDTO", InvolucradoDTO.class);
			String xml = converter.toXML(invo);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	public ActionForward consultarAgentesUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			//Representa el id de la Unidad Investigacion especializada 
			Long idUIE = 0L; 
			
			log.info("$$$$ metodo consultarAgentesUI : ");
			FuncionarioDTO filtro=new FuncionarioDTO();
			JerarquiaOrganizacionalDTO jerarquiaOrganizacional=new JerarquiaOrganizacionalDTO(Areas.UNIDAD_INVESTIGACION.parseLong());
			filtro.setJerarquiaOrganizacional(jerarquiaOrganizacional);
			UsuarioDTO usuarioDTO=new UsuarioDTO();
			AreaDTO areaDTO=new AreaDTO(Areas.UNIDAD_INVESTIGACION);
			usuarioDTO.setAreaActual(areaDTO);
			filtro.setUsuario(usuarioDTO);
			DepartamentoDTO departamentoDTO=new DepartamentoDTO();
			departamentoDTO.setArea(areaDTO);
			filtro.setDepartamento(departamentoDTO);
			UsuarioDTO usuario = super.getUsuarioFirmado(request);
			//Se recupera el id de la Unidad de Investigacion especializada
			if(usuario != null && usuario.getFuncionario() != null && usuario.getFuncionario().getUnidadIEspecializada() != null
					&& usuario.getFuncionario().getUnidadIEspecializada().getCatUIEId() != null)
				idUIE = usuario.getFuncionario().getUnidadIEspecializada().getCatUIEId();
			
			log.info("La Unidad Investigacion especializada: " + idUIE);
			
			filtro.setUnidadIEspecializada(idUIE > 0 ? new CatUIEspecializadaDTO(idUIE): null);
			
			List<FuncionarioDTO> listFuncionarioDTO = new ArrayList<FuncionarioDTO>();
			//aplica esto solo a este rol
			if (usuario != null
					&& usuario.getRolACtivo() != null
					&& usuario.getRolACtivo().getRol() != null
					&& usuario.getRolACtivo().getRol().getRolId() != null
					&& Roles.COORDINADORAMP.getValorId() == usuario
							.getRolACtivo().getRol().getRolId()) {
				
				log.info("%&/%% Rol del usuario: " + usuario.getRolPrincipal().getRol().getRolId());
				
				CatDiscriminanteDTO catDiscriminanteDTO = new CatDiscriminanteDTO();
				catDiscriminanteDTO.setCatDiscriminanteId(usuario.getFuncionario().getDiscriminante().getCatDiscriminanteId().longValue());
				listFuncionarioDTO = funcionarioDelegate.consultarFuncionariosPorDicriminanteYRol(
						catDiscriminanteDTO.getCatDiscriminanteId(),Roles.AGENTEMP.getValorId(),(idUIE > 0 ? idUIE: null));
			}else{
				log.info("%&/%% filtro: " + filtro);
				listFuncionarioDTO = solicitudPericialDelegate.consultarFuncionarioPorFiltro(filtro);
			}
			converter.alias("listFuncionarioDTO", java.util.List.class);
			converter.alias("funcionarioDTO", FuncionarioDTO.class);
			String xml = converter.toXML(listFuncionarioDTO);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	public ActionForward consultarAgentesJAR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("$$$$ metodo consultarAgentesUI : ");
			FuncionarioDTO filtro=new FuncionarioDTO();
			JerarquiaOrganizacionalDTO jerarquiaOrganizacional=new JerarquiaOrganizacionalDTO(Areas.FISCAL_FACILITADOR.parseLong());
			filtro.setJerarquiaOrganizacional(jerarquiaOrganizacional);
			UsuarioDTO usuarioDTO=new UsuarioDTO();
			AreaDTO areaDTO=new AreaDTO(Areas.FISCAL_FACILITADOR);
			usuarioDTO.setAreaActual(areaDTO);
			filtro.setUsuario(usuarioDTO);
			DepartamentoDTO departamentoDTO=new DepartamentoDTO();
			departamentoDTO.setArea(areaDTO);
			filtro.setDepartamento(departamentoDTO);
			List<FuncionarioDTO> listFuncionarioDTO=solicitudPericialDelegate.consultarFuncionarioPorFiltro(filtro);
			converter.alias("listFuncionarioDTO", java.util.List.class);
			converter.alias("funcionarioDTO", FuncionarioDTO.class);
			String xml = converter.toXML(listFuncionarioDTO);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * funcion para consultar los datos Audiencias generados para el visor de elementos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarAudiencias(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String numeroExpediente=request.getParameter("numeroExpediente");
			log.info("$$$$ numeroExpediente de Expediente consultarAudiencias $$$ : "+numeroExpediente);
			UsuarioDTO usuarioDTO=super.getUsuarioFirmado(request);
//			List<Long> listEstatus=new ArrayList<Long>();
//			EstatusSolicitud[] arreglo=EstatusSolicitud.values();
//			for (EstatusSolicitud estatusSolicitud : arreglo) {
//				listEstatus.add(estatusSolicitud.getValorId());
//			}
			List<Long> listTipoSolicitud=new ArrayList<Long>();
			listTipoSolicitud.add( TiposSolicitudes.AUDIENCIA.getValorId());
			listTipoSolicitud.add( TiposSolicitudes.TRANSCRIPCION_DE_AUDIENCIA.getValorId());
			listTipoSolicitud.add( TiposSolicitudes.AUDIO_VIDEO.getValorId());
			listTipoSolicitud.add( TiposSolicitudes.MANDATO_JUDICIAL.getValorId());
			listTipoSolicitud.add( TiposSolicitudes.MEDIDAS_ALTERNATIVAS.getValorId());
			listTipoSolicitud.add( TiposSolicitudes.PRISION_PREVENTIVA.getValorId());
			listTipoSolicitud.add( TiposSolicitudes.RECURSO_APELACION.getValorId());
			listTipoSolicitud.add( TiposSolicitudes.BENEFICIO_PRELIBERACION.getValorId());
			List<SolicitudDTO> listSolicitudDTO=solicitudDelegate.consultarSolicitudesGeneradasPorNumeroExpediente(null,listTipoSolicitud, usuarioDTO.getAreaActual().getAreaId(), usuarioDTO.getFuncionario().getClaveFuncionario(), numeroExpediente);
			
			
			

			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
		
			writer.print("<rows>");
			int lTotalRegistros=listSolicitudDTO.size();
			writer.print("<records>" + lTotalRegistros + "</records>");	
			for (SolicitudDTO solicitudDTO : listSolicitudDTO) {
				writer.print("<row id='"+solicitudDTO.getDocumentoId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getFolioSolicitud()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getEstatus().getValor() +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getFechaCierreStr() +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getFechaLimiteStr()+" </div>]]></cell>");
				if(solicitudDTO != null && solicitudDTO.getInstitucion() != null && solicitudDTO.getInstitucion().getNombreInst() != null){
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getInstitucion().getNombreInst()+" </div>]]></cell>");
				}
				else{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"-"+" </div>]]></cell>");
				}
				
				if(solicitudDTO != null && solicitudDTO.getDestinatario() != null && solicitudDTO.getDestinatario().getNombreCompleto() != null){
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getDestinatario().getNombreCompleto()+" </div>]]></cell>");
				}
				else{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"-"+" </div>]]></cell>");
				}
				
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
	 * funcion para mandar a la vista el XML con los objetos del tipo de objeto seleccioando
	 * para llenar el grid en el visor intermedio y ver el grid en cada pesta�a de los objetos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaObjetosGridVisorXTipo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String numeroExpediente=request.getParameter("numeroExpediente");
			Long tipoObjeto = Long.parseLong(request.getParameter("tipoObjeto"));
			
			log.info("$$$$ ID de Expediente consultaObjetosGridVisorXTipo $$$ : "+numeroExpediente +" - tipoObj:: "+tipoObjeto);

			
			ExpedienteDTO expedienteDTO=super.getExpedienteTrabajo(request, numeroExpediente);
			//expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
//			expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
//			//consultamos el expediente
//			expedienteDTO = expedienteDelegate.obtenerExpediente(expedienteDTO); 
			
			List<ObjetoDTO> lista = new ArrayList<ObjetoDTO>();
			
			if(tipoObjeto.longValue() == Objetos.VEHICULO.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.VEHICULO,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.EQUIPO_DE_COMPUTO.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.EQUIPO_DE_COMPUTO,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.ARMA.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.ARMA,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.EQUIPO_TELEFONICO.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.EQUIPO_TELEFONICO,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.EXPLOSIVO.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.EXPLOSIVO,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.SUSTANCIA.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.SUSTANCIA,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.ANIMAL.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.ANIMAL,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.AERONAVE.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.AERONAVE,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.EMBARCACION.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.EMBARCACION,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.INMUEBLE.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.INMUEBLE,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.NUMERARIO.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.NUMERARIO,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.VEGETAL.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.VEGETAL,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.DOCUMENTO_OFICIAL.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.DOCUMENTO_OFICIAL,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.JOYA.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.JOYA,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.OBRA_DE_ARTE.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.OBRA_DE_ARTE,expedienteDTO);
			}
			else if(tipoObjeto.longValue() == Objetos.OTRO.getValorId().longValue())
			{
				lista = objetoDelegate.consultarObjetosPorTipoConFolioDeCustodia(Objetos.OTRO,expedienteDTO);
			}
			
			log.info("tamano lista de objetos GRID visor:: "+lista.size());
			
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

			int k=0;
			//barremos la lista de Objetos
			for (ObjetoDTO objetoDTO : lista) 
			{
				//barremos la lista de los numeros de caso del objeto actual
				for (int i = 0; i < objetoDTO.getNumerosDeCasos().size(); i++) 
				{
					if(i==0)
					{
						//agreagamos el renglon por default
						writer.print("<row id='"+objetoDTO.getElementoId()+"'>");
						if(tipoObjeto.longValue() == Objetos.VEHICULO.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((VehiculoDTO)objetoDTO).getValorByTipoVehiculo().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.EQUIPO_DE_COMPUTO.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((EquipoComputoDTO)objetoDTO).getTipoEquipo().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.ARMA.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((ArmaDTO)objetoDTO).getTipoArma().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.EQUIPO_TELEFONICO.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((TelefoniaDTO)objetoDTO).getTipoTelefono().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.EXPLOSIVO.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((ExplosivoDTO)objetoDTO).getTipoExplosivo().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.SUSTANCIA.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((SustanciaDTO)objetoDTO).getTipoSustancia().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.ANIMAL.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((AnimalDTO)objetoDTO).getTipoAnimal().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.AERONAVE.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((AeronaveDTO)objetoDTO).getTipoAeroNave().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.EMBARCACION.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((EmbarcacionDTO)objetoDTO).getTipoEmbarcacion().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.INMUEBLE.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((InmuebleDTO)objetoDTO).getTipoInmueble().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.NUMERARIO.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((NumerarioDTO)objetoDTO).getMoneda()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.VEGETAL.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((VegetalDTO)objetoDTO).getTipoVegetal().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.DOCUMENTO_OFICIAL.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((DocumentoOficialDTO)objetoDTO).getTipoDocumento().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.JOYA.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((JoyaDTO)objetoDTO).getTipoJoya().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.OBRA_DE_ARTE.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((ObraArteDTO)objetoDTO).getTipoObraArte().getValor()+" </div>]]></cell>");
						}
						else if(tipoObjeto.longValue() == Objetos.OTRO.getValorId().longValue())
						{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+objetoDTO.getNombreObjeto()+" </div>]]></cell>");
						}
						//agregamos el primer registro de la lista
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((objetoDTO.getCadenaDeCustodia()!=null && objetoDTO.getCadenaDeCustodia().getFolio()!=null)?objetoDTO.getCadenaDeCustodia().getFolio():"---")+" </div>]]></cell>");
						if(objetoDTO.getNumerosDeCasos()!=null && objetoDTO.getNumerosDeCasos().get(i)!=null){
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+objetoDTO.getNumerosDeCasos().get(i)+" </div>]]></cell>");
						}
						else{
							writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+"---"+" </div>]]></cell>");
						}
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+objetoDTO.getNumerosDeCasos() +" </div>]]></cell>");
						writer.print("</row>");
					}
					else
					{
						//agregamos el registro iesimo de la lista
						writer.print("<row id='"+objetoDTO.getElementoId()+"'>");
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'></div>]]></cell>");
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'></div>]]></cell>");
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+objetoDTO.getNumerosDeCasos().get(i)+" </div>]]></cell>");
						writer.print("</row>");
					}
				}
				if(objetoDTO.getNumerosDeCasos().size()==0)
				{
					//si la lista de numero de casos fue vacia agregamos el registro por default
					writer.print("<row id='"+objetoDTO.getElementoId()+"'>");
					if(tipoObjeto.longValue() == Objetos.VEHICULO.getValorId().longValue())
					{
					    VehiculoDTO veh = (VehiculoDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(veh.getValorByTipoVehiculo()!=null ? veh.getValorByTipoVehiculo().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.EQUIPO_DE_COMPUTO.getValorId().longValue())
					{
					    EquipoComputoDTO ec = (EquipoComputoDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(ec.getTipoEquipo()!=null?ec.getTipoEquipo().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.ARMA.getValorId().longValue())
					{
					    ArmaDTO arma =(ArmaDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(arma.getTipoArma()!=null?arma.getTipoArma().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.EQUIPO_TELEFONICO.getValorId().longValue())
					{
						TelefoniaDTO eqTel = (TelefoniaDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(eqTel.getTipoTelefono()!=null?eqTel.getTipoTelefono().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.EXPLOSIVO.getValorId().longValue())
					{
						ExplosivoDTO exp = (ExplosivoDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(exp.getTipoExplosivo()!=null?exp.getTipoExplosivo().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.SUSTANCIA.getValorId().longValue())
					{
						SustanciaDTO sus = (SustanciaDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(sus.getTipoSustancia()!=null?sus.getTipoSustancia().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.ANIMAL.getValorId().longValue())
					{
						AnimalDTO animal = (AnimalDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(animal.getTipoAnimal()!=null?animal.getTipoAnimal().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.AERONAVE.getValorId().longValue())
					{
						AeronaveDTO aero = (AeronaveDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(aero.getTipoAeroNave()!=null?aero.getTipoAeroNave().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.EMBARCACION.getValorId().longValue())
					{
						EmbarcacionDTO embar = (EmbarcacionDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(embar.getTipoEmbarcacion()!=null?embar.getTipoEmbarcacion().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.INMUEBLE.getValorId().longValue())
					{
						InmuebleDTO mueble = (InmuebleDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(mueble.getTipoInmueble()!=null?mueble.getTipoInmueble().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.NUMERARIO.getValorId().longValue())
					{
						NumerarioDTO num = (NumerarioDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(num.getMoneda()!=null?num.getMoneda():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.VEGETAL.getValorId().longValue())
					{
						VegetalDTO veg = (VegetalDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(veg.getTipoVegetal()!=null?veg.getTipoVegetal().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.DOCUMENTO_OFICIAL.getValorId().longValue())
					{
						DocumentoOficialDTO docOf = (DocumentoOficialDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(docOf.getTipoDocumento()!=null?docOf.getTipoDocumento().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.JOYA.getValorId().longValue())
					{
						JoyaDTO joya = (JoyaDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(joya.getTipoJoya()!=null?joya.getTipoJoya().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.OBRA_DE_ARTE.getValorId().longValue())
					{
						ObraArteDTO obra = (ObraArteDTO)objetoDTO;
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(obra.getTipoObraArte()!=null?obra.getTipoObraArte().getValor():"--")+" </div>]]></cell>");
					}
					else if(tipoObjeto.longValue() == Objetos.OTRO.getValorId().longValue())
					{
						writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+(objetoDTO.getNombreObjeto()!=null?objetoDTO.getNombreObjeto():"--")+" </div>]]></cell>");
					}
					writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+((objetoDTO.getCadenaDeCustodia() !=null && objetoDTO.getCadenaDeCustodia().getFolio()!=null)?objetoDTO.getCadenaDeCustodia().getFolio():"---")+" </div>]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+"---"+" </div>]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: "+((k%2)==0?"#f2f2f2":"#ffffff")+"; color:#393939;'>"+objetoDTO.getNumerosDeCasos() +" </div>]]></cell>");
					writer.print("</row>");
				}
				k++;
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
	
	
	public ActionForward registraStatusExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo registraStatusExpediente");
			try {
				String estatus=request.getParameter("estatus");
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registraStatusExpediente el estatus es:"+estatus);
				String idExpediente=request.getParameter("idExpediente");
				String idNumeroExpediente=request.getParameter("idNumeroExpediente");
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registraActividadExpediente el id de expediente es:"+idExpediente);
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registraActividadExpediente el idNumeroExpediente es:"+idNumeroExpediente);
				ExpedienteDTO expediente=new ExpedienteDTO();
				if(idExpediente!=null && !idExpediente.equals("")){
					expediente.setExpedienteId(Long.parseLong(idExpediente));
				}
				if(idNumeroExpediente!=null && !idNumeroExpediente.equals("")){
					expediente.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
				}
				if(estatus!=null && !estatus.equals("")){
					ValorDTO valorDTO=new ValorDTO();
					valorDTO.setIdCampo(Long.parseLong(estatus));
					expediente.setEstatus(valorDTO);
				}
				expedienteDelegate.actualizarEstatusExpediente(expediente);
				converter.alias("expedienteDTO", ExpedienteDTO.class);
				String xml = converter.toXML(expediente);
				if(log.isDebugEnabled())
				{
					log.info(xml);
				}
				escribirRespuesta(response, xml);
			} catch (Exception e) {		
				log.error(e.getMessage(),e);
			}
			return null;
	}
	
	
	public ActionForward busquedaCanalizadosRestaurativaStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaCanalizadosRestaurativaStatus");
			try {
				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
				
				String estatus = request.getParameter("estatus");
				String activaExpedienteId = request.getParameter("activaExpedienteId");
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaCanalizadosRestaurativaStatus: "+estatus );
				Long statusExpe=EstatusExpediente.ABIERTO.getValorId();
				if(estatus!=null && !estatus.equals("")){
					statusExpe=Long.parseLong(estatus);
				}
//				usuarioDTO.setAreaActual(new AreaDTO(Areas.UNIDAD_INVESTIGACION));
				List<ExpedienteDTO> listaExpedienteDTOs=expedienteDelegate.consultarExpedientesPorUsuarioAreaEstatus(usuarioDTO, statusExpe);
//				List<ExpedienteDTO> listaExpedienteDTOs=expedienteDelegate.consultarExpedienteActividadAreaAnio(filtroExpedienteDTO);
				if (log.isDebugEnabled()) {
					log.debug("##################lista de Casos:::::::::" + listaExpedienteDTOs.size());
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
				
				for (ExpedienteDTO expedienteDTO : listaExpedienteDTOs) {
					
					if( activaExpedienteId != null && activaExpedienteId.equals("true")){
						writer.print("<row id='"+expedienteDTO.getExpedienteId()+"'>");
					}else{
						writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+"'>");
					}
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getNumeroExpediente()+" </div]]></cell>");

					if(expedienteDTO.getOrigen() != null && expedienteDTO.getOrigen().getValor() != null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getOrigen().getValor()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ "---" +" </div]]></cell>");
					}
						
					if(expedienteDTO.getStrFechaApertura()==null)
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
					}
					else
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getStrFechaApertura()+" </div]]></cell>");
					}
					log.info("Este es el expediente con calidad de denunciante"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE));
					log.info("invol tama�o"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
					log.info("invol tama�o de"+expedienteDTO.getInvolucradosDTO().size());
					 boolean op=true;
					for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE)) {
						log.info("numero de involucrado nombre completo perdon:"+involucradoDTO.getNombreCompleto());
						for (NombreDemograficoDTO nombreDemograficoDTO : involucradoDTO.getNombresDemograficoDTO()) {
							log.info("Verdadero nombre:"+nombreDemograficoDTO.getEsVerdadero());
							if(nombreDemograficoDTO.getEsVerdadero()!=null && nombreDemograficoDTO.getEsVerdadero()){
								writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+nombreDemograficoDTO.getNombre()+" "+nombreDemograficoDTO.getApellidoPaterno()+" "+nombreDemograficoDTO.getApellidoMaterno() +" </div]]></cell>");
								op=false;
							}
						}
						
					}
					if(op){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An�nimo"+" </div]]></cell>");
					}
					DelitoDTO delito=expedienteDTO.getDelitoPrincipal();
					if(delito!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+delito.getCatDelitoDTO().getNombre()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Sin Delito"+" </div]]></cell>");
					}
					if(expedienteDTO.getOrigen()!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getOrigen().getValor()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Denuncia"+" </div]]></cell>");
					}
						
					log.info("etapa ex padre"+expedienteDTO.getEstatusExpedientePadre());
					if(expedienteDTO.getEstatusExpedientePadre()!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getEstatusExpedientePadre().getValor()+" </div]]></cell>"); log.info("etapa ex padre"+expedienteDTO.getEstatusExpedientePadre());
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Desconocido"+" </div]]></cell>");
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
	
	
	public ActionForward registrafuncionarioNumeroExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo registrafuncionarioNumeroExpediente");
			try {
				String funcionario=request.getParameter("funcionario");
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registrafuncionarioNumeroExpediente el funcionario es:"+funcionario);
				String idNumeroExpediente=request.getParameter("idNumeroExpediente");
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo registrafuncionarioNumeroExpediente el idNumeroExpediente es:"+idNumeroExpediente);
				List<Long> lisLongs=new ArrayList<Long>();
				if(idNumeroExpediente!=null &&!idNumeroExpediente.equals("")){
					lisLongs.add(Long.parseLong(idNumeroExpediente));
				}
				if(funcionario!=null &&!funcionario.equals("")){
					expedienteDelegate.asociarExpedientesAFuncionario(lisLongs, Long.parseLong(funcionario));
				}		

				String xml = "<respuesta><bandera>1</bandera></respuesta>";
				if(log.isDebugEnabled())
				{
					log.info(xml);
				}
				escribirRespuesta(response, xml);
			} catch (Exception e) {		
				log.error(e.getMessage(),e);
				String xml = "<respuesta><bandera>0</bandera></respuesta>";
				escribirRespuesta(response, xml);
			}
			return null;
	}
	
	/***
	 * Funcion para regresar la hora y fecha del servidor
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward regresaFechaYHoraDelServidor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			try {
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo regresaFechaYHoraDelServidor");
				Date fecha = new Date();
				if(log.isDebugEnabled())
				{
					log.info("fecha_hora_server:: "+DateUtils.formatearBD120(fecha));
				}
				escribirRespuesta(response, "<fecha>"+DateUtils.formatearBD120(fecha)+"</fecha>");
			} catch (Exception e) {		
				log.error(e.getMessage(),e);
			}
			return null;
	}
	
	
	
	public ActionForward consultarInvolucradosExpedienteUAVD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			log.info("$$$$ consultarInvolucradosExpedienteUAVD $$$ : ");
			String idNumeroExpediente=request.getParameter("idNumeroExpediente");
			log.info("$$$$ numero DE Expediente consultar involucrados $$$ : "+idNumeroExpediente);

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.COORDINACION_ATENCION_VICTIMAS));
			expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
			expedienteDTO.setInvolucradosSolicitados(true);
			expedienteDTO = expedienteDelegate.obtenerExpediente(expedienteDTO);
				super.setExpedienteTrabajo(request, expedienteDTO);
			List<InvolucradoViewDTO> listaInvolucrados=new ArrayList<InvolucradoViewDTO>();

			for(int i=0;i<expedienteDTO.getInvolucradosDTO().size(); i++){
				InvolucradoDTO involucrado = expedienteDTO.getInvolucradosDTO().get(i);
				InvolucradoViewDTO involucradoView = new InvolucradoViewDTO();
				log.info("$$$$ Calidad a pintar del involucrado : "+involucrado.getCalidadDTO().getValorIdCalidad().getIdCampo());
				involucradoView.setCalidad((involucrado.getCalidadDTO().getValorIdCalidad().getIdCampo()).toString());
				log.info("&&&& Condicion del invoolucrado:"+involucrado.getCondicion());
				log.info("&&&& Condicion del invoolucrado:"+involucrado.getCalidadDTO().getValorIdCalidad().getIdCampo());
					if(involucrado.getCondicion()!= null && involucrado.getCondicion()==(short)1){
						log.info("#################################33333");
						involucradoView.setEsVictima(true);
					}
				
				log.info("$$$$ id a pintar del involucrado : "+involucrado.getElementoId());
				involucradoView.setInvolucradoId(involucrado.getElementoId());
				if(involucrado.getTipoPersona().equals(0L) && !(involucrado.getCalidadDTO().getValorIdCalidad().getIdCampo().equals(Calidades.TRADUCTOR.getValorId()))){
					log.info("$$$$ nombre de la organisacion a pintar del involucrado : "+involucrado.getOrganizacionDTO().getNombreOrganizacion());
					involucradoView.setNombre(involucrado.getOrganizacionDTO().getNombreOrganizacion());
				}else{
					if(involucrado.getCalidadDTO().getValorIdCalidad().getIdCampo().equals(Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId())){
						involucradoView.setNombre(involucrado.getNombreCompleto()+" - "+involucrado.getValorSituacionJuridica().getValor());
						log.info("PROBABLE RESPONSABLE::::: "+involucradoView.getNombre());
					}else{
						involucradoView.setNombre(involucrado.getNombreCompleto());
					}
					log.info("PROBABLE RESPONSABLE::::: "+involucrado.getCalidadDTO().getValorIdCalidad().getIdCampo()+"::::"+Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId());
					log.info("PROBABLE RESPONSABLE::::: "+involucradoView.getNombre());
				}
				listaInvolucrados.add(involucradoView);
			}
			log.info("$$$$ numero el Expediente consultar involucrados  listaInvolucrados.size()$$$ : "+listaInvolucrados.size());
			
			converter.alias("listaInvolucrados", java.util.List.class);
			converter.alias("involucradoViewDTO", InvolucradoViewDTO.class);
			
			String xml = converter.toXML(listaInvolucrados);

//			if(expedienteDTO.getDocumentosDTO() != null && !expedienteDTO.getDocumentosDTO().isEmpty()){
//				List<DocumentoDTO> listaDocumentoDTOs=new ArrayList<DocumentoDTO>();
//				listaDocumentoDTOs=expedienteDTO.getDocumentosDTO();
//
//				converter.alias("listaDocumentoDTOs", java.util.List.class);
//				converter.alias("documentoDTO", DocumentoDTO.class);
//				xml += converter.toXML(listaDocumentoDTOs);
//				
//			}
    		
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			pw.print(xml);
			pw.flush();
			pw.close();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	
	public ActionForward consultarDocumentosSSPCautelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idExpediente=request.getParameter("idExpedienteop");
			
			log.info("$$$$ ID de Expediente consultarDocumentos $$$ : "+idExpediente);

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.UNIDAD_CAPTURA_SSP));
			expedienteDTO.setNumeroExpedienteId(Long.parseLong(idExpediente));
			List<DocumentoDTO> listaDocumentoDTOs=documentoDelegate.consultarDocumentosExpediente(expedienteDTO);
			request.getSession().setAttribute("totalRegistrosDocumentos", listaDocumentoDTOs.size());
			request.setAttribute("totalRegistrosDocumentos", listaDocumentoDTOs.size());
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
		
			writer.print("<rows>");
			int lTotalRegistros=listaDocumentoDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");	
			
			for (DocumentoDTO documentoDTO : listaDocumentoDTOs) {
				writer.print("<row id='"+documentoDTO.getDocumentoId()+"'>");
			
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getNombreDocumento()+" </div>]]></cell>");
				
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
	
	
	
	public ActionForward busquedaInicialTurnosGridSinFecha(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaInicialTurnosGrid");
			try {
				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
//				usuarioDTO.setAreaActual(new AreaDTO(Areas.ATENCION_TEMPRANA_PG));
				log.info("ejecutando Action AtencionTempranaPenalAction en metodo busquedaInicialTurnosGrid:#####"+turnoDelegate);
				List<TurnoDTO> listTurnoDTOs=turnoDelegate.consultarTurnosAtendidosPorUsuario(usuarioDTO,false);
				if (log.isDebugEnabled()) {
					log.debug("##################lista de Casos:::::::::" + listTurnoDTOs.size());
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
				for (TurnoDTO turnoDTO : listTurnoDTOs) {
					ExpedienteDTO expedienteDTO=turnoDTO.getExpediente();
					log.info("caso en expediente art" + expedienteDTO.getCasoDTO().getNumeroGeneralCaso());
					writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getNumeroExpediente()+" </div]]></cell>");
					
					if(expedienteDTO.getOrigen() != null && expedienteDTO.getOrigen().getValor() != null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getOrigen().getValor()+" </div]]></cell>");
					}else{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ "---" +" </div]]></cell>");
					}					
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getStrFechaApertura()+" </div]]></cell>");
					log.info("Este es el expediente con calidad de denunciante"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE));
					log.info("invol tama�o"+expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
					log.info("invol tama�o de"+expedienteDTO.getInvolucradosDTO().size());
					 boolean op=true;
					for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE)) {
						log.info("numero de involucrado nombre completo perdon:"+involucradoDTO.getNombreCompleto());
								if(involucradoDTO.getNombreCompleto()!= null && !involucradoDTO.getNombreCompleto().equals("")){
									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getNombreCompleto() +" </div]]></cell>");
								}else{
									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An�nimo"+" </div]]></cell>");
								}
								
								op=false;
						
					}
					if(op){
						boolean op2=true;
						for (InvolucradoDTO involucradoDTO : 	expedienteDTO.getInvolucradoByCalidad(Calidades.DENUNCIANTE_ORGANIZACION)) {
							log.info("numero de involucrado nombre completo de organizacion:"+involucradoDTO.getOrganizacionDTO().getNombreOrganizacion());
									writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+involucradoDTO.getOrganizacionDTO().getNombreOrganizacion() +" </div]]></cell>");
									op2=false;
							
						}
						if(op2)
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"An�nimo"+" </div]]></cell>");
					}
					DelitoDTO delito=expedienteDTO.getDelitoPrincipal();
					if(delito!=null){
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+delito.getCatDelitoDTO().getNombre()+" </div]]></cell>");
					}else{	
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Sin delito"+" </div]]></cell>");
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
	 * Permite saber si existe un delito grave o no en un numero de expediente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return true o false
	 * @throws IOException
	 */
	public ActionForward existeDelitoGravePorIdExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			
			log.info("$$$$ numero DE Expediente consultar delito por expediente $$$ : ");
			
			String idNumeroExpediente=request.getParameter("idNumeroExpediente");
			log.info("idNumeroExpediente" + idNumeroExpediente);
			
			String numeroExpedienteId = request.getParameter("numeroExpedienteId");
			log.info("numeroExpedienteId" + numeroExpedienteId);			

			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			if(numeroExpedienteId != null && StringUtils.isNotEmpty(numeroExpedienteId) && !numeroExpedienteId.equals("null")){
				expedienteDTO.setNumeroExpedienteId(Long.parseLong(numeroExpedienteId));			}
			if(StringUtils.isNotEmpty(idNumeroExpediente) && !idNumeroExpediente.equals("null")){
				expedienteDTO.setExpedienteId(Long.parseLong(idNumeroExpediente));
				
			}
			expedienteDTO = expedienteDelegate.obtenerExpediente(expedienteDTO);
			
			List<DelitoDTO> listaDelitos=new ArrayList<DelitoDTO>();
			Boolean existeDelitoGrave = null;
						
			//Consulta la lista de delitos asociada al expediente
			if(StringUtils.isNotEmpty(idNumeroExpediente) && !idNumeroExpediente.equals("null")){
				expedienteDTO.setExpedienteId(Long.parseLong(idNumeroExpediente));
				
			}
			log.info("El valor antes de consultar los delitos" + expedienteDTO.getExpedienteId());

			listaDelitos=delitoDelegate.consultarDelitosExpediente(expedienteDTO);
    
			if(listaDelitos != null && listaDelitos.size() > 0){
				existeDelitoGrave = false;
				for (DelitoDTO loDelito : listaDelitos) {
					//Formatea la clasificacion del delito
					if(loDelito.getCatDelitoDTO().getEsGrave()==true)
							existeDelitoGrave = true;
				}
			}
				
			log.info("El valor que debe de regresar en existeDelitoGrave es:" + existeDelitoGrave + ".");
			
			converter.alias("existeDelitoGrave", java.lang.Boolean.class);
			String xml = converter.toXML(existeDelitoGrave);
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

	/**
	 * Permite saber si los delitos de un expediente exceden de la media aritmetica permitida
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return true o false
	 * @throws IOException
	 */
	public ActionForward excedeMediaAritmeticaDelitos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
	
		try {
			
			log.info("$$$$ numero de Expediente ID a consultar : ");
			
			Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"),0L);
			log.info("numeroExpedienteId " + numeroExpedienteId);
			Boolean respuesta = null;
			
			if(numeroExpedienteId > 0L){
				ExpedienteDTO expediente = new ExpedienteDTO();
				expediente.setNumeroExpedienteId(numeroExpedienteId);

				respuesta = delitoDelegate.consultarMediaAritmeticaDelitosExpediente(expediente);

			}
			
			if(respuesta!=null){
				log.info("respuesta: " + respuesta);
			}else{
				log.info("respuesta: NULO");
			}
			converter.alias("existeDelitoGrave", java.lang.Boolean.class);
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
