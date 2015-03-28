/**
 * 
 */
package mx.gob.segob.nsjp.dao.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.catalogo.CatDelitoClasificacionDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CatDelitoClasificacion;

import org.springframework.stereotype.Repository;

/**
 * @author EduardoAD
 *
 */
@Repository
public class CatDelitoClasificacionDAOImpl  extends
GenericDaoHibernateImpl<CatDelitoClasificacion, Long> implements CatDelitoClasificacionDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<CatDelitoClasificacion> consultarTodos() {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("from CatDelitoClasificacion dc ");
		queryStr.append(" order by dc.catDelitoClasificacionId");

		logger.debug("queryStr :: " + queryStr);
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);

        return super.ejecutarQueryPaginado(queryStr, pag);
	}

}
