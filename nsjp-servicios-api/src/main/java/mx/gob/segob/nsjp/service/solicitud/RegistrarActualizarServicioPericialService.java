/**
* Nombre del Programa : RegistrarServicioPericialService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23/06/2011
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
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;

/**
 * Contraro del servicio para el manejo de Servicios Periciales.
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface RegistrarActualizarServicioPericialService {
		
	/**
	 * Método que realiza la funcionalidad de Registrar/Actualizar la información 
	 * de una Solicitud Pericial. 
	 * Registar Nueva Solicitud Pericial no se pasa el IdDocumento
	 * Actualizar Solicitud Pericial es necsario ingresar el IdDocumento
	 * 
	 * Es posible para Registrar/Actualizar la informacion de la solicitud Pericial para el caso de 
	 * pasar, o no, los siguientes parametros 
	 *  -Manejo de expediente (con un numero de expediente) para ser asignado a la solicitud
	 *  -Funcionario - Solicitante considerando el area a que pertence y el puesto del solicitante. 
	 *  
	 * Pendientes: 
	 * 1)El manejo de estatus de la solicitud.
	 * 2)Los atributos de documento y forma, que son necesario para guardar/actualiza la solicitud.
	 * 
	 * @param solicitudPericialDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
    public SolicitudPericialDTO registrarActulizarSolicitudPericial( SolicitudPericialDTO solicitudPericialDTO ) throws NSJPNegocioException;
    
}
