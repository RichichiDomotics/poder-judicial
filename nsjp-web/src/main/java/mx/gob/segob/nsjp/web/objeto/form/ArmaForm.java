/**
* Nombre del Programa 		: ArmaForm.java
* Autor                 	: AlejandroGA
* Compania              	: Ultrasist
* Proyecto              	: NSJP                    Fecha: 5 May 2011
* Marca de cambio        	: N/A
* Descripcion General    	: Forma usada para ingresar una arma
* Programa Dependiente  	: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion        : N/A
* Dias de ejecucion         : N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                     : N/A
* Compania               	: N/A
* Proyecto                 	: N/A                                 Fecha: N/A
* Modificacion           	: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.objeto.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Forma usada para ingresar una arma.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class ArmaForm extends GenericForm{
	
	private Long idArma;
	private Boolean anular;
	public Long getIdArma() {
		return idArma;
	}

	public void setIdArma(Long idArma) {
		this.idArma = idArma;
	}
	
	//Campos para la insercion desde cadena de custodia
	private String fechaLevCadCus; 
	private String origenEvdCadCus;
	private String cadenaCustodia;
	//Fin Campos para la insercion desde cadena de custodia

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
	 * Serialize de la clase
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *Matricula del arma
	 */
	private String gcMatriculaArma;

	/**
	 *Calibre del arma
	 */
	private String gcCalibreArma;
	
	/**
	 *Id de la marca del arma
	 */
	private Long glMarcaArmaId;
	
	/**
	 *Id del tipo del arma
	 */
	private Long glTipoArmaId;
	
	/**
	 *Modelo del arma
	 */
	private String gcModeloArma;
	 
	/**
	 *Id de la condicion fisica del arma
	 */
	private Long glCondicionFisicaArmaId;
	
	/**
	 * Breve descripcion del arma
	 */
	private String gcDescripcionArma;

	/**
	 * Método de acceso al campo gcMatriculaArma.
	 * @return El valor del campo gcMatriculaArma
	 */
	public String getGcMatriculaArma() {
		return gcMatriculaArma;
	}

	/**
	 * Asigna el valor al campo gcMatriculaArma.
	 * @param gcMatriculaArma el valor gcMatriculaArma a asignar
	 */
	public void setGcMatriculaArma(String gcMatriculaArma) {
		this.gcMatriculaArma = gcMatriculaArma;
	}

	/**
	 * Método de acceso al campo gcCalibreArma.
	 * @return El valor del campo gcCalibreArma
	 */
	public String getGcCalibreArma() {
		return gcCalibreArma;
	}

	/**
	 * Asigna el valor al campo gcCalibreArma.
	 * @param gcCalibreArma el valor gcCalibreArma a asignar
	 */
	public void setGcCalibreArma(String gcCalibreArma) {
		this.gcCalibreArma = gcCalibreArma;
	}

	/**
	 * Método de acceso al campo glMarcaArmaId.
	 * @return El valor del campo glMarcaArmaId
	 */
	public Long getGlMarcaArmaId() {
		return glMarcaArmaId;
	}

	/**
	 * Asigna el valor al campo glMarcaArmaId.
	 * @param glMarcaArmaId el valor glMarcaArmaId a asignar
	 */
	public void setGlMarcaArmaId(Long glMarcaArmaId) {
		this.glMarcaArmaId = glMarcaArmaId;
	}

	/**
	 * Método de acceso al campo glTipoArmaId.
	 * @return El valor del campo glTipoArmaId
	 */
	public Long getGlTipoArmaId() {
		return glTipoArmaId;
	}

	/**
	 * Asigna el valor al campo glTipoArmaId.
	 * @param glTipoArmaId el valor glTipoArmaId a asignar
	 */
	public void setGlTipoArmaId(Long glTipoArmaId) {
		this.glTipoArmaId = glTipoArmaId;
	}

	/**
	 * Método de acceso al campo gcModeloArma.
	 * @return El valor del campo gcModeloArma
	 */
	public String getGcModeloArma() {
		return gcModeloArma;
	}

	/**
	 * Asigna el valor al campo gcModeloArma.
	 * @param gcModeloArma el valor gcModeloArma a asignar
	 */
	public void setGcModeloArma(String gcModeloArma) {
		this.gcModeloArma = gcModeloArma;
	}

	/**
	 * Método de acceso al campo glCondicionFisicaArmaId.
	 * @return El valor del campo glCondicionFisicaArmaId
	 */
	public Long getGlCondicionFisicaArmaId() {
		return glCondicionFisicaArmaId;
	}

	/**
	 * Asigna el valor al campo glCondicionFisicaArmaId.
	 * @param glCondicionFisicaArmaId el valor glCondicionFisicaArmaId a asignar
	 */
	public void setGlCondicionFisicaArmaId(Long glCondicionFisicaArmaId) {
		this.glCondicionFisicaArmaId = glCondicionFisicaArmaId;
	}

	/**
	 * Método de acceso al campo gcDescripcionArma.
	 * @return El valor del campo gcDescripcionArma
	 */
	public String getGcDescripcionArma() {
		return gcDescripcionArma;
	}

	/**
	 * Asigna el valor al campo gcDescripcionArma.
	 * @param gcDescripcionArma el valor gcDescripcionArma a asignar
	 */
	public void setGcDescripcionArma(String gcDescripcionArma) {
		this.gcDescripcionArma = gcDescripcionArma;
	}

	/**
	 * Método de acceso al campo serialversionuid.
	 * @return El valor del campo serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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

}
