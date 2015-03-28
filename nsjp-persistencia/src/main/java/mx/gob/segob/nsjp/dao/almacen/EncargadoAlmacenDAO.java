/**
 * 
 */
package mx.gob.segob.nsjp.dao.almacen;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.EncargadoAlmacen;
import mx.gob.segob.nsjp.model.EncargadoAlmacenId;
import mx.gob.segob.nsjp.model.Funcionario;

/**
 * @author adrian
 *
 */
public interface EncargadoAlmacenDAO extends GenericDao<EncargadoAlmacen, EncargadoAlmacenId>{

	/**
	 * Operación que permite consultar los funcionarios asignados a un almacen dado
	 * @param identificadorAlmacen Si el identificador es null consulta todos los registros de EncargadoAlmacen 
	 * @return
	 */
	List<Funcionario> consultarEncargadosDAlmacen(Long identificadorAlmacen);

}
