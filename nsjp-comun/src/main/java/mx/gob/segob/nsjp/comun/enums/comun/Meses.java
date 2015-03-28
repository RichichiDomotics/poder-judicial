/**
 * Nombre del Programa : Meses.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración de los meses para uasr sus nombres
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
package mx.gob.segob.nsjp.comun.enums.comun;

/**
 * Enumeración de los meses para uasr sus nombres.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum Meses {
    /**
     * Representa el 0 (cero) en el objeto <code>Calendar</code>.
     */
    ENERO,

    FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE;
    /**
     * Obtiene el nombre de mes..
     * 
     * @return El valor del campo nombre
     */
    public String getNombre() {
        return name().toLowerCase();
    }
    /**
     * Obtiene el nombre de mes.
     * 
     * @param ordinal
     * @return
     */
    public static String getNombre(int ordinal) {
        return Meses.values()[ordinal].getNombre();
    }
}
