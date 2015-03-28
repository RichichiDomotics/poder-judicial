/**
 * Nombre del Programa : TipoEspecialidad.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración de las especialidades
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
package mx.gob.segob.nsjp.comun.enums.funcionario;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeración de las especialidades.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum TipoEspecialidad {
    PERICIAL(1727L), MINISTERIO_PUBLICO(1728L), JUEZ(1729L), DEFENSOR(1730L), POLICIA(
            1L),
    /**
     * Usado para el catalogo de seguimiento en medidas cautelares.
     */
    SEGUIMIENTO(2107L);

    private Long valorId;
    private final static Map<Long, TipoEspecialidad> hash = new HashMap<Long, TipoEspecialidad>();

    static {
        TipoEspecialidad[] acts = TipoEspecialidad.values();
        int pos = 0;
        while (pos < acts.length) {
            hash.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }
    }
    
    private TipoEspecialidad(Long valorIdPredefinido) {
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
    
    public static TipoEspecialidad getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }
}
