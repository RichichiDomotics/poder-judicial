/**
* Nombre del Programa : EvaluarDocumentosRecibidosAMP.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/07/2011
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
package mx.gob.segob.nsjp.web.pericial.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.NotaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Action para atender las solicitudes de evaluación de documentos
 * recibidos por el agente del ministerio público procedentes de servicios periciales
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class EvaluarDocumentosRecibidosAMP extends GenericAction {
	@Autowired
	DocumentoDelegate documentoDelegate;
	/**
	 * Realizar una consulta para obtener los documentos recibidos para el agente procedente
	 * de servicios periciales y presenta una lista de estos documentos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws Exception
	 */
	public ActionForward consultarDocumentosRecibidos(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		
		Long tipoForma = NumberUtils.toLong(request.getParameter("formaId"));
		List<DictamenDTO> documentos = documentoDelegate.
		consultarDocumentosRecibidosPorUsuario(Formas.getByValor(tipoForma), getUsuarioFirmado(request).getFuncionario());
		
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter writer = response.getWriter();
		writer.print("<rows>");
		writer.print("<records>" + documentos.size() + "</records>");
		for (DictamenDTO dictamen : documentos) {
			   
			writer.print("<row id='"+ dictamen.getDocumentoId() + "'>");
			writer.print("<cell>"+ (dictamen.getSolicitudPericial().getExpedienteDTO()!=null?
									dictamen.getSolicitudPericial().getExpedienteDTO().getNumeroExpediente()
									:"") +  "</cell>");
			writer.print("<cell>"+ (dictamen.getSolicitudPericial().getExpedienteDTO()!=null && 
									dictamen.getSolicitudPericial().getExpedienteDTO().getCasoDTO()!=null?
									dictamen.getSolicitudPericial().getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso()
									:"") +  "</cell>");
			writer.print("<cell>"+  dictamen.getSolicitudPericial().getDestinatario().getNombreCompleto() +  "</cell>");
			writer.print("<cell>"+  dictamen.getSolicitudPericial().getDestinatario().getEspecialidad().getValor() +  "</cell>");
			writer.print("<cell>"+  DateUtils.formatear(dictamen.getFechaCreacion()) +  "</cell>");
			writer.print("</row>");
		}
		writer.print("</rows>");
		writer.flush();
		writer.close();
		
		return null;
		
	}
	
	/**
	 * Consulta el detalle de un Dictamen en base a su identificador
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws Exception
	 */
	public ActionForward consultarDetalleDictamen(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) 	throws Exception {
	
		Long dictamenId = NumberUtils.toLong(request.getParameter("dictamenId"));
		
		DictamenDTO dictamen = documentoDelegate.consultarDetalleDictamenPorId(dictamenId);
		dictamen.setTotalNotas(documentoDelegate.consultarNotasPorDocumento(dictamenId).size());
		escribirRespuesta(response, converter.toXML(dictamen));
	
		return null;
	
	}
	
	
	/**
	 * Consulta las notas asociadas a un documento
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws Exception
	 */
	public ActionForward consultarNotasPorDocumento(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) 	throws Exception {
	
		Long dictamenId = NumberUtils.toLong(request.getParameter("dictamenId"));
		
		
		List<NotaDTO> notas = documentoDelegate.consultarNotasPorDocumento(dictamenId);
		escribirRespuesta(response, converter.toXML(notas));
	
		return null;
	
	}
	
	/**
	 * Consulta el detalle de una nota en base a su identificador
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws Exception
	 */
	public ActionForward consultarDetalleNota(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) 	throws Exception {
	
		Long notaId = NumberUtils.toLong(request.getParameter("notaId"));
		
		NotaDTO nota = documentoDelegate.consultarDetalleNotaPorId(notaId);
		
		escribirRespuesta(response, converter.toXML(nota));
	
		return null;
	
	}
	
	/**
	 * Crea o actualiza una nota y la asocia al documento recibido como parámetro
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws Exception
	 */
	public ActionForward guardarNota(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) 	throws Exception {
	
		Long notaId = NumberUtils.toLong(request.getParameter("notaId"));
		Long documentoId = NumberUtils.toLong(request.getParameter("dictamenId"));
		
		NotaDTO nota = documentoDelegate.consultarDetalleNotaPorId(notaId);
		if(nota == null){
			nota = new NotaDTO();
			nota.setFechaCreacion(new Date());
		}
		
		nota.setFechaActualizacion(new Date());
		nota.setNombreNota(request.getParameter("nombreNota"));
		nota.setDescripcion(request.getParameter("descripcionNota"));
		nota.setDocumento(new DocumentoDTO(documentoId));
		if(nota.getIdNota() == null){
			documentoDelegate.asociarNotaADocumento(nota, new DocumentoDTO(documentoId));
		}else{
			documentoDelegate.actualizarNota(nota);
		}
		
	
		return null;
	
	}
	
	
	
}
