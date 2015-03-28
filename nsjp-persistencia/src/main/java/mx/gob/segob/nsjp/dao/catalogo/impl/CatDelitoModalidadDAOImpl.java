/**
 * 
 */
package mx.gob.segob.nsjp.dao.catalogo.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.catalogo.CatDelitoModalidadDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CatDelitoModalidad;

/**
 * @author EduardoAD
 *
 */
@Repository
public class CatDelitoModalidadDAOImpl extends
GenericDaoHibernateImpl<CatDelitoModalidad, Long> implements CatDelitoModalidadDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<CatDelitoModalidad> consultarTodos() {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("from CatDelitoModalidad dm ");
		queryStr.append(" order by dm.catDelitoModalidadId");

		logger.debug("queryStr :: " + queryStr);
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);

        return super.ejecutarQueryPaginado(queryStr, pag);
	}
}
