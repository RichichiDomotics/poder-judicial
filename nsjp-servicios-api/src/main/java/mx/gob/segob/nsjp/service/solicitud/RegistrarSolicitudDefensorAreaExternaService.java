/**
* Nombre del Programa : RegistrarSolicitudDefensorAreaExternaService.java
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
package mx.gob.segob.nsjp.service.solicitud;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorWSDTO;

/**
 * Interfaz de negocio para la funcionalidad de registro de una solicitud de defensor
 * generada por una institución externa
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface RegistrarSolicitudDefensorAreaExternaService {

	/**
	 * Realizar el registro en la base de datos local de una solicitud de defensor
	 * junto con los datos de audiencia y sala, estos datos se registran únicamente con 
	 * fines informativos, el objetivo final es enviar una notificación al abogado defensor
	 * para que se presente en la sala indicada a la hora indicada
	 * @param solicitud Datos fuente para el registro
	 * @return Objeto de solicitud con el ID resultante después de insertarlo en la BD
	 */
	SolicitudDefensorWSDTO registrarSolicitudDefensor(SolicitudDefensorWSDTO solicitud) throws NSJPNegocioException;
	
}
