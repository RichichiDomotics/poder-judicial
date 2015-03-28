/**
 * 
 */
package mx.gob.segob.nsjp.dao.quejaciudadana.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.quejaciudadana.QuejaCiudadanaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.QuejaCiudadana;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author adrian
 *
 */
@Repository
public class QuejaCiudadanaDAOImpl extends GenericDaoHibernateImpl<QuejaCiudadana, Long>
		implements QuejaCiudadanaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<QuejaCiudadana> consultarQuejasPorEstatus(Long estatus) {
		final StringBuffer queryStr = new StringBuffer();
		
		queryStr.append(" FROM QuejaCiudadana qc");
		queryStr.append(" WHERE qc.estatusQueja="+estatus);
		
		//Query query=super.getSession().createQuery(queryStr.toString());
		//return query.list();
		
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("1")) {
            	queryStr.append(" order by ");
            	queryStr.append("qc.folioQueja");
            	queryStr.append(" ").append(pag.getDirOrd());
            }
        }
        logger.debug("queryString :: " + queryStr);
        final Query query = super.getSession().createQuery(queryStr.toString());
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(pag.getFirstResult());
            if (pag.getRows() != null & pag.getRows() > 0) {
                query.setMaxResults(pag.getRows());
            } else {
                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
            }
        }
        
        final List<QuejaCiudadana> resp = query.list();
        if (logger.isDebugEnabled()) {
            logger.debug("resp.size() :: " + resp.size());
        }
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<QuejaCiudadana> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
		
		return resp;
	}


}
