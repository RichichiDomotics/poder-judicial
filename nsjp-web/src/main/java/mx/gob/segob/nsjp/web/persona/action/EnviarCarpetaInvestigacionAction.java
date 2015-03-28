/**
 * 
 */
package mx.gob.segob.nsjp.web.persona.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author EdgarAG
 *
 */
public class EnviarCarpetaInvestigacionAction extends GenericAction {
	
	private static final Logger log = Logger.getLogger(EnviarCarpetaInvestigacionAction.class);
	@Autowired
	private ExpedienteDelegate expedienteDelegate ;
	
	public ActionForward enviarCarpeta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String folioSolicitud = request.getParameter("folioSol");
	     log.info("folioSolicitud:: "+folioSolicitud);
	     String numeroGeneralCaso = request.getParameter("caso");
	     log.info("numeroGeneralCaso:: "+numeroGeneralCaso);
	     //String nombreCompleto = request.getParameter("proresp");
	     //log.info(nombreCompleto);
	    
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
    	try {
    	expedienteDTO =  expedienteDelegate.enviarCarpetaDeInvestigacion(numeroGeneralCaso, folioSolicitud);
    	String xml = null;
		PrintWriter pw = null;
		//converter.alias("defensorDTOs",  java.util.List.class);
		converter.alias("expedienteDTO", ExpedienteDTO.class);
		xml = converter.toXML(expedienteDTO);
		response.setContentType("text/xml");
		pw = response.getWriter();
		pw.print(xml);
		pw.flush();
		pw.close();
		log.info("regreso del envio de carpeta" + expedienteDTO );			
    		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
			
		return null;

			
	}
	

}
