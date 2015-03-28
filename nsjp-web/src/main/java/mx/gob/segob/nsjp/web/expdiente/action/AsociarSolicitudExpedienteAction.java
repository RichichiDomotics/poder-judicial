/**
* Nombre del Programa 	: AsociarSolicitudExpedienteAction.java                                    
* Autor               	: Alejandro Galaviz Alvarez                                              
* Compania            	: Ultrasist                                                
* Proyecto            	: NSJP              			Fecha:26/07/2011 
* Marca de cambio     	: N/A                                                     
* Descripcion General   : Clase action para asociar una solicitud a un numero de expediente
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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Clase action para asignar un numero de expediente a un numero de solicitud
 * @version 1.0
 * @author AlejandroGA
 */
public class AsociarSolicitudExpedienteAction extends GenericAction {
	
	/**Log de clase*/
	private static final Logger log  = Logger.getLogger(AsociarSolicitudExpedienteAction.class);
	@Autowired
	SolicitudDelegate solicitudDelegate;
	@Autowired
	ExpedienteDelegate expedienteDelegate; 
	/**
	 * Metodo utilizado validar la relacion del numero de solicitud con un numero de expediente
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward validarRelacionSolicitudExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			
			String respuesta = "";
		try {
			log.info("EJECUTANDO ACTION VALIDAR RELACION SOLICITUD-EXPEDIENTE::::::::::___________:::::::::::::::::::______________::::::::::::::::.");
			String solicitudId = request.getParameter("idEvento");
			String []ids = solicitudId.split(",");
			//La primera parte del id corresponde al id de la audiencia, y la segunda al id de la solicitud
			log.info("ID-DE LA SOLICITUD"+ ids[1]);
			
			//consultar el numero de expediente asociado a la solicitud y si tiene mas de uno
			Long expedienteId = solicitudDelegate.consultarNumeroExpedienteDeSolicitud(NumberUtils.toLong(ids[1]));
			
			if( expedienteId != null){				
				respuesta="succes";
				request.setAttribute("idEvento",ids[0]);	
			}
			else{
				respuesta="alterno";
				request.setAttribute("idEvento",ids[1]);	
			}
			
					
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return mapping.findForward(respuesta);
	}	
	
	
	/**
	 * Método para guardar la relación seleccionada entre el expediente y la solicitud
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward asociarSolicitudAExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
	
		Long solicitudId = NumberUtils.toLong(request.getParameter("solicitudId"));
		Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"));
		
		if(solicitudId != null && numeroExpedienteId != null){
			try {
				expedienteDelegate.asignarNumeroExpedienteASolicitud(numeroExpedienteId, solicitudId);
				
			} catch (NSJPNegocioException e) {
				log.error(e);
			}
		}
		
		
		return null;
	}
	
	
	/**
	 * Metodo utilizado para consultar los expedientes asociados a una solicitud, para posteriormente
	 * asociarle un expediente, esto se realiza por que tiene más de un expediente asociado y necesita 
	 * informacion del usuario para asociarle el correcto
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarExpedientesPorSolicitudId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			
			String respuesta = "";
		try {
			log.info("EJECUTANDO ACTION CONSUKTAR EXPEDIENTES POR ID DE SOLICITUD::::::::::___________:::::::::::::::::::______________::::::::::::::::.");
			String solicitudId = request.getParameter("solicitudId");
			log.info("ID-DE LA SOLICITUD"+ solicitudId);
			
			//consultar el numero de expediente asociado a la solicitud y si tiene mas de uno
			List<ExpedienteDTO> listaExpedientes = expedienteDelegate.consultarNumeroExpedientePorCasoDeSolicitud(NumberUtils.toLong(solicitudId.split(",")[1]));
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
		
			writer.print("<rows>");
		
			int lTotalRegistros = listaExpedientes.size();
		
			writer.print("<records>" + lTotalRegistros + "</records>");
			for (ExpedienteDTO expediente : listaExpedientes) {
			  		    
						writer.print("<row id='"+ expediente.getNumeroExpedienteId()+ "'>");
							//Numero de expediente
							writer.print("<cell>"+ expediente.getNumeroExpediente() + "</cell>");
							//Fecha de apertura
							writer.print("<cell>"+(expediente.getFechaApertura() != null ? DateUtils.formatear(expediente.getFechaApertura()) : "" )+ "</cell>");
						writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
				
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return mapping.findForward(respuesta);
	}
}
