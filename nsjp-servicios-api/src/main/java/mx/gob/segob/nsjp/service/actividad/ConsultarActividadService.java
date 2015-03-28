/**
 * 
 */
package mx.gob.segob.nsjp.service.actividad;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.ActividadDTO;

/**
 * @author MelitonBC
 *
 */
public interface ConsultarActividadService {
	
	/**
	 * Metdo que permite obtener la actividad por el id del expediente
	 * @param expedienteId
	 * @param actividad
	 * @return 
	 * @throws NSJPNegocioException
	 */
	public ActividadDTO obtenerActPorExpTipoAct(Long expedienteId,	Actividades actividad) throws NSJPNegocioException;
}
