/**
 * Nombre del Programa : PertenenciaDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 11 Oct 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Representa una pertenencia
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
package mx.gob.segob.nsjp.dto.detencion;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO;

/**
 * Representa una pertenencia.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class PertenenciaDTO extends GenericDTO {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3512401294658476058L;
	
	private Long pertenenciaId;
    private ValorDTO condicionFisica;
    private DetencionDTO detencion;
    private ValorDTO tipoPertenencia;
    private String descripcion;
    private Short cantidad;
    private Boolean esDevuelto;
    private Date fechaDevolucion;
    private ValorDTO unidadMedida;
    private InventarioPertenenciaDTO inventarioPertenenciaDTO;
    /**
     * M&eacute;todo de acceso al campo pertenenciaId.
     * 
     * @return El valor del campo pertenenciaId
     */
    public Long getPertenenciaId() {
        return pertenenciaId;
    }
    /**
     * Asigna el valor al campo pertenenciaId.
     * 
     * @param pertenenciaId
     *            el valor pertenenciaId a asignar
     */
    public void setPertenenciaId(Long pertenenciaId) {
        this.pertenenciaId = pertenenciaId;
    }
    /**
     * M&eacute;todo de acceso al campo condicionFisica.
     * 
     * @return El valor del campo condicionFisica
     */
    public ValorDTO getCondicionFisica() {
        return condicionFisica;
    }
    /**
     * Asigna el valor al campo condicionFisica.
     * 
     * @param condicionFisica
     *            el valor condicionFisica a asignar
     */
    public void setCondicionFisica(ValorDTO condicionFisica) {
        this.condicionFisica = condicionFisica;
    }
    /**
     * M&eacute;todo de acceso al campo detencion.
     * 
     * @return El valor del campo detencion
     */
    public DetencionDTO getDetencion() {
        return detencion;
    }
    /**
     * Asigna el valor al campo detencion.
     * 
     * @param detencion
     *            el valor detencion a asignar
     */
    public void setDetencion(DetencionDTO detencion) {
        this.detencion = detencion;
    }
    /**
     * M&eacute;todo de acceso al campo tipoPertenencia.
     * 
     * @return El valor del campo tipoPertenencia
     */
    public ValorDTO getTipoPertenencia() {
        return tipoPertenencia;
    }
    /**
     * Asigna el valor al campo tipoPertenencia.
     * 
     * @param tipoPertenencia
     *            el valor tipoPertenencia a asignar
     */
    public void setTipoPertenencia(ValorDTO tipoPertenencia) {
        this.tipoPertenencia = tipoPertenencia;
    }
    /**
     * M&eacute;todo de acceso al campo descripcion.
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
     * M&eacute;todo de acceso al campo cantidad.
     * 
     * @return El valor del campo cantidad
     */
    public Short getCantidad() {
        return cantidad;
    }
    /**
     * Asigna el valor al campo cantidad.
     * 
     * @param cantidad
     *            el valor cantidad a asignar
     */
    public void setCantidad(Short cantidad) {
        this.cantidad = cantidad;
    }
    /**
     * M&eacute;todo de acceso al campo esDevuelto.
     * 
     * @return El valor del campo esDevuelto
     */
    public Boolean getEsDevuelto() {
        return esDevuelto;
    }
    /**
     * Asigna el valor al campo esDevuelto.
     * 
     * @param esDevuelto
     *            el valor esDevuelto a asignar
     */
    public void setEsDevuelto(Boolean esDevuelto) {
        this.esDevuelto = esDevuelto;
    }
    /**
     * M&eacute;todo de acceso al campo fechaDevolucion.
     * 
     * @return El valor del campo fechaDevolucion
     */
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
    /**
     * Asigna el valor al campo fechaDevolucion.
     * 
     * @param fechaDevolucion
     *            el valor fechaDevolucion a asignar
     */
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
	/**
	 * M&eacute;todo de acceso al campo unidadMedida.
	 * @return El valor del campo unidadMedida
	 */
	public ValorDTO getUnidadMedida() {
		return unidadMedida;
	}
	
	/**
	 * Asigna el valor al campo unidadMedida.
	 * @param unidadMedida el valor unidadMedida a asignar
	 */
	public void setUnidadMedida(ValorDTO unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	/**
	 * M&eacute;todo de acceso al campo inventarioPertenenciaDTO.
	 * @return El valor del campo inventarioPertenenciaDTO
	 */
	public InventarioPertenenciaDTO getInventarioPertenenciaDTO() {
		return inventarioPertenenciaDTO;
	}
	
	/**
	 * Asigna el valor al campo inventarioPertenenciaDTO.
	 * @param inventarioPertenenciaDTO el valor inventarioPertenenciaDTO a asignar
	 */
	public void setInventarioPertenenciaDTO(
			InventarioPertenenciaDTO inventarioPertenenciaDTO) {
		this.inventarioPertenenciaDTO = inventarioPertenenciaDTO;
	}
}