/**
* Nombre del Programa 		: JoyaForm.java
* Autor                     : AlejandroGA
* Compania                  : Ultrasist
* Proyecto                  : NSJP                    Fecha: 9 May 2011
* Marca de cambio        	: N/A
* Descripcion General    	: Forma usada para ingresar joya jsp.
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
 * Forma usada para ingresar joya jsp.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class JoyaForm extends GenericForm{
	
	private Long idJoya;
	private Boolean anular;
	//Campos para la insercion desde cadena de custodia
	private String fechaLevCadCus; 
	private String origenEvdCadCus;
	private String cadenaCustodia;
	
	/**
	 * @return the idJoya
	 */
	public Long getIdJoya() {
		return idJoya;
	}

	/**
	 * @param idJoya the idJoya to set
	 */
	public void setIdJoya(Long idJoya) {
		this.idJoya = idJoya;
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
	 *Serialize de la clase 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Cantidad de la joya
	 */
	private Long gsCantidadJoya;
    
	/**
	 * id del Tipo de Joya
	 */
	private Long  glTipoJoyaId;
    
	/**
	 * Material de la joya
	 */
	private String gcMaterialJoya;
    
	/**
	 *condicion de la Joya
	 */
	private Long glCondicionFisica;
	
	/**
	 * Breve descripcion de la Joya
	 */
	private String gcDescripcion;

	/**
	 * Método de acceso al campo gsCantidadJoya.
	 * @return El valor del campo gsCantidadJoya
	 */
	public Long getGsCantidadJoya() {
		return gsCantidadJoya;
	}

	/**
	 * Asigna el valor al campo gsCantidadJoya.
	 * @param gsCantidadJoya el valor gsCantidadJoya a asignar
	 */
	public void setGsCantidadJoya(Long gsCantidadJoya) {
		this.gsCantidadJoya = gsCantidadJoya;
	}

	/**
	 * Método de acceso al campo glTipoJoyaId.
	 * @return El valor del campo glTipoJoyaId
	 */
	public Long getGlTipoJoyaId() {
		return glTipoJoyaId;
	}

	/**
	 * Asigna el valor al campo glTipoJoyaId.
	 * @param glTipoJoyaId el valor glTipoJoyaId a asignar
	 */
	public void setGlTipoJoyaId(Long glTipoJoyaId) {
		this.glTipoJoyaId = glTipoJoyaId;
	}

	/**
	 * Método de acceso al campo gcMaterialJoya.
	 * @return El valor del campo gcMaterialJoya
	 */
	public String getGcMaterialJoya() {
		return gcMaterialJoya;
	}

	/**
	 * Asigna el valor al campo gcMaterialJoya.
	 * @param gcMaterialJoya el valor gcMaterialJoya a asignar
	 */
	public void setGcMaterialJoya(String gcMaterialJoya) {
		this.gcMaterialJoya = gcMaterialJoya;
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
