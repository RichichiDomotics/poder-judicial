/**
* Nombre del Programa : ActaCircunstanciadaDTO.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13/07/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dto.expediente;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class ActaCircunstanciadaDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5623749198397189721L;
	
	private InvolucradoDTO involucradoDTO;
	private HechoDTO hechoDTO;
	
	
	
	public ActaCircunstanciadaDTO(InvolucradoDTO involucradoDTO,
			HechoDTO hechoDTO) {
		super();
		this.involucradoDTO = involucradoDTO;
		this.hechoDTO = hechoDTO;
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
	/**
	 * Método de acceso al campo hechoDTO.
	 * @return El valor del campo hechoDTO
	 */
	public HechoDTO getHechoDTO() {
		return hechoDTO;
	}
	/**
	 * Asigna el valor al campo hechoDTO.
	 * @param hechoDTO el valor hechoDTO a asignar
	 */
	public void setHechoDTO(HechoDTO hechoDTO) {
		this.hechoDTO = hechoDTO;
	}
	
	

}
