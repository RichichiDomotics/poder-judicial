/**
 * Nombre del Programa 	: Defensa Restaurativa                                    
 * Autor               	: Cuauhtémoc Paredes                                              
 * Compania            	: Ultrasist                                                
 * Proyecto            	: NSJP              			Fecha:22/07/2011 
 * Marca de cambio     	: N/A                                                     
 * Descripcion General   : Clase para el caso de uso defensa restaurativa
 * Programa Dependiente  : N/A                                                      
 * Programa Subsecuente  : N/A                                                      
 * Cond. de ejecucion    : N/A                                                  
 * Dias de ejecucion     : N/A                             Horario: N/A
 *                               MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                 :N/A                                                           
 * Compania              :N/A                                                           
 * Proyecto              :N/A                                   Fecha: N/A       
 * Modificacion          :N/A                                                           
 *------------------------------------------------------------------------------      
 */
package mx.gob.segob.nsjp.web.defensor.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica;
import mx.gob.segob.nsjp.delegate.actividad.ActividadDelegate;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDesignacionDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.documento.form.AdjuntarDocumentoAsociadoAExpedienteForm;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Cuauhtemoc Paredes Serrano
 *
 */
public class DefensaRestaurativaAction extends GenericAction {
	
	private static final Logger log = Logger.getLogger(DefensaRestaurativaAction.class);
		
	@Autowired
	private ExpedienteDelegate expedienteDelegate;
	
	@Autowired
	private ActividadDelegate actividadDelegate;
	
	@Autowired
	private DocumentoDelegate documentoDelegate;
	
