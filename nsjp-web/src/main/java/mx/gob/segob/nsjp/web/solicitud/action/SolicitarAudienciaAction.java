package mx.gob.segob.nsjp.web.solicitud.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.actuaciones.ActuacionesDelegate;
import mx.gob.segob.nsjp.delegate.caso.CasoDelegate;
import mx.gob.segob.nsjp.delegate.configuracion.ConfiguracionDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.solicitud.form.SolicitudAudienciaForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jacob Lobaco
 */
public class SolicitarAudienciaAction extends GenericAction {

    private static final Logger logger =
            Logger.getLogger(SolicitarAudienciaAction.class);
    @Autowired
    private InvolucradoDelegate involucradoDelegate;
    @Autowired
    private ActuacionesDelegate actuacionesDelegate;
    @Autowired
    private CasoDelegate casoDelegate;
    @Autowired
    private ExpedienteDelegate expedienteDelegate;
    @Autowired
	private ConfiguracionDelegate  configuracionDelegate;

    /**
     * Obtiene el "numeroeExpediente" del expediente que se esta trabajando en
     * sesion.
     * @param request
     * @return
     */
    private String obtenNumeroExpediente(HttpServletRequest request) {
        String numeroExpediente = (String) request.getParameter("numeroExpediente");
        if (logger.isDebugEnabled()) {
            logger.debug("numeroExpediente = " + numeroExpediente);
        }
        return numeroExpediente;
    }

