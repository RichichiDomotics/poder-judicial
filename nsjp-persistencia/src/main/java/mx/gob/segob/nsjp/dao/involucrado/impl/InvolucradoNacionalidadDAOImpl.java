/**
* Nombre del Programa : InvolucradoNacionalidadDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos para el acceso a datos de la entidad InvolucradoNacionalidad
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
package mx.gob.segob.nsjp.dao.involucrado.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoNacionalidadDAO;
import mx.gob.segob.nsjp.model.InvolucradoNacionalidad;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Contrato de metodos para el acceso a datos de la entidad InvolucradoNacionalidad.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class InvolucradoNacionalidadDAOImpl extends
		GenericDaoHibernateImpl<InvolucradoNacionalidad, Long> implements
		InvolucradoNacionalidadDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<InvolucradoNacionalidad> consultarNacionalidadByInvolucrado(
			Long involucradoId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM InvolucradoNacionalidad n ")
					.append("WHERE n.involucrado.elementoId=")
					.append(involucradoId);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

}
