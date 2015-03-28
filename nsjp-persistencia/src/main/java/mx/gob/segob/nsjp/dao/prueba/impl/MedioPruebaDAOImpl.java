/**
 * Nombre del Programa : MedioPruebaDAOImpl.java
 * Autor                            : GustavoBP
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 29/09/2011
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
package mx.gob.segob.nsjp.dao.prueba.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.prueba.MedioPruebaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.DatoPrueba;
import mx.gob.segob.nsjp.model.MedioPrueba;

/**
 * Implementación de los métodos de accesos a datos de la entidad MedioPrueba.
 * 
 * @version 1.0
 * @author GustavoBP
 */
@Repository
public class MedioPruebaDAOImpl extends
		GenericDaoHibernateImpl<MedioPrueba, Long> implements MedioPruebaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<MedioPrueba> consultarMediosPruebaPorDatoPrueba(
			Long datoPruebaId, Long expedienteId, Boolean relacionados) {
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT DISTINCT rmp.medioPrueba");
		sb.append(" FROM RelacionDatoMedioPrueba rmp");
		sb.append(" WHERE rmp.datoPrueba.expediente=" + expedienteId);
		if (relacionados != null) {
			if (relacionados)
				sb.append(" AND rmp.datoPrueba=" + datoPruebaId);
		}
		final PaginacionDTO pag = PaginacionThreadHolder.get();
		return super.ejecutarQueryPaginado(sb, pag);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DatoPrueba> consultarDatosPruebaXMedioPrueba(Long medioPruebaId) {
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT DISTINCT rmp.datoPrueba");
		sb.append(" FROM RelacionDatoMedioPrueba rmp");
		sb.append(" WHERE rmp.medioPrueba=" + medioPruebaId);

		Query query = super.getSession().createQuery(sb.toString());
		return query.list();
	}

}
