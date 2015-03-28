/**
 * 
 */
package mx.gob.segob.nsjp.dao.visitante;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Visitante;

/**
 * @author LuisMG
 * 
 */
public interface VisitanteDAO extends GenericDao<Visitante, Long> {

	public Visitante consultarVisitantePorIP(String IP);

	public boolean borrarVisitantePorIP(Visitante visitante);

	public boolean actualizarVisitantePorIP (Visitante visitante);
	
	public boolean registrarVisitante(Visitante visitante);
	
	public void actualizarIPVisitante();
}
