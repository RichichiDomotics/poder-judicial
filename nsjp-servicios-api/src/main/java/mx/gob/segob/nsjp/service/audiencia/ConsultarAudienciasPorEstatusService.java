/**
* Nombre del Programa : ConsultarAudienciasPorEstatusService.java
* Autor                            : adrian
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
package mx.gob.segob.nsjp.service.audiencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface ConsultarAudienciasPorEstatusService {

	/**
	 * Operación que realiza la funcionalidad de consultar las Audiencias por un estatus de la Audiencia
	 * 
	 * @param estatus
	 * @return Devuelve un listado de Audiencias que cumplen con el criterio.
	 * @throws NSJPNegocioException
	 */
	List<AudienciaDTO> consultarAudienciasPorEstatus(Long estatus)throws NSJPNegocioException;

	/**
	 * Consulta las audiencias que cumplen con el estatus definido por audiencia.estatus y que se encuentran
	 * entre las fechas definidas por audiencia.fechaFiltroInicio y audiencia.fechaFiltroFin y que se encuentran
	 * registradas en la base de datos de PODER JUDICIAL.
	 * @param audiencia Parametros para la busqueda de las audiencias en poder judicial
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<AudienciaDTO> consultarAudienciasFromPoderJudicial(
			AudienciaDTO audiencia) throws NSJPNegocioException;


	AudienciaDTO consultarAudienciaFromPoderJudicial(
			AudienciaDTO audiencia) throws NSJPNegocioException;

}
