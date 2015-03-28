/**
 * Nombre del Programa : CiudadDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación para accesar a la entidad Ciudad
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
import mx.gob.segob.nsjp.dao.domicilio.CiudadDAO;
import mx.gob.segob.nsjp.model.Ciudad;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación para accesar a la entidad Ciudad.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class CiudadDAOImpl extends GenericDaoHibernateImpl<Ciudad, Long>
        implements
            CiudadDAO {

    @Override
    public List<Ciudad> consultarPorEntidadFederativa(Long idEntidadFederativa) {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("select new Ciudad(v.ciudadId, v.nombreCiudad)");
        queryStr.append(" from Ciudad v ");
        queryStr.append(" where v.entidadFederativa.entidadFederativaId = ");
        queryStr.append(idEntidadFederativa);
        queryStr.append(" order by v.nombreCiudad");
        @SuppressWarnings("unchecked")
        List<Ciudad> resp = super.getHibernateTemplate().find(
                queryStr.toString());
        if (logger.isDebugEnabled()) {

            logger.debug("resp.size() :: " + resp.size());
        }
        return resp;
    }

	@Override
	@SuppressWarnings("unchecked")
	public List<Ciudad> consultarTodos() {
		final StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Ciudad");
		
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

}
