/**
* Nombre del Programa : MandamientoWSDTO.java
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
package mx.gob.segob.nsjp.dto.documento;


/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public class MandamientoWSDTO extends DocumentoWSDTO {
    private Long idTipoMandamiento;
    private Long idEstatus;
    /**
     * Método de acceso al campo idTipoMandamiento.
     * @return El valor del campo idTipoMandamiento
     */
    public Long getIdTipoMandamiento() {
        return idTipoMandamiento;
    }
    /**
     * Asigna el valor al campo idTipoMandamiento.
     * @param idTipoMandamiento el valor idTipoMandamiento a asignar
     */
    public void setIdTipoMandamiento(Long idTipoMandamiento) {
        this.idTipoMandamiento = idTipoMandamiento;
    }
    /**
     * Método de acceso al campo idEstatus.
     * @return El valor del campo idEstatus
     */
    public Long getIdEstatus() {
        return idEstatus;
    }
    /**
     * Asigna el valor al campo idEstatus.
     * @param idEstatus el valor idEstatus a asignar
     */
    public void setIdEstatus(Long idEstatus) {
        this.idEstatus = idEstatus;
    }
}
