/**
 *
 * Nombre del Programa : DomicilioDAOImpl.java                                    
 * Autor                            : Cesar Agustin                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 30/03/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Implementación para el DAO de la entidad Domicilio                      
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
package mx.gob.segob.nsjp.dao.domicilio.impl;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.model.Domicilio;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author CesarAgustin
 * @version
 */
@Repository
public class DomicilioDAOImpl extends GenericDaoHibernateImpl<Domicilio, Long>
		implements DomicilioDAO {

	@Override
	public Domicilio obtenerDomicilioByRelacion(Long elementoId,
			Long catRelacionId) {
		StringBuffer queryString = new StringBuffer();
		queryString
				.append("SELECT d FROM Domicilio d LEFT JOIN d.relacionsForComplementoId r ")
				.append("WHERE r.elementoBySujetoId.elementoId=")
				.append(elementoId).append(" ");
		if (catRelacionId != null) {
			queryString.append("AND r.catRelacion.catRelacionId=").append(
					catRelacionId);
		}
		Query query = super.getSession().createQuery(queryString.toString());
		return (Domicilio) query.uniqueResult();

	}
}
