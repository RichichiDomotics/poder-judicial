/**
*
* Nombre del Programa : PersonaDAOImpl.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Implementación para el DAO de la entidad Persona                      
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

package mx.gob.segob.nsjp.dao.persona.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.persona.PersonaDAO;
import mx.gob.segob.nsjp.model.Persona;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author CesarAgustin
 * @version 1.0
 *
 */
@Repository
public class PersonaDAOImpl extends GenericDaoHibernateImpl<Persona, Long> implements
		PersonaDAO {

	@SuppressWarnings("unchecked")
	public List<Persona> obtenerPersonasAll() {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("FROM Persona ");				
		Query query = super.getSession().createQuery(queryStr.toString());	
		return query.list();		
	}

}