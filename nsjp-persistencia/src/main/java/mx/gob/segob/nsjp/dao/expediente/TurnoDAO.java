/**
* Nombre del Programa : TurnoDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 4 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Interface para metodos de acceso a datos de la entidad Turno
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
package mx.gob.segob.nsjp.dao.expediente;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Turno;
/**
 * Interface para metodos de acceso a datos de la entidad Turno.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface TurnoDAO extends GenericDao<Turno, Long> {

	/**
	 * Recupera el ultimo numero de turno de acuerdo al tipo 
	 * de turno y la urgencia 
	 * @param tipoTurno
	 * @param urgente
	 * @return
	 */
	String obtenerUltimoNumero(String tipoTurno, Boolean urgente, Date fecha,long agenciaId);
	
	/**
	 * Obtiene los turnos que fueron atendidos por usuario
	 * @param idUsuario
	 * @return
	 */
	List<Turno> obtenerTurnosAtencionPorIdUsuario(Long idUsuario, Date today, TipoTurno tTurno,Long agenciaId);	
	
	/**
	 * Obtiene el siguiente turno para ser atendido, de acuerdo al tipo del turno solicitado.
	 * @param tipoturno
	 * @param urgente
	 * @param dia
	 * @return
	 */
	Turno obtenerProximoTurnoAtencion(String tipoturno, Boolean urgente, Date dia,long agenciaId);
	/**
	 * Obtiene un listado de los turnos pendientes de atender por tipo de turno
	 * @param tipo Tipo de turno a filtrar
	 * @return Lista de turno pendientes
	 */
	List<Turno> obtenerTurnosPendientesPorTipo(TipoTurno tipo);

	/**
	 * Obtiene los turnos por filtro de:
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
	List<Turno> obtenerTurnosPorFiltro(Turno turnoFiltro);
}
