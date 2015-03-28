package mx.gob.segob.nsjp.web.expdiente.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.defensoria.ArancelDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.dto.defensoria.ArancelDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.defensor.action.AdministrarActuacionDefensoriaAction;
import mx.gob.segob.nsjp.web.expediente.form.ArancelExpedienteForm;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class ArancelExpedienteAction extends GenericAction {
	
	private static final Logger log = Logger.getLogger(AdministrarActuacionDefensoriaAction.class);
	@Autowired
	private ArancelDelegate arancelDelegate;
	
	public ActionForward consultarArancelesExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			log.info("Inicia Action actuacion consulta por etapa y usuario");
			Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"), 0L);
			UsuarioDTO usuarioDTO = super.getUsuarioFirmado(request);
			ExpedienteDTO expediente = new ExpedienteDTO();
			expediente.setUsuario(usuarioDTO);
			expediente.setNumeroExpedienteId(numeroExpedienteId);
			List<ArancelDTO> aranceles = new LinkedList<ArancelDTO>();
			aranceles = arancelDelegate.consultarArancelesXNumeroExpediente(expediente, null);
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");		
			writer.print("<records>" + aranceles.size() + "</records>");
			for (ArancelDTO arancel : aranceles) {
				writer.print("<row id='"+arancel.getArancel_id()+"'>");
				writer.print("<cell>"+DateUtils.formatear(arancel.getFechaRegistro())+"</cell>");
				writer.print("<cell>"+arancel.getFuncionarioDTO().getNombreCompleto()+"</cell>");
				writer.print("<cell>");
				writer.print(DateUtils.formatear(arancel.getFechaInicio()));
				writer.print("-");
				writer.print(DateUtils.formatear(arancel.getFechaFin()));
				writer.print("</cell>");
				writer.print("<cell>"+arancel.getClase_valDTO().getValor()+"</cell>");
				writer.print("<cell>"+arancel.getHoras()+"</cell>");
				writer.print("<cell>"+arancel.getMonto()+"</cell>");
				if(arancel.getFechaPago() != null){
					writer.print("<cell>"+arancel.getFechaPago()+"</cell>");
				}else{
					writer.print("<cell>sin pago</cell>");
				}
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

	public ActionForward registrarArancelExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			log.info("Inicia Action registrar arancel");
			ArancelExpedienteForm forma = (ArancelExpedienteForm)form;
			ArancelDTO arancel = new ArancelDTO();
			BeanUtils.copyProperties(arancel, forma);
			arancel.setFechaInicio(DateUtils.obtener(forma.getFechaInicial()));
			arancel.setFechaFin(DateUtils.obtener(forma.getFechaFinal()));
			String mensaje = "";
			mensaje += "\nArancel.FechaInicio :: "+arancel.getFechaInicio();
			mensaje += "\nArancel.FechaFinal  :: "+arancel.getFechaFin();
			mensaje += "\nArancel.Horas       :: "+arancel.getHoras();
			mensaje += "\nArancel.Monto       :: "+arancel.getMonto();
			mensaje += "\nArancel.Nivel_ID    :: "+arancel.getClase_valDTO().getIdCampo();
			mensaje += "\nArancel.NumeroExpID :: "+arancel.getExpedienteDTO().getNumeroExpedienteId();
			mensaje += "\nArancel.ClaveFunc   :: "+arancel.getFuncionarioDTO().getClaveFuncionario();
			log.info(mensaje);
			arancelDelegate.guardarArancel(arancel);
			
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public ActionForward pagarArancelExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			log.info("Inicia Action registrar arancel");
			ArancelExpedienteForm forma = (ArancelExpedienteForm)form;
			ArancelDTO arancel = new ArancelDTO();
			BeanUtils.copyProperties(arancel, forma);
			arancel.setFechaPago(Calendar.getInstance().getTime());
			arancelDelegate.pagarArancel(arancel);
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}

}
