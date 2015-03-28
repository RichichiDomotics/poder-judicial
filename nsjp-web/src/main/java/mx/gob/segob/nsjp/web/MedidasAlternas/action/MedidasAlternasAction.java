package mx.gob.segob.nsjp.web.MedidasAlternas.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.agenda.AgendaDelegate;
import mx.gob.segob.nsjp.delegate.medidasalternas.MedidasAlternasDelegate;
import mx.gob.segob.nsjp.delegate.medidascautelares.MedidasCautelaresDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class MedidasAlternasAction extends GenericAction{
	
	/** Log de clase */
	private static final Logger log = Logger.getLogger(MedidasAlternasAction.class);
	
	@Autowired
	private MedidasAlternasDelegate medidasAlternasDelegate;
	
	@Autowired
	private MedidasCautelaresDelegate medidasCautelaresDelegate;
	
	@Autowired
	AgendaDelegate agendaDelegate;
	
	/**
	 * Método para obtener las Medidas Alternas
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultaGridMedidasAlternas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action de Consultar Grid Medidas Alternas :::::");
		Long estatus = parseLong(request.getParameter("estatus"));
		log.debug("estatus (request) ... " + estatus);

		List<MedidaAlternaDTO> medidasAlternasDTOs = new ArrayList<MedidaAlternaDTO>();
		
		try {
			if(estatus.equals(EstatusMedida.NO_ATENDIDA.getValorId())){
				log.debug("NO_ATENDIDA ... " + EstatusMedida.NO_ATENDIDA.getValorId());
				medidasAlternasDTOs = medidasAlternasDelegate.consultarMedidaAlternaPorEstatus(EstatusMedida.NO_ATENDIDA);
			}else if (estatus.equals(EstatusMedida.EN_PROCESO.getValorId())){
				log.debug("EN_PROCESO ... " + EstatusMedida.EN_PROCESO.getValorId());
				medidasAlternasDTOs = medidasAlternasDelegate.consultarMedidaAlternaPorEstatus(EstatusMedida.EN_PROCESO);
			}else{
				log.debug("ATENDIDA ... " + EstatusMedida.ATENDIDA.getValorId());
				medidasAlternasDTOs = medidasAlternasDelegate.consultarMedidaAlternaPorEstatus(EstatusMedida.ATENDIDA);
			}
			log.info("Consultar Grid Medidas Alternas ::medidasAlternasDTOs.SIZE:::"+medidasAlternasDTOs.size());
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");

			final PaginacionDTO pag = PaginacionThreadHolder.get();
			log.debug("pag :: " + pag);
            if (pag != null && pag.getTotalRegistros() != null) {
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
            } else {
                writer.print("<total>0</total>");
                writer.print("<records>0</records>");
            }
            
			for (MedidaAlternaDTO medidaAlternaDTO : medidasAlternasDTOs) {
				log.info("/******** ::::: /" + medidaAlternaDTO);
				writer.print("<row id='" + medidaAlternaDTO.getDocumentoId() +"'>");
				writer.print("<cell>---</cell>");
				writer.print("<cell>"+medidaAlternaDTO.getInvolucrado().getNombreCompleto()+"</cell>");
				if(medidaAlternaDTO.getExpedienteDTO() != null){
					writer.print("<cell>"+ medidaAlternaDTO.getExpedienteDTO().getNumeroExpediente() + "</cell>");					
				}else{
					writer.print("<cell> --- </cell>");					
				}
				writer.print("<cell>"+ medidaAlternaDTO.getNumeroCaso() + "</cell>");					
				writer.print("<cell>"+ medidaAlternaDTO.getNumeroCausa() + "</cell>");	
				writer.print("<cell>"+ medidaAlternaDTO.getNumeroCarpetaEjecucion() + "</cell>");
				writer.print("</row>");
			} 
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (NSJPNegocioException e) {
			log.error(e);
		}
		
		return null;		
	}

	/**
	 * Método para cambiar el estatus de las Medidas Alternas
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public void cambiarEstatusMedida(Long id, Long estatus)
			throws IOException {
		
		log.info("Método para cambiar estatus de Medida Alterna:::::");

		try {
			medidasCautelaresDelegate.cambiarEstatusMedida(id, estatus);			
		} catch (NSJPNegocioException e) {
			log.error(e);
		}
		
	}

	/**
	 * Método para consultar una Medida Alterna por Id
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarMedidaAlternaPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action de consultar Medida Alterna por id:::::");

		Long medidaAlternaId = new Long(request.getParameter("medidaAlternaId"));
		Long estatusId = new Long(request.getParameter("estatusId"));
		
		log.debug("medidaAlternaId... " + medidaAlternaId);
		log.debug("estatusId ... " + estatusId);
		
		try {
			
			MedidaAlternaDTO dto = new MedidaAlternaDTO();
			dto.setDocumentoId(medidaAlternaId);
			
			MedidaAlternaDTO medidaAlternaDTO = new MedidaAlternaDTO();
			medidaAlternaDTO = medidasAlternasDelegate.consultarMedidasAlternasPorId(dto);
			
			converter.alias("MedidaAlternaDTO", MedidaAlternaDTO.class);
			String xml = converter.toXML(medidaAlternaDTO);
			log.debug("xml consultarMedidaAlternaPorId() ... " + xml);
			escribir(response, xml,null);
			
		} catch (NSJPNegocioException e) {
			log.error(e);
		}
		
		return null;
	}
	
}
