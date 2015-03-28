/**
 * Nombre del Programa : TipoExpediente.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 6 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para le tipo de expedientes
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

/**
 * Enumeración para le tipo de expedientes.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum TipoExpediente {

    CAUSA(2093L), TOCA(2094L), CARPETA_DE_EJECUCION(2095L), CARPETA_DE_INVESTIGACION(2384L);

    private Long valorId;

    private final static Map<Long, OrigenExpediente> hash = new HashMap<Long, OrigenExpediente>();

    static {
        OrigenExpediente[] objs = OrigenExpediente.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }

    private TipoExpediente(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }
    public static OrigenExpediente getByValor(Long valorIdPredefinido) {
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
