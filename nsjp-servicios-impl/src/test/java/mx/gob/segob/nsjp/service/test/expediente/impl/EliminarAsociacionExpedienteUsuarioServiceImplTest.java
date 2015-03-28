/**
* Nombre del Programa : EliminarAsociacionExpedienteUsuarioServiceImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01 Ago 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria del Servicio 
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.expediente.EliminarAsociacionExpedienteUsuarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria del Servicio que permite eliminar la asociacion de un Expediente Usuario
 * @version 1.0
 * @author rgama
 *
 */
public class EliminarAsociacionExpedienteUsuarioServiceImplTest extends BaseTestServicios<EliminarAsociacionExpedienteUsuarioService> {
	
	public void testEliminarAsociacionExpedienteUsuario(){
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(1L);
		Long idFuncionario = 5L;
		 
		try {
			service.eliminarAsociacionExpedienteUsuario(expedienteDTO, idFuncionario);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
}
