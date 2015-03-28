/**
 * 
 */
package mx.gob.segob.nsjp.dto.catalogo;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * @author AlineGS
 *
 */
@SuppressWarnings("rawtypes")
public class CatDiscriminanteDTO extends GenericDTO implements Comparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5067491371417418698L;
	
	private Long catDiscriminanteId;
    private CatDistritoDTO distrito;
    private String clave;
    private String nombre;
    private Short tipo;
    
    
    public CatDiscriminanteDTO(){
    	super();
    }
    
    
	public CatDiscriminanteDTO(Long catDiscriminanteId) {
		super();
		this.catDiscriminanteId = catDiscriminanteId;
	}


	public CatDiscriminanteDTO(Long catDiscriminanteId,
			CatDistritoDTO catDistrito, String claveDiscriminante,
			String nombreDisc, Short tipo) {
		super();
		this.catDiscriminanteId = catDiscriminanteId;
		this.distrito = catDistrito;
		this.clave = claveDiscriminante;
		this.nombre = nombreDisc;
		this.tipo = tipo;
	}
	
	
	/**
	 * @param catDiscriminanteId
	 * @param claveDiscriminante
	 */
	public CatDiscriminanteDTO(Long catDiscriminanteId,
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
	public CatDistritoDTO getDistrito() {
		return distrito;
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
	public void setDistrito(CatDistritoDTO catDistrito) {
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
	public void setTipo(Short tipo) {
		this.tipo = tipo;
	}

	@Override
	public int compareTo(Object o) {
		CatDiscriminanteDTO loCatDiscriminanteDTO = (CatDiscriminanteDTO)o; 
		return this.nombre.compareToIgnoreCase(loCatDiscriminanteDTO.nombre);        	
	}    

}
