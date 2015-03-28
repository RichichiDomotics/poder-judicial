/**
 *
 * Nombre del Programa : NarrativaDAO.java                                    
 * Autor                            : Ricardo Gama                                              
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 24/05/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Implementación para la entidad Delito.                      
 * Programa Dependiente  :N/A                                                      
 * Programa Subsecuente :N/A                                                      
 * Cond. de ejecucion        :N/A                                                      
 * Dias de ejecucion          :N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */
package mx.gob.segob.nsjp.dao.delito.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Expediente;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación para la entidad Delito
 * 
 * @author rgama
 * @since 1.0
 * 
 */
@Repository("delitoDAO")
public class DelitoDAOImpl extends GenericDaoHibernateImpl<Delito, Long>
		implements DelitoDAO {

	@SuppressWarnings("unchecked")
	public List<Delito> consultarDelitosPorCaso(String numeroCaso) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT d ")
				.append(" FROM Caso c JOIN c.expedientes e")
				.append(" JOIN e.delitos d")
				.append(" WHERE c.numeroGeneralCaso like :numeroCaso ");
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("numeroCaso", numeroCaso);
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Delito> obtenerDelitoPorExpediente(Long expedienteId) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("FROM Delito d ").append("WHERE d.expediente=")
				.append(expedienteId);
		Query query = super.getSession().createQuery(queryStr.toString());
		//return query.list();
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("clave")) {
                queryStr.append(" order by ");
                queryStr.append("d.Delito_id");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("claveDB")) {
                queryStr.append(" order by ");
                queryStr.append("d.Delito_id");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("delito")) {
                queryStr.append(" order by ");
                queryStr.append("d.Expediente_id");
                queryStr.append(" ").append(pag.getDirOrd());
            }
        }

        return super.ejecutarQueryPaginado(queryStr, pag);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void guardarDelito(List<Delito> delitos, Expediente expediente) {
		for (Delito delito : delitos) {
			delito.setExpediente(expediente);
			if (logger.isDebugEnabled()) {
				logger.debug("Guardando el delito = " + delito.getDelitoId());
			}
			saveOrUpdate(delito);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> obtenerTipoDelitoPorMes(Date fechaIni, Date fechaFin,
			Boolean tipoDelito) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT MONTH (d.expediente.fechaCreacion), COUNT (*)")
					.append(" FROM Delito d WHERE d.catDelito.esGrave=").append(tipoDelito)
					.append(" AND CONVERT (varchar, d.expediente.fechaCreacion, 112)")
					.append(" BETWEEN ").append(DateUtils.formatearBD(fechaIni)).append(" AND ")
					.append(DateUtils.formatearBD(fechaFin)).append(" GROUP BY MONTH (d.expediente.fechaCreacion)");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> obtenerDelitoPorMes(Date fechaIni, Date fechaFin,
			Long catDelito) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT MONTH(d.expediente.fechaCreacion), COUNT(*) FROM Delito d ")
					.append("WHERE d.catDelito=").append(catDelito).append(" AND ")
					.append("CONVERT(VARCHAR, d.expediente.fechaCreacion, 112) BETWEEN ")
					.append(DateUtils.formatearBD(fechaIni)).append(" AND ").append(DateUtils.formatearBD(fechaFin))
					.append(" GROUP BY MONTH(d.expediente.fechaCreacion)");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Delito obtenerDelitoPorId(Long delitoId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Delito d ").append("WHERE d.delitoId=")
				.append(delitoId);
		Query query = super.getSession().createQuery(queryString.toString());
		return (Delito)query.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Delito> obtenerListaDeDelitoPorExpediente(Long expedienteId) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("FROM Delito d ").append("WHERE d.expediente.expedienteId=")
				.append(expedienteId);
		
		Query query = super.getSession().createQuery(queryStr.toString());
		return (List<Delito>) query.list();
}
}
