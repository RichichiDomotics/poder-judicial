/**
* Nombre del Programa : InvolucradoOcupacionDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos para la entidad InvolucradoOcupacion
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
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoOcupacionDAO;
import mx.gob.segob.nsjp.model.InvolucradoOcupacion;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de metodos de acceso a datos para la entidad InvolucradoOcupacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class InvolucradoOcupacionDAOImpl extends
		GenericDaoHibernateImpl<InvolucradoOcupacion, Long> implements
		InvolucradoOcupacionDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<InvolucradoOcupacion> consultarOcupacionByInvolucrado(
			Long involucradoId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM InvolucradoOcupacion io ")
					.append("WHERE io.involucrado.elementoId=")
					.append(involucradoId);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

}
