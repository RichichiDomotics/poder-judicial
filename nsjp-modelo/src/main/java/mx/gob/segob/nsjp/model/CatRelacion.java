package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CatRelacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatRelacion" )
public class CatRelacion implements java.io.Serializable {

	// Fields

	private Long catRelacionId;
	private CatCategoriaRelacion catCategoriaRelacion;
	private String descripcionRelacion;
	private Set<Relacion> relacions = new HashSet<Relacion>(0);
	   private Boolean esParentesco;

	// Constructors

	/** default constructor */
	public CatRelacion() {
	}	
	
	/**
	 * @param catRelacionId
	 */
	public CatRelacion(Long catRelacionId) {
		super();
		this.catRelacionId = catRelacionId;
	}



	/** minimal constructor */
	public CatRelacion(Long catRelacionId,
			CatCategoriaRelacion catCategoriaRelacion,
			String descripcionRelacion) {
		this.catRelacionId = catRelacionId;
		this.catCategoriaRelacion = catCategoriaRelacion;
		this.descripcionRelacion = descripcionRelacion;
	}

	/** full constructor */
	public CatRelacion(Long catRelacionId,
			CatCategoriaRelacion catCategoriaRelacion,
			String descripcionRelacion, Set<Relacion> relacions) {
		this.catRelacionId = catRelacionId;
		this.catCategoriaRelacion = catCategoriaRelacion;
		this.descripcionRelacion = descripcionRelacion;
		this.relacions = relacions;
	}

	// Property accessors
	@Id
	@Column(name = "CatRelacion_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatRelacionId() {
		return this.catRelacionId;
	}

	public void setCatRelacionId(Long catRelacionId) {
		this.catRelacionId = catRelacionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatCategoriaRelacion_id", nullable = false)
	public CatCategoriaRelacion getCatCategoriaRelacion() {
		return this.catCategoriaRelacion;
	}

	public void setCatCategoriaRelacion(
			CatCategoriaRelacion catCategoriaRelacion) {
		this.catCategoriaRelacion = catCategoriaRelacion;
	}

	@Column(name = "cDescripcionRelacion", nullable = false, length = 50)
	public String getDescripcionRelacion() {
		return this.descripcionRelacion;
	}

	public void setDescripcionRelacion(String descripcionRelacion) {
		this.descripcionRelacion = descripcionRelacion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catRelacion")
	public Set<Relacion> getRelacions() {
		return this.relacions;
	}

	public void setRelacions(Set<Relacion> relacions) {
		this.relacions = relacions;
	}

    /**
     * Método de acceso al campo esParentesco.
     * @return El valor del campo esParentesco
     */
	@Column(name = "bEsParentesco", precision = 1, scale = 0, nullable= false)
    public Boolean getEsParentesco() {
        return esParentesco;
    }

    /**
     * Asigna el valor al campo esParentesco.
     * @param esParentesco el valor esParentesco a asignar
     */
    public void setEsParentesco(Boolean esParentesco) {
        this.esParentesco = esParentesco;
    }

}
