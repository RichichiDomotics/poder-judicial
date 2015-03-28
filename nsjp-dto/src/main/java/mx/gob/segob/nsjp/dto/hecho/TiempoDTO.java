/**
* Nombre del Programa : TiempoDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Objeto de Transferencia del Hecho
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
package mx.gob.segob.nsjp.dto.hecho;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Objeto de Transferencia del Hecho.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class TiempoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1182023040392393045L;

	private Long tiempoId;
    private ValorDTO tipoRegistro;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    
   /**
    * 
    * @param tiempoId
    */
	public TiempoDTO(Long tiempoId) {
		this.tiempoId=tiempoId;
	}
	
	public TiempoDTO() {
		
	}

	/**
	 * Método de acceso al campo tiempoId.
	 * @return El valor del campo tiempoId
	 */
	public Long getTiempoId() {
		return tiempoId;
	}
	/**
	 * Asigna el valor al campo tiempoId.
	 * @param tiempoId el valor tiempoId a asignar
	 */
	public void setTiempoId(Long tiempoId) {
		this.tiempoId = tiempoId;
	}
	/**
	 * Método de acceso al campo tipoRegistro.
	 * @return El valor del campo tipoRegistro
	 */
	public ValorDTO getTipoRegistro() {
		return tipoRegistro;
	}
	/**
	 * Asigna el valor al campo tipoRegistro.
	 * @param tipoRegistro el valor tipoRegistro a asignar
	 */
	public void setTipoRegistro(ValorDTO tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
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
	 * Método de acceso al campo fechaInicio.
	 * @return El valor del campo fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * Asigna el valor al campo fechaInicio.
	 * @param fechaInicio el valor fechaInicio a asignar
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * Método de acceso al campo fechaFin.
	 * @return El valor del campo fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * Asigna el valor al campo fechaFin.
	 * @param fechaFin el valor fechaFin a asignar
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
    
    
}
