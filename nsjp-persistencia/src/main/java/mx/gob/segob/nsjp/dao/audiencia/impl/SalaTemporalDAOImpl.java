/**
 * Nombre del Programa : SalaTemporalDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion de SalaTemporalDAO
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
package mx.gob.segob.nsjp.dao.audiencia.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.audiencia.SalaTemporalDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.model.SalaTemporal;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación de SalaTemporalDAO.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class SalaTemporalDAOImpl
        extends
            GenericDaoHibernateImpl<SalaTemporal, Long>
        implements
            SalaTemporalDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<SalaTemporal> consultarTodos() {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM SalaTemporal");
		
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

}
