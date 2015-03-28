/**
 * Nombre del Programa : ObtenerFechaActualDelegate.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 23-jun-2011
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
package mx.gob.segob.nsjp.delegate.fecha;

import java.util.Date;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;


/**
 * Contrato del delegate para los metodos de comunicacion de ObtenerFechaActual
 * entre la vista y los servicios.
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ObtenerFechaActualDelegate {

    /**
     * Operación que realiza la funcionalidad de obtener la fecha Actual del
     * sistema.
     * @return La fecha actual del sistema.
     * @throws NSJPNegocioException En caso de ocurrir algun error.
     */
    Date obtenerFechaActual() throws NSJPNegocioException;

}
