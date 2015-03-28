/**
 * Nombre del Programa : MandamientoDAOImpl.java
 * Autor                            : Emigdio
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 23/08/2011
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
package mx.gob.segob.nsjp.dao.documento.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.TipoExpediente;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.documento.MandamientoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Mandamiento;
import mx.gob.segob.nsjp.model.NumeroExpediente;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación del DAO para el acceso a los mandamientos
 * 
 * @version 1.0
 * @author Emigdio
 * 
 */
@Repository
public class MandamientoDAOImpl
        extends
            GenericDaoHibernateImpl<Mandamiento, Long>
        implements
            MandamientoDAO {
    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.dao.documento.MandamientoDAO#
     * consultarMandamientosPorNumeroExpediente(java.lang.String)
     */
	@SuppressWarnings("unchecked")
    @Override
    public List<Mandamiento> consultarMandamientosPorNumeroExpediente(
            String numeroExpediente,Long discriminanteId) {
		
		logger.info("Numero Expediente: " + numeroExpediente);
		
		 return getHibernateTemplate()
	                .find("from Mandamiento m where"
	                        + " m.resolutivo.audiencia.numeroExpediente.numeroExpediente = ? "
	                        + " and m.resolutivo.audiencia.numeroExpediente.expediente.discriminante.catDiscriminanteId = ? "
	                        + " order by m.resolutivo.audiencia.fechaAudiencia desc",
	                        numeroExpediente,discriminanteId);
		 
		 
//		final PaginacionDTO pag = PaginacionThreadHolder.get();
//        logger.debug("pag :: " + pag);
//        
//        StringBuffer queryString = new StringBuffer();
//
//        queryString.append("from Mandamiento m where "
//                        + "m.resolutivo.audiencia.numeroExpediente.numeroExpediente = " + numeroExpediente
//                        + "order by m.resolutivo.audiencia.fechaAudiencia desc");
//        
//      Query query = super.getSession().createQuery(queryString.toString());  
//       return query.list();  
//        return super.ejecutarQueryPaginado(queryString, pag);
        
    }

	@SuppressWarnings("unchecked")
    @Override
    public List<Mandamiento> consultarMandamientoPorFiltro(
			Mandamiento mandamiento, String numeroExpediente) {
		
		StringBuffer queryString = new StringBuffer();
        queryString.append("SELECT m from Mandamiento m where 1=1");

        String fecha;
        
        if(numeroExpediente != null && numeroExpediente!="")
			queryString.append(" and m.resolutivo.audiencia.numeroExpediente.numeroExpediente ='").append(numeroExpediente).append("'");			
		
        if(mandamiento!=null){
        	fecha= DateUtils.formatear(mandamiento.getFechaInicial());
        	//fecha= DateUtils.formatearBD(mandamiento.getFechaInicial());               
        	if(mandamiento.getFechaInicial()!= null)
        		//queryString.append(" and CONVERT (nvarchar, m.fechaInicial, 112) >=  '").append(fecha).append("'");
        		queryString.append(" and CONVERT (nvarchar, m.fechaInicial, 103) >=  '").append(fecha).append("'");
		
        	fecha= DateUtils.formatear(mandamiento.getFechaFinal());
        	//fecha= DateUtils.formatearBD(mandamiento.getFechaFinal());        
        	if(mandamiento.getFechaFinal()!= null)
        		//queryString.append(" and CONVERT (nvarchar, m.fechaFinal, 112) <=  '").append(fecha).append("'");
        		queryString.append(" and CONVERT (nvarchar, m.fechaFinal, 103) <=  '").append(fecha).append("'");				
		
        	if(mandamiento.getEstatus()!=null)
        		queryString.append(" and m.estatus.valorId =").append(mandamiento.getEstatus().getValorId());			
        }

        logger.info(queryString);
		
        Query query = super.getSession().createQuery(queryString.toString());
        return query.list();
	}


    @Override
    public Mandamiento obtenerMandamientoPorFolioDoc(String folioDocumento) {

        StringBuffer queryString = new StringBuffer();
        queryString.append(" FROM Mandamiento m  ")
                .append(" WHERE m.folioDocumento = '").append(folioDocumento)
                .append("'");
        Query query = super.getSession().createQuery(queryString.toString());
        return (Mandamiento) query.uniqueResult();
    }

}
