/**
* Nombre del Programa 	: UbicarExpedienteAction.java                                    
* Autor               	: Eduardo Gonzalez                                              
* Compania            	: Ultrasist                                                
* Proyecto            	: NSJP              			Fecha:03/08/2011 
* Marca de cambio     	: N/A                                                     
* Descripcion General   : Clase action que implementa las acciones para el CU Ubicar Expediente
* Programa Dependiente  : N/A                                                      
* Programa Subsecuente 	: N/A                                                      
* Cond. de ejecucion    : struts-config.xml                                                    
* Dias de ejecucion     : N/A                             Horario: N/A
*                               MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                 :N/A                                                           
* Compania              :N/A                                                           
* Proyecto              :N/A                                   Fecha: N/A       
* Modificacion          :N/A                                                           
*------------------------------------------------------------------------------      
*/
package mx.gob.segob.nsjp.web.expdiente.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.delegate.almacen.AlmacenDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *Clase que implementa las acciones para el CU Ubicar Expediente
 * @version 1.0
 * @author EduardoG
 */

public class UbicarExpedienteAction extends GenericAction{
	private static final Logger log  = Logger.getLogger(UbicarExpedienteAction.class);
		
	@Autowired
	ExpedienteDelegate expedienteDelegate;
	@Autowired
	AlmacenDelegate almacenDelegate;
	
	/**
	 * Metodo utilizado para mostrar modal de CU Ubicar Expediente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward ubicarExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {			
			String idExpediente=request.getParameter("idExpediente");
			log.info("ID Expediente ... " + idExpediente);			
		} catch (Exception e) {		
			log.error(e.getCause(),e);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * Metodo utilizado para realizar la consulta de expedientes y obtener el almacen asociado
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward buscarExpedienteAlmacenPorExpedienteId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
			
			String idNumeroExpediente = request.getParameter("idNumeroExpediente");
			if (log.isDebugEnabled()) {
				log.debug("##################llega noExpediente:::::::::" + idNumeroExpediente);
			}
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			expedienteDTO.setNumeroExpedienteId(new Long(idNumeroExpediente));
			ExpedienteDTO resultDTO = expedienteDelegate.obtenerExpediente(expedienteDTO);

			converter.alias("expediente", ExpedienteDTO.class);		
			String xml = converter.toXML(resultDTO);
			log.info("xml ... " + xml);
			response.setContentType("text/xml");
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			
		} catch (Exception e) {		
			log.error(e.getCause(),e);
			
		}
		return null;
	}

	/**
	 * Metodo utilizado para asignar o cambiar un almacen determinado a un expediente seleccionado.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward asociarExpedienteAlmacen(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException{	
		
		String estatus = "success";
		
		try {
			
			String idNumeroExpediente = request.getParameter("idNumeroExpediente");
			String identificadorAlmacen = request.getParameter("identificadorAlmacen");
			
			if (log.isDebugEnabled()) {
				log.debug("Asociando expedienteId ::: " + idNumeroExpediente + " con almacenId ::: " + identificadorAlmacen);
			}
			
			AlmacenDTO almacenDTO = new AlmacenDTO();
			almacenDTO.setIdentificadorAlmacen(new Long(identificadorAlmacen));
			
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			expedienteDTO.setNumeroExpedienteId(new Long(idNumeroExpediente));
			
			AlmacenDTO almacenDTO2 = almacenDelegate.asociarExpedienteAlmacen(almacenDTO, expedienteDTO);	
						
		} catch (Exception e) {
			estatus = "error";
			log.error(e.getCause(),e);			
		} finally{
			converter.alias("estatus", java.lang.String.class);			
			String xml = converter.toXML(estatus);
			log.info(xml);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		}
		
		return null;
	}
	
}
