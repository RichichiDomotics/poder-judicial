package mx.gob.segob.nsjp.dao.informepolicial.impl;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.informepolicial.OperativoDAO;
import mx.gob.segob.nsjp.model.Operativo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OperativoDAOImpl
		     extends GenericDaoHibernateImpl<Operativo,Long>
		     implements OperativoDAO {

	public Operativo consultarOperativoPorIdIPH(Long idIPH){
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append(" from Operativo o ");
		queryStr.append(" where o.informePolicialHomologado.informePolicialHomologadoId = ");
		queryStr.append(idIPH);
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (Operativo) qry.uniqueResult();
	}
}
