/**
 * 
 */
package mx.gob.segob.nsjp.dao.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.catalogo.CatDelitoLugarDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CatDelitoLugar;

import org.springframework.stereotype.Repository;

/**
 * @author EduardoAD
 *
 */
@Repository
public class CatDelitoLugarDAOImpl extends
GenericDaoHibernateImpl<CatDelitoLugar, Long> implements CatDelitoLugarDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<CatDelitoLugar> consultarTodos() {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("from CatDelitoLugar dl ");
		queryStr.append(" order by dl.catDelitoLugarId");

		logger.debug("queryStr :: " + queryStr);
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);

        return super.ejecutarQueryPaginado(queryStr, pag);
	}

}
