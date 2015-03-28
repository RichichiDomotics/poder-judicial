/**
* Nombre del Programa : GenerarTurnoServiceImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 4 May 2011
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

import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.service.expediente.GenerarTurnoAtencionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesar
 *
 */
public class GenerarTurnoServiceImplTest extends BaseTestServicios<GenerarTurnoAtencionService> {
	
	public void testGenerarTurno (){
		TurnoDTO turnoDTO = new TurnoDTO();
		TurnoDTO turnoResDTO = new TurnoDTO();
		
		turnoDTO.setTipoTurno(TipoTurno.PENAL);
		turnoDTO.setEsUrgente(true);
		
		try {
			turnoResDTO = service.generarTurnoAtencion(turnoDTO);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
		assertTrue("El numero de atencion no puede ser nulo ", turnoResDTO.getNumeroTurno()!=null);
		logger.info("Numero atencion " + turnoResDTO.getNumeroTurno());
	}
}
