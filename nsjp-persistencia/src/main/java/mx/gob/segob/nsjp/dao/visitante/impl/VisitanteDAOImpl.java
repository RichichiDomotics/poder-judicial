/**
 * 
 */
package mx.gob.segob.nsjp.dao.visitante.impl;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.visitante.VisitanteDAO;
import mx.gob.segob.nsjp.model.Visitante;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author LuisMG
 * 
 */
@Repository
public class VisitanteDAOImpl extends GenericDaoHibernateImpl<Visitante, Long>
		implements VisitanteDAO {

	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public Visitante consultarVisitantePorIP(String IP) {
		Visitante resp = new Visitante();
		final StringBuffer query = new StringBuffer();
		query.append("from Visitante s");
		query.append(" where s.cIp= :cIp");
		logger.debug("query :: " + query);
		Query hbq = super.getSession().createQuery(query.toString());
		hbq.setString("cIp", IP);
		resp = (Visitante) hbq.uniqueResult();
		return resp;
	}

	@Override
	public boolean borrarVisitantePorIP(Visitante visitante) {
		boolean resp = false;
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("DELETE FROM Visitante WHERE cIP='"
				+ visitante.getcIp() + "'");
		super.getSession().createSQLQuery(queryStr.toString()).executeUpdate();
		resp = true;
		return resp;
	}

	@Override
	public boolean registrarVisitante(Visitante visitante) {
		boolean resp = false;
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("INSERT INTO Visitante(cIP,cMac,iIntentos)");
		queryStr.append(" VALUES('");
		queryStr.append(visitante.getcIp() + "','" + visitante.getcMac() + "',"
				+ visitante.getiIntentos() + ")");
		super.getSession().createSQLQuery(queryStr.toString()).executeUpdate();
		resp = true;
		return resp;

	}

	@Override
	public boolean actualizarVisitantePorIP (Visitante visitante){
		boolean resp= false;
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("UPDATE Visitante set iIntentos="+visitante.getiIntentos()+" WHERE cIP='"+visitante.getcIp()+"'");
		super.getSession().createSQLQuery(queryStr.toString()).executeUpdate();
		resp=true;
			
		return resp;
	}
	
	@Override
	public void actualizarIPVisitante(){
    	final StringBuffer queryStr = new StringBuffer();
		queryStr.append("UPDATE Visitante");
		queryStr.append(" SET iIntentos = 0");
		queryStr.append(" WHERE iIntentos >= 10");
		super.getSession().createSQLQuery(queryStr.toString()).executeUpdate();
	}
}
