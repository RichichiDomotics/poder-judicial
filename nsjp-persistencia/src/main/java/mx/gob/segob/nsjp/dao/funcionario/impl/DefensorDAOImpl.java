/**
* Nombre del Programa : DefensorDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementaciom de metodos de acceso a datos de la entidad Defensor
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

import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.funcionario.DefensorDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Funcionario;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Repository;

/**
 * Implementaciom de metodos de acceso a datos de la entidad Defensor.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class DefensorDAOImpl extends GenericDaoHibernateImpl<Funcionario, Long>
		implements DefensorDAO {

	@Override
	public List<Funcionario> consultarDefensoresActivos() {
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> consultarDefensoresActivosPorTipoDefensa(
			Long defensa) {
		final StringBuffer query = new StringBuffer();
		
		query.append(" from Funcionario f");
//		Codigo antes de multirooll
//		query.append(" where f.puesto="+Puestos.ABOGADO_DEFENSOR.getValorId());
		
		query.append(" where f.claveFuncionario in( ");
		query.append(" SELECT funcionario.claveFuncionario FROM Usuario WHERE usuarioId in(");
		query.append(" SELECT id.usuarioId FROM UsuarioRol WHERE id.rolId in(");
		query.append(" SELECT rolId FROM Rol WHERE jerarquiaOrganizacional.jerarquiaOrganizacionalId="+Areas.DEFENSORIA.parseLong());
		query.append(" ))) ");
		
		
		
//		Codigo antes de multirooll
//		if(defensa != null){
//			query.append(" and f.especialidad = ");
//			query.append(defensa);
//		}
		
		final PaginacionDTO pag = PaginacionThreadHolder.get();
	    logger.debug("pag :: " + pag);
	    if(pag!=null && pag.getCampoOrd() != null){
	    	query.append(" ORDER BY ");
	    	int orden = NumberUtils.toInt(pag.getCampoOrd(), 0);
	    	switch(orden){
		    	case 2025: //Tipo Defensa
		    		query.append(" f.tipoEspecialidad ");
		    		break;
		    	case 2027: // Especialidad
		    		query.append(" f.especialidad ");
		    		break;
		    	case 2026: // Nombre 		    		
		    		query.append(" f.nombreFuncionario,");
		    		query.append(" f.apellidoPaternoFuncionario,");
		    		query.append(" f.apellidoMaternoFuncionario");
		    		break;
		    	default:
		    		query.append(" f.cargaTrabajo asc");
		    		break;		    		
		    	}
	    	query.append(" "+pag.getDirOrd());
	    }
	    logger.debug("query :: " + query);
	    return super.ejecutarQueryPaginado(query, pag);

//		Query hbq = super.getSession().createQuery(query.toString());
//		
//		
//		return hbq.list();


	}

}
