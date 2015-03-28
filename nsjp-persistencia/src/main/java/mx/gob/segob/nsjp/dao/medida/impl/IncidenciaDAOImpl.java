/**
* Nombre del Programa : IncidenciaDAOImpl.java
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

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.medida.IncidenciaDAO;
import mx.gob.segob.nsjp.model.Incidencia;

/**
 * Implementación para el DAO de Incidencia
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Repository
public class IncidenciaDAOImpl  extends GenericDaoHibernateImpl<Incidencia, Long>
	implements IncidenciaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Incidencia> consultarIncidenciaPorMedida(Long idMedida){
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT I FROM  Incidencia I ");
		queryStr.append(" WHERE I.medida.documentoId = ")
			.append(idMedida);
		logger.info("Query"+queryStr);
		Query query = super.getSession().createQuery(queryStr.toString());
		
		return query.list();
	}
	
}
