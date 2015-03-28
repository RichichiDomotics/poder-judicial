package mx.gob.segob.nsjp.web.lineadeinvestigacion.action;

import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_ATTACH_FILE_NAME;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_CACHE_CONTROL;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_CONTENT_DISPOSITION;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_NOCACHE;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_PRAGMA;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.policiaministerial.ComentarioDelegate;
import mx.gob.segob.nsjp.delegate.policiaministerial.LineaInvestigacionDelegate;
import mx.gob.segob.nsjp.delegate.policiaministerial.SeguimientoLIDelegate;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class LineaInvestigacionAction extends GenericAction{
	/* Log de clase*/
	private static final Logger logger  = Logger.getLogger(LineaInvestigacionAction.class);

	@Autowired
	public SeguimientoLIDelegate seguimiento;
	@Autowired
	public LineaInvestigacionDelegate investigacion;
	@Autowired
	public ComentarioDelegate comentarioDelegate;
	@Autowired
	private ExpedienteDelegate expedienteDelegate;
	
	public ActionForward registrarHipotesisExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			logger.info("ejecutando Action LineaInvestigacionAction en metodo registrarHipotesisExpediente");
			try {
				String numeroUnicoExpediente = request.getParameter("numeroUnicoExpediente");
				String hipotesis=request.getParameter("hipotesis");
				Long idHipotesis = NumberUtils.toLong(request.getParameter("idHipotesis"),0);
				
				logger.info("ejecutando Action LineaInvestigacionAction en metodo registrarHipotesisExpediente parametro idnumero expediente:"+numeroUnicoExpediente);
				logger.info("ejecutando Action LineaInvestigacionAction en metodo registrarHipotesisExpediente parametro hipotesis:"+hipotesis);
				logger.info("ejecutando Action LineaInvestigacionAction en metodo registrarHipotesisExpediente parametro idHipotesis:"+idHipotesis);
				
				SeguimientoLIDTO loHipotesis=new SeguimientoLIDTO();
				
				if(idHipotesis > 0)
					 loHipotesis.setSeguimientoLIId(idHipotesis);
				else{
					loHipotesis.setSeguimientoLIId(null); 
				}				
				loHipotesis.setFechaCreacion(new Date());
				//Se asigna la hipotesis
				loHipotesis.setHipotesis(hipotesis);				
				//Se asigna el numero de expediente
				ExpedienteDTO expedienteDTO = super.getExpedienteTrabajo(request, request.getParameter("numeroUnicoExpediente"));
				loHipotesis.setExpedienteDTO(expedienteDTO);
				//Se asigna el usuario
				UsuarioDTO usuarioDTO = super.getUsuarioFirmado(request);
				loHipotesis.setUsuario(usuarioDTO);
				//Se registra la hipotesis
				Long idHipotesisBD=seguimiento.guardarSeguimientoLI(loHipotesis);
				SeguimientoLIDTO hipotesisBD=new SeguimientoLIDTO(idHipotesisBD);
				
				converter.alias("SeguimientoLIDTO", SeguimientoLIDTO.class);
				String xml = converter.toXML(hipotesisBD);
				if(logger.isDebugEnabled())
				{
					logger.info(xml);
					logger.info("RGG:: "+hipotesisBD.getSeguimientoLIId());
				}				
				escribirRespuesta(response, xml);
			} catch (Exception e) {		
				logger.info(e.getCause(),e);
				escribirRespuesta(response, "");
			}
			return null;
	}
	
	public ActionForward registrarLineaInvestigacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws IOException {
		logger.info("ejecutando Action LineaInvestigacionAction en metodo registrarLineaInvestigacion");
		try {
			String numeroUnicoExpediente = request.getParameter("numeroUnicoExpediente");
			String temaDeLaLinea = request.getParameter("temaDeLaLinea");
			Long idLineaInvestigacion = NumberUtils.toLong(request.getParameter("idLineaInvestigacion"),0);
			Long idTituloLineaInvestigacion = NumberUtils.toLong(request.getParameter("idTituloLineaInvestigacion"),0);
			
			logger.info("ejecutando Action LineaInvestigacionAction en metodo registrarHipotesisExpediente parametro numeroUnicoExpediente:"+numeroUnicoExpediente);
			logger.info("ejecutando Action LineaInvestigacionAction en metodo registrarHipotesisExpediente parametro temaDeLaLinea:"+temaDeLaLinea );
			logger.info("ejecutando Action LineaInvestigacionAction en metodo registrarHipotesisExpediente parametro idLineaInvestigacion:"+idLineaInvestigacion);
			logger.info("ejecutando Action LineaInvestigacionAction en metodo registrarHipotesisExpediente parametro idTituloLineaInvestigacion:"+idTituloLineaInvestigacion);
			
			
			LineaInvestigacionDTO loLinea = new LineaInvestigacionDTO();
			
			if(idLineaInvestigacion > 0)
				loLinea.setLineaInvestigacionId(idLineaInvestigacion); 
			else{
				loLinea.setLineaInvestigacionId(null); 
			}
			loLinea.setFechaCreacion(new Date());
			
			//Se asigna el numero de expediente par obtener el id del seguimiento
			ExpedienteDTO expedienteDTO = super.getExpedienteTrabajo(request, request.getParameter("numeroUnicoExpediente"));
			List<SeguimientoLIDTO> loSeguimientos = seguimiento.consultarSeguimientosLIXExpedienteId(expedienteDTO);				
			SeguimientoLIDTO loSeguimiento = null;
			if(loSeguimientos != null && loSeguimientos.size() >0){
				loSeguimiento = loSeguimientos.get(0);
			}				
			loLinea.setSeguimientoLIDTO(loSeguimiento);
			loLinea.setExpedienteDTO(expedienteDTO);
			
			//Se asigna el tema de la linea de investigacion
			loLinea.setDescripcion( temaDeLaLinea );
			//Se asigna el titulo de la linea de investigacion
			loLinea.setTipoLineaInvestigacioValorDTO(new ValorDTO(idTituloLineaInvestigacion));
			
			//Se asigna el usuario
			UsuarioDTO usuarioDTO = super.getUsuarioFirmado(request);
			loLinea.setUsuario(usuarioDTO);
			
			//Se registra la linea de investigacion
			Long idLineaInvBD=investigacion.guardarLineaInvestigacion(loLinea);
			
			LineaInvestigacionDTO lineaBD = new LineaInvestigacionDTO(idLineaInvBD);
			
			converter.alias("LineaInvestigacionDTO", LineaInvestigacionDTO.class);
			String xml = converter.toXML(lineaBD);
			if(logger.isDebugEnabled())
			{
				logger.info(xml);
				logger.info("RGG:: "+lineaBD.getLineaInvestigacionId());
			}				
			escribirRespuesta(response, xml);
		} catch (Exception e) {		
			logger.info(e.getCause(),e);
			escribirRespuesta(response, "");
		}
		return null;
	}
	
	
	public ActionForward registrarComentario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			logger.info("ejecutando Action LineaInvestigacionAction en metodo registrarComentario");
			try {

				Long idLineaInvestigacion = NumberUtils.toLong(request.getParameter("idLineaInvestigacion"),0);
				String comentario = request.getParameter("comentario");
				
				logger.info("ejecutando Action LineaInvestigacionAction en metodo registrarHipotesisExpediente parametro comentario:"+comentario);
				logger.info("ejecutando Action LineaInvestigacionAction en metodo registrarHipotesisExpediente parametro idLineaInvestigacion:"+idLineaInvestigacion);
				
				ComentarioDTO loComentario=new ComentarioDTO();
				
				//Se asigna el comentario
				loComentario.setDescripcion(comentario);
				//Se asigna el numero de investigacion al cual quedara asociado el comentario
				loComentario.setLineaInvestigacionDTO(new LineaInvestigacionDTO(idLineaInvestigacion));
				//Se asigna el usuario
				UsuarioDTO usuarioDTO = super.getUsuarioFirmado(request);
				loComentario.setUsuario(usuarioDTO);
				//Se asigna el funcionario
				loComentario.setFuncionarioDTO(usuarioDTO.getFuncionario());				
				//Se registra el comentario
				Long idComentarioBD= comentarioDelegate.guardarComentario(loComentario);
				ComentarioDTO hipotesisBD=new ComentarioDTO(idComentarioBD);
				
				converter.alias("ComentarioDTO", ComentarioDTO.class);
				String xml = converter.toXML(hipotesisBD);
				if(logger.isDebugEnabled())
				{
					logger.info(xml);
					logger.info("RGG:: "+hipotesisBD.getComentarioId());
				}				
				escribirRespuesta(response, xml);
			} catch (Exception e) {		
				logger.info(e.getCause(),e);
				escribirRespuesta(response, "");
			}
			return null;
	}
	

	
	public ActionForward consultarLineasInvestigacionXExpedienteId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			logger.info("ejecutando Action LineaInvestigacionAction en metodo consultarLineasInvestigacionXSeguimiento");
			try {
				String numeroUnicoExpediente = request.getParameter("numeroUnicoExpediente");
				String pantalla= request.getParameter("pantalla");
				if(pantalla == null)
					pantalla = "";
								
				logger.info("ejecutando Action LineaInvestigacionAction en metodo consultarLineasInvestigacionXSeguimiento parametro numeroUnicoExpediente:"+numeroUnicoExpediente);
				logger.info("ejecutando Action LineaInvestigacionAction en metodo consultarLineasInvestigacionXSeguimiento parametro pantalla:"+pantalla);
					
				//Se asigna el numero de expediente par obtener el id del seguimiento
				ExpedienteDTO expedienteDTO = super.getExpedienteTrabajo(request, request.getParameter("numeroUnicoExpediente"));

				List<LineaInvestigacionDTO> loInvestigaciones = investigacion.consultarLineasInvestigacionXExpedienteId(expedienteDTO);
				StringBuffer respuesta = new StringBuffer();
				//Se arman las investigaciones
				for(int i=1; i<= loInvestigaciones.size();i++){
					LineaInvestigacionDTO loInvestigacionBD = loInvestigaciones.get(i-1); 
					respuesta.append("<div id=\"idLineaInv_").append(i).append("\" class=\"lineasDeInvestigacion\">");
					  respuesta.append("<div class=\"titleLineaInvestigacion\">");
					      respuesta.append("<a href=\"#\" id=\"idLigaLineaInvestigacion_").append(i).append("\">");
					      		respuesta.append("<img id=\"imgAddLineaInvestigacion_").append(i).append("\"  ");
					      			respuesta.append("onclick='$(\"#detalleLineaInvestigacion_").append(i).append("\").toggle(200);$(\"#imgAddLineaInvestigacion_").append(i).append("\").toggle(0);$(\"#imgRemoveLineaInvestigacion_").append(i).append("\").toggle(0);' src='").append(request.getContextPath()).append("/resources/images/add.png'>");
					      		respuesta.append("<img id=\"imgRemoveLineaInvestigacion_").append(i).append("\" ");
					      			respuesta.append("onclick='$(\"#detalleLineaInvestigacion_").append(i).append("\").toggle(200);$(\"#imgAddLineaInvestigacion_").append(i).append("\").toggle(0);$(\"#imgRemoveLineaInvestigacion_").append(i).append("\").toggle(0);' ");
					      			respuesta.append("src='").append(request.getContextPath()).append("/resources/images/remove.png' style=\"display: none\">");
					      respuesta.append("</a> &nbsp; Línea de investigación " + loInvestigacionBD.getConsecutivo());
					  respuesta.append("</div>");
					  respuesta.append("<div id=\"detalleLineaInvestigacion_").append(i).append("\" class=\"detalleLineaInvestigacion\">");
					      respuesta.append("<table border=\"0\" width=\"100%\" id=\"tblLineaInvestigacion_").append(i).append("\" class=\"cuerpoLineaInvestigacion\">");
					      //Permite mostrar u ocultar los botones
					      if(loInvestigacionBD.getEsImpreso() == null || loInvestigacionBD.getEsImpreso() == false){
						        respuesta.append("<tr id=\"trBotonesLinea_").append(loInvestigacionBD.getLineaInvestigacionId()).append("\">");
						          respuesta.append("<td align=\"right\"><span style=\"vertical-align: top\">");
						            respuesta.append("<input name=\"btnIngresarComentario_").append(i)
						            	.append("\" type=\"button\" id=\"btnIngresarComentario_").append(i)
						            	.append("\" onclick=\"ingresarComentario(").append(loInvestigacionBD.getLineaInvestigacionId()).append(")")
						            	.append(";actualizaEtiqueta(").append(i).append(")")
						            	.append("\" value=\"Ingresar comentario\">");
						          respuesta.append("<input name=\"btnImprimir_").append(i).append("\" type=\"button\" id=\"btnImprimir_").append(i)
					              .append("\" onclick=\"imprimirLinea(").append(loInvestigacionBD.getLineaInvestigacionId()).append(")").
						          append("\" value=\"Imprimir\">");
						          if (pantalla != null && !pantalla.equals("6")){// Si la pantalla es solicitada por un policia Ministerial entonces no se muestra el boton de cerrar
						        	  respuesta.append("<input name=\"btnCerrar_").append(i).append("\" type=\"button\" id=\"btnCerrar_").append(i)
	      					          .append("\" onclick=\"cerrarLinea(").append(loInvestigacionBD.getLineaInvestigacionId()).append(")")
							          .append("\" value=\"Cerrar\">");  
						          }
						          
						          respuesta.append("</span></td>");
						        respuesta.append("</tr>");					    	  
					      }

					        respuesta.append("<tr>");
					          respuesta.append("<td>Título: ").append(loInvestigacionBD.getTipoLineaInvestigacioValorDTO().getValor()).append("</td>");
					        respuesta.append("</tr>");
					        respuesta.append("<tr>");
					          respuesta.append("<td>Tema: ").append(loInvestigacionBD.getDescripcion()).append("</td>");
					        respuesta.append("</tr>");
					        respuesta.append("<tr>");
					          respuesta.append("<td>&nbsp;</td>");
					        respuesta.append("</tr>");
					        respuesta.append("<tr>");
					          respuesta.append("<td>Texto ingresado el día ").append(DateUtils.formatear(loInvestigacionBD.getFechaCreacion()))
					          .append(" a las ").append(DateUtils.formatearHora(loInvestigacionBD.getFechaCreacion()))
					          .append("</td>");
					        respuesta.append("</tr>");
					      respuesta.append("</table>");
					List<ComentarioDTO> loComentarios = loInvestigacionBD.getComentarioDTOs();
					if(loComentarios != null && loComentarios.size() > 0){
						for(int j=1; j<= loComentarios.size();j++){
							ComentarioDTO loComentario = loComentarios.get(j-1);
							respuesta.append("<table border=\"0\" width=\"100%\" id=\"tblComentario_").append(i).append(j).append("\" class=\"comentario\">");
							respuesta.append("    <tr>");
							respuesta.append("      <td>").append(loComentario.getDescripcion()).append("</td>");
							respuesta.append("    </tr>");
							respuesta.append("    <tr>");
					          respuesta.append("<td align='RIGHT'>Texto ingresado por ")
					          .append(loComentario.getFuncionarioDTO().getNombreCompleto())
					          .append(", el día " )
					          .append(DateUtils.formatear(loComentario.getFechaCreacion()))
					          .append(" a las ").append(DateUtils.formatearHora(loComentario.getFechaCreacion()))
					          .append("</td>");
							respuesta.append("    </tr>");
							respuesta.append("  </table>");
						}
					}
				    respuesta.append("</div>");
					respuesta.append("</div>");
				}				
				String xml = converter.toXML(respuesta);
				if(logger.isDebugEnabled())
				{
					logger.info(xml);
				}				
				escribirRespuesta(response, xml);
			} catch (Exception e) {		
				logger.info(e.getCause(),e);
				escribirRespuesta(response, "");
			}
			return null;
	}
	
	

	public ActionForward consultarHipotesisPorNumExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			logger.info("ejecutando Action LineaInvestigacionAction en metodo consultarHipotesis");
			try {
				String numeroUnicoExpediente = request.getParameter("numeroUnicoExpediente");
				
				logger.info("ejecutando Action LineaInvestigacionAction en metodo registrarHipotesisExpediente parametro idnumero expediente:"+numeroUnicoExpediente);
				List<SeguimientoLIDTO> seguimientos = null;
				SeguimientoLIDTO loSeguimientoBD = new SeguimientoLIDTO(0L);
				
				//Se obtiene el numero de expediente
				ExpedienteDTO expedienteDTO = super.getExpedienteTrabajo(request, request.getParameter("numeroUnicoExpediente"));
				
				seguimientos = seguimiento.consultarSeguimientosLIXExpedienteId(expedienteDTO);
				if(seguimientos != null && seguimientos.size() > 0){
					loSeguimientoBD = seguimientos.get(0);
				}	
				
				converter.alias("SeguimientoLIDTO", SeguimientoLIDTO.class);
				String xml = converter.toXML(loSeguimientoBD);
				if(logger.isDebugEnabled())
				{
					logger.info(xml);
					logger.info("RGG:: "+loSeguimientoBD.getSeguimientoLIId());
				}				
				escribirRespuesta(response, xml);
			} catch (Exception e) {		
				logger.info(e.getCause(),e);
				escribirRespuesta(response, "");
			}
			return null;
	}
	
	
	/**
     * Realiza la consulta de un documento o archivoDigital en base a su ID enviado como parámetro del 
     * request.
     * Lo recupera de la base de datos, consulta su archivo digital y el contenido
     * lo escribe al response con el nombre del archivo digital y el tipo de archivo
     * Parametros necesario:
     * documentoId - Identificador del documento o
     * archivoDigitalId - Identificador del archivoDigital
     * 
     * @param mapping Configuración del mapping
     * @param form Form generado
     * @param request Objeto request
     * @param response Objeto response
     * @return null
     */
    public ActionForward consultarDocumentoPorIDLinea(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)  {    	
    	try {
    		
    		logger.info("EJECUTANDO consultarDocumentoPorIDLinea................");

    			Long lineaInvestigacionId = request.getParameter("lineaInvestigacionId")!=null?NumberUtils.toLong(request.getParameter("lineaInvestigacionId")):null;
	    		logger.info("LINEA DE INVESTIGACION OBTENIDA = "+lineaInvestigacionId);
	    		
    			Long esGuardado = request.getParameter("esGuardado")!=null?NumberUtils.toLong(request.getParameter("esGuardado")):null;
	    		logger.info("esGuardado = "+esGuardado);
	    		LineaInvestigacionDTO loLinea = new LineaInvestigacionDTO();
	    		loLinea.setLineaInvestigacionId(lineaInvestigacionId);	    		
	    		
	    		DocumentoDTO loDocumento = investigacion.crearDocumentoLineaInvestigacion(loLinea, esGuardado == 1 ? true : false);
    			response.setContentType("application/octet-stream");
	            response.setHeader(ENCABEZADO_CACHE_CONTROL, ENCABEZADO_NOCACHE);
	            response.setHeader(ENCABEZADO_PRAGMA, ENCABEZADO_NOCACHE);
    			response.setHeader(ENCABEZADO_CONTENT_DISPOSITION,
    	            		ENCABEZADO_ATTACH_FILE_NAME
    	                    + loDocumento.getArchivoDigital().getNombreArchivo().trim().replaceAll(" ", "_") + loDocumento.getArchivoDigital().getTipoArchivo());
    			response.setContentLength(loDocumento.getArchivoDigital().getContenido().length);
    			  ServletOutputStream sos = response.getOutputStream();
    	            sos.write(loDocumento.getArchivoDigital().getContenido());
    	            sos.flush();

		    }catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		    
        return null;
    	
    }	
    
    
	public ActionForward consultarComentario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			logger.info("ejecutando Action LineaInvestigacionAction en metodo consultarComentario");
			try {
				Long comentarioId = NumberUtils.toLong(request.getParameter("comentarioId"),0);				
				logger.info("ejecutando Action LineaInvestigacionAction en metodo consultarComentario parametro comentarioId:"+comentarioId);
				
				StringBuffer respuesta = new StringBuffer();
							ComentarioDTO loComentario = comentarioDelegate.consultarComentario(new ComentarioDTO(comentarioId));
							respuesta.append("<table border=\"0\" width=\"100%\" id=\"tblComentario_").append("x").append(comentarioId).append("\" class=\"comentario\">");
							respuesta.append("    <tr>");
							respuesta.append("      <td>").append(loComentario.getDescripcion()).append("</td>");
							respuesta.append("    </tr>");
							respuesta.append("    <tr>");
					          respuesta.append("<td align='RIGHT'>Texto ingresado por ")
					          .append(loComentario.getFuncionarioDTO().getNombreCompleto())
					          .append(", el día " )
					          .append(DateUtils.formatear(loComentario.getFechaCreacion()))
					          .append(" a las ").append(DateUtils.formatearHora(loComentario.getFechaCreacion()))
					          .append("</td>");
							respuesta.append("    </tr>");
							respuesta.append("  </table>");								
				String xml = converter.toXML(respuesta);
				if(logger.isDebugEnabled())
				{
					logger.info(xml);
				}				
				escribirRespuesta(response, xml);
			} catch (Exception e) {		
				logger.info(e.getCause(),e);
				escribirRespuesta(response, "");
			}
			return null;
	}
	
}
