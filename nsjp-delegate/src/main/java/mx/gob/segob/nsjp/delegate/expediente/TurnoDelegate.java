/**
 * Nombre del Programa : TurnoDelegate.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 26 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Delegate para las acciones con el turno
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
package mx.gob.segob.nsjp.delegate.expediente;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Delegate para las acciones con el turno.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface TurnoDelegate {
    /**
     * Genera un turno de atención en base a los siguientes valors
     * <ul>
     * <li>tipoTurno</li>
     * <li>esUrgente</li>
     * </ul>
     * 
     * @param defTurno
     * @return TurnoDTO.<b>numeroTurno</b>
     * @throws NSJPNegocioException
     */
    TurnoDTO generarTurnoAtencion(TurnoDTO defTurno)
            throws NSJPNegocioException;
    
    /**
     * Consulta los turnos atendidos por el usuario firmado
     * @param usuarioDTO
     * @return List<TurnoDTO>
     * @throws NSJPNegocioException
     */
    List<TurnoDTO> consultarTurnosAtendidosPorUsuario(UsuarioDTO usuarioDTO,boolean turnosDelDia)throws NSJPNegocioException;
    
    /**
     * Obtiene el turno proximo para atencion
     * @return TurnoDTO
     * @throws NSJPNegocioException
     */
    TurnoDTO obtenerTurnoParaAtencion(TurnoDTO turnoDTO)throws NSJPNegocioException;
    
    /**
     * Cancela el turno que estaba listo para ser atendido
     * @param turnDto
     * @throws NSJPNegocioException
     */
    void cancelarTurnoParaAtencion(TurnoDTO turnDto, UsuarioDTO usuarioDTO) throws NSJPNegocioException;
    /**
	 * Obtiene un listado de los turnos pendientes de atender por tipo de turno
	 * @param tipo Tipo de turno a filtrar
	 * @return Lista de turno pendientes
	 */
	List<TurnoDTO> obtenerTurnosPendientesPorTipo(TipoTurno tipo);

	/**
	 * Servicio que consulta los Turnos por cualquiera, o combinación, 
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

	/**
	 * Genera un turno de atención en base a los siguientes valors
     * <ul>
     * <li>tipoTurno</li>
     * <li>esUrgente</li>
     * </ul>
     * 
     * Y posterior al ageneración, Obtiene el turno proximo para atencion.
	 * 
	 * @param defTurno
	 * @return
	 * @throws NSJPNegocioException
	 */
	TurnoDTO generarConsultarTurnoAtencion(TurnoDTO defTurno) throws NSJPNegocioException;
	
	
	/**
	 * NSJP F25.2
	 * Servicio que se encarga de proveer los expedientes en la Bandeja de Expedientes con Permisos.
	 * Se consulta a los expedientes que tienen permisos de lectura y escritura, validando que no se 
	 * haya pasado la fecha de vencimiento.  
	 * Parametros Requeridos:
	 * -Usuario:
	 * 		-Funcionario:
	 * 			-claveFuncionario
	 * 			-discriminante
	 * 		
	 * @param usuarioDto
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<TurnoDTO> consultarTurnosConPermisosFuncionario(UsuarioDTO usuarioDto) throws NSJPNegocioException;
}
