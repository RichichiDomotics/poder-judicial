/**
 * Nombre del Programa : DocumentoDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 May 2011
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

import java.util.Calendar;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CatCurso;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Usuario;

import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class DocumentoDAOImpl extends GenericDaoHibernateImpl<Documento, Long>
        implements
            DocumentoDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<Documento> consultarDocumentoPorExpediente(Long expedienteId) {

        StringBuffer queryString = new StringBuffer();
        queryString.append("SELECT d ")
                .append("FROM Documento d LEFT JOIN d.actividad da ")
                .append("WHERE da.expediente=").append(expedienteId)
                .append(" AND da.documento is not null ");
//                .append("AND d.archivoDigital is not null");

        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            queryString.append(" ORDER BY ");
            int orden = NumberUtils.toInt(pag.getCampoOrd(), 0);
            switch (orden) {
                case 2019 : // Area FIXME DAJV poner la ruta del area a la que
                            // hace referencia el documento
                    queryString.append(" da");
                    break;
                case 2020 : // Fecha actividad
                    queryString.append(" da.fechaCreacion");
                    break;
                case 2021 : // Nombre de la actividad
                    queryString.append(" da.tipoActividad.valor");
                    break;
                case 2022 : // Tipo del documento
                    queryString.append(" d.tipoDocumento.valor");
                    break;
                case 2023 : // Nombre del documento
                    queryString.append(" d.nombreDocumento");
                    break;
                case 2024 : // Fecha del documento
                    queryString.append(" d.fechaCreacion");
                    break;
                // FIXME DAJV poner la ruta de ordenamientos
                default :
                    queryString.append(" d.documentoId");
                    break;
            }
            queryString.append(" " + pag.getDirOrd());
        }

        return super.ejecutarQueryPaginado(queryString, pag);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Documento> consultarDocumentosXExpedienteYTipoDocumento(
            Long numExpId, Long tipoDocumento) {
        StringBuffer queryString = new StringBuffer();

        queryString
                .append("SELECT new Documento(d.documentoId, d.nombreDocumento,e.expedienteId, ne.numeroExpediente, e.caso.casoId, e.caso.numeroGeneralCaso, d.tipoDocumento.valorId, d.tipoDocumento.valor, d.fechaCreacion, d.folioDocumento, d.descripcion)")
                .append(" FROM Documento d LEFT JOIN d.actividad da")
                .append(" LEFT JOIN da.expediente e")
                .append(" LEFT JOIN e.numeroExpedientes ne")
                .append(" WHERE 1=1");
        if (numExpId != null) {
            queryString.append(" AND ne.numeroExpedienteId=").append(numExpId);
        }
        queryString.append(" AND da.documento is not null ")
                .append(" AND d.archivoDigital is not null")
                .append(" AND d.tipoDocumento=").append(tipoDocumento);
        logger.info("\n\r/***** " + queryString.toString());

        final PaginacionDTO pag = PaginacionThreadHolder.get();

		logger.debug("pag :: " + pag);
		if (pag != null && pag.getCampoOrd() != null) {
			if (pag.getCampoOrd().equals("1")) {
				queryString.append(" order by ");
				queryString.append("d.folioDocumento");
				queryString.append(" ").append(pag.getDirOrd());
			}
		}
		return super.ejecutarQueryPaginado(queryString, pag);   

    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<Documento> consultarDocumentosPorUsuario(Usuario usuario,
            Long tipoDocumento) {
        if (logger.isDebugEnabled()) {
            logger.debug("ConsultarDocumentosPorUsuario = " + usuario);
        }
        Funcionario funcionario = usuario.getFuncionario();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT docs FROM Documento docs ")
                .append("WHERE docs.responsableDocumento = ")
                .append(funcionario.getClaveFuncionario())
                .append("AND docs.tipoDocumento = ").append(tipoDocumento);
        Query query = super.getSession().createQuery(sb.toString());
        List list = query.list();
        if (logger.isDebugEnabled()) {
            logger.debug("list.size() = " + list.size());
        }
        return list;
    }

    @Override
    public Documento consultarDocumentoXExpediente(Expediente expediente,
            Long tipoDocumento) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT d FROM Documento d ")
                .append("INNER JOIN d.actividad da ")
                .append("INNER JOIN da.expediente e ")
                .append("INNER JOIN e.numeroExpedientes ne ")
                .append("WHERE ne.numeroExpediente = :numeroExpediente ")
                .append("AND d.tipoDocumento = ").append(tipoDocumento);
        Query query = super.getSession().createQuery(sb.toString());
        query.setParameter("numeroExpediente", expediente.getNumeroExpediente());
        return (Documento) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Documento> consultarDocumentosPorNumeroExpedienteId(
            Long numeroExpedienteId) {

        // StringBuilder sb = new StringBuilder();
        // sb.append("SELECT d FROM Documento d ")
        // .append("INNER JOIN d.actividad da ")
        // .append("INNER JOIN da.expediente e ")
        // .append("INNER JOIN e.numeroExpedientes ne ")
        // .append("WHERE ne.numeroExpedienteId = ? ");
        // return getHibernateTemplate().find(sb.toString(),
        // numeroExpedienteId);

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT d FROM Documento d ")
                .append("INNER JOIN d.actividad da ")
                .append("INNER JOIN da.expediente e ")
                .append("INNER JOIN e.numeroExpedientes ne ")
                .append("WHERE ne.numeroExpedienteId =")
                .append(numeroExpedienteId);

        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        return super.ejecutarQueryPaginado(sb, pag);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Documento> consultarDocumentosAudienciaByTipoForma(
            Long audienciaId, Long tipoForma) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("SELECT d FROM Documento d ")
                .append("WHERE d.resolutivo.audiencia=").append(audienciaId)
                .append(" AND d.forma.tipoForma=").append(tipoForma);
        Query query = super.getSession().createQuery(queryString.toString());
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Documento consultarDocumentoPorDocumentoIdLigero(Long documentoId) {

        StringBuffer queryString = new StringBuffer();
        queryString
                .append("SELECT new Documento(d.documentoId, d.nombreDocumento, d.forma.formaId, d.tipoDocumento.valorId, d.tipoDocumento.valor, d.fechaCreacion) ");

        queryString.append("FROM Documento d ").append("WHERE d.documentoId=")
                .append(documentoId);

        logger.info("/***** " + queryString.toString());
        Query query = super.getSession().createQuery(queryString.toString());
        List<Documento> temp = query.list();
        if (temp != null && !temp.isEmpty()) {
            return temp.get(0);
        }
        return null;
    }

    @Override
    public Documento consultarDocumentoPorId(Long documentoId) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("FROM Documento d ").append("WHERE d.documentoId=")
                .append(documentoId);

        Query query = super.getSession().createQuery(queryString.toString());
        return (Documento) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Documento> consultarDocumentosXTipoDocumento(
            Long idTipoDocumento) {
        StringBuffer queryString = new StringBuffer();

        queryString
                .append("SELECT new Documento(d.documentoId, d.tipoDocumento,")
                .append(" d.archivoDigital, d.nombreDocumento,")
                .append(" d.fechaCreacion, d.responsableDocumento,")
                .append(" d.actividad, d.esEnviado)");
        queryString.append(" FROM Documento d");
        queryString.append(" WHERE d.tipoDocumento=" + idTipoDocumento);

        Query query = super.getSession().createQuery(queryString.toString());
        return query.list();
    }

    @Override
    public String obtenerUltimoFolioDocumento() throws NSJPNegocioException {
        final StringBuffer query = new StringBuffer();
        query.append("SELECT MAX(d.folioDocumento) ");
        query.append("FROM Documento d ");
        query.append("WHERE d.confInstitucion.esInstalacionActual = true  ");
        query.append("and d.folioDocumento like '%"
                + Calendar.getInstance().get(Calendar.YEAR) + "%'");
        logger.debug("query :: " + query);
        Query hbq = super.getSession().createQuery(query.toString());
        return (String) hbq.uniqueResult();
    }

    @Override
    public List<Documento> consultarDocumentosByExpedienteIdYForma(
            Long expedienteId, Formas tipoforma) {

        StringBuffer queryString = new StringBuffer();

        queryString.append("SELECT new Documento(d.documentoId)")
                .append(" FROM Documento d LEFT JOIN d.actividad da")
                .append(" LEFT JOIN da.expediente e").append(" WHERE 1=1");
        if (expedienteId != null) {
            queryString.append(" AND e.expedienteId=").append(expedienteId);
        }
        if (tipoforma != null) {
            queryString.append(" AND d.forma.formaId=").append(
                    tipoforma.getValorId());
        }
        queryString.append(" AND da.documento is not null ").append(
                " AND d.archivoDigital is not null");
        logger.info("queryString :: " + queryString.toString());

        Query query = super.getSession().createQuery(queryString.toString());
        return query.list();

    }

    @Override
    @SuppressWarnings("unchecked")
    public Documento consultarDocumentoPorArchivoDigital(Long archivoDigitalId){

        StringBuffer queryString = new StringBuffer();
        //queryString.append("SELECT d ")
          //      .append("FROM Documento d LEFT JOIN d.actividad da")
            //    .append(" WHERE da.documento is not null ")                
              //  .append("AND d.archivoDigital=").append(archivoDigitalId);                                
     
        
        queryString.append("SELECT d ")
            .append("FROM Documento d" )
            .append(" INNER JOIN d.actividad da ")
            .append(" INNER JOIN d.archivoDigital dd ")            
            .append(" WHERE da.documento is not null ")                      
            .append("AND d.archivoDigital=").append(archivoDigitalId);                                

        Query query = super.getSession().createQuery(queryString.toString());
        return (Documento)query.uniqueResult();

    }



    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<Documento> consultarDocumentosReinsercionSocial(Funcionario funcionario, 
    		Documento documento,
    		NumeroExpediente numeroExpediente)  throws NSJPNegocioException {
        if (logger.isDebugEnabled()) {
            logger.debug("ConsultarDocumentosPorUsuario = " + funcionario);
        }
        
        StringBuilder  strQuery = new StringBuilder();
        strQuery.append(" SELECT docs FROM Documento docs ")
                 .append(" INNER JOIN docs.actividad da ")
                .append(" INNER JOIN da.expediente e ")
                .append(" INNER JOIN e.numeroExpedientes ne ")
                .append(" WHERE docs.responsableDocumento = ")
                .append(" " + funcionario.getClaveFuncionario() + " ")
                .append(" AND ne.numeroExpedienteId = ")
                .append(" " + numeroExpediente.getNumeroExpedienteId() + " ")
                .append(" AND docs.documentoId NOT IN ( ")
                .append(" SELECT sol.documentoId FROM Solicitud sol ")
                .append(" ) ");
                //.append("AND docs.tipoDocumento = ").append(documento.getTipoDocumento());
                
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        if (pag != null && pag.getCampoOrd() != null && !pag.getCampoOrd().isEmpty()) {
        	strQuery.append(" ORDER BY ");
        	strQuery.append(pag.getCampoOrd());
        	strQuery.append(" ").append(pag.getDirOrd());
        }

        Query query = super.getSession().createQuery(strQuery.toString());
        
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(pag.getFirstResult());
            if (pag.getRows() != null & pag.getRows() > 0) {
                query.setMaxResults(pag.getRows());
            } else {
                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
            }
        }
        logger.debug("query :: " + query);
        List<Documento> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());

        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<Documento> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros((long)temp.size());
            PaginacionThreadHolder.set(pag);
        }
        return resp;

    }
}
