/**
 * 
 */
package mx.gob.segob.nsjp.web.MedidasCautelares.Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.medidascautelares.MedidasCautelaresDelegate;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.MedidaCautelarDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author AlejandroGA
 *
 */
public class AdministrarMedidasCautelaresSSPAction extends GenericAction{
	
	@Autowired
	private DocumentoDelegate documentoDelegate;
	@Autowired
	private MedidasCautelaresDelegate medidasCautelaresDelegate;	
	
	
	/** Log de clase */
	private static final Logger log = Logger.getLogger(AdministrarMedidasCautelaresSSPAction.class);
	
	/**
	 * Funcion para consultar los  Documentos asociados a medidas cualtelares
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @author AlejandroGA
	 */
	public ActionForward consultarDocumentosAsociadosAMedidaCautelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			Long medidaId = NumberUtils.toLong(request.getParameter("medidaId")); 
			log.info("ID de la medida cautelar para consultar documentos .:: "+medidaId);

			if(medidaId >0){
				
				MedidaDTO medidaDTO  = new MedidaDTO();
				medidaDTO.setDocumentoId(medidaId);
				
				List<ArchivoDigitalDTO> listaDocumentoDTOs = documentoDelegate.consultarArchivoDigitalPorMedida(medidaDTO);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=listaDocumentoDTOs.size();
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (ArchivoDigitalDTO documentoDTO : listaDocumentoDTOs) {
					writer.print("<row id='"+documentoDTO.getArchivoDigitalId()+"'>");
					writer.print("<cell>"+documentoDTO.getNombreArchivo()+"</cell>");
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
				
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	public ActionForward crearDocumentoParaMedidaCautelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
					
		try {
			DocumentoDTO documentoDTO = new DocumentoDTO();
			FormaDTO forma =null;
			Long formaId = NumberUtils.toLong(request.getParameter("formaId"),0);
			log.info("::::::::::::: FORMA ID ::::::::::::.:: "+formaId);
			log.info("::::::::::::: NUMERO UNICO DE EXPEDIENTE ::::::::::::.:: "+request.getParameter("numeroUnicoExpediente"));
			forma = documentoDelegate.buscarForma(formaId);
			documentoDTO.setFechaCreacion(new Date());
			documentoDTO.setFormaDTO(forma);
			documentoDTO.setNombreDocumento(forma.getNombre());
			documentoDTO.setTipoDocumentoDTO(new ValorDTO(TipoDocumento.DOCUMENTO.getValorId()));					
			
			ExpedienteDTO expTrabajo = super.getExpedienteTrabajo(request, request.getParameter("numeroUnicoExpediente"));
			
			Long documentoId = documentoDelegate.guardarDocumento(documentoDTO, expTrabajo);
			converter.alias("documentoId", Long.class);
			escribirRespuesta(response, converter.toXML(documentoId));
		} catch (NSJPNegocioException e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}		
		return null;
	}
	
	public ActionForward colocarDocumentoParaMedidaCautelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
				
		try {
			log.info("::::::::::::: EJECUTANDO ACTION COLOCAR DOCUMENTO PARA MEDIDA CAUTELAR ::::::::::::.:: ");
			Long cautelarId = NumberUtils.toLong(request.getParameter("medidaId"),0);
			log.info("::::::::::::: ID DE LA MEDIDA CAUTELAR ::::::::::::.:: "+cautelarId);
			MedidaCautelarDTO cautelarDTO = medidasCautelaresDelegate.consultarMedidasCautelaresPorId(cautelarId);
			setExpedienteTrabajo(request, cautelarDTO.getExpedienteDTO());
			escribirRespuesta(response, converter.toXML(cautelarDTO.getExpedienteDTO()));
		} catch (NSJPNegocioException e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		
		return null;
	}
	
	/**
	 * Funcion paraasociar la medida cautelar con el archivo digital
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @author AlejandroGA
	 */
	public ActionForward asociarMedidaCautelarConArchivoDigital(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {				
		
		try {
			
			Long medidaId = NumberUtils.toLong(request.getParameter("medidaId"),0);
			Long documentoId = NumberUtils.toLong(request.getParameter("documentoId"),0);
			
			if(medidaId >0 && documentoId>0){
				DocumentoDTO documentoDTO = new DocumentoDTO();
				MedidaCautelarDTO medidaCautelarDTO = new MedidaCautelarDTO();
				documentoDTO.setDocumentoId(documentoId);
				medidaCautelarDTO.setDocumentoId(medidaId);
				medidasCautelaresDelegate.asociarDocumentoConMedidaCautelar(documentoDTO, medidaCautelarDTO);
			}
			
			
			
		} catch (NSJPNegocioException e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}		
		
		return null;
	}
}
