/**
 * Nombre del Programa : HechoDAOImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 15 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion de metodos de acceso a datos de la entidad Hecho
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
package mx.gob.segob.nsjp.dao.hecho.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.hecho.HechoDAO;
import mx.gob.segob.nsjp.model.Hecho;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de metodos de acceso a datos de la entidad Hecho.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Repository("hechoDAO")
public class HechoDAOImpl extends GenericDaoHibernateImpl<Hecho, Long>
		implements HechoDAO {

	@Override
	public Hecho consultarHechoByExpediente(Long expedienteId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Hecho h ").append("WHERE h.expediente = ")
				.append(expedienteId);
		Query query = super.getSession().createQuery(queryString.toString());
		return (Hecho) query.uniqueResult();
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hecho> consultarHechos(Hecho hecho) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Hecho h");
		if (hecho.getExpediente() != null) {
			queryString.append(" WHERE h.expediente = "
					+ hecho.getExpediente().getExpedienteId());
			if (hecho.getLugar() != null)
				queryString.append(" AND h.lugar = "
						+ hecho.getLugar().getElementoId());
			if (hecho.getTiempo() != null)
				queryString.append(" AND h.tiempo = "
						+ hecho.getTiempo().getTiempoId());
		} else if (hecho.getLugar() != null) {
			queryString.append(" WHERE h.lugar = "
					+ hecho.getLugar().getElementoId());
			if (hecho.getTiempo() != null)
				queryString.append(" AND h.tiempo = "
						+ hecho.getTiempo().getTiempoId());
		} else if (hecho.getTiempo() != null) {
			queryString.append(" WHERE h.tiempo = "
					+ hecho.getTiempo().getTiempoId());
		}

		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

}
