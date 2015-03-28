/**
* Nombre del Programa : CatTipoProgramaDTO.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 09/02/2012
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
package mx.gob.segob.nsjp.dto.programas;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CatTipoProgramaDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3716333928253125980L;

	private Long catTipoProgramaId;
	
	private String descripcion;

	/**
	 * Método de acceso al campo catTipoProgramaId.
	 * @return El valor del campo catTipoProgramaId
	 */
	public Long getCatTipoProgramaId() {
		return catTipoProgramaId;
	}

	/**
	 * Asigna el valor al campo catTipoProgramaId.
	 * @param catTipoProgramaId el valor catTipoProgramaId a asignar
	 */
	public void setCatTipoProgramaId(Long catTipoProgramaId) {
		this.catTipoProgramaId = catTipoProgramaId;
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
