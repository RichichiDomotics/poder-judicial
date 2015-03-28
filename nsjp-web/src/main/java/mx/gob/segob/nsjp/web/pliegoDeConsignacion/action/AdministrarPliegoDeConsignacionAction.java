/**
 * 
 */
package mx.gob.segob.nsjp.web.pliegoDeConsignacion.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.dto.documento.CuerpoOficioEstructuradoDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author AlejandroGA
 *
 */
public class AdministrarPliegoDeConsignacionAction extends GenericAction{
	
	/* Log de clase*/
	private static final Logger log  = Logger.getLogger(AdministrarPliegoDeConsignacionAction.class);
	
	@Autowired
	public DocumentoDelegate documentoDelegate;
	
	
	
	/**
	 * MEtodo para consultar los pliegos de consignacion del Expediente
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarPliegoDeConsignacionExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("EJECUTANDO ACTION:::consultarPliegoDeConsignacionExpediente");
			
			String numeroExpediente=request.getParameter("numeroExpediente");
			log.info("PLIEGO DE CONSIGNACION::::::::::Numero expediente::::"+numeroExpediente);
			ExpedienteDTO expedienteDTO=super.getExpedienteTrabajo(request, numeroExpediente);
			log.info("PLIEGO DE CONSIGNACION::::::::::::::expedienteDTO::::"+expedienteDTO);
			
			DocumentoDTO documentoDTO=documentoDelegate.consultarPliegoDeConsignacionXExpediente(expedienteDTO);
			
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
			
			
			log.info("PLIEGO DE CONSIGNACION DTO### DOCUMENTO::::"+documentoDTO);
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

}
