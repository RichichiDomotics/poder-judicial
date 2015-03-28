/**
* Nombre del Programa : SolicitudTrasladoImputadoDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos de la entidad SolicitudTrasladoImputado
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
import mx.gob.segob.nsjp.dao.solicitud.SolicitudTrasladoImputadoDAO;
import mx.gob.segob.nsjp.model.SolicitudTrasladoImputado;

/**
 * Implementacion de metodos de acceso a datos de la entidad SolicitudTrasladoImputado.
 * @author cesarAgustin
 *
 */
@Repository
public class SolicitudTrasladoImputadoDAOImpl extends
		GenericDaoHibernateImpl<SolicitudTrasladoImputado, Long> implements
		SolicitudTrasladoImputadoDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<SolicitudTrasladoImputado> consultarSolicitudesTrasladosImputadoByEstatus(
			Long estatusSolicitud) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM SolicitudTrasladoImputado st ")
					.append("WHERE st.estatus=").append(estatusSolicitud);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

}
