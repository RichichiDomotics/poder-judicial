/**
* Nombre del Programa : InstitucionDTO.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Jun 2011
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
package mx.gob.segob.nsjp.dto.institucion;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public class InstitucionDTO extends GenericDTO {

    private Long institucionId;
    private String nombre;
    
    /**
     * 
     */
    public InstitucionDTO() {
        super();
    }  
    /**
     * @param institucionId
     * @param nombre
     */
    public InstitucionDTO(Long institucionId, String nombre) {
        super();
        this.institucionId = institucionId;
        this.nombre = nombre;
    }
    /**
     * Método de acceso al campo institucionId.
     * @return El valor del campo institucionId
     */
    public Long getInstitucionId() {
        return institucionId;
    }
    /**
     * Asigna el valor al campo institucionId.
     * @param institucionId el valor institucionId a asignar
     */
    public void setInstitucionId(Long institucionId) {
        this.institucionId = institucionId;
    }
    /**
     * Método de acceso al campo nombre.
     * @return El valor del campo nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Asigna el valor al campo nombre.
     * @param nombre el valor nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
