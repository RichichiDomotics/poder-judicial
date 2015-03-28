/**
* Nombre del Programa : BiometricoInvolucradoDAOImpl.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 27/04/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Implementacion del contrato para los metodos de acceso a datos de la entidad Biometrico              
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
package mx.gob.segob.nsjp.dao.involucrado.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.involucrado.BiometricoDAO;
import mx.gob.segob.nsjp.model.Biometrico;

/**
 * Implementacion del Contrato para los metodos de acceso a datos de la entidad BiometricoInvolucrado
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Repository

public class BiometricoDAOImpl extends GenericDaoHibernateImpl<Biometrico, Long> implements BiometricoDAO {

    /* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.involucrado.BiometricoInvolucradoDAO#consultarBiometricoByInvolucrado(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<Biometrico> consultarBiometricoByInvolucrado(
			Long elementoId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT a ")
					.append("FROM Biometrico a ")
					.append("WHERE a.involucrado.elementoId=:elementoId");
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("elementoId", elementoId);
		return query.list();

	}

}
