/**
 * Nombre del Programa : EntidadFederativaDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    :Implementacion de persistencia de la tabla entidad federativa
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
package mx.gob.segob.nsjp.dao.domicilio.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.domicilio.EntidadFederativaDAO;
import mx.gob.segob.nsjp.model.EntidadFederativa;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de persistencia de la tabla entidad federativa.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class EntidadFederativaDAOImpl
        extends
            GenericDaoHibernateImpl<EntidadFederativa, Long>
        implements
            EntidadFederativaDAO {

    @Override
    public List<EntidadFederativa> consultarPorPais(Long idPais) {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("select new EntidadFederativa(v.entidadFederativaId, v.nombre)");
        queryStr.append(" from EntidadFederativa v ");
        queryStr.append(" where v.pais.valorId = ");
        queryStr.append(idPais);
        queryStr.append(" order by v.nombre");
        @SuppressWarnings("unchecked")
        List<EntidadFederativa> resp = super.getHibernateTemplate().find(
                queryStr.toString());
        if (logger.isDebugEnabled()) {

            logger.debug("resp.size() :: " + resp.size());
        }
        return resp;
    }

	@Override
	@SuppressWarnings("unchecked")
	public List<EntidadFederativa> consultarTodos() {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM EntidadFederativa");
		
		Query query = super.getSession().createQuery(queryString.toString()); 
		return query.list();
	}

}
