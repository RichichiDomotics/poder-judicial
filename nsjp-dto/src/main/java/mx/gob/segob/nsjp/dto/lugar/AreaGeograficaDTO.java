/**
* Nombre del Programa : AreaGeograficaDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto AreaGeografica
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

import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto AreaGeografica.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class AreaGeograficaDTO extends LugarDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 777276830641440138L;

	private String nombre;
	private String referencias;
	/**
	 * Método de acceso al campo nombre.
	 * @return El valor del campo nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Asigna el valor al campo nombre.
	 * @param nombre el valor nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Método de acceso al campo referencias.
	 * @return El valor del campo referencias
	 */
	public String getReferencias() {
		return referencias;
	}
	/**
	 * Asigna el valor al campo referencias.
	 * @param referencias el valor referencias a asignar
	 */
	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}	

}
