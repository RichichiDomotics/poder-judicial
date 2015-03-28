/**
* Nombre del Programa : DictamenDTO.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Jul 2011
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
package mx.gob.segob.nsjp.dto.documento;

import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */

public class DictamenDTO extends DocumentoDTO {

	private SolicitudPericialDTO solicitudPericial;
	
	private int totalNotas = 0;

	/**
	 * Método de acceso al campo solicitudPericial.
	 * @return El valor del campo solicitudPericial
	 */
	public SolicitudPericialDTO getSolicitudPericial() {
		return solicitudPericial;
	}

	/**
	 * Asigna el valor al campo solicitudPericial.
	 * @param solicitudPericial el valor solicitudPericial a asignar
	 */
	public void setSolicitudPericial(SolicitudPericialDTO solicitudPericial) {
		this.solicitudPericial = solicitudPericial;
	}

	/**
	 * Método de acceso al campo totalNotas.
	 * @return El valor del campo totalNotas
	 */
	public int getTotalNotas() {
		return totalNotas;
	}

	/**
	 * Asigna el valor al campo totalNotas.
	 * @param totalNotas el valor totalNotas a asignar
	 */
	public void setTotalNotas(int totalNotas) {
		this.totalNotas = totalNotas;
	}
}
