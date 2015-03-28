/**
 * Nombre del Programa : EstatusAudiencia.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para el estatus de la audiencia.
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
package mx.gob.segob.nsjp.comun.enums.audiencia;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeración para el estatus de la audiencia.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum EstatusAudiencia {
    SOLICITADA(2017L), PROGRAMADA(2018L), EN_PROCESO(2019L), FINALIZADA(2020L), CANCELADA(3552L), REPROGRAMADA(3553L);

    private Long valorId;
    private final static Map<Long, EstatusAudiencia> hash = new HashMap<Long, EstatusAudiencia>();

    static {
        EstatusAudiencia[] acts = EstatusAudiencia.values();
        int pos = 0;
        while (pos < acts.length) {
            hash.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }
    }
    private EstatusAudiencia(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    public static EstatusAudiencia getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
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
