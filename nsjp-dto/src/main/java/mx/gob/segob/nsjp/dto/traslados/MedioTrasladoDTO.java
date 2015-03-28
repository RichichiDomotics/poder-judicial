package mx.gob.segob.nsjp.dto.traslados;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

// 1. Por tierra
// 2. Por aire
// 3. Por mar
/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class MedioTrasladoDTO extends GenericDTO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4774004784126660154L;

	// Número consecutivo que identifica de manera única al tipo de traslado
	private	Long idMedioTraslado;

	// Descripción del medio de traslado
	private	String descripcion;

	/**
	 * Método de acceso al campo idMedioTraslado.
	 * @return El valor del campo idMedioTraslado
	 */
	public Long getIdMedioTraslado() {
		return idMedioTraslado;
	}

	/**
	 * Asigna el valor al campo idMedioTraslado.
	 * @param idMedioTraslado el valor idMedioTraslado a asignar
	 */
	public void setIdMedioTraslado(Long idMedioTraslado) {
		this.idMedioTraslado = idMedioTraslado;
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
