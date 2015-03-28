package mx.gob.segob.nsjp.web.evento.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.segob.nsjp.comun.enums.documento.TipoForma;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.solicitud.action.SolicitudCiudadanaDefensorAction;

public class ModuloAdministrativoEjecucionAction extends GenericAction {
	
	private static final Logger log = Logger.getLogger(SolicitudCiudadanaDefensorAction.class);
	
	public ActionForward solicitudesBeneficios(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
//			log.info("Solicitudes no Atendidas");
//			String idR = request.getParameter("idResolutivo");
//			Long idResolutivo = 0L;
//			if(idR != null && !idR.isEmpty()){
//				idResolutivo = Long.parseLong(idR);
//			}else{
//				return null;
//			}
//			log.info("Solicitudes no Atendidas" + idResolutivo);
//			List<FormaDTO> formas = null;
//			FormaDTO formaDTO = new FormaDTO();
//			
//			formas =  formaDelegate.consultarPlantillaPorTipo(TipoForma.RESOLUCION.getValorId());
//			
//			log.info("formas" + formas);
//			
//			response.setContentType("text/xml; charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache");
//			PrintWriter writer = response.getWriter();
//
//			writer.print("<rows>");		
//
//			int lTotalRegistros = formas.size();
//			writer.print("<records>" + lTotalRegistros + "</records>");
//			
//			for(FormaDTO forma : formas){
//				writer.print("<row id='"+forma.getFormaId()+ "'>");
//				
//				writer.print("<cell>"+forma.getNombre()+ "</cell>");
//				
//				writer.print("</row>");
//			}
//			writer.print("</rows>");
//			writer.flush();
//			writer.close();
//			

		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}

}
