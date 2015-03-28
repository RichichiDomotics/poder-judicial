/**
* Nombre del Programa : TareaDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos para la entidad Tarea
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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.tarea.TareaDAO;
import mx.gob.segob.nsjp.model.Tarea;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de metodos de acceso a datos para la entidad Tarea.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class TareaDAOImpl extends GenericDaoHibernateImpl<Tarea, Long>
		implements TareaDAO {

	@Override
	public Long sumCargaDiaria(Date fechaInicioEvento, Long funcionarioId) {		
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT SUM(t.ntiempoReal) ")
					.append("FROM Tarea t WHERE ")
					.append("CONVERT (nvarchar, t.eventoCita.fechaInicioEvento, 112)=")
					.append(DateUtils.formatearBD(fechaInicioEvento)).append(" AND ")
					.append("t.eventoCita.agendaFuncionario.funcionario=")
					.append(funcionarioId);
		Query query = super.getSession().createQuery(queryString.toString());
		return (Long)query.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Tarea> obtenerDisponibilidadHorarioActividad(Date iniTarea, Short duracion, Long idFuncionario) {		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(iniTarea);
	    int iniciioTareaAProgramar = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
		int finTareaAProgramar =  iniciioTareaAProgramar+duracion;
		
		String inicioTarea = "(DATEPART(HOUR, t.eventoCita.fechaInicioEvento ) * 60 + 	datepart(MINUTE, t.eventoCita.fechaInicioEvento ))";
	    String finTarea = "(DATEPART(HOUR, t.eventoCita.fechaFinEvento ) * 60 + datepart(MINUTE, t.eventoCita.fechaFinEvento))";		
		
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Tarea t WHERE t.eventoCita.agendaFuncionario.funcionario=")
					.append(idFuncionario).append(" AND CONVERT (nvarchar, t.eventoCita.fechaInicioEvento, 112)=")
					.append(DateUtils.formatearBD(iniTarea)).append(" AND ((")
					.append(inicioTarea).append("<").append(iniciioTareaAProgramar).append(" AND ")
					.append(iniciioTareaAProgramar).append("<").append(finTarea).append(")")
					.append(" OR (")
					.append(inicioTarea).append("<").append(finTareaAProgramar).append(" AND ")
					.append(finTareaAProgramar).append("<").append(finTarea).append(")")
					.append(" OR (")
					.append(iniciioTareaAProgramar).append("<").append(inicioTarea).append(" AND ")
					.append(inicioTarea).append("<").append(finTareaAProgramar).append(")")
					.append(" OR (")
					.append(iniciioTareaAProgramar).append("<").append(finTarea).append(" AND ")
					.append(finTarea).append("<").append(finTareaAProgramar).append(")")
					.append(" OR (")
					.append(iniciioTareaAProgramar).append("=").append(inicioTarea).append(" AND ")
					.append(finTareaAProgramar).append("=").append(finTarea).append("))");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Tarea> consultarTareasFuncionario(Long idFuncionario, Date fecha) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Tarea t WHERE ")
					.append("CONVERT (nvarchar, t.eventoCita.fechaInicioEvento, 112)=")
					.append(DateUtils.formatearBD(fecha)).append(" AND ")
					.append("t.eventoCita.agendaFuncionario.funcionario=")
					.append(idFuncionario);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Tarea> consultarTareasFuncionarioByEstatus(Long idFuncionario,
			Date fecha, Long estatus) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Tarea t WHERE ")
					.append("CONVERT (nvarchar, t.eventoCita.fechaInicioEvento, 112)=")
					.append(DateUtils.formatearBD(fecha)).append(" AND ")
					.append("t.eventoCita.agendaFuncionario.funcionario=")
					.append(idFuncionario).append(" AND ").append("t.eventoCita.estatus=")
					.append(estatus);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

}
