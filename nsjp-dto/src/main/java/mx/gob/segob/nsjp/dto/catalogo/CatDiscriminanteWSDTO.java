/**
 * 
 */
package mx.gob.segob.nsjp.dto.catalogo;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos del Catalogo de Discriminante.
 * 
 * @author GustavoBP
 * @version 1.0
 */
public class CatDiscriminanteWSDTO extends GenericWSDTO {

	private Long catDiscriminanteId;
    private Long distritoId;
    private String clave;
    private String nombre;
    private Short tipo;
    
    
    public CatDiscriminanteWSDTO(){
    	super();
    }
    
	public CatDiscriminanteWSDTO(Long catDiscriminanteId,
			Long catDistritoId, String claveDiscriminante,
			String nombreDisc, Short tipo) {
		super();
		this.catDiscriminanteId = catDiscriminanteId;
		this.distritoId = catDistritoId;
		this.clave = claveDiscriminante;
		this.nombre = nombreDisc;
		this.tipo = tipo;
	}
	
	
	/**
	 * @param catDiscriminanteId
	 * @param claveDiscriminante
	 */
	public CatDiscriminanteWSDTO(Long catDiscriminanteId,
			String claveDiscriminante) {
		super();
		this.catDiscriminanteId = catDiscriminanteId;
		this.clave = claveDiscriminante;
	}

	/**
	 * @return the catDiscriminanteId
	 */
	public Long getCatDiscriminanteId() {
		return catDiscriminanteId;
	}
	/**
	 * @return the catDistrito
	 */
	public Long getDistritoId() {
		return distritoId;
	}
	/**
	 * @return the claveDiscriminante
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @return the nombreDisc
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return the tipo
	 */
	public Short getTipo() {
		return tipo;
	}
	/**
	 * @param catDiscriminanteId the catDiscriminanteId to set
	 */
	public void setCatDiscriminanteId(Long catDiscriminanteId) {
		this.catDiscriminanteId = catDiscriminanteId;
	}
	/**
	 * @param catDistrito the catDistrito to set
	 */
	public void setDistritoId(Long catDistritoId) {
		this.distritoId = catDistritoId;
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
	public void setTipo(Short tipo) {
		this.tipo = tipo;
	}

}
