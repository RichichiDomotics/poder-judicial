/**
* Nombre del Programa : MedidaAlternaDAOImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dao.medida.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.medida.MedidaAlternaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.MedidaAlterna;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación para el DAO de Medida Alterna
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Repository
public class MedidaAlternaDAOImpl extends GenericDaoHibernateImpl<MedidaAlterna, Long> 
	implements MedidaAlternaDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<MedidaAlterna> consultarMedidasAlternasPorNumeroExpediente(
			Long numeroExpedienteId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM MedidaAlterna ma WHERE")
					.append(" ma.numeroExpediente=").append(numeroExpedienteId);

		//Query query = super.getSession().createQuery(queryString.toString());
		//return query.list();

		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("1")) {
            	queryString.append(" order by ");
            	queryString.append("ma.numeroCaso");
            	queryString.append(" ").append(pag.getDirOrd());
            }
        }
        logger.debug("queryString :: " + queryString);
        final Query query = super.getSession().createQuery(queryString.toString());
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(pag.getFirstResult());
            if (pag.getRows() != null & pag.getRows() > 0) {
                query.setMaxResults(pag.getRows());
            } else {
                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
            }
        }
        
        final List<MedidaAlterna> resp = query.list();
        if (logger.isDebugEnabled()) {
            logger.debug("resp.size() :: " + resp.size());
        }
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<MedidaAlterna> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
		
		return resp;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MedidaAlterna> consultarMedidasAlternasPorEstatus(
			Long estatusMedida) {
		
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM MedidaAlterna ma ")
					.append("WHERE ma.estatus=").append(estatusMedida);					
		
		//Query query = super.getSession().createQuery(queryString.toString());
		//return query.list();
		
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("1")) {
            	queryString.append(" order by ");
            	queryString.append("ma.numeroCaso");
            	queryString.append(" ").append(pag.getDirOrd());
            }
        }
        logger.debug("queryString :: " + queryString);
        final Query query = super.getSession().createQuery(queryString.toString());
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(pag.getFirstResult());
            if (pag.getRows() != null & pag.getRows() > 0) {
                query.setMaxResults(pag.getRows());
            } else {
                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
            }
        }
        
        final List<MedidaAlterna> resp = query.list();
        if (logger.isDebugEnabled()) {
            logger.debug("resp.size() :: " + resp.size());
        }
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<MedidaAlterna> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
		
		return resp;
	}

	@Override
	public Long obtenerMedAltPorFechasYTipoNedida(Date fechaInicio,
			Date fechaFin, Long tipoMedida) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT COUNT(*) FROM MedidaAlterna ma ")
					.append("WHERE ma.valorTipoMedida=").append(tipoMedida)
					.append(" AND CONVERT(VARCHAR, ma.fechaCreacion, 112)")
					.append(" BETWEEN ").append(DateUtils.formatearBD(fechaInicio))
					.append(" AND ").append(DateUtils.formatearBD(fechaFin));
		Query query = super.getSession().createQuery(queryString.toString());
		return (Long) query.uniqueResult();
	}

	@Override
	public MedidaAlterna obtenerMedidaAlterna(Long idMed, Long idInv) {
		
		final StringBuffer queryString = new StringBuffer();
		queryString.append(" From MedidaAlterna m ")
				.append(" where m.involucrado.elementoId =")
				.append(idInv)
				.append(" AND m.documentoId = ")
				.append(idMed);
		Query query = super.getSession().createQuery(queryString.toString());
		return (MedidaAlterna) query.uniqueResult();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MedidaAlterna> obtenerMedidasAlternasIncumplidasDelDiaAnterior() {		
		Date fecha = obtenFechaDelDiaAnterior();
		DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
		
		StringBuffer queryString = new StringBuffer();		
		
		queryString.append("select ma FROM MedidaAlterna ma inner join ma.compromisoPeriodico.fechaCompromisos fc").
		append(" WHERE fc.fechaCompromiso = '" + dfm.format(fecha) + "'");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();	
	}

	@SuppressWarnings("static-access")
	private Date obtenFechaDelDiaAnterior() {
		Calendar hoy =Calendar.getInstance();
		hoy.setTime(new Date());
		System.out.println("Fecha Inicio: " + hoy.getTime());			
		if(hoy.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)//Si es Lunes restamos 3 dias, es decir consulta los del sabado
			hoy.add(Calendar.DATE, -3);
		else{
			if(hoy.get(Calendar.DAY_OF_WEEK) == hoy.SUNDAY )//Si es Domingo restamos 2 dias, es decir consulta los del sabado				
				hoy.add(Calendar.DATE, -2);
			else
				hoy.add(Calendar.DATE, -1);
		}			
		return hoy.getTime();
	}


}
