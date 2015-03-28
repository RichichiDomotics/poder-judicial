/**
* Nombre del Programa : ActualizarTurnoAtencionService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17/11/2011
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
package mx.gob.segob.nsjp.service.expediente;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;

/**
 * Contrato de servicios que se encargan de actualizar la información de los Turnos.
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface ActualizarTurnoAtencionService {

	/**
	 * Servicio que se encarga de actualizar un turno, en cualquiera de los siguientes valores:
	 * -TipoTurno
	 * -EsUrgente
	 * -Estatus
	 * -Expediente (Relación)
	 * -Usuario (Relación)
	 * -NombreCiudadano
	 * -ApellidoPaternoCiudadano
	 * -ApellidoMaternoCiudadano
	 * 
	 * @param turnoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	TurnoDTO actualizarTurno(TurnoDTO turnoDTO) throws  NSJPNegocioException;
	
}
