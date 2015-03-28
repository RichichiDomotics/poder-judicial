/**
* Nombre del Programa : RelNumExpedienteAuditoriaDAOImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/09/2011
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
package mx.gob.segob.nsjp.dao.expediente.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.expediente.RelNumExpedienteAuditoriaDAO;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RelNumExpedienteAuditoria;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación para el DAO de de la Relación de Número Expediente Auditado con  
 * Número de Auditoria.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Repository("relNumExpedienteAuditoriaDAO")
public class RelNumExpedienteAuditoriaDAOImpl extends GenericDaoHibernateImpl<RelNumExpedienteAuditoria, Long> 
	implements RelNumExpedienteAuditoriaDAO{
	
	@Override
	public RelNumExpedienteAuditoria buscarRelacionDeNumExpPorIdAuditoria(Long idAuditoria) {
		logger.debug("idNumExpediente :: [" + idAuditoria +"]:: ");
		
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append(" FROM RelNumExpedienteAuditoria r");
		queryStr.append(" WHERE r.numeroAuditoriaId = ");
		queryStr.append(" :idAuditoria ");		
		
		Query query = super.getSession().createQuery(queryStr.toString());
		query.setParameter("idAuditoria", idAuditoria);
		return (RelNumExpedienteAuditoria) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<RelNumExpedienteAuditoria> consultarRelNumeroExpedienteAuditoriaPorFiltro(RelNumExpedienteAuditoria filtro, 
			 NumeroExpediente numExpedienteAuditado, NumeroExpediente numCarpetaAuditoria){
		logger.info("consultarNumeroAuditoriaPorFiltro:"+filtro + " ExpedienteAuditado: "+ numExpedienteAuditado +
				" CarpetaAuditoria:" + numCarpetaAuditoria);
		if(numExpedienteAuditado!= null && numExpedienteAuditado.getEstatus()!= null )
			logger.info(" estatus: "+numExpedienteAuditado.getEstatus() );
			
		final StringBuffer query = new StringBuffer();
        query.append(" SELECT RNE FROM RelNumExpedienteAuditoria RNE, NumeroExpediente NEX");
        query.append(" WHERE  RNE.numeroAuditoriaId = NEX.numeroExpedienteId ");
        
        if(filtro!= null && filtro.getRelNumExpedienteAuditoriaId()!= null)
        	query.append(" AND RNE.relNumExpedienteAuditoriaId = " + filtro.getRelNumExpedienteAuditoriaId());
        
        if(filtro!= null && filtro.getNumeroAuditoriaId()!= null )
        	query.append(" AND RNE.numeroAuditoriaId = " + filtro.getNumeroAuditoriaId());
        
        //Para Numero Expediente Auditado
        if(numExpedienteAuditado!= null && numExpedienteAuditado.getNumeroExpedienteId()!=null)
        	query.append(" AND RNE.numeroExpediente.numeroExpedienteId = " + numExpedienteAuditado.getNumeroExpedienteId());
        if(numExpedienteAuditado!= null && numExpedienteAuditado.getJerarquiaOrganizacional()!=null
        		&& numExpedienteAuditado.getJerarquiaOrganizacional().getJerarquiaOrganizacionalId()!= null)
        	query.append(" AND RNE.numeroExpediente.jerarquiaOrganizacional.jerarquiaOrganizacionalId = " + 
        			numExpedienteAuditado.getJerarquiaOrganizacional().getJerarquiaOrganizacionalId());
        if(numExpedienteAuditado!= null && numExpedienteAuditado.getFuncionario()!=null
        		&& numExpedienteAuditado.getFuncionario().getClaveFuncionario()!=null)
        	query.append(" AND RNE.numeroExpediente.funcionario.claveFuncionario = " + numExpedienteAuditado.getFuncionario().getClaveFuncionario());
        
        /************************************************************************************************/
        //Para agencias 
        if(numExpedienteAuditado!= null && numExpedienteAuditado.getFuncionario() != null && numExpedienteAuditado.getFuncionario().getDiscriminante() !=null && 
        		numExpedienteAuditado.getFuncionario().getDiscriminante().getCatDiscriminanteId() != null && 
        		numExpedienteAuditado.getFuncionario().getDiscriminante().getCatDiscriminanteId()>0)
        	query.append(" AND RNE.numeroExpediente.funcionario.discriminante.catDiscriminanteId = " + numExpedienteAuditado.getFuncionario().getDiscriminante().getCatDiscriminanteId());
        /************************************************************************************************/
        
        if(numExpedienteAuditado!= null && numExpedienteAuditado.getEstatus()!= null && 
        		numExpedienteAuditado.getEstatus().getValorId()!= null)
        	query.append(" AND RNE.numeroExpediente.estatus.valorId = " + numExpedienteAuditado.getEstatus().getValorId());
        
        //Para Numero Carpeta de Auditoria
        if(numCarpetaAuditoria!= null && numCarpetaAuditoria.getNumeroExpedienteId()!=null)
        	query.append(" AND NEX.numeroExpedienteId = " + numCarpetaAuditoria.getNumeroExpedienteId());
        if(numCarpetaAuditoria!= null && numCarpetaAuditoria.getJerarquiaOrganizacional()!=null
        		&& numCarpetaAuditoria.getJerarquiaOrganizacional().getJerarquiaOrganizacionalId()!= null)
        	query.append(" AND NEX.jerarquiaOrganizacional.jerarquiaOrganizacionalId = " + 
        			numCarpetaAuditoria.getJerarquiaOrganizacional().getJerarquiaOrganizacionalId());
        if(numCarpetaAuditoria!= null && numCarpetaAuditoria.getFuncionario()!=null
        		&& numCarpetaAuditoria.getFuncionario().getClaveFuncionario()!=null)
        	query.append(" AND NEX.funcionario.claveFuncionario = " + numCarpetaAuditoria.getFuncionario().getClaveFuncionario());
        /************************************************************************************************/
        //Para agencias
        if( numCarpetaAuditoria.getFuncionario()!=null  && numCarpetaAuditoria.getFuncionario().getDiscriminante()!=null )
        	query.append(" AND NEX.funcionario.discriminante.catDiscriminanteId  = " + numCarpetaAuditoria.getFuncionario().getDiscriminante().getCatDiscriminanteId());        
        /************************************************************************************************/
        if(numCarpetaAuditoria!= null && numCarpetaAuditoria.getEstatus()!= null && 
        		numCarpetaAuditoria.getEstatus().getValorId()!= null)
        	query.append(" AND NEX.estatus.valorId = " + numCarpetaAuditoria.getEstatus().getValorId());
        
        logger.debug("query :: " + query);
		Query hbq = super.getSession().createQuery(query.toString());

		return hbq.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RelNumExpedienteAuditoria> consultarNumeroVisitaduriaUIE(
			Long idUIE, Long idEstatus) {
		
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM RelNumExpedienteAuditoria rn ")
					.append("WHERE rn.numeroExpediente.estatus=").append(idEstatus)
					.append(" AND rn.numeroExpediente.funcionario.unidadIEspecializada=").append(idUIE);
		Query query = super.getSession().createQuery(queryString.toString());
		
		return query.list();
	}
	
}
