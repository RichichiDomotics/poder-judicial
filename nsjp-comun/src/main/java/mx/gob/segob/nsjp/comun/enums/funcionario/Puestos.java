/**
 * Nombre del Programa : Puestos.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    
 * Marca de cambio        : N/A
 * Descripcion General    : Enumaración para las actividades
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
 * Enumaración para las actividades.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public enum Puestos {
    COORDINADOR_DE_SERVICIOS_PERICIALES(1585L), 
    /**
     * Equivalente a <b>Coordinador de AMPs</b>.
     */
    FISCAL_GENERAL(1586L), 
    COORDINADOR_DE_DEFENSORES(1587L), 
    ABOGADO_DEFENSOR(2108L), 
    /**
     * @deprecated Usar FISCAL_GENERAL
     */
    COORDINADOR_DE_FISCALES(2109L), 
    /**
     * Equivalente a <b>AMP</b>.
     */
    FISCAL(2110L), 
    JUEZ(2111L), 
    MAGISTRADO(2112L), 
    COORDINADOR_PERICIAL_DE_DEFENSORIA(2113L),
    COMANDANTE_POLICIA_MINISTERIAL(2194L),
    POLICIA_MINISTERIAL(2381L),
    PERITO(2273L),  
    COMANDANTE_SEG_PUBLICA(2587L),
    OFICIAL_SEG_PUBLICA(2588L),
    COORDINADOR_ATENCION_VICTIMAS(2890L);
    
    private Long valorId;
    private final static Map<Long, Puestos> hash = new HashMap<Long, Puestos>();

    static {
        Puestos[] acts = Puestos.values();
        int pos = 0;
        while (pos < acts.length) {
            hash.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }
    }
    private Puestos(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    public static Puestos getByValor(Long valorIdPredefinido) {
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
