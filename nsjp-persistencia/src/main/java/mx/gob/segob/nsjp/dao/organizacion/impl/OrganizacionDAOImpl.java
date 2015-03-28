/**
* Nombre del Programa : OrganizacionDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de los metodos de acceso a datos de Organizacion
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.dao.organizacion.impl;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.organizacion.OrganizacionDAO;
import mx.gob.segob.nsjp.model.Organizacion;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de los metodos de acceso a datos de Organizacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository("organizacionDAO")
public class OrganizacionDAOImpl extends
		GenericDaoHibernateImpl<Organizacion, Long> implements OrganizacionDAO {
	
	@Override
	public Organizacion obtenerOrganizacionByRelacion(Long elementoId, Long catRelacionId){
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT d ")
					.append("FROM Organizacion d, Relacion r ")
					.append("WHERE r.elementoBySujetoId.elementoId=:elementoId ")
					.append("AND r.catRelacion.catRelacionId=:catRelacionId ")
					.append("AND r.elementoByComplementoId.elementoId=d.elementoId");
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("elementoId", elementoId);
		query.setParameter("catRelacionId", catRelacionId);
		return (Organizacion) query.uniqueResult();
	}	
}
