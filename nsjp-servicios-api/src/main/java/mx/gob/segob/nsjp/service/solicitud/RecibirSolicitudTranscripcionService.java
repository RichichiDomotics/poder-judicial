/**
 * Nombre del Programa : RecibirSolicitudTranscripcionService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 02-sep-2011
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
package mx.gob.segob.nsjp.service.solicitud;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionWSDTO;

/**
 * 
 * @version 1.0
 * @author rgama
 */
public interface RecibirSolicitudTranscripcionService {

    public SolicitudTranscripcionAudienciaDTO recibirSolicitudTranscripcion(
            SolicitudTranscripcionWSDTO solicitud)
            throws NSJPNegocioException;

}
