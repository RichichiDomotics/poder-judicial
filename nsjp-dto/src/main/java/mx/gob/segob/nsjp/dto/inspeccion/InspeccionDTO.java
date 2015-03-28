/**
 * Nombre del Programa : InspeccionDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 Oct 2011
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
package mx.gob.segob.nsjp.dto.inspeccion;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class InspeccionDTO extends GenericDTO {
    private Long inspeccionId;
    private ExpedienteDTO expediente;
    private FuncionarioDTO funcionarioInspeccionado;
    private FuncionarioDTO funcionarioRegistra;
    private ValorDTO estatus;
    private String folioInspeccion;
    private Date fechaRegistro;
    private String descripcion;
    private String motivo;
    private MultaSancionDTO multaSancion;

    /**
     * 
     */
    public InspeccionDTO() {
        super();
    }


    /**
     * Método de acceso al campo inspeccionId.
     * 
     * @return El valor del campo inspeccionId
     */
    public Long getInspeccionId() {
        return inspeccionId;
    }
    /**
     * Asigna el valor al campo inspeccionId.
     * 
     * @param inspeccionId
     *            el valor inspeccionId a asignar
     */
    public void setInspeccionId(Long inspeccionId) {
        this.inspeccionId = inspeccionId;
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
     * Método de acceso al campo funcionarioInspeccionado.
     * 
     * @return El valor del campo funcionarioInspeccionado
     */
    public FuncionarioDTO getFuncionarioInspeccionado() {
        return funcionarioInspeccionado;
    }
    /**
     * Asigna el valor al campo funcionarioInspeccionado.
     * 
     * @param funcionarioInspeccionado
     *            el valor funcionarioInspeccionado a asignar
     */
    public void setFuncionarioInspeccionado(
            FuncionarioDTO funcionarioInspeccionado) {
        this.funcionarioInspeccionado = funcionarioInspeccionado;
    }
    /**
     * Método de acceso al campo funcionarioRegistra.
     * 
     * @return El valor del campo funcionarioRegistra
     */
    public FuncionarioDTO getFuncionarioRegistra() {
        return funcionarioRegistra;
    }
    /**
     * Asigna el valor al campo funcionarioRegistra.
     * 
     * @param funcionarioRegistra
     *            el valor funcionarioRegistra a asignar
     */
    public void setFuncionarioRegistra(FuncionarioDTO funcionarioRegistra) {
        this.funcionarioRegistra = funcionarioRegistra;
    }
    /**
     * Método de acceso al campo estatus.
     * 
     * @return El valor del campo estatus
     */
    public ValorDTO getEstatus() {
        return estatus;
    }
    /**
     * Asigna el valor al campo estatus.
     * 
     * @param estatus
     *            el valor estatus a asignar
     */
    public void setEstatus(ValorDTO estatus) {
        this.estatus = estatus;
    }
    /**
     * Método de acceso al campo folioInspeccion.
     * 
     * @return El valor del campo folioInspeccion
     */
    public String getFolioInspeccion() {
        return folioInspeccion;
    }
    /**
     * Asigna el valor al campo folioInspeccion.
     * 
     * @param folioInspeccion
     *            el valor folioInspeccion a asignar
     */
    public void setFolioInspeccion(String folioInspeccion) {
        this.folioInspeccion = folioInspeccion;
    }
    /**
     * Método de acceso al campo fechaRegistro.
     * 
     * @return El valor del campo fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    /**
     * Asigna el valor al campo fechaRegistro.
     * 
     * @param fechaRegistro
     *            el valor fechaRegistro a asignar
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    /**
     * Método de acceso al campo descripcion.
     * 
     * @return El valor del campo descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Asigna el valor al campo descripcion.
     * 
     * @param descripcion
     *            el valor descripcion a asignar
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Método de acceso al campo motivo.
     * 
     * @return El valor del campo motivo
     */
    public String getMotivo() {
        return motivo;
    }
    /**
     * Asigna el valor al campo motivo.
     * 
     * @param motivo
     *            el valor motivo a asignar
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    /**
     * Método de acceso al campo multaSancion.
     * 
     * @return El valor del campo multaSancion
     */
    public MultaSancionDTO getMultaSancion() {
        return multaSancion;
    }
    /**
     * Asigna el valor al campo multaSancion.
     * 
     * @param multaSancion
     *            el valor multaSancion a asignar
     */
    public void setMultaSancion(MultaSancionDTO multaSancion) {
        this.multaSancion = multaSancion;
    }
}
