/**
 * 
 */
package mx.gob.segob.nsjp.service.objeto;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;

/**
 * @author adrian
 *
 */
public interface AsociarObjetoAlmacenService {

	/**
     * Operación que permite asignar un objeto-evidencia a un almacen
     * @param objetoDTO: idObjeto
     * @param almacenDTO: idAlmacen
     * @return
     * @throws NSJPNegocioException
     */
	ObjetoDTO asociarObjetoAlmacen(ObjetoDTO objetoDTO, AlmacenDTO almacenDTO)throws NSJPNegocioException;

}
