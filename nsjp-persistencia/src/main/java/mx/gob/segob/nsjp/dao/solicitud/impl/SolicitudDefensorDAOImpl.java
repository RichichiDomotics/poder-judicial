/**
* Nombre del Programa : SolicitudDefensorDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacio de metodos de acceso a datos para la entidad SolicitudDefensor
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
package mx.gob.segob.nsjp.dao.solicitud.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDefensorDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.SolicitudDefensor;

import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacio de metodos de acceso a datos para la entidad SolicitudDefensor.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class SolicitudDefensorDAOImpl extends
		GenericDaoHibernateImpl<SolicitudDefensor, Long> implements
		SolicitudDefensorDAO {
	
	@Override
    public Long obtenerUltimoNumero() {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("select isnull(MAX(d.documentoId),0)" +
        		"		from Documento d");
        Query qry = super.getSession().createQuery(queryStr.toString());
        return (Long) qry.uniqueResult();
    }
	
	@SuppressWarnings("unchecked")
	@Override
	
	public List<SolicitudDefensor> obtenerSolicitudesDefensoriaPorEstatus(
            Long estatus, Instituciones institucion) {

        final StringBuffer query = new StringBuffer();

        query.append(" from SolicitudDefensor s");
        query.append(" where  s.tipoSolicitud = ").append(
                TiposSolicitudes.DEFENSOR.getValorId());
        if (estatus != null) {
            query.append(" and s.estatus = ");
            query.append(estatus);
        } else {
            query.append(" and s.estatus is null ");
        }
        if (institucion != null) {
            query.append(" and s.confInstitucion.confInstitucionId = ");
            query.append(institucion.getValorId());
        }
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            query.append(" ORDER BY ");
            int orden = NumberUtils.toInt(pag.getCampoOrd(), 0);
            switch (orden) {
                case 2002 : // Numero General de Caso
                    query.append(" s.numeroCasoAsociado");
                    break;
                case 2003 : // Expediente
                    query.append(" s.numeroExpediente.expediente.numeroExpediente");
                    break;
                case 2009 : // Imputado FIXME DAJV Ruta de involucrado con
                            // calidad de probable responsable
                    query.append(" s.detenido");
                    break;
                case 2013 : // Fecha hora solicitud
                    query.append(" s.fechaCreacion");
                    break;
                // FIXME DAJV poner la ruta de ordenamientos
                default :
                    query.append(" s.documentoId");
                    break;
            }
            query.append(" " + pag.getDirOrd());
        }

        return super.ejecutarQueryPaginado(query, pag);
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudDefensor> obtenerSolicitudesAsesoriaDefensoriaPorEstatus(
			Long estatus,Instituciones institucion) {
	
		final StringBuffer query = new StringBuffer();
		
		query.append(" from SolicitudDefensor s");
		query.append("  where s.estatus = ");
		query.append(estatus);
		query.append(" and s.confInstitucion.confInstitucionId = ");
		query.append(institucion.getValorId());		
		query.append(" and s.tipoSolicitud = "+TiposSolicitudes.ASESORIA_DEFENSORIA.getValorId());
		
		final PaginacionDTO pag = PaginacionThreadHolder.get();
	    logger.debug("pag :: " + pag);
	    if(pag!=null && pag.getCampoOrd() != null){
	    	query.append(" ORDER BY ");
	    	int orden = NumberUtils.toInt(pag.getCampoOrd(), 0);
	    	switch(orden){
		    	case 2003: //Expediente FIXME DAJV no invierte el orden poner la ruta correcta
		    		query.append(" s.numeroExpediente.expediente.numeroExpediente");
		    		break;
		    	case 2016: //Interesado  FIXME DAJV Ruta de involucrado con calidad de solicitante
		    		query.append(" s.detenido");
		    		break;
		    	case 2013: //Fecha hora solicitud
		    		query.append(" s.fechaCreacion");
		    		break;
		    	//FIXME DAJV poner la ruta de ordenamientos
		    	default:
		    		query.append(" s.documentoId");
		    		break;
	    	}
	    	query.append(" "+pag.getDirOrd());
	    }
	    
	    return super.ejecutarQueryPaginado(query, pag);		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SolicitudDefensor> consultarSolDefensorAsignadas(Long idUsuario) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM SolicitudDefensor sd ")
					.append("WHERE sd.destinatario=").append(idUsuario)
					.append(" AND sd.estatus=").append(EstatusSolicitud.CERRADA.getValorId());
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SolicitudDefensor> consultarSolicitudesDefensoriaByHistoricoYEstatus(
			Date fechaHistorico, Long estatusSolicitud) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM SolicitudDefensor sd ")
					.append(" WHERE sd.estatus.valorId=").append(estatusSolicitud)
					.append(" AND CONVERT (nvarchar, sd.fechaCreacion, 112)>=").append(DateUtils.formatearBD(fechaHistorico));
		
		return getHibernateTemplate().find(queryString.toString());
	}

	@Override
	public SolicitudDefensor obtenerSolDfensorByExpedienteYFuncionario(
			Long expedienteId, Long claveFuncionario) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM SolicitudDefensor sd WHERE ")
					.append(" sd.destinatario=").append(claveFuncionario)
					.append(" AND sd.numeroExpediente=").append(expedienteId);
		Query query = super.getSession().createQuery(queryString.toString());
		return (SolicitudDefensor) query.uniqueResult();
	}

	
	@Override
	public SolicitudDefensor consultarSolicituDefensorPorNumeroExpedienteId(
			Long numeroExpedienteId) {
		StringBuffer hqlQuery = new StringBuffer();
		
		hqlQuery.append("FROM SolicitudDefensor sd ")
				.append(" WHERE sd.numeroExpediente=").append(numeroExpedienteId);
		
		Query query = super.getSession().createQuery(hqlQuery.toString());
		return (SolicitudDefensor) query.uniqueResult();
	}


	@Override
	public SolicitudDefensor obtenerSolicitudByIdyYipo(Long documentoId,
			Long idCampo) {
		StringBuffer hqlQuery = new StringBuffer();
		
		hqlQuery.append("FROM SolicitudDefensor s  ");
		hqlQuery.append(" WHERE s.documentoId =").append(documentoId);
		if(idCampo != null){
			hqlQuery.append(" AND s.tipoSolicitud = ").append(idCampo);
		}
		
		Query query = super.getSession().createQuery(hqlQuery.toString());
		return (SolicitudDefensor) query.uniqueResult();
	}



}
