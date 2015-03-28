/**
* Nombre del Programa : ProgramarAudienciaService.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato funcional del servicio de negocio para almacenar la programación de una agenda
 * con su respectiva sala, hora, fecha, duración y juez
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
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;

/**
 * Contrato funcional del servicio de negocio para almacenar la programación de una agenda
 * con su respectiva sala, hora, fecha, duración y juez
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface ProgramarAudienciaService {
	/**
	 * Almacena los cambios hechos a la audiencia:
	 * Fecha, Hora, duración estimada
	 * Sala o Sala temporal
	 * Juez o jueces asignados
	 * Genera las notificaciones a los funcionarios involucrados y 
	 * actualiza la carga de trabajo de los funcionarios jueces de la audiencia
	 * @param audiencia Datos fuente
	 */
	void guardarProgramacionAudiencia(AudienciaDTO audiencia) throws NSJPNegocioException;

	/**
	 * Servicio que crea una audiencia vacía a partir de una audiencia
	 * @param audienciaDTO: audienciaId
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long crearAudienciaSiguiente(AudienciaDTO audienciaDTO)throws NSJPNegocioException;

}
