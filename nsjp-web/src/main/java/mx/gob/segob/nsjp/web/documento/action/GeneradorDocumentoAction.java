/**
 * Nombre del Programa : GeneradorDocumentoAction.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 May 2011
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
package mx.gob.segob.nsjp.web.documento.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.constants.ConstantesGenerales;
import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.documento.OperacionDocumento;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.actividad.ActividadDelegate;
import mx.gob.segob.nsjp.delegate.audiencia.AudienciaDelegate;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.delegate.notificacion.NotificacionDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.delegate.usuario.UsuarioDelegate;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.GrupoObjetosExpedienteDTO;
import mx.gob.segob.nsjp.dto.documento.ObjetoResumenDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoPersonaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.ParametrosDocumentoDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.ReporteBaseAction;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Action encargado de recibir y procesar las solicitudes para la emisi?n de un documento
 * basado en una Forma (plantilla) de la base de datos y los datos de un expediente
 * 
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class GeneradorDocumentoAction extends ReporteBaseAction {
    
	@Autowired
	private DocumentoDelegate documentoDelegate;
	@Autowired
	private SolicitudDelegate solicituDelegate;
	@Autowired
	private ActividadDelegate actividadDelegate;
	@Autowired
	private NotificacionDelegate notificacionDelegate;
	@Autowired
	private ExpedienteDelegate expedienteDlegate;
	@Autowired
	private FuncionarioDelegate funcionarioDelegate;
	@Autowired
	private UsuarioDelegate usuarioDelegate;
	@Autowired
	private AudienciaDelegate audienciaDelegate; 
	@Autowired
	private ExpedienteDelegate expedienteDelegate; 
	
	public static final String PARAM_TEXTO_DOCUMENTO = "texto";
	public static final String PARAM_GUARDAR_PARCIAL = "parcial";
	public static final String PARAM_FORMA_DOCUMENTO = "formaId";
	public static final String PARAM_IDENT_DOCUMENTO = "documentoId";
	public static final String PARAMT_TIPO_DOCUMENTO = "tipoDocumento";
	public static final String PARAMT_TIPO_OPERACION = "tipoOperacion";
	public static final Long   TIPO_DOCUMT_SOLICITUD = TipoDocumento.SOLICITUD.getValorId();
	
	private static final Logger logger = Logger
            .getLogger(GeneradorDocumentoAction.class);
    /**
     * Crea o actualiza un documento para el expediente que se est? trabajando actualmente
     * 
     * Parametros:
     * 
     * formaId (obligatorio) -opcional si se manda el tipo de documento- El identificador del formato a emitir (acta, denuncia, acuse de recibo, etc)
     * 
     * documentoId (opcional) - Indica un cierto ID de documento con el que se debe de trabajar en lugar de
     * crear uno nuevo. En este caso el documento se actualizar?a en lugar de crearse 
     * 
     * tipoOperacion - Identificador del tipo de operaci?n a realizar una vez que sea impreso el documento
     * <code>OperacionDocumento.ACTUALIZAR_ESTADO_SOLICITUD</code> Actualizar el estado de la solicitud
     * <code>OperacionDocumento.REGISTRAR_ORDEN_DETENCION</code> Registra un solicitud de Orden de Detencion
     * <code>OperacionDocumento.ASOCIAR_DOCUMENTO_A_RESOLUTIVO</code> Asocia el Documento a un resolutivo
     * posterior al emitir un documento 
     * Al utilizar este tipo de operaci?n se debe enviar tambi?n el par?metro de estatusSolicitud indicando
     * el estado de solicitud destino
     * 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward generarDocumento(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

		    try{
		    	// se obtiene el texto del editor
		    	String textoPdf = request.getParameter(PARAM_TEXTO_DOCUMENTO);
		    	DocumentoDTO documento = null;
		    	FormaDTO forma =null;
		    	String parametro = request.getParameter(PARAM_IDENT_DOCUMENTO);
		    	logger.debug(PARAM_IDENT_DOCUMENTO + " :: " + parametro);
		    	
		    	String audienciaId = request.getParameter("audienciaId");
		    	
		    	UsuarioDTO loUsuario = getUsuarioFirmado(request); 
				FuncionarioDTO responsableDocumento = loUsuario.getFuncionario();
				
				ConfInstitucionDTO confInstitucionDTO = null;
				if(loUsuario.getInstitucion() != null) {
					confInstitucionDTO = new ConfInstitucionDTO();
					Long confInstId = loUsuario.getInstitucion().getConfInstitucionId();
					confInstitucionDTO.setConfInstitucionId(confInstId);	
				}
				
				
		    	if(StringUtils.isNotBlank(parametro)){
//		    		Se est? editando un documento en espec?fico
		    		Long documentoId = NumberUtils.toLong(parametro);
		    		documento = documentoDelegate.cargarDocumentoPorId(documentoId);
		    		forma = documento.getFormaDTO();
		    	}else{
//		    		Si es un documento nuevo se obtiene el tipo de forma que se est? editando
		            documento = new DocumentoDTO();
			    	Long formaId = NumberUtils.toLong(request.getParameter(PARAM_FORMA_DOCUMENTO), 1L);
		            forma = documentoDelegate.buscarForma(formaId);
		    	}
		    	
		    	if(forma == null){
		    		throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		    	}
		    	documento.setConfInstitucion(confInstitucionDTO);
		    	
		    	ArchivoDigitalDTO archivo = null;
		    	//FIXME La entidad Forma no contiene el campo TipoDocumento. 
//			    Long tipoDocumento = NumberUtils.toLong(request.getParameter(PARAMT_TIPO_DOCUMENTO), forma.getTipoDocumentoDTO().getIdCampo());
		    	Long tipoDocumento = NumberUtils.toLong(request.getParameter(PARAMT_TIPO_DOCUMENTO), TIPO_DOCUMT_SOLICITUD);
			    boolean parcial = StringUtils.isNotBlank(request.getParameter(PARAM_GUARDAR_PARCIAL));
			    String numeroExpediente = request.getParameter("numeroUnicoExpediente").trim();
		    	ExpedienteDTO expTrabajo = super.getExpedienteTrabajo(request, numeroExpediente);
		    	if(expTrabajo == null && numeroExpediente != null)
		    		expTrabajo = expedienteDelegate.obtenerExpedientePorNumeroExpediente(numeroExpediente);
		    	UsuarioDTO usuarioFirmado = super.getUsuarioFirmado(request);
		    	String numeroFolio = request.getParameter("iNumeroOficio");
		    	ByteArrayOutputStream archivoPDF = null;
		    	logger.debug("Numero Folio :: " + request.getParameter("iNumeroOficio"));
		    	logger.debug("parcial :: " + parcial);
		    	logger.debug("JO Usu firmado :: "+usuarioFirmado.getFuncionario().getJerarquiaOrganizacional().getJerarquiaOrganizacionalId());
			    if(!parcial){
		    		archivoPDF = generarReportePDFDeHTML(textoPdf);
				    archivo = new ArchivoDigitalDTO();
				    archivo.setContenido(archivoPDF.toByteArray());
				    archivo.setNombreArchivo(forma.getNombre());
				    archivo.setTipoArchivo(ConstantesGenerales.EXTENSION_PDF);
		    	}else{
		    	    documento.setTextoParcial(textoPdf);
		    	}
			    
			    documento.setFormaDTO(forma);
			    documento.setArchivoDigital(archivo);
			    documento.setFechaCreacion(new Date());
			    documento.setNombreDocumento(forma.getNombre());
			    documento.setFolioDocumento(numeroFolio);
			    documento.setTipoDocumentoDTO(new ValorDTO(tipoDocumento));
			    documento.setEsGuardadoParcial(parcial);
			    documento.setResponsableDocumento(responsableDocumento);
			    documento.setJerarquiaOrganizacional(usuarioFirmado.getFuncionario().getJerarquiaOrganizacional().getJerarquiaOrganizacionalId());
			    
		    	Long documentoId = documentoDelegate.guardarDocumento(documento,expTrabajo);
		    	
				try {
					
					Long audienciaIdRel = NumberUtils.toLong(audienciaId, 0L);
					
					if(audienciaIdRel > 0L){
						AudienciaDTO audienciaRel = new AudienciaDTO();
						audienciaRel.setId(audienciaIdRel);
						
						DocumentoDTO documentoRel = new DocumentoDTO();
						documentoRel.setDocumentoId(documentoId);
						
						audienciaDelegate.asociarDocumentoAAudiencia(audienciaRel, documentoRel);
					}
				} catch (NSJPNegocioException ne) {
					if (ne.getCodigo().equals(CodigoError.DOCUMENTO_YA_ASOCIADO)) {
						logger.debug("DOCUMENTO YA ASOCIADO EN AUDIENCIA");
					}
				}

		    	if(!parcial){
		    		 request.getSession().setAttribute("documentoId",documentoId);
			    	 escribirReporte(response, archivoPDF, forma.getNombre());
			    	 logger.info("/**** ID Retornado :: "+documentoId);			    	 
//			    	 escribirRespuesta(response, converter.toXML(documentoId));
			    	 if(StringUtils.isNotBlank(request.getParameter(PARAMT_TIPO_OPERACION))){
			    		 documento.setDocumentoId(documentoId);
			    		 ejecutarAccion(request, documento);
			    	 }
			    }else{
			    	escribirRespuesta(response, converter.toXML(documentoId));
			    }
		    }catch (NSJPNegocioException e) {
				logger.error(e.getMessage(), e);
			}
		    
        return null;
    }
    
    
    public ActionForward generarDocumentoNotificacion(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

		    try{
		    	logger.debug("*********EJECUTANDO ACTION generarDocumentoNotificacion************");
		    	logger.debug("VERIFICANDO PARAMETROS.....");
		    	    	
		    	// se obtiene el texto del editor
		    	String textoPdf = request.getParameter(PARAM_TEXTO_DOCUMENTO);
		    	logger.debug("textoPdf:: " + textoPdf);
		    	DocumentoDTO documento = null;
		    	FormaDTO forma =null;
		    	String parametro = request.getParameter(PARAM_IDENT_DOCUMENTO);
		    	logger.debug(PARAM_IDENT_DOCUMENTO+" :: "+parametro);
		    	
		    	//para verificar parametros
		    	logger.debug(PARAM_FORMA_DOCUMENTO+"::" +request.getParameter(PARAM_FORMA_DOCUMENTO));
		    	logger.debug(PARAMT_TIPO_DOCUMENTO+"::" +request.getParameter(PARAMT_TIPO_DOCUMENTO));
		    	logger.debug("NUMERO UNICO EXPEDIENTE::" +request.getParameter("numeroUnicoExpediente"));
		    	
		    	
		    	
		    	
		    	
		    	if(StringUtils.isNotBlank(parametro)){
//		    		Se est? editando un documento en espec?fico
		    		Long documentoId = NumberUtils.toLong(parametro);
		    		documento = documentoDelegate.cargarDocumentoPorId(documentoId);
		    		forma = documento.getFormaDTO();
		    	}else{
//		    		Si es un documento nuevo se obtiene el tipo de forma que se est? editando
		            documento = new DocumentoDTO();
			    	Long formaId = NumberUtils.toLong(request.getParameter(PARAM_FORMA_DOCUMENTO), 1L);
		            forma = documentoDelegate.buscarForma(formaId);
		    	}
		    	
		    	if(forma == null){
		    		throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		    	}
		    	
		    	ArchivoDigitalDTO archivo = null;
			    Long tipoDocumento = NumberUtils.toLong(request.getParameter(PARAMT_TIPO_DOCUMENTO), TIPO_DOCUMT_SOLICITUD);
			    logger.debug("tipoDocumento:: " + tipoDocumento);
			    boolean parcial = StringUtils.isNotBlank(request.getParameter(PARAM_GUARDAR_PARCIAL));
		    	ExpedienteDTO expTrabajo = super.getExpedienteTrabajo(request, request.getParameter("numeroUnicoExpediente"));
		    	ByteArrayOutputStream archivoPDF = null;
		    	logger.debug("parcial :: " + parcial);
			    if(!parcial){
		    		archivoPDF = generarReportePDFDeHTML(textoPdf);
				    archivo = new ArchivoDigitalDTO();
				    archivo.setContenido(archivoPDF.toByteArray());
				    archivo.setNombreArchivo(forma.getNombre());
				    archivo.setTipoArchivo(ConstantesGenerales.EXTENSION_PDF);
		    	}else{
		    	    documento.setTextoParcial(textoPdf);
		    	}
			    
			    documento.setFormaDTO(forma);
			    documento.setArchivoDigital(archivo);
			    documento.setFechaCreacion(new Date());
			    documento.setNombreDocumento(forma.getNombre());
			    documento.setTipoDocumentoDTO(new ValorDTO(tipoDocumento));
		    	Long documentoId = notificacionDelegate.guardarYEnviarNotificacionAMismaInstitucion(expTrabajo,documento);
		    	if(!parcial){
			    	 escribirReporte(response, archivoPDF, forma.getNombre());
			    	 if(StringUtils.isNotBlank(request.getParameter(PARAMT_TIPO_OPERACION))){
			    		 documento.setDocumentoId(documentoId);
			    		 ejecutarAccion(request, documento);
			    	 }
			    }else{
			    	escribirRespuesta(response, converter.toXML(documentoId));
			    }
		    	return mapping.findForward("success");
		    }catch (NSJPNegocioException e) {
				logger.error(e.getMessage(), e);
				return mapping.findForward("fail");
			}
    }
    
    
    /**
     * 
     * @param request
     * @param documento
     * @throws NSJPNegocioException
     */
    private void ejecutarAccion( HttpServletRequest request, DocumentoDTO documento) throws NSJPNegocioException{
		OperacionDocumento op = OperacionDocumento.getByValor(NumberUtils.toLong(request.getParameter(PARAMT_TIPO_OPERACION)));
		logger.debug(PARAMT_TIPO_OPERACION + " :: " + op);
		logger.info("Inicia - ejecutarAccion(...)");
		switch(op){
			case ACTUALIZAR_ESTADO_SOLICITUD:
				 if(request.getParameter("estatusSolicitud") != null){
					 SolicitudDTO solicitud = new SolicitudDTO(documento.getDocumentoId());
					 EstatusSolicitud estatus = EstatusSolicitud.getByValor(NumberUtils.toLong( request.getParameter("estatusSolicitud")));
					 solicituDelegate.actualizaEstatusSolicitud(solicitud, estatus);
				 }else{
			    		throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
				 }
				 break;
			case ASOCIAR_DOCUMENTO_A_RESOLUTIVO:
			    Long resolutivoId = null;
			    ResolutivoDTO resolutivo = new ResolutivoDTO();
			    if(request.getParameter("idResolutivo") != null){
					resolutivoId = NumberUtils.toLong(request.getParameter("idResolutivo"), 2L);
					resolutivo.setResolutivoId(resolutivoId);
			    	documentoDelegate.asociarDocuementoA(resolutivo, documento);

			    }else{
		    		throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			    }
			    break;
			case REGISTRAR_ORDEN_DETENCION:
				SolicitudDTO ordenDetencion = (SolicitudDTO) request.getSession().getAttribute("ordenDetencion");
				if(ordenDetencion != null){
					solicituDelegate.registrarSolicitudOrdenDeDetencion(ordenDetencion  , documento.getDocumentoId());
					request.getSession().removeAttribute("ordenDetencion");
				}else{
		    		throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
				}
				break;
				
		}
    }
    
    /**
     * Realiza la pre-carga de un documento para el expediente de trabajo.
     * Si no existe ning?n documento del tipo de forma seleccionado entonces prepara
     * un documento nuevo (sin guardarlo en BD), obtiene la plantilla de la forma
     * y la llena con los datos del expediente para presentarla
     * en el CKEditor. Si ya existe alg?n documento con texto parcial para el expediente
     * de trabajo carga la informaci?n que el usuario hab?a guardado parcialmente
     * y la presenta en el CKEditor
     * 
     * Parametros:
     * 
     * formaId (obligatorio-opcional si se env?a el tipo de documento)- El identificador del formato a emitir (acta, denuncia, acuse de recibo, etc)
     * 
     * documentoId (opcional) - Indica un cierto ID de documento con el que se debe de trabajar en lugar de
     * crear uno nuevo. En este caso el documento se actualizar?a en lugar de crearse 
     * 
     * tipoOperacion - Identificador del tipo de operaci?n a realizar una vez que sea impreso el documento
     * <code>OperacionDocumento.ACTUALIZAR_ESTADO_SOLICITUD</code> Actualizar el estado de la solicitud
     * posterior al emitir un documento 
     * Al utilizar este tipo de operaci?n se debe enviar tambi?n el par?metro de estatusSolicitud indicando
     * el estado de solicitud destino
     * 
     * esconderArbol - Enviar este par?metro con uno para no mostrar el ?rbol de datos de expediente, no mandarlo
     * para mostrar el ?rbol
     * 
     * numeroUnicoExpediente (obligatorio) - Identificador del n?mero ?nico de expediente con el que se est? trabajando
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward cargarDocumento(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)  {    	
    	try {
    		UsuarioDTO usuario = getUsuarioFirmado(request);
    		logger.debug("/**** NumeroUnico :::: "+request.getParameter("numeroUnicoExpediente"));
    		ExpedienteDTO expTrabajo = super.getExpedienteTrabajo(request, request.getParameter("numeroUnicoExpediente"));
        	if(expTrabajo != null){
        		expTrabajo.setArea(getUsuarioFirmado(request).getAreaActual());
            	expTrabajo.setUsuario(getUsuarioFirmado(request));
        	}
    		Map<String,Object> parametrosExtra = new HashMap<String,Object> ();
    		
    		for(Object llave:request.getParameterMap().keySet()){
    			parametrosExtra.put(llave.toString(), request.getParameter(llave.toString()));
    		}
    		
    		
    		Long formaId = NumberUtils.toLong(request.getParameter("formaId"), 1L);
    		Long documentoId =  StringUtils.isNotBlank(request.getParameter("documentoId"))?NumberUtils.toLong(request.getParameter("documentoId")):null;
    		DocumentoDTO documentoCargado = null;
    		logger.debug("Intentando carga de documento: forma: " + formaId );
    		logger.debug("Intentando carga de documento: documento: " + documentoId );
    		logger.debug("Intentando carga de documento: numeroExpediente: " + request.getParameter("numeroUnicoExpediente"));
    		logger.debug("Expediente de trabajo: " + expTrabajo);
    		//si documentioId es nulo se trabaja con el expediente actual de sesi?n
    		if(documentoId == null){
    			logger.debug("entra formas");
    			Formas forma = Formas.getByValor(formaId);
    			logger.debug("forma_antes_del_switch:: "+forma);
    			if(forma==null)
    			{
    				logger.info("forma_no_en_enum.... formaID:: "+formaId);
    				documentoCargado = documentoDelegate.cargarDocumentoPorExpedienteYForma(expTrabajo, new FormaDTO(formaId),parametrosExtra);
    			}
    			else
    			{
	    			switch(forma){
	    				case AUDIENCIA:
	    					logger.debug("entra switch audiencia");
	    					Long auidenciaId = NumberUtils.toLong(request.getParameter("idAudiencia"));
	    					logger.debug("LLega Audiencia id: " + auidenciaId );
	    					
	    					
	    					
	    					actividadDelegate.registrarActividad(expTrabajo, usuario.getFuncionario(), Actividades.FINAL_DE_AUDIENCIA.getValorId());
	    					
	    					AudienciaDTO audiencia = new AudienciaDTO();
	    					audiencia.setId(auidenciaId);
	    					documentoCargado = documentoDelegate.cargarDocumentoPorAudienciaYForma(audiencia, new FormaDTO(formaId));
	    					break;
	    				case DUPLICIDAD_TERMINO_CONSTITUCIONAL:
	    					logger.debug("entra switch duplicidad termino");
	    					auidenciaId = NumberUtils.toLong(request.getParameter("idAudiencia"));
	    					audiencia = new AudienciaDTO();
	    					audiencia.setId(auidenciaId);
	    					Long resolutivoId = NumberUtils.toLong(request.getParameter("idResolutivo"));
	    					logger.debug("LLega Resolutivo: " + resolutivoId );
	    					logger.debug("LLega Audiencia: " + auidenciaId );
	    					ResolutivoDTO resolutivo = new ResolutivoDTO();
	    					resolutivo.setResolutivoId(resolutivoId);
	    					resolutivo.setAudiencia(audiencia);
	    					documentoCargado = documentoDelegate.cargarDocumentoPorResolutivoYForma(resolutivo, new FormaDTO(formaId));
	    					break;
	    				default:
	    					
	    					documentoCargado = documentoDelegate.cargarDocumentoPorExpedienteYForma(expTrabajo, new FormaDTO(formaId),parametrosExtra);
	    					break;
	    			}
    			}
    		}else{
    			documentoCargado = documentoDelegate.cargarDocumentoPorId(documentoId,expTrabajo);
    		}
			response.setContentType(ConstantesGenerales.CONTENT_TYPE_XML);
			PrintWriter pw = response.getWriter();
			logger.info(ConstantesGenerales.BODY_TAG_APERTURA+ConstantesGenerales.C_DATA_OPEN+
					(documentoCargado.getTextoParcial()!=null?documentoCargado.getTextoParcial():StringUtils.EMPTY)+
					ConstantesGenerales.C_DATA_CLOSE+ConstantesGenerales.BODY_TAG_CIERRE);
			pw.print(
					ConstantesGenerales.BODY_TAG_APERTURA+ConstantesGenerales.C_DATA_OPEN+
					(documentoCargado.getTextoParcial()!=null?documentoCargado.getTextoParcial():StringUtils.EMPTY)+
					ConstantesGenerales.C_DATA_CLOSE+ConstantesGenerales.BODY_TAG_CIERRE
					);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			logger.error("Exception forma :"+e.getMessage(), e);
		}
    	return null;
    	
    }
    
    /**
     * Carga un resumen de expediente para llenar un ?rbol de contenido
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward cargarArbolExpediente(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)  {   
    	logger.debug("cargarArbolExpediente");
    	ExpedienteDTO expTrabajo = super.getExpedienteTrabajo(request, request.getParameter("numeroUnicoExpediente"));	
    	//expTrabajo.setExpedienteId(3L);
    	if(expTrabajo != null){
    		expTrabajo.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
    		expTrabajo.setInvolucradosSolicitados(true);
    		expTrabajo.setObjetosSolicitados(true);
    	}
    	String idnumero=request.getParameter("idNumeroUnicoExpediente");
    	if(idnumero!=null &&!idnumero.equals("")){
    		expTrabajo.setNumeroExpedienteId(Long.parseLong(idnumero));
    	}
    	try{
    		ParametrosDocumentoDTO resumen = documentoDelegate.cargarResumenExpedienteParaDocumento(expTrabajo);
    		if(resumen != null){
    			logger.debug("cargarArbolExpediente :::: "+resumen);
    			response.setContentType(ConstantesGenerales.CONTENT_TYPE_XML);
    			PrintWriter pw = response.getWriter();
    			pw.print(
    					escribirXMLArbolExpediente(resumen)
    					);
    			pw.flush();
    			pw.close();
    		}
    		
    	}catch (Exception e) {
    		logger.error(e.getMessage(), e);
		}
    	
    	
    	return null;
    }
    
    /**
     * Convierte la informaci?n necesaria de un expediente para poder trabajarlo en el documento
     * en forma de ?rbol de informaci?n
     * 
     * 
     * @param resumen
     * @return
     */
	private String escribirXMLArbolExpediente(ParametrosDocumentoDTO resumen) {
		converter.alias("expedienteResumenDTO", ParametrosDocumentoDTO.class);
		converter.alias("involucradoDTO", InvolucradoDTO.class);
		converter.alias("calidadDTO", CalidadDTO.class);
		converter.alias("domicilioDTO", DomicilioDTO.class);
		converter.alias("nombreDemograficoDTO", NombreDemograficoDTO.class);
		converter.alias("telefonoDTO", TelefonoDTO.class);
		converter.alias("correoElectronicoDTO", CorreoElectronicoDTO.class);
		converter.alias("aliasInvolucradoDTO", AliasInvolucradoDTO.class);
		converter.alias("grupoObjetosExpedienteDTO", GrupoObjetosExpedienteDTO.class);
		converter.alias("objetoResumenDTO", ObjetoResumenDTO.class);
		
		return converter.toXML(resumen);
	}
	
	/**
     * Crea o actualiza un documento producto de una transcripci?n
     * 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward generarDocumentoTranscripcion(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

		    try{
		    	// se obtiene el texto del editor
		    	String textoPdf = request.getParameter(PARAM_TEXTO_DOCUMENTO);
		    	DocumentoDTO documento = null;
		    	FormaDTO forma =null;
		    	documento = new DocumentoDTO();
		    	//se obtiene el tipo de forma que se est? editando
			    Long formaId = NumberUtils.toLong(request.getParameter("formaId"), 1L);
			    Long solicitudId = NumberUtils.toLong(request.getParameter("solicitudId"), 1L);
		        forma = documentoDelegate.buscarForma(formaId);
		    	 if(forma != null){
		            	//se copian valores del tipo de forma al documento
		            	String nombreArchivo = forma.getNombre();
					    String nombreDocumento = forma.getNombre();
					    ValorDTO tipoDocumento = new ValorDTO(TipoDocumento.SOLICITUD.getValorId());
					    Double version = 1.2;
					    boolean parcial = StringUtils.isNotBlank(request.getParameter("parcial"));
				    	ArchivoDigitalDTO archivo = null;
				    	//ExpedienteDTO expTrabajo =   super.getExpedienteTrabajo(); //cambiar a expediente de la audiencia
				    	ByteArrayOutputStream archivoPDF = null;
				    	if(!parcial){
				    		archivoPDF = generarReportePDFDeHTML(textoPdf);
						    //Crear el archivo digital
						    archivo = new ArchivoDigitalDTO();
						    archivo.setContenido(archivoPDF.toByteArray());
						    archivo.setNombreArchivo(nombreArchivo);
						    archivo.setTipoArchivo(ConstantesGenerales.EXTENSION_PDF);
				    	}
					    documento.setFechaCreacion(new Date());
					    documento.setFormaDTO(forma);
					    documento.setTipoDocumentoDTO(tipoDocumento);
					    documento.setNombreDocumento(nombreDocumento);
					    documento.setVersion(version);
					    documento.setTextoParcial(parcial?textoPdf:null);
					    //documentoDelegate.guardarDocumentoTranscripcion(documento,expTrabajo);
					    documentoDelegate.guardarDocumentoTranscripcion(documento, solicitudId);
					    if(!parcial){
					    	 escribirReporte(response, archivoPDF, nombreArchivo);
					    }
		            }
		    }catch (NSJPNegocioException e) {
		        logger.error(e.getMessage(), e);
			}
		    
        return null;
    }
    
	/**
     * Crea o actualiza un documento producto de una transcripci?n
     * 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward generarActaAudiencia(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

		    try{
		    	// se obtiene el texto del editor
		    	String textoPdf = request.getParameter(PARAM_TEXTO_DOCUMENTO);
		    	DocumentoDTO documento = null;
		    	FormaDTO forma =null;
		    	documento = new DocumentoDTO();
		    	//se obtiene el tipo de forma que se est? editando
			    Long formaId = NumberUtils.toLong(request.getParameter("formaId"), 1L);
		        forma = documentoDelegate.buscarForma(formaId);
		    	 if(forma != null){
		            	//se copian valores del tipo de forma al documento
		            	String nombreArchivo = forma.getNombre();
					    String nombreDocumento = forma.getNombre();
					    ValorDTO tipoDocumento = new ValorDTO(TipoDocumento.SOLICITUD.getValorId());
					    Double version = 1.2;
					    boolean parcial = StringUtils.isNotBlank(request.getParameter("parcial"));
				    	ArchivoDigitalDTO archivo = null;
				    	ExpedienteDTO expTrabajo =   super.getExpedienteTrabajo(request, request.getParameter("numeroUnicoExpediente"));
				    	ByteArrayOutputStream archivoPDF = null;
				    	if(!parcial){
				    		archivoPDF = generarReportePDFDeHTML(textoPdf);
						    //Crear el archivo digital
						    archivo = new ArchivoDigitalDTO();
						    archivo.setContenido(archivoPDF.toByteArray());
						    archivo.setNombreArchivo(nombreArchivo);
						    archivo.setTipoArchivo(ConstantesGenerales.EXTENSION_PDF);
				    	}
					    documento.setFechaCreacion(new Date());
					    documento.setFormaDTO(forma);
					    documento.setTipoDocumentoDTO(tipoDocumento);
					    documento.setNombreDocumento(nombreDocumento);
					    documento.setVersion(version);
					    documento.setTextoParcial(parcial?textoPdf:null);
					    documentoDelegate.guardarActaAudiencia(documento,expTrabajo);
					    if(!parcial){
					    	 escribirReporte(response, archivoPDF, nombreArchivo);
					    }
		            }
		    }catch (NSJPNegocioException e) {
		        logger.error(e.getMessage(), e);
			}
		    
        return null;
    }
    
    /**
     * Realiza la carga y guardado del documento sin pasar por la pantalla de edici?n 
     * del documento.
     * 
     * Se trabaja con los datos del expediente de trabajo
     * 
     * Parametros:
     * 
     * formaId (obligatorio)- El identificador del formato a emitir (acta, denuncia, acuse de recibo, etc)
     * 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward generarDocumentoDirecto(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)  {    	
    	try {
    		logger.info("GenerarDocumento numeroUnicoExpediente:: "+request.getParameter("numeroUnicoExpediente"));
    		ExpedienteDTO expTrabajo = super.getExpedienteTrabajo(request,request.getParameter("numeroUnicoExpediente")!=null?
    				request.getParameter("numeroUnicoExpediente").trim():null);	
    		
        	expTrabajo.setArea(super.getUsuarioFirmado(request).getAreaActual());
        	expTrabajo.setUsuario(super.getUsuarioFirmado(request));
    		Long formaId = NumberUtils.toLong(request.getParameter("formaId"), 1L);
    		Long documentoId = NumberUtils.toLong(request.getParameter("documentoId"), -1L);
    		Long tipoDocumt = NumberUtils.toLong(request.getParameter("tipoDocumento"), -1L);
    		boolean returnDocument = false;
    		if(request.getParameter("returnDocument") == null || 
    		  (request.getParameter("returnDocument") != null && request.getParameter("returnDocument").equals("1"))){
    			returnDocument = true;
    		} 
    		
    		if (logger.isDebugEnabled()) {
    		    logger.debug("formaId :: " + formaId);
    		    logger.debug("documentoId :: " + documentoId);
    		    logger.debug("tipoDocumt :: " + tipoDocumt);
    		    
    		    
    		}
    		converter.alias("expedienteDTO", ExpedienteDTO.class);
		    logger.info(converter.toXML("MMM::"+expTrabajo));

    		FormaDTO forma = documentoDelegate.buscarForma(formaId);
    		 logger.debug("SE ENCONTRO LA FORMA:: " + forma);
    		DocumentoDTO documentoCargado = null;
    		if(documentoId.longValue() != -1L){
    			documentoCargado = documentoDelegate.cargarDocumentoPorId(documentoId, expTrabajo);
    			forma = documentoCargado.getFormaDTO();
    			logger.debug("FORMA DEL DOCUMENTO CARGADO:: " + documentoCargado.getFormaDTO());
    		}else{
	    		documentoCargado = documentoDelegate.cargarDocumentoPorExpedienteYForma(expTrabajo, new FormaDTO(formaId));
    		}
	    	if(forma != null){
            	//se copian valores del tipo de forma al documento
            	String nombreArchivo = forma.getNombre();
			    String nombreDocumento = forma.getNombre();
			    ValorDTO tipoDocumento = null;
			    if(tipoDocumt.longValue() != -1){
			    	tipoDocumento = new ValorDTO(tipoDocumt);
			    }else{
			    	tipoDocumento = new ValorDTO(TipoDocumento.ACTA.getValorId());
			    }
			    Double version = 1.2;
			    
		    	ArchivoDigitalDTO archivo = null;
		    	
		    	ByteArrayOutputStream archivoPDF = null;
		    	
	    		archivoPDF = generarReportePDFDeHTML(documentoCargado.getTextoParcial());
			    //Crear el archivo digital
			    archivo = new ArchivoDigitalDTO();
			    archivo.setContenido(archivoPDF.toByteArray());
			    archivo.setNombreArchivo(nombreArchivo);
			    archivo.setTipoArchivo(ConstantesGenerales.EXTENSION_PDF);
			    documentoCargado.setArchivoDigital(archivo);
			    documentoCargado.setFechaCreacion(new Date());
			    documentoCargado.setFormaDTO(forma);
			    documentoCargado.setTipoDocumentoDTO(tipoDocumento);
			    documentoCargado.setNombreDocumento(nombreDocumento);
			    documentoCargado.setVersion(version);
			    documentoCargado.setTextoParcial(null);
			    documentoDelegate.guardarDocumento(documentoCargado,expTrabajo);	
			    if(returnDocument){
			    	escribirReporte(response, archivoPDF, nombreArchivo);
			    }
			 }
	    }catch (NSJPNegocioException e) {
	        logger.error(e.getMessage(), e);
		}
        return null;
    }
    /**
     * Genera un archivo digital que corresponde al documento a generar/crear
     * sin embargo no escribe el archivo generado al response del cliente, en vez de esto
     * escribe como XML ?nicamente el ID del documento que se acaba de generar, dejando
     * a la pantalla que invoc? a este m?todo el comportamiento posterior que desea realizar, ya sea
     * para actualizar alg?n estatus o para enviar el documento reci?n creado 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward guardarDocumentoSincrono(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)  {
    	
    	try{
	    	// se obtiene el texto del editor
	    	String textoPdf = request.getParameter(PARAM_TEXTO_DOCUMENTO);
	    	DocumentoDTO documento = null;
	    	FormaDTO forma =null;
	    	String parametro = request.getParameter(PARAM_IDENT_DOCUMENTO);
	    	if(StringUtils.isNotBlank(parametro)){
	    		//Se est? editando un documento en espec?fico
	    		Long documentoId = NumberUtils.toLong(parametro);
	    		documento = documentoDelegate.cargarDocumentoPorId(documentoId);
	    		forma = documento.getFormaDTO();
	    	}else{
	    		//Si es un documento nuevo se obtiene el tipo de forma que se est? editando
	            documento = new DocumentoDTO();
		    	Long formaId = NumberUtils.toLong(request.getParameter(PARAM_FORMA_DOCUMENTO), 1L);
	            forma = documentoDelegate.buscarForma(formaId);
	    	}
	    	
	    	if(forma == null){
	    		throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
	    	}
	    	
	    	ArchivoDigitalDTO archivo = null;
		    Long tipoDocumento = NumberUtils.toLong(request.getParameter(PARAMT_TIPO_DOCUMENTO), TIPO_DOCUMT_SOLICITUD);
		    boolean parcial = StringUtils.isNotBlank(request.getParameter(PARAM_GUARDAR_PARCIAL));
	    	ExpedienteDTO expTrabajo = super.getExpedienteTrabajo(request, request.getParameter("numeroUnicoExpediente"));
	    	ByteArrayOutputStream archivoPDF = null;
	    	
		    if(!parcial){
	    		archivoPDF = generarReportePDFDeHTML(textoPdf);
			    archivo = new ArchivoDigitalDTO();
			    archivo.setContenido(archivoPDF.toByteArray());
			    archivo.setNombreArchivo(forma.getNombre());
			    archivo.setTipoArchivo(ConstantesGenerales.EXTENSION_PDF);
	    	}else{
	    	    documento.setTextoParcial(textoPdf);
	    	}
		    
		    documento.setFormaDTO(forma);
		    documento.setArchivoDigital(archivo);
		    documento.setFechaCreacion(new Date());
		    documento.setNombreDocumento(forma.getNombre());
		    documento.setTipoDocumentoDTO(new ValorDTO(tipoDocumento));
	    	Long documentoId = documentoDelegate.guardarDocumento(documento,expTrabajo);
	    	
		    escribirRespuesta(response, converter.toXML(documentoId));
		    
	    }catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
    	
    	
    	return null;
    }
    
    /***
     * funcion para generar la notificacion de la solicitud de ayuda psicologica UAVD
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward generarDocumentoNotificacionAyudaPsicologicaUAVD(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

		    try{
		    	// se obtiene el texto del editor
		    	String textoPdf = request.getParameter(PARAM_TEXTO_DOCUMENTO);
		    	DocumentoDTO documento = null;
		    	FormaDTO forma =null;
		    	String parametro = request.getParameter(PARAM_IDENT_DOCUMENTO);
		    	logger.debug(PARAM_IDENT_DOCUMENTO + " :: " + parametro);
		    	if(StringUtils.isNotBlank(parametro)){
//		    		Se est? editando un documento en espec?fico
		    		Long documentoId = NumberUtils.toLong(parametro);
		    		documento = documentoDelegate.cargarDocumentoPorId(documentoId);
		    		forma = documento.getFormaDTO();
		    	}else{
//		    		Si es un documento nuevo se obtiene el tipo de forma que se est? editando
		            documento = new DocumentoDTO();
			    	Long formaId = NumberUtils.toLong(request.getParameter(PARAM_FORMA_DOCUMENTO), 1L);
		            forma = documentoDelegate.buscarForma(formaId);
		    	}
		    	
		    	if(forma == null){
		    		throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		    	}
		    	
		    	ArchivoDigitalDTO archivo = null;
			    Long tipoDocumento = NumberUtils.toLong(request.getParameter(PARAMT_TIPO_DOCUMENTO), TIPO_DOCUMT_SOLICITUD);
			    boolean parcial = StringUtils.isNotBlank(request.getParameter(PARAM_GUARDAR_PARCIAL));
		    	ExpedienteDTO expTrabajo = super.getExpedienteTrabajo(request, request.getParameter("numeroUnicoExpediente"));
		    	ByteArrayOutputStream archivoPDF = null;
		    	logger.debug("parcial :: " + parcial);
			    if(!parcial){
		    		archivoPDF = generarReportePDFDeHTML(textoPdf);
				    archivo = new ArchivoDigitalDTO();
				    archivo.setContenido(archivoPDF.toByteArray());
				    archivo.setNombreArchivo(forma.getNombre());
				    archivo.setTipoArchivo(ConstantesGenerales.EXTENSION_PDF);
		    	}else{
		    	    documento.setTextoParcial(textoPdf);
		    	}
			    
			    documento.setFormaDTO(forma);
			    documento.setArchivoDigital(archivo);
			    documento.setFechaCreacion(new Date());
			    documento.setNombreDocumento(forma.getNombre());
			    documento.setTipoDocumentoDTO(new ValorDTO(tipoDocumento));
			    
			    logger.debug("idRelacionDelito" + " :: " + request.getParameter("idRelDelito"));
			    logger.debug("idProbableResponsable" + " :: " + request.getParameter("idPR"));
			    logger.debug("idVictima" + " :: " + request.getParameter("idVictima"));
			    logger.debug("idDelito" + " :: " + request.getParameter("idDelito"));
			    logger.debug("idSolicitud" + " :: " + request.getParameter("idSolicitudVa"));
			    Long idSolici=Long.parseLong(request.getParameter("idSolicitudVa"));
			    DelitoPersonaDTO delitoPersonaDTO=new DelitoPersonaDTO();
			    delitoPersonaDTO.setDelitoPersonaId(Long.parseLong(request.getParameter("idRelDelito")));
			    delitoPersonaDTO.setProbableResponsable(new InvolucradoDTO(Long.parseLong(request.getParameter("idPR"))));
			    delitoPersonaDTO.setVictima(new InvolucradoDTO(Long.parseLong(request.getParameter("idVictima"))));
			    delitoPersonaDTO.setDelito(new DelitoDTO(Long.parseLong(request.getParameter("idDelito"))));
			    
			    List<FuncionarioDTO> listaFuncionario=funcionarioDelegate.consultarFuncionariosPorAreayPuesto(Areas.COORDINACION_ATENCION_VICTIMAS.parseLong(), Puestos.COORDINADOR_ATENCION_VICTIMAS.getValorId());
			    if(listaFuncionario!= null && listaFuncionario.size()!=0){
			    	UsuarioDTO usuarioDTO=usuarioDelegate.consultarUsuarioPorClaveFuncionario(listaFuncionario.get(0).getClaveFuncionario());
			    	delitoPersonaDTO.setUsuario(usuarioDTO);
			    }else{
			    	delitoPersonaDTO.setUsuario(super.getUsuarioFirmado(request));
			    }
			    
			    //se genera el expediente de UAVD
			    logger.debug("se generar? el expediente de UAVD");
			    ExpedienteDTO expediente = expedienteDlegate.generarNuevoExpedienteUAVD(delitoPersonaDTO);
			    logger.debug("fin se generar? el expediente de UAVD");
			    Long documentoId = documentoDelegate.guardarDocumento(documento,expediente);
		    	//Long documentoId = notificacionDelegate.guardarYEnviarNotificacionAMismaInstitucion(expTrabajo,documento);
			    logger.debug("se coloca la solicitud el expediente de UAVD");
			    expedienteDlegate.asociarNumCarpetaASolicitud(expediente, idSolici);
			    logger.debug("fin coloca la solicitud el expediente de UAVD");
			    if(!parcial){
			    	 escribirReporte(response, archivoPDF, forma.getNombre());
			    	 logger.debug("expediente_ayuda_psicoliga:: "+converter.toXML(expediente)); 
			    	 if(StringUtils.isNotBlank(request.getParameter(PARAMT_TIPO_OPERACION))){
			    		 documento.setDocumentoId(documentoId);
			    		 ejecutarAccion(request, documento);
			    	 }
			    }else{
			    	logger.debug("expediente_ayuda_psicoliga:: "+converter.toXML(expediente));
			    	escribirRespuesta(response, converter.toXML(expediente));
			    }
		    }catch (NSJPNegocioException e) {
				logger.error(e.getMessage(), e);
			}
		    
        return null;
    }
}	