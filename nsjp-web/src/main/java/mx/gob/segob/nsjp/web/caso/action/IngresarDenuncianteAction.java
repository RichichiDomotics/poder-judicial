/**
* Nombre del Programa 	: IngresarDenuncianteAction.java                                    
* Autor               	: Jorge Fernandez                                              
* Compania            	: Ultrasist                                                
* Proyecto            	: NSJP              			Fecha:18/03/2011 
* Marca de cambio     	: N/A                                                     
* Descripcion General   : Clase action para ingresar, modificar y buscar el denunciante
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
package mx.gob.segob.nsjp.web.caso.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.BiometricoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase action de ingresar denunciante
 * @version 1.0
 * @author JorgeFO
 */

public class IngresarDenuncianteAction extends GenericAction{
	/** Log de clase */
	private static final Logger log = Logger
			.getLogger(IngresarDenuncianteAction.class);
	@Autowired
	InvolucradoDelegate involucradoDelegate;

	/**
	 * Metodo utilizado para obtener los datos del denunciante
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	public ActionForward buscarInvolucrado(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("Ejecutando Action buscarDenunciante");
			Long idinvol=Long.parseLong(request.getParameter("idInvolucrado"));
			InvolucradoDTO involucrado=new InvolucradoDTO();
			involucrado.setElementoId(idinvol);
			CalidadDTO calidadDTO=new CalidadDTO();
			calidadDTO.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
			involucrado.setCalidadDTO(calidadDTO);
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setExpedienteId((long)1);
			involucrado.setExpedienteDTO(expedienteDTO);
			log.info("Ejecutando Action buscarDenunciante mas denunciante:"+involucradoDelegate);
			InvolucradoDTO involucradoDTO=involucradoDelegate.consultarIndividuo(involucrado);
			
			
			converter.alias("calidadDTO", CalidadDTO.class);
			converter.alias("valorDTO", ValorDTO.class);
			converter.alias("medioDeContactoDTO", MedioDeContactoDTO.class);
			converter.alias("nombreDemograficoDTO", NombreDemograficoDTO.class);
			converter.alias("elementoDTO", ElementoDTO.class);
			converter.alias("personaDTO", PersonaDTO.class);
			converter.alias("involucradoDTO", InvolucradoDTO.class);
			converter.alias("expedienteDTO", ExpedienteDTO.class);
			converter.alias("domicilioDTO", DomicilioDTO.class);
			converter.alias("aliasInvolucradoDTO", AliasInvolucradoDTO.class);
			converter.alias("biometricoDTO", BiometricoDTO.class);
			
			
			
			String xml = converter.toXML(involucradoDTO);
			
			response.setContentType("text/xml");
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();	
			
		}
		catch (Exception e) {		
			log.error(e.getMessage(), e);
		}
		return null;
	}
		
}
