/**
 * Nombre del Programa : AsentamientoDAOImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion de los metodos de acceso a datos de Aentamiento
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

import java.sql.SQLException;
import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.domicilio.AsentamientoDAO;
import mx.gob.segob.nsjp.model.Asentamiento;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de los metodos de acceso a datos de Aentamiento.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Repository
public class AsentamientoDAOImpl
        extends
            GenericDaoHibernateImpl<Asentamiento, Long>
        implements
            AsentamientoDAO {

    @Override
    public List<Asentamiento> consultarPorTipo(Long idTipoAsentamiento) {
        return this.consultar(null, null, idTipoAsentamiento);
    }

    @Override
    public List<Asentamiento> consultar(Long idMpio, Long idCiudad,
            Long idTipoAsentamiento) {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("select new Asentamiento(v.asentamientoId, v.nombreAsentamiento)");
        queryStr.append(" from Asentamiento v ");
        queryStr.append(" where 1=1");
        if (idMpio != null) {
            queryStr.append(" and v.municipio.municipioId = ");
            queryStr.append(idMpio);
        }
        if (idCiudad != null) {
            queryStr.append(" and v.ciudad.ciudadId = ");
            queryStr.append(idCiudad);
        }
        if (idTipoAsentamiento != null) {
            queryStr.append(" and v.catTipoAsentamiento.catTipoAsentamientoId = ");
            queryStr.append(idTipoAsentamiento);
        }
        queryStr.append(" order by v.nombreAsentamiento");

        logger.debug("queryStr :: " + queryStr);

        @SuppressWarnings("unchecked")
        List<Asentamiento> resp = super.getHibernateTemplate().find(
                queryStr.toString());
        if (logger.isDebugEnabled()) {
            logger.debug("resp.size() :: " + resp.size());
        }
        return resp;
    }

    @Override
    public String obtenerCP(final Long idAsentamiento) {
        return getHibernateTemplate().execute(new HibernateCallback<String>() {
            @Override
            public String doInHibernate(Session session)
                    throws HibernateException, SQLException {
                final StringBuffer queryString = new StringBuffer();
                queryString.append("select a.codigoPostal");
                queryString.append(" from Asentamiento a");
                queryString.append(" where a.asentamientoId = :idAsentamiento");
                Query q = session.createQuery(queryString.toString());
                q.setLong("idAsentamiento", idAsentamiento);
                return (String) q.uniqueResult();
            }
        });
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Asentamiento> consultarPorCP(String cp) {
    	final StringBuffer queryStr = new StringBuffer();
    	
    	queryStr.append(" FROM Asentamiento a");
    	queryStr.append(" WHERE a.codigoPostal ='"+cp+"'");
    	
    	Query query = super.getSession().createQuery(queryStr.toString());
        return query.list();
    }



}
