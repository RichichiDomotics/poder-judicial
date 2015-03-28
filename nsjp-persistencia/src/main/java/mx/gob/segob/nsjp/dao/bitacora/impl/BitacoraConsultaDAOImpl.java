/**
* Nombre del Programa : BitacoraConsultaDAOImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del DAO para BitacoraConsulta.
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
package mx.gob.segob.nsjp.dao.bitacora.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.bitacora.BitacoraConsultaDAO;
import mx.gob.segob.nsjp.model.BitacoraConsulta;
import mx.gob.segob.nsjp.model.Funcionario;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación del DAO para BitacoraConsulta.
 * @version 1.0
 * @author GustavoBP
 */
@Repository("bitacoraConsultaDAO")
public class BitacoraConsultaDAOImpl extends GenericDaoHibernateImpl<BitacoraConsulta, Long> implements BitacoraConsultaDAO {

	@SuppressWarnings("unchecked")
	public List<BitacoraConsulta> consultarBitacoraConsultaPorFiltros(
			String numeroExpediente, Date fechaConsulta, Date horaConsulta, Funcionario funcionario, Boolean esPermitida ){
		final StringBuffer queryString = new StringBuffer();
		queryString.append(" FROM BitacoraConsulta BC ")
					.append(" WHERE 1=1 ");
		
		if(numeroExpediente!= null && !numeroExpediente.trim().isEmpty() )
			queryString.append(" AND BC.numeroExpediente.numeroExpediente LIKE '%")
					.append( numeroExpediente ).append("%' ");
				
		if(fechaConsulta!= null )
			queryString.append(" AND CONVERT (varchar, BC.fechaConsulta, 112) >= ")
						.append( DateUtils.formatearBD(fechaConsulta));
		
		if(horaConsulta!= null )
			queryString.append(" AND CONVERT (varchar, BC.fechaConsulta, 114) >= '")
						.append(  DateUtils.formatearHoraSegs(horaConsulta)).append("' ");
		
		if(funcionario!= null) {
			if(funcionario.getNombreFuncionario()!= null && !funcionario.getNombreFuncionario().trim().isEmpty())
				queryString.append(" AND BC.usuario.funcionario.nombreFuncionario like '%")
							.append(funcionario.getNombreFuncionario()).append("%' ");
			if(funcionario.getApellidoPaternoFuncionario()!= null && !funcionario.getApellidoPaternoFuncionario().trim().isEmpty())
				queryString.append(" AND BC.usuario.funcionario.apellidoPaternoFuncionario like '%")
							.append(funcionario.getApellidoPaternoFuncionario()).append("%' ");
			if(funcionario.getApellidoMaternoFuncionario()!= null && !funcionario.getApellidoMaternoFuncionario().trim().isEmpty())
				queryString.append(" AND BC.usuario.funcionario.apellidoMaternoFuncionario like '%")
							.append(funcionario.getApellidoMaternoFuncionario()).append("%' ");
		}
		
		if(esPermitida!= null){
			queryString.append(" AND BC.esPermitida = ").append(esPermitida);
		}
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}
}
