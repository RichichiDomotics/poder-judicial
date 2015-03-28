/**
* Nombre del Programa : JerarquiaOrgTipoSolicitudDAOImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 16/08/2011
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
package mx.gob.segob.nsjp.dao.institucion.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.institucion.JerarquiaOrgTipoSolicitudDAO;
import mx.gob.segob.nsjp.model.JerarquiaOrgTipoSolicitud;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.Valor;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación del DAO para la entidad de cruce entre 
 * Jerarquia Organizacional y Tipo Solicitud
 *  
 * @version 1.0
 * @author GustavoBP
 *
 */
@Repository
public class JerarquiaOrgTipoSolicitudDAOImpl extends
		GenericDaoHibernateImpl<JerarquiaOrgTipoSolicitud, Long> implements
		JerarquiaOrgTipoSolicitudDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Valor> consultarTipoSolicitudPorJerarquiaOrganizacional(
			Long jerarquiaOrganizacionalId) {
		final StringBuffer query = new StringBuffer();
		query.append(" SELECT V FROM JerarquiaOrgTipoSolicitud JOTP, Valor V");
		query.append(" WHERE JOTP.tipoSolicitud.valorId = V.valorId");
		query.append(" AND JOTP.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ");
		query.append(jerarquiaOrganizacionalId);
		logger.debug("query :: " + query);
		Query hbq = super.getSession().createQuery(query.toString());

		return hbq.list();
	}
}
