/**
* Nombre del Programa : AgendaFuncionarioDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos de la entidad AgendaFuncionario
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
package mx.gob.segob.nsjp.dao.tarea.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.tarea.AgendaFuncionarioDAO;
import mx.gob.segob.nsjp.model.AgendaFuncionario;

/**
 * Implementacion de metodos de acceso a datos de la entidad AgendaFuncionario.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class AgendaFuncionarioDAOImpl extends
		GenericDaoHibernateImpl<AgendaFuncionario, Long> implements
		AgendaFuncionarioDAO {

	@Override
	public AgendaFuncionario obtenerAgendaFuncionario(Long idFuncionario) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM AgendaFuncionario af ")
					.append("WHERE af.funcionario=")
					.append(idFuncionario);
		Query query = super.getSession().createQuery(queryString.toString());
		return (AgendaFuncionario) query.uniqueResult();
	}

}
