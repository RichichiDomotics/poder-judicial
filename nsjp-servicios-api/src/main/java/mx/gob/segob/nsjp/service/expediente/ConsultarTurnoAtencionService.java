/**
* Nombre del Programa : ConsultarTurnoAtencionService.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio de consulta de Turnos
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

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Contrato del servicio de consulta de Turnos
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface ConsultarTurnoAtencionService {
	
	/**
	 * Obtencion de los turnos atendidos por el usuario firmado
	 * @param usuarioDto
	 * @return List<TurnoDTO>
	 * @throws NSJPNegocioException
	 */
	List<TurnoDTO> consultarTurnosAtendidosPorUsuario(UsuarioDTO usuarioDto,Boolean turnosDelDia)
	throws NSJPNegocioException;
	
	/**
	 * Obtener turno para atencion
	 * @return String
	 * @throws NSJPNegocioException
	 */
	TurnoDTO obtenerTurnoParaAtencion(TurnoDTO turnoDTO) throws NSJPNegocioException;
	
	/**
	 * Obtiene un listado de los turnos pendientes de atender por tipo de turno
	 * @param tipo Tipo de turno a filtrar
	 * @return Lista de turno pendientes
	 */
	List<TurnoDTO> obtenerTurnosPendientesPorTipo(TipoTurno tipo);

	/**
	 * Sertvicio que consulta los Turnos por cualquiera, o combinación, 
	 * de los siguientes filtros:
	 * -turnoId
	 * -expediente.expedienteId
	 * -usuario.usuarioId 
	 * -numeroTurno
	 * -tipoTurno
	 * -esUrgente
	 * -fechaAtencion
	 * -estatus.valorId
	 * @param turnoFiltro
	 * @return
	 */
	List<TurnoDTO> obtenerTurnosPorFiltro(TurnoDTO turnoFiltro) throws NSJPNegocioException;
	
	/** NSJP F25.2
	 * Servicio que se encarga de proveer los expedientes en la Bandeja de Expedientes con Permisos.
	 * Se consulta a los expedientes que tienen permisos de lectura y escritura, validando que no se 
	 * haya pasado la fecha de vencimiento.  
	 * 
	 * @param usuarioDto
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<TurnoDTO> consultarTurnosConPermisosFuncionario(UsuarioDTO usuarioDto) throws NSJPNegocioException;
	
}
