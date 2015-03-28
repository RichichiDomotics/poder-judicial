/**
 * 
 */
package mx.gob.segob.nsjp.dao.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.catalogo.CatDistritoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CatDistrito;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author AlineGS
 *
 */
@Repository
public class CatDistritoDAOImpl extends GenericDaoHibernateImpl<CatDistrito, Long>
		implements CatDistritoDAO {
    public List<CatDistrito> consultarTodos() {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append(" from CatDistrito v ");
        queryStr.append(" order by v.claveDistrito");
        
        logger.debug("queryStr :: " + queryStr); 
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("1")) {
                queryStr.append(" order by ");
                queryStr.append("v.claveDistrito");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("2")) {
                queryStr.append(" order by ");
                queryStr.append("v.nombreDist");
                queryStr.append(" ").append(pag.getDirOrd());
            }
        }

        return super.ejecutarQueryPaginado(queryStr, pag);
    }

    @SuppressWarnings("unchecked")
    @Override
 	public CatDistrito consultarDistritoPorDiscriminante(Long discriminante) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT d.distrito FROM CatDiscriminante d");		
		queryStr.append(" WHERE d.catDiscriminanteId = "+ discriminante);
			
        Query query = super.getSession().createQuery(queryStr.toString());
        return (CatDistrito) query.uniqueResult();
	}
}
