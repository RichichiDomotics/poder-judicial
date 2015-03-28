/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author adrian
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "EncargadoAlmacen")
public class EncargadoAlmacen implements Serializable {
	
	private EncargadoAlmacenId encargadoAlmacenId;
	private Almacen almacen;
	private Funcionario funcionario;
	/**
	 * @return the encargadoAlmacenId
	 */
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "almacenId", column = @Column(name = "iIdentificadorAlmacen", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "funcionarioId", column = @Column(name = "iClaveFuncionario", unique=true, nullable = false, precision = 18, scale = 0)) })
	public EncargadoAlmacenId getEncargadoAlmacenId() {
		return encargadoAlmacenId;
	}
	/**
	 * @return the almacen
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iIdentificadorAlmacen", nullable = false, insertable = false, updatable = false)
	public Almacen getAlmacen() {
		return almacen;
	}
	/**
	 * @return the funcionario
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iClaveFuncionario",unique=true, nullable = false, insertable = false, updatable = false)
	public Funcionario getFuncionario() {
		return funcionario;
	}
	/**
	 * @param encargadoAlmacenId the encargadoAlmacenId to set
	 */
	public void setEncargadoAlmacenId(EncargadoAlmacenId encargadoAlmacenId) {
		this.encargadoAlmacenId = encargadoAlmacenId;
	}
	/**
	 * @param almacen the almacen to set
	 */
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	
}
