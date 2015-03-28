/**
* Nombre del Programa : CatFaltaAdministrativa.java
* Autor                            : Tattva-IT
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 4 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.dto.catalogo;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author mgallardo
 *
 */
public class CatFaltaAdministrativaDTO extends GenericDTO{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long catFaltaAdministrativaId;
    private String claveFalta;
    private String nombreFalta;
    private String descripcionFalta;
    
    
    
	public CatFaltaAdministrativaDTO(Long catFaltaAdministrativaId,
			String claveFalta, String nombreFalta, String descripcionFalta) {
		super();
		this.catFaltaAdministrativaId = catFaltaAdministrativaId;
		this.claveFalta = claveFalta;
		this.nombreFalta = nombreFalta;
		this.descripcionFalta = descripcionFalta;
	}
	
	
	
	public CatFaltaAdministrativaDTO() {
		super();	
	}

	public Long getCatFaltaAdministrativaId() {
		return catFaltaAdministrativaId;
	}
	public void setCatFaltaAdministrativaId(Long catFaltaAdministrativaId) {
		this.catFaltaAdministrativaId = catFaltaAdministrativaId;
	}
	public String getClaveFalta() {
		return claveFalta;
	}
	public void setClaveFalta(String claveFalta) {
		this.claveFalta = claveFalta;
	}
	public String getNombreFalta() {
		return nombreFalta;
	}
	public void setNombreFalta(String nombreFalta) {
		this.nombreFalta = nombreFalta;
	}
	public String getDescripcionFalta() {
		return descripcionFalta;
	}
	public void setDescripcionFalta(String descripcionFalta) {
		this.descripcionFalta = descripcionFalta;
	}
    
}
