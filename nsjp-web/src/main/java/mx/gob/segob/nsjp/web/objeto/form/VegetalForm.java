/**
* Nombre del Programa 		: VegetalForm.java
* Autor                     : AlejandroGA
* Compania                  : Ultrasist
* Proyecto                  : NSJP                    Fecha: 9 May 2011
* Marca de cambio        	: N/A
* Descripcion General    	: Forma usada para ingresar vegetal jsp.
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
 * Forma usada para ingresar vegetal jsp.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class VegetalForm extends GenericForm{

	private Long idVegetal;
	private Boolean anular;
	//Campos para la insercion desde cadena de custodia
	private String fechaLevCadCus; 
	private String origenEvdCadCus;
	private String cadenaCustodia;
	
	/**
	 * @return the idVegetal
	 */
	public Long getIdVegetal() {
		return idVegetal;
	}

	/**
	 * @param idVegetal the idVegetal to set
	 */
	public void setIdVegetal(Long idVegetal) {
		this.idVegetal = idVegetal;
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
	 * Cantidad de vegetales
	 */
	private Long gsCantidadVegetal;
	
	/**
	 * id del tipo de vegetal
	 */
    private Long  glTipoVegetalId;
    
    /**
     * id de la unidad de medida del vegetal
     */
    private Long glUMedidaVegetalId;
    
    /**
	 *condicion del vegetal
	 */
	private Long glCondicionFisica;
	
	/**
	 * Breve descripcion del vegetal
	 */
	private String gcDescripcion;

	
	/**
	 * Método de acceso al campo gsCantidadVegetalId.
	 * @return El valor del campo gsCantidadVegetalId
	 */
	public Long getGsCantidadVegetal() {
		return gsCantidadVegetal;
	}

	/**
	 * Asigna el valor al campo gsCantidadVegetalId.
	 * @param gsCantidadVegetalId el valor gsCantidadVegetalId a asignar
	 */
	public void setGsCantidadVegetal(Long gsCantidadVegetal) {
		this.gsCantidadVegetal = gsCantidadVegetal;
	}

	/**
	 * Método de acceso al campo glTipoVegetalId.
	 * @return El valor del campo glTipoVegetalId
	 */
	public Long getGlTipoVegetalId() {
		return glTipoVegetalId;
	}

	/**
	 * Asigna el valor al campo glTipoVegetalId.
	 * @param glTipoVegetalId el valor glTipoVegetalId a asignar
	 */
	public void setGlTipoVegetalId(Long glTipoVegetalId) {
		this.glTipoVegetalId = glTipoVegetalId;
	}

	/**
	 * Método de acceso al campo glUMedidaVegetalId.
	 * @return El valor del campo glUMedidaVegetalId
	 */
	public Long getGlUMedidaVegetalId() {
		return glUMedidaVegetalId;
	}

	/**
	 * Asigna el valor al campo glUMedidaVegetalId.
	 * @param glUMedidaVegetalId el valor glUMedidaVegetalId a asignar
	 */
	public void setGlUMedidaVegetalId(Long glUMedidaVegetalId) {
		this.glUMedidaVegetalId = glUMedidaVegetalId;
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
