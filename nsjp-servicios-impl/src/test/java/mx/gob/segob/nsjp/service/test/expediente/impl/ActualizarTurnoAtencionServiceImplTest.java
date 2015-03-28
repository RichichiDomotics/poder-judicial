/**
* Nombre del Programa : ActaulizarTurnoAtencionServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17/11/2011
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

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusTurno;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.service.expediente.ActualizarTurnoAtencionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria del servicio para actualizar los turnos atendidos.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ActualizarTurnoAtencionServiceImplTest extends BaseTestServicios<ActualizarTurnoAtencionService> {

	public void testActualizarEstatusTurno(){
		
		TurnoDTO turnoModificadoDTO = new TurnoDTO(55L);
		turnoModificadoDTO.setEstado(EstatusTurno.ESPERA);
		turnoModificadoDTO.setExpediente(new ExpedienteDTO(10L));
		
		try {
			TurnoDTO turnoDTO = service.actualizarTurno(turnoModificadoDTO);
			assertNotNull("El valor no puede ser nulo", turnoDTO);
			logger.info(" Turno:"+ turnoDTO.getTurnoId());
			logger.info(" Turno:"+ turnoDTO.getEsUrgente());
			logger.info(" Turno:"+ turnoDTO.getEstado());
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
		
	}
}
