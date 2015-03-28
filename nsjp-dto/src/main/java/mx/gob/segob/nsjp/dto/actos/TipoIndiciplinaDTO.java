package mx.gob.segob.nsjp.dto.actos;

import mx.gob.segob.nsjp.dto.base.GenericDTO;



public class TipoIndiciplinaDTO extends GenericDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2148342949760607899L;

	private Long idTipoActo;
	
	private String descripcion;

	/**
	 * Método de acceso al campo idTipoActo.
	 * @return El valor del campo idTipoActo
	 */
	public Long getIdTipoActo() {
		return idTipoActo;
	}

	/**
	 * Asigna el valor al campo idTipoActo.
	 * @param idTipoActo el valor idTipoActo a asignar
	 */
	public void setIdTipoActo(Long idTipoActo) {
		this.idTipoActo = idTipoActo;
	}

	/**
	 * Método de acceso al campo descripcion.
	 * @return El valor del campo descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Asigna el valor al campo descripcion.
	 * @param descripcion el valor descripcion a asignar
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}