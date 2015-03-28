/**
* Nombre del Programa : TiempoWSDTO.java
 * Autor                            : GustavoBP                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 13/09/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Tiempo.  
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

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Tiempo.
 * 
 * @author GustavoBP
 * @version 1.0
 */
public class TiempoWSDTO extends GenericWSDTO {

    private Long tipoRegistro;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    
	public TiempoWSDTO() {
		
	}

	/**
	 * Método de acceso al campo tipoRegistro.
	 * @return El valor del campo tipoRegistro
	 */
	public Long getTipoRegistro() {
		return tipoRegistro;
	}
	/**
	 * Asigna el valor al campo tipoRegistro.
	 * @param tipoRegistro el valor tipoRegistro a asignar
	 */
	public void setTipoRegistro(Long tipoRegistro) {
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
