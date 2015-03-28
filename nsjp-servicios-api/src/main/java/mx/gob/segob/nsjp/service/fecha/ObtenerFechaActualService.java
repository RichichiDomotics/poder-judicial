/**
 * Nombre del Programa : ObtenerFechaActualService.java
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
package mx.gob.segob.nsjp.service.fecha;

import java.util.Date;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;


/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ObtenerFechaActualService {

    Date obtenerFechaActual() throws NSJPNegocioException;

}
