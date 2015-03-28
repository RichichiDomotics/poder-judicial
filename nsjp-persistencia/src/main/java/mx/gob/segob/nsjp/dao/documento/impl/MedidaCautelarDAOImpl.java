/**
* Nombre del Programa : MedidaCautelarDAOImpl.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 6 Jul 2011
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
package mx.gob.segob.nsjp.dao.documento.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.documento.MedidaCautelarDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.MedidaCautelar;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author Tattva-IT
 *
 */

@Repository
public class MedidaCautelarDAOImpl extends GenericDaoHibernateImpl<MedidaCautelar, Long>
		implements MedidaCautelarDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<MedidaCautelar> consultarMedidasCautelaesPorInvolucrado(
			Long invId) {

		final StringBuffer queryStr = new StringBuffer();
		queryStr.append(" From MedidaCautelar m ")
				.append(" where m.involucrado.elementoId =")
				.append(invId);
		Query query = super.getSession().createQuery(queryStr.toString());
		return query.list();

	}

	@Override
	public MedidaCautelar obtenerMedidaCautelar(Long idMed, Long idInv) {
		
		final StringBuffer queryString = new StringBuffer();
		queryString.append(" From MedidaCautelar m ")
				.append(" where m.involucrado.elementoId =")
				.append(idInv)
				.append(" AND m.documentoId = ")
				.append(idMed);
		Query query = super.getSession().createQuery(queryString.toString());
		return (MedidaCautelar) query.uniqueResult();

				
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.documento.MedidaCautelarDAO#obtenerMedidasCautelaresPorExpediente(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MedidaCautelar> obtenerMedidasCautelaresPorExpediente(
			Long numeroExpedienteId) {
		
		return getHibernateTemplate().find("from MedidaCautelar m where m.numeroExpediente.numeroExpedienteId = ? or " +
				" m.numeroExpediente.numeroExpedientePadre.numeroExpedienteId = ?",numeroExpedienteId,numeroExpedienteId);
	}

	   /*
     * (non-Javadoc)
     * @see mx.gob.segob.nsjp.dao.documento.MedidaCautelarDAO#obtenerMedidasCautelaresPorExpediente(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MedidaCautelar> obtenerMedidasCautelaresPorNumeroExpediente(
            String numeroExpediente,Long discriminanteId) {

        final StringBuffer query = new StringBuffer();
        query.append("select m from MedidaCautelar m join m.numeroExpediente ne left join ne.numeroExpedientePadre nep");
        query.append(" where ne.numeroExpediente = '");
        query.append(numeroExpediente).append("'");
        query.append(" or nep.numeroExpediente = '");
        query.append(numeroExpediente).append("'");
        query.append(" and ne.expediente.discriminante.catDiscriminanteId=").append(discriminanteId);
        PaginacionDTO pag = PaginacionThreadHolder.get();
        return super.ejecutarQueryPaginado(query, pag);
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MedidaCautelar> consultarMedidasCautelaresPorEstatus(
			Long estatusId) {
		
		final StringBuffer queryString = new StringBuffer();
		queryString.append(" From MedidaCautelar m where m.estatus = " + estatusId);				
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();				
	}

	@Override
	public Long obtenerMedCauPorFechasYTipoNedida(Date fechaInicio,
			Date fechaFin, Long tipoMedida) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT COUNT(*) FROM MedidaCautelar mc ")
					.append("WHERE mc.valorTipoMedida=").append(tipoMedida)
					.append(" AND CONVERT(VARCHAR, mc.fechaCreacion, 112)")
					.append(" BETWEEN ").append(DateUtils.formatearBD(fechaInicio))
					.append(" AND ").append(DateUtils.formatearBD(fechaFin));
		Query query = super.getSession().createQuery(queryString.toString());
		return (Long) query.uniqueResult();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MedidaCautelar> obtenerMedidasCautelaresPorFechaIncumplientoDelDiaAnterior(){
		
		Date fecha = obtenFechaDelDiaAnterior();
		DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
		
		StringBuffer queryString = new StringBuffer();		
		
		queryString.append("select mc FROM MedidaCautelar mc inner join mc.compromisoPeriodico.fechaCompromisos fc").
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
