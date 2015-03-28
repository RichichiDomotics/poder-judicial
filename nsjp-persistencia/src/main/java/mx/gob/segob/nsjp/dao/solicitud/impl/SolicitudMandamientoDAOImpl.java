package mx.gob.segob.nsjp.dao.solicitud.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudMandamientoDAO;
import mx.gob.segob.nsjp.model.SolicitudMandamiento;

@Service
@Transactional
public class SolicitudMandamientoDAOImpl extends GenericDaoHibernateImpl<SolicitudMandamiento, Long>
		implements SolicitudMandamientoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudMandamiento> consultarSolicitudesMandamientoBy(
			EstatusSolicitud estado) throws NSJPNegocioException {
		final StringBuffer hqlQuery = new StringBuffer();

		hqlQuery.append(" FROM SolicitudMandamiento s")
				.append(" WHERE s.estatus = "+estado.getValorId());
		
		logger.debug("query :: " + hqlQuery);
		
		Query hbq = super.getSession().createQuery(hqlQuery.toString());

		return hbq.list();
	}

}
