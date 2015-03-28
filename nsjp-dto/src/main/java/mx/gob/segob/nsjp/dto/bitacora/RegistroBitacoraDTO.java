/**
 * Nombre del Programa : RegistroBitacoraDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 17 Oct 2011
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
package mx.gob.segob.nsjp.dto.bitacora;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class RegistroBitacoraDTO {
    private Long registroBitacoraId;
    private ValorDTO tipoMovimiento;
    private ExpedienteDTO expediente;
    private Date fechaInicio;
    private String nuevo;
    private Short consecutivo;
    /**
     * Método de acceso al campo registroBitacoraId.
     * 
     * @return El valor del campo registroBitacoraId
     */
    public Long getRegistroBitacoraId() {
        return registroBitacoraId;
    }
    /**
     * Asigna el valor al campo registroBitacoraId.
     * 
     * @param registroBitacoraId
     *            el valor registroBitacoraId a asignar
     */
    public void setRegistroBitacoraId(Long registroBitacoraId) {
        this.registroBitacoraId = registroBitacoraId;
    }
    /**
     * Método de acceso al campo tipoMovimiento.
     * 
     * @return El valor del campo tipoMovimiento
     */
    public ValorDTO getTipoMovimiento() {
        return tipoMovimiento;
    }
    /**
     * Asigna el valor al campo tipoMovimiento.
     * 
     * @param tipoMovimiento
     *            el valor tipoMovimiento a asignar
     */
    public void setTipoMovimiento(ValorDTO tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
    /**
     * Método de acceso al campo expediente.
     * 
     * @return El valor del campo expediente
     */
    public ExpedienteDTO getExpediente() {
        return expediente;
    }
    /**
     * Asigna el valor al campo expediente.
     * 
     * @param expediente
     *            el valor expediente a asignar
     */
    public void setExpediente(ExpedienteDTO expediente) {
        this.expediente = expediente;
    }
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
     * Método de acceso al campo nuevo.
     * 
     * @return El valor del campo nuevo
     */
    public String getNuevo() {
        return nuevo;
    }
    /**
     * Asigna el valor al campo nuevo.
     * 
     * @param nuevo
     *            el valor nuevo a asignar
     */
    public void setNuevo(String nuevo) {
        this.nuevo = nuevo;
    }
    /**
     * Método de acceso al campo consecutivo.
     * 
     * @return El valor del campo consecutivo
     */
    public Short getConsecutivo() {
        return consecutivo;
    }
    /**
     * Asigna el valor al campo consecutivo.
     * 
     * @param consecutivo
     *            el valor consecutivo a asignar
     */
    public void setConsecutivo(Short consecutivo) {
        this.consecutivo = consecutivo;
    }
}
