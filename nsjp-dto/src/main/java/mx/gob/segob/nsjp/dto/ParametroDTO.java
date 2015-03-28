/**
 * 
 */
package mx.gob.segob.nsjp.dto;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * @author adrian
 *
 */
public class ParametroDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3175595855502714025L;
	
	private Long parametroId;
    private String clave;
    private String valor;
    private String descripcion; 
    private String tipoValor;
    
    
    
	/**
	 * @param parametroId
	 * @param clave
	 * @param valor
	 * @param descripcion
	 * @param tipoValor
	 */
	public ParametroDTO(Long parametroId, String clave, String valor,
			String descripcion, String tipoValor) {
		super();
		this.parametroId = parametroId;
		this.clave = clave;
		this.valor = valor;
		this.descripcion = descripcion;
		this.tipoValor = tipoValor;
	}
	/**
	 * @return the parametroId
	 */
	public Long getParametroId() {
		return parametroId;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @return the tipoValor
	 */
	public String getTipoValor() {
		return tipoValor;
	}
	/**
	 * @param parametroId the parametroId to set
	 */
	public void setParametroId(Long parametroId) {
		this.parametroId = parametroId;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @param tipoValor the tipoValor to set
	 */
	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}
    
    

}
