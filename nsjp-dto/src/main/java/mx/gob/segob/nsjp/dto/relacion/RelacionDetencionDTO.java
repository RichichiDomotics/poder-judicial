/**
* Nombre del Programa : RelacionDetencion.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : DTO para mostrar los datos de una relacion de quien detuvo
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
package mx.gob.segob.nsjp.dto.relacion;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * DTO para mostrar los datos de una relacion de quien detuvo.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class RelacionDetencionDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1211424185797745644L;

	private Long idRelacion;
	private Long idSujeto;
	private Long idCmplemento;
	private String nombreSujeto;
	/**
	 * Método de acceso al campo idRelacion.
	 * @return El valor del campo idRelacion
	 */
	public Long getIdRelacion() {
		return idRelacion;
	}
	/**
	 * Asigna el valor al campo idRelacion.
	 * @param idRelacion el valor idRelacion a asignar
	 */
	public void setIdRelacion(Long idRelacion) {
		this.idRelacion = idRelacion;
	}
	/**
	 * Método de acceso al campo idSujeto.
	 * @return El valor del campo idSujeto
	 */
	public Long getIdSujeto() {
		return idSujeto;
	}
	/**
	 * Asigna el valor al campo idSujeto.
	 * @param idSujeto el valor idSujeto a asignar
	 */
	public void setIdSujeto(Long idSujeto) {
		this.idSujeto = idSujeto;
	}
	/**
	 * Método de acceso al campo idCmplemento.
	 * @return El valor del campo idCmplemento
	 */
	public Long getIdCmplemento() {
		return idCmplemento;
	}
	/**
	 * Asigna el valor al campo idCmplemento.
	 * @param idCmplemento el valor idCmplemento a asignar
	 */
	public void setIdCmplemento(Long idCmplemento) {
		this.idCmplemento = idCmplemento;
	}
	/**
	 * Método de acceso al campo nombreSujeto.
	 * @return El valor del campo nombreSujeto
	 */
	public String getNombreSujeto() {
		return nombreSujeto;
	}
	/**
	 * Asigna el valor al campo nombreSujeto.
	 * @param nombreSujeto el valor nombreSujeto a asignar
	 */
	public void setNombreSujeto(String nombreSujeto) {
		this.nombreSujeto = nombreSujeto;
	}	
	
}
