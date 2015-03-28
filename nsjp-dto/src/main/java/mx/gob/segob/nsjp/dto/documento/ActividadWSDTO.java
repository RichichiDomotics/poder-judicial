/**
 * Nombre del Programa : ActividadWSDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 15 Nov 2011
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

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ActividadWSDTO extends GenericWSDTO {
    private String nombre;
    private Date fechaCreacion;
    private Long tipoActividadId;
    /**
     * Método de acceso al campo nombre.
     * 
     * @return El valor del campo nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Asigna el valor al campo nombre.
     * 
     * @param nombre
     *            el valor nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Método de acceso al campo fechaCreacion.
     * 
     * @return El valor del campo fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    /**
     * Asigna el valor al campo fechaCreacion.
     * 
     * @param fechaCreacion
     *            el valor fechaCreacion a asignar
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    /**
     * Método de acceso al campo tipoActividadId.
     * 
     * @return El valor del campo tipoActividadId
     */
    public Long getTipoActividadId() {
        return tipoActividadId;
    }
    /**
     * Asigna el valor al campo tipoActividadId.
     * 
     * @param tipoActividadId
     *            el valor tipoActividadId a asignar
     */
    public void setTipoActividadId(Long tipoActividadId) {
        this.tipoActividadId = tipoActividadId;
    }
}
