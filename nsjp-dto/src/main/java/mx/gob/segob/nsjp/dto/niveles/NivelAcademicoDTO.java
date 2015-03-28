/**
* Nombre del Programa : NivelAcademicoDTO.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08/02/2012
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
package mx.gob.segob.nsjp.dto.niveles;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class NivelAcademicoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 616115649930510699L;

	private Long nivelAcademicoId;
	
	private String observaciones;
	
	private String otros;
	
	private CatTipoNivelAcademicoDTO catTipoNivelAcademico;

	/**
	 * Método de acceso al campo nivelAcademicoId.
	 * @return El valor del campo nivelAcademicoId
	 */
	public Long getNivelAcademicoId() {
		return nivelAcademicoId;
	}

	/**
	 * Asigna el valor al campo nivelAcademicoId.
	 * @param nivelAcademicoId el valor nivelAcademicoId a asignar
	 */
	public void setNivelAcademicoId(Long nivelAcademicoId) {
		this.nivelAcademicoId = nivelAcademicoId;
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
	 * Método de acceso al campo otros.
	 * @return El valor del campo otros
	 */
	public String getOtros() {
		return otros;
	}

	/**
	 * Asigna el valor al campo otros.
	 * @param otros el valor otros a asignar
	 */
	public void setOtros(String otros) {
		this.otros = otros;
	}

	/**
	 * Método de acceso al campo catTipoNivelAcademico.
	 * @return El valor del campo catTipoNivelAcademico
	 */
	public CatTipoNivelAcademicoDTO getCatTipoNivelAcademico() {
		return catTipoNivelAcademico;
	}

	/**
	 * Asigna el valor al campo catTipoNivelAcademico.
	 * @param catTipoNivelAcademico el valor catTipoNivelAcademico a asignar
	 */
	public void setCatTipoNivelAcademico(
			CatTipoNivelAcademicoDTO catTipoNivelAcademico) {
		this.catTipoNivelAcademico = catTipoNivelAcademico;
	}
	
}
