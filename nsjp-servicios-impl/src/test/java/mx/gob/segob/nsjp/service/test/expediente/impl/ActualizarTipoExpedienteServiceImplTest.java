/**
* Nombre del Programa : ActualizarTipoExpedienteServiceImplTest.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria del Servicio para la actualizacion del tipo de Expediente
* en base al area y al usuario logueado en el sistema
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

import mx.gob.segob.nsjp.comun.enums.expediente.OrigenExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.expediente.ActualizarTipoExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria del Servicio para la actualizacion del tipo de Expediente * 
 * @version 1.0
 * @author rgama
 *
 */
public class ActualizarTipoExpedienteServiceImplTest extends BaseTestServicios<ActualizarTipoExpedienteService> {
	/**
	 * Prueba que permite actualizar el tipo de Expediente a Denuncia
	 */
	public void testActualizarTipoExpediente(){
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(673L);
		
		try {
			service.actualizarTipoExpediente(expedienteDTO,OrigenExpediente.QUERELLA);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
}
