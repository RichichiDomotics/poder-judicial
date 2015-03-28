/**
 * Nombre del Programa : ThreadHolder.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 Aug 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Holder para hacer el manejo de los datos de la paginación por cada hilo de ejecución
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
package mx.gob.segob.nsjp.comun.util.tl;

import java.util.HashMap;
import java.util.Map;

import mx.gob.segob.nsjp.dto.base.PaginacionDTO;

/**
 * Holder para hacer el manejo de los datos de la paginación por cada hilo de
 * ejecución.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class PaginacionThreadHolder {
    /**
     * Mapa para asociar al hilo de ejecución los datos de la paginación.
     */
    private static Map<Integer, PaginacionDTO> hilos = new HashMap<Integer, PaginacionDTO>();
    /**
     * Obtiene el DTO con los datos de paginación del el hilo actual de
     * ejecución actual.<br>
     * El método ha sido pensado para lo de paginación, si por algún tema de
     * seguridad o bitacore de operaciones se deberá invocar nuevamente el
     * método <code>set()</code> para que siga disponible para la paginación.
     * 
     * @return
     */
    public static PaginacionDTO get() {
        Integer key = new Integer(UniqueThreadIdGenerator.getCurrentThreadId());
        return hilos.remove(key);
    }
    /**
     * Asocia los datos de la paginación al hilo actual de ejecución.
     * 
     * @param fromFilter
     */
    public static void set(PaginacionDTO fromFilter) {
        Integer key = new Integer(UniqueThreadIdGenerator.getCurrentThreadId());
        hilos.put(key, fromFilter);
    }
}
