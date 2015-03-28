/**
 * Nombre del Programa  : ResolutivoDAOImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Administra la persistencia y acceso a los datos de los objetos de tipo resolutivo.
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dao.audiencia.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.audiencia.ResolutivoDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Resolutivo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Administra la persistencia y acceso a los datos de los objetos de tipo
 * resolutivo.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class ResolutivoDAOImpl extends
		GenericDaoHibernateImpl<Resolutivo, Long> implements ResolutivoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Resolutivo> consultarResolutivosByAudienciaId(Long idAudiencia) {

		final StringBuffer qryStr = new StringBuffer();

		qryStr.append("SELECT new Resolutivo(r.resolutivoId, r.temporizador, r.detalle)");
		qryStr.append("FROM Resolutivo as r ");
		qryStr.append("WHERE r.audiencia.audienciaId = ");
		qryStr.append(idAudiencia);
		qryStr.append(" ORDER BY r.temporizador");
		
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        return super.ejecutarQueryPaginado(qryStr, pag);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resolutivo> consultarResolutivosByAudienciaIdWithDocumento(
			Long idAudiencia) {

		final StringBuffer qryStr = new StringBuffer();

		qryStr.append("SELECT new Resolutivo(r.resolutivoId, r.temporizador, r.detalle, m)");
		qryStr.append("FROM Resolutivo as r, Mandamiento m ");
		qryStr.append("WHERE m.resolutivo.resolutivoId = r.resolutivoId");
		qryStr.append(" AND r.audiencia.audienciaId = " + idAudiencia);
		qryStr.append(" AND r.documento is not null ");
		
		
		logger.debug("qryStr :: " + qryStr);

		Query query = super.getSession().createQuery(qryStr.toString());
		return query.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resolutivo> consultarResolutivosByAudienciaIdWithDocumentoAndArchDigital(
			Long idAudiencia) {
		final StringBuffer qryStr = new StringBuffer();

		qryStr.append("SELECT new Resolutivo(r.resolutivoId, r.temporizador, r.detalle, m)");
		qryStr.append("FROM Resolutivo as r, Mandamiento m ");
		qryStr.append("WHERE m.resolutivo.resolutivoId = r.resolutivoId");
		qryStr.append(" AND r.audiencia.audienciaId = " + idAudiencia);
		qryStr.append(" AND r.documento is not null");
		qryStr.append(" AND m.archivoDigital is not null ");
		
		

			logger.debug("qryStr :: " + qryStr);

		Query query = super.getSession().createQuery(qryStr.toString());
		return query.list();
	}

}
