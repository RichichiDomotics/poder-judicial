/**
* Nombre del Programa : ConsultarBandejaVisitaduriaService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/09/2011
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
package mx.gob.segob.nsjp.service.expediente;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.RelNumExpedienteAuditoriaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Servicios utilizados para hacer consulta de los Números de Expedientes
 * Auditados. Es decir, la Bandeja de Visitaduría. 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface ConsultarBandejaVisitaduriaService {

	/**
	 * Servicio que permite la consulta de Numeros de Visitaduria de acuerdo a:
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
	 * ** Numero Carpeta de Auditoria 
	 *   -numeroExpedienteId
	 *   --jerarquiaOrganizacional.jerarquiaOrganizacionalId
	 *   -funcionario.claveFuncionario
	 *   -estatus.valorId
	 *  
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<RelNumExpedienteAuditoriaDTO> consultarNumeroVisitaduriaPorFiltro(RelNumExpedienteAuditoriaDTO filtro )  throws NSJPNegocioException;
	
	/**
	 * Servicio que permite consultar los funcionarios, visitadores, por
	 * -Departamento del Expediente Auditado 
	 * -Estatus del expediente de Carpeta de Auditoria
	 *  
	 * @param idEstatus
	 * @param idDepartamento
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<FuncionarioDTO> consultarFuncionariosPorEstatusDeparamento(Long idEstatus, Long idDepartamento) 
		throws NSJPNegocioException;

	/**
	 * @param relNumExpedienteAuditoriaDTO
	 * 		*relNumExpedienteAuditoriaDTO.numeroExpediente.Area area de la cual se requieren los num expedientes 
	 * 		*relNumExpedienteAuditoriaDTO.numeroExpediente.Estatus estatus en el cual deben estar los num expedientes
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<RelNumExpedienteAuditoriaDTO> consultarNumeroVisitaduriaUIE(
			RelNumExpedienteAuditoriaDTO relNumExpedienteAuditoriaDTO) throws NSJPNegocioException;
}