    /**
     * Obtiene el caso asociado al expediente que esta en sesion.
     * @param request
     * @return
     * @throws NSJPNegocioException
     */
    private CasoDTO obtenerCasoPorNumeroExpediente(HttpServletRequest request)
            throws NSJPNegocioException {
        String numeroExpediente = obtenNumeroExpediente(request);
        ExpedienteDTO expedienteDTO = getExpedienteTrabajo(request, numeroExpediente);
        if (logger.isDebugEnabled()) {
            logger.debug("expedienteDTO.getExpedienteId() = " + expedienteDTO.getExpedienteId());
        }
        return expedienteDTO.getCasoDTO();
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
    public ActionForward consultarCasoPorExpediente(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("consultarCasoPorExpediente");
            }
            // Consultamos el caso asociado al expediente que esta en sesion.
            CasoDTO casoDTO = obtenerCasoPorNumeroExpediente(request);
            if (casoDTO == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("No existe una caso asociado al expediente = "
                            + obtenNumeroExpediente(request));
                }
                throw new NSJPNegocioException(CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO);
            } else {
                String xml = converter.toXML(casoDTO);
                escribirRespuesta(response, xml);
                response.setContentType("text/xml");
                PrintWriter pw = response.getWriter();
                pw.print(xml);
                pw.flush();
                pw.close();
            }
        } catch (NSJPNegocioException ex) {
            logger.error("Ocurrio un error en consultarCasoPorExpediente", ex);
            escribirError(response, ex);
        }
        return null;
    }

    public ActionForward consultarInvolucradoPorCalidadCaso(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("consultarInvolucradoPorCalidadCaso");
            }
            // De nuevo obtenemos el caso del expediente en session.
            CasoDTO casoDTO = obtenerCasoPorNumeroExpediente(request);
            if (casoDTO == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("No existe una caso asociado al expediente = "
                            + obtenNumeroExpediente(request));
                }
                throw new NSJPNegocioException(CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO);
            } else {
                CalidadDTO calidadDTO = new CalidadDTO();
                // Consultamos los involucrados que sean probables responsables.
                calidadDTO.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
                calidadDTO.setCalidadId(Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId());
                List<InvolucradoDTO> involucradosProbablesResponsables =
                        involucradoDelegate.consultarInvolucradoPorCalidadCaso(casoDTO, calidadDTO);
                // Consultamos a los involucrados que sean victimas.
                calidadDTO.setCalidades(Calidades.VICTIMA_PERSONA);
                calidadDTO.setCalidadId(Calidades.VICTIMA_PERSONA.getValorId());
                List<InvolucradoDTO> involucradosVictimas =
                        involucradoDelegate.consultarInvolucradoPorCalidadCaso(casoDTO, calidadDTO);
                
                //Consultar los Denunciantes que son víctimas
                calidadDTO.setCalidades(Calidades.DENUNCIANTE);
                calidadDTO.setCalidadId(Calidades.DENUNCIANTE.getValorId());
                List<InvolucradoDTO> involucradosDenunciantesVictimas =
                        involucradoDelegate.consultarInvolucradoPorCalidadCaso(casoDTO, calidadDTO);
                for (InvolucradoDTO involucradoDTO : involucradosDenunciantesVictimas) {
                	//Validar si es víctima
                	if(involucradoDTO.getCondicion().equals(new Short("1"))){
                		involucradosProbablesResponsables.add(involucradoDTO);
                	}
				}
                converter.alias("involucrado", InvolucradoDTO.class);
                converter.alias("nombreDemografico", NombreDemograficoDTO.class);
                // Los juntamos en una sola lista que sera filtrada en la vista
                // con javascript
                involucradosProbablesResponsables.addAll(involucradosVictimas);
                if (logger.isDebugEnabled()) {
                    logger.debug("involucradosProbablesResponsables.size() = "
                            + involucradosProbablesResponsables.size());
                }
                String xml = converter.toXML(involucradosProbablesResponsables);
                if (logger.isDebugEnabled()) {
                    logger.debug("involucrados = " + xml);
                }
                escribirRespuesta(response, xml);
                response.setContentType("text/xml");
                PrintWriter pw = response.getWriter();
                pw.print(xml);
                pw.flush();
                pw.close();
            }
        } catch (NSJPNegocioException ex) {
            logger.error("Ocurrio un error al consultarInvolucradoPor"
                    + "CalidadCaso", ex);
            escribirError(response, ex);
        }
        return null;
    }

    public ActionForward validarExisteCaso(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String numeroDeCaso = request.getParameter("numeroDeCaso");
            if (logger.isDebugEnabled()) {
                logger.debug("numeroDeCaso = " + numeroDeCaso);
            }
            if (numeroDeCaso != null) {
                CasoDTO casoDTO = new CasoDTO();
                casoDTO.setNumeroGeneralCaso(numeroDeCaso);
                List<CasoDTO> casos = casoDelegate.consultarCasoPorNumero(casoDTO);
                Boolean existeCaso = casos != null && casos.size() > 0;
                String existeCasoXML = converter.toXML(existeCaso);
                escribirRespuesta(response, existeCasoXML);
            }
        } catch (NSJPNegocioException ex) {
            logger.error("Ocurrio un error en validarExisteCaso", ex);
            escribirError(response, ex);
        }
        return null;
    }
    /**
     * Envía la solicutd de audiencia.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward enviarSolicitudAudiencia(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("enviarSolicitudAudiencia");
        }
        if (form != null) {
            try {
                SolicitudAudienciaForm forma = (SolicitudAudienciaForm) form;
                if (logger.isDebugEnabled()) {
                    logger.debug("Se obtuvo la forma = " + forma);
                }
                SolicitudAudienciaDTO solicitudAudienciaDTO = new SolicitudAudienciaDTO();
                AudienciaDTO audienciaDTO = new AudienciaDTO();
                ValorDTO tipoAudienciaDto = new ValorDTO();
                if (logger.isDebugEnabled()) {
                    logger.debug("forma.getTipoDeAudiencia() = " + forma.getTipoDeAudiencia());
                }
                tipoAudienciaDto.setIdCampo(forma.getTipoDeAudiencia());
                audienciaDTO.setTipoAudiencia(tipoAudienciaDto);
                solicitudAudienciaDTO.setAudiencia(audienciaDTO);
                solicitudAudienciaDTO.setTipoSolicitudDTO(new ValorDTO(TiposSolicitudes.AUDIENCIA.getValorId()));
                AreaDTO areaActual = getUsuarioFirmado(request).getAreaActual();
                if (areaActual != null) {
                    solicitudAudienciaDTO.setAreaOrigen(areaActual.getAreaId());
                }
                UsuarioDTO usuarioFirmado = getUsuarioFirmado(request);
                if (usuarioFirmado.getFuncionario() != null
                 && usuarioFirmado.getFuncionario().getDepartamento() != null
                 && usuarioFirmado.getFuncionario().getDepartamento().getArea() != null) {
                    forma.setInstitucionSolicitante(usuarioFirmado.getFuncionario().
                            getDepartamento().getArea().getAreaId());
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("forma.getInstitucionSolicitante() = "
                            + forma.getInstitucionSolicitante());
                }
                solicitudAudienciaDTO.setInstitucion(null);
                solicitudAudienciaDTO.setMotivo(forma.getFundamentoDeLaSolicitud());
                if (logger.isDebugEnabled()) {
                    logger.debug("forma.getFundamentoDeLaSolicitud() = "
                            + forma.getFundamentoDeLaSolicitud());
                }
                solicitudAudienciaDTO.setNombreSolicitante(forma.getNombreDelSolicitante());
                if (logger.isDebugEnabled()) {
                    logger.debug("forma.getNombreDelSolicitante() = "
                            + forma.getNombreDelSolicitante());
                }
                solicitudAudienciaDTO.setNumeroCasoAsociado(forma.getNumeroDeCaso());
                if (logger.isDebugEnabled()) {
                    logger.debug("forma.getNumeroDeCaso() = "
                            + forma.getNumeroDeCaso());
                }
                solicitudAudienciaDTO.setStrFechaCreacion(new Date() + "");
                solicitudAudienciaDTO.setStrHoraCreacion(System.currentTimeMillis() + "");
                solicitudAudienciaDTO.setStrFechaLimite(forma.getFechaLimiteAudiencia());
                // En la fecha de creacion va la fecha y la hora
                solicitudAudienciaDTO.setFechaCreacion(new Date());
                // en la fecha limite va la fecha y la hora hh:mm
                // 15/6/2011 14:22
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String fechaCompleta = forma.getFechaLimiteAudiencia() + " "+ forma.getHoraLimiteAudiencia();
                try {
                    Date fechaLimite = df.parse(fechaCompleta);
                    solicitudAudienciaDTO.setFechaLimite(fechaLimite);
                    if (logger.isDebugEnabled()) {
                        logger.debug("fechaLimite = " + fechaLimite);
                    }
                } catch (ParseException ex) {
                    logger.error("Error de formato (dd/MM/yyyy HH:mm) con la fecha: "+ fechaCompleta);
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("forma.getFechaLimiteAudiencia() = "+ forma.getFechaLimiteAudiencia());
                }
                solicitudAudienciaDTO.setStrHoraLimite(forma.getHoraLimiteAudiencia());
                if (logger.isDebugEnabled()) {
                    logger.debug("forma.getHoraLimiteAudiencia() = "+ forma.getHoraLimiteAudiencia());
                }
                solicitudAudienciaDTO.setSolicitanteExterno(usuarioFirmado.getFuncionario().getClaveFuncionario());
                solicitudAudienciaDTO.setUsuarioSolicitante(usuarioFirmado.getFuncionario());
                
                /*Obtener expediente de sesión*/
                ExpedienteDTO expedienteDTO=new ExpedienteDTO();
