/**
* Nombre del Programa : EstatusEventoCita.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Enumeracion para los estatus de los eventos cita
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
package mx.gob.segob.nsjp.comun.enums.eventocita;

/**
 * Enumeracion para los estatus de los eventos cita.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public enum EstatusEventoCita {
	ATENDIDO(2117L), NO_ATENDIDO(2116L), EN_PROCESO(2115L);
	
	private Long valorId;

	private EstatusEventoCita (Long valorId) {
		this.valorId=valorId;
	}
	
	/**
	 * Método de acceso al campo valorId.
	 * @return El valor del campo valorId
	 */
	public Long getValorId() {
		return valorId;
	}
	
}
