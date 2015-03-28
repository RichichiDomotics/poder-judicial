/**
 * Nombre del Programa : TipoJerarquia.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 23 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para el tipo de jerarquias
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
package mx.gob.segob.nsjp.comun.enums.institucion;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeración para el tipo de jerarquias.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum TipoJerarquia {
    INSTITUCION(2012L), DISTRITO(2013L), AREA(2014L), DEPARTAMENTO(2015L), AGENCIA(
            2016L);

    private Long valorId;

    private final static Map<Long, TipoJerarquia> hash = new HashMap<Long, TipoJerarquia>();

    static {
        TipoJerarquia[] objs = TipoJerarquia.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
    /**
     * 
     * @param valorIdPredefinidod
     */
    private TipoJerarquia(Long valorIdPredefinidod) {
        this.valorId = valorIdPredefinidod;
    }

    /**
     * 
     * @param valorIdPredefinido
     * @return
     */
    public static TipoJerarquia getByValor(Long valorIdPredefinido) {
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
