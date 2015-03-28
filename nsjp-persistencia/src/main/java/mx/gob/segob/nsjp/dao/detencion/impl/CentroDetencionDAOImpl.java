/**
*
* Nombre del Programa : CentroDetencionDAOImpl                                    
* Autor                  : Cuauhtemoc Paredes Serrano                                              
 Compania               : Ultrasist                                                
* Proyecto               : NSJP                    Fecha: 22/09/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Implementacion para la entidad CentronDetencion.                      
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
package mx.gob.segob.nsjp.dao.detencion.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.detencion.CentroDetencionDAO;
import mx.gob.segob.nsjp.model.CentroDetencion;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CentroDetencionDAOImpl extends GenericDaoHibernateImpl<CentroDetencion, Long> 
			implements CentroDetencionDAO{
	
	@Override
	public List<CentroDetencion> consultarCentrosDetencionPorTipo(Long tipoCentroDetencion) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM CentroDetencion cd WHERE ")		
				   .append("cd.tipo.valorId=").append(tipoCentroDetencion);
		Query query = super.getSession().createQuery(queryString.toString());		
		return(List<CentroDetencion>) query.list();
	}
}


