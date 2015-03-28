/**
 * Nombre del Programa : ObtenerFolioSolicitudService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio para calcular el numero de folio de la solicitud
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Contrato del servicio para calcular el numero de folio de la solicitud.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface ObtenerFolioSolicitudService {
    /**
     * Calcula el siguiente folio de la solicitud para la institución en la que está instalada la aplicación
     * @return
     * @throws NSJPNegocioException
     */
    String obtenerFolioSolicitud() throws NSJPNegocioException;
}
