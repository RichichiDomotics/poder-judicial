package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CatTipoTrabajoExterno entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatTipoTrabajoExterno")
public class CatTipoTrabajoExterno implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3093854249139419676L;
	private Long catTipoTrabajoExternoId;
	private String cdescripcion;

	// Constructors

	/** default constructor */
	public CatTipoTrabajoExterno() {
	}

	/** minimal constructor */
	public CatTipoTrabajoExterno(Long catTipoTrabajoExternoId,
			String cdescripcion) {
		this.catTipoTrabajoExternoId = catTipoTrabajoExternoId;
		this.cdescripcion = cdescripcion;
	}


	// Property accessors
	@Id
	@Column(name = "CatTipoTrabajoExterno_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatTipoTrabajoExternoId() {
		return this.catTipoTrabajoExternoId;
	}

	public void setCatTipoTrabajoExternoId(Long catTipoTrabajoExternoId) {
		this.catTipoTrabajoExternoId = catTipoTrabajoExternoId;
	}

	@Column(name = "cDescripcion", nullable = false, length = 200)
	public String getCdescripcion() {
		return this.cdescripcion;
	}

	public void setCdescripcion(String cdescripcion) {
		this.cdescripcion = cdescripcion;
	}

}