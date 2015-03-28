/**
* Nombre del Programa : SolicitarAudienciaBasicoService.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24/06/2011
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
package mx.gob.segob.nsjp.service.audiencia;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO;

/**
 * Interfaz de negocio para registra la solicitud de una audiencia con datos básicos, ya sea interna
 * o deste otra área
 * @version 1.0
 * @author Emigdio Hernández
 *
 */

public interface SolicitarAudienciaBasicoService {
	/**
	 * Recibe una petición básica de una audiencia de una área externa
	 * Se realiza una búsqueda del caso con el número de caso enviado como parte del DTO:
	 * Si no se encuentra el caso entonces se crea un expediente nuevo y se asocia a la solicitud de audiencia
	 * Si se encuentra el número de caso y el caso tiene un solo expediente entonces la solicitud
	 * se asocia a ese expediente
	 * Si se encuntra mas de un expediente entonces la solicitud no se asocia a ninguno
	 * @param solicitud Datos
	 * @return Identificador de la Solicitud de Audiencia creada
	 */
	Long registrarSolicitudAudienciaBasico(SolicitudAudienciaBasicoWSDTO solicitud) throws NSJPNegocioException;

}
