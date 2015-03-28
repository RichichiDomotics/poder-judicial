/**
 * Nombre del Programa : EstatusTurno.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para los estados del Turno
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
package mx.gob.segob.nsjp.comun.enums.expediente;

import java.util.HashMap;
import java.util.Map;

import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;

/**
 * Enumeración para los estados del Turno.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum EstatusTurno {
    ESPERA(2040L), ASIGNADO(2041L), ATENDIDO(2042L), CANCELADO(2043L);

    private final static Map<Long, EstatusTurno> hash = new HashMap<Long, EstatusTurno>();

    static {
        EstatusTurno[] objs = EstatusTurno.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }

    
    private Long valorId;
    private EstatusTurno(Long valorIdPredefinido) {
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
    public static EstatusTurno getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }
}