	@Autowired
	private InvolucradoDelegate involucradoDelegate;
	
	
	/**
	 * Metodo utilizado para realizar la consulta de expedientes por usuario etapa
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	
	public ActionForward buscarExpedientePorUsuarioEtapa(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("Entra a buscar expediente por usuario etapa");
			
			UsuarioDTO usuarioDTO = super.getUsuarioFirmado(request);
			//usuarioDTO.setIdUsuario(1L);
			List<ExpedienteDTO> expedienteDTOs = expedienteDelegate.consultarExpedientesPorUsuarioYEtapa(usuarioDTO, EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId());
			
			log.info("Lista de expedientes" + expedienteDTOs);
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");	
						
			int lTotalRegistros = expedienteDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			
			for(ExpedienteDTO expedienteDTO : expedienteDTOs){
				
				List<InvolucradoDTO> probablesResponsables = expedienteDTO.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA);
				 log.info("NumeroExpediente" + expedienteDTO.getNumeroExpediente() +"Probables responsable"+ probablesResponsables);
				
					writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+ "-"+ expedienteDTO.getEtapa().getIdCampo()+ "-"+ expedienteDTO.getEtapa().getValor()+"'>");
					
					writer.print("<cell>");
					if(expedienteDTO.getCasoDTO()!=null && expedienteDTO.getCasoDTO().getNumeroGeneralCaso()!= null){
						writer.print(expedienteDTO.getCasoDTO().getNumeroGeneralCaso());
							}else{
								
								writer.print("-");
							
							}
															
					writer.print("</cell>");
										
					writer.print("<cell>");
					if(expedienteDTO.getNumeroExpediente()!=null){
						writer.print(expedienteDTO.getNumeroExpediente());
					}
					writer.print("</cell>");					
									
					for (InvolucradoDTO involucradoDTO : probablesResponsables) {
						if(involucradoDTO.getNombreCompleto()!=null){
							writer.print("<cell>"+ involucradoDTO.getNombreCompleto()+"</cell>");
						}
					}	
					writer.print("<cell>" +"-" + "</cell>");
				
					//fecha limite de atencion
					writer.print("<cell>"+ expedienteDTO.getStrFechaApertura() +"-"+ expedienteDTO.getStrHoraApertura()+"</cell>");
					//fecha de designacion
					writer.print("<cell>" + expedienteDTO.getStrFechaCierre()+"-"+expedienteDTO.getStrHoraCierre() + "</cell>");
					
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
	 * Metodo utilizado para cambiar la etapa del expediente en defensoria
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	
	public ActionForward cambiarEtapaDelExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("Cambiar etapa del expediente");

			String numeroExpedienteId = request.getParameter("numeroExpedienteId");
			log.info("numero de expediente id" + numeroExpedienteId);
			
			String etapaId = request.getParameter("etapaId");
			log.info("etapa id" + etapaId);

			EtapasExpediente etapa = EtapasExpediente.getByValor(Long.parseLong(etapaId));
			expedienteDelegate.cambiarEtapaDelExpediente(Long.parseLong(numeroExpedienteId), etapa);
						
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para cambiar la situacion juridica el defendido
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	
	public ActionForward cambiarSituacionDelDefendido(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("Cambiar situacion del defendido");

			String involucradoId = request.getParameter("involucradoId");
			log.info("involucradoId - " + involucradoId);
			
			String situacionId = request.getParameter("situacionJuridicaId");
			log.info("situacionId - " + situacionId);
			
			InvolucradoDTO involucradoDTO = new InvolucradoDTO();
			involucradoDTO.setElementoId(new Long(involucradoId));
			
			involucradoDelegate.actualizarSituacionJuridicaDefensoria(involucradoDTO, SituacionJuridica.getByValor(new Long(situacionId)));
						
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para realizar la consulta de expedientes por usuario etapa
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	
	public ActionForward consultaDetalleExpedienteDefensoria(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("Entra a consultar el detalle del expediente*********");
			
			String numeroExpedienteId = request.getParameter("numeroExpedienteId");
			String numeroExpediente = request.getParameter("numeroExpediente");
			
			
			ExpedienteDTO expedienteDTO = getExpedienteTrabajo(request, numeroExpediente);
			if(expedienteDTO == null){
				expedienteDTO = new ExpedienteDTO();
				expedienteDTO.setNumeroExpedienteId(Long.parseLong(numeroExpedienteId));
				AreaDTO areaDTO = new AreaDTO(Areas.ATENCION_TEMPRANA_DEFENSORIA);
				expedienteDTO.setArea(areaDTO);
				expedienteDTO.setAvisosDesignacionSolicitados(true);
				expedienteDTO.setInvolucradosSolicitados(true);
				expedienteDTO.setDomicliosInvolucradoSolicitados(true);
				expedienteDTO = expedienteDelegate.obtenerExpedienteDefensoria(expedienteDTO);
				super.setExpedienteTrabajo(request, expedienteDTO);
			}
			if (log.isDebugEnabled()){
			    log.debug("Expediente :: " + expedienteDTO);
			}
			String xml = null;
			PrintWriter pw = null;
			converter.alias("expediente",  ExpedienteDTO.class);
			converter.alias("nombreDemografico", NombreDemograficoDTO.class);
			converter.alias("involucradoDTO", InvolucradoDTO.class);
			converter.alias("detencionDTO", DetencionDTO.class);
			converter.alias("delitoDTO", DelitoDTO.class);
			converter.alias("avisoDesignacionDTO", AvisoDesignacionDTO.class);
			converter.alias("etapasExpediente", EtapasExpediente.class);
			converter.alias("domicilio", DomicilioDTO.class);
			converter.alias("telefono", TelefonoDTO.class);
			converter.alias("correo", CorreoElectronicoDTO.class);
			
			xml = converter.toXML(expedienteDTO);
			log.info("EXPEDIENTE DEFENSA RESTAURATIVA : xml : " + xml);
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
	 * Metodo utilizado para adjuntar un documento y asociarlo a un expediente 
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward adjuntarDocumentoAsociadoAExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO ADJUNTAR DOCUMENTO Y ASOCIARLO A UN EXPEDIENTE");
			
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			ArchivoDigitalDTO adjunto = new ArchivoDigitalDTO();
			FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
			AdjuntarDocumentoAsociadoAExpedienteForm adjuntarDocumentoAsociadoAExpedienteForm = (AdjuntarDocumentoAsociadoAExpedienteForm)form;
					
			expedienteDTO.setExpedienteId(adjuntarDocumentoAsociadoAExpedienteForm.getExpedienteId());
			log.info("expediente Id de la forma" + adjuntarDocumentoAsociadoAExpedienteForm.getExpedienteId());
			
			FormFile archivo = adjuntarDocumentoAsociadoAExpedienteForm.getArchivoAdjunto();
			String contentType = archivo.getContentType();
			String fileName    = archivo.getFileName();
			int fileSize       = archivo.getFileSize();
			byte[] fileData    = archivo.getFileData();			        
			
			adjunto.setContenido(fileData);
			
			if(fileName != null){
				String[] extension = fileName.split("\\.");
				adjunto.setTipoArchivo("."+extension[extension.length-1]);
				adjunto.setNombreArchivo(extension[0]);
			}
			
			adjunto.setUsuario(super.getUsuarioFirmado(request));
			
			funcionarioDTO = getUsuarioFirmado(request).getFuncionario();
			
			Actividades actividad = Actividades.ADJUNTAR_ACUERDO_RESTAURATIVO;
			
			if(adjuntarDocumentoAsociadoAExpedienteForm.getTipo()!= 0){
				log.info("entra a archivo digital");
				actividad = Actividades.ADJUNTAR_ARCHIVO_DIGITAL;
				
			}
			
			Long exito = documentoDelegate.adjuntarArchivoDigitalAExpediente(expedienteDTO, adjunto, funcionarioDTO, actividad);
			log.info("regreso"+ exito);
					
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para adjuntar una denuncia externa y asociarlo a un expediente 
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward adjuntarDenunciaExternaAExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO ADJUNTAR DOCUMENTO Y ASOCIARLO A UN EXPEDIENTE");
			
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			ArchivoDigitalDTO adjunto = new ArchivoDigitalDTO();
			FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
			AdjuntarDocumentoAsociadoAExpedienteForm adjuntarDocumentoAsociadoAExpedienteForm = (AdjuntarDocumentoAsociadoAExpedienteForm)form;
					
			expedienteDTO.setExpedienteId(adjuntarDocumentoAsociadoAExpedienteForm.getExpedienteId());
			log.info("expediente Id de la forma" + adjuntarDocumentoAsociadoAExpedienteForm.getExpedienteId());
			
			FormFile archivo = adjuntarDocumentoAsociadoAExpedienteForm.getArchivoAdjunto();
			String contentType = archivo.getContentType();
			String fileName    = archivo.getFileName();
			int fileSize       = archivo.getFileSize();
			byte[] fileData    = archivo.getFileData();			        
			
			adjunto.setContenido(fileData);
			
			if(fileName != null){
				String[] extension = fileName.split("\\.");
				adjunto.setTipoArchivo("."+extension[extension.length-1]);
				adjunto.setNombreArchivo(extension[0]);
			}
			
			adjunto.setUsuario(super.getUsuarioFirmado(request));
			
			funcionarioDTO = getUsuarioFirmado(request).getFuncionario();
			
			Actividades actividad = Actividades.ADJUNTAR_ACUERDO_RESTAURATIVO;
			
			if(adjuntarDocumentoAsociadoAExpedienteForm.getTipo()!= 0){
				log.info("entra a archivo digital");
				actividad = Actividades.ADJUNTAR_ARCHIVO_DIGITAL;
				
			}
			
			Long exito = documentoDelegate.adjuntarArchivoDigitalAExpediente(expedienteDTO, adjunto, funcionarioDTO, actividad);
			log.info("regreso"+ exito);
			return mapping.findForward("success");
					
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			return mapping.findForward("fail");
		}
	}
		
	/**
	 * Metodo utilizado para adjuntar una actividad al expediente 
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward asociarActividadExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("Asociar actividad a expediente en defensoria");
			
			Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"), 0);
			String numeroExpediente = request.getParameter("numeroExpediente");
			Long actividad = NumberUtils.toLong(request.getParameter("actividad"), 0);
			
			ExpedienteDTO expediente = super.getExpedienteTrabajo(request, numeroExpediente);
			if(expediente == null && numeroExpedienteId > 0){
				expediente = new ExpedienteDTO();
				expediente.setNumeroExpedienteId(numeroExpedienteId);
				expediente = expedienteDelegate.obtenerExpedienteDefensoria(expediente);
			}
			
			FuncionarioDTO funcionario = getUsuarioFirmado(request).getFuncionario();
			
			actividadDelegate.registrarActividad(expediente, funcionario, actividad);	

		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
}
