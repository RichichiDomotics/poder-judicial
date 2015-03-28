/**
 * 
 */
package mx.gob.segob.nsjp.service.funcionario;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarAreasAgenciaService {

	/**
	 * Servicio que permite consultar que áreas existen para una agencia
	 * @param idAgencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<JerarquiaOrganizacionalDTO> consultarAreasXAgencia(Long idAgencia)throws NSJPNegocioException;

}
