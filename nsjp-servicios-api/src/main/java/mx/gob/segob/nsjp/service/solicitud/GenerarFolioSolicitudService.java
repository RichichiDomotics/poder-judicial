/**
 * Nombre del Programa  : GenerarFolioSolicitudService.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Genera un nuevo folio de solicitud
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.solicitud;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

public interface GenerarFolioSolicitudService {

	/**
	 * Genera el siguiente folio disponible para asignarlo a una solicitud
	 * @return folio para solicitud
	 * @throws NSJPNegocioException
	 */
	public String generarFolioSolicitud()throws NSJPNegocioException;

	/**
	 * Genera el siguiente folio disponible para asignarlo a una notifiación
	 * @return folio para notificacion
	 * @throws NSJPNegocioException
	 */
	public String generarFolioNotificacion()throws NSJPNegocioException;

	/**
	 * Genera el siguiente folio disponible para asignarlo a un documento
	 * @return folio para notificacion
	 * @throws NSJPNegocioException
	 */
	public String generarFoliodDocumento() throws NSJPNegocioException;
	/**
	 * Genera el siguiente folio disponible para asignarlo a una audiencia
	 * @return folio para notificacion
	 * @throws NSJPNegocioException
	 */
	String generarFolioAudiencia() throws NSJPNegocioException;
}
