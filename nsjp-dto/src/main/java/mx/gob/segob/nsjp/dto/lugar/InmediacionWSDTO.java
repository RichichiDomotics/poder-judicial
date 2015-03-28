/**
* Nombre del Programa : InmediacionWSDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25/07/2012
* Marca de cambio        : N/A
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Objeto Inmediacion.
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
package mx.gob.segob.nsjp.dto.lugar;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Objeto Inmediacion. 
 * @author GustavoBP
 * @version 1.0
 */
public class InmediacionWSDTO extends GenericWSDTO {

	private Long valor;
	private String descripcion;	
	
	
	/**
	 * Método de acceso al campo valor.
	 * @return El valor del campo valor
	 */
	public Long getValor() {
		return valor;
	}
	/**
	 * Asigna el valor al campo valor.
	 * @param valor el valor valor a asignar
	 */
	public void setValor(Long valor) {
		this.valor = valor;
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

}
