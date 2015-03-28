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
public class CatCategoriaRelacionDTO extends GenericDTO{

    private Long catCategoriaRelacionId;
    private String desCategoriaRelacion;
    private Boolean esDocumento;

    public CatCategoriaRelacionDTO(Long catCategoriaRelacionId,
			String desCategoriaRelacion, Boolean esDocumento) {
		super();
		this.catCategoriaRelacionId = catCategoriaRelacionId;
		this.desCategoriaRelacion = desCategoriaRelacion;
		this.esDocumento = esDocumento;
	}
    public CatCategoriaRelacionDTO(){
    	
    }

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


    /**
     * Get the value of desCategoriaRelacion
     *
     * @return the value of desCategoriaRelacion
     */
    public String getDesCategoriaRelacion() {
        return desCategoriaRelacion;
    }

    /**
     * Set the value of desCategoriaRelacion
     *
     * @param desCategoriaRelacion new value of desCategoriaRelacion
     */
    public void setDesCategoriaRelacion(String desCategoriaRelacion) {
        this.desCategoriaRelacion = desCategoriaRelacion;
    }

	/**
	 * Asigna el valor al campo esDocumento.
	 * @param esDocumento el valor esDocumento a asignar
	 */
	public void setEsDocumento(Boolean esDocumento) {
		this.esDocumento = esDocumento;
	}

	/**
	 * Método de acceso al campo esDocumento.
	 * @return El valor del campo esDocumento
	 */
	public Boolean getEsDocumento() {
		return esDocumento;
	}


}
