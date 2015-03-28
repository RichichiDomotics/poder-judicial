/**
*
* Nombre del Programa : NombreDemograficoDAOImpl.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Implemenatción para el DAO de la entidad Nombre demografico                      
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
import mx.gob.segob.nsjp.dao.persona.NombreDemograficoDAO;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Persona;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author CesarAgustin
 * @version 1.0
 *
 */
@Repository("nombreDemograficoDAO")
public class NombreDemograficoDAOImpl extends GenericDaoHibernateImpl<NombreDemografico, Long>
		implements NombreDemograficoDAO {

	@SuppressWarnings("unchecked")
	public List<NombreDemografico> consutarNombresByInvolucrado(
			Long involucradoId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT n ")
					.append("FROM NombreDemografico n ")
					.append("WHERE n.persona.elementoId=:involucradoId");
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("involucradoId", involucradoId);
		return query.list();
	}

	@Override
	public NombreDemografico consultarNombreDemograficoByNombreCompleto(
			String nombre) {
		StringBuffer hqlQuery = new StringBuffer();
		hqlQuery.append(" FROM NombreDemografico nd");
		hqlQuery.append(" WHERE nd.nombre+' '+nd.apellidoPaterno+' '+nd.apellidoMaterno");
		hqlQuery.append("like '"+nombre+"'");
		Query query = super.getSession().createQuery(hqlQuery.toString());	
		return (NombreDemografico)query.uniqueResult();
	}

	@Override
	public Persona obtenerPersonaXDemografico(Long idDemografico) {
		StringBuffer hqlQuery = new StringBuffer();
		
		hqlQuery.append("SELECT nd.persona FROM NombreDemografico nd");
		hqlQuery.append(" WHERE nd.nombreDemograficoId="+idDemografico);
		
		Query query = super.getSession().createQuery(hqlQuery.toString());
		return (Persona) query.uniqueResult();
	}

	

}
