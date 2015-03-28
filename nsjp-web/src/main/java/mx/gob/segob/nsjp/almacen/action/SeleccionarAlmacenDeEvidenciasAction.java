package mx.gob.segob.nsjp.almacen.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.almacen.AlmacenDelegate;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jacob Lobaco
 */
public class SeleccionarAlmacenDeEvidenciasAction extends GenericAction {

    private static final Logger logger =
            Logger.getLogger(GenericAction.class);

    @Autowired
    private AlmacenDelegate almacenDelegate;

    
    public ActionForward consultarAlmacenesPorTipo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
        	// Se recuperan parametros            
            Long idCaso = NumberUtils.toLong(request.getParameter("idCaso"),0);
            // Declaracion de objetos
            CasoDTO casoDto = null;
            // Consultas de negocio
            List<AlmacenDTO> almacenesFisicosDto = almacenDelegate.
                    consultarAlmacenesPorTipo(null, Boolean.TRUE, casoDto);
            if (idCaso != -1) {
                casoDto = new CasoDTO(idCaso);
                List<AlmacenDTO> almacenesVirtuales =
                        almacenDelegate.consultarAlmacenesPorTipo(null, Boolean.TRUE, casoDto);
                almacenesFisicosDto.addAll(almacenesVirtuales);
            }
                   
            //Inicia la iteracion sobre Almacenes
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
		
			writer.print("<rows>");
			final PaginacionDTO pag = PaginacionThreadHolder.get();
            if (pag != null && pag.getTotalRegistros() != null) {
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
            } else {
                writer.print("<total>0</total>");
                writer.print("<records>0</records>");
            }
			

			
			for (AlmacenDTO almacenDTO : almacenesFisicosDto) {
					DomicilioDTO loDomicilio = almacenDTO.getDomicilio();
					writer.print("<row id='"+almacenDTO.getIdentificadorAlmacen()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+
							(almacenDTO.getEsVirtual() != null ? almacenDTO.getEsVirtual()==true ? "Virtual":"Físico" :"-")
							+" </div>]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+
							(almacenDTO.getNombreAlmacen() != null ? almacenDTO.getNombreAlmacen(): "-")
							+" </div>]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+
							( loDomicilio != null?
									"Calle: " + ( loDomicilio.getCalle() != null ? loDomicilio.getCalle() : "-") +
									" No. Ext: " + ( loDomicilio.getNumeroExterior() != null ? loDomicilio.getNumeroExterior() : "-") +
									" Ciudad: " + ( loDomicilio.getCiudadDTO() != null && loDomicilio.getCiudadDTO().getNombreCiudad() != null? loDomicilio.getCiudadDTO().getNombreCiudad() : "-")									
									:"-")
							
							+" </div>]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+
							(almacenDTO.getDescripcion() != null ? almacenDTO.getDescripcion(): "-")
							+" </div>]]></cell>");
					
					writer.print("</row>");	
				
			}
		writer.print("</rows>");
		writer.flush();
		writer.close();
       
        } catch (NSJPNegocioException ex) {
            java.util.logging.Logger.getLogger(SeleccionarAlmacenDeEvidenciasAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
