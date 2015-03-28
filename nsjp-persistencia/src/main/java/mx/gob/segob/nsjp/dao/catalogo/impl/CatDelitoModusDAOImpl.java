/**
 * 
 */
package mx.gob.segob.nsjp.dao.catalogo.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.catalogo.CatDelitoModusDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CatDelitoModus;

/**
 * @author EduardoAD
 *
 */
@Repository
public class CatDelitoModusDAOImpl extends
GenericDaoHibernateImpl<CatDelitoModus, Long> implements CatDelitoModusDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<CatDelitoModus> consultarTodos() {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("from CatDelitoModus dm ");
		queryStr.append(" order by dm.catDelitoModusId");

		logger.debug("queryStr :: " + queryStr);
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);

        return super.ejecutarQueryPaginado(queryStr, pag);
	}
}
