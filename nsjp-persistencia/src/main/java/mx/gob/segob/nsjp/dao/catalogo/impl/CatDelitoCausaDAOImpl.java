/**
 * 
 */
package mx.gob.segob.nsjp.dao.catalogo.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.catalogo.CatDelitoCausaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CatDelitoCausa;

/**
 * @author EduardoAD
 *
 */
@Repository
public class CatDelitoCausaDAOImpl extends
GenericDaoHibernateImpl<CatDelitoCausa, Long> implements CatDelitoCausaDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<CatDelitoCausa> consultarTodos() {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("from CatDelitoCausa dc ");
		queryStr.append(" order by dc.catDelitoCausaId");

		logger.debug("queryStr :: " + queryStr);
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);

        return super.ejecutarQueryPaginado(queryStr, pag);
	}
}
