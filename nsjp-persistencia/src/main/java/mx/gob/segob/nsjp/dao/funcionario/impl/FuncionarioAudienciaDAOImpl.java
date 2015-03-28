/**
* Nombre del Programa : FuncionarioAudienciaDAOImpl.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24/06/2011
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
package mx.gob.segob.nsjp.dao.funcionario.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioAudienciaDAO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.FuncionarioAudienciaId;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
@Repository
public class FuncionarioAudienciaDAOImpl extends GenericDaoHibernateImpl<FuncionarioAudiencia, FuncionarioAudienciaId>
		implements FuncionarioAudienciaDAO {
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.funcionario.FuncionarioAudienciaDAO#actualizarIndicadorPresenteInvolucrado(java.lang.Long, java.lang.Long, boolean)
	 */
	@Override
	public void actualizarIndicadorPresenteInvolucrado(Long funcionarioId,
			Long audienciaId, boolean presente, Boolean esTitular) {
	
		getSession().createQuery("update FuncionarioAudiencia fa set fa.esPresente = ?, fa.esTitular = ? where  "+
		" fa.id.audienciaId = ? and fa.id.claveFuncionario = ? ").
		setParameter(0, presente).
		setParameter(1, esTitular).
		setParameter(2, audienciaId).
		setParameter(3, funcionarioId).
		executeUpdate();
		
	}
	
	@Override
	public FuncionarioAudiencia consultarIndicadorPresenteInvolucrado(Long funcionarioId, Long audienciaId){
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT fa FROM FuncionarioAudiencia fa WHERE ");
		queryStr.append(" fa.id.audienciaId = ").append(audienciaId);
		queryStr.append(" AND fa.id.claveFuncionario = ").append(funcionarioId);
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (FuncionarioAudiencia) qry.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> consultarFuncionariosPorAudiencia(Long audienciaId){
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT new Funcionario(fa.funcionario.claveFuncionario) FROM FuncionarioAudiencia fa WHERE ");
		queryStr.append(" fa.id.audienciaId =  ").append(audienciaId);
		Query qry = super.getSession().createQuery(queryStr.toString());
		return qry.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<FuncionarioAudiencia> consultaFuncionariosPorAudiencia(Long audienciaId){
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT fa FROM FuncionarioAudiencia fa WHERE ");
		queryStr.append(" fa.id.audienciaId =  ").append(audienciaId);
		Query qry = super.getSession().createQuery(queryStr.toString());
		return qry.list();
	}
}
