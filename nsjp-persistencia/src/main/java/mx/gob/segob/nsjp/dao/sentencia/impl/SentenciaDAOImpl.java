/**
* Nombre del Programa : SentenciaDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos de la entidad Sentencia
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
package mx.gob.segob.nsjp.dao.sentencia.impl;

import java.text.Normalizer;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.sentencia.SentenciaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Sentencia;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de metodos de acceso a datos de la entidad Sentencia.
 * @author cesarAgustin
 *
 */
@Repository
public class SentenciaDAOImpl extends
		GenericDaoHibernateImpl<Sentencia, Long> implements SentenciaDAO {

	/**
	 * M&eacute;todo que consulta todos las sentencias
	 * @return Lista de Sentencias
	 * @throws NSJPNegocioException
	 */
    @SuppressWarnings("unchecked")
	public List<Sentencia> consultarSentencias() throws NSJPNegocioException {
        
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append(" FROM Sentencia ");
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        if (pag != null && pag.getCampoOrd() != null) {
            hqlQuery.append(" order by ");
            hqlQuery.append(pag.getCampoOrd());
            hqlQuery.append(" ").append(pag.getDirOrd());
        }
        Query query = super.getSession().createQuery(hqlQuery.toString());
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(pag.getFirstResult());
            if (pag.getRows() != null & pag.getRows() > 0) {
                query.setMaxResults(pag.getRows());
            } else {
                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
            }
        }
        logger.debug("query :: " + query);
        List<Sentencia> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());

        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<Sentencia> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
        return resp;
    }
	
	/**
	 * M&eacute;todo que consulta una Sentencia por id
	 * @return Sentencia
	 * @throws NSJPNegocioException
	 */
	@SuppressWarnings("unchecked")
	public Sentencia consultarSentenciaPorId(Sentencia sentencia)throws NSJPNegocioException{
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append("FROM Sentencia rev ");
        hqlQuery.append(" WHERE Sentencia_id = ").append(sentencia.getSentenciaId()).append(" ");
        Query query = super.getSession().createQuery(hqlQuery.toString());

        logger.debug("query :: " + query);
        List<Sentencia> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());
        if(!resp.isEmpty()){
        	return resp.get(0);
        }
        
        return null;
    }

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.sentencia.SentenciaDAO#consultarSentenciasXEstado(mx.gob.segob.nsjp.model.Sentencia)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Sentencia> consultarSentenciasXEstado(Sentencia sentencia)
			throws NSJPNegocioException {
		if (sentencia == null || sentencia.getNumeroExpediente() == null 
				|| sentencia.getNumeroExpediente().getExpediente() == null
				|| sentencia.getNumeroExpediente().getExpediente().getEstatus() == null
				|| sentencia.getNumeroExpediente().getExpediente().getEstatus().getValorId() == null){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}else{
			StringBuilder hqlQuery = new StringBuilder(" SELECT s ")
											   .append(" FROM Sentencia s ")
											   .append(" WHERE s.numeroExpediente.expediente.estatus.valorId = :estatus ");
			
	        final PaginacionDTO pag = PaginacionThreadHolder.get();
	        if (pag != null && pag.getCampoOrd() != null) {
	            hqlQuery.append(" order by ");
	            hqlQuery.append(pag.getCampoOrd());
	            hqlQuery.append(" ").append(pag.getDirOrd());
	        }
	        Query query = super.getSession().createQuery(hqlQuery.toString());
	        query.setParameter("estatus", sentencia.getNumeroExpediente().getExpediente().getEstatus().getValorId());
	        if (pag != null && pag.getPage() != null) {
	            query.setFirstResult(pag.getFirstResult());
	            if (pag.getRows() != null & pag.getRows() > 0) {
	                query.setMaxResults(pag.getRows());
	            } else {
	                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
	            }
	        }
	        logger.debug("query :: " + query);
	        List<Sentencia> resp = query.list();
	        logger.debug("resp.size() :: " + resp.size());

	        if (pag != null && pag.getPage() != null) {
	            query.setFirstResult(0);
	            query.setMaxResults(-1);
	            final List<Sentencia> temp = query.list();
	            logger.debug("temp.size() :: " + temp.size());
	            pag.setTotalRegistros(new Long(temp.size()));
	            PaginacionThreadHolder.set(pag);
	        }
	        return resp;
		}
	}
	
	/**
	 * M&eacute;todo que consulta una Sentencia por id
	 * @return Sentencia
	 * @throws NSJPNegocioException
	 */
	@SuppressWarnings("unchecked")
	public List<Sentencia> consultarNUS(NombreDemografico nombreDemografico, Boolean esPorCURP)throws NSJPNegocioException{
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append(" SELECT s FROM Sentencia s  ");
        hqlQuery.append(" LEFT JOIN s.involucrado i "); 
//        hqlQuery.append(" JOIN i.persona p ");
        hqlQuery.append(" LEFT JOIN i.nombreDemograficos nd "); 
        hqlQuery.append(" WHERE ");
        if (esPorCURP) {
        	hqlQuery.append(" nd.curp LIKE :CURP ");
        } else {
        	hqlQuery.append(" nd.nombre LIKE :NOMBRE ");
        	hqlQuery.append(" AND nd.apellidoPaterno LIKE :APATERNO ");
            hqlQuery.append(" AND nd.apellidoMaterno LIKE :AMATERNO ");
        	//hqlQuery.append(" COLLATE SQL_LATIN1_GENERAL_CP1_CI_AI ");
        }
        
        Query query = super.getSession().createQuery(hqlQuery.toString());
        
        if (esPorCURP) {
        	query.setString("CURP", nombreDemografico.getCurp());
        }  else {

        	String nombre = Normalizer.normalize(nombreDemografico.getNombre() != null ?  nombreDemografico.getNombre().trim() : "", Normalizer.Form.NFD);
        	nombre = nombre.replaceAll("[^\\p{ASCII}]", "_");
        	String aPaterno = Normalizer.normalize(nombreDemografico.getApellidoPaterno() != null ?  nombreDemografico.getApellidoPaterno().trim() : "", Normalizer.Form.NFD);
        	aPaterno = aPaterno.replaceAll("[^\\p{ASCII}]", "_");
        	String aMaterno = Normalizer.normalize(nombreDemografico.getApellidoMaterno() != null ?  nombreDemografico.getApellidoMaterno().trim() : "", Normalizer.Form.NFD);
        	aPaterno = aPaterno.replaceAll("[^\\p{ASCII}]", "_");

        	query.setString("NOMBRE", nombre);
        	query.setString("APATERNO", aPaterno);
        	query.setString("AMATERNO", aMaterno);
        }
        
        logger.debug("query :: " + query);
        List<Sentencia> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());
        
        return resp;
    }	
	
}