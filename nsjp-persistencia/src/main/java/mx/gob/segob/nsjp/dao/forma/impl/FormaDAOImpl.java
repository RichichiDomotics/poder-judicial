/**
* Nombre del Programa : FormaDAOImpl.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/05/2011
* Marca de cambio        : N/A
* Descripcion General    : Definición del objeto de acceso a datos para el catálogo de formas
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
package mx.gob.segob.nsjp.dao.forma.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Forma;

import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
/**
 * Definición del objeto de acceso a datos para el catálogo de formas
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
@Repository
public class FormaDAOImpl extends GenericDaoHibernateImpl<Forma, Long>
implements FormaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Forma> consultarFormaPlantilla(String tipoDocumento) {
		StringBuffer queryString = new StringBuffer();
		
		queryString.append("SELECT f.formaId, f.nombre, f.tipoForma, f.tipoDocumento, f.fechaCreacion");
		queryString.append(" FROM Forma f");
		queryString.append(" WHERE f.tipoDocumento=").append(tipoDocumento);
		
		logger.info("/***** " + queryString.toString());
		Query query = super.getSession().createQuery(queryString.toString());
		
		return query.list();
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.forma.FormaDAO#consultarPlantillaPorTipo(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Forma> consultarPlantillaPorTipo(Long tipoForma) {
		StringBuffer queryString = new StringBuffer();
		queryString
			.append("SELECT f ")
			.append(" FROM Forma f ");
		if(tipoForma!=null && tipoForma>0){
			queryString.append(" WHERE f.tipoForma.valorId = "+tipoForma);
		}
		final PaginacionDTO pag = PaginacionThreadHolder.get();
	    logger.debug("pag :: " + pag);
	    if(pag!=null && pag.getCampoOrd() != null){
	    	queryString.append(" ORDER BY ");
	    	int orden = NumberUtils.toInt(pag.getCampoOrd(), 0);
	    	switch(orden){
		    	case 1: //Nombre Plantilla
		    		queryString.append(" f.nombre ");
		    		break;
		    	case 2: // TipoPlantilla
		    		queryString.append(" f.tipoForma.valor ");
		    		break;
		    	case 3: // FechaCreacionPlantilla
		    		queryString.append(" f.fechaCreacion ");
		    		break;
		    	default:
		    		queryString.append(" f.formaId ");
		    		break;		    		
		    	}
	    	queryString.append(" "+pag.getDirOrd());
	    }
	    logger.debug("query :: " + queryString);
		return ejecutarQueryPaginado(queryString, pag);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.forma.FormaDAO#consultarFormasDisponibles()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Forma> consultarFormasDisponibles() {
		return getHibernateTemplate().find("from Forma f order by f.formaId asc");
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.forma.FormaDAO#consultarFormaPorNombre(java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Forma consultarFormaPorNombre(String nombreForma) {
		
		List resultado = getHibernateTemplate().find("from Forma f where f.nombre = ?",nombreForma);
		if(resultado != null && resultado.size()>0){
			return (Forma)resultado.get(0);
		}
		
		return null;
	}

}
