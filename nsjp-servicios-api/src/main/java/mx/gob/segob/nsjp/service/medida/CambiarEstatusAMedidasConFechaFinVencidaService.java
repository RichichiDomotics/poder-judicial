package mx.gob.segob.nsjp.service.medida;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;


/**
 * Cambia el estatus de la(s) Medida(s) ya sea Cautelar o Alternativa las cuales
 * tengan "Fecha de fin" vencida  
 * @return List<MedidaDTO> 
 * @throws NSJPNegocioException
 */
public interface CambiarEstatusAMedidasConFechaFinVencidaService {	
	List<MedidaDTO> cambiarEstatusAMedidasConFechaFinVencidaService() throws NSJPNegocioException;
}
