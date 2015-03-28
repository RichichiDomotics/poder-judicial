/**
 * Nombre del Programa : RecibirNotificacionAudienciaService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 2 Sep 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Servicios para recibir la notificacion.
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
package mx.gob.segob.nsjp.service.audiencia;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO;

/**
 * Servicios para recibir la notificacion.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface RecibirNotificacionAudienciaService {
    /**
     * 
     * @param notificacion
     * @throws NSJPNegocioException
     */
    Boolean recibirNotificacionAudiencia(SolicitudAudienciaBasicoWSDTO notificacion)
            throws NSJPNegocioException;
}
