package mx.gob.segob.nsjp.dto.estudios;

import mx.gob.segob.nsjp.dto.base.GenericDTO;



// 1 Medico
// 2. Psiquiatrico
/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class TipoEstudioDTO extends GenericDTO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4200791122845301180L;

	private Long idTipoEstudio;
	
	private String descripcion;

	/**
	 * Método de acceso al campo idTipoEstudio.
	 * @return El valor del campo idTipoEstudio
	 */
	public Long getIdTipoEstudio() {
		return idTipoEstudio;
	}

	/**
	 * Asigna el valor al campo idTipoEstudio.
	 * @param idTipoEstudio el valor idTipoEstudio a asignar
	 */
	public void setIdTipoEstudio(Long idTipoEstudio) {
		this.idTipoEstudio = idTipoEstudio;
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
