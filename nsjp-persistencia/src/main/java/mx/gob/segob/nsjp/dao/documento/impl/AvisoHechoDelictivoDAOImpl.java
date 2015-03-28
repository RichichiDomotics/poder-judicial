/**
 * 
 */
package mx.gob.segob.nsjp.dao.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.AvisoHechoDelictivo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author adrian
 *
 */
@Repository
public class AvisoHechoDelictivoDAOImpl extends
GenericDaoHibernateImpl<AvisoHechoDelictivo, Long> implements 
		mx.gob.segob.nsjp.dao.documento.AvisoHechoDelictivoDAO {

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.documento.AvisoHechoDelictivoDAO#consultarAvisosHDelictivoPorEstatus(java.lang.Long)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<AvisoHechoDelictivo> consultarAvisosHDelictivoPorEstatus(
			Long estatusNotificacion) {
		final StringBuffer queryStr = new StringBuffer();
		
		queryStr.append("FROM AvisoHechoDelictivo ahd");
		queryStr.append(" WHERE ahd.estatus="+estatusNotificacion);
		PaginacionDTO pag = PaginacionThreadHolder.get();
		return super.ejecutarQueryPaginado(queryStr, pag);
	}

	@Override
	public String obtenerUltimoFolio() {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT a.folioNotificacion ")
					.append("FROM AvisoHechoDelictivo a WHERE ")
					.append("a.documentoId= ").append("(SELECT MAX(ah.documentoId)")
					.append("FROM AvisoHechoDelictivo ah)");
		Query query = super.getSession().createQuery(queryString.toString());		
		return (String) query.uniqueResult();
	}

}
