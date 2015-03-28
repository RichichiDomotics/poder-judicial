/**
* Nombre del Programa : InmediacionDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto Inmediacion
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

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto Inmediacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class InmediacionDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -600546995957970704L;
	
	private Long inmediacionId;	
	private ValorDTO valor;
	private String descripcion;	
	
	
	/**
	 * Método de acceso al campo inmediacionId.
	 * @return El valor del campo inmediacionId
	 */
	public Long getInmediacionId() {
		return inmediacionId;
	}
	/**
	 * Asigna el valor al campo inmediacionId.
	 * @param inmediacionId el valor inmediacionId a asignar
	 */
	public void setInmediacionId(Long inmediacionId) {
		this.inmediacionId = inmediacionId;
	}
	/**
	 * Método de acceso al campo valor.
	 * @return El valor del campo valor
	 */
	public ValorDTO getValor() {
		return valor;
	}
	/**
	 * Asigna el valor al campo valor.
	 * @param valor el valor valor a asignar
	 */
	public void setValor(ValorDTO valor) {
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
