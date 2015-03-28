/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;

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
 * @author AlineGS
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CatDiscriminante")
public class CatDiscriminante implements Serializable {
	
	
	//Fields
	private Long catDiscriminanteId;//	campo llave IDENTITY(1,1) NOT NULL,
    private CatDistrito distrito;//     	campo llave NOT NULL,
    private String clave;//	cadena 10 NOT NULL,
    private String nombre;//            	cadena 300 NOT NULL,
    private Short clasificacion;//              	enteroCorto NOT NULL,
	
    
    /**
	 * 
	 */
	public CatDiscriminante() {
		super();
	}
	
    /**
	 * @param catDiscriminanteId
	 * @param claveDiscriminante
	 */
	public CatDiscriminante(Long catDiscriminanteId, String claveDiscriminante) {
		super();
		this.catDiscriminanteId = catDiscriminanteId;
		this.clave = claveDiscriminante;
	}

	/**
	 * @param catDiscriminanteId
	 */
	public CatDiscriminante(Long catDiscriminanteId) {
		super();
		this.catDiscriminanteId = catDiscriminanteId;
	}
	
	
	
	public CatDiscriminante(Long catDiscriminanteId, CatDistrito catDistrito,
			String claveDiscriminante, String nombreDisc, Short tipo) {
		super();
		this.catDiscriminanteId = catDiscriminanteId;
		this.distrito = catDistrito;
		this.clave = claveDiscriminante;
		this.nombre = nombreDisc;
		this.clasificacion = tipo;
	}

	/**
	 * @return the catDiscriminanteId
	 */
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catDiscriminante_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatDiscriminanteId() {
		return catDiscriminanteId;
	}
	/**
	 * @return the catDistritoId
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catDistrito_id", nullable = false)
	public CatDistrito getDistrito() {
		return distrito;
	}
	/**
	 * @return the claveDiscriminante
	 */
    @Column(name = "cClaveDiscriminante", nullable = false, length = 3)
	public String getClave() {
		return clave;
	}
	/**
	 * @return the nombreDisc
	 */
    @Column(name = "cNombre", nullable = false, length = 300)
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return the tipo
	 */
    @Column(name = "iTipo", nullable = false, precision = 4, scale = 0)
	public Short getClasificacion() {
		return clasificacion;
	}
	/**
	 * @param catDiscriminanteId the catDiscriminanteId to set
	 */
	public void setCatDiscriminanteId(Long catDiscriminanteId) {
		this.catDiscriminanteId = catDiscriminanteId;
	}
	/**
	 * @param catDistritoId the catDistritoId to set
	 */
	public void setDistrito(CatDistrito catDistrito) {
		this.distrito = catDistrito;
	}
	/**
	 * @param claveDiscriminante the claveDiscriminante to set
	 */
	public void setClave(String claveDiscriminante) {
		this.clave = claveDiscriminante;
	}
	/**
	 * @param nombreDisc the nombreDisc to set
	 */
	public void setNombre(String nombreDisc) {
		this.nombre = nombreDisc;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setClasificacion(Short tipo) {
		this.clasificacion = tipo;
	}

    
}
