package mx.gob.segob.nsjp.dto.traslados;

import mx.gob.segob.nsjp.dto.base.GenericDTO;


// Motivo del traslado
// 1. Por excarcelación 
// 2. Por Audiencia-
// 3. Cambio de cereso
/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class MotivoTrasladoDTO extends GenericDTO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5134018629714334787L;

	// Número consecutivo que identifica de manera única al traslado
	private	Long idMotivoTraslado;
	
	// Descripción del motivo del traslado
	private	String descripcion;

	/**
	 * Método de acceso al campo idMotivoTraslado.
	 * @return El valor del campo idMotivoTraslado
	 */
	public Long getIdMotivoTraslado() {
		return idMotivoTraslado;
	}

	/**
	 * Asigna el valor al campo idMotivoTraslado.
	 * @param idMotivoTraslado el valor idMotivoTraslado a asignar
	 */
	public void setIdMotivoTraslado(Long idMotivoTraslado) {
		this.idMotivoTraslado = idMotivoTraslado;
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
