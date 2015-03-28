/**
* Nombre del Programa : CancelarTurnoAtencionService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar la cancelacion de un turno de atencion
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
package mx.gob.segob.nsjp.service.expediente;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Contrato del servicio para realizar la cancelacion de un turno de atencion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface CancelarTurnoAtencionService {

	/**
	 * Metodo para realizar la cancelacion de un turno de atencion
	 * @param turnoDTO
	 * @throws NSJPNegocioException
	 */
	public void cancelarTurnoAtencion (TurnoDTO turnoDTO, UsuarioDTO usuarioDTO) throws NSJPNegocioException;
}
