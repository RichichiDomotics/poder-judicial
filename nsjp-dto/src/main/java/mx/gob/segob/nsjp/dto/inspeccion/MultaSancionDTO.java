/**
* Nombre del Programa : MultaSancionDTO.java
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
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public class MultaSancionDTO extends GenericDTO{
    private Long multaSancionId;
    private FuncionarioDTO funcionarioMultado;
    private FuncionarioDTO funcionarioRegistra;
    private ValorDTO estatus;
    private InspeccionDTO inspeccion;
    private String folioMultaSancion;
    private Date fechaRegistro;
    private String descripcion;
    private String motivo;
    /**
     * Método de acceso al campo multaSancionId.
     * @return El valor del campo multaSancionId
     */
    public Long getMultaSancionId() {
        return multaSancionId;
    }
    /**
     * Asigna el valor al campo multaSancionId.
     * @param multaSancionId el valor multaSancionId a asignar
     */
    public void setMultaSancionId(Long multaSancionId) {
        this.multaSancionId = multaSancionId;
    }
    /**
     * Método de acceso al campo funcionarioMultado.
     * @return El valor del campo funcionarioMultado
     */
    public FuncionarioDTO getFuncionarioMultado() {
        return funcionarioMultado;
    }
    /**
     * Asigna el valor al campo funcionarioMultado.
     * @param funcionarioMultado el valor funcionarioMultado a asignar
     */
    public void setFuncionarioMultado(FuncionarioDTO funcionarioMultado) {
        this.funcionarioMultado = funcionarioMultado;
    }
    /**
     * Método de acceso al campo funcionarioRegistra.
     * @return El valor del campo funcionarioRegistra
     */
    public FuncionarioDTO getFuncionarioRegistra() {
        return funcionarioRegistra;
    }
    /**
     * Asigna el valor al campo funcionarioRegistra.
     * @param funcionarioRegistra el valor funcionarioRegistra a asignar
     */
    public void setFuncionarioRegistra(FuncionarioDTO funcionarioRegistra) {
        this.funcionarioRegistra = funcionarioRegistra;
    }
    /**
     * Método de acceso al campo estatus.
     * @return El valor del campo estatus
     */
    public ValorDTO getEstatus() {
        return estatus;
    }
    /**
     * Asigna el valor al campo estatus.
     * @param estatus el valor estatus a asignar
     */
    public void setEstatus(ValorDTO estatus) {
        this.estatus = estatus;
    }
    /**
     * Método de acceso al campo inspeccion.
     * @return El valor del campo inspeccion
     */
    public InspeccionDTO getInspeccion() {
        return inspeccion;
    }
    /**
     * Asigna el valor al campo inspeccion.
     * @param inspeccion el valor inspeccion a asignar
     */
    public void setInspeccion(InspeccionDTO inspeccion) {
        this.inspeccion = inspeccion;
    }
    /**
     * Método de acceso al campo folioMultaSancion.
     * @return El valor del campo folioMultaSancion
     */
    public String getFolioMultaSancion() {
        return folioMultaSancion;
    }
    /**
     * Asigna el valor al campo folioMultaSancion.
     * @param folioMultaSancion el valor folioMultaSancion a asignar
     */
    public void setFolioMultaSancion(String folioMultaSancion) {
        this.folioMultaSancion = folioMultaSancion;
    }
    /**
     * Método de acceso al campo dfechaRegistro.
     * @return El valor del campo dfechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    /**
     * Asigna el valor al campo dfechaRegistro.
     * @param dfechaRegistro el valor dfechaRegistro a asignar
     */
    public void setFechaRegistro(Date dfechaRegistro) {
        this.fechaRegistro = dfechaRegistro;
    }
    /**
     * Método de acceso al campo descripcion.
     * @return El valor del campo descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Asigna el valor al campo descripcion.
     * @param descripcion el valor descripcion a asignar
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Método de acceso al campo motivo.
     * @return El valor del campo motivo
     */
    public String getMotivo() {
        return motivo;
    }
    /**
     * Asigna el valor al campo motivo.
     * @param motivo el valor motivo a asignar
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
