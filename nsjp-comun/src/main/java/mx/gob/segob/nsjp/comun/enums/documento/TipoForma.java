/**
 * Nombre del Programa : Forma.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para el tipo forma
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
 * Enumeración para el tipo forma.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum TipoForma {
    PERICIAL(1634L), RESOLUCION(1614l);

    private final static Map<Long, TipoForma> hash = new HashMap<Long, TipoForma>();

    static {
        TipoForma[] acts = TipoForma.values();
        int pos = 0;
        while (pos < acts.length) {
            hash.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }
    }

    private Long valorId;
    private TipoForma(Long valorIdPredefinido) {
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
    /**
     * 
     * @param valorIdPredefinido
     * @return
     */
    public static TipoForma getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }
}
