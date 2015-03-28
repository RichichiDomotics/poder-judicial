
package mx.gob.segob.nsjp.dto.actos;


/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class BuenaConductaDTO extends ActoDTO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8114473378829499826L;
	// Número de puntos asignados por el acto de buena conducta
	private	Long puntos;

	/**
	 * Método de acceso al campo puntos.
	 * @return El valor del campo puntos
	 */
	public Long getPuntos() {
		return puntos;
	}

	/**
	 * Asigna el valor al campo puntos.
	 * @param puntos el valor puntos a asignar
	 */
	public void setPuntos(Long puntos) {
		this.puntos = puntos;
	}
}
