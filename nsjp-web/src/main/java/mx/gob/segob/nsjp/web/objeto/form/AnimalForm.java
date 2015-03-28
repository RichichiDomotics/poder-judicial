/**
* Nombre del Programa : AnimalForm.java
* Autor                            : AlejandroGA
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 5 May 2011
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
package mx.gob.segob.nsjp.web.objeto.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class AnimalForm extends GenericForm{

	private Long idAnimal;
	//Campos para la insercion desde cadena de custodia
	private String fechaLevCadCus; 
	private String origenEvdCadCus;
	private String cadenaCustodia;
	
	private Boolean anular;
	
	/**
	 * @return the idAnimal
	 */
	public Long getIdAnimal() {
		return idAnimal;
	}

	/**
	 * @param idAnimal the idAnimal to set
	 */
	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}

	/**
	 * @return the fechaLevCadCus
	 */
	public String getFechaLevCadCus() {
		return fechaLevCadCus;
	}

	/**
	 * @param fechaLevCadCus the fechaLevCadCus to set
	 */
	public void setFechaLevCadCus(String fechaLevCadCus) {
		this.fechaLevCadCus = fechaLevCadCus;
	}

	/**
	 * @return the origenEvdCadCus
	 */
	public String getOrigenEvdCadCus() {
		return origenEvdCadCus;
	}

	/**
	 * @param origenEvdCadCus the origenEvdCadCus to set
	 */
	public void setOrigenEvdCadCus(String origenEvdCadCus) {
		this.origenEvdCadCus = origenEvdCadCus;
	}

	/**
	 * @return the cadenaCustodia
	 */
	public String getCadenaCustodia() {
		return cadenaCustodia;
	}

	/**
	 * @param cadenaCustodia the cadenaCustodia to set
	 */
	public void setCadenaCustodia(String cadenaCustodia) {
		this.cadenaCustodia = cadenaCustodia;
	}

	/**
	 * @return the anular
	 */
	public Boolean getAnular() {
		return anular;
	}

	/**
	 * @param anular the anular to set
	 */
	public void setAnular(Boolean anular) {
		this.anular = anular;
	}

	/**
	 * Serialize de la clase
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *Id del tipo de animal
	 */
	private Long glTipoAnimalId;
	
	/**
	 *Estado del estado del animal
	 */
	private String gsEstadoAnimal;
	
	/**
	 *Raza del animal
	 */
	private String gsRazaAnimal;
	
	/**
	 *Id de la condicion fisica del animal
	 */
	private Long glCondicionFisicaAnimalId;
	
	/**
	 * Breve descripcion del animal
	 */
	private String gcDescripcionAnimal;

	/**
	 * Método de acceso al campo glTipoAnimalId.
	 * @return El valor del campo glTipoAnimalId
	 */
	public Long getGlTipoAnimalId() {
		return glTipoAnimalId;
	}

	/**
	 * Asigna el valor al campo glTipoAnimalId.
	 * @param glTipoAnimalId el valor glTipoAnimalId a asignar
	 */
	public void setGlTipoAnimalId(Long glTipoAnimalId) {
		this.glTipoAnimalId = glTipoAnimalId;
	}

	/**
	 * Método de acceso al campo gsEstadoAnimal.
	 * @return El valor del campo gsEstadoAnimal
	 */
	public String getGsEstadoAnimal() {
		return gsEstadoAnimal;
	}

	/**
	 * Asigna el valor al campo gsEstadoAnimal.
	 * @param gsEstadoAnimal el valor gsEstadoAnimal a asignar
	 */
	public void setGsEstadoAnimal(String gsEstadoAnimal) {
		this.gsEstadoAnimal = gsEstadoAnimal;
	}

	/**
	 * Método de acceso al campo gsRazaAnimal.
	 * @return El valor del campo gsRazaAnimal
	 */
	public String getGsRazaAnimal() {
		return gsRazaAnimal;
	}

	/**
	 * Asigna el valor al campo gsRazaAnimal.
	 * @param gsRazaAnimal el valor gsRazaAnimal a asignar
	 */
	public void setGsRazaAnimal(String gsRazaAnimal) {
		this.gsRazaAnimal = gsRazaAnimal;
	}

	/**
	 * Método de acceso al campo glCondicionFisicaAnimalId.
	 * @return El valor del campo glCondicionFisicaAnimalId
	 */
	public Long getGlCondicionFisicaAnimalId() {
		return glCondicionFisicaAnimalId;
	}

	/**
	 * Asigna el valor al campo glCondicionFisicaAnimalId.
	 * @param glCondicionFisicaAnimalId el valor glCondicionFisicaAnimalId a asignar
	 */
	public void setGlCondicionFisicaAnimalId(Long glCondicionFisicaAnimalId) {
		this.glCondicionFisicaAnimalId = glCondicionFisicaAnimalId;
	}

	/**
	 * Método de acceso al campo gcDescripcionAnimal.
	 * @return El valor del campo gcDescripcionAnimal
	 */
	public String getGcDescripcionAnimal() {
		return gcDescripcionAnimal;
	}

	/**
	 * Asigna el valor al campo gcDescripcionAnimal.
	 * @param gcDescripcionAnimal el valor gcDescripcionAnimal a asignar
	 */
	public void setGcDescripcionAnimal(String gcDescripcionAnimal) {
		this.gcDescripcionAnimal = gcDescripcionAnimal;
	}

	/**
	 * Método de acceso al campo serialversionuid.
	 * @return El valor del campo serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
