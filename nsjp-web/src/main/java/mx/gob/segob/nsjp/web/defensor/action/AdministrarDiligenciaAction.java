package mx.gob.segob.nsjp.web.defensor.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDiligenciaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;


public class AdministrarDiligenciaAction extends GenericAction{
	private static final Logger log  = Logger.getLogger(AdministrarDiligenciaAction.class);
	
	@Autowired
	private SolicitudDelegate solicitudDelegate;
	
	//diligencia asociada al expediente para grid 
	//DATOS PARA GRID
//	Tipo de diligencia.
//	  - Descripción de la diligencia.
//	  - Fecha y hora de la diligencias.
	//asocia la descripción, fecha y hora de la diligencia capturada por el usuario, con el número de expediente.

	public ActionForward diligenciaAsociadaExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		ExpedienteDTO expedienteDTO = new ExpedienteDTO(); //FIXME EAPS Obtener expedienteId para asignarlo a ExpedienteDTO
		
    	try {
    		List<SolicitudDiligenciaDTO> solsDiligenciaDTO = solicitudDelegate.consultarDiligenciasDelExpediente(expedienteDTO);
    		
		} catch (Exception e) {
			log.info(e);
		}
			
		return null;

			
	}
	
	public ActionForward guardarDiligencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		
    	try {
    		
    		
    		//solicitudDelegate.registrarSolicitudDiligencia(solicitudDiligenciaDTO);
			}
		catch (Exception e) {
			log.info(e);
		}
			
		return null;

			
	}

}
