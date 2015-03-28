/**
* Nombre del Programa : RelNumExpedienteAuditoria.java
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
package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad que permite relacionar un numero Expediente con los
 * números de Auditoría, considerando su estado.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Entity
@Table(name="RelNumExpedienteAuditoria")
public class RelNumExpedienteAuditoria {
	private Long relNumExpedienteAuditoriaId;
	private NumeroExpediente numeroExpediente;
	private Long numeroAuditoriaId;
	
	public RelNumExpedienteAuditoria() {
	}
	
	public RelNumExpedienteAuditoria(Long relNumExpedienteAuditoriaId,
			NumeroExpediente numeroExpediente, Long numeroExpedienteAuditadoId,
			Long numeroAuditoriaId, Valor estatus) {
		this.relNumExpedienteAuditoriaId = relNumExpedienteAuditoriaId;
		this.numeroAuditoriaId = numeroAuditoriaId;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RelNumExpedienteAuditoria_Id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getRelNumExpedienteAuditoriaId() {
		return relNumExpedienteAuditoriaId;
	}

	public void setRelNumExpedienteAuditoriaId(Long relNumExpedienteAuditoriaId) {
		this.relNumExpedienteAuditoriaId = relNumExpedienteAuditoriaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NumeroExpediente_id", nullable = false)
	public NumeroExpediente getNumeroExpediente() {
		return numeroExpediente;
	}

	public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	
	@Column(name = "NumeroAuditoria_id", precision = 18, scale = 0, nullable = false)
	public Long getNumeroAuditoriaId() {
		return numeroAuditoriaId;
	}

	public void setNumeroAuditoriaId(Long numeroAuditoriaId) {
		this.numeroAuditoriaId = numeroAuditoriaId;
	}

}
