package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TipoRemision entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatTipoRemision")
public class CatTipoRemision implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6100490611838289016L;	
	private Long catTipoRemisionId;
	private String cnombre;
	private String cdescripcion;
	private Long iporcentajeCumplido;

	// Constructors

	/** default constructor */
	public CatTipoRemision() {
	}

	/** minimal constructor */
	public CatTipoRemision(Long catTipoRemisionId, String cnombre,
			Long iporcentajeCumplido) {
		this.catTipoRemisionId = catTipoRemisionId;
		this.cnombre = cnombre;
		this.iporcentajeCumplido = iporcentajeCumplido;
	}



	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CatTipoRemision_Id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatTipoRemisionId() {
		return this.catTipoRemisionId;
	}

	public void setCatTipoRemisionId(Long catTipoRemisionId) {
		this.catTipoRemisionId = catTipoRemisionId;
	}

	@Column(name = "cNombre", nullable = false, length = 200)
	public String getCnombre() {
		return this.cnombre;
	}

	public void setCnombre(String cnombre) {
		this.cnombre = cnombre;
	}

	@Column(name = "cDescripcion", length = 200)
	public String getCdescripcion() {
		return this.cdescripcion;
	}

	public void setCdescripcion(String cdescripcion) {
		this.cdescripcion = cdescripcion;
	}

	@Column(name = "iPorcentajeCumplido", nullable = false, precision = 18, scale = 0)
	public Long getIporcentajeCumplido() {
		return this.iporcentajeCumplido;
	}

	public void setIporcentajeCumplido(Long iporcentajeCumplido) {
		this.iporcentajeCumplido = iporcentajeCumplido;
	}

}