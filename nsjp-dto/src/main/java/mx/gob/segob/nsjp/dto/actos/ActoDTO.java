package mx.gob.segob.nsjp.dto.actos;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class ActoDTO extends GenericDTO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8551179801860103814L;

	// Número consecutivo que identifica de manera única  al acto
	private Long idActo;

	// Fecha y hora en que se lleva a cabo el acto
	private	Date fechaActo;

	// Describe de  manera natural los hechos durante el acto
	private	String narrativa;

	/**
	 * Método de acceso al campo idActo.
	 * @return El valor del campo idActo
	 */
	public Long getIdActo() {
		return idActo;
	}

	/**
	 * Asigna el valor al campo idActo.
	 * @param idActo el valor idActo a asignar
	 */
	public void setIdActo(Long idActo) {
		this.idActo = idActo;
	}

	/**
	 * Método de acceso al campo fechaActo.
	 * @return El valor del campo fechaActo
	 */
	public Date getFechaActo() {
		return fechaActo;
	}

	/**
	 * Asigna el valor al campo fechaActo.
	 * @param fechaActo el valor fechaActo a asignar
	 */
	public void setFechaActo(Date fechaActo) {
		this.fechaActo = fechaActo;
	}

	/**
	 * Método de acceso al campo narrativa.
	 * @return El valor del campo narrativa
	 */
	public String getNarrativa() {
		return narrativa;
	}

	/**
	 * Asigna el valor al campo narrativa.
	 * @param narrativa el valor narrativa a asignar
	 */
	public void setNarrativa(String narrativa) {
		this.narrativa = narrativa;
	}

}
