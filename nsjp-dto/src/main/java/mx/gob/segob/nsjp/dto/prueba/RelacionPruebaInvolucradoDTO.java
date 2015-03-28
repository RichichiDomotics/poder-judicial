/**
* Nombre del Programa : RelacionPruebaInvolucradoDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/09/2011
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
package mx.gob.segob.nsjp.dto.prueba;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * DTO para la transferencia de parámetros de Relacion Prueba con Involucrado, entre la Vista y Servicio.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class RelacionPruebaInvolucradoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1558516924532313096L;

	private Long relacionPruebaInvolucradoId;
	
	//Relaciones
	private InvolucradoDTO involucrado;
	private DatoPruebaDTO datoPrueba;
	/**
	 */
	public RelacionPruebaInvolucradoDTO(){
	}
	
	public RelacionPruebaInvolucradoDTO(Long relacionPruebaInvolucradoId) {
		this.relacionPruebaInvolucradoId = relacionPruebaInvolucradoId;
	}

	/**
	 * Método de acceso al campo relacionPruebaInvolucradoId.
	 * @return El valor del campo relacionPruebaInvolucradoId
	 */
	public Long getRelacionPruebaInvolucradoId() {
		return relacionPruebaInvolucradoId;
	}

	/**
	 * Asigna el valor al campo relacionPruebaInvolucradoId.
	 * @param relacionPruebaInvolucradoId el valor relacionPruebaInvolucradoId a asignar
	 */
	public void setRelacionPruebaInvolucradoId(Long relacionPruebaInvolucradoId) {
		this.relacionPruebaInvolucradoId = relacionPruebaInvolucradoId;
	}

	/**
	 * Método de acceso al campo involucrado.
	 * @return El valor del campo involucrado
	 */
	public InvolucradoDTO getInvolucrado() {
		return involucrado;
	}

	/**
	 * Asigna el valor al campo involucrado.
	 * @param involucrado el valor involucrado a asignar
	 */
	public void setInvolucrado(InvolucradoDTO involucrado) {
		this.involucrado = involucrado;
	}

	/**
	 * Método de acceso al campo datoPrueba.
	 * @return El valor del campo datoPrueba
	 */
	public DatoPruebaDTO getDatoPrueba() {
		return datoPrueba;
	}

	/**
	 * Asigna el valor al campo datoPrueba.
	 * @param datoPrueba el valor datoPrueba a asignar
	 */
	public void setDatoPrueba(DatoPruebaDTO datoPrueba) {
		this.datoPrueba = datoPrueba;
	}
}
