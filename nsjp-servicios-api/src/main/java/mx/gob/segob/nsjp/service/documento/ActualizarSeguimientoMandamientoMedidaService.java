/**
 * Nombre del Programa : ActualizarSeguimientoMandamientoMedidaService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 1 Sep 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Interfaz para registrar el seguimiento
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
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.SeguimientoMandamientoMedidaWSDTO;

/**
 * Interfaz para registrar el seguimiento.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface ActualizarSeguimientoMandamientoMedidaService {
    /**
     * Actualiza el seguimiento de una medida o de un mandamiento dependiendeo
     * de <code>tipoOperacion</code>.
     * 
     * @param input
     */
    public void actualizarSeguimiento(SeguimientoMandamientoMedidaWSDTO input)
            throws NSJPNegocioException;
}