//                expedienteDTO.setExpedienteId(NumberUtils.toLong(forma.getIdExpedienteSoli(),0));
                if(forma.getIdNumeroExpediente() != null){
//                	expedienteDTO.setNumeroExpedienteId(NumberUtils.toLong(forma.getIdNumeroExpediente(),0));
                	expedienteDTO.setNumeroExpedienteId(Long.parseLong(forma.getIdNumeroExpediente()));
                	expedienteDTO.setExpedienteId(expedienteDelegate.obtenerExpedienteIdPorNumExpId(expedienteDTO));
                }
                expedienteDTO.setUsuario(usuarioFirmado);  
                /**
                 *Se agrega lo referente al envío de solicitud 
                 */
                if (logger.isDebugEnabled()) {
                    logger.debug("**********************************forma.getDistrito() = "
                            + forma.getDistrito());
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("**********************************forma.getTribunal() = "
                            + forma.getTribunal());
                }
                
                if (logger.isDebugEnabled()) {
                    logger.debug("**********************************forma.getFuncionarioDestinatario() = "
                            + forma.getFuncionarioDestinatario());
                }
                
                String respuesta="";
//                List<DocumentoDTO> documentos=new ArrayList<DocumentoDTO>();
                
                logger.info("enviarSolicitudAudiencia - areaUsuarioFirmado : "+usuarioFirmado.getFuncionario().getJerarquiaOrganizacional().getJerarquiaOrganizacionalId());
                
                //Se atiende inciencia para Enviar Solicitud y Generar denuncia NO SIEMPRE APLICA
                //Se cambia por consultar tipo de actividad
                //--2242	Generar querella
                //--1647	Generar denuncia
                //--2134	Atender canalización en la Unidad de Fiscales Investigadores
