/**
* Nombre del Programa : CancelarTurnoAtencionServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio de cancelacion de turnos de atencion
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
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.expediente.CancelarTurnoAtencionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de cancelacion de turnos de atencion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class CancelarTurnoAtencionServiceImplTest extends BaseTestServicios<CancelarTurnoAtencionService> {

	public void testCancelarTurnoAtencion () {
		TurnoDTO turnoDTO = new TurnoDTO();
		turnoDTO.setTurnoId(91L);
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario(4L);
	
		try {
			service.cancelarTurnoAtencion(turnoDTO, usuarioDTO);
			String resultado = "Paso la prueba";
			assertFalse(resultado.isEmpty());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
}
