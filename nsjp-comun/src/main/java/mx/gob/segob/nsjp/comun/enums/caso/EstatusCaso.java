/**
 * Nombre del Programa : EstatusCaso.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Énumeración para los estatus del caso
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
package mx.gob.segob.nsjp.comun.enums.caso;

/**
 * Énumeración para los estatus del caso.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum EstatusCaso {
//    TODO VAP Definir estatus
    CERRADO_TEMPORAL("Cerrado Temporal"), CERRADO_DEFINITIVO(
            "Cerrado Definitivo"), INVESTIGACION("Investigación"), JUDICIALIZADO(
            "Judicializado");

    private String nombre;

    private EstatusCaso(String nom) {
        this.nombre = nom;
    }

    /**
     * Método de acceso al campo nombre.
     * 
     * @return El valor del campo nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    public Short getShort(){
        return Short.valueOf((short) ordinal());
    }
    
    /**
     * Obtiene el nombre de mes.
     * 
     * @param ordinal
     * @return
     */
    public static String getNombre(int ordinal) {
        return EstatusCaso.values()[ordinal].getNombre();
    }

}
