/**
 * Nombre del Programa : ActualizarEstatusNotificacionService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 03-ago-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.notificacion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ActualizarEstatusNotificacionService {

    /**
     * 
     * @param notificacionDto
     * @param nuevoEstado
     * @throws NSJPNegocioException
     */
    void actualizarEstatusNotificacion(NotificacionDTO notificacionDto,
            ValorDTO nuevoEstado) throws NSJPNegocioException;

}
