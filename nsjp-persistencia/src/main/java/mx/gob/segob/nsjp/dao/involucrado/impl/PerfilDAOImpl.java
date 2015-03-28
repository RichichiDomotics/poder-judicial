/**
* Nombre del Programa : PerfilDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos para el acceso a datos de la entidad Perfil
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
import mx.gob.segob.nsjp.dao.involucrado.PerfilDAO;
import mx.gob.segob.nsjp.model.Perfil;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de metodos para el acceso a datos de la entidad Perfil.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class PerfilDAOImpl extends GenericDaoHibernateImpl<Perfil,Long> implements PerfilDAO {
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Perfil> consultarPerfilByInvolucrado(Long involucradoId)
			{
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Perfil p ")
					.append("WHERE p.involucrado.elementoId=")
					.append(involucradoId);
		Query query = super.getSession().createQuery(queryString.toString());		
		return query.list();
	}

}