//                Long[] idTA= {Actividades.GENERAR_QUERELLA.getValorId(),
//        				Actividades.GENERAR_DENUNCIA_EN_ATP.getValorId(),
//        				Actividades.ATENDER_CANALIZACION_UI.getValorId()  //FIXME GBP verificar si no se estan guardado el Tipo documento: 
//        				//  82 Solicitud  Denuncia en Atención Temprana --  Tipo 82 Forma 1 ****Debe de ser 83 Pero hay 82 
//        				//  83 Acta Denuncia en Unidad de Investigación --  Tipo 82 Forma 50    ...Esta bien....
//        				};
//        		List<Long> idTipoActividades= Arrays.asList(idTA);
//        		Boolean documentoRec = true; //Debe de generarse la actuación con documento 
//                List<ActividadDTO> actividadesDTO = actividadDelegate.consultarActividadesPorTipoActividadExpedienteId(expedienteDTO.getExpedienteId(), idTipoActividades, documentoRec); 

//                logger.info("enviarSolicitudAudiencia - areaUsuarioFirmado atpenal : "+Areas.ATENCION_TEMPRANA_PG_PENAL.parseLong());
//                logger.info("enviarSolicitudAudiencia - areaUsuarioFirmado agenteMP: "+Areas.UNIDAD_INVESTIGACION.parseLong());
//                
//    			if(usuarioFirmado.getFuncionario().getJerarquiaOrganizacional().getJerarquiaOrganizacionalId().longValue()== Areas.ATENCION_TEMPRANA_PG_PENAL.parseLong())
//    			{	
//    				logger.info("enviarSolicitudAudiencia - Consultando documentos por ATENCION_TEMPRANA_PG_PENAL.....");
//    				documentos=documentoDelegate.consultarDocumentosXTipoDocumento(expedienteDTO,83L);
//    			}
//    			else if(usuarioFirmado.getFuncionario().getJerarquiaOrganizacional().getJerarquiaOrganizacionalId().longValue()== Areas.UNIDAD_INVESTIGACION.parseLong())
//    			{
//    				logger.info("enviarSolicitudAudiencia - Consultando documentos por area UNIDAD_INVESTIGACION .....");
//    				documentos=documentoDelegate.consultarDocumentosXTipoDocumento(expedienteDTO,82L);
//    			}
//    			logger.info("enviarSolicitudAudiencia - tamano lista documentos:: "+documentos.size());
    			
    		       // se obtienen
                Long idDistrito = forma.getDistrito();
                Long idTribunal = forma.getTribunal();
                Long idClaveFuncionario = forma.getFuncionarioDestinatario();
    			
    			//if(documentos.size()>0)
                //if(actividadesDTO.size()>0)

                //Se consulta si el xpediente fue replicado antes de enviar la solicitud.
    			expedienteDTO.setInvolucradosSolicitados(true);
                ExpedienteDTO expDTO = expedienteDelegate.obtenerExpedientePorExpedienteId(expedienteDTO);
                
                //Incian cambios RGG: Incidencia 35-1
                int institucionActual = 0;
    			ConfInstitucionDTO loInstitucion = configuracionDelegate.consultarInstitucionActual();
                if(loInstitucion != null && loInstitucion.getConfInstitucionId() != null)
    				institucionActual = loInstitucion.getConfInstitucionId().intValue();
                
                //Si la institucion es diferente a PG 
                if(institucionActual == Instituciones.PGJ.getValorId()){
                    //Si ha sido replicado, es posible su envío
                    if( expDTO!= null && expDTO.getEsReplicado() != null && expDTO.getEsReplicado() )
    				{
    					actuacionesDelegate.enviarSolicitudDeAudiencia(solicitudAudienciaDTO, expedienteDTO, idDistrito, idTribunal, idClaveFuncionario );
    					respuesta="Solicitud enviada correctamente.";
    				}
    				else
    				{
    					respuesta="No se ha generado la denuncia, favor de realizarla.";
    				}
                }else{
                		//Si la institucion actual NO es PGJ entonces envia la solicitud de audicencia sin importar si la denuncia fue creada
    					actuacionesDelegate.enviarSolicitudDeAudiencia(solicitudAudienciaDTO, expedienteDTO, idDistrito, idTribunal, idClaveFuncionario );
    					respuesta="Solicitud enviada correctamente.";
                }
                //Finalizan cambios RGG: Incidencia 35-1
            
    			
    			//mandamos la respuesta
                escribirRespuesta(response, respuesta);
               
            } catch (NSJPNegocioException ex) {
                logger.error("Ocurrio un error en enviarSolicitudAudiencia", ex);
                escribirError(response, ex);
            }
        }
        return null;
    }

    public ActionForward validarPlazoConstitucionalDeAudiencia(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        return null;
    }
}
