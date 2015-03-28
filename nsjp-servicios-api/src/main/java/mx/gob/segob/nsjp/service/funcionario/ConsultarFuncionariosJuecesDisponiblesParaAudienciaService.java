/**
* Nombre del Programa : ConsultarJuecesService.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio de negocio para la consulta de jueces
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
package mx.gob.segob.nsjp.service.funcionario;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Contrato del servicio de negocio para la consulta de jueces
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface ConsultarFuncionariosJuecesDisponiblesParaAudienciaService {
	
	/**
	 * Consulta los jueces disponibles para una cierta fecha y que estén desocupados durante el 
	 * toda la duración estimada de la audiencia.
	 * 
	 * @param fecha fecha y hora de la audiencia
	 * @param duracionEstimada Duración estimada en minutos
	 * @param funcionario Discriminante por el cual va a buscar funcionarios	
	 * @return Lista de jueces disponibles para la audiencia a programar
	 */
	List<FuncionarioDTO> consultarJuecesDisponiblesParaFechaYHoraAudiencia(
			Date fecha,
			Integer duracionEstimada, FuncionarioDTO funcionarioDTO) throws NSJPNegocioException;
	/**
	 * Consulta una lista de jueces disponibles para una audiencia a cierto día y cierta hora con cierta duración estimada
	 * retornando el juez con menor carga de trabajo.
	 * @param fecha Fecha y hora de la audiencia
	 * @param duracionEstimada Duración en minutos estimada de la audiencia
	 * @param funcionario Discriminante por el cual va a buscar funcionarios
	 * @return Juez o jueces elegidos
	 * @throws NSJPNegocioException 
	 */
	List<FuncionarioDTO> consultarJuezAutomaticoADesignar(
			Date fecha,
			Integer duracionEstimada,
			AudienciaDTO audiencia,
			boolean juezSustituto, 
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException;

}
