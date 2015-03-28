/**
* Nombre del Programa : Excarcelacion.java
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

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class ExcarcelacionDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2990448242048934115L;

	private Long idExcarcelacion;
	
	private String observaciones;
	
	private Date fechaExcarcelacion;
	
	private TipoExcarcelacionDTO tipoExcarcelacionDTO;

	/**
	 * Método de acceso al campo idExcarcelacion.
	 * @return El valor del campo idExcarcelacion
	 */
	public Long getIdExcarcelacion() {
		return idExcarcelacion;
	}

	/**
	 * Asigna el valor al campo idExcarcelacion.
	 * @param idExcarcelacion el valor idExcarcelacion a asignar
	 */
	public void setIdExcarcelacion(Long idExcarcelacion) {
		this.idExcarcelacion = idExcarcelacion;
	}

	/**
	 * Método de acceso al campo observaciones.
	 * @return El valor del campo observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Asigna el valor al campo observaciones.
	 * @param observaciones el valor observaciones a asignar
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Método de acceso al campo fechaExcarcelacion.
	 * @return El valor del campo fechaExcarcelacion
	 */
	public Date getFechaExcarcelacion() {
		return fechaExcarcelacion;
	}

	/**
	 * Asigna el valor al campo fechaExcarcelacion.
	 * @param fechaExcarcelacion el valor fechaExcarcelacion a asignar
	 */
	public void setFechaExcarcelacion(Date fechaExcarcelacion) {
		this.fechaExcarcelacion = fechaExcarcelacion;
	}

	/**
	 * Método de acceso al campo tipoExcarcelacion.
	 * @return El valor del campo tipoExcarcelacion
	 */
	public TipoExcarcelacionDTO getTipoExcarcelacionDTO() {
		return tipoExcarcelacionDTO;
	}

	/**
	 * Asigna el valor al campo tipoExcarcelacion.
	 * @param tipoExcarcelacion el valor tipoExcarcelacion a asignar
	 */
	public void setTipoExcarcelacionDTO(TipoExcarcelacionDTO tipoExcarcelacionDTO) {
		this.tipoExcarcelacionDTO = tipoExcarcelacionDTO;
	}
	
}
