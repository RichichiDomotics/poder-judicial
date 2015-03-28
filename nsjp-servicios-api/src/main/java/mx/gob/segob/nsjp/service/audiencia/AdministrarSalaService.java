/**
 * 
 */
package mx.gob.segob.nsjp.service.audiencia;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;

/**
 * Interfaz de servicios que permiten administrar las Salas:
 * Audiencia
 * JAVS
 * @author GustavoBP
 *
 */
public interface AdministrarSalaService {

	/**
	 * Servicio para consultar una Sala Audiencia por medio de SalaAudienciaId
	 * 
	 * @param salaAudienciaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	SalaAudienciaDTO consultarSalaAudiencia(
			SalaAudienciaDTO salaAudienciaDTO) throws NSJPNegocioException;

	/**
	 * Servicio que se encarga de actualizar o modificar la sala de Audiencia (Con o sin Sala JAVS)
	 * donde se muestran los siguientes casos:
	 * Si NO existe el Id (null) de Sala Audiencia: Guardar la Sala Audiencia con la Sala JAVS
	 * Si existe el ID de Sala Auidiencia: se modifica la Sala Audiencia con la Sala JAVS.
	 * 
	 * El servicio que permite la modificación de la SalaAudiencia, considerando la 
	 * modificación de la SalaJavs
	 * En caso de que se cambie el estatus de Inactivo de la SalaAudiencia, 
	 * la SalaJAvs se elimina fisicamente.
	 * 
	 * @param salaAudienciaDTO
	 * @throws NSJPNegocioException
	 */
	SalaAudienciaDTO guardarActualizarSalaAudiencia(SalaAudienciaDTO salaAudienciaDTO) throws NSJPNegocioException;
	
}