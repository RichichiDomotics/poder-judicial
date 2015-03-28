/**
* Nombre del Programa : AliasInvolucradoDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 8 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto calidad.
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
package mx.gob.segob.nsjp.dto.involucrado;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class AliasInvolucradoDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6531770862151992874L;

	private Long aliasInvolucradoId;	
	private String alias;
	private InvolucradoDTO involucradoDTO;
	
	/**
	 * 
	 */
	public AliasInvolucradoDTO() {
		super();
	}
    /**
     * 
     */
    public AliasInvolucradoDTO(String elAlias) {
        super();
        this.alias = elAlias;
    }
	/**
	 * Método de acceso al campo aliasInvolucradoId.
	 * @return El valor del campo aliasInvolucradoId
	 */
	public Long getAliasInvolucradoId() {
		return aliasInvolucradoId;
	}

	/**
	 * Asigna el valor al campo aliasInvolucradoId.
	 * @param aliasInvolucradoId el valor aliasInvolucradoId a asignar
	 */
	public void setAliasInvolucradoId(Long aliasInvolucradoId) {
		this.aliasInvolucradoId = aliasInvolucradoId;
	}

	/**
	 * Método de acceso al campo calias.
	 * @return El valor del campo calias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Asigna el valor al campo calias.
	 * @param calias el valor calias a asignar
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Método de acceso al campo involucradoDTO.
	 * @return El valor del campo involucradoDTO
	 */
	public InvolucradoDTO getInvolucradoDTO() {
		return involucradoDTO;
	}

	/**
	 * Asigna el valor al campo involucradoDTO.
	 * @param involucradoDTO el valor involucradoDTO a asignar
	 */
	public void setInvolucradoDTO(InvolucradoDTO involucradoDTO) {
		this.involucradoDTO = involucradoDTO;
	}	

}
