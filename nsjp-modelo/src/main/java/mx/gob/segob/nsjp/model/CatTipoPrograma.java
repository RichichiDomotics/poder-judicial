package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CatTipoPrograma entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatTipoPrograma")
public class CatTipoPrograma implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2012015535779788207L;
	private Long catTipoProgramaId;
	private String cdescripcion;

	// Constructors

	/** default constructor */
	public CatTipoPrograma() {
	}

	/** minimal constructor */
	public CatTipoPrograma(Long catTipoProgramaId, String cdescripcion) {
		this.catTipoProgramaId = catTipoProgramaId;
		this.cdescripcion = cdescripcion;
	}

	// Property accessors
	@Id
	@Column(name = "CatTipoPrograma_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatTipoProgramaId() {
		return this.catTipoProgramaId;
	}

	public void setCatTipoProgramaId(Long catTipoProgramaId) {
		this.catTipoProgramaId = catTipoProgramaId;
	}

	@Column(name = "cDescripcion", nullable = false, length = 200)
	public String getCdescripcion() {
		return this.cdescripcion;
	}

	public void setCdescripcion(String cdescripcion) {
		this.cdescripcion = cdescripcion;
	}

}