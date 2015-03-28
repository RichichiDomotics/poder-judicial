package mx.gob.segob.nsjp.dao.involucrado.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.involucrado.DefensaInvolucradoDAO;
import mx.gob.segob.nsjp.model.DefensaInvolucrado;
import mx.gob.segob.nsjp.model.DefensaInvolucradoId;
import mx.gob.segob.nsjp.model.Involucrado;
//FIXME DAJV DOCUMENTACION
@Repository
public class DefensaInvolucradoDAOImpl extends
		GenericDaoHibernateImpl<DefensaInvolucrado, DefensaInvolucradoId>
		implements DefensaInvolucradoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DefensaInvolucrado> consultarInvolucradoExpedienteDefensoria(
			Long expedienteId, Long valorId) throws NSJPNegocioException {
		final StringBuffer hqlQuery = new StringBuffer();
		hqlQuery.append(" FROM DefensaInvolucrado di ")
				.append(" WHERE di.numeroExpediente.expediente.expedienteId="+expedienteId)
				.append(" AND di.involucrado.calidad.tipoCalidad.valorId="+valorId)
				.append(" and di.involucrado.esActivo = true");
		Query query = super.getSession().createQuery(hqlQuery.toString());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Involucrado consultarInvolucradoNumeroExpedienteDefensoria(
			Long idNumeroExpediente) throws NSJPNegocioException {
		final StringBuffer hqlQuery = new StringBuffer();
		hqlQuery.append(" SELECT di.involucrado ")
				.append(" FROM DefensaInvolucrado di ")
				.append(" WHERE di.numeroExpediente.numeroExpedienteId="+idNumeroExpediente)
				.append(" and di.involucrado.esActivo = true");
		Query query = super.getSession().createQuery(hqlQuery.toString());
		return (Involucrado) query.uniqueResult();
	}

	
	@Override
	public Involucrado consultarInvolucradoPGNumeroExpedienteDefensoria(
			Long numeroExpedienteId) throws NSJPNegocioException {
		final StringBuffer hqlQuery = new StringBuffer();
		hqlQuery.append(" SELECT di.involucradoPg ")
				.append(" FROM DefensaInvolucrado di ")
				.append(" WHERE di.numeroExpediente.numeroExpedienteId="+numeroExpedienteId)
				.append(" and di.involucrado.esActivo = true");
		Query query = super.getSession().createQuery(hqlQuery.toString());
		return (Involucrado) query.uniqueResult();
	}
	
	
	@Override
	public List<Involucrado> consultarInvolucradosDEFNumeroExpedienteDefensoriaPorCalidad(
			Long expedienteId) throws NSJPNegocioException {
		final StringBuffer hqlQuery = new StringBuffer();
		hqlQuery.append(" SELECT i ")
				.append(" FROM Involucrado i ")
				.append(" WHERE i.expediente.expedienteId="+expedienteId)
				.append(" and i.calidad.tipoCalidad.valorId = " + Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId());
		Query query = super.getSession().createQuery(hqlQuery.toString());
		return query.list();
	}

}
