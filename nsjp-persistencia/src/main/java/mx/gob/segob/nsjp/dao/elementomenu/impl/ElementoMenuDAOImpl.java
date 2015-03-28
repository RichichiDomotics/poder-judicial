/**
 * 
 */
package mx.gob.segob.nsjp.dao.elementomenu.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.elementomenu.ElementoMenuDAO;
import mx.gob.segob.nsjp.model.ElementoMenu;
import mx.gob.segob.nsjp.model.Rol;


/**
 * @author LuisMG
 * 
 */
@Repository
public class ElementoMenuDAOImpl extends
		GenericDaoHibernateImpl<ElementoMenu, Long> implements ElementoMenuDAO {

	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public ElementoMenu consultarElementoMenu(ElementoMenu elementoMenu) {
		ElementoMenu resp = new ElementoMenu();
		final StringBuffer query = new StringBuffer();
		query.append(" select s ");
		query.append(" from ElementoMenu s");
		query.append(" where s.elementoMenuId= :elementoMenuId");
		logger.debug(" query :: " + query);
		Query hbq = super.getSession().createQuery(query.toString());
		hbq.setLong("elementoMenuId", elementoMenu.getElementoMenuId());
		resp = (ElementoMenu) hbq.uniqueResult();
		return resp;
	}
	@Override
	public boolean eliminarElementoMenu (ElementoMenu elementoMenu){
		boolean resp=false;
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("DELETE FROM ElementoMenu WHERE ElementoMenu_id= "
				+ elementoMenu.getElementoMenuId());
		super.getSession().createSQLQuery(queryStr.toString()).executeUpdate();
		resp = true;
		return resp;
	}
	
	@Override
	public Long insertarSimpleElementoMenu (ElementoMenu elementoMenu){
		Long resp=null;
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("INSERT INTO ElementoMenu(ElementoMenuPadre_id,Funcion_id,cNombre,iOrden,cComando)");
		queryStr.append(" VALUES(");
		queryStr.append(elementoMenu.getElementoMenuPadre().getElementoMenuId() + "," + elementoMenu.getFuncion().getFuncionId() + ",'"
				+ elementoMenu.getcNombre() + "'," + elementoMenu.getiOrden() + ",'"+ elementoMenu.getcComando() + "')");
		resp= (long) super.getSession().createSQLQuery(queryStr.toString()).executeUpdate();
		
		return resp;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ElementoMenu> consultarElementosMenuXRol (Rol rol){
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM ElementoMenu em WHERE em.elementoMenuId IN ( ")
		.append(" SELECT em.elementoMenuId FROM ElementoMenu em left join em.roles r WHERE r.rolId = ").append(rol.getRolId())
		.append(" ) ");			
		Query query = super.getSession().createQuery	(queryString.toString());
		return query.list();
	}
	
	
}
