/**
* Nombre del Programa : MedidaAlternaWSDTO.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dto.medida;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public class MedidaAlternaWSDTO extends MedidaWSDTO {
    /**Años de duración de la sentencia**/
    private Short anios;
    /**Meses de duración de la sentencia**/
    private Short meses;
    /**
     * Método de acceso al campo anios.
     * @return El valor del campo anios
     */
    public Short getAnios() {
        return anios;
    }
    /**
     * Asigna el valor al campo anios.
     * @param anios el valor anios a asignar
     */
    public void setAnios(Short anios) {
        this.anios = anios;
    }
    /**
     * Método de acceso al campo meses.
     * @return El valor del campo meses
     */
    public Short getMeses() {
        return meses;
    }
    /**
     * Asigna el valor al campo meses.
     * @param meses el valor meses a asignar
     */
    public void setMeses(Short meses) {
        this.meses = meses;
    }
}
