/**
* Nombre del Programa : AvisoDetencionDAOImpl.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dao.avisodetencion.impl;


import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.avisodetencion.AvisoDetencionDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.AvisoDetencion;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author Tattva-IT
 *
 */

@Repository
public class AvisoDetencionDAOImpl extends GenericDaoHibernateImpl<AvisoDetencion, Long>
			implements AvisoDetencionDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<AvisoDetencion> obtenerAvisosDetencionPorEstatus(Long estatus) {
		
		final StringBuffer query = new StringBuffer();
		
		query.append("FROM  AvisoDetencion ad ")
				.append("WHERE  ad.estatus=").append(estatus); 			
			
		final PaginacionDTO pag = PaginacionThreadHolder.get();
	    logger.debug("pag :: " + pag);
	    if(pag!=null && pag.getCampoOrd() != null){
	    	query.append(" ORDER BY ");
	    	int orden = NumberUtils.toInt(pag.getCampoOrd(), 0);
	    	switch(orden){
		    	case 2002: //Numero General de Caso
		    		query.append(" ad.expediente.caso.numeroGeneralCaso");
		    		break;
		    	case 2009: // Imputado
		    		query.append(" ad.detenido");
		    		break;
		    	case 2012: // Fecha hora aviso
		    		query.append(" ad.fechaCreacion");
		    		break;
		    	case 2011: // Fecha hora detencion
		    		query.append(" ad.detencion.fechaFinDetencion");
		    		break;
		       		//FIXME DAJV poner la ruta de ordenamientos
		    	default:
		    		query.append(" ad.documentoId");
		    		break;
	    	}
	    	query.append(" "+pag.getDirOrd());
	    }
	    
	    return super.ejecutarQueryPaginado(query, pag);		

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AvisoDetencion> consultarAvisosDetencionHistoricoByEstatus(
			Date fechHistorico, Long estatusAviso) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM AvisoDetencion ad WHERE ")
					.append("CONVERT (nvarchar, ad.fechaCreacion, 112)>=")
					.append(DateUtils.formatearBD(fechHistorico)).append(" AND ad.estatus=")
					.append(estatusAviso);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}
	
	public List<AvisoDetencion> consultarAvisosDetencionPorExpediente(Long expedienteId) {

        
        final StringBuffer queryStr = new StringBuffer();
        
        queryStr.append("FROM  AvisoDetencion ad ")
                .append("WHERE  ad.expediente.expedienteId=").append(expedienteId);          
            
        final Query query = super.getSession().createQuery(queryStr.toString());
        return query.list();     

	    
	}
     
}
