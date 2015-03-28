/**
 * Nombre del Programa : EstatusNotificacion.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enum para los estados de la notificación
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
package mx.gob.segob.nsjp.comun.enums.documento;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum para los estados de la notificación.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum EstatusNotificacion {
    NO_ATENDIDA(2143L), ATENDIDA(2142L), LEIDA(2140L), NO_LEIDA(2141L), EN_PROCESO(2144L);
    private Long valorId;
    private final static Map<Long, EstatusNotificacion> hash = new HashMap<Long, EstatusNotificacion>();

    static {
        EstatusNotificacion[] objs = EstatusNotificacion.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
    public static EstatusNotificacion getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }

    private EstatusNotificacion(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    /**
     * Método de acceso al campo valorId.
     * 
     * @return El valor del campo valorId asociado en le BD.
     */
    public Long getValorId() {
        return valorId;
    }

}
