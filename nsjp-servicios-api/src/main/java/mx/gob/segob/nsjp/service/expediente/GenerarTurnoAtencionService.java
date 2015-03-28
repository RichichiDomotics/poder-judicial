/**
 * Nombre del Programa : GenerarTurnoAtencionService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 26 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Generar el turno para orientar a la víctima y darle la atención adecuada.

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

/**
 * Generar el turno para orientar a la víctima y darle la atención adecuada.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface GenerarTurnoAtencionService {
    /**
     * Genera un turno de atención en base a los siguientes valors
     * <ul>
     * <li>tipoTurno</li>
     * <li>esUrgente</li>
     * </ul>
     * 
     * @param defTurno
     * @return TurnoDTO.<b>numeroTurno</b>
     * @throws NSJPNegocioException
     */
    TurnoDTO generarTurnoAtencion(TurnoDTO defTurno)
            throws NSJPNegocioException;
}
