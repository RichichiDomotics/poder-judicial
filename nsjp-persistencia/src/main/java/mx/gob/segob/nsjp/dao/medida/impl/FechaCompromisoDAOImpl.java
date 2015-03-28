/**
 * 
 */
package mx.gob.segob.nsjp.dao.medida.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.medida.FechaCompromisoDAO;
import mx.gob.segob.nsjp.model.FechaCompromiso;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author adrian
 *
 */
@Repository
public class FechaCompromisoDAOImpl extends GenericDaoHibernateImpl<FechaCompromiso, Long>
		implements FechaCompromisoDAO {

	@SuppressWarnings("unchecked")
	public List<FechaCompromiso> consultarFechaCompromisoPorMedidaId(Long idMedida)
	{
				
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM FechaCompromiso fc WHERE")
					.append(" fc.compromisoPeriodico.medida.documentoId=").append(idMedida);
		Query query = super.getSession().createQuery(queryString.toString());
		
		return query.list();
	}

	@Override
	public List<FechaCompromiso> consultarCalendarizacionPorMedidaIdReducido(
			Long idMedida) throws NSJPNegocioException {
		
		List<FechaCompromiso> fechas = new ArrayList<FechaCompromiso>();
		
		Date fecha = new Date();
		DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
		
		StringBuffer queryString = new StringBuffer();
		
		
		queryString.append("FROM FechaCompromiso fc WHERE")
		.append(" fc.compromisoPeriodico.medida.documentoId=").append(idMedida).
		append(" and fc.fechaCompromiso < '" + dfm.format(fecha) + "' order by fc.fechaCompromiso desc");
		Query query = super.getSession().createQuery(queryString.toString());
		
		if(query.list().size()>0)
		{
			try
			{
				fechas.add((FechaCompromiso)query.list().get(0));
			}catch(Exception ex)
			{
				logger.debug(ex.getStackTrace());
			}
		}
		
		
		
		
		queryString.delete(0, queryString.length());

		queryString.append("FROM FechaCompromiso fc WHERE")
		.append(" fc.compromisoPeriodico.medida.documentoId=").append(idMedida).
		append(" and fc.fechaCompromiso >= '" + dfm.format(fecha) + "'");
		
		query = super.getSession().createQuery(queryString.toString());
		
		int totalFechasCompromiso = query.list().size();
		logger.info("El total de Fechas compromiso recuperadas mayores o iguales al dia actual es: " +totalFechasCompromiso );
		if(totalFechasCompromiso >0)
		{
			if(totalFechasCompromiso >= 1)
				fechas.add((FechaCompromiso) query.list().get(0));
			if(totalFechasCompromiso > 1)
				fechas.add((FechaCompromiso) query.list().get(1));			
		}
		
		return fechas;
	}

    @Override
    public List<FechaCompromiso> buscarFechas(Date fechaInicio, Date fechaFin,
            Boolean isIncumplimiento) {

        StringBuffer qry = new StringBuffer();

        qry.append("from FechaCompromiso fc where 1=1");
        if (fechaFin == null) {
            qry.append(" AND CONVERT (nvarchar, fc.fechaCompromiso, 112) = ");
            qry.append(DateUtils.formatearBD(fechaInicio));
        } else {
            qry.append(" AND CONVERT (nvarchar, fc.fechaCompromiso, 112) between ");
            qry.append(DateUtils.formatearBD(fechaInicio));
            qry.append(" AND ").append(DateUtils.formatearBD(fechaFin));
        }
        if (isIncumplimiento != null && isIncumplimiento) {
            qry.append(" AND fc.fechaCumplimiento is null");
        } else {
            if (!isIncumplimiento) {
                qry.append(" AND fc.fechaCumplimiento is not null");
            }
        }
        logger.debug("qry :: " + qry);
        return super.getHibernateTemplate().find(qry.toString());
    }
    
    @Override
	public FechaCompromiso consultarPagoPorId(FechaCompromiso aoFechaCompromiso) {
		final StringBuffer queryStr = new StringBuffer();		
		queryStr.append(" FROM FechaCompromiso fc");
		queryStr.append(" WHERE fc.fechaCompromisoId = " + aoFechaCompromiso.getFechaCompromisoId());		
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (FechaCompromiso)qry.uniqueResult();
	}
}
