/**
* Nombre del Programa : ActualizarCarpetaDeInvestigacionServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 28/07/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.test.expediente.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.expediente.ActualizarCarpetaDeInvestigacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de servicios de pruebas para actualizar la carpeta de investigacion enviada por 
 * procuraduria 
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ActualizarCarpetaDeInvestigacionServiceImplTest  extends
BaseTestServicios<ActualizarCarpetaDeInvestigacionService> {

	public void testConsultarExpedientePorFolioSolicitud(){
		String folioSolicitud = "CD/199900006";
		
		try {
			ExpedienteDTO expedienteDTO = service.consultarExpedientePorFolioSolicitud(folioSolicitud);
			assertNotNull("Debe de recuperarse un Expediente ", expedienteDTO);
			logger.info(" Expediente:" + expedienteDTO);
			logger.info(" Expediente id:" + expedienteDTO.getExpedienteId());
			logger.info(" Expediente NumeroExpediente:" + expedienteDTO.getNumeroExpediente());
			logger.info(" Expediente NumeroExpedienteId:" + expedienteDTO.getNumeroExpedienteId());
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void testActualizarExpedienteDeCarpetaInvestigacionDefensoria(){
		Long expedienteId = 3L;
		
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setExpedienteId(expedienteId);
		
		try {
			expedienteDTO =  service.actualizarExpedienteDeCarpetaInvestigacionDefensoria(expedienteDTO);
			assertNotNull("Debe de recuperarse un Expediente ", expedienteDTO);
			logger.info(" Expediente:" + expedienteDTO);
			logger.info(" Expediente id:" + expedienteDTO.getExpedienteId());
			logger.info(" Expediente NumeroExpediente:" + expedienteDTO.getNumeroExpediente());
			logger.info(" Expediente NumeroExpedienteId:" + expedienteDTO.getNumeroExpedienteId());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void testEliminarDelitos(){
		Long idIndividuo = 1826L;
		try {
			service.eliminarDelitos(idIndividuo);
			logger.info(" Eliminado." );
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
