/**
* Nombre del Programa 			: ExplosivoForm.java
* Autor                     	: AlejandroGA
* Compania                    	: Ultrasist
* Proyecto                      : NSJP                    Fecha: 5 May 2011
* Marca de cambio        		: N/A
* Descripcion General    		: Clase forma para el objeto explosivo
* Programa Dependiente  		: N/A
* Programa Subsecuente 			: N/A
* Cond. de ejecucion        	: N/A
* Dias de ejecucion          	: N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                      	: N/A
* Compania               		: N/A
* Proyecto                 		: N/A                             Fecha: N/A
* Modificacion           		: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.objeto.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Clase forma para el objeto explosivo.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class ExplosivoForm extends GenericForm{
	
	private Long idExplosivo;
	//Campos para la insercion desde cadena de custodia
	private String fechaLevCadCus; 
	private String origenEvdCadCus;
	private String cadenaCustodia;
	
	private Boolean anular;

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
	 * @return the idExplosivo
	 */
	public Long getIdExplosivo() {
		return idExplosivo;
	}

	/**
	 * @param idExplosivo the idExplosivo to set
	 */
	public void setIdExplosivo(Long idExplosivo) {
		this.idExplosivo = idExplosivo;
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
	 *cantidad de explosivo
	 */
	private Long gsCantidadExplosivo;
	
	/**
	 *Id del tipo de explosivo
	 */
	private Long glTipoExplosivoId;
	
	/**
	 *Id de la unidad de medida
	 */
	private Long glUnidadMedidaExplosivoId;
	
	/**
	 *Id de la condicion fisica del explosivo
	 */
	private Long glCondicionFisicaExplosivoId;
	
	/**
	 * Breve descripcion del explosivo
	 */
	private String gcDescripcionExplosivo;

	/**
	 * Método de acceso al campo gsCantidadExplosivo.
	 * @return El valor del campo gsCantidadExplosivo
	 */
	public Long getGsCantidadExplosivo() {
		return gsCantidadExplosivo;
	}

	/**
	 * Asigna el valor al campo gsCantidadExplosivo.
	 * @param gsCantidadExplosivo el valor gsCantidadExplosivo a asignar
	 */
	public void setGsCantidadExplosivo(Long gsCantidadExplosivo) {
		this.gsCantidadExplosivo = gsCantidadExplosivo;
	}

	/**
	 * Método de acceso al campo glTipoExplosivoId.
	 * @return El valor del campo glTipoExplosivoId
	 */
	public Long getGlTipoExplosivoId() {
		return glTipoExplosivoId;
	}

	/**
	 * Asigna el valor al campo glTipoExplosivoId.
	 * @param glTipoExplosivoId el valor glTipoExplosivoId a asignar
	 */
	public void setGlTipoExplosivoId(Long glTipoExplosivoId) {
		this.glTipoExplosivoId = glTipoExplosivoId;
	}

	/**
	 * Método de acceso al campo glUnidadMedidaExplosivoId.
	 * @return El valor del campo glUnidadMedidaExplosivoId
	 */
	public Long getGlUnidadMedidaExplosivoId() {
		return glUnidadMedidaExplosivoId;
	}

	/**
	 * Asigna el valor al campo glUnidadMedidaExplosivoId.
	 * @param glUnidadMedidaExplosivoId el valor glUnidadMedidaExplosivoId a asignar
	 */
	public void setGlUnidadMedidaExplosivoId(Long glUnidadMedidaExplosivoId) {
		this.glUnidadMedidaExplosivoId = glUnidadMedidaExplosivoId;
	}

	/**
	 * Método de acceso al campo glCondicionFisicaExplosivoId.
	 * @return El valor del campo glCondicionFisicaExplosivoId
	 */
	public Long getGlCondicionFisicaExplosivoId() {
		return glCondicionFisicaExplosivoId;
	}

	/**
	 * Asigna el valor al campo glCondicionFisicaExplosivoId.
	 * @param glCondicionFisicaExplosivoId el valor glCondicionFisicaExplosivoId a asignar
	 */
	public void setGlCondicionFisicaExplosivoId(Long glCondicionFisicaExplosivoId) {
		this.glCondicionFisicaExplosivoId = glCondicionFisicaExplosivoId;
	}

	/**
	 * Método de acceso al campo gcDescripcionExplosivo.
	 * @return El valor del campo gcDescripcionExplosivo
	 */
	public String getGcDescripcionExplosivo() {
		return gcDescripcionExplosivo;
	}

	/**
	 * Asigna el valor al campo gcDescripcionExplosivo.
	 * @param gcDescripcionExplosivo el valor gcDescripcionExplosivo a asignar
	 */
	public void setGcDescripcionExplosivo(String gcDescripcionExplosivo) {
		this.gcDescripcionExplosivo = gcDescripcionExplosivo;
	}

	/**
	 * Método de acceso al campo serialversionuid.
	 * @return El valor del campo serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
