package mx.gob.segob.nsjp.delegate.turnolaboral;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.turnolaboral.TurnoLaboralDTO;

/**
 * Contrato de metodos para el acceso a los servicios de Turnos Laborales.
 * @version 1.0
 * @author mgallardo
 *
 */

public interface TurnoLaboralDelegate {
    
	/**
     * Consulta el Catalogo de Turnos Laborales
     * @param 
     * @return Una lista de Turnos Laborales TurnoLaboralDTO 
     * @throws NSJPNegocioException
     */
	List<TurnoLaboralDTO> consultarCatalogoTurnoLaboral() throws NSJPNegocioException;
	
}
