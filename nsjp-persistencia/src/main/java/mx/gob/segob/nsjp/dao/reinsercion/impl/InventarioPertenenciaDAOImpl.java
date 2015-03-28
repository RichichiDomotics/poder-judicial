/**
* Nombre del Programa 		: InventarioPertenenciaDAOImpl.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 								Fecha: 28 Mar 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Implementaci&oacute;n del DAO para InventarioPertenencia
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 								Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 								Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.dao.reinsercion.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.reinsercion.InventarioPertenenciaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO;
import mx.gob.segob.nsjp.model.InventarioPertenencia;
import mx.gob.segob.nsjp.model.Pertenencia;

/**
 * Implementaci&oacute;n del objeto de acceso a datos para la 
 * entidad InventarioPertenencia
 * @version 1.0
 * @author EdgarTE
 *
 */
@Repository
public class InventarioPertenenciaDAOImpl extends GenericDaoHibernateImpl<InventarioPertenencia, Long> implements InventarioPertenenciaDAO {

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.reinsercion.InventarioPertenenciaDAO#consultarPertenenciasPorInventario(mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Pertenencia> consultarPertenenciasPorInventario(
			InventarioPertenenciaDTO inventarioPertenenciaDTO) throws NSJPNegocioException {
		List<Pertenencia> pertenencias = null;
		if (inventarioPertenenciaDTO != null 
				&& inventarioPertenenciaDTO.getInventarioPertenenciaId() != null 
				&& inventarioPertenenciaDTO.getInventarioPertenenciaId() > 0L ){
			
			StringBuffer hqlQuery = new StringBuffer();
	        hqlQuery.append(" SELECT p ")
	        		.append(" FROM Pertenencia as p ")
	        		.append(" JOIN p.inventarioPertenencia as ip")
	        		.append(" WHERE ip.inventarioPertenenciaId = ? ");
	        
	        final PaginacionDTO pag = PaginacionThreadHolder.get();
	        if (pag != null && pag.getCampoOrd() != null) {
	            hqlQuery.append(" order by ");
	            hqlQuery.append(pag.getCampoOrd());
	            hqlQuery.append(" ").append(pag.getDirOrd());
	        }
	        
	        Query query = super.getSession().createQuery(hqlQuery.toString());
	        query.setParameter(0, inventarioPertenenciaDTO.getInventarioPertenenciaId());
	        if (pag != null && pag.getPage() != null) {
	            query.setFirstResult(pag.getFirstResult());
	            if (pag.getRows() != null & pag.getRows() > 0) {
	                query.setMaxResults(pag.getRows());
	            } else {
	                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
	            }
	        }
	        pertenencias = (List<Pertenencia>) query.list(); 

	        if (pag != null && pag.getPage() != null) {
	            query.setFirstResult(0);
	            query.setMaxResults(-1);
	            final List<Pertenencia> temp = query.list();
	            logger.debug("temp.size() :: " + temp.size());
	            pag.setTotalRegistros(new Long(temp.size()));
	            PaginacionThreadHolder.set(pag);
	        }
	        
		}else{
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		return pertenencias;
	}
}
