/**
 * Nombre del Programa : ConsultarSolicitudesDefensoriaServiceImplTestTest.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 16 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Prueba unitaria de ConsultarSolicitudesDefensoriaServiceImplTest
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.test.solicitud.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.service.solicitud.ConsultarSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.ConsultarSolicitudesDefensoriaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria de ConsultarSolicitudesDefensoriaServiceImplTest.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
@SuppressWarnings("deprecation")
public class ConsultarSolicitudesDefensoriaServiceImplTest
        extends
            BaseTestServicios<ConsultarSolicitudesDefensoriaService> {
    
    public void testObtenerSolicitudesDefensoria() {
    	List<SolicitudDefensorDTO> sol = null;
        try {
        	
        	sol = service.obtenerSolicitudesDefensoriaPorEstatus(EstatusSolicitud.ABIERTA,Instituciones.DEF);
        	
        	
        	for (SolicitudDefensorDTO respDTO : sol) {
            //  if(respDTO.getExpedienteDTO().getExpedienteId() == 562){
            		
            	
        		  System.out.println("---------SOLICITUD " + respDTO.getDocumentoId() +"---------------------------------------");

        		 if(respDTO.getExpedienteDTO() != null && respDTO.getExpedienteDTO().getCasoDTO() != null && respDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso() != null){
        	    	System.out.println("Id del expediente  \t: "+respDTO.getExpedienteDTO().getExpedienteId());
        	    	System.out.println("Número de caso  \t: "+respDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso());
        	    	 System.out.println("Número de causa \t: "+respDTO.getExpedienteDTO().getNumeroExpediente());
        	    	 System.out.println("Fecha y hora de vencimiento para atención \t: "+respDTO.getFechaLimite());        	    	 
        	    }
        		 
        		 //Muestra la informacion sobre el imputado
    			 for (InvolucradoDTO elementoDTO : respDTO.getExpedienteDTO().getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA)) {
        			  System.out.println("            * Nombre del imputado \t: "+ elementoDTO.getNombreCompleto() + "  ID " + elementoDTO.getElementoId());
        			  if(elementoDTO.getEsDetenido()!= null && elementoDTO.getEsDetenido()){
            			  System.out.println("            * ¿El imputado esta detenido? "+ elementoDTO.getEsDetenido() );
            			  //Delitos del imputado            			  
            			  if(elementoDTO.getDelitosCometidos() !=  null){
            				  System.out.println("            * Delitos propios del imputado: ");
//	            			  for (DelitoDTO delitoDTO : elementoDTO.getDelitosCometidos()) {
////		            			  System.out.print("            * "+ delitoDTO.getNombreDelito() + "\n");
//	            			  }            			  
            			  }
            			  
        				  for (DetencionDTO detencionDTO : elementoDTO.getDetenciones()) {
	            				  System.out.println("     * Lugar donde se encuentra detenido: "+ detencionDTO.getLugarDetencionProvicional());            				  
	            				  System.out.println("     * Fecha y hora de detencion: "+ detencionDTO.getstrfechaFinDetencion());        					  
						}
        			  }        			  
    			  }
    			 
    			 //Información asociada a la Audiencia de la solicitud que viene de PoderJudicial (PJ)
    			 AudienciaDTO loAud = respDTO.getAudiencia();
    		    if(loAud != null){    					 
    				 System.out.println("Fecha Audiencia: " + DateUtils.formatear(loAud.getFechaEvento()));
    				 System.out.println("Hora Audiencia: " +  DateUtils.formatearHora(loAud.getFechaEvento()));
    				 System.out.println("Tipo audiencia: " + loAud.getTipoAudiencia().getValor());    				 
    				 System.out.println("Sala: " + loAud.getSala().getNombreSala());
    				 System.out.println("Ubicacion Sala: " + loAud.getSala().getUbicacionSala());    				 
    				 System.out.println("Domicilio Sala: " + loAud.getSala().getDomicilioSala());
       			 } 
    			  System.out.println("---------------------------------------------------");
              }
			//}          
            
            assertTrue("La lista debe de ser mayor a cero", sol.size() >0);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }
    
    
    
    public void testConsultarSolicitudesDefensoriaByHistoricoYEstatus () {
    	try {
			List<SolicitudDefensorDTO> respuesta = service.consultarSolicitudesDefensoriaByHistoricoYEstatus(EstatusSolicitud.CERRADA);
			assertTrue("La lista debe de ser mayor a cero", respuesta.size()>0);
			logger.info("La lista debe de ser mayor a cero"+respuesta.size());
			
			for (SolicitudDefensorDTO solicitudDefensorDTO : respuesta) {
				logger.info("----------------------");
				logger.info("Solicitu ID :: "+solicitudDefensorDTO.getDocumentoId());
				logger.info("Origen docto :: "+solicitudDefensorDTO.getOrigenDocumento());
				if (solicitudDefensorDTO.getExpedienteDTO()!=null) {
					logger.info("Num expediente :: "+solicitudDefensorDTO.getExpedienteDTO().getNumeroExpediente());
					if (solicitudDefensorDTO.getExpedienteDTO().getCasoDTO()!=null) {
						logger.info("Num caso :: "+solicitudDefensorDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso());
					}
				}
				if (solicitudDefensorDTO.getDestinatario()!=null) {
					logger.info("Defensor :: "+solicitudDefensorDTO.getDestinatario().getNombreCompleto());
				}
				if (solicitudDefensorDTO.getInstitucion()!=null)
					logger.info("Defensor :: "+solicitudDefensorDTO.getInstitucion().getNombreInst());
				
				logger.info("----------------------");
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
    }

}
