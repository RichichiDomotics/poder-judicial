/**
 * Nombre del Programa : SituacionJuridica.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 17 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para agrupar los valores de la situación jurídica del probable responsable
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
package mx.gob.segob.nsjp.comun.enums.involucrado;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeración para agrupar los valores de la situación jurídica del probable
 * responsable.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum SituacionJuridica {

    INVITADO(1699L), INDICIADO(1700L), IMPUTADO(1701L), SENTENCIADO(1702L), INTERNO(
            1703L), LIBRE_CON_RESERVAS_DE_LEY(1704L), LIBRE_DE_PROCESO(1705L), DETENIDO(3703L), INVESTIGADO(3704L), ACUSADO(3705L);

    private Long valorId;

    private final static Map<Long, SituacionJuridica> hash = new HashMap<Long, SituacionJuridica>();
    static {
        SituacionJuridica[] objs = SituacionJuridica.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
    private SituacionJuridica(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }
    /**
     * 
     * @param valorIdPredefinido
     * @return
     */
    public static SituacionJuridica getByValor(Long valorIdPredefinido) {
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
