/**
 * Nombre del Programa : AvisoHechoDelictivoWSDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 Sep 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DTO para transmitir el aviso de hecho delictivo via web service
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

import mx.gob.segob.nsjp.dto.hecho.HechoWSDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoWSDTO;

/**
 * DTO para transmitir el aviso de hecho delictivo via web service.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class AvisoHechoDelictivoWSDTO extends NotificacionWSDTO {
    private Date fechaAtencion;
    private HechoWSDTO hecho;
    private Long catDelitoId;
    private InvolucradoWSDTO implicado;
    private Boolean esAnonimo;
    /**
     * Método de acceso al campo fechaAtencion.
     * 
     * @return El valor del campo fechaAtencion
     */
    public Date getFechaAtencion() {
        return fechaAtencion;
    }
    /**
     * Asigna el valor al campo fechaAtencion.
     * 
     * @param fechaAtencion
     *            el valor fechaAtencion a asignar
     */
    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }
    /**
     * Método de acceso al campo hecho.
     * 
     * @return El valor del campo hecho
     */
    public HechoWSDTO getHecho() {
        return hecho;
    }
    /**
     * Asigna el valor al campo hecho.
     * 
     * @param hecho
     *            el valor hecho a asignar
     */
    public void setHecho(HechoWSDTO hecho) {
        this.hecho = hecho;
    }
    /**
     * Método de acceso al campo catDelitoId.
     * 
     * @return El valor del campo catDelitoId
     */
    public Long getCatDelitoId() {
        return catDelitoId;
    }
    /**
     * Asigna el valor al campo catDelitoId.
     * 
     * @param catDelitoId
     *            el valor catDelitoId a asignar
     */
    public void setCatDelitoId(Long catDelitoId) {
        this.catDelitoId = catDelitoId;
    }
    /**
     * Método de acceso al campo implicado.
     * 
     * @return El valor del campo implicado
     */
    public InvolucradoWSDTO getImplicado() {
        return implicado;
    }
    /**
     * Asigna el valor al campo implicado.
     * 
     * @param implicado
     *            el valor implicado a asignar
     */
    public void setImplicado(InvolucradoWSDTO implicado) {
        this.implicado = implicado;
    }
    /**
     * Método de acceso al campo esAnonimo.
     * @return El valor del campo esAnonimo
     */
    public Boolean getEsAnonimo() {
        return esAnonimo;
    }
    /**
     * Asigna el valor al campo esAnonimo.
     * @param esAnonimo el valor esAnonimo a asignar
     */
    public void setEsAnonimo(Boolean esAnonimo) {
        this.esAnonimo = esAnonimo;
    }
}
