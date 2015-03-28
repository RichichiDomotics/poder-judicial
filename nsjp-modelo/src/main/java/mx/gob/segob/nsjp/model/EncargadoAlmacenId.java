/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author adrian
 *
 */
@SuppressWarnings("serial")
@Embeddable
public class EncargadoAlmacenId implements Serializable {
	
	private Long almacenId;
	private Long funcionarioId;
	
	
	
	public EncargadoAlmacenId() {
		super();
	}
	/**
	 * @param almacenId
	 * @param funcionarioId
	 */
	public EncargadoAlmacenId(Long almacenId, Long funcionarioId) {
		super();
		this.almacenId = almacenId;
		this.funcionarioId = funcionarioId;
	}
	/**
	 * @return the almacenId
	 */
	@Column(name = "iIdentificadorAlmacen", nullable = false, precision = 18, scale = 0)
	public Long getAlmacenId() {
		return almacenId;
	}
	/**
	 * @return the funcionarioId
	 */
	@Column(name = "iClaveFuncionario", unique=true, nullable = false, precision = 18, scale = 0)
	public Long getFuncionarioId() {
		return funcionarioId;
	}
	/**
	 * @param almacenId the almacenId to set
	 */
	public void setAlmacenId(Long almacenId) {
		this.almacenId = almacenId;
	}
	/**
	 * @param funcionarioId the funcionarioId to set
	 */
	public void setFuncionarioId(Long funcionarioId) {
		this.funcionarioId = funcionarioId;
	}
	
	

}
