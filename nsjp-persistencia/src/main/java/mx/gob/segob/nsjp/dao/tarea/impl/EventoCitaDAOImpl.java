/**
 * Nombre del Programa : EventoCitaDAOImpl.java
 * Autor                            : cesar
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 Jul 2011
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
package mx.gob.segob.nsjp.dao.tarea.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.tarea.TipoEvento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.tarea.EventoCitaDAO;
import mx.gob.segob.nsjp.model.EventoCita;
import mx.gob.segob.nsjp.model.Funcionario;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Repository
public class EventoCitaDAOImpl extends
		GenericDaoHibernateImpl<EventoCita, Long> implements EventoCitaDAO {

	@SuppressWarnings("unchecked")
    @Override
	public List<EventoCita> consultarEventosCitasPorFunciconario(
			Long idFuncionario, Date fInicio, Date fFinal)
			throws NSJPNegocioException {

		StringBuffer queryString = new StringBuffer();
		queryString
				.append("FROM EventoCita e ")
				.append("WHERE 1 = 1 ");
		
				if (fInicio!=null && fFinal!=null) {
					queryString.append("AND e.fechaInicioEvento BETWEEN ")
								.append("'" +DateUtils.formatearBDConHora(fInicio)+"'").append(" AND ")
								.append("'" +DateUtils.formatearBDConHora(fFinal) +"'");
				}
				
				queryString.append(" AND e.agendaFuncionario.funcionario.claveFuncionario = :funcionario")
				.append(" ORDER BY e.fechaInicioEvento");
		Query query = super.getSession().createQuery(queryString.toString());
		System.out.println("EL Query es: " +  query.toString() + DateUtils.formatearBD120(fInicio));
		query.setParameter("funcionario", idFuncionario);
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EventoCita> consultarEventosPorTipoYFecha(TipoEvento tipoEvento, Date fechaEvento) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM EventoCita ec WHERE ")
					.append("ec.tipoEvento=").append(tipoEvento.getValorId())
					.append(" AND CONVERT (nvarchar, ec.fechaInicioEvento, 112)=")
				.append(DateUtils.formatearBD(fechaEvento));
				//.append();
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
    @Override
    public List<EventoCita> consultarEventosCitasPorFuncionario(Funcionario funcionario) {
        StringBuilder queryString = new StringBuilder();
        queryString.append("FROM EventoCita e ").
                append("WHERE e.agendaFuncionario.funcionario.claveFuncionario = :funcionario");
        Query query = super.getSession().createQuery(queryString.toString());
        query.setParameter("funcionario", funcionario.getClaveFuncionario());
        return query.list();
    }

}
