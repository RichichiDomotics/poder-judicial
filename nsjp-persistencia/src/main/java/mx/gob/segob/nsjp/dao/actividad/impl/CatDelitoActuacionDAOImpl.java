/**
 * Nombre del Programa : CatDelitoActuacionDAOImpl.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 24-08-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dao.actividad.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.actividad.CatDelitoActuacionDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.model.CatDelitoActuacion;
import mx.gob.segob.nsjp.model.CatDelitoActuacionId;
import mx.gob.segob.nsjp.model.Valor;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @version 1.0
 * @author rgama
 */
@Repository
public class CatDelitoActuacionDAOImpl
    extends GenericDaoHibernateImpl<CatDelitoActuacion, CatDelitoActuacionId>
    implements CatDelitoActuacionDAO {
	

	@SuppressWarnings("unchecked")
	public List<Valor> consultarActuacionesPorIdsCatDelito(List<Long> idCatDelitos) {
		final StringBuffer queryStr = new StringBuffer();
		
		String idsCatDelitos = "";
		for (Long idCatDelito : idCatDelitos) {
			idsCatDelitos = idsCatDelitos + idCatDelito + ",";
		}
		idsCatDelitos = idsCatDelitos.substring(0, idsCatDelitos.lastIndexOf(","));
				
		queryStr.append("select DISTINCT cd.actuacion from CatDelitoActuacion cd ");
		queryStr.append(" where cd.catDelito.catDelitoId IN (");
			queryStr.append(idsCatDelitos);
		queryStr.append(")");
		Query query = super.getSession().createQuery(queryStr.toString());
		return query.list();
	}

	
}
