/**
* Nombre del Programa 		: DocumentoOficialForm.java
* Autor                     : AlejandroGA
* Compania                  : Ultrasist
* Proyecto                  : NSJP                    Fecha: 9 May 2011
* Marca de cambio        	: N/A
* Descripcion General    	: Forma usada para ingresar documento jsp.
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
 * Forma usada para ingresar documento jsp.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class DocumentoOficialForm extends GenericForm{
    
	private Long idDocOficial;
	private Boolean anular;
	
	//Campos para la insercion desde cadena de custodia
	private String fechaLevCadCus; 
	private String origenEvdCadCus;
	private String cadenaCustodia;
	
	/**
	 * @return the idDocOficial
	 */
	public Long getIdDocOficial() {
		return idDocOficial;
	}

	/**
	 * @param idDocOficial the idDocOficial to set
	 */
	public void setIdDocOficial(Long idDocOficial) {
		this.idDocOficial = idDocOficial;
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
	 *cantidad del documento oficial 
	 */
	private Long gsCantidadDocOficial;
	
	/**
	 *Id del tipo de documento oficial
	 */
    private Long glTipoDocOficialId;
    
	/**
	 *condicion del documento oficial
	 */
	private Long glCondicionFisica;
	
	/**
	 * Breve descripcion del documento oficial
	 */
	private String gcDescripcion;

	/**
	 * Método de acceso al campo gsCantidadDocOficial.
	 * @return El valor del campo gsCantidadDocOficial
	 */
	public Long getGsCantidadDocOficial() {
		return gsCantidadDocOficial;
	}

	/**
	 * Asigna el valor al campo gsCantidadDocOficial.
	 * @param gsCantidadDocOficial el valor gsCantidadDocOficial a asignar
	 */
	public void setGsCantidadDocOficial(Long gsCantidadDocOficial) {
		this.gsCantidadDocOficial = gsCantidadDocOficial;
	}

	/**
	 * Método de acceso al campo glTipoDocOficialId.
	 * @return El valor del campo glTipoDocOficialId
	 */
	public Long getGlTipoDocOficialId() {
		return glTipoDocOficialId;
	}

	/**
	 * Asigna el valor al campo glTipoDocOficialId.
	 * @param glTipoDocOficialId el valor glTipoDocOficialId a asignar
	 */
	public void setGlTipoDocOficialId(Long glTipoDocOficialId) {
		this.glTipoDocOficialId = glTipoDocOficialId;
	}

	/**
	 * Método de acceso al campo glCondicionFisica.
	 * @return El valor del campo glCondicionFisica
	 */
	public Long getGlCondicionFisica() {
		return glCondicionFisica;
	}

	/**
	 * Asigna el valor al campo glCondicionFisica.
	 * @param glCondicionFisica el valor glCondicionFisica a asignar
	 */
	public void setGlCondicionFisica(Long glCondicionFisica) {
		this.glCondicionFisica = glCondicionFisica;
	}

	/**
	 * Método de acceso al campo gcDescripcion.
	 * @return El valor del campo gcDescripcion
	 */
	public String getGcDescripcion() {
		return gcDescripcion;
	}

	/**
	 * Asigna el valor al campo gcDescripcion.
	 * @param gcDescripcion el valor gcDescripcion a asignar
	 */
	public void setGcDescripcion(String gcDescripcion) {
		this.gcDescripcion = gcDescripcion;
	}

	/**
	 * Método de acceso al campo serialversionuid.
	 * @return El valor del campo serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
