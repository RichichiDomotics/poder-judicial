/**
 * 
 */
package mx.gob.segob.nsjp.dto.catalogo;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * @author AlineGS
 *
 */
public class CatDistritoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6556604757579568343L;
	
	private Long catDistritoId;
    private String claveDistrito;
    private String nombreDist;
    
    private List<CatDiscriminanteDTO> discriminantes=new ArrayList<CatDiscriminanteDTO>();
    
    
    
    public CatDistritoDTO() {
		super();
	}
    
	public CatDistritoDTO(Long catDistritoId) {
		super();
		this.catDistritoId = catDistritoId;
    }
    public CatDistritoDTO(Long catDistritoId, String claveDistrito,
			String nombreDist) {
		super();
		this.catDistritoId = catDistritoId;
		this.claveDistrito = claveDistrito;
		this.nombreDist = nombreDist;
	}

	public CatDistritoDTO(Long catDistritoId, String claveDistrito,
			String nombreDist, List<CatDiscriminanteDTO> discriminantes) {
		super();
		this.catDistritoId = catDistritoId;
		this.claveDistrito = claveDistrito;
		this.nombreDist = nombreDist;
		this.discriminantes = discriminantes;
	}

	/**
	 * @return the catDistritoId
	 */
	public Long getCatDistritoId() {
		return catDistritoId;
	}

	/**
	 * @return the claveDistrito
	 */
	public String getClaveDistrito() {
		return claveDistrito;
	}

	/**
	 * @return the nombreDist
	 */
	public String getNombreDist() {
		return nombreDist;
	}

	/**
	 * @return the discriminantes
	 */
	public List<CatDiscriminanteDTO> getDiscriminantes() {
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
	public void setDiscriminantes(List<CatDiscriminanteDTO> discriminantes) {
		this.discriminantes = discriminantes;
	}
    
    
    

}
