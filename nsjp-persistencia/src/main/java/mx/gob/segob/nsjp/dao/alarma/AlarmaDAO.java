/**
 * 
 */
package mx.gob.segob.nsjp.dao.alarma;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Alarma;

/**
 * @author adrian
 *
 */
public interface AlarmaDAO extends GenericDao<Alarma, Long> {

	/**
	 * Operación que permite consultar las alarmas para un funcionario y opcionalmente se puede filtrar por estatus
	 * @param claveFuncionario
	 * @param estatusAlarma
	 * @return
	 */
	List<Alarma> consultarAlarmasXFuncionario(Long claveFuncionario,
			Long estatusAlarma);

	/**
	 * 
	 * @param eventoCitaId
	 * @return
	 */
	Alarma consultarAlarmasEvento(String eventoCitaId);

}
