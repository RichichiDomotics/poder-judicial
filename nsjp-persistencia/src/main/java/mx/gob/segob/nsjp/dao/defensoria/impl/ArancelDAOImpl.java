/**
 * 
 */
package mx.gob.segob.nsjp.dao.defensoria.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.defensoria.ArancelDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Arancel;

import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author adrian
 *
 */
@Repository("arancelDAO")
public class ArancelDAOImpl extends GenericDaoHibernateImpl<Arancel, Long> implements
		ArancelDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Arancel> consultarArancelesXNumeroExpediente(
			Long numeroExpedienteId, Long claveFuncionario) throws NSJPNegocioException {
		StringBuffer query = new StringBuffer();
		
		query.append(" FROM Arancel ar");
		query.append(" WHERE ar.numeroExpediente="+numeroExpedienteId);
		if(claveFuncionario != null){
			query.append(" and ar.funcionario="+claveFuncionario);
		}
		
		final PaginacionDTO pag = PaginacionThreadHolder.get();
	    logger.debug("pag :: " + pag);
	    if(pag!=null && pag.getCampoOrd() != null){
	    	query.append(" ORDER BY ");
	    	int orden = NumberUtils.toInt(pag.getCampoOrd(), 0);
	    	switch(orden){
		    	case 2032: //Periodo
		    		query.append(" ar.fechaInicio");
		    		break;
		    	case 2033: //Costo
		    		query.append(" ar.clase_val.valor");
		    		break;
		    	case 2034: //Horas
		    		query.append(" ar.horas");
		    		break;
		    	case 2035: //Costo total
		    		query.append(" ar.monto");
		    		break;
		    	case 2036: // Fecha de Pago
		    		query.append(" ar.fechaPago");
		    		break;
		    	case 2006:// Nombre del defensor
		    		query.append(" ar.funcionario.nombreFuncionario "+pag.getDirOrd()+",");
		    		query.append(" ar.funcionario.apellidoPaternoFuncionario "+pag.getDirOrd()+", ");
		    		query.append(" ar.funcionario.apellidoMaternoFuncionario ");
		    	case 2031: //Fecha de registro tiene el mismo orden que el identificador del arancel por lo tanto manejamos ese ordenamiento
		    	default:
		    		query.append(" arancel_id");
		    		break;
	    	}
	    	query.append(" "+pag.getDirOrd());
	    }
	    
	    return super.ejecutarQueryPaginado(query, pag);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Arancel> consultarArancelesXFuncionario(Long claveFuncionario) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" FROM Arancel ar");
		sb.append(" WHERE ar.funcionario="+claveFuncionario);
		
		Query query = super.getSession().createQuery(sb.toString());
		return query.list();
	}


}
