package mx.gob.segob.nsjp.web.caso.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.caso.CasoDelegate;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDesignacionDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.solicitud.action.SolicitudDefensorAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class VictularNumeroExpedienteConCasoAction extends GenericAction {

	private static final Logger log = Logger.getLogger(SolicitudDefensorAction.class);
	
	@Autowired
	private CasoDelegate casoDelegate;

	public ActionForward consultarCasosPorFiltro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {

			String numeroGeneralCaso = request.getParameter("numeroCaso");
			List<CasoDTO> casos = casoDelegate.consultarCasos(numeroGeneralCaso);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");
			writer.print("<records>" + casos.size() + "</records>");


			for (CasoDTO caso : casos) {
				writer.print("<row id='" + caso.getCasoId() + "'>");
				writer.print("<cell>" + caso.getNumeroGeneralCaso() + "</cell>");
				writer.print("<cell>" + DateUtils.formatear(caso.getFechaApertura())
									  + "-"
									  + DateUtils.formatearHora(caso.getFechaApertura()) + "</cell>");
				writer.print("<cell>" + caso.getImputado() + "</cell>");
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
	
	public ActionForward vincularNumeroExpedienteConCaso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String casoId = request.getParameter("casoId");
		String expedienteId = request.getParameter("numeroExpedienteId");
		ExpedienteDTO expediente = new ExpedienteDTO();
		CasoDTO caso = new CasoDTO();
		casoDelegate.vincularExpedienteConCaso(expediente, caso, null);
		return null;
	}	
		
}
