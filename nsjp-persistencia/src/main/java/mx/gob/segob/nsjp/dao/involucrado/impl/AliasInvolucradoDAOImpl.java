/**
* Nombre del Programa : AliasInvolucradoDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Inplementacion de los metodos de acceso a datos de la entidad AliasInvolucrado
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
package mx.gob.segob.nsjp.dao.involucrado.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.involucrado.AliasInvolucradoDAO;
import mx.gob.segob.nsjp.model.AliasInvolucrado;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Inplementacion de los metodos de acceso a datos de la entidad AliasInvolucrado.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class AliasInvolucradoDAOImpl extends GenericDaoHibernateImpl<AliasInvolucrado, Long>
		implements AliasInvolucradoDAO {

	@SuppressWarnings("unchecked")
	public List<AliasInvolucrado> consultarAliasByInvolucrado(Long elementoId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT a ")
					.append("FROM AliasInvolucrado a ")
					.append("WHERE a.involucrado.elementoId=:elementoId");
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("elementoId", elementoId);
		return query.list();
	}

}
