/**
 * Nombre del Programa : FiltroBusquedaCompromisoPeriodicoDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 Oct 2011
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

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Objeto de transferencia para la busqueda de de fechas compromiso.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class FiltroBusquedaCompromisosDTO extends GenericDTO {
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean isIncumplimiento;
    /**
     * Método de acceso al campo fechaInicio.
     * 
     * @return El valor del campo fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }
    /**
     * Asigna el valor al campo fechaInicio.
     * 
     * @param fechaInicio
     *            el valor fechaInicio a asignar
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    /**
     * Método de acceso al campo fechaFin.
     * 
     * @return El valor del campo fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }
    /**
     * Asigna el valor al campo fechaFin.
     * 
     * @param fechaFin
     *            el valor fechaFin a asignar
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    /**
     * Método de acceso al campo isIncumplimiento.
     * 
     * @return El valor del campo isIncumplimiento
     */
    public Boolean getIsIncumplimiento() {
        return isIncumplimiento;
    }
    /**
     * Asigna el valor al campo isIncumplimiento.
     * 
     * @param isIncumplimiento
     *            el valor isIncumplimiento a asignar
     */
    public void setIsIncumplimiento(Boolean isIncumplimiento) {
        this.isIncumplimiento = isIncumplimiento;
    }
}
