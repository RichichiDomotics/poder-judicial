/**
* Nombre del Programa : ActualizarIntervininenteServiceImplTest.java
* Autor                            : LuisMG
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17/10/2011
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
package mx.gob.segob.nsjp.service.test.audiencia.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.IntervinienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.audiencia.ActualizarIntervininenteService;

import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author LuisMG
 *
 */
public class ActualizarIntervininenteServiceImplTest extends BaseTestServicios<ActualizarIntervininenteService> {
	public void testActualizarInterviniente() {
		try {
			IntervinienteDTO interviniente = new IntervinienteDTO();
			interviniente.setIntervinienteId(5L);
			//interviniente.setAudiencia(new AudienciaDTO(103L));
			//interviniente.setFuncionario(new FuncionarioDTO(1L));
			
			//Datos a modificar
			interviniente.setEsAceptado(true);
			
			IntervinienteDTO respuesta = service.actualizarInterviniente(interviniente);
			assertTrue("Interviniente modificado ", respuesta != null);
			logger.info("Interviniente: " + respuesta.getIntervinienteId());
			logger.info("-----------------------");

		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}

	}
}
