package mx.gob.segob.nsjp.web.caso.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.caso.CasoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDesignacionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.solicitud.action.SolicitudDefensorAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class VincularNumeroExpedienteConCasoAction extends GenericAction {

	private static final Logger log = Logger.getLogger(SolicitudDefensorAction.class);
	
	@Autowired
	private CasoDelegate casoDelegate;
	@Autowired
	private ExpedienteDelegate expedienteDelegate;
	
	
	public ActionForward consultarCasosPorFiltro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {

			String numeroGeneralCaso = request.getParameter("numeroCaso");
			
			if(numeroGeneralCaso == null){
				numeroGeneralCaso = "";
			}
			CasoDTO caso = new CasoDTO();
			caso.setNumeroGeneralCaso(numeroGeneralCaso);
			List<ExpedienteDTO> expedientes = expedienteDelegate.consultarExpedientesPorCaso(caso);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			long registros = 0;
			
			List<InvolucradoDTO> imputados= null;
			
			for (ExpedienteDTO expediente : expedientes) {
				imputados = expediente.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA);
				if(imputados.isEmpty()){
					registros ++;
				}else{
					registros += imputados.size();
				}
			}

			final PaginacionDTO pag = PaginacionThreadHolder.get();
			
			writer.print("<rows>");
            log.debug("pag :: " + pag);
            if (pag != null && pag.getTotalRegistros() != null) {
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
            } else {
                writer.print("<total>0</total>");
                writer.print("<records>0</records>");
            }

			int consec = 1;
			for (ExpedienteDTO expediente : expedientes) {
				caso = expediente.getCasoDTO(); 
				imputados = expediente.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA);
				for (InvolucradoDTO imputado : imputados) {
					writer.print("<row id='" + consec++ + "'>");
					writer.print("<cell>" + caso.getCasoId() + "</cell>");
					writer.print("<cell>" + caso.getNumeroGeneralCaso() + "</cell>");
					writer.print("<cell>" + DateUtils.formatear(caso.getFechaApertura())
										  + "-"
										  + DateUtils.formatearHora(caso.getFechaApertura()) + "</cell>");
					writer.print("<cell>" + imputado.getNombreCompleto() + "</cell>");
					writer.print("<cell>" + imputado.getElementoId() + "</cell>");
					writer.print("</row>");
				}
			}

			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
		
	}
	
	public ActionForward vincularNumeroExpedienteConCaso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String casoId = request.getParameter("casoId");
			String numeroExpedienteId = request.getParameter("numeroExpedienteId");
			String involucradoId = request.getParameter("involucradoId");
			InvolucradoDTO invoDto = null;
			if (involucradoId!=null && NumberUtils.isNumber(involucradoId)){
			    invoDto = new InvolucradoDTO(Long.parseLong(involucradoId));
			}
			ExpedienteDTO expediente = new ExpedienteDTO();
			expediente.setNumeroExpedienteId(new Long(numeroExpedienteId));
			CasoDTO caso = new CasoDTO();
			caso.setCasoId(new Long(casoId));
			casoDelegate.vincularExpedienteConCaso(expediente, caso, invoDto);
			PrintWriter pw = response.getWriter();
			response.setContentType("text/xml");
			pw.print("<respuesta>exito</respuesta>");
			pw.flush();
			pw.close();
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			PrintWriter pw = response.getWriter();
			response.setContentType("text/xml");
			pw.print("<respuesta>fallo</respuesta>");
			pw.flush();
			pw.close();
		}
		return null;
	}	
		
}
