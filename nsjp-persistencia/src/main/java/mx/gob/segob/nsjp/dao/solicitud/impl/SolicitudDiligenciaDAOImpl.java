/**
* Nombre del Programa : SolicitudDiligenciaDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 28 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos para el acceso a datos de la Entidad SolicitudDiligencia
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
package mx.gob.segob.nsjp.dao.solicitud.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDiligenciaDAO;
import mx.gob.segob.nsjp.model.SolicitudDiligencia;

/**
 * Implementacion de metodos para el acceso a datos de la Entidad SolicitudDiligencia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class SolicitudDiligenciaDAOImpl extends GenericDaoHibernateImpl<SolicitudDiligencia, Long> 
	implements SolicitudDiligenciaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudDiligencia> consultarDiligenciasDelExpediente(
			Long expedienteId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM SolicitudDiligencia sd ")
					.append("WHERE sd.numeroExpediente=")
					.append(expedienteId);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

}
