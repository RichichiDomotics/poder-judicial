/**
* Nombre del Programa : CorreoElectronicoDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de los metodos de acceso a datos de la entidad CorreoElectronico
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
package mx.gob.segob.nsjp.dao.persona.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.persona.CorreoElectronicoDAO;
import mx.gob.segob.nsjp.model.CorreoElectronico;

/**
 * Implementacion de los metodos de acceso a datos de la entidad CorreoElectronico.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class CorreoElectronicoDAOImpl extends
		GenericDaoHibernateImpl<CorreoElectronico, Long> implements
		CorreoElectronicoDAO {

	@SuppressWarnings("unchecked")
	public List<CorreoElectronico> consultarCorreosByPersona(Long elementoId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT c ")
					.append("FROM CorreoElectronico c ")
					.append("WHERE c.persona.elementoId=:elementoId");
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("elementoId", elementoId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<CorreoElectronico> consultarCorreosByFuncionario(Long claveFuncionario) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT c ")
					.append("FROM CorreoElectronico c ")
					.append("WHERE c.funcionario.claveFuncionario=:claveFuncionario");
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("claveFuncionario", claveFuncionario);
		return query.list();
	}
}
