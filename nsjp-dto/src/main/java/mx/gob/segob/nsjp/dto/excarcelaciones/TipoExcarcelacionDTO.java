/**
* Nombre del Programa : TipoExcarcelacion.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08/02/2012
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
package mx.gob.segob.nsjp.dto.excarcelaciones;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class TipoExcarcelacionDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2632904644756614035L;

	private Long idTipoExcarcelacion;
	
	private String descripcion;

	/**
	 * Método de acceso al campo idTipoExcarcelacion.
	 * @return El valor del campo idTipoExcarcelacion
	 */
	public Long getIdTipoExcarcelacion() {
		return idTipoExcarcelacion;
	}

	/**
	 * Asigna el valor al campo idTipoExcarcelacion.
	 * @param idTipoExcarcelacion el valor idTipoExcarcelacion a asignar
	 */
	public void setIdTipoExcarcelacion(Long idTipoExcarcelacion) {
		this.idTipoExcarcelacion = idTipoExcarcelacion;
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
