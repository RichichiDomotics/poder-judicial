/**
 * Nombre del Programa : Notificaciones.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 1 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para los tipos de notificaciones
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
package mx.gob.segob.nsjp.comun.enums.documento;

/**
 * Enumeración para los tipos de notificaciones.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum Notificaciones {
    FISICA("Física"), ELECTRONICA("Electrónica");

    private String nombre;

    private Notificaciones(String nom) {
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
}
