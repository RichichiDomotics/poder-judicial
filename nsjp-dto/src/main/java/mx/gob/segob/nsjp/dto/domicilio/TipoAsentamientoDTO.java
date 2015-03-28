/**
* Nombre del Programa : TipoAsentamientoDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto tipoAsentamiento
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
package mx.gob.segob.nsjp.dto.domicilio;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto tipoAsentamiento.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class TipoAsentamientoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3690028664560077152L;

	private Long tipoAsentamientoId;
	private String tipoAsentamiento;	
	
	public TipoAsentamientoDTO() {
		super();
	}
	
	/**
	 * @param tipoAsentamientoId
	 * @param tipoAsentamiento
	 */
	public TipoAsentamientoDTO(Long tipoAsentamientoId, String tipoAsentamiento) {
		super();
		this.tipoAsentamientoId = tipoAsentamientoId;
		this.tipoAsentamiento = tipoAsentamiento;
	}
	/**
	 * M�todo de acceso al campo serialversionuid.
	 * @return El valor del campo serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * M�todo de acceso al campo tipoAsentamientoId.
	 * @return El valor del campo tipoAsentamientoId
	 */
	public Long getTipoAsentamientoId() {
		return tipoAsentamientoId;
	}
	/**
	 * Asigna el valor al campo tipoAsentamientoId.
	 * @param tipoAsentamientoId el valor tipoAsentamientoId a asignar
	 */
	public void setTipoAsentamientoId(Long tipoAsentamientoId) {
		this.tipoAsentamientoId = tipoAsentamientoId;
	}
	/**
	 * M�todo de acceso al campo tipoAsentamiento.
	 * @return El valor del campo tipoAsentamiento
	 */
	public String getTipoAsentamiento() {
		return tipoAsentamiento;
	}
	/**
	 * Asigna el valor al campo tipoAsentamiento.
	 * @param tipoAsentamiento el valor tipoAsentamiento a asignar
	 */
	public void setTipoAsentamiento(String tipoAsentamiento) {
		this.tipoAsentamiento = tipoAsentamiento;
	}
}
