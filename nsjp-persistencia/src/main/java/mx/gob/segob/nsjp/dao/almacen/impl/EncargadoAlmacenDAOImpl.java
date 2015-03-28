/**
 * 
 */
package mx.gob.segob.nsjp.dao.almacen.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.almacen.EncargadoAlmacenDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.model.EncargadoAlmacen;
import mx.gob.segob.nsjp.model.EncargadoAlmacenId;
import mx.gob.segob.nsjp.model.Funcionario;

/**
 * @author adrian
 *
 */
@Repository
public class EncargadoAlmacenDAOImpl extends GenericDaoHibernateImpl<EncargadoAlmacen, EncargadoAlmacenId> implements EncargadoAlmacenDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> consultarEncargadosDAlmacen(
			Long identificadorAlmacen) {
		final StringBuffer queryStr = new StringBuffer();
		
		queryStr.append("SELECT ea.funcionario");
		queryStr.append(" FROM EncargadoAlmacen ea");
		if(identificadorAlmacen != null && identificadorAlmacen > 0)
			queryStr.append(" WHERE ea.almacen="+identificadorAlmacen);
		Query qry = super.getSession().createQuery(queryStr.toString());
		return qry.list();
	}

}
