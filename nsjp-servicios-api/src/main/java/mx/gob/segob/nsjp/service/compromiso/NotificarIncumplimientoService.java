/**
 * Nombre del Programa : NotificarIncumplimientoService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 Oct 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Servicio para notificar los incumplimientos
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
package mx.gob.segob.nsjp.service.compromiso;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Servicio para notificar los incumplimientos.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface NotificarIncumplimientoService {
    /**
     * Notofica todos lo incumplimientos vencidos para la
     * <code>fechaCompromiso</code>.
     * 
     * @param fechaCompromiso
     * @throws NSJPNegocioException
     */
    void generarNotificacionIncumplimientos(Date fechaCompromiso)
            throws NSJPNegocioException;
}
