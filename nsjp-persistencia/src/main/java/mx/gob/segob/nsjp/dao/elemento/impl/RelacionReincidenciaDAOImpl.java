/**
 *
 * Nombre del Programa : RelacionReincidenciaDAOImpl.java                                    
 * Autor                            : Cesar Agustin                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 23/08/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Implementación para el DAO de la entidad RelacionReincidencia                      
 * Programa Dependiente  :N/A                                                      
 * Programa Subsecuente :N/A                                                      
 * Cond. de ejecucion        :N/A                                                      
 * Dias de ejecucion          :N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */
package mx.gob.segob.nsjp.dao.elemento.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.elemento.RelacionReincidenciaDAO;
import mx.gob.segob.nsjp.model.RelacionReincidencia;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author rgama
 * @version 1.0
 * 
 */
@Repository
public class RelacionReincidenciaDAOImpl extends GenericDaoHibernateImpl<RelacionReincidencia, Long>
        implements RelacionReincidenciaDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RelacionReincidencia> consultarRelacionesDeReincidencia(Long elementoId) {
		final StringBuffer queryString = new StringBuffer();
		queryString.append(" SELECT DISTINCT rr FROM RelacionReincidencia rr");
		queryString.append(" WHERE rr.elemento.elementoId = "+elementoId);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}
   
}
