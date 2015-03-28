/**
* Nombre del Programa : AsignarNumeroExpedienteServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21/10/2011
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
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para los servicios de Asignar numeroExpediente.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class AsignarNumeroExpedienteServiceImplTest extends
	BaseTestServicios<AsignarNumeroExpedienteService> {
	
	public void testAsignarNumeroExpedienteCarpetaEjecucion(){
		
		try {
			Long expedienteId = 69L;
			ExpedienteDTO expDTO =service.asignarNumeroExpedienteCarpetaEjecucion(expedienteId);
			assertNotNull("El objeto no puede ser nulo", expDTO);
			logger.info(" expDTO:"+ expDTO.getExpedienteId());
			logger.info(" expDTO:"+ expDTO.getEstatus());
			logger.info(" expDTO:"+ expDTO.getTipoExpediente());
			logger.info(" expDTO:"+ expDTO.getNumeroExpediente());
			logger.info(" expDTO:"+ expDTO.getNumeroExpedienteId());
			
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(),e);
		}
		
		
	}

}
