/**
 * Nombre del Programa : ActualizarEstatusSolicitudService.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 21 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato para la actualizacio del estatus de una solicitud
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
package mx.gob.segob.nsjp.service.solicitud;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;

/**
 * Contrato para la actualizacio del estatus de una solicitud
 * 
 * @version 1.0
 * @author Tattva-IT
 * 
 */
public interface ActualizarEstatusSolicitudService {
    /**
     * Actualiza una solicitud al estatus recibido.
     * 
     * @param solicitud
     * @param estatus Estatus a cambiar
     * @throws NSJPNegocioException
     */

    void actualizaEstatusSolicitud(SolicitudDTO solicitud,
            EstatusSolicitud estatus) throws NSJPNegocioException;    
    
    /**
     * Actualiza una solicitud al estatus recibido.
     * 
     * @param folioSolicitud
     * @param estatus Estatus a cambiar
     * @throws NSJPNegocioException
     */
    public void actualizaEstatusSolicitud(String folioSolicitud, EstatusSolicitud estatus) throws NSJPNegocioException;

}
