/**
 * 
 */
package mx.gob.segob.nsjp.dao.alarma.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.alarma.AlarmaDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Alarma;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author adrian
 *
 */
@Repository
public class AlarmaDAOImpl extends GenericDaoHibernateImpl<Alarma, Long> implements
		AlarmaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Alarma> consultarAlarmasXFuncionario(Long claveFuncionario,
			Long estatusAlarma) {
		final StringBuffer queryStr = new StringBuffer();
		
		queryStr.append(" FROM Alarma al");
		queryStr.append(" WHERE al.funcionario.claveFuncionario="+claveFuncionario);
		if(estatusAlarma!=null)
		queryStr.append(" AND al.estatusAlarmaAlerta="+estatusAlarma);
			
		Query qry = super.getSession().createQuery(queryStr.toString());
		//return qry.list();
		
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("1")) {
                queryStr.append(" order by ");
                queryStr.append("al.funcionario");
                queryStr.append(" ").append(pag.getDirOrd());
            }
           
        }

        return super.ejecutarQueryPaginado(queryStr, pag);

	}

	@Override
	public Alarma consultarAlarmasEvento(String eventoCitaId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Alarma a WHERE ")
					.append("a.datosAsociados='").append(eventoCitaId)
					.append("'");
		Query query = super.getSession().createQuery(queryString.toString());
		return (Alarma) query.uniqueResult();
	}

}
