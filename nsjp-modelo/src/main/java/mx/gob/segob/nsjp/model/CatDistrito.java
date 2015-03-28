/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author AlineGS
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CatDistrito")
public class CatDistrito implements Serializable {
	
	//Fields
	private Long catDistritoId;//	campo llave IDENTITY(1,1) NOT NULL,
    private String claveDistrito;//	cadena 10 NOT NULL,
    private String nombreDist;//       	cadena 300 NOT NULL,
    
    private Set<CatDiscriminante> discriminantes = new HashSet<CatDiscriminante>(0);
    
    public CatDistrito(){
    	super();
    }
    
    public CatDistrito(Long catDistritoId) {
		super();
		this.catDistritoId = catDistritoId;
	}
    
	public CatDistrito(Long catDistritoId, String claveDistrito,
			String nombreDist) {
		super();
		this.catDistritoId = catDistritoId;
		this.claveDistrito = claveDistrito;
		this.nombreDist = nombreDist;
	}
	/**
	 * @return the catDistritoId
	 */
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catDistrito_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatDistritoId() {
		return catDistritoId;
	}
	/**
	 * @return the claveDistrito
	 */
    @Column(name = "cClaveDistrito", nullable = false, length = 2)
	public String getClaveDistrito() {
		return claveDistrito;
	}
	/**
	 * @return the nombreDist
	 */
    @Column(name = "cNombre", nullable = false, length = 300)
	public String getNombreDist() {
		return nombreDist;
	}
    /**
	 * @return the discriminantes
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "distrito")
	public Set<CatDiscriminante> getDiscriminantes() {
		return discriminantes;
	}
	/**
	 * @param catDistritoId the catDistritoId to set
	 */
	public void setCatDistritoId(Long catDistritoId) {
		this.catDistritoId = catDistritoId;
	}
	/**
	 * @param claveDistrito the claveDistrito to set
	 */
	public void setClaveDistrito(String claveDistrito) {
		this.claveDistrito = claveDistrito;
	}
	/**
	 * @param nombreDist the nombreDist to set
	 */
	public void setNombreDist(String nombreDist) {
		this.nombreDist = nombreDist;
	}
	/**
	 * @param discriminantes the discriminantes to set
	 */
	public void setDiscriminantes(Set<CatDiscriminante> discriminantes) {
		this.discriminantes = discriminantes;
	}
	

    
}
