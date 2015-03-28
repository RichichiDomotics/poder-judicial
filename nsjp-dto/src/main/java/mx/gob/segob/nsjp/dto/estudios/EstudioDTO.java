package mx.gob.segob.nsjp.dto.estudios;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class EstudioDTO extends GenericDTO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3081070186193662494L;

	private String resultado;

	private String observaciones;

	private Date fechaEstudio;

	/**
	 * Método de acceso al campo resultado.
	 * @return El valor del campo resultado
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Asigna el valor al campo resultado.
	 * @param resultado el valor resultado a asignar
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	/**
	 * Método de acceso al campo observaciones.
	 * @return El valor del campo observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Asigna el valor al campo observaciones.
	 * @param observaciones el valor observaciones a asignar
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Método de acceso al campo fechaEstudio.
	 * @return El valor del campo fechaEstudio
	 */
	public Date getFechaEstudio() {
		return fechaEstudio;
	}

	/**
	 * Asigna el valor al campo fechaEstudio.
	 * @param fechaEstudio el valor fechaEstudio a asignar
	 */
	public void setFechaEstudio(Date fechaEstudio) {
		this.fechaEstudio = fechaEstudio;
	}

}
