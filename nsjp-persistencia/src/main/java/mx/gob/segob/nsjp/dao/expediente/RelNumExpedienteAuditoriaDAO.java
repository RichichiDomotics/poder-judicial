/**
* Nombre del Programa : RelNumExpedienteAuditoriaDAO.java
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
package mx.gob.segob.nsjp.dao.expediente;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RelNumExpedienteAuditoria;

/**
 * Interfaz para el Acceso a BD de la Relación de Número Expediente Auditado con  
 * Número de Auditoria.
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface RelNumExpedienteAuditoriaDAO  extends GenericDao<RelNumExpedienteAuditoria, Long> {

	
	/**
	 * Permite consultar RelNumExpedienteAuditoria en base a un numero de auditoria
	 * @param idAuditoria: Identificador del identificador del numero de auditoria (Numero de expediente)
	 * @return RelNumExpedienteAuditoria
	 */
	public RelNumExpedienteAuditoria buscarRelacionDeNumExpPorIdAuditoria(Long idAuditoria);


	
	
	/**
	 * Permite consultar RelNumExpedienteAuditoria de acuerdo al filtro, para:
	 * ** RelNumExpedienteAuditoria
	 *   -relNumExpedienteAuditoriaId  Id de la relación.
	 *   -numeroAuditoriaId   Id del nuevo número de expediente generado por la auditoria.
	 *   
	 * ** Numero Expediente Auditado (numExpedienteAuditado)
	 *   -numeroExpedienteId
	 *   -jerarquiaOrganizacional.jerarquiaOrganizacionalId
	 *   -funcionario.claveFuncionario
	 *   -estatus.valorId
	 *   
	 * ** Numero Carpeta de Auditoria ()
	 *   -numeroExpedienteId
	 *   --jerarquiaOrganizacional.jerarquiaOrganizacionalId
	 *   -funcionario.claveFuncionario
	 *   -estatus.valorId
	 * 
	 * @param filtro
	 * @param numExpedienteAuditado
	 * @param numCarpetaAuditoria
	 * @return
	 */
	List<RelNumExpedienteAuditoria> consultarRelNumeroExpedienteAuditoriaPorFiltro(RelNumExpedienteAuditoria filtro, 
			 NumeroExpediente numExpedienteAuditado, NumeroExpediente numCarpetaAuditoria);



	/**
	 * 
	 * @param idJerOrg
	 * @param idEstats
	 * @return
	 */
	public List<RelNumExpedienteAuditoria> consultarNumeroVisitaduriaUIE(
			Long idUIE, Long idEstatus);
	
	
}
