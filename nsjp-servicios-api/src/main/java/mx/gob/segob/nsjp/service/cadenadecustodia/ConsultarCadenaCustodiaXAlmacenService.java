/**
 * 
 */
package mx.gob.segob.nsjp.service.cadenadecustodia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarCadenaCustodiaXAlmacenService {

	/**
     * Operación que permite consultar las evidencias que se encuentran asignadas a un almacen dado
     * @param almacenDTO: idAlmacen
     * @param casoDTO: idCaso (Opcional)
     * @return
     * @throws NSJPNegocioException
     */
	List<CadenaDeCustodiaDTO> consultarCadenaCustodiaXAlmacen(
			AlmacenDTO almacenDTO, CasoDTO casoDTO)throws NSJPNegocioException;

}
