/**
 * Nombre del Programa : IntentoDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 1 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de transferencia de Intento de notificacion.
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

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Objeto de transferencia de Intento de notificacion.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class IntentoDTO extends GenericDTO {
    private Long intentoId;
    private Date fechaIntento;
    private String consecutivoIntento;
    /**
     * Método de acceso al campo intentoId.
     * 
     * @return El valor del campo intentoId
     */
    public Long getIntentoId() {
        return intentoId;
    }
    /**
     * Asigna el valor al campo intentoId.
     * 
     * @param intentoId
     *            el valor intentoId a asignar
     */
    public void setIntentoId(Long intentoId) {
        this.intentoId = intentoId;
    }
    /**
     * Método de acceso al campo fechaIntento.
     * 
     * @return El valor del campo fechaIntento
     */
    public Date getFechaIntento() {
        return fechaIntento;
    }
    /**
     * Asigna el valor al campo fechaIntento.
     * 
     * @param fechaIntento
     *            el valor fechaIntento a asignar
     */
    public void setFechaIntento(Date fechaIntento) {
        this.fechaIntento = fechaIntento;
    }
    /**
     * Método de acceso al campo consecutivoIntento.
     * 
     * @return El valor del campo consecutivoIntento
     */
    public String getConsecutivoIntento() {
        return consecutivoIntento;
    }
    /**
     * Asigna el valor al campo consecutivoIntento.
     * 
     * @param consecutivoIntento
     *            el valor consecutivoIntento a asignar
     */
    public void setConsecutivoIntento(String consecutivoIntento) {
        this.consecutivoIntento = consecutivoIntento;
    }
}
