/**
 * Nombre del Programa : ConsultarRelacionesXCategoriaService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 12-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dto.relacion;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 *
 * @author Jacob Lobaco
 */
public class CatRelacionDTO extends GenericDTO{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8897792775463039588L;
	private Long catRelacionId;

    public CatRelacionDTO(long idCatRelacion) {
    	this.catRelacionId = idCatRelacion;
	}

	public CatRelacionDTO() {
	}

	/**
     * Get the value of catRelacionId
     *
     * @return the value of catRelacionId
     */
    public Long getCatRelacionId() {
        return catRelacionId;
    }

    /**
     * Set the value of catRelacionId
     *
     * @param catRelacionId new value of catRelacionId
     */
    public void setCatRelacionId(Long catRelacionId) {
        this.catRelacionId = catRelacionId;
    }

    private String descripcionRelacion;

    /**
     * Get the value of descripcionRelacion
     *
     * @return the value of descripcionRelacion
     */
    public String getDescripcionRelacion() {
        return descripcionRelacion;
    }

    /**
     * Set the value of descripcionRelacion
     *
     * @param descripcionRelacion new value of descripcionRelacion
     */
    public void setDescripcionRelacion(String descripcionRelacion) {
        this.descripcionRelacion = descripcionRelacion;
    }
    private Long catCategoriaRelacionId;

    /**
     * Get the value of catCategoriaRelacionId
     *
     * @return the value of catCategoriaRelacionId
     */
    public Long getCatCategoriaRelacionId() {
        return catCategoriaRelacionId;
    }

    /**
     * Set the value of catCategoriaRelacionId
     *
     * @param catCategoriaRelacionId new value of catCategoriaRelacionId
     */
    public void setCatCategoriaRelacionId(Long catCategoriaRelacionId) {
        this.catCategoriaRelacionId = catCategoriaRelacionId;
    }
    
    private String desCategoriaRelacion;

	/**
	 * Método de acceso al campo desCategoriaRelacion.
	 * @return El valor del campo desCategoriaRelacion
	 */
	public String getDesCategoriaRelacion() {
		return desCategoriaRelacion;
	}

	/**
	 * Asigna el valor al campo desCategoriaRelacion.
	 * @param desCategoriaRelacion el valor desCategoriaRelacion a asignar
	 */
	public void setDesCategoriaRelacion(String desCategoriaRelacion) {
		this.desCategoriaRelacion = desCategoriaRelacion;
	}
    
}
