package mx.gob.segob.nsjp.service.medida;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;


/**
 * Cambia el estatus de una Medida ya sea Cautelar o Alternativa  
 * @param idMedida : Long el Id de la medida (Cautelar/Alternativa)
 * @param idEstatus : Long el Id del estatus (Obtenido del Enum EstatusMedida) 
 * @return void
 * @throws NSJPNegocioException
 */
public interface CambiarEstatusMedidaService {
	void cambiarEstatusMedida(Long idMedida, Long idEstatus) throws NSJPNegocioException;
}
