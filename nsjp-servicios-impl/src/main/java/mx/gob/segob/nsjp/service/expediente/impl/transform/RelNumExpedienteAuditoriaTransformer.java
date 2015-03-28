/**
 * Nombre del Programa : RelNumExpedienteAuditoria.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 27 09 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Clase para convertir objetos RelNumExpedienteAuditoria a RelNumExpedienteAuditoriaDTO y viceversa
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
package mx.gob.segob.nsjp.service.expediente.impl.transform;

import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.RelNumExpedienteAuditoriaDTO;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RelNumExpedienteAuditoria;

/**
 * Clase para convertir objetos RelNumExpedienteAuditoria a RelNumExpedienteAuditoriaDTO y viceversa.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class RelNumExpedienteAuditoriaTransformer {

	/**
	 * Convierte un objeto RelNumExpedienteAuditoria a un objeto RelNumExpedienteAuditoriaDTO.
	 * 
	 * @param expediente
	 * @return objeto ExpedienteDTO
	 */
	public static RelNumExpedienteAuditoriaDTO transformar(RelNumExpedienteAuditoria entidad) {
		if (entidad == null) {
			return null;
		}
		RelNumExpedienteAuditoriaDTO dto = new RelNumExpedienteAuditoriaDTO();

		dto.setRelNumExpedienteAuditoriaId(entidad.getRelNumExpedienteAuditoriaId());
		dto.setNumeroExpediente(ExpedienteTransformer.transformarExpediente(entidad.getNumeroExpediente()));
		dto.setNumeroAuditoria(new ExpedienteDTO(entidad.getNumeroAuditoriaId()));	
		
		return dto;
	}
	
	/**
	 * Convierte un objeto RelNumExpedienteAuditoria a un objeto RelNumExpedienteAuditoriaDTO.
	 * 
	 * @param expediente
	 * @return objeto ExpedienteDTO
	 */
	public static RelNumExpedienteAuditoria transformar(RelNumExpedienteAuditoriaDTO dto) {
		if (dto == null) {
			return null;
		}
		RelNumExpedienteAuditoria entidad = new RelNumExpedienteAuditoria();

		entidad.setRelNumExpedienteAuditoriaId(dto.getRelNumExpedienteAuditoriaId());
		if(dto.getNumeroExpediente() != null && dto.getNumeroExpediente().getNumeroExpedienteId() != null)
			entidad.setNumeroExpediente(new NumeroExpediente(dto.getNumeroExpediente().getNumeroExpedienteId()));
		
		if(dto.getNumeroAuditoria() != null && dto.getNumeroAuditoria().getExpedienteId() != null)
			entidad.setNumeroAuditoriaId(dto.getNumeroAuditoria().getExpedienteId());	
		
		return entidad;
	}

}
