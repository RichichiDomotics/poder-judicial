/**
* Nombre del Programa : CalidadWSDTO.java
* Autor                            : GustavoBP                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 22/07/2012 
* Marca de cambio        : N/A
* Descripcion General    :  DTO de intercambio entre sistemas para transportar los datos básicos de calidad.
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
package mx.gob.segob.nsjp.dto.elemento;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de calidad.
 * @author GustavoBP
 * @version 1.0
 */
public class CalidadWSDTO extends GenericWSDTO {
	
	private String descripcionEstadoFisico;
	private Long valorIdCalidad;
	private Long calidades;

	/**
	 * Método de acceso al campo descripcionEstadoFisico.
	 * @return El valor del campo descripcionEstadoFisico
	 */
	public String getDescripcionEstadoFisico() {
		return descripcionEstadoFisico;
	}
	/**
	 * Asigna el valor al campo descripcionEstadoFisico.
	 * @param descripcionEstadoFisico el valor descripcionEstadoFisico a asignar
	 */
	public void setDescripcionEstadoFisico(String descripcionEstadoFisico) {
		this.descripcionEstadoFisico = descripcionEstadoFisico;
	}
	/**
	 * Método de acceso al campo valorIdCalidad.
	 * @return El valor del campo valorIdCalidad
	 */
	public Long getValorIdCalidad() {
		return valorIdCalidad;
	}
	/**
	 * Asigna el valor al campo valorIdCalidad.
	 * @param valorIdCalidad el valor valorIdCalidad a asignar
	 */
	public void setValorIdCalidad(Long valorIdCalidad) {
		this.valorIdCalidad = valorIdCalidad;
	}
	/**
	 * Método de acceso al campo calidades.
	 * @return El valor del campo calidades
	 */
	public Long getCalidades() {
		return calidades;
	}
	/**
	 * Asigna el valor al campo calidades.
	 * @param calidades el valor calidades a asignar
	 */
	public void setCalidades(Long calidades) {
		this.calidades = calidades;
	}
}
