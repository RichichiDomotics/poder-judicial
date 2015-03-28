/**
 * Nombre del Programa : RelNumExpedienteAuditoriaDTO.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 27 09 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DTO para la transferencia de parametros de RelNumExpedienteAuditoria entre la vista y servicios.
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
package mx.gob.segob.nsjp.dto.expediente;

import mx.gob.segob.nsjp.dto.base.GenericDTO;


/**
 * DTO para la transferencia de parametros de RelNumExpedienteAuditoria entre la vista y
 * servicios.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class RelNumExpedienteAuditoriaDTO extends GenericDTO {


    private static final long serialVersionUID = 76743324785091772L;
    
	private Long relNumExpedienteAuditoriaId;
	private ExpedienteDTO numeroExpediente;
	private ExpedienteDTO numeroAuditoria;
	/**
	 * Método de acceso al campo relNumExpedienteAuditoriaId.
	 * @return El valor del campo relNumExpedienteAuditoriaId
	 */
	public Long getRelNumExpedienteAuditoriaId() {
		return relNumExpedienteAuditoriaId;
	}
	/**
	 * Asigna el valor al campo relNumExpedienteAuditoriaId.
	 * @param relNumExpedienteAuditoriaId el valor relNumExpedienteAuditoriaId a asignar
	 */
	public void setRelNumExpedienteAuditoriaId(Long relNumExpedienteAuditoriaId) {
		this.relNumExpedienteAuditoriaId = relNumExpedienteAuditoriaId;
	}
	/**
	 * Método de acceso al campo numeroExpediente.
	 * @return El valor del campo numeroExpediente
	 */
	public ExpedienteDTO getNumeroExpediente() {
		return numeroExpediente;
	}
	/**
	 * Asigna el valor al campo numeroExpediente.
	 * @param numeroExpediente el valor numeroExpediente a asignar
	 */
	public void setNumeroExpediente(ExpedienteDTO numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	/**
	 * Método de acceso al campo numeroAuditoria.
	 * @return El valor del campo numeroAuditoria
	 */
	public ExpedienteDTO getNumeroAuditoria() {
		return numeroAuditoria;
	}
	/**
	 * Asigna el valor al campo numeroAuditoria.
	 * @param numeroAuditoria el valor numeroAuditoria a asignar
	 */
	public void setNumeroAuditoria(ExpedienteDTO numeroAuditoria) {
		this.numeroAuditoria = numeroAuditoria;
	}
}
