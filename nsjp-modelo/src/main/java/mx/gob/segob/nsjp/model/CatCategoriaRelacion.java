package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CatCategoriaRelacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatCategoriaRelacion" )
public class CatCategoriaRelacion implements java.io.Serializable {

	// Fields

	private Long catCategoriaRelacionId;
	private String desCategoriaRelacion;
	private Set<CatRelacion> catRelacions = new HashSet<CatRelacion>(0);
    private Boolean esDocumento;


	// Constructors

	/** default constructor */
	public CatCategoriaRelacion() {
	}

	/** minimal constructor */
	public CatCategoriaRelacion(Long catCategoriaRelacionId) {
		this.catCategoriaRelacionId = catCategoriaRelacionId;
	}

	/** full constructor */
	public CatCategoriaRelacion(Long catCategoriaReacionId,
			String desCategoriaRelacion, Set<CatRelacion> catRelacions) {
		this.catCategoriaRelacionId = catCategoriaReacionId;
		this.desCategoriaRelacion = desCategoriaRelacion;
		this.catRelacions = catRelacions;
	}

	// Property accessors
	@Id
	@Column(name = "CatCategoriaRelacion_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatCategoriaRelacionId() {
		return this.catCategoriaRelacionId;
	}

	public void setCatCategoriaRelacionId(Long catCategoriaRelacionId) {
		this.catCategoriaRelacionId = catCategoriaRelacionId;
	}

	@Column(name = "cDesCategoriaRelacion", length = 30)
	public String getDesCategoriaRelacion() {
		return this.desCategoriaRelacion;
	}

	public void setDesCategoriaRelacion(String desCategoriaRelacion) {
		this.desCategoriaRelacion = desCategoriaRelacion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catCategoriaRelacion")
	public Set<CatRelacion> getCatRelacions() {
		return this.catRelacions;
	}

	public void setCatRelacions(Set<CatRelacion> catRelacions) {
		this.catRelacions = catRelacions;
	}
	
	/**
	 * Método de acceso al campo esDocumento.
	 * @return El valor del campo esDocumento
	 */
	@Column(name = "bEsDocumento", precision = 1, scale = 0)
	public Boolean getEsDocumento() {
		return esDocumento;
	}

	/**
	 * Asigna el valor al campo esDocumento.
	 * @param esDocumento el valor esDocumento a asignar
	 */
	public void setEsDocumento(Boolean esDocumento) {
		this.esDocumento = esDocumento;
	}

}
