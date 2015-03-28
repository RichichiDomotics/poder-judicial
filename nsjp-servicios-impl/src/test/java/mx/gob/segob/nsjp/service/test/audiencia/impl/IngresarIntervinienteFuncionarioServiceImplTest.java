/**
 * Nombre del Programa : IngresarIntervinienteFuncionarioServiceImplTest.java
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.IntervinienteDTO;
import mx.gob.segob.nsjp.service.audiencia.ActualizarIntervininenteService;
import mx.gob.segob.nsjp.service.audiencia.IngresarIntervinienteFuncionarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author LuisMG
 * 
 */
public class IngresarIntervinienteFuncionarioServiceImplTest extends
		BaseTestServicios<IngresarIntervinienteFuncionarioService> {
	public void testIngresarIntervinienteFuncionario() {
		try {
			IntervinienteDTO respuesta = service
					.ingresarIntervinienteFuncionario(103L, 1L);

			assertTrue("Interviniente creado ", respuesta != null);
			logger.info("Interviniente: " + respuesta.getIntervinienteId());
			
			logger.info("-----------------------");

		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}

	}
}
