/**
 * Nombre del Programa : Instituciones.java
 * Autor                            : gama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 29 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeraci�n para el tipo de jerarquias
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
 * Enumeraci�n para las Instituciones
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public enum Instituciones {
    PGJ(1L),//Procuraduria General de Justicia
    DEF(2L), //Defensoria
    PJ(3L),//Poder Judicial
    SSP(4L),//Secretaria de Seguridad Publica
    RS(5L);//Reinsercion Social
    private Long valorId;

    private final static Map<Long, Instituciones> hash = new HashMap<Long, Instituciones>();

    static {
        Instituciones[] objs = Instituciones.values();
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
    private Instituciones(Long valorIdPredefinidod) {
        this.valorId = valorIdPredefinidod;
    }

    /**
     * 
     * @param valorIdPredefinido
     * @return
     */
    public static Instituciones getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }

    /**
     * M�todo de acceso al campo valorId.
     * 
     * @return El valor del campo valorId asociado en le BD.
     */
    public Long getValorId() {
        return valorId;
    }

}
