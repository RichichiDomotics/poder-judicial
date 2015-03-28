/**
 * Nombre del Programa : Estatus.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para las etapas del expediente
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
 * Enumeración para las atapas del expediente.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum EtapasExpediente {
    INTEGRACION(2048L), 
    TECNICA(2049L), 
    CONCILIACION_Y_MEDIACION(2050L), 
    EJECUCION(2051L), 
    ASESORIA(2533L);

    private final static Map<Long, EtapasExpediente> hash = new HashMap<Long, EtapasExpediente>();

    static {
    	EtapasExpediente[] objs = EtapasExpediente.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }

    
    private Long valorId;
    private EtapasExpediente(Long valorIdPredefinido) {
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
    public static EtapasExpediente getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }
}
