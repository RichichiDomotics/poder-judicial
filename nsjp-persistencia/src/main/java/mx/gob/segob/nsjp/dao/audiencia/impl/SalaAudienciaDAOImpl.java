/**
 * Nombre del Programa : SalaAudienciaDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 10 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación para el acceso a datos de las sala de audiencia
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.audiencia.SalaAudienciaDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.SalaAudiencia;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación para el acceso a datos de las sala de audiencia.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class SalaAudienciaDAOImpl
        extends
            GenericDaoHibernateImpl<SalaAudiencia, Long>
        implements
            SalaAudienciaDAO {

    @Override
    public boolean existeDisponibilidad(Long salaId, Date dia,
            Long limiteOcupacion) {
        final SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        final StringBuffer qry = new StringBuffer();
        qry.append(" select SUM(a.duracionEstimada)");
        qry.append(" from SalaAudiencia s");
        qry.append(" INNER JOIN s.audiencias a");
        qry.append(" where s.salaAudienciaId = ");
        qry.append(salaId);
        qry.append("and CONVERT (nvarchar, a.fechaAudiencia, 112)=");
        qry.append(formato.format(dia));

        Query query = super.getSession().createQuery(qry.toString());

        Long suma = (Long) query.uniqueResult();
        if (logger.isDebugEnabled()) {
            logger.debug("qry :: " + qry);
            logger.debug("suma :: " + suma);
            logger.debug("limiteOcupacion :: " + limiteOcupacion);
        }
        return (suma == null || suma < limiteOcupacion);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Audiencia> consultarHoras(Long salaId, Date dia) {
        final SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        final StringBuffer qry = new StringBuffer();
        qry.append(" select new Audiencia(a.audienciaId, a.fechaAudiencia, a.duracionEstimada)");
        qry.append(" from SalaAudiencia s");
        qry.append(" INNER JOIN s.audiencias a");
        qry.append(" where s.salaAudienciaId = ");
        qry.append(salaId);
        qry.append("and CONVERT (nvarchar, a.fechaAudiencia, 112)=");
        qry.append(formato.format(dia));
        qry.append(" order by a.fechaAudiencia");

        Query query = super.getSession().createQuery(qry.toString());

        return query.list();
    }

    @Override
    public List<SalaAudiencia> consultarSalasMinimo() {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("select new SalaAudiencia(v.salaAudienciaId, v.nombreSala) from SalaAudiencia v ");
        queryStr.append(" where v.esActivo = 1");
        queryStr.append(" order by v.nombreSala");
        @SuppressWarnings("unchecked")
        List<SalaAudiencia> resp = super.getHibernateTemplate().find(
                queryStr.toString());
        if (logger.isDebugEnabled()) {

            logger.debug("resp.size() :: " + resp.size());
        }
        return resp;
    }

    @Override
    public Long consultarNumeroSalas() {
        final StringBuffer qry = new StringBuffer();
        qry.append(" select COUNT(*)");
        qry.append(" from SalaAudiencia s");
        qry.append(" where s.esActivo = 1");
        Query query = super.getSession().createQuery(qry.toString());

        Long count = (Long) query.uniqueResult();
        if (count==null) {
            return 0L;
        } 
        
        return count;
    }
    /*
     * (non-Javadoc)
     * @see mx.gob.segob.nsjp.dao.audiencia.SalaAudienciaDAO#consultarSalasPorFiltro(mx.gob.segob.nsjp.model.SalaAudiencia)
     */
	@Override
	@SuppressWarnings("unchecked")
	public List<SalaAudiencia> consultarSalasPorFiltro(
			SalaAudiencia filtro) {

		return (List<SalaAudiencia>) getHibernateTemplate().findByExample(filtro);
		
		
		
		
	}

	public List<SalaAudiencia> consultarNombresSalas(Long catDiscriminante) {

        final StringBuffer queryStr = new StringBuffer();
        
        queryStr.append("select v from SalaAudiencia v ");
        queryStr.append(" where v.esActivo = 1");

        
        if(catDiscriminante != 0){
        	queryStr.append(" and v.catDiscriminante = ");
        	queryStr.append(catDiscriminante);

        }
        
        queryStr.append(" order by v.nombreSala");
        
        @SuppressWarnings("unchecked")
        List<SalaAudiencia> resp = super.getHibernateTemplate().find(
                queryStr.toString());
        if (logger.isDebugEnabled()) {

            logger.debug("resp.size() :: " + resp.size());
        }
        return resp;


	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SalaAudiencia> consultarTodos() {

		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM SalaAudiencia sa WHERE sa.esActivo=1");
		
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}
	
}
