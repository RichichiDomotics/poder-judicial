/**
 * Nombre del Programa : TipoOficioEstructurado
 * Autor                            : rrodriguez
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 05 Aug 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para el tipo oficio estructurado
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
 * Enumeración para el tipo oficio estructurado.
 * 
 * @version 1.0
 * @author rrodriguez
 * 
 */
public enum TipoOficioEstructurado {
    TEORIA_DEL_CASO(2382L),
    PLIEGO_DE_CONSIGNACION(256L);

    private final static Map<Long, TipoOficioEstructurado> hash = new HashMap<Long, TipoOficioEstructurado>();

    static {
        TipoOficioEstructurado[] acts = TipoOficioEstructurado.values();
        int pos = 0;
        while (pos < acts.length) {
            hash.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }
    }

    private Long valorId;
    private TipoOficioEstructurado(Long valorIdPredefinido) {
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
    public static TipoOficioEstructurado getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }
}
