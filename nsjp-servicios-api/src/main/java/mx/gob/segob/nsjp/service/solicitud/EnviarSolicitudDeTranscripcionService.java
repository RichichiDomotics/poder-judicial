/**
 * Nombre del Programa : EnviarSolicitudDeTranscripcionService.java
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

/**
 * 
 * @version 1.0
 * @author rgama
 */
public interface EnviarSolicitudDeTranscripcionService {

    /**
     * 
     * @param solicitudTranscripcionDto
     * @throws NSJPNegocioException
     */
    public SolicitudTranscripcionAudienciaDTO enviarSolicitudDeTranscripcion(SolicitudTranscripcionAudienciaDTO
            solicitudTranscripcionDto) throws NSJPNegocioException;

}
