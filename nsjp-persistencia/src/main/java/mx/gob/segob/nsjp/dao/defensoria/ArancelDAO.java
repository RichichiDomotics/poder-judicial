/**
 * 
 */
package mx.gob.segob.nsjp.dao.defensoria;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Arancel;

/**
 * @author adrian
 *
 */
public interface ArancelDAO extends GenericDao<Arancel, Long> {

	List<Arancel> consultarArancelesXNumeroExpediente(Long numeroExpedienteId, Long claveFuncionario) throws NSJPNegocioException;

	List<Arancel> consultarArancelesXFuncionario(Long claveFuncionario);

}
