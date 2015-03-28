/**
* Nombre del Programa : SustanciaForm.java
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
public class SustanciaForm extends GenericForm{

	private Long idSustancia;
	//Campos para la insercion desde cadena de custodia
	private String fechaLevCadCus; 
	private String origenEvdCadCus;
	private String cadenaCustodia;
	
	private Boolean anular;
	
	/**
	 * @return the idSustancia
	 */
	public Long getIdSustancia() {
		return idSustancia;
	}

	/**
	 * @param idSustancia the idSustancia to set
	 */
	public void setIdSustancia(Long idSustancia) {
		this.idSustancia = idSustancia;
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
	 *cantidad de sustancia
	 */
	private Long gsCantidadSustancia;
	    
	/**
	 *Id del tipo de sustancia
	 */
	private Long glTipoSustanciaId;
	 
	/**
	 *Id del tipo de ampaque de la sustancia
	 */
	private Long glEmpaqueSustanciaId;
	 
	/**
	 *Id de la unidad de medida
	 */
	private Long glUnidadMedidaId;
	
	/**
	 *Id de la condicion fisica de la sustancia
	 */
	private Long glCondicionFisicaSustanciaId;
	
	/**
	 * Breve descripcion de la sustancia
	 */
	private String gcDescripcionSustancia;

	/**
	 * Método de acceso al campo gsCantidadSustancia.
	 * @return El valor del campo gsCantidadSustancia
	 */
	public Long getGsCantidadSustancia() {
		return gsCantidadSustancia;
	}

	/**
	 * Asigna el valor al campo gsCantidadSustancia.
	 * @param gsCantidadSustancia el valor gsCantidadSustancia a asignar
	 */
	public void setGsCantidadSustancia(Long gsCantidadSustancia) {
		this.gsCantidadSustancia = gsCantidadSustancia;
	}

	/**
	 * Método de acceso al campo glTipoSustanciaId.
	 * @return El valor del campo glTipoSustanciaId
	 */
	public Long getGlTipoSustanciaId() {
		return glTipoSustanciaId;
	}

	/**
	 * Asigna el valor al campo glTipoSustanciaId.
	 * @param glTipoSustanciaId el valor glTipoSustanciaId a asignar
	 */
	public void setGlTipoSustanciaId(Long glTipoSustanciaId) {
		this.glTipoSustanciaId = glTipoSustanciaId;
	}

	/**
	 * Método de acceso al campo glEmpaqueSustanciaId.
	 * @return El valor del campo glEmpaqueSustanciaId
	 */
	public Long getGlEmpaqueSustanciaId() {
		return glEmpaqueSustanciaId;
	}

	/**
	 * Asigna el valor al campo glEmpaqueSustanciaId.
	 * @param glEmpaqueSustanciaId el valor glEmpaqueSustanciaId a asignar
	 */
	public void setGlEmpaqueSustanciaId(Long glEmpaqueSustanciaId) {
		this.glEmpaqueSustanciaId = glEmpaqueSustanciaId;
	}

	/**
	 * Método de acceso al campo glUnidadMedidaId.
	 * @return El valor del campo glUnidadMedidaId
	 */
	public Long getGlUnidadMedidaId() {
		return glUnidadMedidaId;
	}

	/**
	 * Asigna el valor al campo glUnidadMedidaId.
	 * @param glUnidadMedidaId el valor glUnidadMedidaId a asignar
	 */
	public void setGlUnidadMedidaId(Long glUnidadMedidaId) {
		this.glUnidadMedidaId = glUnidadMedidaId;
	}

	/**
	 * Método de acceso al campo glCondicionFisicaSustanciaId.
	 * @return El valor del campo glCondicionFisicaSustanciaId
	 */
	public Long getGlCondicionFisicaSustanciaId() {
		return glCondicionFisicaSustanciaId;
	}

	/**
	 * Asigna el valor al campo glCondicionFisicaSustanciaId.
	 * @param glCondicionFisicaSustanciaId el valor glCondicionFisicaSustanciaId a asignar
	 */
	public void setGlCondicionFisicaSustanciaId(Long glCondicionFisicaSustanciaId) {
		this.glCondicionFisicaSustanciaId = glCondicionFisicaSustanciaId;
	}

	/**
	 * Método de acceso al campo gcDescripcionSustancia.
	 * @return El valor del campo gcDescripcionSustancia
	 */
	public String getGcDescripcionSustancia() {
		return gcDescripcionSustancia;
	}

	/**
	 * Asigna el valor al campo gcDescripcionSustancia.
	 * @param gcDescripcionSustancia el valor gcDescripcionSustancia a asignar
	 */
	public void setGcDescripcionSustancia(String gcDescripcionSustancia) {
		this.gcDescripcionSustancia = gcDescripcionSustancia;
	}

	/**
	 * Método de acceso al campo serialversionuid.
	 * @return El valor del campo serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
