/**
* Nombre del Programa : SolicitudTranscripcionWSDTO.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/06/2011
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
package mx.gob.segob.nsjp.dto.solicitud;

import java.util.Date;


/**
 * Objeto de transferencia entre sistemas para transporte de los datos de una solicitud transcripcion
 * @version 1.0
 * @author rgama
 *
 */
public class SolicitudTranscripcionWSDTO extends SolicitudAudienciaBasicoWSDTO {

	private Long idAudiencia;
	private Date fechaSolicitud;
	private Long idTipoSolicitud;


	/**
	 * Método de acceso al campo idAudiencia.
	 * @return El valor del campo idAudiencia
	 */
	public Long getIdAudiencia() {
		return idAudiencia;
	}

	/**
	 * Asigna el valor al campo idAudiencia.
	 * @param idAudiencia el valor idAudiencia a asignar
	 */
	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}

	/**
	 * Método de acceso al campo fechaSolicitud.
	 * @return El valor del campo fechaSolicitud
	 */
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * Asigna el valor al campo fechaSolicitud.
	 * @param fechaSolicitud el valor fechaSolicitud a asignar
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * Método de acceso al campo idTipoSolicitud.
	 * @return El valor del campo idTipoSolicitud
	 */
	public Long getIdTipoSolicitud() {
		return idTipoSolicitud;
	}

	/**
	 * Asigna el valor al campo idTipoSolicitud.
	 * @param idTipoSolicitud el valor idTipoSolicitud a asignar
	 */
	public void setIdTipoSolicitud(Long idTipoSolicitud) {
		this.idTipoSolicitud = idTipoSolicitud;
	}
	
	
}
